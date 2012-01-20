package com.joshcough.cpu.electric

import org.scalatest.FunSuite
import org.easymock.EasyMock._
import org.easymock.EasyMock.expect
import com.joshcough.pimped.Equalizer._
import State._

trait SwitchTest extends FunSuite {

  def withSwitch(f: Switch => Unit) = {f(Switch(On))}

  test("expect New Switch To Be On") { withSwitch { s => s.state mustBe On } }
  test("expect Turned Off Switch(Off)"){ withSwitch{ s => s.turnOff; s.state mustBe Off } }

  test("when The Switch Is Turned Off Make Sure Connection Is Notified"){
    withSwitch { switch =>
      // given
      val wire = new Wire
      switch --> wire
      wire.state mustBe On

      // when
      switch.turnOff
      // then
      wire.state mustBe Off
    }
  }

  test("last PowerSource In Chain Should Be Notified When First In Chain Is Turned Off"){
    withSwitch { first =>
      //given
      val last = Wire()

      first --> Wire() --> Wire() --> Wire() --> Wire() -> last

      // when
      first.turnOff

      // then
      last.state mustBe Off
    }
  }
}

