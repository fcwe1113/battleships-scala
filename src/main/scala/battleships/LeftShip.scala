package battleships

class LeftShip(length: Int) extends Ship(length){
  def this(origin: Array[Int], length: Int) = {
    this(length)
    placement = Array.fill(length, 2)(-1)
    for (i <- 0 until length) {
      origin.copyToArray(placement(i)) // note array = is by referenc
      origin(0) = origin(0) - 1 // default ship direction is right
    }
    
  }

  override def toString: String = {
    s"origin: ${placement(0).mkString("Array(", ", ", ")")}, dir: Left, length: $length"
  }
}

