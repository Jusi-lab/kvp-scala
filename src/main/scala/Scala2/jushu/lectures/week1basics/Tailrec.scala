package Scala2.jushu.lectures.week1basics

import scala.annotation.tailrec
import scala.sys.process.BasicIO.input

//функцию powerOfTwo, вычисляющую степень двойки.
object Tailrec {
  def powerOfTwo(n: Int): BigInt = {
    @tailrec
    def loop(x: Int, accumulator: BigInt = 2): BigInt = {
      if (x <= 1) accumulator
      else loop(x-1, 2*accumulator)
    }
    loop(n)
  }
}
//увеличивает заданное число x на число y n-ое количество раз
//выводит на экран полученное на шаге 1 число столько раз, сколько в нем цифр, и фразу is the result
object Tailrec2 {
    def powerOfTwo(n: Int): BigInt = {
      @tailrec
      def loop(x: Int, accumulator: BigInt = 2): BigInt = {
        if (x <= 1) accumulator
        else loop(x-1, 2*accumulator)
      }
      loop(n)
    }
  //От вас требуется модифицировать поданную на вход строку так, чтобы слова в ней разместились в обратном порядке. Например, строка I like     Scala должна трансформироваться в Scala like I
  // Функция для перестановки слов в обратном порядке
  def reverseWords(s: String): String = {
    val words = s.trim.split("\\s+") // Удаляем лишние пробелы и разбиваем на слова
    words.reverse.mkString(" ")      // Обратный порядок слов и объединение с одним пробелом
  }
  // Обращение к входной строке через переменную input
  //val result = reverseWords(input)
  // Вывод результата
  //println(result)
}
