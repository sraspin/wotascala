package Database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import Entities._

class AnOrderDB {
  val db = new Database
  
  def getOrder(): ObservableBuffer[AnOrder] = {
    val orderArray:ObservableBuffer[AnOrder] = ObservableBuffer[AnOrder]()
    
    try{
      val conn= db connect()
      
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT * FROM anorder")
      while(rs next){
        orderArray += new AnOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3))
      }
      conn.close()
    } catch {
      case e : SQLException => e printStackTrace
    }
    orderArray
  }
}