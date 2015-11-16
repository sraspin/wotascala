package com.qa.entities

/**
 * @author sraspin
 */

import scalafx.beans.property.ObjectProperty

class PurchaseOrder(idPOrder_ : Int, pOstatus_ : String) {
  val idPOrder = new ObjectProperty[Int](this, "idPOrder", idPOrder_)
  val pOstatus = new ObjectProperty[String](this, "pOstatus", pOstatus_)
}