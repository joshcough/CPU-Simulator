package com.joshcough.cpu.memory

/**
 * @author dood
 * Date: Jun 6, 2009
 * Time: 7:11:24 PM
 */

object BitTable {
  
  private val gens: List[Boolean] = List(false, true)

  private lazy val two   = buildTable(2)
  private lazy val three = buildTable(3)
  private lazy val four  = buildTable(4)
  private lazy val five  = buildTable(5)

  def apply(nrBits: Int): List[List[Boolean]] = {
    nrBits match {
      case 2 => two
      case 3 => three
      case 4 => four
      case 5 => five
      case _ => buildTable(nrBits)
    }
  }

  def apply[T, U](nrBits: Int, trueFunction: Int => T, falseFunction: Int => U) = {
    buildTableWithIndeces(nrBits).map {
      _.map {case (b, i) => if (b) trueFunction(i) else falseFunction(i)}
    }
  }

  private def buildTableWithIndeces(nrBits: Int): List[List[(Boolean, Int)]] = {
    buildTable(nrBits).map(l => l.zipWithIndex)
  }

  private def buildTable(nrBits: Int): List[List[Boolean]] = {
    assert(nrBits >= 2)
    def inner(x: Int, xs: List[Boolean]): List[List[Boolean]] = {
      x match {
        case 2 => {gens flatMap {g2 => gens map {g3 => xs ::: List(g3, g2)}}}
        case _ => {gens flatMap {g => {inner(x - 1, xs ::: List(g))}}}
      }
    }
    inner(nrBits, Nil)
  }

}