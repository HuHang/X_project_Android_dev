package com.cztek.concept.cargps.http;

/**
 * Created by charlot
 * Date: 2017/11/30.
 */

public class ApiStore {

    static final  String apiVersion = "/api/";

    public static final String getMessageTypeList_url = "Messages/AlarmMsgTypeCount";

    public static final String getFavoriteMessageTypeList_url = "Messages/FavoriteAlarmMsgTypeCount";

    public static final String getMessage_url = "Messages/UnReadAlarmMsgWithPage";

    public static final String getAllShop_url = "Shops/GetAllWithChild";

    public static final String getMonitorData_url = "shops/GetBindedGroupByShopsWithListWithLimit";

}
