package com.joshcough.cpu.alu

import com.joshcough.cpu.electric.PowerSource
import com.joshcough.cpu.gates.LogicGate._
import com.joshcough.cpu.BitList

object Adders {

  case class HalfAdder(a: PowerSource, b: PowerSource) {
    def getSumOut = xor(a,b)
    def getCarryOut = and(a,b)
  }

  case class FullAdder(a: PowerSource, b: PowerSource, carryIn: PowerSource) {
    private val ha1 = HalfAdder(a,b)
    private val ha2 = HalfAdder(carryIn,ha1.getSumOut)
    def getSumOut = ha2.getSumOut
    def getCarryOut = or(ha2.getCarryOut, ha1.getCarryOut)
  }

  case class NBitAdder(as: BitList, bs: BitList, carryIn: PowerSource) {

    if( as.length != bs.length ) sys.error("numbers must both be the same size")

    private val (fullAdders, carryOut) =
      (as zip bs).foldLeft((List[FullAdder](),carryIn)){
        case ((current, carry), (a, b)) =>
          val adder = FullAdder(a, b, carry)
          (current ::: List(adder),adder.getCarryOut)
      }

    private val output = new BitList(fullAdders.map(_.getSumOut))
    def getOutput: BitList = output
    def getCarryOut: PowerSource = carryOut
  }
}