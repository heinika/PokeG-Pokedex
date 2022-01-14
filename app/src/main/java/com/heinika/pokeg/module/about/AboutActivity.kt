package com.heinika.pokeg.module.about

import android.widget.ImageView
import android.widget.TextView
import com.drakeet.about.*
import com.heinika.pokeg.BuildConfig
import com.heinika.pokeg.R

class AboutActivity : AbsAboutActivity() {
  override fun onCreateHeader(icon: ImageView, slogan: TextView, version: TextView) {
    icon.setImageResource(R.mipmap.ic_launcher)
    slogan.text = "PokeG By heinika"
    version.text = "v" + BuildConfig.VERSION_NAME
  }

  override fun onItemsCreated(items: MutableList<Any?>) {
    items.add(Category("介绍与帮助"))
    items.add(Card(getString(R.string.app_desc)))
    items.add(Category("Developers"))
    items.add(
      Contributor(
        R.drawable.about_avatar,
        "乌班图说",
        "Developer & 好奇猫",
        "http://weibo.com/u/6222257860"
      )
    )
    items.add(Contributor(R.drawable.chenlijin, "heinika", "Developer", "https://heinika.github.io"))
    items.add(Contributor(R.drawable.kabi, "卡比兽", "陪睡员"))
  }
}