package lecture

object HWRetro3 extends App {
  object Book {
    def apply(authors: List[String], title: String, year: Int, genre: String): Book =
      new Book(authors, title, year, genre)
  }

  class Book(authors: List[String], title: String, year: Int, genre: String) {
    // Переопределение метода toString для красивого вывода информации о книге
    override def toString: String =
      s"Authors: ${authors.mkString(", ")}\nTitle: $title\nPublished in the year: $year\nGenre: $genre"
  }

  // Создание экземпляра книги и вывод информации
  val b = Book(List("Author1", "Author2"), "Modified Title", 2025, "Fiction")
  println(b)
}

object HWRetro4 extends App {
  trait Shape {
    def area: Double
    def perimeter: Double
  }
}

// Примеры работы с Option и базовыми методами
object ScalaOption extends App {
  val o1 = Option(10)         // Some(10)
  val o2 = Option.empty[Int]  // None
  val o3 = None               // None
  val o4 = Some(5)            // Some(5)
  val o5 = Option(null)       // None
  // val o6 = Some(null)        // не рекомендуется

  // Проверки на пустоту/непустоту
  val b1: Boolean = o1.isEmpty
  val b2: Boolean = o1.isDefined
  val b3: Boolean = o1.nonEmpty

  // Проверка на содержание элемента
  val b4: Boolean = o1.contains(1)

  // Фильтрация Option
  val o_1: Option[Int] = o1.filter(_ > 5)    // Some(10)
  val o_2: Option[Int] = o1.filterNot(_ > 5) // None

  // Использование exists и forall
  val b5: Boolean = o1.exists(_ > 5)           // true
  val b6: Boolean = o1.forall(_ > 5)           // true

  // Извлечение значения
  val i  = o1.get                                      // Если o1 = None, будет выброшено исключение
  val i1 = o1.getOrElse(100)                             // Если o1 = None, возвращается 2
  println(i1)
  // val i2 = o1.getOrElse(throw new Exception("babah!"))
}

object ScalaOptionProd extends App {
  def loadById(id: Long): String = "User1"
  def loadByContractNumber(cn: String): String = "Contract123"

  val x: String =
    Option(loadById(1))
      .orElse(Option(loadByContractNumber("456")))
      .getOrElse("not found")
  println(x) // Выводит найденное значение или "not found"
}
// Пример использования pattern matching для Option
object OptionMatch extends App {
  val y = Option(3) match {
    case None =>
      println("None!")
      0
    case Some(value) if value > 2 =>
      println("> 2")
      value * 2
    case Some(v) =>
      println(s"Value: $v")
      v
  }
  println(y)
}

// Пример работы с вложенными Option и flatten
object ScalaOptionSomeSome extends App {
  val o: Option[Option[Int]] = Option(Option(1))
  val x: Option[Int] = o.flatten
  println(x)                    // Some(1)
  println(Option(None).flatten) // None
}

// Примеры использования map с Option
object ScalaOptionMap extends App {
  val o = Option(2)
  val o1 = o.map(_ + 2).map(_ + 2).map(_ + 2)
  println(o1) // Some(8)
  println("--------------")

  val o2 = o
    .map { i =>
      println(s"Первый шаг: $i")
      i + 2
    }
    .map { i =>
      println(s"Второй шаг: $i")
      i + 3
    }
    .filter { _ > 10 }
    .map { i =>
      println(s"Третий шаг: $i")
      i * 2
    }
  println(o2)
  println("----------------")
  println(o)
}


// Пример комбинирования map и flatten для Option вложенных значений
object ScalaOptionMapSomeSome extends App {
  val o = Option(Option(2))
  println(o.map(inner => inner.map(_ + 2)).flatten) // Some(4)
  println(o.flatMap(inner => inner.map(_ + 2))) // Аналог через flatMap
}

// Пример работы с Option и возможными null значениями
object ScalaOptionSomeNull extends App {
  case class Person(id: Long, name: String)
  case class Person1(id: Long, name: Option[String])
  val p = Person(2, null)

  // Здесь результатом будет Some(null), так как map не фильтрует null (поэтому проверяем вручную)
  val x = Option(p).map(_.name).map(name => if (name != null) name.length else null)
  println(x)

  // Правильное извлечение, чтобы избежать null
  val x1 = Option(p).flatMap(p => Option(p.name))
  println(x1)
}

// Пример использования foreach с Option
object OptionForeach extends App {
  val o = Option(2)
  o.foreach(println)
  o.foreach(x => println(s"Элемент: $x"))
}

// Пример использования методов map с функциями
object OptionMapDef extends App {
  val o = Option(2)
  def inc(i: Int): Int = i + 2
  val inc1: Int => Int = _ + 2
  println(o.map(inc1)) // Some(4)
  println(o.map(inc))  // Some(4)
}

// Пример использования Option.when
object OptionWhen extends App {
  val o = Option.when(1 == 2)("456")
  println(o) // None
  val o1 = Option.when(1 != 2)("456")
  println(s"o1 = ${o1}") // Some(456)
}

// Пример работы с методами Iterable для Option, преобразованного в Iterable
object OptionIterableOnceMethods extends App {
  val o1 = Option(2)
  // Преобразуем Option в список (List наследуется от Iterable)
  val i: Iterable[Int] = o1.toList

  // Получаем Option обратно из Iterable
  val o2: Option[Int] = i.headOption
  val o3: Option[Int] = i.lastOption
  val o4: Option[Int] = i.find(_ > 2) // None, так как 2 не > 2

  // Применяем методы take, drop и т.д. к Iterable
  val take1: Iterable[Int] = i.take(1)          // List(2)
  val takeWhileRes: Iterable[Int] = i.takeWhile(_ > 2) // пустой список, так как 2 не > 2
  val drop1: Iterable[Int] = i.drop(2)          // пустой список, так как отбрасываем единственный элемент
  val dropRight1: Iterable[Int] = i.dropRight(2) // пустой список
  val dropWhileRes: Iterable[Int] = i.dropWhile(_ > 2) // Список остается без изменений, так как первый элемент (2) не > 2

  // Примеры использования fold
  val foldRes: Int = i.fold(2)(_ + _)   // 2 + 2 = 4
  val foldLeftRes: String = i.foldLeft("2") { case (acc, e) => acc + e + 1 }
  val foldRightRes: String = i.foldRight("2") { case (e, acc) => acc + e + 1 }

  // Вывод результатов
  println(s"headOption: $o2")          // Some(2)
  println(s"lastOption: $o3")          // Some(2)
  println(s"find(_ > 1): $o4")         // None
  println(s"take(1): $take1")          // List(2)
  println(s"takeWhile(_ > 1): $takeWhileRes") // List()
  println(s"drop(1): $drop1")          // List()
  println(s"dropRight(1): $dropRight1")// List()
  println(s"dropWhile(_ > 1): $dropWhileRes") // List(2)
  println(s"fold: $foldRes")           // 4
  println(s"foldLeft: $foldLeftRes")   // "221"
  println(s"foldRight: $foldRightRes") // "221"
}
