package cn.alan.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import java.io.InputStream;
import java.net.InetAddress;

/**
 * 地理工具类，实现通过ip查找地址区域
 */

public class GeoUtil {
    //
    //private static InputStream in;
    private static Reader reader;

    // 初始化in和reader
    static{
        try {
            // 因为要在Web项目中调用该工具类，直接这样使用，会出现空指针错误。
            // in  = ClassLoader.getSystemResourceAsStream("GeoLite2-City.mmdb");
            // 需要先获获取当前执行线程的类加载器
           // ClassLoader loader = Thread.currentThread().getContextClassLoader();
           // in = loader.getResource("GeoLite2-City.mmdb").openStream();
           // reader = new Reader(in);
            Resource resource = new ClassPathResource("GeoLite2-City.mmdb");
            reader = new Reader(resource.getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得国家数据
     */
    public static String getCountry(String ip) {
        //System.out.println("111");
        try{
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("country").get("names").get("zh-CN").textValue();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "" ;

    }

    /**
     * 获得省份数据
     */
    public static String getProvince(String ip) {
        try{
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("subdivisions").get(0).get("names").get("zh-CN").textValue();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "" ;
    }

    /**
     * 获得城市数据
     * @param ip
     * @return
     */
    public static String getCity(String ip){
        try{
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("city").get("names").get("zh-CN").textValue();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "" ;
    }
}
