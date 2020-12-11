package com.neurix.simrs.master.kategoritindakan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KategoriTindakanAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KategoriTindakanAction.class);
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private KategoriTindakan kategoriTindakan;
    private List<KategoriTindakan> listOfKategoriTindakan = new ArrayList<>();

    public List<KategoriTindakan> getListOfKategoriTindakan() {
        return listOfKategoriTindakan;
    }

    public void setListOfKategoriTindakan(List<KategoriTindakan> listOfKategoriTindakan) {
        this.listOfKategoriTindakan = listOfKategoriTindakan;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KategoriTindakanAction.logger = logger;
    }

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    public KategoriTindakan getKategoriTindakan() {
        return kategoriTindakan;
    }

    public void setKategoriTindakan(KategoriTindakan kategoriTindakan) {
        this.kategoriTindakan = kategoriTindakan;
    }


    public KategoriTindakan init(String kode, String flag){
        logger.info("[KategoriTindakanAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KategoriTindakan> listOfResult = (List<KategoriTindakan>) session.getAttribute("listOfResultKategoriTindakan");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (KategoriTindakan kategoriTindakan : listOfResult){
                    if (kode.equalsIgnoreCase(kategoriTindakan.getIdKategoriTindakan()) && flag.equalsIgnoreCase(kategoriTindakan.getFlag())){
                        setKategoriTindakan(kategoriTindakan);
                        break;
                    }
                }
            } else {
                setKategoriTindakan(new KategoriTindakan());
            }
            logger.info("[KelasRuanganAction.init] end process >>>>>");
        }
        return getKategoriTindakan();
    }

    @Override
    public String add() {
        logger.info("[KategoriTindakanAction.add] start process >>>");

        KategoriTindakan addKategoriTindakan = new KategoriTindakan();
        setKategoriTindakan(addKategoriTindakan);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[KategoriTindakanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[KategoriTindakanAction.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        KategoriTindakan editKategoriTindakan = new KategoriTindakan();
        if(itemFlag != null){
            try {
                editKategoriTindakan = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("editaction"+e.getMessage());
                throw new GeneralBOException("editaction, "+e.getMessage());
            }

            if(editKategoriTindakan != null) {
                setKategoriTindakan(editKategoriTindakan);
            } else {
                editKategoriTindakan.setFlag(itemFlag);
                setKategoriTindakan(editKategoriTindakan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editKategoriTindakan.setFlag(getFlag());
            setKategoriTindakan(editKategoriTindakan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[KategoriTindakanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[KategoriTindakanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        KategoriTindakan deleteKategoriTindakan = new KategoriTindakan();

        if (itemFlag != null ) {
            try {
                deleteKategoriTindakan = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteKategoriTindakan != null) {
                setKategoriTindakan(deleteKategoriTindakan);

            } else {
                deleteKategoriTindakan.setFlag(itemFlag);
                setKategoriTindakan(deleteKategoriTindakan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteKategoriTindakan.setFlag(itemFlag);
            setKategoriTindakan(deleteKategoriTindakan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteKategoriTindakanAction.delete] end process <<<");

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

    @Override
    public String search() {
        logger.info("[KategoriTindakanAction.search] start process >>>");

        KategoriTindakan searchKategoriTindakan = getKategoriTindakan();
        List<KategoriTindakan> listOfsearchKategoriTindakan = new ArrayList();
        try {
            listOfsearchKategoriTindakan = kategoriTindakanBoProxy.getDataByCriteria(searchKategoriTindakan);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKategoriTindakan");
        session.setAttribute("listOfResultKategoriTindakan", listOfsearchKategoriTindakan);
        setKategoriTindakan(searchKategoriTindakan);
        logger.info("[KategoriTindakanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[kategoriTindakanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setKategoriTindakan(new KategoriTindakan());
        session.removeAttribute("listOfResultKategoriTindakan");
        logger.info("[kategoriTindakanAction.initForm] end process >>>");
        return INPUT;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

//    public String getListKategoriTindakan(){
//
//        logger.info("[CheckupDetailAction.getListKategoriTindakan] start process >>>");
//
//        List<KategoriTindakan> kategoriTindakanList = new ArrayList<>();
//        KategoriTindakan kategoriTindakan = new KategoriTindakan();
//
//        try {
//            kategoriTindakanList = kategoriTindakanBoProxy.getByCriteria(kategoriTindakan);
//        }catch (GeneralBOException e){
//            logger.error("[CheckupDetailAction.getListKategoriTindakan] Error when get jenis obat ," + "Found problem when saving add data, please inform to your admin.", e);
//        }
//
//        listOfKategoriTindakan.addAll(kategoriTindakanList);
//        logger.info("[CheckupDetailAction.getListKategoriTindakan] end process <<<");
//        return SUCCESS;
//
//    }
    public String saveAdd(){
        logger.info("[KategoriTindakanAction.saveAdd] start process >>>");

        try {
            KategoriTindakan kategoriTindakan = getKategoriTindakan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            kategoriTindakan.setCreatedWho(userLogin);
            kategoriTindakan.setLastUpdate(updateTime);
            kategoriTindakan.setCreatedDate(updateTime);
            kategoriTindakan.setLastUpdateWho(userLogin);
            kategoriTindakan.setAction("C");
            kategoriTindakan.setFlag("Y");

            kategoriTindakanBoProxy.saveAdd(kategoriTindakan);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultKategoriTindakan");

        logger.info("[KategoriTindakanAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[KategoriTindakanAction.saveDelete] start process >>>");
        try {

            KategoriTindakan deleteKategoriTindakan = getKategoriTindakan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKategoriTindakan.setLastUpdate(updateTime);
            deleteKategoriTindakan.setLastUpdateWho(userLogin);
            deleteKategoriTindakan.setAction("D");
            deleteKategoriTindakan.setFlag("N");

            kategoriTindakanBoProxy.saveDelete(deleteKategoriTindakan);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[KategoriTindakanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[KategoriTindakanAction.saveEdit] start process >>>");
        try {
            KategoriTindakan editKategoriTindakan = getKategoriTindakan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editKategoriTindakan.setLastUpdateWho(userLogin);
            editKategoriTindakan.setLastUpdate(updateTime);
            editKategoriTindakan.setAction("U");
            editKategoriTindakan.setFlag("Y");

            kategoriTindakanBoProxy.saveEdit(editKategoriTindakan);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[KategoriTindakanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }




}