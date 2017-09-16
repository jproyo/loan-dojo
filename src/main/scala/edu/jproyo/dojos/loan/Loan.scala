package edu.jproyo.dojos.loan

object Loan {

  /**
    * Request an amount to be borrowed by one or more lenders at the lowest rate posible
    * @param amount
    * @return the condition on which the
    */
  def request(amount: Int): Option[Condition] = Condition(amount, 7.0, 34.25, 1233.08)

}
