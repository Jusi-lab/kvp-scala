package lecture

object ViewExperiments extends App {
  // Используем Vector вместо List
  val vec = Vector.range(1, 6)
  val processed = vec.view
    .map(_ * 2)
    .filter(_ % 3 != 0)
    .take(3)
    .toList

  println(s"Original: $vec")
  println(s"Processed: $processed")
}

object MutCollections extends App {
  // Сравниваем ArrayBuffer и HashSet
  val arrBuf = scala.collection.mutable.ArrayBuffer(5, 4, 3)
  val hashSet = scala.collection.mutable.HashSet(1, 2, 3)

  arrBuf.insert(1, 10)    // Вставляем 10 на позицию 1
 // hashSet.add(4).add(2)   // Пытаемся добавить существующий элемент

  println(s"ArrayBuffer: $arrBuf")
  println(s"HashSet: $hashSet")
}

object VarCollections extends App {
  var mutableVar = scala.collection.mutable.Queue(1, 2)
  mutableVar.enqueue(3)


}

object MutateInFunction extends App {
  def modify(col: scala.collection.mutable.Buffer[Int]): Unit = {
    col += 10
  }

  val buf = scala.collection.mutable.ArrayBuffer(1, 2)
  modify(buf)
  println(s"Modified buffer: $buf")
}

object ObjectMutation extends App {
  case class Dog(var name: String, age: Int)

  val dogs = List(Dog("Sharik", 3), Dog("Bobik", 5))
  dogs.foreach(_.name = "Mutated") // Изменяем имена всех собак

  println(s"Mutated dogs: $dogs") // Все имена изменены
}

object FunctionCombo extends App {
  // Композиция функций с разными типами
  val toStr: Int => String = _.toString
  val duplicate: String => String = _ * 2
  val toInt: String => Int = _.toInt

  val pipeline = toStr.andThen(duplicate).andThen(toInt)
  println(pipeline(42))
}

object AdvancedPartial extends App {
  val parseNumber: PartialFunction[Any, Int] = {
    case s: String if s.matches("\\d+") => s.toInt
    case d: Double if d >= 0 => d.toInt
  }

  val handleNegative: PartialFunction[Any, Int] = {
    case i: Int if i < 0 => -i
  }

  val processor = parseNumber.orElse(handleNegative)

  println(List("123", -5, 45.6, "abc").collect(processor))
}

object FunctionChaining extends App {
  val isEven: PartialFunction[Int, String] = { case x if x%2 == 0 => s"$x even" }
  val isOdd: PartialFunction[Int, String] = { case x => s"$x odd" }

  val checkParity = isEven.orElse(isOdd)

  println((1 to 5).map(checkParity))
}