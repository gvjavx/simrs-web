package com.neurix.simrs.master.obat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.obatgejala.dao.ObatGejalaDao;
import com.neurix.simrs.master.obatgejala.model.ImSimrsObatGejalaEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatBoImpl implements ObatBo {

    protected static transient Logger logger = Logger.getLogger(ObatBoImpl.class);
    private ObatDao obatDao;
    private JenisObatDao jenisObatDao;
    private ObatGejalaDao obatGejalaDao;

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setObatGejalaDao(ObatGejalaDao obatGejalaDao) {
        this.obatGejalaDao = obatGejalaDao;
    }

    public static Logger getLogger() {
        return logger;
    }


    @Override
    public List<Obat> getByCriteria(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getByCriteria] Start >>>>>>>");

        List<Obat> result = new ArrayList<>();
        if (bean != null) {

            List<ImSimrsObatEntity> obatEntityList = getListEntityObat(bean);

            if (!obatEntityList.isEmpty()){
                Obat obat;
                for (ImSimrsObatEntity obatEntity : obatEntityList){
                    obat = new Obat();
                    obat.setIdObat(obatEntity.getIdObat());
                    obat.setIdJenisObat(obatEntity.getIdJenisObat());
                    obat.setNamaObat(obatEntity.getNamaObat());
                    obat.setHarga(obatEntity.getHarga());
                    obat.setFlag(obatEntity.getFlag());
                    obat.setAction(obatEntity.getAction());
                    obat.setCreatedDate(obatEntity.getCreatedDate());
                    obat.setCreatedWho(obatEntity.getCreatedWho());
                    obat.setLastUpdate(obatEntity.getLastUpdate());
                    obat.setLastUpdateWho(obatEntity.getLastUpdateWho());
                    obat.setQty(obatEntity.getQty());
                    obat.setBranchId(obatEntity.getBranchId());
                    obat.setQtyBox(obatEntity.getQtyBox());
                    obat.setLembarPerBox(obatEntity.getLembarPerBox());
                    obat.setQtyLembar(obatEntity.getQtyLembar());
                    obat.setBijiPerLembar(obatEntity.getBijiPerLembar());
                    obat.setQtyBiji(obatEntity.getQtyBiji());
                    obat.setAverageHargaBox(obatEntity.getAverageHargaBox());
                    obat.setAverageHargaLembar(obatEntity.getAverageHargaLembar());
                    obat.setAverageHargaBiji(obatEntity.getAverageHargaBiji());
                    obat.setIdPabrik(obatEntity.getIdPabrik());
                    obat.setMerk(obatEntity.getMerk());

                    List<ImSimrsObatGejalaEntity> obatGejalaEntities = new ArrayList<>();

                    Map hsCriteria = new HashMap();
                    hsCriteria.put("id_obat", obatEntity.getIdObat());
                    hsCriteria.put("flag", "Y");

                    try {
                        obatGejalaEntities = obatGejalaDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e){
                        logger.error("[ObatBoImpl.updateObatGejala] error when get data obat gejala "+ e.getMessage());
                        throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when get data obat gejala "+ e.getMessage());
                    }

                    if (!obatGejalaEntities.isEmpty() && obatGejalaEntities.size() > 0)
                    {
                        String listJenisObat = "";
                        for (ImSimrsObatGejalaEntity gejalaEntity : obatGejalaEntities)
                        {
                            StringBuilder addedScript = new StringBuilder();
                            JenisObat jenisObat = new JenisObat();
                            jenisObat.setIdJenisObat(gejalaEntity.getIdJenisObat());
                            List<ImSimrsJenisObatEntity> jenisObatEntityList = getListEntityJenisObat(jenisObat);

                            if (!jenisObatEntityList.isEmpty())
                            {
                                ImSimrsJenisObatEntity jenisObatEntity = jenisObatEntityList.get(0);
                                if(jenisObatEntity != null)
                                {
                                    listJenisObat = listJenisObat + addedScript.append("<label class=\"label label-primary\">"+jenisObatEntity.getNamaJenisObat()+"</label>");
                                }

                            }
                        }
                        obat.setJenisObat(listJenisObat.toString());
                    }
                    result.add(obat);
                }
            }
            logger.info("[ObatBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[ObatBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }


    public List<ImSimrsJenisObatEntity> getListEntityJenisObat(JenisObat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListEntityJenisObat] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
                hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsJenisObatEntity> jenisObatEntityList = new ArrayList<>();

            try {
                jenisObatEntityList = jenisObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[ObatBoImpl.getListEntityJenisObat] error when get data jenis obat by get by criteria "+ e.getMessage());
            }

            logger.info("[ObatBoImpl.getListEntityJenisObat] End <<<<<<<");
            return jenisObatEntityList;
        }
        logger.info("[ObatBoImpl.getListEntityJenisObat] End <<<<<<<");
        return null;
    }

    @Override
    public List<Obat> getListObatByJenisObat(String idObat, String branchId) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListObatByJenisObat] Start >>>>>>>");

        List<Obat> obats = new ArrayList<>();

        try {
          obats = obatDao.getListObatByJenisObat(idObat, branchId);
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.getListEntityJenisObat] error when get data obat by jenis obat "+ e.getMessage());
        }

        logger.info("[ObatBoImpl.getListObatByJenisObat] End <<<<<<<");
        return obats;
    }

    @Override
    public List<Obat> getJenisObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getJenisObat] Start >>>>>>>");

        List<Obat> obats = new ArrayList<>();

        try {
            obats = obatDao.getJenisObat(bean);
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.getJenisObat] error when get data obat by jenis obat "+ e.getMessage());
        }

        logger.info("[ObatBoImpl.getJenisObat] End <<<<<<<");
        return obats;
    }

    private List<ImSimrsObatEntity> getListEntityObat(Obat bean) throws GeneralBOException{
        logger.info("[ObatBoImpl.getListEntityObat] Start >>>>>>>");

        Map hsCriteria = new HashMap();

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
            hsCriteria.put("id_obat", bean.getIdObat());
        }
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
            hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
        }
        if (bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())) {
            hsCriteria.put("nama_obat", bean.getNamaObat());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            hsCriteria.put("flag", bean.getFlag());
        }
        if (bean.getIdPabrik() != null && !"".equalsIgnoreCase(bean.getIdPabrik())){
            hsCriteria.put("id_pabrik", bean.getIdPabrik());
        }

        List<ImSimrsObatEntity> obatEntityList = new ArrayList<>();
        try {
            obatEntityList = obatDao.getListObatByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.getByCriteria] error when get data obat by get by criteria "+ e.getMessage());
        }

        logger.info("[ObatBoImpl.getListEntityObat] End <<<<<<<");
        return obatEntityList;
    }


    @Override
    public void saveAdd(Obat bean, List<String> idJenisObats) throws GeneralBOException {
        logger.info("[ObatBoImpl.saveAdd] Start >>>>>>>");

        ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        String id = getIdNextObat();
        obatEntity.setIdObat("OBT"+id);
        obatEntity.setNamaObat(bean.getNamaObat());
        obatEntity.setHarga(bean.getHarga());
        obatEntity.setQty(bean.getQty());
        obatEntity.setBranchId(bean.getBranchId());
        obatEntity.setMerk(bean.getMerk());
        obatEntity.setIdPabrik(bean.getIdPabrik());
        obatEntity.setQtyBox(bean.getQtyBox());
        obatEntity.setQtyLembar(bean.getQtyLembar());
        obatEntity.setLembarPerBox(bean.getLembarPerBox());
        obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
        obatEntity.setQtyBiji(bean.getQtyBiji());
        obatEntity.setFlag(bean.getFlag());
        obatEntity.setAction(bean.getAction());
        obatEntity.setCreatedDate(time);
        obatEntity.setCreatedWho(userLogin);
        obatEntity.setLastUpdate(time);
        obatEntity.setLastUpdateWho(userLogin);
        obatEntity.setQtyBox(bean.getQtyBox());
        obatEntity.setLembarPerBox(bean.getLembarPerBox());
        obatEntity.setQtyLembar(bean.getQtyLembar());
        obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
        obatEntity.setQtyBiji(bean.getQtyBiji());
        obatEntity.setAverageHargaBox(bean.getAverageHargaBox());
        obatEntity.setAverageHargaLembar(bean.getAverageHargaLembar());
        obatEntity.setAverageHargaBiji(bean.getAverageHargaBiji());
        obatEntity.setIdPabrik(bean.getIdPabrik());
        obatEntity.setMerk(bean.getMerk());

        try {
            obatDao.addAndSave(obatEntity);
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.saveAdd] error when add data obat "+ e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.saveAdd] error when add data obat "+ e.getMessage());
        }

        if (!idJenisObats.isEmpty() && idJenisObats.size() > 0){
            for (String idJenisObat : idJenisObats)
            {
                ImSimrsObatGejalaEntity obatGejalaEntity = new ImSimrsObatGejalaEntity();

                id = getIdNextObatGejala();

                obatGejalaEntity.setIdObatGejala("OGJ"+id);
                obatGejalaEntity.setIdObat(obatEntity.getIdObat());
                obatGejalaEntity.setIdJenisObat(idJenisObat);
                obatGejalaEntity.setFlag(bean.getFlag());
                obatGejalaEntity.setAction(bean.getAction());
                obatGejalaEntity.setCreatedDate(time);
                obatGejalaEntity.setCreatedWho(userLogin);
                obatGejalaEntity.setLastUpdate(time);
                obatGejalaEntity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.addAndSave(obatGejalaEntity);
                } catch (HibernateException e){
                    logger.error("[ObatBoImpl.saveAdd] error when insert new obat gejala "+ e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.saveAdd] error when insert new obat gejala "+ e.getMessage());
                }
            }
        }
        logger.info("[ObatBoImpl.saveAdd] End <<<<<<<");
    }

    @Override
    public void saveEdit(Obat bean, List<String> idJenisObats) throws GeneralBOException {
        logger.info("[ObatBoImpl.saveEdit] Start >>>>>>>");

        List<ImSimrsObatEntity> obatEntities = getListEntityObat(bean);

        if (!obatEntities.isEmpty() && obatEntities.size() > 0)
        {
            ImSimrsObatEntity obatEntity = obatEntities.get(0);
            obatEntity.setNamaObat(bean.getNamaObat());
            obatEntity.setQty(bean.getQty());
            obatEntity.setHarga(bean.getHarga());
            obatEntity.setLastUpdate(bean.getLastUpdate());
            obatEntity.setLastUpdateWho(bean.getLastUpdateWho());
            obatEntity.setQtyBox(bean.getQtyBox());
            obatEntity.setLembarPerBox(bean.getLembarPerBox());
            obatEntity.setQtyLembar(bean.getQtyLembar());
            obatEntity.setBijiPerLembar(bean.getBijiPerLembar());
            obatEntity.setQtyBiji(bean.getQtyBiji());
            obatEntity.setAverageHargaBox(bean.getAverageHargaBox());
            obatEntity.setAverageHargaLembar(bean.getAverageHargaLembar());
            obatEntity.setAverageHargaBiji(bean.getAverageHargaBiji());
            obatEntity.setIdPabrik(bean.getIdPabrik());
            obatEntity.setMerk(bean.getMerk());

            try {
                obatDao.updateAndSave(obatEntity);
            } catch (HibernateException e){
                logger.error("[ObatBoImpl.saveEdit] error when update data obat "+ e.getMessage());
                throw new GeneralBOException("[ObatBoImpl.saveEdit] error when update data obat "+ e.getMessage());
            }

            updateObatGejala(idJenisObats, bean.getIdObat());
        }
    }

    @Override
    public List<Obat> getListNamaObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListNamaObat] Start >>>>>>>");
        List<Obat> listOfResult = new ArrayList();
        try {
            listOfResult = obatDao.getListNamaObat(bean);
        } catch (HibernateException e) {
            logger.error("[ObatBoImpl.getListNamaObat Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[ObatBoImpl.getListNamaObat] End >>>>>>>");
        return listOfResult;

    }

    private void updateObatGejala(List<String> idJenisObats, String idObat) throws GeneralBOException{
        logger.info("[ObatBoImpl.updateObatGejala] Start >>>>>>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();

        List<ImSimrsObatGejalaEntity> obatGejalaEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", idObat);
        hsCriteria.put("flag", "Y");

        try {
            obatGejalaEntities = obatGejalaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.updateObatGejala] error when get data obat gejala "+ e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when get data obat gejala "+ e.getMessage());
        }

        if (!obatGejalaEntities.isEmpty() && obatGejalaEntities.size() > 0)
        {
            for (ImSimrsObatGejalaEntity gejalaEntity : obatGejalaEntities)
            {
                gejalaEntity.setFlag("N");
                gejalaEntity.setAction("U");
                gejalaEntity.setCreatedDate(time);
                gejalaEntity.setCreatedWho(userLogin);
                gejalaEntity.setLastUpdate(time);
                gejalaEntity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.updateAndSave(gejalaEntity);
                } catch (HibernateException e){
                    logger.error("[ObatBoImpl.updateObatGejala] error when update flag N obat gejala "+ e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when update flag N obat gejala "+ e.getMessage());
                }
            }
        }

        for (String idJenisObat : idJenisObats)
        {
            obatGejalaEntities = new ArrayList<>();
            hsCriteria = new HashMap();

            hsCriteria.put("id_obat", idObat);
            hsCriteria.put("id_jenis_obat", idJenisObat);

            try {
                obatGejalaEntities = obatGejalaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[ObatBoImpl.updateObatGejala] error when get data obat gejala "+ e.getMessage());
                throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when get data obat gejala "+ e.getMessage());
            }

            if (!obatGejalaEntities.isEmpty() && obatGejalaEntities.size() > 0)
            {
                ImSimrsObatGejalaEntity entity = obatGejalaEntities.get(0);

                entity.setFlag("Y");
                entity.setAction("U");
                entity.setCreatedDate(time);
                entity.setCreatedWho(userLogin);
                entity.setLastUpdate(time);
                entity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.updateAndSave(entity);
                } catch (HibernateException e){
                    logger.error("[ObatBoImpl.updateObatGejala] error when update flag Y obat gejala "+ e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when when update flag Y obat gejala "+ e.getMessage());
                }
            }
            else
            {
                ImSimrsObatGejalaEntity entity = new ImSimrsObatGejalaEntity();

                String id = getIdNextObatGejala();

                entity.setIdObatGejala("OGJ"+id);
                entity.setIdObat(idObat);
                entity.setIdJenisObat(idJenisObat);
                entity.setFlag("Y");
                entity.setAction("U");
                entity.setCreatedDate(time);
                entity.setCreatedWho(userLogin);
                entity.setLastUpdate(time);
                entity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.addAndSave(entity);
                } catch (HibernateException e){
                    logger.error("[ObatBoImpl.updateObatGejala] error when insert new obat gejala "+ e.getMessage());
                    throw new GeneralBOException("[ObatBoImpl.updateObatGejala] error when insert new obat gejala "+ e.getMessage());
                }
            }
        }
        logger.info("[ObatBoImpl.updateObatGejala] End <<<<<<<");
    }

    @Override
    public CheckObatResponse checkObatStockLama(String idObat, String branchId) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkObatStockLama] START >>>>>>>>>>");
        logger.info("[ObatPoliBoImpl.checkObatStockLama] END <<<<<<<<<<");
        return null;
    }

    @Override
    public CheckObatResponse checkFisikObat(Obat bean) throws GeneralBOException {
        logger.info("[ObatPoliBoImpl.checkFisikObat] START >>>>>>>>>>");

        CheckObatResponse response = new CheckObatResponse();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_obat", bean.getIdObat());
        hsCriteria.put("id_pabrik", bean.getIdPabrik());
        hsCriteria.put("branch_id", bean.getBranchId());
        hsCriteria.put("desc", "Y");
        hsCriteria.put("flag", "Y");


        List<ImSimrsObatEntity> obatEntities = new ArrayList<>();
        try {
            obatEntities = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.checkFisikObat] error when check fisik obat"+ e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.checkFisikObat] check fisik obat "+ e.getMessage());
        }

        if (obatEntities.size() > 0){
            ImSimrsObatEntity obatEntity = obatEntities.get(0);

            if (obatEntity.getLembarPerBox().compareTo(bean.getLembarPerBox()) == 0 && obatEntity.getBijiPerLembar().compareTo(bean.getBijiPerLembar()) == 0) {
                response.setStatus("success");
                response.setMessage("Obat Sesuai");
            } else {
                response.setStatus("warning");

                StringBuilder r = new StringBuilder();
                r.append("Obat teridentifikasi berubah fisik");
                r.append("\n");
                r.append("Kode produksi : "+obatEntity.getIdPabrik());
                r.append("Nama : "+obatEntity.getNamaObat());
                r.append("Merk : "+obatEntity.getMerk());
                r.append("\n");
                r.append("Lembar/Box : "+obatEntity.getLembarPerBox());
                r.append("Biji/Lembar : "+obatEntity.getBijiPerLembar());
                r.append("\n");
                r.append("Tgl diterima : "+obatEntity.getCreatedDate());

                response.setMessage(String.valueOf(r));
            }
        } else {
            response.setStatus("new");

            StringBuilder r = new StringBuilder();
            r.append("Obat teridentifikasi baru");
            r.append("Karna tidak ditemukan kecocokan kode produksi pada data master");
            response.setMessage(String.valueOf(r));
        }

        logger.info("[ObatPoliBoImpl.checkFisikObat] END <<<<<<<<<<");
        return response;
    }

    private String getIdNextObatGejala() throws GeneralBOException{
        String id = "";

        try {
            id = obatGejalaDao.getNextId();
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.getIdNextObatGejala] ERROR WHEN GET data id obat gejala, "+e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getIdNextObatGejala] ERROR WHEN GET data id obat gejala, "+e.getMessage());
        }

        return id;
    }

    private String getIdNextObat() throws GeneralBOException{
        String id = "";

        try {
            id = obatDao.getNextId();
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.getIdNextObat] ERROR WHEN GET data id obat, "+e.getMessage());
            throw new GeneralBOException("[ObatBoImpl.getIdNextObat] ERROR WHEN GET data id obat, "+e.getMessage());
        }

        return id;
    }

}