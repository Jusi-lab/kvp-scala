package homework

object ExampleHW7 extends App {

  // Пример map
  val numbers = List(1, 2, 3, 4)
  val squaredNumbers = numbers.map(x => x * x)
  println(s"map: $squaredNumbers")

  // Пример filter
  val evenNumbers = numbers.filter(x => x % 2 == 0)
  println(s"filter: $evenNumbers")

  // Пример fold
  val sum = numbers.fold(0)((acc, x) => acc + x)
  println(s"fold: $sum")

  // Пример groupBy
  case class Person(name: String, age: Int)
  val people = List(Person("Alice", 25), Person("Bob", 30), Person("Charlie", 25))
  val groupedByAge = people.groupBy(_.age)
  println(s"groupBy: $groupedByAge")

  // Пример collect
  val evenNumbersCollected = numbers.collect { case x if x % 2 == 0 => x * 2 }
  println(s"collect: $evenNumbersCollected")

  // Пример flatMap
  val expandedNumbers = numbers.flatMap(x => List(x, x * 2))
  println(s"flatMap: $expandedNumbers")

  // Пример partition
  val (even, odd) = numbers.partition(x => x % 2 == 0)
  println(s"partition - even: $even")
  println(s"partition - odd: $odd")

  // Пример reduce
  val product = numbers.reduce((x, y) => x * y)
  println(s"reduce: $product")

  // Пример foreach
  println("foreach:")
  numbers.foreach(x => println(s"Number: $x"))

  // Пример takeWhile
  val taken = numbers.takeWhile(x => x < 4)
  println(s"takeWhile: $taken")

  // Пример dropWhile
  val dropped = numbers.dropWhile(x => x < 4)
  println(s"dropWhile: $dropped")

  // Пример intersect
  val list1 = List(1, 2, 3, 4)
  val list2 = List(3, 4, 5, 6)
  val intersection = list1.intersect(list2)
  println(s"intersect: $intersection")

  // Пример diff
  val difference = list1.diff(list2)
  println(s"diff: $difference")

  // Пример concat
  val concatenated = list1.concat(list2)
  println(s"concat: $concatenated")

  // Пример collectFirst
  val firstEven = numbers.collectFirst { case x if x % 2 == 0 => x }
  println(s"collectFirst: $firstEven")

  // Пример maxBy
  val oldestPerson = people.maxBy(_.age)
  println(s"maxBy: $oldestPerson")

  // Пример minBy
  val youngestPerson = people.minBy(_.age)
  println(s"minBy: $youngestPerson")

  // Пример to
  val uniqueNumbers = numbers.toSet
  println(s"toSet: $uniqueNumbers")
  val array = numbers.toArray
  println(s"toArray: ${array.mkString(", ")}")
  val vector = numbers.toVector
  println(s"toVector: $vector")

  // Пример exists
  val hasEven = numbers.exists(x => x % 2 == 0)
  println(s"exists: $hasEven")

  // Пример mkString
  val str = numbers.mkString(", ")
  println(s"mkString: $str")

  // Пример sum
  val total = numbers.sum
  println(s"sum: $total")

  // Пример product
  val productTotal = numbers.product
  println(s"product: $productTotal")

  // Пример take
  val firstTwo = numbers.take(2)
  println(s"take: $firstTwo")

  // Пример drop
  val withoutFirstTwo = numbers.drop(2)
  println(s"drop: $withoutFirstTwo")

  // Пример find
  val found = numbers.find(_ > 2)
  println(s"find: $found")
}

object  TaskHW7 {
  // Перечисление дней недели с исп. Enumeration
  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
  }

  import WeekDay._

  // Функция, которая возвращает список всех значений между двумя днями недели
  def getDaysBetween(start: WeekDay, end: WeekDay): List[WeekDay] = {
    val allDays = WeekDay.values.toList // Получаем все дни недели в виде списка
    val startIdx = allDays.indexOf(start) // Индекс начального дня
    val endIdx = allDays.indexOf(end) // Индекс конечного дня
    if (startIdx < endIdx) allDays.slice(startIdx + 1, endIdx) // Если начальный день раньше конечного
    else allDays.slice(endIdx + 1, startIdx).reverse // Если начальный день позже конечного
  }

  // Функция, которая возвращает следующий день недели
  def getNextDay(day: WeekDay): WeekDay = {
    val allDays = WeekDay.values.toList // Получаем все дни недели в виде списка
    val nextIndex = (allDays.indexOf(day) + 1) % allDays.size // Вычисляем индекс следующего дня
    allDays(nextIndex) // Возвращаем следующий день
  }

  // Функция, которая возвращает список уникальных элементов
  def uniqueElements[T](list: List[T]): List[T] = {
    list.distinct // Метод distinct для удаления дубликатов
  }

  // Функция, которая возвращает среднее значение чисел в списке Option[Int]
  def average(list: List[Option[Int]]): Option[Double] = {
    val filtered = list.flatten // Убираем None и получаем список Int
    if (filtered.isEmpty) None // Если список пуст, возвращаем None
    else Some(filtered.sum.toDouble / filtered.size) // Иначе считаем среднее значение
  }

  // Функция, которая возвращает сумму всех значений, если они все есть, или None
  def sumAll(list: List[Option[Int]]): Option[Int] = {
    if (list.exists(_.isEmpty)) None // Если есть хотя бы один None, возвращаем None
    else Some(list.flatten.sum) // Иначе возвращаем сумму всех значений
  }

  // Функция, которая возвращает список строк, у которых длина больше 5 символов
  def filterLongStrings(list: List[Option[String]]): List[String] = {
    list.flatten.filter(_.length > 5) // Убираем None и фильтруем строки по длине
  }

  // Функция, которая возвращает список элементов, присутствующих в обоих исходных списках
  def commonElements(list1: List[String], list2: List[String]): List[String] = {
    list1.intersect(list2) //  Метод intersect для поиска общих элементов
  }

  def main(args: Array[String]): Unit = {
    // Пример функции getDaysBetween
    val daysBetween = getDaysBetween(WeekDay.Monday, WeekDay.Friday)
    println(s"Дни между Monday и Friday: $daysBetween")

    // Пример функции getNextDay
    val nextDay = getNextDay(WeekDay.Friday)
    println(s"Следующий день после Friday: $nextDay")

    // Пример функции uniqueElements
    val unique = uniqueElements(List(1, 2, 2, 3, 4, 4))
    println(s"Уникальные элементы: $unique")

    // Пример функции average
    val avg = average(List(Some(1), Some(2), None, Some(3)))
    println(s"Среднее значение: $avg")

    // Пример функции sumAll
    val sum = sumAll(List(Some(1), Some(2), Some(3)))
    println(s"Сумма всех значений: $sum")

    // Пример функции filterLongStrings
    val filteredStrings = filterLongStrings(List(Some("hello"), Some("world"), Some("scala")))
    println(s"Строки длиннее 5 символов: $filteredStrings")

    // Пример функции commonElements
    val common = commonElements(List("a", "b", "c"), List("b", "c", "d"))
    println(s"Общие элементы: $common")
  }
}

object LastTask {
  // Функция, возвращающая список всех уникальных комбинаций элементов двух списков
  def combineLists(list1: List[Int], list2: List[Int]): List[(Int, Int)] = {
    val combinations = list1.flatMap(x => list2.map(y => (x, y))) ++ list2.flatMap(x => list1.map(y => (x, y)))
    combinations.distinct
  }

  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 3)
    val list2 = List(2, 3)
    val uniqueCombinations = combineLists(list1, list2)
    println(s"Уникальные комбинации: $uniqueCombinations")
  }
}