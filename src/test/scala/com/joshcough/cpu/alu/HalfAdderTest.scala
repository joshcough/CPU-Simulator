package com.joshcough.cpu.alu

import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._
import electric._

trait HalfAdderTest extends FunSuite{
  
  test("HalfAdder With Two On Switches"){
    val ha = HalfAdder(Switch.on, Switch.on)
    ha.getSumOut.state mustBe Off
    ha.getCarryOut.state mustBe On
  }
        
  test("HalfAdder With Two Off Switches"){
    val ha = HalfAdder(Switch.off, Switch.off)
    ha.getSumOut.state mustBe Off
    ha.getCarryOut.state mustBe Off
  }
        
  test("HalfAdder With One On Switch And One Off Switch"){
    var ha = HalfAdder(Switch.on, Switch.off)
    ha.getSumOut.state mustBe On
    ha.getCarryOut.state mustBe Off

    ha = HalfAdder(Switch.off, Switch.on)
    ha.getSumOut.state mustBe On
    ha.getCarryOut.state mustBe Off
  }
}
