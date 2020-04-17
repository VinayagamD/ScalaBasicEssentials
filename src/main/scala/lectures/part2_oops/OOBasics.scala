package lectures.part2_oops

object OOBasics extends App {

  val person = new Person("Vinayagam", 28)
  println(person.age)
  println(person.x)
  person.greet("vinay")
  person.greet

}

// constructor
class Person(name: String, val age: Int = 0) {
  // body
  val x = 2
  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: hi $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)

  def this() = this("Vinay Ganesh")
}

// class parameters are NOT FIELDS