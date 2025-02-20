package Scala2

object HelloWorld extends App{
  println("Hello, world!1")
}
object HelloWorld2 extends App{
  println({
    var str = "Hello, " // переменная
    val add = "world!" // константа
      // val короче чем Final int или const int
    str +=add
    str// возвращется послденне значение в блоке
  })
}
object HelloWorld3 extends App{
  //val pair:(Int,Int) = (1,2)
  //val pair2 = "key" -> "value"
  // Tuple2(Int,Int)
  println({
    def square(x: Int)= x * x
    square(10)
  })
}
