package lectures.part2_oops

import scala.language.postfixOps

object Objects  extends App {

    // SCALA DOES NOT HAVE CLASS_LEVEL FUNCTIONALITY ("static")
  object Person { // type + its only instance
      // "static" / "class" - level functionality
    val N_EYES = 2
      def canFly : Boolean = false
      def from(mother: Person, father: Person) : Person = new Person("Bobbie")

      // factory method
      def apply(mother: Person, father: Person): Person = new Person("Bobbie")
   }

  class Person(val name: String) {
    // instance-level functionality
  }

  // COMPANIONS

  println(Person.N_EYES)
  println(Person canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("John")
  println(john == mary)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)
//  val bobbie = Person.from(mary, john)
  val bobbie = Person(mary, john)

  // Scala Applications = Scala object with
  // def main(args: Array[String]) : Unit
}
