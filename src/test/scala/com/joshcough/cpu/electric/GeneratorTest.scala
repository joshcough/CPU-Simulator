package com.joshcough.cpu.electric

import org.scalatest.FunSuite
import org.testng.annotations._
import org.easymock.EasyMock._
import org.easymock.EasyMock.expect
import pimped.Equalizer._

trait SwitchTest extends FunSuite{

  test("expect New Switch To Be On") {
    withSwitch { gen =>
      gen.state mustBe On
    }
  }

  test("expect Turned Off Switch off"){
    withSwitch{ gen =>
      gen.turnOff
      gen.state mustBe Off
    }
  }

  test("when The Switch Is Turned Off Make Sure Connection Is Notified"){
    withSwitch { gen =>
      // given
      val mockPowerSource = createMockWithConnectionTo(gen)
      // when
      gen.turnOff
      // then
      verify(Array(mockPowerSource))
    }
  }

  test("when The Switch Is Turned Off Make Sure All Connections Are Notified"){
    withSwitch { gen =>
      // given
      val powerSourcesToBeNotified =
        List(
          createMockWithConnectionTo(gen),
          createMockWithConnectionTo(gen), 
          createMockWithConnectionTo(gen))
      // when
      gen.turnOff
      // then
      powerSourcesToBeNotified.foreach(easyVerify)
    }
  }

  test("last PowerSource In Chain Should Be Notified When First In Chain Is Turned Off"){
    withSwitch { gen =>
      //given
      val first = Switch.on
      val secondToLast = new Wire

      first --> new Wire --> new Wire --> new Wire --> secondToLast

      val last = createMockWithConnectionTo(secondToLast)

      // when
      first.turnOff

      // then
      verify(Array(last))
    }
  }

  def withSwitch(f: Switch => Unit) = {f(Switch.on)}

  private def easyVerify(p: PowerSource) = verify(Array(p))

  private def createMockWithConnectionTo(p: PowerSource): PowerSource = {
    val mockPowerSource: PowerSource = createStrictMock(classOf[PowerSource]).asInstanceOf[PowerSource]
    org.easymock.EasyMock.expect(mockPowerSource <-- p).andReturn(p)
    org.easymock.EasyMock.expect(mockPowerSource.handleStateChanged(p))
    replay(Array(mockPowerSource))
    p --> mockPowerSource
    mockPowerSource
  }

  //private def createMockWithConnectionTo: PowerSource = createMockWithConnectionTo(gen)
}

