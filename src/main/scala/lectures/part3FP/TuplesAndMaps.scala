package lectures.part3FP

import com.sun.prism.impl.Disposer.Target

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val  aTuple =  (2, "Hello, Scala")// Tuple2[Int, String](2,"hello, Scala")
  println(aTuple)
  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye java"))
  println(aTuple.swap)

  // Maps keys -> values
  val aMap: Map[String, Int] = Map()
  val phoneBook  = Map(("Jim", 555), "Daniel" -> 789, "JIM"-> 9000).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
//  println(phoneBook("Mary")) -> throws NoSuchElementException Since Key not found
  println(phoneBook("Mary"))


  // add a pairing
  val newParing = "Mary" -> 678
  val newPhoneBook = phoneBook + newParing
  println(newPhoneBook)

  // functionals on map

  // map, flatmap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterkeys
  println(phoneBook.filter( x => x._1.startsWith("J")))
  // mapvalues
  println(phoneBook.map(number => number._1 -> number._2*10))
  println(phoneBook.map(number => number._1 -> ("0245"+ number._2*10).toInt))

  // conversion to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)
  val names =List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. What would happen if I had two original entries "jim" -> 555 and "JIM" -> 900 ?
      !!! careful with mapping keys
    2. Overly simplified social network based on maps
      Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend

        - number of friends of a person
        - person with most friends
        - how many people have NO friends
   */
  val mapsAssign = Map("Jim" -> 555, "JIM" -> 900)
  println(mapsAssign)

  def add(network: Map[String, Set[String]], person: String): Map[String,Set[String]] =
    network+(person-> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String) : Map[String,Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a) )
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String) : Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a) )
  }

  def remove(network: Map[String, Set[String]], person: String ): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]) : Map[String, Set[String]] =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person),network)
    unfriended - person
  }

  val  empty : Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"),"Mary")
  println(network)
  println(friend(network,"Bob","Mary"))
  println(unfriend(friend(network,"Bob","Mary"),"Bob", "Mary"))
  println(remove(friend(network,"Bob","Mary"), "Bob"))

  // Jim, Bob, "Mary
  val people = add(add(add(empty, "Bob"),"Mary"),"Jim")
  println(people)
  val jimBob = friend(people, "Bob", "Jim")
//  val maryBob = friend(people, "Mary", "Bob")
  val testNet= friend(jimBob, "Bob", "Mary")
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String) : Int = {
    if(!network.contains(person)) 0
    else network(person).size
  }
  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]):String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network : Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)
  println(nPeopleWithNoFriends(network))
  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String) : Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople:Set[String], discoveredPeople: Set[String]) : Boolean ={
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
       if(person == target) true
       else if(consideredPeople.contains(person)) bfs (target, consideredPeople, discoveredPeople.tail)
       else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))

}
