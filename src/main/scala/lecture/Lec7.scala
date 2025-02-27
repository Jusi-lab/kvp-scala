package lecture

// 1. Использование Enumeration для создания перечислений
// 2. Использование sealed trait для создания типов с ограниченным набором подтипов

object ScalaEnumByEnum extends App {
  // Создаем перечисление Colors с помощью Enumeration
  object Colors extends Enumeration {
    type Color = Value
    val Red, Green, Blue, Orange = Value // Добавляем цвета

    // Метод для проверки, является ли цвет "теплым" (красный или оранжевый)
    def isWarm(c: Colors.Color): Boolean = c == Colors.Red || c == Colors.Orange
  }
  println(Colors.Orange)
  println(Colors.Orange.id)
  println(Colors.isWarm(Colors.Red))    // true
  println(Colors.isWarm(Colors.Green))  // false
  println(Colors.isWarm(Colors.Orange)) // true
  Colors.values.foreach(println)
  val c = Colors.withName("Orange")
  println(c)
}

object ScalaEnumByEnumWithOverride extends App {
  // Создаем перечисление Colors с кастомными значениями и именами
  object Colors extends Enumeration {
    type Color = Value
    val Red                             = Value(10, "Red color") // Красный с кастомным ID и именем
    val Green                           = Value(1)              // Зеленый с кастомным ID
    val Blue                            = Value("Blue color")   // Синий с кастомным именем
    val Yellow                          = Value("Yellow color") // Желтый с кастомным именем

    // Метод для проверки, является ли цвет "холодным" (синий или зеленый)
    def isCold(c: Colors.Color): Boolean = c == Colors.Blue || c == Colors.Green
  }
  val c = Colors.withName("Blue color")
  println(c)

  // Используем match для проверки, является ли цвет "холодным"
  c match {
    case x if Colors.isCold(x) =>
      println(s"Cold color: $x")
    case _ =>
      println("Not a cold color")
  }
}

object ScalaEnumByEnumWithParams extends App {
  object ColorWP extends Enumeration {
    protected case class RGBColor(i: Int, r: Int, g: Int, b: Int) extends super.Val(i) {
      def printRGV(): Unit = println(s"${this.getClass.getSimpleName} rgb is $r $g $b, id: $i")
    }

    type Color = RGBColor
    val Red   = RGBColor(1, 255, 0, 0)
    val Green = RGBColor(2, 0, 255, 0)
    val Blue  = RGBColor(3, 0, 0, 255)
    val Yellow = RGBColor(4, 255, 255, 0)  // Желтый

    // Метод для приведения Value к RGBColor
    def valueToRBG(x: Value): ColorWP.Color = x.asInstanceOf[RGBColor]
  }
  ColorWP.values.toList.map(ColorWP.valueToRBG).foreach { rgb =>
    println(rgb.id)
    rgb.printRGV()
  }
}

object ScalaEnumBySealed extends App {
  // sealed trait для ограниченного набора подтипов
  sealed trait ColorST {
    val r: Int                = 0
    val g: Int                = 0
    val b: Int                = 0
    def printRGBValue(): Unit = println(s"${this.getClass.getSimpleName} rgb is $r $g $b")
  }

  case class Red() extends ColorST {
    override val r: Int = 255
  }

  case class Green() extends ColorST {
    override val g: Int = 255
  }

  case class Blue() extends ColorST {
    override val b: Int = 255
  }

  case class Yellow() extends ColorST {
    override val r: Int = 255 // Желтый
    override val g: Int = 255
  }

  // Метод для сопоставления
  def matchColor(t: ColorST): Unit = t match {
    case r: Red    => r.printRGBValue()
    case g: Green  => g.printRGBValue()
    case b: Blue   => b.printRGBValue()
    case y: Yellow => y.printRGBValue()
  }
  matchColor(Red())
  matchColor(Green())
  matchColor(Blue())
  matchColor(Yellow())
}

object ScalaList extends App {
  val l1: List[Int] = List(1, 2, 3, 4)
  val l2: List[Int] = List.empty[Int]
  val l3: List[Int] = Nil
  val l4: List[Int] = 1 :: 2 :: 3 :: Nil

  // Получаем первый и последний элементы списка с обработкой пустого списка
  println(l1.headOption.getOrElse("List is empty"))
  println(l1.lastOption.getOrElse("List is empty"))

  // Объединяем списки и "выравниваем" их
  val l5: List[Int] = List(l1, l4).flatten
  println(l5)

  // Фильтруем четные числа
  val evenNumbers = l1.filter(_ % 2 == 0)
  println(s"Even numbers: $evenNumbers")

  // Считаем сумму квадратов элементов списка
  val sumOfSquares = l1.map(x => x * x).sum
  println(s"Sum of squares: $sumOfSquares")

  // Группируем элементы по четности
  val groupedByEvenOdd = l1.groupBy(_ % 2 == 0)
  println(s"Grouped by even/odd: $groupedByEvenOdd")

  // Применяем flatMap для создания нового списка
  val l6 = l1.flatMap(x => List(x, x * 2))
  println(s"FlatMapped list: $l6")
}

object ScalaListSpec extends App {
  case class Colored1(id: Long, color: String)
  val l1: List[Int]      = List(1, 2, 3, 4)
  val l2: List[Colored1] = List.empty[Colored1]

  // Сопоставление для списка чисел
  l1 match {
    case List(a, b, c, d) =>
      println(s"List with four elements: $a, $b, $c, $d")
    case Nil =>
      println("empty")
    case l if l.isEmpty =>
      println("empty")
    case head :: Nil =>
      println(s"List with one element: $head")
    case head :: tail =>
      println(s"Head: $head, Tail: $tail")
    case full @ head :: scnd :: tail =>
      println(s"Full list: $full")
  }

  // Сопоставление для списка объектов Colored1
  l2 match {
    case full @ Colored1(id, color) :: tail =>
      println(s"First element: ${full.head}, Tail: $tail")
    case Nil =>
      println("empty")
  }

  // Примеры операций со списками
  val l3 = List(1, 2, 3, 4)
  val l4 = List(4, 5, 6)

  val intersect = l3.intersect(l4) // Пересечение списков
  val diff1     = l3.diff(l4)     // Разница между списками
  val diff2     = l4.diff(l3)
  val concat    = l3.concat(l4)
  val concat2   = l3.++(l4)

  println(s"Intersection: $intersect")
  println(s"Difference l3 - l4: $diff1")
  println(s"Difference l4 - l3: $diff2")
  println(s"Concatenation: $concat")
  println(s"Concatenation 2: $concat2")
}