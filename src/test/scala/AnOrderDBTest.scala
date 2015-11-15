import Database.AnOrderDB

/**
 * @author sraspin
 */
class AnOrderDBTest extends TestBase{
  
  def testGetOrder{
    "The getOrder method" should "not return null" in{
      val anOrderDB = new AnOrderDB
      val result = anOrderDB getOrder'_'
        
      assert(result != null)
    }
  }
  testGetOrder
}