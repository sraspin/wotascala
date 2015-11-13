package Database

/**
 * @author sraspin
 */

import java.sql.SQLException
import scala.collection.mutable.ArrayBuffer
import scala.math.abs

class SalesmanDB {
  val db = new Database
  var xpos = new ArrayBuffer[Int]
  var ypos = new ArrayBuffer[Int]
  var current = Array(4,11)       //Array(x coordinate, y coordinate)
  var nextCurrent = Array(0,0)    //Array(n value, length)
  var l = new ArrayBuffer[Int]
  
  def getPositions(): Int = {
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
     xpos length
  }
  
  
  def dijkstraMain(n: Int, o: Int){
    if(n < o){
      //println(xpos length)
      dijkstraDist(0, (xpos length))
      nextCurrent(0) = 0
      nextCurrent(1) = l(0)
      dijkstraNext(0, l length)
      current(0) = xpos(nextCurrent(0))
      current(1) = ypos(nextCurrent(0))
      println(current(0) + ", " + current(1))
      l(nextCurrent(0)) = l((l length)-1)
      xpos(nextCurrent(0)) = xpos((l length)-1)
      ypos(nextCurrent(0)) = ypos((l length)-1)
      xpos remove((l length)-1)
      ypos remove((l length)-1)
      resetArray(0, (l length))
      dijkstraMain(n + 1, o)
    }
  }
  
  def dijkstraDist(n: Int, i: Int){
    if(n < i){
      l += calculate(current(0),current(1),xpos(n),ypos(n))
      println(n + ": " + l(n))
      dijkstraDist(n + 1, i)
    }
  }
  
  
  def dijkstraNext(n: Int, i: Int){
    if(n < i){
      if(l(n) < nextCurrent(1)){
        nextCurrent(0) = n
        nextCurrent(1) = l(n)
      }
      dijkstraNext(n + 1, i)
    }
  }
  
  def resetArray(a: Int, b: Int){
    if(a < b){
      l remove(0)
      resetArray(a + 1, b)
    }
  }
  
  def calculate(a: Int, b: Int, x: Int, y: Int): Int = {
    var distance: Int = 0
    
    if((a==1 || a==4 || a==7) && (x==1 || x==4 || x==7) && a!=x){
      if(((1<b && b<6) && (1<y && y<6)) || ((6<b && b<11) && (6<y && y<11))){
        if(b==y){
          if(b==2||b==5||b==7||b==10){
            distance += 2
          } else {
            distance += 4
          }
        } else if((b%3!=0 && b%4!=0) || (y%3!=0 && y%4!=0)) {
          distance += 2
        } else {
          distance += 4
        }
      }
    }
    if((((a==2||a==3) && (x==2||x==3)) || ((a==5||a==6) && (x==5||x==6))) && b!=y){
      distance += 2
    }
    
    val fx = abs(x - a)
    val fy = abs(y - b)
    
    distance += (fx + fy)
    
    distance
  }
}