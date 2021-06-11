package com.neurix.akuntansi.transaksi.koreksi.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.kas.bo.KasBo;
import com.neurix.akuntansi.transaksi.kas.model.Lampiran;
import com.neurix.akuntansi.transaksi.koreksi.bo.KoreksiBo;
import com.neurix.akuntansi.transaksi.koreksi.bo.KoreksiDetailBo;
import com.neurix.akuntansi.transaksi.koreksi.model.Koreksi;
import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class KoreksiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(KoreksiAction.class);
    private Koreksi koreksi;
    private KoreksiBo koreksiBoProxy;
    private KoreksiDetailBo koreksiDetailBoProxy;
    private BillingSystemBo billingSystemBoProxy;
    private MappingJurnalBo mappingJurnalBoProxy;
    private KodeRekeningBo kodeRekeningBoProxy;
    private KasBo kasBoProxy;


    public KoreksiDetailBo getKoreksiDetailBoProxy() {
        return koreksiDetailBoProxy;
    }

    public void setKoreksiDetailBoProxy(KoreksiDetailBo koreksiDetailBoProxy) {
        this.koreksiDetailBoProxy = koreksiDetailBoProxy;
    }

    public KasBo getKasBoProxy() {
        return kasBoProxy;
    }

    public void setKasBoProxy(KasBo kasBoProxy) {
        this.kasBoProxy = kasBoProxy;
    }

    private String koreksiId;

    public String getKoreksiId() {
        return koreksiId;
    }

    public void setKoreksiId(String koreksiId) {
        this.koreksiId = koreksiId;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    public Koreksi getKoreksi() {
        return koreksi;
    }

    public void setKoreksi(Koreksi koreksi) {
        this.koreksi = koreksi;
    }

    public KoreksiBo getKoreksiBoProxy() {
        return koreksiBoProxy;
    }

    public void setKoreksiBoProxy(KoreksiBo koreksiBoProxy) {
        this.koreksiBoProxy = koreksiBoProxy;
    }

    public BillingSystemBo getBillingSystemBoProxy() {
        return billingSystemBoProxy;
    }

    public List<Lampiran> loadSessionLampiran() {
        logger.info("[KoreksiAction.loadSessionLampiran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");
        logger.info("[KoreksiAction.loadSessionLampiran] end process >>>");
        return lampiranList;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public MappingJurnalBo getMappingJurnalBoProxy() {
        return mappingJurnalBoProxy;
    }

    public void setMappingJurnalBoProxy(MappingJurnalBo mappingJurnalBoProxy) {
        this.mappingJurnalBoProxy = mappingJurnalBoProxy;
    }

    public KodeRekeningBo getKodeRekeningBoProxy() {
        return kodeRekeningBoProxy;
    }

    public void setKodeRekeningBoProxy(KodeRekeningBo kodeRekeningBoProxy) {
        this.kodeRekeningBoProxy = kodeRekeningBoProxy;
    }


    @Override
    public String downloadXls() {
        return null;
    }

    public String saveDetailKoreksi(String kodeVendor, String namaVendor, String noNota, String jumlahPembayaran, String rekeningId, String divisiId, String divisiName, String transId) {
        logger.info("[KoreksiAction.saveDetailKoreksi] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String status = "";
        KoreksiBo koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        String posisiCoa = koreksiBo.getPosisiCoaDiMappingJurnal(transId, rekeningId);
        boolean ada = false;

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KoreksiDetail> koreksiList = (List<KoreksiDetail>) session.getAttribute("listOfResultKoreksiDetail");
        List<KoreksiDetail> koreksiArrayList = new ArrayList<>();

        if (koreksiList != null) {
            for (KoreksiDetail data : koreksiList) {
                if (data != null && data.getDivisiId().equalsIgnoreCase(divisiId) && data.getNoNota().equalsIgnoreCase(noNota) && data.getKodeCoa().equalsIgnoreCase(rekeningId)) {
                    ada = true;
                    status = "Pengeluaran/Pemasukan dengan data ini ( kode rekening,vendor , no nota ) sudah ada.";
                    break;
                }
            }
        }
        if (!ada) {
            Comparator<KoreksiDetail> comparator = (left, right) -> {
                int angka1 = 1;
                int angka2 = 1;

                if ("D".equalsIgnoreCase(left.getPosisiCoa())) {
                    angka1 = 0;
                }
                if ("K".equalsIgnoreCase(right.getPosisiCoa())) {
                    angka2 = 0;
                }
                return (angka1 - angka2);
            };

            if (koreksiList != null) {
                koreksiArrayList.addAll(koreksiList);
            }

            KoreksiDetail newData = new KoreksiDetail();
            ImKodeRekeningEntity kodeRekeningEntity = kodeRekeningBo.getKodeRekeningByCoa(rekeningId);
            if(kodeRekeningEntity != null){
                newData.setNamaCoa(kodeRekeningEntity.getNamaKodeRekening());
            }
            newData.setStJumlahPembayaran(jumlahPembayaran);
            newData.setJumlahPembayaran(CommonUtil.StringDenganFormatToBigDecimal(jumlahPembayaran));
            newData.setMasterId(kodeVendor);
            newData.setNoNota(noNota);
            newData.setMasterName(namaVendor);
            newData.setKodeCoa(rekeningId);
            newData.setDivisiId(divisiId);
            newData.setDivisiName(divisiName);
            newData.setPosisiCoa(posisiCoa);

            koreksiArrayList.add(newData);

            Collections.sort(koreksiArrayList, comparator);
            session.setAttribute("listOfResultKoreksiDetail", koreksiArrayList);
        }
        logger.info("[KoreksiAction.saveDetailKoreksi] end process >>>");
        return status;
    }

    public void deleteDetailKoreksi(String rekeningId, String divisiId, String vendor, String noNota, String biaya) {
        logger.info("[KoreksiAction.deleteDetailKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KoreksiDetail> koreksiList = (List<KoreksiDetail>) session.getAttribute("listOfResultKoreksiDetail");
        List<KoreksiDetail> koreksiArrayList = new ArrayList<>();
        koreksiArrayList.addAll(koreksiList);
        for (KoreksiDetail koreksi : koreksiList) {
            String nota = koreksi.getNoNota() == null ? "" : koreksi.getNoNota();
            String coa = koreksi.getKodeCoa() == null ? "" : koreksi.getKodeCoa();
            String vendorDb = koreksi.getMasterId() == null ? "" : koreksi.getMasterId();
            String jumlah = koreksi.getStJumlahPembayaran() == null ? "" : koreksi.getStJumlahPembayaran();
            String divisi = koreksi.getDivisiId() == null ? "" : koreksi.getDivisiId();
            if (coa.equalsIgnoreCase(rekeningId) && divisi.equalsIgnoreCase(divisiId)) {
                koreksiArrayList.remove(koreksi);

            }
        }
        session.setAttribute("listOfResultKoreksiDetail", koreksiArrayList);
        logger.info("[KoreksiAction.deleteDetailKoreksi] end process >>>");
    }

    public String saveSessionLampiran(String namaLampiran, String fileUpload) {
        logger.info("[KoreksiAction.saveSessionLampiran] start process >>>");
        String status = "";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        if (lampiranList == null) {
            lampiranList = new ArrayList<>();
        }
        if(lampiranList.size() > 0) {
            for (Lampiran lampiran : lampiranList) {
                if (!lampiran.getNamaLampiran().equalsIgnoreCase(namaLampiran)) {
                    Lampiran newData = new Lampiran();
                    newData.setNamaLampiran(namaLampiran);
                    newData.setUploadFile(fileUpload);
                    lampiranList.add(newData);
                } else {
                    status = "nama sudah ada sebelemunya";
                }

            }
        }
        else {
            Lampiran newData = new Lampiran();
            newData.setNamaLampiran(namaLampiran);
            newData.setUploadFile(fileUpload);
            lampiranList.add(newData);
        }
        session.setAttribute("listOfResultLampiran", lampiranList);

        logger.info("[KoreksiAction.saveSessionLampiran] end process >>>");
        return status;
    }

    public Trans getDisableTrans(String transaksiId, String coaLawan) {
        logger.info("[KoreksiAction.getDisableTrans] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KoreksiBo koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
        Trans data = koreksiBo.getDisableTrans(transaksiId, coaLawan);
        logger.info("[KoreksiAction.getDisableTrans] end process >>>");
        return data;
    }

    public List<Koreksi> searchDetailKoreksi() {
        logger.info("[KoreksiAction.searchDetailKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Koreksi> koreksiList = (List<Koreksi>) session.getAttribute("listOfResultKoreksiDetail");
        logger.info("[KoreksiAction.searchDetailKoreksi] end process >>>");
        return koreksiList;
    }


    public String getTipeMaster(String transaksiId) {
        logger.info("[KoreksiAction.getTipeMaster] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KoreksiBo koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
        String master = "";

        ImTransEntity transEntity = koreksiBo.getTipeMaster(transaksiId);
        master = transEntity != null ? transEntity.getMaster() : master;
        logger.info("[KoreksiAction.getTipeMaster] end process >>>");
        return master;
    }


    public String addKoreksi() {
        logger.info("[KoreksiAction.addKoreksi] start process >>>");
        Koreksi addKoreksi = new Koreksi();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null) {
            addKoreksi.setBranchId(branchId);
        } else {
            addKoreksi.setBranchId("");
        }
        setKoreksi(addKoreksi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultLampiran");
        session.removeAttribute("listOfResultKoreksiDetail");

        logger.info("[KoreksiAction.addKoreksi] stop process >>>");
        return "init_add_koreksi";
    }

    public String editKoreksi() {
        logger.info("[KoreksiAction.editKoreksi] start process >>>");
        String koreksiId = getKoreksiId();

        Koreksi editKoreksi = koreksiBoProxy.getById(koreksiId);
        if(editKoreksi == null){
            return "input_koreksi";
        }
        else {
            List<Lampiran> lampiran = kasBoProxy.getLampiranList(editKoreksi.getKoreksiId());
            List<KoreksiDetail> koreksiDetailList = koreksiBoProxy.getKoreksiDetail(editKoreksi.getKoreksiId());
            String branchId = CommonUtil.userBranchLogin();
            if (branchId != null) {
                editKoreksi.setBranchIdUser(branchId);
            } else {
                editKoreksi.setBranchIdUser("");
            }
            setKoreksi(editKoreksi);
            setAddOrEdit(true);
            setAdd(false);

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResult");
            session.removeAttribute("listOfResultLampiran");
            session.removeAttribute("listOfResultKoreksiDetail");

            if(lampiran.size() > 0) {
                session.setAttribute("listOfResultLampiran", lampiran);
            }
            if(koreksiDetailList.size() > 0) {
                for(KoreksiDetail koreksiDetail : koreksiDetailList){
                    koreksiDetail.setStJumlahPembayaran(koreksiDetail.getStJumlahPembayaran().replace(",","."));
                }
                session.setAttribute("listOfResultKoreksiDetail", koreksiDetailList);
            }
        }
        logger.info("[KoreksiAction.editKoreksi] stop process >>>");
        return "init_edit_koreksi";
    }

    public String initFormKoreksi() {
        logger.info("[KoreksiAction.initFormKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Koreksi data = new Koreksi();
        if (branchId != null) {
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        } else {
            data.setBranchId("");
        }
        setKoreksi(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKoreksiDetail");
        logger.info("[KoreksiAction.initFormKoreksi] end process >>>");
        return "input_koreksi";
    }

    public String saveAddKoreksi() {
        logger.info("[KoreksiAction.saveAddKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal = "";
        List<KoreksiDetail> koreksiDetailList = (List<KoreksiDetail>) session.getAttribute("listOfResultKoreksiDetail");
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        Koreksi koreksiHeader = getKoreksi();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        koreksiHeader.setTanggalKoreksi(CommonUtil.convertTimestampToDate(updateTime));
        koreksiHeader.setCreatedWho(userLogin);
        koreksiHeader.setLastUpdate(updateTime);
        koreksiHeader.setCreatedDate(updateTime);
        koreksiHeader.setLastUpdateWho(userLogin);
        koreksiHeader.setAction("C");
        koreksiHeader.setFlag("Y");

        try {
            //get parameter pembayaran
            Map data = new HashMap();
            MappingJurnal search = new MappingJurnal();
            search.setTransId(koreksiHeader.getTipeTransaksi());
            search.setFlag("Y");

            List<MappingJurnal> mappingJurnalList = mappingJurnalBoProxy.getByCriteria(search);
            for (MappingJurnal mappingJurnal : mappingJurnalList) {
                List<Map> dataMap = new ArrayList<>();
                for (KoreksiDetail koreksiDetail : koreksiDetailList) {

                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(koreksiDetail.getKodeCoa()); //perlu diganti saat BOImpl sudah diperbaiki - noted By Aji

                    if (mappingJurnal.getPosisi().equalsIgnoreCase(koreksiDetail.getPosisiCoa())) {
                        BigDecimal jumlahPembayaran = new BigDecimal(koreksiDetail.getStJumlahPembayaran().replace(".", ""));
                        Map hs = new HashMap();
                        hs.put("bukti", koreksiDetail.getNoNota());
                        hs.put("nilai", jumlahPembayaran);
                        hs.put("master_id", koreksiDetail.getMasterId());
                        hs.put("divisi_id", koreksiDetail.getDivisiId());
                        hs.put("rekening_id", rekeningId);
                        dataMap.add(hs);
                    }
                }
                data.put(mappingJurnal.getKeterangan(), dataMap);
            }
            Jurnal jurnal = billingSystemBoProxy.createJurnal(koreksiHeader.getTipeTransaksi(), data, koreksiHeader.getBranchId(), koreksiHeader.getKeterangan(), "N");
            noJurnal = jurnal.getNoJurnal();
            koreksiHeader.setNoJurnal(noJurnal);
            koreksiBoProxy.saveAddKoreksi(koreksiHeader, koreksiDetailList, lampiranList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = koreksiBoProxy.saveErrorMessage(e.getMessage(), "KoreksiAction.saveAddKoreksi");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.saveAddKoreksi] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[KoreksiAction.saveAddKoreksi] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKoreksiDetail");

        logger.info("[KoreksiAction.saveAddKoreksi] end process >>>");
        return "success_save_add_koreksi";
    }

    public String searchKoreksi() {
        logger.info("[KoreksiAction.searchKoreksi] start process >>>");
        Koreksi searchKoreksi = getKoreksi();
        List<Koreksi> listOfResult = new ArrayList();
        try {
            if (searchKoreksi != null) {
                listOfResult = koreksiBoProxy.getByCriteria(searchKoreksi);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = koreksiBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.searchKoreksi] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KoreksiAction.searchKoreksi] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }
        /*if(listOfsearchKoreksi != null){
        for (Koreksi data : listOfsearchKoreksi) {
            if ("".equalsIgnoreCase(data.getDivisiId())) {
                listOfResult.add(data);
            }
        }
        }*/
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfResult);
        searchKoreksi.setBranchIdUser(CommonUtil.userBranchLogin());
        setKoreksi(searchKoreksi);
        logger.info("[KoreksiAction.searchKoreksi] end process <<<");
        return "success_koreksi";
    }

    public String saveEditKoreksi() {
        logger.info("[KoreksiAction.saveEditKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal = "";
        Koreksi koreksiHeader = getKoreksi();

        List<KoreksiDetail> detailListBaru = (List<KoreksiDetail>) session.getAttribute("listOfResultKoreksiDetail");
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        Map<String, KoreksiDetail> koreksiDetailMap = koreksiDetailBoProxy.getListKoreksiDetail(koreksiHeader.getKoreksiId());
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        List<KoreksiDetail> detail = new ArrayList<>();
        for (KoreksiDetail dataBaru : detailListBaru) {
            KoreksiDetail old = koreksiDetailMap.get(dataBaru.getKoreksiDetailId());
            //data update
            if (old != null && dataBaru.getKoreksiDetailId().equalsIgnoreCase(old.getKoreksiDetailId())) {
                dataBaru.setKoreksiDetailId(old.getKoreksiDetailId());
                dataBaru.setKoreksiId(old.getKoreksiId());
                dataBaru.setKodeVendor(old.getKodeVendor() == null ? "" : old.getKodeVendor());
                dataBaru.setPosisiCoa(old.getPosisiCoa());
                dataBaru.setLastUpdate(updateTime);
                dataBaru.setLastUpdateWho(userLogin);
                dataBaru.setAction("U");
                dataBaru.setFlag("Y");
             detail.add(dataBaru);
             koreksiDetailMap.remove(dataBaru.getKoreksiDetailId());
            }
            else {
                dataBaru.setCreatedDate(updateTime);
                dataBaru.setCreatedWho(userLogin);
                dataBaru.setAction("C");
                dataBaru.setFlag("Y");
                detail.add(dataBaru);
            }
        }
        if (koreksiDetailMap.size() > 0) {
            koreksiDetailMap.forEach((k, dataLama) -> {
                KoreksiDetail  old = null;
                old = koreksiDetailMap.get(dataLama.getKoreksiDetailId());
                if(old != null) {
                    old.setLastUpdateWho(userLogin);
                    old.setLastUpdate(updateTime);
                    old.setKodeVendor(dataLama.getKodeVendor());
                    old.setPosisiCoa(dataLama.getPosisiCoa());
                    old.setAction("D");
                    old.setStJumlahPembayaran(old.getJumlahPembayaran().toString());
                    old.setFlag("N");
                    detail.add(old);
                }
            });
            koreksiDetailMap.clear();
        }
        koreksiHeader.setCreatedWho(userLogin);
        koreksiHeader.setCreatedDate(updateTime);
        koreksiHeader.setApprovalKeuanganFlag(null); //agar trx bisa di approve lagi
        koreksiHeader.setApprovalKeuanganName(null); //agar trx bisa di approve lagi
//        koreksiHeader.setLastUpdate(updateTime);
//        koreksiHeader.setLastUpdateWho(userLogin);
        koreksiHeader.setAction("U");
        koreksiHeader.setFlag("Y");
        try {
            Koreksi status = koreksiBoProxy.saveEditKoreksi(koreksiHeader,detail,lampiranList);
            if (status  .getKoreksiId() != null) {
                session.setAttribute("param_id",status.getKoreksiId());
                return "success_koreksi";
            }
        }
        catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = koreksiBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.saveEditKoreksi] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KoreksiAction.saveEditKoreksi] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        try {
            //get parameter pembayaran
            Map data = new HashMap();
            MappingJurnal search = new MappingJurnal();
            search.setTransId(koreksiHeader.getTipeTransaksi());
            search.setFlag("Y");

            List<MappingJurnal> mappingJurnalList = mappingJurnalBoProxy.getByCriteria(search);
            for (MappingJurnal mappingJurnal : mappingJurnalList) {
                List<Map> dataMap = new ArrayList<>();
                for (KoreksiDetail koreksiDetail : detailListBaru) {

                    //
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(koreksiDetail.getKodeCoa()); //perlu diganti saat BOImpl sudah diperbaiki - noted By Aji
                    if (mappingJurnal.getPosisi().equalsIgnoreCase(koreksiDetail.getPosisiCoa())) {
                        BigDecimal jumlahPembayaran = new BigDecimal(koreksiDetail.getStJumlahPembayaran().replace(".", ""));
                        Map hs = new HashMap();
                        hs.put("bukti", koreksiDetail.getNoNota());
                        hs.put("nilai", jumlahPembayaran);
                        hs.put("master_id", koreksiDetail.getMasterId());
                        hs.put("divisi_id", koreksiDetail.getDivisiId());
                        hs.put("rekening_id", rekeningId);
                        dataMap.add(hs);
                    }
                }
                data.put(mappingJurnal.getKeterangan(), dataMap);
            }
            Jurnal jurnal = billingSystemBoProxy.createJurnal(koreksiHeader.getTipeTransaksi(), data, koreksiHeader.getBranchId(), koreksiHeader.getKeterangan(), "N");
            noJurnal = jurnal.getNoJurnal();
            koreksiHeader.setNoJurnal(noJurnal);
            koreksiBoProxy.saveEditKoreksi(koreksiHeader, detailListBaru, lampiranList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = koreksiBoProxy.saveErrorMessage(e.getMessage(), "KoreksiAction.saveAddKoreksi");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.saveEditKoreksi] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[KoreksiAction.saveEditKoreksi] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKoreksiDetail");

        logger.info("[KoreksiAction.saveEditKoreksi] end process >>>");
        return "success_save_edit_koreksi";
    }


    public Koreksi init(String kode, String flag) {
        logger.info("[KoreksiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Koreksi> listOfResult = (List<Koreksi>) session.getAttribute("listOfResult");

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResult != null) {
                for (Koreksi koreksi : listOfResult) {
                    if (kode.equalsIgnoreCase(koreksi.getKoreksiId()) && flag.equalsIgnoreCase(koreksi.getFlag())) {
                        setKoreksi(koreksi);
                        break;
                    }
                }
            } else {
                setKoreksi(new Koreksi());
            }

            logger.info("[KoreksiAction.init] end process >>>");
        }
        return getKoreksi();
    }

    public Koreksi getForModalPopUp(String koreksiId) {
        logger.info("[KoreksiAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KoreksiBo koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
        String itemId = koreksiId;
        String itemFlag = "Y";
        Koreksi modalPopUp = new Koreksi();
        List<KoreksiDetail> listDetail;
        List<Lampiran> lampiranList;
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            modalPopUp = init(itemId, itemFlag);
            if (modalPopUp.getTanggalKoreksi() != null) {
                modalPopUp.setStTanggalKoreksi(CommonUtil.convertDateToString(modalPopUp.getTanggalKoreksi()));
            }
            listDetail = koreksiBo.getKoreksiDetail(koreksiId);
            lampiranList = koreksiBo.getLampiranList(koreksiId);
            session.setAttribute("listKoreksiDetailModal", listDetail);
            session.setAttribute("listOfResultLampiran", lampiranList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = koreksiBoProxy.saveErrorMessage(e.getMessage(), "koreksiBO.getForModalPopUp");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.getForModalPopUp] Error when retrieving delete data,", e1);
            }
            logger.error("[KoreksiAction.getForModalPopUp] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        logger.info("[KoreksiAction.getForModalPopUp] end process >>>");
        return modalPopUp;
    }

    public String postingJurnal(String koreksiId) {
        logger.info("[KoreksiAction.postingJurnal] start process >>>");
        KoreksiBo koreksiBo = null;
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
            Koreksi data = new Koreksi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            data.setKoreksiId(koreksiId);
            data.setRegisteredDate(updateTime);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");
            koreksiBo.postingJurnal(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = koreksiBo.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KoreksiAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[KoreksiAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public List<KoreksiDetail> searchNotaKoreksi(String masterId, String transaksiId, String branchId, String divisiId, String coa) {
        logger.info("[KoreksiAction.searchDetailKoreksi] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KoreksiBo koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<KoreksiDetail> listDataDb = koreksiBo.getSearchNotaKoreksi(masterId, transaksiId, branchId, divisiId, coa);
        List<KoreksiDetail> listDataSession = (List<KoreksiDetail>) session.getAttribute("listOfResultKoreksiDetail");
        List<KoreksiDetail> result = new ArrayList<>();
        if (listDataSession == null) {
            result.addAll(listDataDb);
        } else {
            //jika sudah ada pada session tidak usah ditampilkan
            for (KoreksiDetail dataDb : listDataDb) {
                boolean ada = false;
                for (KoreksiDetail dataSession : listDataSession) {
                    if (dataDb.getNoNota().equalsIgnoreCase(dataSession.getNoNota())) {
                        ada = true;
                        break;
                    }
                }
                if (!ada) {
                    result.add(dataDb);
                }
            }
        }
        logger.info("[KoreksiAction.searchDetailKoreksi] end process >>>");
        return result;
    }

    public String printReportBuktiPosting() {
        logger.info("[KoreksiAction.printReportBuktiPosting] start process >>>");
        List<KoreksiDetail> koreksiList;
        String tipeTransaksi = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KoreksiBo koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
        KoreksiDetailBo koreksidetailBo = (KoreksiDetailBo) ctx.getBean("koreksiDetailBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        Koreksi data = getKoreksi();

        data = koreksiBo.getById(data != null ? data.getKoreksiId() : "");
        if (data != null) {
            Branch branch = branchBo.getBranchById(data.getBranchId(), "Y");
            KoreksiDetail search = new KoreksiDetail();
            search.setKoreksiId(data.getKoreksiId());
            search.setFlag("Y");
            koreksiList = koreksidetailBo.getByCriteria(search);

            //menambah printCount
            koreksiBo.addPrintCount(data.getNoJurnal());

            for (KoreksiDetail result : koreksiList) {
                //di report KR tidak dipakai
                /*reportParams.put("terbilang", CommonUtil.angkaToTerbilang(result.getJumlahPembayaran().longValue())); // masih salah - noted by Aji
                reportParams.put("totalBayar", CommonUtil.numbericFormat(result.getJumlahPembayaran(), "###,###.00"));
                reportParams.put("noSlipBank", data.getNoSlipBank());*/
                reportParams.put("uraian", data.getKeterangan());
                reportParams.put("coaKas", result.getKodeCoa());
                tipeTransaksi = "KR";
            }

            String reportName = "";
            switch (tipeTransaksi) {
                case ("KR"):
                    reportName = "BUKTI KOREKSI";
                    break;
            }

            reportParams.put("reportTitle", reportName);
            reportParams.put("reportName", reportName);
            reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT + branch.getLogoName());
            reportParams.put("branchId", data.getBranchId());
            Date now = new Date();
            reportParams.put("tanggal", CommonUtil.convertDateToString(now));
            reportParams.put("kota", branch.getBranchName());
            reportParams.put("alamatSurat", branch.getAlamatSurat());
            reportParams.put("noJurnal", data.getNoJurnal());
            reportParams.put("koreksiId", data.getKoreksiId());
            reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT + branch.getLogoName());
            reportParams.put("areaId", CommonUtil.userAreaName());
        }
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = koreksiBo.saveErrorMessage(e.getMessage(), "printReportBuktiPosting");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.printReportBuktiPosting] Error when downloading ,", e1);
            }
            logger.error("[KoreksiAction.printReportBuktiPosting] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[KoreksiAction.printReportBuktiPosting] end process <<<");

        return "print_report_bukti_posting_koreksi";
    }

    public String approveKoreksi(String koreksiId, String who, String flag) {
        logger.info("[KoreksiAction.approvePembayaran] start process >>>");
        KoreksiBo koreksiBo = null;
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            koreksiBo = (KoreksiBo) ctx.getBean("koreksiBoProxy");
            Koreksi data = new Koreksi();
            String userLogin = CommonUtil.userLogin();
            String userIdLogin = CommonUtil.userIdLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setKoreksiId(koreksiId);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");

            if ("keu".equalsIgnoreCase(who)) {
                data.setApprovalKeuanganFlag(flag);
                data.setApprovalKeuanganId(userIdLogin);
                data.setApprovalKeuanganName(userLogin);
                data.setApprovalKeuanganDate(updateTime);
            } else if ("kasub".equalsIgnoreCase(who)) {
                data.setApprovalKasubKeuanganFlag(flag);
                data.setApprovalKasubKeuanganId(userIdLogin);
                data.setApprovalKasubKeuanganName(userLogin);
                data.setApprovalKasubKeuanganDate(updateTime);
            }
            koreksiBo.approveKoreksi(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = koreksiBo.saveErrorMessage(e.getMessage(), "koreksiBO.approveKas");
            } catch (GeneralBOException e1) {
                logger.error("[KoreksiAction.approvePembayaran] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KoreksiAction.approvePembayaran] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KoreksiAction.approvePembayaran] end process <<<");

        if ("Y".equalsIgnoreCase(flag)) {
            return "Sukses Approve";
        } else {
            return "Sukses Not Approve";
        }
    }

    public List<KoreksiDetail> searchPembayaranDetail() {
        logger.info("[KoreksiAction.searchPembayaranDetail] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KoreksiDetail> result = (List<KoreksiDetail>) session.getAttribute("listKoreksiDetailModal");
        logger.info("[KoreksiAction.searchPembayaranDetail] end process >>>");
        return result;
    }

    public Koreksi getViewApproval(String koreksiId) {
        logger.info("[KoreksiAction.getViewApproval] start process >>>");
        String itemFlag = "Y";
        Koreksi modalApproval;
        try {
            modalApproval = init(koreksiId, itemFlag);
        } catch (GeneralBOException e) {
            throw new GeneralBOException("Error saat mengambil data approval ");
        }
        logger.info("[KoreksiAction.getViewApproval] end process >>>");
        return modalApproval;
    }

    public String cekBeforeSaveKoreksi(String tipeTransaksi, String tanggal, String keterangan, String branchId) {
        String status = "";
        logger.info("[KoreksiAction.cekBeforeSaveKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KoreksiDetail> koreksiList = (List<KoreksiDetail>) session.getAttribute("listOfResultKoreksiDetail");

        if (tipeTransaksi.equalsIgnoreCase("") || tanggal.equalsIgnoreCase("") || keterangan.equalsIgnoreCase("")) {
            if (tipeTransaksi.equalsIgnoreCase("")) {
                status += "Tipe transaksi masih kosong <br>";
            }
            if (tanggal.equalsIgnoreCase("")) {
                status += "Tanggal masih kosong <br>";
            }
            if (branchId.equalsIgnoreCase("")) {
                status += "Unit masih kosong <br>";
            }
            if (keterangan.equalsIgnoreCase("")) {
                status += "Keterangan masih kosong <br>";
            }
        } else if (koreksiList == null || koreksiList.size() == 0) {
            status += "Detail pembayaran belum ada <br>";
        } else {
            BigDecimal bayarDetailDebit = BigDecimal.ZERO;
            BigDecimal bayarDetailKredit = BigDecimal.ZERO;
            for (KoreksiDetail data : koreksiList) {
                if ("K".equalsIgnoreCase(data.getPosisiCoa())) {
                    bayarDetailKredit = bayarDetailKredit.add(new BigDecimal(data.getStJumlahPembayaran().replace(",", "")));
                } else {
                    bayarDetailDebit = bayarDetailDebit.add(new BigDecimal(data.getStJumlahPembayaran().replace(",", "")));
                }
            }
            if (!bayarDetailKredit.equals(bayarDetailDebit)) {
                if (bayarDetailKredit.compareTo(bayarDetailDebit) > 0) {
                    status = "Jumlah pembayaran Kredit melebihi Jumlah Pembayaran Debit";
                } else if (bayarDetailKredit.compareTo(bayarDetailDebit) < 0) {
                    status = "Jumlah pembayaran Debit melebihi Jumlah Pembayaran Kredit";
                }
            }
        }
        logger.info("[KoreksiAction.cekBeforeSaveKoreksi] end process >>>");
        return status;
    }

    public String loadImageSessionLaporan(String namaLampiran) {
        logger.info("[KasAction.loadImageSessionLaporan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        String images = "";
        for (Lampiran lampiran : lampiranList) {
            if (namaLampiran.equalsIgnoreCase(lampiran.getNamaLampiran())) {
                images = lampiran.getUploadFile();
            }
        }
        logger.info("[KasAction.loadImageSessionLaporan] end process >>>");
        return images;
    }

    public String deleteSessionLampiran(String namaLampiran) {
        logger.info("[KasAction.deleteSessionLampiran] start process >>>");
        String status = "";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");
        List<Lampiran> lampirans = new ArrayList<>();
        for (Lampiran data : lampiranList) {
            if (!namaLampiran.equalsIgnoreCase(data.getNamaLampiran())) {
                lampirans.add(data);
            }
        }
        session.setAttribute("listOfResultLampiran", lampirans);

        logger.info("[KasAction.deleteSessionLampiran] end process >>>");
        return status;
    }
}
