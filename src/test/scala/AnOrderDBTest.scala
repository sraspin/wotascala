import com.qa.database.{AnOrderDB, Database}
import com.qa.entities.{AnOrder, APOrder}
import scalafx.collections.ObservableBuffer
import java.sql.ResultSet

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
    
    it should "return all the individual orders within a customer order" in{
      val anOrderDB = new AnOrderDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM anorder WHERE idOrder = '" + 1003 + "'")
      
      val results = anOrderDB.getOrder(1003)
      
      val anOrderArray: ObservableBuffer[AnOrder] = ObservableBuffer[AnOrder]()
      
      def getData(rs: ResultSet, anOrder: ObservableBuffer[AnOrder]) = {
        def getRSData(){
          if(rs next){
            anOrder += new AnOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, anOrderArray)
      
      if(results.length == anOrderArray.length) assert(true) else assert(false)
    }
    it should "return the correct data" in{
      val anOrderDB = new AnOrderDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM anorder WHERE idOrder = '" + 1003 + "'")
      
      val results = anOrderDB.getOrder(1003)
      
      val anOrderArray: ObservableBuffer[AnOrder] = ObservableBuffer[AnOrder]()
      
      def getData(rs: ResultSet, anOrder: ObservableBuffer[AnOrder]) = {
        def getRSData(){
          if(rs next){
            anOrder += new AnOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, anOrderArray)
      
      if(results(0).orderNo.value == anOrderArray(0).orderNo.value && 
          results(2).orderNo.value == anOrderArray(2).orderNo.value)
        
        assert(true) else assert(false)
    }
  }
  testGetOrder
  
  def testGetPOrder{
    "The getPOrder method" should "not return null" in{
      val anOrderDB = new AnOrderDB
      val result = anOrderDB getPOrder'_'
        
      assert(result != null)
    }
    
    it should "return all the individual orders within a purchase order" in{
      val anOrderDB = new AnOrderDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM aporder WHERE idOrder = '" + 15034503 + "'")
      
      val results = anOrderDB.getPOrder(15034503)
      
      val aPOrderArray: ObservableBuffer[APOrder] = ObservableBuffer[APOrder]()
      
      def getData(rs: ResultSet, apOrder: ObservableBuffer[APOrder]) = {
        def getRSData(){
          if(rs next){
            apOrder += new APOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, aPOrderArray)
      
      if(results.length == aPOrderArray.length) assert(true) else assert(false)
    }
    it should "return the correct data" in{
      val anOrderDB = new AnOrderDB
      
      val database = new Database
      
      val resultSet = database.connect().createStatement.executeQuery("SELECT * FROM aporder WHERE idOrder = '" + 15034503 + "'")
      
      val results = anOrderDB.getPOrder(15034503)
      
      val aPOrderArray: ObservableBuffer[APOrder] = ObservableBuffer[APOrder]()
      
      def getData(rs: ResultSet, apOrder: ObservableBuffer[APOrder]) = {
        def getRSData(){
          if(rs next){
            apOrder += new APOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4))
            getRSData
          }
        }
        getRSData
      }
      getData(resultSet, aPOrderArray)
      
      if(results(0).idIndividual.value == aPOrderArray(0).idIndividual.value && 
          results(1).idIndividual.value == aPOrderArray(1).idIndividual.value)
        
        assert(true) else assert(false)
    }
  }
  testGetPOrder
}