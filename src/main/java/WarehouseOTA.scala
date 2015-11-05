/**
 * @author sraspin
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
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


object WarehouseOTA extends JFXApp{
  stage = new PrimaryStage {
    title = "Raspin LogIn"
    stage width = 300
    stage height = 400
    scene = new Scene {
      root = new BorderPane {
        padding = Insets(25)
        content = new HBox {
         children = Seq(
           new GridPane {
             add(new Label("asdfsdf"), 1, 1)
             add(new Label("bsdfsdf"), 1, 2)
             }
          )
        }
        //right = new Label("Howdy Doodly")
        val data = new Database
        val a = new Employee
        
        def newmeth (n:Int) {
          if(n <= 1){
            val s = a loggingIn(data connection)
            //left = new Label(s)
          } else {
            newmeth(n-1)
          }
        }
        newmeth(2)
      }
    }
  }
}