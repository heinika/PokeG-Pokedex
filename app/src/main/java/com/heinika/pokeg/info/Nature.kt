package com.heinika.pokeg.info

import com.heinika.pokeg.R

enum class Nature(
  val stringId: Int,
  growEasyStatus: BaseStatus?,
  growHardStatus: BaseStatus?,
  val tasteLikeId: Int,
  val tasteDislikeId: Int
) {
  Hardy(R.string.Hardy, null, null, R.string.teste_equal, R.string.teste_equal),
  Lonely(R.string.Lonely, BaseStatus.ATK, BaseStatus.DEF, R.string.hot, R.string.acid),
  Adamant(R.string.Adamant, BaseStatus.ATK, BaseStatus.SP_ATK, R.string.hot, R.string.astringent),
  Naughty(R.string.Naughty, BaseStatus.ATK, BaseStatus.SP_DEF, R.string.hot, R.string.bitter),
  Brave(R.string.Brave, BaseStatus.ATK, BaseStatus.SPEED, R.string.hot, R.string.sweet),
  Bold(R.string.Bold, BaseStatus.DEF, BaseStatus.ATK, R.string.acid, R.string.hot),
  Docile(R.string.Docile, null, null, R.string.teste_equal, R.string.teste_equal),
  Impish(R.string.Impish, BaseStatus.DEF, BaseStatus.SP_ATK, R.string.acid, R.string.astringent),
  Lax(R.string.Lax, BaseStatus.DEF, BaseStatus.SP_DEF, R.string.acid, R.string.bitter),
  Relaxed(R.string.Relaxed, BaseStatus.DEF, BaseStatus.SPEED, R.string.acid, R.string.sweet),
  Modest(R.string.Modest, BaseStatus.SP_ATK, BaseStatus.ATK, R.string.astringent, R.string.hot),
  Mild(R.string.Mild, BaseStatus.SP_ATK, BaseStatus.DEF, R.string.astringent, R.string.acid),
  Bashful(R.string.Bashful, null, null, R.string.teste_equal, R.string.teste_equal),
  Rash(R.string.Rash, BaseStatus.SP_ATK, BaseStatus.SP_DEF, R.string.astringent, R.string.bitter),
  Quiet(R.string.Quiet, BaseStatus.SP_ATK, BaseStatus.SPEED, R.string.astringent, R.string.sweet),
  Calm(R.string.Calm, BaseStatus.SP_DEF, BaseStatus.ATK, R.string.bitter, R.string.hot),
  Gentle(R.string.Gentle, BaseStatus.SP_DEF, BaseStatus.DEF, R.string.bitter, R.string.acid),
  Careful(R.string.Careful, BaseStatus.SP_DEF, BaseStatus.SP_ATK, R.string.bitter, R.string.astringent),
  Quirky(R.string.Quirky, null, null, R.string.teste_equal, R.string.teste_equal),
  Sassy(R.string.Sassy, BaseStatus.SP_DEF, BaseStatus.SPEED, R.string.bitter, R.string.sweet),
  Timid(R.string.Timid, BaseStatus.SPEED, BaseStatus.ATK, R.string.sweet, R.string.hot),
  Hasty(R.string.Hasty, BaseStatus.SPEED, BaseStatus.DEF, R.string.sweet, R.string.acid),
  Jolly(R.string.Jolly, BaseStatus.SPEED, BaseStatus.SP_ATK, R.string.sweet, R.string.astringent),
  Naive(R.string.Naive, BaseStatus.SPEED, BaseStatus.SP_DEF, R.string.sweet, R.string.bitter),
  Serious(R.string.Serious, null, null, R.string.teste_equal, R.string.teste_equal);

  val growEasyStringId = growEasyStatus?.resId ?: R.string.teste_equal
  val growHardStringId = growHardStatus?.resId ?: R.string.teste_equal
}