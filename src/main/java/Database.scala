import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author sraspin
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
    /*val statement = connection.createStatement
    val rs = statement.executeQuery("SELECT idEmployee FROM employee")
    while (rs.next) {
      val host = rs.getInt("idEmployee")
      println("host = %s".format(host,user))
    }*/
  } catch {
    case e: Exception => e.printStackTrace
  }
}