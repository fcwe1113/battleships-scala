package battleships

import scala.collection.mutable
import scala.io.StdIn.readLine
import scala.util.Random



object Game{

  private val board = new Board
  private val shotsTaken = 0
  private var gameEnd = false
  private val shipList: mutable.Buffer[Ship] = mutable.Buffer[Ship]()
  private val lengths = Array(2, 3, 3, 4, 5)

  def main(): Unit = {
    ship_gen(lengths)
    println("Welcome to battleships!\nIn this game you select a square to shoot\nto see if there is a ship hiding there.\nTry and sink all 5 ships with the lowest shots possible!\n")
    while (!gameEnd) {

      board.printBoard()
      println(s"Misses: ${Space.MISS.toString}\tHits: ${Space.HIT.toString}\nShots fired: ${shotsTaken}\nPlease select from the following:\n1. shoot\n2. forfeit")
      readLine() match
        case "1" => shoot()
        case "2" => forfeit()
        case _  => println("Invalid input") // error handled

    }
  }

  private def ship_gen(lengths: Array[Int]): Unit = {
    val rng = new Random()

    for (i <- lengths) {
      var done = false
      var newShip: Ship = null
      while (!done) {
        done = true

        rng.nextInt(4) match { // direction rng
          case 0 => newShip = new RightShip(Array(rng.nextInt(10), rng.nextInt(10)), i) // 0 gives right
          case 1 => newShip = new DownShip(Array(rng.nextInt(10), rng.nextInt(10)), i) // 1 gives down
          case 2 => newShip = new LeftShip(Array(rng.nextInt(10), rng.nextInt(10)), i) // 2 gives left
          case 3 => newShip = new UpShip(Array(rng.nextInt(10), rng.nextInt(10)), i) // 3 gives up
          case 4 => newShip = new RightShip(Array(rng.nextInt(10), rng.nextInt(10)), i) // if they randomized the upper limit then right lol
        }
        if newShip.isOutOfBounds then done = false
        for (ship <- shipList){
          if ship.isCollide(newShip) then done = false
        }
      }
      shipList += newShip
    }
  }

  private def shoot(): Unit = {
    println("shoot")
  }

  private def forfeit(): Unit = {
    for (ship <- shipList){
      for (coord <- ship.getPlacement){
        //        println(s"${coord.mkString("Array(", ", ", ")")}")
        board.setBoard(coord(0), coord(1), Space.FORFEIT)
      }
    }
    board.printBoard()
    println("You lose!")
    gameEnd = true
  }
}
