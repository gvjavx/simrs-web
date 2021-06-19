package com.neurix.akuntansi.transaksi.kas.bo.impl;

import com.neurix.akuntansi.transaksi.kas.bo.KasDetailBo;
import com.neurix.akuntansi.transaksi.kas.dao.KasDetailDao;
import com.neurix.akuntansi.transaksi.kas.model.ItAkunKasDetailEntity;
import com.neurix.akuntansi.transaksi.kas.model.KasDetail;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aji Noor on 24/03/2021
 */
public class KasDetailBoImpl implements KasDetailBo {

    protected static transient Logger logger = Logger.getLogger(KasDetailBoImpl.class);

    private KasDetailDao kasDetailDao;

    public void setKasDetailDao(KasDetailDao kasDetailDao) {
        this.kasDetailDao = kasDetailDao;
    }

    @Override
    public void saveDelete(KasDetail bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(KasDetail bean) throws GeneralBOException {

    }

    @Override
    public KasDetail saveAdd(KasDetail bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<KasDetail> getByCriteria(KasDetail searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<KasDetail> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public Map<String, KasDetail> getListKasDetail(String kasId) {
        logger.info("[KasDetailBoImpl.getListKoreksiDetail] start process >>>");
        Map<String, KasDetail> resultList = new HashMap<>();
        List<ItAkunKasDetailEntity> detailEntityList;
        try {
            detailEntityList = kasDetailDao.getByAkunKasId(kasId);
        } catch (Exception e) {
            logger.error("[KoreksiDetailBoImpl.getListKoreksiDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data MappingJurnal by transaksiId , please inform to your admin...," + e.getMessage());
        }
        if (detailEntityList.size() > 0) {
            for (ItAkunKasDetailEntity itAkunKasDetailEntity : detailEntityList) {
                KasDetail kasDetail = convertEntityToKasDetail(itAkunKasDetailEntity);
                resultList.put(kasDetail.getKasDetailId(), kasDetail);
            }
        }
        logger.info("[MappingJurnalBoImpl.getListMappingJurnal] end process <<<");
        return resultList;
    }

    @Override
    public KasDetail getById(String kasDetailId) {
        KasDetail result = null;
        try {
            ItAkunKasDetailEntity itAkunKasDetailEntity = kasDetailDao.getById("kasDetailId",kasDetailId);
            result = convertEntityToKasDetail(itAkunKasDetailEntity);
        }
        catch (Exception e){
            logger.error("[KasDetailBoImpl.getKasById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get data Kas, please info to your admin..." + e.getMessage());
        }
        return result;
    }


    private KasDetail convertEntityToKasDetail(ItAkunKasDetailEntity dd){
        KasDetail result = null;
        if(dd != null) {
            result = new KasDetail();
            result.setKasDetailId(dd.getKasDetailId());
            result.setKasId(dd.getKasId());
            result.setMasterId(dd.getMasterId());
            result.setNoNota(dd.getNoNota());
            result.setStJumlahPembayaran(dd.getJumlahPembayaran().toString());
            result.setKodeRekening(dd.getKodeCoa());
            result.setDivisiId(dd.getDivisiId());
            result.setNoFakturPajak(dd.getNoFakturPajak());
            result.setUrlFakturImage(dd.getUrlFakturImage());
            result.setStPph(dd.getPph() == null ? "0" : dd.getPph().toString());
            result.setStPpn(dd.getPpn() == null ? "0" : dd.getPpn().toString());

            result.setFlag(dd.getFlag());
            result.setAction(dd.getAction());
            result.setCreatedDate(dd.getCreatedDate());
            result.setLastUpdate(dd.getLastUpdate());
            result.setCreatedWho(dd.getCreatedWho());
            result.setLastUpdateWho(dd.getLastUpdateWho());
        }
        return result;
    }
}
