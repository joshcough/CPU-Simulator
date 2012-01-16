/*
 * Created by IntelliJ IDEA.
 * User: joshcough
 * Date: May 31, 2009
 * Time: 9:01:58 AM
 */
package com.joshcough.cpu.memory

import com.joshcough.cpu.electric.PowerSource
import com.joshcough.cpu.BitList
import com.joshcough.cpu.BitsToList._

case class Register(data: BitList, writeBit: PowerSource) {
  val flipFlops = data.map( FlipFlop(_, writeBit ) )
  val output = BitList(flipFlops.map(_.output))
}
