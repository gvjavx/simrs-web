package com.neurix.simrs.transaksi.tindakanrawat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.KlaimDetailRequest;
import com.neurix.simrs.bpjs.eklaim.model.KlaimDetailResponse;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TindakanRawatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(TindakanRawatAction.class);
    private TindakanRawatBo tindakanRawatBoProxy;
    private TindakanRawat tindakanRawat;
    private TindakanBo tindakanBoProxy;
    private CheckupBo checkupBoProxy;
    private EklaimBo eklaimBoProxy;
    private DokterBo dokterBoProxy;

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public void setEklaimBoProxy(EklaimBo eklaimBoProxy) {
        this.eklaimBoProxy = eklaimBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TindakanRawatAction.logger = logger;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

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
        return "init_add";
    }

    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return "init_view";
    }

    @Override
    public String save() {
        return "init_add";
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
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
        return "success_add";
    }

    public String getComboJenisPeriksaPasien(){
        return "init_add";
    }

    public String getComboPelayanan(){
        return "init_add";
    }

    public CrudResponse saveTindakanRawat(String idDetailCheckup, String idTindakan, String idDokter, String tipeRawat, BigInteger qty, String jenisTransaksi, String idPelayanan, String idRuangan){
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");

        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);
            if(!"".equalsIgnoreCase(idRuangan) && idRuangan != null){
                tindakanRawat.setIdRuangan(idRuangan);
            }

            List<Tindakan> tindakanList = new ArrayList<>();
            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan(idTindakan);
            Tindakan tindakanResult = new Tindakan();
            try {
                tindakanList = tindakanBo.getDataTindakan(tindakan);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.saveTindakanRawat] Error when search tarif dan decs tindakan by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (tindakanList.size() > 0){
                tindakanResult = tindakanList.get(0);
            }

            RekananOps ops = new RekananOps();
            if("rekanan".equalsIgnoreCase(jenisTransaksi)){
                try {
                    ops = rekananOpsBo.getDetailRekananOpsByDetail(idDetailCheckup, userArea);
                }catch (GeneralBOException e){
                    logger.error("Error, "+e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Tidak dapat menemukan id detail checkup");
                    return response;
                }
            }

            BigInteger tarifBpjs = tindakanResult.getTarifBpjs();
            BigInteger tarifNormal = tindakanResult.getTarif();
            if(tindakanResult.getDiskon() != null && !"".equalsIgnoreCase(tindakanResult.getDiskon().toString()) && tindakanResult.getDiskon().intValue() > 0){
                BigDecimal diskonTarif = (new BigDecimal(100).subtract(tindakanResult.getDiskon())).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal hasilTarifBpjs = new BigDecimal(tindakanResult.getTarifBpjs()).multiply(diskonTarif);
                BigDecimal hasilTarifNormal = new BigDecimal(tindakanResult.getTarif()).multiply(diskonTarif);
                tarifBpjs = hasilTarifBpjs.toBigInteger();
                tarifNormal = hasilTarifNormal.toBigInteger();
            }

            if ("bpjs".equalsIgnoreCase(jenisTransaksi)){
                tindakanRawat.setTarif(tarifBpjs);
            }else if("rekanan".equalsIgnoreCase(jenisTransaksi)){
                if("Y".equalsIgnoreCase(ops.getIsBpjs())){
                    if(ops.getDiskon() != null){
                        BigDecimal hasil = new BigDecimal(tarifNormal).multiply(ops.getDiskon());
                        tindakanRawat.setTarif(hasil.toBigInteger());
                    }else{
                        tindakanRawat.setTarif(tarifBpjs);
                    }
                }else{
                    if(ops.getDiskon() != null){
                        BigDecimal hasil = new BigDecimal(tarifNormal).multiply(ops.getDiskon());
                        tindakanRawat.setTarif(hasil.toBigInteger());
                    }else{
                        tindakanRawat.setTarif(tarifNormal);
                    }
                }
            }else {
                tindakanRawat.setTarif(tarifNormal);
            }

            tindakanRawat.setNamaTindakan(tindakanResult.getTindakan());
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(userLogin);
            tindakanRawat.setQty(qty);
            tindakanRawat.setTarifTotal(tindakanRawat.getQty().multiply(tindakanRawat.getTarif()));
            tindakanRawat.setCreatedWho(userLogin);
            tindakanRawat.setLastUpdate(updateTime);
            tindakanRawat.setCreatedDate(updateTime);
            tindakanRawat.setLastUpdateWho(userLogin);
            tindakanRawat.setAction("C");
            tindakanRawat.setFlag("Y");
            tindakanRawat.setIdPelayanan(idPelayanan);

            response = tindakanRawatBo.saveAdd(tindakanRawat);

        }catch (GeneralBOException e) {
            logger.error(e.getMessage());
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }
        return response;
    }

    public List<TindakanRawat> listTindakanRawat(String idDetailCheckup){
        logger.info("[TindakanRawatAction.listTindakanRawat] start process >>>");
        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
        TindakanRawat tindakanRawat = new TindakanRawat();
        tindakanRawat.setIdDetailCheckup(idDetailCheckup);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

        if(!"".equalsIgnoreCase(idDetailCheckup)){
            try {
                tindakanRawatList = tindakanRawatBo.getByCriteria(tindakanRawat);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
            return tindakanRawatList;

        }else{
            return null;
        }
    }

    public CrudResponse editTindakanRawat(String idTindakanRawat, String idDetailCheckup, String idTindakan, String idDokter, String tipeRawat, BigInteger qty, String jenisTransaksi, String idPelayanan){
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdTindakanRawat(idTindakanRawat);
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);

            List<Tindakan> tindakanList = new ArrayList<>();
            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan(idTindakan);
            Tindakan tindakanResult = new Tindakan();

            try {
                tindakanList = tindakanBo.getDataTindakan(tindakan);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.saveTindakanRawat] Error when search tarif dan decs tindakan by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            if (tindakanList.size() > 0){
                tindakanResult = tindakanList.get(0);
            }

            RekananOps ops = new RekananOps();
            if("rekanan".equalsIgnoreCase(jenisTransaksi)){
                try {
                    ops = rekananOpsBo.getDetailRekananOpsByDetail(idDetailCheckup, userArea);
                }catch (GeneralBOException e){
                    logger.error("Error, "+e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Tidak dapat menemukan id detail checkup");
                    return response;
                }
            }

            BigInteger tarifBpjs = tindakanResult.getTarifBpjs();
            BigInteger tarifNormal = tindakanResult.getTarif();
            if(tindakanResult.getDiskon() != null && !"".equalsIgnoreCase(tindakanResult.getDiskon().toString()) && tindakanResult.getDiskon().intValue() > 0){
                BigDecimal diskonTarif = (new BigDecimal(100).subtract(tindakanResult.getDiskon())).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal hasilTarifBpjs = new BigDecimal(tindakanResult.getTarifBpjs()).multiply(diskonTarif);
                BigDecimal hasilTarifNormal = new BigDecimal(tindakanResult.getTarif()).multiply(diskonTarif);
                tarifBpjs = hasilTarifBpjs.toBigInteger();
                tarifNormal = hasilTarifNormal.toBigInteger();
            }

            if ("bpjs".equalsIgnoreCase(jenisTransaksi)){
                tindakanRawat.setTarif(tarifBpjs);
            }else if("rekanan".equalsIgnoreCase(jenisTransaksi)){
                if("Y".equalsIgnoreCase(ops.getIsBpjs())){
                    if(ops.getDiskon() != null){
                        BigDecimal hasil = new BigDecimal(tarifNormal).multiply(ops.getDiskon());
                        tindakanRawat.setTarif(hasil.toBigInteger());
                    }else{
                        tindakanRawat.setTarif(tarifBpjs);
                    }
                }else{
                    if(ops.getDiskon() != null){
                        BigDecimal hasil = new BigDecimal(tarifNormal).multiply(ops.getDiskon());
                        tindakanRawat.setTarif(hasil.toBigInteger());
                    }else{
                        tindakanRawat.setTarif(tarifNormal);
                    }
                }
            }else {
                tindakanRawat.setTarif(tarifNormal);
            }

            tindakanRawat.setNamaTindakan(tindakanResult.getTindakan());
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(userLogin);
            tindakanRawat.setQty(qty);
            tindakanRawat.setTarifTotal(tindakanRawat.getQty().multiply(tindakanRawat.getTarif()));
            tindakanRawat.setLastUpdate(updateTime);
            tindakanRawat.setLastUpdateWho(userLogin);
            tindakanRawat.setAction("U");
            tindakanRawat.setIdPelayanan(idPelayanan);

            response = tindakanRawatBo.saveEdit(tindakanRawat);

        }catch (GeneralBOException e) {
            logger.error(e.getMessage());
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }
        return response;
    }

    public List<TindakanRawat> getListTindakanRawat(String noCheckup){

        logger.info("[TindakanRawatAction.getListTindakanRawat] start process >>>");
        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

        if(!"".equalsIgnoreCase(noCheckup) && noCheckup != null){
            try {
                tindakanRawatList = tindakanRawatBo.getListTindakanRawat(noCheckup);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.getListTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            logger.info("[TindakanRawatAction.getListTindakanRawat] start process >>>");
            return tindakanRawatList;

        }
        return  tindakanRawatList;
    }
}