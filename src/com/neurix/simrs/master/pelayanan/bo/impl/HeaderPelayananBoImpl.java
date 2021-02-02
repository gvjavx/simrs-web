package com.neurix.simrs.master.pelayanan.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.bo.HeaderPelayananBo;
import com.neurix.simrs.master.pelayanan.dao.HeaderPelayananDao;
import com.neurix.simrs.master.pelayanan.model.HeaderPelayanan;
import com.neurix.simrs.master.pelayanan.model.ImSimrsHeaderPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HeaderPelayananBoImpl implements HeaderPelayananBo {
    protected static transient Logger logger = Logger.getLogger(HeaderPelayananBoImpl.class);
    private HeaderPelayananDao headerPelayananDao;
    private PositionDao positionDao;

    @Override
    public List<HeaderPelayanan> getByCriteria(HeaderPelayanan bean) throws GeneralBOException {
        List<HeaderPelayanan> headerPelayananList = new ArrayList<>();
        List<ImSimrsHeaderPelayananEntity> list = getListEntity(bean);
        if(list.size() > 0){
            for (ImSimrsHeaderPelayananEntity entity: list){
                HeaderPelayanan headerPelayanan = new HeaderPelayanan();
                headerPelayanan.setIdHeaderPelayanan(entity.getIdHeaderPelayanan());
                headerPelayanan.setNamaPelayanan(entity.getNamaPelayanan());
                headerPelayanan.setTipePelayanan(entity.getTipePelayanan());
                headerPelayanan.setKategoriPelayanan(entity.getKategoriPelayanan());
                headerPelayanan.setKodeVclaim(entity.getKodeVclaim());
                headerPelayanan.setDivisiId(entity.getDivisiId());
                headerPelayanan.setIsVaksin(entity.getIsVaksin());
                headerPelayanan.setFlag(entity.getFlag());
                headerPelayanan.setAction(entity.getAction());
                headerPelayanan.setCreatedDate(entity.getCreatedDate());
                headerPelayanan.setCreatedWho(entity.getCreatedWho());
                headerPelayanan.setLastUpdate(entity.getLastUpdate());
                headerPelayanan.setLastUpdateWho(entity.getLastUpdateWho());
                ImPosition position = positionDao.getById("positionId", entity.getDivisiId());
                if(position != null){
                    headerPelayanan.setDivisiName(position.getPositionName());
                }
                headerPelayananList.add(headerPelayanan);
            }
        }
        return headerPelayananList;
    }

    @Override
    public List<ImSimrsHeaderPelayananEntity> getListEntity(HeaderPelayanan bean) throws GeneralBOException {
        List<ImSimrsHeaderPelayananEntity> headerPelayananEntityList = new ArrayList<>();
        HashMap hsCriteria = new HashMap();
        if(bean.getIdHeaderPelayanan() != null && !"".equalsIgnoreCase(bean.getIdHeaderPelayanan())){
            hsCriteria.put("id_header_pelayanan", bean.getIdHeaderPelayanan());
        }
        if(bean.getNamaPelayanan() != null && !"".equalsIgnoreCase(bean.getNamaPelayanan())){
            hsCriteria.put("nama_pelayanan", bean.getNamaPelayanan());
        }
        if(bean.getTipePelayanan() != null && !"".equalsIgnoreCase(bean.getTipePelayanan())){
            hsCriteria.put("tipe_pelayanan", bean.getTipePelayanan());
        }
        if(bean.getDivisiId() != null && !"".equalsIgnoreCase(bean.getDivisiId())){
            hsCriteria.put("divisi_id", bean.getDivisiId());
        }
        if(bean.getKodeVclaim() != null && !"".equalsIgnoreCase(bean.getKodeVclaim())){
            hsCriteria.put("kode_vclaim", bean.getKodeVclaim());
        }
        if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            hsCriteria.put("flag", bean.getFlag());
        }

        try {
            headerPelayananEntityList = headerPelayananDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            throw new GeneralBOException("Error"+e.getMessage());
        }
        return headerPelayananEntityList;
    }

    @Override
    public void saveAdd(HeaderPelayanan bean) throws GeneralBOException {
        if(bean != null){
            try {
                List<ImSimrsHeaderPelayananEntity> cek = new ArrayList<>();
                try {
                    cek = headerPelayananDao.getData(bean.getNamaPelayanan());
                }catch (Exception e){
                    logger.error("error"+e.getMessage());
                    throw new GeneralBOException("Error when save "+e.getMessage());
                }
                if(cek.size() > 0){
                    logger.error("error sudah ada nama pelayanan");
                    throw new GeneralBOException("Mohon maaf data pelayanan "+bean.getNamaPelayanan()+" sudah ada...! @_@");
                }else{
                    ImSimrsHeaderPelayananEntity headerPelayananEntity = new ImSimrsHeaderPelayananEntity();
                    headerPelayananEntity.setIdHeaderPelayanan(headerPelayananDao.getNextId());
                    headerPelayananEntity.setNamaPelayanan(bean.getNamaPelayanan());
                    headerPelayananEntity.setTipePelayanan(bean.getTipePelayanan());
                    headerPelayananEntity.setKategoriPelayanan(bean.getKategoriPelayanan());
                    headerPelayananEntity.setKodeVclaim(bean.getKodeVclaim());
                    headerPelayananEntity.setDivisiId(bean.getDivisiId());
                    headerPelayananEntity.setIsVaksin(bean.getIsVaksin());
                    headerPelayananEntity.setFlag(bean.getFlag());
                    headerPelayananEntity.setAction(bean.getAction());
                    headerPelayananEntity.setCreatedWho(bean.getCreatedWho());
                    headerPelayananEntity.setCreatedDate(bean.getCreatedDate());
                    headerPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    headerPelayananEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        headerPelayananDao.addAndSave(headerPelayananEntity);
                    }catch (HibernateException e){
                        logger.error("error"+e.getMessage());
                        throw new GeneralBOException("Error when save "+e.getMessage());
                    }
                }
            }catch (Exception e){
                logger.error("error"+e.getMessage());
                throw new GeneralBOException("Error when save "+e.getMessage());
            }
        }
    }

    @Override
    public void saveEdit(HeaderPelayanan bean) throws GeneralBOException {
        if(bean != null){
            try {
                ImSimrsHeaderPelayananEntity entity = headerPelayananDao.getById("idHeaderPelayanan", bean.getIdHeaderPelayanan());
                if(entity != null){
                    if(bean.getNamaPelayanan().equalsIgnoreCase(entity.getNamaPelayanan())){
                        entity.setNamaPelayanan(bean.getNamaPelayanan());
                        entity.setTipePelayanan(bean.getTipePelayanan());
                        entity.setKategoriPelayanan(bean.getKategoriPelayanan());
                        entity.setKodeVclaim(bean.getKodeVclaim());
                        entity.setDivisiId(bean.getDivisiId());
                        entity.setIsVaksin(bean.getIsVaksin());
                        entity.setFlag(bean.getFlag());
                        entity.setAction(bean.getAction());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setLastUpdate(bean.getLastUpdate());
                        try {
                            headerPelayananDao.updateAndSave(entity);
                        }catch (HibernateException e){
                            logger.error("error"+e.getMessage());
                            throw new GeneralBOException("Error when save "+e.getMessage());
                        }
                    }else{
                        List<ImSimrsHeaderPelayananEntity> cek = new ArrayList<>();
                        try {
                            cek = headerPelayananDao.getData(bean.getNamaPelayanan());
                        }catch (Exception e){
                            logger.error("error"+e.getMessage());
                            throw new GeneralBOException("Error when save "+e.getMessage());
                        }
                        if(cek.size() > 0){
                            logger.error("error sudah ada nama pelayanan");
                            throw new GeneralBOException("Mohon maaf data pelayanan "+bean.getNamaPelayanan()+" sudah ada...! @_@");
                        }else{
                            entity.setNamaPelayanan(bean.getNamaPelayanan());
                            entity.setTipePelayanan(bean.getTipePelayanan());
                            entity.setKategoriPelayanan(bean.getKategoriPelayanan());
                            entity.setKodeVclaim(bean.getKodeVclaim());
                            entity.setDivisiId(bean.getDivisiId());
                            entity.setIsVaksin(bean.getDivisiId());
                            entity.setFlag(bean.getFlag());
                            entity.setAction(bean.getAction());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            entity.setLastUpdate(bean.getLastUpdate());
                            try {
                                headerPelayananDao.updateAndSave(entity);
                            }catch (HibernateException e){
                                logger.error("error"+e.getMessage());
                                throw new GeneralBOException("Error when save "+e.getMessage());
                            }
                        }
                    }
                }
            }catch (Exception e){
                logger.error("error"+e.getMessage());
                throw new GeneralBOException("Error when edit "+e.getMessage());
            }
        }
    }

    @Override
    public void saveDelete(HeaderPelayanan bean) throws GeneralBOException {
        if(bean != null){
            try {
                ImSimrsHeaderPelayananEntity entity = headerPelayananDao.getById("idHeaderPelayanan", bean.getIdHeaderPelayanan());
                if(entity != null){
                    List<ImSimrsPelayananEntity> cek = new ArrayList<>();
                    try {
                        cek = headerPelayananDao.getDataPelayanan(bean.getIdHeaderPelayanan());
                    }catch (Exception e){
                        logger.error("error"+e.getMessage());
                        throw new GeneralBOException("Error when save "+e.getMessage());
                    }
                    if(cek.size() > 0){
                        logger.error("error");
                        throw new GeneralBOException("Data pelayanan "+bean.getNamaPelayanan()+" sudah ada...! @_@");
                    }else{
                        entity.setFlag(bean.getFlag());
                        entity.setAction(bean.getAction());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setLastUpdate(bean.getLastUpdate());
                        try {
                            headerPelayananDao.updateAndSave(entity);
                        }catch (HibernateException e){
                            logger.error("error"+e.getMessage());
                            throw new GeneralBOException("Error when save "+e.getMessage());
                        }
                    }
                }
            }catch (Exception e){
                logger.error("error"+e.getMessage());
                throw new GeneralBOException("Error when delete "+e.getMessage());
            }
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHeaderPelayananDao(HeaderPelayananDao headerPelayananDao) {
        this.headerPelayananDao = headerPelayananDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }
}
