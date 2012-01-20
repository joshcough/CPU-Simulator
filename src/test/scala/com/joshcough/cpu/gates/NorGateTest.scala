package com.joshcough.cpu.gates

import com.joshcough.cpu.electric.State._
import org.scalatest.FunSuite
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.electric.Switch
import com.joshcough.cpu.gates.LogicGate._
import com.joshcough.cpu.electric.State._

trait NorGateTest extends FunSuite{
  
  test("test NorGate With Two On Switches"){
    nor(Switch(On), Switch(On)).state mustBe Off
  }

  test("test NorGate With Two Off Switches"){
    nor(Switch(Off), Switch(Off)).state mustBe On
  }
  
  test("test NorGate With One On Switch and One Off Switch"){
    nor(Switch(On), Switch(Off)).state mustBe Off
    nor(Switch(Off), Switch(On)).state mustBe Off
  }  

  test("test NorGate While Toggling Switch States"){
    val switchA = Switch(On)
    val switchB = Switch(On)
    val n = nor(switchA, switchB)
    n.state mustBe Off
    
    switchA.turnOff; n.state mustBe Off
    switchA.turnOn;  n.state mustBe Off
    
    switchB.turnOff; n.state mustBe Off
    switchB.turnOn; n.state mustBe Off
    
    switchA.turnOff; switchB.turnOff; n.state mustBe On
    switchA.turnOn; switchB.turnOn; n.state mustBe Off
  }
  
}
