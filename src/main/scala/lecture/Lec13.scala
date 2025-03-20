package lecture

import java.util.concurrent.Executors
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Random, Success, Try}

object JustFuture extends App {
  val pool = Executors.newFixedThreadPool(4)
  implicit val ec = ExecutionContext.fromExecutor(pool)

  val f3: Future[Int] = Future {
    println("start")
    Thread.sleep(5000)
    val result = 1 + 1
    println(s"computed result: $result")
    result
  }

  f3.onComplete {
    case Success(value) => println(s"Success: $value")
    case Failure(exception) => println(s"Failure: ${exception.getMessage}")
  }

  val res = Await.result(f3, Duration.Inf)
  pool.shutdown()
  println(s"Final result: $res")
}

object CreateFuture extends App {
  val pool = Executors.newFixedThreadPool(4)
  implicit val ec = ExecutionContext.fromExecutor(pool)

  val f = Future {
    val result = 42 / 0
    println(s"computed result: $result")
    result
  }

  f.onComplete {
    case Success(value) => println(s"Success: $value")
    case Failure(exception) => println(s"Failure: ${exception.getMessage}")
  }

  Try {
    Await.result(f, Duration.Inf)
  }.recover {
    case e: Exception => println(s"Caught exception: ${e.getMessage}")
  }

  pool.shutdown()
}

object FutureSequence extends App {
  val pool = Executors.newFixedThreadPool(4)
  implicit val ec = ExecutionContext.fromExecutor(pool)

  val ids: List[Future[Int]] = (1 to 100).toList.map { i =>
    Future {
      val sleep = Random.nextInt(1000) // Исправлено: убрана лишняя закрывающая скобка
      println(s"Processing $i, sleeping for $sleep ms")
      Thread.sleep(sleep)
      i
    }
  }

  val x: Future[List[Int]] = Future.sequence(ids)

  x.onComplete {
    case Success(values) => println(s"Successfully processed: ${values.mkString(", ")}")
    case Failure(exception) => println(s"Failed to process: ${exception.getMessage}")
  }

  val res = Await.result(x, Duration.Inf)
  res.foreach(println)

  pool.shutdown()
}

object FutureTraverseV1 extends App {
  val pool = Executors.newFixedThreadPool(20)
  implicit val ec = ExecutionContext.fromExecutor(pool)

  val ids = (1 to 100).toList

  val x: Future[List[Int]] = Future.traverse(ids) { i =>
    Future {
      val sleep = Random.nextInt(2000) // Исправлено: убрана лишняя закрывающая скобка
      println(s"Processing $i, sleeping for $sleep ms")
      Thread.sleep(sleep)
      i
    }
  }

  x.onComplete {
    case Success(values) => println(s"Successfully processed: ${values.mkString(", ")}")
    case Failure(exception) => println(s"Failed to process: ${exception.getMessage}")
  }

  val res = Await.result(x, Duration.Inf)
  res.foreach(println)

  pool.shutdown()
}

object FutureTraverseV2 extends App {
  val pool = Executors.newFixedThreadPool(20)
  implicit val ec = ExecutionContext.fromExecutor(pool)

  val ids = (1 to 2000).toList

  val x: Future[List[List[Int]]] = Future.traverse(ids.grouped(200).toList) { li =>
    Future {
      li.map { i =>
        val sleep = Random.nextInt(2000) // Исправлено: убрана лишняя закрывающая скобка
        println(s"Processing $i, sleeping for $sleep ms")
        Thread.sleep(sleep)
        i
      }
    }
  }

  x.onComplete {
    case Success(values) => println(s"Successfully processed: ${values.flatten.mkString(", ")}")
    case Failure(exception) => println(s"Failed to process: ${exception.getMessage}")
  }

  val res = Await.result(x, Duration.Inf)
  println(res.flatten)

  pool.shutdown()
}

// Запуск всех объектов
object Main extends App {
  JustFuture.main(Array.empty)
  CreateFuture.main(Array.empty)
  FutureSequence.main(Array.empty)
  FutureTraverseV1.main(Array.empty)
  FutureTraverseV2.main(Array.empty)
}
