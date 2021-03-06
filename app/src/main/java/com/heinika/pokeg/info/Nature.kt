package com.heinika.pokeg.info


import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.*

enum class Nature(
  val stringId: Int,
  growEasyStatus: BaseStatus?,
  growHardStatus: BaseStatus?,
  val tasteLikeId: Int,
  val tasteDislikeId: Int,
  val color: Color
) {
  Hardy(R.string.Hardy, null, null, R.string.teste_equal, R.string.teste_equal, HardyColor),
  Lonely(R.string.Lonely, BaseStatus.ATK, BaseStatus.DEF, R.string.hot, R.string.acid,LonelyColor),
  Adamant(R.string.Adamant, BaseStatus.ATK, BaseStatus.SP_ATK, R.string.hot, R.string.astringent,AdamantColor),
  Naughty(R.string.Naughty, BaseStatus.ATK, BaseStatus.SP_DEF, R.string.hot, R.string.bitter,NaughtyColor),
  Brave(R.string.Brave, BaseStatus.ATK, BaseStatus.SPEED, R.string.hot, R.string.sweet,BraveColor),
  Bold(R.string.Bold, BaseStatus.DEF, BaseStatus.ATK, R.string.acid, R.string.hot,BoldColor),
  Docile(R.string.Docile, null, null, R.string.teste_equal, R.string.teste_equal,DocileColor),
  Impish(R.string.Impish, BaseStatus.DEF, BaseStatus.SP_ATK, R.string.acid, R.string.astringent,ImpishColor),
  Lax(R.string.Lax, BaseStatus.DEF, BaseStatus.SP_DEF, R.string.acid, R.string.bitter,LaxColor),
  Relaxed(R.string.Relaxed, BaseStatus.DEF, BaseStatus.SPEED, R.string.acid, R.string.sweet,RelaxedColor),
  Modest(R.string.Modest, BaseStatus.SP_ATK, BaseStatus.ATK, R.string.astringent, R.string.hot,ModestColor),
  Mild(R.string.Mild, BaseStatus.SP_ATK, BaseStatus.DEF, R.string.astringent, R.string.acid,MildColor),
  Bashful(R.string.Bashful, null, null, R.string.teste_equal, R.string.teste_equal,BashfulColor),
  Rash(R.string.Rash, BaseStatus.SP_ATK, BaseStatus.SP_DEF, R.string.astringent, R.string.bitter,RashColor),
  Quiet(R.string.Quiet, BaseStatus.SP_ATK, BaseStatus.SPEED, R.string.astringent, R.string.sweet,QuietColor),
  Calm(R.string.Calm, BaseStatus.SP_DEF, BaseStatus.ATK, R.string.bitter, R.string.hot,CalmColor),
  Gentle(R.string.Gentle, BaseStatus.SP_DEF, BaseStatus.DEF, R.string.bitter, R.string.acid,GentleColor),
  Careful(R.string.Careful, BaseStatus.SP_DEF, BaseStatus.SP_ATK, R.string.bitter, R.string.astringent,CarefulColor),
  Quirky(R.string.Quirky, null, null, R.string.teste_equal, R.string.teste_equal,QuirkyColor),
  Sassy(R.string.Sassy, BaseStatus.SP_DEF, BaseStatus.SPEED, R.string.bitter, R.string.sweet,SassyColor),
  Timid(R.string.Timid, BaseStatus.SPEED, BaseStatus.ATK, R.string.sweet, R.string.hot,TimidColor),
  Hasty(R.string.Hasty, BaseStatus.SPEED, BaseStatus.DEF, R.string.sweet, R.string.acid,HastyColor),
  Jolly(R.string.Jolly, BaseStatus.SPEED, BaseStatus.SP_ATK, R.string.sweet, R.string.astringent,JollyColor),
  Naive(R.string.Naive, BaseStatus.SPEED, BaseStatus.SP_DEF, R.string.sweet, R.string.bitter,NaiveColor),
  Serious(R.string.Serious, null, null, R.string.teste_equal, R.string.teste_equal,SeriousColor);

  val growEasyStringId = growEasyStatus?.resId ?: R.string.teste_equal
  val growHardStringId = growHardStatus?.resId ?: R.string.teste_equal
}