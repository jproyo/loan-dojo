import edu.jproyo.dojos.loan.{Loan, TestHelper}

object play {
  import TestHelper.testDataLoader
  Loan().lendersGroupByRate

  300*math.pow((1+(0.069/12)),36) +
    300*math.pow((1+(0.081/12)),36) +
    400*math.pow((1+(0.104/12)),36)

  (300*0.069)+300 + 300+400+(300*0.081) + (400*0.104)
}