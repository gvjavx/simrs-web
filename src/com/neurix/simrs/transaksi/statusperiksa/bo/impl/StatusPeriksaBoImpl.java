package com.neurix.simrs.transaksi.statusperiksa.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.statusperiksa.bo.StatusPeriksaBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusPeriksaBoImpl implements StatusPeriksaBo {

    public static transient Logger logger = Logger.getLogger(StatusPeriksaBoImpl.class);
    private CheckupDetailDao checkupDetailDao;
    private HeaderCheckupDao headerCheckupDao;
    private UangMukaDao uangMukaDao;

    @Override
    public List<HeaderDetailCheckup> getListStatusPeriksa(HeaderDetailCheckup headerDetailCheckup) throws GeneralBOException {
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        if (headerDetailCheckup != null) {
            try {
                detailCheckupList = checkupDetailDao.getStatusPeriksa(headerDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Error when search status periksa " + e.getMessage());
            }
        }

        return detailCheckupList;
    }

    @Override
    public CheckResponse saveEditPerubahanStatus(HeaderDetailCheckup bean) throws GeneralBOException {
        CheckResponse response = new CheckResponse();
        if (bean != null) {

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {

                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
                ItSimrsHeaderChekupEntity headerChekupEntity = new ItSimrsHeaderChekupEntity();

                try {

                    headerChekupEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());

                    if (headerChekupEntity != null) {

                        if("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                            headerChekupEntity.setNoRujukan(bean.getNoRujukan());
                            headerChekupEntity.setNoPpkRujukan(bean.getNoPpk());
                            headerChekupEntity.setTglRujukan(Date.valueOf(bean.getTglRujukan()));
                            headerChekupEntity.setUrlDocRujuk(bean.getSuratRujukan());
                            headerChekupEntity.setKelasPasien(bean.getIdKelas());
                            headerChekupEntity.setRujuk(bean.getPerujuk());
                            headerChekupEntity.setKetRujukan(bean.getNamaPerujuk());
                            headerChekupEntity.setIdPelayananBpjs(bean.getIdPelayanan());
                        }

                        headerChekupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
                        headerChekupEntity.setJenisTransaksi(bean.getIdJenisPeriksaPasien());
                        headerChekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        headerChekupEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            headerCheckupDao.updateAndSave(headerChekupEntity);
                            response.setStatus("success");
                            response.setMessage("Berhasil");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Found Error when save update " + e.getMessage());
                            logger.error("Found Error when save update header " + e.getMessage());
                        }
                    }

                    detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());

                    if (detailCheckupEntity != null) {

                        if("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                            detailCheckupEntity.setNoSep(bean.getNoSep());
                            detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
                            detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
                        }

                        if("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                            detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi());
                            detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi());
                        }

                        if("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                            detailCheckupEntity.setMetodePembayaran(bean.getMetodePembayaran());
                        }

                        detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                        detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {

                            checkupDetailDao.updateAndSave(detailCheckupEntity);

                            if("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                                UangMuka uangMuka = new UangMuka();
                                uangMuka.setIdDetailCheckup(bean.getIdDetailCheckup());
                                ItSimrsUangMukaPendaftaranEntity entityUangMuka = getEntityUangMuka(uangMuka);

                                if (entityUangMuka != null) {

                                    entityUangMuka.setFlagRefund("Y");
                                    entityUangMuka.setLastUpdate(bean.getLastUpdate());
                                    entityUangMuka.setLastUpdateWho(bean.getLastUpdateWho());

                                    try {
                                        uangMukaDao.updateAndSave(entityUangMuka);
                                        response.setStatus("success");
                                        response.setMessage("Berhasil");
                                    } catch (HibernateException e) {
                                        response.setStatus("error");
                                        response.setMessage("Found Error when save update " + e.getMessage());
                                        logger.error("Found Error when search uang muka " + e.getMessage());
                                    }
                                }
                            }

                            if("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                                if (bean.getJumlahUangMuka() != null && !"".equalsIgnoreCase(bean.getJumlahUangMuka().toString())) {
                                    ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
                                    uangMukaPendaftaranEntity.setId("UM" + bean.getBranchId() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
                                    uangMukaPendaftaranEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                    uangMukaPendaftaranEntity.setFlag("Y");
                                    uangMukaPendaftaranEntity.setAction("C");
                                    uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
                                    uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
                                    uangMukaPendaftaranEntity.setLastUpdate(bean.getCreatedDate());
                                    uangMukaPendaftaranEntity.setLastUpdateWho(bean.getCreatedWho());

                                    if (bean.getJumlahUangMuka() == null || bean.getJumlahUangMuka().compareTo(new BigInteger(String.valueOf(0))) == 0) {
                                        uangMukaPendaftaranEntity.setJumlah(new BigInteger(String.valueOf(0)));
                                    } else {
                                        uangMukaPendaftaranEntity.setJumlah(bean.getJumlahUangMuka());
                                    }

                                    try {
                                        uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
                                        response.setStatus("success");
                                        response.setMessage("Berhasil");
                                    } catch (HibernateException e) {
                                        response.setStatus("error");
                                        response.setMessage("Found Error when save update " + e.getMessage());
                                        logger.error("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                                        throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                                    }
                                }
                            }
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Found Error when save update " + e.getMessage());
                            logger.error("Found Error when save checkup detail " + e.getMessage());
                        }
                    }

                } catch (HibernateException e) {
                    logger.error("Found Error when search id detail checkup " + e.getMessage());
                    response.setStatus("error");
                    response.setMessage("Found Error when save update " + e.getMessage());
                }
            }


        }
        return response;
    }

    private ItSimrsUangMukaPendaftaranEntity getEntityUangMuka(UangMuka bean) {
        ItSimrsUangMukaPendaftaranEntity entityUangMuka = new ItSimrsUangMukaPendaftaranEntity();
        Map hsCriteria = new HashMap();

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        List<ItSimrsUangMukaPendaftaranEntity> entityList = new ArrayList<>();

        try {
            entityList = uangMukaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("Found Error when search uang muka " + e.getMessage());
        }

        if (entityList != null) {
            entityUangMuka = entityList.get(0);
        }

        return entityUangMuka;
    }

    private String dateFormater(String type) {
        Date date = new Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }
}
