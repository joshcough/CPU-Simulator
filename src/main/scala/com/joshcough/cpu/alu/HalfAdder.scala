package com.joshcough.cpu.alu

import com.joshcough.cpu.electric.PowerSource
import com.joshcough.cpu.gates._

case class HalfAdder(a: PowerSource, b: PowerSource) {
  def getSumOut: PowerSource = XorGate(a,b)
  def getCarryOut: PowerSource = AndGate(a,b)
}