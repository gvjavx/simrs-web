package com.neurix.akuntansi.master.mappingJurnal.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.akuntansi.master.trans.bo.TransBo;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.engine.Mapping;
import org.springframework.context.ApplicationContext;
import org.springframework.util.comparator.ComparableComparator;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class MappingJurnalAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MappingJurnalAction.class);
    private MappingJurnalBo mappingJurnalBoProxy;
    private TransBo transBoProxy;
    private MappingJurnal mappingJurnal;
    private String transId;


    public TransBo getTransBoProxy() {
        return transBoProxy;
    }

    public void setTransBoProxy(TransBo transBoProxy) {
        this.transBoProxy = transBoProxy;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    private List<MappingJurnal> listOfComboMappingJurnal = new ArrayList<MappingJurnal>();

    public List<MappingJurnal> getListOfComboMappingJurnal() {
        return listOfComboMappingJurnal;
    }

    public void setListOfComboMappingJurnal(List<MappingJurnal> listOfComboMappingJurnal) {
        this.listOfComboMappingJurnal = listOfComboMappingJurnal;
    }


    public MappingJurnalBo getMappingJurnalBoProxy() {
        return mappingJurnalBoProxy;
    }

    public void setMappingJurnalBoProxy(MappingJurnalBo mappingJurnalBoProxy) {
        this.mappingJurnalBoProxy = mappingJurnalBoProxy;
    }

    public MappingJurnal getMappingJurnal() {
        return mappingJurnal;
    }

    public void setMappingJurnal(MappingJurnal mappingJurnal) {
        this.mappingJurnal = mappingJurnal;
    }

    private List<MappingJurnal> initComboMappingJurnal;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MappingJurnalAction.logger = logger;
    }


    public List<MappingJurnal> getInitComboMappingJurnal() {
        return initComboMappingJurnal;
    }

    public void setInitComboMappingJurnal(List<MappingJurnal> initComboMappingJurnal) {
        this.initComboMappingJurnal = initComboMappingJurnal;
    }

    public MappingJurnal init(String kode, String flag){
        logger.info("[MappingJurnalAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MappingJurnal mappingJurnal: listOfResult) {
                    if(kode.equalsIgnoreCase(mappingJurnal.getMappingJurnalId()) && flag.equalsIgnoreCase(mappingJurnal.getFlag())){
                        setMappingJurnal(mappingJurnal);
                        break;
                    }
                }
            } else {
                setMappingJurnal(new MappingJurnal());
            }

            logger.info("[MappingJurnalAction.init] end process >>>");
        }
        return getMappingJurnal();
    }

    @Override
    public String add() {
        logger.info("[MappingJurnalAction.add] start process >>>");
        MappingJurnal addMappingJurnal = new MappingJurnal();
        setMappingJurnal(addMappingJurnal);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultMapping");

        logger.info("[MappingJurnalAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[MappingJurnalAction.edit] start process >>>");
        String transId = getTransId();
        List<MappingJurnal> mappingJurnalList;
        MappingJurnal search = new MappingJurnal();
        search.setTransId(transId);
        search.setFlag("Y");
        try {
            mappingJurnalList= mappingJurnalBoProxy.getByCriteria(search);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalAction.edit");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.edit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.edit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        Trans trans = transBoProxy.getByTransId(transId);
        if (trans != null) {
        MappingJurnal edit = new MappingJurnal();
        for (MappingJurnal mappingJurnal1 : mappingJurnalList){
            edit.setTransId(mappingJurnal1.getTransId());
            edit.setTipeJurnalId(mappingJurnal1.getTipeJurnalId());
            break;
        }
            edit.setTransName(trans.getTransName());
            edit.setIsPengajuanBiaya(trans.getFlagPengajuanBiaya());
            edit.setTipeJurnalId(trans.getTipeJurnalId());
            edit.setIsOtomatis(trans.getIsOtomatis());
            edit.setIsSumberBaru(trans.getFlagSumberBaru());
            edit.setCreatedWho(trans.getCreatedWho());
            edit.setCreatedDate(trans.getCreatedDate());
            edit.setMaster(trans.getMaster());
        mappingJurnal=edit;
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultMapping",mappingJurnalList);

        logger.info("[MappingJurnalAction.edit] end process <<<");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MappingJurnalAction.delete] start process >>>");
        String transId = getTransId();
        List<MappingJurnal> mappingJurnalList = new ArrayList<>();
        MappingJurnal search = new MappingJurnal();
        search.setTransId(transId);
        search.setFlag("Y");
        try {
            mappingJurnalList= mappingJurnalBoProxy.getByCriteria(search);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "mappingJurnalBoProxy.delete");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.delete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.delete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        Trans trans = transBoProxy.getByTransId(transId);
        if (trans != null) {
        MappingJurnal delete = new MappingJurnal();
        for (MappingJurnal mappingJurnal1 : mappingJurnalList){
            delete.setTransId(mappingJurnal1.getTransId());
            delete.setTipeJurnalId(mappingJurnal1.getTipeJurnalId());
            break;
        }
            delete.setTransName(trans.getTransName());
            delete.setIsPengajuanBiaya(trans.getFlagPengajuanBiaya());
            delete.setTipeJurnalId(trans.getTipeJurnalId());
            delete.setIsOtomatis(trans.getIsOtomatis());
            delete.setIsSumberBaru(trans.getFlagSumberBaru());
            delete.setCreatedWho(trans.getCreatedWho());
            delete.setCreatedDate(trans.getCreatedDate());
            delete.setMaster(trans.getMaster());
        mappingJurnal=delete;

        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultMapping",mappingJurnalList);

        logger.info("[MappingJurnalAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[MappingJurnalAction.view] start process >>>");
        String transId = getTransId();
        List<MappingJurnal> mappingJurnalList;
        MappingJurnal search = new MappingJurnal();
        search.setTransId(transId);
        search.setFlag("Y");
        try {
            mappingJurnalList= mappingJurnalBoProxy.getByCriteria(search);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "mappingJurnalBoProxy.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[mappingJurnalAction.view] Error when view error,", e1);
                return ERROR;
            }
            logger.error("[mappingJurnalAction.view] Error when adding item ," + "[" + logId + "] Found problem when view data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when view data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        Trans trans = transBoProxy.getByTransId(transId);
        if (trans != null) {
        MappingJurnal view = new MappingJurnal();
        for (MappingJurnal mappingJurnal1 : mappingJurnalList){
            view.setTransId(mappingJurnal1.getTransId());
            view.setTipeJurnalId(mappingJurnal1.getTipeJurnalId());
            break;
        }
            view.setTransName(trans.getTransName());
            view.setIsPengajuanBiaya(trans.getFlagPengajuanBiaya());
            view.setTipeJurnalId(trans.getTipeJurnalId());
            view.setIsOtomatis(trans.getIsOtomatis());
            view.setIsSumberBaru(trans.getFlagSumberBaru());
            view.setCreatedWho(trans.getCreatedWho());
            view.setCreatedDate(trans.getCreatedDate());
            view.setMaster(trans.getMaster());
        mappingJurnal=view;
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultMapping",mappingJurnalList);

        logger.info("[MappingJurnalAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    /*public String saveAndEdit() {
        logger.info("[MappingJurnalAction.saveEdit] start process >>>");
        try {
            MappingJurnal dataMapping = getMappingJurnal();
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<MappingJurnal> mappingBaru = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
            List<MappingJurnal> mappingLama = new ArrayList<>();

            MappingJurnal search = new MappingJurnal();
            search.setTransId(dataMapping.getTransId());
            search.setFlag("Y");
            mappingLama= mappingJurnalBoProxy.getByCriteria(search);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            for (MappingJurnal dataBaru : mappingBaru){
                for (MappingJurnal dataLama : mappingLama){
                    //jika sama hanya diupdate
                    if (dataBaru.getKodeRekening().equalsIgnoreCase(dataLama.getKodeRekening())){
                        dataBaru.setTipeJurnalId(dataLama.getTipeJurnalId());
                        dataBaru.setTransName(dataLama.getTransName());
                        dataBaru.setMappingJurnalId(dataLama.getMappingJurnalId());
                        dataBaru.setDivisiId(dataLama.getDivisiId());
                        dataBaru.setCreatedWho(dataLama.getCreatedWho());
                        dataBaru.setCreatedDate(dataLama.getCreatedDate());
                        dataBaru.setTransId(dataLama.getTransId());
                        dataBaru.setLastUpdateWho(userLogin);
                        dataBaru.setLastUpdate(updateTime);
                        dataBaru.setAction("U");
                        dataBaru.setFlag("Y");

                        mappingJurnalBoProxy.saveEdit(dataBaru);
                    }
                }
            }

            //jika tidak ada di data lama maka menambahkan baru
            for (MappingJurnal dataBaru : mappingBaru){
                boolean ada = false;
                for (MappingJurnal dataLama : mappingLama){
                    if (dataBaru.getKodeRekening().equalsIgnoreCase(dataLama.getKodeRekening())){
                        ada=true;
                    }
                }
                if (!ada){
                    dataBaru.setTipeJurnalId(dataMapping.getTipeJurnalId());
                    dataBaru.setTransId(dataMapping.getTransId());
                    dataBaru.setCreatedWho(userLogin);
                    dataBaru.setLastUpdate(updateTime);
                    dataBaru.setCreatedDate(updateTime);
                    dataBaru.setLastUpdateWho(userLogin);
                    dataBaru.setAction("C");
                    dataBaru.setFlag("Y");
                    mappingJurnalBoProxy.saveAdd(dataBaru);
                }
            }

            //jika data tidak ada pada data baru maka dihapus
            for (MappingJurnal dataLama : mappingLama){
                boolean ada = false;
                for (MappingJurnal dataBaru : mappingBaru){
                    if (dataBaru.getKodeRekening().equalsIgnoreCase(dataLama.getKodeRekening())){
                        ada=true;
                    }
                }
                if (!ada){
                    dataLama.setLastUpdateWho(userLogin);
                    dataLama.setLastUpdate(updateTime);
                    dataLama.setAction("D");
                    dataLama.setFlag("N");
                    mappingJurnalBoProxy.saveDelete(dataLama);
                }
            }

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MappingJurnalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }*/


    public String saveEdit() {
        logger.info("[MappingJurnalAction.saveEdit] start process >>>");
        try {
            MappingJurnal dataMapping = getMappingJurnal();
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<MappingJurnal> mappingBaru = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");

            Map<String, MappingJurnal> mappingJurnal = mappingJurnalBoProxy.getListMappingJurnal(dataMapping.getTransId());
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            for (MappingJurnal dataBaru : mappingBaru) {
                MappingJurnal old = mappingJurnal.get(dataBaru.getKodeRekening());
                if (old != null && dataBaru.getKodeRekening().equalsIgnoreCase(old.getKodeRekening())) {
                    dataBaru.setMappingJurnalId(old.getMappingJurnalId());
                    dataBaru.setDivisiId(old.getDivisiId());
                    dataBaru.setCreatedWho(old.getCreatedWho());
                    dataBaru.setCreatedDate(old.getCreatedDate());
                    dataBaru.setTransId(old.getTransId());
                    dataBaru.setTipeJurnalId(old.getTipeJurnalId());
                    dataBaru.setLastUpdateWho(userLogin);
                    dataBaru.setLastUpdate(updateTime);
                    dataBaru.setAction("U");
                    dataBaru.setFlag("Y");
                    mappingJurnalBoProxy.saveEdit(dataBaru);

                    mappingJurnal.remove(old.getKodeRekening());
                } else {
                    dataBaru.setTipeJurnalId(dataMapping.getTipeJurnalId());
                    dataBaru.setTransId(dataMapping.getTransId());
                    dataBaru.setCreatedWho(userLogin);
                    dataBaru.setLastUpdate(updateTime);
                    dataBaru.setCreatedDate(updateTime);
                    dataBaru.setLastUpdateWho(userLogin);
                    dataBaru.setAction("C");
                    dataBaru.setFlag("Y");
                    mappingJurnalBoProxy.saveAdd(dataBaru);
                }

            }
            //jika data tidak ada pada data baru maka dihapus
            if (mappingJurnal.size() > 0) {
                mappingJurnal.forEach((k, dataLama) -> {
                    dataLama.setLastUpdateWho(userLogin);
                    dataLama.setLastUpdate(updateTime);
                    dataLama.setAction("D");
                    dataLama.setFlag("N");
                    mappingJurnalBoProxy.saveDelete(dataLama);
                });

            }

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MappingJurnalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }


    public String saveEditMappingTransaction() {
        logger.info("[MappingJurnalAction.saveEditMappingTransaction] start process >>>");
        MappingJurnal dataMapping = getMappingJurnal();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> mappingBaru = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");

        Map<String, MappingJurnal> mappingJurnal = mappingJurnalBoProxy.getListMappingJurnal(dataMapping.getTransId());
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        List<MappingJurnal> detail = new ArrayList<>();
        for (MappingJurnal dataBaru : mappingBaru) {
            MappingJurnal old = mappingJurnal.get(dataBaru.getKodeRekening());
            if (old != null && dataBaru.getKodeRekening().equalsIgnoreCase(old.getKodeRekening())) {
                dataBaru.setMappingJurnalId(old.getMappingJurnalId());
                dataBaru.setDivisiId(old.getDivisiId());
                dataBaru.setTransId(old.getTransId());
                dataBaru.setTipeJurnalId(old.getTipeJurnalId());
                dataBaru.setLastUpdateWho(userLogin);
                dataBaru.setLastUpdate(updateTime);
                dataBaru.setAction("U");
                dataBaru.setFlag("Y");
//                    mappingJurnalBoProxy.saveEdit(dataBaru);
                detail.add(dataBaru);
                mappingJurnal.remove(old.getKodeRekening());
            } else {
                dataBaru.setTipeJurnalId(dataMapping.getTipeJurnalId());
                dataBaru.setTransId(dataMapping.getTransId());
                dataBaru.setCreatedWho(userLogin);
                dataBaru.setLastUpdate(updateTime);
                dataBaru.setCreatedDate(updateTime);
                dataBaru.setLastUpdateWho(userLogin);
                dataBaru.setAction("C");
                dataBaru.setFlag("Y");
                detail.add(dataBaru);
//                    mappingJurnalBoProxy.saveAdd(dataBaru);
            }

        }
        //jika data tidak ada pada data baru maka dihapus
        if (mappingJurnal.size() > 0) {
            mappingJurnal.forEach((k, dataLama) -> {
                MappingJurnal old = mappingJurnalBoProxy.getMappingJurnalById(dataLama.getMappingJurnalId());
                old.setLastUpdateWho(userLogin);
                old.setLastUpdate(updateTime);
                old.setAction("D");
                old.setFlag("N");
                detail.add(old);
//                    mappingJurnalBoProxy.saveDelete(dataLama);
            });

        }
        try {
            MappingJurnal status = mappingJurnalBoProxy.editMappingJurnalTransaction(dataMapping,detail);

            if (status.getTransId() != null) {
                session.setAttribute("param_id",status.getTransId());
                return SUCCESS;
            }

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.saveEditMappingTransaction] Error when saving error,", e1);
                return ERROR;
    }
            logger.error("[MappingJurnalAction.saveEditMappingTransaction] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MappingJurnalAction.saveEditMappingTransaction] end process <<<");

        return "success_save_edit";
    }


    public String saveDelete(){
        logger.info("[MappingJurnalAction.saveDelete] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
            for (MappingJurnal delete : listOfResult){
                delete.setLastUpdate(updateTime);
                delete.setLastUpdateWho(userLogin);
                delete.setAction("U");
                delete.setFlag("N");

                mappingJurnalBoProxy.saveDelete(delete);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MappingJurnalAction.saveDelete] end process <<<");

        return "success_save_delete";
    }
    public String saveDeleteMappingTransaction() {
        logger.info("[MappingJurnalAction.saveDeleteMappingTransaction] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            HttpSession session = ServletActionContext.getRequest().getSession();
            MappingJurnal dataMapping = getMappingJurnal();
            List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
            for (MappingJurnal delete : listOfResult) {
                delete.setLastUpdate(updateTime);
                delete.setLastUpdateWho(userLogin);
                delete.setAction("D");
                delete.setFlag("N");

                mappingJurnalBoProxy.saveDeleteMappingTransaction(dataMapping, listOfResult);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveDeleteMappingTransaction");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.saveDeleteMappingTransaction] Error when delete error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.saveDeleteMappingTransaction] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MappingJurnalAction.saveDeleteMappingTransaction] end process <<<");

        return "success_save_delete";
    }

    public String saveAddMappingJurnalTransaction() {
        logger.info("[MappingJurnalAction.saveAddMappingJurnalTransaction] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");

        List<MappingJurnal> detail = new ArrayList<>();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String resultBack = "";
        MappingJurnal data = getMappingJurnal();

        for (MappingJurnal mappingJurnal : listOfResult) {
            mappingJurnal.setTipeJurnalId(data.getTipeJurnalId());
            mappingJurnal.setTransName(data.getTransName());
            mappingJurnal.setCreatedWho(userLogin);
            mappingJurnal.setLastUpdate(updateTime);
            mappingJurnal.setCreatedDate(updateTime);
            mappingJurnal.setLastUpdateWho(userLogin);
            mappingJurnal.setAction("C");
            mappingJurnal.setFlag("Y");
            detail.add(mappingJurnal);
        }

        // Fahmi 2021-07-26, penambahan pengecheckkan jika ada detail no rekening sama persis dengan yang akan ditambahkan.
        if(detail.size() > 0)
        {
            int dataSama;
            List<MappingJurnal> oldData ;
            List<MappingJurnal> listDetail ;
            MappingJurnal mappingJurnal = new MappingJurnal();

            // ambil semua data berdasarkan tipe jurnal, posisi dan kode rekening.
            mappingJurnal.setTipeJurnalId(detail.get(0).getTipeJurnalId());
            mappingJurnal.setKodeRekening(detail.get(0).getKodeRekening());
            mappingJurnal.setPosisi(detail.get(0).getPosisi());
            oldData = mappingJurnalBoProxy.getByCriteria(mappingJurnal);

            // kalau ada data yang sama, lakukan pengecheckkan lagi.
            if(null!=oldData && oldData.size() > 0)
            {
                // Ambil 1 1 berdasarkan trans_id untuk mencari yang benar2 sama detail kode rekeningnya dengan
                // yang akan di save
                for (MappingJurnal oldDatum : oldData) {
                    mappingJurnal = new MappingJurnal();
                    mappingJurnal.setTransId(oldDatum.getTransId());
                    listDetail = mappingJurnalBoProxy.getByCriteria(mappingJurnal);
                    dataSama = 0;

                    // Check jika jumlah data tidak sama, lanjut ke data berikutnya
                    if (listDetail.size() != detail.size()) continue;

                    // Check data apakah sama persis.
                    for (MappingJurnal mapping : listDetail) {
                        for (MappingJurnal newData : detail) {
                            if (mapping.getKodeRekening().equalsIgnoreCase(newData.getKodeRekening()) &&
                                    mapping.getTipeJurnalId().equalsIgnoreCase(newData.getTipeJurnalId()) &&
                                    mapping.getPosisi().equalsIgnoreCase(newData.getPosisi())) {
                                dataSama++;
                            }
                        }
                    }

                    // jika jumlah dataSama sana dengan detail yang akan di save,
                    // maka data tersebut sudah pernah dimasukkan
                    if (dataSama == detail.size()) {
                        logger.error("[mappingJurnalAction.saveAddMappingJurnalTransaction] Error data sudah pernah dimasukkan. ");
                        addActionError("Error, " + " data sudah pernah dimasukkan.\n");
                        resultBack = ERROR;
                        throw new GeneralBOException("Error, data sudah pernah dimasukkan.");
                    }

                }
            }
            // jika tidak ada data yang sama, maka aman untuk di save.

        }
        // end Fahmi.

        try {
            MappingJurnal status = mappingJurnalBoProxy.saveMappingJurnalTransaction(data, detail);
            if (status.getTransId() != null) {
                session.setAttribute("param_id",status.getTransId());
                return SUCCESS;
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "mappingJurnalBoProxy.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[mappingJurnalAction.saveAddMappingJurnalTransaction] Error when saving error,", e1);
                resultBack = ERROR;
                return resultBack;
            }
            logger.error("[mappingJurnalAction.saveAddMappingJurnalTransaction] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            resultBack = ERROR;
            return resultBack;
        }
        session.removeAttribute("listOfResult");

        logger.info("[mappingJurnalAction.saveAddMappingJurnalTransaction] end process >>>");
        return resultBack;
    }

    public String saveAdd(){
        logger.info("[MappingJurnalAction.saveAdd] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String resultBack = "";
        MappingJurnal data = getMappingJurnal();
        try {
            if (!resultBack.equals(ERROR)) {
                for (MappingJurnal mappingJurnal : listOfResult) {
                    mappingJurnal.setTipeJurnalId(data.getTipeJurnalId());
                    mappingJurnal.setCreatedWho(userLogin);
                    mappingJurnal.setLastUpdate(updateTime);
                    mappingJurnal.setCreatedDate(updateTime);
                    mappingJurnal.setLastUpdateWho(userLogin);
                    mappingJurnal.setAction("C");
                    mappingJurnal.setFlag("Y");

                    mappingJurnal.setTransId(transId);
                    mappingJurnal.setTransName(data.getTransName());
                    mappingJurnalBoProxy.saveAdd(mappingJurnal);
                    resultBack = "success_save_add";
                }
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "mappingJurnalBoProxy.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[mappingJurnalAction.saveAdd] Error when saving error,", e1);
                resultBack = ERROR;
                return resultBack;
            }
            logger.error("[mappingJurnalAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            resultBack = ERROR;
            return resultBack;
        }

        session.removeAttribute("listOfResult");

        logger.info("[mappingJurnalAction.saveAdd] end process >>>");
        return resultBack;
    }

    @Override
    public String search() {
        logger.info("[MappingJurnalAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object param = session.getAttribute("param_id");
        MappingJurnal searchMappingJurnal;
        if(param != null){
            searchMappingJurnal = new MappingJurnal();
            searchMappingJurnal.setTransId(param.toString());
            searchMappingJurnal.setFlag("Y");
            session.removeAttribute("param_id");
        }
        else {
            searchMappingJurnal = getMappingJurnal();
        }

        List<MappingJurnal> listOfsearchMappingJurnal;
        try {
            listOfsearchMappingJurnal = mappingJurnalBoProxy.getByCriteria(searchMappingJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        session.removeAttribute("listOfResultMapping");
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchMappingJurnal);

        logger.info("[MappingJurnalAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MappingJurnalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[MappingJurnalAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboMappingJurnal() {
        logger.info("[MappingJurnalAction.initComboMappingJurnal] start process >>>");

        MappingJurnal search = new MappingJurnal();
        List<MappingJurnal> listOfSearchMappingJurnal = new ArrayList();
        search.setFlag("Y");
        try {
            listOfSearchMappingJurnal = mappingJurnalBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "mappingJurnalBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.initComboMappingJurnal] Error when saving error,", e1);
            }
            logger.error("[MappingJurnalAction.initComboMappingJurnal] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboMappingJurnal.addAll(listOfSearchMappingJurnal);
        logger.info("[MappingJurnalAction.initComboMappingJurnal] end process <<<");

        return SUCCESS;
    }

    public void saveKodeRekeningSession(String tipeJurnalId,String kodeRekening, String posisi, String master, String bukti, String kodeBarang, String listKirim, String parameter, String kodeRekeningName, String divisiId, String editBiaya) {
        logger.info("[MappingJurnalAction.savePegawaiShift] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
        boolean save = true;
        if (listOfResult==null){
            listOfResult= new ArrayList<>();
        }else{
            for (MappingJurnal mappingJurnal : listOfResult){
                //edited by Aji Noor
                //penambahan kondisi untuk JKR dibolehkan jika D dan K punya coa sama
                if(tipeJurnalId.equalsIgnoreCase("JKR") && !posisi.equalsIgnoreCase(mappingJurnal.getPosisi())){
                    save = true;
                }
                else if(!tipeJurnalId.equalsIgnoreCase("JKR") && kodeRekening.equalsIgnoreCase(mappingJurnal.getKodeRekening())){
                    save = false;
                }
                /*if (parameter.equalsIgnoreCase(mappingJurnal.getKeterangan())) {
                    save=false;saveKodeRekeningSession
                }*/
            }
        }
        if (save){
            MappingJurnal result = new MappingJurnal();
            result.setKodeRekening(kodeRekening);
            result.setKodeRekeningName(kodeRekeningName);
            result.setPosisi(posisi);
            result.setMasterId(master);
            result.setBukti(bukti);
            result.setKodeBarang(kodeBarang);
            result.setKirimList(listKirim);
            result.setKeterangan(parameter);
            result.setDivisiId(divisiId);
            result.setEditBiaya(editBiaya);
            listOfResult.add(result);
        }

        // Fahmi 2021-07-25, sortir untuk penampilan list kode rekening
        listOfResult.sort(CommonUtil.comparatorMappingJurnal());

        session.setAttribute("listOfResultMapping",listOfResult);
        logger.info("[MappingJurnalAction.savePegawaiShift] end process <<<");
    }

    public List<MappingJurnal> searchKodeRekeningSession() {
        logger.info("[MappingJurnalAction.searchKodeRekeningSession] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfsearch= (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
        return listOfsearch;
    }

    public String cekBeforeSave(String tipeJurnal,String transId,String metode){
        String status="";
        /*ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        List<MappingJurnal> mappingJurnalList = new ArrayList<>();
        MappingJurnalBo mappingJurnalBo = (MappingJurnalBo) ctx.getBean("mappingJurnalBoProxy");
        MappingJurnal search = new MappingJurnal();
        search.setFlag("Y");
        search.setTransId(transId);
        try {
            if (("add").equalsIgnoreCase(metode)){
                mappingJurnalList=mappingJurnalBo.getByCriteria(search);
            }
        } catch (GeneralBOException e1) {
            logger.error("[MappingJurnalAction.initComboMappingJurnal] Error when saving error,", e1);
        }
        if (mappingJurnalList.size()!=0){
            status="Transaksi Billing ini sudah ada , gunakan edit";
        }else{
        */
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<MappingJurnal> listOfsearch= (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
            boolean adaDebit=false;
            boolean adaKredit=false;

            if (listOfsearch==null){
                status="Belum ada kode rekening, silahkan ditambahkan";
            }else{
                for(MappingJurnal mappingJurnal:listOfsearch){
                    if (("D").equalsIgnoreCase(mappingJurnal.getPosisi())){
                        adaDebit=true;
                    }else if (("K").equalsIgnoreCase(mappingJurnal.getPosisi())){
                        adaKredit=true;
                    }
                }
                if (!adaDebit){
                    status="Belum ada kode rekening dengan posisi debit";
                }else if (!adaKredit){
                    status="belum ada kode rekening dengan posisi kredit";
                }
            }
//        }
        return status;
    }

    public String deleteSessionKodeRekening(String kodeRekening) {
        logger.info("[MappingJurnalAction.deleteDetailPembayaran] start process >>>");
        String status="";
        String coa = kodeRekening.split(",")[0];
        String posisi = kodeRekening.split(",")[1];
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> mappingJurnalList = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
        List<MappingJurnal> mappingJurnalArrayList = new ArrayList<>();
        for (MappingJurnal mappingJurnal:mappingJurnalList){
            if (mappingJurnal.getKodeRekening().equalsIgnoreCase(coa) && mappingJurnal.getPosisi().equalsIgnoreCase(posisi)) {
            }else{
                mappingJurnalArrayList.add(mappingJurnal);
            }
        }
        session.setAttribute("listOfResultMapping",mappingJurnalArrayList);
        logger.info("[MappingJurnalAction.deleteDetailPembayaran] end process >>>");
        return status;
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}
