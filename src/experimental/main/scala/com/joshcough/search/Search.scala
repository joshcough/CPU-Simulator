package com.joshcough.search
/**
 * @author dood
 * Date: Dec 27, 2008
 * Time: 8:07:02 PM
 */

trait Search[T <: Ordered[T]]{

  def all: List[T]

  def apply(c: Criteria[T]) = find(c)

  def find(c: Criteria[T]) = ResultSet(all.filter(c(_)))
  def filter(c: Criteria[T]) = find(c)
  def search(c: Criteria[T]) = find(c)

  private def true_criteria = new Criteria((t: T) => true)
  private def join(criteria: Criteria[T]*) = criteria.foldLeft(true_criteria){_ and _}
}