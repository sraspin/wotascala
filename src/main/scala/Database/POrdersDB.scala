package Database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import Entities.PurchaseOrder
import Database.Database

class POrdersDB {
  val db = new Database
  
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