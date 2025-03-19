package homework

object HW extends App {

  // Новый пример 1 Implicit Val (вычисление площади круга)
  object ImplicitValExample extends App {
    implicit val defaultPi: Double = 3.14159

    // Функция для вычисления площади круга
    def calculateCircleArea(radius: Double)(implicit pi: Double): Double = {
      pi * radius * radius
    }

    val radius = 5.0
    val area = calculateCircleArea(radius)
    println(s"Area of circle with radius $radius is $area")
  }

  // Новый пример 2 Implicit Def (преобразование String в Boolean)
  object ImplicitDefExample extends App {

    implicit def stringToBoolean(s: String): Boolean = s.toLowerCase == "true"

    val boolTrue: Boolean = "true"
    val boolFalse: Boolean = "false"

    println(s"'true' as Boolean: $boolTrue")
    println(s"'false' as Boolean: $boolFalse")
  }

  // Новый пример 3 Implicit Class (метод isEven для Int)
  object ImplicitClassExample extends App {
    implicit class IntOps(i: Int) {
      def isEven: Boolean = i % 2 == 0
    }

    val number: Int = 5
    val evenCheck = number.isEven
    println(s"Is $number even? $evenCheck")
  }
  ImplicitValExample.main(Array.empty)
  ImplicitDefExample.main(Array.empty)
  ImplicitClassExample.main(Array.empty)
}

object HW2 extends App {

  //Implicit class для умножения числа на 10
  implicit class IntMultiplier(val number: Int) extends AnyVal {
    def multiplyByTen: Int = number * 10
  }
  val num = 5
  println(s"$num multiplied by 10 is ${num.multiplyByTen}")

  //Implicit class для проверки, является ли число четным
  implicit class IntEvenChecker(val number: Int) extends AnyVal {
    def isEven: Boolean = number % 2 == 0
  }
  val checkNum = 6
  println(s"Is $checkNum even? ${checkNum.isEven}")

  // Implicit val для хранения курса обмена валюты (USD to RUB)
  implicit val exchangeRate: Double = 75.0

  def convertUsdToRub(amount: Double)(implicit rate: Double): Double = {
    amount * rate
  }
  val usdAmount = 100.0
  val rubAmount = convertUsdToRub(usdAmount)
  println(s"$usdAmount USD is $rubAmount RUB")

  //Case class Person и сортировка по возрасту
  case class Person(name: String, age: Int)

  // Implicit val для Ordering[Person] (сравнение по возрасту)
  implicit val personOrdering: Ordering[Person] = Ordering.by(_.age)

  val people = List(
    Person("Alice", 30),
    Person("Bob", 25),
    Person("Charlie", 35)
  )

  // Печать исходного списка
  println("Original list:")
  people.foreach(println)

  // Сортировка списка
  val sortedPeople = people.sorted

  // Печать отсортированного списка
  println("Sorted list by age:")
  sortedPeople.foreach(println)
}