package com.joshcough.cpu.memory


import electric.Switch
import org.scalatest.FunSuite
import BitsToList._
import pimped.Equalizer._
import test.Timers


/**
 * @author dood
 * Date: Jun 6, 2009
 * Time: 10:59:49 PM
 */
trait RAMTest extends FunSuite with Timers{
    
  test("can set and get all addresses 4x16") {
    withRam(4, 4)(testAllRamAddresses _)
  }

  test("can set and get all addresses 16x8 (512B)") {
    withRam(16, 8)(testAllRamAddresses _)
  }

  /**
   * current timings for 4KB (32x10):
   *
   * creating ram took: 39749 ms
   * setting all the data in the ram took:   64066 ms
   * getting all the data from the ram took: 79971 ms
   *
   * 64066/1024 = 62 ms for a single write
   * 79971/1024 = 78 ms for a single read
   */
  test("can set and get all addresses 32x10 (4096B or 4kB)") {
    withRam(32, 10)(testAllRamAddresses _)
  }

  test("timings for one operation in 512B ram") {
    withRam(16, 8) {
      (data, address, writeBit, ram) => {

        timed("setting single ram value in 512B") {
          writeBit.turnOn
          address setTo 256
          data setTo 42
          writeBit.turnOff
          address setTo 0
        }

        timed("getting single ram value in 512B") {
          address setTo 256
          ram.dataOut.toString mustBe "0000000000101010" // 42 in binary
        }
      }
    }
  }

  def withRam(registerWidth: Int, addressWidth: Int)
             (f: (AllSwitchBitList, AllSwitchBitList, Switch, RAM) => Unit) {
    val data = AllSwitchBitList(registerWidth)
    val address = AllSwitchBitList(addressWidth)
    val writeBit = Switch.off
    val ram = timed("creating ram") {FastRAM(data, address, writeBit)} //RAM!
    f(data, address, writeBit, ram)
  }

  // this assumes that the registers are at least as wide as the addresses!
  def testAllRamAddresses(data: AllSwitchBitList,
                          address: AllSwitchBitList,
                          writeBit: Switch, ram: RAM) {
    timed("setting all the data in the ram") {
      for (i <- 0 until ram.numberOfRegisters) {
        address setTo i
        writeBit.turnOn
        data setTo i
        writeBit.turnOff
      }
    }

    timed("getting all the data from the ram") {
      for (i <- 0 until ram.numberOfRegisters) {
        address setTo i
        data setTo i
        ram.dataOut.toString mustBe data.toString
      }
    }
  }

}