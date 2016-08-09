package net.atos

import org.scalatest.FreeSpec

class ShopTest extends FreeSpec {

  val apple = "Apple"
  val orange = "Orange"

  "The shop should" - {
    "return the correct price for a valid input where" - {
      "the input is 3 apples" in {
        assert(Shop.purchase(List(apple, apple, apple)).contains("1.80"))
      }
      "the input is 2 apples and 2 oranges" in {
        assert(Shop.purchase(List(apple, apple, orange, orange)).contains("1.70"))
      }
      "the input is 3 oranges" in {
        assert(Shop.purchase(List(orange, orange, orange)).contains("0.75"))
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
  }

}
