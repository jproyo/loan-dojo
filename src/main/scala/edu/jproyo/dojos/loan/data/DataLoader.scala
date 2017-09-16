package edu.jproyo.dojos.loan.data

import java.io.File

import edu.jproyo.dojos.loan.model._

trait DataLoader {
  /**
    * Load default Data
    * @return
    */
  def loadData: Either[String, Set[Lender]]
}

object DataLoader {

  /**
    * Implementation Loading data from CSV
    * @param filePath
    */
  implicit class FileDataLoader(val filePath: String) extends DataLoader{
    import com.github.tototoshi.csv._

    /**
      * Load data from file.
      * @return Either -> Left(String with error) | Right(Set[Lender])
      */
    def loadData: Either[String, Set[Lender]] =
      loadFile.map(CSVReader.open).map(_.allWithHeaders()).map(_.map(Lender(_)).toSet) match {
        case Some(x) => Right(x)
        case None => Left(s"Could not read CSV file at $filePath")
      }

    private def loadFile: Option[File] = {
      val file = new File(filePath)
      if (file.exists()) Some(file) else None
    }

  }
}