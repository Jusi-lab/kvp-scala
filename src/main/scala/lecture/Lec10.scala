package lecture

object Lec10HofModified extends App {
  // Higher Order Function (HOF)
  def hof1[A, B](input: A, f: A => B): B = f(input)
  def square(i: Int): Int = i * i
  def toUpper(s: String): String = s.toUpperCase
  def isEven(n: Int): Boolean = n % 2 == 0
  val resInt = hof1(3, square) // 9
  val resStr = hof1("hello", toUpper) // "HELLO"
  val resBool = hof1(4, isEven) // true

  println(s"Int result: $resInt")
  println(s"String result: $resStr")
  println(s"Boolean result: $resBool")

  def hof2[A, B](multiplier: A)(implicit num: Numeric[A]): A => A =
    (x: A) => num.times(x, multiplier)

  val doubleFn = hof2(2)
  val tripleFn = hof2(3)

  val numbers = List(1, 2, 3, 4, 5)
  println(numbers.map(doubleFn))
  println(numbers.map(tripleFn))

  object ModifyList {
    def applyFunctionToList[A](list: List[A], f: A => A): List[A] = list.map(f)

    def addN[A](n: A)(implicit num: Numeric[A]): List[A] => List[A] =
      list => applyFunctionToList(list, x => num.plus(x, n))

    def multiplyN[A](n: A)(implicit num: Numeric[A]): List[A] => List[A] =
      list => applyFunctionToList(list, x => num.times(x, n))
  }

  //val intList = List(1, 2, 3, 4, 5)
//  val doubleList = ModifyList.multiplyN(2)(intList)
 // println(s"Double list: $doubleList") // List(2, 4, 6, 8, 10)

  val addFive = ModifyList.addN(5)
  //println(s"Add five: ${addFive(intList)}") // List(6, 7, 8, 9, 10)
}

object Lec10CurryingModified extends App {
  // Каррирование
  def divide[A](x: A)(y: A)(implicit frac: Fractional[A]): A =
    frac.div(x, y)

 // val divideByTwo = divide(1)(_)
 // println(divideByTwo(4)) // 0.25

  def sumWithFunction[A](f: A => A)(x: A, y: A)(implicit num: Numeric[A]): A =
    num.plus(f(x), f(y))

  val sumSquares = sumWithFunction((x: Int) => x * x)(_, _)
  println(sumSquares(2, 3)) // 13!!!


  class LazyContainer[A](f: => A) {
    def evaluate(): A = {
      f
      f
      f
    }
  }

  var counter = 0
  lazy val lazyValue = {
    counter += 1
    counter
  }

  val container = new LazyContainer(lazyValue)
  container.evaluate()
  println(s"Counter after lazy evaluation: $counter")
  var eagerCounter = 0
  val eagerValue = {
    eagerCounter += 1
    eagerCounter
  }

  val eagerContainer = new LazyContainer(eagerValue)
  eagerContainer.evaluate()
  println(s"Eager counter: $eagerCounter")
}
