package com.neurix.hris.master.smkJabatan.action;

import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkJabatan.bo.SmkJabatanBo;
import com.neurix.hris.master.smkJabatan.dao.SmkJabatanDetailDao;
import com.neurix.hris.master.smkJabatan.model.ImtSmkJabatanDetailEntity;
import com.neurix.hris.master.smkJabatan.model.SmkJabatan;
import com.neurix.hris.master.smkJabatan.model.SmkJabatanDetail;
import com.neurix.hris.master.smkKategoriAspek.model.SmkKategoriAspek;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class SmkJabatanAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkJabatanAction.class);
    private SmkJabatanBo smkJabatanBoProxy;
    private SmkJabatan smkJabatan;
    private SmkJabatanDetailDao smkJabatanDetailDao;
    private SmkJabatanDetail smkJabatanDetail;
    private String position;
    private String branch;
    private String divisi;

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public SmkJabatanDetailDao getSmkJabatanDetailDao() {
        return smkJabatanDetailDao;
    }

    public void setSmkJabatanDetailDao(SmkJabatanDetailDao smkJabatanDetailDao) {
        this.smkJabatanDetailDao = smkJabatanDetailDao;
    }

    public SmkJabatanDetail getSmkJabatanDetail() {
        return smkJabatanDetail;
    }

    public void setSmkJabatanDetail(SmkJabatanDetail smkJabatanDetail) {
        this.smkJabatanDetail = smkJabatanDetail;
    }

    private List<SmkJabatan> listComboSmkJabatan = new ArrayList<SmkJabatan>();

    public List<SmkJabatan> getListComboSmkJabatan() {
        return listComboSmkJabatan;
    }

    public void setListComboSmkJabatan(List<SmkJabatan> listComboSmkJabatan) {
        this.listComboSmkJabatan = listComboSmkJabatan;
    }

    public SmkJabatanBo getSmkJabatanBoProxy() {
        return smkJabatanBoProxy;
    }

    public void setSmkJabatanBoProxy(SmkJabatanBo smkJabatanBoProxy) {
        this.smkJabatanBoProxy = smkJabatanBoProxy;
    }

    public SmkJabatan getSmkJabatan() {
        return smkJabatan;
    }

    public void setSmkJabatan(SmkJabatan smkJabatan) {
        this.smkJabatan = smkJabatan;
    }

    private List<SmkJabatan> initComboSmkJabatan;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkJabatanAction.logger = logger;
    }


    public List<SmkJabatan> getInitComboSmkJabatan() {
        return initComboSmkJabatan;
    }

    public void setInitComboSmkJabatan(List<SmkJabatan> initComboSmkJabatan) {
        this.initComboSmkJabatan = initComboSmkJabatan;
    }

    public SmkJabatan init(String kode, String flag){
        logger.info("[SmkJabatanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatan> listOfResult = (List<SmkJabatan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkJabatan smkJabatan : listOfResult) {
                    if(kode.equalsIgnoreCase(smkJabatan.getJabatanSmkId()) && flag.equalsIgnoreCase(smkJabatan.getFlag())){
                        setSmkJabatan(smkJabatan);
                        break;
                    }
                }
            } else {
                setSmkJabatan(new SmkJabatan());
            }

            logger.info("[SmkJabatanAction.init] end process >>>");
        }
        return getSmkJabatan();
    }

    @Override
    public String add() {
        logger.info("[SmkJabatanAction.add] start process >>>");
        SmkJabatan addSmkJabatan = new SmkJabatan();
        setSmkJabatan(addSmkJabatan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkJabatanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkJabatanAction.edit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String itemId = getId();

        List<SmkJabatanDetail> smkJabatanDetailList = new ArrayList();
        List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = new ArrayList();
        SmkJabatan editSmkJabatan = new SmkJabatan();

        try {
            editSmkJabatan = smkJabatanBoProxy.getEditSmkJabatan(itemId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getSmkJabatanByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.edit] Error when retrieving edit data,", e1);
            }
            logger.error("[SmkJabatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            return "failure";
        }

        if(editSmkJabatan != null) {
            editSmkJabatan.setPositionName(editSmkJabatan.getPositionId());
            setSmkJabatan(editSmkJabatan);
            setAddOrEdit(true);
            SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();
            smkJabatanDetail.setFlag("Y");
            smkJabatanDetail.setJabatanSmkId(itemId);
            smkJabatanDetailList = smkJabatanBoProxy.getListSmkJabatanDetail(smkJabatanDetail);

            if(editSmkJabatan.getTipeAspekId().equalsIgnoreCase("A")){
                session.setAttribute("listSmkJabatanSubTipeA", smkJabatanDetailList);
            }else if(editSmkJabatan.getTipeAspekId().equalsIgnoreCase("B1")){
                session.setAttribute("listSmkJabatanBC", smkJabatanDetailList);
            }else if(editSmkJabatan.getTipeAspekId().equalsIgnoreCase("B2")){
                session.setAttribute("listSmkJabatanBC", smkJabatanDetailList);
            }else if(editSmkJabatan.getTipeAspekId().equalsIgnoreCase("C")){
                session.setAttribute("listSmkJabatanBC", smkJabatanDetailList);
            }
        }

        setAddOrEdit(true);
        logger.info("[SmkJabatanAction.edit] end process >>>");
        return "init_add";
    }

    @Override
    public String delete() {
        logger.info("[SmkJabatanAction.edit] start process >>>");
        String itemId = getId();

        List<SmkJabatanDetail> smkJabatanDetailList = new ArrayList();
        List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = new ArrayList();
        SmkJabatan editSmkJabatan = new SmkJabatan();

        try {
            editSmkJabatan = smkJabatanBoProxy.getEditSmkJabatan(itemId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getSmkJabatanByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.delete] Error when retrieving edit data,", e1);
            }
            logger.error("[SmkJabatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            return "failure";
        }

        if(editSmkJabatan != null) {
            editSmkJabatan.setPositionName(editSmkJabatan.getPositionId());
            setSmkJabatan(editSmkJabatan);
            setDelete(true);
            SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();
            smkJabatanDetail.setFlag("Y");
            smkJabatanDetail.setJabatanSmkId(itemId);
            smkJabatanDetailList = smkJabatanBoProxy.getListSmkJabatanDetail(smkJabatanDetail);

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("listSmkJabatanSubTipeA", smkJabatanDetailList);
        }

        logger.info("[SmkJabatanAction.delete] end process >>>");
        return "init_add";
    }

    public List<Position> cekJabatan(String branchId, String Id){
        List<Position> positions = new ArrayList<>();
        String jabatan = "";
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkJabatanBo smkJabatanBo = (SmkJabatanBo) ctx.getBean("smkJabatanBoProxy");
            positions = smkJabatanBo.cekJabatan(branchId, Id);
        } catch (GeneralBOException e1) {
            logger.error("[SmkJabatanAction.edit] Error when retrieving edit data,", e1);
        }

        return positions;
    }

    //untuk mendapatkan nilai aspek A, B, C
    public SmkJabatan getNilaiAspek(String branchId, String positionId){
        SmkJabatan smkJabatan = new SmkJabatan();
        String jabatan = "";
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkJabatanBo smkJabatanBo = (SmkJabatanBo) ctx.getBean("smkJabatanBoProxy");
            smkJabatan = smkJabatanBo.getNilaiAspek(branchId, positionId);
        } catch (GeneralBOException e1) {
            logger.error("[SmkJabatanAction.edit] Error when retrieving edit data,", e1);
        }

        return smkJabatan;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        if (isAddOrEdit()) {
            if (!isAdd()) {
                logger.info("[SmkKategoriAction.saveEdit] start process >>>");
                try {
                    SmkJabatan editSmkJabatan = getSmkJabatan();
                    SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    editSmkJabatan.setLastUpdateWho(userLogin);
                    editSmkJabatan.setLastUpdate(updateTime);
                    editSmkJabatan.setAction("U");
                    editSmkJabatan.setFlag("Y");

                    smkJabatanDetail.setFlag("Y");
                    smkJabatanDetail.setJabatanSmkId(editSmkJabatan.getJabatanSmkId());

                    smkJabatanBoProxy.saveEdit(editSmkJabatan);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[SmkKategoriAction.saveEdit] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[SmkKategoriAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }
                logger.info("[SmkKategoriAction.saveEdit] end process <<<");
            }else{
                logger.info("[SmkJabatanAction.saveAdd] start process >>>");

                try {
                    SmkJabatan smkJabatan = getSmkJabatan();
                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


                    smkJabatan.setCreatedWho(userLogin);
                    smkJabatan.setLastUpdate(updateTime);
                    smkJabatan.setCreatedDate(updateTime);
                    smkJabatan.setLastUpdateWho(userLogin);
                    smkJabatan.setAction("C");
                    smkJabatan.setFlag("Y");

                    smkJabatanBoProxy.saveAdd(smkJabatan);
                }catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }


                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");

                logger.info("[liburAction.saveAdd] end process >>>");
                return "success_save_add";
            }
        }else if (isDelete()) {
            logger.info("[SmkJabatanAction.saveDelete] start process >>>");
            try {
                SmkJabatan deleteSmkJabatan = getSmkJabatan();

                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                deleteSmkJabatan.setLastUpdate(updateTime);
                deleteSmkJabatan.setLastUpdateWho(userLogin);
                deleteSmkJabatan.setAction("D");
                deleteSmkJabatan.setFlag("N");

                smkJabatanBoProxy.saveDelete(deleteSmkJabatan);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkJabatanAction.saveDelete] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[SmkJabatanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }
            logger.info("[SmkJabatanAction.saveDelete] end process <<<");
            return "success_save_add";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSmkJabatanSubTipeA");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[SmkJabatanAction.saveEdit] start process >>>");
        try {

            SmkJabatan editSmkJabatan = getSmkJabatan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkJabatan.setLastUpdateWho(userLogin);
            editSmkJabatan.setLastUpdate(updateTime);
            editSmkJabatan.setAction("U");
            editSmkJabatan.setFlag("Y");

            smkJabatanBoProxy.saveEdit(editSmkJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkJabatanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public List<SmkJabatanDetail> searchSmkJabatanSubTipeA() {
        logger.info("[SmkJabatanAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");

        return smkJabatanDetailList;
    }

    public List<SmkIndikatorPenilaianAspek> aspekB() {
        logger.info("[SmkJabatanAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianAspek> smkJabatanDetailList = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("listSmkJabatanBC");

        return smkJabatanDetailList;
    }

    public List<SmkJabatanDetail> searchSmkJabatanDetailEdit(String nip) {
        logger.info("[SmkJabatanAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> listOfsearchSmkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
        List<SmkJabatanDetail> listHasil =  new ArrayList();

        if(nip != null && !"".equalsIgnoreCase(nip)){
            if(listOfsearchSmkJabatanDetail != null){
                for (SmkJabatanDetail smkJabatanDetail: listOfsearchSmkJabatanDetail) {
                    if(nip.equalsIgnoreCase(smkJabatanDetail.getIndikatorKinerja())){
                        listHasil.add(smkJabatanDetail);
                        break;
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        return listHasil;
    }

    public List<SmkJabatanDetail> searchSmkJabatanDetail(String id) {
        logger.info("[SmkJabatanAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> listOfsearchSmkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanBC");
        List<SmkJabatanDetail> listHasil =  new ArrayList();

        if(id != null && !"".equalsIgnoreCase(id)){
            if(listOfsearchSmkJabatanDetail != null){
                for (SmkJabatanDetail smkJabatanDetail: listOfsearchSmkJabatanDetail) {
                    if(id.equalsIgnoreCase(smkJabatanDetail.getJabatanSmkDetailId())){
                        listHasil.add(smkJabatanDetail);
                        break;
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        return listHasil;
    }

    public void saveAdd(String nama, String bobot, String subAspek, String subAspekName, String satuan){
        logger.info("[SppdAction.saveAdd] start process >>>");
        List<SmkJabatanDetail> smkJabatanDetailList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();

            smkJabatanDetail.setIndikatorKinerja(nama);
            smkJabatanDetail.setBobot(BigDecimal.valueOf(Double.parseDouble(bobot)));
            smkJabatanDetail.setSubAspekA(subAspek);
            smkJabatanDetail.setSubAspekANama(subAspekName);
            smkJabatanDetail.setSatuan(satuan);
            smkJabatanDetail.setNilaiPrestasi(BigDecimal.valueOf(0));
            smkJabatanDetail.setNilai(BigDecimal.valueOf(0));

            smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
            if(smkJabatanDetailList != null){
                smkJabatanDetailList.add(smkJabatanDetail);
            }else{
                smkJabatanDetailList = new ArrayList();
                smkJabatanDetailList.add(smkJabatanDetail);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "smkJabatanBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[smkJabatanAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[smkJabatanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("listSmkJabatanSubTipeA", smkJabatanDetailList);
    }

    public List<SmkJabatanDetail> loadAspekA(){
        logger.info("[SppdAction.loadAspekA] start process >>>");
        List<SmkJabatanDetail> smkJabatanDetailList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();
        smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");

        return smkJabatanDetailList;
    }

    public List<SmkJabatanDetail> saveEditSmkJabatan(String namaOld, String nama, String bobot, String subAspek, String subAspekName, String satuan) {
        logger.info("[SmkJabatanAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> listOfsearchSmkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
        List<SmkJabatanDetail> listHasil =  new ArrayList();

        if(namaOld != null && !"".equalsIgnoreCase(namaOld)){
            if(listOfsearchSmkJabatanDetail != null){
                for (SmkJabatanDetail smkJabatanDetail: listOfsearchSmkJabatanDetail) {
                    if(namaOld.equalsIgnoreCase(smkJabatanDetail.getIndikatorKinerja())){
                        smkJabatanDetail.setIndikatorKinerja(nama);
                        smkJabatanDetail.setBobot(BigDecimal.valueOf(Integer.valueOf(bobot)));
                        smkJabatanDetail.setSubAspekA(subAspek);
                        smkJabatanDetail.setSubAspekANama(subAspekName);
                        smkJabatanDetail.setSatuan(satuan);

                        listHasil.add(smkJabatanDetail);
                    }else{
                        listHasil.add(smkJabatanDetail);
                    }
                }
            }

            logger.info("[SmkJabatanAction.init] end process >>>");
        }
        session.removeAttribute("listSmkJabatanSubTipeA");
        session.setAttribute("listSmkJabatanSubTipeA", listHasil);
        return listHasil;
    }

    public List<SmkJabatanDetail> saveEditSmkJabatan(String namaOld, String nama, String strBobot,
                                                     String subAspek, String subAspekName, String satuan, String strNilai) {
        logger.info("[SmkJabatanAction.saveEditSmkJabatan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> listOfsearchSmkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
        List<SmkJabatanDetail> listHasil =  new ArrayList();

        try{
            if(namaOld != null && !"".equalsIgnoreCase(namaOld)){
                if(listOfsearchSmkJabatanDetail != null){
                    for (SmkJabatanDetail smkJabatanDetail: listOfsearchSmkJabatanDetail) {
                        if(namaOld.equalsIgnoreCase(smkJabatanDetail.getIndikatorKinerja())){
                            smkJabatanDetail.setIndikatorKinerja(nama);
                            smkJabatanDetail.setBobot(BigDecimal.valueOf(Double.valueOf(strBobot)));
                            smkJabatanDetail.setSubAspekA(subAspek);
                            smkJabatanDetail.setSubAspekANama(subAspekName);
                            smkJabatanDetail.setSatuan(satuan);

                            BigDecimal nilai = new BigDecimal(Double.valueOf(strNilai));
                            BigDecimal bobot = new BigDecimal(Double.valueOf(strBobot));

                            BigDecimal total = nilai.multiply(bobot).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);

                            smkJabatanDetail.setNilai(nilai);
                            smkJabatanDetail.setNilaiPrestasi(total);

                            listHasil.add(smkJabatanDetail);
                        }else{
                            listHasil.add(smkJabatanDetail);
                        }
                    }
                }

                logger.info("[SmkJabatanAction.saveEditSmkJabatan] end process >>>");
            }
        }catch (Exception E){
            logger.error("[SmkJabatanAction.saveEditSmkJabatan] Error when saving error,", E);
        }

        session.removeAttribute("listSmkJabatanSubTipeA");
        session.setAttribute("listSmkJabatanSubTipeA", listHasil);
        return listHasil;
    }

    public List<SmkJabatanDetail> saveEditSmkJabatanBC(String id, String bobot) {
        logger.info("[SmkJabatanAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> listOfsearchSmkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanBC");
        List<SmkJabatanDetail> listHasil =  new ArrayList();

        if(id!= null && !"".equalsIgnoreCase(id)){
            if(listOfsearchSmkJabatanDetail != null){
                for (SmkJabatanDetail smkJabatanDetail: listOfsearchSmkJabatanDetail) {
                    if(id.equalsIgnoreCase(smkJabatanDetail.getJabatanSmkDetailId())){
                        smkJabatanDetail.setBobot(BigDecimal.valueOf(Double.valueOf(bobot)));
                        listHasil.add(smkJabatanDetail);
                    }else{
                        listHasil.add(smkJabatanDetail);
                    }
                }
            }

            logger.info("[SmkJabatanAction.init] end process >>>");
        }
        session.removeAttribute("listSmkJabatanBC");
        session.setAttribute("listSmkJabatanBC", listHasil);
        return listHasil;
    }

    public List<SmkJabatanDetail> saveSmkJabatanDetailDelete(String nama) {
        logger.info("[KeluargaAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> listOfsearchSmkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
        List<SmkJabatanDetail> listHasil =  new ArrayList();

        if(nama != null && !"".equalsIgnoreCase(nama)){
            if(listOfsearchSmkJabatanDetail != null){
                for (SmkJabatanDetail smkJabatanDetail: listOfsearchSmkJabatanDetail) {
                    if(nama.equalsIgnoreCase(smkJabatanDetail.getIndikatorKinerja())){

                    }else{
                        listHasil.add(smkJabatanDetail);
                    }
                }
            }

            logger.info("[SppdAction.init] end process >>>");
        }
        session.removeAttribute("listSmkJabatanSubTipeA");
        session.setAttribute("listSmkJabatanSubTipeA", listHasil);
        return listHasil;
    }

    public String saveDelete(){
        logger.info("[SmkJabatanAction.saveDelete] start process >>>");
        try {

            SmkJabatan deleteSmkJabatan = getSmkJabatan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkJabatan.setLastUpdate(updateTime);
            deleteSmkJabatan.setLastUpdateWho(userLogin);
            deleteSmkJabatan.setAction("U");
            deleteSmkJabatan.setFlag("N");

            smkJabatanBoProxy.saveDelete(deleteSmkJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkJabatanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        return null;
    }

    @Override
    public String search() {
        logger.info("[SmkJabatanAction.search] start process >>>");

        SmkJabatan searchSmkJabatan = getSmkJabatan();
        List<SmkJabatan> listOfsearchSmkJabatan = new ArrayList();

        try {
            listOfsearchSmkJabatan = smkJabatanBoProxy.getByCriteria(searchSmkJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkJabatan);

        logger.info("[SmkJabatanAction.search] end process <<<");

        return SUCCESS;
    }

    public String detail() {
        logger.info("[SmkJabatanAction.search] start process >>>");

        SmkJabatan viewSmkJabatan = new SmkJabatan();
        viewSmkJabatan.setPositionId(getPosition());
        viewSmkJabatan.setDivisiId(getDivisi());
        viewSmkJabatan.setBranchId(getBranch());
        List<SmkJabatan> listOfViewSmkJabatan = new ArrayList();

        try {
            listOfViewSmkJabatan = smkJabatanBoProxy.getViewSmkJabatan(viewSmkJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfViewSmkJabatan");
        session.setAttribute("listOfViewSmkJabatan", listOfViewSmkJabatan);

        logger.info("[SmkJabatanAction.search] end process <<<");
        setSmkJabatan(viewSmkJabatan);
        return "init_detail";
    }

    public String detailAction() {
        logger.info("[SmkJabatanAction.search] start process >>>");

        SmkJabatan viewSmkJabatan = new SmkJabatan();
        viewSmkJabatan.setPositionId(getPosition());
        viewSmkJabatan.setDivisiId(getDivisi());
        viewSmkJabatan.setBranchId(getBranch());
        List<SmkJabatan> listOfViewSmkJabatan = new ArrayList();

        try {
            listOfViewSmkJabatan = smkJabatanBoProxy.getViewSmkJabatan(viewSmkJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfViewSmkJabatan");
        session.setAttribute("listOfViewSmkJabatan", listOfViewSmkJabatan);

        logger.info("[SmkJabatanAction.search] end process <<<");
        return "modal_action";
    }

    public String detail2() {
        logger.info("[SmkJabatanAction.search] start process >>>");

        SmkJabatanDetail viewSmkJabatanDetail = new SmkJabatanDetail();
        viewSmkJabatanDetail.setJabatanSmkId(getId());
        List<SmkJabatanDetail> listOfViewSmkJabatanDetail = new ArrayList();

        try {
            listOfViewSmkJabatanDetail = smkJabatanBoProxy.getViewSmkJabatanDetail(viewSmkJabatanDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfViewSmkJabatanDetail");
        session.setAttribute("listOfViewSmkJabatanDetail", listOfViewSmkJabatanDetail);

        logger.info("[SmkJabatanAction.search] end process <<<");
        return "init_detail2";
    }

    public String cancelIndikator(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSmkJabatanSubTipeA");
        return INPUT;
    }

    public String searchSmkJabatan() {
        logger.info("[SmkJabatanAction.search] start process >>>");

        SmkJabatan searchSmkJabatan = new SmkJabatan();
        searchSmkJabatan.setFlag("Y");
        List<SmkJabatan> listOfsearchSmkJabatan = new ArrayList();

        try {
            listOfsearchSmkJabatan = smkJabatanBoProxy.getByCriteria(searchSmkJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkJabatan.addAll(listOfsearchSmkJabatan);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkJabatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkJabatanAction.initForm] end process >>>");
        return INPUT;
    }

    public SmkJabatanDetail initIndikator(String name){
        logger.info("[SmkKategoriAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> listOfResult = (List<SmkJabatanDetail>) session.getAttribute("smkJabatanDetail");

        if(name != null && !"".equalsIgnoreCase(name)){
            if(listOfResult != null){
                for (SmkJabatanDetail smkJabatanDetail : listOfResult) {
                    if(name.equalsIgnoreCase(smkJabatanDetail.getIndikatorKinerja())){
                        setSmkJabatanDetail(smkJabatanDetail);
                        break;
                    }
                }
            } else {
                setSmkJabatanDetail(new SmkJabatanDetail());
            }

            logger.info("[SmkKategoriAction.init] end process >>>");
        }
        return getSmkJabatanDetail();
    }

    public String initSmkJabatan() {
        logger.info("[SmkJabatanAction.search] start process >>>");

        SmkJabatan searchSmkJabatan = new SmkJabatan();
        searchSmkJabatan.setFlag("Y");
        List<SmkJabatan> listOfsearchSmkJabatan = new ArrayList();

        try {
            listOfsearchSmkJabatan = smkJabatanBoProxy.getByCriteria(searchSmkJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkJabatan");
        session.setAttribute("listOfResultSmkJabatan", listOfsearchSmkJabatan);

        logger.info("[SmkJabatanAction.search] end process <<<");

        return "";
    }

    public String modalAdd() {
        SmkJabatan addSmkJabatan = new SmkJabatan();
        setSmkJabatan(addSmkJabatan);
        setAddOrEdit(true);
        setAdd(true);
        return "init_addIndikator";
    }

   /* public String detail(){
        logger.info("[SmkKategoriAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkJabatan smkJabatan = new SmkJabatan();
        SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();
        List<SmkJabatanDetail> smkJabatanDetailList = new ArrayList();

        smkJabatanDetail.setFlag("Y");
        smkJabatanDetail.setJabatanSmkId(itemId);

        if(itemFlag != null){
            try {
                smkJabatan = init(itemId, itemFlag);
                smkJabatanDetailList = smkJabatanDetailBoProxy.getByCriteria(smkJabatanDetail);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getSmkKategoriByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkKategoriAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkKategoriAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(smkJabatan != null) {
                setKategoriAspekId(smkJabatan);
            } else {
                smkJabatan.setFlag(itemFlag);
                smkJabatan.setKategoriAspekId(itemId);
                setKategoriAspekId(smkJabatan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            smkJabatan.setJabatanSmkId(itemId);
            smkJabatan.setFlag(getFlag());
            setKategoriAspekId(smkJabatan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("kategoriAspekIndikator");
        session.setAttribute("kategoriAspekIndikator", smkJabatanDetailList);

        setAddOrEdit(true);
        logger.info("[SmkKategoriAction.edit] end process >>>");

        return "init_detail";
    }*/

    public String addIndikator() {
        logger.info("[SmkJabatanAction.saveAdd] start process >>>");
        List<SmkJabatanDetail> smkJabatanDetailList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            SmkJabatanDetail smkJabatanDetail = getSmkJabatanDetail();

            //HttpSession session = ServletActionContext.getRequest().getSession();
            smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("smkJabatanDetail");
            if(smkJabatanDetailList != null){
                smkJabatanDetailList.add(smkJabatanDetail);
            }else{
                smkJabatanDetailList = new ArrayList();
                smkJabatanDetailList.add(smkJabatanDetail);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("smkJabatanDetail", smkJabatanDetailList);
        return "init_addIndikator";
    }

    public String deleteIndikator() {
        logger.info("[SmkKategoriAction.delete] start process >>>");

        String itemId = getId();
        SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();

        try {
            smkJabatanDetail = initIndikator(itemId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getAlatById");
            } catch (GeneralBOException e1) {
                logger.error("[SmkKategoriAction.delete] Error when retrieving delete data,", e1);
            }
            logger.error("[SmkKategoriAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }

        if (smkJabatanDetail != null) {
            setSmkJabatanDetail(smkJabatanDetail);

        } else {
            smkJabatanDetail.setIndikatorKinerja(itemId);
            setSmkJabatanDetail(smkJabatanDetail);
            addActionError("Error, Unable to find data with id = " + itemId);
            return "failure";
        }

        logger.info("[SmkKategoriAction.delete] end process <<<");

        return "deleteIndikator";
    }

    public String SavedeleteIndikator() {
        logger.info("[SmkJabatanDetailAction.reroute] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("smkJabatanDetail");
        List<SmkJabatanDetail> listHasil =  new ArrayList();
        SmkJabatanDetail smkJabatanDetail1 = getSmkJabatanDetail();

        if(smkJabatanDetail1.getIndikatorKinerja() != null && !"".equalsIgnoreCase(smkJabatanDetail1.getIndikatorKinerja())){
            if(smkJabatanDetailList != null){
                for (SmkJabatanDetail smkJabatanDetail: smkJabatanDetailList) {
                    if(!smkJabatanDetail1.getIndikatorKinerja().equalsIgnoreCase(smkJabatanDetail.getIndikatorKinerja())){
                        listHasil.add(smkJabatanDetail);
                    }else{
                    }
                }
            }
            logger.info("[smkJabatanDetail.init] end process >>>");
        }
        session.removeAttribute("smkJabatanDetail");
        session.setAttribute("smkJabatanDetail", listHasil);
        return "init_addIndikator";
    }

    public List<SmkJabatanDetail> getDataAspek(String branchId, String tipeAspek) {
        logger.info("[DepartmentAction.search] start process >>>");
        List<SmkJabatanDetail> listOfsearchIndikator = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkJabatanBo smkJabatanBo = (SmkJabatanBo) ctx.getBean("smkJabatanBoProxy");
            listOfsearchIndikator = smkJabatanBo.getAspek(branchId, tipeAspek);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkJabatanBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.search] Error when saving error,", e1);
            }
            logger.error("[DepartmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSmkJabatanBC");
        session.setAttribute("listSmkJabatanBC", listOfsearchIndikator);
        return listOfsearchIndikator;
    }

    public String cekSudahTerinput(String branchId, String positionId, String aspek){
        String hasil = "0";
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkJabatanBo smkJabatanBo = (SmkJabatanBo) ctx.getBean("smkJabatanBoProxy");
            hasil = smkJabatanBo.cekSudahTerinput(branchId, positionId, aspek);
        } catch (GeneralBOException e1) {
            logger.error("[SmkJabatanAction.edit] Error when retrieving edit data,", e1);
        }

        return hasil;
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
