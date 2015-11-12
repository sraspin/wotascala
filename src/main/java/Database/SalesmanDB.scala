package Database

/**
 * @author sraspin
 */

import java.sql.SQLException
import scala.collection.mutable.ArrayBuffer

class SalesmanDB {
  val db = new Database
  var xpos = new ArrayBuffer[Int]
  var ypos = new ArrayBuffer[Int]
  var current = Array(4,11)
  var nextCurrent = Array(0,0)
  var l = Array[Int](xpos length)
  
  def getPositions(){
     try{
       val conn = db connect()
       
       val statement = conn createStatement()
       val rs = statement executeQuery("SELECT * FROM products")
       while(rs next){
         xpos += rs getInt("xpos")
         ypos += rs getInt("ypos")
       }
       conn close()
     } catch {
       case e : SQLException => e printStackTrace
     }
  }
  
  def dijkstraDist(i: Int){
    var n: Int = 0
    if(n < i){
      l(n) = calculate(current(0),current(1),xpos(n),ypos(n))
      n += 1
    } else {
      dijkstraDist(i-1)
    }
  }
  
  def dijkstraNext(i: Int){
    nextCurrent(1) = l(0)
    dijkstraNext1(i)
  }
  
  def dijkstraNext1(i: Int){
    var m: Int = 0
    
    if(m < i){
      if(l(m) < nextCurrent(1)){
        nextCurrent(0) = m
      }
      dijkstraNext1(m+1)
    }
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