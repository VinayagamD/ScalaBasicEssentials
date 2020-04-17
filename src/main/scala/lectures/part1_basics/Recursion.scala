package lectures.part1_basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n : Int) : Int = {
    if(n <= 1) 1
    else {
      println(f"Computation factorial of $n -I first need factorial")
      val result =  n * factorial(n-1)
      println(f"Computed factorial of $n is $result")
      result
    }
  }

  println(factorial(10))
  // Recursive depth is too big stackoverflow will throw
//  println(factorial(5000))

  def anotherFactorial(n: Int) : BigInt = {
    // Tailrec helps in building dept of the recursion that's have we can fix the iteration vs recursion
    @tailrec
    def factHelper(x: Int, accumulator: BigInt) : BigInt =
      if(x <= 1) accumulator
      else factHelper(x-1, x*accumulator)
    factHelper(n,1)
  }
  println(anotherFactorial(10))
  println(anotherFactorial(20000))

  // WHEN YOU NEED LOOPS, USE _TAIL_RECURSION.

  /************
   *
   * 1. Concatenate string n times
   * 2. IsPrime function tail recursive
   * 3. Fibonacci function, tail recursive
   *****************/

    @tailrec
  def concatenateTailRec(aString: String, n: Int, accumulator: String): String = {
    if(n <= 0) accumulator
    else concatenateTailRec(aString, n-1, aString+accumulator)
  }

  println(concatenateTailRec("vinay", 1000, ""))

  def isPrime(n: Int) : Boolean = {
    @tailrec
    def isPrimeTailRec(t:Int, isStillPrime: Boolean) : Boolean = {
      if(!isStillPrime) false
      else if( t <= 1) true
      else isPrimeTailRec(t-1, n%t != 0 && isStillPrime)
    }
    isPrimeTailRec(n/2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))


  def fibonacci(n: Int) : Int = {
    @tailrec
    def fiboTailRec(i: Int, last: Int, nextToLast: Int) : Int= {
      if(i >= n) last
      else fiboTailRec(i+1, last+nextToLast, last)
    }

    if(n<2) 1
    else fiboTailRec(2,1,1 )
  }

  println(fibonacci(10))

}
