package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.data.DataLoader

case class Condition(amountRequested: Int, rate: Double, monthlyRepayment: Double, totalRepayment: Double)
case class Lender(name: String, rate: Double, available: Int)


case class Loan(lenders: Set[Lender]) {

  implicit val ordering = new Ordering[Lender] {
    override def compare(x: Lender, y: Lender) = x.available.compare(y.available)
  }

  def lendersGroupByRate: Map[Double, List[Lender]] = lenders.toList.sorted groupBy(_.rate)

  def findBestCondition(amount: Int): Option[Condition] = ???

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
  def apply()(implicit loader: DataLoader): Loan = Loan(loader.loadData)
}

