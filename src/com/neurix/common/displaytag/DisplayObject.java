package com.neurix.common.displaytag;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/10/12
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */

import com.neurix.common.constant.CommonConstant;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

public class DisplayObject implements Serializable {

    protected static transient Logger logger = Logger.getLogger(DisplayObject.class);

    private String modalWindow;
    private String checkBox;
    private String idCheckBox;
    private boolean checkBoxChecked;
    private boolean checkBoxReadOnly;
    private String radio;
    private String idRadio;
    private boolean radioChecked;
    private String div;

    private String newPage;

    public boolean isCheckBoxReadOnly() {
        return checkBoxReadOnly;
    }

    public void setCheckBoxReadOnly(boolean checkBoxReadOnly) {
        this.checkBoxReadOnly = checkBoxReadOnly;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public void setNewPage(String newPage) {
        this.newPage = newPage;
    }

    public String getNewPage() {
        return newPage;
    }

    public String getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String checkBox) {
        this.checkBox = checkBox;
    }

    public String getIdCheckBox() {
        return idCheckBox;
    }

    public void setIdCheckBox(String idCheckBox) {
        this.idCheckBox = idCheckBox;
    }

    public boolean isCheckBoxChecked() {
        return checkBoxChecked;
    }

    public void setCheckBoxChecked(boolean checkBoxChecked) {
        this.checkBoxChecked = checkBoxChecked;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getIdRadio() {
        return idRadio;
    }

    public void setIdRadio(String idRadio) {
        this.idRadio = idRadio;
    }

    public boolean getRadioChecked() {
        return radioChecked;
    }

    public void setRadioChecked(boolean radioChecked) {
        this.radioChecked = radioChecked;
    }

    public String getModalWindow() {
        return modalWindow;
    }

    public void setModalWindow(String modalWindow) {
        this.modalWindow = modalWindow;
    }


    /**
     * used to goto popup page
     *
     * @param action          is complete action path to open popup
     * @param type            is edit, or view, or delete
     * @param id              is primary key of record
     * @param windowSize      is style of window size
     * @param refreshAction   is refresh action path
     * @param listSearchParam is list of search params(criteria) main form (ex : "obj.name,obj.address,obj.type")
     */
    public void setModalWindow(String action,
                               String type,
                               String id,
                               String windowSize,
                               String refreshAction,
                               List listSearchParam) {

        if (type != null) {
            String button = null;
            if (type.equalsIgnoreCase(CommonConstant.EDIT)) {
                button = CommonConstant.EDIT_BTN;
            } else if (type.equalsIgnoreCase(CommonConstant.VIEW)) {
                button = CommonConstant.VIEW_BTN;
            } else if (type.equalsIgnoreCase(CommonConstant.DELETE)) {
                button = CommonConstant.DELETE_BTN;
            } else if (type.equalsIgnoreCase(CommonConstant.PRINT)) {
                button = CommonConstant.PRINT_BTN;
            }

            if (button != null) {
                HttpServletRequest request = ServletActionContext.getRequest();
                StringBuilder sbAction = new StringBuilder();
                sbAction.append(request.getContextPath()).append(action);

                StringBuilder sbRefreshAction = new StringBuilder();
                if (refreshAction!=null && !"".equalsIgnoreCase(refreshAction)) {
                    sbRefreshAction.append(request.getContextPath()).append(refreshAction);
                } else {
                    sbRefreshAction.append("");
                }

                StringBuilder imgBuf = new StringBuilder();
                imgBuf.append(CommonConstant.PREV_TAG_BTN);
                imgBuf.append(request.getContextPath());
                imgBuf.append(button);

                ParamRequestObject paramRequestObject = new ParamRequestObject();
                paramRequestObject.setParamId(CommonConstant.ID);
                paramRequestObject.setParamValue(id);


                //showDialogOpen(action,sendListParam,windowSize,refreshAction,listSearchCriteria);
                StringBuilder sb = new StringBuilder();

                sb.append("<a href=\"#\" ")
                        .append("onClick=\"showDialogOpen(")
                        .append(jsString(sbAction.toString()))
                        .append(",'");

                sb.append(paramRequestObject.getParamId())
                        .append("=")
                        .append((paramRequestObject.getParamValue().replaceAll("'", "\\\\'")));

                sb.append("',");
                sb.append(jsString(windowSize));
                sb.append(",");
                sb.append(jsString(sbRefreshAction.toString()));

                if (listSearchParam != null) {
                    sb.append(",'");

                    String searchParam;
                    for (int i = 0; i < listSearchParam.size(); i++) {
                        searchParam = (String) listSearchParam.get(i);
                        sb.append(searchParam.replaceAll("'", "\\\\'"));

                        int j = i;
                        if (++j < listSearchParam.size()) sb.append(",");
                    }

                    sb.append("'");
                } else {
                    sb.append(",null");
                }

                sb.append(")");

                sb.append("\"   >")
                        .append(imgBuf.toString())
                        .append("</a>");

                this.modalWindow = sb.toString();

            } else {
                logger.error("Type is not 'edit','view', or 'delete'.");
                this.modalWindow = "";
            }
        } else {
            logger.error("Type modal window is null.");
            this.modalWindow = "";
        }
    }

    /**
     * convert string to JavaScript string
     * (put (') at beginning and end of string )
     *
     * @param str
     * @return
     */
    private String jsString(String str) {
        if (str != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("'")
                    .append(str.replaceAll("'", "\\\\'"))
                    .append("'");
            return buffer.toString();
        } else {
            return null;
        }
    }

    /**
     * used to set check box in grid table
     *
     * @param id
     * @param property
     * @param checked
     * @param readonly
     * @param onclick
     */
    public void setCheckBox(String id, String property, boolean checked, boolean readonly, String onclick) {
        this.setIdCheckBox(id);
        this.setCheckBoxChecked(checked);
        StringBuilder strTemp = new StringBuilder();
        strTemp.append("<input class=\"radio\" type=\"checkbox\" value=\"")
                .append(id)
                .append("\" name=\"")
                .append(property)
                .append("\" ");

        if (onclick != null) {
            strTemp.append(" onclick=\"")
                    .append(onclick)
                    .append("\" ");
        }

        if (checked) {
            strTemp.append("checked");
        }
        if (readonly) {
            strTemp.append(" disabled ");
        }

        strTemp.append(" >");
        this.checkBox = strTemp.toString();
    }

    /**
     * used to set check box in grid table
     *
     * @param id
     * @param property
     * @param checked
     * @param readonly
     */
    public void setCheckBox(String id, String property, boolean checked, boolean readonly) {
        setCheckBox(id, property, checked, readonly, null);
    }

    /**
     * call this if we want make radio button in the grid.
     *
     * @param id       is primary key of record
     * @param property is the name of this radio button
     * @param checked  is pointing status, true for pointed.
     * @param readonly is true for readonly, and false for enable editing
     */
    public void setRadio(String id, String property, boolean checked, boolean readonly) {

        this.setIdRadio(id);
        this.setRadioChecked(checked);
        StringBuffer strTemp = new StringBuffer();
        strTemp.append("<input class=\"radio\" type=\"radio\" value=\"")
                .append(id)
                .append("\" name=\"")
                .append(property)
                .append("\" ");
        if (checked) {
            strTemp.append("checked");
        }

        if (readonly) {
            strTemp.append(" disabled ");
        }

        strTemp.append(" >");
        this.radio = strTemp.toString();
    }


    /**
     * call this if we want make radio button in the grid.
     * add param onclick if needed
     *
     * @param id       is primary key of record
     * @param property is the name of this radio button
     * @param checked  is pointing status, true for pointed.
     * @param readonly is true for readonly, and false for enable editing
     * @param onclick  is function js
     */
    public void setRadio(String id, String property, boolean checked, boolean readonly, String onclick) {
        this.setIdRadio(id);
        this.setRadioChecked(checked);
        StringBuilder strTemp = new StringBuilder();
        strTemp.append("<input class=\"radio\" type=\"radio\" value=\"")
                .append(id)
                .append("\" name=\"")
                .append(property)
                .append("\" ");

        if (onclick != null) {
            strTemp.append(" onclick=\"")
                    .append(onclick)
                    .append("\" ");
        }

        if (checked) {
            strTemp.append("checked");
        }
        if (readonly) {
            strTemp.append(" disabled ");
        }

        strTemp.append(" >");
        this.radio = strTemp.toString();
    }


    /**
     * used to goto new page
     *
     * @param action              is complete action path to open page
     * @param type                is edit, or view, or delete
     * @param id                  is primary key of record
     */
    public void setNewPage(String action,
                           String type,
                           String id) {

        if (type != null) {
            String button = null;
            if (type.equalsIgnoreCase(CommonConstant.EDIT)) {
                button = CommonConstant.EDIT_BTN;
            } else if (type.equalsIgnoreCase(CommonConstant.VIEW)) {
                button = CommonConstant.VIEW_BTN;
            } else if (type.equalsIgnoreCase(CommonConstant.DELETE)) {
                button = CommonConstant.DELETE_BTN;
            } else if (type.equalsIgnoreCase(CommonConstant.PRINT)) {
                button = CommonConstant.PRINT_BTN;
            }

            if (button != null) {
                HttpServletRequest request = ServletActionContext.getRequest();
                StringBuilder sbAction = new StringBuilder();
                sbAction.append(request.getContextPath()).append(action);

                StringBuilder imgBuf = new StringBuilder();
                imgBuf.append(CommonConstant.PREV_TAG_BTN);
                imgBuf.append(request.getContextPath());
                imgBuf.append(button);

                ParamRequestObject paramRequestObject = new ParamRequestObject();
                paramRequestObject.setParamId(CommonConstant.ID);
                paramRequestObject.setParamValue(id);

                //openNewPage(action,sendListParam);
                StringBuilder sb = new StringBuilder();

                sb.append("<a href=\"#\" ")
                        .append("onClick=\"openNewPage(")
                        .append(jsString(sbAction.toString()))
                        .append(",'");

                sb.append(paramRequestObject.getParamId())
                        .append("=")
                        .append((paramRequestObject.getParamValue().replaceAll("'", "\\\\'")));

                sb.append("')");

                sb.append("\"   >")
                        .append(imgBuf.toString())
                        .append("</a>");

                this.newPage = sb.toString();

            } else {
                logger.error("Type is not 'edit','view', or 'delete'.");
                this.newPage = "";
            }
        } else {
            logger.error("Type new page is null.");
            this.newPage = "";
        }

    }

    /**
     * used to set div in style visibility:hidden, for store other value, pairing with checkbox
     *
     * @param value
     * @param property
     */
    public void setDiv(String value, String property) {
        StringBuilder strTemp = new StringBuilder();
        strTemp.append("<div style=\"visibility:hidden\" name=\"")
                .append(property)
                .append("\" ")
                .append(" >")
                .append(value)
                .append("</div>");

        this.div = strTemp.toString();
    }

}
