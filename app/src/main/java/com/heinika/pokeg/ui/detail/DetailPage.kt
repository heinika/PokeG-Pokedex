package com.heinika.pokeg.ui.detail

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.app.ActivityCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.drakeet.multitype.MultiTypeAdapter
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.ConfigMMKV.favoritePokemons
import com.heinika.pokeg.ConfigMMKV.isFavoritePokemon
import com.heinika.pokeg.PokemonDataCache.pokemonList
import com.heinika.pokeg.R
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.databinding.PageDetailBinding
import com.heinika.pokeg.model.Ability
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.ui.detail.itemdelegate.MoveItemDelegate
import com.heinika.pokeg.ui.detail.itemdelegate.model.MoveItem
import com.heinika.pokeg.ui.main.MainViewModel
import com.heinika.pokeg.ui.main.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.utils.*
import com.heinika.pokeg.view.MoveMethodRadioButton
import com.skydoves.progressview.ProgressView
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import java.util.*


class DetailPage(
  private val pokemonRes: PokemonRes,
  private val activity: AppCompatActivity,
  private val pokemon: Pokemon,
  private val shareView: AppCompatImageView,
  pageStack: Stack<BasePage>
) : BasePage(activity, pageStack) {

  private val detailViewModel: DetailViewModel by activity.viewModels()
  private val mainViewModel: MainViewModel by activity.viewModels()

  private val binding: PageDetailBinding = PageDetailBinding.inflate(activity.layoutInflater)

  private var adapter: MultiTypeAdapter = MultiTypeAdapter()

  private val animatorDuration = 200L

  @SuppressLint("NotifyDataSetChanged")
  override fun showPage() {
    super.showPage()

    (binding.arrow.layoutParams as ConstraintLayout.LayoutParams).topMargin = StatusBarHeight.value

    Glide.with(binding.image)
      .load(pokemon.getImageUrl())
      .listener(
        GlidePalette.with(pokemon.getImageUrl())
          .use(BitmapPalette.Profile.MUTED_LIGHT)
          .intoCallBack { palette ->
            val light = palette?.lightVibrantSwatch?.rgb
            val domain = palette?.dominantSwatch?.rgb
            if (domain != null) {
              if (light != null) {
                Rainbow(binding.header).palette {
                  +color(domain)
                  +color(light)
                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
              } else {
                binding.header.setBackgroundColor(domain)
              }
            }
          }.crossfade(true)
      ).into(binding.image)
    binding.image.setOnLongClickListener {
      val dialog =
        AlertDialog.Builder(activity)
          .setTitle("保存图片")
          .setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
          }
          .setPositiveButton(R.string.ok) { dialog, _ ->
            Glide.with(activity)
              .asBitmap()
              .load(pokemon.getImageUrl())
              .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                  resource: Bitmap,
                  @Nullable transition: Transition<in Bitmap>?
                ) {
                  if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    if (checkPermission()) {
                      saveImageToDCIM(resource)
                    } else {
                      //请求权限
                      ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1
                      )
                    }
                  } else {
                    saveImageToDCIM(resource)
                  }
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {
                  Toast.makeText(activity.applicationContext, "保存失败", Toast.LENGTH_SHORT).show()
                }
              })
            dialog.dismiss()
          }
          .create()
      dialog.show()
      true
    }

    binding.favoriteCheckBox.isChecked = isFavoritePokemon(pokemon.id)
    binding.favoriteCheckBox.setOnClickListener {
      favoritePokemons = if (binding.favoriteCheckBox.isChecked) {
        favoritePokemons + pokemon.id.toString()
      } else {
        favoritePokemons - pokemon.id.toString()
      }
      mainViewModel.changeBasePokemonListFavorite(pokemon, binding.favoriteCheckBox.isChecked)
    }

    binding.progressHp.max = PokemonInfo.maxHp
    binding.progressAttach.max = PokemonInfo.maxAttack
    binding.progressDefense.max = PokemonInfo.maxDefense
    binding.progressSpAttack.max = PokemonInfo.maxSpecialAttack
    binding.progressSpDefense.max = PokemonInfo.maxSpecialDefense
    binding.progressSpd.max = PokemonInfo.maxSpeed

    detailViewModel.getPokemonBaseStatLiveData(pokemon.id).observe(activity) { baseStats ->
      initProgress(binding.progressSumBaseStatus, baseStats.sumOf { it.baseStat })
      initProgress(binding.progressHp, baseStats.first { it.statId.isHPStat }.baseStat)
      initProgress(
        binding.progressAttach,
        baseStats.first { it.statId.isAttackStat }.baseStat
      )
      initProgress(
        binding.progressDefense,
        baseStats.first { it.statId.isDefenseStat }.baseStat
      )
      initProgress(
        binding.progressSpAttack,
        baseStats.first { it.statId.isSAttackStat }.baseStat
      )
      initProgress(
        binding.progressSpDefense,
        baseStats.first { it.statId.isSDefenseStat }.baseStat
      )
      initProgress(binding.progressSpd, baseStats.first { it.statId.isSPeedStat }.baseStat)
    }

    detailViewModel.getPokemonAbilitiesLiveData(pokemon.id).observe(activity) { abilities ->
      abilities.forEachIndexed { index, ability ->
        when (index) {
          0 -> setAbility(binding.ability1, binding.ability1Desc, ability)
          1 -> setAbility(binding.ability2, binding.ability2Desc, ability)
          2 -> setAbility(binding.ability3, binding.ability3Desc, ability)
        }
      }
    }

    detailViewModel.getPokemonTypeLiveData(pokemon.id).observe(activity) { types ->
      val type1Id = types[0].typeId
      binding.type1Text.text = pokemonRes.getTypeString(type1Id)
      binding.type1Text.background.let {
        it.setTint(pokemonRes.getTypeColor(type1Id))
        it.alpha = 155
      }
      if (types.size == 2) {
        val typeId = types[1].typeId
        binding.type2Text.isVisible = true
        binding.type2Text.text = pokemonRes.getTypeString(typeId)
        binding.type2Text.background.let {
          it.setTint(pokemonRes.getTypeColor(typeId))
          it.alpha = 155
        }
      }
    }

    detailViewModel.getPokemonNewLiveData(pokemon.id).observe(activity) { pokemon ->
      binding.name.text = pokemonRes.getNameById(pokemon.id, pokemon.identifier)
      binding.index.text = pokemon.getFormatId()
      binding.weight.text = pokemon.getFormatWeight()
      binding.height.text = pokemon.getFormatHeight()

      detailViewModel.getPokemonSpecieNameLiveData(pokemon.speciesId)
        .observe(activity) { names ->
          binding.eNameText.text = names.first { it.localLanguageId.isEnId }.name
          binding.jNameText.text = names.first { it.localLanguageId.isJaId }.name
          binding.race.text = names.first { it.localLanguageId.isCnId }.genus
        }

      detailViewModel.getPokemonSpecieLiveData(pokemon.speciesId)
        .observe(activity) { species ->
          binding.eggStepsText.text = species.getEggSteps()
          binding.generationText.text = pokemonRes.getGeneration(species.generationId)
          binding.shapeText.text = pokemonRes.getShape(species.shapeId)
          if (species.habitatId.isNotEmpty()) {
            binding.habitatText.isVisible = true
            binding.habitatText.text = pokemonRes.getHabitat(species.habitatId.toInt())
          }
          binding.growSpeedText.text = pokemonRes.getGrowRate(species.growthRateId)
          binding.babyText.isVisible = species.isBaby.toBoolean
          binding.legendaryText.isVisible = species.isLegendary.toBoolean
          binding.mythicalText.isVisible = species.isMythical.toBoolean
        }


      detailViewModel.getSpecieEggGroupLiveData(pokemon.speciesId).observe(activity) { list ->
        binding.eggGroupText.text =
          list.joinToString { pokemonRes.getEggGroupName(it.eggGroupId) }
            .replace(",", " ")
      }

      detailViewModel.speciesAllOtherFormsLiveData(pokemon.speciesId, pokemon.id)
        .observe(activity) { forms ->
          if (forms.isNotEmpty()) {
            binding.formsRecyclerView.isVisible = true
            binding.formsRecyclerView.layoutManager = LinearLayoutManager(activity)
            binding.formsRecyclerView.adapter = MultiTypeAdapter().apply {
              register(
                PokemonItemDelegate(
                  pokemonRes,
                  onItemClick = { imageView, pokemon ->
                    DetailPage(
                      pokemonRes,
                      activity,
                      pokemon,
                      imageView,
                      pageStack
                    ).also {
                      it.showPage()
                    }
                  },
                  onFavoriteClick = { pokemon, isChecked ->
                    favoritePokemons = if (isChecked) {
                      favoritePokemons + pokemon.id.toString()
                    } else {
                      favoritePokemons - pokemon.id.toString()
                    }
                  }
                )
              )
              items = forms
            }
            adapter.notifyDataSetChanged()
          }
        }

      detailViewModel.getSpecieEvolutionChainLiveData(pokemon.speciesId)
        .observe(activity) { chainList ->
          if (chainList.isNotEmpty()) {
            binding.evolutionCard.isVisible = true
            chainList.forEach { chain ->
              binding.evolutionLinear.addView(
                LayoutInflater.from(activity)
                  .inflate(
                    R.layout.item_evolution,
                    binding.evolutionLinear,
                    false
                  ).also { view ->
                    val fromImage =
                      view.findViewById<AppCompatImageView>(R.id.fromImageView).apply {
                        if (pokemon.id != chain.evolvedFromSpeciesId) {
                          toTargetDetailPage(chain.evolvedFromSpeciesId)
                        }
                      }
                    val toImage =
                      view.findViewById<AppCompatImageView>(R.id.toImageView).apply {
                        if (pokemon.id != chain.evolvedToSpeciesId) {
                          toTargetDetailPage(chain.evolvedToSpeciesId)
                        }
                      }
                    val descText =
                      view.findViewById<AppCompatTextView>(R.id.descText)

                    descText.text = chain.getDescText(pokemonRes)

                    Glide.with(fromImage)
                      .load(chain.getSpeciesFromImageUrl())
                      .into(fromImage)

                    Glide.with(toImage)
                      .load(chain.getSpeciesToImageUrl())
                      .into(toImage)
                  })
            }
          }
        }

      detailViewModel.getSpecieFlavorTextsLiveData(pokemon.speciesId).observe(activity) {
        binding.description.text = it
      }
    }

    binding.arrow.setOnClickListener {
      onBackPressed()
    }

    adapter.register(MoveItemDelegate(pokemonRes))
    binding.moveRecyclerView.layoutManager = LinearLayoutManager(activity)
    binding.moveRecyclerView.adapter = adapter

    content.addView(binding.root)

    binding.image.visibility = View.INVISIBLE
    binding.root.visibility = View.INVISIBLE

    binding.root.post {
      ValueAnimator.ofFloat(0f, 1f).apply {
        val startLocation = IntArray(2)
        shareView.getLocationInWindow(startLocation)
        val endImageLocation = IntArray(2)
        binding.image.getLocationInWindow(endImageLocation)
        val endRootLocation = IntArray(2)
        binding.root.getLocationInWindow(endRootLocation)

        val startRootScale = shareView.height / binding.image.height.toFloat()
        val startRootCenterX = startLocation[0] + shareView.width / 2
        val startRootCenterY =
          startLocation[1] + binding.root.height.toFloat() / 2 * startRootScale - endImageLocation[1] * startRootScale
        val endRootCenterX = endRootLocation[0] + binding.root.width / 2
        val endRootCenterY = endRootLocation[1] + binding.root.height / 2


        doOnStart {
          binding.image.alpha = 1f
          binding.image.isVisible = true
          binding.root.isVisible = true
          binding.root.background.alpha = 0
        }
        doOnEnd {
          binding.root.background.alpha = 255
          detailViewModel.getPokemonMoveVersionLiveData(pokemon.id, pokemon.speciesId)
            .observe(activity) { versions ->
              val defaultVersion = ConfigMMKV.defaultVersion
              val version =
                if (versions.contains(defaultVersion)) defaultVersion else versions.last()
              binding.moveVersionText.text =
                pokemonRes.getVersionName(version)

              val selectVersionDialog = AlertDialog.Builder(activity).setTitle("选择版本")
                .setItems(
                  versions.map { pokemonRes.getVersionName(it) }.toTypedArray()
                ) { dialog, index ->
                  binding.moveVersionText.text =
                    pokemonRes.getVersionName(versions[index])
                  refreshMoveTable(versions[index])
                  dialog.dismiss()
                }.create()
              binding.moveVersionText.setOnClickListener {
                selectVersionDialog.show()
              }

              refreshMoveTable(version)
            }
        }

        addUpdateListener { valueAnimator ->
          binding.root.background.alpha = (valueAnimator.animatedFraction * 255).toInt()


          val rootTranslationX =
            (startRootCenterX - endRootCenterX) * (1 - valueAnimator.animatedFraction)
          val rootTranslationY =
            (startRootCenterY - endRootCenterY) * (1 - valueAnimator.animatedFraction)
          val rootScale =
            startRootScale + (1 - startRootScale) * valueAnimator.animatedFraction

          binding.root.translationX = rootTranslationX
          binding.root.translationY = rootTranslationY
          binding.root.scaleX = rootScale
          binding.root.scaleY = rootScale

          binding.constraintLayout.children.forEach { view ->
            if (view != binding.image) {
              view.alpha = valueAnimator.animatedFraction
            }
          }
        }

        duration = animatorDuration
        start()
      }
    }
  }

  private fun saveImageToDCIM(resource: Bitmap) {
    PhotoUtils.saveBitmap2Gallery2(activity, resource, pokemon.getCName(pokemonRes))
    Toast.makeText(activity.applicationContext, "已保存到相册", Toast.LENGTH_SHORT).show()
  }

  private fun AppCompatImageView.toTargetDetailPage(targetId: Int) {
    setOnClickListener {
      DetailPage(
        pokemonRes,
        activity,
        pokemonList.first { it.id == targetId },
        this,
        pageStack
      ).also {
        it.showPage()
      }
    }
  }

  private fun initProgress(progressView: ProgressView, it: Int) {
    progressView.progress = it.toFloat()
    progressView.labelText = it.toString()
  }

  private fun refreshMoveTable(version: Int) {
    binding.moveMethodRadioGroup.removeAllViews()
    detailViewModel.getPokemonMoveLiveData(pokemon.id, pokemon.speciesId, version)
      .observe(activity) { pokemonMoveMap ->
        pokemonMoveMap.keys.sortedBy {
          when (it) {
            2 -> 3
            3 -> 4
            4 -> 2
            else -> it
          }
        }.forEach { methodId ->
          binding.moveMethodRadioGroup.addView(MoveMethodRadioButton(activity).apply {
            text = pokemonRes.getMoveMethodName(methodId)
            setOnClickListener {
              refreshMoveItem(pokemonMoveMap, methodId)
            }
          }
          )
        }
        binding.moveMethodRadioGroup.check(binding.moveMethodRadioGroup.children.first().id)

        if (binding.progressSpDefense.isAnimating) {
          binding.moveRecyclerView.postDelayed({
            refreshMoveItem(pokemonMoveMap, 1)
          }, binding.progressSpDefense.duration)
        } else {
          refreshMoveItem(pokemonMoveMap, 1)
        }
      }
  }

  private fun refreshMoveItem(pokemonMoveMap: Map<Int, List<MoveItem>>, methodId: Int) {
    pokemonMoveMap[methodId]?.let { moveItemList ->
      val diffResult =
        DiffUtil.calculateDiff(AdapterDiffUtils(adapter.items, moveItemList), true)
      adapter.items = moveItemList
      diffResult.dispatchUpdatesTo(adapter)
    }
  }

  private fun setAbility(
    abilityText: AppCompatTextView,
    descText: AppCompatTextView,
    ability: Ability
  ) {
    abilityText.isVisible = true
    abilityText.text = ability.cname
    descText.text = ability.synopsis
    abilityText.setOnClickListener {
      descText.isVisible = !descText.isVisible
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()

    ValueAnimator.ofFloat(0f, 1f).apply {
      val endLocation = IntArray(2)
      shareView.getLocationInWindow(endLocation)
      val startImageLocation = IntArray(2)
      binding.image.getLocationInWindow(startImageLocation)
      val startRootLocation = IntArray(2)
      binding.root.getLocationInWindow(startRootLocation)

      val endRootScale = shareView.height / binding.image.height.toFloat()
      val endRootCenterX = endLocation[0] + shareView.width / 2
      val endRootCenterY =
        endLocation[1] + binding.root.height.toFloat() / 2 * endRootScale - startImageLocation[1] * endRootScale
      val startRootCenterX = startRootLocation[0] + binding.root.width / 2
      val startRootCenterY = startRootLocation[1] + binding.root.height / 2

      addUpdateListener { valueAnimator ->
        val translateX =
          0 + (endRootCenterX - startRootCenterX) * valueAnimator.animatedFraction
        val translateY =
          0 + (endRootCenterY - startRootCenterY) * valueAnimator.animatedFraction
        val scale = 1 - (1 - endRootScale) * valueAnimator.animatedFraction
        binding.root.translationX = translateX
        binding.root.translationY = translateY
        binding.root.scaleX = scale
        binding.root.scaleY = scale

        binding.root.background.alpha = ((1 - valueAnimator.animatedFraction) * 255).toInt()
        binding.constraintLayout.children.forEach { view ->
          if (view != binding.image) {
            view.alpha = 1 - valueAnimator.animatedFraction
          }
        }
      }

      doOnEnd {
        content.removeView(binding.root)
        binding.root.background.alpha = 255
        binding.image.translationX = 0f
        binding.image.translationY = 0f
        binding.image.scaleX = 1f
        binding.image.scaleY = 1f
      }

      duration = animatorDuration
      start()
    }
  }

  private fun checkPermission(): Boolean {
    return ActivityCompat.checkSelfPermission(
      activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
  }
}