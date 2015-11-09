package Database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import Entities.CustomerOrder

class COrdersDB {
  val db = new Database
  
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