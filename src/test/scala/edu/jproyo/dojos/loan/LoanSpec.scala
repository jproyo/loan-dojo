package edu.jproyo.dojos.loan

import org.scalatest._

class LoanSpec extends WordSpec with Matchers {

  import TestHelper._

  "A Borrower" when {
    "request £ 1000 and default data" should {
      "return loan condition" in {
        val condition = Loan().request(1000)
        condition should not be empty
        condition.get should equal(Condition(amountRequested = 1000, rate = 0.07, monthlyRepayment = 34.25, totalRepayment = 1233.08))
      }
    }
  }

}
