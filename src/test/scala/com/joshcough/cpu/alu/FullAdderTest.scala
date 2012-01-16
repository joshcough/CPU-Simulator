package com.joshcough.cpu.alu;

import com.joshcough.cpu.electric._
import com.joshcough.cpu.gates._
import com.joshcough.cpu.alu._
import org.scalatest.FunSuite
import pimped.Equalizer._

trait FullAdderTest extends FunSuite{

  test("FullAdder With Three On Switches"){
    val fa = FullAdder( Switch.on, Switch.on, Switch.on )
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe On
  }

  test("FullAdder With Three Off Switches"){
    val fa = FullAdder( Switch.off, Switch.off, Switch.off )
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe Off
  }
  
  test("only Sum Out Should Be On With One On Input"){
    val fa = FullAdder( Switch.on, Switch.off, Switch.off )
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe Off
  }
  
  test("only Carry Out Should Be On With Two On Inputs"){
    val fa = FullAdder( Switch.on, Switch.on, Switch.off )
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe On
  }  
  
  test("Turning On And Off FullAdder Inputs"){
    val genA = Switch.on
    val genB = Switch.on
    val genC = Switch.on
    val fa = FullAdder( genA, genB, genC )
    
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe On
    
    genA.turnOff
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe On
    
    genB.turnOff
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe Off
    
    genC.turnOff
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe Off
    
    
    genB.turnOn
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe Off
    
    genA.turnOn
    fa.getSumOut.state mustBe Off
    fa.getCarryOut.state mustBe On
    
    genC.turnOn
    fa.getSumOut.state mustBe On
    fa.getCarryOut.state mustBe On
  }

}
