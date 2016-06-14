package com.leatherswan.artisticendeavors.app;

import java.io.Serializable;

public class ApiMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    enum MsgType {
        INFO,
        ERROR
    }
    
    private MsgType msgType; 
    private String message;

    public ApiMessage(MsgType msgType, String message) {
        this.msgType = msgType;
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    //    private MessageType messageType;
//    private String messageText;
//    
//    public ApiMessage(MessageType type, String text) {
//    	
//    }
//    
//	public MessageType getMessageType() {
//		return messageType;
//	}
//	public void setMessageType(MessageType messageType) {
//		this.messageType = messageType;
//	}
//	public String getMessageText() {
//		return messageText;
//	}
//	public void setMessageText(String messageText) {
//		this.messageText = messageText;
//	}
    
}
