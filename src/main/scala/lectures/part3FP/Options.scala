package lectures.part3FP

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOptions: Option[Int] = None

  println(myFirstOption)

  // unsafe APIs
  def unsafeMethod(): String = null
//  val  result = Some(null) // WRONG
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  //DESIGNED UNSAFE APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackUpMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackUpMethod()


  // functions in Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS


  // map, flatmap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehension

  /*
    Exercise.
   */
  val config : Map[String, String] = Map(
    // Fetched from elsewhere
    "host" -> "27.7.86.72",
    "port" -> "80"

  )

  class  Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String) : Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connectionStatus = connection.map(c => c.connect)

  // if(connectionStatus == null) println(None) else print (Some(connectionStatus.get))
  println(connectionStatus)
  /*
      if(status != null)
      println(status)
   */
  connectionStatus.foreach(println)


  // Chained Call
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield  connection.connect
  forConnectionStatus.foreach(println)
}
