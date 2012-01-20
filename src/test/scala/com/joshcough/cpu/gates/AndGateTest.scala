package com.joshcough.cpu.gates;

import org.scalatest.FunSuite
import com.joshcough.cpu.electric.Switch
import com.joshcough.cpu.electric.State._
import com.joshcough.pimped.Equalizer._

trait AndGateTest extends FunSuite{

  val switches = List(Switch(On), Switch(Off))

  for(switchA <- switches; switchB <- switches){
    test("And Gate States: " + switchA + ", " + switchB){
      LogicGate.and(switchA, switchB).state mustBe (switchA.state && switchB.state)
    }
  }

  test("And Gate While Toggling Switch States"){
    val switchA = Switch(On)
    val switchB = Switch(On)
    val and = LogicGate.and(switchA, switchB)
    and.state mustBe On
    
    switchA.turnOff; and.state mustBe Off
    switchA.turnOn;  and.state mustBe On
    
    switchB.turnOff; and.state mustBe Off
    switchB.turnOn; and.state mustBe On
    
    switchA.turnOff; switchB.turnOff; and.state mustBe Off
    switchA.turnOn; switchB.turnOn; and.state mustBe On
  }
}
