package battleships

import battleships.Space.*

class Board { // note with no parameter the inferred constructor will hv no parameters
  private val grid: Array[Array[Space]] = Array.fill[Space](10, 10)(UNKNOWN)

  def printBoard(): Unit = {
//    println(s"${grid.getClass}\n")
    print("       |")


    for (i <- 1 to 10) {
      if i != 10 then {
        print(" ")
      }

      print(s"${i} |") // may not do what i think it does
    }

    println()

    for (i <- 1 to 10) {
      println("    --------------------------------------------")
      if i != 10 then {
        print(" ")
      }

      print(s"    ${i} ")
      for (j <- 1 to 10) {
        print(s"| ${grid(i - 1)(j - 1).toString} ")
      }
      println("|")
    }
    println("    --------------------------------------------")
  }
  
  def setBoard(x: Int, y: Int, value: Space): Unit = {
    grid(x)(y) = value
  }
}
