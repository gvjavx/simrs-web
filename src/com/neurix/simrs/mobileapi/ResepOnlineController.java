package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.model.ResepOnlineMobile;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.model.ResepOnline;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 17/06/20 10:27
 */
public class ResepOnlineController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(ResepOnlineController.class);
    private ResepOnlineMobile model = new ResepOnlineMobile();
    private Collection<ResepOnlineMobile> listOfResepMobile;
    private ResepOnlineBo resepOnlineBoProxy;

    private String action;
    private String idPembayaranOnline;
    private String idDokter;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(ResepOnlineMobile model) {
        this.model = model;
    }

    public Collection<ResepOnlineMobile> getListOfResepMobile() {
        return listOfResepMobile;
    }

    public void setListOfResepMobile(Collection<ResepOnlineMobile> listOfResepMobile) {
        this.listOfResepMobile = listOfResepMobile;
    }

    public ResepOnlineBo getResepOnlineBoProxy() {
        return resepOnlineBoProxy;
    }

    public void setResepOnlineBoProxy(ResepOnlineBo resepOnlineBoProxy) {
        this.resepOnlineBoProxy = resepOnlineBoProxy;
    }

    public String getIdPembayaranOnline() {
        return idPembayaranOnline;
    }

    public void setIdPembayaranOnline(String idPembayaranOnline) {
        this.idPembayaranOnline = idPembayaranOnline;
    }

    @Override
    public Object getModel() {
        return listOfResepMobile != null ? listOfResepMobile : model;
    }

    public HttpHeaders create() {
        logger.info("[ResepOnlineController.create] start process POST / <<<");

        if (action.equalsIgnoreCase("getResepOnline")) {

            List<ResepOnline> result = new ArrayList<>();
            listOfResepMobile = new ArrayList<>();

            ResepOnline bean = new ResepOnline();
            bean.setIdTransaksiOnline(idPembayaranOnline);
            bean.setIdDokter(idDokter);

            try {
               result = resepOnlineBoProxy.getByCriteria(bean);
            }catch (GeneralBOException e) {
                logger.error("[ResepOnlineController.create] Error, " + e.getMessage());
            }

            String total="";
            BigDecimal totalDec = new BigDecimal("0");
            for (ResepOnline item : result) {
               totalDec = totalDec.add(item.getSubTotal());
            }

            total = CommonUtil.numbericFormat(totalDec, "###,###");

            for (ResepOnline item : result) {
                ResepOnlineMobile resepOnlineMobile = new ResepOnlineMobile();
                resepOnlineMobile.setId(item.getId());
                resepOnlineMobile.setIdDokter(item.getIdDokter());
                resepOnlineMobile.setIdObat(item.getIdObat());
                resepOnlineMobile.setIdPelayanan(item.getIdPelayanan());
                resepOnlineMobile.setIdTransaksiOnline(item.getIdTransaksiOnline());
                resepOnlineMobile.setHarga(CommonUtil.numbericFormat(item.getHarga(), "###,###"));
                resepOnlineMobile.setQty(item.getQty().toString());
                resepOnlineMobile.setSubTotal(CommonUtil.numbericFormat(item.getSubTotal(), "###,###"));
                resepOnlineMobile.setTtdDokter(item.getTtdDokter());
                resepOnlineMobile.setFlag(item.getFlag());
                resepOnlineMobile.setAction(item.getAction());
                resepOnlineMobile.setCreatedDate(item.getCreatedDate().toLocaleString());
                resepOnlineMobile.setCreatedWho(item.getCreatedWho());
                resepOnlineMobile.setLastUpdate(item.getLastUpdate().toLocaleString());
                resepOnlineMobile.setLastUpdateWho(item.getLastUpdateWho());
                resepOnlineMobile.setTotal(total);

                listOfResepMobile.add(resepOnlineMobile);
            }
        }

        logger.info("[ResepOnlineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
