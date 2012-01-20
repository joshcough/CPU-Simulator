package com.joshcough.cpu.gates

import org.scalatest.FunSuite
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.electric.Switch
import com.joshcough.cpu.electric.State._
import com.joshcough.cpu.gates.LogicGate._

trait OrGateTest extends FunSuite{
  
  test("test OrGate With Two On Switches"){
    or(Switch(On), Switch(On)).state mustBe On
  }
  
  test("test OrGate With Two Off Switches"){
    or(Switch(Off), Switch(Off)).state mustBe Off
  }
  
  test("test OrGate With One On Switch and One Off Switch"){
    or(Switch(On), Switch(Off)).state mustBe On
    or(Switch(Off), Switch(On)).state mustBe On
  }  
  
  test("test OrGate While Toggling Switch States"){
    val switchA = Switch(On)
    val switchB = Switch(On)
    val or = LogicGate.or(switchA, switchB)
    or.state mustBe On
    
    switchA.turnOff; or.state mustBe On
    switchA.turnOn;  or.state mustBe On
    
    switchB.turnOff; or.state mustBe On
    switchB.turnOn; or.state mustBe On
    
    switchA.turnOff; switchB.turnOff; or.state mustBe Off
    switchA.turnOn; switchB.turnOn; or.state mustBe On
  }
  
}
