package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.data.DataLoader

object TestHelper {

  implicit object testDataLoader extends DataLoader {
    def loadData: Set[Lender] = {
      Set(Lender("Bob", 0.075, 640),
        Lender("Jane", 0.069, 480),
        Lender("Fred", 0.071, 520),
        Lender("Mary", 0.104, 170),
        Lender("John", 0.081, 320),
        Lender("Dave", 0.074, 140),
        Lender("Angela", 0.071, 60))
    }
  }

}
