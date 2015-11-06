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
  val url = "jdbc:mysql://localhost:3306/forwota"
  val driver = "com.mysql.jdbc.Driver"
  val user = "root"
  val pass = "root"
  var connection:Connection = _
  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, user, pass)
  } catch {
    case e: Exception => e.printStackTrace
  }
}