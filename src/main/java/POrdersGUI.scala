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
import javafx.scene.control.{TextField, PasswordField}
import scalafx.event.ActionEvent
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import Database.{POrdersDB, AnOrderDB, StatusUpdateDB}
import Entities.{PurchaseOrder, AnOrder, APOrder}
import scalafx.stage.Popup


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
        add(viewButton, 2, 0)
        add(statupButton, 2, 1)
        add(statup2Button, 2, 2)
        add(statup3Button, 2, 3)
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
}