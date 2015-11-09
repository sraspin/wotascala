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
import Database.COrdersDB
import Database.AnOrderDB
import Entities.CustomerOrder
import Entities.AnOrder
import scalafx.stage.Popup


class COrdersGUI extends JFXApp{
  var currentCOrder: Int = 0
  
  def createStage: PrimaryStage = {
    stage = new PrimaryStage{
      title = "Raspin Customer Orders"
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
        println(table.getSelectionModel.selectedItemProperty.get.idCOrder.value)
      } catch {
        case e : NullPointerException => e printStackTrace
      }
    }
    children = Seq(
        table
    )
  }
  
  def rightPane() = new HBox{
    children = Seq(
      new GridPane{
        hgap = 10
        vgap = 10
        padding = Insets(20, 75, 10, 10)
        val valueButton = new Button{
          text = "View Order"
          onAction = {
            e: ActionEvent => {
              val orderPopup = createPopup("Order Number " + currentCOrder)
              orderPopup.show(stage,
              (stage.width() - orderPopup.width()) / 2.0 + stage.x(),
              (stage.height() - orderPopup.height()) / 2.0 + stage.y())
            }
          }
        }
        add(valueButton,2,0)
      }
    )
  }
  
  def createPopup(writing: String) = new Popup {
    inner =>
    content.add(new StackPane {
      children = List(
        new Rectangle {
          width = 300
          height = 200
          arcWidth = 20
          arcHeight = 20
          fill = Color.LightGray
          stroke = Color.Gray
          strokeWidth = 2
        },
        new BorderPane {
          center = new HBox{
            val aodb = new AnOrderDB
            val order2 = aodb getOrder(currentCOrder)
            val table2 = new TableView[AnOrder](order2){
              columns ++= List(
                new TableColumn[AnOrder, Int]{
                  text = "Order Number"
                  cellValueFactory = {_.value.idOrder}
                  prefWidth = 120
                },
                new TableColumn[AnOrder, Int]{
                  text = "Customer Order ID"
                  cellValueFactory = {_.value.idProduct}
                  prefWidth = 120
                },
                new TableColumn[AnOrder, Int]{
                  text = "Product ID"
                  cellValueFactory = {_.value.Quantity}
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
          top = new Label {
            text = writing
            wrapText = true
            maxWidth = 280
            maxHeight = 140
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