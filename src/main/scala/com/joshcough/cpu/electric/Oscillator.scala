//package com.joshcough.cpu.electric
//
//import scala.actors._
//import scala.actors.Actor._
//
//
//object Oscillator{
//  implicit def toPowerSource( o: Oscillator ): PowerSource = o.output
//}
//
//object Actors{
//  def newReactor( f: PartialFunction[Any,Unit] ): Actor = {
//    new Actor(){ def act() { loop{ react(f) } } }
//  }
////  def newReactor( f: => Unit ): Actor = {
////    new Actor(){ def act() { loop{ react{ case msg => f } } } }
////  }
//}
//
//class Oscillator {
//
//  val actor = Actors.newReactor{ case p: PowerSource => updateOutputWire( p ) }
//  actor.start
//
//  private val inputToSwitch,output = (Switch.on, new Wire)
//
//  private var ironBar = {
//    val ironBar = new IronBar
//    output --> ironBar
//    updateOutputWire( output )
//    ironBar
//  }
//
//  var onCount, offCount = 0
//
//  def updateOutputWire( source: PowerSource ){
//    if( source ){ 
//      println( "on count = " + (onCount+=1) + " " + this)
//      inputToSwitch --> output
//    }
//    else{
//      println( "off count = " + (offCount+=1) + " " + this )
//      inputToSwitch disconnectFrom output
//    }
//  }
//
//  class IronBar extends BasePowerSource{
//    override def handleStateChanged( p: PowerSource ) = actor ! p
//  }
//}
