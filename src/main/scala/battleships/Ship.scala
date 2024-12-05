package battleships

import scala.language.postfixOps

abstract class Ship(length: Int) { // do error checking outside before running constructor

  // i have to fill in something here or scala points a gun to my fucking head and say abstract all your children
  protected var placement: Array[Array[Int]] = Array.fill(2, 2)(-1)

  def this(origin: Array[Int], length: Int) = { // length should be a class member where origin is only a temporary var to iterate with
    //  Ship(origin: (Int, Int), length: Int) {
    this(length) // here its running the primary constructor which does nothing
    placement = Array.fill(length, 2)(-1) // redo the array to extend it
    for (i <- 0 until length) {
      origin.copyToArray(placement(i)) // note array = is by reference
      //    println(origin(i)(0).tostring)
      origin(0) = origin(0) + 1 // default ship direction is right
    }
    for (i <- placement.indices) {
      println(placement(i).mkString("Array(", ", ", ")"))
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

}
