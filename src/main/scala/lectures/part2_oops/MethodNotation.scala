package lectures.part2_oops

import scala.language.postfixOps

object MethodNotation extends App {

    class Person(val name: String, favoriteMovie: String) {
      def likes(movie: String) : Boolean =  movie == favoriteMovie
      def hangOutWith(person: Person) : String = s"${this.name} hanging out with  ${person.name}"
      def +(person: Person) : String = s"${this.name} hanging out with  ${person.name}"
      def unary_! : String = s"$name, what the heck?!"
      def isAlive : Boolean = true
      def apply() : String = s" Hi, my name is $name and I like $favoriteMovie"
    }

    val mary = new Person("Mary", "Inception")
    println(mary.likes("Inception"))
    println(mary likes "Inception") // equivalent
    // infix notation = operator notation (syntactic sugar)

  // operators in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary .+(tom))

  println(1 + 2)
  println(1 .+(2))

  // ALL OPERATORS ARE METHODS.
  // AKKA actors have ! ?


  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val  y = 1.unary_-
  println(x)
  println(y)

  // unary_prefix only works with - + ~ !
  println(mary unary_!)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent apply
//  println(mary)
}
