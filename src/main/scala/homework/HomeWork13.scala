package homework

import java.util.concurrent.Executors
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Random, Success, Try}

object FutureExamples extends App {

  // 1. Future (асинхронные вычисления)
  val pool = Executors.newFixedThreadPool(4)
  implicit val ec = ExecutionContext.fromExecutor(pool)

  val futureExample: Future[Int] = Future {
    println("Start Future computation")
    Thread.sleep(1000) // Имитация долгой операции
    println("Finish Future computation")
    42
  }

  // 2. Await.result (ожидание результата)
  val result: Int = Await.result(futureExample, Duration.Inf)
  println(s"Await.result: $result")

  // 3. onComplete (обработка завершения)
  futureExample.onComplete {
    case Success(value) => println(s"onComplete Success: $value")
    case Failure(exception) => println(s"onComplete Failure: ${exception.getMessage}")
  }

  // 4. Future.sequence (преобразование списка Future)
  val futuresList: List[Future[Int]] = List(Future(1), Future(2), Future(3))
  val sequenceResult: Future[List[Int]] = Future.sequence(futuresList)
  sequenceResult.onComplete {
    case Success(values) => println(s"Future.sequence Success: $values")
    case Failure(exception) => println(s"Future.sequence Failure: ${exception.getMessage}")
  }

  // 5. Future.traverse (параллельное выполнение для списка)
  val ids = List(1, 2, 3)
  val traverseResult: Future[List[Int]] = Future.traverse(ids) { id =>
    Future {
      println(s"Processing id: $id")
      id * 2
    }
  }
  traverseResult.onComplete {
    case Success(values) => println(s"Future.traverse Success: $values")
    case Failure(exception) => println(s"Future.traverse Failure: ${exception.getMessage}")
  }

  // 6. flatMap (цепочка Future)
  val flatMapResult: Future[Int] = Future(1).flatMap { x =>
    Future(x + 1)
  }
  flatMapResult.onComplete {
    case Success(value) => println(s"flatMap Success: $value")
    case Failure(exception) => println(s"flatMap Failure: ${exception.getMessage}")
  }

  // 7. map (преобразование результата Future)
  val mapResult: Future[String] = Future(1).map(x => s"Value: $x")
  mapResult.onComplete {
    case Success(value) => println(s"map Success: $value")
    case Failure(exception) => println(s"map Failure: ${exception.getMessage}")
  }

  // 8. Future.successful и Future.failed (создание завершённых Future)
  val successfulFuture: Future[Int] = Future.successful(42)
  successfulFuture.onComplete {
    case Success(value) => println(s"Future.successful Success: $value")
    case Failure(exception) => println(s"Future.successful Failure: ${exception.getMessage}")
  }

  val failedFuture: Future[Int] = Future.failed(new Exception("Error"))
  failedFuture.onComplete {
    case Success(value) => println(s"Future.failed Success: $value")
    case Failure(exception) => println(s"Future.failed Failure: ${exception.getMessage}")
  }

  // 9. Try (обработка исключений)
  val tryExample: Try[Int] = Try {
    42 / 0
  }
  tryExample match {
    case Success(value) => println(s"Try Success: $value")
    case Failure(exception) => println(s"Try Failure: ${exception.getMessage}")
  }

  // 10. for-comprehension (синтаксический сахар для цепочек Future)
  val forComprehensionResult: Future[Int] = for {
    x <- Future(1)
    y <- Future(2)
  } yield x + y
  forComprehensionResult.onComplete {
    case Success(value) => println(s"for-comprehension Success: $value")
    case Failure(exception) => println(s"for-comprehension Failure: ${exception.getMessage}")
  }

  // 11. Future.traverse с группировкой
  val groupedIds = (1 to 10).toList
  val groupedTraverseResult: Future[Iterator[List[Int]]] = Future.traverse(groupedIds.grouped(2)) { group =>
    Future {
      println(s"Processing group: $group")
      group.map(_ * 2)
    }
  }
  groupedTraverseResult.onComplete {
    case Success(values) => println(s"Grouped Future.traverse Success: ${values.flatten}")
    case Failure(exception) => println(s"Grouped Future.traverse Failure: ${exception.getMessage}")
  }

  // 12. Option и Future (комбинация)
  val optionFutureResult: Future[Option[Int]] = for {
    x <- Future(Option(1))
    y <- Future(x)
  } yield y
  optionFutureResult.onComplete {
    case Success(value) => println(s"Option + Future Success: $value")
    case Failure(exception) => println(s"Option + Future Failure: ${exception.getMessage}")
  }

  // Ожидание завершения всех Future
  Thread.sleep(3000) // Даём время для завершения всех асинхронных операций
  pool.shutdown()
}

import scala.concurrent.ExecutionContext.Implicits.global

object FutureOperations {
  // Функция, которая принимает список строк и возвращает список Future[Int] с их длинами
  def stringLengths(strings: List[String]): List[Future[Int]] = {
    strings.map(s => Future(s.length))
  }

  // Функция, которая принимает список Future[Int] и возвращает Future[Int] с их суммой
  def sumFutures(futures: List[Future[Int]]): Future[Int] = {
    Future.sequence(futures).map(_.sum)
  }

  // Функция, которая принимает список Future[String] и возвращает Future[List[String]], содержащий строки в верхнем регистре
  def uppercaseFutures(futures: List[Future[String]]): Future[List[String]] = {
    Future.sequence(futures).map(_.map(_.toUpperCase))
  }

  // Функция, которая асинхронно вызывает две Future[Int] функции и выводит их сумму
  def sumAndPrint(f1: Future[Int], f2: Future[Int]): Unit = {
    val result = for {
      v1 <- f1
      v2 <- f2
    } yield v1 + v2

    result.foreach(sum => println(s"Сумма: $sum"))
  }

  // Пример использования
  def main(args: Array[String]): Unit = {
    val strings = List("hello", "world", "scala")
    val lengthsFutures = stringLengths(strings)
    val sumFuture = sumFutures(lengthsFutures)
    sumFuture.foreach(sum => println(s"Сумма длин строк: $sum"))

    val stringFutures = strings.map(s => Future(s))
    val uppercasedFuture = uppercaseFutures(stringFutures)
    uppercasedFuture.foreach(uppercased => println(s"Верхний регистр: $uppercased"))

    val f1 = Future(10)
    val f2 = Future(20)
    sumAndPrint(f1, f2)

    Thread.sleep(1000) //время асинхронным операциям выполниться
  }
}
