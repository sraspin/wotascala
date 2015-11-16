package com.qa.database

/**
 * @author sraspin
 */

import java.sql.SQLException


class StatusUpdateDB {
  val db = new Database
  
  
  /**
   * @param : cp - determines which table of the database to update
   * @param : s - what the new status will say
   * @param : i - the ID of the order whose status is to be updated
   */
  def statUpdate(cp: String, s: String, i: Int) {
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      if(cp equals "C"){
        val rs = statement executeUpdate("UPDATE customerorder SET status = '" + s + "' WHERE idCOrder = '" + i + "'")
      }
      if(cp equals "P"){
        val rs = statement executeUpdate("UPDATE purchaseorder SET pOstatus = '" + s + "' WHERE idPOrder = '" + i + "'")
      }
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
}