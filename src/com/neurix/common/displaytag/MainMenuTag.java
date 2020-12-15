package com.neurix.common.displaytag;

import com.neurix.authorization.user.model.UserDetailsLogin;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import org.apache.log4j.*;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 06/02/13
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuTag extends TagSupport {
    private BiodataBo biodataBoProxy;
    private Biodata biodata;

    public BiodataBo getBiodataBoProxy() {
        return biodataBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    protected static transient Logger logger = Logger.getLogger(MainMenuTag.class);

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        initParameters();
        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException {
        logger.debug("begin execute method doEndTag ");

        JspWriter out = pageContext.getOut();
        try {
            out.write(this.getScriptCodes());
        } catch (IOException e) {
            logger.error("[doEndTag] error renderer tag popup, ", e);
            throw new JspException();
        }

        logger.debug("end execute method doEndTag ");

        return EVAL_BODY_INCLUDE;
    }

    private String getScriptCodes() {
        logger.debug("begin execute method getScriptCodes ");

        StringBuilder addedScript = new StringBuilder();

        SimpleDateFormat format = new SimpleDateFormat("EEEEEE, d MMM yyyy");
        String formatedDate = format.format(new Date());
        String userId = "";
        String companyName = "";
        String areaName = "";
        String branchName = "";
        String divisiName = "";
        String photoUserUrl = "";
        String photoUpload = "";
        String statusCaption = "";

        //for payment gateway
        String customerId = "";
        String customerName = "";
        String customerAddress = "";
        String customerNPWP = "";

        //tambahan posisi user
        String positionName = "";
        String positionId = "";
        String divisiId = "";
        List<String> listOfMenuString = new ArrayList<String>();
        String userName = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HttpSession session = ServletActionContext.getRequest().getSession();
        String contextPath = ServletActionContext.getRequest().getContextPath();
        String logoutUrl = contextPath + CommonConstant.LOGOUT_URL;
        String sessionMonitoringUrl = contextPath + CommonConstant.SESSION_URL;
        String notificationUrl = contextPath + CommonConstant.NOTIFICATION_URL;
        SessionRegistryImpl sessionRegistry = (SessionRegistryImpl) ctx.getBean("sessionRegistry");
        SessionInformation sessionInfo = (sessionRegistry != null ? sessionRegistry.getSessionInformation(session.getId()) : null);
        UserDetailsLogin userDetailsLogin;

        if (sessionInfo != null) {
            userDetailsLogin = (UserDetailsLogin) sessionInfo.getPrincipal();

            if (userDetailsLogin != null) {
                userId = userDetailsLogin.getUsername();
                userName = userDetailsLogin.getUserNameDetail();
                companyName = userDetailsLogin.getCompanyName();
                areaName = userDetailsLogin.getAreaName();
                branchName = userDetailsLogin.getBranchName();
                if(userDetailsLogin.getDivisiName() != null){
                    divisiName = userDetailsLogin.getDivisiName();
                }else{
                    divisiName = "";
                }
                positionId = userDetailsLogin.getPositionId();
                positionName = userDetailsLogin.getPositionName();
                divisiId = userDetailsLogin.getDivisiId();
                statusCaption = userDetailsLogin.getStatusCaption();
                photoUpload = userDetailsLogin.getPhotoUpload();
                listOfMenuString = userDetailsLogin.getMenus();


                //for payment-gateway
//                customerId = userDetailsLogin.getCustomerId();
//                customerName = userDetailsLogin.getCustomerName();
//                customerAddress = userDetailsLogin.getCustomerAddress();
//                customerNPWP = userDetailsLogin.getCustomerNPWP();


                if (userDetailsLogin.getPhotoUpload()!=null) {

                    //set windows or linux path first
//                    photoUserUrl = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY  + contextPath  + userDetailsLogin.getPhotoUserUrl();


                    photoUserUrl = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() +
                            CommonConstant.RESOURCE_PATH_USER_UPLOAD + userDetailsLogin.getPhotoUpload();

                    File filePhoto = new File(photoUserUrl);

                    //File f = new File(filePathString);
                    if(filePhoto.exists() && !filePhoto.isDirectory()) {
                        // do something
                        photoUserUrl = contextPath  + CommonConstant.RESOURCE_PATH_USER_PHOTO + userDetailsLogin.getPhotoUpload();
                    }else{
                        photoUserUrl = contextPath  + CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO;
                    }

                    /*if (filePhoto!=null) {
                        if (filePhoto.length() == 0) {
                            photoUserUrl = contextPath  + CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO;
                        } else {
                            //photoUserUrl = contextPath  + userDetailsLogin.getPhotoUserUrl();
                            photoUserUrl = contextPath  + userDetailsLogin.getPhotoUpload();
//                            photoUserUrl =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY  + contextPath + userDetailsLogin.getPhotoUserUrl();
                        }
                    } else {
                        photoUserUrl = contextPath  + CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO;
//                        photoUserUrl =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY   + contextPath + CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO;
                    }*/

                } else {
                    photoUserUrl = contextPath  + CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO;
//                    photoUserUrl =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY   + contextPath + CommonConstant.RESOURCE_PATH_USER_PHOTO + CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO;
                }

                session.setAttribute("user_name", userName);
                session.setAttribute("user_area", areaName);
                session.setAttribute("user_branch", branchName);
                session.setAttribute("user_position", positionName);
                session.setAttribute("user_photo", photoUserUrl);
            }
        }
        /*if(statusCaption.equals("") || statusCaption == null){
            statusCaption = "Available";
        }*/


        addedScript.append(" <!-- Left side column. contains the logo and sidebar -->\n" +
                "  <aside class=\"main-sidebar\">\n" +
                "\n" +
                "    <!-- sidebar: style can be found in sidebar.less -->\n" +
                "    <section class=\"sidebar\">\n" +
                "\n" +
                "      <!-- Sidebar user panel (optional) -->\n" +
//                "      <div class=\"user-panel\">\n" +
//                "        <div class=\"pull-left image\">\n" +
//                "          <a id='popoverData' rel=\"popover\" data-placement=\"bottom\" data-original-title='<b>"+userName+"</b>' data-trigger=\"hover\" " +
//                "data-content='<b>Branch : " + branchName +" <br/>Divisi : "+divisiName+"<br/> Position : "+positionName+" </b>' data-html='true' data-container='body' data-toogle='tooltip'"+
//                "href='/hris/biodata/Form_biodata.action'><img src=\"").append(photoUserUrl).append("\" class=\"img-circle\" alt=\"User Image\" width='50px'></a>\n" +
//                "        </div>\n" +
//                "        <div class=\"pull-left info\">\n" +
//                "          <p>").append(userName).append("</p>\n" +
//                "          <!-- Status -->\n" +
//                "          <a href=\"#\"><i class=\"fa fa-circle text-success\"></i> "+ statusCaption +"</a>\n" +
//                "        </div>\n" +
//                "      </div>\n" +
                "\n"
        );

        addedScript.append("<ul class=\"sidebar-menu\" style=\"margin-top:10px\">\n" +
//                "        <li class=\"header\">MAIN MENU</li>\n" +
                "        <!-- Optionally, you can add icons to the links -->\n");

        //looping menu
        for (String menuString : listOfMenuString) {
            addedScript.append(menuString);
        }


        addedScript.append("</ul>\n" +
                "      <!-- /.sidebar-menu -->\n" +
                "    </section>\n" +
                "    <!-- /.sidebar -->\n" +
                "  </aside>");

        logger.debug("end execute method getScriptCodes ");

        return addedScript.toString();
    }

    public void release() {
        super.release();
    }

    protected void initParameters() {

    }
}
