package com.neurix.simrs.transaksi.transaksiobat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransaksiObatAction extends BaseMasterAction {

    private static transient Logger logger = Logger.getLogger(TransaksiObatAction.class);

    private TransaksiObatDetail transaksiObatDetail;
    private TransaksiObatBo transaksiObatBoProxy;

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
        searchResep();
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[TransaksiObatAction.initForm] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultResep");
        session.removeAttribute("listOfResultObat");

        setTransaksiObatDetail(new TransaksiObatDetail());

        logger.info("[TransaksiObatAction.initForm] END <<<<<<<");
        return "search";

    }

    public String searchResep(){
        logger.info("[TransaksiObatAction.searchResep] START >>>>>>>");

        TransaksiObatDetail transaksiObatDetail = getTransaksiObatDetail();
        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if(transaksiObatDetail != null) {

            if (transaksiObatDetail.getIdPermintaanResep() != null && !"".equalsIgnoreCase(transaksiObatDetail.getIdPermintaanResep())) {
                try {
                    obatDetailList = transaksiObatBoProxy.getSearchObatTransaksiByCriteria(transaksiObatDetail);
                } catch (GeneralBOException e) {
                    logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
                    addActionError("[TransaksiObatAction.searchResep] ERROR error when get searh resep. " + e.getMessage());
                }
            }
        }
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

            // hitung total bayar
            BigInteger hitungTotalResep = hitungTotalBayar(obatDetailList);
            BigInteger hitungTotalPembelian = hitungTotalBayar(pembelianObatList);

            transaksiObatDetail.setTotalBayar(hitungTotalResep.add(hitungTotalPembelian));
            setTransaksiObatDetail(transaksiObatDetail);

            session.removeAttribute("listOfResultResep");
            session.setAttribute("listOfResultResep", obatDetailList);

            logger.info("[TransaksiObatAction.searchResep] END <<<<<<<");
            return "search";

    }

    public String pembayaran(){
        logger.info("[TransaksiObatAction.savePembayaran] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> obatResepList     = (List) session.getAttribute("listOfResultResep");
        List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

        String userLogin    = CommonUtil.userLogin();
        Timestamp time      = new Timestamp(System.currentTimeMillis());

        TransaksiObatDetail transaksiObatDetail = getTransaksiObatDetail();
        transaksiObatDetail.setLastUpdate(time);
        transaksiObatDetail.setLastUpdateWho(userLogin);
        transaksiObatDetail.setBranchId(CommonUtil.userBranchLogin());

        try {
            transaksiObatBoProxy.saveAdd(transaksiObatDetail, obatResepList, pembelianObatList);
        } catch (GeneralBOException e){
            logger.error("[TransaksiObatAction.savePembayaran] ERROR error when save pembayaran. ", e);
            addActionError("[TransaksiObatAction.savePembayaran] ERROR error when save pembayaran. "+ e.getMessage());
        }

        logger.info("[TransaksiObatAction.savePembayaran] END <<<<<<<");
        return initForm();
    }

    public String saveAddObat(String idObat, String qty){
        logger.info("[TransaksiObatAction.savePembayaran] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> obatResepList = (List) session.getAttribute("listOfResultResep");
        List<TransaksiObatDetail> pembelianObatList = (List) session.getAttribute("listOfResultObat");

        if (pembelianObatList == null){
            pembelianObatList = new ArrayList<>();
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setFlag("Y");

        List<Obat> obatList = new ArrayList<>();
        try {
            obatList = obatBo.getByCriteria(obat);
        } catch (HibernateException e){
            logger.error("[TransaksiObatAction.saveAddObat] ERROR error when get searh obat. ", e);
            addActionError("[TransaksiObatAction.saveAddObat] ERROR error when get searh obat. "+ e.getMessage());
        }

        if (obatList.size() > 0){
            Obat obatData = obatList.get(0);

            BigInteger bQty = new BigInteger(String.valueOf(qty));
            BigInteger jml  = obatData.getHarga().multiply(bQty);

            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
            transaksiObatDetail.setIdObat(obatData.getIdObat());
            transaksiObatDetail.setNamaObat(obatData.getNamaObat());
            transaksiObatDetail.setQty(bQty);
            transaksiObatDetail.setHarga(obatData.getHarga());
            transaksiObatDetail.setTotalHarga(jml);
            transaksiObatDetail.setFlag("Y");
            transaksiObatDetail.setAction("C");
            pembelianObatList.add(transaksiObatDetail);
        }

        // hitung total bayar
        BigInteger hitungTotalResep         = hitungTotalBayar(obatResepList);
        BigInteger hitungTotalPembelian     = hitungTotalBayar(pembelianObatList);
        TransaksiObatDetail newTransaksi    = new TransaksiObatDetail();

        String idResep = "";
        if (obatResepList != null && obatResepList.size() > 0)
        {
            idResep = obatResepList.get(0).getIdPermintaanResep();
        }

        newTransaksi.setIdPermintaanResep(idResep);
        newTransaksi.setTotalBayar(hitungTotalResep.add(hitungTotalPembelian));

        setTransaksiObatDetail(newTransaksi);
        session.setAttribute("listOfResultObat", pembelianObatList);
        logger.info("[TransaksiObatAction.savePembayaran] END <<<<<<<");
        return "success";
    }

    private BigInteger hitungTotalBayar(List<TransaksiObatDetail> transaksiObatDetails){

        BigInteger total = new BigInteger(String.valueOf("0"));
        if (transaksiObatDetails != null && transaksiObatDetails.size() > 0){
            for (TransaksiObatDetail trans : transaksiObatDetails){
                total = total.add(trans.getTotalHarga());
            }
        }
        return total;
    }

    public String init(){
        logger.info("[TransaksiObatAction.init] START >>>>>>>");
        logger.info("[TransaksiObatAction.init] END <<<<<<<");
        return "search";
    }

    public String resetobat(){
        logger.info("[TransaksiObatAction.resetobat] START >>>>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultObat");
        searchResep();
        logger.info("[TransaksiObatAction.resetobat] END <<<<<<<");
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


    public TransaksiObatDetail getTransaksiObatDetail() {
        return transaksiObatDetail;
    }

    public void setTransaksiObatDetail(TransaksiObatDetail transaksiObatDetail) {
        this.transaksiObatDetail = transaksiObatDetail;
    }

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }

}