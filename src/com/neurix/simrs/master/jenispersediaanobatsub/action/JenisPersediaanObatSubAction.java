package com.neurix.simrs.master.jenispersediaanobatsub.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobatsub.bo.JenisPersediaanObatSubBo;
import com.neurix.simrs.master.jenispersediaanobatsub.model.JenisPersediaanObatSub;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class JenisPersediaanObatSubAction {
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatSubAction.class);
    private JenisPersediaanObatSubBo jenisPersediaanObatSubBoProxy;

    public void setJenisPersediaanObatSubBoProxy(JenisPersediaanObatSubBo jenisPersediaanObatSubBoProxy) {
        this.jenisPersediaanObatSubBoProxy = jenisPersediaanObatSubBoProxy;
    }

    public List<JenisPersediaanObatSub> getListJenisObatSubByIdJenis(String idJenis){
        logger.info("[JenisPersediaanObatSubAction.getListJenisObatSubByIdJenis] START >>>");
        JenisPersediaanObatSub jenisPersediaanObatSub = new JenisPersediaanObatSub();
        jenisPersediaanObatSub.setIdJenisObat(idJenis);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPersediaanObatSubBo jenisPersediaanObatSubBo = (JenisPersediaanObatSubBo) ctx.getBean("jenisPersediaanObatSubBoProxy");
        List<JenisPersediaanObatSub> jenisPersediaanObatSubs = new ArrayList<>();
        if(idJenis != null && !"".equalsIgnoreCase(idJenis)){
            try {
                jenisPersediaanObatSubs = jenisPersediaanObatSubBo.getSearchByCriteria(jenisPersediaanObatSub);
            } catch (GeneralBOException e){
                logger.error("[JenisPersediaanObatSubAction.getListJenisObatSubByIdJenis] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
            }
        }
        logger.info("[JenisPersediaanObatSubAction.getListJenisObatSubByIdJenis] END <<<");
        return jenisPersediaanObatSubs;
    }
}
