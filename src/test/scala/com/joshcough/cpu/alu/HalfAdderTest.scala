package com.joshcough.cpu.alu

import org.scalatest.FunSuite
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.alu.Adders.HalfAdder
import com.joshcough.cpu.electric.Switch
import com.joshcough.cpu.electric.State._

trait HalfAdderTest extends FunSuite{
  
  test("HalfAdder With Two On Switches"){
    val ha = HalfAdder(Switch(On), Switch(On))
    ha.getSumOut.state mustBe Off
    ha.getCarryOut.state mustBe On
  }
        
  test("HalfAdder With Two Off Switches"){
    val ha = HalfAdder(Switch(Off), Switch(Off))
    ha.getSumOut.state mustBe Off
    ha.getCarryOut.state mustBe Off
  }
        
  test("HalfAdder With One On Switch And One Off Switch"){
    var ha = HalfAdder(Switch(On), Switch(Off))
    ha.getSumOut.state mustBe On
    ha.getCarryOut.state mustBe Off

    ha = HalfAdder(Switch(Off), Switch(On))
    ha.getSumOut.state mustBe On
    ha.getCarryOut.state mustBe Off
  }

  test("HalfAdder Changes properly"){
    val (switchA, switchB) = (Switch(On), Switch(On))
    val ha = HalfAdder(switchA, switchB)

    ha.getSumOut.state mustBe Off
    ha.getCarryOut.state mustBe On

    switchA.flip

    ha.getSumOut.state mustBe On
    ha.getCarryOut.state mustBe Off

    switchB.flip

    ha.getSumOut.state mustBe Off
    ha.getCarryOut.state mustBe Off

    switchB.flip

    ha.getSumOut.state mustBe On
    ha.getCarryOut.state mustBe Off

    switchA.flip

    ha.getSumOut.state mustBe Off
    ha.getCarryOut.state mustBe On

  }
}
