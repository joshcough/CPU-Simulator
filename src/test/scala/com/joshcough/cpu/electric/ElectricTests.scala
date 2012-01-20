package com.joshcough.cpu.electric

import org.scalatest.FunSuite

/**
 * @author dood
 * Date: Jun 8, 2009
 * Time: 12:05:15 PM
 */
trait ElectricTests extends FunSuite with SwitchTest with InverterTest with WireTest

class ElectricTestsClass extends ElectricTests