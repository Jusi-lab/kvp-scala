package homework

import scala.annotation.tailrec

object HomeWork2 extends App {

  // Функция проверки четности числа
  def checkEvenOdd(n: Int): String = if (n % 2 == 0) "четное" else "нечетное"

  println(s"4 - ${checkEvenOdd(4)}")
  println(s"7 - ${checkEvenOdd(7)}")
  println(s"10 - ${checkEvenOdd(10)}")

  // Функция проверки числа на знак
  def checkNumberSign(n: Int): String =
    if (n > 0) "Положительное"
    else if (n < 0) "Отрицательное"
    else "Ноль"

  println(checkNumberSign(5))
  println(checkNumberSign(-3))
  println(checkNumberSign(0))

  // Функция вывода чисел от 1 до 10 (включая 10)
  def printNumbersInclusive(): Unit = {
    for (i <- 1 to 10) println(i)
  }

  // Функция вывода чисел от 1 до 10 (не включая 10)
  def printNumbersExclusive(): Unit = {
    for (i <- 1 until 10) println(i)
  }

  printNumbersInclusive()
  printNumbersExclusive()

  // Функция вывода таблицы умножения на 5 до 10
  def printMultiplicationTable5(): Unit = {
    for (i <- 1 to 10) println(s"5 * $i = ${5 * i}")
  }

  printMultiplicationTable5()

  // Фибоначчи через обычную рекурсию
  def fibonacci(n: Int): Int = {
    if (n <= 1) n
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  // Фибоначчи через хвостовую рекурсию
  @tailrec
  def fibonacciTailRec(n: Int, a: Int = 0, b: Int = 1): Int = {
    if (n == 0) a
    else if (n == 1) b
    else fibonacciTailRec(n - 1, b, a + b)
  }

  // Вывод первых 10 чисел Фибоначчи
  println("Фибоначчи (обычная рекурсия):")
  for (i <- 0 until 10) print(s"${fibonacci(i)} ")

  println("\nФибоначчи (хвостовая рекурсия):")
  for (i <- 0 until 10) print(s"${fibonacciTailRec(i)} ")

}
