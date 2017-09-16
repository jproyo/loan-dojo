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


  implicit class FileDataLoader(val filePath: String) extends DataLoader{
    import com.github.tototoshi.csv._

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