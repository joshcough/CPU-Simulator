package com.joshcough.cpu.electric

import org.scalatest.testng.TestNGSuite
import org.testng.annotations._
import org.testng.annotations.DataProvider
import com.joshcough.cpu.gates._
import pimped.Equalizer._

class ScalaTestNGExample extends TestNGSuite{

  @DataProvider{val name="generators"}
  def createSwitches = {
    val gens = Array(Switch.off, Switch(On))
    for( x <- gens; y <- gens ) yield Array(x,y)
  }

  @Test{ val dataProvider="generators" }
  def testAndGateStates(genA: Switch, genB: Switch){
    val and = AndGate(genA, genB)

    // you can "and" States together, just as if they were booleans.
    val whatItShouldBe: State = genA.state && genB.state

    and.state mustBe whatItShouldBe
    println( and.state + "==" + genA.state + "&&" + genB.state )
  }

  @Test{ val expectedExceptions = Array( classOf[Exception] ) }
  def simpleExceptionTest = throw new Exception()

  @BeforeMethod def printLineBefore = println("-----------entering method-----------")
  @AfterMethod  def printLineAfter  = println("-----------exiting  method-----------")

}

//@Test{ val invocationCount = 5, val enabled=false }
//def runMeFiveTimes = assertThat( true, is(true) )


//Array(Array(on, on),Array(on, off),Array(off, on),Array(off, off))

// leaving this here to show how terrible the equivalent java code is.... ouch

//public Switch[][] createSwitches() {
//
//  Switch[] onAndOff = Switch.on[]{
//    Switch( true }, Switch( false )
//  };

//  Switch[][] gensToReturn = Switch.on[4][2];
//  for( int i = 0; i<onAndOff.length; i++ ){
//    for( int j = 0; i<onAndOff.length; j++ ){
//      gensToReturn[i+j] = Switch.on[]{ onAndOff[i], onAndOff[j] };
//    }
//  }
//  return gensToReturn;
//}
