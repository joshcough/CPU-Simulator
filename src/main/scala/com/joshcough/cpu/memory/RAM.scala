/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: Jun 6, 2009
 * Time: 8:25:16 PM
 */
package com.joshcough.cpu.memory

import BitsToList._
import electric.{On, Switch, BasePowerSource, PowerSource}

/**
 * Automatically creates Math.pow(2, addressInputs.size) registers.
 * This gives a ram of (dataIn.size x Math.pow(2, addressInputs.size))
 *
 * ex:
 * dataIn is 8 bits wide, addressInputs is 3 bits wide
 * then we have 8 addressable registers 8 bits wide each,
 * for a total of 64 bits of memory
 *
 * most will need more, so heres an larger example.
 *
 * lets say the registers are each two bytes wide (16 bits)
 * and we have 8 address bits for 256 addressable 2-byte registers
 * this gives us 512 bytes of memory (4096 bits)
 *
 * for 1kB of memory simply add one address bit (2^9 * 16)
 * for 4kB use 32 bit registers with 10 address bits (2^10 * 32)
 *
 *
 * 1 byte registers (8 bits) * 3 address bits = 8 bytes
 * 1 byte registers * 4 address bits = 16 bytes
 * 1 byte registers * 5 address bits = 32 bytes
 * 1 byte registers * 6 address bits = 64 bytes
 * 1 byte registers * 7 address bits = 128 bytes
 * 1 byte registers * 8 address bits = 256 bytes
 * 1 byte registers * 9 address bits = 512 bytes
 * 1 byte registers * 10 address bits = 1024 bytes = 1kB
 * 1 byte registers * 11 address bits = 2048 bytes = 2kB
 * 1 byte registers * 12 address bits = 4096 bytes = 3kB
 * 1 byte registers * 13 address bits = 8192 bytes = 8kB
 * 1 byte registers * 14 address bits = 16384 bytes = 16kB
 * 1 byte registers * 15 address bits = 32768 bytes = 32kB
 * 1 byte registers * 16 address bits = 65536 bytes = 64kB
 */
trait RAM extends Addressable {
  val dataIn: BitList
  val writeBit: PowerSource
  val dataOut: BitList
  val numberOfRegisters = Math.pow(2, addressInputs.size).toInt

  def dump {
    println("--------------start of ram--------------")
    println(this)
    println("dataIn:" + dataIn)
    println("addresses: " + addresses.map {as => BitList(as.map {case p: PowerSource => p})})
    println("writeBit:" + writeBit)
    println("outputs:" + dataOut)
    println("--------------end of ram--------------")
  }
}

/**
 * RAM made up entirely of logic gates. 
 */
case class RealRAM(val dataIn: BitList,
                   val addressInputs: BitList,
                   val writeBit: PowerSource) extends RAM {

  val decoder = Decoder(dataIn, addressInputs, writeBit)

  val registers: List[Register] = decoder.output.zip(decoder.writeBits).map {
    case (data, wb) => Register(data, wb)
  }

  var selector = Selector(addressInputs, registers.map(_.output))

  val dataOut = selector.outputs
}

/**
 * This RAM is not real, in the sense that it is not really made
 * up entirely of logic gates at all... it can be used as a replacement
 * for the real ram because its obviously much much faster.
 * But, its impure. 
 */
case class FastRAM(val dataIn: BitList,
                   val addressInputs: BitList,
                   val writeBit: PowerSource) extends RAM {

  // update if anything changes.
  val updater = new Updater()
  writeBit --> updater
  dataIn.foreach(p => p --> updater)
  addressInputs.foreach(p => p --> updater)

  // create the registers, all initialized to zero
  val registers = new scala.collection.mutable.ArrayBuffer[String]
  val emptyRegister = "0" * dataIn.size
  (0 until numberOfRegisters).foreach {i => registers += emptyRegister}

  // the output is just some switches that are always kept correct
  val dataOut: AllSwitchBitList = AllSwitchBitList(dataIn.size)

  // this class handles updating the registers and the output
  class Updater extends BasePowerSource {
    override def handleStateChanged(p: PowerSource) = {

      val addr = addressInputs.toDecimal

      // if the write bit is on set the contents of register
      // addressed by address inputs to the contents of dataIn
      if (writeBit.state == On) registers(addr) = dataIn.toString

      // set the contents of dataOut to the contents of
      // the register addressed by address inputs
      dataOut setTo registers(addr)
    }
  }
}