/**
 * @author sraspin
 */

import java.sql.SQLException
import java.sql.Connection

class LogIn(val user:String, val pass:String) {
  var u = new Array[String](3)
  var p = new Array[String](3)
  
  /**
   * Storing the usernames and passwords in arrays
   * Checking the username and password input against the arrays
   * Returns whether username & password valid or not (true or false)
   */
  def Login(conn: Connection): Boolean = {
    var i:Int = 0
    var check: Boolean = false
    var n: Int = 0
    try{
      val statement = conn createStatement()
      val rs = statement executeQuery("SELECT idEmployee, passEmployee FROM employee")
      while(rs next){
        i += 1
        u(i-1) = rs getString("idEmployee")
        p(i-1) = rs getString("passEmployee")
      }
    } catch {
      case e : SQLException => e printStackTrace
    }
    if(check == false){
      while(check == false && n < i){
        if(user == u(n) && pass == p(n)){
          check = true
        } else {
          n += 1
        }
      }
    }
    if(check == false){
      println("you got it wrong")
    }
    if(check == true){
      println("you got it right")
    }
    check
  }
}