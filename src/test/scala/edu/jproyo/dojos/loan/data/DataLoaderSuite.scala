package edu.jproyo.dojos.loan.data

import edu.jproyo.dojos.loan.TestHelper.testDataLoader
import edu.jproyo.dojos.loan.data.DataLoader.FileDataLoader
import edu.jproyo.dojos.loan.model._
import org.scalatest.FunSuite

class DataLoaderSuite extends FunSuite{

  test("test load market csv file"){
    val file = getClass.getClassLoader.getResource("data/market_data_for_tech.csv").getPath
    assert(new FileDataLoader(file).loadData === testDataLoader.loadData)
  }


  test("test load csv file Not found"){
    assert(new FileDataLoader("/home/something/notfound.csv").loadData.isLeft)
  }

}
