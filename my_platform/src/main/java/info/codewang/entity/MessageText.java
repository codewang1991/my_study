package info.codewang.entity;

public class MessageText extends BaseMessage{
    private String Content;//文本消息内容  
    
 
      
    public MessageText(){  
          
    }  
  
      
    public MessageText( String content) {  
        super();  
       
        Content = content;  
    }  
  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}
