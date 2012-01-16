package com.joshcough.cpu


import alu.ALUTests
import electric.ElectricTests
import memory.MemoryTests
import gates.LogicGateTests

/**
 * @author dood
 * Date: Jun 8, 2009
 * Time: 12:11:35 PM
 */
trait CPUTests extends ElectricTests with LogicGateTests with MemoryTests with ALUTests

class CPUTestsClass extends CPUTests