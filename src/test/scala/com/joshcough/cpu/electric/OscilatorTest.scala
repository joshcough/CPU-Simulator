//package com.joshcough.cpu.electric;
//
//import org.testng.annotations._
//import org.hamcrest.MatcherAssert._
//import org.hamcrest.Matchers._
//import org.easymock.EasyMock._
//
//class OscillatorTest {
//
//  @Test
//  def oscillate(){
//    //new Oscillator
//    //Thread sleep 5000
//    //ActorUser.sender.start
//  }
//}
//
//import scala.actors._
//import scala.actors.Actor._
//
//object ActorUser extends Application{
//
//  val sender = new Sender(
//    ActorsX.newReactor {
//      case x: Int => println( "Got int: " + x )
//      case msg => println( "Got message: " + msg )
//    },
//    ActorsX.newReactor {
//      case msg => println("eruhewiurhqweihu!!!!")
//    }
//  )
//
//  def normalReaction : PartialFunction[Any,Unit] = {
//    case x: Int => println( "Got int: " + x )
//    case msg => println( "Got message: " + msg )
//  }
//
//  def abnormalReaction : PartialFunction[Any,Unit] = {
//    case msg => println("eruhewiurhqweihu!!!!")
//  }
//}
//
//object ActorsX{
//  def newReactor( f: PartialFunction[Any,Unit] ): Actor = {
//    new Actor(){ def act() { loop{ react(f) } } }
//  }
//}
//
//class Sender( a: Actor, b: Actor ) extends Actor{
//  a.start
//  b.start
//
//  def act () {
//    for( i <- 1 to 5 ){
//      a ! "hey dood " + i
//      b ! "hey doodette " + i
//      a ! i
//      b ! i
//      Thread.sleep(500)
//    }
//  }
//}