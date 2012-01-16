package com.joshcough.cpu.memory


import org.scalatest.FunSuite

/**
 * @author dood
 * Date: Jun 8, 2009
 * Time: 12:09:17 PM
 */

trait MemoryTests extends FunSuite with FlipFlopTest with DecoderTest with SelectorTest with RAMTest

class MemoryTestsClass extends MemoryTests