package com.joshcough.cpu.electric

object PowerSource{
  implicit def powerSourceToBoolean(that: PowerSource): Boolean = that.state
  implicit def whatever(that: Seq[List[PowerSource]]): Seq[PowerSource] = that.toSeq
}

/**
 * Basic building block for all CPU components.
 */
trait PowerSource {
  /**
   * On or Off
   */
  var state: Boolean

  /**
   * --> connect to the given PowerSource
   * The given PowerSource (that) should be notified
   * each time state changes in this (from On to Off, or Off to On)
   */
  def --> ( that: InboundPowerAcceptor ): PowerSource

  def notifyConnections(): Unit
}

trait InboundPowerAcceptor extends PowerSource {

  /**
   * <-- connect from the given PowerSource
   * that PowerSource will notify this PowerSource if its state has changed.
   * If that has changed, state may need to be updated for this as well.
   */
  def <-- ( that: PowerSource ): PowerSource

  def calculateNewState(inputs: List[PowerSource]): Boolean

  /**
   * The given PowerSource (that) has changed it state.
   * This might cause state to change in this.
   */
  def handleStateChanged( that: PowerSource )
}