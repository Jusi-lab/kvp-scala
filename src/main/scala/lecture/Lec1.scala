package lecture

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}

object HelloWorldWithApp extends App {
  val a: Int = 2
  val b: Int = 3
  var c = a + b
  c = 10

  println(s"Сумма a и b: ${a + b}")
  println(s"Новое значение c: $c")

  val x = {
    println("Инициализация переменной x")
    10
  }
  println(x)
}

object TypesAndAll extends App {
  val intValue: Int = 42
  val longValue: Long = 100000L
  val doubleValue: Double = 3.14
  val charValue: Char = 'A'
  lazy val byteValue: Byte = 127
  val stringValue: String = "Scala"
  val boolValue: Boolean = true

  println(s"Int: $intValue, Long: $longValue, Double: $doubleValue")
  println(s"Char: $charValue, Byte: $byteValue, String: $stringValue, Boolean: $boolValue")
}

object StringManipulations extends App {
  val x: Int = 5
  val y: Long = 100L
  val str: String = "Hello"
  val concatenated = s"$str, $x + $y = ${x + y}"

  println(concatenated)

  val multilineStr = s"""
                        |First line: $x
                        |Second line: $str
  """.stripMargin
  println(multilineStr)
}

object TypeDef extends App {
  val sum = 2 + 3
  val diff = 7 - 3
  val product = 4 * 5
  val quotient = 8 / 2
  val remainder = 7 % 3

  println(s"Sum: $sum, Difference: $diff, Product: $product, Quotient: $quotient, Remainder: $remainder")
}

object TupleExample extends App {
  val person: (Int, String, Boolean) = (25, "Alice", true)
  val (age, name, isActive) = person
  println(s"Age: $age, Name: $name, Active: $isActive")

  type PersonId = Long
  def getPersonDetails(id: PersonId): String = s"Details for person with ID: $id"
  println(getPersonDetails(12345L))
}

object JavaScalaTypes extends App {
  import java.lang.{Long => JLong}
  val longValue: Long = 100L
  val javaLongValue: JLong = 200L

  println(s"Scala Long: $longValue, Java Long: $javaLongValue")
}

object DefFun extends App {
  def greet(name: String): Unit = {
    println(s"Hello, $name!")
  }

  greet("Bob")

  val greetFn: String => Unit = name => println(s"Welcome, $name!")
  greetFn("Charlie")
}

object DefaultDefParam extends App {
  def greetUser(name: String = "Guest", age: Int = 30, isAdmin: Boolean = false): String = {
    s"Name: $name, Age: $age, Admin: $isAdmin"
  }

  println(greetUser()) // Используем параметры по умолчанию
  println(greetUser(name = "Alice", age = 25)) // Указываем параметры
}

object DefParams extends App {
  def greet(name: String)(age: Int): String = {
    s"Name: $name, Age: $age"
  }

  println(greet("Bob")(22))
}
