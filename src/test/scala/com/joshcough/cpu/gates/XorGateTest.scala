package com.joshcough.cpu.gates;

import org.scalatest.FunSuite
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.electric.Switch
import com.joshcough.cpu.electric.State._
import com.joshcough.cpu.gates.LogicGate._

trait XorGateTest extends FunSuite{

  test("test XorGate With Two On Switches"){
    xor(Switch(On), Switch(On)).state mustBe Off
  }

  test("test XorGate With Two Off Switches"){
    xor(Switch(Off), Switch(Off)).state mustBe Off
  }
  
  test("test XorGate With One On Switch and One Off Switch"){
    xor(Switch(On), Switch(Off)).state mustBe On
    xor(Switch(Off), Switch(On)).state mustBe On
  }  
  
  test("test XorGate While Toggling Switch States"){
    val switchA = Switch(On)
    val switchB = Switch(On)
    val x = xor(switchA, switchB)
    x.state mustBe Off
    
    switchA.turnOff; x.state mustBe On
    switchA.turnOn;  x.state mustBe Off
    
    switchB.turnOff; x.state mustBe On
    switchB.turnOn; x.state mustBe Off
    
    switchA.turnOff; switchB.turnOff; x.state mustBe Off
    switchA.turnOn; switchB.turnOn; x.state mustBe Off
  }
  
}
