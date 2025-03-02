package homework

object Lec8Examples extends App {

  //Метод map для Set
  val set = Set(1, 2, 3, 4, 5)
  val squaredSet = set.map(x => x * x)
  println(s"1. Метод map для Set: $squaredSet")

  // Метод flatten для List
  val listOfLists = List(List(1, 2), List(3, 4), List(5, 6))
  val flattenedList = listOfLists.flatten
  println(s"2. Метод flatten для List: $flattenedList")

  //Метод flatMap для Option
  val optList = Option(List(1, 2, 3))
  val flatMappedList = optList.flatMap(list => Option(list.map(_ * 2)))
  println(s"3. Метод flatMap для Option: $flatMappedList")

  //Метод filter для List
  val numbers = List(1, 2, 3, 4, 5, 6)
  val evenNumbers = numbers.filter(_ % 2 == 0)
  println(s"4. Метод filter для List: $evenNumbers")

  //Метод foreach для Map
  val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
  println("5. Метод foreach для Map:")
  map.foreach { case (key, value) =>
    println(s"   Key: $key, Value: $value")
  }
  //Метод updated для Map
  val originalMap = Map(1 -> "One", 2 -> "Two")
  val updatedMap = originalMap.updated(2, "TwoUpdated")
  println(s"6. Метод updated для Map: $updatedMap")

  //Метод ++ для List
  val list1 = List(1, 2, 3)
  val list2 = List(4, 5, 6)
  val combinedList = list1 ++ list2
  println(s"7. Метод ++ для List: $combinedList")

  //Метод mkString для Array
  val array = Array("Hello", "World", "!")
  val resultString = array.mkString(" ")
  println(s"8. Метод mkString для Array: $resultString")

  // 9. Метод headOption для List
  val list = List(1, 2, 3)
  val headOption = list.headOption
  println(s"9. Метод headOption для List: $headOption")

  // 10. Метод values для Map
  val mapValues = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
  val values = mapValues.values
  println(s"10. Метод values для Map: $values")

  // 11. Метод keySet для Map
  val mapKeys = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
  val keys = mapKeys.keySet
  println(s"11. Метод keySet для Map: $keys")

  // 12. Метод -- для Set
  val set1 = Set(1, 2, 3, 4, 5)
  val set2 = Set(3, 4)
  val differenceSet = set1 -- set2
  println(s"12. Метод -- для Set: $differenceSet")

  // 13. Метод + для Set
  val setAdd = Set(1, 2, 3)
  val newSet = setAdd + 4
  println(s"13. Метод + для Set: $newSet")

  // 14. Метод - для Set
  val setRemove = Set(1, 2, 3, 4)
  val newSetRemove = setRemove - 3
  println(s"14. Метод - для Set: $newSetRemove")

  // 15. Метод map для Array
  val arrayMap = Array(1, 2, 3, 4)
  val squaredArray = arrayMap.map(x => x * x)
  println(s"15. Метод map для Array: ${squaredArray.mkString(", ")}")

  // 16. Метод map для Option
  val opt = Option(5)
  val mappedOpt = opt.map(_ * 2)
  println(s"16. Метод map для Option: $mappedOpt")

  // 17. Метод flatMap для List
  val listFlatMap = List(1, 2, 3)
  val flatMappedListExample = listFlatMap.flatMap(x => List(x, x * 2))
  println(s"17. Метод flatMap для List: $flatMappedListExample")

  // 18. Метод flatten для Option
  val optFlatten = Option(Option(42))
  val flattenedOpt = optFlatten.flatten
  println(s"18. Метод flatten для Option: $flattenedOpt")

  // 19. Метод map для Map
  val mapMap = Map(1 -> "One", 2 -> "Two")
  val mappedMap = mapMap.map { case (k, v) => (k + 1, v + "!") }
  println(s"19. Метод map для Map: $mappedMap")

  // 20. Метод map для List с использованием функции
  def customFunction(x: Int): String = s"Number: $x"

  val listCustom = List(1, 2, 3)
  val mappedListCustom = listCustom.map(customFunction)
  println(s"20. Метод map для List с польз. функцией: $mappedListCustom")
}

object HomeWork8 extends App {

  // Функция, которая возвращает объединение двух множеств
  def unionSets(set1: Set[Int], set2: Set[Int]): Set[Int] = {
    set1.union(set2)
  }

  // Функция, которая удаляет элемент из множества
  def removeElement(set: Set[Int], element: Int): Set[Int] = {
    set - element
  }

  // Функция, которая возвращает элементы, присутствующие только в одном из множеств
  def symmetricDifference(set1: Set[Int], set2: Set[Int]): Set[Int] = {
    set1.diff(set2).union(set2.diff(set1))
  }

  // Функция, которая удваивает значения в Map[String, Option[Int]]
  def doubleOptionValues(map: Map[String, Option[Int]]): Map[String, Int] = {
    map.collect {
      case (key, Some(value)) => key -> (value * 2)
    }
  }

  // Функция, которая увеличивает значения в Map[String, Int] на 1
  def incrementValues(map: Map[String, Int]): Map[String, Int] = {
    map.view.mapValues(_ + 1).toMap
  }

  // Функция, которая разделяет Map[String, Int] на два Map по значениям
  def splitMapByValues(map: Map[String, Int]): (Map[String, Int], Map[String, Int]) = {
    val (positive, negative) = map.partition { case (_, value) => value > 0 }
    (positive, negative)
  }

  // Функция, которая объединяет два Map[String, Int], складывая значения для общих ключей
  def mergeMaps(map1: Map[String, Int], map2: Map[String, Int]): Map[String, Int] = {
    map1 ++ map2.map { case (key, value) =>
      key -> (value + map1.getOrElse(key, 0))
    }
  }

  //  Функция, которая возвращает новый Map, содержащий только те ключи, у которых значения равны
  def filterEqualValues(map1: Map[String, Int], map2: Map[String, Int]): Map[String, Int] = {
    map1.filter { case (key, value) =>
      map2.get(key).contains(value)
    }
  }
  val set1 = Set(1, 2, 3)
  val set2 = Set(3, 4, 5)
  println(s"Объединение множеств: ${unionSets(set1, set2)}") // Вывод: Set(1, 2, 3, 4, 5)
  val set = Set(1, 2, 3, 4)
  println(s"Множество после удаления элемента: ${removeElement(set, 3)}") // Вывод: Set(1, 2, 4)
  val setA = Set(1, 2, 3)
  val setB = Set(3, 4, 5)
  println(s"Разность множеств: ${symmetricDifference(setA, setB)}") // Вывод: Set(1, 2, 4, 5)
  val optionMap = Map("a" -> Some(1), "b" -> None, "c" -> Some(3))
  println(s"Удвоенные значения: ${doubleOptionValues(optionMap)}") // Вывод: Map(a -> 2, c -> 6)
  val intMap = Map("a" -> 1, "b" -> 2, "c" -> 3)
  println(s"Увеличенные значения: ${incrementValues(intMap)}") // Вывод: Map(a -> 2, b -> 3, c -> 4)
  val mapToSplit = Map("a" -> 1, "b" -> -2, "c" -> 3, "d" -> -4)
  val (positiveMap, negativeMap) = splitMapByValues(mapToSplit)
  println(s"Положительные значения: $positiveMap") // Вывод: Map(a -> 1, c -> 3)
  println(s"Отрицательные значения: $negativeMap") // Вывод: Map(b -> -2, d -> -4)
  val map1 = Map("a" -> 1, "b" -> 2)
  val map2 = Map("b" -> 3, "c" -> 4)
  println(s"Объединенные Map: ${mergeMaps(map1, map2)}") // Вывод: Map(a -> 1, b -> 5, c -> 4)
  val mapA = Map("a" -> 1, "b" -> 2, "c" -> 3)
  val mapB = Map("a" -> 1, "b" -> 5, "c" -> 3)
  println(s"Ключи с равными значениями: ${filterEqualValues(mapA, mapB)}") // Вывод: Map(a -> 1, c -> 3)
}