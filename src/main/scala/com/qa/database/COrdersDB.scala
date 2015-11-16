package com.qa.database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import com.qa.entities.CustomerOrder

class COrdersDB {
  val db = new Database
  
  
  /**
   * @return : ObservableBuffer[CustomerOrder]
   * 
   * stores data from the customerorder table in the database into an array
   */
  def getCOrders(): ObservableBuffer[CustomerOrder] = {
    val cOrderArray:ObservableBuffer[CustomerOrder] = ObservableBuffer[CustomerOrder]()
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT * FROM customerorder")
      while(rs next){
        cOrderArray += new CustomerOrder(rs.getInt(1), rs.getString(2))
      }
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
    cOrderArray
  }
}