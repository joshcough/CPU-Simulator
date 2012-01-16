package com.joshcough.cpu

import electric.{Switch, PowerSource}

object BitsToList {
  implicit def bitsToPowerSourceList(b: BitList): List[PowerSource] = b.powerSources
  implicit def powerSourceListToBits(p:List[PowerSource]): BitList = new BitList(p)
}

case class BitList(val powerSources: List[PowerSource]){
  override def toString = {
    val buf = new StringBuffer
    powerSources.foreach(buf append _.state.toInt)
    buf.reverse.toString
  }
  def toDecimal: Int = Integer.parseInt(this.toString, 2)
}