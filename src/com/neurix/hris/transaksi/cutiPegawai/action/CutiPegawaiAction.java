package com.neurix.hris.transaksi.cutiPegawai.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;

import com.neurix.hris.master.cuti.model.Cuti;

import com.neurix.hris.master.cuti.bo.CutiBo;

import com.neurix.hris.master.cutiPanjang.model.CutiPanjang;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.positionBagian;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class CutiPegawaiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(CutiPegawaiAction.class);
    private CutiPegawaiBo cutiPegawaiBoProxy;
    private CutiPegawai cutiPegawai;
    private BiodataBo biodataBoProxy;
    private PositionBagianBo positionBagianBoProxy;
    private String tglFrom;
    private String tglTo;
    private String nip;
    private String bagian;
    private String branchId;
    private boolean admin=false;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTglFrom() {
        return tglFrom;
    }

    public void setTglFrom(String tglFrom) {
        this.tglFrom = tglFrom;
    }

    public String getTglTo() {
        return tglTo;
    }

    public void setTglTo(String tglTo) {
        this.tglTo = tglTo;
    }

    private List<CutiPegawai> initComboAlat;

    public PositionBagianBo getPositionBagianBoProxy() {
        return positionBagianBoProxy;
    }

    public void setPositionBagianBoProxy(PositionBagianBo positionBagianBoProxy) {
        this.positionBagianBoProxy = positionBagianBoProxy;
    }

    public BiodataBo getBiodataBoProxy() {
        return biodataBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CutiPegawaiAction.logger = logger;
    }

    public CutiPegawaiBo getCutiPegawaiBoProxy() {
        return cutiPegawaiBoProxy;
    }

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public CutiPegawai getCutiPegawai() {
        return cutiPegawai;
    }

    public void setCutiPegawai(CutiPegawai cutiPegawai) {
        this.cutiPegawai = cutiPegawai;
    }

    public List<CutiPegawai> getInitComboAlat() {
        return initComboAlat;
    }

    public void setInitComboAlat(List<CutiPegawai> initComboAlat) {
        this.initComboAlat = initComboAlat;
    }

    public CutiPegawai init(String kode, String flag) {
        logger.info("[CutiPegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> listOfResultCutiPegawai = (List<CutiPegawai>) session.getAttribute("listOfResultCutiPegawai");

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResultCutiPegawai != null) {
                for (CutiPegawai cutiPegawai : listOfResultCutiPegawai) {
                    if (kode.equalsIgnoreCase(cutiPegawai.getCutiPegawaiId()) && flag.equalsIgnoreCase(cutiPegawai.getFlag())) {
                        setCutiPegawai(cutiPegawai);
                        break;
                    }
                }
            } else {
                setCutiPegawai(new CutiPegawai());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getCutiPegawai();
    }

    public CutiPegawai init2(String kode, String flag) {
        logger.info("[AlatAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> listOfResultCutiPegawai = (List<CutiPegawai>) session.getAttribute("listOfResultCutiPegawai");

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResultCutiPegawai != null) {
                for (CutiPegawai cutiPegawai : listOfResultCutiPegawai) {
                    if (kode.equalsIgnoreCase(cutiPegawai.getCutiPegawaiId()) && flag.equalsIgnoreCase(cutiPegawai.getFlag())) {
                        setCutiPegawai(cutiPegawai);
                        break;
                    }
                }
            } else {
                setCutiPegawai(new CutiPegawai());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getCutiPegawai();
    }

    @Override
    public String add() {
        logger.info("[AlatAction.add] start process >>>");
        CutiPegawai addCutiPegawai = new CutiPegawai();
        String role = CommonUtil.roleAsLogin();
        HttpSession session = ServletActionContext.getRequest().getSession();

        if (("ADMIN").equalsIgnoreCase(role)||("Admin Bagian").equalsIgnoreCase(role)){

        }else{
            Biodata searchBiodata = new Biodata();
            List<CutiPegawai> listOfsearchBiodata = new ArrayList();
            String user = CommonUtil.userIdLogin();
            addCutiPegawai.setNip(user);
            searchBiodata.setNip(user);
            listOfsearchBiodata = cutiPegawaiBoProxy.getBiodatawithCriteria(user);
            session.removeAttribute("listOfResultCutiPegawaiPersonil");
            session.setAttribute("listOfResultCutiPegawaiPersonil", listOfsearchBiodata);
            List<CutiPegawai> listOfResultCutiPegawaiPersonil = (List<CutiPegawai>) session.getAttribute("listOfResultCutiPegawaiPersonil");
            if (listOfResultCutiPegawaiPersonil != null) {
                for (CutiPegawai cutiPegawai : listOfResultCutiPegawaiPersonil) {
                    addCutiPegawai.setNip(cutiPegawai.getNip());
                    addCutiPegawai.setUnitId(cutiPegawai.getUnitId());
                    addCutiPegawai.setDivisiId(cutiPegawai.getDivisiId());
                    addCutiPegawai.setPosisiId(cutiPegawai.getPosisiId());
                    addCutiPegawai.setNamaPegawai(cutiPegawai.getNamaPegawai());
                    addCutiPegawai.setTanggalMasuk(cutiPegawai.getTanggalMasuk());
                    addCutiPegawai.setSisaCutiHari(cutiPegawai.getSisaCutiHari());
                    addCutiPegawai.setSelf("Y");
                    break;
                }
            } else {
                setCutiPegawai(new CutiPegawai());
            }
            setCutiPegawai(addCutiPegawai);
        }
        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultCutiPegawai");

        logger.info("[CutiPegawaiAction.add] stop process >>>");
        return "init_add";
    }

    /*public String add1() {
        logger.info("[AlatAction.add] start process >>>");
        CutiPegawai addCutiPegawai = new CutiPegawai();
        HttpSession session = ServletActionContext.getRequest().getSession();
        setAddOrEdit(true);
        setAdd(true);
        session.removeAttribute("listOfResultCutiPegawai");


        logger.info("[AlatAction.add] stop process >>>");
        return "init_add";
    }*/

    public List<CutiPegawai> sisaCuti(String nip) {
        List<CutiPegawai> cutiPegawai = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");

        cutiPegawai = cutiPegawaiBo.sisaCutiSys(nip);
        return cutiPegawai;
    }


    @Override
    public String edit() {
        logger.info("[AlatAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        CutiPegawai editCutiPegawai = new CutiPegawai();

        if(itemFlag != null){
            try {
                editCutiPegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[AlatAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }
            if(editCutiPegawai != null) {
                setCutiPegawai(editCutiPegawai);
            } else {
                editCutiPegawai.setFlag(itemFlag);
                editCutiPegawai.setCutiPegawaiId(itemId);
                setCutiPegawai(editCutiPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editCutiPegawai.setCutiPegawaiId(itemId);
            editCutiPegawai.setFlag(getFlag());
            setCutiPegawai(editCutiPegawai);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[CutiPegawaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        CutiPegawai deleteCutiPegawai = new CutiPegawai();

        if (itemFlag != null ) {

            try {
                deleteCutiPegawai = init(itemId, itemFlag);
                Date date  = deleteCutiPegawai.getTanggalDari();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String Format= format.format(date);
                System.out.println(Format);// FOrmat in This Format or you change Change as well
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[CutiPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteCutiPegawai != null) {
                setCutiPegawai(deleteCutiPegawai);

            } else {
                deleteCutiPegawai.setCutiPegawaiId(itemId);
                deleteCutiPegawai.setFlag(itemFlag);
                setCutiPegawai(deleteCutiPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteCutiPegawai.setCutiPegawaiId(itemId);
            deleteCutiPegawai.setFlag(itemFlag);
            setCutiPegawai(deleteCutiPegawai);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AlatAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {

            CutiPegawai editCutiPegawai = getCutiPegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            java.sql.Date dateStart = CommonUtil.convertToDate(cutiPegawai.getStTanggalDari());
            java.sql.Date dateEnd = CommonUtil.convertToDate(cutiPegawai.getStTanggalSelesai());
            editCutiPegawai.setTanggalDari(dateStart);
            editCutiPegawai.setTanggalSelesai(dateEnd);
            editCutiPegawai.setLastUpdateWho(userLogin);
            editCutiPegawai.setLastUpdate(updateTime);
            editCutiPegawai.setAction("U");
            editCutiPegawai.setFlag("Y");

//            String condition;

            cutiPegawaiBoProxy.saveEdit(editCutiPegawai);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPegawaiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }
    public String saveCancel(){
        logger.info("[AlatAction.saveEdit] start process >>>");
        try {
            CutiPegawai cancelCutiPegawai = getCutiPegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            java.sql.Date dateStart = CommonUtil.convertToDate(cutiPegawai.getStTanggalDari());
            java.sql.Date dateEnd = CommonUtil.convertToDate(cutiPegawai.getStTanggalSelesai());
            cancelCutiPegawai.setTanggalDari(dateStart);
            cancelCutiPegawai.setTanggalSelesai(dateEnd);
            cancelCutiPegawai.setCancelFlag("Y");
            cancelCutiPegawai.setCancelDate(updateTime);
            cancelCutiPegawai.setCancelPerson(userLogin);
            cancelCutiPegawai.setLastUpdateWho(userLogin);
            cancelCutiPegawai.setLastUpdate(updateTime);
            cancelCutiPegawai.setAction("U");
            cancelCutiPegawai.setFlag("Y");

            cutiPegawaiBoProxy.saveEdit(cancelCutiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "AlatBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiPegawaiAction.saveEdit] end process <<<");

        return "success_save_cancel";
    }
    public String saveDelete(){
        logger.info("[CutiPegawaiAction.saveDelete] start process >>>");
        try {

            CutiPegawai deleteCutiPegawai = getCutiPegawai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteCutiPegawai.setLastUpdate(updateTime);
            deleteCutiPegawai.setLastUpdateWho(userLogin);
            deleteCutiPegawai.setAction("U");
            deleteCutiPegawai.setFlag("N");

            cutiPegawaiBoProxy.saveDelete(deleteCutiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AlatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[AlatAction.saveAdd] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        try {
            CutiPegawai cutiPegawai = getCutiPegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            java.sql.Date dateStart = CommonUtil.convertToDate(cutiPegawai.getStTanggalDari());
            java.sql.Date dateEnd = CommonUtil.convertToDate(cutiPegawai.getStTanggalSelesai());
            cutiPegawai.setTanggalDari(dateStart);
            cutiPegawai.setTanggalSelesai(dateEnd);
            cutiPegawai.setApprovalFlag("-");
            cutiPegawai.setCreatedWho(userLogin);
            cutiPegawai.setLastUpdate(updateTime);
            cutiPegawai.setCreatedDate(updateTime);
            cutiPegawai.setLastUpdateWho(userLogin);
            cutiPegawai.setAction("C");
            cutiPegawai.setFlag("Y");

            List<Notifikasi> notifCuti = cutiPegawaiBoProxy.saveAddCuti(cutiPegawai);

            for (Notifikasi notifikasi : notifCuti ){
                notifikasiBo.sendNotif(notifikasi);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "cutiPegawaiBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[cutiPegawaiAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[cutiPegawaiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultCutiPegawai");

        logger.info("[cutiPegawaiAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[CutiPegawaiAction.search] start process >>>");
        CutiPegawai searchAlat = getCutiPegawai();
        List<CutiPegawai> listOfSearchCutiPegawai = new ArrayList();
        String role = CommonUtil.roleAsLogin();
        if ("ADMIN".equalsIgnoreCase(role)||"Admin bagian".equalsIgnoreCase(role)){
        }
        else{
            searchAlat.setNip(CommonUtil.userIdLogin());
        }

        if(!("Admin Bagian").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            listOfSearchCutiPegawai = cutiPegawaiBoProxy.getByCriteria(searchAlat);
        }else{
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

            positionBagian searchBagian = new positionBagian();
            searchBagian.setBagianName(CommonUtil.userLogin());
            List<positionBagian> positionBagianList = positionBagianBoProxy.getByCriteria(searchBagian);
            for (positionBagian bagian : positionBagianList){
                List<CutiPegawai> cutiPegawaiList ;
                List<Biodata> biodataList = biodataBo.getBiodataByBagian(null,null,bagian.getBagianId(),null);
                for (Biodata biodata : biodataList){
                    if(!("").equalsIgnoreCase(searchAlat.getNip())){
                        if (biodata.getNip().equalsIgnoreCase(searchAlat.getNip())){
                            cutiPegawaiList = cutiPegawaiBoProxy.getByCriteria(searchAlat);

                            listOfSearchCutiPegawai.addAll(cutiPegawaiList);
                        }
                    }else{
                        searchAlat.setNip(biodata.getNip());
                        cutiPegawaiList = cutiPegawaiBoProxy.getByCriteria(searchAlat);
                        listOfSearchCutiPegawai.addAll(cutiPegawaiList);
                        searchAlat.setNip("");
                    }
                }
            }
            Comparator<CutiPegawai> comparator = new Comparator<CutiPegawai>() {
                @Override
                public int compare(CutiPegawai left, CutiPegawai right) {
                    String awal =left.getCutiPegawaiId().replaceAll("[a-zA-Z]", "");
                    String akhir =right.getCutiPegawaiId().replaceAll("[a-zA-Z]", "");
                    Long angka1 = Long.parseLong(awal);
                    Long angka2 = Long.parseLong(akhir);
                    return (int) (angka2-angka1);
                }
            };
            Collections.sort(listOfSearchCutiPegawai, comparator);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultCutiPegawai");
        session.setAttribute("listOfResultCutiPegawai", listOfSearchCutiPegawai);
        if (("ADMIN").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            setAdmin(true);
        }
        logger.info("[CutiPegawaiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[AlatAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultCutiPegawai");
        if (("ADMIN").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            setAdmin(true);
        }
        logger.info("[AlatAction.initForm] end process >>>");

        return INPUT;
    }

    public List initComboSisaCutiPegawaiId(String query,String cutiId,String branchId) {
        logger.info("[cutiPegawaiAction.initComboSisaCutiPegawaiId] start process >>>");

        List<CutiPegawai> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");

        try {
            listOfAlat = cutiPegawaiBo.getComboSisaCutiPegawaiWithCriteria(query,cutiId,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiAction.initComboSisaCutiPegawaiId");
            } catch (GeneralBOException e1) {
                logger.error("[cutiPegawaiAction.initComboSisaCutiPegawaiId] Error when saving error,", e1);
            }
            logger.error("[cutiPegawaiAction.initComboSisaCutiPegawaiId] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[cutiPegawaiAction.initComboSisaCutiPegawaiId] end process <<<");

        return listOfAlat;
    }
    public String cekNipCuti(String nip) {
        logger.info("[cutiPegawaiAction.cekNipCuti] start process >>>");

        List<CutiPegawai> listOfCuti = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        try {
            listOfCuti = cutiPegawaiBo.getListCekNipCuti(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiAction.cekNipCuti");
            } catch (GeneralBOException e1) {
                logger.error("[cutiPegawaiAction.cekNipCuti] Error when saving error,", e1);
            }
            logger.error("[cutiPegawaiAction.cekNipCuti] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[cutiPegawaiAction.cekNipCuti] end process <<<");

        if (listOfCuti.size()!=0){
            return "00";
        }else{
            return "";
        }
    }
    public List initComboTestTanggal(String nip, String tanggalAwal, String tanggalSelesai) {

        List listOfAlat = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        try {
            listOfAlat = cutiPegawaiBo.getComboTestTanggal(nip,tanggalAwal,tanggalSelesai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }
    public List searchCutiBersama(String unit) {

        List listOfResult = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        try {
            listOfResult = cutiPegawaiBo.getCriteriaForCutiBersama(unit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiBo.getCriteriaForResetCuti");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.searchCutiBersama] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.searchCutiBersama] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[CutiPegawaiAction.searchCutiBersama] end process <<<");

        return listOfResult;
    }
    public List searchViewCuti(String nip) {
        List listOfResult = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        try {
            listOfResult = cutiPegawaiBo.getListCutiForView(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiBo.getListCutiForView");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.searchViewCuti] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.searchViewCuti] Error when get List Cuti For View," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[CutiPegawaiAction.searchViewCuti] end process <<<");

        return listOfResult;
    }
    public List searchResetPanjang(String unit) {
        List<CutiPegawai> listOfResult = null;
        List<CutiPegawai> finalResult = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            listOfResult = cutiPegawaiBo.getCriteriaForResetCuti(unit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiBo.getCriteriaForResetCuti");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.searchResetPanjang] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.searchResetPanjang] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }
        logger.info("[CutiPegawaiAction.searchResetPanjang] end process <<<");

        for (CutiPegawai cutiPegawai : listOfResult){
            int tahunSekarang = Calendar.getInstance().get(Calendar.YEAR);
            if (cutiPegawai.getTanggalAktif()!=null){
                Calendar cal = Calendar.getInstance();
                cal.setTime(cutiPegawai.getTanggalAktif());
                int tahunAktif = cal.get(Calendar.YEAR);
                int selisihTahun=tahunSekarang-tahunAktif;
                if (selisihTahun>=5){
                    if (selisihTahun%5==0){
                        finalResult.add(cutiPegawai);
                    }
                }
            }
        }

        return finalResult;
    }
    public List searchCutiMinus(String unit) {
        List<CutiPegawai> listOfResult = null;
        List<CutiPegawai> finalResult = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            listOfResult = cutiPegawaiBo.getCriteriaForResetCuti(unit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiBo.getCriteriaForResetCuti");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.searchResetPanjang] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.searchResetPanjang] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }
        logger.info("[CutiPegawaiAction.searchResetPanjang] end process <<<");

        for (CutiPegawai cutiPegawai : listOfResult){
            boolean sudah=false;

            if (cutiPegawai.getSisaCutiPanjang()==null){
                cutiPegawai.setSisaCutiPanjang("0");
            }

            if (cutiPegawai.getSisaCutiTahunan()!=null){
                if (Integer.parseInt(cutiPegawai.getSisaCutiTahunan())<0){
                    finalResult.add(cutiPegawai);
                    sudah=true;
                }
            }
            if (!sudah&&cutiPegawai.getSisaCutiPanjang()!=null){
                if (Integer.parseInt(cutiPegawai.getSisaCutiPanjang())<0){
                    finalResult.add(cutiPegawai);
                }
            }
        }

        return finalResult;
    }
    public void saveCutiBersama(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        CutiPegawai result = getCutiPegawai();
        String[] allData = cutiPegawai.getCheckedValue().split(",");
        for (String anAllData : allData) {
            String[] parts = anAllData.split(":");
            String nip = parts[0];
            nip = nip.replace(" ","");
            String sisaCutiTahunan= parts[1];
            String sisaCutiPanjang= parts[2];
            CutiPegawai cutiPegawai = new CutiPegawai();
            cutiPegawai.setNip(nip);
            cutiPegawai.setAlamatCuti(result.getAlamatCuti());
            cutiPegawai.setKeterangan(result.getKeterangan());
            java.sql.Date dateStart = CommonUtil.convertToDate(result.getStTanggalDari());
            java.sql.Date dateEnd = CommonUtil.convertToDate(result.getStTanggalSelesai());
            cutiPegawai.setTanggalDari(dateStart);
            cutiPegawai.setTanggalSelesai(dateEnd);
            cutiPegawai.setLamaHariCuti(result.getLamaHariCuti());
            cutiPegawai.setCreatedWho(CommonUtil.userIdLogin());
            cutiPegawai.setFlag("Y");
            cutiPegawai.setAction("Y");
            cutiPegawai.setCreatedDate(updateTime);
            cutiPegawai.setLastUpdate(updateTime);
            cutiPegawai.setLastUpdateWho(CommonUtil.userIdLogin());
            if (Integer.parseInt(sisaCutiTahunan)==0){
                if (Integer.parseInt(sisaCutiPanjang)==0){
                    cutiPegawai.setSisaHariCuti(BigInteger.valueOf(Integer.parseInt(sisaCutiTahunan)-result.getLamaHariCuti().intValue()));
                    cutiPegawai.setCutiId("CT002");
                }else{
                    cutiPegawai.setSisaHariCuti(BigInteger.valueOf(Integer.parseInt(sisaCutiTahunan)-result.getLamaHariCuti().intValue()));
                    cutiPegawai.setCutiId("CT006");
                }
            }else{
                BigInteger jumlah = BigInteger.valueOf(Long.parseLong(sisaCutiTahunan)).subtract(result.getLamaHariCuti());
                cutiPegawai.setSisaCutiHari(jumlah);
                cutiPegawai.setCutiId("CT002");
            }
            try {
                cutiPegawaiBo.saveCutiBersama(cutiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveCutiBersama");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.saveCutiBersama] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.saveCutiBersama] Error when get cuti," + "[" + logId + "] Found problem when retrieving cuti, please inform to your admin.", e);
            }
        }
    }
    public void saveResetTahunan(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        CutiPegawai result = getCutiPegawai();
        String[] allData = result.getCheckedValue().split(",");
        for (String anAllData : allData) {
            String[] parts = anAllData.split(":");
            String nip = parts[0];
            nip = nip.replace(" ","");
            String setelahReset= parts[2];
            CutiPegawai cutiPegawai = new CutiPegawai();
            cutiPegawai.setNip(nip);
            cutiPegawai.setKeterangan("RESET TAHUNAN");
            java.sql.Date dateStart = new java.sql.Date(System.currentTimeMillis());
            java.sql.Date dateEnd = new java.sql.Date(System.currentTimeMillis());
            cutiPegawai.setTanggalDari(dateStart);
            cutiPegawai.setTanggalSelesai(dateEnd);
            cutiPegawai.setLamaHariCuti(BigInteger.valueOf(0));
            cutiPegawai.setCreatedWho(CommonUtil.userIdLogin());
            cutiPegawai.setFlag("Y");
            cutiPegawai.setAction("Y");
            cutiPegawai.setCreatedDate(updateTime);
            cutiPegawai.setLastUpdate(updateTime);
            cutiPegawai.setLastUpdateWho(CommonUtil.userIdLogin());
            cutiPegawai.setSisaCutiHari(BigInteger.valueOf(Integer.parseInt(setelahReset)));
            cutiPegawai.setCutiId("CT002");
            try {
                cutiPegawaiBo.saveCutiBersama(cutiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveCutiBersama");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.saveResetTahunan] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.saveResetTahunan] Error when get cuti," + "[" + logId + "] Found problem when retrieving cuti, please inform to your admin.", e);
            }
        }
    }
    public void saveResetPanjang(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        CutiPegawai result = getCutiPegawai();
        String[] allData = result.getCheckedValue().split(",");
        for (String anAllData : allData) {
            String[] parts = anAllData.split(":");
            String nip = parts[0];
            nip = nip.replace(" ","");
            String setelahReset= parts[2];
            CutiPegawai cutiPegawai = new CutiPegawai();
            cutiPegawai.setNip(nip);
            cutiPegawai.setKeterangan("RESET PANJANG");
            java.sql.Date dateStart = new java.sql.Date(System.currentTimeMillis());
            java.sql.Date dateEnd = new java.sql.Date(System.currentTimeMillis());
            cutiPegawai.setTanggalDari(dateStart);
            cutiPegawai.setTanggalSelesai(dateEnd);
            cutiPegawai.setLamaHariCuti(BigInteger.valueOf(0));
            cutiPegawai.setCreatedWho(CommonUtil.userIdLogin());
            cutiPegawai.setFlag("Y");
            cutiPegawai.setAction("Y");
            cutiPegawai.setCreatedDate(updateTime);
            cutiPegawai.setLastUpdate(updateTime);
            cutiPegawai.setLastUpdateWho(CommonUtil.userIdLogin());
            cutiPegawai.setSisaCutiHari(BigInteger.valueOf(Integer.parseInt(setelahReset)));
            cutiPegawai.setCutiId("CT006");
            try {
                cutiPegawaiBo.saveCutiBersama(cutiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveCutiBersama");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.saveResetPanjang] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.saveResetPanjang] Error when get cuti," + "[" + logId + "] Found problem when retrieving cuti, please inform to your admin.", e);
            }

            //reset panjang for tahunan
            cutiPegawai = new CutiPegawai();
            cutiPegawai.setNip(nip);
            cutiPegawai.setKeterangan("RESET PANJANG");
            cutiPegawai.setTanggalDari(dateStart);
            cutiPegawai.setTanggalSelesai(dateEnd);
            cutiPegawai.setLamaHariCuti(BigInteger.valueOf(0));
            cutiPegawai.setCreatedWho(CommonUtil.userIdLogin());
            cutiPegawai.setFlag("Y");
            cutiPegawai.setAction("Y");
            cutiPegawai.setCreatedDate(updateTime);
            cutiPegawai.setLastUpdate(updateTime);
            cutiPegawai.setLastUpdateWho(CommonUtil.userIdLogin());
            cutiPegawai.setSisaCutiHari(BigInteger.valueOf(0));
            cutiPegawai.setCutiId("CT002");
            try {
                cutiPegawaiBo.saveCutiBersama(cutiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveCutiBersama");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.saveResetPanjang] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.saveResetPanjang] Error when get cuti," + "[" + logId + "] Found problem when retrieving cuti, please inform to your admin.", e);
            }
        }
    }
    public List initComboCutiPegawaiId(String query) {
        logger.info("[CutiPegawaiAction.initComboCutiPegawaiId] start process >>>");

        List<CutiPegawai> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");

        try {
            listOfAlat = cutiPegawaiBo.getComboCutiPegawaiWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }

    public List initComboAlat(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<CutiPegawai> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("alatBoProxy");

        try {
            listOfAlat = cutiPegawaiBo.getComboCutiPegawaiWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }


    public String saveApprove(String CutiPegawaiId, String statusApprove, String who,String nip){
        logger.info("[SppdAction.saveEdit] start process >>>");
        CutiPegawai editCutiPegawai = new CutiPegawai();
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");

        editCutiPegawai.setCutiPegawaiId(CutiPegawaiId);
        if(who.equals("atasan")){
            if(statusApprove.equals("Y")){
                editCutiPegawai.setApprovalFlag(statusApprove);
            }else{
                editCutiPegawai.setApprovalFlag("N");
                editCutiPegawai.setNoteApproval(statusApprove);
            }
        }

        editCutiPegawai.setNip(nip);
        editCutiPegawai.setTmpApprove(who);
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        editCutiPegawai.setUserIdActive(CommonUtil.userIdLogin());
        editCutiPegawai.setUserNameActive(CommonUtil.userLogin());
        editCutiPegawai.setLastUpdateWho(userLogin);
        editCutiPegawai.setLastUpdate(updateTime);
        editCutiPegawai.setAction("U");
        editCutiPegawai.setFlag("Y");

        try {
            notifikasiList  = cutiPegawaiBo.saveApprove(editCutiPegawai);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SppdAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SppdAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        logger.info("[SppdAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String resetSisaCuti(){
        logger.info("[IjinKeluarAction.resetSisaCuti] end process >>>");
        String userId = CommonUtil.userIdLogin();
        String role = CommonUtil.roleAsLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        List<CutiPegawai> cutiPegawaiList = new ArrayList<>();
        CutiPegawai cutiPegawai = new CutiPegawai();
        cutiPegawai.setFlag("Y");
        cutiPegawai.setForReset(true);
        cutiPegawaiList = cutiPegawaiBo.getByCriteria(cutiPegawai);
        if (role.equalsIgnoreCase("ADMIN")){
            for (CutiPegawai data : cutiPegawaiList){
                try {
                    data.setFlag("N");
                    data.setLastUpdate(updateTime);
                    data.setLastUpdateWho(userId);
                    data.setAction("U");
                    cutiPegawaiBo.saveEdit(data);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "IjinKeluarAction.resetSisaCuti");
                    } catch (GeneralBOException e1) {
                        logger.error("[IjinKeluarAction.resetSisaCuti] Error when saving error,", e1);
                    }
                    logger.error("[IjinKeluarAction.resetSisaCuti] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                    return "failure";
                }
            }
            return "sukses";
        }
        return "failure";
    }
    public String cetakSuratCuti(){
        logger.info("[IjinKeluarAction.cetakSurat] end process >>>");
        String id = getId();
        CutiPegawai cutiPegawai = new CutiPegawai();
        cutiPegawai.setCutiPegawaiId(id);
        List<CutiPegawai> cutiPegawaiList= new ArrayList<>();
        List<StrukturJabatan> strukturJabatanList= new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        StrukturJabatanBo strukturJabatanBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");
        NotifikasiBo notifikasiBo= (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        List<PersonilPosition> personilPositionList = new ArrayList<>();

        try {
            cutiPegawaiList = cutiPegawaiBo.getByCriteria(cutiPegawai);
            strukturJabatanList = strukturJabatanBo.getPerBagianSys();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "medicalRecordBo.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[medicalRecordAction.saveApprove] Error when saving error,", e1);
            }
            logger.error("[medicalRecordAction.medicalRecordAction] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        if (cutiPegawaiList != null){
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
            String stDate = dt1.format(dataDate);
            for (CutiPegawai cutiPegawai1 : cutiPegawaiList){
                for (StrukturJabatan strukturJabatan : strukturJabatanList){
                    if (cutiPegawai1.getNip().equalsIgnoreCase(strukturJabatan.getNip())){
                        cutiPegawai1.setBagian(strukturJabatan.getBagian());
                        break;
                    }
                }
                CutiPegawai result = new CutiPegawai();
                result = cutiPegawaiBo.getSisaCuti(cutiPegawai1.getNip());

                if (cutiPegawai1.getBagian()==null){
                    cutiPegawai1.setBagian("-");
                }
                if (cutiPegawai1.getDivisiName()==null){
                    cutiPegawai1.setDivisiName("-");
                }

                Biodata  biodataAtasan = biodataBo.detailBiodataSys(cutiPegawai1.getApprovalId());
                if (biodataAtasan.getNamaPegawai()==null){
                    biodataAtasan.setNamaPegawai("");
                }
                
                String kabag = null;
                personilPositionList = notifikasiBo.daftarKabag(cutiPegawai1.getNip());
                for (PersonilPosition personilPosition : personilPositionList){
                    Biodata  biodataKabag = biodataBo.detailBiodataSys(personilPosition.getNip());
                    kabag=biodataKabag.getNamaPegawai();
                }
                if (personilPositionList.size()==0){
                    reportParams.put("atasan1", biodataAtasan.getNamaPegawai());
                    reportParams.put("atasan2", "");
                    reportParams.put("tulisanAtasan1", "Atasan");
                    reportParams.put("tulisanAtasan2", "");
                }else if (kabag.equalsIgnoreCase(biodataAtasan.getNamaPegawai())) {
                    reportParams.put("atasan1", biodataAtasan.getNamaPegawai());
                    reportParams.put("atasan2", "");
                    reportParams.put("tulisanAtasan1", "Atasan");
                    reportParams.put("tulisanAtasan2", "");
                }else{
                    reportParams.put("atasan1",kabag);
                    reportParams.put("atasan2", biodataAtasan.getNamaPegawai());
                    reportParams.put("tulisanAtasan1", "Kepala Bagian");
                    reportParams.put("tulisanAtasan2", "Atasan");
                }


                if (result.getSisaCutiTahunan()==null){
                    result.setSisaCutiTahunan("0");
                }
                if (result.getSisaCutiPanjang()==null){
                    result.setSisaCutiPanjang("0");
                }

                reportParams.put("urlLogo",CommonConstant.URL_IMAGE_LOGO_REPORT);
                reportParams.put("cutiPegawaiId", id);
                reportParams.put("nama",cutiPegawai1.getNamaPegawai());
                reportParams.put("nip",cutiPegawai1.getNip());
                reportParams.put("jabatan",cutiPegawai1.getPosisiName());
                reportParams.put("divisi",cutiPegawai1.getDivisiName());
                reportParams.put("bagian",cutiPegawai1.getBagian());
                reportParams.put("sisaCutiPanjang",result.getSisaCutiPanjang());
                reportParams.put("sisaCutiTahunan",result.getSisaCutiTahunan());
                reportParams.put("alamatCuti",cutiPegawai1.getAlamatCuti());
                reportParams.put("keterangan",cutiPegawai1.getKeterangan());
                reportParams.put("unit",cutiPegawai1.getUnitName());
                reportParams.put("cuti",cutiPegawai1.getCutiName());
                reportParams.put("lama",cutiPegawai1.getLamaHariCuti());
                reportParams.put("tanggalDari",cutiPegawai1.getStTanggalDari());
                reportParams.put("tanggalSelesai",cutiPegawai1.getStTanggalSelesai());
                reportParams.put("date", stDate);
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "printSuratJaminan");
                } catch (GeneralBOException e1) {
                    logger.error("[TrainingAction.printSuratJaminan] Error when downloading ,", e1);
                }
                logger.error("[TrainingAction.printReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure_print";
            }

        } else {
            logger.error("[TrainingAction.printSuratJaminan] Error when print report realiassi bibit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list notification detail is empty, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[TrainingAction.printSuratJaminan] end process <<<");
        return "print_surat_cuti";
    }
    public List<CutiPegawai> approveAtasan(String cutiPegawaiId) {
        logger.info("[CutiPegawaiAction.edit] start process >>>");
        String itemId = cutiPegawaiId;
        String itemFlag = "Y";
        List<CutiPegawai> cutiPegawaiList = new ArrayList<CutiPegawai>();
        List<Biodata> personList = new ArrayList<Biodata>();
        CutiPegawai editCutiPegawai = new CutiPegawai();
        if(itemFlag != null){
            try {
                editCutiPegawai = init2(itemId, itemFlag);
                cutiPegawaiList.add(editCutiPegawai);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getSppdByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[CutiPegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }

            if(editCutiPegawai != null) {
                setCutiPegawai(editCutiPegawai);
            } else {
                editCutiPegawai.setFlag(itemFlag);
                editCutiPegawai.setCutiPegawaiId(itemId);
                setCutiPegawai(editCutiPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
            }
        }

        setAddOrEdit(true);
        logger.info("[CutiPegawaiAction.edit] end process >>>");
        return cutiPegawaiList;
    }
    public List initComboPersonil(String query, String branchId) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
//        HttpSession session = WebContextFactory.get().getSession();


        try {
            listOfUser = biodataBo.getListOfPersonil(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfUser;
    }
    public List<CutiPegawai> searchData(String nip, String id) {
        logger.info("[CutiPegawaiAction.search] start process >>>");

        CutiPegawai searchCutiPegawai = new CutiPegawai();
        searchCutiPegawai.setNip(nip);
        searchCutiPegawai.setCutiPegawaiId(id);
        searchCutiPegawai.setFlag("Y");
        List<CutiPegawai> listOfsearchCutiPegawai = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");

            listOfsearchCutiPegawai = cutiPegawaiBo.getByCriteria(searchCutiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.search] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfsearchCutiPegawai;
    }

    public List initComboCutiPanjangFull(String golonganId,String branchId){
        logger.info("[CutiPegawaiAction.initComboCutiFull] start process >>>");
        List<CutiPanjang> listOfCutiPanjang = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        try {
            listOfCutiPanjang = cutiPegawaiBo.getComboCutiPanjangFull(golonganId,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBo.getComboCutiPegawaiWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.initComboCutiFull] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.initComboCutiFull] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
        }

        logger.info("[CutiPegawaiAction.initComboCutiFull] end process <<<");

        return listOfCutiPanjang;
    }

    public String cancel() {
        logger.info("[AlatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        CutiPegawai cancelCutiPegawai = new CutiPegawai();

        if (itemFlag != null ) {

            try {
                cancelCutiPegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AlatAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[CutiPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (cancelCutiPegawai != null) {
                setCutiPegawai(cancelCutiPegawai);

            } else {
                cancelCutiPegawai.setCutiPegawaiId(itemId);
                cancelCutiPegawai.setFlag(itemFlag);
                setCutiPegawai(cancelCutiPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            cancelCutiPegawai.setCutiPegawaiId(itemId);
            cancelCutiPegawai.setFlag(itemFlag);
            setCutiPegawai(cancelCutiPegawai);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[AlatAction.delete] end process <<<");
        return "init_cancel";
    }
    public String reportCuti() {
        logger.info("[CutiPegawaiAction.reportCuti] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCuti");
        logger.info("[CutiPegawaiAction.reportCuti] end process >>>");
        return "report_cuti";
    }

    public String searchReportCuti(){
        logger.info("[CutiPegawaiAction.searchReportCuti] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> result = new ArrayList<>();
        List<Biodata> biodataList= new ArrayList();
        CutiPegawai search = getCutiPegawai();
        if (search.getStTanggalDari()!=null){
            search.setTanggalDari(CommonUtil.convertStringToDate(search.getStTanggalDari()));
        }
        if (search.getStTanggalSelesai()!=null){
            search.setTanggalSelesai(CommonUtil.convertStringToDate(search.getStTanggalSelesai()));
        }

        search.setFlag("Y");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        biodataList = biodataBo.getBiodataforAbsensi(search.getUnitId(),"",search.getBagian(),search.getNip());
        for (Biodata biodata : biodataList){
            List<CutiPegawai> cutiPegawaiList = new ArrayList<>();
            List<CutiPegawai> cutiPegawaiFinalList = new ArrayList<>();
            CutiPegawai searchData = new CutiPegawai();
            searchData.setNip(biodata.getNip());
            searchData.setStTanggalDari(search.getStTanggalDari());
            searchData.setStTanggalSelesai(search.getStTanggalSelesai());
            try {
                cutiPegawaiList = cutiPegawaiBo.getCutiUser(searchData);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBo.getCutiUser");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.searchReportCuti] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.searchReportCuti] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
            for (CutiPegawai cutiPegawai : cutiPegawaiList ){
                cutiPegawai.setNamaPegawai(biodata.getNamaPegawai());
                cutiPegawai.setPosisiName(biodata.getPositionName());
                cutiPegawai.setDivisiName(biodata.getDivisiName());
                cutiPegawai.setUnitName(biodata.getBranchName());
                cutiPegawai.setBagian(biodata.getBagianName());
                cutiPegawaiFinalList.add(cutiPegawai);
            }

            result.addAll(cutiPegawaiFinalList);
        }

        session.removeAttribute("listOfResultCuti");
        session.setAttribute("listOfResultCuti", result);
        logger.info("[CutiPegawaiAction.searchReportCuti] end process >>>");
        return "report_cuti";
    }

    public String printReportCuti(){
        logger.info("[CutiPegawaiAction.printReportCuti] start process >>>");
        List<CutiPegawai> listOfResult = new ArrayList<>();
        List<Biodata> biodata = new ArrayList<>();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);
        String pembuat = CommonUtil.userLogin();

        String nip = getNip();
        String flag = getFlag();
        String stTanggalDari = getTglFrom();
        String stTanggalSelesai = getTglTo();
        String nama = "",jabatan="",divisi="",unit="";

        CutiPegawai result = new CutiPegawai();
        result.setNip(nip);
        result.setFlag(flag);
        result.setStTanggalDari(stTanggalDari);
        result.setStTanggalSelesai(stTanggalSelesai);
        result.setTanggalDari(CommonUtil.convertToDate(stTanggalDari));
        result.setTanggalSelesai(CommonUtil.convertToDate(stTanggalSelesai));

        Biodata searchBiodata = new Biodata();
        searchBiodata.setNip(nip);
        searchBiodata.setFlag(getFlag());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            listOfResult = cutiPegawaiBo.getListCutiForReport(result);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiBo.printReportCuti");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.printReportCuti] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.printReportCuti] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        try {
            biodata = biodataBo.getByCriteria(searchBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiBo.printReportCuti");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.printReportCuti] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.printReportCuti] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        for (Biodata hasilBiodata : biodata){
            nama=hasilBiodata.getNamaPegawai();
            jabatan=hasilBiodata.getPositionName();
            divisi=hasilBiodata.getDivisiName();
            unit=hasilBiodata.getBranchName();
            break;
        }

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listOfResult);
        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport","REPORT CUTI");
        reportParams.put("nama",nama);
        reportParams.put("jabatan",jabatan);
        reportParams.put("divisi",divisi);
        reportParams.put("stTanggalDari",getTglFrom());
        reportParams.put("stTanggalSelesai",getTglTo());
        reportParams.put("pembuat",pembuat);
        reportParams.put("unit",unit);
        reportParams.put("date", stDate);
        reportParams.put("itemDataSource", itemData);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "printReport");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.printReport] Error when downloading ,", e1);
            }
            logger.error("[CutiPegawaiAction.cetakSuratMedicalRecord] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[medicalRecordAction.cetakSuratMedicalRecord] end process <<<");
        return "success_print_report_cuti";
    }

    public List initComboPersonilOnlyName(String query, String branchId) {
        logger.info("[CutiPegawaiAction.initComboPersonilOnlyName] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        try {
            listOfUser = biodataBo.getListOfPersonilOnlyName(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "biodataBo.getListOfPersonilOnlyName");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.initComboPersonilOnlyName] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.initComboPersonilOnlyName] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[CutiPegawaiAction.initComboPersonilOnlyName] end process <<<");

        return listOfUser;
    }

    public List initComboSetCuti(String nip) {
        logger.info("[CutiPegawaiAction.initComboSetCuti] start process >>>");

        List<CutiPegawai> listOfCuti= new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo= (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");

        try {
            listOfCuti = cutiPegawaiBo.getListSetCuti(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "biodataBo.getListOfPersonilOnlyName");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.initComboSetCuti] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiAction.initComboSetCuti] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[CutiPegawaiAction.initComboSetCuti] end process <<<");

        return listOfCuti;
    }

    public String printReportRekapitulasiCuti(){
        logger.info("[CutiPegawaiAction.printReportRekapitulasiCuti] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> result = new ArrayList<>();
        List<Biodata> biodataList= new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        BiodataBo biodataBo= (BiodataBo) ctx.getBean("biodataBoProxy");
        BranchBo branchBo= (BranchBo) ctx.getBean("branchBoProxy");
        PositionBagianBo positionBagianBo= (PositionBagianBo) ctx.getBean("positionBagianBoProxy");
        StrukturJabatanBo strukturJabatanBo= (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");
        Branch branch = new Branch();
        positionBagian positionBagian = new positionBagian();
        biodataList = biodataBo.getBiodataforAbsensi(getBranchId(),"",getBagian(),getNip());

        for (Biodata biodata : biodataList){
            List<CutiPegawai> listData = new ArrayList();
            List<CutiPegawai> listDataCutiPegawai = new ArrayList();
            CutiPegawai search = new CutiPegawai();
            search.setFlag("Y");
            search.setStTanggalDari(getTglFrom());
            search.setStTanggalSelesai(getTglTo());
            search.setNip(biodata.getNip());
            try {
                listData = cutiPegawaiBo.getCutiUser(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBo.getCutiUser");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
            for (CutiPegawai cutiPegawai : listData ){
                cutiPegawai.setNamaPegawai(biodata.getNamaPegawai());
                cutiPegawai.setPosisiName(biodata.getPositionName());
                cutiPegawai.setDivisiName(biodata.getDivisiName());
                cutiPegawai.setUnitName(biodata.getBranchName());
                cutiPegawai.setBagian(biodata.getBagianName());
                listDataCutiPegawai.add(cutiPegawai);
            }
            result.addAll(listDataCutiPegawai);
        }

        if (!("").equalsIgnoreCase(getBranchId())){
            try {
                branch = branchBo.getBranchById(getBranchId(),"Y");
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "strukturJabatanBo.getBranchById");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
        }
        if (!("").equalsIgnoreCase(getBagian())){
            try {
                positionBagian = positionBagianBo.getBagianById(getBagian(),"Y");
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "strukturJabatanBo.getBranchById");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when get combo Cuti Pegawai Full," + "[" + logId + "] Found problem when retrieving combo Cuti Pegawai Full data, please inform to your admin.", e);
            }
        }

        JRBeanCollectionDataSource itemData;


        String bagianPegawai="";
        int a = 1;
        List<CutiPegawai> forReport = new ArrayList<>();
        List<StrukturJabatan> strukturBagian= strukturJabatanBo.getPerBagian();
        for(CutiPegawai cutiPegawai: result) {
            if (!bagianPegawai.equalsIgnoreCase(cutiPegawai.getBagian())){
                CutiPegawai tmp = new CutiPegawai();
                tmp.setNo("");
                tmp.setNip(cutiPegawai.getBagian());
                tmp.setNamaPegawai("");
                tmp.setBagian("");
                tmp.setSisaCutiTahunan("");
                tmp.setSisaCutiPanjang("");
                forReport.add(tmp);
                bagianPegawai = cutiPegawai.getBagian();
                a=1;
            }
            if (bagianPegawai.equalsIgnoreCase(cutiPegawai.getBagian())) {
                cutiPegawai.setNo(String.valueOf(a));
                forReport.add(cutiPegawai);
                a++;
            }
        }

        itemData = new JRBeanCollectionDataSource(forReport);
        String bagian="-",unit="-";
        if (!("").equalsIgnoreCase(getBagian())){
            bagian=positionBagian.getBagianName();
        }
        if (branch.getBranchName()!=null){
            unit=branch.getBranchName();
        }

        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport","REPORT REKAPITULASI CUTI");
        reportParams.put("bagian",bagian);
        reportParams.put("unit",unit);
        reportParams.put("tanggalDari",getTglFrom());
        reportParams.put("tanggalSelesai",getTglTo());
        reportParams.put("date", stDate);
        reportParams.put("itemDataSource", itemData);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "printReport");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when downloading ,", e1);
            }
            logger.error("[CutiPegawaiAction.printReportRekapitulasiCuti] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[CutiPegawaiAction.printReportRekapitulasiCuti] end process <<<");
        return "success_print_report_rekapitulasi_cuti";
    }
    public List searchInisialisasiCuti(String unit) {
        List<CutiPegawai> listOfResult = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> listOfResultCutiPegawai = (List<CutiPegawai>) session.getAttribute("listOfResultInisialisasiCuti");
        if (listOfResultCutiPegawai==null){
            try {
                listOfResult = cutiPegawaiBo.getCriteriaForInisialisasiCuti(unit);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "cutiPegawaiBo.getCriteriaForResetCuti");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiPegawaiAction.searchResetPanjang] Error when saving error,", e1);
                }
                logger.error("[CutiPegawaiAction.searchResetPanjang] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
            }
            logger.info("[CutiPegawaiAction.searchResetPanjang] end process <<<");
        }else{
            listOfResult.addAll(listOfResultCutiPegawai);
        }
        session.setAttribute("listOfResultInisialisasiCuti",listOfResult);

        return listOfResult;
    }
    public List searchNipInisialisasiCuti(String nip) {
        List<CutiPegawai> listOfResult = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> listOfResultCutiPegawai = (List<CutiPegawai>) session.getAttribute("listOfResultInisialisasiCuti");
        for (CutiPegawai cutiPegawai :listOfResultCutiPegawai){
            if (nip.equalsIgnoreCase(cutiPegawai.getNip())){
                listOfResult.add(cutiPegawai);
            }
        }
        return listOfResult;
    }
    public void destroySessionInisialisasiCuti() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultInisialisasiCuti");
    }
    public String editTmpInisialisasiCuti(String nip ,String stSisaCutiTahunan,String stSisaCutiPanjang) {
        String status="";
        List<CutiPegawai> listOfResult = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> listOfResultCutiPegawai = (List<CutiPegawai>) session.getAttribute("listOfResultInisialisasiCuti");
        for (CutiPegawai cutiPegawai :listOfResultCutiPegawai){
            if (nip.equalsIgnoreCase(cutiPegawai.getNip())){
                cutiPegawai.setStSetelahResetCutiTahunan(stSisaCutiTahunan);
                cutiPegawai.setStSetelahResetCutiPanjang(stSisaCutiPanjang);
            }
            listOfResult.add(cutiPegawai);
        }
        session.setAttribute("listOfResultInisialisasiCuti",listOfResult);
        return status;
    }
    public String saveInisialisasi(){
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CutiPegawai> listOfResultCutiPegawai = (List<CutiPegawai>) session.getAttribute("listOfResultInisialisasiCuti");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiPegawaiBo cutiPegawaiBo = (CutiPegawaiBo) ctx.getBean("cutiPegawaiBoProxy");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        for (CutiPegawai data : listOfResultCutiPegawai){
            if (!("").equalsIgnoreCase(data.getStSetelahResetCutiPanjang())||!("").equalsIgnoreCase(data.getStSetelahResetCutiTahunan())){
                CutiPegawai cutiPegawai = new CutiPegawai();
                cutiPegawai.setNip(data.getNip());
                cutiPegawai.setKeterangan("Penambahan/perbaikan Cuti");
                java.sql.Date dateStart = new java.sql.Date(System.currentTimeMillis());
                java.sql.Date dateEnd = new java.sql.Date(System.currentTimeMillis());
                cutiPegawai.setTanggalDari(dateStart);
                cutiPegawai.setTanggalSelesai(dateEnd);
                cutiPegawai.setLamaHariCuti(BigInteger.valueOf(0));
                cutiPegawai.setCreatedWho(CommonUtil.userIdLogin());
                cutiPegawai.setFlag("Y");
                cutiPegawai.setAction("Y");
                cutiPegawai.setCreatedDate(updateTime);
                cutiPegawai.setLastUpdate(updateTime);
                cutiPegawai.setLastUpdateWho(CommonUtil.userIdLogin());
                if (!("").equalsIgnoreCase(data.getStSetelahResetCutiTahunan())){
                    cutiPegawai.setSisaCutiHari(BigInteger.valueOf(Integer.parseInt(data.getStSetelahResetCutiTahunan())));
                    cutiPegawai.setCutiId("CT002");
                    try {
                        cutiPegawaiBo.saveCutiBersama(cutiPegawai);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveCutiBersama");
                        } catch (GeneralBOException e1) {
                            logger.error("[CutiPegawaiAction.saveResetTahunan] Error when saving error,", e1);
                        }
                        logger.error("[CutiPegawaiAction.saveResetTahunan] Error when get cuti," + "[" + logId + "] Found problem when retrieving cuti, please inform to your admin.", e);
                    }
                }
                if (!("").equalsIgnoreCase(data.getStSetelahResetCutiPanjang())){
                    cutiPegawai.setSisaCutiHari(BigInteger.valueOf(Integer.parseInt(data.getStSetelahResetCutiPanjang())));
                    cutiPegawai.setCutiId("CT006");
                    try {
                        cutiPegawaiBo.saveCutiBersama(cutiPegawai);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = cutiPegawaiBo.saveErrorMessage(e.getMessage(), "CutiPegawaiBO.saveCutiBersama");
                        } catch (GeneralBOException e1) {
                            logger.error("[CutiPegawaiAction.saveResetTahunan] Error when saving error,", e1);
                        }
                        logger.error("[CutiPegawaiAction.saveResetTahunan] Error when get cuti," + "[" + logId + "] Found problem when retrieving cuti, please inform to your admin.", e);
                    }
                }
            }
            status="";
        }
        return status;
    }
    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}