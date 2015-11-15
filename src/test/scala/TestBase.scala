

/**
 * @author sraspin
 */

import org.scalatest._
import org.scalatest.Inspectors

abstract class TestBase extends FlatSpec
with Matchers with OptionValues
with Inside with Inspectors{
  
}