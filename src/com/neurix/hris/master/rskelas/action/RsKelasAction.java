package com.neurix.hris.master.rskelas.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golongan.bo.GolonganBo;
import com.neurix.hris.master.golongan.model.Golongan;
import com.neurix.hris.master.kelompokPosition.bo.KelompokPositionBo;
import com.neurix.hris.master.kelompokPosition.model.KelompokPosition;
import com.neurix.hris.master.rskelas.bo.RsKelasBo;
import com.neurix.hris.master.rskelas.model.RsKelas;
import com.neurix.hris.master.rsKerjasama.bo.RsKerjasamaBo;
import com.neurix.hris.master.rsKerjasama.model.RsKerjasama;
import com.neurix.hris.master.tipelibur.model.TipeLibur;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class RsKelasAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(RsKelasAction.class);

    private RsKelas rsKelas;
    private RsKelasBo rsKelasBoProxy;
    private RsKerjasamaBo rsKerjasamaBoProxy;
    private GolonganBo golonganBoProxy;
    private KelompokPositionBo kelompokPositionBoProxy;

    private List<RsKerjasama> listOfComboRsKerjasama = new ArrayList<RsKerjasama>();
    private List<Golongan> listOfComboGolongan = new ArrayList<Golongan>();
    private List<KelompokPosition> listOfComboKelompokPosition = new ArrayList<KelompokPosition>();

    public List<RsKerjasama> getListOfComboRsKerjasama() {
        return listOfComboRsKerjasama;
    }

    public void setListOfComboRsKerjasama(List<RsKerjasama> listOfComboRsKerjasama) {
        this.listOfComboRsKerjasama = listOfComboRsKerjasama;
    }

    public List<Golongan> getListOfComboGolongan() {
        return listOfComboGolongan;
    }

    public void setListOfComboGolongan(List<Golongan> listOfComboGolongan) {
        this.listOfComboGolongan = listOfComboGolongan;
    }

    public List<KelompokPosition> getListOfComboKelompokPosition() {
        return listOfComboKelompokPosition;
    }

    public void setListOfComboKelompokPosition(List<KelompokPosition> listOfComboKelompokPosition) {
        this.listOfComboKelompokPosition = listOfComboKelompokPosition;
    }

    public RsKerjasamaBo getRsKerjasamaBoProxy() {
        return rsKerjasamaBoProxy;
    }

    public void setRsKerjasamaBoProxy(RsKerjasamaBo rsKerjasamaBoProxy) {
        this.rsKerjasamaBoProxy = rsKerjasamaBoProxy;
    }

    public GolonganBo getGolonganBoProxy() {
        return golonganBoProxy;
    }

    public void setGolonganBoProxy(GolonganBo golonganBoProxy) {
        this.golonganBoProxy = golonganBoProxy;
    }

    public KelompokPositionBo getKelompokPositionBoProxy() {
        return kelompokPositionBoProxy;
    }

    public void setKelompokPositionBoProxy(KelompokPositionBo kelompokPositionBoProxy) {
        this.kelompokPositionBoProxy = kelompokPositionBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RsKelasAction.logger = logger;
    }

    public RsKelas getRsKelas() {
        return rsKelas;
    }

    public void setRsKelas(RsKelas rsKelas) {
        this.rsKelas = rsKelas;
    }

    public RsKelasBo getRsKelasBoProxy() {
        return rsKelasBoProxy;
    }

    public void setRsKelasBoProxy(RsKelasBo rsKelasBoProxy) {
        this.rsKelasBoProxy = rsKelasBoProxy;
    }

    public RsKelas init(String kode, String flag){
        logger.info("[TipePegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RsKelas> listOfResult = (List<RsKelas>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (RsKelas tipeLibur: listOfResult) {
                    if(kode.equalsIgnoreCase(tipeLibur.getRsKelasId()) && flag.equalsIgnoreCase(tipeLibur.getFlag())){
                        setRsKelas(tipeLibur);
                        break;
                    }
                }
            } else {
                setRsKelas(new RsKelas());
            }

            logger.info("[TipePegawaiAction.init] end process >>>");
        }
        return getRsKelas();
    }

    @Override
    public String add() {
        logger.info("[TipePegawaiAction.add] start process >>>");
        RsKelas add = new RsKelas();
        setRsKelas(add);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipePegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RsKelas rsKelas = new RsKelas();

        if(itemFlag != null){
            try {
                rsKelas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rsKelasBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(rsKelas != null) {
                setRsKelas(rsKelas);
            } else {
                rsKelas.setFlag(itemFlag);
                rsKelas.setRsKelasId(itemId);
                setRsKelas(rsKelas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            rsKelas.setRsKelasId(itemId);
            rsKelas.setFlag(getFlag());
            setRsKelas(rsKelas);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TipePegawaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {

        logger.info("[TipePegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RsKelas rsKelas = new RsKelas();

        if(itemFlag != null){
            try {
                rsKelas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rsKelasBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(rsKelas != null) {
                setRsKelas(rsKelas);
            } else {
                rsKelas.setFlag(itemFlag);
                rsKelas.setRsKelasId(itemId);
                setRsKelas(rsKelas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            rsKelas.setRsKelasId(itemId);
            rsKelas.setFlag(getFlag());
            setRsKelas(rsKelas);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[TipePegawaiAction.saveAdd] start process >>>");

        try {
            RsKelas rsKelas = getRsKelas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rsKelas.setCreatedWho(userLogin);
            rsKelas.setLastUpdate(updateTime);
            rsKelas.setCreatedDate(updateTime);
            rsKelas.setLastUpdateWho(userLogin);
            rsKelas.setAction("C");
            rsKelas.setFlag("Y");


            rsKelasBoProxy.saveAdd(rsKelas);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rsKelasBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[TipePegawaiAction.saveEdit] start process >>>");

        try {
            RsKelas rsKelas = getRsKelas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rsKelas.setCreatedWho(rsKelas.getCreatedWho());
            rsKelas.setLastUpdate(updateTime);
            rsKelas.setCreatedDate(rsKelas.getCreatedDate());
            rsKelas.setLastUpdateWho(userLogin);
            rsKelas.setAction("U");
            rsKelas.setFlag("Y");


            rsKelasBoProxy.saveEdit(rsKelas);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rsKelasBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[TipePegawaiAction.saveDelete] start process >>>");

        try {
            RsKelas rsKelas = getRsKelas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rsKelas.setCreatedWho(rsKelas.getCreatedWho());
            rsKelas.setLastUpdate(updateTime);
            rsKelas.setCreatedDate(rsKelas.getCreatedDate());
            rsKelas.setLastUpdateWho(userLogin);
            rsKelas.setAction("U");
            rsKelas.setFlag("N");


            rsKelasBoProxy.saveEdit(rsKelas);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rsKelasBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[TipePegawaiAction.search] start process >>>");

        RsKelas search = getRsKelas();
        List<RsKelas> listOfSearch = new ArrayList();

        try {
            listOfSearch = rsKelasBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rsKelasBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearch);

        logger.info("[TipePegawaiAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipePegawaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[TipePegawaiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initComboRsKerjasama(){
        logger.info("[RsKelasAction.initComboRsKerjasama] start process >>>");

        RsKerjasama rsKerjasama = new RsKerjasama();
        rsKerjasama.setFlag("Y");

        List<RsKerjasama> rsKerjasamaList = new ArrayList<RsKerjasama>();
        try {
            rsKerjasamaList = rsKerjasamaBoProxy.getByCriteria(rsKerjasama);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rsKerjasamaBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RsKelasAction.initComboRsKerjasama] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RsKelasAction.initComboRsKerjasama] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        listOfComboRsKerjasama.addAll(rsKerjasamaList);
        logger.info("[RsKelasAction.initComboRsKerjasama] end process >>>");
        return "init_add";
    }

    public String initComboGolongan(){
        logger.info("[RsKelasAction.initComboGolongan] start process >>>");

        Golongan golongan = new Golongan();
        golongan.setFlag("Y");

        List<Golongan> golonganList = new ArrayList<Golongan>();
        try {
            golonganList = golonganBoProxy.getByCriteria(golongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "golonganBoProxy.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RsKelasAction.initComboGolongan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RsKelasAction.initComboGolongan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        listOfComboGolongan.addAll(golonganList);
        logger.info("[RsKelasAction.initComboGolongan] end process >>>");
        return "init_add";
    }

    public String initComboKelompok(){
        logger.info("[RsKelasAction.initComboKelompok] start process >>>");

        KelompokPosition kelompokPosition = new KelompokPosition();
        kelompokPosition.setFlag("Y");

        List<KelompokPosition> kelompokPositions = new ArrayList<KelompokPosition>();
        try {
            kelompokPositions = kelompokPositionBoProxy.getByCriteria(kelompokPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rsKerjasamaBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RsKelasAction.initComboKelompok] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RsKelasAction.initComboKelompok] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        listOfComboKelompokPosition.addAll(kelompokPositions);
        logger.info("[RsKelasAction.initComboKelompok] end process >>>");
        return "init_add";
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
