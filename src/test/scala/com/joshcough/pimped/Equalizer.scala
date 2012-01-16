package com.joshcough.pimped

import scala.xml.{Elem, PrettyPrinter}
import org.testng.Assert._

case object Equalizer {
  implicit def anyToCompare(a: Any) = new Equalizer(a)
  implicit def xmlComparator(x: Elem) = new XmlEqualizer(x)
}

/**
 *
 * User: Dood
 * Date: Nov 17, 2008
 * Time: 7:49:30 PM
 */
class Equalizer(a: Any) {

  def mustBe(bs: Any*): Unit = {
    bs(0) match {
      case x:MyTuple => x mustBe a
      case _ => {
        val message = "In Equalizer: expecting one of=" + bs + "\nIn Equalizer: actual  =" + a
        //println(message)
        bs size match {
          case 1 => assertEquals(a, bs(0), message)
          case _ => assertTrue(bs.contains(a), message)
        }
      }
    }
  }

  def canBeNullOr(bs: Any*) = {
    if (a != null) mustBe(bs: _*)
    else println("In Equalizer: expecting one of=" + bs + " or null\nIn Equalizer: actual  =" + a)
  }

  def cantBe(b: Any) = assertFalse(a == b)

  def is(b: Any) = a equals b

  def or(b: Any) = {
    a match {
      case x:MyTuple => x + b
      case _ => MyTuple2(a, b)
    }
  }

  import Equalizer._

  trait MyTuple{
    def +(b: Any): MyTuple
    def mustBe(a: Any): Unit
  }
  case class MyTuple2(y: Any, z: Any) extends MyTuple{
    def +(b: Any): MyTuple = MyTuple3(y, z, b)
    def mustBe(a: Any): Unit = a mustBe (y,z)
  }
  case class MyTuple3(x: Any, y: Any, z: Any) extends MyTuple{
    def +(b: Any): MyTuple = MyTuple4(x, y, z, b)
    def mustBe(a: Any): Unit = a mustBe (x,y,z)
  }
  case class MyTuple4(w: Any, x: Any, y: Any, z: Any) extends MyTuple{
    def +(b: Any): MyTuple = MyTuple5(w, x, y, z, b)
    def mustBe(a: Any): Unit = a mustBe (w,x,y,z)
  }
  case class MyTuple5(v: Any, w: Any, x: Any, y: Any, z: Any) extends MyTuple{
    def +(b: Any): MyTuple = MyTuple6(v, w, x, y, z, b)
    def mustBe(a: Any): Unit = a mustBe (v,w,x,y,z)
  }
  case class MyTuple6(u: Any, v: Any, w: Any, x: Any, y: Any, z: Any) extends MyTuple{
    def +(b: Any): MyTuple = throw new IllegalArgumentException("too many ors")
    def mustBe(a: Any): Unit = a mustBe (u,v,w,x,y,z)
  }
}

class XmlEqualizer(ax: Elem) {
  def mustBe(bx: Elem) = {
    assertEquals(
      new PrettyPrinter(120, 3).formatNodes(ax),
      new PrettyPrinter(120, 3).formatNodes(bx)
      )
  }
}
