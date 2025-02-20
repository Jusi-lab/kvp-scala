package homework

object AdditionalExamples extends App {
  // Пример для map
  val optMapExample: Option[Int] = Option(5).map(x => x * x)
  println(s"map example: $optMapExample") // Some(25)

  // Пример для flatMap
  val optFlatMapExample: Option[Int] = Option(5).flatMap(x => Option(x + 10))
  println(s"flatMap example: $optFlatMapExample") // Some(15)

  // Пример для filter
  val optFilterExample: Option[Int] = Option(5).filter(_ > 3)
  println(s"filter example: $optFilterExample") // Some(5)

  // Пример для filterNot
  val optFilterNotExample: Option[Int] = Option(5).filterNot(_ > 3)
  println(s"filterNot example: $optFilterNotExample") // None

  // Пример для exists
  val optExistsExample: Boolean = Option(5).exists(_ > 3)
  println(s"exists example: $optExistsExample") // true

  // Пример для forall
  val optForallExample: Boolean = Option(5).forall(_ > 3)
  println(s"forall example: $optForallExample") // true

  // Пример для getOrElse
  val optGetOrElseExample: Int = Option.empty[Int].getOrElse(42)
  println(s"getOrElse example: $optGetOrElseExample") // 42

  // Пример для orElse
  val optOrElseExample: Option[Int] = Option.empty[Int].orElse(Option(42))
  println(s"orElse example: $optOrElseExample") // Some(42)

  // Пример для fold
  val optFoldExample: Int = Option(5).fold(0)(_ + 10)
  println(s"fold example: $optFoldExample") // 15

  // Пример для foreach
  Option(5).foreach(x => println(s"foreach example: $x")) // 5

  // Пример для toList
  val optToListExample: List[Int] = Option(5).toList
  println(s"toList example: $optToListExample") // List(5)

  // Пример для headOption
  val optHeadOptionExample: Option[Int] = List(1, 2, 3).headOption
  println(s"headOption example: $optHeadOptionExample") // Some(1)

  // Пример для find
  val optFindExample: Option[Int] = List(1, 2, 3).find(_ > 2)
  println(s"find example: $optFindExample") // Some(3)
}


// Функция для проверки, содержит ли строка из Option[String] определенное слово
object StringSearchInOption extends App {
  def searchWordInOption(o: Option[String], word: String): Unit = {
    o match {
      case Some(value) if value.contains(word) =>
        println("Слово найдено")
      case _ =>
        println("Слово не найдено")
    }
  }

  val optionStr: Option[String] = Some("Hello world")
  searchWordInOption(optionStr, "world") //  Слово найдено
  searchWordInOption(optionStr, "Scala") //  Слово не найдено
}

// Функция, которая принимает Option[Int] с возрастом и выводит категорию возраста
object AgeCategory extends App {
  def ageCategory(age: Option[Int]): Unit = {
    age match {
      case Some(a) if a < 13 =>
        println("Ребенок")
      case Some(a) if a < 18 =>
        println("Подросток")
      case Some(a) if a < 60 =>
        println("Взрослый")
      case Some(_) =>
        println("Пенсионер")
      case None =>
        println("Возраст не указан")
    }
  }

  val age: Option[Int] = Some(25)
  ageCategory(age) // Взрослый

  val age2: Option[Int] = None
  ageCategory(age2) // Возраст не указан
}
