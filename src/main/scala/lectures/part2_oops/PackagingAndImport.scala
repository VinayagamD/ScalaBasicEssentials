package lectures.part2_oops

import java.sql

import playground.{PrinceCharming, Cinderella => Princess}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImport extends App{

  // package members are accessible by their simple names
  val writer = new Writer("Daniel", "RockTheJvm", 2018)

  // import the package
  val princess = new Princess // playground.Cinderella = fully qualified name

  // packages are in hierarchy
  // matching foldr structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val price = new PrinceCharming

  // 1. use FQ names
  val date = new Date
  val sqlDate = new SqlDate(2018,5,4)
  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
