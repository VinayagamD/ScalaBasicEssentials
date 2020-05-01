package lectures.part2_oops

object Inheritance extends App {


  /*final*/sealed class Animal {
    val creatureType = "wild"
     /*final*/ def eat = println("nomnom")
  }

  class  Cat extends Animal {

    def crunch = {
      eat
      println("Crunch Crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // Constructors
  class Person(name: String, age: Int){
    def this(name: String) = this(name, 0)

  }
  class Adult(name: String, age: Int, idCard: String ) extends Person(name)

  class Dog(dogType: String) extends Animal {

    override val creatureType: String = dogType

    override  def eat = println("Crunch, Crunch")
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: Polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overriding vs overloading
  // super

  // preventing overrides

  // 1 - keyword final on member
  // 2 - keyword final on class
  // 3 - seal the class = extend in THIS file, prevent extension in other files
}
