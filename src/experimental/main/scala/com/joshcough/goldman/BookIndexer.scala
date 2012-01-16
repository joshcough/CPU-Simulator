package com.joshcough.goldman

import java.io._

trait Book {
  def inputStream: InputStream
}

class TextFileBook(file: File) extends Book {
  def inputStream = new FileInputStream(file)
}

class StringBook(s: String) extends Book {
  def inputStream = new ByteArrayInputStream(s.getBytes)
}

case class Page(content: String, pageNumber: Int) {
  def words = content.split(" ")
}

trait Index {
  def add(word: String, page: Int)
  def print: Unit
}

case class MapBackedIndex extends Index {
  import scala.collection.mutable.Map

  val map = Map[String, List[Int]]()

  def add(word: String, page: Int) = {
    val list: List[Int] = map.getOrElse(word.toLowerCase, Nil)
    if (list.isEmpty || list.last != page) map.put(word.toLowerCase, list ::: List(page))
  }

  def print {
    map.keySet.toList.sort((e1, e2) => (e1.toLowerCase compareTo e2.toLowerCase) < 0).foreach{k => println(k + ": " + map(k))}
  }
}

trait PageReader {
  def nextPage: Option[Page]
}

class SimplePageReader(book: Book, linesPerPage: Int) extends PageReader {
  val reader = new BufferedReader(new InputStreamReader(book.inputStream))
  var finished = false
  var pageCount = 0

  def nextPage: Option[Page] = {
    if (finished) None
    else {
      pageCount = pageCount + 1
      Some(new Page(readNextNLines, pageCount))
    }
  }

  def readNextNLines: String = {
    def readLine: Option[String] = {
      val line = reader.readLine
      if (line != null) Some(line)
      else{ finished = true; None }
    }
    1.to(linesPerPage).map(i => readLine).foldLeft(""){
      case (total: String, line: Option[String]) => {
        line match {
          case Some(l) => total + "\n" + l
          case None => total
        }
      }
    }
  }
}

trait BookTraverser {
  def foreach(f: Page => Unit)
}

class SimpleBookTraverser(pageReader: PageReader) extends BookTraverser {
  def foreach(f: (Page) => Unit) {
    val page = pageReader.nextPage
    page match {
      case Some(page) => { f(page); foreach(f) }
      case None => {}
    }
  }
}

trait BookIndexer {
  def index(book: Book, linesPerPage: Int): Index
}

object SimpleBookIndexer extends BookIndexer {
  def index(book: Book, linesPerPage: Int) = {
    val bookTraverser = new SimpleBookTraverser(new SimplePageReader(book, linesPerPage))
    val index = new MapBackedIndex
    bookTraverser.foreach((p: Page) => p.words.foreach(w => index.add(w, p.pageNumber)))
    index
  }
}