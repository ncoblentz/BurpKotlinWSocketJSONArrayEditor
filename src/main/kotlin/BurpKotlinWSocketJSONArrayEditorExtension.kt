import burp.api.montoya.BurpExtension
import burp.api.montoya.MontoyaApi
import burp.api.montoya.http.message.params.HttpParameterType
import burp.api.montoya.proxy.http.InterceptedRequest
import burp.api.montoya.proxy.http.ProxyRequestHandler
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction
import burp.api.montoya.ui.editor.extension.EditorCreationContext
import burp.api.montoya.ui.editor.extension.ExtensionProvidedHttpRequestEditor
import burp.api.montoya.ui.editor.extension.HttpRequestEditorProvider
import burp.api.montoya.utilities.json.JsonNode
import burp.api.montoya.ui.editor.extension.WebSocketMessageEditorProvider
import burp.api.montoya.ui.editor.extension.ExtensionProvidedWebSocketMessageEditor;
import com.nickcoblentz.montoya.LogLevel
import com.nickcoblentz.montoya.MontoyaLogger

// Montoya API Documentation: https://portswigger.github.io/burp-extensions-montoya-api/javadoc/burp/api/montoya/MontoyaApi.html
// Montoya Extension Examples: https://github.com/PortSwigger/burp-extensions-montoya-api-examples

class BurpKotlinWSocketJSONArrayEditorExtension : BurpExtension, WebSocketMessageEditorProvider {
    private lateinit var api: MontoyaApi
    private lateinit var logger : MontoyaLogger



    override fun initialize(api: MontoyaApi?) {

        // In Kotlin, you have to explicitly define variables as nullable with a ? as in MontoyaApi? above
        // This is necessary because the Java Library allows null to be passed into this function
        // requireNotNull is a built-in Kotlin function to check for null that throws an Illegal Argument exception if it is null
        // after checking for null, the Kotlin compiler knows that any reference to api  or this.api below will not = null and you no longer have to check it
        // Finally, assign the MontoyaApi instance (not nullable) to a class property to be accessible from other functions in this class
        this.api = requireNotNull(api) { "api : MontoyaApi is not allowed to be null" }
        logger = MontoyaLogger(api, LogLevel.DEBUG)
        // This will print to Burp Suite's Extension output and can be used to debug whether the extension loaded properly
        logger.debugLog("Started loading the extension...")

        // Name our extension when it is displayed inside of Burp Suite
        api.extension().setName("WSocketJSONArrayEditor")

        // Code for setting up your extension starts here...

        api.userInterface().registerWebSocketMessageEditorProvider(this)


        // Code for setting up your extension ends here

        // See logging comment above
        logger.debugLog("...Finished loading the extension")

    }


    override fun provideMessageEditor(creationContext : EditorCreationContext) : ExtensionProvidedWebSocketMessageEditor {
        return WSocketJSONArrayMessageEditor(api,creationContext);
    }
}