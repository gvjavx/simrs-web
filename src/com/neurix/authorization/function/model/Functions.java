package com.neurix.authorization.function.model;

import com.neurix.common.displaytag.DisplayObject;
import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 15/02/13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class Functions extends BaseModel implements Serializable, Comparable<Functions>, Cloneable {
    protected Long funcId;
    protected String stFuncId;
    protected String funcName;
    protected String url;
    protected Long parent;
    protected String stParent="";
    protected Long funcLevel;
    protected String stFuncLevel;
    private Long menu;
    private String stMenu;
    private boolean statusMenuFunc=false;

    protected DisplayObject displayObjectCheck;

    public DisplayObject getDisplayObjectCheck() {
        return displayObjectCheck;
    }

    public void setDisplayObjectCheck(DisplayObject displayObjectCheck) {
        this.displayObjectCheck = displayObjectCheck;
    }

    public String getStFuncId() {
        return stFuncId;
    }

    public void setStFuncId(String stFuncId) {
        this.stFuncId = stFuncId;
    }

    public String getStParent() {
        return stParent;
    }

    public void setStParent(String stParent) {
        this.stParent = stParent;
    }

    public String getStFuncLevel() {
        return stFuncLevel;
    }

    public void setStFuncLevel(String stFuncLevel) {
        this.stFuncLevel = stFuncLevel;
    }

    public String getStMenu() {
        return stMenu;
    }

    public void setStMenu(String stMenu) {
        this.stMenu = stMenu;
    }

    public boolean isStatusMenuFunc() {
        return statusMenuFunc;
    }

    public void setStatusMenuFunc(boolean statusMenuFunc) {
        this.statusMenuFunc = statusMenuFunc;
    }

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(Long funcLevel) {
        this.funcLevel = funcLevel;
    }

    public Long getMenu() {
        return menu;
    }

    public void setMenu(Long menu) {
        this.menu = menu;
    }

    public int compareTo(Functions o) {
        return this.funcName.toLowerCase().compareTo(o.getFuncName().toLowerCase());
    }

    public String getLabel() {
        return this.funcName;
    }

    @Override
    public String toString() {
        return "Functions{" +
                "funcId=" + funcId +
                ", funcName='" + funcName + '\'' +
                ", url='" + url + '\'' +
                ", parent=" + parent +
                ", funcLevel=" + funcLevel +
                ", menu=" + menu +
                ", action='" + action + '\'' +
                ", flag='" + flag + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
