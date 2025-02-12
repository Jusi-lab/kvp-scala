package lecture

import scala.util.control.Breaks.{break, breakable}

object Lec2IfElse extends App {
  val x = if (1 != 1) {
    10
  } else if (2 == 2) {
    20
  } else 30

  val withOutElse = if (2 != 2) 100 // else ()

  val x1 = if (false) 100 else 200 // тернарка
}

object Lec2Lazy extends App {
  lazy val lazyEval = {
    println("Thread sleep")
    Thread.sleep(2000)
    5
  }
  def sleepless = {
    println("sleepless")
    10
  }
  println("1 if eval")
  if (1 != 1) sleepless else lazyEval
  println("2 if eval")
  if (2 == 2) sleepless
  println("3 if eval")
  if (3 == 3) lazyEval else sleepless
}

object Lec2For extends App {
  val x  = 2 until 12
  val x1 = 2 to 12
  val x2 = for (i <- 2 until 12) yield i * 3

  for {
    i <- 2 to 12
    if i > 6
    j <- 2 to 12
  } {
    val x = i * 3
    println(s"i=$i j=$j x=$x")
  }

  x.foreach(println)
  val x2Alt = x.map(v => v * 3)
}

object Lec2While extends App {
  var x = 1
  while (x < 5) {
    println(x)
    x += 1
  }

  var x1 = 1
  do {
    println(x1)
    x1 += 1
  } while (x1 < 2)
}

object Lec2Recursion extends App {
  def factorial1(n: BigInt): BigInt = {
    if (n <= 1) 1
    else n * factorial1(n - 1)
  }

  import scala.annotation.tailrec
  def factorial2(n: BigInt): BigInt = {
    @tailrec
    def calcFactorial(acc: BigInt, n: BigInt): BigInt = {
      if (n == 0) acc
      else calcFactorial(n * acc, n - 1)
    }
    calcFactorial(1, n)
  }
  println(factorial2(5000).toString().length)
}

object Lec2Try extends App {
  try {
    val x = 10 / 0
    println(x)
  } catch {
    case e: ArithmeticException =>
      println("Division by zero error")
    case e: Exception =>
      println(e)
  } finally {
    println("Execution finished")
  }
}

object Lec2Break extends App {
  val search = "Test....String...."
  var num    = 0

  for (i <- 0 until search.length) {
    breakable {
      if (search.charAt(i) != '.') {
        break
      } else {
        num += 1
      }
    }
  }
  val x1 = search.count(c => c == '.')
  println(s"Found $x1 dots.")
}
