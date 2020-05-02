package lectures.part2_oops

object AbstractDataTypes extends App {

    // abstract
  abstract class Animal {
      val creatureType: String
      def eat: Unit
    }

  class Dog extends Animal{
    override val creatureType: String = "Canine"

    override def eat: Unit = println("Crunch Crunch")
  }

  // traits
  trait  Carnivore {
    def eat(animal: Animal): Unit
    val  preferredMeal: String = "fresh meat"
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"

    override def eat: Unit = "nomnomnom"

    override def eat(animal: Animal): Unit = println(s"I'm a $creatureType I'am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1- traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits = behavior, abstract class = "thing"


}
