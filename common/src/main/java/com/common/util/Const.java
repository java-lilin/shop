package com.common.util;


import com.common.enumeration.ClientType;
import java.util.Arrays;
import java.util.List;

public class Const {

    public static Integer ES_QUERY_MAX_NUMBER = 10000;

    public static class DELETE_FLAG {
        /**
         * 已删除
         */
        public static final Integer DELETE = 0;
        /**
         * 未删除
         */
        public static final Integer NOT_DELETE = 1;
    }


    public static class HOME_SCHOOL_ORG {
        /**
         * 已删除
         */
        public static final Integer DELETE = 0;
        /**
         * 未删除
         */
        public static final Integer NOT_DELETE = 1;
    }

    //批量增加学生校验学号和身份证号
    public static class CHECK_STUDENT {

        public static final String REPEAT_STUDENT_CODE = "学号重复";

        public static final String REPEAT_STUDENT_ID_CARD = "身份证号重复";

        public static final String ERROR_STUDENT_ID_CARD = "身份证号格式错误";

        public static final String ERROR_STUDENT_NAME = "名字格式错误";

        public static final String ERROR_STUDENT_CODE = "学号格式错误";

        public static final Integer INPUT_REPEAT_TYPE = 0;  //前端输入重复的身份证号或学号的学生

        public static final Integer EXISTS_REPEAT_TYPE = 1; //数据库中已存在相同身份证号或学号的学生

        public static final Integer ERROR_ID_CARD_TYPE = 2; //输入身份证号格式错误/名字格式错误

        public static final Integer DIFF_ID_CARD_TYPE = 3; //数据库中已存在相同身份证号的学生(不同的班级)

        public static final Integer ERROR_STUDENT_LIST = 0; //有问题的学生

        public static final Integer SAVE_STUDENT_LIST = 1; //要保存的学生


    }

    public static final String NOTCIE_KEY = "tab_notice_";

    public static final String NOTCIE_REMARK_KEY = "tab_notice_remark_";

    //学生奖状评语
    public static final String CERTIFICATE_KEY = "rewards";

    //教师退休年份
    public static class RETIRE {
        public static final String ID = "Time_Teacher_Retire";
        public static final String MAN_AGE = "男";
        public static final String WOMAN_AGE = "女";
    }

    /**
     * 作业点评常量
     */
    public static class HE {
        public static final Integer UN_EVALUATE = 0; //未点评
        public static final Integer EVALUATED = 1;  //已点评
        public static final Integer UN_CORRECT = 0; //未订正
        public static final Integer TO_BE_CORRECT = 1;  //待订正（驳回）
        public static final Integer CORRECTED = 2; //已订正
    }


    /**
     * 群组消息常量
     */
    public static class IM_GROUP_CONST {
        public static final String MQ_JSON_KEY_SEND_TYPE = "TYPE";
        public static final String MQ_JSON_KEY_SEND_TYPE_YES = "y";

        public static final String ERROR_PARAM = "参数错误";
        public static final String FAIL = "失败";
        public static final String ERROR = "异常处理……";
        public static final int COMMOD_ADD_GROUP = 0;
        public static final int COMMOD_DEL_GROUP = 1;
        public static final int COMMOD_ADD_GROUP_USER = 2;
        public static final int COMMOD_DEL_GROUP_USER = 3;
        public static final int COMMOD_UPDATE_GROUP = 4;
        public static final int COMMOD_SEND_GROUP_MSG = 5;
        /**
         * 验证消息
         */
        public static final int COMMOD_VERIFY_MSG = 6;
        /**
         * 关闭群组
         */
        public static final int COMMOD_CLOSE_GROUP = 7;
        /**
         * 未读消息数量清零
         */
        public static final int COMMOD_UNREAD_MSG_NUM_CLEAR_ZERO = 8;

        public static final int CHAT_GROUP = 1;//群聊
        public static final int CHAT_SINGLE = 0;//单聊
        public static final int CHAT_ALL = 2;//全部
        public static final Integer KEEP_ACTIVITI_STATUS = 999;
        public static final String SYSTEM_USER_ID = "xitongyonghu";
        public static final String SYSTEM_GROUP_NAME = "系统消息";
        /**
         * 发送用户ID集合
         * 发送mq消息json传key
         */
        public static final String MQ_JSON_KEY_SEND_USER_IDS = "sendUserIds";
        /**
         * 组id
         * 发送mq消息json传key
         */
        public static final String MQ_JSON_KEY_GROUP_ID = "groupId";
        /**
         * 消息
         * 发送mq消息json传key
         */
        public static final String MQ_JSON_KEY_MESSAGE = "message";
        /**
         * redis key的分隔符
         * 发送mq消息json传key
         */
        public static final String MQ_KEY_SEP = ":";
        /**
         * 消息撤回提示语
         * 发送mq消息json传key
         */
        public static final String MQ_JSON_KEY_REVOKE_MSG_TIP = "tip";
        /**
         * 被撤回的消息
         * 发送mq消息json传key
         */
        public static final String MQ_JSON_KEY_REVOKE_MSG = "revokeMsgInfo";
        /**
         * 消息撤销提示语
         */
        public static final String REVOKE_MSG_TIP = "你撤回了一条消息";
    }

    /**
     * 使用redis发送消息的消息类型
     */
    public static class RedisNoticeType{
        /**
         * 通知公告前缀
         */
        public static final String APP_NOTICE_TYPE = "APP_NOTICE_TYPE_";
        /**
         * 通知公告前缀
         */
        public static final String APP_GROUP_MSG_TYPE = "APP_GROUP_MSG_TYPE_";
        /**
         * 通知公告锁前缀
         */
        public static final String APP_GROUP_MSG_TYPE_LOCK = "APP_GROUP_MSG_TYPE_LOCK";
        /**
         * client标识
         */
        public static final String CLIENT_KEY = "CLIENT";
        /**
         * 未读数量redis key前缀
         */
        public static final String GROUP_MSG_UNREAD_NUM = "GROUP_MSG_UNREAD_NUM";
        /**
         * 未读数量同步锁redis key前缀
         */
        public static final String GROUP_MSG_UNREAD_NUM_LOCK = "GROUP_MSG_UNREAD_NUM_LOCK";
        /**
         * 应用消息配置redis 缓存key
         */
        public static final String APPLICATION_MESSAGE_CONF = "APPLICATION_MESSAGE_CONF";
    }

    /**
     * 请假申请审核状态
     */
    public static class ASK_LEAVE_STATUS {
        /**
         * 未审批
         */
        public static final Integer UNAPPROVED = 0;
        /**
         * 审批未通过
         */
        public static final Integer FAIL_APPROVE = 1;
        /**
         * 审批通过
         */
        public static final Integer APPROVE = 2;
    }

    /**
     * 请假标题
     */
    public static String ASK_LEAVE_TITLE = "提交的请假";

    public static class TAB_SYSTEM_CONF{
        /**
         * 最后统计查询行为点评时间配置
         */
        public static final String LAST_STAT_BEHAVIOR_TIME_ID = "LAST_STAT_BEHAVIOR_TIME";
        /**
         * 行为点评类型默认图片id
         */
        public static final String BEHAVIOR_REVIEW_DEFAULT_FILE_ID = "BEHAVIOR_REVIEW_DEFAULT_FILE_ID";
        /**
         * 及时消息可撤回限、可重新编辑制配置(单位：秒)
         */
        public static final String IM_GROUP_MSG_REVOKE_TIME_CONF = "IM_GROUP_MSG_REVOKE_TIME_CONF";
        /**
         * 应用消息配置-应用消息类型对应应用消息群组类型id
         */
        public static final String APPLICATION_MESSAGE_CONF = "APPLICATION_MESSAGE_CONF";
        /**
         * 忘记密码验证code有效期 单位：秒
         */
        public static final String FORGET_PASSWORD_VALIDATE_CODE_VALID_TIME_CONF_ID = "FORGET_PASSWORD_VALIDATE_CODE_VALID_TIME";
    }

    /**
     * 是否
     */
    public static class WHETHER {
        /**
         * 否
         */
        public static final Integer NO = 0;
        /**
         * 是
         */
        public static final Integer YES = 1;
    }

    /**
     * 行为类型积分常量
     */
    public static class BEHAVIOR_CONST{
        /**
         * day字段前缀
         */
        public static final String DAY_PREFIX = "day";
    }

    /**
     * dic配置常量
     */
    public static class TAB_DIC_CONST{
        /**
         * 考勤状态
         */
        public static final Integer ATTENDANCE_STATE = 2023062701;
        /**
         * 行为大类型
         */
        public static final Integer BEHAVIOR_TYPE = 2023070301;

        public static final Integer FAMILY_RELATION_TYPE = 2023070101;

    }
    public static class ROLE_ID{
        public static final String TEARCHER_ROLE = "1c5b02f77e71440097928ede2e4394c8";
        public static final String PARENT_ROLE = "1c5b02f77e71440097928ede2e4394c7";
        public static final String AUDIT_ADMIN_ROLE = "audit_admin";
        public static final String[] TEARCHER_PERMISSIONS =  {"audit_admin","1c5b02f77e71440097928ede2e4394c8"};
    }

    /**
     * 等级制
     */
    public static class SCORE_LEVEL{
        public static final String UNIT_DEFAULT = "default";
        public static final String A = "A";
        public static final String B = "B";
        public static final String C = "C";
        public static final String D = "D";
    }

    /**
     * 默认发送公众号消息的类型
     */
    public static List<Integer> sendNoticeTypes = Arrays.asList(1, 2, 3, 4, 5, 7);

    /**
     * redis - key
     */
    public static class REDIS_KEY {
        public static final String OFFICIAL_ACCOUNT_TOKEN = "official_account_token";
    }

    /**
     * 基础类型id
     */
    public static class BEHAVIOR_REVIEW_BASE_TYPE {
        public static final String CLOCK_IN_ID = "fa722096-63a5-4a15-bdc5-c0d35811923e";
    }

    /**
     * 客户端类型
     */
    public static List<ClientType> clientTypeList = Arrays.asList(ClientType.CLIENT_TYPE_EXE,
            ClientType.CLIENT_TYPE_APP, ClientType.CLIENT_TYPE_APPLET);

    /**
     * 家校通讯录变更信息mq routing key
     */
    public static class HOME_SCHOOL_ROUTING_KEY {
        public static final String ORG = "home_school_org";
        public static final String STUDENT_PARENT = "student";
        public static final String STUDENT = "person_student";
    }

}
