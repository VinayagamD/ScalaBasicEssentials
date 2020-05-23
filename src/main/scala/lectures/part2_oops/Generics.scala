package lectures.part2_oops

import lectures.part2_oops.Generics.MyList

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B) : MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }

  class  MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A] : MyList[A] = ???

  }

  val emptyListOfIntegers = MyList.empty[Int]


  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // yes List[Cat] extends List[Animal] = covariance
  class  CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return list of Animals
  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell No! Contravariant
  class ContravariantList[-A]
  val contravariantList : ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  val trainer : Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

//  class Car
//  val newCage = new Cage(new Car)
  // UpperBounded types
  class NewCage[A >: Animal]
 }

// Expand My List to be Generic

abstract class MyList[+A] {
  /*
   * head = first element of the list
   * tail = remained of the list
   * isEmpty = is this list empty
   * add(int) => new list with this element added
   * toString => a string representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def  add[B >: A](element: B) : MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String =f"[ $printElements ]"

  def map[B] (transformer: A => B): MyList[B]
  def flatMap[B] (transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A]( list : MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw  new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add [B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override def printElements: String = ""

  override def map[B] (transformer: Nothing => B): MyList[B] = Empty
  override def flatMap[B] (transformer: Nothing => MyList[B]): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def ++[B >: Nothing]( list : MyList[B]): MyList[B] = list

}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait  MyTransformer[-A, B] {
  def  transform(elem:A): B
}

case class Cons[+A](h:A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override  def printElements: String =
    if(t.isEmpty) "" + h
    else h + ", " + t.printElements


  def filter(predicate: A => Boolean): MyList[A] =
    if(predicate.apply(head)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B] (transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))


  override def ++[B >: A]( list : MyList[B]): MyList[B] = new Cons(h, t ++ list)
  def flatMap[B] (transformer: A => MyList[B]): MyList[B] = transformer(h) ++ t.flatMap(transformer)
}

object ListTest extends App {
  val listOfIntegers : MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers : MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers : MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(_ * 2).toString)
  println(listOfIntegers.filter(_%2==0).toString)
  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap( elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println(cloneListOfIntegers == listOfIntegers)
}
