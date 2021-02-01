package com.neurix.simrs.master.pelayanan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.bo.HeaderPelayananBo;
import com.neurix.simrs.master.pelayanan.dao.HeaderPelayananDao;
import com.neurix.simrs.master.pelayanan.model.HeaderPelayanan;
import com.neurix.simrs.master.pelayanan.model.ImSimrsHeaderPelayananEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HeaderPelayananBoImpl implements HeaderPelayananBo {
    protected static transient Logger logger = Logger.getLogger(HeaderPelayananBoImpl.class);
    private HeaderPelayananDao headerPelayananDao;

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
                headerPelayanan.setFlag(entity.getFlag());
                headerPelayanan.setAction(entity.getAction());
                headerPelayanan.setCreatedDate(entity.getCreatedDate());
                headerPelayanan.setCreatedWho(entity.getCreatedWho());
                headerPelayanan.setLastUpdate(entity.getLastUpdate());
                headerPelayanan.setLastUpdateWho(entity.getLastUpdateWho());
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

    public static Logger getLogger() {
        return logger;
    }

    public void setHeaderPelayananDao(HeaderPelayananDao headerPelayananDao) {
        this.headerPelayananDao = headerPelayananDao;
    }
}
