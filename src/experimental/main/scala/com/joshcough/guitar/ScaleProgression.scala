package com.joshcough.guitar

import Music._

/**
 * @author dood
 * Date: Dec 27, 2008
 * Time: 7:12:14 PM
 */

object MajorScaleProgression extends ScaleProgression {val steps_from_major = 0}
object MinorScaleProgression extends ScaleProgression {val steps_from_major = 5}

trait ScaleProgression {
  type Step = Int

  val steps_from_major: Int

  val major_chords = List(Major, Minor, Minor, Major, Major, Minor, Dim)
  val major_steps = List(2, 2, 1, 2, 2, 2, 1)

  def apply() = build

  private def build: List[(Step, ChordType)] = {
    val steps = (List(0) ::: (start_at(steps_from_major, major_steps)))
    val chords = start_at(steps_from_major, major_chords)
    return steps zip chords
  }

  private def reorder_notes(startNote: Note, notes: List[Note]) = start_at(notes.indexOf(startNote), notes)

  private def start_at[T](index: Int, l: List[T]): List[T] = {val (a, b) = l.splitAt(index); b ::: a}
}
