package edu.jproyo.dojos.loan

import org.scalatest._

import edu.jproyo.dojos.loan.model._

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

    "request £ 1000 and default data" should {
      "prettyShow print desired result" in {
        val condition = Loan().request(1000)
        condition should not be empty
        val expected =
          """
            |Requested amount: £ 1000
            |Rate: 7.0%%
            |Monthly repayment: £ 34.25
            |Total repayment:  £ 1233.08
          """.stripMargin
        condition.get.pretttyPrint should equal(expected)
      }
    }

    "request £ 1500 and default data" should {
      "return loan condition" in {
        val condition = Loan().request(1500)
        condition should not be empty
        condition.get should equal(Condition(amountRequested = 1500, rate = 0.072, monthlyRepayment = 51.59, totalRepayment = 1857.39))
      }
    }

    "request not enough founds" should {
      "return loan None" in {
        val condition = Loan().request(4000)
        condition shouldBe empty
      }
    }

    "request above boundaries" should {
      "throw exception" in {
        assertThrows[IllegalArgumentException] {
          Loan().request(999)
        }
        assertThrows[IllegalArgumentException] {
          Loan().request(15001)
        }
      }
    }

    "request without data" should {
      "throw exception" in {
        assertThrows[IllegalArgumentException] {
          Loan(Set()).request(100)
        }
      }
    }
  }

}
