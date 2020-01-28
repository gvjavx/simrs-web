package com.neurix.simrs.bpjs.tindakan.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.tindakan.bo.TindakanBpjsBo;
import com.neurix.simrs.bpjs.tindakan.dao.TindakanBpjsDao;
import com.neurix.simrs.bpjs.tindakan.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TindakanBpjsBoImpl extends BpjsService implements TindakanBpjsBo {

    protected static transient Logger logger = Logger.getLogger(TindakanBpjsBoImpl.class);
    private TindakanBpjsDao tindakanBpjsDao;

    public TindakanBpjsBoImpl() throws GeneralSecurityException, IOException {
    }

    public static void setLogger(Logger logger) {
        TindakanBpjsBoImpl.logger = logger;
    }

    public TindakanBpjsDao getTindakanBpjsDao() {
        return tindakanBpjsDao;
    }

    public void setTindakanBpjsDao(TindakanBpjsDao tindakanBpjsDao) {
        this.tindakanBpjsDao = tindakanBpjsDao;
    }

    public static Logger getLogger() {
        return logger;
    }


    @Override
    public List<TindakanBpjs> getByCriteria(TindakanBpjs bean) throws GeneralBOException {
        logger.info("[TindakanBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdTindakanBpjs() != null && !"".equalsIgnoreCase(bean.getIdTindakanBpjs())) {
                hsCriteria.put("id_tindakan", bean.getIdTindakanBpjs());
            }
            if (bean.getNamaTindakanBpjs() != null && !"".equalsIgnoreCase(bean.getNamaTindakanBpjs())) {
                hsCriteria.put("id_jenis_tindakan", bean.getNamaTindakanBpjs());
            }
            if (bean.getKodeTindakanBpjs() != null && !"".equalsIgnoreCase(bean.getKodeTindakanBpjs())) {
                hsCriteria.put("nama_tindakan", bean.getKodeTindakanBpjs());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsTindakanBpjsEntity> tindakanEntityList = new ArrayList<>();
            List<TindakanBpjs> result = new ArrayList<>();

            try {
                tindakanEntityList = tindakanBpjsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[TindakanBoImpl.getByCriteria] error when get data tindakan by get by criteria "+ e.getMessage());
            }

            if (!tindakanEntityList.isEmpty()){
                TindakanBpjs tindakan;
                for (ImSimrsTindakanBpjsEntity tindakanEntity : tindakanEntityList){
                    tindakan = new TindakanBpjs();
                    tindakan.setIdTindakanBpjs(tindakanEntity.getIdTindakanBpjs());
                    tindakan.setNamaTindakanBpjs(tindakanEntity.getNamaTindakanBpjs());
                    tindakan.setKodeTindakanBpjs(tindakanEntity.getKodeTindakanBpjs());
                    tindakan.setFlag(tindakanEntity.getFlag());
                    tindakan.setAction(tindakanEntity.getAction());
                    tindakan.setCreatedDate(tindakanEntity.getCreatedDate());
                    tindakan.setCreatedWho(tindakanEntity.getCreatedWho());
                    tindakan.setLastUpdate(tindakanEntity.getLastUpdate());
                    tindakan.setLastUpdateWho(tindakanEntity.getLastUpdateWho());

                    result.add(tindakan);
                }
            }

            logger.info("[TindakanBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[TindakanBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }
}