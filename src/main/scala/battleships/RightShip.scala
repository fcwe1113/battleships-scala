package battleships

class RightShip(length: Int) extends Ship(length){
//  placement = Array.fill[Int](length, 2)(-1)
  //  Ship(origin: (Int, Int), length: Int) {
  def this(origin: Array[Int], length: Int) = {
    this(length)
    placement = Array.fill(length, 2)(-1)
    for (i <- 0 until length) {
      origin.copyToArray(placement(i)) // note array = is by reference
      //    println(origin(i)(0).tostring)
      origin(0) = origin(0) + 1 // default ship direction is right
    }
    for (i <- placement.indices) {
      println(placement(i).mkString("Array(", ", ", ")"))
    }
  }
}
