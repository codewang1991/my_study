package info.codewang.util;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.baidu.aip.ocr.AipOcr;

@Component
public class CommonUtil {
    @Value("${api_key}")
    private  String apiKey;
    @Value("${secret_key}")
    private  String secretKey;
    @Value("${app_id}")
    private String appId;
   
   private AipOcr getAipClient(){
       AipOcr client = new AipOcr(appId, apiKey, secretKey);
       client.setConnectionTimeoutInMillis(2000);
       client.setSocketTimeoutInMillis(60000);
       return client;
   }
    
   public JSONObject getWord(String url){
       HashMap<String, String> options = new HashMap<String, String>();
       options.put("detect_direction", "true");
       options.put("detect_language", "true");
       JSONObject res = this.getAipClient().webImageUrl(url, options);
       return res;
   }
   
}
