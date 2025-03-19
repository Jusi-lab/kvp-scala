package lecture

import scala.beans.BeanProperty

object Lec12Generic extends App {
  trait Animal {
    def voice(): Unit
  }

  case class Cat() extends Animal {
    override def voice(): Unit = println("I am Mya")
  }

  case class Dog() extends Animal {
    override def voice(): Unit = println("I am Gav")
  }

  case class Bird() extends Animal {
    override def voice(): Unit = println("I am Chirik")
  }

  class Box[T](value: T) {
    def get: T = value
  }

  val cat    = Cat()
  val dog    = Dog()
  val bird   = Bird()
  val boxCat = new Box[Cat](cat)
  boxCat.get.voice()
  val boxDog = new Box[Dog](dog)
  boxDog.get.voice()
  val boxBird = new Box[Bird](bird)
  boxBird.get.voice()

  // Попробуем создать Box[Animal] и посмотрим, что произойдет
  // val boxAnimal: Box[Animal] = boxCat // CE (Compilation Error)
}

object Lec12ImplOption extends App {
  implicit class AnyToOption[T](value: T) {
    def asOption: Option[T] = Option(value)
  }

  val strNull: String = null
  val str: String     = "abc"
  println(strNull.asOption)
  println(str.asOption)
}

object Lec12Cov extends App {
  trait Animal {
    def voice(): Unit
  }

  case class Cat() extends Animal {
    override def voice(): Unit = println("I am Mya")
  }

  case class Dog() extends Animal {
    override def voice(): Unit = println("I am Gav")
  }

  case class Bird() extends Animal {
    override def voice(): Unit = println("I am Chirik")
  }

  val cat = Cat()
  val dog = Dog()
  val bird = Bird()

  class BoxV2[+T](value: T) {
    def get: T = value
  }

  val boxCatV2 = new BoxV2[Cat](cat)
  val boxDogV2 = new BoxV2[Dog](dog)
  val boxBirdV2 = new BoxV2[Bird](bird)

  // Ковариантность позволяет присвоить BoxV2[Cat] переменной типа BoxV2[Animal]
  val boxAnimal: BoxV2[Animal] = boxCatV2
  boxAnimal.get.voice() // Выведет: I am Mya
}

object Lec12Contr extends App {
  trait Animal {
    def voice(): Unit
  }

  case class Cat() extends Animal {
    override def voice(): Unit = println("I am Mya")
  }

  case class Dog() extends Animal {
    override def voice(): Unit = println("I am Gav")
  }

  case class Bird() extends Animal {
    override def voice(): Unit = println("I am Chirik")
  }

  val cat = Cat()
  val dog = Dog()
  val bird = Bird()

  class BoxV3[-T](value: T) {
    def serialize(value: T): String = value.toString
  }

  val boxCatV3 = new BoxV3[Cat](cat)
  val boxDogV3 = new BoxV3[Dog](dog)
  val boxBirdV3 = new BoxV3[Bird](bird)

  // Контравариантность позволяет присвоить BoxV3[Animal] переменной типа BoxV3[Cat]
  val boxAnimalV3: BoxV3[Cat] = new BoxV3[Animal](cat)
  println(boxAnimalV3.serialize(cat)) // Выведет: Cat()
}

object Lec12Upper extends App {
  sealed trait Box[+A] {
    def map[B](f: A => B): Box[B] = {
      if (isEmpty) BoxNone else BoxSome(f(this.get))
    }

    def flatMap[B](f: A => Box[B]): Box[B] = {
      if (isEmpty) BoxNone else f(this.get)
    }

    def contains[B >: A](value: B): Boolean = {
      nonEmpty && this.get == value
    }

    def withFilter(p: A => Boolean): Box[A] = {
      if (nonEmpty && p(this.get)) this else BoxNone
    }

    def filter(p: A => Boolean): Box[A] = {
      if (nonEmpty && p(this.get)) this else BoxNone
    }

    def get: A

    def nonEmpty: Boolean = !isEmpty

    def isEmpty: Boolean = this eq BoxNone
  }

  case class BoxSome[+A](value: A) extends Box[A] {
    override def get: A = value
  }

  case object BoxNone extends Box[Nothing] {
    override def get: Nothing = throw new RuntimeException("NONE!!!")
  }

  object Box {
    def apply[T](value: T): Box[T] = {
      if (value == null) BoxNone else BoxSome(value)
    }
  }

  val box = Box(42)
  val filteredBox = box.filter(_ > 40)
  println(filteredBox.get) // Выведет: 42
}

object Lec12TypedClass extends App {
  case class Snils(snils: String)
  case class Inn(inn: String)
  case class Passport(passportNumber: String)

  case class Person(id: Long, name: String, @BeanProperty var age: Int, @BeanProperty var inn: Inn, @BeanProperty var snils: Snils, @BeanProperty var passport: Passport)

  trait NeedToValidate[T] {
    def validate(v: T): T
  }

  object ValidateTypeClassSyntax {
    implicit class NeedToValidOps[T](value: T) {
      def validated(implicit needToValidate: NeedToValidate[T]): T = needToValidate.validate(value)
    }

    implicit val validateInn: NeedToValidate[Inn] = (v: Inn) => {
      if (v.inn.length != 1) throw new Exception("Bad inn")
      else v
    }

    implicit val validateSnils: NeedToValidate[Snils] = { (v: Snils) =>
      if (v.snils.length != 2) throw new Exception("Bad Snills")
      else v
    }

    implicit val validatePassport: NeedToValidate[Passport] = { (v: Passport) =>
      if (v.passportNumber.length != 10) throw new Exception("Bad Passport")
      else v
    }
  }

  case class PersonDocumentInfoUpdate(inn: Inn, snils: Snils, passport: Passport)

  val p  = Person(2, "scmd", 14, null, null, null)
  val e1 = PersonDocumentInfoUpdate(Inn("1"), Snils("2"), Passport("1234567890"))
  def proccessPersonDocumentInfoUpdated(event: PersonDocumentInfoUpdate): Unit = {
    import ValidateTypeClassSyntax._
    p.setInn(event.inn.validated)
    p.setSnils(event.snils.validated)
    p.setPassport(event.passport.validated)
    println(p)
  }

  proccessPersonDocumentInfoUpdated(e1)
}

object ApplicativeSyntax {
  trait Applicative[I, O, M[_]] {
    def ap(m1: M[I => O])(m2: M[I]): M[O]
  }

  implicit class HasApplication[I, O, M[_]](m1: M[I => O]) {
    def application(m2: M[I])(implicit applicative: Applicative[I, O, M]): M[O] =
      applicative.ap(m1)(m2)
  }

  implicit def optionAp[I, O]: Applicative[I, O, Option] = new Applicative[I, O, Option] {
    override def ap(m1: Option[I => O])(m2: Option[I]): Option[O] =
      for {
        f <- m1
        i <- m2
      } yield f(i)
  }
}

object ApplicativeExample extends App {
  import ApplicativeSyntax._

  implicit def listAp[I, O]: Applicative[I, O, List] = new Applicative[I, O, List] {
    override def ap(m1: List[I => O])(m2: List[I]): List[O] =
      for {
        f <- m1
        i <- m2
      } yield f(i)
  }

  {
    val f: Int => Int            = _ * 2
    val vOpt: Option[Int]        = Option(2)
    val fOpt: Option[Int => Int] = Option(f)

    println {
      val res = fOpt.application(vOpt)
      res
    }
  }

  {
    val f: Int => Int           = _ * 2
    val f1: Int => Int          = _ * 3
    val vList: List[Int]        = List(1, 2, 3)
    val fList: List[Int => Int] = List(f, f1)

    println {
      val res = fList.application(vList)
      res
    }
  }

  {
    val f: String => String = _ + "!"
    val f1: String => String = _ + "?"
    val vList: List[String] = List("Hello", "World")
    val fList: List[String => String] = List(f, f1)

    println {
      val res = fList.application(vList)
      res
    }
  }
}