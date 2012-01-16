package com.joshcough.cpu.electric

import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._

trait InverterTest extends FunSuite{

  test("inverter Should Be Off When Switch is on"){
    // given
    val inverter = Inverter(Switch.on)
    
    // then
    inverter.state mustBe Off
  }
  
  test("inverter Should Be On When Switch is off"){
    //  given
    val inverter = Inverter(Switch.off)

    // then
    inverter.state mustBe On
  }
  
  test("inverter Should Turn On When Switch Is Turned Off"){
    //  given
    val gen = Switch.on
    val inverter = Inverter(gen)

    //when 
    gen.turnOff
    
    // then
    inverter.state mustBe On
  }

  test("inverter Should Turn Back Off When Switch Is Turned Back On"){
    //  given
    val gen = Switch.on
    val inverter = Inverter(gen)

    //when 
    gen.turnOff
    
    // then
    inverter.state mustBe On
    
    //  when 
    gen.turnOn

    // then
    inverter.state mustBe Off
  }
}
