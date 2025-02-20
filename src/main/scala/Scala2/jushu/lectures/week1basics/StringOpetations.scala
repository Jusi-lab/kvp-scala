package Scala2.jushu.lectures.week1basics

object StringOpetations extends App{
  val aString: String = "Hello, world!"

  println(aString.length) // выводит 13
  println(aString.charAt(1)) // выводит e
  println(aString.substring(0, 2)) // выводит He
  println(aString.startsWith("He")) // выводит true
  println(aString.replace("!", ".")) // выводит Hello, world.
  println(aString.toLowerCase) // выводит hello, world!
  println(aString.toUpperCase) // выводит HELLO, WORLD!
  println("abcd".reverse) // выводит dcba
  println("abcd".take(2)) // выводит ab


  val aStringNum: String = "123"
  val anIntNum: Int = aStringNum.toInt

  "df".toInt // преобразование "df" в число невозможно, будет NumberFormatException

  //Указанные методы можно применять по отдельности:
  val aString1 = "Some String"

  val lowerCaseStr = aString1.toLowerCase
  val strLength = aString1.length
  val lowerCaseSubStr = lowerCaseStr.substring(5, strLength)

  println(lowerCaseSubStr) // string
  //А можно комбинировать:
  println(aString.toLowerCase.substring(5, aString.length)) // string
}
