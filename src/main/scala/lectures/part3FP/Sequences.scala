package lectures.part3FP

import scala.util.Random

object Sequences extends  App {
   // Sequences
  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence ++ Seq(7,6,5).sorted)

  // Ranges
  val aRange : Seq[Int] = 1 to 10
  println(aRange)
  aRange.foreach(println)
  val aUntilRange : Seq[Int] = 1 until 10
  println(aUntilRange)
  aUntilRange.foreach(println)
  (1 to 10).foreach(x => println("Hello"))


  val aList = List(1,2,3)
  val prepended  = 42 :: aList
  println(prepended)

  val prependappend = 42 +: aList :+ 89
  println(prependappend)

  val apples5 =  List.fill(5)("apples")
  println(apples5)
  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  val threeElementsString = Array.ofDim[String](3)
  println(threeElements)
  threeElements.foreach(println)
  threeElementsString.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and sequences
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs list
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]) : Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0/maxRuns
  }


  // keep reference to tail
  // updating an element in the middle take long
  val numbersList = (1 to maxCapacity).toList

  // depth of the tree is small
  // needs to replace an entire 32 element chunk
  val numbersVector = (1 to maxCapacity).toVector
  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

}
