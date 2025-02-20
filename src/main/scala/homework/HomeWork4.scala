package homework

object Homework4 extends App {
  // Трейт Фигура с методом расчета площади
  trait Shape {
    def area: Double
  }

  // Кейc классы фигур
  case class Circle(radius: Double) extends Shape {
    override def area: Double = math.Pi * radius * radius
  }

  case class Rectangle(width: Double, height: Double) extends Shape {
    override def area: Double = width * height
  }

  case class Square(side: Double) extends Shape {
    override def area: Double = side * side
  }

  case class Triangle(base: Double, height: Double) extends Shape {
    override def area: Double = 0.5 * base * height
  }

  case class Trapezoid(base1: Double, base2: Double, height: Double) extends Shape {
    override def area: Double = 0.5 * (base1 + base2) * height
  }

  // Функция с полным матчингом
  def describeShape(shape: Shape): Unit = shape match {
    case Circle(radius) if radius > 10 => println("Огромный круг")
    case Circle(_) => println("Это круг")
    case Rectangle(w, h) => println(s"Прямоугольник $w x $h")
    case Square(s) => println(s"Квадрат $s x $s")
    case Triangle(b, h) => println(s"Треугольник с основанием $b и высотой $h")
    case Trapezoid(b1, b2, h) => println(s"Трапеция с основаниями $b1, $b2 и высотой $h")
  }

  // Функция с матчингом по типу
  def describeShapeByType(shape: Shape): Unit = shape match {
    case _: Circle => println("Это круг")
    case _: Rectangle => println("Это прямоугольник")
    case _: Square => println("Это квадрат")
    case _: Triangle => println("Это треугольник")
    case _: Trapezoid => println("Это трапеция")
  }

  // Объект-логгер
  sealed trait LogMessage
  case class Info(msg: String) extends LogMessage
  case class Warning(msg: String) extends LogMessage
  case class Error(msg: String) extends LogMessage

  object Logger {
    def logIt(log: LogMessage): Unit = log match {
      case Info(msg) => println(s"\u001B[32m[INFO] $msg\u001B[0m")
      case Warning(msg) => println(s"\u001B[33m[WARNING] $msg\u001B[0m")
      case Error(msg) => println(s"\u001B[31m[ERROR] $msg\u001B[0m")
    }
  }

  // Тестирование функций и логгера
  val circle = Circle(5)
  val bigCircle = Circle(15)
  val rectangle = Rectangle(4, 6)
  val square = Square(3)
  val triangle = Triangle(4, 5)
  val trapezoid = Trapezoid(3, 5, 4)

  describeShape(circle)
  describeShape(bigCircle)
  describeShape(rectangle)
  describeShape(square)
  describeShape(triangle)
  describeShape(trapezoid)

  describeShapeByType(circle)
  describeShapeByType(rectangle)
  describeShapeByType(square)
  describeShapeByType(triangle)
  describeShapeByType(trapezoid)

  Logger.logIt(Info("Запуск"))
  Logger.logIt(Warning("Мало памяти"))
  Logger.logIt(Error("Ошибка!"))
}
