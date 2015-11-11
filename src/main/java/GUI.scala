/**
 * @author sraspin
 */
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.paint._
import scalafx.stage.StageStyle
import scalafx.scene.control.Button
import scalafx.scene.shape.Circle
import scalafx.scene.layout.{BorderPane, GridPane, HBox}
import scalafx.event.ActionEvent
import Database._
import scalafx.scene.control.PasswordField
import scalafx.scene.control.TextField

class GUI (stage:PrimaryStage) {
  stage title = "Raspin LogIn"
  stage width = 200
  stage height = 175
  
  val username = new TextField() {
    promptText = "Username:"
  }
  val password = new PasswordField() {
    promptText = "Password:"
  }
  
  
  /**
   * Making the scene visible
   */
  def show() {
    stage setScene(createScene)
  }
  
  
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
      add(username, 0, 0)
      add(password, 0, 1)
      add(createLogin(username, password), 0, 2)
    }
  }
  
  
  /**
   * Creating the login button and function
   */
  def createLogin(usernameField:TextField, passwordField:PasswordField) : Button = {
    val button = new Button{
       text = ("Log In")
       onAction = (ae: ActionEvent) => {
         val user:String = usernameField.text getValue()
         val pass:String = passwordField.text getValue()
         val login = new LoginDB(user, pass)
         if(login Login()){
           val home = new HomeScreenGUI
           val homeStage = home HomeStage
         } else {
           show()
         }
       }
    }
    button
  }
}