package com.template.ryan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fcibook.quick.http.QuickHttp;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log
@Service
public class GetDateSevice {


    private String login(String username,String pass){
        JSONObject jb1=new JSONObject();
        jb1.put("username",username);
        jb1.put("password",pass);
        String res = new QuickHttp()
                .url("http://api.gizwits.com/app/login")
                .post()
                .addHeader("X-Gizwits-Application-Id","a30b600da84245e1909158dc4ee9a6bb")
                .setBodyContent(jb1.toJSONString())
                .text();
        JSONObject jb= JSON.parseObject(res);
        return jb.getString("token");
    }

    public String getSum(String time) throws ParseException {

        long end=System.currentTimeMillis()/1000-100;
        if (time!=null) {
            end=dateToStamp(time);
        }
        long start=end-2*24*60*60+1;

        String res = new QuickHttp()
                .url("https://api.gizwits.com/app/devices/3XKMeyGRo7psMot2avqo33/raw_data?type=cmd&start_time="+start+"&end_time="+end+"&limit=1000")
                .get()
                .addHeader("X-Gizwits-Application-Id","a30b600da84245e1909158dc4ee9a6bb")
                .addHeader("X-Gizwits-User-token","4448438a16db49f9946f0e849b28bc88")
                .text();
        JSONObject m1= JSON.parseObject(StringEscapeUtils.unescapeJava(res.substring(1, res.length()-1)));//将json文本转化为jsonobject
        JSONArray ja=m1.getJSONArray("objects");
        int sum=0;
        if(ja.size()>0){
            for(int i=0;i<ja.size();i++){
                JSONObject job = ja.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                if ("dev2app".equals(job.getString("type"))){
                    String payload=job.getString("payload_bin");
                    if (payload.length()>60){
                        String hex=payload.substring(60,68);
                        sum+=getdata(hex);
                        log.info(getdata(hex)+"");
                    }
                }
            }
        }
        return "sum:"+sum/100.0;
    }

    private int getdata(String str){
        return Integer.valueOf(str,16);
    }

    /*
     * 将时间转换为时间戳
     */
    private long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }
}
