package com.joshcough.cpu.electric

import scala.collection.mutable.HashSet

trait BasePowerSource extends PowerSource {
  
  private val incomingPowerSources = new HashSet[PowerSource]
  private val outgoingPowerConnections = new HashSet[PowerSource]

  private var currentState: State = Off
  def state = currentState

  def -->(p: PowerSource) = connect(p, outgoingPowerConnections){p <-- this}
  def <--(p: PowerSource) = connect(p, incomingPowerSources){updateOnOff}

  def disconnectFrom(p: PowerSource) = disconnect(p, outgoingPowerConnections){p disconnectedFrom this}
  def disconnectedFrom(p: PowerSource) = disconnect(p, incomingPowerSources){updateOnOff}

  private def connect(p: PowerSource, sources: HashSet[PowerSource])(f: => Unit): PowerSource = {
    if (!(sources contains p)) {sources += p; f}
    p
  }

  private def disconnect(p: PowerSource, sources: HashSet[PowerSource])(f: => Unit): PowerSource = {
    if (sources contains p) {sources -= p; f}
    p
  }

  def notifyConnections = outgoingPowerConnections.foreach(p => p.handleStateChanged(this))
  def handleStateChanged(p: PowerSource) = updateOnOff

  private def updateOnOff = {
    def shouldSwitch = currentState() != isAnyIncomingPowerSourceOn
    def isAnyIncomingPowerSourceOn = incomingPowerSources.find(_.state == On).isDefined

    if (shouldSwitch) {
      currentState = currentState.switch
      notifyConnections
    }
  }
}