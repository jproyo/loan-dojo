package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.data.DataLoader

object TestHelper {

  implicit object testDataLoader extends DataLoader {
    def loadData: Set[Lender] =
      (for {
        name <- (1 to 10).map("Name " + _)
        rate <- (1 to 10).map(0.06 + _ / 100)
        amount <- (1 to 10).map(100 * _)
      } yield Lender(name, rate, amount)).toSet

  }

}
