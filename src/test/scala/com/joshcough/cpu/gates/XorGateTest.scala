package com.joshcough.cpu.gates;

import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._
import electric._

trait XorGateTest extends FunSuite{

  test("test XorGate With Two On Switches"){
    XorGate(Switch.on, Switch.on).state mustBe Off
  }

  test("test XorGate With Two Off Switches"){
    XorGate(Switch.off, Switch.off).state mustBe Off
  }
  
  test("test XorGate With One On Switch and One Off Switch"){
    XorGate(Switch.on, Switch.off).state mustBe On
    XorGate(Switch.off, Switch.on).state mustBe On
  }  
  
  test("test XorGate While Toggling Switch States"){
    val genA = Switch.on
    val genB = Switch.on
    val xor = XorGate(genA, genB)
    xor.state mustBe Off
    
    genA.turnOff; xor.state mustBe On
    genA.turnOn;  xor.state mustBe Off
    
    genB.turnOff; xor.state mustBe On
    genB.turnOn; xor.state mustBe Off
    
    genA.turnOff; genB.turnOff; xor.state mustBe Off
    genA.turnOn; genB.turnOn; xor.state mustBe Off
  }
  
}
