package com.qa.database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import com.qa.entities.PurchaseOrder

class POrdersDB {
  val db = new Database
  
  
  /**
   * @return : ObservableBuffer[PurchaseOrder]
   * 
   * Stores data from the purchaseorder table in the database into arrays
   */
  def getPOrders(): ObservableBuffer[PurchaseOrder] = {
    val pOrderArray:ObservableBuffer[PurchaseOrder] = ObservableBuffer[PurchaseOrder]()
    
    try{
      val conn =db connect()
      
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT * FROM purchaseorder")
      while(rs next){
        pOrderArray += new PurchaseOrder(rs.getInt(1), rs.getString(2))
      }
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
    pOrderArray
  }
}