import com.nickcoblentz.montoya.MontoyaLogger
import burp.api.montoya.MontoyaApi
import burp.api.montoya.core.ByteArray
import burp.api.montoya.ui.Selection
import burp.api.montoya.ui.contextmenu.WebSocketMessage
import burp.api.montoya.ui.editor.EditorOptions
import burp.api.montoya.ui.editor.extension.ExtensionProvidedWebSocketMessageEditor;
import burp.api.montoya.ui.editor.extension.EditorCreationContext
import com.nickcoblentz.montoya.LogLevel
import org.json.JSONArray
import org.json.JSONObject
import java.awt.Component
import burp.api.montoya.ui.editor.extension.EditorMode
import burp.api.montoya.ui.editor.WebSocketMessageEditor
import java.util.Optional
import kotlin.jvm.optionals.getOrDefault
import kotlin.jvm.optionals.getOrNull

class WSocketJSONArrayMessageEditor(private val api : MontoyaApi, private val creationContext : EditorCreationContext) : ExtensionProvidedWebSocketMessageEditor {
    private val logger = MontoyaLogger(api, LogLevel.DEBUG)
    private var originalMessage : WebSocketMessage? = null
    private lateinit var editor : WebSocketMessageEditor
    private var startsWithA=false

    init {
        logger.debugLog("got here")
        editor = if(creationContext.editorMode()==EditorMode.READ_ONLY)
            api.userInterface().createWebSocketMessageEditor(EditorOptions.READ_ONLY)
        else
            api.userInterface().createWebSocketMessageEditor()
        logger.debugLog("reached end")
    }

    override fun getMessage(): ByteArray {
        logger.debugLog("got here")
        logger.debugLog("reached end")

        if(isModified) {
            val jsonArrayInput = JSONArray(editor.contents.toString())
            val jsonArrayOutput = JSONArray()

            for (i in 0 until jsonArrayInput.length()) {
                logger.debugLog("$i: "+jsonArrayInput.get(i).javaClass.name)
                val jsonString = jsonArrayInput.getJSONObject(i).toString()
                jsonArrayOutput.put(jsonString)
            }

            if(startsWithA)
                return burp.api.montoya.core.ByteArray.byteArray("a"+jsonArrayOutput.toString())
            else
                return burp.api.montoya.core.ByteArray.byteArray(jsonArrayOutput.toString())

            editor.contents = burp.api.montoya.core.ByteArray.byteArray(jsonArrayOutput.toString(4))
        }

        originalMessage?.let {
            return it.payload()
        }

        return burp.api.montoya.core.ByteArray.byteArray("msg was null")

    }

    override fun setMessage(message: WebSocketMessage?) {
        logger.debugLog("got here")
        originalMessage=message
        message?.let {
            var payload = it.payload().toString()
            if(payload.startsWith("a[",true)) {
                startsWithA=true
                payload=payload.substring(1)
            }
            val jsonArrayInput = JSONArray(payload)
            val jsonArrayOutput = JSONArray()
            for (i in 0 until jsonArrayInput.length()) {
                val jsonObject = JSONObject(jsonArrayInput.getString(i))
                jsonArrayOutput.put(jsonObject)
            }
            editor.contents = burp.api.montoya.core.ByteArray.byteArray(jsonArrayOutput.toString(4))
        }
        logger.debugLog("reached end")
    }

    override fun isEnabledFor(message: WebSocketMessage?): Boolean {
        logger.debugLog("got here")
        message?.let {
            val body = it.payload().toString();
            logger.debugLog("reached end")
            return (body.startsWith("[") || body.startsWith("a[")) && body.endsWith("]")
        }
        logger.debugLog("reached end")
        return true
    }

    override fun caption(): String {
        logger.debugLog("got here")
        logger.debugLog("reached end")
        return "JSON Array With JSON Strings"
    }


    override fun uiComponent(): Component {
        logger.debugLog("got here")
        logger.debugLog("reached end")
        return editor.uiComponent()
    }

    override fun selectedData(): Selection? {
        logger.debugLog("got here")
        logger.debugLog("reached end")
        if(editor.selection().isPresent)
            return editor.selection().get()
        else
            return null
    }

    override fun isModified(): Boolean {
        logger.debugLog("got here")
        logger.debugLog("reached end")
        return editor.isModified
    }
}