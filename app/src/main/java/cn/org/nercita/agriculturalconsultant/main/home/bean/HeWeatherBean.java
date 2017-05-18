package cn.org.nercita.agriculturalconsultant.main.home.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/10.
 * 天气预报数据bean
 */

public class HeWeatherBean {
    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904989","lon":"116.405285","update":{"loc":"2017-04-10 13:51","utc":"2017-04-10 05:51"}}
         * daily_forecast : [{"astro":{"mr":"17:51","ms":"05:25","sr":"05:44","ss":"18:47"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-04-10","hum":"41","pcpn":"0.0","pop":"0","pres":"1013","tmp":{"max":"20","min":"10"},"uv":"6","vis":"20","wind":{"deg":"211","dir":"南风","sc":"微风","spd":"5"}},{"astro":{"mr":"18:49","ms":"05:55","sr":"05:43","ss":"18:48"},"cond":{"code_d":"a100","code_n":"a100","txt_d":"晴","txt_n":"晴"},"date":"2017-04-11","hum":"29","pcpn":"0.0","pop":"1","pres":"1015","tmp":{"max":"22","min":"7"},"uv":"6","vis":"20","wind":{"deg":"198","dir":"北风","sc":"微风","spd":"5"}},{"astro":{"mr":"19:47","ms":"06:25","sr":"05:41","ss":"18:49"},"cond":{"code_d":"a100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2017-04-12","hum":"25","pcpn":"0.0","pop":"0","pres":"1014","tmp":{"max":"25","min":"10"},"uv":"6","vis":"20","wind":{"deg":"255","dir":"南风","sc":"微风","spd":"2"}},{"astro":{"mr":"20:44","ms":"06:57","sr":"05:40","ss":"18:50"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2017-04-13","hum":"40","pcpn":"0.1","pop":"31","pres":"1009","tmp":{"max":"18","min":"9"},"uv":"6","vis":"18","wind":{"deg":"139","dir":"南风","sc":"微风","spd":"1"}},{"astro":{"mr":"21:40","ms":"07:31","sr":"05:38","ss":"18:51"},"cond":{"code_d":"a100","code_n":"a100","txt_d":"晴","txt_n":"晴"},"date":"2017-04-14","hum":"34","pcpn":"0.2","pop":"68","pres":"1005","tmp":{"max":"26","min":"12"},"uv":"6","vis":"20","wind":{"deg":"304","dir":"南风","sc":"微风","spd":"1"}},{"astro":{"mr":"22:34","ms":"08:07","sr":"05:37","ss":"18:52"},"cond":{"code_d":"a100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2017-04-15","hum":"24","pcpn":"0.0","pop":"4","pres":"1008","tmp":{"max":"27","min":"14"},"uv":"6","vis":"20","wind":{"deg":"225","dir":"南风","sc":"微风","spd":"3"}},{"astro":{"mr":"23:26","ms":"08:47","sr":"05:35","ss":"18:53"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2017-04-16","hum":"36","pcpn":"8.9","pop":"46","pres":"1010","tmp":{"max":"26","min":"12"},"uv":"4","vis":"17","wind":{"deg":"144","dir":"南风","sc":"微风","spd":"1"}}]
         * status : ok
         */

        private BasicBean basic;
        private String status;
        private NowBean now;
        private List<DailyForecastBean> daily_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            /**
             * city : 北京
             * cnty : 中国
             * id : CN101010100
             * lat : 39.904989
             * lon : 116.405285
             * update : {"loc":"2017-04-10 13:51","utc":"2017-04-10 05:51"}
             */

            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                /**
                 * loc : 2017-04-10 13:51
                 * utc : 2017-04-10 05:51
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowBean {
            /**
             * cond : {"code":"101","txt":"多云"}
             * fl : 20
             * hum : 32
             * pcpn : 0
             * pres : 1007
             * tmp : 19
             * vis : 7
             * wind : {"deg":"210","dir":"西风","sc":"3-4","spd":"15"}
             */

            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                /**
                 * code : 101
                 * txt : 多云
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                /**
                 * deg : 210
                 * dir : 西风
                 * sc : 3-4
                 * spd : 15
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * astro : {"mr":"17:51","ms":"05:25","sr":"05:44","ss":"18:47"}
             * cond : {"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"}
             * date : 2017-04-10
             * hum : 41
             * pcpn : 0.0
             * pop : 0
             * pres : 1013
             * tmp : {"max":"20","min":"10"}
             * uv : 6
             * vis : 20
             * wind : {"deg":"211","dir":"南风","sc":"微风","spd":"5"}
             */

            private AstroBean astro;
            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpBean tmp;
            private String uv;
            private String vis;
            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                /**
                 * mr : 17:51
                 * ms : 05:25
                 * sr : 05:44
                 * ss : 18:47
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                /**
                 * code_d : 101
                 * code_n : 101
                 * txt_d : 多云
                 * txt_n : 多云
                 */

                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                /**
                 * max : 20
                 * min : 10
                 */

                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                /**
                 * deg : 211
                 * dir : 南风
                 * sc : 微风
                 * spd : 5
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
