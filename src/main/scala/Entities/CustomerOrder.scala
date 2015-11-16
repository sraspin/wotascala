package Entities

/**
 * @author sraspin
 */

import scalafx.beans.property.ObjectProperty

class CustomerOrder(idCOrder_ : Int, status_ : String) {
  val idCOrder = new ObjectProperty[Int](this, "idCOrder", idCOrder_)
  val status = new ObjectProperty[String](this, "status", status_)
}