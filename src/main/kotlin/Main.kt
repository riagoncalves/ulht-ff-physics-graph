import kotlin.math.PI
import kotlin.math.pow

fun start() {
  val y0 = defineHeight()
  val canonA = defineCanon(30.0, 40.0, y0, "A")
  val canonB = defineCanon(45.0, 35.0, y0, "B")

  when {
    canonA.first && canonA.first == canonB.first -> println("Ambos os canhões irão ultrapassar o muro.")
    canonA.first -> println("Apenas o canhão A consegue ultrapassar o muro pois atinge uma altura superior a 25 metros em y aos 75 metros de x.")
    canonB.first -> println("Apenas o canhão B consegue ultrapassar o muro pois atinge uma altura superior a 25 metros em y aos 75 metros de x.")
    else -> println("Nenhum canhão consegue ultrapassar o muro, é recomendado aumentar a altura inicial.")
  }

  when {
    canonA.second >= canonB.second -> println("O melhor canhão num camplo plano horizontal seria o A pois atinge uma distância de ${canonA.second.toInt()} metros em x.")
    canonA.second <= canonB.second -> println("O melhor canhão num camplo plano horizontal seria o B pois atinge uma distância de ${canonA.second.toInt()} metros em x.")
  }

  restart()
}

fun restart() {
  println("Experimentar com diferentes alturas iniciais? (s/n)")
  when(readLine()) {
    "s" -> start()
    "S" -> start()
    "sim" -> start()
    "n" -> return
    "N" -> return
    "não" -> return
    else -> {
      println("Resposta inválida.")
      restart()
    }
  }
}

fun defineHeight() : Double {
  println("Introduza a altura inicial.")
  return when(val height = readLine()?.toDoubleOrNull()) {
    null -> {
      println("Altura inválida.")
      defineHeight()
    }
    else -> {
      height
    }
  }
}

fun defineCanon(angle: Double, v0: Double, y0: Double, name: String) : Pair<Boolean, Double> {
  println("Gráfico do canhão $name")

  val larguraGrafico = 80
  val alturaGrafico = 25
  val grafico = Chart(larguraGrafico, alturaGrafico)

  val angleRad = angle * PI/180
  val vx = v0 * kotlin.math.cos(angleRad)
  val v0y = v0 * kotlin.math.sin(angleRad)

  var x = 0.0
  var y = y0
  var t = 0.0
  var wallConfirmation = false
  var maxDistance = 0.0

  while(y >= 0.0) {
    grafico.ponto(x, y)
    t += 0.01
    x = vx * t
    y = y0 + (v0y * t) - (5 * (t).pow(2))
    if(x in 75.0..76.0 && y >= 25.0) wallConfirmation = true
    if(y >= 0.0) maxDistance = x
  }

  grafico.draw()
  return Pair(wallConfirmation, maxDistance)
}

fun main() {
  start()
}