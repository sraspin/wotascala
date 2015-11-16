
import com.qa.database.Database

/**
 * @author sraspin
 */

class DatabaseTest extends TestBase{
  
  "The connect method" should "return a driver manager for wotascala that should not be null" in{
    val database : Database = new Database
    database.connect() should not be (null)
  }
}