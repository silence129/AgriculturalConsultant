package cn.org.nercita.agriculturalconsultant.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    private static int days; //天数

    private static int hours; //时

    private static int minutes; //分
    private static String result;
    private static final String FORMAT = "yyyy-MM-dd";
    private static SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    private DateUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat(FORMAT).format(new Date());
    }
    public static String getChineseDate() {
        return chineseDateFormat.format(new Date());
    }

    public static String getCurrentTime(long integer) {
        return new SimpleDateFormat(FORMAT).format(new Date(integer));
    }

    public static String getCurrentTimeNoDetail() {
        String format = "yyyy-MM-dd";
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String getCurrentTimeFormat() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        return format.format(new Date());
    }

    public static int getCurrentYear() {
        return Integer.parseInt(new SimpleDateFormat(FORMAT).format(new Date()).substring(0, 4));
    }

    public static int getCurrentMonth() {
        return Integer.parseInt(new SimpleDateFormat(FORMAT).format(new Date()).substring(5, 7));
    }

    /* 获取月、日、星期几 */
    public static String getMonthWeekDay() {
        Date date = new Date();

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

        /* 将每周获取的天数转化为String星期的天数 */
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

        if (day < 1) {
            month -= 1;
            if (month == 0) {
                year -= 1;
                month = 12;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                day = 30 + day;
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                day = 31 + day;
            } else if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) day = 29 + day;
                else day = 28 + day;
            }
        }
        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10) m = "0" + month;
        else m = month + "";
        if (day < 10) d = "0" + day;
        else d = day + "";

        Calendar today = Calendar.getInstance();
        try {
            today.setTime(chineseDateFormat.parse("2016年10月14日"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Lunar lunar = new Lunar(today);
        return m + "月" + d + "号 " + "【农历" +"】 " + "星期" + mWay;
    }

    /* 获取天气预报6天内的月、日 */
    public static String getForeMonthDay(int ref) {
        Date date = new Date();

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) + ref;
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

        /* 将每周获取的天数转化为String星期的天数 */
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30) {
                month += 1;
                if (month == 0) {
                    year -= 1;
                    month = 12;
                }
                day -= 30;
            }
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day > 31) {
                month += 1;
                if (month == 0) {
                    year -= 1;
                    month = 12;
                }
                day -= 31;
            }
        } else if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                if (day > 29)
                    month += 1;
                if (month == 0) {
                    year -= 1;
                    month = 12;
                }
                day -= 29;
            } else {
                if (day > 28)
                    month += 1;
                if (month == 0) {
                    year -= 1;
                    month = 12;
                }
                day -= 28;
            }
        }

        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10) m = "0" + month;
        else m = month + "";
        if (day < 10) d = "0" + day;
        else d = day + "";
        return m + "/" + d;
    }

    /* 获取天气预报星期几 */
    public static String getForeWeek(int ref) {

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        int iWay = Integer.parseInt(mWay);
        iWay += ref;

        if (iWay > 7)
            iWay -= 7;

        mWay = iWay + "";

        /* 将每周获取的天数转化为String星期的天数 */
        if ("1".equals(mWay)) {
            mWay = "日";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "星期" + mWay;
    }

    public static String lastnDays(int n) {
        Date date = new Date();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - n - 1;
        if (day < 1) {
            month -= 1;
            if (month == 0) {
                year -= 1;
                month = 12;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                day = 30 + day;
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                day = 31 + day;
            } else if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) day = 29 + day;
                else day = 28 + day;
            }
        }
        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10) m = "0" + month;
        else m = month + "";
        if (day < 10) d = "0" + day;
        else d = day + "";
        return y + "-" + m + "-" + d;
    }

    public static String getLastWeek() {
        Date date = new Date();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - 6;
        if (day < 1) {
            month -= 1;
            if (month == 0) {
                year -= 1;
                month = 12;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                day = 30 + day;
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                day = 31 + day;
            } else if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) day = 29 + day;
                else day = 28 + day;
            }
        }
        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10) m = "0" + month;
        else m = month + "";
        if (day < 10) d = "0" + day;
        else d = day + "";

        return y + "-" + m + "-" + d;
    }

    public static String getLunarDate() {

        Calendar today = Calendar.getInstance();
        try {
            today.setTime(chineseDateFormat.parse("2016年10月14日"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Lunar lunar = new Lunar(today);
        return "农历" ;
    }
    //获取当前时间是星期几
    public static String getWeek(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

        /* 将每周获取的天数转化为String星期的天数 */
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mWay;
    }
    public static String poorTime(String num) {
        Date nowDate = new Date();
        long curentTime = nowDate.getTime();//系统当前时间
        long l = Long.parseLong(num);
        Date date = new Date();//转换的时间戳
        date.setTime(l);
        long sendTime = date.getTime();
        long poorTime = curentTime - sendTime;
        //毫秒转为秒
        int totalSeconds = (int) (poorTime / 1000);
        //得到总天数
        days = totalSeconds / (3600 * 24);
        int days_remains = totalSeconds % (3600 * 24);

        //得到总小时数
        hours = days_remains / 3600;
        int remains_hours = days_remains % 3600;

        //得到分种数
        minutes = remains_hours / 60;
        if (days < 1) {
            if (hours < 1) {
                result = minutes + "分钟";
            } else {
                result = hours + "小时";
            }
        } else {
            result = days + "天";
        }
        return result;

    }

}
