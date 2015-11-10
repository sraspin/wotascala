package Database

/**
 * @author sraspin
 */

import scalafx.collections.ObservableBuffer
import java.sql.SQLException
import Entities.CustomerOrder
import java.text.SimpleDateFormat
import java.util.Date

class AddStockDB {
  val db = new Database
  
  def createOrder(){
    val date = new Date
    val sdf = new SimpleDateFormat("ddhhmmss")
    val dates = sdf.format(date)
    val datei = Integer.parseInt(dates)
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("INSERT INTO `purchaseorder` (`idPOrder`, `pOstatus`) VALUES ('" + datei + "', 'a');")
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
  def addToOrder(p: Int, q: Int){
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("INSERT INTO `aporder` (`orderNo`, `Quantity`) VALUES ('" + p + "', '" + q + "')")
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
}