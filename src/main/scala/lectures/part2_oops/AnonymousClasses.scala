package lectures.part2_oops

object AnonymousClasses extends App {

  trait Animal {
    def eat: Unit
  }

  val funnyAnimal : Animal = new Animal {
    override def eat: Unit = println("ahahahahahahaha")
  }

  /*
    equivalent with
    AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahahahahahahaha")
    }
     val funnyAnimal : Animal = new AnonymousClasses$$anon$1
   */
  println(funnyAnimal.getClass)

  class Person(name: String ){
    def sayHi: Unit = println(f"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit =  println(f"Hi, my name is jim, how can I be of service?")
  }
}
