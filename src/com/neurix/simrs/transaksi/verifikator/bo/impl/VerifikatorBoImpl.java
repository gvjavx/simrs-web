package com.neurix.simrs.transaksi.verifikator.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakanina.dao.KategoriTindakanInaDao;
import com.neurix.simrs.master.kategoritindakanina.model.ImSimrsKategoriTindakanInaEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.impl.TransaksiObatBoImpl;
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VerifikatorBoImpl implements VerifikatorBo {

    private static transient Logger logger = Logger.getLogger(VerifikatorBoImpl.class);
    private TindakanRawatDao tindakanRawatDao;
    private HeaderCheckupDao checkupDao;
    private CheckupDetailDao checkupDetailDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private RawatInapDao rawatInapDao;
    private KategoriTindakanInaDao kategoriTindakanInaDao;

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setCheckupDao(HeaderCheckupDao checkupDao) {
        this.checkupDao = checkupDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public void setKategoriTindakanInaDao(KategoriTindakanInaDao kategoriTindakanInaDao) {
        this.kategoriTindakanInaDao = kategoriTindakanInaDao;
    }

    @Override
    public CheckResponse updateApproveBpjsFlag(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.updateApproveBpjsFlag] START process <<<");
        CheckResponse response = new CheckResponse();
        if (bean != null) {

            ItSimrsRiwayatTindakanEntity entity = new ItSimrsRiwayatTindakanEntity();
            try {
                entity = riwayatTindakanDao.getById("idRiwayatTindakan", bean.getIdRiwayatTindakan());
            } catch (HibernateException e) {
                logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when update data flag approve tindakan rawat ", e);
            }

            if (entity != null) {

                entity.setApproveBpjsFlag("Y");
                entity.setKategoriTindakanBpjs(bean.getKategoriTindakanBpjs());
                entity.setAction("U");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                if (!"".equalsIgnoreCase(bean.getJenisPasien()) && bean.getJenisPasien() != null) {
                    entity.setJenisPasien(bean.getJenisPasien());
                }

                try {
                    riwayatTindakanDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMessage("Berhasil menyimpan kategori tindakan BPJS!");
                } catch (HibernateException e) {
                    logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when save update data flag approve tindakan rawat ", e);
                    response.setStatus("error");
                    response.setMessage("Terjadi kesalahan saat menyimpan ke database : " + e.getMessage());
                }
            }
        }
        logger.info("[VerifikatorBoImpl.updateApproveBpjsFlag] END process <<<");
        return response;
    }

    @Override
    public CheckResponse updateKlaimBpjsFlag(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.updateKlaimBpjsFlag] START process <<<");
        CheckResponse response = new CheckResponse();
        if (bean != null) {

            ItSimrsHeaderDetailCheckupEntity entity = new ItSimrsHeaderDetailCheckupEntity();
            try {
                entity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
            } catch (HibernateException e) {
                logger.error("[VerifikatorBoImpl.updateKlaimBpjsFlag] Error when update data flag approve tindakan rawat ", e);
            }

            if (entity != null) {

                entity.setKlaimBpjsFlag("Y");
                entity.setFlagCloseTraksaksi("Y");
                entity.setFlagCover("Y");
                entity.setAction("U");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    checkupDetailDao.updateAndSave(entity);
                    response.setStatus("200");
                    response.setMessage("Berhasil mengubah flag bpjs flag klaim!");
                } catch (HibernateException e) {
                    logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when save update data flag approve tindakan rawat ", e);
                    response.setStatus("400");
                    response.setMessage("Terjadi kesalahan saat menyimpan ke database : " + e.getMessage());
                }
            }
        }
        logger.info("[VerifikatorBoImpl.updateKlaimBpjsFlag] END process <<<");
        return response;
    }

    @Override
    public List<HeaderDetailCheckup> getListVerifikasiRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatJalan] START process <<<");
        List<HeaderDetailCheckup> result = new ArrayList<>();
        if (bean != null) {
            try {
                result = checkupDetailDao.getSearchVerifikasiRawatJalan(bean);
            } catch (HibernateException e) {
                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatJalan] END process <<<");
        return result;
    }

    @Override
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.getListAllTindakan] START process <<<");
        List<RiwayatTindakan> result = new ArrayList<>();
        if (bean != null) {
            try {
                result = riwayatTindakanDao.getListTindakan(bean);
            } catch (HibernateException e) {
                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[VerifikatorBoImpl.getListAllTindakan] END process <<<");
        return result;
    }

    @Override
    public CheckResponse updateFlagKlaim(RiwayatTindakan bean) throws GeneralBOException {

        logger.info("[VerifikatorBoImpl.updateFlagKlaim] START process <<<");
        CheckResponse response = new CheckResponse();
        if (bean != null) {
            ItSimrsRiwayatTindakanEntity entity = new ItSimrsRiwayatTindakanEntity();
            try {
                entity = riwayatTindakanDao.getById("idRiwayatTindakan", bean.getIdRiwayatTindakan());
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Foun error" + e.getMessage());
                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when search data tindakan rawat ", e);
            }

            if (entity != null) {

                entity.setFlagUpdateKlaim("Y");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    riwayatTindakanDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMessage("Berhasil mengupdate flag update klaim");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Foun error" + e.getMessage());
                    logger.error("[VerifikatorBoImpl.getListVerifikasiRawatJalan] Error when save update data flag klaim tindakan rawat ");
                }
            }
        }

        logger.info("[VerifikatorBoImpl.updateFlagKlaim] END process <<<");
        return response;
    }

    @Override
    public List<RawatInap> getListVerifikasiRawatInap(RawatInap bean) throws GeneralBOException {
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatInap] START process <<<");
        List<RawatInap> result = new ArrayList<>();
        if (bean != null) {
            try {
                result = rawatInapDao.getSearchVerifikasiRawatInap(bean, "");
            } catch (HibernateException e) {

                logger.error("[VerifikatorBoImpl.getListVerifikasiRawatInap] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[VerifikatorBoImpl.getListVerifikasiRawatInap] END process <<<");
        return result;
    }

    @Override
    public List<RiwayatTindakan> getListTindakanApprove(String idDetail) throws GeneralBOException {
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();

        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {
            try {
                riwayatTindakanList = riwayatTindakanDao.getListTindakanApprove(idDetail);
            } catch (HibernateException e) {
                logger.error("found error when search tindakan " + e.getMessage());
            }
        }

        return riwayatTindakanList;
    }

    public List<ImSimrsKategoriTindakanInaEntity> getAllKatTindakanInaList() throws GeneralBOException {
        return kategoriTindakanInaDao.getByCriteria(new HashMap());
    }

    @Override
    public List<HeaderDetailCheckup> getListVerifTransaksi(HeaderDetailCheckup detailCheckup) throws GeneralBOException {
        return checkupDetailDao.getListVerifTransaksi(detailCheckup);
    }

    @Override
    public CrudResponse updateCoverAsuransi(List<RiwayatTindakan> list, HeaderDetailCheckup bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            for (RiwayatTindakan tindakan : list) {
                ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = new ItSimrsRiwayatTindakanEntity();
                riwayatTindakanEntity = riwayatTindakanDao.getById("idRiwayatTindakan", tindakan.getIdRiwayatTindakan());
                if (riwayatTindakanEntity != null) {
                    riwayatTindakanEntity.setJenisPasien(tindakan.getJenisPasien());
                    riwayatTindakanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    riwayatTindakanEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        riwayatTindakanDao.updateAndSave(riwayatTindakanEntity);
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("found error, " + e.getMessage());
                        return response;
                    }
                }
            }

            if (bean.getIdDetailCheckup() != null) {
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
                detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                if (detailCheckupEntity != null) {
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya());
                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("found error, " + e.getMessage());
                    }
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("Found Error, List yang dikirm tidak ada...!");
        }
        return response;
    }

    @Override
    public CrudResponse updateInvoice(HeaderDetailCheckup bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean.getIdDetailCheckup() != null) {
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
            detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
            if (detailCheckupEntity != null) {
                detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckupEntity.setInvoice(bean.getInvoice());
                detailCheckupEntity.setFlagCover(bean.getFlagCover());
                detailCheckupEntity.setDibayarPasien(bean.getPasienBayar());
                if (bean.getPasienBayar() != null) {
                    if (bean.getPasienBayar().intValue() > 0) {
                        detailCheckupEntity.setFlagSisa("Y");
                    }
                }
                try {
                    checkupDetailDao.updateAndSave(detailCheckupEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("found error, " + e.getMessage());
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("found error id detail checkup tidak ditemukan..!");
        }
        return response;
    }

    @Override
    public CheckResponse updateRiwayatTindakan(List<RiwayatTindakan> list) throws GeneralBOException {
        CheckResponse response = new CheckResponse();
        if (list.size() > 0) {
            ItSimrsRiwayatTindakanEntity entity = new ItSimrsRiwayatTindakanEntity();
            for (RiwayatTindakan bean : list) {
                try {
                    entity = riwayatTindakanDao.getById("idRiwayatTindakan", bean.getIdRiwayatTindakan());
                } catch (HibernateException e) {
                    logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when update data flag approve tindakan rawat ", e);
                }

                if (entity != null) {

                    entity.setApproveBpjsFlag("Y");
                    entity.setKategoriTindakanBpjs(bean.getKategoriTindakanBpjs());
                    entity.setAction("U");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    if (!"".equalsIgnoreCase(bean.getJenisPasien()) && bean.getJenisPasien() != null) {
                        entity.setJenisPasien(bean.getJenisPasien());
                    }

                    try {
                        riwayatTindakanDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMessage("Berhasil menyimpan kategori tindakan BPJS!");
                    } catch (HibernateException e) {
                        logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when save update data flag approve tindakan rawat ", e);
                        response.setStatus("error");
                        response.setMessage("Terjadi kesalahan saat menyimpan ke database : " + e.getMessage());
                    }
                } else {
                    logger.error("[VerifikatorBoImpl.updateApproveBpjsFlag] Error when save update data flag approve tindakan rawat ");
                    response.setStatus("error");
                    response.setMessage("Terjadi kesalahan saat menyimpan ke database ");
                }
            }
        }
        return response;
    }
}