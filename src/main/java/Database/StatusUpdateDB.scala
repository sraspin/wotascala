package Database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import Entities._


class StatusUpdateDB {
  val db = new Database
  
  def statUpdate(s: String, i: Int) {
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("UPDATE customerorder SET status = '" + s + "' WHERE idCOrder = '" + i + "'")
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
}