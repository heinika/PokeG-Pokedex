package com.heinika.pokeg.module.egg

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar

@Preview
@Composable
fun EggScreen() {
  PokeGTheme {
    Surface(
      Modifier
        .fillMaxSize()
    ) {
      val state = rememberScrollState()
      Column(
        Modifier
          .fillMaxSize()
          .padding(top = Dp(SystemBar.statusBarHeightDp), start = 12.dp, end = 12.dp)
          .verticalScroll(state)
      ) {
//        Text(text = "宝可梦培育", style = MaterialTheme.typography.h3)
//        Divider(Modifier.padding(bottom = 16.dp))
        Text(
          style = MaterialTheme.typography.body1,
          text = "宝可梦培育是指通过生蛋孵化获得新的宝可梦的方式来获得玩家想要的宝可梦，同时兼指对宝可梦能力的培养，于第二世代引入。培养宝可梦一般是为了获得在对战中最符合其能力的强大的宝可梦。"
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "概述", style = MaterialTheme.typography.h4)
        Divider(Modifier.padding(bottom = 16.dp))
        Text(
          style = MaterialTheme.typography.body1,
          text = "宝可梦可以通过寄存在培育屋或寄放屋中与其他宝可梦生蛋，主角每走过一定步数后宝可梦便有可能生出一个蛋。获得宝可梦的蛋后，可以通过带着一起行走来孵蛋。通过不断孵化宝可梦，来获得玩家想要的宝可梦的外貌（包括形态和是否异色）、特性、招式和能力，甚至是精灵球的种类。之后，通过提升等级和基础点数来培养能力，再利用极限特训和薄荷来更极致地优化宝可梦的能力，最终获得玩家想要的强大宝可梦。\n" +
            "\n" +
            "一般情况下，两只异性宝可梦需要蛋群相同或其中一只为百变怪才可以生蛋。培育宝可梦时，生出什么样的宝可梦由雌性宝可梦决定；特别地，和百变怪生蛋时，生出的宝可梦均为另一只不是百变怪的宝可梦。培育宝可梦时，子代的宝可梦有概率能够继承亲代宝可梦的外貌、特性、招式和能力，这一般被称为遗传。有些道具，如不变之石、红线和基础点数增强道具等，可以在培育中发挥作用。"
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "孵化宝可梦", style = MaterialTheme.typography.h4)
        Divider(Modifier.padding(bottom = 16.dp))
        Text(text = "生蛋", style = MaterialTheme.typography.h5)
        Divider(Modifier.padding(bottom = 16.dp))
        Text(
          style = MaterialTheme.typography.body1,
          text = "两只性别不同且具有相同蛋群的宝可梦被寄存在培育屋或寄放屋中，就有可能获得一个宝可梦的蛋。如果被寄存的宝可梦没有共有的蛋群，或者是同性，则不可能生出蛋来。未发现群的宝可梦无论在什么情况下都不可能生出蛋来。特别地，百变怪可以和任何不是百变怪和未发现群的宝可梦生蛋。\n" +
            "\n" +
            "第二世代中，每行走一步，都有一定几率生出一个蛋。在判断两只宝可梦是否可以生蛋时，它们的蛋群是唯一标准。在同一蛋群的宝可梦，如果亲代的防御个体值是相同的，而特殊个体值相差不足8，它们将无法生蛋，因为它们很有可能是同一宝可梦家族。\n" +
            "\n" +
            "第三世代起，玩家每走256步，会判定一次是否生了蛋。亲代是同类且初训家不同的话，判定通过的几率是69.3%；同类且初训家相同或者不同类且初训家不同的话，通过率是49.5%；不同类且初训家相同的话，通过率是19.8%。从第五世代开始，如果已获得了圆形护符，则几率会分别提高至87.8%、79.5%和39.3%。\n" +
            "\n" +
            "生出蛋以后，培育屋或寄放屋的老爷爷或老奶奶的站位或身体朝向ΩRαS或姿势SMUSUM会与平时不同，与其对话便就可以获得蛋。\n" +
            "\n" +
            "第三世代起，两只宝可梦是否能生蛋遵循以下原则："
        )
        Spacer(modifier = Modifier.height(16.dp))
        EggDescImage(painterResource(id = R.drawable.egg1))

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "特殊的宝可梦", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text("一些宝可梦并不符合以上的生蛋规则：", style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(16.dp))
        EggDescImage(painterResource(id = R.drawable.egg2))

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "薰香", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "一些宝可梦作为母方携带特定的薰香时，会生出特定的宝可梦，这样生出来的宝可梦是幼年宝可梦。从第六世代开始，父方携带薰香与百变怪生蛋也可以生出特定的宝可梦：",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        EggDescImage(painterResource(id = R.drawable.egg3))

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "遗传", style = MaterialTheme.typography.h4)
        Divider(Modifier.padding(bottom = 16.dp))
        Text(
          "孵化出来的宝可梦会有可能遗传亲代的形态、招式、个体值、性格、特性和精灵球种类。",
          style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "第四世代", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "性格、性别、是否为异色等是在出现蛋的一瞬间决定。个体值则是在与老爷爷交谈后获得蛋的一瞬间决定。",
          style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "第六世代和第七世代", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "若玩家没有闪耀护符且亲代双方语言相同时，性格、性别、个体值和特性在与培养家交谈选择接受或拒绝上一个蛋的瞬间决定，是否为异色则是在与培养家交谈后获得这个蛋的瞬间决定。\n" +
            "\n" +
            "若玩家拥有闪耀护符或亲代双方语言不同（国际婚姻）时, 所有能力都在与培养家交谈选择接受或拒绝上一个蛋的瞬间决定。",
          style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "第八世代", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "若玩家没有闪耀护符且亲代双方语言相同时，性格、性别、个体值和特性在蛋出现的瞬间决定（培养家抬手，表示有蛋可以领），是否为异色则是在与培养家交谈后获得蛋的瞬间决定。\n" +
            "\n" +
            "若玩家拥有闪耀护符或亲代双方语言不同（国际婚姻）时, 所有能力都在蛋出现的瞬间决定。",
          style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "形态", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "一般地，由其他因素（如性格值、季节、是否理发等）决定形态的宝可梦，宝可梦的形态由这些因素决定；否则生出子代的形态来自母方。特别地，如果母方是百变怪，则形态有可能由父方决定，也有可能是最基本的样子。具体如下：",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        EggDescImage(painterResource(id = R.drawable.egg4))

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "招式", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "生出的子代能够遗传亲代的一部分招式。有些招式只能通过遗传获得，这部分招式被称为蛋招式。在第六世代以前，所有的招式均遗传自父方。自第六世代起，亲代双方均可遗传招式给子代。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "能力", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "宝可梦的个体值也会有所遗传。根据世代不同，遗传方法有所不同。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "第二世代", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "第二世代中，子代会遗传到异性亲代的防御、特防能力。如果亲代之一是百变怪，则遗传百变怪的能力。防御个体会直接遗传，特殊个体值会有50%几率加减8遗传（在原值为0～7时加、在8～15时减），另外也有50%的几率保持不变。攻击和速度的个体值完全随机决定，ＨＰ个体值则由其余四项能力决定，公式与野生宝可梦相同。一只宝可梦完全遗传到异性亲代能力的概率是1⁄512。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "第三世代、《钻石／珍珠／白金》", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "子代的3项个体值遗传自亲代，另外3项则是随机的。不过这会导致数值重复，因此在《绿宝石》中，过程变得更加复杂：首先亲代随机遗传一项个体值，然后从除了ＨＰ的其他能力中随机选择一项，由任一亲代遗传，如果这个与一开始选择的能力相同，则会覆盖。最后，从除了ＨＰ和防御的个体值中随机选择一项从亲代遗传，这项同样会覆盖前面的值。剩下的个体值则随机决定。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "《心金／魂银》、第五世代", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "子代的3项个体值遗传自亲代，另外3项则是随机的。此外，移除了绿宝石中的遗传机制，6项个体值的遗传概率相同，并且不再出现重复的情况。遗传自亲代的个体值从父母中随机选择。\n" +
            "\n" +
            "另外，新增了6种基础点数增强道具。如果父母的其中一方携带该道具，则该道具所对应的那项个体值将与道具携带方相同，并另有2项个体值按照上文的规则遗传自亲代。如果亲代双方携带有不同的基础点数道具，子代则只会确定地从中遗传一项，剩下的同样随机。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "第六世代起", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "与第五世代中不同，红线增加了双亲一方携带红线生蛋，子代会随机遗传亲代双方的6项个体值中的5项的新效果。\n" +
            "\n" +
            "当双亲一方携带基础点数增强道具，另一方携带红线生蛋，子代会遗传到道具所对应的特定的能力，剩下的四种能力同样随机，并且随机亲代。当双亲都携带基础点数增强道具，子代将各有50%的几率遗传其中一方所携带道具对应的一项能力。\n" +
            "\n" +
            "例1：若父母双方的个体值全部是31，则子代必定有3项个体值是31，另外3项则完全随机。若父母双方的任意者携带红线，则会有5项个体值是31，仅有1项完全随机。\n" +
            "例2：若父亲的个体值全部是31，母亲的个体值全部是30，则子代有3项个体值从31或30中随机选择，另外3项则完全随机。若父母双方的任意者携带红线，则会有5项个体值从31或30中随机选择，仅有1项完全随机。\n" +
            "例3：若父母双方的个体值全部是31，且父方携带力量护腕，则子代的攻击个体值必定是31，另有2项个体值必定是31，另外3项则完全随机。若母方携带红线，则子代的攻击个体值必定是31，另有4项个体值必定是31，仅有1项完全随机。\n" +
            "例4：若父母双方的个体值全部是31，且父方携带力量护腕、母方携带力量负重，则子代的攻击、ＨＰ个体值中有随机一项必定是31，另有2项个体值必定是31，另外3项则完全随机。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "性格", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "携带不变之石的宝可梦生蛋时，子代的性格会遗传自携带者，否则性格为随机的。当亲代双方都携带不变之石时，将必定遗传其中一方，且几率各为50%。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "特性", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "不考虑隐藏特性的情况下，普通特性只有1种的宝可梦不存在遗传问题，会固定为该特性。普通特性有2种的特性会存在遗传的问题。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "普通特性", style = MaterialTheme.typography.h5)
        Text(text = "第三世代至《黑／白》", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "无法遗传。出生的宝可梦的特性与亲代无关系，会随机地在可能的普通特性中选择，几率各50%。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "《黑２／白２》", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "相同家族的宝可梦生蛋时，遗传母方的特性的概率较大，大约有70%几率会遗传母方的特性。父方不影响该项遗传。但是，如果是与同蛋群的其它宝可梦或者百变怪生蛋则不会遗传母方的特性，会随机地在可能的普通特性中选择，几率各50%。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "第六世代起", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "子代的特性数将由母方（亲代无百变怪时）或非百变方（亲代有百变怪时）的特性数决定。如果母方或非百变怪方为普通特性，则遗传其特性几率为80%，遗传另一普通特性的几率为20%。如果母方或非百变怪方为隐藏特性，则遗传隐藏特性的几率为60%，遗传第一特性，第二特性的几率各为20%。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "隐藏特性", style = MaterialTheme.typography.h5)
        Text(text = "第五世代", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "第五世代的隐藏特性必须满足以下条件才能遗传：\n" +
            "\n" +
            "♀一方持有隐藏特性，♂一方是同种族的宝可梦或者是同蛋群但不同种族的宝可梦。♂一方是否持有隐藏特性不影响遗传。\n" +
            "另一方不能是百变怪。百变怪即使持有隐藏特性也不能遗传。\n" +
            "满足该特性的情况下生蛋有60%的概率得到拥有隐藏特性的子代，算上2种普通特性共计3种的出现率约为20%：20%：60%。\n" +
            "\n" +
            "第五世代的父方的隐藏特性以及性别不明的宝可梦的隐藏特性无法遗传。例如：",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        EggDescImage(painterResource(id = R.drawable.egg5))
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "第六世代起", style = MaterialTheme.typography.h5)
        EggDescImage(painterResource(id = R.drawable.egg7))
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "特殊特性", style = MaterialTheme.typography.h5)
        Text(text = "第七世代", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "特殊岩狗狗的蛋可以通过宝可梦培育得到。该岩狗狗实际拥有不同的形态数而拥有与一般形态不一样的特性，且形态数能保持遗传，因此：\n" +
            "\n" +
            "(特殊岩狗狗 或者 黄昏鬃岩狼人) ＋ 百变怪 → 特殊岩狗狗的蛋\n" +
            "(特殊岩狗狗♀ 或者 黄昏鬃岩狼人♀) ＋ 任意陆上群宝可梦♂ → 特殊岩狗狗的蛋",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "异色", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "在第二世代，亲代为异色宝可梦时，异性子代有1⁄64的几率是异色宝可梦。自第三世代起，培育出异色宝可梦的几率与该世代在野外遇到野生异色宝可梦的几率相同。第五世代后可以受到闪耀护符的加成。\n" +
            "\n" +
            "自第四世代起，当父母双方来自不同语言的游戏时，子代会是异色宝可梦的几率是1⁄2048，这一特殊机制通常被称作国际婚姻。自《黑／白》起，这个概率上升到1⁄1366。第五世代及之后的游戏中，子代为异色宝可梦的概率列表如下：",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        EggDescImage(painterResource(id = R.drawable.egg8))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "第八世代起，当培育出异色宝可梦的蛋时，此蛋会根据孵化者的特殊ID进行对应改变，从而使得孵化者孵出的宝可梦为异色宝可梦，其中，有1⁄16的可能是方块特效，有15⁄16的可能是星星特效。",
          style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        EggDescImage(painterResource(id = R.drawable.egg9))
        EggDescImage(painterResource(id = R.drawable.egg10))

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "转自：https://wiki.52poke.com/zh-hans/宝可梦培育")
      }
    }
  }
}

@Composable
fun EggDescImage(painter : Painter){
  Image(
    modifier = Modifier.fillMaxWidth(),
    painter = painter,
    contentDescription = "",
    contentScale = ContentScale.FillWidth
  )
}