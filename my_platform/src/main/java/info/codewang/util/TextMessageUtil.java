package info.codewang.util;

import java.util.Date;

import info.codewang.entity.MessageText;

public class TextMessageUtil {
    public  String initMessage(String FromUserName, String ToUserName,String content) {  
        MessageText text = new MessageText();  
        text.setToUserName(FromUserName);  
        text.setFromUserName(ToUserName);  
        text.setContent(content);  
        text.setCreateTime(new Date().getTime());  
        text.setMsgType("text");  
        return  XMLUtils.messageToxml(text);  
    }  
}
