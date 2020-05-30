package lectures.part3FP

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))


  // filter
  println(list.filter(_ % 2 == 0))

  // flatmap
  val toPair = (x:Int) => List(x, x+1)
  println(list.flatMap(toPair))
  println(list.map(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // List("a1","a2" ..... "d4")

  // "iterating"
  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map( color => ""+c+n+ "-"+color)))
  println(combinations)
  val combinationsFilter = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map( color => ""+c+n+ "-"+color)))
  println(combinationsFilter)
  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield  ""+c+n+ "-"+color

  println(forCombinations)

  val forCombinationsFilter = for {
    n <- numbers if n%2 == 0
    c <- chars
    color <- colors
  } yield  ""+c+n+ "-"+color

  println(forCombinationsFilter)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  println(list.map { x =>
    x * 2
  })

  /*
    1. My List supports for comprehensions
      map(f: A => B) => MyList[B]
      filter(p: A => Boolean) => MyList[A]
      flatMap(f: A=> MyList[B]) => MyList[B]

    2. A small collections at most ONE element - Maybe[+T]
      - map, flatmap, filter
   */


}
