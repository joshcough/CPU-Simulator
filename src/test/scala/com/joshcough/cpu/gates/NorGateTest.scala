package com.joshcough.cpu.gates

import electric.Switch
import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._
import electric._

trait NorGateTest extends FunSuite{
  
  test("test NorGate With Two On Switches"){
    NorGate(Switch.on, Switch.on).state mustBe Off
  }

  test("test NorGate With Two Off Switches"){
    NorGate(Switch.off, Switch.off).state mustBe On
  }
  
  test("test NorGate With One On Switch and One Off Switch"){
    NorGate(Switch.on, Switch.off).state mustBe Off
    NorGate(Switch.off, Switch.on).state mustBe Off
  }  

  test("test NorGate While Toggling Switch States"){
    val genA = Switch.on
    val genB = Switch.on
    val nor = NorGate(genA, genB)
    nor.state mustBe Off
    
    genA.turnOff; nor.state mustBe Off
    genA.turnOn;  nor.state mustBe Off
    
    genB.turnOff; nor.state mustBe Off
    genB.turnOn; nor.state mustBe Off
    
    genA.turnOff; genB.turnOff; nor.state mustBe On
    genA.turnOn; genB.turnOn; nor.state mustBe Off
  }
  
}
