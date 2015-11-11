/**
 * @author sraspin
 */

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.{GridPane, HBox}
import scalafx.event.ActionEvent


class HomeScreenGUI extends JFXApp{
  
  /**
   * Creates stage and scene
   * Calls HomeStage method
   */
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
  
  
  /**
   * Creates a GridPane
   * Creates buttons to link to different pages of the application
   */
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