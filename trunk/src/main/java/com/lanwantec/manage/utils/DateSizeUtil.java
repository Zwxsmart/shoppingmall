package com.lanwantec.manage.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSizeUtil {
    public static Integer isBelong(Date beginTime, Date endTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date now =null;
        try {
            now = df.parse(df.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer flag = belongCalendar(now, beginTime, endTime);
        return flag;
    }


    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Integer belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        System.out.println(date.after(begin)); // 当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
        System.out.println(date.before(end)); // 当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
        if (date.after(begin)==true && date.before(end)==true) {
            return 1; // 进行中
        } else if(date.before(end)==false){
            return 2; // 已结束
        }else if(date.after(begin)==false){
            return 3; // 未开始
        }else{
            return 0;
        }
    }
}
