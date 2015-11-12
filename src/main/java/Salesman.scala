

/**
 * @author sraspin
 */

import Database.SalesmanDB

class Salesman() {
  val b1 = Array(2,1); val b6 = Array(2,6); val b11 = Array(2,11)
  val c1 = Array(3,1); val c6 = Array(3,6); val c11 = Array(3,11)
  val e1 = Array(5,1); val e6 = Array(5,6); val e11 = Array(5,11)
  val f1 = Array(6,1); val f6 = Array(6,6); val f11 = Array(6,11)
  val a2 = Array(1,2); val a3 = Array(1,3); val a4 = Array(1,4); val a5 = Array(1,5); val a7 = Array(1,7); val a8 = Array(1,8); val a9 = Array(1,9); val a10 = Array(1,10) 
  val d2 = Array(4,2); val d3 = Array(4,3); val d4 = Array(4,4); val d5 = Array(4,5); val d7 = Array(4,7); val d8 = Array(4,8); val d9 = Array(4,9); val d10 = Array(4,10)
  val g2 = Array(7,2); val g3 = Array(7,3); val g4 = Array(7,4); val g5 = Array(7,5); val g7 = Array(7,7); val g8 = Array(7,8); val g9 = Array(7,9); val g10 = Array(7,10)
  
  def selling(){
    val saleman = new SalesmanDB
    saleman mainOne()
  }
}