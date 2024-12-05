package battleships

import scala.io.StdIn.readLine

var board = new Board
var shots_taken = 0
var game_end = false

object Game{
  def main(): Unit = {
    println("Welcome to battleships!\nIn this game you select a square to shoot\nto see if there is a ship hiding there.\nTry and sink all 5 ships with the lowest shots possible!\n")
    while (!game_end) {
      while (true) {
        board.printBoard()
        println(s"Misses: ${Space.MISS.toString}\tHits: ${Space.HIT.toString}\nShots fired: ${shots_taken}\nPlease select from the following:\n1. shoot\n2. forfeit")
        readLine() match
          case "1" => println("shoot")
          case "2" => println("forfeit")
          case _  => println("Invalid input") // error handled
      }
    }
  }

  def shoot(): Unit = {

  }
}
