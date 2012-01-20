/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: Jun 6, 2009
 * Time: 8:40:14 PM
 */
package com.joshcough.cpu.memory

import org.scalatest.FunSuite
import com.joshcough.cpu.BitList._
import com.joshcough.pimped.Equalizer._
import com.joshcough.cpu.electric.Switch
import com.joshcough.cpu.electric.State._
import com.joshcough.cpu.{BitList, AllSwitchBitList}


trait DecoderTest extends FunSuite{
  
  test("decoder"){
    val data = AllSwitchBitList(8)
    val address = AllSwitchBitList(2)
    val writeBit = Switch(On)
    val decoder = Decoder(data, address, writeBit)

    data.flip(0,7)
    //decoder.dump
    BitList(decoder.output(0)).toString mustBe "10000001"

    address.setTo(1)
    //decoder.dump
    BitList(decoder.output(1)).toString mustBe "10000001"

    data.flip(0,7,3,4)
    address.setTo(3)
    //decoder.dump
    BitList(decoder.output(3)).toString mustBe "00011000"

  }
}