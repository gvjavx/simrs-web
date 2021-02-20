package com.neurix.simrs.master.kategoripersediaan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;

import com.neurix.simrs.master.kategoripersediaan.bo.KategoriPersediaanBo;
import com.neurix.simrs.master.kategoripersediaan.model.KategoriPersediaan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by reza on 30/09/20.
 */
public class KategoriPersediaanAction  extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(KategoriPersediaanAction.class);
    private KategoriPersediaanBo kategoriPersediaanBoProxy;
    private KategoriPersediaan kategoriPersediaan;
    private List<KategoriPersediaan> listOfKategoriPersediaan = new ArrayList<>();

    public KategoriPersediaanBo getKategoriPersediaanBoProxy() {
        return kategoriPersediaanBoProxy;
    }

    public void setKategoriPersediaanBoProxy(KategoriPersediaanBo kategoriPersediaanBoProxy) {
        this.kategoriPersediaanBoProxy = kategoriPersediaanBoProxy;
    }

    public KategoriPersediaan getKategoriPersediaan() {
        return kategoriPersediaan;
    }

    public void setKategoriPersediaan(KategoriPersediaan kategoriPersediaan) {
        this.kategoriPersediaan = kategoriPersediaan;
    }

    public List<KategoriPersediaan> getListOfKategoriPersediaan() {
        return listOfKategoriPersediaan;
    }

    public void setListOfKategoriPersediaan(List<KategoriPersediaan> listOfKategoriPersediaan) {
        this.listOfKategoriPersediaan = listOfKategoriPersediaan;
    }
    public KategoriPersediaan init(String kode, String flag){
        logger.info("[KategoriPersediaanAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KategoriPersediaan> listOfResult = (List<KategoriPersediaan>) session.getAttribute("listOfResultKategoriPersediaan");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (KategoriPersediaan kategoriPersediaan : listOfResult){
                    if (kode.equalsIgnoreCase(kategoriPersediaan.getIdKategoriPersediaan()) && flag.equalsIgnoreCase(kategoriPersediaan.getFlag())){
                        setKategoriPersediaan(kategoriPersediaan);
                        break;
                    }
                }
            } else {
                setKategoriPersediaan(new KategoriPersediaan());
            }
            logger.info("[KategoriPersediaanAction.init] end process >>>>>");
        }
        return getKategoriPersediaan();
    }

    @Override
    public String add() {
        logger.info("[KategoriPersediaanAction.add] start process >>>");

        KategoriPersediaan addKategoriPersediaan = new KategoriPersediaan();
        setKategoriPersediaan(addKategoriPersediaan);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[KategoriPersediaan Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[KategoriPersediaan Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        KategoriPersediaan editKategoriPersediaan = new KategoriPersediaan();
        if(itemFlag != null){
            try {
                editKategoriPersediaan = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editKategoriPersediaan != null) {
                setKategoriPersediaan(editKategoriPersediaan);
            } else {
                editKategoriPersediaan.setFlag(itemFlag);
                setKategoriPersediaan(editKategoriPersediaan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editKategoriPersediaan.setFlag(getFlag());
            setKategoriPersediaan(editKategoriPersediaan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[KategoriPersediaanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[KategoriPersediaanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        KategoriPersediaan deleteKategoriPersediaan = new KategoriPersediaan();

        if (itemFlag != null ) {
            try {
                deleteKategoriPersediaan = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteKategoriPersediaan != null) {
                setKategoriPersediaan(deleteKategoriPersediaan);

            } else {
                deleteKategoriPersediaan.setFlag(itemFlag);
                setKategoriPersediaan(deleteKategoriPersediaan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteKategoriPersediaan.setFlag(itemFlag);
            setKategoriPersediaan(deleteKategoriPersediaan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteKategoriPersediaanAction.delete] end process <<<");
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
        logger.info("[JenisObatAction.search] start process >>>");

        KategoriPersediaan searchKategoriPersediaan = getKategoriPersediaan();
        List<KategoriPersediaan> listOfsearchKategoriPersediaan = new ArrayList();
        try {
            listOfsearchKategoriPersediaan = kategoriPersediaanBoProxy.getByCriteria(searchKategoriPersediaan);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKategoriPersediaan");
        session.setAttribute("listOfResultKategoriPersediaan", listOfsearchKategoriPersediaan);
        setKategoriPersediaan(searchKategoriPersediaan);
        logger.info("[KategoriPersediaanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[KategoriPersediaanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setKategoriPersediaan(new KategoriPersediaan());
        session.removeAttribute("listOfResultKategoriPersediaan");
        logger.info("[KategoriPersediaanAction.initForm] end process >>>");
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

    public String saveAdd(){
        logger.info("[KategoriPersediaanAction.saveAdd] start process >>>");

        try {
            KategoriPersediaan kategoriPersediaan = getKategoriPersediaan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            kategoriPersediaan.setCreatedWho(userLogin);
            kategoriPersediaan.setLastUpdate(updateTime);
            kategoriPersediaan.setCreatedDate(updateTime);
            kategoriPersediaan.setLastUpdateWho(userLogin);
            kategoriPersediaan.setAction("C");
            kategoriPersediaan.setFlag("Y");

            kategoriPersediaanBoProxy.saveAdd(kategoriPersediaan);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultKategoriPersediaan");

        logger.info("[kategoriPersediaanAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[JenisObatAction.saveDelete] start process >>>");
        try {

            KategoriPersediaan deleteKategoriPersediaan = getKategoriPersediaan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKategoriPersediaan.setLastUpdate(updateTime);
            deleteKategoriPersediaan.setLastUpdateWho(userLogin);
            deleteKategoriPersediaan.setAction("D");
            deleteKategoriPersediaan.setFlag("N");

            kategoriPersediaanBoProxy.saveDelete(deleteKategoriPersediaan);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[KategoriPersediaanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[KategoriPersediaanAction.saveEdit] start process >>>");
        try {
            KategoriPersediaan editKategoriPersediaan = getKategoriPersediaan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editKategoriPersediaan.setLastUpdateWho(userLogin);
            editKategoriPersediaan.setLastUpdate(updateTime);
            editKategoriPersediaan.setAction("U");
            editKategoriPersediaan.setFlag("Y");

            kategoriPersediaanBoProxy.saveEdit(editKategoriPersediaan);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[KategoriPersediaanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }
}
