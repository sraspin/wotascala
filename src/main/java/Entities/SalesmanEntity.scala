package Entities

/**
 * @author sraspin
 */

import scalafx.beans.property.ObjectProperty

class SalesmanEntity(val xpos_ : Int, val ypos_ : Int) {
  val xpos = new ObjectProperty[Int](this, "xpos", xpos_)
  val ypos = new ObjectProperty[Int](this, "ypos", ypos_)
}