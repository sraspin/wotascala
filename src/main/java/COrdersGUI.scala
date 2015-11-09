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
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.stage.StageStyle
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView, Button, Label}
import scalafx.scene.shape.Circle
import scalafx.scene.layout.{BorderPane, GridPane, HBox, VBox, StackPane}
import javafx.scene.control.{TextField, PasswordField}
import scalafx.event.ActionEvent
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import java.sql.Connection
import Database._
import Entities._
import scalafx.event.ActionEvent
import scalafx.stage.Popup

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.event.{ActionEvent, Event}
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.control.MenuItem._
import scalafx.scene.control.ScrollPane.ScrollBarPolicy
import scalafx.scene.control._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.KeyCombination
import scalafx.scene.layout.{BorderPane, HBox, StackPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.web.{HTMLEditor, WebView}
import scalafx.scene.{Node, Scene}
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