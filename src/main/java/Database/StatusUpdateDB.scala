package Database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import Entities._


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
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
}