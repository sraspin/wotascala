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
import scalafx.scene.control.{TableColumn, TableView, Button, Label}
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.{BorderPane, GridPane, HBox, StackPane}
import scalafx.event.ActionEvent
import scalafx.stage.Popup
import scalafx.scene.control.TextField
import Database.{AnOrderDB, StatusUpdateDB, AddStockDB, RemoveStockDB, POrdersDB}
import Entities.{APOrder, PurchaseOrder}
import scalafx.beans.property.StringProperty.sfxStringProperty2jfx
import scalafx.scene.control.TableView.sfxTableView2jfx



class POrdersGUI extends JFXApp{
  var currentPOrder: Int = 0
  var indVal: Int = 0
  
  
  /**
   * @return Primary Stage
   * 
   * Creating the stage and scene for Purchase Orders page
   * Calling methods for the central, right and top parts of BorderPane
   */
  def createStage: PrimaryStage = {
    stage = new PrimaryStage{
      title = "Raspin Purchase Orders"
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
   * Calls method that pulls purchase order info from database
   * Creates a table of all purchase orders (columns: Purchase Order ID, Status)
   * Stores the order ID of the last order that was clicked on
   */
  def centrePane() = new HBox {
    val db = new POrdersDB
    val order = db getPOrders()
    val table = new TableView[PurchaseOrder](order){
      columns ++= List(
        new TableColumn[PurchaseOrder, Int]{
          text = "Purchase Order ID"
          cellValueFactory = {_.value.idPOrder}
          prefWidth = 130
        },
        new TableColumn[PurchaseOrder, String]{
          text = "Order Status"
          cellValueFactory = {_.value.pOstatus}
          prefWidth = 120
        }
      )
    }
    table onMouseClicked = handle{
      try{
        currentPOrder = table.getSelectionModel.selectedItemProperty.get.idPOrder.value
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
   * newOrderButton creates a new, empty purchase order in the database
   * removeOrderButton removes the highlighted order
   */
  def rightPane() = new HBox{
    val sudb = new StatusUpdateDB
    val asdb = new AddStockDB
    val rsdb = new RemoveStockDB
    children = Seq(
      new GridPane{
        hgap = 10
        vgap = 10
        padding = Insets(20, 75, 10, 10)
        val viewButton = new Button{
          padding = Insets(5,15,5,14)
          text = "View Order"
          onAction = {
            e: ActionEvent => {
              val orderPopup = createPopup(true)
              orderPopup.show(stage,
              (stage.width() - orderPopup.width()) / 2.0 + stage.x(),
              (stage.height() - orderPopup.height()) / 2.0 + stage.y())
            }
          }
        }
        val statupButton = new Button{
          padding = Insets(5,12,5,12)
          text = "Status: Pending"
          onAction = {
            e: ActionEvent => {
              sudb statUpdate("P","Pending", currentPOrder)
              createStage
            }
          }
        }
        val statup2Button = new Button{
          padding = Insets(5,5,5,6)
          text = "Status: Order Sent"
          onAction = {
            e: ActionEvent => {
              sudb statUpdate("P","Order Sent", currentPOrder)
              createStage
            }
          }
        }
        val statup3Button = new Button{
          padding = Insets(5,11,5,10)
          text = "Status: Received"
          onAction = {
            e: ActionEvent => {
              sudb statUpdate("P","Received", currentPOrder)
              createStage
            }
          }
        }
        val newOrderButton = new Button{
          padding = Insets(5,5,5,5)
            text = "Add new order"
            onAction = {
              e: ActionEvent => {
                asdb createOrder()
                createStage
              }
            }
          }
        val removeOrderButton = new Button{
          padding = Insets(5,7,5,7)
            text = "Remove order"
            onAction = {
              e: ActionEvent => {
                rsdb removeOrder(currentPOrder)
                createStage
              }
            }
          }
        add(viewButton, 2, 0)
        add(statupButton, 2, 5)
        add(statup2Button, 2, 6)
        add(statup3Button, 2, 7)
        add(newOrderButton, 2, 1)
        add(removeOrderButton, 2, 2)
      }
    )
  }
  
  
  /**
   * creates a rectangle (acts as a scene)
   * editButton and removeButton appear at the top if condition is true
   * calls method that pulls individual order info from database
   * creates a table that contains the individual order info
   * creates a button labelled "OK" which hides the pop-up when clicked on
   */
  def createPopup(insight: Boolean) = new Popup {
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
          if(insight == true){
            top = new HBox{
              padding = Insets(10,10,10,10)
              val editButton = new Button{
                padding = Insets(5,5,5,5)
                text = "Add to Order"
                onAction = {
                  e: ActionEvent => {
                    val popUp = popup2()
                    popUp.show(stage,
                    (stage.width() - popUp.width()) / 2.0 + stage.x(),
                    (stage.height() - popUp.height()) / 2.0 + stage.y())
                    inner.hide()
                  }
                }
              }
              val removeButton = new Button{
                padding = Insets(5,5,5,5)
                text = "Remove Order"
                onAction = {
                  e: ActionEvent => {
                    removeFunction()
                    inner.hide()
                    
                  }
                }
              }
              children = Seq(
                new GridPane{
                  add(editButton, 0, 0)
                  add(removeButton, 1, 0)
                }
              )
            }
          }
          center = new HBox{
            padding = Insets(10, 10, 10, 10)
            val aodb = new AnOrderDB
            val order2 = aodb getPOrder(currentPOrder)
            val table2 = new TableView[APOrder](order2){
              columns ++= List(
                /*new TableColumn[APOrder, Int]{
                  text = "Purchase Number"
                  cellValueFactory = {_.value.orderNo}
                  prefWidth = 120
                },*/
                new TableColumn[APOrder, Int]{
                  text = "Purchase Order ID"
                  cellValueFactory = {_.value.idOrder}
                  prefWidth = 120
                },
                new TableColumn[APOrder, Int]{
                  text = "Product ID"
                  cellValueFactory = {_.value.idProduct}
                  prefWidth = 120
                },
                new TableColumn[APOrder, Int]{
                  text = "Quantity"
                  cellValueFactory = {_.value.Quantity}
                  prefWidth = 120
                }
              )
            }
            table2 onMouseClicked = handle{
              try{
                indVal = table2.getSelectionModel.selectedItemProperty.get.orderNo.value
              } catch {
                case e : NullPointerException => e printStackTrace
              }
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
   * Appears if the editButton is clicked on
   * asks the user for product ID and quantity values
   * Adds button that accepts the users input and adds it into the database
   * Adds "Close" button that hides pop-up when clicked on
   */
  def popup2() = new Popup {
    inner =>
      content.add(new StackPane {
        children = List(
          new Rectangle {
            width = 250
            height = 250
            arcWidth = 20
            arcHeight = 20
            fill = Color.LightGray
            stroke = Color.Gray
            strokeWidth = 2
          },
          new BorderPane{
            center = new HBox {
              val prodId = new TextField() {
                promptText = "Product ID:"
              }
              val quantity = new TextField() {
                promptText = "Quantity:"
              }
              children = Seq(
                new GridPane{
                  hgap = 10
                  vgap = 10
                  padding = Insets(20, 100, 10, 10)
                  add(new Label("Enter Product ID"), 0, 0)
                  add(prodId, 0, 1)
                  add(new Label("Enter a quantity"), 0, 3)
                  add(quantity, 0, 4)
                  add(updatePOrder(prodId, quantity),0,6)
                }
              )
            }
            bottom = new Button("Close") {
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
   * @return : Button
   * 
   * Stores the given parameters into the database
   * Refreshes the first pop-up to contain the recent input
   */
  def updatePOrder(prodId: TextField, quantity: TextField): Button = {
    val finalButton = new Button("Update"){
      onAction = (ae: ActionEvent) => {
        val prid: Int = prodId.text.getValue() toInt
        val quan: Int = quantity.text.getValue() toInt
        val atsdb = new AddStockDB
        atsdb addToOrder(prid, quan, currentPOrder)
        val popup = createPopup(false)
        popup.show(stage,
        (stage.width() - popup.width()) / 2.0 + stage.x(),
        (stage.height() - popup.height()) / 2.0 + stage.y())
      }
    }
    finalButton
  }
  
  
  /**
   * Removes the highlighted order from the database
   * refreshes the pop-up screen
   */
  def removeFunction(){
    val rsdb = new RemoveStockDB
    rsdb removeIndOrder(indVal)
    val showup = createPopup(true)
    showup.show(stage,
    (stage.width() - showup.width()) / 2.0 + stage.x(),
    (stage.height() - showup.height()) / 2.0 + stage.y())
  }
  
  
  /**
   * Inserts a Label "HOME" at the top of the page that returns user to title screen
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