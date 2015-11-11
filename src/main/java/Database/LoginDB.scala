package Database

/**
 * @author sraspin
 */

import java.sql.SQLException
import scala.collection.mutable.ArrayBuffer

class LoginDB(val user: String, val pass: String){
  var u = new ArrayBuffer[String]
  var p = new ArrayBuffer[String]
  var name = new ArrayBuffer[String]
  var check: Boolean = false
  var n: Int = 0
  val db = new Database
  def Login():Boolean = {
    try{
      val conn = db connect()
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT * FROM employee")
      while(rs next){
        u += rs getString("idEmployee")
        p += rs getString("passEmployee")
        name += rs getString("userName")
      }
      conn close()
    } catch {
      case e : SQLException => e printStackTrace
    }
    loginLoop(0)
    check
  }
  def loginLoop(n: Int){
    if(n < (u length)){
      if(user == u(n) && pass == p(n)){
        check = true
      } else {
        loginLoop(n + 1)
      }
    }
  }
}