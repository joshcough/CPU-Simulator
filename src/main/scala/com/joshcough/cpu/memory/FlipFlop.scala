package com.joshcough.cpu.memory

import com.joshcough.cpu.electric.{Inverter, Wire, PowerSource}
import com.joshcough.cpu.gates.LogicGate._

case class FlipFlop( dataBit: PowerSource, writeBit: PowerSource ) {

  private val inverter = Inverter.inverter( dataBit )
  private val invertedDataAnd = and( inverter, writeBit )
  private val dataAnd = and( dataBit, writeBit )

  private val outputWire = new Wire
  private val dataNor = nor( dataAnd, outputWire )
  private val invertedDataNor = nor( invertedDataAnd, dataNor )

  invertedDataNor --> outputWire

  def state = outputWire.state

  val output: PowerSource = outputWire

  def dump(){
    println( "databit: " + dataBit.state )
    println( "writeBit: " + writeBit.state )
    println( "inverter: " + inverter.state )
    println( "invertedDataAnd: " + invertedDataAnd.state )
    println( "dataAnd: " + dataAnd.state )
    println( "dataNor: " + dataNor.state )
    println( "invertedDataNor: " + invertedDataNor.state )
    println( "outputWire: " + outputWire.state )
  }
}

/**
 *


private val inverter = Inverter
dataBit --> Inverter

private val invertedDataAnd = AndGate
inverter --> invertedDataAnd
writeBit --> invertedDataAnd

private val dataAnd = AndGate
dataBit  --> dataAnd
writeBit --> dataAnd

private val outputWire = new Wire
private val dataNor = NorGate
dataAnd    --> dataNor
outputWire --> dataNode

private val invertedDataNor = NorGate
invertedDataAnd --> invertedDataNor
dataNor --> invertedDataNor

invertedDataNor --> outputWire



 */

/**
 *


private val inverter = Inverter()
private val invertedDataAnd = and()
private val dataAnd = and()
private val outputWire = new Wire()
private val dataNor = nor()
private val invertedDataNor = nor()


dataBit --> Inverter

inverter --> invertedDataAnd
writeBit --> invertedDataAnd

dataBit  --> dataAnd
writeBit --> dataAnd

dataAnd    --> dataNor
outputWire --> dataNor

invertedDataAnd --> invertedDataNor
dataNor --> invertedDataNor

invertedDataNor --> outputWire


 */

