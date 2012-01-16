package com.joshcough.guitar

import Music._
import search._

/**
 * @author dood
 * Date: Dec 27, 2008
 * Time: 7:12:14 PM
 */
object Scale extends Search[Scale]{
  def all = notes.map(MajorScale(_)) ::: notes.map(MinorScale(_))
  def rootChord( s: Scale ) = s.rootChord
}

object ScaleMatchers {
  def contains(c: Chord) = (s: Scale) => s contains c
}

case class MajorScale(note: Note) extends Scale(note, MajorScaleProgression)
case class MinorScale(note: Note) extends Scale(note, MinorScaleProgression)

class Scale(val root: Note, progression: ScaleProgression) extends Ordered[Scale] {
  def apply() = chords
  def contains(c: Chord) = this() contains c
  def compare(s: Scale) = root compareTo s.root

  def rootChord = this()(0)

  private def chords = {
    val notes_from_root = reorder_notes(root, notes)
    progression().foldLeft((0, List[Chord]())){
      case ((count, list), (step, chord_type)) => {
        (count + step, list ::: List(new Chord(notes_from_root(count + step), chord_type)))
      }
    }._2
  }

  private def reorder_notes(startNote: Note, notes: List[Note]) = start_at(notes.indexOf(startNote), notes)
  private def start_at[T](index: Int, l: List[T]): List[T] = {val (a, b) = l.splitAt(index); b ::: a}
  override def toString = chords.toString
}
