package edu.jproyo.dojos.loan.calc

import edu.jproyo.dojos.loan.{Condition, Lender}


trait Calculator {

  val period: Int = 36

  def interest(amount: Int, rate: Double): Double

  def reduceInterest(pair: (Double, Int), lender: Lender) = {
    val amountToCalc = if (pair._2 > lender.available) lender.available else pair._2
    (interest(amountToCalc, lender.rate) + pair._1, pair._2-amountToCalc)
  }

  def calculate(amount: Int, lenders: Set[Lender]): Condition = {
    val total = (lenders foldLeft (0.0, amount))(reduceInterest)
    val rate = ((lenders foldLeft 0.0)((acc,l) => acc + l.rate)) / lenders.size
    val monthly = total._1 / period
    Condition(amount, rate, monthly, total._1)
  }

}

object calc {

  implicit object monthlyCompoundInterest extends Calculator {
    def interest(amount: Int, rate: Double): Double =
      amount*math.pow((1+(rate/12)),period)
  }
}