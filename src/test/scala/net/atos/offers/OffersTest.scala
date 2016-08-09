package net.atos.offers

import net.atos.items.Items.{Orange, Apple}
import org.scalatest.FreeSpec

class OffersTest extends FreeSpec {

  "buyOneGetOneFreeApple offer should" - {
    "return correct price and remaining items for" - {
      "2 apples and 1 orange" in {
        assert(Offers.buyOneGetOneFreeApple(List(Apple, Apple, Orange)) == (60, List(Orange)))
      }
      "0 apples and 3 orange" in {
        assert(Offers.buyOneGetOneFreeApple(List(Orange, Orange, Orange)) == (0, List(Orange, Orange, Orange)))
      }
      "3 apples and 0 orange" in {
        assert(Offers.buyOneGetOneFreeApple(List(Apple, Apple, Apple)) == (60, List(Apple)))
      }
    }
  }

  "buyTwoForThreeOranges offer should" - {
    "return correct price and remaining items for" - {
      "2 apples and 1 orange" in {
        assert(Offers.buyTwoForThreeOranges(List(Apple, Apple, Orange)) == (0, List(Apple, Apple, Orange)))
      }
      "0 apples and 3 orange" in {
        assert(Offers.buyTwoForThreeOranges(List(Orange, Orange, Orange)) == (50, Nil))
      }
      "3 apples and 0 orange" in {
        assert(Offers.buyTwoForThreeOranges(List(Apple, Apple, Apple)) == (0, List(Apple, Apple, Apple)))
      }
      "5 oranges and 2 apples" in {
        assert(Offers.buyTwoForThreeOranges(List.fill(5)(Orange)) == (50, List(Orange, Orange)))
      }
    }
  }

  "applyOffer should" - {
    "apply an offer returning the correct running total for" - {
      "no previous total and no offers in cart" in {
        assert(Offers.applyOffer((0, List(Apple)), Offers.buyOneGetOneFreeApple) == (0, List(Apple)))
      }
      "a previous total and no offers in cart" in {
        assert(Offers.applyOffer((50, List(Apple)), Offers.buyOneGetOneFreeApple) == (50, List(Apple)))
      }
      "no previous total and an offers in cart" in {
        assert(Offers.applyOffer((0, List(Apple, Apple)), Offers.buyOneGetOneFreeApple) == (60, Nil))
      }
      "a previous total and an offers in cart" in {
        assert(Offers.applyOffer((50, List(Apple, Apple)), Offers.buyOneGetOneFreeApple) == (110, Nil))
      }
    }
  }

  "applyOffers should" - {
    "return the total price of all items in the cart applying active offers where" - {
      "the cart contains no offers" in {
        assert(Offers.applyOffers(List(Apple, Orange)) == 85)
      }
      "the cart contains a buy on apple get one free offer" in {
        assert(Offers.applyOffers(List(Apple, Apple)) == 60)
      }
      "the cart contains a buy three oranges for 2 offer" in {
        assert(Offers.applyOffers(List(Orange, Orange, Orange)) == 50)
      }
      "the cart contains both offers" in {
        assert(Offers.applyOffers(List(Apple, Apple, Orange, Orange, Orange, Orange)) == 135)
      }
    }
  }

}
