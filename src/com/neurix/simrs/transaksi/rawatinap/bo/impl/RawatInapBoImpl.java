package com.neurix.simrs.transaksi.rawatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.rencanarawat.dao.ParameterRencanaRawatDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.KategoriSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.MasterSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.ParameterSkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.dao.SkorRanapDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsParameterSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class RawatInapBoImpl implements RawatInapBo {

    private static transient Logger logger = Logger.getLogger(RawatInapBoImpl.class);
    private RawatInapDao rawatInapDao;
    private KategoriSkorRanapDao kategoriSkorRanapDao;
    private ParameterSkorRanapDao parameterSkorRanapDao;
    private MasterSkorRanapDao masterSkorRanapDao;
    private SkorRanapDao skorRanapDao;

    protected List<ItSimrsRawatInapEntity> getListEntityByCriteria(RawatInap bean) throws GeneralBOException{
        logger.info("[RawatInapBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsRawatInapEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())){
            hsCriteria.put("id_rawat_inap", bean.getIdRawatInap());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }

        hsCriteria.put("flag", "Y");

        try {
            entityList = rawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ",e);
            throw new GeneralBOException("Error when get data detail checkup entity "+e.getMessage());
        }

        logger.info("[RawatInapBoImpl.getListEntityByCriteria] End <<<<<<<<");
        return entityList;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    @Override
    public List<RawatInap> getSearchRawatInap(RawatInap bean) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.getSearchRawatInap] Start >>>>>>>");
        List<RawatInap> results = new ArrayList<>();
        if (bean != null){
            try {
                results = rawatInapDao.getSearchRawatInap(bean);
            } catch (HibernateException e){
                logger.error("[RawatInapBoImpl.getSearchRawatInap] Error when get data rawat inap",e);
                throw new GeneralBOException("Error when get data detail checkup"+e.getMessage());
            }
        }
        logger.info("[RawatInapBoImpl.getSearchRawatInap] End <<<<<<<<");
        return results;
    }

    @Override
    public List<ItSimrsSkorRanapEntity> getListSkorRanap(SkorRanap bean) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.getListSkorRanap] Start >>>>>>>");

        List<ItSimrsSkorRanapEntity> skorRanapEntities = new ArrayList<>();
        if (bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getNoCheckup() != null){
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }
            if (bean.getIdDetailCheckup() != null){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getIdKategori() != null){
                hsCriteria.put("id_kategori", bean.getIdKategori());
            }
            if (bean.getGroupId() != null){
                hsCriteria.put("group_id", bean.getGroupId());
            }
            if (bean.getStDate() != null){
                hsCriteria.put("date", bean.getStDate());
            }

            try {
                skorRanapEntities = skorRanapDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[RawatInapBoImpl.getListSkorRanap] ERROR",e);
                throw new GeneralBOException("[RawatInapBoImpl.getListSkorRanap] ERROR"+e.getMessage());
            }

            if (skorRanapEntities.size() > 0){

                hsCriteria = new HashMap();
                hsCriteria.put("id_kategori", bean.getIdKategori());

                List<ImSimrsParameterSkorRanapEntity> parameterSkorRanapEntities = new ArrayList<>();
                try {
                    parameterSkorRanapEntities = parameterSkorRanapDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[RawatInapBoImpl.getListSkorRanap] ERROR",e);
                    throw new GeneralBOException("[RawatInapBoImpl.getListSkorRanap] ERROR"+e.getMessage());
                }

                if (parameterSkorRanapEntities.size() > 0){
                    ItSimrsSkorRanapEntity skorRanapEntity;
                    for (ImSimrsParameterSkorRanapEntity parameter : parameterSkorRanapEntities){
                        skorRanapEntity = new ItSimrsSkorRanapEntity();
                        skorRanapEntity.setIdKategori(parameter.getIdKategori());
                        skorRanapEntity.setIdParameter(parameter.getIdParameter());
                        skorRanapEntity.setNamaParameter(parameter.getNamaParameter());
                        skorRanapEntities.add(skorRanapEntity);
                    }
                }
            }
        }

        logger.info("[RawatInapBoImpl.getListSkorRanap] End <<<<<<<<");
        return skorRanapEntities;
    }

    private String getNextSkorRanap(){
        String id = "";
        try {
            id = skorRanapDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextSkorRanap] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextSkorRanap] Error when get seq "+e.getMessage());
        }
        return id;
    }

    private String getNextGroupSkorRanap(){
        String id = "";
        try {
            id = skorRanapDao.getNextGroupSeq();
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getNextGroupSkorRanap] Error when get seq ",e);
            throw new GeneralBOException("[RawatInapBoImpl.getNextGroupSkorRanap] Error when get seq "+e.getMessage());
        }
        return id;
    }


    public void setKategoriSkorRanapDao(KategoriSkorRanapDao kategoriSkorRanapDao) {
        this.kategoriSkorRanapDao = kategoriSkorRanapDao;
    }

    public void setParameterSkorRanapDao(ParameterSkorRanapDao parameterSkorRanapDao) {
        this.parameterSkorRanapDao = parameterSkorRanapDao;
    }

    public void setMasterSkorRanapDao(MasterSkorRanapDao masterSkorRanapDao) {
        this.masterSkorRanapDao = masterSkorRanapDao;
    }

    public void setSkorRanapDao(SkorRanapDao skorRanapDao) {
        this.skorRanapDao = skorRanapDao;
    }
}
