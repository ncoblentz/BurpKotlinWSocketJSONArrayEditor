# WSocket JSON Array Editor Burp Extension

_By [Nick Coblentz](https://www.linkedin.com/in/ncoblentz/)_

__This Burp Extension is made possible by [Virtue Security](https://www.virtuesecurity.com), the Application Penetration Testing consulting company I work for.__

This extension provides a WebSocket Message Editor for messages that have JSON arrays containing stringified JSON elements. It parses those stringified JSON objects, prettifies them, and makes them easy to see and edit.

Converts a WebSocket message containing:
```json
["{\"msg\":\"method\",\"method\":\"login\",\"id\":\"1\",\"params\":[{\"lookup\":\"213AEBDC12\"}]}"]
```

to
```json
[{
    "msg": "method",
    "method": "login",
    "id": "1",
    "params": [{"lookup": "213AEBDC12"}]
}]
```

## Setup

This project was initially created using the template found at: https://github.com/ncoblentz/KotlinBurpExtensionBase. That template also describes how to:
- Build this and other projects based on the template
- Load the built jar file in Burp Suite
- Debug Burp Suite extensions using IntelliJ
- Provides links to documentation for building Burp Suite Plugins