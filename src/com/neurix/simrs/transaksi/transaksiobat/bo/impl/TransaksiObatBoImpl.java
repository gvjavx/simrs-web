package com.neurix.simrs.transaksi.transaksiobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.RiwayatTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class TransaksiObatBoImpl implements TransaksiObatBo {
    private static transient Logger logger = Logger.getLogger(TransaksiObatBoImpl.class);

    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private RiwayatTransaksiObatDao riwayatTransaksiObatDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private ObatPoliDao obatPoliDao;
    private ObatDao obatDao;

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setRiwayatTransaksiObatDao(RiwayatTransaksiObatDao riwayatTransaksiObatDao) {
        this.riwayatTransaksiObatDao = riwayatTransaksiObatDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }

    @Override
    public List<TransaksiObatDetail> getByCriteria(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getByCriteria] START >>>>>>>");

        List<TransaksiObatDetail> listOfResults = new ArrayList<>();

        if (bean != null){

            List<ImtSimrsTransaksiObatDetailEntity> resepDetail = getListEntityResepDetail(bean);
            if (!resepDetail.isEmpty() && resepDetail.size() > 0)
            {
                TransaksiObatDetail transaksiObatDetail;
                for (ImtSimrsTransaksiObatDetailEntity resepEntity : resepDetail)
                {
                    transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdTransaksiObatDetail(resepEntity.getIdTransaksiObatDetail());
                    transaksiObatDetail.setIdApprovalObat(resepEntity.getIdApprovalObat());
                    transaksiObatDetail.setIdObat(resepEntity.getIdObat());
                    transaksiObatDetail.setQty(resepEntity.getQty());
                    transaksiObatDetail.setKeterangan(resepEntity.getKeterangan());
                    transaksiObatDetail.setFlag(resepEntity.getFlag());
                    transaksiObatDetail.setAction(resepEntity.getAction());
                    transaksiObatDetail.setCreatedDate(resepEntity.getCreatedDate());
                    transaksiObatDetail.setCreatedWho(resepEntity.getCreatedWho());
                    transaksiObatDetail.setLastUpdate(resepEntity.getLastUpdate());
                    transaksiObatDetail.setLastUpdateWho(resepEntity.getLastUpdateWho());

                    ImSimrsObatEntity obatEntity = getObatById(resepEntity.getIdObat());
                    if (obatEntity != null)
                    {
                        transaksiObatDetail.setNamaObat(obatEntity.getNamaObat());
                    }

                    listOfResults.add(transaksiObatDetail);
                }
            }
        }

        logger.info("[TransaksiObatBoImpl.getByCriteria] END <<<<<<<");
        return listOfResults;
    }

    @Override
    public void saveEditDetail(TransaksiObatDetail bean) throws GeneralBOException {

        logger.info("[DiagnosaRawatBoImpl.saveEditDetail] Start >>>>>>>>>");

            if (bean != null){

                ImtSimrsTransaksiObatDetailEntity entity = null;

                try {
                    entity = transaksiObatDetailDao.getById("idTransaksiObatDetail", bean.getIdTransaksiObatDetail());
                } catch (HibernateException e){
                    logger.error("[TeamDokterBoImpl.saveEdit] Error when getById diagnosa rawat ",e);
                    throw new GeneralBOException("[TeamDokterBoImpl.saveEditDetail] Error when save edit diagnosa rawat "+e.getMessage());
                }
                if(entity != null){
                    entity.setIdObat(bean.getIdObat());
                    entity.setQty(bean.getQty());
                    entity.setKeterangan(bean.getKeterangan());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                }

                try {
                    transaksiObatDetailDao.updateAndSave(entity);
                } catch (HibernateException e){
                    logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                    throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
                }
            }

            logger.info("[DiagnosaRawatBoImpl.saveEditDetail] End <<<<<<<<<");
    }

    private List<ImtSimrsTransaksiObatDetailEntity> getListEntityResepDetail(TransaksiObatDetail bean) throws GeneralBOException{
        logger.info("[TransaksiObatBoImpl.getListEntityResep] START >>>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> detailEntityList = new ArrayList<>();

        if (bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())){
                hsCriteria.put("id_transaksi_obat_detail", bean.getIdTransaksiObatDetail());
            }
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
                hsCriteria.put("id_obat", bean.getIdObat());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                detailEntityList = transaksiObatDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[TransaksiObatBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ",e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ",e);
            }
        }

        logger.info("[TransaksiObatBoImpl.getListEntityResep] END <<<<<<<");
        return detailEntityList;
    }

    private ImSimrsObatEntity getObatById(String id) throws GeneralBOException{
        logger.info("[TransaksiObatBoImpl.getObatById] START >>>>>>>>>>");
        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", id);
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getObatById] ERROR when get obat entity by criteria. ",e);
            throw new GeneralBOException("[PermintaanResepBoImpl.getObatById] ERROR when get obat entity by criteria. ",e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0)
        {
            return obatEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getObatById] END <<<<<<<<<<");
        return null;
    }
}
