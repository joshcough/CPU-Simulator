package com.joshcough.cpu

import electric.{State, PowerSource, Switch}

/**
 * User: Dood
 * Date: Nov 25, 2008
 * Time: 1:55:33 PM
 */
object AllSwitchBitList {
  def padBinaryString(binaryString: String, digits: Int): String = {
    val buf = new StringBuffer()
    while (buf.length() + binaryString.length() < digits) {buf append "0"}
    buf append binaryString
    buf.toString
  }
  def apply(numOfBits: Int) = new AllSwitchBitList((0 until numOfBits).map(_ => Switch(State.Off)).toList)
}

class AllSwitchBitList(switches: List[Switch]) extends BitList(switches) {
  def setTo(decString: String){ setTo(Integer.parseInt(decString, 2)) }
  def flipAllBits(){ switches.foreach(_.flip) }
  def flip(bits: Int*) {bits.foreach(switches(_).flip)}
  def setTo(dec: Int) {
    AllSwitchBitList.padBinaryString(
      Integer.toBinaryString(dec), powerSources.length).zip(switches.reverse).foreach { case (num, ps) =>
        num match {
          case '1' => ps.turnOn
          case '0' => ps.turnOff
        }
    }
  }
}