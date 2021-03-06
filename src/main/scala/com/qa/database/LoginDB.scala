package com.qa.database

/**
 * @author sraspin
 */

import java.sql.SQLException
import scala.collection.mutable.ArrayBuffer

class LoginDB(val user: String, val pass: String){
  var u = new ArrayBuffer[String]
  var p = new ArrayBuffer[String]
  var name = new ArrayBuffer[String]
  var n: Int = 0
  val db = new Database
  
  
  /**
   * Stores data from the employee table in the database into separate arrays
   */
  def login():Boolean = {
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
  }
  
  
  /**
   * @return : Boolean
   * @param : n - increased by 1 after each loop
   * 
   * checks whether there are still unchecked usernames in the database
   * once the username and password match, exits loop with value true else false
   */
  def loginLoop(n: Int): Boolean = {
    if(n < (u length)){
      if(user == u(n) && pass == p(n)){
        true
      } else {
        loginLoop(n + 1)
      }
    } else {
      false
    }
  }
}