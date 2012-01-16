package com.joshcough.cpu

/**
 *  Created by IntelliJ IDEA.
 *  User: joshcough
 *  Date: Dec 12, 2008
 *  Time: 10:04:16 AM
 *  To change this template use File | Settings | File Templates.
 */
class Sake {
  def task(f: => Unit): Task = new Task(f)

  def task(deps: Task*)(f: => Unit): Task = {
    new Task(deps.toList, f)
  }

  def file(ft: FileNameAndTasks)(f: => Unit): Task = {
    new Task(ft.tasks, f)
  }

  implicit def addToString(s: String): PimpedString = new PimpedString(s)
}

class PimpedString(s: String) {
  def ->(f: String, tasks: Task*): FileNameAndTasks = {
    new FileNameAndTasks(s, tasks.toList)
  }
}

case class FileNameAndTasks(name: String, tasks: List[Task])

class Task(deps: List[Task], f: => Unit) {
  def this(f: => Unit) = this (Nil, f)

  def apply(): Unit = {
    deps foreach (t => t())
    f
  }
}


object MySakeProject extends Sake {
  val compile = task{
    println("compile")
  }

  val test = task(compile){
    println("test")
  }

  val main = file("main.o" -> ("hey.txt", test)){
    println("main.o")
  }
}
