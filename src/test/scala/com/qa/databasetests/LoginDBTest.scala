package com.qa.databasetests

import com.qa.database.LoginDB

/**
 * @author sraspin
 */
class LoginDBTest extends TestBase{
  
  def testLogin{
    "The login method" should "return true when correct details are input" in{
      val loginTest = new LoginDB("2345","root")
      
      assert(loginTest.login()==true)
    }
    it should "fail with the  incorrect login details" in{
      val loginTest = new LoginDB("wrong","incorrect")
      
      assert(loginTest.login()==false)
    }
  }
  testLogin
}