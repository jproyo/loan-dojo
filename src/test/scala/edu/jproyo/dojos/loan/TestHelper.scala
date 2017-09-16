package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.data.DataLoader
import edu.jproyo.dojos.loan.model._
import org.scalactic.Equality

import scala.language.implicitConversions

object TestHelper {

  implicit object testDataLoader extends DataLoader {
    def loadData: Either[String, Set[Lender]] = {
      Right(Set(Lender("Bob", 0.075, 640),
        Lender("Jane", 0.069, 480),
        Lender("Fred", 0.071, 520),
        Lender("Mary", 0.104, 170),
        Lender("John", 0.081, 320),
        Lender("Dave", 0.074, 140),
        Lender("Angela", 0.071, 60)))
    }
  }


  class DoubleUtils(x: Double) {
    def ~=(y: Double): Boolean = math.abs(x - y) < 0.01
  }

  implicit def doubleCompare(d: Double) = new DoubleUtils(d)

  implicit val conditionEq = new Equality[Condition] {
    override def areEqual(a: Condition, b: Any) = b match {
      case c: Condition => (a.amountRequested == c.amountRequested) &&
        (a.rate ~= c.rate) &&
        (a.monthlyRepayment ~= c.monthlyRepayment) &&
        (a.totalRepayment ~= c.totalRepayment)
    }
  }

}
