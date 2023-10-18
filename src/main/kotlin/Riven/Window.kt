package Riven

import Riven.Events.KeyListener
import Riven.Events.MouseListener
import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL

object Window {
    internal var width:Int = 800
    internal var height:Int = 800
    internal var glfWindow:Long = 0

    private const val TITLE:String = "Mario"
    private var r = 1.0f
    private var g = 1.0f
    private var b = 1.0f
    private var a = 1.0f


    fun run(){
        println("Window is starting " + Version.getVersion() + " ! ")
        init()
        loop()

        //free the memory
        glfwFreeCallbacks(glfWindow)
        glfwDestroyWindow(glfWindow)

        // terminate GLFW and free the error callback
        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }

    private fun init(){
        // create an error callback to print the error to System.err
        // so if something goes wrong withing GLFW it will print the error to the console
        // Similar to   System.err.println(ERROR)
        GLFWErrorCallback.createPrint(System.err).set()

        // initialize GLFW
        if (!glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }

        // Configure GLFW
        //glfwDefaultWindowHints() // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE) // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE) // the window will be resizable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE) // the window will be maximized

        // Create the window
        glfWindow = glfwCreateWindow(width, height, TITLE, NULL, NULL)
        if (glfWindow == NULL){
            throw IllegalStateException("Failed to create the GLFW window")
        }

        glfwSetCursorPosCallback(glfWindow, MouseListener::mouseCallback)
        glfwSetMouseButtonCallback(glfWindow, MouseListener::mouseButtonCallback)
        glfwSetScrollCallback(glfWindow, MouseListener::mouseScrollCallback)

        glfwSetKeyCallback(glfWindow, KeyListener::keyCallback)

        // Make the openGL context current
        glfwMakeContextCurrent(glfWindow)
        // Enable V-sync
        glfwSwapInterval(1)

        // make the window visible
        glfwShowWindow(glfWindow)

        // Makes sure we could use OpenGl Binding with Kotlin very Important
        GL.createCapabilities()
    }

    private fun loop(){
        while (!glfwWindowShouldClose(glfWindow)){
            // Poll for window events. The key callback above will only be invoked during this call.
            glfwPollEvents()

            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
                println("Space is pressed")
                r = Math.random().toFloat()
                g = Math.random().toFloat()
                b = Math.random().toFloat()
                a = Math.random().toFloat()
            }

            glClearColor(r, g, b, a)
            glClear(GL_COLOR_BUFFER_BIT) // clear the framebuffer
            // Swap the color buffers
            glfwSwapBuffers(glfWindow)
        }
    }
}