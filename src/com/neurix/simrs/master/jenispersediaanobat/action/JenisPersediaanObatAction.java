package com.neurix.simrs.master.jenispersediaanobat.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.bo.JenisPersediaanObatBo;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobat.model.JenisPersediaanObat;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class JenisPersediaanObatAction {
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatAction.class);
    private JenisPersediaanObatBo jenisPersediaanObatBoProxy;

    public void setJenisPersediaanObatBoProxy(JenisPersediaanObatBo jenisPersediaanObatBoProxy) {
        this.jenisPersediaanObatBoProxy = jenisPersediaanObatBoProxy;
    }

    public List<JenisPersediaanObat> getJenisPersediaanAll(){
        logger.info("[JenisPersediaanObatAction.getJenisPersediaanAll] START >>> ");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPersediaanObatBo jenisPersediaanObatBo = (JenisPersediaanObatBo) ctx.getBean("jenisPersediaanObatBoProxy");

        List<JenisPersediaanObat> jenisPersediaanObats = new ArrayList<>();

        try {
            jenisPersediaanObats = jenisPersediaanObatBo.getAll();
        } catch (GeneralBOException e){
            logger.error("[JenisPersediaanObatAction.getJenisPersediaanAll] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
        }
        logger.info("[JenisPersediaanObatAction.getJenisPersediaanAll] END <<< ");
        return jenisPersediaanObats;
    }
}
