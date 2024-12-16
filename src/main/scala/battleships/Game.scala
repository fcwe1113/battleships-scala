package battleships

import battleships.Space.HIT

import scala.collection.mutable
import scala.io.StdIn.readLine
import scala.language.postfixOps
import scala.util.Random

object Game{ // this class only exist once and is essentially treated as a static class from java

  private val board = new Board
  private var shotsTaken = 0
  private var gameEnd = false
  private val shipList: mutable.Buffer[Ship] = mutable.Buffer[Ship]()
  private val lengths = Array(2, 3, 3, 4, 5)

  def main(): Unit = {
    ship_gen(lengths)
    println("Welcome to battleships!\nIn this game you select a square to shoot\nto see if there is a ship hiding there.\nTry and sink all 5 ships with the lowest shots possible!\n")
    while (!gameEnd) {

      board.printBoard()
      println(s"Misses: ${Space.MISS.toString}\tHits: ${Space.HIT.toString}\nShots fired: $shotsTaken\nPlease select from the following:\n1. shoot\n2. forfeit")
      readLine() match
        case "1" => shoot()
        case "2" => forfeit()
        case _  => println("Invalid input") // error handled

    }
  }

  // function declaration contains the return type right before the {
  // if it says Unit that means it returns nothing
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
        for (ship <- shipList){ // equivalent to foreach ship in shipList
          if ship.isCollide(newShip) then done = false
        }
      }
      shipList += newShip
    }
  }

  private def shoot(): Unit = {
    var validInput = false
    var inputs = Array("")
    while (!validInput) {
      println("please enter the row and column in the format of : ROW,COLUMN")
      inputs = readLine().split(',').map(_.trim)
      if (inputs.length != 2){
        println("incorrect format, please enter in the format of : ROW,COLUMN")
      } else if (inputs(0).toInt < 1 || inputs(0).toInt > 10 || inputs(1).toInt < 1 || inputs(1).toInt > 10) {
        println("please enter numbers within 1 to 10")
      } else {
        validInput = true
      }
    }
    
    validInput = false
    val inputss = inputs.map(_.toInt)
    val originalValue = board.getBoard(inputss(0) - 1, inputss(1) - 1)
    board.setBoard(inputss(0) - 1, inputss(1) - 1, Space.TARGETING)
    board.printBoard()
    while (!validInput) {
      println("You sure you want to shoot there?(Y/N)")
      val input = readLine().trim.toLowerCase()
      if (input == "y" || input == "n") {
        validInput = true
        if (input == "y") {
          shotsTaken+=1
          var hit = false
          for (ship <- shipList){
            if ship.isCollide(Array(inputss(0) - 1, inputss(1) - 1)) then hit = true
          }

          if (hit) {
            board.setBoard(inputss(0) - 1, inputss(1) - 1, Space.HIT)
          } else {
            board.setBoard(inputss(0) - 1, inputss(1) - 1, Space.MISS)
          }
        } else {
          board.setBoard(inputss(0) - 1, inputss(1) - 1, originalValue)
        }
      } else {
        println("invalid input")
      }
    }

  }

  private def forfeit(): Unit = {
    for (ship <- shipList){
      for (coord <- ship.getPlacement){
        if (board.getBoard(coord(0), coord(1)) != HIT) { // sometimes the logic of java/scala just amazes me, why does one use of the enum needs the class name and one doesnt
          board.setBoard(coord(0), coord(1), Space.FORFEIT)
        }
      }
    }
    board.printBoard()
    println("You lose!")
    gameEnd = true
  }
}
