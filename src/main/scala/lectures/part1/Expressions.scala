package lectures.part1

object Expressions extends App {

  val x = 1+2 // EXPRESSIONS
  println(x)

  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (right shift with zero extensions)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ... side effects
  println(aVariable)

  // Instruction (DO) vs Expression (VALUE)

  // IF Expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if(aCondition) 5 else  3)
  println(1 + 3)

  var i = 0
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }
  println(aWhile)

  // NEVER WRITE THIS AGAIN.

  // EVERYTHING in Scala is an Expression!

  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue)

  // side effects : println(), while, reassigning

  // Code blocks

  val  aCodeBlock = {
    val y = 2
    val z = y + 1

    if(z > 2) "hello" else "goodbye"
  }

//  val anotherValue = z + 1 // This value inside code block cannot be accessed by outer block code

  /****************************
   *
   * Notes:
   *
   *  Expression vs Instruction
   *
   *   * Instruction are executed (think Java), expressions are evaluated( Scala)
   *   * In Scala we'll think in terms of the expressions
   *
   *
   *
   ***********************/

  /****************************
   *
   *  1) Difference between "hello world" vs println("hello world")?
   *
   *************************/

  val someValue = {
    2 < 3
  }
  println(someValue)
  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
  println(someOtherValue)
}
