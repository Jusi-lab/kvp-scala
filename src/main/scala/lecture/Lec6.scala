package lecture

import scala.util.{Failure, Success, Try}

object Lec6ForCompr extends App {
  case class ClassWithOption(id: Long, urza: Option[Urza])
  case class Urza(name: Option[String])

  // ClassWithOption с вложенными Option
  val cwo: Option[ClassWithOption] = Option(ClassWithOption(1, Some(Urza(Some("urza")))))
  val name: Option[String] = cwo.flatMap(_.urza).flatMap(_.name)

  // Пример раскрытия for-comprehension в flatMap и map
  val nameCorrect: Option[String] = cwo.flatMap { cwo =>
    cwo.urza.flatMap { urza =>
      urza.name.map { n =>
        n.capitalize
      }
    }
  }

  // Использование for-comprehension для извлечения имени
  val name1: Option[String] = for {
    cwo1 <- cwo
    urza <- cwo1.urza
    name <- urza.name
  } yield name

  // Альтернативный вариант for-comprehension с использованием паттерн-матчинга
  val name2: Option[String] = for {
    ClassWithOption(_, Some(Urza(Some(name)))) <- cwo if name.nonEmpty
  } yield name

  println(s"name: $name")
  println(s"name1: $name1")
  println(s"name2: $name2")
}

object Lec6ForComprCustom extends App {
  // Трейт ForCompSupport для кастомной реализации for-comprehension
  trait ForCompSupport[A] {
    def flatMap[B](f: A => ForCompSupport[B]): ForCompSupport[B] =
      if (isEmpty) ForCompSupportEmpty[B]() else f(this.get)

    def map[B](f: A => B): ForCompSupport[B] =
      if (isEmpty) ForCompSupportEmpty[B]() else ForCompSupportTest(f(this.get))

    def withFilter(p: A => Boolean): ForCompSupport[A] =
      if (isEmpty || p(this.get)) this else ForCompSupportEmpty[A]()

    def isEmpty: Boolean = this == ForCompSupportEmpty[A]()

    def get: A
  }

  // Компаньон-объект для ForCompSupport
  object ForCompSupport {
    def apply[A](i: A): ForCompSupport[A] = if (i == null) ForCompSupportEmpty() else ForCompSupportTest(i)
  }

  // Пустой случай для ForCompSupport
  case class ForCompSupportEmpty[A]() extends ForCompSupport[A] {
    override def get: A = throw new Exception("ForCompSupportEmpty !!!")
  }

  // Реализация ForCompSupport с данными
  case class ForCompSupportTest[A](i: A) extends ForCompSupport[A] {
    override def get: A = i
  }

  // Пример использования ForCompSupport с числами и строками
  val fcst: ForCompSupport[Int] = ForCompSupport(2).map(_ * 2)
  val fcsStr: ForCompSupport[String] = ForCompSupport("any")

  val x: ForCompSupport[(Int, String)] = for {
    x <- fcst
    if x > 2
    x1 <- fcsStr
  } yield (x, x1)

  println(s"x: ${x.get}")
}

object Lec6Try extends App {
  // Пример использования Try для обработки исключений
  val maybeThrowException: Try[Int] = Try {
    1 / 0
  }

  val maybeThrowExceptionV2: Try[Int] = Try {
    1 / 1
  }

  // Обработка результата Try с помощью match
  maybeThrowException match {
    case Failure(exception) => println(s"exception: ${exception.getMessage}")
    case Success(value)     => println(s"value: $value")
  }

  // Примеры методов Try
  val isSuccess: Boolean = maybeThrowException.isSuccess
  val isFailure: Boolean = maybeThrowException.isFailure
 // val getOrElse: Double = maybeThrowException.getOrElse(3d).toDouble
  val mapTry: Try[Int] = maybeThrowException.map(_ * 2)
  val filterTry: Try[Int] = mapTry.filter(_ < 2)
  maybeThrowException.foreach(println)

  // Восстановление после исключения с помощью recover
  val recoverFail: Try[Double] = maybeThrowException.recover {
    case _: ArithmeticException =>
      println("Arithmetic exception recovered")
      4d

    case x: RuntimeException =>
      println(s"Runtime exception recovered: ${x.getMessage}")
      3d
  }.map(_.asInstanceOf[Double])
  println(s"recoverFail: ${recoverFail}")

  // Комбинирование Try с flatMap
  val newX: Try[Int] = maybeThrowException.flatMap(_ => maybeThrowExceptionV2)

  // Использование for-comprehension с Try
  val newX1: Try[Int] = for {
    n  <- maybeThrowException
    n1 <- maybeThrowExceptionV2
  } yield n1

  println(s"newX1: $newX1")
}

object Lec6Either extends App {
  // Пример использования Either для обработки ошибок
  def getInfoFromUrl(url: String): Either[String, Int] = {
    if (url.isEmpty) Left("Empty url!")
    else Right(url.length)
  }

  val res1: Either[String, Int] = getInfoFromUrl("AAA")
  println(res1)
  val res2: Either[String, Int] = getInfoFromUrl("")
  println(res2)

  // Обработка Either с помощью match
  getInfoFromUrl("BBB") match {
    case Left(valueLeft)   => println(s"Left: $valueLeft")
    case Right(valueRight) => println(s"Right: $valueRight")
  }

  // Примеры методов Either
  getInfoFromUrl("CCC").map(_.toLong)

  getInfoFromUrl("DDD").left
  getInfoFromUrl("DDD").right

  getInfoFromUrl("EEE").flatMap { x =>
    getInfoFromUrl("ZZZ").map { y => x + y }
  }

  // Использование for-comprehension с Either
  val result: Either[String, Int] = for {
    eee <- getInfoFromUrl("EEE")
    zzz <- getInfoFromUrl("ZZZ")
  } yield eee + zzz

  println(s"result: $result")

  // Пример работы с коллекцией и Either
  case class PersonalInfo(id: Long, contractNumber: String, phone: String)
  val infos: List[PersonalInfo] = List(
    PersonalInfo(1, "43", "99"),
    PersonalInfo(2, "", "1"),
    PersonalInfo(3, "99", ""),
    PersonalInfo(4, "11", "43"),
    PersonalInfo(5, "24", "22"),
  )

  def insertToDb(pi: PersonalInfo): Unit = println(s"Insert to DB: ${pi}")
  val resultInfos: List[Either[String, Long]] = infos.map {
    case pi if pi.contractNumber.isEmpty => Left(s"${pi} => contractNumber is empty")
    case pi if pi.phone.isEmpty         => Left(s"${pi} => phone is empty")
    case pi                            =>
      insertToDb(pi)
      Right(pi.id)
  }

  println("----------------------------")
  val resultStr: String = resultInfos.mkString(",\n")
  println(resultStr)
}