package com.neurix.authorization.position.action;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.PositionBagian;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 02/02/2015.
 */
public class PositionAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(PositionAction.class);

    private PositionBo positionBoProxy;
    private List<Position> positionList;
    private List<Position> listOfComboPosition = new ArrayList<Position>();
    private Position position;

    public PositionBo getPositionBoProxy() {
        return positionBoProxy;
    }

    public List<Position> getListOfComboPosition() {
        return listOfComboPosition;
    }

    public void setListOfComboPosition(List<Position> listOfComboPosition) {
        this.listOfComboPosition = listOfComboPosition;
    }

    public void setPositionBoProxy(PositionBo positionBoProxy) {
        this.positionBoProxy = positionBoProxy;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private Position init(String id, String flag) throws NumberFormatException, GeneralBOException {

        logger.info("[PositionAction.init] start process >>>");

        Position initPosition = new Position();
        if (id != null && !"".equalsIgnoreCase(id)) {
            String lId = id;
            try {
                initPosition = positionBoProxy.getPositionById(lId, flag);
            }catch (GeneralBOException e){
                logger.error("[PositionAction.init] error, " + e.getMessage());
                addActionError("Error, Found problem when get position, please inform to your admin" );
            }
        }

        logger.info("[PositionAction.init] end process <<<");

        return initPosition;
    }

    public String initComboPosition(){
        logger.info("[PositionAction.search] start process >>>");

        Position searchPosition = new Position();
        List<Position> listOfSearchPosition = new ArrayList();
        searchPosition.setFlag("Y");
        try {
            listOfSearchPosition = positionBoProxy.getByCriteria(searchPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[PositionhAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboPosition.addAll(listOfSearchPosition);
        logger.info("[PositionAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String edit() {

        logger.info("[PositionAction.edit] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Position editPosition = new Position();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            try {
                editPosition = init(itemId, itemFlag);
            } catch (NumberFormatException e) {
                logger.error("[PositionAction.edit] error parsing long.", e);
                addFieldError("Position.positionId", "Position Id must number.");
                return "failure";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getPositionById");
                } catch (GeneralBOException e1) {
                    logger.error("[PositionAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PositionAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editPosition != null) {
                if (editPosition.getFlag().equalsIgnoreCase("Y")) {
                    setAddOrEdit(true);
                    setPosition(editPosition);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    editPosition.setStPositionId(itemId);
                    editPosition.setFlag(itemFlag);
                    setPosition(editPosition);
                    addActionError("Error, Unable to edit again with flag = N.");
                    return "failure";
                }
            } else {
                editPosition.setStPositionId(itemId);
                editPosition.setFlag(itemFlag);
                setPosition(editPosition);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPosition.setStPositionId(itemId);
            editPosition.setFlag(itemFlag);
            setPosition(editPosition);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[PositionAction.edit] end process <<<");

        return "init_edit";
    }

    @Override
    public String add() {
        logger.info("[PositionAction.add] start process >>>");
        Position addPosition = new Position();
        setPosition(addPosition);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PositionAction.add] end process <<<");
        return "init_add";
    }

    @Override
    public String delete() {
        logger.info("[PositionAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Position deletePosition = new Position();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag) ) {

            try {
                deletePosition = init(itemId, itemFlag);
            } catch (NumberFormatException e) {
                logger.error("[PositionAction.delete] error parsing long.", e);
                addFieldError("Position.positionId", "Position Id must number.");
                return "failure";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getPositionById");
                } catch (GeneralBOException e1) {
                    logger.error("[PositionAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PositionAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePosition != null) {
                if (deletePosition.getFlag().equalsIgnoreCase("Y")) {
                    setDelete(true);
                    setPosition(deletePosition);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    deletePosition.setStPositionId(itemId);
                    deletePosition.setFlag(itemFlag);
                    setPosition(deletePosition);
                    addActionError("Error, Unable to delete again with flag = N.");
                    return "failure";
                }
            } else {
                deletePosition.setStPositionId(itemId);
                deletePosition.setFlag(itemFlag);
                setPosition(deletePosition);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePosition.setStPositionId(itemId);
            deletePosition.setFlag(itemFlag);
            setPosition(deletePosition);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PositionAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {

        return INPUT;
    }

    @Override
    public String save() {
        logger.info("[PositionAction.save] start process >>>");

        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            Position entryPosition = getPosition();

            entryPosition.setCreatedDate(createTime);
            entryPosition.setCreatedWho(userLogin);
            entryPosition.setLastUpdate(createTime);
            entryPosition.setLastUpdateWho(userLogin);
            positionBoProxy.saveAdd(entryPosition);
        }  catch (GeneralBOException e) {
            logger.error("[PositionAction.save] Error, " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PositionAction.save] end process <<<");
        return "success_save";
    }

    public String saveDelete(){
        logger.info("[PositionAction.saveDelete] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            Position deletePosition = getPosition();

            if (deletePosition.getStPositionId() != null && !"".equalsIgnoreCase(deletePosition.getStPositionId()))
                    //deletePosition.setPositionId(Long.parseLong());
                deletePosition.setPositionId(deletePosition.getStPositionId());

                deletePosition.setLastUpdate(updateTime);
                deletePosition.setLastUpdateWho(userLogin);
                deletePosition.setAction("U");
                deletePosition.setFlag("N");

                positionBoProxy.saveDelete(deletePosition);

            } catch (GeneralBOException e) {
                throw new GeneralBOException(e.getMessage());
            }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[Position.saveDelete] end process <<<");
        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[GolonganAction.saveEdit] start process >>>");

        try {

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            Position editPosition = getPosition();
            editPosition.setLastUpdate(updateTime);
            editPosition.setLastUpdateWho(userLogin);
            editPosition.setAction("U");
            editPosition.setFlag("Y");

            positionBoProxy.saveEdit(editPosition);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PositionAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }
//        String itemId = getPosition().getStPositionId();
//        if (itemId != null && !"".equalsIgnoreCase(itemId)) {
//            //edit
//
//        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PositionAction.saveEdit] end process <<<");
        return "success_save_edit";
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        Position initPosition = new Position();
        setPosition(initPosition);
        setDelete(false);
        setAdd(false);
        setAddOrEdit(false);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

//        return INPUT;
        return "input_position";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[PositionAction.search] start process >>>");

        Position searchPosition = getPosition();
        List<Position> listOfSearchPosition = new ArrayList();
        try {
            listOfSearchPosition = positionBoProxy.getByCriteria(searchPosition);
            position.setSuccessMessage("Data Search Success");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PositionAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchPosition);

        logger.info("[PositionAction.search] end process <<<");

        return SUCCESS;
    }


    public String searchPosition() {
        logger.info("[PositionAction.search] start process >>>");

        Position searchPosition = new Position();
        searchPosition.setFlag("Y");
        List<Position> listOfSearchPosition = new ArrayList();
        try {
            listOfSearchPosition = positionBoProxy.getByCriteria(searchPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[PositionAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboPosition.addAll(listOfSearchPosition);
        return SUCCESS;
    }
    public List<Position> searchPositionMutasi(String branchId, String divisiId) {
        logger.info("[PositionAction.search] start process >>>");

        Position searchPosition = new Position();
        searchPosition.setBranchId(branchId);
        searchPosition.setDepartmentId(divisiId);

        List<Position> listOfSearchPosition = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

            listOfSearchPosition = positionBo.searchPositionMutasi(searchPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[PositionAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfSearchPosition;
    }
    public List<Position> searchPosition2(String branchId, String divisiId) {
        logger.info("[PositionAction.search] start process >>>");

        Position searchPosition = new Position();
        searchPosition.setBranchId(branchId);
        searchPosition.setDepartmentId(divisiId);

        List<Position> listOfSearchPosition = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

            listOfSearchPosition = positionBo.searchPosition2Sys(searchPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[PositionAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfSearchPosition;
    }

    public List<Position> searchDivisi(String branchId) {
        logger.info("[PositionAction.searchDivisi] start process >>>");

        Position searchPosition = new Position();
        searchPosition.setBranchId(branchId);

        List<Position> listOfSearchPosition = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

            listOfSearchPosition = positionBo.searchDivisi(searchPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.searchDivisi] Error when saving error,", e1);
            }
            logger.error("[PositionAction.searchDivisi] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfSearchPosition;
    }

    public List<Position> searchPositionBiodata(String divisiId) {
        logger.info("[PositionAction.searchPositionBiodata] start process >>>");

        List<Position> listOfSearchPosition = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

            listOfSearchPosition = positionBo.searchPositionBiodataSys(divisiId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.searchPositionBiodata] Error when saving error,", e1);
            }
            logger.error("[PositionAction.searchPositionBiodata] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[PositionAction.searchPositionBiodata] End process >>>");
        return listOfSearchPosition;
    }
    public List<Position> searchPositionBiodataHistory(String divisiId) {
        logger.info("[PositionAction.searchPositionBiodata] start process >>>");

        List<Position> listOfSearchPosition = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

            listOfSearchPosition = positionBo.searchPositionBiodataSysHistory(divisiId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.searchPositionBiodata] Error when saving error,", e1);
            }
            logger.error("[PositionAction.searchPositionBiodata] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[PositionAction.searchPositionBiodata] End process >>>");
        return listOfSearchPosition;
    }

    public List<Position> searchPosition2(String branchId) {
        logger.info("[PositionAction.search] start process >>>");

        Position searchPosition = new Position();
        searchPosition.setBranchId(branchId);

        List<Position> listOfSearchPosition = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

            listOfSearchPosition = positionBo.searchPosition2Sys(searchPosition.getBranchId());
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[PositionAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfSearchPosition;
    }
    public List<Position> searchDivisi2(String branchId) {
        logger.info("[PositionAction.searchDivisi] start process >>>");

        Position searchPosition = new Position();
//        if (!"0".equalsIgnoreCase(branchId)){
            searchPosition.setBranchId(branchId);
//        }
        List<Position> listOfSearchPosition = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

            listOfSearchPosition = positionBo.searchDivisi(searchPosition);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.searchDivisi] Error when saving error,", e1);
            }
            logger.error("[PositionAction.searchDivisi] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfSearchPosition;
    }

    public List<Position> typeAheadPosition(String key) {
        logger.info("[PositionAction.typeAheadPosition] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        List<Position> listOfSearchPosition = new ArrayList();
        try {
            listOfSearchPosition = positionBo.typeAheadPosition(key);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.typeAheadPosition");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.typeAheadPosition] Error when saving error,", e1);
            }
            logger.error("[PositionAction.typeAheadPosition] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfSearchPosition;
    }
    public List<ImPosition> typeHeadPosition(String query) {
        logger.info("[PositionAction.typeHeadPosition] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        return positionBo.getPositionByString(query);
    }

    public CrudResponse checkAndGetPositionAktif(String positionId, String branchId, String nip){
        logger.info("[PositionAction.typeHeadPosition] START process >>>");

        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        try {
            PersonilPosition personilPosition = positionBo.getAndCheckJabatanTerpakai(positionId, branchId, nip);
            if (personilPosition != null){
                response.setStatus("error");
                response.setMsg("ditemukan pegawai aktif pada jabatan tersebut : "+personilPosition.getPersonName());

                response.setData(personilPosition.getNip());
            } else {
                response.setStatus("success");
            }
        } catch (GeneralBOException e){
            logger.info("[PositionAction.checkAndGetPositionAktif] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[PositionAction.checkAndGetPositionAktif] ERROR. " + e);
        }

        logger.info("[PositionAction.typeHeadPosition] END process <<<");
        return response;
    }

    public PositionBagian getBagianById(String id) {
        logger.info("[PositionAction.getBagianById] START process >>>");

        PositionBagian positionBagian = new PositionBagian();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBagianBo positionBagianBo = (PositionBagianBo) ctx.getBean("positionBagianBoProxy");

        try {
            positionBagian = positionBagianBo.getPositionBagianById(id);
        } catch (GeneralBOException e){
            logger.error("[PositionAction.getBagianById] Found problem when searching data by criteria, please inform to your admin.", e);

        }

        logger.info("[PositionAction.getBagianById] END process <<<");
        return positionBagian;
    }

    public List<Position> getUnitCostBySubBid(String bagianId){
        logger.info("[PositionAction.getUnitCostBySubBid] START process >>>");

        List<Position> positions = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        try {
            positions = positionBo.getUnitCostByBagian(bagianId);
        } catch (GeneralBOException e){
            logger.error("[PositionAction.getBagianById] Found problem when searching data by criteria, please inform to your admin.", e);
        }

        logger.info("[PositionAction.getUnitCostBySubBid] END process <<<");
        return positions;
    }

    public CrudResponse getOnePositionByKodering(String kodering){
        logger.info("[PositionAction.getUnitCostBySubBid] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        CrudResponse response = new CrudResponse();

        Position position = new Position();

        try {
            position = positionBo.getOnePositionByKodering(kodering);
        } catch (GeneralBOException e){
            logger.error("[PositionAction.getOnePositionByKodering] Found problem when searching data by criteria, please inform to your admin.", e);
            response.addResponse("error", "[PositionAction.getOnePositionByKodering] Found problem when searching data by criteria, please inform to your admin."+ e);
            return response;
        }

        if (position != null && position.getPositionId() != null){
            String positionName = position.getPositionName();
            response.addResponse("error", "Ditemukan Kodering yang sama pada : " + positionName);
            return response;
        }

        response.addResponse("success", "Tidak ditemukan kodering yang sama.");
        logger.info("[PositionAction.getUnitCostBySubBid] END process <<<");
        return response;
    }

    public String getSugestKodering(String idSubBidang){
        logger.info("[PositionAction.getSugestKodering] START process >>>");

        String sugestKodering = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        try {
            sugestKodering = positionBo.sugestLastKoderingBySubbidId(idSubBidang);
        } catch (GeneralBOException e){
            logger.error("[PositionAction.getSugestKodering] Found problem when searching data by criteria, please inform to your admin.", e);
        }
        logger.info("[PositionAction.getSugestKodering] END process <<<");
        return sugestKodering;
    }
}
