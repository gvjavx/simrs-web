package com.neurix.simrs.transaksi.tindakanrawat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;

public class TindakanRawatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(TindakanRawatAction.class);
    private TindakanRawatBo tindakanRawatBoProxy;
    private TindakanRawat tindakanRawat;

    public TindakanRawatBo getTindakanRawatBoProxy() {
        return tindakanRawatBoProxy;
    }

    public void setTindakanRawatBoProxy(TindakanRawatBo tindakanRawatBoProxy) {
        this.tindakanRawatBoProxy = tindakanRawatBoProxy;
    }

    public TindakanRawat getTindakanRawat() {
        return tindakanRawat;
    }

    public void setTindakanRawat(TindakanRawat tindakanRawat) {
        this.tindakanRawat = tindakanRawat;
    }

    @Override
    public String add() {

//        logger.info("[CheckupAction.add] start process >>>");
//
//        HeaderCheckup checkup = new HeaderCheckup();
//        setHeaderCheckup(checkup);
//
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");
//
//        logger.info("[CheckupAction.add] end process <<<");

        return "init_add";
    }

    @Override
    public String edit() {

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<HeaderCheckup> listOfResult = (List) session.getAttribute("listOfResult");
//        List<HeaderDetailCheckup> listOfsearchDetailCheckup = new ArrayList();
//        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
//
//        if (id != null && !"".equalsIgnoreCase(id)) {
//
//            if (listOfResult != null) {
//
//                for (HeaderCheckup headerCheckup : listOfResult) {
//                    if (id.equalsIgnoreCase(headerCheckup.getNoCheckup())) {
//                        setHeaderCheckup(headerCheckup);
//                        detailCheckup.setNoCheckup(headerCheckup.getNoCheckup());
//                        break;
//                    }
//                }
//
//            } else {
//                setHeaderCheckup(new HeaderCheckup());
//            }
//        } else {
//            setHeaderCheckup(new HeaderCheckup());
//        }

        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {

//        logger.info("[CheckupAction.view] start process >>>");
//
//        //get data from session
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<HeaderCheckup> listOfResult = (List) session.getAttribute("listOfResult");
//        List<HeaderDetailCheckup> listOfsearchDetailCheckup = new ArrayList();
//        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
//
//        if (id != null && !"".equalsIgnoreCase(id)) {
//
//            if (listOfResult != null) {
//
//                for (HeaderCheckup headerCheckup : listOfResult) {
//                    if (id.equalsIgnoreCase(headerCheckup.getNoCheckup())) {
//                        setHeaderCheckup(headerCheckup);
//                        detailCheckup.setNoCheckup(headerCheckup.getNoCheckup());
//                        break;
//                    }
//                }
//
//            } else {
//                setHeaderCheckup(new HeaderCheckup());
//            }
//        } else {
//            setHeaderCheckup(new HeaderCheckup());
//        }
//
//        try {
//            listOfsearchDetailCheckup = checkupDetailBoProxy.getByCriteria(detailCheckup);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            logger.error("[CheckupAction.view] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
//        }
//
//        session.removeAttribute("listOfRiwayat");
//        session.setAttribute("listOfRiwayat", listOfsearchDetailCheckup);
//
//        logger.info("[CheckupAction.view] DATA YANG DI PARAM ID: "+getId());
//        logger.info("[CheckupAction.view] end process <<<");

        return "init_view";
    }

    @Override
    public String save() {
        return "init_add";
    }

    @Override
    public String search() {
//        logger.info("[CheckupAction.search] start process >>>");
//
//        HeaderCheckup headerCheckup = getHeaderCheckup();
//        List<HeaderCheckup> listOfsearchHeaderCheckup = new ArrayList();
//
//        try {
//            listOfsearchHeaderCheckup = checkupBoProxy.getByCriteria(headerCheckup);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = checkupBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
//            } catch (GeneralBOException e1) {
//                logger.error("[CheckupAction.search] Error when saving error,", e1);
//                return ERROR;
//            }
//            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
//        }
//
//        HttpSession session = ServletActionContext.getRequest().getSession();
//
//        session.removeAttribute("listOfResult");
//        session.setAttribute("listOfResult", listOfsearchHeaderCheckup);
//
//        logger.info("[CheckupAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
//        logger.info("[CheckupAction.initForm] start process >>>");
//        HttpSession session = ServletActionContext.getRequest().getSession();
//
//        session.removeAttribute("listOfResult");
//        logger.info("[CheckupAction.initForm] end process >>>");
        return "search";
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

//        logger.info("[CheckupAction.saveAdd] start process >>>");
//        try {
//            HeaderCheckup checkup = getHeaderCheckup();
//            String userLogin = CommonUtil.userLogin();
//            String userArea  = CommonUtil.userBranchLogin();
//            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//
//            String tgl_lahir = checkup.getStTglLahir();
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//            try {
//                java.util.Date date = format.parse(tgl_lahir);
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                checkup.setTglLahir(sqlDate);
//            } catch (ParseException e) {
//
//            }
//
//            checkup.setBranchId(userArea);
//            checkup.setCreatedWho(userLogin);
//            checkup.setLastUpdate(updateTime);
//            checkup.setCreatedDate(updateTime);
//            checkup.setLastUpdateWho(userLogin);
//            checkup.setAction("C");
//            checkup.setFlag("Y");
//
//            checkupBoProxy.saveAdd(checkup);
//        }catch (GeneralBOException e) {
//            Long logId = null;
//            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
//            return ERROR;
//        }
//
//
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");
//
//        logger.info("[CheckupAction.saveAdd] end process >>>");
        return "success_add";

    }

    public String getComboJenisPeriksaPasien(){
//        List<JenisPriksaPasien> lisJenisPeriksa = new ArrayList<>();
//
//        try {
//            lisJenisPeriksa = jenisPriksaPasienBoProxy.getListAllJenisPeriksa();
//        } catch (HibernateException e){
//            logger.error("[CheckupAction.getComboJenisPeriksaPasien] Error when get data for combo listOfJenisPriksaPasien", e);
//            addActionError(" Error when get data for combo listOfJenisPriksaPasien" + e.getMessage());
//        }
//
//        listOfJenisPriksaPasien.addAll(lisJenisPeriksa);
        return "init_add";
    }

    public String getComboPelayanan(){
//        List<Pelayanan> pelayananList = new ArrayList<>();
//
//        try {
//            pelayananList = pelayananBoProxy.getListAllPelayanan();
//        } catch (HibernateException e){
//            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
//            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
//        }
//
//        listOfPelayanan.addAll(pelayananList);
        return "init_add";
    }

    public String saveTindakanRawat(String idDetailCheckup, String idTindakan, String idDokter, String idPerawat, Long qty){
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(idPerawat);
            tindakanRawat.setQty(BigInteger.valueOf(qty));
            tindakanRawat.setCreatedWho(userLogin);
            tindakanRawat.setLastUpdate(updateTime);
            tindakanRawat.setCreatedDate(updateTime);
            tindakanRawat.setLastUpdateWho(userLogin);
            tindakanRawat.setAction("C");
            tindakanRawat.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

            tindakanRawatBo.saveAdd(tindakanRawat);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return "init_add";
    }
}