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

    public final static String EDIT_BTN = "/pages/images/icon_edit.ico\" border=\"none\" cursor:hand;\" >";
    public final static String VIEW_BTN = "/pages/images/icon_lup.ico\" border=\"none\" cursor:hand;\" >";
    public final static String DELETE_BTN = "/pages/images/icon_trash.ico\" border=\"none\" cursor:hand;\" >";
    public final static String PRINT_BTN = "/pages/images/icon_printer.ico\" border=\"none\" cursor:hand;\" >";

    public final static String EDIT = "edit";
    public final static String VIEW = "view";
    public final static String DELETE = "delete";
    public final static String PRINT = "print";

    public final static String ID = "id";
    public final static String DEFAULT_PASSWORD = "123456";

    //    public final static String RESOURCE_PATH_USER_PHOTO="/pages/images/";
    public final static String RESOURCE_PATH_IMAGES = "/pages/images/";
    public final static String RESOURCE_PATH_USER_PHOTO = "/pages/upload/image/profile/";
    public final static String RESOURCE_PATH_UNKNOWN_PHOTO = "unknown-person.png";
    public final static String RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI = "unknown-person.png";
    public final static String RESOURCE_PATH_SPPD_FILE = "/pages/upload/file/sppd/";
    public final static String RESOURCE_PATH_REKRUITMEN_FILE = "/pages/upload/file/rekruitmen/";
    public final static String RESOURCE_PATH_MUTASI_FILE = "/pages/upload/file/mutasi/";

    public final static String RESOURCE_PATH_KTP_PASIEN = "/upload/ktp_pasien/";
    public final static String RESOURCE_PATH_DOC_RUJUK_PASIEN = "/upload/surat_rujuk/";
    public final static String RESOURCE_PATH_DOC_PO = "/upload/surat_po/";
    public final static String RESOURCE_PATH_TTD_PASIEN = "/upload/ttd_pasien/";
    public final static String RESOURCE_PATH_DOC_FPK = "/upload/fpk/";
    public final static String RESOURCE_PATH_TTD_DOKTER = "/upload/ttd_dokter/";
    public final static String RESOURCE_PATH_TTD_APOTEKER = "/upload/ttd_apoteker/";

    public final static String RESOURCE_PATH_USER_UPLOAD_PAYMENT_BANK = "/pages/upload/";
    public final static String RESOURCE_PATH_USER_UPLOAD = "/pages/upload/image/profile/";
    public final static String RESOURCE_PATH_DEFAULT_MAP = "/pages/upload/map/";
    public final static String RESOURCE_PATH_SAVED_UPLOAD_PAYMENT_DIRECTORY = "C:\\xampp\\tomcat\\webapps\\";

    public final static String RESOURCE_PATH_USER_UPLOAD_IJAZAH = "/pages/upload/image/ijazah/";

    //sodiq, 18 Nov 2019, Upload KTP pasien
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = "C:\\Users\\user\\Pictures";
    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = "C:\\Users\\pc001\\Pictures";
    public final static String RESOURCE_PATH_IMG_ASSET = "C:\\apache-tomcat-8.5.40\\webapps";
//    public final static String RESOURCE_PATH_IMG_ASSET = "/opt/tomcat/webapps";
//    public final static String RESOURCE_PATH_IMG_ASSET = "C:\\tomcat\\webapps";


    //    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY="C:\\Users\\Toshiba\\Pictures";

//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = "/mnt/image";
    public final static String EXTERNAL_IMG_URI = "/simrs/images";

    public final static String RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY = CommonUtil.getUploadFolderValue();

//    public final static String RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY="/usr/share/tomcat7-webapps";

    public final static String RESOURCE_PATH_DEFAULT_PHOTO_UPLOAD = "no-image-upload.jpg";
    public final static String RESOURCE_PATH_DEFAULT_KTP = "petani/ktp-default.jpg";


    public final static String LOGOUT_URL = "/j_spring_security_logout";
    public final static String SESSION_URL = "/admin/usersessionlog/initForm_usersessionlog.action";
    public final static String NOTIFICATION_URL = "/notificationlahan/initForm_notificationlahan.action";

    public final static String EXCEL = "xls";

    public final static String PRENUMBER_INVOICE_REKAP = "199515"; //airnav - nomor abjad, yang lebih dari satu digit di jumlah

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

    public final static String RESOURCE_PATH_USER_UPLOAD_PETANI = "/pages/upload/petani/";
    public final static String RESOURCE_PATH_USER_UPLOAD_DOC = "/pages/upload/doc/";
    public final static String RESOURCE_PATH_USER_UPLOAD_PDF = "/pages/upload/doc/pdf/";

    //    public final static String URL_IMAGE_LOGO_REPORT = "C:\\Users\\Ferdi\\Downloads\\airnav2.jpg";
//    public final static String URL_IMAGE_LOGO_REPORT = "C:\\tomcat-7-64x\\webapps\\hris\\pages\\images\\LOGO-RW.png";
//        public final static String URL_IMAGE_LOGO_REPORT = "/opt/tomcat/webapps/simrs/pages/images/LOGO-RW.png";

    public final static String URL_IMAGE_LOGO_REPORT = "C:\\tomcat\\webapps\\simrs\\pages\\images\\logo-nmu.png";
    public final static String URL_LOGO_REPORT = "C:\\tomcat\\webapps\\simrs\\pages\\images\\";

//    public final static String URL_IMAGE_LOGO_REPORT = "C:\\apache-tomcat-8.5.40\\webapps\\simrs\\pages\\images\\LOGO-RW.png";
//    public final static String URL_LOGO_REPORT = "C:\\apache-tomcat-8.5.40\\webapps\\simrs\\pages\\images\\";

//    public final static String URL_IMAGE_LOGO_REPORT = "/opt/tomcat/webapps/simrs/pages/images/LOGO-RW.png";
//    public final static String URL_LOGO_REPORT = "/opt/tomcat/webapps/simrs/pages/images/";

    public final static String RESOURCE_DOCUMENT_PAYROLL = "/opt/tomcat/webapps/mnt/documents/";

    public final static String URL_IMAGE_LOGO_REPORT_BACKGROUND_PRAPRINT = "C:\\project\\e-farming\\web\\pages\\images\\pre-print.png";

    public final static String REQUEST_CREDIT_NOTE = "RCN";
    public final static String APPROVE_CREDIT_NOTE = "ACN";
    public final static String NOTAPPROVE_CREDIT_NOTE = "NCN";


    public final static String REPORT_PAYROLL = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayroll3.jrxml";
    public final static String REPORT_PAYROLL_THR = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayrollThrBranch3.jrxml";
    public final static String REPORT_PAYROLL_JASPROD = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayrollJasoprBranch3jrxml";
    public final static String REPORT_PAYROLL_PENSIUN = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayrollPensiun3.jrxml";
    public final static String REPORT_PAYROLL_PMP = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayrollJubileum3.jrxml";
    public final static String REPORT_PAYROLL_INSENTIF = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayrollInsentifBranch3.jrxml";
    public final static String REPORT_PAYROLL_CUTI_PANJANG = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayrollCutiPanjangBranch3.jrxml";
    public final static String REPORT_PAYROLL_CUTI_TAHUNAN = "/opt/tomcat/webapps/simrs/pages/report/com/neurix/hris/reportPayrollCutiTahunanBranch3.jrxml";


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
    public final static String ROLE_ADMIN_APOTEK = "ADMIN APOTEK";

    public final static String BRANCH_KANTOR_DIREKSI = "KD01";
    public final static String CS_LOGIN = "CUSTOMER_SERVICE";

    public final static String HGU = "HGU";

    //BPJS
    public final static String APP_NAME = "simrs";

    //for prod
    public final static String BPJS_BASE_URL = "https://new-api.bpjs-kesehatan.go.id:8080";
    public final static String BPJS_SERVICE_VKLAIM = "/new-vclaim-rest/";

    //developer purpose only
//    public final static String BPJS_BASE_URL = "https://dvlp.bpjs-kesehatan.go.id";
//    public final static String BPJS_SERVICE_VKLAIM = "/vclaim-rest/";
    public final static String BPJS_SERVICE_APLICARE = "/aplicaresws/rest/";
    public final static String BPJS_SERVICE_PCARE = "/pcare-rest-v3.0";
    public final static String EKLAIM_SERVICE = "/E-Klaim/ws.php";
    public final static String EKLAIM_SERVICE_DEBUG = "/E-Klaim/ws.php?mode=debug";

    //FINGER
    public final static String baseUrl = "http://192.168.43.222:8080/simrs/";
    public final static String timeLimitReg = "15";
    public final static String timeLimitVer = "10";
    public final static String verAddress = baseUrl + "prosesLoginFinger.action";
    public final static String regAddress = baseUrl + "prosesRegisterFinger.action";
    public final static String addRawatPasien = baseUrl + "checkup/add_checkup.action";


    public final static String LOGO_RS01 = "/pages/images/RS01.png";
    public final static String LOGO_RS02 = "/pages/images/RS02.png";
    public final static String LOGO_RS03 = "/pages/images/RS03.png";
    public final static String LOGO_KP = "/pages/images/KP.png";
    public final static String LOGO_NMU = "/pages/images/logo-nmu-copy.png";

    public final static String LOGO_REPORT_PAYROLL = "C:\\tomcat-7-64x\\webapps\\simrs\\pages\\images\\logo-nmu-copy.png";
//    public final static String LOGO_REPORT_PAYROLL = "/opt/tomcat/webapps/simrs/pages/images/logo-nmu-copy.png";

    public final static String IMAGE_CARD = "/pages/images/card.png";


    public final static String BRANCH_RS01 = "RS01";
    public final static String BRANCH_RS02 = "RS02";
    public final static String BRANCH_RS03 = "RS03";

    public final static String URL_IMAGE_CARD = "C:\\tomcat\\webapps\\simrs\\pages\\images\\card.png";

    public final static String RESOURCE_PATH_USER_UPLOAD_KTP_PASIEN = "/pages/upload/image/pasien/";

    public final static String RESOURCE_PATH_JRXML_QRCODE_ALAT = "/opt/tomcat/webapps/pmsapb/pages/report/com/neurix/apbpln/printQRCode.jrxml/";
    public final static String RESOURCE_DOCUMENT_APB = "/opt/tomcat/webapps/mnt/documents/simrs/";
    public final static String RESOURCE_IMAGE_APB = "/opt/tomcat/webapps/mnt/images/simrs/";
    //    public static final String RESOURCE_IMAGE_TTD = "/opt/tomcat/webapps/mnt/images/";
    public static final String RESOURCE_IMAGE_TTD = "C:/Users/pc001/Pictures/";
    public final static String IMAGE_TYPE = ".png";
    public final static String DOC_TYPE = ".pdf";

    public final static String URL_IMG = "/images/";
    public final static String URL_DOC = "/documents/";
    public final static String URL_IMG_RM = "rekam_medic";


}