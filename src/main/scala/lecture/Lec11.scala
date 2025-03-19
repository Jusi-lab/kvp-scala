package lecture

object HW extends App {
  object Implicits {
    // 1. Параметры (implicit val)
    // 2. Функции (implicit def)
    // 3. Классы (implicit class)

    // Ключевое слово implicit обязательно
    // Тип должен соответствовать объявленному
    // Implicit должен находиться в зоне видимости
    // Всё происходит на этапе компиляции (compile-time)
  }

  case class Example(value: String)

  object ImplicitValForImport {
    implicit val defaultExample: Example = Example("Default Value")
  }

  object ImplicitVal extends App {
    implicit val defaultInt: Int = 42

    def printExample(s: String)(implicit e: Example): Unit = {
      println(s"$s ${e.value}")
    }

    val customExample = Example("Custom Value")
    printExample("Explicit:")(customExample)

    def combinedExample(s: String)(implicit e: Example, i: Int): Unit = {
      println(s"$s ${e.value}, Int: $i")
    }
  }

  object ImplicitDef extends App {
    case class A(i: Int)
    case class B(i: Int)

    // Преобразование A в B
    implicit def aToB(a: A): B = B(a.i)

    val b: B = A(10) // Неявное преобразование A в B
    println(b)

    case class C(i: Int)

    // Преобразование B в C
    implicit def bToC(b: B): C = C(b.i)

    // Преобразование A в C через цепочку implicit def
    implicit def aToC(a: A): C = bToC(aToB(a))

    val c: C = A(10) // Неявное преобразование A в C
    println(c)
  }

  object ImplicitDefConversion extends App {
    val javaDouble: java.lang.Double = null
    println(javaDouble)

    val scalaDouble: Double = javaDouble // Неявное преобразование в Double
    println(scalaDouble) // Вывод 0.0

    val optScalaDouble: Option[Double] = Option(javaDouble)
    println(optScalaDouble) // Вывод Some(0.0)

    val optJavaDouble: Option[java.lang.Double] = Option(javaDouble).map(x => x)
    println(optJavaDouble) // Вывод None
  }

  object ImplicitDefForImport extends App {
    // Преобразование строки в её длину
    implicit def stringToInt(s: String): Int = s.length
  }

  object ImplicitDefImport extends App {
    import ImplicitDefForImport._

    // Используем implicit преобразование для деления длины строки на 2
    val result: Int = "Hello, World!" / 2
    println(result)
  }

  object ImplicitClass extends App {
    // Добавляем метод opt к Int
    implicit class IntOps(i: Int) {
      def opt: Option[Int] = Option(i)
    }

    val number: Int = 5
    val optionalNumber: Option[Int] = number.opt
    println(optionalNumber) // Вывод: Some(5)
  }

  object StrSyntax {
    // Добавляем метод orEmpty к String
    implicit class StrOps(s: String) {
      def orEmpty: String = Option(s).getOrElse("")
    }
  }

  object ImplicitClassStrSyntax extends App {
    import StrSyntax._

    val nonNullString: String = "Hello"
    val nullString: String = null

    println(nonNullString.orEmpty)
    println(nullString.orEmpty)

    case class Person(name: String, age: Int)
    val person: Person = Person(null, 25)

    println(person.name.orEmpty)
  }

  object ImplicitHell extends App {
    implicit val strings: Seq[String] = Seq("Scala", "Java", "Kotlin")

    // Пример использования implicit параметра
    def splitAndJoin(s: String)(implicit seq: Seq[String]): String = {
      s.split(" ").mkString(seq.mkString("(", ",", ")"))
    }

    val result: String = splitAndJoin("Hello World")
    println(result)
  }
}