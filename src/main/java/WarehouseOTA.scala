/**
 * @author sraspin
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage


object WarehouseOTA extends JFXApp{
  stage = new PrimaryStage
  val gui: GUI = new GUI(stage)
  gui show()
  //val doit = new Salesman
  //doit createStage
}