package com.joshcough.cpu.electric

case class Wire(ins:PowerSource*) extends BasePowerSource(ins.toList){
  override def toString = "Wire(" + state + ")"
  def calculateNewState(inputs: List[PowerSource]) = inputs.exists(_.state == State.On)
}

object Inverter{
  def inverter(input: PowerSource) = new BasePowerSource(List(input)){
    def calculateNewState(inputs: List[PowerSource]) = ! input.state
  }
}
