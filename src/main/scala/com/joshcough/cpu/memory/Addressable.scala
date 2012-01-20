package com.joshcough.cpu.memory

import com.joshcough.cpu.gates.LogicGate._
import com.joshcough.cpu.electric.PowerSource
import com.joshcough.cpu.BitList
import com.joshcough.cpu.electric.Inverter._

/**
 * @author dood
 * Date: Jun 6, 2009
 * Time: 7:17:50 PM
 */
trait Addressable {

  val addressInputs: BitList
  
  private val addressInverters: List[PowerSource] =
    addressInputs.powerSources.map(inverter(_)).asInstanceOf[List[PowerSource]]

  val addresses:List[List[PowerSource]] = {
    BitTable(addressInputs.powerSources.size,
             addressInputs(_),
             // hating the compiler right now for making me do this....
             addressInverters(_)).asInstanceOf[List[List[PowerSource]]]
  }
}