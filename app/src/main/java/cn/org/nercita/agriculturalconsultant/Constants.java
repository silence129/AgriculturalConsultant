package cn.org.nercita.agriculturalconsultant;

/**
 * Created by 范博文 on 2017/4/7.
 * 接口
 */

public class Constants {
    public static final String BaseUrL = "http://123.127.160.22/bthscreen/";
    //  public static final String BaseUrL = "http://123.127.160.22/bthscreen/";
//    public static final String BaseUrL23 = "http://192.168.16.23:8888/bthscreen/";
    public static final String BaseUrL45 = "http://192.168.16.45:9090/bthscreen/";
    public static final String BaseUrL56 = "http://192.168.16.56:8081/bthscreen/";
//    public static final String BaseUrL66 = "http://192.168.16.66:8080/bthscreen/";

    //图片
    public static final String PIC_PATH = BaseUrL + "resources/uploads";
    //图片
    public static final String PIC_PATH1 = "http://gc.nercita.org.cn/bth/resources/";

    //获取提问分类
    //获取提问分类
    public static final String QUESTION_TYPE =
            "http://123.127.160.21/atemanage/mobile/question/type/list.shtml";

    //种植类型
    public static final String TYPE_LIST = "mobile/techQA/getBthTypeList.shtml?typeId=1";

    /**
     * 首页接口
     * @author: liangxingsheng
     * @date: 2017/4/14 下午12:40
     */
    public static String HOME = "mobile/laws/mavin";

    /**
     * 首页最新资讯接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:40
     */
    public static String HOME_LAWS_LIST = "mobile/laws/list";

    /**
     * 首页价格接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:40
     */
    public static String HOME_PRICE_LIST = "mobile/laws/priceList";

    /**
     * 农技问答列表接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:00
     * @para LinkAccountId 如果查登录人提的问题及解答过的问题，传此值为登录id，默认查询所有问题
     * @para everyPage 页个数
     * @para currentPage 页码
     */
    public static String TECH_QUESTION_LIST = "mobile/techQA/questionList.shtml";

    /**
     * 农技问答详情接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:20
     * @para id 问题id
     * @para everyPage 页个数
     * @para currentPage 页码
     */
    public static String TECH_QUESTION_DETAIL = "mobile/techQA/techQuestionDetail.shtml";

    /**
     * 农技问答提问接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:00
     * @para accountId 登录账户id
     * @para accountName 登录账户nickname
     * @para title 问题内容
     * @para bthTypeId 类型
     * @para file 图片文
     */
    public static String TECH_QUESTION_ASK = "mobile/techQA/addTechQuestion.shtml";

    /**
     * 农技问答回答接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:00
     * @para accountId 登录账户id
     * @para accountName 登录账户nickname
     * @para content 问题内容
     * @para questionId 问题id
     */
    public static String TECH_QUESTION_ANSWER = "mobile/techQA/addTechReponse.shtml";

    /**
     * 专家问答列表接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午4:50
     * @para accountId 登录账户id专家或农民
     * @para everyPage 页个数
     * @para currentPage 页码
     */
    public static String EXPERT_QUESTION_LIST = "mobile/knowledgeQA/questionList";

    /**
     * 专家问答详情接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:20
     * @para questionId 问题id
     * post
     */
    public static String EXPERT_QUESTION_DETAIL = "mobile/knowledgeQA/getQuestion";

    /**
     * 专家问答回答接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午5:26
     * @para expertid 回复专家id，对应的是userid
     * @para questionid 问题id
     * @para content 回复内容
     * @para file
     */
    public static String EXPERT_QUESTION_ANSWER = "mobile/knowledgeQA/insertAnswerAndroid";

    /**
     * 服务首页接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午2:07
     */
    public static String SERVICE_HOME = "mobile/service/serviceIndex";

    /**
     * 服务资讯类详情接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午2:07
     */
    public static String SERVICE_NEWS_DETAIL = "h5/news/detail";

    /**
     * 服务最新供应更多接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午2:07
     */
    public static String SERVICE_SUPPLY_MORE = "mobile/service/displayBalanceList";

    /**
     * 服务技术规程接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午2:07
     * Integer typeId //类型id （查询条件，可以没有）
     * String province //省份 （查询条件，可以没有）
     * Integer everyPage 每页显示的数量 默认是15条
     * Integer currentPage 第几页
     */
    public static String SERVICE_TECH_RULES = "mobile/resource/resourceList";

    /**
     * 服务技术规程详情接口 h5
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午2:07
     * id
     */
    public static String SERVICE_TECH_RULES_H5 = "h5/resource/detail";

    /**
     * 服务 农资列表
     * @author: liangxingsheng
     * @date: 2017/4/14 上午10:07
     * id
     */
    public static String NONGZI_LIST = "mobile/bthAgricult/agricultList";

    /**
     * 服务 农业资讯更多列表
     * @author: liangxingsheng
     * @date: 2017/4/14 14:07
     * id
     */
    public static String MARK_NEWS_LIST = "/mobile/service/markNewList";

    /**
     * 和风天气预报接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:00
     * @para city 城市拼音
     * @para key app key
     */
    public static String HE_WEATHER = "https://free-api.heweather.com/v5/forecast";

    /**
     * 和风实况天气接口
     *
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:00
     * @para city 城市拼音
     * @para key app key
     */
    public static String HE_WEATHER_NOW = "https://free-api.heweather.com/v5/now";


    /**
     * 和风天气接口key
     *
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:00
     */
    public static String HE_WEATHER_KEY = "132f924e4bd3405daff09960a87e935a";

    /**
     * accountId
     */
    public static String ACCOUNT_ID = "accountId";
    //环信账号
    public static  String IMUSERNAME = "imusername";
    //天气地址
    public static  String WEATHER_ADDRESS = "weather_address";


    //根据questionid获取问答详情
    public static final String QUESTIONDES = BaseUrL + "mobile/knowledgeQA/getQuestion";
    //专家根据问题id 做一对一回答
    public static final String ANSWERQUESTION = BaseUrL + "bthQuestion/answerQuestion";
    //登录
    public static final String LOGIN = BaseUrL + "visitor/mobilelogin";
    //注册
    public static final String REGEST = BaseUrL + "visitor/mobilesign";
    //农民获得一对一问答列表
    public static final String FARMERQUESLIST = BaseUrL + "mobile/knowledgeQA/commonQuestionList";
    //专家获得一对一问答列表
    public static final String EXPERTQUESTIONLIST = BaseUrL + "mobile/knowledgeQA/questionList";
    //农事管理列表
    public static final String FARMERMANAGELIST = BaseUrL + "mobile/farmRecord/list";
    //上传农事管理
    public static final String UPDATEFARMMESSAGE = BaseUrL + "mobile/farmRecord/saveRecord";
    //获得注册类型
    public static final String GETREGESTTYPE = BaseUrL + "visitor/typeId";
    //一对一向专家提问
    public static final String ASKEXPERT = BaseUrL + "mobile/knowledgeQA/insertQuestionAndroid";
    //获得专家列表
    public static final String GETEXPERTLIST = BaseUrL + " mobile/knowledgeQA/getExpert";
    //获取基地列表
    public static final String GETBASELIST = BaseUrL + "mobile/service/stationList";

}
