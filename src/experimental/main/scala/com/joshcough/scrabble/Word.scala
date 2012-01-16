package com.joshcough.scrabble

import search.Criteria
import search.Criteria._

/**
 * @author dood
 * Date: Dec 22, 2008
 * Time: 3:12:35 PM
 */
case class Word(s: String) {
  def apply(criteria: Criteria[String]): Boolean = criteria(s)
}

object WordMatchers {

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>only</i> the characters in the given String, s.
   * @param s
   */
  def includes_only(s: String) = includes_none(inverse(s))

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>none</i> of the characters in the given String, s.
   * @param s
   */
  def includes_none(s: String) = (t: String) => fold(s){!t.contains(_)}

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>all</i> of the characters in the given String, s.
   * @param s
   */
  def includes_all(s: String) = (t: String) => fold(s){t contains _}


  /**
   * Returns a function that seaches for words that contain exactly the letters in the given tray.
   *
   * If the tray is "veeelta", then only words containing exactly those letters will be returned.
   *
   * For example, 'elevate' would be returned. Howerver, 'elevatee' (a thing being elevated?)
   * would not be returned, because the given tray does not contain the 4th 'e'. Also, 'ate' would
   * not be returned because 'ate' does not contain 3 e's, or a 'v'.
   *
   * The algorithm words like this:
   *
   * For each letter in the "tray"
   *   count how many times that letter occurs in the tray, and remeber it.
   *
   * Example: (v,1)(e,3)(a,1)
   *
   * Then for each word in the dictionary
   *   check to see if the word contains those letters,
   *   exactly how many times they occurred in the tray.
   *
   * @param s
   */
  def smart_includes_all(tray: String) = {
    length(tray.length) and ((t: String) => get_chars_with_counts(tray).foldLeft(true){
      case (b, (c, n)) => b && includes_exactly(n, c)(t)
    })
    // new Criteria(includes_exact_char_counts(get_chars_with_counts(tray):_*))
  }

  //  def includes_exact_char_counts(cis: Tuple2[Char, Int]*) = {
  //    (t: String) => cis.foldLeft( new Criteria((s:String) => true) ){
  //      case ((criteria), (c,n)) => criteria and includes_exactly (n,c)
  //    }
  //  }
  

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>any</i> of the characters in the given String, s.
   * @param s
   */
  def includes_any(s: String) = (t: String) => fold(false, s){t contains _}

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>at least n</i> of the given character, c.
   *
   * @param c the character to search for
   * @param n the least number of c's that must exist in the String, t.
   */
  def includes_at_least(n: Int, cs: Char*) = (t: String) => fold(cs: _*){count(_, t) >= n}

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>at most 'n'</i> of the given character, c.
   *
   * @param c the character to search for
   * @param n the maximum number of c's that must exist in the String, t.
   */
  def includes_at_most(n: Int, cs: Char*) = (t: String) => fold(cs: _*){count(_, t) <= n}

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>exactly 'n'</i> of the given character, c.
   *
   * @param c the character to search for
   * @param n the maximum number of c's that must exist in the String, t.
   */
  def includes_exactly(n: Int, cs: Char*) = (t: String) => fold(cs: _*){count(_, t) == n}

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains the given character, c, at at the given position in t.
   * This is 1 based, not zero based, as most humans don't think like machines.
   *
   * @param c the character to search for
   * @param n the maximum number of c's that must exist in the String, t.
   */
  def has_letter_at_postition(c: Char, pos: Int) = {
    (t: String) => (t.length >= pos) && (t.charAt(pos - 1) == c)
  }

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>all</i> of the characters in the given String, s, <i>in order</i>.
   * @param s
   */
  def includes_phrase(s: String) = (t: String) => t.indexOf(s) > -1

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>starts with</i> the given String, s.
   * @param s
   */
  def starts_with(s: String) = (t: String) => t startsWith s

  /**
   * Returns a function that takes a String, t, which returns true
   * if t contains <i>ends</i> the given String, s, <i>in order</i>.
   * @param s
   */
  def ends_with(s: String) = (t: String) => t endsWith s

  /**
   * Returns a function that takes a String, t, which returns true
   * if length of t is equal to the given Int, len.
   * @param s
   */
  def length(len: Int) = (s: String) => s.length == len

  /**
   * Returns a function that takes a String, t, which returns true
   * if length of t is equal to or less than the given Int, max.
   * @param s
   */
  def max_length(max: Int) = (s: String) => s.length <= max

  /**
   * Returns a function that takes a String, t, which returns true
   * if length of t is equal to or greater than the given Int, min.
   * @param s
   */
  def min_length(min: Int) = (s: String) => s.length >= min


  /********************
   * Utility Functions!
   ********************/

  /**
   *
   */
  private def inverse(s: String): String = 'a'.to('z').filter(!s.contains(_)).mkString("")

  /**
   *
   */
  private def count(c: Char, s: String) = s.filter(_ == c).size

  /**
   *
   */
  private def get_chars_with_counts(s: String) = s.map(c => (c, count(c, s)))

  /**
   *
   */
  private def fold(s: String)(f: Char => Boolean): Boolean = fold(true, s){f}

  /**
   *
   */
  private def fold(cs: Char*)(f: Char => Boolean): Boolean = fold(true, cs: _*){f}

  /**
   *
   */
  private def fold(start: Boolean, cs: Char*)(f: Char => Boolean): Boolean = {
    cs.foldLeft(start)((b, c) => b && f(c))
  }

  /**
   *
   */
  private def fold(start: Boolean, s: String)(f: Char => Boolean): Boolean = {
    s.foldLeft(start)((b, c) => b && f(c))
  }
}

