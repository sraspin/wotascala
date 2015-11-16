import Database.COrdersDB

/**
 * @author sraspin
 */
class COrdersDBTest extends TestBase{
  
  def testGetCOrders{
    "The getCOrders method" should "not return null" in{
      val cOrdersDB = new COrdersDB
      val result = cOrdersDB getCOrders
        
      assert(result != null)
    }
    
    it should "return all the customer"
  }
  testGetCOrders
}