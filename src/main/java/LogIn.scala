/**
 * @author sraspin
 */

import java.sql.SQLException
import java.sql.Connection

class LogIn(val user:String, val pass:String) {
  var u = new Array[String](3)
  var p = new Array[String](3)
  var a = new Array[String](3)
  
  def Login(conn: Connection){
    try{
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT idEmployee, passEmployee FROM employee")
      var i:Int = -1
      while(rs next){
        i += 1
        u(i) = rs getString("idEmployee")
        p(i) = rs getString("passEmployee")
      }
    } catch {
      case e : SQLException => e printStackTrace
    }
    println(u(1) + u(2) + p(1) + p(2))
  }
}