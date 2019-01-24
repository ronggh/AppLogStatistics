package cn.alan.test;

import cn.alan.common.utils.GeoUtil;
import org.junit.Test;

public class TestGeoLite {
    private String ip = "115.239.211.112";
    @Test
    public void testGeoGetCountry(){
        String country = GeoUtil.getCountry(ip);
        System.out.println("country:"+country);
    }

    @Test
    public void testGeoGetProvince(){
        String province = GeoUtil.getProvince(ip);
        System.out.println("province:"+ province);
    }

    @Test
    public void testGeoGetCity(){
        String city = GeoUtil.getCity(ip);
        System.out.println("city:" + city);
    }

    @Test
    public void test(){
        System.out.println(""+GeoUtil.getCountry(ip) +":"+GeoUtil.getProvince(ip)+":"+GeoUtil.getCity(ip));
    }
}
