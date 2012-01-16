//package com.joshcough.scrabble
//
//import search.Criteria._
//import WordMatchers._
//import org.testng.annotations.Test
//
//
///**
// * <ul>
// * <li>Find words that contain ALL  the letters in my tray. (but maybe some other letters)</li>
// * <li>Find words that contain ONLY the letters in my tray. (no other letters allowed)</li>
// * <li>Find words that contain ANY  of the letters in my tray. (other letters allowed)</li>
// * <li>Find words that contain ALL of the letters in my tray, and ONLY the letters in my tray.</li>
// * </ul>
// */
//class ScrabbleTest {
//
//  val dictionary = TestDictionary()
//
//  @Test def tests {
//
//    val tray = "defilnr"
//
//    // the 4 original requirements
//    val all = dictionary find includes_all(tray)
//    val any = dictionary find includes_any(tray)
//    val only = dictionary find includes_only(tray)
//    val all_and_only = dictionary find (includes_all(tray) and includes_only(tray))
//    print(all, only, all_and_only)
//
//
//
//    // additional cool things (use of Dictionary.apply and Criteria's "||" method )
//    val all_max_7 = dictionary(includes_all(tray) and max_length(7))
//    val all_or_min_10 = dictionary(includes_all(tray) or max_length(7))
//    print(all_max_7, all_or_min_10)
//
//
//
//    // more things (use of Dictionary.filter, starts_with, ends_with, max_length, min_length)
//    val xin_or_max_4 = dictionary search (starts_with("xin") or max_length(4))
//    val van_or_min_4 = dictionary search (ends_with("van") and min_length(4))
//    print(xin_or_max_4, van_or_min_4)
//
//
//
//    // more complex query
//    val complexQuery =
//    (
//        (starts_with("d") and ends_with("ing")) or
//            (starts_with("b") and ends_with("ed"))
//        ) and max_length(7) and min_length(4)
//
//    var result = dictionary find complexQuery
//    print(result)
//
//
//
//    // hmmm, but i didnt like my result...how about i modify my query...
//    val complexQueryWithoutAs = complexQuery and includes_none("a")
//    result = dictionary find complexQueryWithoutAs
//    print(result)
//
//    //val c_and_not_d = ((s: String) => s.includes("c")) and ((s: String) => !s.includes("d"))
//    //println(dictionary find c_and_not_d)
//
//  }
//
//  def print(resultsSets: StringResultSet*) = resultsSets foreach println
//}