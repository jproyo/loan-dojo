package edu.jproyo.dojos.loan

case class Condition(amountRequested: Int, rate: Double, monthlyRepayment: Double, totalRepayment: Double)
case class Lender(name: String, rate: Double, available: Int)
case class Loan(lenders: Set[Lender])

object Loan {

  /**
    * Request an amount to be borrowed by one or more lenders at the lowest rate posible
    * @param amount
    * @return the condition on which the
    */
  def request(amount: Int): Option[Condition] = Some(Condition(amount, 7.0, 34.25, 1233.08))

}

