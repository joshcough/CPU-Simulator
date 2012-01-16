package com.joshcough.cpu.memory

import cpu.electric._
import cpu.gates._

case class FlipFlop( dataBit: PowerSource, writeBit: PowerSource ) {

  private val inverter = Inverter( dataBit )
  private val invertedDataAnd = AndGate( inverter, writeBit )
  private val dataAnd = AndGate( dataBit, writeBit )

  private val outputWire = new Wire
  private val dataNor = NorGate( dataAnd, outputWire )
  private val invertedDataNor = NorGate( invertedDataAnd, dataNor )

  invertedDataNor --> outputWire

  def state = outputWire.state

  val output: PowerSource = outputWire

  def dump = {
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
private val invertedDataAnd = AndGate()
private val dataAnd = AndGate()
private val outputWire = new Wire()
private val dataNor = NorGate()
private val invertedDataNor = NorGate()


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

