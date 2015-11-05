/**
 * @author sraspin
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage


object WarehouseOTA extends JFXApp{
  val data: Database = new Database()
  stage = new PrimaryStage
  val gui: GUI = new GUI(stage)
  gui show()
}