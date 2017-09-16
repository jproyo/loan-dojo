package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.calc.Calculator
import edu.jproyo.dojos.loan.calc.calc.monthlyCompoundInterest
import edu.jproyo.dojos.loan.data.DataLoader
import edu.jproyo.dojos.loan.model._

import scala.collection.immutable.ListMap

trait LoanService {
  val calculator: Calculator
}

case class Loan(lenders: Set[Lender]) extends LoanService{

  implicit val calculator = monthlyCompoundInterest

  implicit val ordering = new Ordering[Lender] {
    override def compare(x: Lender, y: Lender): Int = -(x.available.compareTo(y.available))
  }

  /**
    * Lenders group by rate order from lowest rate to highest
    *
    * @return
    */
  lazy val lendersGroupByRate: Map[Double, Set[Lender]] = ListMap((lenders groupBy (_.rate)).toSeq.sortBy(_._1): _*).map(p => p._1 -> p._2.toList.sorted.toSet)


  /**
    * Take all posible lenders from the lower rate to the highest until we could fill up the amount requested
    *
    * @param amount
    * @return
    */
  def elegibleLenders(amount: Int): Option[List[Lender]] = {
    val eleg = elegibles(amount)
    if (eleg.foldLeft(0)((acc, e) => acc + e.available) >= amount) Some(eleg) else None
  }

  /**
    * Elegible Lenders for this amount
    * @param amount
    * @return
    */
  def elegibles(amount: Int): List[Lender] = {
    (lendersGroupByRate.map(_._2).flatten.foldLeft(((List[Lender](), amount)))(takeElegibles))._1
  }

  /**
    * Helper method
    *
    * @return
    */
  private def takeElegibles(accLender: (List[Lender], Int), rateLender: Lender): (List[Lender], Int) = {
    val (acc, amount) = accLender
    if (amount == 0) accLender
    else if (amount >= rateLender.available) (acc ++ List(rateLender), amount - rateLender.available) else (acc ++ List(rateLender), 0)
  }

  /**
    * Build a condition based on the elegible lenders obtained.
    *
    * Delegate calculation to specific form of calculation
    *
    * @param amount
    * @return
    */
  def conditionFrom(amount: Int): (List[Lender]) => Option[Condition] = lenders => {
    Some(calculator.calculate(amount, lenders))
  }

  /**
    * Find best rates for this requested amount
    *
    * @param amount
    * @return
    */
  def findBestCondition(amount: Int): Option[Condition] =
    elegibleLenders(amount).flatMap(conditionFrom(amount))

  /**
    * Request an amount to be borrowed by one or more lenders at the lowest rate posible
    *
    * @param amount
    * @return the condition on which the loan should be granted or None if money is not available
    */
  def request(amount: Int): Option[Condition] = {
    require(!lenders.isEmpty, "Lenders data not available")
    require(amount >= 1000 && amount <= 15000, "Amount should be between 1000 and 15000")
    findBestCondition(amount)
  }
}

object Loan {
  /**
    * Factory Loan Object Companion
    *
    * @param loader
    * @return
    */
  def apply()(implicit loader: DataLoader): Loan = {
    loader.loadData match {
      case Right(data) => Loan(data)
      case Left(error) => throw new IllegalArgumentException(error)
    }
  }
}

