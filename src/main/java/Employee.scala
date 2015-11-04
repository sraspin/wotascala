/**
 * @author sraspin
 */

import java.sql.Connection

class Employee {
  def loggingIn(connection:Connection):String = {
    var stuff: String = null
    try {
      val statement = connection createStatement
      val rs = statement executeQuery("SELECT * FROM employee")
      
      while (rs next) {
        stuff = rs getString("idEmployee")
        //println("id = " + stuff)
      }
    } catch {
      case e: Exception => e.printStackTrace
    }
    return stuff
  }
}