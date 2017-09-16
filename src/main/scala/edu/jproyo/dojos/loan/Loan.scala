package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.calc.Calculator
import edu.jproyo.dojos.loan.calc.calc.monthlyCompoundInterest
import edu.jproyo.dojos.loan.data.DataLoader

import scala.annotation.tailrec
import scala.collection.immutable.ListMap

case class Condition(amountRequested: Int, rate: Double, monthlyRepayment: Double, totalRepayment: Double)
case class Lender(name: String, rate: Double, available: Int)

trait LoanService {
  val calculator: Calculator
}

case class Loan(lenders: Set[Lender]) {

  implicit val calculator = monthlyCompoundInterest

  /**
    * Lenders group by rate order from lowest rate to highest
    *
    * @return
    */
  def lendersGroupByRate: ListMap[Double, Set[Lender]] = ListMap((lenders groupBy(_.rate)).toSeq.sortBy(_._1):_*)


  /**
    * Take all posible lenders from the lower rate to the highest until we could fill up the amount requested
    *
    * @param amount
    * @return
    */
  def elegibleLenders(amount: Int): Option[Set[Lender]] = {
    val elegibles = (lendersGroupByRate foldLeft (Set[Lender]())) (takeElegibles(amount))
    if (elegibles.foldLeft(0)((acc, e) => acc + e.available) >= amount) Some(elegibles) else None
  }

  /**
    * Helper tail recursive method
    * @param amount
    * @return
    */
  private def takeElegibles(amount: Int): (Set[Lender], (Double, Set[Lender])) => Set[Lender] = (lendersAcc, rateLender) => {
    @tailrec
    def lendersAcc(acc: Set[Lender], lenders: List[Lender], amountRequested: Int): Set[Lender] = {
      if (amount == 0) acc
      else lenders match {
        case Nil => acc
        case x :: xs => lendersAcc( acc + x, xs, math.abs(amount - x.available))
      }
    }
    lendersAcc(Set(), rateLender._2.toList, amount)
  }

  /**
    * Build a condition based on the elegible lenders obtained.
    *
    * Delegate calculation to specific form of calculation
    *
    * @param amount
    * @return
    */
  def conditionFrom(amount: Int): (Set[Lender]) => Option[Condition] = lenders => {
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
    * @param loader
    * @return
    */
  def apply()(implicit loader: DataLoader): Loan = Loan(loader.loadData)
}

