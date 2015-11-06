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
import scalafx.scene.text.Text
import javafx.scene.control.TextField
import javafx.scene.control.PasswordField
import scalafx.scene.control.Button
import scalafx.event.ActionEvent

class GUI (stage:PrimaryStage) {
  stage title = "Raspin LogIn"
  stage width = 300
  stage height = 400
  
  /**
   * Creating a scene to hold the GridPane
   */
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
  
  /**
   * The GridPane that holds the TextFields and Login button
   */
  def createGridPane():GridPane={
    new GridPane {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)
      val e = new Employee
      add(username, 0, 0)
      add(password, 0, 1)
      add(createUsernameField(username, password), 0, 2)
    }
  }
  
  /**
   * Making the scene visible
   */
  def show() {
    stage setScene(createScene)
  }
  
  /**
   * Creating the login button and function
   */
  def createUsernameField(usernameField:TextField, passwordField:PasswordField) : Button = {
    val button = new Button{
       text = ("Log In")
       onAction = (ae: ActionEvent) => {
         val user:String = usernameField getText()
         val pass:String = passwordField getText()
         val data: Database = new Database
         val login = new LogIn(user, pass)
         login Login(data connection)
         println(user + pass)
       }
    }
    button
  }
  
  val username = new TextField() {
    promptTextProperty() = "Username:"
  }
  val password = new PasswordField() {
    promptTextProperty() = "Password:"
  }
}