package com.neurix.simrs.master.kelasruangan.action;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.pasien.action.PasienAction;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KelasRuanganAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(PasienAction.class);
    private KelasRuangan kelasRuangan;
    private KelasRuanganBo kelasRuanganBoProxy;
    private PositionBo positionBoProxy;

    private List<Position> listOfComboPositions = new ArrayList<Position>();
    private List<KelasRuangan> listOfKelasRuangan = new ArrayList<>();

    public List<Position> getListOfComboPositions() {
        return listOfComboPositions;
    }

    public void setListOfComboPositions(List<Position> listOfComboPositions) {
        this.listOfComboPositions = listOfComboPositions;
    }

    public PositionBo getPositionBoProxy() {
        return positionBoProxy;
    }

    public void setPositionBoProxy(PositionBo positionBoProxy) {
        this.positionBoProxy = positionBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KelasRuanganAction.logger = logger;
    }

    public KelasRuangan getKelasRuangan() {
        return kelasRuangan;
    }

    public void setKelasRuangan(KelasRuangan kelasRuangan) {
        this.kelasRuangan = kelasRuangan;
    }

    public KelasRuanganBo getKelasRuanganBoProxy() {
        return kelasRuanganBoProxy;
    }

    public void setKelasRuanganBoProxy(KelasRuanganBo kelasRuanganBoProxy) {
        this.kelasRuanganBoProxy = kelasRuanganBoProxy;
    }

    public List<KelasRuangan> getListOfKelasRuangan() {
        return listOfKelasRuangan;
    }

    public void setListOfKelasRuangan(List<KelasRuangan> listOfKelasRuangan) {
        this.listOfKelasRuangan = listOfKelasRuangan;
    }

    public KelasRuangan init(String kode, String flag){
        logger.info("[KelasRuanganAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KelasRuangan> listOfResult = (List<KelasRuangan>) session.getAttribute("listOfResultRuanganKelas");
//        List<Ruangan> listPasien = new ArrayList<>();

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (KelasRuangan ruangan : listOfResult){
                    if (kode.equalsIgnoreCase(ruangan.getIdKelasRuangan()) && flag.equalsIgnoreCase(ruangan.getFlag())){
                        setKelasRuangan(ruangan);
                        break;
                    }
                }
            } else {
                setKelasRuangan(new KelasRuangan());
            }
            logger.info("[KelasRuanganAction.init] end process >>>>>");
        }
        return getKelasRuangan();
    }

    @Override
    public String add() {
        logger.info("[KelasRuanganAction.add] start process");
        KelasRuangan addKelasRuangan = new KelasRuangan();
        setKelasRuangan(addKelasRuangan);
        setAdd(true);
        setAddOrEdit(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[Ruangan.add] stop process");
        return "add";
    }

    @Override
    public String edit() {
        logger.info("[RuangKelasAction.edit] start process >>>>");
        String ruanganKelasId = getId();
        String ruanganKelasFlag = getFlag();

        KelasRuangan editKelasRuangan = new KelasRuangan();

        if (ruanganKelasFlag != null){
            try{
                editKelasRuangan = init(ruanganKelasId, ruanganKelasFlag);
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = kelasRuanganBoProxy.saveErrorMessage(e.getMessage(), "kelasruanganBO.getBelajarByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[KelasRuanganAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[KelasRuanganAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editKelasRuangan != null){
                setKelasRuangan(editKelasRuangan);
            }else {
                editKelasRuangan.setFlag(ruanganKelasFlag);
                editKelasRuangan.setIdKelasRuangan(ruanganKelasId);
                setKelasRuangan(editKelasRuangan);
                addActionError("Error, Unable to find data with id = "+ ruanganKelasId);
                return "failure";
            }
        }else {
            editKelasRuangan.setIdKelasRuangan(ruanganKelasId);
            editKelasRuangan.setFlag(ruanganKelasFlag);
            setKelasRuangan(editKelasRuangan);
            addActionError("Error, Unable to find data with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PasienAction.edit] end process >>>>>");
        return "edit";
    }

    @Override
    public String delete() {
        logger.info("[KelasRuanganAction.delete] start process");

        String kelasruanganId = getId();
        String kelasruanganFlag = getFlag();

        KelasRuangan deleteRuangan = new KelasRuangan();

        if (flag != null){
            try{
                deleteRuangan = init(getId(), getFlag());
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = kelasRuanganBoProxy.saveErrorMessage(e.getMessage(), "KelasRuanganBO.getById");
                } catch (GeneralBOException e1) {
                    logger.error("[KelasRuanganAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[KelasRuanganAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteRuangan != null) {
                setKelasRuangan(deleteRuangan);
            }else {
                deleteRuangan.setIdKelasRuangan(kelasruanganId);
                deleteRuangan.setFlag(kelasruanganFlag);
                setKelasRuangan(deleteRuangan);
                addActionError("Error, Unable to find data with id = " + kelasruanganId);
                return "failure";
            }
        }else {
            deleteRuangan.setIdKelasRuangan(kelasruanganId);
            deleteRuangan.setFlag(kelasruanganFlag);
            setKelasRuangan(deleteRuangan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[RuanganAction.delete] end process <<<<<<");
        return "delete";

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
        logger.info("[RuanganAction.search] start process");

        KelasRuangan searchKelasRuangan = getKelasRuangan();
        List<KelasRuangan> listOfKelas = new ArrayList<>();

        try{
            listOfKelas= kelasRuanganBoProxy.getByCriteria(searchKelasRuangan);
        }catch (GeneralBOException e) {
            logger.error("[KelasRuanganAction.getByCriteria] Error when get by criteria kelasruangan, please inform to your admin.", e);
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultRuanganKelas");
        session.setAttribute("listOfResultRuanganKelas", listOfKelas);

        logger.info("[KelasRuanganAction.search] end process <<<");

        return "search";
    }

    public String saveAdd(){
        logger.info(("[KelasRuanganAction.saveAdd] start process"));

        try{
            KelasRuangan kelasRuangan = getKelasRuangan();
            String userLogin = CommonUtil.userLogin();
            String branchId = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            kelasRuangan.setFlag("Y");
            kelasRuangan.setAction("C");
            kelasRuangan.setCreatedDate(updateTime);
            kelasRuangan.setCreatedWho(userLogin);
            kelasRuangan.setLastUpdate(updateTime);
            kelasRuangan.setLastUpdateWho(userLogin);

            kelasRuanganBoProxy.saveAdd(kelasRuangan);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = kelasRuanganBoProxy.saveErrorMessage(e.getMessage(), "kelasRuanganBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[kelasRuanganAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[KelasAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultRuanganKelas");
        logger.info("[kelasRuanganAction.saveAdd] end process >>>>");
        return "add";
    }

    public String saveEdit(){
        logger.info("[PasienAction.saveEdit] start process >>>");
        try {

            KelasRuangan editRuangan = getKelasRuangan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editRuangan.setLastUpdateWho(userLogin);
            editRuangan.setLastUpdate(updateTime);
            editRuangan.setAction("U");
            editRuangan.setFlag("Y");

            kelasRuanganBoProxy.saveEdit(editRuangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelasRuanganBoProxy.saveErrorMessage(e.getMessage(), "RuanganBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[RuanganAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[RuanganAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[RuanganAction.saveEdit] end process <<<");
        return "edit";
    }

    public String saveDelete(){
        logger.info("[KelasRuanganAction.saveDelete] start process >>>>");

        try{
            KelasRuangan deleteRuangan = getKelasRuangan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteRuangan.setLastUpdate(updateTime);
            deleteRuangan.setLastUpdateWho(userLogin);
            deleteRuangan.setAction("U");
            deleteRuangan.setFlag("N");

            kelasRuanganBoProxy.saveDelete(deleteRuangan);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = kelasRuanganBoProxy.saveErrorMessage(e.getMessage(), "KelasRuanganBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[KelasRuanganAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[KelasRuanganAction.saveDelete] Error when editing item pasien," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }
        logger.info("[KelasRuanganAction.saveDelete] end process <<<");
        return "delete";
    }

    @Override
    public String initForm() {
        logger.info("[KelasRuanganAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultRuanganKelas");
        logger.info("[KelasRuanganAction.initForm] end process >>>");
        return "search";
    }

    public String initComboPosition() {

        Position position = new Position();
        position.setFlag("Y");
        position.setKategori("kelas_ruangan");

        List<Position> listOfPosition = new ArrayList<Position>();
        try {
            listOfPosition = positionBoProxy.getByCriteria(position);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboPositions.addAll(listOfPosition);

        return "init_combo_position";
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