package com.qa.databasetests

import com.qa.database.{COrdersDB, Database}
import com.qa.entities.CustomerOrder
import scalafx.collections.ObservableBuffer
import java.sql.ResultSet

/**
 * @author sraspin
 */
class COrdersDBTest extends TestBase{
  
  def testGetCOrders{
    "The getCOrders method" should "not return null" in{
      val cOrdersDB = new COrdersDB
      val result = cOrdersDB getCOrders
        
      assert(result != null)
    }
    
    it should "return all the customer orders within the database" in{
      
      val cOrdersDB = new COrdersDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM customerorder")
      
      val results = cOrdersDB.getCOrders()
      
      val customerOrderArray: ObservableBuffer[CustomerOrder] = ObservableBuffer[CustomerOrder]()
      
      def getData(rs: ResultSet, cOrder: ObservableBuffer[CustomerOrder]) = {
        def getRSData(){
          if(rs next){
            cOrder += new CustomerOrder(rs.getInt(1), rs.getString(2))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, customerOrderArray)
      
      if(results.length == customerOrderArray.length) assert(true) else assert(false)
    }
    it should "return the correct data" in{
      val cOrdersDB = new COrdersDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM customerorder")
      
      val results = cOrdersDB.getCOrders()
      
      val customerOrderArray: ObservableBuffer[CustomerOrder] = ObservableBuffer[CustomerOrder]()
      
      def getData(rs: ResultSet, cOrder: ObservableBuffer[CustomerOrder]) = {
        def getRSData(){
          if(rs next){
            cOrder += new CustomerOrder(rs.getInt(1), rs.getString(2))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, customerOrderArray)
      
      if(results(1).idCOrder.value == customerOrderArray(1).idCOrder.value && 
          results(3).idCOrder.value == customerOrderArray(3).idCOrder.value)
      
        assert(true) else assert(false)
    }
  }
  testGetCOrders
}