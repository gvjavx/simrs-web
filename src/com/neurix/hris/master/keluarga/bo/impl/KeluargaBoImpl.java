package com.neurix.hris.master.keluarga.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.keluarga.bo.KeluargaBo;
import com.neurix.hris.master.keluarga.dao.KeluargaDao;
import com.neurix.hris.master.keluarga.model.Keluarga;
import com.neurix.hris.master.keluarga.model.ImKeluargaEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class KeluargaBoImpl implements KeluargaBo {

    protected static transient Logger logger = Logger.getLogger(KeluargaBoImpl.class);
    private KeluargaDao keluargaDao;
    private BiodataDao biodataDao;

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KeluargaBoImpl.logger = logger;
    }

    public KeluargaDao getKeluargaDao() {
        return keluargaDao;
    }


    public void setKeluargaDao(KeluargaDao keluargaDao) {
        this.keluargaDao = keluargaDao;
    }

    @Override
    public void saveDelete(Keluarga bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String keluargaId = bean.getKeluargaId();

            ImKeluargaEntity imKeluargaEntity = null;

            try {
                // Get data from database by ID
                imKeluargaEntity = keluargaDao.getById("keluargaId", keluargaId);
            } catch (HibernateException e) {
                logger.error("[KeluargaBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imKeluargaEntity != null) {

                imKeluargaEntity.setFlag(bean.getFlag());
                imKeluargaEntity.setAction(bean.getAction());
                imKeluargaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imKeluargaEntity.setLastUpdate(bean.getLastUpdate());

                // update jumlah anak keluarga
                if (imKeluargaEntity.getStatusKeluarga().contains("A")){
                    ImBiodataEntity biodataEntity = biodataDao.getById("nip",imKeluargaEntity.getNip());
                    BigInteger jumlahAnak = biodataEntity.getJumlahAnak();
                    BigInteger totJumlahAnak = jumlahAnak.subtract(BigInteger.ONE);
                    biodataEntity.setJumlahAnak(totJumlahAnak);
                    biodataDao.updateAndSave(biodataEntity);
                }

                try {
                    // Delete (Edit) into database
                    keluargaDao.updateAndSave(imKeluargaEntity);
                } catch (HibernateException e) {
                    logger.error("[KeluargaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Keluarga, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[KeluargaBoImpl.saveDelete] Error, not found data Keluarga with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Keluarga with request id, please check again your data ...");

            }
        }
        logger.info("[KeluargaBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Keluarga bean) throws GeneralBOException {
        logger.info("[KeluargaBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String keluargaId = bean.getKeluargaId();

            ImKeluargaEntity imKeluargaEntity = null;
            try {
                // Get data from database by ID
                imKeluargaEntity = keluargaDao.getById("keluargaId", keluargaId);
            } catch (HibernateException e) {
                logger.error("[KeluargaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Keluarga by Kode Keluarga, please inform to your admin...," + e.getMessage());
            }

            if (imKeluargaEntity != null) {
                imKeluargaEntity.setKeluargaId(bean.getKeluargaId());
                imKeluargaEntity.setName(bean.getName());

                imKeluargaEntity.setTanggalLahir(bean.getTanggalLahir());
                imKeluargaEntity.setStatusKeluarga(bean.getStatusKeluargaId());
                imKeluargaEntity.setGender(bean.getGender());

                imKeluargaEntity.setAction(bean.getAction());
                imKeluargaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imKeluargaEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    keluargaDao.updateAndSave(imKeluargaEntity);

                } catch (HibernateException e) {
                    logger.error("[KeluargaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Keluarga, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KeluargaBoImpl.saveEdit] Error, not found data Keluarga with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Keluarga with request id, please check again your data ...");
//                condition = "Error, not found data Keluarga with request id, please check again your data ...";
            }
        }
        logger.info("[KeluargaBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Keluarga saveAdd(Keluarga bean) throws GeneralBOException {
        logger.info("[KeluargaBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String keluargaId;
            try {
                // Generating ID, get from postgre sequence
                keluargaId = keluargaDao.getNextKeluargaId();
            } catch (HibernateException e) {
                logger.error("[KeluargaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence keluargaId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImKeluargaEntity imKeluargaEntity = new ImKeluargaEntity();

            imKeluargaEntity.setKeluargaId(keluargaId);
            imKeluargaEntity.setName(bean.getName());
            imKeluargaEntity.setNip(bean.getNip());
            imKeluargaEntity.setGender(bean.getGender());
            imKeluargaEntity.setTanggalLahir(bean.getTanggalLahir());
            imKeluargaEntity.setStTanggalLahir(bean.getStTanggalLahir());
            imKeluargaEntity.setStatusKeluarga(bean.getStatusKeluargaId());

            imKeluargaEntity.setFlag(bean.getFlag());
            imKeluargaEntity.setAction(bean.getAction());
            imKeluargaEntity.setCreatedWho(bean.getCreatedWho());
            imKeluargaEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imKeluargaEntity.setCreatedDate(bean.getCreatedDate());
            imKeluargaEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                keluargaDao.addAndSave(imKeluargaEntity);
            } catch (HibernateException e) {
                logger.error("[KeluargaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Keluarga, please info to your admin..." + e.getMessage());
            }

            // update jumlah anak keluarga
            if (bean.getStatusKeluargaId().contains("A")){
                ImBiodataEntity biodataEntity = biodataDao.getById("nip",bean.getNip());
                BigInteger jumlahAnak = BigInteger.ZERO;
                if(biodataEntity.getJumlahAnak()!=null)
                    jumlahAnak = biodataEntity.getJumlahAnak();
                BigInteger totJumlahAnak = jumlahAnak.add(BigInteger.ONE);
                biodataEntity.setJumlahAnak(totJumlahAnak);
                biodataDao.updateAndSave(biodataEntity);
            }
        }

        logger.info("[KeluargaBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Keluarga> getByCriteria(Keluarga searchBean) throws GeneralBOException {
        logger.info("[KeluargaBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Keluarga> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getKeluargaId() != null && !"".equalsIgnoreCase(searchBean.getKeluargaId())) {
                hsCriteria.put("keluarga_id", searchBean.getKeluargaId());
            }

            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImKeluargaEntity> imKeluargaEntity = null;
            try {

                imKeluargaEntity = keluargaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[KeluargaBoImpl.getSearchKeluargaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imKeluargaEntity != null){
                Keluarga returnKeluarga;
                // Looping from dao to object and save in collection
                for(ImKeluargaEntity keluargaEntity : imKeluargaEntity){
                    returnKeluarga = new Keluarga();
                    returnKeluarga.setKeluargaId(keluargaEntity.getKeluargaId());
                    returnKeluarga.setName(keluargaEntity.getName());
                    returnKeluarga.setNip(keluargaEntity.getNip());

                    if(keluargaEntity.getGender() != null && !keluargaEntity.getGender().equalsIgnoreCase("")){
                        returnKeluarga.setGender(keluargaEntity.getGender());
                    }else{
                        returnKeluarga.setGender("-");
                    }

                    returnKeluarga.setTanggalLahir(keluargaEntity.getTanggalLahir());
                    returnKeluarga.setStatusKeluargaId(keluargaEntity.getStatusKeluarga());

                    if(keluargaEntity.getStatusKeluarga() != null){
                        if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("I")){
                            returnKeluarga.setStatusKeluargaName("Istri");
                        }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("S")){
                            returnKeluarga.setStatusKeluargaName("Suami");
                        }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A1")){
                            returnKeluarga.setStatusKeluargaName("Anak Pertama");
                        }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A2")){
                            returnKeluarga.setStatusKeluargaName("Anak Kedua");
                        }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A3")){
                            returnKeluarga.setStatusKeluargaName("Anak Ketiga");
                        }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A4")){
                            returnKeluarga.setStatusKeluargaName("Anak Keempat");
                        }else if(keluargaEntity.getStatusKeluarga().equalsIgnoreCase("A5")){
                            returnKeluarga.setStatusKeluargaName("Anak Kelima");
                        }else{
                            returnKeluarga.setStatusKeluargaName("");
                        }
                    }else{
                        returnKeluarga.setStatusKeluargaName("");
                    }

                    if(keluargaEntity.getStTanggalLahir() != null){
                        returnKeluarga.setStTanggalLahir(keluargaEntity.getStTanggalLahir());
                    }else{
                        returnKeluarga.setStTanggalLahir("");
                    }

                    returnKeluarga.setCreatedWho(keluargaEntity.getCreatedWho());
                    returnKeluarga.setCreatedDate(keluargaEntity.getCreatedDate());
                    returnKeluarga.setLastUpdate(keluargaEntity.getLastUpdate());

                    returnKeluarga.setAction(keluargaEntity.getAction());
                    returnKeluarga.setFlag(keluargaEntity.getFlag());
                    listOfResult.add(returnKeluarga);
                }
            }
        }
        logger.info("[KeluargaBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Keluarga> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Keluarga> getComboKeluargaWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Keluarga> listComboKeluarga = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImKeluargaEntity> listKeluarga = null;
        try {
            listKeluarga = keluargaDao.getListKeluarga(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listKeluarga != null) {
            for (ImKeluargaEntity imKeluargaEntity : listKeluarga) {
                Keluarga itemComboKeluarga = new Keluarga();
                itemComboKeluarga.setKeluargaId(imKeluargaEntity.getKeluargaId());
                itemComboKeluarga.setName(imKeluargaEntity.getName());
                itemComboKeluarga.setNip(imKeluargaEntity.getNip());
                itemComboKeluarga.setTanggalLahir(imKeluargaEntity.getTanggalLahir());
                itemComboKeluarga.setStatusKeluargaId(imKeluargaEntity.getStatusKeluarga());
                listComboKeluarga.add(itemComboKeluarga);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboKeluarga;
    }

    @Override
    public List<Keluarga> getComboKeluargaByNip(String query, String nip) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Keluarga> listComboKeluarga = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImKeluargaEntity> listKeluarga = null;
        try {
            listKeluarga = keluargaDao.getListKeluargaById(criteria, nip);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listKeluarga != null) {
            for (ImKeluargaEntity imKeluargaEntity : listKeluarga) {
                Keluarga itemComboKeluarga = new Keluarga();
                itemComboKeluarga.setKeluargaId(imKeluargaEntity.getKeluargaId());
                itemComboKeluarga.setName(imKeluargaEntity.getName());
                itemComboKeluarga.setNip(imKeluargaEntity.getNip());
                itemComboKeluarga.setTanggalLahir(imKeluargaEntity.getTanggalLahir());
                itemComboKeluarga.setStatusKeluargaId(imKeluargaEntity.getStatusKeluarga());
                switch (imKeluargaEntity.getStatusKeluarga()){
                    case "I":  itemComboKeluarga.setStatusKeluargaName("Istri");
                        break;
                    case "S":  itemComboKeluarga.setStatusKeluargaName("Suami");
                        break;
                    case "A1":  itemComboKeluarga.setStatusKeluargaName("Anak Ke-1");
                        break;
                    case "A2":  itemComboKeluarga.setStatusKeluargaName("Anak Ke-2");
                        break;
                    case "A3":  itemComboKeluarga.setStatusKeluargaName("Anak Ke-3");
                        break;
                    case "A4":  itemComboKeluarga.setStatusKeluargaName("Anak Ke-4");
                        break;
                    case "A5":  itemComboKeluarga.setStatusKeluargaName("Anak Ke-5");
                        break;
                    default: itemComboKeluarga.setStatusKeluargaName("Keluarga");
                        break;
                }
                int umur = CommonUtil.getAge(new Date(imKeluargaEntity.getTanggalLahir().getTime()));
                if (("A4").equalsIgnoreCase(imKeluargaEntity.getStatusKeluarga())||("A5").equalsIgnoreCase(imKeluargaEntity.getStatusKeluarga())){ }
                else if (("I").equalsIgnoreCase(imKeluargaEntity.getStatusKeluarga())||("S").equalsIgnoreCase(imKeluargaEntity.getStatusKeluarga())){
                    listComboKeluarga.add(itemComboKeluarga);
                }
                else if (umur>25){}
                else{
                    listComboKeluarga.add(itemComboKeluarga);
                }
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboKeluarga;
    }
}