package edu.jproyo.dojos.loan

case class Condition(amountRequested: Int, rate: Double, monthlyRepayment: Double, totalRepayment: Double)
case class Lender(name: String, rate: Double, available: Int)

case class Loan(lenders: Set[Lender]) {
  /**
    * Request an amount to be borrowed by one or more lenders at the lowest rate posible
    * @param amount
    * @return the condition on which the loan should be granted or None if money is not available
    */
  def request(amount: Int): Option[Condition] = {
    require(!lenders.isEmpty, "Lenders data not available")
    None
  }
}

object Loan {

  val defaultData = Set[Lender]()

  def apply(): Loan = Loan(defaultData)

  def request(amount: Int): Option[Condition] = this().request(amount)

}

