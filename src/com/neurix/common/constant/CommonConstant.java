package com.neurix.common.constant;

import com.neurix.common.util.CommonUtil;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/10/12
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
public class CommonConstant {

    public final static String PREV_TAG_BTN = "<img src=\"";
//    public final static String EDIT_BTN  = "/pages/images/edit.gif\" border=\"none\" cursor:hand;\" >";
//    public final static String VIEW_BTN = "/pages/images/lup.gif\" border=\"none\" cursor:hand;\" >";
//    public final static String DELETE_BTN = "/pages/images/trash.gif\" border=\"none\" cursor:hand;\" >";
//    public final static String PRINT_BTN = "/pages/images/printer.gif\" border=\"none\" cursor:hand;\" >";

    public final static String EDIT_BTN  = "/pages/images/icon_edit.ico\" border=\"none\" cursor:hand;\" >";
    public final static String VIEW_BTN = "/pages/images/icon_lup.ico\" border=\"none\" cursor:hand;\" >";
    public final static String DELETE_BTN = "/pages/images/icon_trash.ico\" border=\"none\" cursor:hand;\" >";
    public final static String PRINT_BTN = "/pages/images/icon_printer.ico\" border=\"none\" cursor:hand;\" >";

    public final static String EDIT="edit";
    public final static String VIEW="view";
    public final static String DELETE="delete";
    public final static String PRINT="print";

    public final static String ID="id";
    public final static String DEFAULT_PASSWORD="123456";

//    public final static String RESOURCE_PATH_USER_PHOTO="/pages/images/";
    public final static String RESOURCE_PATH_USER_PHOTO="/pages/upload/image/profile/";
    public final static String RESOURCE_PATH_UNKNOWN_PHOTO="unknown-person.png";
    public final static String RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI="unknown-person.png";
    public final static String RESOURCE_PATH_SPPD_FILE="/pages/upload/file/sppd/";
    public final static String RESOURCE_PATH_REKRUITMEN_FILE="/pages/upload/file/rekruitmen/";
    public final static String RESOURCE_PATH_MUTASI_FILE="/pages/upload/file/mutasi/";

    public final static String RESOURCE_PATH_KTP_PASIEN="/upload/ktp_pasien/";

    public final static String RESOURCE_PATH_USER_UPLOAD_PAYMENT_BANK="/pages/upload/";
    public final static String RESOURCE_PATH_USER_UPLOAD="/pages/upload/image/profile/";
    public final static String RESOURCE_PATH_DEFAULT_MAP ="/pages/upload/map/";
    public final static String RESOURCE_PATH_SAVED_UPLOAD_PAYMENT_DIRECTORY="C:\\xampp\\tomcat\\webapps\\";
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY="C:\\tomcat-7-64x\\webapps";

    //sodiq, 18 Nov 2019, Upload KTP pasien
    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY="C:\\Users\\pc001\\Pictures";
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY="C:\\Users\\Toshiba\\Pictures";
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY="/mnt/image";

    public final static String RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY = CommonUtil.getUploadFolderValue();

//    public final static String RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY="/usr/share/tomcat7-webapps";

    public final static String RESOURCE_PATH_DEFAULT_PHOTO_UPLOAD ="no-image-upload.jpg";
    public final static String RESOURCE_PATH_DEFAULT_KTP ="petani/ktp-default.jpg";


    public final static String LOGOUT_URL="/j_spring_security_logout";
    public final static String SESSION_URL = "/admin/usersessionlog/initForm_usersessionlog.action";
    public final static String NOTIFICATION_URL = "/notificationlahan/initForm_notificationlahan.action";

    public final static String EXCEL="xls";

    public final static String PRENUMBER_INVOICE_REKAP="199515"; //airnav - nomor abjad, yang lebih dari satu digit di jumlah

    public final static String INPROGRESS_ICON = "/pages/images/icon_payment.ico\" border=\"none\" cursor:hand;\" >";
    public final static String SUCCESS_ICON = "/pages/images/icon_success.ico\" border=\"none\" cursor:hand;\" >";
    public final static String FAILURE_ICON = "/pages/images/icon_failure.ico\" border=\"none\" cursor:hand;\" >";
    public final static String REVERSAL_ICON = "/pages/images/icon_trash.ico\" border=\"none\" cursor:hand;\" >";
    public final static String NOT_PAID_ICON = "/pages/images/icon_warning.ico\" border=\"none\" cursor:hand;\" >";
    public final static String WARNING_ICON = "/pages/images/icon_warning.ico\" border=\"none\" cursor:hand;\" >";
    public final static String RECONCILE_ICON = "/pages/images/icon_reconcile.ico\" border=\"none\" cursor:hand;\" >";
    public final static String UNPARSE_ICON = "/pages/images/icon_warning2.ico\" border=\"none\" cursor:hand;\" >";

    public final static String NOT_PAID_NOT_UPLOAD_ICON = "/pages/images/icon_warning2.ico\" border=\"none\" cursor:hand;\" >";
    public final static String RECONCILE_WITH_UPLOAD_ICON = "/pages/images/icon_reconcile.ico\" border=\"none\" cursor:hand;\" >";

    public final static String MOBILE_ICON = "/pages/images/icon_mobile.ico\" border=\"none\" cursor:hand;\" >";
    public final static String WEB_ICON = "/pages/images/icon_web.ico\" border=\"none\" cursor:hand;\" >";
    public final static String SURVEY_ICON = "/pages/images/icon_reg_survey.png\" border=\"none\" cursor:hand;\" >";

    public final static String USER_MOVE_ICON = "/pages/images/User_move.png\" border=\"none\" cursor:hand;\" >";
    public final static String USER_REMOVE_ICON = "/pages/images/User_remove.png\" border=\"none\" cursor:hand;\" >";
    public final static String USER_REPLACE_ICON = "/pages/images/User_replace.png\" border=\"none\" cursor:hand;\" >";
    public final static String USER_STILL_ICON = "/pages/images/User_still.png\" border=\"none\" cursor:hand;\" >";
    public final static String USER_NEW_ICON = "/pages/images/User_new.png\" border=\"none\" cursor:hand;\" >";
    public final static String USER_DONE_ICON = "/pages/images/User_done.png\" border=\"none\" cursor:hand;\" >";
    public final static String USER_INPROGRESS_ICON = "/pages/images/User_progress.png\" border=\"none\" cursor:hand;\" >";
    public final static String USER_RESET_ICON = "/pages/images/icon_reset.png\" border=\"none\" cursor:hand;\" >";

    public final static String RESOURCE_PATH_USER_UPLOAD_PETANI="/pages/upload/petani/";
    public final static String RESOURCE_PATH_USER_UPLOAD_DOC="/pages/upload/doc/";
    public final static String RESOURCE_PATH_USER_UPLOAD_PDF="/pages/upload/doc/pdf/";

//    public final static String URL_IMAGE_LOGO_REPORT = "C:\\Users\\Ferdi\\Downloads\\airnav2.jpg";
//    public final static String URL_IMAGE_LOGO_REPORT = "C:\\tomcat-7-64x\\webapps\\hris\\pages\\images\\LOGO-RW.png";
    public final static String URL_IMAGE_LOGO_REPORT = "/opt/tomcat/webapps/simrs/pages/images/LOGO-RW.png";
    public final static String RESOURCE_DOCUMENT_PAYROLL = "/opt/tomcat/webapps/mnt/documents/";

    public final static String URL_IMAGE_LOGO_REPORT_BACKGROUND_PRAPRINT = "C:\\project\\e-farming\\web\\pages\\images\\pre-print.png";

    public final static String REQUEST_CREDIT_NOTE = "RCN";
    public final static String APPROVE_CREDIT_NOTE = "ACN";
    public final static String NOTAPPROVE_CREDIT_NOTE = "NCN";

    //ptpnx e-farming
    public final static String ROLE_MANAGER_QC = "MANAGER QC";
    public final static String ROLE_MANAGER_TANAMAN = "MANAGER TANAMAN";
    public final static String ROLE_ASS_MANAGER_QC = "ASMAN QC";
    public final static String ROLE_ASS_MANAGER_TANAMAN = "ASMAN TANAMAN";
    public final static String ROLE_ADMIN = "ADMIN";
    public final static String ROLE_OPERATOR_GPS = "OPERATOR_GPS";
    public final static String ROLE_ASMUD = "ASMUD";
    public final static String ROLE_MAPPER = "MAPPER";
    public final static String ROLE_ADMIN_POLI = "ADMIN POLI";
    public final static String ROLE_ADMIN_RS = "ADMIN RS";
    public final static String ROLE_ADMIN_IGD = "ADMIN IGD";

    public final static String BRANCH_KANTOR_DIREKSI = "KD01";
    public final static String CS_LOGIN = "CUSTOMER_SERVICE";

    public final static String HGU = "HGU";
    public final static String APP_NAME = "simrs";
    public final static String BPJS_CONS_ID = "10356";
    public final static String BPJS_SECRET_KEY = "7eT69578CF";
    public final static String BPJS_BASE_URL = "https://new-api.bpjs-kesehatan.go.id:8080";
    public final static String BPJS_SERVICE_VKLAIM = "/new-vclaim-rest";
    public final static String BPJS_SERVICE_APLICARE = "/aplicaresws/rest";
    public final static String BPJS_SERVICE_PCARE = "/pcare-rest-v3.0";

    public final static String LOGO_RS01 = "/pages/images/RS01.png";
    public final static String LOGO_RS02 = "/pages/images/RS02.jpg";
    public final static String LOGO_RS03 = "/pages/images/RS03.png";

    public final static String RESOURCE_PATH_USER_UPLOAD_KTP_PASIEN="/pages/upload/image/pasien/";

    public final static String RESOURCE_PATH_JRXML_QRCODE_ALAT="/opt/tomcat/webapps/pmsapb/pages/report/com/neurix/apbpln/printQRCode.jrxml/";
    public final static String RESOURCE_DOCUMENT_APB="/opt/tomcat/webapps/mnt/documents/simrs/";
    public final static String RESOURCE_IMAGE_APB="/opt/tomcat/webapps/mnt/images/simrs/";
    public final static String IMAGE_TYPE=".jpg";
    public final static String DOC_TYPE = ".pdf";

    public final static String URL_IMG="/images/";
    public final static String URL_DOC="/documents/";


}