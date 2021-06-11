package com.neurix.akuntansi.transaksi.koreksi.bo.impl;

import com.neurix.akuntansi.transaksi.koreksi.bo.KoreksiDetailBo;
import com.neurix.akuntansi.transaksi.koreksi.dao.KoreksiDetailDao;
import com.neurix.akuntansi.transaksi.koreksi.model.ItAkunKoreksiDetailEntity;
import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aji Noor on 19/03/2021
 */
public class KoreksiDetailBoImpl implements KoreksiDetailBo {
    protected static transient Logger logger = Logger.getLogger(KoreksiDetailBoImpl.class);
    private KoreksiDetailDao koreksiDetailDao;

    public void setKoreksiDetailDao(KoreksiDetailDao koreksiDetailDao) {
        this.koreksiDetailDao = koreksiDetailDao;
    }

    @Override
    public void saveDelete(KoreksiDetail bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(KoreksiDetail bean) throws GeneralBOException {

    }

    @Override
    public KoreksiDetail saveAdd(KoreksiDetail bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<KoreksiDetail> getByCriteria(KoreksiDetail searchBean) throws GeneralBOException {
        logger.info("[KoreksiDetailBoImpl..getByCriteria] start process >>>");
        // Mapping with collection and put
        List<KoreksiDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getKoreksiId() != null && !"".equalsIgnoreCase(searchBean.getKoreksiId())) {
                hsCriteria.put("koreksiId", searchBean.getKoreksiId());
            }
            if (searchBean.getKoreksiDetailId() != null && !"".equalsIgnoreCase(searchBean.getKoreksiDetailId())) {
                hsCriteria.put("koreksiDetailId", searchBean.getKoreksiDetailId());
            }
            if (searchBean.getDivisiId() != null && !"".equalsIgnoreCase(searchBean.getDivisiId())) {
                hsCriteria.put("divisiId", searchBean.getDivisiId());
            }
            if (searchBean.getPosisiCoa() != null && !"".equalsIgnoreCase(searchBean.getPosisiCoa())) {
                hsCriteria.put("posisi", searchBean.getPosisiCoa());
            }
            if (searchBean.getKodeVendor() != null && !"".equalsIgnoreCase(searchBean.getKodeVendor())) {
                hsCriteria.put("kodeVendor", searchBean.getKodeVendor());
            }

            List<ItAkunKoreksiDetailEntity> detailEntity;
            try {
                detailEntity = koreksiDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[KoreksiDetailBoImpl..getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(detailEntity != null){
                KoreksiDetail returnKoreksi;
                // Looping from dao to object and save in collection
                for(ItAkunKoreksiDetailEntity koreksiDetailEntity : detailEntity){
                    returnKoreksi = convertEntityToKoreksiDetail(koreksiDetailEntity);
                    listOfResult.add(returnKoreksi);
                }
            }
        }
        logger.info("[KoreksiDetailBoImpl..getByCriteria] end process <<<");
        return listOfResult;
    }

    @Override
    public List<KoreksiDetail> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    private KoreksiDetail convertEntityToKoreksiDetail(ItAkunKoreksiDetailEntity detailEntity){
        KoreksiDetail result = new KoreksiDetail();
        result.setKoreksiDetailId(detailEntity.getKoreksiDetailId());
        result.setKoreksiId(detailEntity.getKoreksiId());
        result.setKodeCoa(detailEntity.getKodeCoa());
        result.setKodeVendor(detailEntity.getKodeVendor());
        result.setDivisiId(detailEntity.getDivisiId());
        result.setNoNota(detailEntity.getNoNota());
        result.setJumlahPembayaran(detailEntity.getJumlahPembayaran());
        return result;
    }

    @Override
    public Map<String, KoreksiDetail> getListKoreksiDetail(String koreksiId) {
        logger.info("[KoreksiDetailBoImpl.getListKoreksiDetail] start process >>>");
        Map<String, KoreksiDetail> resultList = new HashMap<>();
        List<ItAkunKoreksiDetailEntity> detailEntityList;
        try {
            detailEntityList = koreksiDetailDao.getByAkunKoreksiId(koreksiId);
        } catch (Exception e) {
            logger.error("[KoreksiDetailBoImpl.getListKoreksiDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data MappingJurnal by transaksiId , please inform to your admin...," + e.getMessage());
        }
        if (detailEntityList.size() > 0) {
            for (ItAkunKoreksiDetailEntity itAkunKoreksiDetailEntity : detailEntityList) {
                KoreksiDetail koreksiDetail = new KoreksiDetail();
                koreksiDetail.setKoreksiDetailId(itAkunKoreksiDetailEntity.getKoreksiDetailId());
                koreksiDetail.setKoreksiId(itAkunKoreksiDetailEntity.getKoreksiId());
                koreksiDetail.setKodeCoa(itAkunKoreksiDetailEntity.getKodeCoa());
                koreksiDetail.setKodeVendor(itAkunKoreksiDetailEntity.getKodeVendor());
                koreksiDetail.setDivisiId(itAkunKoreksiDetailEntity.getDivisiId());
                koreksiDetail.setNoNota(itAkunKoreksiDetailEntity.getNoNota());
                koreksiDetail.setPosisiCoa(itAkunKoreksiDetailEntity.getPosisi());
                koreksiDetail.setJumlahPembayaran(itAkunKoreksiDetailEntity.getJumlahPembayaran());

                koreksiDetail.setMasterId(itAkunKoreksiDetailEntity.getKodeVendor());
                resultList.put(koreksiDetail.getKoreksiDetailId(), koreksiDetail);
            }
        }
        logger.info("[MappingJurnalBoImpl.getListMappingJurnal] end process <<<");
        return resultList;
    }

    @Override
    public KoreksiDetail getById(String koreksiDetailId) {
        KoreksiDetail result=null;
        ItAkunKoreksiDetailEntity itAkunKoreksiDetailEntity = koreksiDetailDao.getById("koreksiDetailId",koreksiDetailId);
        result = convertEntityToKoreksiDetail(itAkunKoreksiDetailEntity);
        return result;
    }
}
