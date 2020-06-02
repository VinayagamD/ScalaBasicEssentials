package lectures.part3FP

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success or failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod() : String = throw new RuntimeException("NO STRING FOR YOU BUSTER ")


  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backUpMethod: String = " A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backUpMethod))
  println(fallbackTry)

  // IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A Valid result")
  def betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatmap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // => for comprehensions

  /*
    Exercise
   */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted")
    }
    def getSafe(url: String) : Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else  throw  new RuntimeException("Someone else took the port")
    }
    def getSafeConnection(host: String, port: String) : Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e., call renderHTML
  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // shorthand version
  HttpService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for-comprehension version
  for{
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  }renderHTML(html)
  /*
    try{
      connection = HttpService.getConnection(host, port)
      try{
        page = connection.get("/home")
        renderHTML(page)
      }catch(some other exception){
      }
    }catch(exception){
    }
   */

}
