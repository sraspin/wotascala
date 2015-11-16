

/**
 * @author sraspin
 */

package com.qa.gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.control.{Button, Label}
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{BorderPane, GridPane, HBox}
import scalafx.scene.paint.Color
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableColumn, TableView, Button, Label}
import scalafx.event.ActionEvent
import scalafx.scene.shape.Rectangle
import com.qa.database.COrdersDB
import com.qa.entities.CustomerOrder
import scala.collection.mutable.ArrayBuffer
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.control.TableView.sfxTableView2jfx
import com.qa.database.SalesmanDB

class Salesman() extends JFXApp{
  
  var currentCOrder: Int = 0
  
  /**
   * @return : PrimaryStage
   * 
   * Creating the stage and scene for the Travelling Salesman page
   * Calling methods and retrieving values from SalesmanDB
   * Calling methods for the central, right and top parts of BorderPane
   */
  def createStage: PrimaryStage = {
    val salesman = new SalesmanDB
    val a = salesman getPositions(currentCOrder)
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
          bottom = bottomPane()
        }
      }
    }
    stage
  }
  
  
  /**
   * Creates a grid of rectangles to replicate the warehouse
   * colours in the rectangles depending on their position
   */
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
  
  
  /**
   * creates individual rectangles for use in "centrePane"
   */
  def createRect(a: Color) = new Rectangle{
      width = 25
      height = 25
      fill = a
  }
  
  
  /**
   * @return : String
   * 
   * Used to label the order of collection (used in "centrePane")
   */
  def theOrder(a: Int, b: Int, z: Int, results: ArrayBuffer[Int]):String = {
    if((results length) <= 1){
      ""
    } else if(a+1 == results(2*z - 2) && b+1 == results(2*z - 1)){
      z toString
    } else if((2*z + 1) < (results length)){
      theOrder(a, b, z + 1, results)
    } else{
      ""
    }
  }
  
  
  /**
   * @return : Color
   * 
   * Colours in the rectangles in certain positions so that they replicate the warehouse
   */
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
  
  
  /**
   * @return : Integer
   * 
   * Calculates the total distance of the travelling salesman algorithm
   */
  def total(i: Int, z: Int, results: ArrayBuffer[Int]): Int = {
    if((results length) <= 1){
      0
    } else {
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
  }
  
  
  /**
   * Creates a new GridPane
   * Inserts a table into the GridPane
   * Stores the order number of the last table element that is clicked on
   */
  def rightPane(results: ArrayBuffer[Int]) = new HBox{
    children = Seq(
      new GridPane{
        hgap = 10
        vgap = 10
        val db = new COrdersDB
        val order = db getCOrders
        val table = new TableView[CustomerOrder](order){
          columns ++=List(
            new TableColumn[CustomerOrder, Int]{
              text = "Customer Order ID"
              cellValueFactory = {_.value.idCOrder}
              prefWidth = 125
            },
            new TableColumn[CustomerOrder, String]{
              text = "Status"
              cellValueFactory = {_.value.status}
              prefWidth = 125
            }
          )
        }
        table onMouseClicked = handle{
          try{
            currentCOrder = table.getSelectionModel.selectedItemProperty.get.idCOrder.value
          } catch {
            case e : NullPointerException => e printStackTrace
          }
        }
        padding = Insets(20,75,10,10)
        
        val viewDistButton = new Button{
          padding = Insets(5,10,5,10)
          text = "Collection Order"
          onAction = {
            e: ActionEvent => {
              createStage
            }
          }
        }
        val t = total((results length)/2, 0, results).toString
        add(new Label("Total Distance: " + t), 0, 0)
        add(viewDistButton, 0, 1)
        add(new Label("Select order, click button, view collection order:"), 0 , 2)
        add(table, 0, 3)
      }
    )
  }
  
  
  /**
   * Creates a Label "Home" that returns the user to the title screen when clicked on
   */
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
  
  
  /**
   * Creates a key to show what each coloured rectangle represents in my warehouse made of rectangles
   */
  def bottomPane() = new HBox{
    children = Seq(
      new GridPane{
        hgap =10
        vgap = 10
        padding = Insets(20, 100, 10, 10)
        def addKey(){
          add(new Label("KEY:"), 0, 0)
          add(createRect(Color Black), 0, 1)
          add(createRect(Color LightGray), 0, 2)
          add(createRect(Color Red), 2, 1)
          add(createRect(Color Blue), 2, 2)
          add(new Label("Shelf"), 1, 1)
          add(new Label("Walkway"), 1, 2)
          add(new Label("Exit"), 3, 1)
          add(new Label("Entrance"), 3, 2)
        }
        addKey
      }
    )
  }
}