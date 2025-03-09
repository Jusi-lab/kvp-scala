object Lec10Hof extends App {
  // Higher Order Function
  def hof1(i: Int, f: Int => Int): Int = f(i)
  def quad(i: Int): Int                = i * i
  val res                              = hof1(2, quad)
  println(res)

  def hof2(i: Int): Int => Int = a => i * a
  val res2: Int => Int         = hof2(2)
  println(res2(3))

  val l = List(1, 2, 3, 4, 5)
  println(l.map(res2))

  object ModifyListInt {
    def add2(ints: List[Int]): List[Int]  = ints.map(i => i + 2)
    def prod2(ints: List[Int]): List[Int] = ints.map(i => i * 2)
    def quad(ints: List[Int]): List[Int]  = ints.map(i => i * i)
  }

  object ModifyListIntV2 {
    private def applyFunctionToListInt(ints: List[Int], f: Int => Int): List[Int] = ints.map(i => f(i))
    def add2(ints: List[Int]): List[Int] = applyFunctionToListInt(ints, i => i + 2)
    def prod2(ints: List[Int]): List[Int] = applyFunctionToListInt(ints, i => i * 2)
    def quad(ints: List[Int]): List[Int]  = applyFunctionToListInt(ints, i => i * i)
  }

  val l1 = List(1, 2, 3, 4, 5)
  println(s"addV1: ${ModifyListInt.add2(l1)}")
  println(s"prdoV1: ${ModifyListInt.prod2(l1)}")
  println(s"quadV1: ${ModifyListInt.quad(l1)}")
  println("-------------")
  println(s"addV2: ${ModifyListIntV2.add2(l1)}")
  println(s"prdoV2: ${ModifyListIntV2.prod2(l1)}")
  println(s"quadV2: ${ModifyListIntV2.quad(l1)}")
  def hofExample(i: Int, f: Int => String): String = f(i)
  def intToString(i: Int): String = s"The number is $i"
  val result = hofExample(42, intToString)
  println(result)

  def multiplier(factor: Int): Int => Int = (x: Int) => x * factor
  val double = multiplier(2)
  println(double(5))
}

object Lec10Currying extends App {
  def sum(x: Int)(y: Int) = x.toDouble / y.toDouble
  def increment           = sum(1)(_)

  val twoIncOne = increment(0)
  println(twoIncOne)

  val l1 = List(1, 2, 3, 4, 5)
  println(l1.map(x => increment(x)))
  println(l1.map(increment))

  def summF(f: Int => Int)(x: Int, y: Int): Int = f(x) + f(y)
  def justSum                                   = summF(identity)(_, _)
  def justSum2                                  = summF(x => x)(_, _)
  val eqJustSum                                 = justSum(1, 2) == justSum2(1, 2)
  println(eqJustSum)

  def squareSum = summF(x => x * x)(_, _)
  println(squareSum(2, 3))

  def incrementV2 = justSum(1, _)
  println(incrementV2(4))
  def addThreeNumbers(a: Int)(b: Int)(c: Int): Int = a + b + c
  val addTwoNumbers = addThreeNumbers(1)(2)(_)
  println(addTwoNumbers(3))
  def rectangleArea(length: Int)(width: Int): Int = length * width
  val areaWithLength5 = rectangleArea(5)(_)
  println(areaWithLength5(10))

  // Ленивые вычисления
  lazy val lazyValue = {
    println("Computing lazy value")
    42
  }
  println("Before accessing lazy value")
  println(lazyValue)

  // Сравнение с null
  val x: String = null
  val y: String = null
  println(x == y)
  println(x eq y)
}

object HigherOrderFunctions {
  // 1. Функция, которая принимает список функций и значение, а затем возвращает список результатов применения каждой функции к этому значению
  def applyFunctions[A, B](functions: List[A => B], value: A): List[B] = {
    functions.map(f => f(value))
  }

  // 2. Функция высшего порядка, которая принимает список функций и возвращает новую функцию, которая применяет каждую функцию из списка к результату предыдущей функции
  def composeFunctions[A](functions: List[A => A]): A => A = {
    functions.reduce((f1, f2) => x => f2(f1(x)))
  }

  // 3. Функция, которая принимает другую функцию двух аргументов и возвращает каррированную версию этой функции
  def curry[A, B, C](f: (A, B) => C): A => B => C = {
    (a: A) => (b: B) => f(a, b)
  }

  // 4. Функция, которая принимает три аргумента (String, Int, Double) и объединяет их в одну строку через пробел
  def combineValues(s: String, i: Int, d: Double): String = {
    s"$s $i $d"
  }

  // Каррированная версия функции combineValues
  def curriedCombineValues(s: String)(i: Int)(d: Double): String = {
    s"$s $i $d"
  }

  def main(args: Array[String]): Unit = {
    // Пример applyFunctions
    val functions = List((x: Int) => x + 1, (x: Int) => x * 2, (x: Int) => x * x)
    val result1 = applyFunctions(functions, 3)
    println(s"applyFunctions result: $result1")

    // Пример composeFunctions
    val composedFunction = composeFunctions(List((x: Int) => x + 1, (x: Int) => x * 2, (x: Int) => x + 10))
    val result2 = composedFunction(3)
    println(s"composeFunctions result: $result2")

    // Пример curry
    val add = (x: Int, y: Int) => x + y
    val curriedAdd = curry(add)
    val result3 = curriedAdd(2)(3)
    println(s"curry result: $result3")

    // Пример combineValues
    val result4 = combineValues("Hello", 42, 3.14)
    println(s"combineValues result: $result4") //

    // Пример curriedCombineValues
    val curriedResult = curriedCombineValues("Hello")(42)(3.14)
    println(s"curriedCombineValues result: $curriedResult")

    // Частичное применение каррированной функции
    val partial1 = curriedCombineValues("Hello")_
    val partial2 = partial1(42)
    val finalResult = partial2(3.14)
    println(s"Partial application result: $finalResult")
  }
}