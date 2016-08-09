package net.atos

import net.atos.shop.Shop

object Main {

  //Main to provide user interaction
  def main(args: Array[String]): Unit = {
    println(s"Running with args: ${args.mkString(", ")}")
    Shop.purchase(args.toList) match {
      case Some(price) =>
        println(s"Your total comes to: $price")
      case None =>
        println("Invalid input")
    }
  }

}
