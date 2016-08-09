package net.atos.offers

import net.atos.items.Items.{Orange, Apple, Item}

//Simple solution that will only work for offers that don't clash. Some sort of offer precedence would need to be
//implemented to allow clashing offers. Right now it would just run through the list of active offers from first to last,
//possibly removing the chance of a better offer further down the list.
object Offers {

  type RunningTotal = (Int, List[Item])
  type Offer = List[Item] => RunningTotal

  private[offers] def anyItemXforY(amount: Int, price: Int, kind: Item): Offer = items => {
    val amountOfItems = items.count(_ == kind)
    (amountOfItems / amount * price, items.filterNot(_ == kind) ++ List.fill(amountOfItems % amount)(kind))
  }

  private[offers] val buyOneGetOneFreeApple: Offer = anyItemXforY(2, Apple.cost, Apple)

  private[offers] val buyTwoForThreeOranges: Offer = anyItemXforY(3, Orange.cost * 2, Orange)

  private val activeOffers: List[Offer] =
    List(buyOneGetOneFreeApple, buyTwoForThreeOranges)

  private[offers] val applyOffer: (RunningTotal, Offer) => RunningTotal = (itr, offer) => {
    val appliedOffer = offer(itr._2)
    (appliedOffer._1 + itr._1, appliedOffer._2)
  }

  def applyOffers(items: List[Item]): Int = {
    val appliedOffers = activeOffers.foldLeft((0, items))(applyOffer)
    appliedOffers._2.map(_.cost).sum + appliedOffers._1
  }

}
