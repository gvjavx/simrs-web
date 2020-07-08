package com.neurix.simrs.mobileapi;

import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.mobileapi.model.KasirMobile;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 10/06/20 10:27
 */
public class KasirController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(KasirController.class);
    private KasirMobile model = new KasirMobile();
    private Collection<KasirMobile> listOfKasirMobile;
    private KasirRawatJalanBo kasirRawatJalanBoProxy;
    private AsuransiBo asuransiBoProxy;

    private String action;

    public AsuransiBo getAsuransiBoProxy() {
        return asuransiBoProxy;
    }

    public void setAsuransiBoProxy(AsuransiBo asuransiBoProxy) {
        this.asuransiBoProxy = asuransiBoProxy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public KasirRawatJalanBo getKasirRawatJalanBoProxy() {
        return kasirRawatJalanBoProxy;
    }

    public void setKasirRawatJalanBoProxy(KasirRawatJalanBo kasirRawatJalanBoProxy) {
        this.kasirRawatJalanBoProxy = kasirRawatJalanBoProxy;
    }

    public HttpHeaders create() {
        logger.info("[KasirController.create] start process POST / <<<");

        listOfKasirMobile = new ArrayList<>();
        List<ImAkunPembayaranEntity> result = new ArrayList<>();

        try {
            result = kasirRawatJalanBoProxy.getListPembayaran();
        } catch (GeneralBOException e) {
            logger.error("[KasirController.create] Error, " + e.getMessage());
        }

        for (ImAkunPembayaranEntity item : result) {
            KasirMobile kasirMobile = new KasirMobile();
            kasirMobile.setPembayaranId(item.getPembayaranId());
            kasirMobile.setPembayaranName(item.getPembayaranName());
            kasirMobile.setCoa(item.getCoa());

            listOfKasirMobile.add(kasirMobile);
        }

        logger.info("[KasirController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }

    @Override
    public Object getModel() {
        return listOfKasirMobile != null ? listOfKasirMobile : model;
    }
}
