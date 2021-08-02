package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo.impl;

import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo.PendaftatanJasaRekananBo;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.dao.PendaftaranJasaRekananDao;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.ItAkunPendaftaranJasaEntity;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.PendaftaranJasa;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendaftaranJasaRekananBoImpl implements PendaftatanJasaRekananBo {
    public static transient Logger logger = Logger.getLogger(PendaftaranJasaRekananBoImpl.class);

    private PendaftaranJasaRekananDao pendaftaranJasaRekananDao;

    public void setPendaftaranJasaRekananDao(PendaftaranJasaRekananDao pendaftaranJasaRekananDao) {
        this.pendaftaranJasaRekananDao = pendaftaranJasaRekananDao;
    }

    @Override
    public List<PendaftaranJasa> getSearchByCriteria(PendaftaranJasa bean) throws GeneralBOException {
        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Start >>>");

        List<ItAkunPendaftaranJasaEntity> listEntity = getPendaftaranJasaEntity(bean);

        List<PendaftaranJasa> listOfResult = new ArrayList<>();
        if (listEntity != null && listEntity.size() > 0){
            BeanUtils.copyProperties(listEntity, listOfResult);
        }

        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] End <<<");
        return listOfResult;
    }

    @Override
    public void saveAdd(PendaftaranJasa bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(PendaftaranJasa bean) throws GeneralBOException {

    }

    private List<ItAkunPendaftaranJasaEntity> getPendaftaranJasaEntity(PendaftaranJasa bean) throws GeneralBOException{
        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Start >>>");

        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())){
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getApproveKaKeu() != null && !"".equalsIgnoreCase(bean.getApproveKaKeu())){
                hsCriteria.put("approve_ka_keu", bean.getApproveKaKeu());
            }
            if (bean.getApproveKasubKeu() != null && !"".equalsIgnoreCase(bean.getApproveKasubKeu())){
                hsCriteria.put("approve_kasub_keu", bean.getApproveKasubKeu());
            }
            if (bean.getApproveKeu() != null && !"".equalsIgnoreCase(bean.getApproveKeu())){
                hsCriteria.put("approve_keu", bean.getApproveKeu());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                hsCriteria.put("flag", bean.getFlag());
            }

            List<ItAkunPendaftaranJasaEntity> itAkunPendaftaranJasaEntities = null;
            try {
                itAkunPendaftaranJasaEntities = pendaftaranJasaRekananDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Error.", e);
                throw new GeneralBOException("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Error.", e);

            }

            return itAkunPendaftaranJasaEntities;
        }

        logger.info("[PendaftaranJasaRekananBoImpl.getSearchByCriteria] Start >>>");
        return null;
    }
}
