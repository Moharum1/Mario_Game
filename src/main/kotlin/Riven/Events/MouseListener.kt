package Riven.Events


import org.lwjgl.glfw.GLFW.GLFW_PRESS
import org.lwjgl.glfw.GLFW.GLFW_RELEASE

object MouseListener {
    private var x:Double = 0.0
    private var y:Double = 0.0
    private var scrollX:Double = 0.0
    private var scrollY:Double = 0.0
    private var lastX:Double = 0.0
    private var lastY:Double = 0.0
    private var dx:Double = 0.0
    private var dy:Double = 0.0
    private var mouseButtons:Array<Boolean> = Array(3){false}
    private var isDragging:Boolean = false

    internal fun mouseCallback(window:Long, xpos:Double, ypos:Double){
        lastX = x
        lastY = y

        x = xpos
        y = ypos

        // if there is a mouse pressing event during the mouse movement this means
        // that the mouse is dragging
        isDragging = mouseButtons[0] || mouseButtons[1] || mouseButtons[2]
    }

    internal fun mouseButtonCallback(window:Long, button:Int, action:Int, mods:Int){
        // we check if the button is pressed or released
        if (action == GLFW_PRESS) {
            if (button < mouseButtons.size) mouseButtons[button] = true
        } else if (action == GLFW_RELEASE) {
            mouseButtons[button] = false
            isDragging = false
        }
    }

    internal fun mouseScrollCallback(window:Long, xOffset:Double, yOffset:Double){
        scrollX = xOffset
        scrollY = yOffset
    }

    internal fun endFrame(){
        scrollX = 0.0
        scrollY = 0.0
        lastX = x
        lastY = y
    }

    fun mouseButtonDown(button:Int):Boolean{
        return if (button < mouseButtons.size) mouseButtons[button]
        else false
    }

    fun isDragging():Boolean{
        return isDragging
    }

    fun Getx():Double{
        return x
    }

    fun Gety():Double{
        return y
    }

    fun getDX():Double{
        return lastX - x
    }

    fun getDY():Double{
        return lastY - y
    }

    fun getScrollX():Double{
        return scrollX
    }

    fun getScrollY():Double{
        return scrollY
    }
}