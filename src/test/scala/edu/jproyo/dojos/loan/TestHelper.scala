package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.data.DataLoader

object TestHelper {

  implicit object testDataLoader extends DataLoader {
    def loadData: Set[Lender] =
      (for {
        rate <- (1 to 10).map(0.06 + _.toDouble / 100)
        amount <- (1 to 10).map(100 * _)
      } yield Lender("Name "+rate+"-"+amount, rate, amount)).toSet

  }

}
