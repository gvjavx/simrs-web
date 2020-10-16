package com.neurix.hris.master.dokterKsoTindakan.bo.Impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.dokterKsoTindakan.bo.DokterKsoTindakanBo;
import com.neurix.hris.master.dokterKsoTindakan.dao.DokterKsoTindakanDao;
import com.neurix.hris.master.dokterKsoTindakan.model.DokterKsoTindakan;
import com.neurix.hris.master.dokterKsoTindakan.model.ImSimrsDokterKsoTindakan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DokterKsoTindakanBoImpl implements DokterKsoTindakanBo {
    protected static transient Logger logger = Logger.getLogger(DokterKsoTindakanBoImpl.class);
    private DokterKsoTindakanDao dokterKsoTindakanDao;
    private TindakanDao tindakanDao;
    private RiwayatTindakanDao riwayatTindakanDao;

    public RiwayatTindakanDao getRiwayatTindakanDao() {
        return riwayatTindakanDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public TindakanDao getTindakanDao() {
        return tindakanDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public DokterKsoTindakanDao getDokterKsoTindakanDao() {
        return dokterKsoTindakanDao;
    }

    public void setDokterKsoTindakanDao(DokterKsoTindakanDao dokterKsoTindakanDao) {
        this.dokterKsoTindakanDao = dokterKsoTindakanDao;
    }

    @Override
    public void saveDelete(DokterKsoTindakan bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(DokterKsoTindakan bean) throws GeneralBOException {

    }

    @Override
    public DokterKsoTindakan saveAdd(DokterKsoTindakan bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<DokterKsoTindakan> getByCriteria(DokterKsoTindakan bean) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        List<DokterKsoTindakan> result = new ArrayList<>();

        if (bean != null){
            Map hsCriteria = new HashMap();

            if (bean.getDokterKsoTindakanId() != null && !"".equalsIgnoreCase(bean.getDokterKsoTindakanId())){
                hsCriteria.put("dokter_kso_tindakan_id", bean.getDokterKsoTindakanId());
            }
            if (bean.getDokterKsoId() != null && !"".equalsIgnoreCase(bean.getDokterKsoId())){
                hsCriteria.put("dokter_kso_id", bean.getDokterKsoId());
            }

            if (bean.getTindakanId() != null && !"".equalsIgnoreCase(bean.getTindakanId())){
                hsCriteria.put("tindakan_id", bean.getTindakanId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImSimrsDokterKsoTindakan> entityList = new ArrayList<>();

            try {
                entityList = dokterKsoTindakanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (entityList.size()!=0){
                DokterKsoTindakan dokterKsoTindakan = null;
                for (ImSimrsDokterKsoTindakan ksoTindakan: entityList){
                    dokterKsoTindakan = new DokterKsoTindakan();
                    dokterKsoTindakan.setDokterKsoTindakanId(ksoTindakan.getDokterKsoTindakanId());
                    dokterKsoTindakan.setDokterKsoId(ksoTindakan.getDokterKsoId());
                    dokterKsoTindakan.setTindakanId(ksoTindakan.getTindakanId());
                    dokterKsoTindakan.setPersenKso(ksoTindakan.getPersenKso());
                    dokterKsoTindakan.setAction(ksoTindakan.getAction());
                    dokterKsoTindakan.setFlag(ksoTindakan.getFlag());
                    dokterKsoTindakan.setCreatedDate(ksoTindakan.getCreatedDate());
                    dokterKsoTindakan.setCreatedWho(ksoTindakan.getCreatedWho());
                    dokterKsoTindakan.setLastUpdate(ksoTindakan.getLastUpdate());
                    dokterKsoTindakan.setLastUpdateWho(ksoTindakan.getLastUpdateWho());
                    if (ksoTindakan.getTindakanId() != null){
                        ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = riwayatTindakanDao.getById("idTindakan",ksoTindakan.getTindakanId());
                        dokterKsoTindakan.setTindakanName(riwayatTindakanEntity.getNamaTindakan());
                    }

                    result.add(dokterKsoTindakan);
                }
            }
        }
        return result;
    }

    @Override
    public List<DokterKsoTindakan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}