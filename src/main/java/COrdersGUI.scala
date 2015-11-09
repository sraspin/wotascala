/**
 * @author sraspin
 */

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
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
import scalafx.scene.layout.{BorderPane, GridPane, HBox}
import javafx.scene.control.{TextField, PasswordField}
import scalafx.event.ActionEvent
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import java.sql.Connection
import Database._
import Entities._

class COrdersGUI extends JFXApp{
  
  def createStage: PrimaryStage = {
    stage = new PrimaryStage{
      title = "Raspin Customer Orders"
      width = 500
      height = 500
      scene = new Scene{
        root = new BorderPane{
          center = centrePane()
        }
      }
    }
    stage
  }
  
  def centrePane() = new HBox {
    val db = new COrdersDB
    val order = db getCOrders()
    children = Seq(
      new TableView[CustomerOrder](order)
    )
  }
  
  def createList():GridPane={
    new GridPane{
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)
      
    }
  }
}