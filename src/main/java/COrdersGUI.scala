/**
 * @author sraspin
 */

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView, Button, Label}
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.{BorderPane, GridPane, HBox, VBox, StackPane}
import scalafx.event.ActionEvent
import Database.{COrdersDB, AnOrderDB, StatusUpdateDB}
import Entities.{CustomerOrder, AnOrder}
import scalafx.stage.Popup


class COrdersGUI extends JFXApp{
  var currentCOrder: Int = 0
  
  
  /**
   * Creating the stage and scene for Customer Orders page
   * Calling methods for the central, right and top parts of BorderPane
   */
  def createStage: PrimaryStage = {
    stage = new PrimaryStage{
      title = "Raspin Customer Orders"
      width = 500
      height = 500
      scene = new Scene{
        root = new BorderPane{
          center = centrePane()
          right = rightPane()
          top = topPane()
        }
      }
    }
    stage
  }
  
  
  /**
   * Calls method that pulls customer order info from database
   * Creates a table of all customer orders
   * Saves the order ID of an order that is clicked on
   */
  def centrePane() = new HBox {
    val db = new COrdersDB
    val order = db getCOrders()
    val table = new TableView[CustomerOrder](order){
      columns ++= List(
        new TableColumn[CustomerOrder, Int]{
          text = "Customer Order ID"
          cellValueFactory = {_.value.idCOrder}
          prefWidth = 130
        },
        new TableColumn[CustomerOrder, String]{
          text = "Order Status"
          cellValueFactory = {_.value.status}
          prefWidth = 120
        }
      )
    }
    table onMouseClicked = handle{
      try{
        currentCOrder = table.getSelectionModel.selectedItemProperty.get.idCOrder.value
        //println(table.getSelectionModel.selectedItemProperty.get.idCOrder.value)
      } catch {
        case e : NullPointerException => e printStackTrace
      }
    }
    children = Seq(
      table
    )
  }
  
  
  /**
   * Creates a GridPane with various buttons
   * viewButton calls method to create pop-up with individual order info
   * the "statup" Buttons change the status of the highlighted order
   */
  def rightPane() = new HBox{
    val sudb = new StatusUpdateDB
    children = Seq(
      new GridPane{
        hgap = 10
        vgap = 10
        padding = Insets(20, 75, 10, 10)
        val viewButton = new Button{
          padding = Insets(5,13,5,13)
          text = "View Order"
          onAction = {
            e: ActionEvent => {
              val orderPopup = createPopup()
              orderPopup.show(stage,
              (stage.width() - orderPopup.width()) / 2.0 + stage.x(),
              (stage.height() - orderPopup.height()) / 2.0 + stage.y())
            }
          }
        }
        val statupButton = new Button{
          padding = Insets(5,10,5,10)
          text = "Status: Received"
          onAction = {
            e: ActionEvent => {
              sudb statUpdate("C","Received", currentCOrder)
              createStage
            }
          }
        }
        val statup2Button = new Button{
          padding = Insets(5,5,5,6)
          text = "Status: Processing"
          onAction = {
            e: ActionEvent => {
              sudb statUpdate("C","Processing", currentCOrder)
              createStage
            }
          }
        }
        val statup3Button = new Button{
          padding = Insets(5,6,5,6)
          text = "Status: In Delivery"
          onAction = {
            e: ActionEvent => {
              sudb statUpdate("C","In Delivery", currentCOrder)
              createStage
            }
          }
        }
        val statup4Button = new Button{
          padding = Insets(5,9,5,9)
          text = "Status: Delivered"
          onAction = {
            e: ActionEvent => {
              sudb statUpdate("C","Delivered", currentCOrder)
              createStage
            }
          }
        }
        add(viewButton, 2, 0)
        add(statupButton, 2, 1)
        add(statup2Button, 2, 2)
        add(statup3Button, 2, 3)
        add(statup4Button, 2, 4)
      }
    )
  }
  
  
  /**
   * creates rectangle (acts as a scene)
   * calls method that pulls individual order info from database
   * creates a table that contains the individual order info
   */
  def createPopup() = new Popup {
    inner =>
    content.add(new StackPane {
      children = List(
        new Rectangle {
          width = 500
          height = 580
          arcWidth = 20
          arcHeight = 20
          fill = Color.LightGray
          stroke = Color.Gray
          strokeWidth = 2
        },
        new BorderPane {
          center = new HBox{
            padding = Insets(10, 10, 10, 10)
            val aodb = new AnOrderDB
            val order2 = aodb getOrder(currentCOrder)
            val table2 = new TableView[AnOrder](order2){
              columns ++= List(
                new TableColumn[AnOrder, Int]{
                  text = "Order Number"
                  cellValueFactory = {_.value.orderNo}
                  prefWidth = 120
                },
                new TableColumn[AnOrder, Int]{
                  text = "Customer Order ID"
                  cellValueFactory = {_.value.idOrder}
                  prefWidth = 120
                },
                new TableColumn[AnOrder, Int]{
                  text = "Product ID"
                  cellValueFactory = {_.value.idProduct}
                  prefWidth = 120
                },
                new TableColumn[AnOrder, Int]{
                  text = "Quantity"
                  cellValueFactory = {_.value.Quantity}
                  prefWidth = 120
                }
              )
            }
            children = Seq(
              table2
            )
          }
          bottom = new Button("OK") {
            onAction = {e: ActionEvent => inner.hide()}
            alignmentInParent = Pos.Center
            margin = Insets(10, 0, 10, 0)
          }
        }
      )
    }.delegate
    )
  }
  
  
  /**
   * Inserts a Label at the top of the page that returns user to title screen
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
}