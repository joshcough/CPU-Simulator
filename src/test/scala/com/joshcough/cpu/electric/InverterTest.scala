package com.joshcough.cpu.electric

import org.scalatest.FunSuite
import com.joshcough.cpu.electric.Inverter._
import com.joshcough.cpu.electric.State._
import com.joshcough.pimped.Equalizer._

class InverterTests extends InverterTest

trait InverterTest extends FunSuite{
//
//  test("inverter Should Be Off When Switch is on"){
//    inverter(Switch(On)).state mustBe Off
//  }
//
//  test("inverter Should Be On When Switch is off"){
//    inverter(Switch(Off)).state mustBe On
//  }
  
  test("inverter Should Turn On When Switch Is Turned Off"){
    //  given
    val s = Switch(On)
    val i = inverter(s)

    i.state mustBe Off

    //when 
    s.flip
    
    // then
    i.state mustBe On
  }

  test("inverter Should Turn Back Off When Switch Is Turned Back On"){
    //  given
    val s = Switch(On)
    val i = inverter(s)

    //when 
    s.flip
    
    // then
    i.state mustBe On
    
    //  when 
    s.flip

    // then
    i.state mustBe Off
  }
}
