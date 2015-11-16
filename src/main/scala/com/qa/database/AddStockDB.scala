package com.qa.database

/**
 * @author sraspin
 */

import java.sql.SQLException
import java.text.SimpleDateFormat
import java.util.Date

class AddStockDB {
  val db = new Database
  
  
  /**
   * generates an (almost) random number to be used as a primary key in SQL database
   * creates a new purchase order in the database
   */
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
  
  
  /**
   * @param : p - the product ID to be added
   * @param : q - the quantity of the product to be added
   * @param : currentPOrder - the ID of the order being added to
   * 
   * generates an (almost) random number to be used as a primary key in SQL database
   * inserts the data input as a parameter into the database
   */
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