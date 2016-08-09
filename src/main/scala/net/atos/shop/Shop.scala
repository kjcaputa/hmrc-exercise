package net.atos.shop

import net.atos.items.Items
import Items.{Apple, Invalid, Item, Orange}
import net.atos.offers.Offers

object Shop {

  def purchase(items: List[String]): Option[String] = {
    calculatePrice(items.map(convertItem))
  }

  private[shop] def convertItem(item: String): Item = {
    item.toUpperCase match {
      case "APPLE" => Apple
      case "ORANGE" => Orange
      case _ => Invalid
    }
  }

  private[shop] def calculatePrice(items: List[Item]): Option[String] = {
    items match {
      case Nil => Some("0.00")
      case x if x.contains(Invalid) => None
      case x =>
        val total: Int = Offers.applyOffers(x)
        Some(f"${total.toDouble / 100}%1.2f")
    }
  }

}
