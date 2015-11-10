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
import Database.{COrdersDB, AnOrderDB, StatusUpdateDB}
import Entities.{CustomerOrder, AnOrder}
import scalafx.stage.Popup


class HomeScreenGUI extends JFXApp{
  
  def HomeStage: PrimaryStage = {
    stage = new PrimaryStage{
      title = "Raspin Home Screen"
       width = 500
      height = 500
      scene = new Scene{
        content = new HBox{
          children = Seq(
            HomeGridPane()
          )
        }
      }
    }
    stage
  }
  
  def HomeGridPane():GridPane={
    new GridPane {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)
      val customerButton = new Button{
        padding = Insets(5,10,5,10)
        text = "Customer Orders"
        onAction = {
          e: ActionEvent => {
            val a = new COrdersGUI
            a createStage
          }
        }
      }
      val purchaseButton = new Button{
        padding = Insets(5,10,5,10)
        text = "Purchase Orders"
        onAction = {
          e: ActionEvent => {
            val b = new POrdersGUI
            b createStage
          }
        }
      }
      add(customerButton, 0, 0)
      add(purchaseButton, 0, 1)
    }
  }
}