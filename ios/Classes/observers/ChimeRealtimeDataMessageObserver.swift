//
//  RealtimeDataMessageObserver.swift
//  eggnstone_amazon_chime
//
//  Created by Hendrik Lakämper on 06.02.21.
//

import Foundation
import AmazonChimeSDK
import Flutter

public class ChimeRealtimeDataMessageObserver: DataMessageObserver {
    let _eventSink: FlutterEventSink

    init(eventSink: @escaping FlutterEventSink) {
        self._eventSink = eventSink
    }
    
    public func dataMessageDidReceived(dataMessage: DataMessage) {
        let decoded = String(decoding: dataMessage.data, as: UTF8.self)
        
        let json="""
        {
            "Name": "DataMessageReceived",
            "Arguments": {
                "topic": "\(dataMessage.topic)",
                "data": \(decoded)
            }}
        """
        
        _eventSink(json)
    }
}
