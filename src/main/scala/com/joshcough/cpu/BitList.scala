package com.joshcough.cpu

import electric.PowerSource

object BitList {
  def apply(powerSources: List[PowerSource]) = new BitList(powerSources)
  implicit def bitsToPowerSourceList(b: BitList): List[PowerSource] = b.powerSources
  implicit def powerSourceListToBits(p:List[PowerSource]): BitList = new BitList(p)
}

class BitList(val powerSources: List[PowerSource]){
  override def toString = {
    val buf = new StringBuffer
    powerSources.foreach(p => buf append (if(p.state) "1" else "0"))
    buf.reverse.toString
  }
  def toDecimal: Int = Integer.parseInt(this.toString, 2)
  def zip(ba:BitList) = powerSources.zip(ba.powerSources)
}