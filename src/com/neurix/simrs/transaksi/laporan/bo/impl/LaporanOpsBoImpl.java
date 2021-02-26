package com.neurix.simrs.transaksi.laporan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.laporan.bo.LaporanOpsBo;
import com.neurix.simrs.transaksi.laporan.dao.LaporanOpsDao;
import com.neurix.simrs.transaksi.laporan.model.ImSimrsLaporanOpsEntity;
import com.neurix.simrs.transaksi.laporan.model.LaporanOps;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LaporanOpsBoImpl implements LaporanOpsBo {
    protected static transient Logger logger = Logger.getLogger(LaporanOpsBoImpl.class);
    private LaporanOpsDao laporanOpsDao;

    @Override
    public List<LaporanOps> getByCriteria(LaporanOps bean) throws GeneralBOException {
        HashMap hsCriteria = new HashMap();
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        if(bean != null){
            if(bean.getIdLaporanOps() != null){
                hsCriteria.put("id_laporan_ops", bean.getIdLaporanOps());
            }
            if(bean.getNamaLaporan() != null){
                hsCriteria.put("nama_laporan", bean.getNamaLaporan());
            }
            List<ImSimrsLaporanOpsEntity> opsEntityList = new ArrayList<>();
            try {
                opsEntityList = laporanOpsDao.getByCriteria(hsCriteria);
            }catch (Exception e){
                logger.error(e.getMessage());
            }

            if(opsEntityList.size() > 0){
                for (ImSimrsLaporanOpsEntity entity: opsEntityList){
                    LaporanOps laporanOps = new LaporanOps();
                    laporanOps.setIdLaporanOps(entity.getIdLaporanOps());
                    laporanOps.setNamaLaporan(entity.getNamaLaporan());
                    laporanOpsList.add(laporanOps);
                }
            }
        }
        return laporanOpsList;
    }

    @Override
    public List<LaporanOps> getLaporanRjRi(LaporanOps bean) throws GeneralBOException {
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        try {
            laporanOpsList = laporanOpsDao.getLaporanRjRi(bean);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return laporanOpsList;
    }

    @Override
    public List<LaporanOps> getLaporanPlyUnggulan(LaporanOps bean) throws GeneralBOException {
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        try {
            laporanOpsList = laporanOpsDao.getLaporanPlyUnggulan(bean);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return laporanOpsList;
    }

    @Override
    public List<LaporanOps> getListTahunByOps() throws GeneralBOException {
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        try {
            laporanOpsList = laporanOpsDao.getListTahunByOps();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return laporanOpsList;
    }

    @Override
    public List<LaporanOps> getListDiagnosaTerbanyak(LaporanOps bean) throws GeneralBOException {
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        try {
            laporanOpsList = laporanOpsDao.getListDiagnosaTerbanyak(bean);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return laporanOpsList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setLaporanOpsDao(LaporanOpsDao laporanOpsDao) {
        this.laporanOpsDao = laporanOpsDao;
    }
}
