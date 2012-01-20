package com.joshcough.cpu.gates

import com.joshcough.cpu.electric.{Wire, Inverter, State, BasePowerSource, PowerSource}
import Inverter.inverter
import State._

object LogicGate{
  def and(is: PowerSource*) = new BasePowerSource(is.toList){
    def calculateNewState(inputs: List[PowerSource]) = inputs.forall(_.state == On)
  }
  def or  (ins: PowerSource*) = new Wire(ins:_*)
  def nand(ins: PowerSource*) = inverter(and(ins:_*))
  def nor (ins: PowerSource*) = inverter(or(ins:_*))
  def xor (a: PowerSource, b: PowerSource) = and(or(a, b), nand(a, b))
}
