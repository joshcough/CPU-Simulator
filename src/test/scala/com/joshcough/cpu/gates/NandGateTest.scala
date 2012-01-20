package com.joshcough.cpu.gates;

import org.scalatest.FunSuite
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.electric._
import com.joshcough.cpu.electric.State._
import com.joshcough.cpu.electric.Switch
import com.joshcough.cpu.gates.LogicGate._

class NandGateTestClass extends NandGateTest

trait NandGateTest extends FunSuite{

  test("test NandGate WithTwoOnSwitches"){
    val n = nand(Switch(On), Switch(On))
    n.state mustBe Off
  }
  
  test("test NandGate WithTwoOffSwitches"){
    nand(Switch(Off), Switch(Off)).state mustBe On
  }
  
  test("test NandGateWithOneOnSwitchNandOneOffSwitch"){
    nand(Switch(On), Switch(Off)).state mustBe On
    nand(Switch(Off), Switch(On)).state mustBe On
  }  
  
  test("test NandGate While Toggling Switch States"){
    val switchA = Switch(On)
    val switchB = Switch(On)
    val nand = LogicGate.nand(switchA, switchB)
    nand.state mustBe Off
    
    switchA.turnOff; nand.state mustBe On
    switchA.turnOn;  nand.state mustBe Off
    
    switchB.turnOff; nand.state mustBe On
    switchB.turnOn; nand.state mustBe Off
    
    switchA.turnOff; switchB.turnOff; nand.state mustBe On
    switchA.turnOn; switchB.turnOn; nand.state mustBe Off
  }
  
}
