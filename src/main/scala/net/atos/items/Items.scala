package net.atos.items

object Items {

  sealed trait Item {
    val cost: Int
  }

  case object Apple extends Item {
    val cost: Int = 60
  }

  case object Orange extends Item {
    val cost: Int = 25
  }

  case object Invalid extends Item {
    val cost: Int = 0
  }

}
