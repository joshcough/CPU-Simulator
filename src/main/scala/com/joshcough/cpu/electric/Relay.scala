package com.joshcough.cpu.electric

object Relay{
  def apply(inputToMagnet: PowerSource, inputToSwitch: PowerSource):Relay = {
    new Relay(inputToMagnet, inputToSwitch)
  }
  def apply(inputToMagnet: PowerSource):Relay = this(inputToMagnet, Switch.on)
}

class Relay(inputToMagnet: PowerSource, inputToSwitch: PowerSource) extends
  Electromagnet(inputToMagnet, inputToSwitch){
  def isSwitchConnectedToMagnet( source: PowerSource ) : Boolean = source
}

object Inverter{
  def apply(inputToMagnet: PowerSource, inputToSwitch: PowerSource):Inverter = {
    new Inverter(inputToMagnet, inputToSwitch)
  }
  def apply(inputToMagnet: PowerSource):Inverter = this(inputToMagnet, Switch.on)
}

class Inverter(inputToMagnet: PowerSource, inputToSwitch: PowerSource) extends
  Electromagnet(inputToMagnet, inputToSwitch){
  def isSwitchConnectedToMagnet( source: PowerSource ) : Boolean = ! source
}

object Electromagnet{
  implicit def switchToPowerSource( s: Electromagnet ): PowerSource = s.output
}

sealed abstract class Electromagnet(inputToMagnet: PowerSource,
                                    inputToSwitch: PowerSource){

  def isSwitchConnectedToMagnet( source: PowerSource ): Boolean

  private val output = new Wire
  private val ironBar = inputToMagnet --> new IronBar
  updateOutputWire( inputToMagnet )

  override def toString =
    super.toString + "(inputToSwitch["+inputToSwitch.state+"] " +
                      "inputToMagnet=["+inputToMagnet.state+"])"

  def updateOutputWire( source: PowerSource ){
    if( isSwitchConnectedToMagnet(source) ){ inputToSwitch --> output }
    else{ inputToSwitch disconnectFrom output }
  }

  case class IronBar() extends BasePowerSource{
    override def handleStateChanged( p: PowerSource ) = updateOutputWire( p )        
  }
}