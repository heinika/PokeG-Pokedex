package com.heinika.pokeg.info

import com.heinika.pokeg.R

enum class Nature(
  stringId: Int,
  growEasyStatus: BaseStatus?,
  growHardStatus: BaseStatus?,
  tasteLikeId: Int,
  tasteDislikeId: Int
) {
  Hardy(R.string.Hardy,null,null,0,1),
  Lonely(R.string.Lonely, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Adamant(R.string.Adamant, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Naughty(R.string.Naughty, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Brave(R.string.Brave, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Bold(R.string.Bold, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Docile(R.string.Docile, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Impish(R.string.Impish, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Lax(R.string.Lax, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Relaxed(R.string.Relaxed, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Modest(R.string.Modest, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Mild(R.string.Mild, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Bashful(R.string.Bashful, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Rash(R.string.Rash, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Quiet(R.string.Quiet, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Calm(R.string.Calm, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Gentle(R.string.Gentle, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Careful(R.string.Careful, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Quirky(R.string.Quirky, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Sassy(R.string.Sassy, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Timid(R.string.Timid, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Hasty(R.string.Hasty, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Jolly(R.string.Jolly, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Naive(R.string.Naive, BaseStatus.ATK, BaseStatus.DEF,0,1),
  Serious(R.string.Serious, BaseStatus.ATK, BaseStatus.DEF,0,1),
}