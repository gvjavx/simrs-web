package com.neurix.hris.master.dashboard.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.dashboard.bo.DashboardBo;
import com.neurix.hris.master.dashboard.dao.DashboardDao;
import com.neurix.hris.master.dashboard.model.Dashboard;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.transaksi.mutasi.dao.MutasiDao;
import com.neurix.hris.transaksi.mutasi.model.Mutasi;
import com.neurix.hris.transaksi.smk.dao.SmkHistoryGolonganDao;
import com.neurix.hris.transaksi.smk.model.ImtHistorySmkGolonganEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class DashboardBoImpl implements DashboardBo {

    protected static transient Logger logger = Logger.getLogger(DashboardBoImpl.class);
    private DashboardDao dashboardDao;
    private BiodataDao biodataDao;
    private SmkHistoryGolonganDao smkHistoryGolonganDao;
    private PositionBagianDao positionBagianDao;

    public DashboardDao getDashboardDao() {
        return dashboardDao;
    }

    public void setDashboardDao(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public SmkHistoryGolonganDao getSmkHistoryGolonganDao() {
        return smkHistoryGolonganDao;
    }

    public void setSmkHistoryGolonganDao(SmkHistoryGolonganDao smkHistoryGolonganDao) {
        this.smkHistoryGolonganDao = smkHistoryGolonganDao;
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
        DashboardBoImpl.logger = logger;
    }

    public DashboardDao getUpdateGolonganDao() {
        return dashboardDao;
    }

    @Override
    public void saveDelete(Dashboard bean) throws GeneralBOException {
        /*logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String golonganId = bean.getUpdateGolonganId();

            ImtUpdateGolonganEntity imGolonganEntity = null;

            try {
                // Get data from database by ID
                imGolonganEntity = dashboardDao.getById("golonganId", golonganId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganEntity != null) {

                // Modify from bean to entity serializable

                imGolonganEntity.setFlag(bean.getFlag());
                imGolonganEntity.setAction(bean.getAction());
                imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    dashboardDao.updateAndSave(imGolonganEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[GolonganBoImpl.saveDelete] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");

            }
        }
        logger.info("[GolonganBoImpl.saveDelete] end process <<<");*/
    }

    @Override
    public void saveEdit(Dashboard bean) throws GeneralBOException {
        /*logger.info("[GolonganBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String golonganId = bean.getUpdateGolonganId();
            String historyId = "";

            ImtUpdateGolonganEntity imGolonganEntity = null;
            ImtUpdateGolonganHistoryEntity imGolonganHistoryEntity = new ImtUpdateGolonganHistoryEntity();

            try {
                // Get data from database by ID
                imGolonganEntity = dashboardDao.getById("golonganId", golonganId);
                historyId = dashboardDao.getNextGolonganHistoryId();
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Golongan by Kode Golongan, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganEntity != null) {
                //
                imGolonganHistoryEntity.setId(historyId);
                imGolonganHistoryEntity.setGolonganId(imGolonganEntity.getUpdateGolonganId());
                imGolonganHistoryEntity.setFlag(imGolonganEntity.getFlag());
                imGolonganHistoryEntity.setAction(imGolonganEntity.getAction());
                imGolonganHistoryEntity.setLastUpdateWho(imGolonganEntity.getLastUpdateWho());
                imGolonganHistoryEntity.setLastUpdate(imGolonganEntity.getLastUpdate());
                imGolonganHistoryEntity.setCreatedWho(imGolonganEntity.getLastUpdateWho());
                imGolonganHistoryEntity.setCreatedDate(imGolonganEntity.getLastUpdate());

                imGolonganEntity.setUpdateGolonganId(bean.getUpdateGolonganId());
                imGolonganEntity.setFlag(bean.getFlag());
                imGolonganEntity.setAction(bean.getAction());
                imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    dashboardDao.updateAndSave(imGolonganEntity);
                    dashboardDao.addAndSaveHistory(imGolonganHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[GolonganBoImpl.saveEdit] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");
//                condition = "Error, not found data Golongan with request id, please check again your data ...";
            }
        }
        logger.info("[GolonganBoImpl.saveEdit] end process <<<");*/
    }

    @Override
    public Dashboard saveAdd(Dashboard bean) throws GeneralBOException {
        /*logger.info("[UpdateGolonganBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String golonganId;
            try {
                // Generating ID, get from postgre sequence
                golonganId = dashboardDao.getNextGolonganId();
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImtUpdateGolonganEntity imGolonganEntity = new ImtUpdateGolonganEntity();
            List<ImBiodataEntity> imBiodataEntity = new ArrayList<>();

            imBiodataEntity = biodataDao.getBiodataByUnit(bean.getBranchId());
            try {
                if(imBiodataEntity.size() > 0){
                    for(ImBiodataEntity biodataLoop: imBiodataEntity){
                        ImtHistorySmkGolonganEntity imtHistorySmkGolonganEntity = new ImtHistorySmkGolonganEntity();

                        String idSmkHistory = smkHistoryGolonganDao.getNextId(bean.getPeriode());
                        imtHistorySmkGolonganEntity.setIdHistorySmkGolongan(idSmkHistory);
                        imtHistorySmkGolonganEntity.setNip(biodataLoop.getNip());
                        imtHistorySmkGolonganEntity.setGolonganId(biodataLoop.getGolongan());
                        imtHistorySmkGolonganEntity.setPoin(biodataLoop.getPoint());
                        imtHistorySmkGolonganEntity.setPoinLebih(biodataLoop.getPoinLebih());

                        imtHistorySmkGolonganEntity.setNilaiAngka(BigDecimal.valueOf(0));
                        imtHistorySmkGolonganEntity.setNilaiHuruf("");
                        imtHistorySmkGolonganEntity.setTahun(bean.getPeriode());
                        imtHistorySmkGolonganEntity.setUpdateGolonganId(golonganId);

                        imtHistorySmkGolonganEntity.setFlag(bean.getFlag());
                        imtHistorySmkGolonganEntity.setAction(bean.getAction());
                        imtHistorySmkGolonganEntity.setCreatedWho(bean.getCreatedWho());
                        imtHistorySmkGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imtHistorySmkGolonganEntity.setCreatedDate(bean.getCreatedDate());
                        imtHistorySmkGolonganEntity.setLastUpdate(bean.getLastUpdate());
                        imtHistorySmkGolonganEntity.setStatus("Tetap");

                        // cek apakah pernah mutasi
                        List<Mutasi> mutasiEntities = mutasiDao.getDataMutasi(biodataLoop.getNip(), bean.getPeriode());
                        if(mutasiEntities.size() > 0){
                            for(Mutasi mutasiEntity: mutasiEntities){
                                imtHistorySmkGolonganEntity.setFlagMutasi("Y");
                                imtHistorySmkGolonganEntity.setPositionId(mutasiEntity.getPositionLamaId());
                                imtHistorySmkGolonganEntity.setBranchId(mutasiEntity.getBranchLamaId());
                            }
                        }else{
                            imtHistorySmkGolonganEntity.setPositionId(biodataLoop.getPosisiId());
                            imtHistorySmkGolonganEntity.setBranchId(bean.getBranchId());
                            imtHistorySmkGolonganEntity.setFlagMutasi("N");
                        }

                        smkHistoryGolonganDao.addAndSave(imtHistorySmkGolonganEntity);
                    }
                }
            } catch (HibernateException e) {
                logger.error("[UpdateGolonganBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
            }

            imGolonganEntity.setUpdateGolonganId(golonganId);
            imGolonganEntity.setPeriode(bean.getPeriode());
            imGolonganEntity.setBranchId(bean.getBranchId());
            imGolonganEntity.setApprovalFlag("-");

            imGolonganEntity.setFlag(bean.getFlag());
            imGolonganEntity.setAction(bean.getAction());
            imGolonganEntity.setCreatedWho(bean.getCreatedWho());
            imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imGolonganEntity.setCreatedDate(bean.getCreatedDate());
            imGolonganEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                dashboardDao.addAndSave(imGolonganEntity);
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
            }
        }
*/
        logger.info("[UpdateGolonganBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Dashboard> getByCriteria(Dashboard searchBean) throws GeneralBOException {
        /*logger.info("[GolonganBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Dashboard> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getUpdateGolonganId() != null && !"".equalsIgnoreCase(searchBean.getUpdateGolonganId())) {
                hsCriteria.put("update_golongan_id", searchBean.getUpdateGolonganId());
            }

            if (searchBean.getApprovalFlag() != null && !"".equalsIgnoreCase(searchBean.getApprovalFlag())) {
                hsCriteria.put("approval_flag", searchBean.getApprovalFlag());
            }

            if (searchBean.getPeriode() != null && !"".equalsIgnoreCase(searchBean.getPeriode())) {
                hsCriteria.put("periode", searchBean.getPeriode());
            }


            List<ImtUpdateGolonganEntity> imGolonganEntity = null;
            try {

                imGolonganEntity = dashboardDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.getSearchGolonganByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imGolonganEntity != null){
                Dashboard returnGolongan;
                // Looping from dao to object and save in collection
                for(ImtUpdateGolonganEntity golonganEntity : imGolonganEntity){
                    returnGolongan = new Dashboard();
                    returnGolongan.setUpdateGolonganId(golonganEntity.getUpdateGolonganId());
                    returnGolongan.setPeriode(golonganEntity.getPeriode());
                    returnGolongan.setBranchId(golonganEntity.getBranchId());
                    returnGolongan.setBranchName(golonganEntity.getImBranches().getBranchName());

                    returnGolongan.setCreatedWho(golonganEntity.getCreatedWho());
                    returnGolongan.setCreatedDate(golonganEntity.getCreatedDate());
                    returnGolongan.setLastUpdate(golonganEntity.getLastUpdate());

                    returnGolongan.setAction(golonganEntity.getAction());
                    returnGolongan.setFlag(golonganEntity.getFlag());
                    listOfResult.add(returnGolongan);
                }
            }
        }
        logger.info("[GolonganBoImpl.getByCriteria] end process <<<");*/

        return null;
    }

    @Override
    public List<Dashboard> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Dashboard> getComboGolonganWithCriteria(String query) throws GeneralBOException {
        /*logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Dashboard> listComboGolongan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImtUpdateGolonganEntity> listGolongan = null;
        try {
            listGolongan = dashboardDao.getListGolongan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listGolongan != null) {
            for (ImtUpdateGolonganEntity imGolonganEntity : listGolongan) {
                Dashboard itemComboGolongan = new Dashboard();
                itemComboGolongan.setUpdateGolonganId(imGolonganEntity.getUpdateGolonganId());
                listComboGolongan.add(itemComboGolongan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboGolongan;*/
        return null;
    }

    @Override
    public List<Dashboard> getDataEdit(String id, String periode) throws GeneralBOException {
        List<Dashboard> updateGolonganList = new ArrayList<>();
        List<ImtHistorySmkGolonganEntity> imtHistorySmkGolonganEntities = new ArrayList<>();

        imtHistorySmkGolonganEntities = smkHistoryGolonganDao.getDataGolongan2Tahun(id, periode);

        if(imtHistorySmkGolonganEntities.size() > 0){
            for(ImtHistorySmkGolonganEntity loopHistory: imtHistorySmkGolonganEntities){
                Dashboard updateGolongan = new Dashboard();
                String golonganLama = "-";
                String golonganBaru = "-";

                if(loopHistory.getGolonganNameBefore() != null){
                    golonganLama = loopHistory.getGolonganNameBefore().substring(9);
                }

                if(loopHistory.getGolonganName() != null){
                    golonganBaru = loopHistory.getGolonganName().substring(9);
                }

                updateGolongan.setNip(loopHistory.getNip());
                updateGolongan.setNamaPegawai(loopHistory.getNama());
                updateGolongan.setGolonganIdBefore(loopHistory.getGolonganIdBefore());
                updateGolongan.setPoinBefore(loopHistory.getPoinBefore() + "");
                updateGolongan.setPoinLebihBefore(loopHistory.getPoinLebihBefore() + "");
                updateGolongan.setNilaiAngka(loopHistory.getNilaiAngka() + "");
                updateGolongan.setNilaiHuruf(loopHistory.getNilaiHuruf() + "");
                updateGolongan.setGolonganId(loopHistory.getGolonganId());
                updateGolongan.setPoin(loopHistory.getPoin() + "");
                updateGolongan.setPoinLebih(loopHistory.getPoinLebih() + "");
                updateGolongan.setUpdateGolonganId(loopHistory.getUpdateGolonganId());
                updateGolongan.setStatus(loopHistory.getStatus());
                updateGolongan.setPositionId(loopHistory.getPositionId());
                updateGolongan.setBagianId(loopHistory.getBagianId());
                updateGolongan.setBagianName(loopHistory.getBagianName());

                if(!updateGolongan.getPoinLebihBefore().equalsIgnoreCase("0")){
                    updateGolongan.setStrGolonganLama(golonganLama + " / " + loopHistory.getPoinBefore() + "+"
                            + loopHistory.getPoinLebihBefore());
                }else{
                    updateGolongan.setStrGolonganLama(golonganLama + " / " + loopHistory.getPoinBefore());
                }

                if(!updateGolongan.getPoinLebih().equalsIgnoreCase("0")){
                    updateGolongan.setStrGolongan(golonganBaru + " / " + loopHistory.getPoin() + "+"
                            + loopHistory.getPoinLebih());
                }else{
                    updateGolongan.setStrGolongan(golonganBaru + " / " + loopHistory.getPoin());
                }

                updateGolonganList.add(updateGolongan);
            }
        }

        return updateGolonganList;
    }

    @Override
    public void saveData() throws GeneralBOException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Dashboard> listOfResult = (List<Dashboard>) session.getAttribute("listDataGolongan");

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        if(listOfResult != null && listOfResult.size() > 0){
            for(Dashboard updateGolonganLoop: listOfResult){
                List<ImtHistorySmkGolonganEntity> imtUpdate =
                        smkHistoryGolonganDao.getHistoryByUpdateIdAndNip(updateGolonganLoop.getUpdateGolonganId(), updateGolonganLoop.getNip());

                if(imtUpdate.size() > 0){
                    for(ImtHistorySmkGolonganEntity imtHistoryLoop: imtUpdate){
                        ImtHistorySmkGolonganEntity historyUpdate = smkHistoryGolonganDao.getById("idHistorySmkGolongan", imtHistoryLoop.getIdHistorySmkGolongan());

                        historyUpdate.setGolonganId(updateGolonganLoop.getGolonganId());
                        historyUpdate.setPoin(Integer.parseInt(updateGolonganLoop.getPoin()));
                        historyUpdate.setPoinLebih(Integer.parseInt(updateGolonganLoop.getPoinLebih()));
                        historyUpdate.setStatus(updateGolonganLoop.getStatus());

                        historyUpdate.setLastUpdateWho(userLogin);
                        historyUpdate.setLastUpdate(updateTime);

                        smkHistoryGolonganDao.updateAndSave(historyUpdate);
                    }
                }

            }
        }
    }

    @Override
    public List<Dashboard> printReport(String id, String periode) throws GeneralBOException {
        List<Dashboard> updateGolonganList = new ArrayList<>();
        List<Dashboard> hasilUpdateGolongan = new ArrayList<>();

        List<ImPositionBagianEntity> finalPosisiBagian = new ArrayList<>();
        String txtWhere = "";
        List<ImPositionBagianEntity> imPositionBagianEntities = positionBagianDao.getAllDataPositionBagian(txtWhere);
        if(imPositionBagianEntities.size() > 0){
            for(ImPositionBagianEntity imPositionBagianEntity: imPositionBagianEntities){
                List<ImPositionBagianEntity> posisiPerBagian =
                        positionBagianDao.getPosisiPerBagianUpdateGolongan(id, imPositionBagianEntity.getBagianId());
                if(posisiPerBagian.size() > 0){
                    int x = 0;
                    ImPositionBagianEntity bagian = new ImPositionBagianEntity();
                    for(ImPositionBagianEntity imPositionBagianEntity1: posisiPerBagian){
                        bagian = new ImPositionBagianEntity();
                        bagian.setBagianId(imPositionBagianEntity.getBagianId());
                        bagian.setBagianName(imPositionBagianEntity.getBagianName());
                        bagian.setNip(imPositionBagianEntity1.getNip());

                        finalPosisiBagian.add(bagian);
                    }
                }
            }
        }

        List<ImtHistorySmkGolonganEntity> imtHistorySmkGolonganEntities = new ArrayList<>();
        imtHistorySmkGolonganEntities = smkHistoryGolonganDao.getDataGolongan2Tahun(id, periode);
        int i = 1;
        if(imtHistorySmkGolonganEntities.size() > 0){
            for(ImtHistorySmkGolonganEntity historyLoop: imtHistorySmkGolonganEntities){
                Dashboard updateGolongan = new Dashboard();
                String golonganLama = "-";
                String golonganBaru = "-";

                if(historyLoop.getGolonganNameBefore() != null){
                    golonganLama = historyLoop.getGolonganNameBefore().substring(9);
                }

                if(historyLoop.getGolonganName() != null){
                    golonganBaru = historyLoop.getGolonganName().substring(9);
                }

                //String tmp = i + " - " + itPayrollEntity.getNip();
                updateGolongan.setNip(historyLoop.getNip());
                updateGolongan.setNamaPegawai(historyLoop.getNama());
                if(historyLoop.getStatus() != null){
                    updateGolongan.setStatus(historyLoop.getStatus());
                }else{
                    updateGolongan.setStatus("-");
                }

                if(historyLoop.getPoinLebihBefore() > 0){
                    updateGolongan.setStrGolonganLama(golonganLama + " / " + historyLoop.getPoinBefore() + "+"
                            + historyLoop.getPoinLebihBefore());
                }else{
                    updateGolongan.setStrGolonganLama(golonganLama + " / " + historyLoop.getPoinBefore());
                }

                if(historyLoop.getPoinLebih() > 0){
                    updateGolongan.setStrGolongan(golonganBaru + " / " + historyLoop.getPoin() + "+"
                            + historyLoop.getPoinLebih());
                }else{
                    updateGolongan.setStrGolongan(golonganBaru + " / " + historyLoop.getPoin());
                }

                updateGolonganList.add(updateGolongan);
            }
        }

        int x = 0;
        String bagian = "";

        for(ImPositionBagianEntity imPositionBagianEntity: finalPosisiBagian){
            if(x == 0){
                hasilUpdateGolongan.addAll(headerUpdateGolongan(imPositionBagianEntity.getBagianName()));
            } else if(!imPositionBagianEntity.getBagianName().equalsIgnoreCase(bagian)){

                hasilUpdateGolongan.addAll(headerPayrollTotalUpdateGolongan(
                        imPositionBagianEntity.getBagianName())
                );
            }

            for(Dashboard updateGolonganLoop: updateGolonganList){
                if(!"".equalsIgnoreCase(imPositionBagianEntity.getNip()) && imPositionBagianEntity.getNip() != null &&
                        !"".equalsIgnoreCase(updateGolonganLoop.getNip()) && updateGolonganLoop.getNip() != null){
                    if(imPositionBagianEntity.getNip().equalsIgnoreCase(updateGolonganLoop.getNip())){
                        updateGolonganLoop.setNip(i + " - " + imPositionBagianEntity.getNip());
                        i++;
                        hasilUpdateGolongan.add(updateGolonganLoop);
                        bagian = imPositionBagianEntity.getBagianName();

                        break;
                    }
                }
            }
            x++;
        }

        return hasilUpdateGolongan;
    }

    private List<Dashboard> headerUpdateGolongan(String bagian){
        Dashboard updateGolongan = new Dashboard();

        List<Dashboard> updateGolonganList = new ArrayList<>();

        updateGolongan.setNip("NIP");
        updateGolongan.setNamaPegawai(bagian);
        updateGolongan.setStrGolonganLama("Golongan Lama.");
        updateGolongan.setStrGolongan("Golongan Baru");
        updateGolongan.setStatus("Status");

        updateGolonganList.add(updateGolongan);

        return updateGolonganList;
    }

    private List<Dashboard> headerPayrollTotalUpdateGolongan(String bagian){
        Dashboard updateGolongan = new Dashboard();
        Dashboard updateGolongan1 = new Dashboard();


        List<Dashboard> updateGolonganList = new ArrayList<>();

        updateGolongan.setNip("");
        updateGolongan.setNamaPegawai("");
        updateGolongan.setStrGolonganLama("");
        updateGolongan.setStrGolongan("");
        updateGolongan.setStatus("");

        updateGolongan1.setNip("NIP");
        updateGolongan1.setNamaPegawai(bagian);
        updateGolongan1.setStrGolonganLama("Golongan Lama.");
        updateGolongan1.setStrGolongan("Golongan Baru");
        updateGolongan1.setStatus("Golongan Baru");

        updateGolonganList.add(updateGolongan);
        updateGolonganList.add(updateGolongan1);

        return updateGolonganList;
    }

}
