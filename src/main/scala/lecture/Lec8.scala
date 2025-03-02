package lecture

object Lec8Set extends App {
  val s1 = Set(1, 2, 3)
  val s2 = Set(1, 2, 3, 3)
  println(s"s1: $s1")
  println(s"s2: $s2")
  println(s1 == s2)

  class SimpleInt(i: Int) {
    override def toString() = s"SimpleInt(${i})"
  }

  val si1 = Set(new SimpleInt(1), new SimpleInt(2), new SimpleInt(3))
  val si2 = Set(new SimpleInt(1), new SimpleInt(2), new SimpleInt(2), new SimpleInt(3), new SimpleInt(3))
  println(si1)
  println(si2)

  // Проверяем, равны ли множества
  println(si1 == si2) // false, так как объекты SimpleInt не имеют реализации equals и hashCode

  val s4 = s1 - 3 // Удаляем элемент 3 из множества s1
  println(s4)

  val s5 = s1 + 5 // Добавляем элемент 5 в множество s1
  println(s5)

  val s6 = s1 -- s2 // Вычитаем множество s2 из s1
  println(s6)
}

object Lec8Map extends App {
  val map1 = Map(1 -> "AAA", 2 -> "BBB", 3 -> "CCC")
  val map2 = Map(1 -> "AAA", 2 -> "BBB", 3 -> "CCC", 2 -> "VVV") // Ключ 2 перезаписывается значением "VVV"
  println(map1)
  println(map2)
  map1.keySet.foreach(println)
  map1.values.foreach(println)

  val transformedMap1 = map1.map { x =>
    val nK = x._1 + 1
    val nV = x._2 ++ "!!!"
    nK -> nV
  }
  println(transformedMap1)
  // То же самое, но с использованием case для более читаемого кода
  val transformedMap2 = map1.map { case (k, v) =>
    val nK = k + 1
    val nV = v ++ "!!!"
    nK -> nV
  }
  println(transformedMap2)
  val key = 1
  val v   = map1.get(key)
  val v2  = map1(key)
  val a  = Map(1 -> "AAAA")
  val a2 = a + (2 -> "BBB")
  println(a)
  println(a2)

  // Удаляем элементы
  val a3 = a - 2 // Удаляем ключ 2, но его нет в мапе
  val a4 = a - 1 - 2 // Удаляем ключи 1 и 2
  val a5 = a -- List(1, 2) // Удаляем ключи 1 и 2
  println(a3)
  println(a4)
  println(a5)

  // Обновляем значений
  val u7 = a.updated(3, "CCC") // Добавляем новый ключ 3
  val u8 = a + (2 -> "EEE") // Добавляем новый ключ 2
  println(u7)
  println(u8)
}

// Объект для экспериментов с коллекциями и Option
object Lec8CollectionTicket extends App {
  val lWithOpt = List(Option(1), None, Option(2))
  println(lWithOpt.flatten) // List(1, 2), так как flatten убирает None и извлекает значения из Some

  val oWithL = Option(List(List(1)))
  println(oWithL.map(_.flatten)) // Some(List(1)), так как flatten объединяет вложенные списки

  // Создаем список с null и другими значениями
  val lWithNulls = List(null, 1, 2, 3)
  println(lWithNulls.headOption.flatMap(x => Option(x)).map(_.toString)) // None, так как headOption возвращает Some(null), а flatMap преобразует его в None

  val llWithNulls  = lWithNulls ++ lWithNulls // Объединяем два списка
  val llWithNulls1 = lWithNulls :+ 3 // Добавляем элемент в конец списка
  val llWithNulls2 = 3 +: lWithNulls // Добавляем элемент в начало списка
  println(s"llWithNulls: $llWithNulls")
  println(s"llWithNulls1: $llWithNulls1")
  println(s"llWithNulls2: $llWithNulls2")

  // Объединяем два списка
  val list         = List(4, 5, 6)
  val llWithNulls3 = list ::: list // Объединяем два списка
  val llWithNulls4 = list ++ list // То же самое, что и :::, но работает для любых коллекций
  println(llWithNulls3)
  println(llWithNulls4)

  // Функция, которая проверяет, четные ли числа в коллекции
  def evenOrOdd(it: Iterable[Int]): Iterable[Boolean] = it.map(_ % 2 == 0)

  val exampe        = Set(2, 4, 6, 8, 10)
  val exampleResult = evenOrOdd(exampe).size
  println(exampleResult) // 5, так как все числа в множестве четные
}

object Lec8Array extends App {
  val arrayByte = Array[Byte](40, 64, 41)
  // Преобразуем массив байтов в строку
  val str = new String(arrayByte)
  println(str) // Выводит символы, соответствующие байтам: (, @, )
  // Преобразуем массив байтов в строку с помощью map и mkString
  println(arrayByte.map(_.toChar).mkString("")) // (, @, )
}