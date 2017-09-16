package edu.jproyo.dojos.loan

import edu.jproyo.dojos.loan.data.DataLoader.FileDataLoader

object App {
  val messageUsage =
    """
      |Error calling command App
      |To call this command please run the following
      |cmd> command FILE_PATH.csv AMOUNT
    """.stripMargin

  def main(args: Array[String]): Unit = {
    require(args.length == 2, messageUsage)
    implicit val loader = new FileDataLoader(args(0))
    Loan().request(args(1).toInt) match {
      case Some(x) => println(x.pretttyPrint)
      case None => println("\n\nNOT Found Lenders for Amount Requested\n\n")
    }
  }
}
