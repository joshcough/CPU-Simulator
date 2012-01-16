package com.joshcough.cpu.electric

object Switch{
  def on = this(On)
  def off = this(Off)
  def apply(state:State) = new Switch(state)
}

class Switch( private var currentState: State ) extends BasePowerSource {

  override def state = currentState

  def turnOn  = switchIf( Off )
  def turnOff = switchIf( On )
  def flip    = switchIf(currentState)
    
  private def switchIf( s: State ): Switch = {
    if( currentState == s ) {
      currentState = currentState.switch
      notifyConnections
    }
    this
  }

  override def toString = "Switch(" + state + ")"

  def toInt = state.toInt
}