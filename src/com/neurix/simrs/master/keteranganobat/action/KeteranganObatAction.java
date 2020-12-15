package com.neurix.simrs.master.keteranganobat.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class KeteranganObatAction {
    private static transient Logger logger = Logger.getLogger(KeteranganObatAction.class);
    private KeteranganObatBo keteranganObatBoProxy;

    public void setKeteranganObatBoProxy(KeteranganObatBo keteranganObatBoProxy) {
        this.keteranganObatBoProxy = keteranganObatBoProxy;
    }

    public List<KeteranganObat> getListKeteranganObatBySubJenisObatAndParam(String idSubJenis, String idParam){
        logger.info("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] START >>> ");

        KeteranganObat keteranganObat = new KeteranganObat();
        keteranganObat.setIdSubJenis(idSubJenis);
        keteranganObat.setIdParameterKeterangan(idParam);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        List<KeteranganObat> keteranganObats = new ArrayList<>();

        try {
            keteranganObats = keteranganObatBo.getListSearchByCriteria(keteranganObat);
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] END <<< ");
        return keteranganObats;
    }
}
