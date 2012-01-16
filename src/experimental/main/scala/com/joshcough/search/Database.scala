package com.joshcough.search

/**
 * @author dood
 * Date: Dec 27, 2008
 * Time: 7:24:01 PM
 */

case class Database[T <: Ordered[T]](data: List[T]) {
  def apply(criteria: Criteria[T]) = ResultSet(data.filter(criteria(_)))
  def find = apply _
  def filter = apply _
  def search = apply _
  def find_all(criteria: Criteria[T]*) = apply(join(criteria: _*))
  private def true_criteria = new Criteria[T]((t: T) => true)
  private def join(criteria: Criteria[T]*) = criteria.foldLeft(true_criteria){_ and _}
}

case class ResultSet[T <: Ordered[T]](data: List[T]) {
  def limit(n: Int) = ResultSet(data.take(n))
  def orderBy[U <: Ordered[U]](f: T => U, dir: Direction) = {
     def f2[U]: Function2[T,T,Boolean] = dir(f)
    val x = data.sort(f2)
    ResultSet[T](x)
  }
  def foreach = data foreach _
}

trait Direction{
  def apply[A <: Ordered[A], B<: Ordered[B]](f: A => B): (A,A) => Boolean
}

case object Asc extends Direction{
  def apply[A <: Ordered[A], B<: Ordered[B]](f: A => B): (A,A) => Boolean = {
    (b1: A,b2: A) => f(b1) < f(b2)
  }
}

case object Desc extends Direction{
  def apply[A <: Ordered[A], B<: Ordered[B]](f: A => B): (A,A) => Boolean = {
    (b1: A,b2: A) => f(b1) > f(b2)
  }
}
