/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: Jun 6, 2009
 * Time: 7:22:30 PM
 */
package com.joshcough.cpu.memory

import electric.PowerSource
import gates.{AndGate, OrGate}
import BitsToList._

case class Decoder(dataIn: BitList, val addressInputs: BitList,
                   writeBit: PowerSource) extends Addressable {

  val output: List[List[PowerSource]] = {
    addresses.map { a => dataIn.map { b => new AndGate(b :: a) } }
  }

  // doing something atypical here. rewiring the write bit
  // instead of using the clock and writing the output of the
  // register back into itself.
  // it works. might need to change it later to be more standard
  // but, im not sure why it isn't this way always, actually...
  val writeBits: List[PowerSource] = {
    addresses.map { a => new AndGate(writeBit :: a) }
  }

  def dump {
    println("--------------start of decoder--------------")
    println("dataIn:" + BitList(dataIn))
    println("addresses: " + addresses.map {as => BitList(as.map {case p: PowerSource => p})})
    println("outputs:" + output.map( BitList(_) ) )
    println("--------------end of decoder--------------")
  }

}