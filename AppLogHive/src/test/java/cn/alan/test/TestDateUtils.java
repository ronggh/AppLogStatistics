package cn.alan.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDateUtils {

    @Test
    public void getCurrentBeginZeroTime() throws Exception{
        Date date  = new Date();
        Date zDate = getZeroTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(zDate));
    }
    @Test
    public void getCurrentEndZeroTime() throws Exception{
        Date date  = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,1);
        Date zDate = getZeroTime(c.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(zDate));
    }

    @Test
    public void getCurrentWeekBeginZeroTime() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int n = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DAY_OF_MONTH,-(n-1));
        Date weekFirstDay = c.getTime();
        Date wfdZero = getZeroTime(weekFirstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strWeekFirstDay = sdf.format(wfdZero);
        System.out.println(strWeekFirstDay);

    }
    @Test
    public void getCurrentWeekEndZeroTime() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int n = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DAY_OF_MONTH,(8-n));
        Date weekFirstDay = c.getTime();
        Date wfdZero = getZeroTime(weekFirstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strWeekFirstDay = sdf.format(wfdZero);
        System.out.println(strWeekFirstDay);

    }

    /**
     * 获取指定时间的零时刻
     * @param date
     * @return
     */
    private Date getZeroTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        try {
            return sdf.parse(sdf.format(date));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
