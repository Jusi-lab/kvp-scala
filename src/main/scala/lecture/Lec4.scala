
object RevisedTraitExample extends App {
  trait Creature {
    val species: String
    def getSpecies: String
    val characteristic: String = ""
    def getCharacteristic: String = characteristic
  }

  case class Dog(breed: String) extends Creature {
    override val species: String = breed
    override def getSpecies: String = breed
    override def getCharacteristic: String = "Loyal"
  }

  trait CanFly {
    def details: Unit = println("I can soar in the sky!")
  }

  case class Bird(species: String) extends Creature with CanFly {
    override def getSpecies: String = species
  }

  val parrot = Bird("Parrot")
  parrot.details

  abstract class Shape(name: String) {
    def calculateArea: Double
    override def toString: String = s"Shape: $name, Area: ${calculateArea}"
  }

  case class Circle(name: String, radius: Double) extends Shape(name) {
    override def calculateArea: Double = Math.PI * radius * radius
  }

  case class Triangle(name: String, base: Double, height: Double) extends Shape(name) {
    override def calculateArea: Double = 0.5 * base * height
  }

  val circle = Circle("Circle1", 3.5)
  val triangle = Triangle("Triangle1", 4, 5)

  println(circle.calculateArea)
  println(triangle.calculateArea)

  sealed trait Operation
  case class Insert(item: String, count: Int) extends Operation
  case class Retrieve(id: Long, limit: Int) extends Operation
  case class Modify(id: Long, newValue: String) extends Operation
  case class Remove(id: Long) extends Operation

  val operation: Operation = Modify(2L, "UpdatedValue")
  operation match {
    case Insert(item, count) => println(s"Insert item: $item with count: $count")
    case Retrieve(id, limit) => println(s"Retrieve ID: $id with limit: $limit")
    case Modify(id, newValue) => println(s"Modify ID: $id with new value: $newValue")
    case Remove(id) => println(s"Remove ID: $id")
  }

  // val randomNumber = scala.util.Random.nextInt(4)
  // println(randomNumber)
  // randomNumber match {
  //   case 0 =>
  //     println("Zero")
  //     println("Nothingness")
  //   case 1 => println("One")
  //   case 2 => println("Two")
  //   case _ => println("Something else")
  // }
}
