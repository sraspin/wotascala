package Database

/**
 * @author sraspin
 */

import java.sql.SQLException


class StatusUpdateDB {
  val db = new Database
  
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