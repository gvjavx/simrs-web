package com.neurix.simrs.transaksi.antrianonline.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.dao.AntrianOnlineDao;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Friday, 15/11/19 14:46
 */
public class AntrianOnlineBoImpl implements AntrianOnlineBo {
    protected static transient Logger logger = Logger.getLogger(AntrianOnlineBoImpl.class);

    private AntrianOnlineDao antrianOnlineDao;

    public void setAntrianOnlineDao(AntrianOnlineDao antrianOnlineDao) {
        this.antrianOnlineDao = antrianOnlineDao;
    }

    @Override
    public List<AntianOnline> getByCriteria(AntianOnline bean) throws GeneralBOException {
        logger.info("[AntrianOnlineBoImpl.getByCriteria] Start >>>>>>>");

        List<AntianOnline> listOfResult = new ArrayList<>();

        if (bean!= null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdAntrianOnline()!= null && !"".equalsIgnoreCase(bean.getIdAntrianOnline())) {
                hsCriteria.put("id_antrian_online", bean.getIdAntrianOnline());
            }

            if (bean.getNoCheckupOnline()!= null && !"".equalsIgnoreCase(bean.getNoCheckupOnline())) {
                hsCriteria.put("no_checkup_online", bean.getNoCheckupOnline());
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
            }

            if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())) {
                hsCriteria.put("id_dokter", bean.getIdDokter());
            }

            List<ItSimrsAntianOnlineEntity> listOfAntrianOnline = new ArrayList<>();


            try {
                listOfAntrianOnline = antrianOnlineDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AntrianOnlineBoImpl.getByCriteria] error when get data by criteria on getListAntrianOnlineByCriteria "+ e.getMessage());

            }

            if (listOfAntrianOnline.size() != 0) {
                for (ItSimrsAntianOnlineEntity item : listOfAntrianOnline) {
                    AntianOnline antrianItem = new AntianOnline();
                    antrianItem.setIdAntrianOnline(item.getIdAntrianOnline());
                    antrianItem.setNoCheckupOnline(item.getNoCheckupOnline());
                    antrianItem.setIdDokter(item.getIdDokter());
                    antrianItem.setIdPelayanan(item.getIdPelayanan());
                    antrianItem.setAction(item.getAction());
                    antrianItem.setCreatedDate(item.getCreatedDate());
                    antrianItem.setFlag(item.getFlag());
                    antrianItem.setCreatedWho(item.getCreatedWho());
                    antrianItem.setLastUpdate(item.getLastUpdate());
                    antrianItem.setLastUpdateWho(item.getLastUpdateWho());

                    listOfResult.add(antrianItem);
                }
            }
        }

        logger.info("[AntrianOnlineBoImpl.getByCriteria] End <<<<<<<");
        return listOfResult;
    }

    @Override
    public void saveAdd(AntianOnline bean) throws GeneralBOException {
        logger.info("[AntrianOnlineBoImpl.saveAdd] Start >>>>>>>");
        if(bean != null) {

            String idAntrian = "";
            idAntrian = getNextAntrianId();

            ItSimrsAntianOnlineEntity antianOnlineEntity = new ItSimrsAntianOnlineEntity();
            antianOnlineEntity.setIdAntrianOnline("ATR" + idAntrian);
            antianOnlineEntity.setNoCheckupOnline(bean.getNoCheckupOnline());
            antianOnlineEntity.setIdDokter(bean.getIdDokter());
            antianOnlineEntity.setIdPelayanan(bean.getIdPelayanan());
            antianOnlineEntity.setAction("C");
            antianOnlineEntity.setFlag("Y");
            antianOnlineEntity.setCreatedDate(bean.getCreatedDate());
            antianOnlineEntity.setLastUpdate(bean.getLastUpdate());
            antianOnlineEntity.setCreatedWho(bean.getCreatedWho());
            antianOnlineEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                antrianOnlineDao.addAndSave(antianOnlineEntity);
            } catch (HibernateException e) {
                logger.error("[AntrianOnlineBoImpl.saveAdd] Error When Saving data antrian online" + e.getMessage());
                throw new GeneralBOException("[AntrianOnlineBoImpl.saveAdd] Error When Saving data antrian online");
            }
        }
    }

    private String getNextAntrianId() {
        String id = "";
        try {
            id = antrianOnlineDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[AntrianOnlineBoImpl.getNextAntrianId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[AntrianOnlineBoImpl.getNextAntrianId] Error When Error get next seq id");
        }

        return id;
    }

}
