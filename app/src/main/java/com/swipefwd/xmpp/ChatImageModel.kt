package com.swipefwd.xmpp

data class ChatImageModel(
    var response: Response = Response()
) {

    data class Response(
        val image: String = ""
    )
}

