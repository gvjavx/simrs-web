package com.neurix.simrs.transaksi.respirasi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.respirasi.bo.RespirasiBo;
import com.neurix.simrs.transaksi.respirasi.dao.RespirasiDao;
import com.neurix.simrs.transaksi.respirasi.model.ItSimrsRespirasiEntity;
import com.neurix.simrs.transaksi.respirasi.model.Respirasi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RespirasiBoImpl implements RespirasiBo {
    private static transient Logger logger = Logger.getLogger(RespirasiBoImpl.class);
    private RespirasiDao respirasiDao;

    @Override
    public List<Respirasi> getByCriteria(Respirasi bean) throws GeneralBOException {
        List<Respirasi> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdRespirasi() != null && !"".equalsIgnoreCase(bean.getIdRespirasi())) {
                hsCriteria.put("id_respirasi", bean.getIdRespirasi());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getTanggal() != null && !"".equalsIgnoreCase(bean.getTanggal().toString())) {
                hsCriteria.put("tanggal", bean.getTanggal());
            }

            List<ItSimrsRespirasiEntity> entityList = new ArrayList<>();

            try {
                entityList = respirasiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsRespirasiEntity entity : entityList) {
                    Respirasi respirasi = new Respirasi();
                    respirasi.setIdRespirasi(entity.getIdRespirasi());
                    respirasi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    respirasi.setWaktu(entity.getWaktu());
                    respirasi.setTanggal(entity.getTanggal());
                    respirasi.setGcs(entity.getGcs());
                    respirasi.setDiameterPupil(entity.getDiameterPupil());
                    respirasi.setReflekCahaya(entity.getReflekCahaya());
                    respirasi.setTk(entity.getTk());
                    respirasi.setKk(entity.getKk());
                    respirasi.setJenisNkRmNrm(entity.getJenisNkRmNrm());
                    respirasi.setNkRmNrm(entity.getNkRmNrm());
                    respirasi.setJenisTPieceJRise(entity.getJenisTPieceJRise());
                    respirasi.settPieceJRise(entity.gettPieceJRise());
                    respirasi.setTipeVentilasi(entity.getTipeVentilasi());
                    respirasi.setJenisPeepCpapEt(entity.getJenisPeepCpapEt());
                    respirasi.setPeepCpapEt(entity.getPeepCpapEt());
                    respirasi.setFrekwensi(entity.getFrekwensi());
                    respirasi.setTv(entity.getTv());
                    respirasi.setMv(entity.getMv());
                    respirasi.setJenisPSupportPAsb(entity.getJenisPSupportPAsb());
                    respirasi.setpSupportPAsb(entity.getpSupportPAsb());
                    respirasi.setJenisPInsPCon(entity.getJenisPInsPCon());
                    respirasi.setpInsPCon(entity.getpInsPCon());
                    respirasi.setTriger(entity.getTriger());
                    respirasi.setInsTime(entity.getInsTime());
                    respirasi.setFlow(entity.getFlow());
                    respirasi.setJenisFioKon(entity.getJenisFioKon());
                    respirasi.setFioKon(entity.getFioKon());
                    respirasi.setJenisUkuranKedalamaanEtt(entity.getJenisUkuranKedalamaanEtt());
                    respirasi.setUkuranKedalamaanEtt(entity.getUkuranKedalamaanEtt());
                    respirasi.setSpo(entity.getSpo());
                    respirasi.setSecret(entity.getSecret());
                    respirasi.setKeterangan(entity.getKeterangan());
                    respirasi.setAction(entity.getAction());
                    respirasi.setFlag(entity.getFlag());
                    respirasi.setCreatedDate(entity.getCreatedDate());
                    respirasi.setCreatedWho(entity.getCreatedWho());
                    respirasi.setLastUpdate(entity.getLastUpdate());
                    respirasi.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(respirasi);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(Respirasi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            ItSimrsRespirasiEntity respirasiEntity = new ItSimrsRespirasiEntity();
            respirasiEntity.setIdRespirasi("RPR" + respirasiDao.getNextSeq());
            respirasiEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            respirasiEntity.setWaktu(bean.getWaktu());
            String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(bean.getCreatedDate());
            respirasiEntity.setTanggal(Date.valueOf(formatDate));
            respirasiEntity.setGcs(bean.getGcs());
            respirasiEntity.setDiameterPupil(bean.getDiameterPupil());
            respirasiEntity.setReflekCahaya(bean.getReflekCahaya());
            respirasiEntity.setTk(bean.getTk());
            respirasiEntity.setKk(bean.getKk());
            respirasiEntity.setJenisNkRmNrm(bean.getJenisNkRmNrm());
            respirasiEntity.setNkRmNrm(bean.getNkRmNrm());
            respirasiEntity.setJenisTPieceJRise(bean.getJenisTPieceJRise());
            respirasiEntity.settPieceJRise(bean.gettPieceJRise());
            respirasiEntity.setTipeVentilasi(bean.getTipeVentilasi());
            respirasiEntity.setJenisPeepCpapEt(bean.getJenisPeepCpapEt());
            respirasiEntity.setPeepCpapEt(bean.getPeepCpapEt());
            respirasiEntity.setFrekwensi(bean.getFrekwensi());
            respirasiEntity.setTv(bean.getTv());
            respirasiEntity.setMv(bean.getMv());
            respirasiEntity.setJenisPSupportPAsb(bean.getJenisPSupportPAsb());
            respirasiEntity.setpSupportPAsb(bean.getpSupportPAsb());
            respirasiEntity.setJenisPInsPCon(bean.getJenisPInsPCon());
            respirasiEntity.setpInsPCon(bean.getpInsPCon());
            respirasiEntity.setTriger(bean.getTriger());
            respirasiEntity.setInsTime(bean.getInsTime());
            respirasiEntity.setFlow(bean.getFlow());
            respirasiEntity.setJenisFioKon(bean.getJenisFioKon());
            respirasiEntity.setFioKon(bean.getFioKon());
            respirasiEntity.setJenisUkuranKedalamaanEtt(bean.getJenisUkuranKedalamaanEtt());
            respirasiEntity.setUkuranKedalamaanEtt(bean.getUkuranKedalamaanEtt());
            respirasiEntity.setSpo(bean.getSpo());
            respirasiEntity.setSecret(bean.getSecret());
            respirasiEntity.setKeterangan(bean.getKeterangan());
            respirasiEntity.setAction(bean.getAction());
            respirasiEntity.setFlag(bean.getFlag());
            respirasiEntity.setCreatedDate(bean.getCreatedDate());
            respirasiEntity.setCreatedWho(bean.getCreatedWho());
            respirasiEntity.setLastUpdate(bean.getLastUpdate());
            respirasiEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                respirasiDao.addAndSave(respirasiEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Found Error " + e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public Boolean cekData(String id, String waktu) throws GeneralBOException {
        return respirasiDao.cekData(id, waktu);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setRespirasiDao(RespirasiDao respirasiDao) {
        this.respirasiDao = respirasiDao;
    }
}
