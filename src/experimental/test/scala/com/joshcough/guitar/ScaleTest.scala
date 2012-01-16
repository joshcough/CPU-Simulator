//package com.joshcough.guitar
//
//import org.testng.annotations.Test
//import pimped.Equalizer._
//import Music._
//import Scale._
//import ScaleMatchers._
//import com.joshcough.search.Criteria._
//import com.joshcough.search.{Asc, Desc}
//
///**
// * @author dood
// * Date: Dec 25, 2008
// * Time: 12:14:59 AM
// */
//class ScaleTest {
//
//  @Test def matchers {
//    val scales = Scale find
//        (contains (
//          Chord("C#", Minor)) or contains(Chord("A", Major))) limit 4 orderBy (rootChord, Desc)
//    scales foreach println
//  }
//
//
////   // 1 2m 3m 4 5 6m 7dim 8
////  @Test def G_major_scale_test {
////    // G Am Bm C D Em F#dim G
////
//////    val real_g_major = List(("G", Major),
//////        ("A", Minor),
//////        ("B", Minor),
//////        ("C", Major),
//////        ("D", Major),
//////        ("E", Minor),
//////        ("F#", Dim))
////
////   // val built_g_major = Scale( "G", MajorScale )
////
////    //built_g_major mustBe real_g_major
////
//////    println( "real: " + built_g_major )
////
////    Scale.all.foreach( println )
////  }
////
//
//}
