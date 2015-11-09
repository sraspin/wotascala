package Entities

/**
 * @author sraspin
 */

import scalafx.beans.property.ObjectProperty

class AnOrder(orderNo_ : Int, idOrder_ : Int, idProduct_ : Int, Quantity_ : Int) {
  val orderNo = new ObjectProperty[Int](this, "orderNo", orderNo_)
  val idOrder = new ObjectProperty[Int](this, "idOrder", idOrder_)
  val idProduct = new ObjectProperty[Int](this, "idProduct", idProduct_)
  val Quantity = new ObjectProperty[Int](this, "Quantity", Quantity_)
}