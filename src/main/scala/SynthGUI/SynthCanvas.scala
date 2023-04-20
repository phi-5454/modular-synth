package SynthGUI

import scalafx.scene.Node
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.Region
import scalafx.scene.shape.Rectangle

import scala.util.Try
// LayoutChildren does not work in the scala wrapper
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color

// from http://fxexperience.com/2014/05/resizable-grid-using-canvas/
class SynthCanvas extends Pane:
  private val XSpacing = 50.0
  private val YSpacing = 40.0
  private val LineThickness = 3
  val canvasSize: (Int, Int) = (4000,3000)
  this.setMinSize(canvasSize._1, canvasSize._2)
  val grid = this.makeGrid()

  // prevent children from going out of bounds
  private val clip:Rectangle = Rectangle(this.getWidth + 500, this.getHeight + 500)
  //this.setClip(clip)

  // If the node is out of bounds, set it into bounds.
  def restrictToBounds(node:Region) =
    // Bounds of background grid in local coords
    val bounds =  this.sceneToLocal(grid.localToScene(grid.getLayoutBounds))
    node.translateX = math.max(math.min(node.getTranslateX, bounds.getMaxX - node.getWidth), bounds.getMinX)
    node.translateY = math.max(math.min(node.getTranslateY, bounds.getMaxY - node.getHeight), bounds.getMinY)


  // The latest known mouse position
  private var localMousePos_ = (0.0,0.0)
  def localMousePos = localMousePos_

  private def makeGrid() =
    // We know beforehand how big the pane is.
    val w = canvasSize._1
    val h = canvasSize._2

    val grid = new Canvas(canvasSize._1, canvasSize._2)

    // Grid should not catch any mouse events
    grid.setMouseTransparent(true)

    // drawing
    val g = grid.getGraphicsContext2D
    g.clearRect(0, 0, w, h)
    g.setFill(Color.gray(0, 0.5))
    g.fillRect(0,0,w,h)
    g.setFill(Color(1,1,1,0.2))


    val a = LazyList.iterate(0.0)(_ + XSpacing)
    val b = LazyList.iterate(0.0)(_ + YSpacing)
    for x <- a.takeWhile(_ < w)
        y <- b.takeWhile(_ < h) do
       // 1.6180... is the Golden ratio. Such an offset Looks prettier and more coherent!A
      val offsetY = if (y % (2 * YSpacing)) == 0 then XSpacing / 1.618034 else 0
      //val offsetY = 0
      // We have a dotted background for the meantime
      g.fillOval(x - LineThickness + offsetY,
        y - LineThickness, 2 * LineThickness, 2 * LineThickness)

    this.children += grid
    grid.toBack()
    grid
  end makeGrid

  this.onMouseMoved = event =>
    this.localMousePos_ = (event.getX, event.getY)

end SynthCanvas

