package com.neurix.simrs.transaksi.permintaanresep.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.permintaanresep.model.ResepObat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.log4j.Logger;
import org.directwebremoting.json.types.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.persistence.Id;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanResepAction extends BaseMasterAction{
    private static transient Logger logger = Logger.getLogger(PermintaanResepAction.class);

    private PermintaanResep permintaanResep;
    private PermintaanResepBo permintaanResepBoProxy;
    private TransaksiObatBo transaksiObatBoProxy;

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }
    public PermintaanResep getPermintaanResep() {
        return permintaanResep;
    }

    public void setPermintaanResep(PermintaanResep permintaanResep) {
        this.permintaanResep = permintaanResep;
    }

    public PermintaanResepBo getPermintaanResepBoProxy() {
        return permintaanResepBoProxy;
    }

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }

    public String saveResepPasien(String idDetailCheckup, String idPelayanan, String idDokter, String idPasien, String resep, String tujuanApotek){
        logger.info("[PermintaanResepAction.saveResepPasien] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            PermintaanResep permintaanResep = new PermintaanResep();
            permintaanResep.setIdDetailCheckup(idDetailCheckup);
            permintaanResep.setIdDokter(idDokter);
            permintaanResep.setIdPelayanan(idPelayanan);
            permintaanResep.setIdPasien(idPasien);
            permintaanResep.setCreatedWho(userLogin);
            permintaanResep.setLastUpdate(updateTime);
            permintaanResep.setCreatedDate(updateTime);
            permintaanResep.setLastUpdateWho(userLogin);
            permintaanResep.setAction("U");
            permintaanResep.setFlag("Y");
            permintaanResep.setBranchId(userArea);
            permintaanResep.setTujuanPelayanan(tujuanApotek);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

            try {
                List<TransaksiObatDetail> detailList = new ArrayList<>();

                if(resep != null && !"".equalsIgnoreCase(resep)){
                    TransaksiObatDetail detail;
                    JSONArray json = new JSONArray(resep);
                    for (int i=0; i < json.length(); i++){

                        JSONObject obj = json.getJSONObject(i);
                        detail = new TransaksiObatDetail();

                        detail.setIdObat(obj.getString("ID"));
                        detail.setQty(new BigInteger(obj.getString("Qty")));
                        detail.setKeterangan(obj.getString("Keterangan"));
                        detailList.add(detail);
                    }
                }

                permintaanResepBo.saveAdd(permintaanResep, detailList);

            }catch (JSONException e){
                logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanResepAction.saveResepPasien] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public List<PermintaanResep> listResepPasien(String IdChekupDetail){

        logger.info("[PermintaanResepAction.listTindakanRawat] start process >>>");
        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdDetailCheckup(IdChekupDetail);
        permintaanResep.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        if(!"".equalsIgnoreCase(IdChekupDetail)){
            try {
                permintaanResepList = permintaanResepBo.getByCriteria(permintaanResep);
            }catch (GeneralBOException e){
                logger.error("[PermintaanResepAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanResepAction.saveTindakanRawat] start process >>>");
            return permintaanResepList;

        }else{
            return null;
        }
    }

    public List<TransaksiObatDetail> listDetail(String idApprovalObat){

        logger.info("[PermintaanResepAction.listDetail] start process >>>");
        List<TransaksiObatDetail> transaksiObatDetailList = new ArrayList<>();
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdApprovalObat(idApprovalObat);
        transaksiObatDetail.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        if(!"".equalsIgnoreCase(idApprovalObat)){
            try {
                transaksiObatDetailList = transaksiObatBo.getByCriteria(transaksiObatDetail);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.listDetail] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanResepAction.listDetail] start process >>>");
            return transaksiObatDetailList;

        }else{
            return null;
        }
    }

    public String saveEditDetail(String idTransaksi, String idObat, BigInteger qty, String ket){
        logger.info("[PermintaanResepAction.saveDetailResep] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();

            transaksiObatDetail.setIdTransaksiObatDetail(idTransaksi);
            transaksiObatDetail.setIdObat(idObat);
            transaksiObatDetail.setQty(qty);
            transaksiObatDetail.setKeterangan(ket);
            transaksiObatDetail.setLastUpdate(updateTime);
            transaksiObatDetail.setLastUpdateWho(userLogin);
            transaksiObatDetail.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

            transaksiObatBo.saveEditDetail(transaksiObatDetail);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanResepAction.saveDetailResep] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
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
}
