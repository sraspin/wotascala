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
    var i:Int = 0
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
    println(u(0) + u(1) + p(0) + p(1))
    var ucheck: Boolean = false
    var n: Int = 0
    if(ucheck == false){
      while(ucheck == false && n < i){
        if(user equals u(n)){
          ucheck = true
        } else {
          n += 1
        }
      }
    }
    if(ucheck == false){
      println("you got it wrong")
    }
    if(ucheck == true){
      println("you got it right")
    }
  }
}