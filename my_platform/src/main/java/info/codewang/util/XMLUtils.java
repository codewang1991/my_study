package info.codewang.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import info.codewang.entity.MessageText;

public class XMLUtils {

    public static Map<String,String> xmlToMap(HttpServletRequest request){
	Map<String,String> map = new HashMap<String,String>();  
        SAXReader reader = new SAXReader();  
        InputStream in = null; 
        try {  
            in = request.getInputStream();  
            Document doc = reader.read(in);  
            Element root = doc.getRootElement();  
            List<Element> list = root.elements();  
            for (Element element : list) {  
                map.put(element.getName(), element.getText());  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (DocumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            try {  
                in.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return map;  
    }
    
    public static String messageToxml(MessageText  message) {  
        XStream xstream  = new XStream();  
        xstream.alias("xml", message.getClass());  
        return xstream.toXML(message);  
    }  
    
}
