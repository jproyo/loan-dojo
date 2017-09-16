package edu.jproyo.dojos.loan

import org.scalatest.FunSuite

import scala.collection.immutable.ListMap

class LoanSuite extends FunSuite{

  import TestHelper.testDataLoader

  test("test lendersGroupByRate"){
    val expected = ListMap(
      0.069 -> Set(Lender("Jane",0.069,480)),
      0.071 -> Set(Lender("Fred",0.071,520), Lender("Angela",0.071,60)),
      0.074 -> Set(Lender("Dave",0.074,140)),
      0.075 -> Set(Lender("Bob",0.075,640)),
      0.081 -> Set(Lender("John",0.081,320)),
      0.104 -> Set(Lender("Mary",0.104,170))
    )
    assert(Loan().lendersGroupByRate === expected)
  }

  test("test elegibles"){
    val expected = Set(Lender("Jane",0.069,480), Lender("Fred",0.071,520))
    assert(Loan().elegibles(1000) === expected)
  }

  test("test elegiblesByRate"){
    val expected = Some(Set(Lender("Jane",0.069,480), Lender("Fred",0.071,520)))
    assert(Loan().elegibleLenders(1000) === expected)
  }
}
