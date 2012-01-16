//package com.joshcough.scrabble
//import org.testng.annotations.Test
//
//import search.Criteria._
//import WordMatchers._
//
///**
// * @author dood
// * Date: Dec 27, 2008
// * Time: 7:16:04 PM
// */
//
//class Cheat{
//
//  val dictionary = TestDictionary()
//
//  @Test def cheat {
//    val tray = "benduf"
//
//    val results = dictionary find_all (
//        //smart_includes_all(tray),
//        //includes_all(tray),
//        //min_length(6),
//        //min_length(7),
//        max_length(7),
//        //includes_exactly(2, 'e'),
//        //includes_at_most  (1, 'd'),
//        //starts_with("bendvuf"),
//        ends_with("lee")
//        //includes_at_least (2, 'e', 'o'),
//        //has_letter_at_postition('l', 4),
//        ) limit 10 desc
//
//    val results2 =
//    dictionary find (max_length(7) and ends_with("lee")) limit 10 desc
//
//
//    print(results)
//  }
//
//  def print(resultsSets: StringResultSet*) = resultsSets foreach println
//
//}