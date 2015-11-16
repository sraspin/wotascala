package com.qa.database

/**
 * @author sraspin
 */

import java.sql.SQLException

class RemoveStockDB {
  val db = new Database
  
  
  /**
   * @param : currentPOrder - the ID of the order to be deleted
   * 
   * Deletes rows from the purchaseorder table of the database where the parameter input matches the data in column idPOrder
   * deletes corresponding data in the aporder table of the database
   */
  def removeOrder(currentPOrder: Int){
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("DELETE FROM `purchaseorder` WHERE `idPOrder`='" + currentPOrder + "'")
      val rs2 = statement executeUpdate("DELETE FROM `aporder` WHERE `idOrder` = '" + currentPOrder + "'")
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
  
  
  /**
   * @param : indVal - the primary key of the order to be deleted
   * 
   * Deletes rows from the aporder table of the database where the parameter input matches the data in column idIndividual
   */
  def removeIndOrder(indVal: Int){
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("DELETE FROM `aporder` WHERE `idIndividual` = '" + indVal + "'")
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
}