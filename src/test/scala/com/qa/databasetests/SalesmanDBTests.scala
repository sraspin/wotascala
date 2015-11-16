package com.qa.databasetests

import com.qa.database.SalesmanDB

/**
 * @author sraspin
 */
class SalesmanDBTests extends TestBase{
  
  def testGetPositions{
    "The getPositions method" should "not return null" in{
      val salesmanDB = new SalesmanDB
      val result = salesmanDB getPositions(1002)
      
      assert(result != 0)
    }
    it should "return the correct number of orders to collect" in{
      val salesmanDB = new SalesmanDB
      val result = salesmanDB getPositions(1003)
      
      assert(result == 8)
    }
  }
  testGetPositions
}