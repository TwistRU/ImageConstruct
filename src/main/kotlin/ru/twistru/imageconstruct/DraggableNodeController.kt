package ru.twistru.imageconstruct

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Point2D
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

open class DraggableNodeController : BorderPane() {
    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    lateinit var deleteNode: Button

    @FXML
    lateinit var outputImageView: ImageView

    @FXML
    lateinit var nodeName: Label

    @FXML
    lateinit var inputVBox: VBox

    @FXML
    private lateinit var mainLayout: BorderPane

    @FXML
    lateinit var nodeContentVBox: VBox

    @FXML
    lateinit var outputVBox: VBox

    @FXML
    fun initialize() {
        id = UUID.randomUUID().toString()
        nodeContentVBox.onDragDetected = EventHandler { mouseEvent ->
            val offset = Point2D(
                mouseEvent.sceneX - mainLayout.layoutX,
                mouseEvent.sceneY - mainLayout.layoutY
            )
            mainLayout.parent.onDragOver = EventHandler { dragEvent ->
                mainLayout.layoutX = dragEvent.sceneX - offset.x
                mainLayout.layoutY = dragEvent.sceneY - offset.y
                dragEvent.acceptTransferModes(*TransferMode.ANY)
                dragEvent.consume()
            }
            mainLayout.parent.onDragDropped = EventHandler { dragEvent ->
                println("Drag drop")
                mainLayout.parent.onDragOver = null
                mainLayout.parent.onDragDropped = null
                dragEvent.isDropCompleted = true
                dragEvent.consume()
            }
            println("Drag detected " + mouseEvent.sceneX.toString() + " " + mouseEvent.sceneY.toString())
            val content = ClipboardContent()
            content.putString("node")
            mainLayout.startDragAndDrop(*TransferMode.ANY).setContent(content)
            mouseEvent.consume()
        }

        nodeContentVBox.onDragDone = EventHandler { dragEvent ->
            println("Drag done" + dragEvent.sceneX.toString() + " " + dragEvent.sceneY.toString())
            mainLayout.parent.onDragOver = null
            mainLayout.parent.onDragDropped = null
            dragEvent.consume()
        }
        deleteNode.onAction = EventHandler {
            this.deleteMyself()
        }
    }

    public fun deleteMyself() {
        println("DELETE")
        inputVBox.children.forEach { i ->
            (i as NodeLinkController).deleteAllNodes()
        }
        outputVBox.children.forEach { i ->
            (i as NodeLinkController).deleteAllNodes()
        }
        (this.parent as Pane).children.remove(this)
    }

    private fun getSerialCoordinates(): String {
        return "${this.layoutX} ${this.layoutY}"
    }

    fun getCallableClassName(): String {
        return this.javaClass.simpleName
    }

    open fun toSerial(): String {
        return this.getSerialCoordinates()
    }

    open fun fromSerial(args: List<String>) {
        this.layoutX = args[0].toDouble()
        this.layoutY = args[1].toDouble()
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("DraggableNode.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<DraggableNodeController>()
    }
}
