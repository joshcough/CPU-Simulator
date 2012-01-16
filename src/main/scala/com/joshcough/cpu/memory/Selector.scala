/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: May 31, 2009
 * Time: 3:26:13 PM
 */
package com.joshcough.cpu.memory

import electric.{PowerSource, Inverter}
import gates.{OrGate, AndGate}
import gates.LogicGate._
import cpu.BitsToList._

// number of bits required (data, address)
// wish i could somehow do this with the type system
// 2 -> 1
// 4 -> 2
// 8 -> 3
// 16 -> 4
// etc...

/*
say you have 8 registers, 8 bits each
you have to have 3 address bits
000 addresses the first register
111 addresses the last one
the address does this:
well, each 8 bit register has 8 outputs
and they go directly into 8 and gates
the address is responsible for setting the ohter input to those And gates
for example
if you want the contents of the second register
the address is 001
the register has its bits hooked up the the and gate
the address wires make sure the and gates for 001 have the second inputs ON
lets sat the 2nd register has
01010101
hook those all up to an and gate
when the address is selected you get
01010101 AND 11111111
which just outputs 01010101
when that address is not selected you get
01010101 AND 00000000
which outputs 00000000
all the 7 registers who you arent interested in all output 00000000
 */

case class Selector(val addressInputs: BitList,registers:List[List[PowerSource]]) extends Addressable{

  // hook the registers and selectors up to and gates
  private val ands: List[List[AndGate]] = addresses.zip(registers.toList).map {
    case (l: List[PowerSource], bits: List[PowerSource]) => {
      bits.map(b => new AndGate(b :: l))
    }
  }

  val outputs: BitList = {
    val registerLength = registers(0).size
    val orGates = for (i <- 0 until registerLength) yield OrGate(ands.map(_(i)): _*)
    BitList(orGates.toList.map(_.output))
  }

  def dump {
    println("--------------start of selector--------------")
    println(this)
    println("addresses: " + addresses.map { as => BitList(as.map{case p:PowerSource => p})})
    println("ands: " + ands.map {as => BitList(as.map(_.output))})
    println("outputs:" + outputs)
    println("--------------end of selector--------------")
  }
}

