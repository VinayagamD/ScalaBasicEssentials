package lectures.part2_oops

object OOBasics extends App {

  val person = new Person("Vinayagam", 28)
  println(person.age)
  println(person.x)
  person.greet("vinay")
  person.greet

  val author = new Writer("Charles","Dickens", 1812)
  val imposter = new Writer("Charles","Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)
  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
//  counter.dec(6).print


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

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy(new year of release) = new instance of Novel
 */

  class Writer(val firstName: String, val surname: String,val year: Int) {

    def fullName() = s"$firstName $surname"

  }

  class Novel(val name: String, val year: Int, val author: Writer) {

    def authorAge: Int = year - author.year

    def isWrittenBy (author: Writer): Boolean = author == this.author

    def copy(newYear: Int): Novel = new Novel(name, newYear, author)

  }

/*
  Counter class
  - receives an int value
  - method current count
  - method to increment / decrement => new Counter
  - overload inc/dec to receive an amount

 */

class Counter(val count: Int = 0){

  def inc() = {
    println("incrementing")
    new Counter(count + 1)
  }

  def dec()= {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n : Int): Counter= {
    if(n <= 0) this
    else  inc().inc(n-1)
  }

  def  dec(n: Int): Counter = {
    if(n <= 0) this
    else dec().dec(n-1)
  }


  def print = println(count)
}