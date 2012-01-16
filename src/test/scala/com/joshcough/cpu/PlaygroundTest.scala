//package com.joshcough.cpu
//
//import com.joshcough.cpu.electric._
//import com.joshcough.cpu.gates._
//import com.joshcough.cpu.alu._
//import org.testng.annotations._
//import pimped.Equalizer._
//
//class PlaygroundTest {
//
//
////  @Test
////  def test_null_in_list {
////    println(List(1, null, 2, null, 3, null))
////    println(List(1, null, 2, null, 3, null).compact)
////
////    println("name: " + Name(Some("Josh"), None, Some("Cough")).pretty)
////  }
////
////  class Compactable[T](l: List[T]) {
////    def compact = {
////      l.filter(_ match {case Some(x) => true; case _ => false})
////    }
////  }
////
////  implicit def compactableList[T](l: List[T]) = new {
////    def compact = l.filter(_ != null)
////    // l.filter(_ match {case Some(x) => true; case _ => false})
////  }
////
////
////  case class Name(f: Option[String], m: Option[String], l: Option[String]) {
////    def pretty: String = {
////      List(f, m, l).compact.map(o => o.get).mkString(" ")
////    }
////  }
//
//  //  @Test{ val enabled=false }
//  //  def test_mustBeWithTuples = {
//  //    val x = 5
//  //    val y = 6
//  //
//  //    x mustBe 5
//  //    y mustBe 6
//  //
//  //    val xy = ((x,y))
//  //
//  //    xy mustBe 6
//  //  }
//
//}
