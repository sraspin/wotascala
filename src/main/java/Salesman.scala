

/**
 * @author sraspin
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, GridPane, HBox, StackPane}
import scalafx.scene.paint.Color
import scalafx.event.ActionEvent
import scalafx.scene.shape.Rectangle
import Database.SalesmanDB

class Salesman() extends JFXApp{
  
  def selling(){
    val salesman = new SalesmanDB
    val a = salesman getPositions()
    salesman dijkstraMain(0, a)
  }
  
  def createStage: PrimaryStage = {
    stage = new PrimaryStage{
      title = "Raspin Travelling Salesman"
      width = 500
      height = 500
      scene = new Scene{
        root = new BorderPane{
          center = centrePane()
          //right = rightPane()
          //top = topPane()
        }
      }
    }
    stage
  }
  
  def centrePane()= new HBox{
    children = Seq(
      new GridPane{
        hgap = 1
        vgap = 1
        padding = Insets(20, 75, 10, 10)
        def addingRect(a: Int, b: Int, x: Int, y: Int){
          val c = theColour(a, b)
          if(b < y){
            if(a < x){
              add(createRect(c), a, b)
              addingRect(a + 1, b, x, y)
            } else {
              addingRect(a-7, b + 1, x, y)
            }
          }
        }
        addingRect(0, 0, 7, 11)
      }
    )
  }
  
  def createRect(a: Color) = new Rectangle{
      width = 20
      height = 20
      fill = a
  }
  
  def theColour(a: Int, b: Int): Color = {
    if(a==0||a==3||a==6||b==0||b==5||b==10){
      Color.LightGray
    } else {
      Color.Black
    }
  }
  
  def rightPane(){
    
  }
  
  def topPane(){
    
  }
}