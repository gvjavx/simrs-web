package com.neurix.simrs.master.tindakan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.tindakan.bo.KategoriTindakanPelayananBo;
import com.neurix.simrs.master.tindakan.model.KategoriTindakanPelayanan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KategoriTindakanPelayananAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KategoriTindakanPelayananAction.class);
    KategoriTindakanPelayanan kategoriTindakanPelayanan;
    private KategoriTindakanPelayananBo kategoriTindakanPelayananBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private PelayananBo pelayananBoProxy;

    private List<Pelayanan> listOfComboPelayanan = new ArrayList<Pelayanan>();
    private List<KategoriTindakan> listOfComboKategoriTindakan = new ArrayList<KategoriTindakan>();

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    public List<KategoriTindakan> getListOfComboKategoriTindakan() {
        return listOfComboKategoriTindakan;
    }

    public void setListOfComboKategoriTindakan(List<KategoriTindakan> listOfComboKategoriTindakan) {
        this.listOfComboKategoriTindakan = listOfComboKategoriTindakan;
    }

    public KategoriTindakanPelayananBo getKategoriTindakanPelayananBoProxy() {
        return kategoriTindakanPelayananBoProxy;
    }

    public void setKategoriTindakanPelayananBoProxy(KategoriTindakanPelayananBo kategoriTindakanPelayananBoProxy) {
        this.kategoriTindakanPelayananBoProxy = kategoriTindakanPelayananBoProxy;
    }

    public KategoriTindakanPelayanan getKategoriTindakanPelayanan() {
        return kategoriTindakanPelayanan;
    }

    public void setKategoriTindakanPelayanan(KategoriTindakanPelayanan kategoriTindakanPelayanan) {
        this.kategoriTindakanPelayanan = kategoriTindakanPelayanan;
    }

    public List<Pelayanan> getListOfComboPelayanan() {
        return listOfComboPelayanan;
    }

    public void setListOfComboPelayanan(List<Pelayanan> listOfComboPelayanan) {
        this.listOfComboPelayanan = listOfComboPelayanan;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public KategoriTindakanPelayanan init(String kode, String flag){
        logger.info("[KategoriTindakanPelayananAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KategoriTindakanPelayanan> listOfResult = (List<KategoriTindakanPelayanan>) session.getAttribute("listOfTindakanPelayanan");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (KategoriTindakanPelayanan tindakanPelayanan: listOfResult) {
                    if(kode.equalsIgnoreCase(tindakanPelayanan.getIdKategoriPelayanan()) && flag.equalsIgnoreCase(tindakanPelayanan.getFlag())){
                        setKategoriTindakanPelayanan(tindakanPelayanan);
                        break;
                    }
                }
            } else {
                setKategoriTindakanPelayanan(new KategoriTindakanPelayanan());
            }

            logger.info("[KategoriTindakanPelayananAction.init] end process >>>");
        }
        return getKategoriTindakanPelayanan();
    }

    @Override
    public String add() {
        logger.info("[KategoriTindakanPelayananAction.add] start process >>>");
        KategoriTindakanPelayanan addKategoriTindakanPelayanan = new KategoriTindakanPelayanan();
        setKategoriTindakanPelayanan(addKategoriTindakanPelayanan);
        setAddOrEdit(true);
        setAdd(true);


        logger.info("[KategoriTindakanPelayananAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[KategoriTindakanPelayananAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        KategoriTindakanPelayanan editKategoriTindakanPelayanan = new KategoriTindakanPelayanan();

        if(itemFlag != null){
            try {
                editKategoriTindakanPelayanan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("[KategoriTindakanPelayananAction.edit] Error when retrieving item, Found problem when retrieving data, please inform to your admin.", e);
            }

            if(editKategoriTindakanPelayanan != null) {
                setKategoriTindakanPelayanan(editKategoriTindakanPelayanan);
            } else {
                editKategoriTindakanPelayanan.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setKategoriTindakanPelayanan(editKategoriTindakanPelayanan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editKategoriTindakanPelayanan.setFlag(getFlag());
            setKategoriTindakanPelayanan(editKategoriTindakanPelayanan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[KategoriTindakanPelayananAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[KategoriTindakanPelayananAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        KategoriTindakanPelayanan deleteKategoriTindakanPelayanan = new KategoriTindakanPelayanan();

        if (itemFlag != null ) {

            try {
                deleteKategoriTindakanPelayanan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kategoriTindakanPelayananBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanPelayananBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[KategoriTindakanPelayananAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[KategoriTindakanPelayananAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteKategoriTindakanPelayanan != null) {
                setKategoriTindakanPelayanan(deleteKategoriTindakanPelayanan);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteKategoriTindakanPelayanan.setFlag(itemFlag);
                setKategoriTindakanPelayanan(deleteKategoriTindakanPelayanan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteKategoriTindakanPelayanan.setFlag(itemFlag);
            setKategoriTindakanPelayanan(deleteKategoriTindakanPelayanan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[KategoriTindakanPelayananAction.delete] end process <<<");

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
        logger.info("[DokterAction.search] start process >>>");

        KategoriTindakanPelayanan searchTindakanPelayanan = getKategoriTindakanPelayanan();
        List<KategoriTindakanPelayanan> listOfsearchTindakanPelayanan = new ArrayList();

        try {
            listOfsearchTindakanPelayanan = kategoriTindakanPelayananBoProxy.getByCriteria(searchTindakanPelayanan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanPelayananBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanPelayananBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KategoriTindakanPelayananAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KategoriTindakanPelayananAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfTindakanPelayanan");
        session.setAttribute("listOfTindakanPelayanan", listOfsearchTindakanPelayanan);

        logger.info("[KategoriTindakanPelayananAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[KategoriTindakanPelayananAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfTindakanPelayanan");
        logger.info("[KategoriTindakanPelayananAction.initForm] end process >>>");
        return INPUT;
    }

    public String saveAdd(){
        logger.info("[KategoriTindakanPelayananAction.saveAdd] start process >>>");

        try {
            KategoriTindakanPelayanan tindakanPelayanan = getKategoriTindakanPelayanan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tindakanPelayanan.setCreatedWho(userLogin);
            tindakanPelayanan.setLastUpdate(updateTime);
            tindakanPelayanan.setCreatedDate(updateTime);
            tindakanPelayanan.setLastUpdateWho(userLogin);
            tindakanPelayanan.setAction("C");
            tindakanPelayanan.setFlag("Y");

            kategoriTindakanPelayananBoProxy.saveAdd(tindakanPelayanan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanPelayananBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanPelayananBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[KategoriTindakanPelayananAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[KategoriTindakanPelayananAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfTindakanPelayanan");

        logger.info("[KategoriTindakanPelayananAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[KategoriTindakanPelayananAction.saveEdit] start process >>>");
        try {

            KategoriTindakanPelayanan editKategoriTindakanPelayanan = getKategoriTindakanPelayanan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editKategoriTindakanPelayanan.setLastUpdateWho(userLogin);
            editKategoriTindakanPelayanan.setLastUpdate(updateTime);
            editKategoriTindakanPelayanan.setAction("U");
            editKategoriTindakanPelayanan.setFlag("Y");

            kategoriTindakanPelayananBoProxy.saveEdit(editKategoriTindakanPelayanan);
        } catch (GeneralBOException e) {
            logger.error("[KategoriTindakanPelayananAction.saveEdit] Error when editing item alat, Found problem when saving edit data, please inform to your admin.", e);
        }

        logger.info("[KategoriTindakanPelayananAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[KategoriTindakanPelayananAction.saveDelete] start process >>>");
        try {

            KategoriTindakanPelayanan deleteKategoriTindakanPelayanan = getKategoriTindakanPelayanan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKategoriTindakanPelayanan.setLastUpdate(updateTime);
            deleteKategoriTindakanPelayanan.setLastUpdateWho(userLogin);
            deleteKategoriTindakanPelayanan.setAction("U");
            deleteKategoriTindakanPelayanan.setFlag("N");

            kategoriTindakanPelayananBoProxy.saveDelete(deleteKategoriTindakanPelayanan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanPelayananBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanPelayananBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[KategoriTindakanPelayananAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[KategoriTindakanPelayananAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[KategoriTindakanPelayananAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String initComboPelayanan() {

        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setFlag("Y");

        List<Pelayanan> listOfPelayanan = new ArrayList<Pelayanan>();
        try {
            listOfPelayanan = pelayananBoProxy.getByCriteria(pelayanan);
        } catch (GeneralBOException e) {
            logger.error("[PelayananAction.initComboRole] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }

        listOfComboPelayanan.addAll(listOfPelayanan);

        return "init_combo";
    }

    public String initComboKategori() {

        KategoriTindakan kategoriTindakan = new KategoriTindakan();
        kategoriTindakan.setFlag("Y");

        List<KategoriTindakan> listOfKategoriTidakans = new ArrayList<KategoriTindakan>();
        try {
            listOfKategoriTidakans = kategoriTindakanBoProxy.getDataByCriteria(kategoriTindakan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.initComboKategori] Error when saving error,", e1);
            }
            logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboKategoriTindakan.addAll(listOfKategoriTidakans);

        return "init_combo";
    }
}