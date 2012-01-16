/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: May 31, 2009
 * Time: 9:23:08 AM
 */
package com.joshcough.cpu.memory

import electric.Switch
import org.scalatest.FunSuite
import pimped.Equalizer._
import BitsToList._

class FlipFlopChainTest extends FunSuite {
  
  test("by default with write bit off, flip flop should contain all zeros") {
    val data = AllSwitchBitList(8)
    data.flipAllBits
    val writeBit = Switch.off

    val chain = Register(data, writeBit)

    // even though all bits are on, flip flop shouldnt care
    // because the write bit is off
    chain.output.toString mustBe "00000000"
  }

  test("by default with write bit on, flip flop should take on its data") {
    val data = AllSwitchBitList(8)
    data.flipAllBits
    val writeBit = Switch.on

    val chain = Register(data, writeBit)
    chain.output.toString mustBe "11111111"
  }

  test("changes to data with write bit off should not be remembered") {
    val data = AllSwitchBitList(8)
    val writeBit = Switch.off
    val chain = Register(data, writeBit)

    data flip 3
    data flip 5
    data flip 7

    data.toString mustBe "10101000"
    chain.output.toString mustBe "00000000"
  }


  test("change states of write bit and make sure things hold") {
    val data = AllSwitchBitList(8)
    val writeBit = Switch.on

    // create a new chain with data all zeros, and the write bit on.
    val chain = Register(data, writeBit)
    // by default, if write bit is on
    // flip flop chain takes on the value of its input data.
    chain.output.toString mustBe "00000000"

    // because write bit is on, changes must be remembered.
    data flip 3
    chain.output.toString mustBe "00001000"

    // turn write bit off
    // any bit flipping should be ignored.
    writeBit.turnOff
    data.flipAllBits
    chain.output.toString mustBe "00001000"

    // turn the write bit back on
    // and the data should now be "saved"
    writeBit.turnOn
    chain.output.toString mustBe "11110111"
  }

}