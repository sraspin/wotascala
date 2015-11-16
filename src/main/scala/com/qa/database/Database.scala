package com.qa.database

/**
 * @author sraspin
 */

import java.sql.Connection
import java.sql.DriverManager

/**
 * Connecting to the MySQL database
 */
class Database {
  var connection:Connection = _
  
  
  /**
   * @return : Connection
   * 
   * The method I use to connect to the database
   */
  def connect(): Connection = {
    val url = "jdbc:mysql://localhost:3306/forwota"
    val driver = "com.mysql.jdbc.Driver"
    val user = "root"
    val pass = "root"
    
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, user, pass)
    } catch {
      case e: Exception => e.printStackTrace
    }
    connection
  }
}