package com.neurix.simrs.master.bentukbarang.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.bentukbarang.bo.BentukBarangBo;
import com.neurix.simrs.master.bentukbarang.model.BentukBarang;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BentukBarangAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(BentukBarangAction.class);
    private BentukBarangBo bentukBarangBoProxy;
    private BentukBarang bentukBarang;
    private List<BentukBarang> listOfBentukBarang = new ArrayList<>();

    public List<BentukBarang> getListOfBentukBarang() {
        return listOfBentukBarang;
    }

    public void setListOfBentukBarang(List<BentukBarang> listOfBentukBarang) {
        this.listOfBentukBarang = listOfBentukBarang;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BentukBarangAction.logger = logger;
    }

    public BentukBarangBo getBentukBarangBoProxy() {
        return bentukBarangBoProxy;
    }

    public void setBentukBarangBoProxy(BentukBarangBo bentukBarangBoProxy) {
        this.bentukBarangBoProxy = bentukBarangBoProxy;
    }

    public BentukBarang getBentukBarang() {
        return bentukBarang;
    }

    public void setBentukBarang(BentukBarang bentukBarang) {
        this.bentukBarang = bentukBarang;
    }


    public BentukBarang init(String kode, String flag){
        logger.info("[BentukBarangAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BentukBarang> listOfResult = (List<BentukBarang>) session.getAttribute("listOfResultBentukBarang");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (BentukBarang bentukBarang : listOfResult){
                    if (kode.equalsIgnoreCase(bentukBarang.getIdBentuk()) && flag.equalsIgnoreCase(bentukBarang.getFlag())){
                        setBentukBarang(bentukBarang);
                        break;
                    }
                }
            } else {
                setBentukBarang(new BentukBarang());
            }
            logger.info("[KelasRuanganAction.init] end process >>>>>");
        }
        return getBentukBarang();
    }

    @Override
    public String add() {
        logger.info("[BentukBarangAction.add] start process >>>");

        BentukBarang addBentukBarang = new BentukBarang();
        setBentukBarang(addBentukBarang);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[BentukBarangAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[BentukBarangAction.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        BentukBarang editBentukBarang = new BentukBarang();
        if(itemFlag != null){
            try {
                editBentukBarang = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("editaction"+e.getMessage());
                throw new GeneralBOException("editaction, "+e.getMessage());
            }

            if(editBentukBarang != null) {
                setBentukBarang(editBentukBarang);
            } else {
                editBentukBarang.setFlag(itemFlag);
                setBentukBarang(editBentukBarang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBentukBarang.setFlag(getFlag());
            setBentukBarang(editBentukBarang);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[BentukBarangAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[BentukBarangAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        BentukBarang deleteBentukBarang = new BentukBarang();

        if (itemFlag != null ) {
            try {
                deleteBentukBarang = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteBentukBarang != null) {
                setBentukBarang(deleteBentukBarang);

            } else {
                deleteBentukBarang.setFlag(itemFlag);
                setBentukBarang(deleteBentukBarang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteBentukBarang.setFlag(itemFlag);
            setBentukBarang(deleteBentukBarang);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteBentukBarangAction.delete] end process <<<");

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
        logger.info("[BentukBarangAction.search] start process >>>");

        BentukBarang searchBentukBarang = getBentukBarang();
        List<BentukBarang> listOfsearchBentukBarang = new ArrayList();
        try {
            listOfsearchBentukBarang = bentukBarangBoProxy.getByCriteria(searchBentukBarang);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultBentukBarang");
        session.setAttribute("listOfResultBentukBarang", listOfsearchBentukBarang);
        setBentukBarang(searchBentukBarang);
        logger.info("[BentukBarangAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[bentukBarangAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setBentukBarang(new BentukBarang());
        session.removeAttribute("listOfResultBentukBarang");
        logger.info("[bentukBarangAction.initForm] end process >>>");
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
        logger.info("[BentukBarangAction.saveAdd] start process >>>");

        try {
            BentukBarang bentukBarang = getBentukBarang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            bentukBarang.setCreatedWho(userLogin);
            bentukBarang.setLastUpdate(updateTime);
            bentukBarang.setCreatedDate(updateTime);
            bentukBarang.setLastUpdateWho(userLogin);
            bentukBarang.setAction("C");
            bentukBarang.setFlag("Y");

            bentukBarangBoProxy.saveAdd(bentukBarang);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultBentukBarang");

        logger.info("[BentukBarangAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[BentukBarangAction.saveDelete] start process >>>");
        try {

            BentukBarang deleteBentukBarang = getBentukBarang();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteBentukBarang.setLastUpdate(updateTime);
            deleteBentukBarang.setLastUpdateWho(userLogin);
            deleteBentukBarang.setAction("D");
            deleteBentukBarang.setFlag("N");

            bentukBarangBoProxy.saveDelete(deleteBentukBarang);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[BentukBarangAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[BentukBarangAction.saveEdit] start process >>>");
        try {
            BentukBarang editBentukBarang = getBentukBarang();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editBentukBarang.setLastUpdateWho(userLogin);
            editBentukBarang.setLastUpdate(updateTime);
            editBentukBarang.setAction("U");
            editBentukBarang.setFlag("Y");

            bentukBarangBoProxy.saveEdit(editBentukBarang);
        } catch (GeneralBOException e) {
            logger.error("error edit bentuk barang, "+e.getMessage());
            throw new GeneralBOException("error edit bentuk barang, "+e.getMessage());
        }

        logger.info("[BentukBarangAction.saveEdit] end process <<<");

        return "success_save_edit";
    }
}