package Entities

/**
 * @author sraspin
 */

import scalafx.beans.property.ObjectProperty

class APOrder(idIndividual_ : Int, idOrder_ : Int, idProduct_ : Int, Quantity_ : Int) {
  val idIndividual = new ObjectProperty[Int](this, "idIndividual", idIndividual_)
  val idOrder = new ObjectProperty[Int](this, "idOrder", idOrder_)
  val idProduct = new ObjectProperty[Int](this, "idProduct", idProduct_)
  val Quantity = new ObjectProperty[Int](this, "Quantity", Quantity_)
}