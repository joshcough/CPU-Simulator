package com.joshcough.cpu.alu

import cpu.BitList
import cpu.BitsToList._
import electric.PowerSource

case class AdderChain(as: BitList, bs: BitList, carryIn: PowerSource) {

  if( as.size != bs.size ) error("numbers must both be the same size")

  private val (fullAdders, carryOut) =
    (as zip bs).foldLeft((List[FullAdder](),carryIn)){
      case ((current, carry), (a, b)) =>
        val adder = FullAdder(a, b, carry)
        (current ::: List(adder),adder.getCarryOut)
    }

  private val output = new BitList(fullAdders.map(fa => fa.getSumOut))
  def getOutput: BitList = output
  def getCarryOut: PowerSource = carryOut
}
