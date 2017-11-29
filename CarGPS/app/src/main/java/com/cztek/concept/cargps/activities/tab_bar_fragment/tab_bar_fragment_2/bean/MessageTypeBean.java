package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.bean;

/**
 * Created by HH
 * Date: 2017/11/29
 */

public class MessageTypeBean {
    private Integer KeyValue;
    private String KeyStr;
    private Integer Count;
    private Boolean isFavorite;

    public MessageTypeBean(Integer keyValue, String keyStr, Integer count, Boolean isFavorite) {
        KeyValue = keyValue;
        KeyStr = keyStr;
        Count = count;
        this.isFavorite = isFavorite;
    }

    public Integer getKeyValue() {
        return KeyValue;
    }

    public void setKeyValue(Integer keyValue) {
        KeyValue = keyValue;
    }

    public String getKeyStr() {
        return KeyStr;
    }

    public void setKeyStr(String keyStr) {
        KeyStr = keyStr;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        this.isFavorite = favorite;
    }
}
