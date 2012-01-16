//package com.joshcough.scrabble
//
//import scala.io.Source
//
///**
// * @author dood
// * Date: Dec 27, 2008
// * Time: 7:17:17 PM
// */
//
//object TestDictionary {
//  val words = Source.fromFile("/usr/share/dict/words").getLines.toList.map(_.trim.toLowerCase)
//  val dictionary = Dictionary(words)
//  def apply() = dictionary
//}