

/**
 * @author sraspin
 */
class Salesman() {
  val b1 = Array(2,1); val b6 = Array(2,6); val b11 = Array(2,11)
  val c1 = Array(3,1); val c6 = Array(3,6); val c11 = Array(3,11)
  val e1 = Array(5,1); val e6 = Array(5,6); val e11 = Array(5,11)
  val f1 = Array(6,1); val f6 = Array(6,6); val f11 = Array(6,11)
  val a2 = Array(1,2); val a3 = Array(1,3); val a4 = Array(1,4); val a5 = Array(1,5); val a7 = Array(1,7); val a8 = Array(1,8); val a9 = Array(1,9); val a10 = Array(1,10) 
  val d2 = Array(4,2); val d3 = Array(4,3); val d4 = Array(4,4); val d5 = Array(4,5); val d7 = Array(4,7); val d8 = Array(4,8); val d9 = Array(4,9); val d10 = Array(4,10)
  val g2 = Array(7,2); val g3 = Array(7,3); val g4 = Array(7,4); val g5 = Array(7,5); val g7 = Array(7,7); val g8 = Array(7,8); val g9 = Array(7,9); val g10 = Array(7,10)
  
  def selling(){
    println(f6(0))
    println(b1(1))
  }
  
  def calculate(a1: Int, b1: Int, x1: Int, y1: Int): Int = {
    
    var distance: Int = 0
    var a = a1
    var b = b1
    var x = x1
    var y = y1
    
    if((a==1 || a==4 || a==7) && (x==1 || x==4 || x==7) && a!=x){
      if(((b==2 || b==3 || b==4 || b==5) && (y==2 || y==3 || y==4 || y==5)) || ((b==7 || b==8 || b==9 || b==10) && (y==7 || y==8 || y==9 || y==10))){
        if(b==y){
          if(b==2||b==5||b==7||b==10){
            distance += 2
          } else {
            distance += 4
          }
        } else if(b==2||b==7) {
          distance += 1
          b -= 1
        } else if(y==2||y==7) {
          distance += 1
          y -= 1
        } else if(b==5||b==10){
          distance += 1
          b += 1
        } else if(y==5||y==10){
          distance += 1
          y += 1
        } else {
          distance += 4
        }
      }
    }
    
    if(((b==1||b==6||b==11)&&(y==1||y==6||y==11)) && b!=y){
      if(((a==2||a==3) && (x==2||x==3)) || ((a==5||a==6) && (x==5||x==6))){
        distance += 2
      }
    }
    
    var fx = x - a
    var fy = y - b
    if(fx < 0){
      fx = fx*(-1)
    }
    if(fy < 0){
      fy = fy*(-1)
    }
    
    distance += (fx + fy)
    
    distance
  }
}