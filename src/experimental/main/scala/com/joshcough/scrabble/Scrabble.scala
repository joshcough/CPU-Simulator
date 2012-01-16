package com.joshcough.scrabble

import _root_.com.joshcough.search.Criteria
import search.Criteria._


/**
 * @author dood
 * Date: Dec 21, 2008
 * Time: 11:24:22 AM
 */
case class StringResultSet( data: List[String] ){
  def limit( n: Int ) = StringResultSet(data.take(n))
  //def orderBy( s: String ) = StringResultSet(data)
  def asc =  StringResultSet(data.sort((a,b) => wtf( a, b )))
  def desc = StringResultSet(data.sort((a,b) => wtf( b, a )))
  def wtf(a: String, b: String) = a.toString.compareTo(b.toString) <= 0
}

case class Dictionary( words: List[String] ) {
  def apply(criteria: Criteria[String]) = StringResultSet(words.filter(criteria(_)))
  def find = apply _; def filter = apply _; def search = apply _;
  def find_all(criteria: Criteria[String]*) = apply(join(criteria:_*))
  private def true_criteria = new Criteria((s:String) => true)
  private def join(criteria: Criteria[String]*) = criteria.foldLeft(true_criteria){ _ and _ }
}

