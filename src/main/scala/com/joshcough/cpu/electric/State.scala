package com.joshcough.cpu.electric

case object On extends State(true)
case object Off extends State(false)

sealed abstract class State( on: Boolean ){
  def apply(): Boolean = on
  def toInt: Int = if( on ) 1 else 0
  def &&( that: State ): State = if( this() && that() ) On else Off
  def switch: State = if( on ) Off else On
}