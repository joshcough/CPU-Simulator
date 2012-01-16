/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: May 31, 2009
 * Time: 10:10:04 PM
 */
package com.joshcough.cpu.memory

import electric.{On, Off}
import org.scalatest.FunSuite
import pimped.Equalizer._
import BitsToList._


trait SelectorTest extends FunSuite {
  
  test("selector") {
    val reg0 = AllSwitchBitList(8)
    val reg1 = AllSwitchBitList(8)
    val reg2 = AllSwitchBitList(8)
    val reg3 = AllSwitchBitList(8)

    val address = AllSwitchBitList(2)
    val selector = Selector(address, List(reg0, reg1, reg2, reg3))

    reg0.flipAllBits
    reg2.flip(0,7)
    reg3.flip(3,4)

    address.setTo(0)
    selector.outputs.toString mustBe "11111111"
    
    address.setTo(1)
    selector.outputs.toString mustBe "00000000"

    address.setTo(2)
    selector.outputs.toString mustBe "10000001"

    address.setTo(3)
    selector.outputs.toString mustBe "00011000"

    //selector.dump
  }
}