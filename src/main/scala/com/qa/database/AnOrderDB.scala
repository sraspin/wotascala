package com.qa.database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import com.qa.entities.{APOrder, AnOrder}

class AnOrderDB {
  val db = new Database
  
  
  /**
   * @return : ObservableBuffer[AnOrder]
   * @param : a - the ID of the orders to be selected
   * 
   * Selects rows from the database where the parameter input matches data in the idOrder column
   * Stores the data in the specified rows into an array
   */
  def getOrder(a: Int): ObservableBuffer[AnOrder] = {
    val orderArray:ObservableBuffer[AnOrder] = ObservableBuffer[AnOrder]()
    
    try{
      val conn= db connect()
      
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT * FROM anorder WHERE idOrder = '" + a + "'")
      while(rs next){
        orderArray += new AnOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4))
      }
      conn.close()
    } catch {
      case e : SQLException => e printStackTrace
    }
    orderArray
  }
  
  
  /**
   * @return : ObservableBuffer[APOrder]
   * @param : the ID of the order to be selected
   * 
   * Selects rows from the database where the parameter input matches data in the idOrder column
   * Stores the data in the specified rows into an array
   */
  def getPOrder(a: Int): ObservableBuffer[APOrder] = {
    val pOrderArray:ObservableBuffer[APOrder] = ObservableBuffer[APOrder]()
    
    try{
      val conn= db connect()
      
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT * FROM aporder WHERE idOrder = '" + a + "'")
      while(rs next){
        pOrderArray += new APOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4))
      }
      conn.close()
    } catch {
      case e : SQLException => e printStackTrace
    }
    pOrderArray
  }
}