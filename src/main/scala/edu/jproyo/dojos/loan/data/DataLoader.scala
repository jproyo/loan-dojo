package edu.jproyo.dojos.loan.data

import edu.jproyo.dojos.loan.Lender

trait DataLoader {
  /**
    * Load default Data
    * @return
    */
  def loadData: Set[Lender]
}

object DataLoader {

  implicit object EmptyDataLoader extends DataLoader{
    def loadData: Set[Lender] = Set()
  }

  implicit class FileDataLoader(val filePath: String) extends DataLoader{
    def loadData: Set[Lender] = Set()
  }
}