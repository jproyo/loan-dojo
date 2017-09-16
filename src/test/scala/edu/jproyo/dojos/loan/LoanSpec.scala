package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.data.DataLoader
import org.scalatest._

class LoanSpec extends WordSpec with Matchers {

  import TestHelper.testDataLoader

  "A Borrower" when {
    "request £ 1000 and default data" should {
      "return loan condition" in {
        val condition = Loan().request(1000)
        condition should not be empty
        condition.get should equal(Condition(amountRequested = 1000, rate = 7.0, monthlyRepayment = 34.25, totalRepayment = 1233.08))
      }
    }
  }

}
