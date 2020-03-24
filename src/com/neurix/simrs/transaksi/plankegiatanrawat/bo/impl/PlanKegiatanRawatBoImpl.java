package com.neurix.simrs.transaksi.plankegiatanrawat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.plankegiatanrawat.bo.PlanKegiatanRawatBo;
import com.neurix.simrs.transaksi.plankegiatanrawat.dao.PlanKegiatanRawatDao;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.ItSimrsPlanKegiatanRawatEntity;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/03/20.
 */
public class PlanKegiatanRawatBoImpl implements PlanKegiatanRawatBo {

    protected static transient Logger logger = Logger.getLogger(PlanKegiatanRawatBoImpl.class);

    private PlanKegiatanRawatDao planKegiatanRawatDao;
    private CheckupDetailDao checkupDetailDao;
    private PasienDao pasienDao;
    private PelayananDao pelayananDao;
    private RawatInapDao rawatInapDao;
    private RuanganDao ruanganDao;

    @Override
    public List<PlanKegiatanRawat> getSearchByCritria(PlanKegiatanRawat bean) throws GeneralBOException {

        List<ItSimrsPlanKegiatanRawatEntity> planKegiatanRawatEntities = getListEntityPlanKegiatan(bean);
        List<PlanKegiatanRawat> planKegiatanRawats = new ArrayList<>();
        if (planKegiatanRawatEntities.size() > 0){
            PlanKegiatanRawat kegiatanRawat;
            for (ItSimrsPlanKegiatanRawatEntity planKegiatan : planKegiatanRawatEntities){
                kegiatanRawat = new PlanKegiatanRawat();

                planKegiatanRawats.add(kegiatanRawat);
            }
        }

        return planKegiatanRawats;
    }

    public List<ItSimrsPlanKegiatanRawatEntity> getListEntityPlanKegiatan(PlanKegiatanRawat bean){

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup()))
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());

        List<ItSimrsPlanKegiatanRawatEntity> planKegiatanRawatEntities = new ArrayList<>();

        try {
            planKegiatanRawatEntities = planKegiatanRawatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PlanKegiatanRawatBoImpl.getListEntityPlanKegiatan] ERROR. ",e);
            throw new GeneralBOException("[PlanKegiatanRawatBoImpl.getListEntityPlanKegiatan] ERROR. "+e);
        }

        return planKegiatanRawatEntities;
    }

    @Override
    public List<PlanKegiatanRawat> getListKegiatanRawat(PlanKegiatanRawat bean) throws GeneralBOException {
        return planKegiatanRawatDao.getSearchPlanKegiataRawat(bean);
    }

    public void setPlanKegiatanRawatDao(PlanKegiatanRawatDao planKegiatanRawatDao) {
        this.planKegiatanRawatDao = planKegiatanRawatDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }
}
