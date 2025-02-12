package lecture

object ScalaClasses extends App {
  class A
  val a1 = new A()

  class Point(private val x: Int, val y: Int) {
    def distance(other: Point): Double = {
      import scala.math.sqrt

      val deltaX     = other.x - this.x
      val deltaXQuad = deltaX * deltaX

      val deltaY     = other.y - this.y
      val deltaYQuad = deltaY * deltaY

      sqrt(deltaXQuad + deltaYQuad)
    }

    private def quadX: Int = x * x
    private def quadY: Int = y * y

    override def toString: String = s"Point($x, $y)"
  }
  val p  = new Point(2, 3)
  val p1 = new Point(4, 22)
  println(p.distance(p1))
  println(p)
  println(p1)

  p.y
}

object ScalaClassesWithVar extends App {
  class Point(var x: Int, var y: Int)
  val mP = new Point(2, 3)
  mP.x = 15
  println(mP.x)
}

object ScalaClassesWithDefaultParam extends App {
  class Point(val x: Int = 1)(val y: Int = 1) {
    override def toString = s"Point($x, $y)"
  }

  val zero = new Point()()
  println(zero)
  val notZero = new Point(2)()
  println(notZero)
  val notZero1 = new Point()(y = 3)
  println(notZero1)
  val notZero2 = new Point(x = 2)(y = 4)
  println(notZero2)
}

object ScalaClassesWithAuxiliaryConstructors extends App {
  class Point(val x: Int, val y: Int) {
    def this() = this(1, 1)
    def this(x: Int) = this(x, 2)
    def this(xOrY: Int, isX: Boolean) = this(if (isX) xOrY else 1, if (!isX) 1 else xOrY)

    override def toString = s"Point($x, $y)"
  }
  val p1 = new Point()
  println(p1)
}

object ScalaClassesExtends extends App {
  class Animal(name: String) {
    def printName() = println(name)
  }

  class Dog(name: String, age: Int) extends Animal(name) {
    def printInfo = println(s"Dog($name, $age) is barking!")
  }
  class Cat(name: String, age: Int) extends Animal(name) {
    def mew = println(s"Meeeeeeow!")
  }
  def printNameByAnimal(a: Animal): Unit = a.printName()

  val a = new Animal("Elephant")
  val d = new Dog("Rex", 8)
  val c = new Cat("Whiskers", 2)

  printNameByAnimal(a)
  printNameByAnimal(d)
  printNameByAnimal(c)
}

object ScalaCaseClasses extends App {
  case class Point(var x: Int, y: Int) {
    def print(): String  = "print"
    private def print1() = "print"
  }
  val p = Point(2, 3)
  val p1 = Point.apply(2, 3)
  p.x = 2

  p.toString
  val p2 = p.copy(x = 6)
  println(p2)
  val eq = p1 == p1
  println(eq)
  p1.hashCode()

  val x: Option[(Int, Int)] = Point.unapply(p1)
  val Point(x1, y1)         = p1

  case class A(i: Int, p: Point)
  val a                       = A(2, p1)
  val z @ A(_, Point(x2, y2)) = a
  val x2_1                    = a.p.x
  println(a)
  println(z)
  println(x2)
  println(y2)
}

object ScalaObjects extends App {
  LogAny.log(2)
}

object LogAny {
  println("Initializing LogAny")
  def log(a: Any): Unit = println(a)
}

object ScalaCompanionObjects extends App {
  case class Point(x: Int, y: Int)

  object Point {
    def createZero(): Point        = Point(0, 0)
    def createFromX(x: Int): Point = Point(x, 1)
    def createFromY(y: Int): Point = Point(1, y)
    def distance(from: Point, to: Point): Double = {
      import scala.math.sqrt

      val deltaX     = to.x - from.x
      val deltaXQuad = deltaX * deltaX

      val deltaY     = to.y - from.y
      val deltaYQuad = deltaY * deltaY

      sqrt(deltaXQuad + deltaYQuad)
    }
  }

  val p1 = Point(2, 3)
  println(p1)
  val p2 = Point(4, 5)
  println(p2)
  println(Point.distance(p1, p2))
  println(Point.createFromX(2))
  println(Point.createFromY(3))
  println(Point.createZero())
}

object CompObjDefClass extends App {
  class A(val i: Int, val s: String) {
    override def toString = s"A($i, $s)"
  }

  object A {
    def apply(i: Int, s: String) = new A(i, s)
  }

  val a = A(2, "example")
  println(a)
}
