package com.joshcough.guitar

import Music.Note

/**
 * @author dood
 * Date: Dec 27, 2008
 * Time: 7:21:00 PM
 */

case class Chord( n: Note, chordType: ChordType ) extends Ordered[Chord]{
  def compare( that: Chord ) = n compareTo that.n
}

trait ChordType
case object Major extends ChordType
case object Minor extends ChordType
case object Dim extends ChordType