package com.joshcough.cpu.gates

import electric._

object LogicGate{
  implicit def logicGateToPowerSource( lg: LogicGate ): PowerSource = lg.output
}

trait LogicGate{
  val output: PowerSource
}

case class AndGate(inputs: PowerSource*) extends LogicGate {
  def this(inputs:List[PowerSource]) = this(inputs:_*)
  val output: PowerSource = inputs.foldLeft(Relay(Switch.on)){ (a, b) => Relay(a, b) }
}

case class NandGate(inputs: PowerSource*) extends LogicGate{
  val output = new Wire
  inputs.foreach(p => Inverter(p)-->output)
}

case class NorGate(inputs: PowerSource*) extends LogicGate{
  val output: PowerSource = Inverter( OrGate(inputs:_*) )
}

case class OrGate(inputs: PowerSource*) extends LogicGate{
  val output = new Wire
  inputs.foreach(_ --> output)
}

case class XorGate(a: PowerSource, b: PowerSource) extends LogicGate {
  val output: PowerSource = AndGate(OrGate(a, b), NandGate(a, b))
}