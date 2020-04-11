package lectures.part1

object ValuesVariableTypes  extends App {

  val x: Int = 42
  println(x)

  //  x = 2
  // val is immutable

  // Compiler can Infer types
  val y = 42
  println(y)

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 5273985273895273L
  val aFloat: Float = 2.0F
  val aDouble: Double = 3.14

  // Variables
  var aVariable: Int = 4

  // var are mutable
  aVariable = 5 // side effects


}
