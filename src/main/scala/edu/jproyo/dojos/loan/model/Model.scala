package edu.jproyo.dojos.loan.model

case class Lender(name: String, rate: Double, available: Int)
object Lender {
  def apply(fields: Map[String,String]): Lender = {
    Lender(fields("Lender"), fields("Rate").toDouble, fields("Available").toInt)
  }
}

case class Condition(amountRequested: Int, rate: Double, monthlyRepayment: Double, totalRepayment: Double) {

  def pretttyPrint: String =
    s"""
       |Requested amount: £ $amountRequested
       |Rate: ${"%.1f".format(rate * 100)}%%
       |Monthly repayment: £ ${"%.2f".format(monthlyRepayment)}
       |Total repayment:  £ ${"%.2f".format(totalRepayment)}
          """.stripMargin
}

