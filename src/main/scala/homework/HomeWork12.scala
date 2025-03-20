package lec25_1.lecture

import scala.beans.BeanProperty

object Main extends App {
  // Примеры из лекции
  {
    println("Примеры из лекции:")

    // Метод `voice()`
    val bird = Bird()
    bird.voice()

    //  Класс `Box[T]`
    val boxBird = new Box[Bird](Bird())
    boxBird.get.voice()

    // Класс `BoxV2[+T]` (ковариантный)
    val boxDogV2 = new BoxV2[Dog](Dog())
    val boxAnimal: BoxV2[Animal] = boxDogV2
    boxAnimal.get.voice()

    // Класс `BoxV3[-T]` (контравариантный)
    val boxDogV3: BoxV3[Dog] = new BoxV3[Animal](Dog())
    println(boxDogV3.serialize(Dog()))

    // Сеттеры в `Person`
    val p = Person(1, "Bob", 30, null, null, null)
    p.setInn(Inn("1"))
    p.setSnils(Snils("12"))
    p.setPassport(Passport("1234567890"))
    println(p)
  }

  // Новые обобщённые функции
  {
    println("\nНовые обобщённые функции:")

    // Функция, возвращающая первый элемент списка
    def firstElement[T](list: List[T]): Option[T] = list.headOption

    val list1 = List(1, 2, 3)
    println(firstElement(list1))

    // Функция, возвращающая список уникальных элементов
    def uniqueElements[T](list: List[T]): List[T] = list.distinct

    val list2 = List(1, 2, 2, 3, 3, 3)
    println(uniqueElements(list2))

    // Функция, возвращающая количество элементов в списке
    def countElements[T](list: List[T]): Int = list.length

    val list3 = List("a", "b", "c")
    println(countElements(list3))

    // Функция, применяющая преобразование к каждому элементу списка
    def mapElements[T, U](list: List[T], f: T => U): List[U] = list.map(f)

    val list4 = List(1, 2, 3)
    val result = mapElements(list4, (x: Int) => x * 2)
    println(result)
  }
}

// Классы и трейты из лекции
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

class BoxV2[+T](value: T) {
  def get: T = value
}

class BoxV3[-T](value: T) {
  def serialize(value: T): String = value.toString
}

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

object GenericFunctions extends App {

  //  Функция, возвращающая первый элемент списка
  def firstElement[T](list: List[T]): Option[T] = list.headOption

  //  Функция, возвращающая список уникальных элементов
  def uniqueElements[T](list: List[T]): List[T] = list.distinct

  //  Функция, возвращающая количество элементов в списке
  def countElements[T](list: List[T]): Int = list.length

  //  Функция, применяющая преобразование к каждому элементу списка
  def transformList[T, U](list: List[T], transform: T => U): List[U] = list.map(transform)

  val intList = List(1, 2, 2, 3, 3, 3)
  val stringList = List("a", "b", "c")
  println(s"First element of intList: ${firstElement(intList)}")
  println(s"First element of empty list: ${firstElement(List.empty[Int])}")
  println(s"Unique elements of intList: ${uniqueElements(intList)}")
  println(s"Count of elements in stringList: ${countElements(stringList)}")
  val transformedList = transformList(intList, (x: Int) => x * 2)
  println(s"Transformed list: $transformedList")
}