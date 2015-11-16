package Database

/**
 * @author sraspin
 */

import java.sql.SQLException
import java.text.SimpleDateFormat
import java.util.Date
import Database.Database

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
      val rs = statement executeUpdate("INSERT INTO `purchaseorder` (`idPOrder`, `pOstatus`) VALUES ('" + datei + "', 'Pending');")
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
  def addToOrder(p: Int, q: Int, currentPOrder: Int){
    val date = new Date
    val sdf = new SimpleDateFormat("ssmmhhdd")
    val dates = sdf.format(date)
    val datei = Integer.parseInt(dates)
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("INSERT INTO `aporder` (`idIndividual`, `idOrder`, `idProduct`, `Quantity`) VALUES ('" + datei + "' , '" + currentPOrder + "', '" + p + "', '" + q + "')")
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
}