/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: Jun 6, 2009
 * Time: 7:22:30 PM
 */
package com.joshcough.cpu.memory

import com.joshcough.cpu.electric.PowerSource
import com.joshcough.cpu.gates.LogicGate._
import com.joshcough.cpu.BitList

case class Decoder(dataIn: BitList, addressInputs: BitList, writeBit: PowerSource) extends Addressable {

  val output: List[List[PowerSource]] = addresses.map{as => dataIn.powerSources.map(p => and(p :: as:_*))}

  // doing something atypical here. rewiring the write bit
  // instead of using the clock and writing the output of the
  // register back into itself.
  // it works. might need to change it later to be more standard
  // but, im not sure why it isn't this way always, actually...
  val writeBits: List[PowerSource] = addresses.map(a => and(writeBit :: a :_*))

  def dump() {
    println("--------------start of decoder--------------")
    println("dataIn:" + BitList(dataIn))
    println("addresses: " + addresses.map {as => BitList(as.map {case p: PowerSource => p})})
    println("outputs:" + output.map( BitList(_) ) )
    println("--------------end of decoder--------------")
  }
}