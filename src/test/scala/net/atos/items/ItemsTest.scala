package net.atos.items

import net.atos.items.Items.{Invalid, Orange, Apple}
import org.scalatest.FreeSpec

class ItemsTests extends FreeSpec {

  "Apples should cost 60" in {
    assert(Apple.cost == 60)
  }

  "Oranges should cost 25" in {
    assert(Orange.cost == 25)
  }

  "Invalid should be 0" in {
    assert(Invalid.cost == 0)
  }

}
