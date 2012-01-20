package com.joshcough.cpu.electric

object State {
  val On = true
  val Off = false
  implicit def pimpedBoolean(b: Boolean) = new {
    def toInt = if(b) 1 else 0
  }
}

import State._

object Switch{
  def apply(state:Boolean) = new Switch(state)
}

class Switch(var state: Boolean) extends Outbound {

  def turnOn  = switchIf(state == Off)
  def turnOff = switchIf(state == On)
  def flip    = switchIf(true)
    
  private def switchIf(b: Boolean): Switch = {
    if(b) { state = ! state; notifyConnections() }
    this
  }

  override def toString = "Switch(" + state + ")"
  def toInt = (if(state) "1" else "0")
}