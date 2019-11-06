package com.neurix.common.displaytag;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/10/12
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */


public class ParamRequestObject implements Serializable {

    private String paramId;
    private String paramValue;

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String toString() {
         StringBuilder sBuffer = new StringBuilder();
         sBuffer.append("[");
         sBuffer.append("paramId = ").append(getParamId()).append(",");
         sBuffer.append("paramValue = ").append(getParamValue());
         sBuffer.append("]");
         return sBuffer.toString();
    }

}
