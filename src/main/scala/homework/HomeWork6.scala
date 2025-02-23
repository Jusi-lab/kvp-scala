import scala.util.{Try, Success, Failure}

object MethodExamples extends App {

  // 1. flatMap
  val either: Either[String, Int] = Right(42)
  val resultFlatMap: Either[String, String] = either.flatMap(x => Right(s"Value: $x"))
  println(s"flatMap example: $resultFlatMap") // Вывод: Right(Value: 42)

  // 2. map
  val eitherMap: Either[String, Int] = Right(10)
  val resultMap: Either[String, Int] = eitherMap.map(x => x * 2)
  println(s"map example: $resultMap") // Вывод: Right(20)

  // 3. withFilter
  val list = List(1, 2, 3, 4, 5)
  val filteredList = for {
    x <- list
    if x % 2 == 0 // Фильтруем только четные числа
  } yield x
  println(s"withFilter example: $filteredList") // Вывод: List(2, 4)

  // 4. isEmpty
  val option: Option[String] = Some("Hello")
  val isEmpty = option.isEmpty
  println(s"isEmpty example: $isEmpty") // Вывод: false

  // 5. get с обработкой ошибки
  val optionGet: Option[Int] = Some(100)
  val value = optionGet.getOrElse(0)
  println(s"get example: $value") // Вывод: 100

  // 6. Try
  val resultTry: Try[Int] = Try {
    "123".toInt // Преобразуем строку в число
  }
  println(s"Try example: $resultTry") // Вывод: Success(123)

  // 7. Either
  def divide(a: Int, b: Int): Either[String, Int] = {
    if (b == 0) Left("Division by zero") // Обработка ошибки
    else Right(a / b) // Успешный результат
  }

  val resultEither = divide(10, 2)
  println(s"Either example: $resultEither") // Вывод: Right(5)

  // 8. recover
  val resultRecover: Try[Double] = Try {
    1 / 0 // Деление на ноль
  }.recover {
    case _: ArithmeticException => 0.0 // Возвращаем Double
  }.map(_.asInstanceOf[Double]) // Явно приводим к Double

  println(s"recover example: $resultRecover") // Вывод: Success(0.0)
  // 9. toOption
  val eitherToOption: Either[String, Int] = Right(42)
  val optionResult = eitherToOption.toOption
  println(s"toOption example: $optionResult") // Вывод: Some(42)

  // 10. mkString
  val listMkString = List("a", "b", "c")
  val resultMkString = listMkString.mkString(", ")
  println(s"mkString example: $resultMkString") // Вывод: a, b, c
}

object DivisionExample extends App {
  // Реализация с Option
  def divideOption(a: Double, b: Double): Option[Double] = {
    if (b == 0) None
    else Some(a / b)
  }

  // Реализация с Try
  def divideTry(a: Double, b: Double): Try[Double] = {
    if (b == 0) Failure(new ArithmeticException("Division by zero")) // Явно проверяем деление на ноль
    else Try(a / b)
  }

  // Реализация с Either
  def divideEither(a: Double, b: Double): Either[String, Double] = {
    if (b == 0) Left("Division by zero")
    else Right(a / b)
  }

  // Примеры использования
  println("Option Examples:")
  println(divideOption(10, 2)) // Some(5.0)
  println(divideOption(10, 0)) // None

  println("\nTry Examples:")
  println(divideTry(10, 2)) // Success(5.0)
  println(divideTry(10, 0)) // Failure(ArithmeticException)

  println("\nEither Examples:")
  println(divideEither(10, 2)) // Right(5.0)
  println(divideEither(10, 0)) // Left(Division by zero)
}