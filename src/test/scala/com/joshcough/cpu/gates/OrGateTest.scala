package com.joshcough.cpu.gates

import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._
import electric._

trait OrGateTest extends FunSuite{

  
  test("test OrGate With Two On Switches"){
    OrGate(Switch.on, Switch.on).state mustBe On
  }
  
  test("test OrGate With Two Off Switches"){
    OrGate(Switch.off, Switch.off).state mustBe Off
  }
  
  test("test OrGate With One On Switch and One Off Switch"){
    OrGate(Switch.on, Switch.off).state mustBe On
    OrGate(Switch.off, Switch.on).state mustBe On
  }  
  
  test("test OrGate While Toggling Switch States"){
    val genA = Switch.on
    val genB = Switch.on
    val or = OrGate(genA, genB)
    or.state mustBe On
    
    genA.turnOff; or.state mustBe On
    genA.turnOn;  or.state mustBe On
    
    genB.turnOff; or.state mustBe On
    genB.turnOn; or.state mustBe On
    
    genA.turnOff; genB.turnOff; or.state mustBe Off
    genA.turnOn; genB.turnOn; or.state mustBe On
  }
  
}
