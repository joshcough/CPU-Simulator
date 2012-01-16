package com.joshcough.cpu.gates;

import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._
import electric._

trait NandGateTest extends FunSuite{

  test("test NandGate WithTwoOnSwitches"){
    NandGate(Switch.on, Switch.on).state mustBe Off
  }
  
  test("test NandGate WithTwoOffSwitches"){
    NandGate(Switch.off, Switch.off).state mustBe On
  }
  
  test("test NandGateWithOneOnSwitchNandOneOffSwitch"){
    NandGate(Switch.on, Switch.off).state mustBe On
    NandGate(Switch.off, Switch.on).state mustBe On
  }  
  
  test("test NandGate While Toggling Switch States"){
    val genA = Switch.on
    val genB = Switch.on
    val nand = NandGate(genA, genB)
    nand.state mustBe Off
    
    genA.turnOff; nand.state mustBe On
    genA.turnOn;  nand.state mustBe Off
    
    genB.turnOff; nand.state mustBe On
    genB.turnOn; nand.state mustBe Off
    
    genA.turnOff; genB.turnOff; nand.state mustBe On
    genA.turnOn; genB.turnOn; nand.state mustBe Off
  }
  
}
