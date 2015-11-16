/**
 * @author sraspin
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import com.qa.gui.GUI


object WarehouseOTA extends JFXApp{
  stage = new PrimaryStage
  val gui: GUI = new GUI(stage)
  gui show()
}