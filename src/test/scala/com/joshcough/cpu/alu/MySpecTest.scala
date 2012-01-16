//package com.joshcough.cpu.alu;
//
//import org.specs.runner.JUnit3
//import org.specs._
//import org.specs.matcher._
//import scala.collection.immutable.HashSet
//import com.joshcough.cpu.electric._
//
//class MySpecTest extends JUnit3(mySpec)
//
//object mySpec extends Specification with PowerSourceMatchers{
//
//  "A Half Adder" should {
//    "have sum out off and carry out on when both inputs are on" in {
//      val ha: HalfAdder = HalfAdder(Switch.on, Switch.on)
//      ha.getSumOut must beOff
//      ha.getCarryOut must beOn
//    }
//  }
//
//
//}
//
