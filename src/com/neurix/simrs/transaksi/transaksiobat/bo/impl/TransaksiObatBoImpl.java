package com.neurix.simrs.transaksi.transaksiobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.vendor.dao.VendorDao;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.RiwayatTransPembelianObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.RiwayatTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Toshiba on 11/12/2019.
 */
public class TransaksiObatBoImpl implements TransaksiObatBo {
    private static transient Logger logger = Logger.getLogger(TransaksiObatBoImpl.class);

    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private RiwayatTransaksiObatDao riwayatTransaksiObatDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private PelayananDao pelayananDao;
    private ObatDao obatDao;
    private PermintaanResepDao permintaanResepDao;
    private PasienDao pasienDao;
    private RiwayatTransPembelianObatDao riwayatTransPembelianObatDao;
    private ObatPoliDao obatPoliDao;
    private VendorDao vendorDao;

    @Override
    public List<TransaksiObatDetail> getSearchObatTransaksiByCriteria(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getSearchObatTransaksiByCriteria] START >>>>>>");

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = getListEntityTransObatDetail(bean);

        if (obatDetailEntities.size() > 0)
        {
            TransaksiObatDetail transaksiObatDetail;
            for (ImtSimrsTransaksiObatDetailEntity obatDetailEntity : obatDetailEntities)
            {
                transaksiObatDetail = new TransaksiObatDetail();
                transaksiObatDetail.setIdTransaksiObatDetail(obatDetailEntity.getIdTransaksiObatDetail());
                transaksiObatDetail.setIdObat(obatDetailEntity.getIdObat());
                transaksiObatDetail.setIdApprovalObat(obatDetailEntity.getIdApprovalObat());
                transaksiObatDetail.setQty(obatDetailEntity.getQty());
                transaksiObatDetail.setFlag(obatDetailEntity.getFlag());
                transaksiObatDetail.setAction(obatDetailEntity.getAction());
                transaksiObatDetail.setCreatedDate(obatDetailEntity.getCreatedDate());
                transaksiObatDetail.setCreatedWho(obatDetailEntity.getCreatedWho());
                transaksiObatDetail.setLastUpdate(obatDetailEntity.getLastUpdate());
                transaksiObatDetail.setLastUpdateWho(obatDetailEntity.getLastUpdateWho());

                ImSimrsObatEntity obatEntity = getObatById(obatDetailEntity.getIdObat());
                if (obatEntity != null)
                {
                    transaksiObatDetail.setNamaObat(obatEntity.getNamaObat());
                    transaksiObatDetail.setHarga(obatEntity.getHarga());

                    // harga*qty
                    BigInteger total = obatEntity.getHarga().multiply(transaksiObatDetail.getQty());
                    transaksiObatDetail.setTotalHarga(total);
                }

                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdApprovalObat(transaksiObatDetail.getIdApprovalObat());
                ImSimrsPermintaanResepEntity resepEntity = getEntityPermintaanResepByCriteria(permintaanResep);
                if (resepEntity != null)
                {
                    ImSimrsPasienEntity pasienEntity = getEntityPasienById(resepEntity.getIdPasien());
                    if (pasienEntity != null)
                    {
                        transaksiObatDetail.setNamaPasien(pasienEntity.getNama());
                    }
                }

                obatDetailList.add(transaksiObatDetail);
            }
        }

        logger.info("[TransaksiObatBoImpl.getSearchObatTransaksiByCriteria] END <<<<<");
        return obatDetailList;
    }

    @Override
    public void saveAdd(TransaksiObatDetail bean, List<TransaksiObatDetail> listOfObatResep, List<TransaksiObatDetail> listOfObat) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.saveAdd] START >>>>>>");

        List<ImtSimrsRiwayatTransaksiObatEntity> listOfTransaksi = new ArrayList<>();
        if (bean != null)
        {
            if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())){
                // update flag list of detail obat for resep
                if (listOfObatResep != null && listOfObatResep.size() > 0)
                {
                    for (TransaksiObatDetail resep : listOfObatResep)
                    {
                        ImtSimrsTransaksiObatDetailEntity obatDetailEntity = getEntityTransObatDetailById(resep.getIdTransaksiObatDetail());
                        obatDetailEntity.setQty(resep.getQty());
                        obatDetailEntity.setFlag("N");
                        obatDetailEntity.setAction("U");
                        obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                        obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            transaksiObatDetailDao.updateAndSave(obatDetailEntity);
                        } catch (HibernateException e){
                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
                        }

                        // kurangi stock obat
                        ImSimrsObatEntity obatEntity = getObatById(resep.getIdObat());
                        if (obatEntity != null){

                            obatEntity.setQty(obatEntity.getQty().subtract(resep.getQty()));
                            obatEntity.setAction("U");
                            obatEntity.setLastUpdate(bean.getLastUpdate());
                            obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            try {

                            } catch (HibernateException e)
                            {
                                logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
                                throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
                            }
                        }
                    }

                    PermintaanResep permintaanResep = new PermintaanResep();
                    permintaanResep.setIdPermintaanResep(bean.getIdPermintaanResep());
                    // update flag permintaan resep data
                    ImSimrsPermintaanResepEntity resepEntity = getEntityPermintaanResepByCriteria(permintaanResep);
                    if (resepEntity != null)
                    {
                        resepEntity.setFlag("N");
                        resepEntity.setAction("U");
                        resepEntity.setLastUpdate(bean.getLastUpdate());
                        resepEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            permintaanResepDao.addAndSave(resepEntity);
                        } catch (HibernateException e){
                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
                        }
                    }
                    // updarte approval transaksi for resep
                    ImtSimrsApprovalTransaksiObatEntity approvalEntity = getApprovalTransaksiById(resepEntity.getIdApprovalObat());
                    if (approvalEntity != null)
                    {
                        approvalEntity.setApprovalFlag("Y");
                        approvalEntity.setApprovePerson(bean.getLastUpdateWho());
                        approvalEntity.setFlag("N");
                        approvalEntity.setAction("U");
                        approvalEntity.setLastUpdate(bean.getLastUpdate());
                        approvalEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            approvalTransaksiObatDao.updateAndSave(approvalEntity);
                        } catch (HibernateException e){
                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
                        }
                    }
                    // record to list riwayat transaksi
                    ImtSimrsRiwayatTransaksiObatEntity transaksiObatEntity = new ImtSimrsRiwayatTransaksiObatEntity();
                    transaksiObatEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                    transaksiObatEntity.setTipePermintaan(approvalEntity.getTipePermintaan());
                    transaksiObatEntity.setFlag("Y");
                    transaksiObatEntity.setAction("C");
                    transaksiObatEntity.setCreatedDate(bean.getLastUpdate());
                    transaksiObatEntity.setCreatedWho(bean.getLastUpdateWho());
                    transaksiObatEntity.setLastUpdate(bean.getLastUpdate());
                    transaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    listOfTransaksi.add(transaksiObatEntity);
                }
            }

            // if list obat pembelian not null
            if (listOfObat != null && listOfObat.size() > 0)
            {
                ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
                String id = getNextApprovalObatId();
                approvalEntity.setIdApprovalObat("INV"+id);
                approvalEntity.setApprovalFlag("Y");
                approvalEntity.setTipePermintaan("000");
                approvalEntity.setApprovePerson(bean.getLastUpdateWho());
                approvalEntity.setFlag("N");
                approvalEntity.setAction("U");
                approvalEntity.setCreatedDate(bean.getLastUpdate());
                approvalEntity.setCreatedWho(bean.getLastUpdateWho());
                approvalEntity.setLastUpdate(bean.getLastUpdate());
                approvalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                approvalEntity.setBranchId(bean.getBranchId());

                try {
                    approvalTransaksiObatDao.addAndSave(approvalEntity);
                } catch (HibernateException e){
                    logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
                    throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
                }

                for (TransaksiObatDetail obatDetail : listOfObat)
                {
                    ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                    id = getNextTransaksiObatDetail();
                    obatDetailEntity.setIdTransaksiObatDetail("ODT"+id);
                    obatDetailEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                    obatDetailEntity.setIdObat(obatDetail.getIdObat());
                    obatDetailEntity.setFlag("N");
                    obatDetailEntity.setAction("C");
                    obatDetailEntity.setQty(obatDetail.getQty());
                    obatDetailEntity.setCreatedDate(bean.getLastUpdate());
                    obatDetailEntity.setCreatedWho(bean.getLastUpdateWho());
                    obatDetailEntity.setLastUpdate(bean.getLastUpdate());
                    obatDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        transaksiObatDetailDao.addAndSave(obatDetailEntity);
                    } catch (HibernateException e){
                        logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
                        throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
                    }

                    // kurangi stock obat
                    ImSimrsObatEntity obatEntity = getObatById(obatDetail.getIdObat());
                    if (obatEntity != null){

                        obatEntity.setQty(obatEntity.getQty().subtract(obatDetail.getQty()));
                        obatEntity.setAction("U");
                        obatEntity.setLastUpdate(bean.getLastUpdate());
                        obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {

                        } catch (HibernateException e)
                        {
                            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. ",e);
                            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when save pembayaran. "+ e.getMessage());
                        }
                    }
                }

                // record to list riwayat transaksi
                ImtSimrsRiwayatTransaksiObatEntity transaksiObatEntity = new ImtSimrsRiwayatTransaksiObatEntity();
                transaksiObatEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                transaksiObatEntity.setTipePermintaan(approvalEntity.getTipePermintaan());
                transaksiObatEntity.setFlag("Y");
                transaksiObatEntity.setAction("C");
                transaksiObatEntity.setCreatedDate(bean.getLastUpdate());
                transaksiObatEntity.setCreatedWho(bean.getLastUpdateWho());
                transaksiObatEntity.setLastUpdate(bean.getLastUpdate());
                transaksiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                listOfTransaksi.add(transaksiObatEntity);
            }
        }

        // save riwayat transaksi
        saveRiwayatTransaksi(listOfTransaksi, bean);
        logger.info("[TransaksiObatBoImpl.saveAdd] END <<<<<");
    }

    private void saveRiwayatTransaksi(List<ImtSimrsRiwayatTransaksiObatEntity> beans, TransaksiObatDetail bean) throws GeneralBOException{
        logger.info("[TransaksiObatBoImpl.saveRiwayatTransaksi] START >>>>>>");

        DateFormat dateFormat   = new SimpleDateFormat("yyyyMMdd");
        Date now                = new Date();
        String stDate           = dateFormat.format(now);
        String id               = getNextTransPembelian();
        String noPemebelian     = bean.getBranchId()+stDate+id;
        Integer compare         = bean.getTotalBayar().compareTo(bean.getNominal());

        if (bean.getNominal() == null)
            bean.setNominal(new BigInteger(String.valueOf(0)));
        if (bean.getKembalian() == null)
            bean.setKembalian(new BigInteger(String.valueOf(0)));
        if (compare == -1)
            bean.setKembalian(bean.getNominal().subtract(bean.getTotalBayar()));

        MtSimrsRiwayatPembelianObat pembelianObat = new MtSimrsRiwayatPembelianObat();
        pembelianObat.setNoPembelianObat(noPemebelian);
        pembelianObat.setTotalBayar(bean.getTotalBayar());
        pembelianObat.setNominal(bean.getNominal());
        pembelianObat.setTotalDibayar(bean.getNominal().subtract(bean.getKembalian()));
        pembelianObat.setKembalian(bean.getKembalian());
        pembelianObat.setFlag("Y");
        pembelianObat.setAction("C");
        pembelianObat.setCreatedDate(bean.getLastUpdate());
        pembelianObat.setCreatedWho(bean.getLastUpdateWho());

        try {
            riwayatTransPembelianObatDao.addAndSave(pembelianObat);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. "+ e.getMessage());
        }

        for (ImtSimrsRiwayatTransaksiObatEntity obatEntity : beans)
        {
            id = getNextRiwayatTransaksiId();
            obatEntity.setIdRiwayatTransaksiObat("ODT"+id);
            obatEntity.setNoTransaksiPembelian(pembelianObat.getNoPembelianObat());

            try {
                riwayatTransaksiObatDao.addAndSave(obatEntity);
            } catch (HibernateException e){
                logger.error("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. ",e);
                throw new GeneralBOException("[TransaksiObatBoImpl.saveRiwayatTransaksi] ERROR when save pembayaran. "+ e.getMessage());
            }
        }
        logger.info("[TransaksiObatBoImpl.saveRiwayatTransaksi] END <<<<<");
    }

    private List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetail(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getListEntityTransObatDetail] START >>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

        try {
            obatDetailEntities = transaksiObatDetailDao.getListEntityTransObatDetails(bean);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getListEntityTransObatDetail] ERROR when get data list entity of trans obat detail. "+ e.getMessage());
        }

        logger.info("[TransaksiObatBoImpl.getListEntityTransObatDetail] END <<<<<");
        return obatDetailEntities;
    }

    private ImtSimrsTransaksiObatDetailEntity getEntityTransObatDetailById(String id) throws GeneralBOException {
        logger.info("[TransaksiObatBoImpl.getEntityTransObatDetailById] START >>>>>>");

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_transaksi_obat_detail", id);
        hsCriteria.put("flag", "Y");

        try {
            obatDetailEntities = transaksiObatDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getEntityTransObatDetailById] ERROR when get data entity of trans obat detail. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getEntityTransObatDetailById] ERROR when get data entity of trans obat detail. "+ e.getMessage());
        }

        if (!obatDetailEntities.isEmpty() && obatDetailEntities.size() > 0)
        {
            logger.info("[TransaksiObatBoImpl.getEntityTransObatDetailById] END <<<<<");
            return obatDetailEntities.get(0);
        }
        logger.info("[TransaksiObatBoImpl.getEntityTransObatDetailById] END <<<<<");
        return null;
    }

    private ImtSimrsApprovalTransaksiObatEntity getApprovalTransaksiById(String id) throws GeneralBOException{
        logger.info("[TransaksiObatBoImpl.getApprovalTransaksiById] START >>>>>>>>>>");

        List<ImtSimrsApprovalTransaksiObatEntity> obatEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_approval_obat", id);
        hsCriteria.put("flag", "Y");

        try {
            obatEntities = approvalTransaksiObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getApprovalTransaksiById] ERROR when get approval transaksi obat entity by criteria. ",e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0)
        {
            return obatEntities.get(0);
        }
        logger.info("[TransaksiObatBoImpl.getApprovalTransaksiById] END <<<<<<<<<<");
        return null;
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
            throw new GeneralBOException("[TransaksiObatBoImpl.getObatById] ERROR when get obat entity by criteria. ",e);
        }

        if (!obatEntities.isEmpty() && obatEntities.size() > 0)
        {
            return obatEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getObatById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsPelayananEntity getPoliById(String id) throws GeneralBOException{
        logger.info("[TransaksiObatBoImpl.getPoliById] START >>>>>>>>>>");
        List<ImSimrsPelayananEntity> pelayananEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pelayanan", id);
        hsCriteria.put("flag", "Y");

        try {
            pelayananEntities = pelayananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getPoliById] ERROR when get data poli by criteria. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getPoliById] ERROR when get data poli by criteria. ",e);
        }

        if (!pelayananEntities.isEmpty() && pelayananEntities.size() > 0)
        {
            return pelayananEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getPoliById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsPermintaanResepEntity getEntityPermintaanResepByCriteria(PermintaanResep bean) throws GeneralBOException{
        logger.info("[TransaksiObatBoImpl.getEntityPermintaanResepById] START >>>>>>>>>>");
        List<ImSimrsPermintaanResepEntity> resepEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
            hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
        }
        if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())){
            hsCriteria.put("id_permintaan_resep", bean.getIdPermintaanResep());
        }
        hsCriteria.put("flag", "Y");

        try {
            resepEntities = permintaanResepDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getEntityPermintaanResepById] ERROR when get data resep header by criteria. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPermintaanResepById] ERROR when get data resep header  by criteria. ",e);
        }

        if (!resepEntities.isEmpty() && resepEntities.size() > 0)
        {
            return resepEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getEntityPermintaanResepById] END <<<<<<<<<<");
        return null;
    }

    private ImSimrsPasienEntity getEntityPasienById(String id) throws GeneralBOException{
        logger.info("[TransaksiObatBoImpl.getEntityPasienById] START >>>>>>>>>>");
        List<ImSimrsPasienEntity> pasienEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pasien", id);
        hsCriteria.put("flag", "Y");

        try {
            pasienEntities = pasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getEntityPasienById] ERROR when get data pasien by criteria. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getEntityPasienById] ERROR when get data pasien by criteria. ",e);
        }

        if (!pasienEntities.isEmpty() && pasienEntities.size() > 0)
        {
            return pasienEntities.get(0);
        }

        logger.info("[TransaksiObatBoImpl.getEntityPasienById] END <<<<<<<<<<");
        return null;
    }

    private String getNextApprovalObatId() throws GeneralBOException{
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getNextApprovalObatId] ERROR when get next seq. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextApprovalObatId] ERRO Rwhen get next seq. ",e);
        }
        return id;
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException{
        String id = "";
        try {
            id = transaksiObatDetailDao.getNextId();
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getNextTransaksiObatDetail] ERROR when get next seq. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextTransaksiObatDetail] ERRO Rwhen get next seq. ",e);
        }
        return id;
    }

    private String getNextRiwayatTransaksiId() throws GeneralBOException{
        String id = "";
        try {
            id = riwayatTransaksiObatDao.getNextId();
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getNextRiwayatTransaksiId] ERROR when get next seq. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextRiwayatTransaksiId] ERRO Rwhen get next seq. ",e);
        }
        return id;
    }

    private String getNextTransPembelian() throws GeneralBOException{
        String id = "";
        try {
            id = riwayatTransPembelianObatDao.getNextId();
        } catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.getNextTransPembelian] ERROR when get next seq. ",e);
            throw new GeneralBOException("[TransaksiObatBoImpl.getNextTransPembelian] ERRO Rwhen get next seq. ",e);
        }
        return id;
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

    @Override
    public List<PermintaanResep> getListResepPasien(PermintaanResep bean) throws GeneralBOException {
        List<PermintaanResep> permintaanResepList = new ArrayList<>();

        if(bean != null){
            try {
                permintaanResepList = transaksiObatDetailDao.getListResepPasien(bean);
            }catch (HibernateException e){
                logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
            }
        }

        return permintaanResepList;
    }

    @Override
    public void saveAntrianResep(PermintaanResep bean) throws GeneralBOException {

        ImSimrsPermintaanResepEntity entity = new ImSimrsPermintaanResepEntity();

        try {
            entity = permintaanResepDao.getById("idPermintaanResep", bean.getIdPermintaanResep());
        }catch (HibernateException e){
            logger.error("[TransaksiObatBoImpl.saveAntrianResep] Error when get by id resep ", e);
            throw new GeneralBOException("Error when get by ud resep " + e.getMessage());
        }

        if(entity != null){

            if("Y".equalsIgnoreCase(bean.getIsUmum())){
                entity.setStatus("0");
            }else{
                entity.setStatus("3");
            }

            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setAction(bean.getAction());
            entity.setIsUmum(bean.getIsUmum());
            entity.setTglAntrian(bean.getTglAntrian());

            try{
                permintaanResepDao.updateAndSave(entity);
            }catch (HibernateException e){
                logger.error("[TransaksiObatBoImpl.saveAntrianResep] Error when update resep ", e);
                throw new GeneralBOException("Error when update resep " + e.getMessage());
            }
        }

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


    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setRiwayatTransaksiObatDao(RiwayatTransaksiObatDao riwayatTransaksiObatDao) {
        this.riwayatTransaksiObatDao = riwayatTransaksiObatDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setPermintaanResepDao(PermintaanResepDao permintaanResepDao) {
        this.permintaanResepDao = permintaanResepDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setRiwayatTransPembelianObatDao(RiwayatTransPembelianObatDao riwayatTransPembelianObatDao) {
        this.riwayatTransPembelianObatDao = riwayatTransPembelianObatDao;
    }

    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }

    public void setVendorDao(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }
}
