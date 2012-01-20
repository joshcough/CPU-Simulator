package com.joshcough.cpu.electric;

import org.scalatest.FunSuite
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.electric.State._

trait WireTest extends FunSuite{

  test("bare wire should simply be off"){
    new Wire().state mustBe Off
  }

  test("expect New Wire To Be On"){
    val gen = Switch(On)
    val wire = new Wire
    gen --> wire
    wire.state mustBe On
  }
  
}
