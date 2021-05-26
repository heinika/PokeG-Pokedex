package com.heinika.pokeg.ui.detail

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.drakeet.multitype.MultiTypeAdapter
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.databinding.PageDetailBinding
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.ui.detail.itemdelegate.MoveItemDelegate
import com.heinika.pokeg.ui.detail.itemdelegate.model.MoveItem
import com.heinika.pokeg.utils.AdapterDiffUtils
import com.heinika.pokeg.utils.PokemonRes
import com.heinika.pokeg.view.MoveMethodRadioButton
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

  private val binding: PageDetailBinding = PageDetailBinding.inflate(activity.layoutInflater)

  private var adapter: MultiTypeAdapter = MultiTypeAdapter()

  private val animatorDuration = 200L

  override fun showPage() {
    super.showPage()

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

    binding.progressHp.max = PokemonInfo.maxHp
    binding.progressAttach.max = PokemonInfo.maxAttack
    binding.progressDefense.max = PokemonInfo.maxDefense
    binding.progressSpAttack.max = PokemonInfo.maxSpecialAttack
    binding.progressSpDefense.max = PokemonInfo.maxSpecialDefense
    binding.progressSpd.max = PokemonInfo.maxSpeed

    detailViewModel.getPokemonInfoLiveData(pokemon).observe(activity) { pokemonInfo ->
      binding.index.text = pokemonInfo.getIdString()
      binding.name.text = pokemonRes.getNameById(pokemonInfo.id, pokemonInfo.name)
      binding.weight.text = pokemonInfo.getWeightString()
      binding.height.text = pokemonInfo.getHeightString()
      binding.progressHp.progress = pokemonInfo.hp.toFloat()
      binding.progressHp.labelText = pokemonInfo.hp.toString()
      binding.progressAttach.progress = pokemonInfo.attack.toFloat()
      binding.progressAttach.labelText = pokemonInfo.attack.toString()
      binding.progressDefense.progress = pokemonInfo.defense.toFloat()
      binding.progressDefense.labelText = pokemonInfo.defense.toString()
      binding.progressSpAttack.progress = pokemonInfo.specialAttack.toFloat()
      binding.progressSpAttack.labelText = pokemonInfo.specialAttack.toString()
      binding.progressSpDefense.progress = pokemonInfo.specialDefense.toFloat()
      binding.progressSpDefense.labelText = pokemonInfo.specialDefense.toString()
      binding.progressSpd.progress = pokemonInfo.speed.toFloat()
      binding.progressSpd.labelText = pokemonInfo.speed.toString()
      binding.race.text = pokemonInfo.race
      binding.description.text = pokemonInfo.description

      setAbility(binding.ability1, binding.ability1Desc, pokemonInfo.ability1)
      setAbility(binding.ability2, binding.ability2Desc, pokemonInfo.ability2)
      setAbility(binding.ability3, binding.ability3Desc, pokemonInfo.ability3)

      pokemonRes.getMegaDrawable(pokemonInfo.id).let { drawableList ->
        if (drawableList.size == 1) {
          drawableList[0]?.let {
            binding.image1.isVisible = true
            binding.image1.setImageDrawable(it)
          }
        } else {
          binding.image1.isVisible = true
          binding.image1.setImageDrawable(drawableList[0])
          binding.image2.isVisible = true
          binding.image2.setImageDrawable(drawableList[1])
        }
      }

      pokemonRes.getMaxDrawable(pokemonInfo.id)?.let {
        binding.image3.isVisible = true
        binding.image3.setImageDrawable(it)
      }

      val type1Name = pokemonInfo.types[0].type.name
      binding.type1Text.text = pokemonRes.getTypeString(type1Name)
      binding.type1Text.background.let {
        it.setTint(pokemonRes.getTypeColor(type1Name))
        it.alpha = 155
      }
      if (pokemonInfo.types.size == 2) {
        val type2Name = pokemonInfo.types[1].type.name
        binding.type2Text.isVisible = true
        binding.type2Text.text = pokemonRes.getTypeString(type2Name)
        binding.type2Text.background.let {
          it.setTint(pokemonRes.getTypeColor(type2Name))
          it.alpha = 155
        }
      }
    }

    detailViewModel.isLoading.observe(activity) { isLoading ->
      binding.progressbar.isVisible = isLoading
    }

    detailViewModel.toastMessage.observe(activity) { toastMessage ->
      Toast.makeText(activity.applicationContext, toastMessage, Toast.LENGTH_LONG).show()
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
        }
        doOnEnd {
          detailViewModel.getPokemonMoveVersionLiveData(pokemon.id).observe(activity) { versions ->
            binding.moveVersionText.text = pokemonRes.getVersionName(versions.last())

            val selectVersionDialog = AlertDialog.Builder(activity).setTitle("选择版本")
              .setItems(
                versions.map { pokemonRes.getVersionName(it) }.toTypedArray()
              ) { dialog, index ->
                binding.moveVersionText.text = pokemonRes.getVersionName(versions[index])
                refreshMoveTable(versions[index])
                dialog.dismiss()
              }.create()
            binding.moveVersionText.setOnClickListener {
              selectVersionDialog.show()
            }

            refreshMoveTable(versions.last())
          }
        }

        addUpdateListener { valueAnimator ->
          binding.root.background.alpha = (valueAnimator.animatedFraction * 255).toInt()


          val rootTranslationX =
            (startRootCenterX - endRootCenterX) * (1 - valueAnimator.animatedFraction)
          val rootTranslationY =
            (startRootCenterY - endRootCenterY) * (1 - valueAnimator.animatedFraction)
          val rootScale = startRootScale + (1 - startRootScale) * valueAnimator.animatedFraction

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

  private fun refreshMoveTable(version: Int) {
    binding.moveMethodRadioGroup.removeAllViews()
    detailViewModel.getPokemonMoveLiveData(pokemon.id, version)
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
      adapter.notifyDataSetChanged()
      diffResult.dispatchUpdatesTo(adapter)
    }
  }

  private fun setAbility(
    abilityView: AppCompatTextView,
    descView: AppCompatTextView,
    abilityNum: Int
  ) {
    if (abilityNum == 0) {
      abilityView.isVisible = false
    } else {
      abilityView.text = pokemonRes.getAbilityName(abilityNum)
      descView.text = pokemonRes.getAbilityDesc(abilityNum)
      abilityView.setOnClickListener {
        descView.isVisible = !descView.isVisible
      }
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
        val translateX = 0 + (endRootCenterX - startRootCenterX) * valueAnimator.animatedFraction
        val translateY = 0 + (endRootCenterY - startRootCenterY) * valueAnimator.animatedFraction
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
        binding.image.translationX = 0f
        binding.image.translationY = 0f
        binding.image.scaleX = 1f
        binding.image.scaleY = 1f
      }

      duration = animatorDuration
      start()
    }
  }
}