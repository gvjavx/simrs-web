package com.neurix.common.constant;

import com.neurix.common.util.CommonUtil;
import org.apache.struts2.ServletActionContext;

import java.math.BigDecimal;

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
    public final static String RESOURCE_PATH_AREA_OPERASI = "/upload/penanda_area_operasi/";
    public final static String RESOURCE_PATH_TTD_RM = "/upload/ttd_rm/";
    public final static String RESOURCE_PATH_DOC_RM = "/upload/cetakan_rm/";
    public final static String RESOURCE_PATH_VIDEO_RM = "/upload/video_rm/";
    public final static String RESOURCE_PATH_PAYROLL = "/upload/payroll/";
    public final static String RESOURCE_PATH_BUKTI_TRANSFER = "/upload/bukti_transfer";
    public final static String RESOURCE_PATH_TTD_PETUGAS = "/upload/ttd_petugas/";
    public static final String RESOURCE_PATH_RESEP = "/upload/resep/";
    public final static String RESOURCE_PATH_IMG_RM = "/upload/img_rm/";
    public final static String RESOURCE_PATH_PHOTO_PROFILE = "/upload/profile/";
    public static final String RESOURCE_PATH_FOTO_KIRIM = "/upload/foto_kirim/";

//    public final static String RESOURCE_PATH_USER_UPLOAD = CommonUtil.getPropertyParams("resource.dir")+"/profile/";
    public final static String RESOURCE_PATH_USER_UPLOAD = "/pages/upload/image/profile/";
    public final static String RESOURCE_PATH_USER_UPLOAD_IJAZAH = CommonUtil.getPropertyParams("resource.dir")+"mnt/ijazah/";
    public final static String RESOURCE_PATH_USER_UPLOAD_SURAT_DOKTER = CommonUtil.getPropertyParams("resource.dir")+"mnt/surat/";

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

    public final static String EXTERNAL_IMG_URI = ServletActionContext.getRequest().getContextPath()+"/images";
    public final static String EXTERNAL_IMG_URI_PROFILE = ServletActionContext.getRequest().getContextPath()+"/images/profile/";
//    public final static String EXTERNAL_IMG_URI_PROFILE = ServletActionContext.getRequest().getContextPath()+"/profile/";
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
    public final static String IMAGE_LOGO_KP = "KP.png";
    public final static String RESOURCE_DOCUMENT_PAYROLL = CommonUtil.getPropertyParams("upload.folder2")+CommonConstant.RESOURCE_PATH_PAYROLL;

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
    public final static String APP_NAME = ServletActionContext.getRequest().getContextPath();
//    public final static String APP_NAME = "simrs";

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
    public final static String BRANCH_KP = "KP";

    public static final String RESOURCE_IMAGE_TTD = CommonUtil.getPropertyParams("upload.folder");
    //    public static final String RESOURCE_IMAGE_TTD = "C:/Users/pc001/Pictures/";
//    public static final String RESOURCE_IMAGE_TTD = CommonUtil.getPropertyParams("resource.dir")+"mnt/images/";
    public final static String IMAGE_TYPE = ".png";

    public final static String URL_IMG = "/images/";
    public final static String URL_IMG_RM = "/rekam_medic/";

    public static final String AGORA_LIB = "/opt/tomcat/webapps/simrs/WEB-INF/lib";

    public final static String AGORA_DIR = "/upload/lib/agora/";
//    public final static String AGORA_DIR = CommonUtil.getPropertyParams("base.dir") + "pages/agora/";
    public static final String AOGRA_REC_DIR = "/home/gondok/workspace/record ";
    public static final String APP_ID = "18016b16da294c7ab3255c5c792384b6";

    //AKUNTANSI
    public final static String NAMA_GENERAL_MANAGER_KP = CommonUtil.getPropertyParams("nama.general.manager.kp");
    public final static String NAMA_MANAGER_KEUANGAN_KP = CommonUtil.getPropertyParams("nama.manager.keuangan.kp");
    public final static String NAMA_GENERAL_MANAGER_UNIT = CommonUtil.getPropertyParams("nama.general.manager.unit");
    public final static String NAMA_MANAGER_KEUANGAN_UNIT = CommonUtil.getPropertyParams("nama.manager.keuangan.unit");
    public final static String COA_PAYROLL = CommonUtil.getPropertyParams("coa.bank.payroll");
    public final static String KELOMPOK_ID_PEJABAT_MUDA = CommonUtil.getPropertyParams("kelompok.id.pejabat.muda");
    public final static String KELOMPOK_ID_PEJABAT_MADYA = CommonUtil.getPropertyParams("kelompok.id.pejabat.madya");
    public final static String KELOMPOK_ID_PEJABAT_UTAMA = CommonUtil.getPropertyParams("kelompok.id.pejabat.utama");
    public final static String BAGIAN_ID_BOD_BOC =CommonUtil.getPropertyParams("bagian.bod.boc");
    public final static String KELOMPOK_ID_BOD =CommonUtil.getPropertyParams("kelompok.id.bod");
    public final static String KELOMPOK_ID_BOC =CommonUtil.getPropertyParams("kelompok.id.boc");
    public final static String ROLE_ID_KARYAWAN = CommonUtil.getPropertyParams("role.id.karyawan");
    public final static String ROLE_ID_ADMIN_AKS = CommonUtil.getPropertyParams("role.id.admin.aks");
    public final static String ROLE_ID_KASUB_KEU = CommonUtil.getPropertyParams("role.id.kasub.keu");
    public final static String ROLE_ID_KA_KEU = CommonUtil.getPropertyParams("role.id.ka.keu");
    public final static String ROLE_ID_ADMIN_DIVISI = CommonUtil.getPropertyParams("role.id.admin.divisi");
    public final static String ROLE_ID_ADMIN = CommonUtil.getPropertyParams("role.id.admin");
    public final static String ID_KANPUS = "KP";
    public final static String MASTER_PAJAK_OBAT = "03.00";


    public final static String RESOURCE_PATH_FAKTUR_PAJAK = "/upload/faktur_pajak/";
    public final static String RESOURCE_PATH_SERTIFIKAT = "/upload/sertifikat/";
    public final static String RESOURCE_PATH_LAMPIRAN = "/upload/lampiran/";
    public final static String RESOURCE_PATH_IPA = "/upload/ipa/";
    public final static String REK_BANK_BRI_TELE = "01041802112149";

//    public final static String KODERING_FARMASI_RI = "02.02.01";
//    public final static String KODERING_INSTALASI_RI = "03.04.01";
//    public final static String KODERING_FARMASI_RJ = "02.01.01";
//    public final static String REKENING_ID_PENDAPATAN_RI ="00304";
//    public final static String REKENING_ID_PENDAPATAN_RJ ="00303";
    public final static String JUNK_MASTER_PIUTANG_PPN = "JNK";
    public final static String KODE_REKENING_LABA_RUGI = "3.3.00.00.02";

    public final static String KODERING_FARMASI_RJ = "05.00.00";
    public final static String KODERING_FARMASI_RI = "05.00.01";
    public final static String KODERING_INSTALASI_RI = "06.01.01";

    public final static String CUTI_ID_DILUAR_TANGGUNJAWAB = CommonUtil.getPropertyParams("cuti.id.diluar.tanggungjawab");

    //TELEMEDICINE
    public static final int ADD_JAM_BAYAR = 5; //MENIT

    public final static String posisiKabidKeuanganKp = CommonUtil.getPropertyParams("posisi.kabid.keuangan.kp");
    public final static String posisiKasubbidKeuanganKp = CommonUtil.getPropertyParams("posisi.kasubbid.keuangan.kp");
    public final static String posisiKadivKeuanganUnit = CommonUtil.getPropertyParams("posisi.kadiv.keuangan.unit");
    public final static String posisiGmUnit = CommonUtil.getPropertyParams("posisi.ka.rs");

    // CONSTANT ID TRANSAKSI BILLING
    public final static String TRANSAKSI_ID_PENYEWAAN_LAHAN =CommonUtil.getPropertyParams("transaksi.id.penyewaan.lahan");
    public final static String TRANSAKSI_ID_PENGAJUAN_SETOR_PPH21 =CommonUtil.getPropertyParams("transaksi.id.pengajuan.setor.pph21");
    public final static String TRANSAKSI_ID_PAYROLL =CommonUtil.getPropertyParams("transaksi.id.payroll");
    public final static String TRANSAKSI_ID_KIRIM_RK =CommonUtil.getPropertyParams("transaksi.id.kirim.rk");
    public final static String TRANSAKSI_ID_TERIMA_RK =CommonUtil.getPropertyParams("transaksi.id.terima.rk");
    public final static String TRANSAKSI_ID_PENGIRIMAN_PENDAPATAN_KE_PUSAT =CommonUtil.getPropertyParams("transaksi.id.pengiriman.pendapatan.ke.pusat");
    public final static String TRANSAKSI_ID_PENERIMAAN_PENDAPATAN_DARI_UNIT =CommonUtil.getPropertyParams("transaksi.id.penerimaan.pendapatan.dari.unit");
    public final static String TRANSAKSI_ID_PERHITUNGAN_PENDAPATAN_DOKTER =CommonUtil.getPropertyParams("transaksi.id.perhitungan.pendapatan.dokter");
    public final static String TRANSAKSI_ID_PEMBAYARAN_PENGAJUAN_BIAYA_UM_KURANG =CommonUtil.getPropertyParams("transaksi.id.pembayaran.pengajuan.biaya.um.kurang");
    public final static String TRANSAKSI_ID_PEMBAYARAN_PENGAJUAN_BIAYA_UM_LEBIH =CommonUtil.getPropertyParams("transaksi.id.pembayaran.pengajuan.biaya.um.lebih");
    public final static String JUNK_MASTER_PIUTANG_PPN_MASUKAN =CommonUtil.getPropertyParams("junk.master.piutang.ppn.keluaran");
    public final static String JUNK_MASTER_PIUTANG_PPN_KELUARAN =CommonUtil.getPropertyParams("junk.master.piutang.ppn.masukan");
    public final static String TRANSAKSI_ID_PENGELOMPOKAN_PPN_KELUARAN = CommonUtil.getPropertyParams("transaksi.id.pengelompokan.ppn.keluaran");
    public final static String TRANSAKSI_ID_PENGELOMPOKAN_PPN_MASUKAN = CommonUtil.getPropertyParams("transaksi.id.pengelompokan.ppn.masukan");
    public final static String TRANSAKSI_ID_PPN_KELUARAN_DIJADIKAN_RK = CommonUtil.getPropertyParams("transaksi.id.ppn.keluaran.dijadikan.rk");
    public final static String TRANSAKSI_ID_PPN_MASUKAN_DIJADIKAN_RK = CommonUtil.getPropertyParams("transaksi.id.ppn.masukan.dijadikan.rk");
    public final static String TRANSAKSI_ID_RK_DIJADIKAN_PPN_KELUARAN = CommonUtil.getPropertyParams("transaksi.id.rk.dijadikan.ppn.keluaran");
    public final static String TRANSAKSI_ID_RK_DIJADIKAN_PPN_MASUKAN = CommonUtil.getPropertyParams("transaksi.id.rk.dijadikan.ppn.masukan");
    public final static String TRANSAKSI_ID_PENGURANG_PPN_KELUARAN = CommonUtil.getPropertyParams("transaksi.id.pengurang.ppn.keluaran");
    public final static String TRANSAKSI_ID_PEMBAYARAN_PPN_KELUARAN = CommonUtil.getPropertyParams("transaksi.id.pembayaran.ppn.keluaran");
    public final static String TRANSAKSI_ID_PEMBAGIAN_BIAYA_UNTUK_RK_TIAP_UNIT = CommonUtil.getPropertyParams("transaksi.id.pembagian.biaya.untuk.rk.tiap.unit");
    public final static String TRANSAKSI_ID_PENERIMAAN_BIAYA_UNTUK_RK_TIAP_UNIT = CommonUtil.getPropertyParams("transaksi.id.penerimaan.biaya.untuk.rk.tiap.unit");
    public final static String TRANSAKSI_ID_KIRIM_PENGAJUAN_PEMBAYARAN_DO = CommonUtil.getPropertyParams("transaksi.id.kirim.pengajuan.pembayaran.do");
    public final static String TRANSAKSI_ID_TERIMA_PENGAJUAN_PEMBAYARAN_DO = CommonUtil.getPropertyParams("transaksi.id.terima.pengajuan.pembayaran.do");
    public final static String TRANSAKSI_ID_PEMBAYARAN_DO = CommonUtil.getPropertyParams("transaksi.id.pembayaran.do");
    public final static String TRANSAKSI_ID_KOREKSI_PENGAJUAN_BIAYA = CommonUtil.getPropertyParams("transaksi.id.koreksi.pengajuan.biaya");

    public final static String REKENING_ID_PPN_MASUKAN = CommonUtil.getPropertyParams("rekening.id.ppn.masukan");
    public final static String REKENING_ID_PPN_KELUARAN = CommonUtil.getPropertyParams("rekening.id.ppn.keluaran");
    public final static String REKENING_PPH21 = "00187";
    public final static String REKENING_ID_PENDAPATAN_RI ="00304";
    public final static String REKENING_ID_PENDAPATAN_RJ ="00303";
    public final static String REKENING_ID_DO =CommonUtil.getPropertyParams("rekening.id.do");

    //CONSTANT HCM
    public final static String METODE_MESIN_ABSENSI = CommonUtil.getPropertyParams("metode.mesin.absensi");
    public final static String IJIN_GANTI_HARI = CommonUtil.getPropertyParams("ijin.ganti.hari");
    public final static String CUTI_TAHUNAN = CommonUtil.getPropertyParams("cuti.id.tahunan");
    public final static String TRANSAKSI_ID_KOREKSI_AKHIR_TAHUN ="99";
    public final static String DEFAULT_RESET_CUTI_PANJANG =CommonUtil.getPropertyParams("default.cuti.panjang");
    public final static String ROLE_ID_ADMIN_SUPER = CommonUtil.getPropertyParams("role.id.admin.super");
    public final static String ID_IJIN_MELAHIRKAN = CommonUtil.getPropertyParams("id.ijin.melahirkan");
    public final static String TRANSAKSI_ID_RK_PERSEDIAAN_PENGIRIM ="88";
    public final static String TRANSAKSI_ID_RK_PERSEDIAAN_PENERIMA ="89";
    public final static String ID_BG_KATEGORI_BIAYA_RUTIN = "KTB000005";

}
