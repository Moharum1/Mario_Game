package Riven.Events

import org.lwjgl.glfw.GLFW.*

object KeyListener {

    private var keys:Array<Boolean> = Array(350){false}
    inline fun keyPress(key:Int, action:Int, block:()->Unit){
        if (key == GLFW_KEY_SPACE && action == GLFW_PRESS){
            block()
        }
    }

    fun keyCallback(window:Long, key:Int, scancode:Int, action:Int, mods:Int){
        if (action == GLFW_PRESS){
            keys[key] = true
        } else if (action == GLFW_RELEASE){
            keys[key] = false
        }
    }

    fun isKeyPressed(key:Int):Boolean{
        return if (key < keys.size) keys[key]
        else false
    }
}