package Database

/**
 * @author sraspin
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Connecting to the MySQL database
 */
class Database {
  var connection:Connection = _
  
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