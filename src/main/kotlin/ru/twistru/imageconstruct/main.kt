package ru.twistru.imageconstruct

import javafx.embed.swing.SwingFXUtils.fromFXImage
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.SplitPane
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.net.URL
import java.util.*
import javax.imageio.ImageIO

class MainActivity : SplitPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var brightnessButton: Button

    @FXML
    private lateinit var addImageButton: Button

    @FXML
    private lateinit var addTextButton: Button

    @FXML
    private lateinit var blurFilterButton: Button

    @FXML
    private lateinit var floatButton: Button

    @FXML
    private lateinit var globalOutputImageView: ImageView

    @FXML
    private lateinit var grayFilterButton: Button

    @FXML
    private lateinit var inputImageButton: Button

    @FXML
    private lateinit var intButton: Button

    @FXML
    private lateinit var invertFilterButton: Button

    @FXML
    private lateinit var nodeContainer: AnchorPane

    @FXML
    private lateinit var sepiaFilterButton: Button

    @FXML
    private lateinit var stringButton: Button

    @FXML
    private lateinit var transformMoveButton: Button

    @FXML
    private lateinit var transformRotateButton: Button

    @FXML
    private lateinit var transformScaleButton: Button

    @FXML
    private lateinit var saveImageButton: Button

    @FXML
    fun initialize() {
        floatButton.onAction = EventHandler {
            nodeContainer.children.add(FloatClass())
        }
        intButton.onAction = EventHandler {
            nodeContainer.children.add(IntClass())
        }
        stringButton.onAction = EventHandler {
            nodeContainer.children.add(StringClass())
        }
        inputImageButton.onAction = EventHandler {
            nodeContainer.children.add(InputImage())
        }
        addTextButton.onAction = EventHandler {
            nodeContainer.children.add(AddText())
        }
        addImageButton.onAction = EventHandler {
            nodeContainer.children.add(AddImage())
        }
        brightnessButton.onAction = EventHandler {
            nodeContainer.children.add(BrightnessClass())
        }
        grayFilterButton.onAction = EventHandler {
            nodeContainer.children.add(GrayFilterClass())
        }
        sepiaFilterButton.onAction = EventHandler {
            nodeContainer.children.add(SepiaFilterClass())
        }
        invertFilterButton.onAction = EventHandler {
            nodeContainer.children.add(InvertFilterClass())
        }
        blurFilterButton.onAction = EventHandler {
            nodeContainer.children.add(BlurFilterClass())
        }
        transformMoveButton.onAction = EventHandler {
            nodeContainer.children.add(TransformMove())
        }
        transformRotateButton.onAction = EventHandler {
            nodeContainer.children.add(TransformRotate())
        }
        transformScaleButton.onAction = EventHandler {
            nodeContainer.children.add(TransformScale())
        }
        saveImageButton.onAction = EventHandler {
            val fileChooser = FileChooser()
            fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"))
            val file = fileChooser.showSaveDialog(Stage())
            ImageIO.write(fromFXImage(this.globalOutputImageView.image, null), "png", file)
        }
        val tmp = EndPointClass()
        tmp.imageV = this.globalOutputImageView
        nodeContainer.children.add(tmp)
    }

    init {
        val tmp = FXMLLoader(javaClass.getResource("main.fxml"))
        tmp.setRoot(this)
        tmp.setController(this)
        tmp.load<Any>()
    }

}
