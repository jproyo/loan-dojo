package edu.jproyo.dojos.loan

import org.scalatest._

import edu.jproyo.dojos.loan._

class LoanSpec extends WordSpec with Matchers {

  "A Borrower" when {
    "request £ 1000 and default data" should {
      "return loan condition" in {
        request(1000) should equal(Condition(amountRequested = 1000, rate = 7.0, monthlyRepayment = 34.25, totalRepayment = 1233.08))
      }
    }
  }

}
