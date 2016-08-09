package net.atos

object Shop {

  def purchase(items: List[String]): Option[String] = {
    calculatePrice(items.map(convertItem))
  }

  private[atos] def convertItem(item: String): Int = {
    item.toUpperCase match {
      case "APPLE" => 60
      case "ORANGE" => 25
      case _ => 0
    }
  }

  private[atos] def calculatePrice(items: List[Int]): Option[String] = {
    items match {
      case Nil => Some("0.00")
      case x if x.contains(0) => None
      case x => Some(f"${x.sum.toDouble / 100}%1.2f")
    }
  }

}
