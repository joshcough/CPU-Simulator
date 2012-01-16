package com.joshcough.cpu.electric

/**
 * @author dood
 * Date: Jun 8, 2009
 * Time: 12:07:44 PM
 */


import org.scalatest.FunSuite

/**
 * @author dood
 * Date: Jun 8, 2009
 * Time: 12:05:15 PM
 */
trait ElectricTests extends FunSuite with
    SwitchTest with InverterTest with RelayTest with WireTest

class ElectricTestsClass extends ElectricTests