/**
 * @author sraspin
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.Scene.sfxScene2jfx
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.stage.StageStyle
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableCell, TableColumn, TableView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Label
import scalafx.scene.layout.GridPane

class GUI (stage:PrimaryStage) {
  stage title = "Raspin LogIn"
  stage width = 300
  stage height = 400
  def createScene():Scene = {
    val scene = new Scene {
      content = new HBox {
        children = Seq(
          createGridPane()
        )
      }
    }
    scene
  }
  def createGridPane():GridPane={
    new GridPane {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)
      
      add(new Label("hello"), 0, 0)
    }
  }
  def show() {
    stage setScene(createScene)
  }
}