package Scala2.lectures.week2oop

object ClassesOop extends App {
  class Student(id: Int = 0, name: String)
  val student = new Student(1, "Bob") // Student(1, Bob)
  val zeroIdStudent = new Student(name = "Alice") // Student(0, Alice)
}

object ClassesOop1 extends App {
  class Student(id: Int, name: String) {
    def showId() = println(s"Student has id = $id")
    def showId(n: Int): Unit = {
      for (i <- 0 to n) println(s"Student has id = $id")
    }
  }
  val student = new Student(1, "Bob")
  student.showId()
  println(s"showId(5)")
  student.showId(5)

}



class Instructor(instructorId: Int, name: String, surname: String) {
  def fullName(): String = name.head.toUpper+ name.tail+" "+surname.head.toUpper+surname.tail
  def getId(): String = this.instructorId.toString
}

class Course(courseId: Int, title: String, releaseYear: String, instructor: Instructor) {
  def getId(): String = this.courseId.toString+instructor.getId()

  def isTaughtBy(instructor: Instructor): Boolean = this.instructor == instructor

  def copyCourse(newReleaseYear: String): Course = {
    new Course(this.courseId, this.title, newReleaseYear, this.instructor)
  }
}

object Task1 extends App{
  val instructor1 = new Instructor(1,"hulk","boris")
  println(instructor1.fullName())
  val course = new Course(1,"Sambo","2025",instructor1)
  println(course.getId())
  println(course.isTaughtBy(instructor1))
  course.copyCourse("2026")
  println(course)
}
//Наследование
class Employee {
  def talk(msg: String): Unit = println(s"Employee says: $msg")
}

object Extend extends App{
  class Manager extends Employee

  val manager = new Manager

  manager.talk("hello")

  class Employee1(name: String) {
    def talk(msg: String): Unit = println(s"Employee says: $msg")
  }

  class Manager1(name: String) extends Employee1(name) {
    override def talk(msg: String): Unit = println(s"Manager [$name] says: $msg")
  }


  val employee = new Employee1("Sam")
  val manager1 = new Manager1("Dan")

  employee.talk("hello") // Employee says: hello
  manager1.talk("congrats") // Manager [Dan] says: congrats
}