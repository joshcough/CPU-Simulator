package com.joshcough.cpu.gates


import org.scalatest.FunSuite

/**
 * @author dood
 * Date: Jun 8, 2009
 * Time: 12:05:15 PM
 */

trait LogicGateTests extends FunSuite with
    AndGateTest with NandGateTest with NorGateTest with OrGateTest with XorGateTest

class LogicGateTestsClass extends LogicGateTests