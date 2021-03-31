package com.neurix.simrs.transaksi.makananpendamping.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.makananpendamping.bo.DetailPendampingMakananBo;
import com.neurix.simrs.transaksi.makananpendamping.bo.HeaderPendampingMakananBo;
import com.neurix.simrs.transaksi.makananpendamping.model.DetailPendampingMakanan;
import com.neurix.simrs.transaksi.makananpendamping.model.HeaderPendampingMakanan;
import com.neurix.simrs.transaksi.makananpendamping.model.ItSimrsDetailPendampingMakananEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PendampingMakananAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(PendampingMakananAction.class);
    private HeaderPendampingMakananBo headerPendampingMakananBoProxy;
    private HeaderPendampingMakanan headerPendampingMakanan;
    private String id;
    private String nama;
    private String keterangan;

    public CrudResponse savePendampingMakanan(String data) {
        logger.info("[HeaderPendampingMakananAction.savePendampingMakanan] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderPendampingMakananBo headerPendampingMakananBo = (HeaderPendampingMakananBo) ctx.getBean("headerPendampingMakananBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        if(data != null && !"".equalsIgnoreCase(data)){
            try {
                JSONObject object = new JSONObject(data);
                HeaderPendampingMakanan headerPendampingMakanan = new HeaderPendampingMakanan();
                if(object != null){
                    headerPendampingMakanan.setIdDetailCheckup(object.getString("id_detail_checkup"));
                    headerPendampingMakanan.setIdRuangan(object.getString("id_ruangan"));
                    headerPendampingMakanan.setFlag("Y");
                    headerPendampingMakanan.setAction("C");
                    headerPendampingMakanan.setCreatedDate(updateTime);
                    headerPendampingMakanan.setCreatedWho(userLogin);
                    headerPendampingMakanan.setLastUpdate(updateTime);
                    headerPendampingMakanan.setLastUpdateWho(userLogin);
                    if(object.getString("detail") != null && !"".equalsIgnoreCase(object.getString("detail"))){
                        JSONArray json = new JSONArray(object.getString("detail"));
                        if (json != null) {
                            List<ItSimrsDetailPendampingMakananEntity> detailPendampingMakananList = new ArrayList<>();
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject obj = json.getJSONObject(i);
                                ItSimrsDetailPendampingMakananEntity detailPendampingMakanan = new ItSimrsDetailPendampingMakananEntity();
                                detailPendampingMakanan.setNama(obj.getString("nama"));
                                detailPendampingMakanan.setQty(Integer.valueOf(obj.getString("qty")));
                                detailPendampingMakanan.setKeterangan(obj.getString("keterangan"));
                                detailPendampingMakanan.setFlag("Y");
                                detailPendampingMakanan.setAction("C");
                                detailPendampingMakanan.setCreatedWho(userLogin);
                                detailPendampingMakanan.setCreatedDate(updateTime);
                                detailPendampingMakanan.setLastUpdate(updateTime);
                                detailPendampingMakanan.setLastUpdateWho(userLogin);
                                detailPendampingMakananList.add(detailPendampingMakanan);
                            }
                            if (detailPendampingMakananList.size() > 0) {
                                headerPendampingMakanan.setDetailPendampingMakananList(detailPendampingMakananList);
                            }
                            headerPendampingMakananBo.saveAdd(headerPendampingMakanan);
                            response.setStatus("success");
                            response.setMsg("OK");
                        } else {
                            response.setStatus("error");
                            response.setMsg("Data json tidak ada...!");
                        }
                    }
                }else{
                    response.setStatus("success");
                    response.setMsg("Data tidak ada");
                }
            } catch (Exception e) {
                response.setStatus("error");
                response.setMsg("Error when parse JSON Objact, " + e.getMessage());
            }
        }else{
            response.setStatus("success");
            response.setMsg("Data tidak ada");
        }
        logger.info("[HeaderPendampingMakananAction.savePendampingMakanan] end process >>>");
        return response;
    }

    public List<HeaderPendampingMakanan> listHeaderPendampingMakanan(String idDetailCheckup) {
        logger.info("[HeaderPendampingMakananAction.listHeaderPendampingMakanan] start process >>>");
        List<HeaderPendampingMakanan> headerPendampingMakananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderPendampingMakananBo headerPendampingMakananBo = (HeaderPendampingMakananBo) ctx.getBean("headerPendampingMakananBoProxy");

        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)){
            HeaderPendampingMakanan HeaderPendampingMakanan = new HeaderPendampingMakanan();
            HeaderPendampingMakanan.setIdDetailCheckup(idDetailCheckup);
            try {
                headerPendampingMakananList = headerPendampingMakananBo.getByCriteria(HeaderPendampingMakanan);
            } catch (GeneralBOException e) {
                logger.info("[HeaderPendampingMakananAction.listHeaderPendampingMakanan] error when search list of pendamping gizi, " + e.getMessage());
            }
        }

        logger.info("[HeaderPendampingMakananAction.listHeaderPendampingMakanan] end process >>>");
        return headerPendampingMakananList;
    }

    public List<DetailPendampingMakanan> listDetailPendampingMakanan(String idHead) {
        logger.info("[HeaderPendampingMakananAction.listHeaderPendampingMakanan] start process >>>");
        List<DetailPendampingMakanan> detailPendampingMakananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailPendampingMakananBo detailPendampingMakananBo = (DetailPendampingMakananBo) ctx.getBean("detailPendampingMakananBoProxy");

        if(idHead != null && !"".equalsIgnoreCase(idHead)){
            DetailPendampingMakanan detailPendampingMakanan = new DetailPendampingMakanan();
            detailPendampingMakanan.setIdHeaderPendampingMakanan(idHead);
            try {
                detailPendampingMakananList = detailPendampingMakananBo.getByCriteria(detailPendampingMakanan);
            } catch (GeneralBOException e) {
                logger.info("[HeaderPendampingMakananAction.listHeaderPendampingMakanan] error when search list of pendamping gizi, " + e.getMessage());
            }
        }

        logger.info("[HeaderPendampingMakananAction.listHeaderPendampingMakanan] end process >>>");
        return detailPendampingMakananList;
    }

    public CrudResponse editDetailPendampingMakanan(String id, String nama, String qty, String keterangan) {
        logger.info("[HeaderPendampingMakananAction.editDetailPendampingMakanan] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailPendampingMakananBo detailPendampingMakananBo = (DetailPendampingMakananBo) ctx.getBean("detailPendampingMakananBoProxy");
        try {
            DetailPendampingMakanan detailPendampingMakanan = new DetailPendampingMakanan();
            detailPendampingMakanan.setIdDetailPendampingMakanan(id);
            detailPendampingMakanan.setNama(nama);
            detailPendampingMakanan.setKeterangan(keterangan);
            detailPendampingMakanan.setQty(Integer.valueOf(qty));
            detailPendampingMakanan.setLastUpdate(updateTime);
            detailPendampingMakanan.setLastUpdateWho(userLogin);
            detailPendampingMakanan.setAction("U");
            detailPendampingMakanan.setFlag("Y");
            detailPendampingMakananBo.saveEdit(detailPendampingMakanan);
            response.setStatus("success");
            response.setMsg("OK");
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Foun Error when save edit order gizi :" + e.getMessage());
            logger.error("[HeaderPendampingMakananAction.editDetailPendampingMakanan] Error when edit item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
        }
        logger.info("[HeaderPendampingMakananAction.editDetailPendampingMakanan] end process >>>");
        return response;
    }

    public CrudResponse verifDetailPendampingMakanan(String data) {
        logger.info("[HeaderPendampingMakananAction.editDetailPendampingMakanan] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailPendampingMakananBo detailPendampingMakananBo = (DetailPendampingMakananBo) ctx.getBean("detailPendampingMakananBoProxy");

        if(data != null && !"".equalsIgnoreCase(data)){
            try {
                JSONArray json = new JSONArray(data);
                List<DetailPendampingMakanan> detailPendampingMakananList = new ArrayList<>();
                if(json != null){
                    for (int i = 0; i<json.length(); i++){
                        JSONObject object = json.getJSONObject(i);
                        DetailPendampingMakanan detail = new DetailPendampingMakanan();
                        detail.setIdHeaderPendampingMakanan(object.getString("id_header_makanan_pendamping"));
                        detail.setIdDetailPendampingMakanan(object.getString("id_detail_makanan_pendamping"));
                        detail.setTarif(new BigDecimal(object.getString("tarif")));
                        detail.setLastUpdate(updateTime);
                        detail.setLastUpdateWho(userLogin);
                        detail.setAction("U");
                        detailPendampingMakananList.add(detail);
                    }
                    detailPendampingMakananBo.updateDetail(detailPendampingMakananList);
                    response.setStatus("success");
                    response.setMsg("OK");
                }else{
                    response.setStatus("error");
                    response.setMsg("Foun Error when save edit order makanan pendamping");
                }
            } catch (Exception e) {
                response.setStatus("error");
                response.setMsg("Foun Error when save edit order makanan pendamping :" + e.getMessage());
                logger.error("[HeaderPendampingMakananAction.editDetailPendampingMakanan] Error when edit item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
            }
        }else{
            response.setStatus("error");
            response.setMsg("Foun Error when save edit order makanan pendamping");
        }

        logger.info("[HeaderPendampingMakananAction.editDetailPendampingMakanan] end process >>>");
        return response;
    }

    @Override
    public String search() {
        logger.info("[HeaderPendampingMakananAction.search] start process >>>");
        HeaderPendampingMakanan search = getHeaderPendampingMakanan();
        List<HeaderPendampingMakanan> headerPendampingMakananList = new ArrayList<>();
        try {
            headerPendampingMakananList = headerPendampingMakananBoProxy.getListSearch(search);
        }catch (Exception e){
            logger.info("[HeaderPendampingMakananAction.search] error when search list of pendamping gizi, " + e.getMessage());
        }
        setHeaderPendampingMakanan(search);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", headerPendampingMakananList);
        logger.info("[HeaderPendampingMakananAction.search] end process >>>");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[HeaderPendampingMakananAction.initForm] start process >>>");
        setHeaderPendampingMakanan(new HeaderPendampingMakanan());
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[HeaderPendampingMakananAction.initForm] end process >>>");
        return "search";
    }

    public String printBarcodeMakanan(){
        reportParams.put("id", getId());
        reportParams.put("nama",getNama());
        reportParams.put("keterangan",getKeterangan());
        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[HeaderPendampingMakananAction.printBarcodeMakanan] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            return "search";
        }
        return "print_barcode_makanan";
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHeaderPendampingMakananBoProxy(HeaderPendampingMakananBo headerPendampingMakananBoProxy) {
        this.headerPendampingMakananBoProxy = headerPendampingMakananBoProxy;
    }

    public HeaderPendampingMakanan getHeaderPendampingMakanan() {
        return headerPendampingMakanan;
    }

    public void setHeaderPendampingMakanan(HeaderPendampingMakanan headerPendampingMakanan) {
        this.headerPendampingMakanan = headerPendampingMakanan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}