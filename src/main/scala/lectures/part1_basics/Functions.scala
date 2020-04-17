package lectures.part1_basics

object Functions extends App {

  def aFunction(a: String, b : Int): String =
    a + " " + b

  println(aFunction("hello" , 3))

  def aParameterLessFunction() : Int = 42

  println(aParameterLessFunction())
  println(aParameterLessFunction)


  def aReapeatedFunction(aString: String, n : Int) : String = {
    if( n == 1) aString
    else aString + aReapeatedFunction(aString, n-1)
  }

  println(aReapeatedFunction("Vinay", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION

  def aFunctionWithSideEffects(aString: String) : Unit = println(aString)

  def aBigFunction(n: Int) : Int  = {
    def aSmallFunction(a: Int, b: Int) = a + b
    aSmallFunction(n, n-1)
  }

  /**
   * 1. A greeting function(name , age ) => "Hi, my name is $name and I am $age years old"
   * 2. Factorial Function 1 * 2 * 3 * ... * n
   * 3. A fibonacci functions
   *    f(1) = 1
   *    f(2) = 1
   *    f(n) = f(n-1) + f(n-2)
   *
   * 4. Tests if a number is prime.
   */

  /**
   * Greeting functions for the user
    * @param name : who you want to greet
   * @param age : represents the greeter age
   */
  def greeting(name: String , age: Int) : String = f"Hi, my name is $name and I am $age years old"
  println (greeting("vinay", 28))

  def factorial(n: Int) : Int =
    if ( n <= 0) 1
    else n * factorial(n-1)

  println(factorial(5))

  def fibonacci(n: Int) : Int = {
    if(n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(8)) // 1 , 1, 2, 3, 5, 8 13 21

  def isPrime(n: Int) : Boolean = {
    def isPrimeUntil(t: Int) : Boolean =
      if(t<= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    isPrimeUntil(n/2)
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(13* 17))
}
