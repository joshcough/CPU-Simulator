package com.joshcough.search

/**
 * @author dood
 * Date: Dec 21, 2008
 * Time: 10:09:24 PM
 */
object Criteria{
  implicit def funcToCriteria[T](f: Function1[T, Boolean]) = new Criteria(f) 
}

class Criteria[T](val f: Function1[T, Boolean]) {
  def apply(t: T) = f(t)
  def and (c: Criteria[T]): Criteria[T] = (t: T) => f(t) && c.f(t)
  def or  (c: Criteria[T]): Criteria[T] = (t: T) => f(t) || c.f(t)
}