package com.joshcough.cpu.alu;

import com.joshcough.cpu.electric._
import com.joshcough.cpu.electric.State._
import com.joshcough.cpu.gates._
import com.joshcough.cpu.alu._
import org.scalatest.FunSuite
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.alu.Adders.FullAdder

trait FullAdderTest extends FunSuite{

  test("FullAdder With Three On Switches"){
    val fa = FullAdder( Switch(On), Switch(On), Switch(On) )
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe On
  }

  test("FullAdder With Three Off Switches"){
    val fa = FullAdder( Switch(Off), Switch(Off), Switch(Off) )
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe Off
  }
  
  test("only Sum Out Should Be On With One On Input"){
    val fa = FullAdder( Switch(On), Switch(Off), Switch(Off) )
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe Off
  }
  
  test("only Carry Out Should Be On With Two On Inputs"){
    val fa = FullAdder( Switch(On), Switch(On), Switch(Off) )
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe On
  }  
  
  test("Turning On And Off FullAdder Inputs"){
    val switchA = Switch(On)
    val switchB = Switch(On)
    val switchC = Switch(On)
    val fa = FullAdder( switchA, switchB, switchC )
    
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe On
    
    switchA.turnOff
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe On
    
    switchB.turnOff
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe Off
    
    switchC.turnOff
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe Off
    
    
    switchB.turnOn
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe Off
    
    switchA.turnOn
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe On
    
    switchC.turnOn
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe On
  }

}
