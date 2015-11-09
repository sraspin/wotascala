package Database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import Entities._

class AnOrderDB {
  val db = new Database
  
  def getOrder(a: Int): ObservableBuffer[AnOrder] = {
    val orderArray:ObservableBuffer[AnOrder] = ObservableBuffer[AnOrder]()
    
    try{
      val conn= db connect()
      
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT orderNo, idOrder, idProduct, Quantity FROM anorder WHERE idOrder = '" + a + "'")
      while(rs next){
        orderArray += new AnOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4))
      }
      conn.close()
    } catch {
      case e : SQLException => e printStackTrace
    }
    orderArray
  }
}