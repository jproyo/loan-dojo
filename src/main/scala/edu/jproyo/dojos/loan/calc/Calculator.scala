package edu.jproyo.dojos.loan.calc

import edu.jproyo.dojos.loan.{Condition, Lender}

trait Calculator {
  def calculate(amount: Int, lenders: Set[Lender]): Condition
}

object calc {
  implicit object monthlyCompoundInterest extends Calculator {
    override def calculate(amount: Int, lenders: Set[Lender]): Condition = {
      Condition(amount, 0.0, 0.0, 0.0)
    }
  }
}