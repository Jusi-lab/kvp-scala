package homework

object Homework3 extends App {
  // Класс "Книга" с полями и переопределением метода toString
  class Book(val title: String, val author: String, val year: Int) {
    override def toString: String = s"Book(title='$title', author='$author', year=$year)"
  }

  // Companion object с методом createBook
  object Book {
    def createBook(title: String, author: String, year: Int): Book = new Book(title, author, year)
  }

  // Создание книги через companion object
  val book = Book.createBook("Scala Programming", "Martin Odersky", 2023)
  println(book) // Вывод в консоль

  // Базовый класс Фигура с методом расчета площади
  abstract class Shape {
    def area: Double
  }

  // Кейc класс Круг
  case class Circle(radius: Double) extends Shape {
    override def area: Double = math.Pi * radius * radius
  }

  // Кейc класс Прямоугольник
  case class Rectangle(width: Double, height: Double) extends Shape {
    override def area: Double = width * height
  }

  // Кейc класс Квадрат
  case class Square(side: Double) extends Shape {
    override def area: Double = side * side
  }

  // Функция для вывода площади фигуры
  def printArea(shape: Shape): Unit = {
    println(s"Площадь фигуры: ${shape.area}")
  }

  // Создание фигур и вывод их площадей
  val circle = Circle(5)
  val rectangle = Rectangle(4, 6)
  val square = Square(3)

  printArea(circle)     // Площадь круга
  printArea(rectangle)  // Площадь прямоугольника
  printArea(square)     // Площадь квадрата
}
