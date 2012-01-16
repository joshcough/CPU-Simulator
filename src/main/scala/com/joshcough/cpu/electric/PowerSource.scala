package com.joshcough.cpu.electric

/**
 * Basic building block for all CPU components.
 */
trait PowerSource{

  /**
   * On or Off
   */
  def state: State

  /**
   * The given PowerSource (that) has changed it state.
   * This might cause state to change in this.
   */
  def handleStateChanged( that: PowerSource )

  /**
   * Notify all outbound connections that state has changed.
   */
  def notifyConnections

  /**
   * --> connect to the given PowerSource
   * The given PowerSource (that) should be notified
   * each time state changes in this (from On to Off, or Off to On)
   */
  def --> ( that: PowerSource ): PowerSource

  /**
   * <-- connect from the given PowerSource
   * that PowerSource will notify this PowerSource if its state has changed.
   * If that has changed, state may need to be updated for this as well.
   */
  def <-- ( that: PowerSource ): PowerSource

  /**
   * No longer connected to that.
   * No longer giving that updates on state changes.
   */
  def disconnectFrom ( that: PowerSource ): PowerSource

  /**
   * No longer receiving state changes from that.
   * that no longer has any effect on the state of this.
   */
  def disconnectedFrom ( that: PowerSource ): PowerSource
}

object PowerSource{
  implicit def powerSourceToBoolean( that: PowerSource ): Boolean = that.state()
}