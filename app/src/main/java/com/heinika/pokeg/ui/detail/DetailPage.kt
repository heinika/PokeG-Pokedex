package com.heinika.pokeg.ui.detail

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.children
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.databinding.PageDetailBinding
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.utils.PokemonTypeUtils
import com.heinika.pokeg.utils.SpacesItemDecoration
import com.skydoves.androidribbon.ribbonView
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import java.util.*

class DetailPage(
  private val activity: AppCompatActivity,
  private val pokemon: Pokemon,
  private val shareView: AppCompatImageView,
  pageStack: Stack<BasePage>
) : BasePage(activity,pageStack) {

  private val detailViewModel: DetailViewModel by activity.viewModels()

  private val binding: PageDetailBinding = PageDetailBinding.inflate(activity.layoutInflater)

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

    detailViewModel.getPokemonInfoLiveData(pokemon).observe(activity) {
      binding.index.text = it.getIdString()
      binding.name.text = it.name
      binding.weight.text = it.getWeightString()
      binding.height.text = it.getHeightString()
      binding.progressHp.progress = it.hp.toFloat()
      binding.progressHp.labelText = it.hp.toString()
      binding.progressAttach.progress = it.attack.toFloat()
      binding.progressAttach.labelText = it.attack.toString()
      binding.progressDefense.progress = it.defense.toFloat()
      binding.progressDefense.labelText = it.defense.toString()
      binding.progressSpAttack.progress = it.specialAttack.toFloat()
      binding.progressSpAttack.labelText = it.specialAttack.toString()
      binding.progressSpDefense.progress = it.specialDefense.toFloat()
      binding.progressSpDefense.labelText = it.specialDefense.toString()
      binding.progressSpd.progress = it.speed.toFloat()
      binding.progressSpd.labelText = it.speed.toString()

      binding.ribbonRecyclerView.clear()
      for (type in it.types) {
        with(binding.ribbonRecyclerView) {
          addRibbon(
            ribbonView(context) {
              setText(type.type.name)
              setTextColor(Color.WHITE)
              setPaddingLeft(84f)
              setPaddingRight(84f)
              setPaddingTop(2f)
              setPaddingBottom(10f)
              setTextSize(16f)
              setRibbonRadius(120f)
              setTextStyle(Typeface.BOLD)
              setRibbonBackgroundColorResource(
                PokemonTypeUtils.getTypeColor(type.type.name)
              )
            }.apply {
              maxLines = 1
              gravity = android.view.Gravity.CENTER
            }
          )
          addItemDecoration(SpacesItemDecoration())
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
      exitPage()
    }

    content.addView(binding.root)

    binding.image.visibility = View.INVISIBLE
    binding.root.visibility = View.INVISIBLE

    binding.root.post {
      ValueAnimator.ofFloat(0f, 1f).apply {
        val startLocation = IntArray(2)
        shareView.getLocationInWindow(startLocation)
        val endLocation = IntArray(2)
        binding.image.getLocationInWindow(endLocation)
        val startCenterX = startLocation[0] + shareView.width / 2
        val startCenterY = startLocation[1] + shareView.height / 2
        val endCenterX = endLocation[0] + binding.image.width / 2
        val endCenterY = endLocation[1] + binding.image.height / 2
        val startScale = shareView.width / binding.image.width.toFloat()
        doOnStart {
          binding.image.alpha = 1f
          binding.image.isVisible = true
          binding.root.isVisible = true
        }
        addUpdateListener { valueAnimator ->
          binding.image.translationX =
            (startCenterX - endCenterX) * (1 - valueAnimator.animatedFraction)
          binding.image.translationY =
            (startCenterY - endCenterY) * (1 - valueAnimator.animatedFraction)
          binding.image.scaleX = startScale + (1 - startScale) * valueAnimator.animatedFraction
          binding.image.scaleY = startScale + (1 - startScale) * valueAnimator.animatedFraction

          binding.root.background.alpha = (valueAnimator.animatedFraction * 255).toInt()
          binding.constraintLayout.children.forEach { view ->
            if (view != binding.image) {
              view.alpha = valueAnimator.animatedFraction
              view.translationX =
                (startCenterX - endCenterX) * (1 - valueAnimator.animatedFraction)
              view.translationY =
                (startCenterY - endCenterY) * (1 - valueAnimator.animatedFraction)
              view.scaleX = startScale + (1 - startScale) * valueAnimator.animatedFraction
              view.scaleY = startScale + (1 - startScale) * valueAnimator.animatedFraction
            }
          }
        }
        duration = 500L
        start()
      }
    }
  }

  override fun exitPage() {
    super.exitPage()

    ValueAnimator.ofFloat(0f, 1f).apply {

      duration = 500L

      val startLocation = IntArray(2)
      binding.image.getLocationInWindow(startLocation)
      val endLocation = IntArray(2)
      shareView.getLocationInWindow(endLocation)
      val startCenterX = startLocation[0] + binding.image.width / 2
      val startCenterY = startLocation[1] + binding.image.height / 2
      val endCenterX = endLocation[0] + shareView.width / 2
      val endCenterY = endLocation[1] + shareView.height / 2
      val endScale = shareView.width / binding.image.width.toFloat()

      addUpdateListener { valueAnimator ->
        val translateX = 0 + (endCenterX - startCenterX) * valueAnimator.animatedFraction
        val translateY = 0 + (endCenterY - startCenterY) * valueAnimator.animatedFraction
        val scale = 1 - (1 - endScale) * valueAnimator.animatedFraction
        binding.image.translationX = translateX
        binding.image.translationY = translateY
        binding.image.scaleX = scale
        binding.image.scaleY = scale

        binding.root.background.alpha = ((1 - valueAnimator.animatedFraction) * 255).toInt()
        binding.constraintLayout.children.forEach { view ->
          if (view != binding.image) {
            view.alpha = 1 - valueAnimator.animatedFraction
            view.translationX = translateX
            view.translationY = translateY
            view.scaleX = scale
            view.scaleY = scale
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

      start()
    }
  }
}