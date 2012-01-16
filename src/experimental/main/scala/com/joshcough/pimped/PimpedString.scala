package com.joshcough.pimped
/**
 * @author dood
 * Date: Dec 27, 2008
 * Time: 5:46:10 PM
 */

object PimpedString{
  implicit def pimpMyString(s:String) = new PimpedString(s)
  implicit def pimpMyListOfStrings( ss: List[String] ): List[PimpedString] = ss.map( new PimpedString(_) )
}

class PimpedString( s: String ) extends Ordered[String]{
  def compare( that: String ) = s compareTo that
}