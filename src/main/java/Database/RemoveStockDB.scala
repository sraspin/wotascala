package Database

/**
 * @author sraspin
 */

import java.sql.SQLException

class RemoveStockDB {
  val db = new Database
  
  def removeOrder(currentPOrder: Int){
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("DELETE FROM `purchaseorder` WHERE `idPOrder`='" + currentPOrder + "'")
      val rs2 = statement executeUpdate("DELETE FROM `aporder` WHERE `idOrder` = '" + currentPOrder + "'")
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
  
  def removeIndOrder(indVal: Int){
    
    try{
      val conn = db connect()
      
      val statement = conn createStatement()
      val rs = statement executeUpdate("DELETE FROM `aporder` WHERE `idIndividual` = '" + indVal + "'")
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
  }
}