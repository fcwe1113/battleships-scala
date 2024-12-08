package battleships

import scala.language.postfixOps

// here i dont know if scala is doing the right thing or not

// on one hand it just gives you a primary constructor that does the most common thing constructors do on paper which is passing values
// but the second you want to self define your primary constructor to do anything beyond that then you have to jump thru like 10 burning hoops to even do that
// and fucks up the rest of your class declaration because you define you put your self define code on the scope where class members and methods live
// so newcomers to the language have to break the cardinal rule for class declaration (putting code outside of methods) especially when they come from java 
// because scala is a java derivative
// what i did here is essentially cheat by leaving the initial class member declaration incomplete by leaving out member i would have added on day one
// and adding them in inside the class with a secondary constructor where you have full freedom to self define to constructor to your hearts content
// all while thinking there has to be a better way of doing this

// anyways rant over

abstract class Ship(length: Int) { // do error checking outside before running constructor

  // i have to fill in something here or scala points a gun to my fucking head and say abstract all your children
  protected var placement: Array[Array[Int]] = Array.fill(2, 2)(-1)

  def this(origin: Array[Int], length: Int) = { // length should be a class member where origin is only a temporary var to iterate with
    this(length) // here its running the primary constructor which does nothing
    placement = Array.fill(length, 2)(-1) // redo the array to extend it
    for (i <- 0 until length) {
      origin.copyToArray(placement(i)) // note array = is by reference
      origin(0) = origin(0) + 1 // default ship direction is right
    }
  }

  def isCollide(coord: Array[Int]): Boolean = {
    var output = false
    for (i <- placement.indices){
      if (coord(0) == placement(i)(0) && coord(1) == placement(i)(1)){
        output = true
      }
    }
    output
  }

  def isCollide(ship: Ship): Boolean = {
    var output = false
    var coord: Array[Int] = Array(-1, -1)
    for (coord <- ship.getPlacement){
      if isCollide(coord) then output = true
    }
    output
  }
  
  def isOutOfBounds: Boolean = {
    // used while generating board, if any tile the ship is in is outside of the board(i.e. >9 or <0) it returns true and the ship gen function would gen another one
    var output = false
    for (i <- placement.indices) {
      if (placement(i)(0) < 0 || placement(i)(1) < 0 || placement(i)(0) > 9 || placement(i)(1) > 9) {
        output = true
      }
    }
    output
  }

  def getPlacement: Array[Array[Int]] = {
    placement
  }

}
