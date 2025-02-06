package homework

object Main extends App {
  //  Возведения числа в квадрат
  def square(n: Int): Int = n * n

  //  Вычисления площади круга
  def circleArea(radius: Double): Double = Math.PI * radius * radius

  //  Перевода температуры из Цельсия в Фаренгейт
  def celsiusToFahrenheit(celsius: Double): Double = (celsius * 9/5) + 32

  //  Вычисления длины строки
  def stringLength(str: String): Int = str.length

  //  Вычисления расстояния между двумя точками (x1, y1) и (x2, y2)
  def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double = {
    Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
  }

  // Примеры возведения в квадрат
  println(s"5 -> ${square(5)}")
  println(s"10 -> ${square(10)}")
  println(s"-3 -> ${square(-3)}")

  // Вычисление площади окружности радиусом 4.5
  println(s"Площадь круга с радиусом 4.5: ${circleArea(4.5)}")

  // Преобразование температуры из 25 градусов Цельсия в Фаренгейт
  println(s"25°C в Фаренгейтах: ${celsiusToFahrenheit(25)}")

  // Длина строки "Hello, Scala!"
  println(s"Длина строки 'Hello, Scala!': ${stringLength("Hello, Scala!")}")

  // Расстояние между точками (1,2) и (4,6)
  println(s"Расстояние между (1,2) и (4,6): ${distance(1, 2, 4, 6)}")
}
