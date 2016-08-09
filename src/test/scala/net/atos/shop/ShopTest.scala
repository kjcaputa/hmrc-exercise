package net.atos.shop

import net.atos.items.Items
import Items.{Apple, Invalid, Orange}
import org.scalatest.FreeSpec

class ShopTest extends FreeSpec {

  val apple = "Apple"
  val orange = "Orange"

  "The shop should" - {
    "return the correct price for a valid input where" - {
      "the input is 3 apples" in {
        assert(Shop.purchase(List(apple, apple, apple)).contains("1.20"))
      }
      "the input is 2 apples and 2 oranges" in {
        assert(Shop.purchase(List(apple, apple, orange, orange)).contains("1.10"))
      }
      "the input is 3 oranges" in {
        assert(Shop.purchase(List(orange, orange, orange)).contains("0.50"))
      }
      "the input is zero items" in {
        assert(Shop.purchase(Nil).contains("0.00"))
      }
      "the input is 1 apple and 1 orange where case doesn't matter" in {
        assert(Shop.purchase(List("ApPlE", "ORANGE")).contains("0.85"))
      }
    }
    "return None for an invalid input where" - {
      "apple is misspelt" in {
        assert(Shop.purchase(List("Aple")).isEmpty)
      }
      "orange is misspelt" in {
        assert(Shop.purchase(List("Oran")).isEmpty)
      }
      "there is a banana in the cart" in {
        assert(Shop.purchase(List(apple, orange, "Banana")).isEmpty)
      }
    }
    "convert items to item objects where" - {
      "item is an apple" in {
        assert(Shop.convertItem(apple) == Apple)
      }
      "item is a orange" in {
        assert(Shop.convertItem(orange) == Orange)
      }
      "item is neither an apple or and orange" in {
        assert(Shop.convertItem("Banana") == Invalid)
      }
    }
    "correctly calculate the price of a list of items where" - {
      "the list of items contain no Invalid items" in {
        assert(Shop.calculatePrice(List(Apple, Orange)).contains("0.85"))
      }
      "the sum is greater that 10 pounds" in {
        assert(Shop.calculatePrice(List.fill(100)(Apple)).contains("30.00"))
      }
      "the items contain an Invalid item" in {
        assert(Shop.calculatePrice(List(Apple, Orange, Invalid)).isEmpty)
      }
      "the list of items is empty" in {
        assert(Shop.calculatePrice(Nil).contains("0.00"))
      }
    }
    "take into account offers and" - {
      "1 apple should cost the same as 2" in {
        assert(Shop.calculatePrice(List(Apple)) == Shop.calculatePrice(List(Apple, Apple)))
      }
      "2 Oranges should cost the same as 3" in {
        assert(Shop.calculatePrice(List(Orange, Orange)) == Shop.calculatePrice(List(Orange, Orange, Orange)))
      }
    }
  }

}
