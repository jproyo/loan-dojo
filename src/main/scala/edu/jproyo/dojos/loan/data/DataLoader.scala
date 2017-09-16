package edu.jproyo.dojos.loan.data

import java.io.File

import edu.jproyo.dojos.loan.model._

trait DataLoader {
  /**
    * Load default Data
    * @return
    */
  def loadData: Set[Lender]
}

object DataLoader {


  implicit class FileDataLoader(val filePath: String) extends DataLoader{
    import com.github.tototoshi.csv._
    def loadData: Set[Lender] = {
      val reader = CSVReader.open(new File(filePath))
      reader.all().map(Lender.apply).toSet
    }
  }
}