package com.joshcough.test

/**
 * @author dood
 * Date: Jun 7, 2009
 * Time: 11:39:08 AM
 */

trait Timers {

  def timed[T](desc: String)(f: => T) = {
    val startTime = System.currentTimeMillis
    val res = f
    val endTime = System.currentTimeMillis
    println(desc + " took: " + (endTime - startTime) + " ms")
    res
  }
}