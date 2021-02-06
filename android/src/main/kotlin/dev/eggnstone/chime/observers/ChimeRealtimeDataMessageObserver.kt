package dev.eggnstone.chime.observers

import com.amazonaws.services.chime.sdk.meetings.realtime.datamessage.DataMessage
import com.amazonaws.services.chime.sdk.meetings.realtime.datamessage.DataMessageObserver
import io.flutter.plugin.common.EventChannel.EventSink
import org.json.JSONObject

class ChimeRealtimeDataMessageObserver(private val _eventSink: EventSink) : DataMessageObserver
{
    private fun convertDataMessageToJson(dataMessage: DataMessage): JSONObject
    {
        val jsonObject = JSONObject()

        jsonObject.put("Topic", dataMessage.topic)
        jsonObject.put("data", String(dataMessage.data, Charsets.UTF_8))

        return jsonObject
    }

    override fun onDataMessageReceived(dataMessage: DataMessage) {
        val jsonObject = JSONObject()
        jsonObject.put("Name", "DataMessageReceived")
        jsonObject.put("Arguments", convertDataMessageToJson(dataMessage))
        _eventSink.success(jsonObject.toString())    }
}
