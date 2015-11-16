import com.qa.database.{POrdersDB, Database}
import com.qa.entities.PurchaseOrder
import scalafx.collections.ObservableBuffer
import java.sql.ResultSet

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
    it should "return all the customer orders within the database" in{
      
      val pOrdersDB = new POrdersDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM purchaseorder")
      
      val results = pOrdersDB.getPOrders()
      
      val purchaseOrderArray: ObservableBuffer[PurchaseOrder] = ObservableBuffer[PurchaseOrder]()
      
      def getData(rs: ResultSet, pOrder: ObservableBuffer[PurchaseOrder]) = {
        def getRSData(){
          if(rs next){
            pOrder += new PurchaseOrder(rs.getInt(1), rs.getString(2))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, purchaseOrderArray)
      
      if(results.length == purchaseOrderArray.length) assert(true) else assert(false)
    }
    it should "return the correct data" in{
      val pOrdersDB = new POrdersDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM purchaseorder")
      
      val results = pOrdersDB.getPOrders()
      
      val purchaseOrderArray: ObservableBuffer[PurchaseOrder] = ObservableBuffer[PurchaseOrder]()
      
      def getData(rs: ResultSet, pOrder: ObservableBuffer[PurchaseOrder]) = {
        def getRSData(){
          if(rs next){
            pOrder += new PurchaseOrder(rs.getInt(1), rs.getString(2))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, purchaseOrderArray)
      
      if(results(0).idPOrder.value == purchaseOrderArray(0).idPOrder.value && 
          results(1).idPOrder.value == purchaseOrderArray(1).idPOrder.value)
      
        assert(true) else assert(false)
    }
  }
  testGetPOrders
}