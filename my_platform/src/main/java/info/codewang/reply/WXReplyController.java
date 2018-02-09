package info.codewang.reply;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import info.codewang.util.CommonUtil;
import info.codewang.util.TextMessageUtil;
import info.codewang.util.XMLUtils;
import net.sf.json.JSONArray;

@RestController
public class WXReplyController {

    @Autowired
    private CommonUtil commonUtil;
    
    @PostMapping(value="/")
    public String reply(HttpServletRequest request){
	    Map<String, String> map = XMLUtils.xmlToMap(request);
	    String ToUserName = map.get("ToUserName");  
	    String FromUserName = map.get("FromUserName");  
	    String MsgType = map.get("MsgType");  
	    String message = null;  
	   
	
	    if("text".equals(MsgType)){  
		String Content = map.get("Content"); 
	        TextMessageUtil textMessage = new TextMessageUtil();
	        String desc = "这是一个图片文字识别的订阅号";
	        message = textMessage.initMessage(FromUserName, ToUserName,desc);
	    } else if("image".equals(MsgType)){
		 String picUrl = map.get("PicUrl"); 
		 TextMessageUtil textMessage = new TextMessageUtil();
		 JSONObject res = commonUtil.getWord(picUrl);
		 StringBuffer sb = new StringBuffer();
		 try {
		     String ws = res.get("words_result").toString();
		     if (StringUtils.isEmpty(ws)) {
			sb.append("识别失败，请重新发送图片");
		    }else{
			JSONArray s = JSONArray.fromObject(ws);
			List<Map<String,Object>> mps = (List<Map<String,Object>>)s;
			if(mps == null ||mps.size()==0){
			    sb.append("识别失败，请重新发送图片");
			}else{
        			for (Map<String, Object> mp : mps) {
        			    sb.append(mp.get("words"));
        			}
			}
		    }
		} catch (Exception e) {
		    sb.append("识别失败，请重新发送图片");
		}
		 
	         message = textMessage.initMessage(FromUserName, ToUserName,sb.toString());  
	    }
	    System.out.println(message);
	    return message;
    }
    
}
