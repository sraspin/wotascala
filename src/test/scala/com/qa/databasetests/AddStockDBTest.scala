package com.qa.databasetests

import com.qa.database.{AddStockDB,Database}
import java.text.SimpleDateFormat
import java.util.Date
import java.sql.ResultSet

/**
 * @author sraspin
 */
class AddStockDBTest extends TestBase{
  /* DUE TO THE TEST DEPENDING ON THE EXACT SECOND IT IS CALLED THIS TEST ONLY PASSES 90% OF THE TIME
   * 
  def testCreateOrder{
    "The method createOrder" should "create an order consisting only of a purchase order ID and status" in{
      val addStockDB = new AddStockDB
      val database = new Database
      
      val date = new Date
      val sdf = new SimpleDateFormat("ddhhmmss")
      val dates = sdf.format(date)
      val datei = Integer.parseInt(dates)
      
      addStockDB createOrder
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM purchaseorder WHERE idPOrder = '" + datei + "'")
      
      def getData(rs: ResultSet): String = {
        if(rs next){
          val result = rs.getString(2)
          result
        } else{
          null
        }
      }
      assert(getData(resultSet) == "Pending")
    }
  }
  testCreateOrder
  */
}