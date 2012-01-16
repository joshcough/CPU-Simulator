package com.joshcough.cpu.memory

import gates.LogicGate._
import cpu.BitsToList._
import electric.{PowerSource, Inverter}

/**
 * @author dood
 * Date: Jun 6, 2009
 * Time: 7:17:50 PM
 */

trait Addressable {

  val addressInputs: BitList
  
  private val addressInverters: List[PowerSource] = addressInputs.map(Inverter(_))

  val addresses:List[List[PowerSource]] = {
    BitTable(addressInputs.size,
             addressInputs(_),
             // hating the compiler right now for making me do this....
             addressInverters(_)).asInstanceOf[List[List[PowerSource]]]
  }
}