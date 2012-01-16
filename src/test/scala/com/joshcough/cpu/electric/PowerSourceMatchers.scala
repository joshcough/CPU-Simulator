//package com.joshcough.cpu.alu
//
//import org.specs.matcher._
//import com.joshcough.cpu.electric._
//
//
//trait PowerSourceMatchers {
//  def beOn = new Matcher[PowerSource] {
//    def apply(p: => PowerSource) = (p, p + " is on", p + " is off")
//  }
//
//  def beOff = beOn.not
//}
