package com.neurix.hris.master.ijin.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.ijin.bo.IjinBo;
import com.neurix.hris.master.ijin.dao.IjinDao;
import com.neurix.hris.master.ijin.model.Ijin;
import com.neurix.hris.master.ijin.model.ImIjinEntity;
import com.neurix.hris.master.ijin.model.ImIjinHistoryEntity;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class IjinBoImpl implements IjinBo {

    protected static transient Logger logger = Logger.getLogger(IjinBoImpl.class);
    private IjinDao ijinDao;
    private BiodataDao biodataDao;
    private IjinKeluarDao ijinKeluarDao;

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

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
        IjinBoImpl.logger = logger;
    }

    public IjinDao getIjinDao() {
        return ijinDao;
    }

    public void setIjinDao(IjinDao ijinDao) {
        this.ijinDao = ijinDao;
    }



    @Override
    public void saveDelete(Ijin bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String ijinId = bean.getIjinId();

            List<IjinKeluarEntity> ijinKeluarEntityList = ijinKeluarDao.getListIjinKeluarByIjinId(ijinId);

            if (ijinKeluarEntityList.size()>0){
                String status = "ERROR : tidak bisa menghapus data karena sudah dipakai di transaksi";
                logger.error(status);
                throw new GeneralBOException(status);
            }

            ImIjinEntity imIjinEntity = null;
            ImIjinHistoryEntity imIjinHistoryEntity = new ImIjinHistoryEntity();
            String idHistory = "";
            try {
                // Get data from database by ID
                imIjinEntity = ijinDao.getById("ijinId", ijinId);
                idHistory = ijinDao.getNextIjinHistoryId();
            } catch (HibernateException e) {
                logger.error("[IjinBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imIjinEntity != null) {
                imIjinHistoryEntity.setId(idHistory);
                imIjinHistoryEntity.setIjinId(bean.getIjinId());
                imIjinHistoryEntity.setIjinName(imIjinEntity.getIjinName());
                imIjinEntity.setGender(bean.getGender());
                imIjinHistoryEntity.setJumlahIjin(imIjinEntity.getJumlahIjin());
                imIjinHistoryEntity.setFlag(imIjinEntity.getFlag());
                imIjinHistoryEntity.setAction(imIjinEntity.getAction());
                imIjinHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imIjinHistoryEntity.setCreatedWho(imIjinEntity.getCreatedWho());
                imIjinHistoryEntity.setCreatedDate(imIjinEntity.getCreatedDate());

                // Modify from bean to entity serializable
                imIjinEntity.setIjinId(bean.getIjinId());
                imIjinEntity.setIjinName(bean.getIjinName());
                imIjinEntity.setTipeHari(bean.getTipeHari());
                imIjinEntity.setJumlahIjin(bean.getJumlahIjin());
                imIjinEntity.setGender(bean.getGender());
                imIjinEntity.setTipeHari(bean.getTipeHari());
                imIjinEntity.setFlag(bean.getFlag());
                imIjinEntity.setAction(bean.getAction());
                imIjinEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    ijinDao.updateAndSave(imIjinEntity);
                    ijinDao.addAndSaveHistory(imIjinHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Ijin, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[IjinBoImpl.saveDelete] Error, not found data Ijin with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Ijin with request id, please check again your data ...");

            }
        }
        logger.info("[IjinBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Ijin bean) throws GeneralBOException {
        logger.info("[IjinBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String ijinId = bean.getIjinId();

            ImIjinEntity imIjinEntity = null;
            ImIjinHistoryEntity imIjinHistoryEntity = new ImIjinHistoryEntity();
            String idHistory = "";
            try {
                // Get data from database by ID
                imIjinEntity = ijinDao.getById("ijinId", ijinId);
                idHistory = ijinDao.getNextIjinHistoryId();
            } catch (HibernateException e) {
                logger.error("[IjinBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Ijin by Kode Ijin, please inform to your admin...," + e.getMessage());
            }

            if (imIjinEntity != null) {
                //
                imIjinHistoryEntity.setId(idHistory);
                imIjinHistoryEntity.setIjinId(bean.getIjinId());
                imIjinHistoryEntity.setIjinName(imIjinEntity.getIjinName());
                imIjinHistoryEntity.setJumlahIjin(imIjinEntity.getJumlahIjin());
                imIjinHistoryEntity.setFlag(imIjinEntity.getFlag());
                imIjinHistoryEntity.setAction(imIjinEntity.getAction());
                imIjinHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imIjinHistoryEntity.setCreatedWho(imIjinEntity.getCreatedWho());
                imIjinHistoryEntity.setCreatedDate(imIjinEntity.getCreatedDate());

                imIjinEntity.setGender(bean.getGender());
                imIjinEntity.setAgama(bean.getAgama());
                imIjinEntity.setIjinId(bean.getIjinId());
                imIjinEntity.setIjinName(bean.getIjinName());
                imIjinEntity.setTipeHari(bean.getTipeHari());
                imIjinEntity.setJumlahIjin(bean.getJumlahIjin());
                imIjinEntity.setFlag(bean.getFlag());
                imIjinEntity.setAction(bean.getAction());
                imIjinEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    ijinDao.updateAndSave(imIjinEntity);
                    ijinDao.addAndSaveHistory(imIjinHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Ijin, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[IjinBoImpl.saveEdit] Error, not found data Ijin with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Ijin with request id, please check again your data ...");
//                condition = "Error, not found data Ijin with request id, please check again your data ...";
            }
        }
        logger.info("[IjinBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Ijin saveAdd(Ijin bean) throws GeneralBOException {
        logger.info("[IjinBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getIjinName(), bean.getFlag());
            if (!status.equalsIgnoreCase("exist")){
                String ijinId;
                try {
                    // Generating ID, get from postgre sequence
                    ijinId = ijinDao.getNextIjinId();
                } catch (HibernateException e) {
                    logger.error("[IjinBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence ijinId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImIjinEntity imIjinEntity = new ImIjinEntity();

                imIjinEntity.setIjinId(ijinId);
                imIjinEntity.setIjinName(bean.getIjinName());
                imIjinEntity.setJumlahIjin(bean.getJumlahIjin());
                imIjinEntity.setGender(bean.getGender());
                imIjinEntity.setTipeHari(bean.getTipeHari());
                imIjinEntity.setFlagDiajukanAdmin("N");
                imIjinEntity.setFlag(bean.getFlag());
                imIjinEntity.setAction(bean.getAction());
                imIjinEntity.setCreatedWho(bean.getCreatedWho());
                imIjinEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imIjinEntity.setCreatedDate(bean.getCreatedDate());
                imIjinEntity.setLastUpdate(bean.getLastUpdate());
                imIjinEntity.setAgama(bean.getAgama());

                try {
                    // insert into database
                    ijinDao.addAndSave(imIjinEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Ijin, please info to your admin..." + e.getMessage());
                }
            }else {
                throw new GeneralBOException("Maaf Data dengan Nama Ijin Tersebut Sudah Ada");
            }
        }

        logger.info("[IjinBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Ijin> getByCriteria(Ijin searchBean) throws GeneralBOException {
        logger.info("[IjinBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Ijin> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getIjinId() != null && !"".equalsIgnoreCase(searchBean.getIjinId())) {
                hsCriteria.put("ijin_id", searchBean.getIjinId());
            }
            if (searchBean.getIjinName() != null && !"".equalsIgnoreCase(searchBean.getIjinName())) {
                hsCriteria.put("ijin_name", searchBean.getIjinName());
            }

            if (searchBean.getGender() != null && !"".equalsIgnoreCase(searchBean.getGender())) {
                hsCriteria.put("gender", searchBean.getGender());
            }
            if (searchBean.getAgama() != null && !"".equalsIgnoreCase(searchBean.getAgama())) {
                hsCriteria.put("agama", searchBean.getAgama());
            }
            if (searchBean.getTipeHari() != null && !"".equalsIgnoreCase(searchBean.getTipeHari())) {
                hsCriteria.put("tipeHari", searchBean.getTipeHari());
            }
            if (searchBean.getFlagDiajukanAdmin() != null && !"".equalsIgnoreCase(searchBean.getFlagDiajukanAdmin())) {
                hsCriteria.put("flag_diajukan_admin", searchBean.getFlagDiajukanAdmin());
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


            List<ImIjinEntity> imIjinEntity = null;
            try {

                imIjinEntity = ijinDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[IjinBoImpl.getSearchIjinByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imIjinEntity != null){
                Ijin returnIjin;
                // Looping from dao to object and save in collection
                for(ImIjinEntity ijinEntity : imIjinEntity){
                    returnIjin = new Ijin();
                    returnIjin.setIjinId(ijinEntity.getIjinId());
                    returnIjin.setIjinName(ijinEntity.getIjinName());
                    returnIjin.setJumlahIjin(ijinEntity.getJumlahIjin());

                    returnIjin.setGender(ijinEntity.getGender());
                    returnIjin.setTipeHari(ijinEntity.getTipeHari());
                    returnIjin.setAgama(ijinEntity.getAgama());
                    returnIjin.setFlagDiajukanAdmin(ijinEntity.getFlagDiajukanAdmin());

                    switch (ijinEntity.getAgama()){
                        case "all":
                            returnIjin.setAgamaName("Semua");
                            break;
                        case "islam":
                            returnIjin.setAgamaName("Islam");
                            break;
                        case "kristen":
                            returnIjin.setAgamaName("Kristen");
                            break;
                        case "katolik":
                            returnIjin.setAgamaName("Katolik");
                            break;
                        case "hindu":
                            returnIjin.setAgamaName("Hindu");
                            break;
                        case "budha":
                            returnIjin.setAgamaName("Buddha");
                            break;
                        case "kong hu cu":
                            returnIjin.setAgamaName("Kong Hu Cu");
                            break;
                    }

                    returnIjin.setCreatedWho(ijinEntity.getCreatedWho());
                    returnIjin.setCreatedDate(ijinEntity.getCreatedDate());
                    returnIjin.setLastUpdate(ijinEntity.getLastUpdate());
                    returnIjin.setLastUpdateWho(ijinEntity.getLastUpdateWho());

                    returnIjin.setAction(ijinEntity.getAction());
                    returnIjin.setFlag(ijinEntity.getFlag());
                    listOfResult.add(returnIjin);
                }
            }
        }
        logger.info("[IjinBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Ijin> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Ijin> getComboIjinWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Ijin> listComboIjin = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImIjinEntity> listIjin = null;
        try {
            listIjin = ijinDao.getListIjin(criteria,"N");
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listIjin != null) {
            for (ImIjinEntity imIjinEntity : listIjin) {
                Ijin itemComboIjin = new Ijin();
                itemComboIjin.setIjinId(imIjinEntity.getIjinId());
                itemComboIjin.setIjinName(imIjinEntity.getIjinName());
                listComboIjin.add(itemComboIjin);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboIjin;
    }

    public List<Ijin> getComboLamaIjinWithCriteria(String query) throws GeneralBOException {
        logger.info("[IjinBoImpl.getComboLamaIjinWithCriteria] start process >>>");
        ArrayList listComboLamaIjin = new ArrayList();
        String criteria = query;
        List listLamaIjin = null;

        try {
            listLamaIjin = this.ijinDao.getListLamaIjin(criteria);
        } catch (HibernateException var8) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + var8.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + var8.getMessage());
        }

        Iterator i$;
        ImIjinEntity imIjinEntity;
        Ijin itemComboLamaIjin;
        if(listLamaIjin != null) {
            i$ = listLamaIjin.iterator();

            while(i$.hasNext()) {
                imIjinEntity = (ImIjinEntity) i$.next();
                itemComboLamaIjin = new Ijin();
                itemComboLamaIjin.setIjinId(imIjinEntity.getIjinId());
                itemComboLamaIjin.setIjinName(imIjinEntity.getIjinName());
                itemComboLamaIjin.setJumlahIjin(imIjinEntity.getJumlahIjin());
                listComboLamaIjin.add(itemComboLamaIjin);
            }
        } else {
            i$ = listLamaIjin.iterator();

            while(i$.hasNext()) {
                imIjinEntity = (ImIjinEntity)i$.next();
                itemComboLamaIjin = new Ijin();
                itemComboLamaIjin.setIjinId((String)null);
                itemComboLamaIjin.setIjinName((String)null);
                itemComboLamaIjin.setJumlahIjin((Long)null);
                listComboLamaIjin.add(itemComboLamaIjin);
            }
        }

        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboLamaIjin;
    }

    public List<Ijin> getComboIjinIdWithCriteria(String query) throws GeneralBOException {
        logger.info("[IjinBoImpl.getComboIjinWithCriteria] start process >>>");

        List<Ijin> listComboIjin = new ArrayList();
        String criteria = "%" + query + "%" ;

        List<ImIjinEntity> listUser = null;
        try {
            listUser = ijinDao.getListIjinId(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboIjinWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listUser != null) {
            for (ImIjinEntity imIjin : listUser) {
                Ijin itemComboIjin = new Ijin();
                itemComboIjin.setIjinId(imIjin.getIjinId());
                itemComboIjin.setIjinName(imIjin.getIjinName());
                itemComboIjin.setJumlahIjin(imIjin.getJumlahIjin());
                listComboIjin.add(itemComboIjin);
            }
        }
        logger.info("[IjinBoImpl.getComboIjinWithCriteria] end process <<<");
        return listComboIjin;
    }

    @Override
    public List<Ijin> getComboIjinIdWithKelamin(String nip,String flagDiajukanAdmin) throws GeneralBOException {
        logger.info("[IjinBoImpl.getComboIjinIdWithKelamin] start process >>>");
        List<Ijin> listComboIjin = new ArrayList();
        List<ImIjinEntity> listIjin = null;
        ImBiodataEntity biodataEntity = new ImBiodataEntity();
        try {
            biodataEntity = biodataDao.getById("nip",nip,"Y");
        } catch (HibernateException e) {
            logger.error("[IjinBoImpl.getComboIjinIdWithKelamin] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        if (biodataEntity.getGender()!=null){
            try {
                listIjin = ijinDao.getListIjin("%",flagDiajukanAdmin);
            } catch (HibernateException e) {
                logger.error("[IjinBoImpl.getComboIjinIdWithKelamin] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
            if (listIjin != null) {
                for (ImIjinEntity imIjinEntity : listIjin) {
                    boolean bisa=true;
                    if (imIjinEntity.getGender()==null){}
                    else if(("L/P").equalsIgnoreCase(imIjinEntity.getGender())){}
                    else if (!imIjinEntity.getGender().equalsIgnoreCase(biodataEntity.getGender())){
                        bisa=false;
                    }

                    if (imIjinEntity.getAgama()==null){}
                    else if(("all").equalsIgnoreCase(imIjinEntity.getAgama())){}
                    else if (!imIjinEntity.getAgama().equalsIgnoreCase(biodataEntity.getAgama())){
                        bisa=false;
                    }

                    if (bisa){
                        Ijin itemComboIjin = new Ijin();
                        itemComboIjin.setIjinId(imIjinEntity.getIjinId());
                        itemComboIjin.setIjinName(imIjinEntity.getIjinName());
                        itemComboIjin.setJumlahIjin(imIjinEntity.getJumlahIjin());

                        itemComboIjin.setGender(imIjinEntity.getGender());
                        listComboIjin.add(itemComboIjin);
                    }
                }
            }
        }
        logger.info("[IjinBoImpl.getComboIjinIdWithKelamin] end process <<<");
        return listComboIjin;
    }

    public String cekStatus(String ijinName, String flag){
        String status = "";
        List<ImIjinEntity> imIjinEntities = new ArrayList<>();
        try{
            imIjinEntities = ijinDao.getDataIjin(ijinName, flag);
        }catch (HibernateException e){
            logger.error("[IjinBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (imIjinEntities.size()>0){
            status = "exist";
        }else {
            status = "notExist";
        }
        return status;
    }
}
