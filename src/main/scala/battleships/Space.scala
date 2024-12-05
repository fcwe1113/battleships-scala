package battleships

enum Space:
  case HIT, MISS, UNKNOWN, FORFEIT, TARGETING

  override def toString: String = {
    this match{
      case HIT => "O"
      case MISS => "X"
      case UNKNOWN => " "
      case FORFEIT => "i"
      case TARGETING => "⌖"
      case null => "lol" // should never happen
    }
  }