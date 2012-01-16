package com.joshcough.cpu.electric;

import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._

trait WireTest extends FunSuite{

  test("bare wire should simply be off"){
    new Wire().state mustBe Off
  }

  test("expect New Wire To Be On"){
    val gen = Switch.on
    val wire = new Wire
    gen --> wire
    wire.state mustBe On
  }
  
}
