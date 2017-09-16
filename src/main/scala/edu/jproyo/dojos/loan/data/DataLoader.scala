package edu.jproyo.dojos.loan.data

import edu.jproyo.dojos.loan.Lender

trait DataLoader {
  /**
    * Load default Data
    * @return
    */
  def loadData: Set[Lender]
}
