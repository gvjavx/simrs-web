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
    public final static String EDIT_BTN = "/pages/images/icon_edit.ico\" border=\"none\" cursor:hand;\" >";
    public final static String VIEW_BTN = "/pages/images/icon_lup.ico\" border=\"none\" cursor:hand;\" >";
    public final static String DELETE_BTN = "/pages/images/icon_trash.ico\" border=\"none\" cursor:hand;\" >";
    public final static String PRINT_BTN = "/pages/images/icon_printer.ico\" border=\"none\" cursor:hand;\" >";

    public final static String EDIT = "edit";
    public final static String VIEW = "view";
    public final static String DELETE = "delete";
    public final static String PRINT = "print";

    public final static String ID = "id";

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

    public final static String RESOURCE_PATH_USER_UPLOAD = "/pages/upload/image/profile/";
    public final static String RESOURCE_PATH_USER_UPLOAD_IJAZAH = CommonUtil.getPropertyParams("resource.dir")+"mnt/ijazah/";

    //sodiq, 18 Nov 2019, Upload KTP pasien
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = CommonUtil.getPropertyParams("upload.external.dir");
    public final static String RESOURCE_PATH_IMG_ASSET = CommonUtil.getPropertyParams("resource.dir");
    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = CommonUtil.getPropertyParams("upload.folder");

//    public final static String RESOURCE_PATH_IMG_ASSET = "C:\\apache-tomcat-8.5.40\\webapps";
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = "C:\\Users\\pc001\\Pictures";
//    public final static String RESOURCE_PATH_IMG_ASSET = "C:\\tomcat\\webapps";
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY="C:\\Users\\Toshiba\\Pictures";
//    public final static String RESOURCE_PATH_IMG_ASSET = CommonUtil.getPropertyParams("resource.dir");
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = "C:\\Users\\user\\Pictures";

    public final static String EXTERNAL_IMG_URI = "/simrs/images";
    public final static String RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY = CommonUtil.getUploadFolderValue();

    public final static String LOGOUT_URL = "/j_spring_security_logout";
    public final static String SESSION_URL = "/admin/usersessionlog/initForm_usersessionlog.action";
    public final static String NOTIFICATION_URL = "/notificationlahan/initForm_notificationlahan.action";

    public final static String EXCEL = "xls";

    public final static String RESOURCE_PATH_USER_UPLOAD_DOC = "/pages/upload/doc/";

//    public final static String URL_IMAGE_LOGO_REPORT = "/opt/tomcat/webapps/simrs/pages/images/LOGO-RW.png";
//    public final static String URL_LOGO_REPORT = "/opt/tomcat/webapps/simrs/pages/images/";
//    public final static String RESOURCE_DOCUMENT_PAYROLL = "/opt/tomcat/webapps/mnt/documents/";


    public final static String URL_IMAGE_LOGO_REPORT = CommonUtil.getPropertyParams("base.dir")+"pages/images/logo-nmu.png";
    public final static String URL_LOGO_REPORT = CommonUtil.getPropertyParams("base.dir")+"pages/images/";
    public final static String RESOURCE_DOCUMENT_PAYROLL = CommonUtil.getPropertyParams("resource.dir")+"mnt/documents/";

    public final static String REPORT_PAYROLL = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayroll3.jrxml";
    public final static String REPORT_PAYROLL_THR = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollThrBranch3.jrxml";
    public final static String REPORT_PAYROLL_JASPROD = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollJasoprBranch3jrxml";
    public final static String REPORT_PAYROLL_PENSIUN = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollPensiun3.jrxml";
    public final static String REPORT_PAYROLL_PMP = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollJubileum3.jrxml";
    public final static String REPORT_PAYROLL_INSENTIF = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollInsentifBranch3.jrxml";
    public final static String REPORT_PAYROLL_CUTI_PANJANG = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollCutiPanjangBranch3.jrxml";
    public final static String REPORT_PAYROLL_CUTI_TAHUNAN = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollCutiTahunanBranch3.jrxml";

    //ptpnx e-farming
    public final static String ROLE_ADMIN_POLI = "ADMIN POLI";
    public final static String ROLE_ADMIN_RS = "ADMIN RS";
    public final static String ROLE_ADMIN_IGD = "ADMIN IGD";
    public final static String ROLE_ADMIN_APOTEK = "ADMIN APOTEK";

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

    public final static String IMAGE_CARD = "/pages/images/card.png";

    public final static String BRANCH_RS01 = "RS01";
    public final static String BRANCH_RS02 = "RS02";
    public final static String BRANCH_RS03 = "RS03";

    public static final String RESOURCE_IMAGE_TTD = CommonUtil.getPropertyParams("upload.folder");
//    public static final String RESOURCE_IMAGE_TTD = "C:/Users/pc001/Pictures/";
//    public static final String RESOURCE_IMAGE_TTD = CommonUtil.getPropertyParams("resource.dir")+"mnt/images/";
    public final static String IMAGE_TYPE = ".png";

    public final static String URL_IMG = "/images/";
    public final static String URL_IMG_RM = "rekam_medic";


}