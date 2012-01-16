package com.joshcough.cpu.gates;

import electric.Switch
import org.scalatest.FunSuite
import pimped.Equalizer._
import electric._

trait AndGateTest extends FunSuite{
  
  test("And Gate With Two On Switches"){
    AndGate(Switch.on, Switch.on).state mustBe On
  }
  
  test("And Gate With Two Off Switches"){
    AndGate(Switch.off, Switch.off).state mustBe Off
  }
  
  test("And Gate With One On Switch and One Off Switch"){
    AndGate(Switch.on, Switch.off).state mustBe Off
    AndGate(Switch.off, Switch.on).state mustBe Off
  }  

  test("And Gate While Toggling Switch States"){
    val genA = Switch.on
    val genB = Switch.on
    val and = AndGate(genA, genB)
    and.state mustBe On
    
    genA.turnOff; and.state mustBe Off
    genA.turnOn;  and.state mustBe On
    
    genB.turnOff; and.state mustBe Off
    genB.turnOn; and.state mustBe On
    
    genA.turnOff; genB.turnOff; and.state mustBe Off
    genA.turnOn; genB.turnOn; and.state mustBe On
  }
}
