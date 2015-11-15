import Database.POrdersDB

/**
 * @author sraspin
 */
class POrdersDBTest extends TestBase{
  
  def testGetPOrders{
    "The getPOrders method" should "not return null" in{
      val pOrdersDB = new POrdersDB
      val result = pOrdersDB getPOrders
      
      assert(result != null)
    }
  }
  testGetPOrders
}