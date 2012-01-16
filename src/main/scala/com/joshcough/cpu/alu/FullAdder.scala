package com.joshcough.cpu.alu

import gates.OrGate
import electric.PowerSource

case class FullAdder(a: PowerSource, b: PowerSource, carryIn: PowerSource) {

  private val ha1 = HalfAdder(a,b)
  private val ha2 = HalfAdder(carryIn,ha1.getSumOut)
  private val or =  OrGate(ha2.getCarryOut, ha1.getCarryOut)
  
  def getSumOut:   PowerSource = ha2.getSumOut
  def getCarryOut: PowerSource = or
}