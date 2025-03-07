package homework

object HomeWork9 extends App {
  // Пример 1 View
  object ViewCollections extends App {
    val list = List(1, 2, 3, 4, 5)
    val x = list.view.map(_ + 1).map(_ + 1).filter(_ > 4).map(_ + 1).toList
    println(s"ViewCollections: $x")
  }
  ViewCollections.main(Array.empty)
  // Пример 2 Mutable Collections
  object MutableCollections extends App {
    val ml = scala.collection.mutable.ListBuffer(1, 2, 3, 4, 5)
    ml.addOne(6)
    ml.prepend(0)
    println(s"MutableCollections: $ml")
  }
  MutableCollections.main(Array.empty)

  // Пример 3 Var With Collections
  object VarWithCollections extends App {
    var mlvar = scala.collection.mutable.ListBuffer(1, 2, 3)
    mlvar = mlvar.map(_ + 1)
    println(s"VarWithCollections: $mlvar")
  }
  VarWithCollections.main(Array.empty)

  // Пример 4 Mutable Collections With Def
  object MutableCollectionsWithDef extends App {
    import scala.collection.mutable.ListBuffer
    def mutate(lb: ListBuffer[Int]): Unit = lb.addOne(10)
    val lb = ListBuffer(1, 2, 3, 4, 5)
    mutate(lb)
    println(s"MutableCollectionsWithDef: $lb")
  }
  MutableCollectionsWithDef.main(Array.empty)
  // Пример 5 Compose and Then Functions
  object CompAndThenFunctions extends App {
    def addOne(i: Int): Int  = i + 1
    def prodTwo(i: Int): Int = i * 2
    def comp = (addOne _).compose(prodTwo)
    println(s"Compose: ${comp(2)}")
    def anThen = (addOne _).andThen(prodTwo)
    println(s"AndThen: ${anThen(2)}")
  }
  CompAndThenFunctions.main(Array.empty)
  // Пример 7 Partial Functions
  object PartialFunctions extends App {
    def squareRoot: PartialFunction[Double, Double] = {
      case x if x >= 0 => Math.sqrt(x)
    }
    val res = squareRoot(4)
    println(s"PartialFunctions (squareRoot): $res")
    val l = List(-1d, 4d).collect(squareRoot)
    println(s"PartialFunctions (collect): $l")
  }
  PartialFunctions.main(Array.empty)
  // Пример 8 Partial Functions OrElse
  object PartialFunctionsOrElse extends App {
    def negativeOrZeroToPos: PartialFunction[Int, Int] = {
      case x if x <= 0 => Math.abs(x)
    }
    def positiveToNegative: PartialFunction[Int, Int] = {
      case x if x > 0 => -x
    }
    def swapSign: PartialFunction[Int, Int] = {
      positiveToNegative.orElse(negativeOrZeroToPos)
    }
    println(s"PartialFunctionsOrElse (swapSign -1): ${swapSign(-1)}")
    println(s"PartialFunctionsOrElse (swapSign 2): ${swapSign(2)}")
  }
  PartialFunctionsOrElse.main(Array.empty)
  //  Пример Partial Functions OrElse
  object PartialFunctionsOrElseExample extends App {
    def isEven: PartialFunction[Int, String] = {
      case x if x % 2 == 0 => s"$x is even"
    }
    def isOdd: PartialFunction[Int, String] = {
      case x if x % 2 != 0 => s"$x is odd"
    }
    def checkNumber: PartialFunction[Int, String] = isEven.orElse(isOdd)
    println(s"PartialFunctionsOrElseExample (checkNumber 4): ${checkNumber(4)}")
    println(s"PartialFunctionsOrElseExample (checkNumber 3): ${checkNumber(3)}")
  }
  PartialFunctionsOrElseExample.main(Array.empty)
}

object PartialFunctionExample extends App {
  // Частичная функция: является ли число квадратом другого числа
  val isSquare: PartialFunction[Int, Int] = {
    case x if x >= 0 && Math.sqrt(x) % 1 == 0 => x
  }
  // фильтрует список с использованием частичной функции
  def filterSquares(numbers: List[Int]): List[Int] = {
    numbers.collect(isSquare)
  }
  val numbers = List(1, 2, 3, 4, 5, 9, 16, 25, 30)
  val squares = filterSquares(numbers)
  println(s"Исходный список: $numbers")
  println(s"Числа, являющиеся квадратами: $squares")
}