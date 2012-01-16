package com.joshcough.cpu.alu

import com.joshcough.cpu.electric._
import com.joshcough.cpu.gates._
import com.joshcough.cpu.alu._
import org.scalatest.FunSuite
import org.testng.annotations._
import pimped.Equalizer._

trait NBitAdderTest extends FunSuite{

  test("All NBitAdder Combos With Carry In On"){ for( i <- 2 until 6 ) testAllCombos(i, On) }
  test("All NBitAdder Combos With Carry In Off"){ for( i <- 2 until 6 ) testAllCombos(i, Off) }

  def testAllCombos(numOfBits: Int, carryInBit: State) = {

    val pow = Math.pow(2, numOfBits).toInt
    val zeros = (1 until numOfBits - 1).foldRight("0"){(s, c) => c + "0"}

    val a = AllSwitchBitList(numOfBits)
    val b = AllSwitchBitList(numOfBits)

    val adder = AdderChain(a, b, Switch(carryInBit))

    for (i <- 0 until pow) {
      a setTo i
      for (j <- 0 until pow) {
        b setTo j
        val actual = adder.getCarryOut.state.toInt + "" + adder.getOutput
        val expected = AllSwitchBitList.padBinaryString(Integer.toBinaryString(i+j+carryInBit.toInt), numOfBits +1 )
        //println(a + " + " + b + " + " + zeros + carryInBit.toInt + " = " + actual + "    expected: " + expected)
        actual mustBe expected
      }
    }
  }
}

