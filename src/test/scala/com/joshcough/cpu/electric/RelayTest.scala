//package com.joshcough.cpu.electric
//
//import org.scalatest.FunSuite
//import com.joshcough.pimped.Equalizer._
//
//trait RelayTest extends FunSuite{
//
//  test("relay Should Be On When PowerSource is on") { Relay(Switch(On)).state mustBe On }
//
//  test("relay Should Be Off When Switch(Off)") { Relay(Switch(Off)).state mustBe Off }
//
//  test("relay Should Turn Off When Switch Is Turned Off"){
//    //  given
//    val gen = Switch(On)
//    val relay = Relay(gen)
//
//    //when
//    gen.turnOff
//
//    // then
//    relay.state mustBe Off
//  }
//
//  test("relay Should Turn Back On When Switch Is Turned Back On"){
//    //  given
//    val gen = Switch(On)
//    val relay = Relay(gen)
//
//    //when
//    gen.turnOff
//
//    // then
//    relay.state mustBe Off
//
//    //  when
//    gen.turnOn
//
//    // then
//    relay.state mustBe On
//  }
//
//  test("two Switch Relay Should Turn Off When First Switch Is Turned Off"){
//    //  given
//    val genToMagnet = Switch(On)
//    val genToSwitch = Switch(On)
//
//    val relay = Relay(genToMagnet, genToSwitch)
//
//    //when
//    genToMagnet.turnOff
//
//    // then
//    relay.state mustBe Off
//  }
//
//  test("two Switch Relay Should Turn Off When Second Switch Is Turned Off"){
//    //  given
//    val genToMagnet = Switch(On)
//    val genToSwitch = Switch(On)
//
//    val relay = Relay(genToMagnet, genToSwitch)
//
//    //when
//    genToSwitch.turnOff
//
//    // then
//    relay.state mustBe Off
//  }
//}
