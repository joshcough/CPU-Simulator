package com.joshcough.cpu

import electric.Switch

/**
 *
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

  def apply(numOfBits: Int): AllSwitchBitList = {
    new AllSwitchBitList((0 until numOfBits).map(i => Switch.off).toList)
  }
}

class AllSwitchBitList(generators: List[Switch]) extends BitList(generators) {
  def setTo(dec: Int): Unit = {
    val zip =
    AllSwitchBitList.padBinaryString(Integer.toBinaryString(dec),
      powerSources.length).toCharArray.zip(generators.reverse.toArray)

    zip.foreach {
      case (num, ps) => num match {
        case '1' => ps.turnOn
        case '0' => ps.turnOff
      }
    }
  }

  def setTo(decString: String): Unit = setTo(Integer.parseInt(decString, 2))

  def flipAllBits = generators.foreach(_.flip)

  def flip(bits: Int*) {bits.foreach(generators(_).flip)}
}