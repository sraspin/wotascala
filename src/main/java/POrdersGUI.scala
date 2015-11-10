/**
 * @author sraspin
 */

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.Scene.sfxScene2jfx
import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView, Button, Label}
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.layout.{BorderPane, GridPane, HBox, VBox, StackPane}
import scalafx.event.ActionEvent
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import Database.{POrdersDB, AnOrderDB, StatusUpdateDB, AddStockDB}
import Entities.{PurchaseOrder, APOrder}
import scalafx.stage.Popup
import scalafx.scene.control.PasswordField
import scalafx.scene.control.TextField


class POrdersGUI extends JFXApp{
  var currentPOrder: Int = 0
  
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
        //println(table.getSelectionModel.selectedItemProperty.get.idPOrder.value)
      } catch {
        case e : NullPointerException => e printStackTrace
      }
    }
    children = Seq(
      table
    )
  }
  
  def rightPane() = new HBox{
    val sudb = new StatusUpdateDB
    val asdb = new AddStockDB
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
          padding = Insets(5,6,5,6)
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
            text = "Create new order"
            onAction = {
              e: ActionEvent => {
                asdb createOrder()
                createStage
              }
            }
          }
        add(viewButton, 2, 0)
        add(statupButton, 2, 1)
        add(statup2Button, 2, 2)
        add(statup3Button, 2, 3)
        add(newOrderButton, 2, 4)
      }
    )
  }
  
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
          top = new HBox{
            padding = Insets(10,10,10,10)
            val editButton = new Button{
              padding = Insets(5,5,5,5)
              text = "Edit Order"
              onAction = {
                e: ActionEvent => {
                  val popUp = popup2()
                  popUp.show(stage,
                  (stage.width() - popUp.width()) / 2.0 + stage.x(),
                  (stage.height() - popUp.height()) / 2.0 + stage.y())
                }
              }
            }
            children = Seq(
              editButton
            )
          }
          center = new HBox{
            padding = Insets(10, 10, 10, 10)
            val aodb = new AnOrderDB
            val order2 = aodb getPOrder(currentPOrder)
            val table2 = new TableView[APOrder](order2){
              columns ++= List(
                new TableColumn[APOrder, Int]{
                  text = "Purchase Number"
                  cellValueFactory = {_.value.orderNo}
                  prefWidth = 120
                },
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
  
  def popup2() = new Popup {
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
                  add(prodId, 0, 0)
                  add(quantity, 0, 1)
                  add(updatePOrder(prodId, quantity),0,2)
                }
              )
            }
            bottom = new Button("close") {
              onAction = {e: ActionEvent => inner.hide()}
              alignmentInParent = Pos.Center
              margin = Insets(10, 0, 10, 0)
            }
          }
        )
      }.delegate
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
  def updatePOrder(prodId: TextField, quantity: TextField): Button = {
    val finalButton = new Button("Update"){
      onAction = (ae: ActionEvent) => {
        val prid: Int = prodId.text.getValue() toInt
        val quan: Int = quantity.text.getValue() toInt
        val atsdb = new AddStockDB
        atsdb addToOrder(prid, quan)
        createPopup()
      }
    }
    finalButton
  }
}