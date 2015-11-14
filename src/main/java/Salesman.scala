

/**
 * @author sraspin
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.control.{Button, Label}
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, GridPane, HBox, StackPane}
import scalafx.scene.paint.Color
import scalafx.event.ActionEvent
import scalafx.scene.shape.Rectangle
import Database.SalesmanDB
import scala.collection.mutable.ArrayBuffer
import scalafx.event.ActionEvent
import scalafx.Includes._

class Salesman() extends JFXApp{
  
  def selling(){
    val salesman = new SalesmanDB
    val a = salesman getPositions()
    val results = salesman dijkstraMain(0, a)
  }
  
  def createStage: PrimaryStage = {
    val salesman = new SalesmanDB
    val a = salesman getPositions()
    val results = salesman dijkstraMain(0, a)
    stage = new PrimaryStage{
      title = "Raspin Travelling Salesman"
      width = 500
      height = 500
      scene = new Scene{
        root = new BorderPane{
          center = centrePane(results)
          right = rightPane(results)
          top = topPane()
        }
      }
    }
    stage
  }
  
  def centrePane(results: ArrayBuffer[Int])= new HBox{
    children = Seq(
      new GridPane{
        hgap = 1
        vgap = 1
        padding = Insets(10, 10, 10, 10)
        def addingRect(a: Int, b: Int, x: Int, y: Int){
          val c = theColour(a, b)
          if(b < y){
            if(a < x){
              add(createRect(c), a, b)
              add(new Label("   " + theOrder(a, b, 1, results)), a, b)
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
      width = 25
      height = 25
      fill = a
      Label("hi")
  }
  
  def theOrder(a: Int, b: Int, z: Int, results: ArrayBuffer[Int]):String = {
    if(a+1 == results(2*z - 2) && b+1 == results(2*z - 1)){
      z toString
    } else if((2*z + 1) < (results length)){
      theOrder(a, b, z + 1, results)
    } else{
      ""
    }
  }
  
  def theColour(a: Int, b: Int): Color = {
    if(b==0 && a ==3){
      Color.Red
    } else if(a==3 && b==10){
      Color.Blue
    } else if(a==0||a==3||a==6||b==0||b==5||b==10){
      Color.LightGray
    } else {
      Color.Black
    }
  }
  
  def total(i: Int, z: Int, results: ArrayBuffer[Int]): Int = {
    val s = new SalesmanDB
    if(i == 0){
      val t = z + s.calculate(results(i), results(i + 1), 4, 11)
      t
    } else if(i == (results length)/2){
      total(i - 1, s.calculate(4, 1, results(2*i - 2), results(2*i - 1)), results)
    } else {
      total(i - 1, s.calculate(results(2*i), results(2*i + 1), results(2*i - 2), results(2*i - 1)) + z, results)
    }
  }
  
  def rightPane(results: ArrayBuffer[Int]) = new HBox{
    children = Seq(
      new GridPane{
        hgap = 10
        vgap = 10
        val cogui = new COrdersGUI
        padding = Insets(20,75,10,10)
        val t = total((results length)/2, 0, results).toString
        add(new Label(t), 0, 0)
        add(cogui.centrePane(), 0, 1)
      }
    )
  }
  
  def topPane() = new HBox{
    children = Seq(
      new GridPane{
        hgap =10
        vgap = 10
        padding = Insets(20, 100, 10, 10)
        val homeButton = new Label("HOME")
        homeButton onMouseClicked = handle{
          val h = new HomeScreenGUI
          h HomeStage
        }
        add(homeButton, 0, 0)
      }
    )
  }
}