package lectures.part3FP

object AnonymousFunctions extends App {


  // anonymous function (LAMBDA)
  val doubler: Int => Int  = (x: Int) =>  x *2


  // multiply params in a lambda
  val adder: (Int, Int) => Int = (a:Int, b: Int) => a+b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambda
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More Syntactic Sugar
  val niceImplementer: Int => Int = _ + 1 // equivalent to x => x+1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent (a,b) => a+b


  /*
    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as an anonymous function
   */

  val superAdder = (x: Int) => (y: Int) => x+y
  println(superAdder(3)(4))
}
