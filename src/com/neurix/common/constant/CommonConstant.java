package com.neurix.common.constant;

import com.neurix.common.util.CommonUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;

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
    public static final String RESOURCE_PATH_APK_ZEBRA = "/upload/apk_zebra";
    public static final String RESOURCE_PATH_PEMERIKSAAN = "/upload/pemeriksaan/";
    public static final String RESOURCE_PATH_PENDUKUNG_PEMERIKSAAN = "/upload/pendukung_pemeriksaan/";

//    public final static String RESOURCE_PATH_USER_UPLOAD = CommonUtil.getPropertyParams("resource.dir")+"/profile/";
    public final static String RESOURCE_PATH_USER_UPLOAD = "/pages/upload/image/profile/";
    public final static String RESOURCE_PATH_USER_UPLOAD_IJAZAH = CommonUtil.getPropertyParams("resource.dir")+"mnt/ijazah/";
    public final static String RESOURCE_PATH_USER_UPLOAD_SURAT_DOKTER = CommonUtil.getPropertyParams("resource.dir")+"mnt/surat/";

    //sodiq, 18 Nov 2019, Upload KTP pasien
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = CommonUtil.getPropertyParams("upload.external.dir");
    public final static String RESOURCE_PATH_IMG_ASSET = CommonUtil.getPropertyParams("resource.dir");
    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = CommonUtil.getPropertyParams("upload.folder");
    public final static String RESOURCE_PATH_LOGS = CommonUtil.getPropertyParams("base.dir.logs");

//    public final static String RESOURCE_PATH_IMG_ASSET = "C:\\apache-tomcat-8.5.40\\webapps";
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = "C:\\Users\\pc001\\Pictures";
//    public final static String RESOURCE_PATH_IMG_ASSET = "C:\\tomcat\\webapps";
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY="C:\\Users\\Toshiba\\Pictures";
//    public final static String RESOURCE_PATH_IMG_ASSET = CommonUtil.getPropertyParams("resource.dir");
//    public final static String RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY = "C:\\Users\\user\\Pictures";

    public final static String EXTERNAL_IMG_URI = ServletActionContext.getRequest().getContextPath()+"/images";
    public final static String EXTERNAL_IMG_URI_PROFILE = ServletActionContext.getRequest().getContextPath()+"/images/"; // profile/";
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
    public final static String RESOURCE_DOCUMENT_PAYROLL = CommonUtil.getPropertyParams("upload.folder")+CommonConstant.RESOURCE_PATH_PAYROLL;

//    public final static String REPORT_PAYROLL = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayroll3.jrxml";
    public final static String REPORT_PAYROLL = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollPeg_update.jrxml";
    public final static String REPORT_PAYROLL_THR = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollThrBranch3.jrxml";
    public final static String REPORT_PAYROLL_JASPROD = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollJasoprBranch3jrxml";
    public final static String REPORT_PAYROLL_PENSIUN = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollPensiun3.jrxml";
    public final static String REPORT_PAYROLL_PMP = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollJubileum3.jrxml";
    public final static String REPORT_PAYROLL_INSENTIF = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollInsentifBranch3.jrxml";
    public final static String REPORT_PAYROLL_CUTI_PANJANG = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollCutiPanjangBranch3.jrxml";
    public final static String REPORT_PAYROLL_CUTI_TAHUNAN = CommonUtil.getPropertyParams("base.dir")+"pages/report/com/neurix/hris/reportPayrollCutiTahunanBranch3.jrxml";

    //role name
    public final static String ROLE_ADMIN_POLI = "ADMIN POLI";
    public final static String ROLE_ADMIN_RS = "ADMIN RS";
    public final static String ROLE_ADMIN_IGD = "ADMIN IGD";
    public final static String ROLE_ADMIN_APOTEK = "ADMIN APOTEK";
    public final static String ROLE_DOKTER_UMUM = "Dokter Umum";
    public final static String ROLE_DOKTER_SPESIALIS = "Dokter Spesialis";


    //JENIS PEGAWAI
    public final static String JP_NORMAL = CommonUtil.getPropertyParams("jenis.pegawai.normal");
    public final static String JP_PLT = CommonUtil.getPropertyParams("jenis.pegawai.plt");
    public final static String JP_PJS = CommonUtil.getPropertyParams("jenis.pegawai.pjs");
    public final static String JP_PERCOBAAN = CommonUtil.getPropertyParams("jenis.pegawai.percobaan");


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
//    public final static String baseUrl = "/go-medsys/";
    public final static String timeLimitReg = "15";
    public final static String timeLimitVer = "10";
//    public final static String verAddress = APP_NAME + "prosesLoginFinger.action";
//    public final static String regAddress = APP_NAME + "prosesRegisterFinger.action";
//    public final static String addRawatPasien = APP_NAME + "checkup/add_checkup.action";


    public final static String LOGO_RS01 = "/pages/images/RS01.png";
    public final static String LOGO_RS02 = "/pages/images/RS02.png";
    public final static String LOGO_RS03 = "/pages/images/RS03.png";
    public final static String LOGO_KP = "/pages/images/KP.png";
    public final static String LOGO_NMU = "/pages/images/logo-nmu-copy.png";

    public final static String IMAGE_CARD = "/pages/images/card.png";

    public final static String BRANCH_RS01 = "02"; //CommonUtil.getPropertyParams("branch.gatoel");
    public final static String BRANCH_RS02 = "03"; //CommonUtil.getPropertyParams("branch.toeloengredjo");
    public final static String BRANCH_RS03 = "04"; //CommonUtil.getPropertyParams("branch.perkebunan");
    public final static String BRANCH_KP = "01"; //CommonUtil.getPropertyParams("branch.kp");
    public final static String BRANCH_RS04 = "05"; //RS medika Utama
    public final static String BRANCH_RS05 = "98"; // Estetika Meditama
    public final static String BRANCH_RS06 = "99"; // Unit Rawat Jalan

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
    //RAKA-09JUN2021 ===> kelompok khusus manager procurement, dibuat untuk handle tunj.Jab & Struk yg berbeda (antara Madya dan Utama)
    public final static String KELOMPOK_ID_MAN_PROCUREMENT = CommonUtil.getPropertyParams("kelompok.id.pejabat.man_procurement");
    //RAKA-end
    public final static String KELOMPOK_ID_PEJABAT_UTAMA = CommonUtil.getPropertyParams("kelompok.id.pejabat.utama");
    public final static String KELOMPOK_ID_STAFF = CommonUtil.getPropertyParams("kelompok.id.pelaksana");
    public final static String BAGIAN_ID_BOD =CommonUtil.getPropertyParams("bagian.bod");
    public final static String BAGIAN_ID_BOC =CommonUtil.getPropertyParams("bagian.boc");
    public final static String KELOMPOK_ID_BOD =CommonUtil.getPropertyParams("kelompok.id.bod");
    public final static String KELOMPOK_ID_BOC =CommonUtil.getPropertyParams("kelompok.id.boc");
    public final static String ROLE_ID_KARYAWAN = CommonUtil.getPropertyParams("role.id.karyawan");
    public final static String ROLE_ID_ADMIN_AKS = CommonUtil.getPropertyParams("role.id.admin.aks");
    public final static String ROLE_ID_KASUB_KEU = CommonUtil.getPropertyParams("role.id.kasub.keu");
    public final static String ROLE_ID_KA_KEU = CommonUtil.getPropertyParams("role.id.ka.keu");
    public final static String ROLE_ID_ADMIN_DIVISI = CommonUtil.getPropertyParams("role.id.admin.divisi");
    public final static String ROLE_ID_ADMIN = CommonUtil.getPropertyParams("role.id.admin");
    public final static String ROLE_ID_ADMIN_SDM = CommonUtil.getPropertyParams("role.id.admin.sdm");

    // Fahmi 2021-08-07, Kelompok Pendapatan Dokter KSO
    public final static String COA_REDUKSI_PENDAPATAN_RI = CommonUtil.getPropertyParams("rekening.id.reduksi.ri");
    public final static String COA_REDUKSI_PENDAPATAN_RJ = CommonUtil.getPropertyParams("rekening.id.reduksi.rj");
    public final static String COA_UTANGDOKTER = CommonUtil.getPropertyParams("rekening.id.utangdokter");
    // End Fahmi
//    public final static String ID_KANPUS = "KP";
    public final static String MASTER_PAJAK_OBAT = "03.00";

    //updated by ferdi, 01-12-2020
    public final static String FLAG_TUTUP_PERIODE = "Y";
    public final static String FLAG_LOCK_PERIODE = "L";


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

    public final static String KODERING_FARMASI_RJ = "02.01.01";
    public final static String KODERING_FARMASI_RI = "02.02.01";
//    public final static String KODERING_INSTALASI_RI = "06.01.01";

    public final static String CUTI_ID_DILUAR_TANGGUNJAWAB = CommonUtil.getPropertyParams("cuti.id.diluar.tanggungjawab");

    //TELEMEDICINE
    public static final int ADD_JAM_BAYAR = 30; //MENIT
    public static final String URL_RECORDING = CommonUtil.getPropertyParams("recording.handler.baseurl");

    public final static String posisiKabidKeuanganKp = CommonUtil.getPropertyParams("posisi.kabid.keuangan.kp");
    public final static String posisiKasubbidKeuanganKp = CommonUtil.getPropertyParams("posisi.kasubbid.keuangan.kp");
    public final static String posisiKadivKeuanganUnit = CommonUtil.getPropertyParams("posisi.kadiv.keuangan.unit");
    public final static String posisiGmUnit = CommonUtil.getPropertyParams("posisi.ka.rs");

    // CONSTANT ID TRANSAKSI BILLING
    public final static String TRANSAKSI_ID_PENYEWAAN_LAHAN =CommonUtil.getPropertyParams("transaksi.id.penyewaan.lahan");
    public final static String TRANSAKSI_ID_PENGAJUAN_SETOR_PPH21 =CommonUtil.getPropertyParams("transaksi.id.pengajuan.setor.pph21");

    //updated by ferdi, 01-12-2021, untuk mapping trans id di bagian payroll
    public final static String TRANSAKSI_ID_PAYROLL_BULANAN =CommonUtil.getPropertyParams("transaksi.id.payroll");
    public final static String TRANSAKSI_ID_THR =CommonUtil.getPropertyParams("transaksi.id.thr");
    public final static String TRANSAKSI_ID_JASOP =CommonUtil.getPropertyParams("transaksi.id.jasop");
    public final static String TRANSAKSI_ID_INSENTIVE =CommonUtil.getPropertyParams("transaksi.id.insentive");
    public final static String TRANSAKSI_ID_CUTI_PANJANG =CommonUtil.getPropertyParams("transaksi.id.cutipanjang");
    public final static String TRANSAKSI_ID_CUTI_TAHUNAN =CommonUtil.getPropertyParams("transaksi.id.cutitahunan");
    public final static String TRANSAKSI_ID_PENSIUN =CommonUtil.getPropertyParams("transaksi.id.pensiun");
    public final static String TRANSAKSI_ID_RAPEL =CommonUtil.getPropertyParams("transaksi.id.rapel");
    public final static String TRANSAKSI_ID_PMP =CommonUtil.getPropertyParams("transaksi.id.pmp");

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

    //untuk sewa lahan - aji noor
    public final static String REKENING_KODE_PPN_KELUARAN = CommonUtil.getPropertyParams("rekening.kode.ppn.keluaran");
    public final static String REKENING_KODE_PPH_PASAL4_AYAT2 = CommonUtil.getPropertyParams("rekening.kode.pph.pasal4.ayat2");
    public final static String REKENING_KODE_PENDAPATAN_DILUAR_USAHA = CommonUtil.getPropertyParams("rekening.kode.pendapatan.diluar.usaha");

    public final static String REKENING_ID_PPN_MASUKAN = CommonUtil.getPropertyParams("rekening.id.ppn.masukan");
    public final static String REKENING_ID_PPN_KELUARAN = CommonUtil.getPropertyParams("rekening.id.ppn.keluaran");
//    public final static String REKENING_PPH21 = "00187";
//    public final static String REKENING_ID_PENDAPATAN_RI ="00304";
//    public final static String REKENING_ID_PENDAPATAN_RJ ="00303";
    public final static String REKENING_PPH21 = CommonUtil.getPropertyParams("rekening.id.pph.21");
    public final static String REKENING_ID_PENDAPATAN_RI = CommonUtil.getPropertyParams("rekening.id.pendapatan.ri");
    public final static String REKENING_ID_PENDAPATAN_RJ = CommonUtil.getPropertyParams("rekening.id.pendapatan.rj");
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

    public final static String PEGAWAI_TETAP = "TP03";
    public final static String PEGAWAI_PKWT = "TP04";

    //constant PAYROLL
    public final static String CODE_PAYROLL = "PY";
    public final static String CODE_THR = "TH";
    public final static String CODE_RAPEL = "RP";
    public final static String CODE_INSENTIF = "IN";
    public final static String CODE_JASOP = "JP";
    public final static String CODE_PENSIUN = "PN";
    public final static String CODE_CUTI_PANJANG = "CP";
    public final static String CODE_CUTI_TAHUNAN = "CT";
    public final static String CODE_PMP = "JB";

    //mapping jurnal untuk payroll, ferdi, 01-12-2021
    public final static String MAPPING_PAYROLL_GAJI_DEKOM = CommonUtil.getPropertyParams("mapping.payroll.gaji.dekom");
    public final static String MAPPING_PAYROLL_TUNJ_PPH_DEKOM = CommonUtil.getPropertyParams("mapping.payroll.tunj.pph.dekom");
    public final static String MAPPING_PAYROLL_TUNJ_TRANSPORT_KOMUNIKASI_DEKOM = CommonUtil.getPropertyParams("mapping.payroll.tunj.transport.komunikasi.dekom");
    public final static String MAPPING_PAYROLL_TUNJ_RUMAH_DEKOM = CommonUtil.getPropertyParams("mapping.payroll.tunj.rumah.dekom");
    public final static String MAPPING_PAYROLL_TUNJ_LAIN_DEKOM = CommonUtil.getPropertyParams("mapping.payroll.tunj.lain.dekom");

    public final static String MAPPING_PAYROLL_GAJI_DIREKSI = CommonUtil.getPropertyParams("mapping.payroll.gaji.direksi");
    public final static String MAPPING_PAYROLL_TUNJ_PPH_DIREKSI = CommonUtil.getPropertyParams("mapping.payroll.tunj.pph.direksi");
    public final static String MAPPING_PAYROLL_TUNJ_TRANSPORT_KOMUNIKASI_DIREKSI = CommonUtil.getPropertyParams("mapping.payroll.tunj.transport.komunikasi.direksi");
    public final static String MAPPING_PAYROLL_TUNJ_RUMAH_DIREKSI = CommonUtil.getPropertyParams("mapping.payroll.tunj.rumah.direksi");
    public final static String MAPPING_PAYROLL_TUNJ_LAIN_DIREKSI = CommonUtil.getPropertyParams("mapping.payroll.tunj.lain.direksi");

    public final static String MAPPING_PAYROLL_GAJI_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.gaji.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_JABATAN_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.jabatan.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_STRUKTURAL_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.struktural.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_PAJAK_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.pajak.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_RLAB_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.rlab.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_PENSIUN_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.pensiun.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_BPJS_TK_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.bpjs.tk.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_BPJS_KS_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.bpjs.ks.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_KHUSUS_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.khusus.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_LEMBUR_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.lembur.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_PERALIHAN_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.peralihan.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_SUPERVISI_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.supervisi.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_FUNGSIONAL_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.fungsional.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_PEMONDOKAN_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.pemondokan.kary.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_LAIN_KARY_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.lainnya.kary.tetap");

    public final static String MAPPING_PAYROLL_GAJI_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.gaji.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_FUNGSIONAL_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.fungsional.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_TAMBAHAN_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.tambahan.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_PAJAK_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.pajak.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_BPJS_TK_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.bpjs.tk.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_BPJS_KS_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.bpjs.ks.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_KHUSUS_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.khusus.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_LEMBUR_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.lembur.kary.tidak.tetap");
    public final static String MAPPING_PAYROLL_TUNJ_LAIN_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.payroll.tunj.lainnya.kary.tidak.tetap");

    public final static String MAPPING_PAYROLL_IURAN_BPJS_TK = CommonUtil.getPropertyParams("mapping.payroll.iuran.bpjs.tk");
    public final static String MAPPING_PAYROLL_IURAN_BPJS_KS = CommonUtil.getPropertyParams("mapping.payroll.iuran.bpjs.ks");
    public final static String MAPPING_PAYROLL_IURAN_DAPENBUN = CommonUtil.getPropertyParams("mapping.payroll.iuran.dapenbun");
    public final static String MAPPING_PAYROLL_IURAN_DPLK = CommonUtil.getPropertyParams("mapping.payroll.iuran.dplk");
    public final static String MAPPING_PAYROLL_IURAN_KARYAWAN = CommonUtil.getPropertyParams("mapping.payroll.iuran.karyawan");

    public final static String MAPPING_PAYROLL_KAS = CommonUtil.getPropertyParams("mapping.payroll.kas");
    public final static String MAPPING_PAYROLL_PPH_GAJI = CommonUtil.getPropertyParams("mapping.payroll.pph.gaji");

    public final static String MAPPING_THR_TUNJ_HARI_RAYA_DEKOM = CommonUtil.getPropertyParams("mapping.thr.tunj.hari.raya.dekom");
    public final static String MAPPING_THR_TUNJ_PPH_DEKOM = CommonUtil.getPropertyParams("mapping.thr.tunj.pph.dekom");
    public final static String MAPPING_THR_TUNJ_HARI_RAYA_DIREKSI = CommonUtil.getPropertyParams("mapping.thr.tunj.hari.raya.direksi");
    public final static String MAPPING_THR_TUNJ_PPH_DIREKSI = CommonUtil.getPropertyParams("mapping.thr.tunj.pph.direksi");
    public final static String MAPPING_THR_TUNJ_HARI_RAYA = CommonUtil.getPropertyParams("mapping.thr.tunj.hari.raya");
    public final static String MAPPING_THR_TUNJ_PPH_KARY_TETAP = CommonUtil.getPropertyParams("mapping.thr.tunj.pph.kary.tetap");
    public final static String MAPPING_THR_TUNJ_PPH_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.thr.tunj.pph.kary.tidak.tetap");
    public final static String MAPPING_THR_KAS = CommonUtil.getPropertyParams("mapping.thr.kas");
    public final static String MAPPING_THR_PPH = CommonUtil.getPropertyParams("mapping.thr.pph");

    public final static String MAPPING_INSENTIVE_TUNJ_INSENTIVE = CommonUtil.getPropertyParams("mapping.insentive.tunj.insentive");
    public final static String MAPPING_INSENTIVE_TUNJ_PPH_KARY_TETAP = CommonUtil.getPropertyParams("mapping.insentive.tunj.pph.kary.tetap");
    public final static String MAPPING_INSENTIVE_TUNJ_PPH_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.insentive.tunj.pph.kary.tidak.tetap");
    public final static String MAPPING_INSENTIVE_KAS = CommonUtil.getPropertyParams("mapping.insentive.kas");
    public final static String MAPPING_INSENTIVE_PPH = CommonUtil.getPropertyParams("mapping.insentive.pph");

    public final static String MAPPING_JASOP_TUNJ_JASOP = CommonUtil.getPropertyParams("mapping.jasop.tunj.jasop");
    public final static String MAPPING_JASOP_TUNJ_PPH_KARY_TETAP = CommonUtil.getPropertyParams("mapping.jasop.tunj.pph.kary.tetap");
    public final static String MAPPING_JASOP_TUNJ_PPH_KARY_TIDAK_TETAP = CommonUtil.getPropertyParams("mapping.jasop.tunj.pph.kary.tidak.tetap");
    public final static String MAPPING_JASOP_KAS = CommonUtil.getPropertyParams("mapping.jasop.kas");
    public final static String MAPPING_JASOP_PPH = CommonUtil.getPropertyParams("mapping.jasop.pph");

    public final static String MAPPING_CUTI_UANG_CUTI = CommonUtil.getPropertyParams("mapping.cuti.uang.cuti");
    public final static String MAPPING_CUTI_TUNJ_PPH_KARY_TETAP = CommonUtil.getPropertyParams("mapping.cuti.tunj.pph.kary.tetap");
    public final static String MAPPING_CUTI_KAS = CommonUtil.getPropertyParams("mapping.cuti.kas");
    public final static String MAPPING_CUTI_PPH = CommonUtil.getPropertyParams("mapping.cuti.pph");

    public final static String TIPE_PEGAWAI_BOC = "TP01";
    public final static String TIPE_PEGAWAI_BOD = "TP02";
    public final static String TIPE_PEGAWAI_TETAP = "TP03";
    public final static String TIPE_PEGAWAI_TIDAK_TETAP = "TP04";

    public final static String TIPE_DPLK = "DP01";
    public final static String TIPE_DAPENBUN = "DP02";

    public final static String TANGGAL_GAJI = "25";
    public final static String BULAN_12 = "12";
    public final static String MATA_UANG_RPH = "032";
    public final static String KAS_TUNAI = CommonUtil.getPropertyParams("coa.kas.tunai");
    public final static String METODE_TRANSFER = "TRANSFER";
    public final static String METODE_TRANSFER_VA = "TRANSFER VA";
    public final static String METODE_TUNAI = "TUNAI";

    //updated by ferdi, 01-12-2021, untuk mapping trans id di telemedicine
    public final static String TRANSAKSI_ID_TELEMEDICINE =CommonUtil.getPropertyParams("transaksi.id.telemedicine");
    public final static String MAPPING_TELEMEDICINE_KAS = CommonUtil.getPropertyParams("mapping.telemedicine.kas");
    public final static String MAPPING_TELEMEDICINE_PENDAPATAN = CommonUtil.getPropertyParams("mapping.telemedicine.pendapatan");

    //CONSTANT STATUS LOG CRON
    public final static String LOG_CRON_SUCCESS = "success";
    public final static String LOG_CRON_CON_PROBLEM = "connection_problem";
    public final static String LOG_CRON_PROG_PROBLEM = "program_problem";
    public final static String LOG_CRON_OTHER = "other";

    //EMAIL
    public final static String EMAIL_USERNAME = CommonUtil.getPropertyParams("email.username");
    public final static String EMAIL_PASSWORD = CommonUtil.getPropertyParams("email.pasword");

    //API LICENSE
    public final static String LICENSE_API = CommonUtil.getPropertyParams("license.api");
    public static final String LICENSE_EMAIL_TO = CommonUtil.getPropertyParams("license.email.to");


    //Sigit 2021-01-02, tipe pelayanan pada role khusus kasir
    public final static String TIPE_PELAYANAN_KASIR_TELE = CommonUtil.getPropertyParams("kasir.telemedicine");
    public final static String TIPE_PELAYANAN_KASIR_OPS = CommonUtil.getPropertyParams("kasir.operasional");
    public final static String TIPE_PELAYANAN_ADMIN_TELE = CommonUtil.getPropertyParams("admin.telemedicine");


    //PATH NEW RM LAMA
    public final static String URL_RM_LAMA = CommonUtil.getPropertyParams("path.rm.lama");

    public final static String CODE_VA_BSI = CommonUtil.getPropertyParams("code.va.bsi");
    public final static String DESC_VA_TELE = CommonUtil.getPropertyParams("desc.va.telemedic");
    public final static String CODE_INVOICE_VA_TELE = CommonUtil.getPropertyParams("code.invoice.telemedic");
    public final static String JENIS_PEMBAYARAN_TRANSFER_TUNAI = CommonUtil.getPropertyParams("jenis.pembayaran.tt");
    public final static String JENIS_PEMBAYARAN_VA = CommonUtil.getPropertyParams("jenis.pembayaran.va");
    public final static String PATH_REPORT_AKUNTANSI = ContextLoader.getCurrentWebApplicationContext().getApplicationName() + "/pages/report/com/neurix/akuntansi/";
    public final static String REALPATH_REPORT_AKUNTANSI = CommonUtil.getPropertyParams("base.dir") + "pages/report/com/neurix/akuntansi/";


}
