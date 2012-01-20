package com.joshcough.cpu.electric

import collection.mutable.ListBuffer

trait Outbound extends PowerSource {
  val outputs = ListBuffer[InboundPowerAcceptor]()
  def -->(p: InboundPowerAcceptor) = {
    if (!(outputs contains p)) { outputs += p; p <-- this }
    p
  }
  def notifyConnections() { outputs.foreach(_.handleStateChanged(this)) }
}

abstract class BasePowerSource(val initialInputs:List[PowerSource])
  extends InboundPowerAcceptor with Outbound {

  val inputs: ListBuffer[PowerSource] = ListBuffer(initialInputs:_*)
  inputs.foreach(_-->this)
  var state: Boolean = calculateNewState(inputs.toList)

  def calculateNewState(inputs: List[PowerSource]): Boolean

  def <--(p: PowerSource) = {
    if (!(inputs contains p)) { inputs += p; updateOnOff() }
    p
  }

  def handleStateChanged(p: PowerSource){ updateOnOff() }

  def updateOnOff() {
    val newState = calculateNewState(inputs.toList)
    if (state != newState) {
      state = newState
      notifyConnections()
    }
  }
}