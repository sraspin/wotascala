package com.qa.databasetests

import com.qa.database.{StatusUpdateDB,Database}
import java.sql.ResultSet

/**
 * @author sraspin
 */
class StatusUpdateDBTest extends TestBase{
  
  def testStatUpdate{
    "The statUpdate method" should "set the status correctly" in{
      val statusUpdateDB = new StatusUpdateDB
      val database = new Database
      statusUpdateDB statUpdate("C", "testing", 1001)
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT status FROM customerorder WHERE idCOrder = '" + 1001 + "'")
      
      def getData(rs: ResultSet): String = {
        if(rs next){
          val result = rs.getString(1)
          result
        } else {
          null
        }
      }
      
      assert(getData(resultSet) == "testing")
    }
  }
  testStatUpdate
}