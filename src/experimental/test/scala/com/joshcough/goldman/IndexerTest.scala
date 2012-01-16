package com.joshcough.goldman

import _root_.org.testng.annotations.Test;

class IndexerTest{

  @Test def hey{

    def contents = "Hello. \n How are you? \n My name is Josh. \n What is your name? \n Oh, I like that name very much. It is very nice. \n Do you know what time it is? \n Do you know what color the sky is?"

    println("\n\n\n\n\n")
    val index = SimpleBookIndexer.index(new StringBook(contents), 2)
    print("index: " ); index.print
    println("\n\n\n\n\n")
    
  }
  
}