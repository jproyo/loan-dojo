package edu.jproyo.dojos.loan.calc

import edu.jproyo.dojos.loan.{Condition, Lender}
import org.scalatest.FunSuite
import edu.jproyo.dojos.loan.TestHelper._

import scala.collection.immutable.ListMap

class CalculatorSuite extends FunSuite{

  test("test calculate with simple interest"){
    val lenders = List(
      Lender("xx",0.069,300),
      Lender("yyy",0.081,300),
      Lender("zzz",0.104,400)
    )
    val expected = Condition(1000, rate = 0.084, monthlyRepayment = 30.18, totalRepayment = 1086.60)
    val calculator = new Calculator {
      override def interest(amount: Int, rate: Double): Double = amount + (amount * rate)
    }
    assert(calculator.calculate(1000, lenders) === expected)
  }

  test("test calculate with identity interest"){
    val lenders = List(
      Lender("xx",0.069,300),
      Lender("yyy",0.081,300),
      Lender("zzz",0.104,400)
    )
    val expected = Condition(1000, rate = 0.084, monthlyRepayment = 27.77, totalRepayment = 1000.00)
    val calculator = new Calculator {
      override def interest(amount: Int, rate: Double): Double = amount
    }
    assert(calculator.calculate(1000, lenders) === expected)
  }


}
