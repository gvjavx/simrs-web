package com.neurix.hris.transaksi.rekruitmenPabrik.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.user.dao.UserRoleDao;
import com.neurix.authorization.user.model.ImUsersRoles;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.masaTanam.dao.MasaTanamDao;
import com.neurix.hris.master.masaTanam.model.ImMasaTanamEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.tipepegawai.dao.TipePegawaiDao;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import com.neurix.hris.transaksi.indisipliner.dao.IndisiplinerDao;
import com.neurix.hris.transaksi.indisipliner.model.ItIndisiplinerEntity;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.rekruitmenPabrik.bo.RekruitmenPabrikBo;
import com.neurix.hris.transaksi.rekruitmenPabrik.dao.RekruitmenPabrikDao;
import com.neurix.hris.transaksi.rekruitmenPabrik.dao.RekruitmenPabrikDetailDao;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.*;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class RekruitmenPabrikBoImpl implements RekruitmenPabrikBo {

    protected static transient Logger logger = Logger.getLogger(RekruitmenPabrikBoImpl.class);
    private RekruitmenPabrikDao rekruitmenDao;
    private RekruitmenPabrikDetailDao rekruitmenStatusDao;
    private RekruitmenPabrikDao rekruitmenPabrikDao;
    private RekruitmenPabrikDetailDao rekruitmenPabrikDetailDao;
    private BiodataDao biodataDao;
    private TipePegawaiDao tipePegawaiDao;
    private PersonilPositionDao personilPositionDao;
    private DepartmentDao departmentDao;
    private PositionDao positionDao;
    private StrukturJabatanDao strukturJabatanDao;
    private NotifikasiDao notifikasiDao;
    private BranchDao branchDao;
    private MasaTanamDao masaTanamDao;
    private PositionBagianDao positionBagianDao;
    private UserRoleDao userRoleDao;
    private IndisiplinerDao indisiplinerDao;

    public IndisiplinerDao getIndisiplinerDao() {
        return indisiplinerDao;
    }

    public void setIndisiplinerDao(IndisiplinerDao indisiplinerDao) {
        this.indisiplinerDao = indisiplinerDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public MasaTanamDao getMasaTanamDao() {
        return masaTanamDao;
    }

    public void setMasaTanamDao(MasaTanamDao masaTanamDao) {
        this.masaTanamDao = masaTanamDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public TipePegawaiDao getTipePegawaiDao() {
        return tipePegawaiDao;
    }

    public void setTipePegawaiDao(TipePegawaiDao tipePegawaiDao) {
        this.tipePegawaiDao = tipePegawaiDao;
    }

    public RekruitmenPabrikDetailDao getRekruitmenStatusDao() {
        return rekruitmenStatusDao;
    }

    public void setRekruitmenStatusDao(RekruitmenPabrikDetailDao rekruitmenStatusDao) {
        this.rekruitmenStatusDao = rekruitmenStatusDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RekruitmenPabrikBoImpl.logger = logger;
    }

    public RekruitmenPabrikDao getRekruitmenDao() {
        return rekruitmenDao;
    }

    public void setRekruitmenDao(RekruitmenPabrikDao rekruitmenDao) {
        this.rekruitmenDao = rekruitmenDao;
    }

    @Override
    public void saveEdit(RekruitmenPabrik bean) throws GeneralBOException {

    }

    @Override
    public RekruitmenPabrik saveAdd(RekruitmenPabrik bean) throws GeneralBOException {
        boolean saved;
        logger.info("[RekruitmenPabrikBoImpl.saveAdd] start process >>>");
        String rekruitmenPabrikId = null;
        if (bean != null) {
            try {
                // Generating ID, get from postgre sequence
                rekruitmenPabrikId = rekruitmenPabrikDao.getNextRekruitmenPabrikId();
            } catch (HibernateException e) {
                logger.error("[RekruitmenPabrikBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence rekruitmenPabrikId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItRekruitmenPabrikEntity itRekruitmenPabrikEntity = new ItRekruitmenPabrikEntity();

            itRekruitmenPabrikEntity.setRekruitmenPabrikId(rekruitmenPabrikId);
            itRekruitmenPabrikEntity.setBranchId(bean.getBranchId());
            itRekruitmenPabrikEntity.setBagianId(bean.getBagianId());
            itRekruitmenPabrikEntity.setMt(bean.getMt());
            itRekruitmenPabrikEntity.setKuota(bean.getKuota());

            itRekruitmenPabrikEntity.setFlag(bean.getFlag());
            itRekruitmenPabrikEntity.setAction(bean.getAction());
            itRekruitmenPabrikEntity.setCreatedWho(bean.getCreatedWho());
            itRekruitmenPabrikEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itRekruitmenPabrikEntity.setCreatedDate(bean.getCreatedDate());
            itRekruitmenPabrikEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rekruitmenPabrikDao.addAndSave(itRekruitmenPabrikEntity);
                saved=true;
            } catch (HibernateException e) {
                logger.error("[RekruitmenPabrikBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Rekruitmen Pabrik, please info to your admin..." + e.getMessage());
            }

            /*if (saved){
                String id;
                try {
                    // Generating ID, get from postgre sequence
                    id = rekruitmenPabrikDao.getNextRekruitmenPabrikHistoryId();
                } catch (HibernateException e) {
                    logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                }

                ItRekruitmenPabrikHistoryEntity entityData = new ItRekruitmenPabrikHistoryEntity();
                entityData.setRekruitmenPabrikId(rekruitmenPabrikId);
                entityData.setBranchId(bean.getBranchId());
                entityData.setDivisiId(bean.getDivisiId());
                entityData.setMt(bean.getMt());
                entityData.setKuota(bean.getKuota());

                entityData.setFlag(bean.getFlag());
                entityData.setAction(bean.getAction());
                entityData.setCreatedWho(bean.getCreatedWho());
                entityData.setLastUpdateWho(bean.getLastUpdateWho());
                entityData.setCreatedDate(bean.getCreatedDate());
                entityData.setLastUpdate(bean.getLastUpdate());
                entityData.setId(id);

                try {
                    rekruitmenPabrikDao.addAndSaveHistory(entityData);
                } catch (HibernateException e) {
                    logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                }
            }*/
        }
        return null;
    }

    @Override
    public List<RekruitmenPabrikDetail> getByCriteriaDetail(RekruitmenPabrik searchBean) {
        logger.info("[RekruitmenPabrikBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RekruitmenPabrikDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getRekruitmenPabrikId() != null && !"".equalsIgnoreCase(searchBean.getRekruitmenPabrikId())) {
                hsCriteria.put("rekruitmen_pabrik_id", searchBean.getRekruitmenPabrikId());
            }
            if (searchBean.getRpbId() != null && !"".equalsIgnoreCase(searchBean.getRpbId())) {
                hsCriteria.put("rekruitmen_pabrik_detail_id", searchBean.getRpbId());
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

            List<ItRekruitmenPabrikDetailEntity> itRekruitmenPabrikDetailEntity = null;
            try {

                itRekruitmenPabrikDetailEntity = rekruitmenPabrikDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenPabrikBoImpl.getSearchRekruitmenPabrikByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itRekruitmenPabrikDetailEntity != null) {
                RekruitmenPabrikDetail returnRekruitmenPabrikDetail;
                // Looping from dao to object and save in collection
                for (ItRekruitmenPabrikDetailEntity rekruitmenPabrikDetailEntity : itRekruitmenPabrikDetailEntity) {
                    returnRekruitmenPabrikDetail = new RekruitmenPabrikDetail();

                    List<ItIndisiplinerEntity> indisiplinerEntityList = new ArrayList<>();
                    indisiplinerEntityList = indisiplinerDao.getListIndisiplinerFromNip(rekruitmenPabrikDetailEntity.getNip());

                    returnRekruitmenPabrikDetail.setJmlIndisipliner(indisiplinerEntityList.size());
                    returnRekruitmenPabrikDetail.setRekruitmenPabrikId(rekruitmenPabrikDetailEntity.getRekruitmenPabrikId());
                    returnRekruitmenPabrikDetail.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailEntity.getRekruitmenPabrikDetailId());
                    returnRekruitmenPabrikDetail.setNip(rekruitmenPabrikDetailEntity.getNip());
                    returnRekruitmenPabrikDetail.setNamaPegawai(rekruitmenPabrikDetailEntity.getNamaPegawai());
                    returnRekruitmenPabrikDetail.setStatusGiling(rekruitmenPabrikDetailEntity.getStatusGiling());
                    returnRekruitmenPabrikDetail.setPosisiLama(rekruitmenPabrikDetailEntity.getPosisiLama());
                    returnRekruitmenPabrikDetail.setPosisiBaru(rekruitmenPabrikDetailEntity.getPosisiBaru());
                    returnRekruitmenPabrikDetail.setNoKontrak(rekruitmenPabrikDetailEntity.getNoKontrak());
                    returnRekruitmenPabrikDetail.setTanggalAktif(rekruitmenPabrikDetailEntity.getTanggalAktif());
                    returnRekruitmenPabrikDetail.setApprovalGmId(rekruitmenPabrikDetailEntity.getApprovalGmId());
                    returnRekruitmenPabrikDetail.setApprovalGmFlag(rekruitmenPabrikDetailEntity.getApprovalGmFlag());
                    returnRekruitmenPabrikDetail.setApprovalGmdate(rekruitmenPabrikDetailEntity.getApprovalGmdate());
                    returnRekruitmenPabrikDetail.setApprovalSdmId(rekruitmenPabrikDetailEntity.getApprovalSdmId());
                    returnRekruitmenPabrikDetail.setApprovalSdmFlag(rekruitmenPabrikDetailEntity.getApprovalSdmFlag());
                    returnRekruitmenPabrikDetail.setApprovalSdmdate(rekruitmenPabrikDetailEntity.getApprovalSdmdate());
                    Map hsCriteria2 =  new HashMap();
                    hsCriteria2.put("nip",returnRekruitmenPabrikDetail.getNip());
                    hsCriteria2.put("flag","Y");
                    List<ItPersonilPositionEntity> itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria2);
                    for (ItPersonilPositionEntity itPersonilPositionEntity:itPersonilPositionEntityList){
                        ImPosition imPosition = positionDao.getById("positionId",itPersonilPositionEntity.getPositionId(),"Y");
                        returnRekruitmenPabrikDetail.setDivisi(imPosition.getDepartmentId());
                        returnRekruitmenPabrikDetail.setBagianId(imPosition.getBagianId());

                        ImPositionBagianEntity positionBagianEntity=positionBagianDao.getById("bagianId",returnRekruitmenPabrikDetail.getBagianId());
                        returnRekruitmenPabrikDetail.setBagianName(positionBagianEntity.getBagianName());
                    }
                    hsCriteria2 = new HashMap();
                    hsCriteria2.put("department_id",returnRekruitmenPabrikDetail.getDivisi());
                    hsCriteria2.put("flag","Y");
                    List<ImDepartmentEntity> imDepartmentEntityList = departmentDao.getByCriteria(hsCriteria2);
                    for(ImDepartmentEntity imDepartmentEntity:imDepartmentEntityList){
                        returnRekruitmenPabrikDetail.setDivisiName(imDepartmentEntity.getDepartmentName());
                    }
                    returnRekruitmenPabrikDetail.setTipePegawaiName(rekruitmenPabrikDetailEntity.getImBiodataEntity().getTipePegawai());
                    hsCriteria2 = new HashMap();
                    hsCriteria2.put("tipe_pegawai_id",returnRekruitmenPabrikDetail.getTipePegawaiName());
                    hsCriteria2.put("flag","Y");
                    List<ImHrisTipePegawai> imHrisTipePegawaiList = tipePegawaiDao.getByCriteria(hsCriteria2);
                    for(ImHrisTipePegawai imHrisTipePegawai : imHrisTipePegawaiList){
                        returnRekruitmenPabrikDetail.setTipePegawaiName(imHrisTipePegawai.getTipePegawaiName());
                    }
                    returnRekruitmenPabrikDetail.setPosisiLamaName(rekruitmenPabrikDetailEntity.getImPosition().getPositionName());
                    returnRekruitmenPabrikDetail.setPosisiBaruName(rekruitmenPabrikDetailEntity.getImPosition2().getPositionName());
                    returnRekruitmenPabrikDetail.setStatusPegawaiName(rekruitmenPabrikDetailEntity.getImBiodataEntity().getStatusPegawai());
                    if (returnRekruitmenPabrikDetail.getStatusPegawaiName().equalsIgnoreCase("KS")){
                        returnRekruitmenPabrikDetail.setStatusPegawaiName("Karyawan Staff");
                    }else if(returnRekruitmenPabrikDetail.getStatusPegawaiName().equalsIgnoreCase("KNS")){
                        returnRekruitmenPabrikDetail.setStatusPegawaiName("Karyawan Non Staff");
                    }

                    returnRekruitmenPabrikDetail.setCreatedWho(rekruitmenPabrikDetailEntity.getCreatedWho());
                    returnRekruitmenPabrikDetail.setCreatedDate(rekruitmenPabrikDetailEntity.getCreatedDate());
                    returnRekruitmenPabrikDetail.setLastUpdate(rekruitmenPabrikDetailEntity.getLastUpdate());
                    returnRekruitmenPabrikDetail.setAction(rekruitmenPabrikDetailEntity.getAction());
                    returnRekruitmenPabrikDetail.setFlag(rekruitmenPabrikDetailEntity.getFlag());
                    listOfResult.add(returnRekruitmenPabrikDetail);
                }
            }
        }
        logger.info("[IjinBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public void saveDelete(RekruitmenPabrik bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean != null) {

            String rekruitmenPabrikId = bean.getRekruitmenPabrikId();
            ItRekruitmenPabrikEntity itRekruitmenPabrikEntity = null;

            try {
                // Get data from database by ID
                itRekruitmenPabrikEntity = rekruitmenPabrikDao.getById("rekruitmenPabrikId", rekruitmenPabrikId);
            } catch (HibernateException e) {
                logger.error("[IjinBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (itRekruitmenPabrikEntity != null) {

                // Modify from bean to entity serializable
                itRekruitmenPabrikEntity.setRekruitmenPabrikId(bean.getRekruitmenPabrikId());
                itRekruitmenPabrikEntity.setFlag(bean.getFlag());
                itRekruitmenPabrikEntity.setAction(bean.getAction());
                itRekruitmenPabrikEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itRekruitmenPabrikEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    rekruitmenPabrikDao.updateAndSave(itRekruitmenPabrikEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Ijin, please info to your admin..." + e.getMessage());
                }

                Map hsCriteria2 = new HashMap();
                if (bean.getRekruitmenPabrikId() != null && !"".equalsIgnoreCase(bean.getRekruitmenPabrikId())) {
                    hsCriteria2.put("rekruitmen_pabrik_id", bean.getRekruitmenPabrikId());
                }
                hsCriteria2.put("flag", "Y");
                List<ItRekruitmenPabrikDetailEntity> itRekruitmenPabrikDetailEntity = null;
                itRekruitmenPabrikDetailEntity = rekruitmenPabrikDetailDao.getByCriteria(hsCriteria2);
                // Get All been Approved
                if (itRekruitmenPabrikDetailEntity != null) {
                    // Looping from dao to object and save in collection
                    for (ItRekruitmenPabrikDetailEntity rekruitmenPabrikDetailEntity : itRekruitmenPabrikDetailEntity) {
                        ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity1 = new ItRekruitmenPabrikDetailEntity();
                        itRekruitmenPabrikDetailEntity1 = rekruitmenPabrikDetailDao.getById("rekruitmenPabrikDetailId", rekruitmenPabrikDetailEntity.getRekruitmenPabrikDetailId());

                        itRekruitmenPabrikDetailEntity1.setRekruitmenPabrikId(bean.getRekruitmenPabrikId());
                        itRekruitmenPabrikDetailEntity1.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailEntity.getRekruitmenPabrikDetailId());
                        itRekruitmenPabrikDetailEntity1.setFlag(bean.getFlag());
                        itRekruitmenPabrikDetailEntity1.setAction(bean.getAction());
                        itRekruitmenPabrikDetailEntity1.setLastUpdateWho(bean.getLastUpdateWho());
                        itRekruitmenPabrikDetailEntity1.setLastUpdate(bean.getLastUpdate());

                        try {
                            // Delete (Edit) into database
                            rekruitmenPabrikDetailDao.updateAndSave(itRekruitmenPabrikDetailEntity1);
                        } catch (HibernateException e) {
                            logger.error("[IjinBoImpl.saveDelete] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data Ijin, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            } else {
                logger.error("[IjinBoImpl.saveDelete] Error, not found data Ijin with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Ijin with request id, please check again your data ...");

            }
        }
        logger.info("[IjinBoImpl.saveDelete] end process <<<");
    }
    @Override
    public List<PersonilPosition> getByCriteriaHistoriPegawai(PersonilPosition searchBean) throws GeneralBOException {
        logger.info("[RekruitmenPabrikBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PersonilPosition> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            List<ItPersonilPositionEntity> itPersonilPositionEntityList = null;
            try {

                itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenPabrikBoImpl.getSearchRekruitmenPabrikByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itPersonilPositionEntityList != null) {
                PersonilPosition returnPersonilPosition;
                // Looping from dao to object and save in collection
                for (ItPersonilPositionEntity personilPositionEntity : itPersonilPositionEntityList) {
                    returnPersonilPosition = new PersonilPosition();
//                    if (!personilPositionEntity.getDivisiId().equalsIgnoreCase("")){
//                        returnPersonilPosition.setDivisiId(personilPositionEntity.getDivisiId());
//                        returnPersonilPosition.setDivisiName(personilPositionEntity.getImDepartmentEntity().getDepartmentName());
//                    }
                    if (!personilPositionEntity.getPositionId().equalsIgnoreCase("")){
                        returnPersonilPosition.setPositionId(personilPositionEntity.getPositionId());
                        returnPersonilPosition.setPositionName(personilPositionEntity.getImPosition().getPositionName());
                    }
                    Timestamp tanggal = personilPositionEntity.getCreatedDate();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String stTanggal  = dateFormat.format(tanggal);
                    returnPersonilPosition.setTanggalAktif(stTanggal);

                    listOfResult.add(returnPersonilPosition);
                }
            }
        }
        logger.info("[IjinBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<RekruitmenPabrik> getByCriteria(RekruitmenPabrik searchBean) throws GeneralBOException {
        logger.info("[RekruitmenPabrikBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RekruitmenPabrik> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getRekruitmenPabrikId() != null && !"".equalsIgnoreCase(searchBean.getRekruitmenPabrikId())) {
                hsCriteria.put("rekruitmen_pabrik_id", searchBean.getRekruitmenPabrikId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getBagianId() != null && !"".equalsIgnoreCase(searchBean.getBagianId())) {
                hsCriteria.put("bagian_id", searchBean.getBagianId());
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

            List<ItRekruitmenPabrikEntity> itRekruitmenPabrikEntity = null;
            try {

                itRekruitmenPabrikEntity = rekruitmenPabrikDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenPabrikBoImpl.getSearchRekruitmenPabrikByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itRekruitmenPabrikEntity != null) {
                RekruitmenPabrik returnRekruitmenPabrik;
                // Looping from dao to object and save in collection
                for (ItRekruitmenPabrikEntity rekruitmenPabrikEntity : itRekruitmenPabrikEntity) {
                    returnRekruitmenPabrik = new RekruitmenPabrik();
                    returnRekruitmenPabrik.setRekruitmenPabrikId(rekruitmenPabrikEntity.getRekruitmenPabrikId());
                    returnRekruitmenPabrik.setBranchId(rekruitmenPabrikEntity.getBranchId());
                    returnRekruitmenPabrik.setDivisiId(rekruitmenPabrikEntity.getDivisiId());
                    returnRekruitmenPabrik.setUnitName(rekruitmenPabrikEntity.getImBranches().getBranchName());
                    returnRekruitmenPabrik.setBagianId(rekruitmenPabrikEntity.getBagianId());

                    if (rekruitmenPabrikEntity.getBagianId()!=null){
                        ImPositionBagianEntity positionBagianEntity= positionBagianDao.getById("bagianId",rekruitmenPabrikEntity.getBagianId());
                        returnRekruitmenPabrik.setBagianName(positionBagianEntity.getBagianName());
                    }
                    returnRekruitmenPabrik.setMt(rekruitmenPabrikEntity.getMt());
                    returnRekruitmenPabrik.setKuota(rekruitmenPabrikEntity.getKuota());

                    Map hsCriteria2 = new HashMap();
                    if (rekruitmenPabrikEntity.getRekruitmenPabrikId() != null && !"".equalsIgnoreCase(rekruitmenPabrikEntity.getRekruitmenPabrikId())) {
                        hsCriteria2.put("rekruitmen_pabrik_id", rekruitmenPabrikEntity.getRekruitmenPabrikId());
                    }
                    if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                        if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                            hsCriteria2.put("flag", "N");
                        } else {
                            hsCriteria2.put("flag", searchBean.getFlag());
                        }
                    } else {
                        hsCriteria2.put("flag", "Y");
                    }
                    List<ItRekruitmenPabrikDetailEntity> itRekruitmenPabrikDetailEntity = null;
                    itRekruitmenPabrikDetailEntity = rekruitmenPabrikDetailDao.getByCriteria(hsCriteria2);

                    //get sisa kuota
                    if (rekruitmenPabrikEntity.getKuota()!=null){
                        Integer sisaKuota = rekruitmenPabrikEntity.getKuota()-itRekruitmenPabrikDetailEntity.size();
                        returnRekruitmenPabrik.setSisaKuota(sisaKuota);
                    }

                    // Get All been Approved
                    if (itRekruitmenPabrikDetailEntity != null) {
                        // Looping from dao to object and save in collection
                        for (ItRekruitmenPabrikDetailEntity rekruitmenPabrikDetailEntity : itRekruitmenPabrikDetailEntity) {
                            if ("ADMIN".equalsIgnoreCase(CommonUtil.roleAsLogin())){
                                returnRekruitmenPabrik.setCekAdmin(true);
                            }
                            if (rekruitmenPabrikDetailEntity.getApprovalSdmFlag() != null) {
                                if (rekruitmenPabrikDetailEntity.getApprovalSdmFlag().equals("Y")) {
                                    returnRekruitmenPabrik.setApprovalSdm(true);

                                } else if (rekruitmenPabrikDetailEntity.getApprovalSdmFlag().equals("N")) {
                                    returnRekruitmenPabrik.setApprovalSdm(false);
                                }
                            } else {
                                returnRekruitmenPabrik.setApprovalSdm(false);
                            }
                            if (rekruitmenPabrikDetailEntity.getApprovalGmFlag()==null){
                                returnRekruitmenPabrik.setApprovalGm(false);
                            }else {
                                switch (rekruitmenPabrikDetailEntity.getApprovalGmFlag()) {
                                    case "Y":
                                        returnRekruitmenPabrik.setApprovalGm(true);
                                        break;
                                    case "N":
                                        returnRekruitmenPabrik.setApprovalGm(false);
                                        break;
                                    default:
                                        returnRekruitmenPabrik.setApprovalGm(false);
                                        break;
                                }
                            }
                            if (!returnRekruitmenPabrik.isApprovalGm() && !returnRekruitmenPabrik.isApprovalSdm()) {
                                break;
                            }
                        }
                        for (ItRekruitmenPabrikDetailEntity rekruitmenPabrikDetailEntity : itRekruitmenPabrikDetailEntity) {
                            if (rekruitmenPabrikDetailEntity.getApprovalSdmFlag().equals("Y")){
                                returnRekruitmenPabrik.setApprovalOneGm(false);
                            }else if (rekruitmenPabrikDetailEntity.getApprovalGmFlag()!=null) {
                                if (rekruitmenPabrikDetailEntity.getApprovalGmFlag().equalsIgnoreCase("Y"))
                                returnRekruitmenPabrik.setApprovalOneGm(true);
                                break;
                            }
                        }
                    }
                    if (rekruitmenPabrikEntity.getClosed()!=null){
                        returnRekruitmenPabrik.setClose(true);
                    }
                        returnRekruitmenPabrik.setCreatedWho(rekruitmenPabrikEntity.getCreatedWho());
                    returnRekruitmenPabrik.setCreatedDate(rekruitmenPabrikEntity.getCreatedDate());
                    returnRekruitmenPabrik.setLastUpdate(rekruitmenPabrikEntity.getLastUpdate());
                    returnRekruitmenPabrik.setAction(rekruitmenPabrikEntity.getAction());
                    returnRekruitmenPabrik.setFlag(rekruitmenPabrikEntity.getFlag());
                    listOfResult.add(returnRekruitmenPabrik);
                }
            }
        }
        logger.info("[IjinBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<RekruitmenPabrik> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public void setRekruitmenPabrikDao(RekruitmenPabrikDao rekruitmenPabrikDao) {
        this.rekruitmenPabrikDao = rekruitmenPabrikDao;
    }

    public RekruitmenPabrikDao getRekruitmenPabrikDao() {
        return rekruitmenPabrikDao;
    }

    public void setRekruitmenPabrikDetailDao(RekruitmenPabrikDetailDao rekruitmenPabrikDetailDao) {
        this.rekruitmenPabrikDetailDao = rekruitmenPabrikDetailDao;
    }

    public RekruitmenPabrikDetailDao getRekruitmenPabrikDetailDao() {
        return rekruitmenPabrikDetailDao;
    }

    @Override
    public List<RekruitmenPabrikDetail> getByCriteriaDetail(RekruitmenPabrikDetail searchRekruitmenPabrikDetail) {
        return null;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    @Override
    public List<RekruitmenPabrikDetail> getComboRekruitmenPabrikPerson2(String nip) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<RekruitmenPabrikDetail> listComboRekruitmenPabrik = new ArrayList();
        List<RekruitmenPabrikDetail> listComboRekruitmenPabrikFix = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<ItRekruitmenPabrikDetailEntity> listRekruitmenPabrik = null;
        try {

            listComboRekruitmenPabrik = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listComboRekruitmenPabrik != null) {
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail : listComboRekruitmenPabrik) {
                if (rekruitmenPabrikDetail.getNip().equals(nip)) {
                    RekruitmenPabrikDetail itemComboRekruitmenPabrik = new RekruitmenPabrikDetail();
                    itemComboRekruitmenPabrik.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                    itemComboRekruitmenPabrik.setPosisiBaru(rekruitmenPabrikDetail.getPosisiBaru());
                    itemComboRekruitmenPabrik.setDivisi(rekruitmenPabrikDetail.getDivisi());
                    itemComboRekruitmenPabrik.setNamaPegawai(rekruitmenPabrikDetail.getNamaPegawai());
                    itemComboRekruitmenPabrik.setNip(nip);
                    itemComboRekruitmenPabrik.setRekruitmenPabrikDetailId(rekruitmenPabrikDetail.getRekruitmenPabrikDetailId());
                    itemComboRekruitmenPabrik.setRekruitmenPabrikId(rekruitmenPabrikDetail.getRekruitmenPabrikId());
                    itemComboRekruitmenPabrik.setStatusGiling(rekruitmenPabrikDetail.getStatusGiling());
                    itemComboRekruitmenPabrik.setTanggalAktif(rekruitmenPabrikDetail.getTanggalAktif());
                    itemComboRekruitmenPabrik.setNoKontrak(rekruitmenPabrikDetail.getNoKontrak());
                    itemComboRekruitmenPabrik.setApprovalSdmId(rekruitmenPabrikDetail.getApprovalSdmId());
                    itemComboRekruitmenPabrik.setApprovalSdmName(rekruitmenPabrikDetail.getApprovalSdmName());
                    itemComboRekruitmenPabrik.setApprovalSdmFlag(rekruitmenPabrikDetail.getApprovalSdmFlag());
                    itemComboRekruitmenPabrik.setApprovalSdmdate(rekruitmenPabrikDetail.getApprovalSdmdate());
                    itemComboRekruitmenPabrik.setApprovalGmId(rekruitmenPabrikDetail.getApprovalGmId());
                    itemComboRekruitmenPabrik.setApprovalGmName(rekruitmenPabrikDetail.getApprovalGmName());
                    itemComboRekruitmenPabrik.setApprovalGmFlag(rekruitmenPabrikDetail.getApprovalGmFlag());
                    itemComboRekruitmenPabrik.setApprovalGmdate(rekruitmenPabrikDetail.getApprovalGmdate());


                    listComboRekruitmenPabrikFix.add(itemComboRekruitmenPabrik);
                } else {
                    logger.info("Error Di getComboRekruitmenPabrikPerson2");
                }
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmenPabrikFix;
    }
    @Override
    public List<RekruitmenPabrikDetail> getComboRekruitmenPabrikPerson3(String nip, String rpdId) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<ItRekruitmenPabrikDetailEntity> listComboRekruitmenPabrik = new ArrayList();
        List<RekruitmenPabrikDetail> listComboRekruitmenPabrikFix = new ArrayList();
        Map hsCriteria = new HashMap();
        hsCriteria.put("nip",nip);
        hsCriteria.put("rekruitmen_pabrik_detail_id",rpdId);
        hsCriteria.put("flag","Y");
        try {
            listComboRekruitmenPabrik = rekruitmenPabrikDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listComboRekruitmenPabrik != null) {
            for (ItRekruitmenPabrikDetailEntity rekruitmenPabrikDetail : listComboRekruitmenPabrik) {
                    RekruitmenPabrikDetail itemComboRekruitmenPabrik = new RekruitmenPabrikDetail();
                    itemComboRekruitmenPabrik.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                    itemComboRekruitmenPabrik.setPosisiBaru(rekruitmenPabrikDetail.getPosisiBaru());
                    itemComboRekruitmenPabrik.setNamaPegawai(rekruitmenPabrikDetail.getNamaPegawai());
                    itemComboRekruitmenPabrik.setNip(nip);
                    itemComboRekruitmenPabrik.setRekruitmenPabrikDetailId(rekruitmenPabrikDetail.getRekruitmenPabrikDetailId());
                    itemComboRekruitmenPabrik.setRekruitmenPabrikId(rekruitmenPabrikDetail.getRekruitmenPabrikId());
                    itemComboRekruitmenPabrik.setStatusGiling(rekruitmenPabrikDetail.getStatusGiling());
                    itemComboRekruitmenPabrik.setTanggalAktif(rekruitmenPabrikDetail.getTanggalAktif());
                    itemComboRekruitmenPabrik.setNoKontrak(rekruitmenPabrikDetail.getNoKontrak());
                    itemComboRekruitmenPabrik.setApprovalSdmId(rekruitmenPabrikDetail.getApprovalSdmId());
                    itemComboRekruitmenPabrik.setApprovalSdmName(rekruitmenPabrikDetail.getApprovalSdmName());
                    itemComboRekruitmenPabrik.setApprovalSdmFlag(rekruitmenPabrikDetail.getApprovalSdmFlag());
                    itemComboRekruitmenPabrik.setApprovalSdmdate(rekruitmenPabrikDetail.getApprovalSdmdate());
                    itemComboRekruitmenPabrik.setApprovalGmId(rekruitmenPabrikDetail.getApprovalGmId());
                    itemComboRekruitmenPabrik.setApprovalGmName(rekruitmenPabrikDetail.getApprovalGmName());
                    itemComboRekruitmenPabrik.setApprovalGmFlag(rekruitmenPabrikDetail.getApprovalGmFlag());
                    itemComboRekruitmenPabrik.setApprovalGmdate(rekruitmenPabrikDetail.getApprovalGmdate());

                    listComboRekruitmenPabrikFix.add(itemComboRekruitmenPabrik);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmenPabrikFix;
    }
    @Override
    public List<RekruitmenPabrikDetail> editRekruitmenPabrikPerson(String nip, String posisiBaru) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<RekruitmenPabrikDetail> listComboRekruitmenPabrik = new ArrayList();
        List<RekruitmenPabrikDetail> listComboRekruitmenPabrikFix = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {

            listComboRekruitmenPabrik = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listComboRekruitmenPabrik != null) {
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail : listComboRekruitmenPabrik) {
                if (rekruitmenPabrikDetail.getNip().equals(nip)) {
                    RekruitmenPabrikDetail itemComboRekruitmenPabrik = new RekruitmenPabrikDetail();
                    if (rekruitmenPabrikDetail.getPosisiBaru().equals("")){
                        itemComboRekruitmenPabrik.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                    }
                    else {
                        itemComboRekruitmenPabrik.setPosisiLama(rekruitmenPabrikDetail.getPosisiBaru());
                    }
                    itemComboRekruitmenPabrik.setPosisiBaru(posisiBaru);
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("position_id",posisiBaru);
                    hsCriteria.put("flag","Y");
                    List<ImPosition> imPositionList = positionDao.getByCriteria(hsCriteria);
                    for (ImPosition imPosition : imPositionList){
                        itemComboRekruitmenPabrik.setPosisiBaruName(imPosition.getPositionName());
                    }
                    itemComboRekruitmenPabrik.setNamaPegawai(rekruitmenPabrikDetail.getNamaPegawai());
                    itemComboRekruitmenPabrik.setNip(nip);
                    itemComboRekruitmenPabrik.setRekruitmenPabrikDetailId(rekruitmenPabrikDetail.getRekruitmenPabrikDetailId());
                    itemComboRekruitmenPabrik.setRekruitmenPabrikId(rekruitmenPabrikDetail.getRekruitmenPabrikId());
                    itemComboRekruitmenPabrik.setStatusGiling(rekruitmenPabrikDetail.getStatusGiling());
                    itemComboRekruitmenPabrik.setTanggalAktif(rekruitmenPabrikDetail.getTanggalAktif());
                    itemComboRekruitmenPabrik.setNoKontrak(rekruitmenPabrikDetail.getNoKontrak());
                    itemComboRekruitmenPabrik.setApprovalSdmId(rekruitmenPabrikDetail.getApprovalSdmId());
                    itemComboRekruitmenPabrik.setApprovalSdmName(rekruitmenPabrikDetail.getApprovalSdmName());
                    itemComboRekruitmenPabrik.setApprovalSdmFlag(rekruitmenPabrikDetail.getApprovalSdmFlag());
                    itemComboRekruitmenPabrik.setApprovalSdmdate(rekruitmenPabrikDetail.getApprovalSdmdate());
                    itemComboRekruitmenPabrik.setApprovalGmId(rekruitmenPabrikDetail.getApprovalGmId());
                    itemComboRekruitmenPabrik.setApprovalGmName(rekruitmenPabrikDetail.getApprovalGmName());
                    itemComboRekruitmenPabrik.setApprovalGmFlag(rekruitmenPabrikDetail.getApprovalGmFlag());
                    itemComboRekruitmenPabrik.setApprovalGmdate(rekruitmenPabrikDetail.getApprovalGmdate());
                    itemComboRekruitmenPabrik.setPosisiLamaName(rekruitmenPabrikDetail.getPosisiLamaName());
                    itemComboRekruitmenPabrik.setTipePegawaiName(rekruitmenPabrikDetail.getTipePegawaiName());
                    itemComboRekruitmenPabrik.setStatusPegawaiName(rekruitmenPabrikDetail.getStatusPegawaiName());
                    itemComboRekruitmenPabrik.setDivisiName(rekruitmenPabrikDetail.getDivisiName());
                    itemComboRekruitmenPabrik.setBagianId(rekruitmenPabrikDetail.getBagianId());
                    itemComboRekruitmenPabrik.setBagianName(rekruitmenPabrikDetail.getBagianName());
                    itemComboRekruitmenPabrik.setDivisi(rekruitmenPabrikDetail.getDivisi());
                    listComboRekruitmenPabrikFix.add(itemComboRekruitmenPabrik);
                } else {
                    listComboRekruitmenPabrikFix.add(rekruitmenPabrikDetail);
                }

            }
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listComboRekruitmenPabrikFix);
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmenPabrikFix;
    }
    @Override
    public void saveClosedRekruitmenPabrik(String id) throws GeneralBOException {
        logger.info("[rekruitmenPabrikBo.saveClosedRekruitmenPabrik] start process >>>");
        ItRekruitmenPabrikEntity rekruitmenPabrikEntity = new ItRekruitmenPabrikEntity();
        try {
            rekruitmenPabrikEntity=rekruitmenPabrikDao.getById("rekruitmenPabrikId",id,"Y");
        } catch (HibernateException e) {
            logger.error("[rekruitmenPabrikBo.saveClosedRekruitmenPabrik] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (rekruitmenPabrikEntity != null) {
            rekruitmenPabrikEntity.setClosed("Y");

            try {
                rekruitmenPabrikDao.updateAndSave(rekruitmenPabrikEntity);
            } catch (HibernateException e) {
                logger.error("[rekruitmenPabrikBo.saveClosedRekruitmenPabrik] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[rekruitmenPabrikBo.saveClosedRekruitmenPabrik] end process <<<");
    }
    @Override
    public List<RekruitmenPabrikDetail> hapusRekruitmenPabrikPerson(String nip) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<RekruitmenPabrikDetail> listComboRekruitmenPabrik = new ArrayList();
        List<RekruitmenPabrikDetail> listComboRekruitmenPabrikFix = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {

            listComboRekruitmenPabrik = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listComboRekruitmenPabrik != null) {
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail : listComboRekruitmenPabrik) {
                if (!nip.equals(rekruitmenPabrikDetail.getNip())) {
                    listComboRekruitmenPabrikFix.add(rekruitmenPabrikDetail);
                }
            }
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listComboRekruitmenPabrikFix);
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRekruitmenPabrikFix;
    }

    @Override
    public List<Notifikasi> approveRekruitmenPabrikPerson(String divisi, String rekId, String approveWho, String approvalFlag) throws GeneralBOException {
        List<Notifikasi> notifikasiList = new ArrayList<>();

        if (rekId != null) {
            String userLogin = CommonUtil.userLogin();
            String userId = CommonUtil.userIdLogin();
            String RekruitmenPabrikDetailId = rekId;
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity = null;
            try {
                // Get data from database by ID
                itRekruitmenPabrikDetailEntity = rekruitmenPabrikDetailDao.getById("rekruitmenPabrikDetailId", RekruitmenPabrikDetailId, "Y");
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data CutiPegawai by Kode CutiPegawai, please inform to your admin...," + e.getMessage());
            }
            ItRekruitmenPabrikEntity itRekruitmenPabrikEntity = rekruitmenPabrikDao.getById("rekruitmenPabrikId",itRekruitmenPabrikDetailEntity.getRekruitmenPabrikId(),"Y");

            if (itRekruitmenPabrikDetailEntity != null) {
                itRekruitmenPabrikDetailEntity.setRekruitmenPabrikDetailId(rekId);
                itRekruitmenPabrikDetailEntity.setFlag("Y");
                //Approve
                if (approvalFlag.equals("Y")) {
                    itRekruitmenPabrikDetailEntity.setApprovalSdmFlag("Y");
                } else {
                    itRekruitmenPabrikDetailEntity.setApprovalSdmFlag("N");
                }
                itRekruitmenPabrikDetailEntity.setApprovalSdmId(CommonUtil.userIdLogin());
                itRekruitmenPabrikDetailEntity.setApprovalSdmdate(updateTime);
                itRekruitmenPabrikDetailEntity.setApprovalSdmName(userLogin);
                itRekruitmenPabrikDetailEntity.setAction("U");
                itRekruitmenPabrikDetailEntity.setLastUpdateWho(CommonUtil.userLogin());
                itRekruitmenPabrikDetailEntity.setLastUpdate(updateTime);

                try {
                    // Update into database
                    rekruitmenPabrikDetailDao.updateAndSave(itRekruitmenPabrikDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                }
                logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");


                ImBiodataEntity imBiodataEntity = new ImBiodataEntity();

                try {
                    imBiodataEntity =  biodataDao.getById("nip",itRekruitmenPabrikDetailEntity.getNip(), "Y");
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                }

                //Send notif ke atasan
                Notifikasi notifAtasan= new Notifikasi();
                notifAtasan.setNip(itRekruitmenPabrikDetailEntity.getNip());
                notifAtasan.setNoRequest(itRekruitmenPabrikDetailEntity.getRekruitmenPabrikId());
                notifAtasan.setTipeNotifId("TN99");
                notifAtasan.setTipeNotifName(("Rekruitmen Pabrik"));
                notifAtasan.setNote("Rekruitmen Pabrik dari : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
                notifAtasan.setCreatedWho(itRekruitmenPabrikDetailEntity.getNip());
                notifAtasan.setTo("kabid");

                notifikasiList.add(notifAtasan);
            }
            /*if (itRekruitmenPabrikDetailEntity.getApprovalGmFlag().equalsIgnoreCase("Y")&&itRekruitmenPabrikDetailEntity.getApprovalSdmFlag().equalsIgnoreCase("Y")) {

                ItPersonilPositionEntity itPersonilPositionEntity = null;
                try {
                    // Get data from database by ID
                    itPersonilPositionEntity = personilPositionDao.getById("nip", itRekruitmenPabrikDetailEntity.getNip(), "Y");
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data CutiPegawai by Kode CutiPegawai, please inform to your admin...," + e.getMessage());
                }
                if (itPersonilPositionEntity != null) {
                    itPersonilPositionEntity.setFlag("Y");
                    itPersonilPositionEntity.setAction("U");
                    itPersonilPositionEntity.setPositionId(itRekruitmenPabrikDetailEntity.getPosisiBaru());
                    itPersonilPositionEntity.setLastUpdateWho(userLogin);
                    itPersonilPositionEntity.setLastUpdate(updateTime);

                    try {
                        // Update into database
                        personilPositionDao.updateAndSave(itPersonilPositionEntity);
                    } catch (HibernateException e) {
                        logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                    }
                }

                Save new Position
                ItPersonilPositionEntity itPersonilPositionEntity2 = new ItPersonilPositionEntity();
                try {
                    // Get Next Personil Position Id
                    itPersonilPositionEntity2.setPersonilPositionId(personilPositionDao.getNextPersonilPositionId());
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                }
                itPersonilPositionEntity2.setNip(itRekruitmenPabrikDetailEntity.getNip());
                if (itPersonilPositionEntity!=null){
                    itPersonilPositionEntity2.setBranchId(itPersonilPositionEntity.getBranchId());
//                    itPersonilPositionEntity2.setDivisiId(divisi);
                    itPersonilPositionEntity2.setPositionId(itRekruitmenPabrikDetailEntity.getPosisiBaru());
                    itPersonilPositionEntity2.setFlag("Y");
                    itPersonilPositionEntity2.setAction("C");
                    itPersonilPositionEntity2.setCreatedDate(updateTime);
                    itPersonilPositionEntity2.setCreatedWho(userLogin);
                    itPersonilPositionEntity2.setLastUpdate(updateTime);
                    itPersonilPositionEntity2.setLastUpdateWho(userLogin);

                    try {
                        // Save New Position
                        personilPositionDao.addAndSave(itPersonilPositionEntity2);
                    } catch (HibernateException e) {
                        logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                    }
                }
                //update masa giling
                ImBiodataEntity imBiodataEntity ;
                try {
                    // Get data from database by ID
                    imBiodataEntity = biodataDao.getById("nip", itRekruitmenPabrikDetailEntity.getNip(), "Y");
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data CutiPegawai by Kode CutiPegawai, please inform to your admin...," + e.getMessage());
                }
                if (imBiodataEntity != null) {
                    ImBiodataHistoryEntity history = new ImBiodataHistoryEntity();
                    history.setId(biodataDao.getNextPersonalHistoryId());
                    history.setNip(imBiodataEntity.getNip());
                    history.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                    history.setGelarDepan(imBiodataEntity.getGelarDepan());
                    history.setGelarBelakang(imBiodataEntity.getGelarBelakang());
                    history.setNoKtp(imBiodataEntity.getNoKtp());
                    history.setAlamat(imBiodataEntity.getAlamat());
                    history.setRtRw(imBiodataEntity.getRtRw());
                    history.setDesa(imBiodataEntity.getDesa());
                    history.setKecamatan(imBiodataEntity.getKecamatan());
                    history.setNoTelp(imBiodataEntity.getNoTelp());
                    history.setKabupaten(imBiodataEntity.getKotaId());
                    history.setProvinsi(imBiodataEntity.getProvinsi());
                    history.setTanggalLahir(imBiodataEntity.getTanggalLahir());
                    history.setTempatLahir(imBiodataEntity.getTempatLahir());
                    history.setTipePegawai(imBiodataEntity.getTipePegawai());
                    history.setFotoUpload(imBiodataEntity.getFotoUpload());
                    history.setStatusCaption(imBiodataEntity.getStatusCaption());
                    history.setKeterangan(imBiodataEntity.getKeterangan());
                    history.setFlag(imBiodataEntity.getFlag());
                    history.setAction(imBiodataEntity.getAction());
                    history.setCreatedWho(imBiodataEntity.getCreatedWho());
                    history.setLastUpdate(imBiodataEntity.getLastUpdate());
                    history.setLastUpdateWho(imBiodataEntity.getLastUpdateWho());
                    history.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                    history.setGolongan(imBiodataEntity.getGolongan());
                    history.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                    history.setStatusKeluarga(imBiodataEntity.getStatusKeluarga());
                    history.setJumlahAnak(imBiodataEntity.getJumlahAnak());
                    history.setJenisKelamin(imBiodataEntity.getGender());
                    history.setMasaGiling(imBiodataEntity.getMasaGiling());
                    history.setPin(imBiodataEntity.getPin());
                    history.setPoint(imBiodataEntity.getPoint());


                    imBiodataEntity.setMasaGiling(itRekruitmenPabrikDetailEntity.getStatusGiling());
                    imBiodataEntity.setAction("U");
                    imBiodataEntity.setLastUpdateWho(userLogin);
                    imBiodataEntity.setLastUpdate(updateTime);
                    try {
                        // Update into database
                        biodataDao.updateAndSave(imBiodataEntity);
                        biodataDao.addAndSaveHistory(history);
                    } catch (HibernateException e) {
                        logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                    }


                }
            }*/

//            String nipGm="";
//            if (!itRekruitmenPabrikEntity.getBranchId().equalsIgnoreCase("KD01")){
//                //Get GM NIP
//                Map hsCriteria =  new HashMap();
//                hsCriteria.put("branch_id",itRekruitmenPabrikEntity.getBranchId());
//                hsCriteria.put("position_id",4);
//                hsCriteria.put("flag","Y");
//                List<ItPersonilPositionEntity> itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria);
//                for (ItPersonilPositionEntity itPersonilPositionEntity1 : itPersonilPositionEntityList){
//                    nipGm = itPersonilPositionEntity1.getNip();
//                }
//            }else{
//                StrukturJabatan strukturJabatan = strukturJabatanDao.searchKabidByBagian(itRekruitmenPabrikEntity.getBagianId());
//                nipGm=strukturJabatan.getNip();
//            }
//
//            // Send Notification
//            ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
//            String idNotif = notifikasiDao.getNextNotifikasiId();
//            addNotif.setNotifId(idNotif);
//            addNotif.setNote("Data Dari Rekruitmen : " + itRekruitmenPabrikEntity.getRekruitmenPabrikId() + " Menunggu di Approve");
//            addNotif.setTipeNotifId("TN99");
//            addNotif.setTipeNotifName("Rekruitmen Pabrik");
//            addNotif.setRead("Y");
//            addNotif.setFlag("Y");
//            addNotif.setAction("C");
//            addNotif.setNip(nipGm);
//            addNotif.setFromPerson(CommonUtil.userIdLogin());
//            addNotif.setNoRequest(itRekruitmenPabrikEntity.getRekruitmenPabrikId());
//            addNotif.setCreatedDate(itRekruitmenPabrikEntity.getCreatedDate());
//            addNotif.setCreatedWho(itRekruitmenPabrikEntity.getCreatedWho());
//            addNotif.setLastUpdate(updateTime);
//            addNotif.setLastUpdateWho(userLogin);
//            try {
//                notifikasiDao.addAndSave(addNotif);
//            } catch (HibernateException e) {
//                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//            }
        }
        return notifikasiList;
    }
    @Override
    public void approveRekruitmenPabrikPersonGm(String rekId, String approveWho, String approvalFlag) throws GeneralBOException {
        if (rekId != null) {
            String RekruitmenPabrikDetailId = rekId;
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity = null;
            try {
                // Get data from database by ID
                itRekruitmenPabrikDetailEntity = rekruitmenPabrikDetailDao.getById("rekruitmenPabrikDetailId", RekruitmenPabrikDetailId, "Y");
            } catch (HibernateException e) {
                logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data CutiPegawai by Kode CutiPegawai, please inform to your admin...," + e.getMessage());
            }

            if (itRekruitmenPabrikDetailEntity != null) {
                String userLogin = CommonUtil.userLogin();
                itRekruitmenPabrikDetailEntity.setRekruitmenPabrikDetailId(rekId);
                itRekruitmenPabrikDetailEntity.setFlag("Y");
                //Approve
                if (approvalFlag.equals("Y")) {
                    itRekruitmenPabrikDetailEntity.setApprovalGmFlag("Y");
                } else {
                    itRekruitmenPabrikDetailEntity.setApprovalGmFlag("N");
                }
                itRekruitmenPabrikDetailEntity.setApprovalGmId(CommonUtil.userIdLogin());
                itRekruitmenPabrikDetailEntity.setApprovalGmdate(updateTime);
                itRekruitmenPabrikDetailEntity.setApprovalGmName(userLogin);
                itRekruitmenPabrikDetailEntity.setAction("U");
                itRekruitmenPabrikDetailEntity.setLastUpdateWho(CommonUtil.userLogin());
                itRekruitmenPabrikDetailEntity.setLastUpdate(updateTime);

                try {
                    // Update into database
                    rekruitmenPabrikDetailDao.updateAndSave(itRekruitmenPabrikDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                }
                logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
            }
        }
    }
    @Override
    public void saveApprove(RekruitmenPabrikDetail bean) throws GeneralBOException {
        logger.info("[IjinBoImpl.saveEdit] start process >>>");

        if (bean != null) {

            String rekruitmenPabrikDetailId = bean.getRekruitmenPabrikDetailId();

            ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity = null;
            /*ImIjinHistoryEntity imIjinHistoryEntity = new ImIjinHistoryEntity();
            String idHistory = "";*/
            try {
                // Get data from database by ID
                itRekruitmenPabrikDetailEntity = rekruitmenPabrikDetailDao.getById("rekruitmenPabrikDetailId", rekruitmenPabrikDetailId);
//                idHistory = ijinDao.getNextIjinHistoryId();
            } catch (HibernateException e) {
                logger.error("[IjinBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Ijin by Kode Ijin, please inform to your admin...," + e.getMessage());
            }

            if (itRekruitmenPabrikDetailEntity != null) {

                itRekruitmenPabrikDetailEntity.setRekruitmenPabrikDetailId(bean.getRekruitmenPabrikDetailId());
                itRekruitmenPabrikDetailEntity.setRekruitmenPabrikId(bean.getRekruitmenPabrikId());
                itRekruitmenPabrikDetailEntity.setNip(bean.getNip());
                itRekruitmenPabrikDetailEntity.setNamaPegawai(bean.getNamaPegawai());
                itRekruitmenPabrikDetailEntity.setStatusGiling(bean.getStatusGiling());
                itRekruitmenPabrikDetailEntity.setPosisiLama(bean.getPosisiLama());
                itRekruitmenPabrikDetailEntity.setPosisiBaru(bean.getPosisiBaru());
                itRekruitmenPabrikDetailEntity.setTanggalAktif(bean.getTanggalAktif());
                itRekruitmenPabrikDetailEntity.setNoKontrak(bean.getNoKontrak());
                itRekruitmenPabrikDetailEntity.setApprovalSdmId(bean.getApprovalSdmId());
                itRekruitmenPabrikDetailEntity.setApprovalSdmName(bean.getApprovalSdmName());
                itRekruitmenPabrikDetailEntity.setApprovalSdmFlag(bean.getApprovalSdmFlag());
                itRekruitmenPabrikDetailEntity.setApprovalSdmdate(bean.getApprovalSdmdate());
                itRekruitmenPabrikDetailEntity.setApprovalGmId(bean.getApprovalGmId());
                itRekruitmenPabrikDetailEntity.setApprovalGmName(bean.getApprovalGmName());
                itRekruitmenPabrikDetailEntity.setApprovalGmFlag(bean.getApprovalGmFlag());
                itRekruitmenPabrikDetailEntity.setApprovalGmdate(bean.getApprovalGmdate());

                itRekruitmenPabrikDetailEntity.setFlag(bean.getFlag());
                itRekruitmenPabrikDetailEntity.setAction(bean.getAction());
                itRekruitmenPabrikDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itRekruitmenPabrikDetailEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    rekruitmenPabrikDetailDao.updateAndSave(itRekruitmenPabrikDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[IjinBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Ijin, please info to your admin..." + e.getMessage());
                }

                if (bean.getApprovalGmFlag().equalsIgnoreCase("Y")&&bean.getApprovalSdmFlag().equalsIgnoreCase("Y")) {
                    String userLogin = CommonUtil.userLogin();
                    String userId = CommonUtil.userIdLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                    ItPersonilPositionEntity itPersonilPositionEntity = null;
                    try {
                        // Get data from database by ID
                        itPersonilPositionEntity = personilPositionDao.getById("nip", bean.getNip(), "Y");
                    } catch (HibernateException e) {
                        logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data CutiPegawai by Kode CutiPegawai, please inform to your admin...," + e.getMessage());
                    }
                    if (itPersonilPositionEntity != null) {
                        itPersonilPositionEntity.setFlag("N");
                        itPersonilPositionEntity.setAction("U");
                        itPersonilPositionEntity.setLastUpdateWho(userLogin);
                        itPersonilPositionEntity.setLastUpdate(updateTime);

                        try {
                            // Update into database
                            personilPositionDao.updateAndSave(itPersonilPositionEntity);
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                        }
                    }
                    // Save new Position
                    ItPersonilPositionEntity itPersonilPositionEntity2 = new ItPersonilPositionEntity();
                    try {
                        // Get Next Personil Position Id
                        itPersonilPositionEntity2.setPersonilPositionId(personilPositionDao.getNextPersonilPositionId());
                    } catch (HibernateException e) {
                        logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                    }
                    itPersonilPositionEntity2.setNip(bean.getNip());
                    if (itPersonilPositionEntity!=null){
                        itPersonilPositionEntity2.setBranchId(itPersonilPositionEntity.getBranchId());
//                        itPersonilPositionEntity2.setDivisiId(itPersonilPositionEntity.getDivisiId());
                        itPersonilPositionEntity2.setPositionId(bean.getPosisiBaru());
                        itPersonilPositionEntity2.setFlag("Y");
                        itPersonilPositionEntity2.setAction("C");
                        itPersonilPositionEntity2.setCreatedDate(updateTime);
                        itPersonilPositionEntity2.setCreatedWho(userLogin);
                        itPersonilPositionEntity2.setLastUpdate(updateTime);
                        itPersonilPositionEntity2.setLastUpdateWho(userLogin);

                        try {
                            // Save New Position
                            personilPositionDao.addAndSave(itPersonilPositionEntity2);
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data CutiPegawai, please info to your admin..." + e.getMessage());
                        }
                    }
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
    public RekruitmenPabrik saveAdd(RekruitmenPabrikDetail bean2) throws GeneralBOException {
        boolean saved;
        String branch = null;
        String divisi=null;
        String masaTanam = "";
        String masaGiling = "";
        logger.info("[RekruitmenPabrikBoImpl.saveAdd] start process >>>");
        String rekruitmenPabrikId = bean2.getRekruitmenPabrikId();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
        if (listOfsearchRekruitmenPabrikDetail != null) {
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail : listOfsearchRekruitmenPabrikDetail) {

                Map hsCriteria = new HashMap();
                hsCriteria.put("rekruitmen_pabrik_id", bean2.getRekruitmenPabrikId());
                hsCriteria.put("flag","Y");
                List<ItRekruitmenPabrikDetailEntity> rekruitmenPabrikDetailList = rekruitmenPabrikDetailDao.getByCriteria(hsCriteria);
                boolean status = false;
                for (ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity : rekruitmenPabrikDetailList){
                    if (itRekruitmenPabrikDetailEntity.getNip().equalsIgnoreCase(rekruitmenPabrikDetail.getNip())){
                        status=true;
                    }
                }

                if (!status){
                    String rekruitmenPabrikDetailId = rekruitmenPabrikDetailDao.getNextRekruitmenPabrikDetailId();
                    ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity = new ItRekruitmenPabrikDetailEntity();

                    Map hsCriteria2 = new HashMap();
                    hsCriteria2.put("nip",rekruitmenPabrikDetail.getNip());
                    hsCriteria2.put("flag","Y");
                    List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria2);
                    for (ItPersonilPositionEntity itPersonilPositionEntity: personilPositionEntityList){
                        branch = itPersonilPositionEntity.getBranchId();
                        ImPosition imPosition = positionDao.getById("positionId",itPersonilPositionEntity.getPositionId(),"Y");
                        divisi = imPosition.getDepartmentId();
                    }
                    itRekruitmenPabrikDetailEntity.setRekruitmenPabrikId(rekruitmenPabrikId);
                    itRekruitmenPabrikDetailEntity.setNip(rekruitmenPabrikDetail.getNip());
                    itRekruitmenPabrikDetailEntity.setNamaPegawai(rekruitmenPabrikDetail.getNamaPegawai());
                    itRekruitmenPabrikDetailEntity.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                    itRekruitmenPabrikDetailEntity.setPosisiBaru(rekruitmenPabrikDetail.getPosisiBaru());
                    itRekruitmenPabrikDetailEntity.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailId);
                    itRekruitmenPabrikDetailEntity.setTanggalAktif(bean2.getCreatedDate());
                    itRekruitmenPabrikDetailEntity.setNoKontrak("");

                    hsCriteria = new HashMap();
                    hsCriteria.put("branch_id",branch);
                    hsCriteria.put("flag","Y");
                    List<ImBranches> imBranchesList = branchDao.getByCriteria(hsCriteria);
                    for (ImBranches imBranches : imBranchesList){
                        masaTanam = imBranches.getMt();
                    }
                    hsCriteria = new HashMap();
                    hsCriteria.put("masa_tanam_id",masaTanam);
                    hsCriteria.put("tanggal",bean2.getCreatedDate());
                    hsCriteria.put("flag","Y");
                    List<ImMasaTanamEntity> imMasaTanamEntities = masaTanamDao.getByCriteria(hsCriteria);
                    if (imMasaTanamEntities.size()!=0){
                        masaGiling="DMG";
                    }else{
                        masaGiling="LMG";
                    }
                    itRekruitmenPabrikDetailEntity.setStatusGiling(masaGiling);
                    itRekruitmenPabrikDetailEntity.setApprovalGmFlag(null);
                    itRekruitmenPabrikDetailEntity.setApprovalSdmFlag("-");

                    itRekruitmenPabrikDetailEntity.setFlag(bean2.getFlag());
                    itRekruitmenPabrikDetailEntity.setAction(bean2.getAction());
                    itRekruitmenPabrikDetailEntity.setCreatedWho(bean2.getCreatedWho());
                    itRekruitmenPabrikDetailEntity.setLastUpdateWho(bean2.getLastUpdateWho());
                    itRekruitmenPabrikDetailEntity.setCreatedDate(bean2.getCreatedDate());
                    itRekruitmenPabrikDetailEntity.setLastUpdate(bean2.getLastUpdate());
                    rekruitmenPabrikDetailDao.addAndSave(itRekruitmenPabrikDetailEntity);
                    saved=true;
                    if (saved){
                        String id;
                        try {
                            // Generating ID, get from postgre sequence
                            id = rekruitmenPabrikDetailDao.getNextRekruitmenPabrikDetailHistoryId();
                        } catch (HibernateException e) {
                            logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                        }

                        ItRekruitmenPabrikDetailHistoryEntity entityData = new ItRekruitmenPabrikDetailHistoryEntity();
                        entityData.setRekruitmenPabrikId(rekruitmenPabrikId);
                        entityData.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailId);

                        entityData.setNip(rekruitmenPabrikDetail.getNip());
                        entityData.setNamaPegawai(rekruitmenPabrikDetail.getNamaPegawai());
                        entityData.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                        entityData.setPosisiBaru(rekruitmenPabrikDetail.getPosisiBaru());
                        entityData.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailId);
                        entityData.setTanggalAktif(bean2.getCreatedDate());
                        entityData.setNoKontrak("");
                        entityData.setStatusGiling(masaGiling);
                        entityData.setApprovalGmFlag("");
                        entityData.setApprovalSdmFlag("");

                        entityData.setFlag(bean2.getFlag());
                        entityData.setAction(bean2.getAction());
                        entityData.setCreatedWho(bean2.getCreatedWho());
                        entityData.setLastUpdateWho(bean2.getLastUpdateWho());
                        entityData.setCreatedDate(bean2.getCreatedDate());
                        entityData.setLastUpdate(bean2.getLastUpdate());
                        entityData.setId(id);

                        try {
                            rekruitmenPabrikDetailDao.addAndSaveHistory(entityData);
                        } catch (HibernateException e) {
                            logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }
            //Send to SDM
            List<ImUsersRoles> usersRolesList = userRoleDao.getAdminUser();

            for (ImUsersRoles roles : usersRolesList){
                // Send Notification
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();
                addNotif.setNotifId(idNotif);
                ImBiodataEntity imBiodataEntity = null ;
                addNotif.setNote("Data Dari Rekruitmen : " + bean2.getRekruitmenPabrikId() + " Menunggu di Approve");
                addNotif.setTipeNotifId("umum");
                addNotif.setTipeNotifName("Rekruitmen Pabrik");
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setNip(roles.getPrimaryKey().getUserId());
                addNotif.setFromPerson(CommonUtil.userIdLogin());
                addNotif.setNoRequest(bean2.getRekruitmenPabrikId());
                addNotif.setCreatedDate(bean2.getCreatedDate());
                addNotif.setCreatedWho(bean2.getCreatedWho());
                addNotif.setLastUpdate(bean2.getLastUpdate());
                addNotif.setLastUpdateWho(bean2.getLastUpdateWho());
                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[IjinBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public RekruitmenPabrik saveEdit(RekruitmenPabrik bean, RekruitmenPabrikDetail bean2) throws GeneralBOException {
        logger.info("[RekruitmenPabrikBoImpl.saveEdit] start process >>>");
        boolean saved;
        String rekruitmenPabrikId = bean.getRekruitmenPabrikId();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getRekruitmenPabrikId() != null && !"".equalsIgnoreCase(bean.getRekruitmenPabrikId())) {
                hsCriteria.put("rekruitmen_pabrik_id", bean.getRekruitmenPabrikId());
            }

            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ItRekruitmenPabrikDetailEntity> itRekruitmenPabrikDetailEntityOld = null;
            try {

                itRekruitmenPabrikDetailEntityOld = rekruitmenPabrikDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenPabrikBoImpl.getSearchRekruitmenPabrikByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
            if (listOfsearchRekruitmenPabrikDetail != null) {
                List<String> rekruitmenDetailId = new ArrayList<String>();
                for (ItRekruitmenPabrikDetailEntity rekruitmenPabrikDetailSave : itRekruitmenPabrikDetailEntityOld) {
                    for (RekruitmenPabrikDetail rekruitmenPabrikDetail : listOfsearchRekruitmenPabrikDetail) {

                        rekruitmenDetailId.add(rekruitmenPabrikDetail.getRekruitmenPabrikDetailId());

                        if (rekruitmenPabrikDetail.getRekruitmenPabrikId()!=null){
                            if (Objects.equals(rekruitmenPabrikDetail.getRekruitmenPabrikDetailId(), rekruitmenPabrikDetailSave.getRekruitmenPabrikDetailId())) {
                                if (!Objects.equals(rekruitmenPabrikDetail.getPosisiBaru(), rekruitmenPabrikDetailSave.getPosisiBaru())) {
                                    rekruitmenPabrikDetailSave.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                                    rekruitmenPabrikDetailSave.setPosisiBaru(rekruitmenPabrikDetail.getPosisiBaru());
                                    rekruitmenPabrikDetailSave.setLastUpdate(bean2.getLastUpdate());
                                    rekruitmenPabrikDetailSave.setLastUpdateWho(bean2.getLastUpdateWho());
                                    rekruitmenPabrikDetailSave.setAction("U");
                                    rekruitmenPabrikDetailDao.updateAndSave(rekruitmenPabrikDetailSave);

                                    saved=true;

                                    if (saved){
                                        String id;
                                        try {
                                            // Generating ID, get from postgre sequence
                                            id = rekruitmenPabrikDetailDao.getNextRekruitmenPabrikDetailHistoryId();
                                        } catch (HibernateException e) {
                                            logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                                        }

                                        ItRekruitmenPabrikDetailHistoryEntity entityData = new ItRekruitmenPabrikDetailHistoryEntity();
                                        entityData.setRekruitmenPabrikId(rekruitmenPabrikDetailSave.getRekruitmenPabrikId());
                                        entityData.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailSave.getRekruitmenPabrikDetailId());

                                        entityData.setNip(rekruitmenPabrikDetailSave.getNip());
                                        entityData.setNamaPegawai(rekruitmenPabrikDetailSave.getNamaPegawai());
                                        entityData.setPosisiLama(rekruitmenPabrikDetailSave.getPosisiLama());
                                        entityData.setPosisiBaru(rekruitmenPabrikDetailSave.getPosisiBaru());
                                        entityData.setTanggalAktif(bean2.getCreatedDate());
                                        entityData.setNoKontrak(rekruitmenPabrikDetailSave.getNoKontrak());
                                        entityData.setStatusGiling(rekruitmenPabrikDetailSave.getStatusGiling());
                                        entityData.setApprovalGmFlag(rekruitmenPabrikDetailSave.getApprovalGmFlag());
                                        entityData.setApprovalGmName(rekruitmenPabrikDetailSave.getApprovalGmName());
                                        entityData.setApprovalGmdate(rekruitmenPabrikDetailSave.getApprovalGmdate());
                                        entityData.setApprovalGmId(rekruitmenPabrikDetailSave.getApprovalGmId());
                                        entityData.setApprovalSdmFlag(rekruitmenPabrikDetailSave.getApprovalSdmFlag());
                                        entityData.setApprovalSdmName(rekruitmenPabrikDetailSave.getApprovalSdmName());
                                        entityData.setApprovalSdmdate(rekruitmenPabrikDetailSave.getApprovalSdmdate());
                                        entityData.setApprovalSdmId(rekruitmenPabrikDetailSave.getApprovalSdmId());

                                        entityData.setFlag(rekruitmenPabrikDetailSave.getFlag());
                                        entityData.setAction(rekruitmenPabrikDetailSave.getAction());
                                        entityData.setCreatedWho(rekruitmenPabrikDetailSave.getCreatedWho());
                                        entityData.setLastUpdateWho(rekruitmenPabrikDetailSave.getLastUpdateWho());
                                        entityData.setCreatedDate(rekruitmenPabrikDetailSave.getCreatedDate());
                                        entityData.setLastUpdate(rekruitmenPabrikDetailSave.getLastUpdate());
                                        entityData.setId(id);

                                        try {
                                            rekruitmenPabrikDetailDao.addAndSaveHistory(entityData);
                                        } catch (HibernateException e) {
                                            logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (RekruitmenPabrikDetail rekruitmenPabrikDetail : listOfsearchRekruitmenPabrikDetail) {
                    if (rekruitmenPabrikDetail.getRekruitmenPabrikDetailId()==null){
                        String rekruitmenPabrikDetailId = rekruitmenPabrikDetailDao.getNextRekruitmenPabrikDetailId();
                        ItRekruitmenPabrikDetailEntity itRekruitmenPabrikDetailEntity = new ItRekruitmenPabrikDetailEntity();

                        itRekruitmenPabrikDetailEntity.setRekruitmenPabrikId(rekruitmenPabrikId);
                        itRekruitmenPabrikDetailEntity.setNip(rekruitmenPabrikDetail.getNip());
                        itRekruitmenPabrikDetailEntity.setNamaPegawai(rekruitmenPabrikDetail.getNamaPegawai());
                        itRekruitmenPabrikDetailEntity.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                        itRekruitmenPabrikDetailEntity.setPosisiBaru(rekruitmenPabrikDetail.getPosisiBaru());
                        itRekruitmenPabrikDetailEntity.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailId);
                        itRekruitmenPabrikDetailEntity.setTanggalAktif(bean2.getCreatedDate());
                        itRekruitmenPabrikDetailEntity.setNoKontrak("");
                        itRekruitmenPabrikDetailEntity.setStatusGiling("DMG");
                        itRekruitmenPabrikDetailEntity.setApprovalGmFlag("N");
                        itRekruitmenPabrikDetailEntity.setApprovalSdmFlag("N");

                        itRekruitmenPabrikDetailEntity.setFlag(bean2.getFlag());
                        itRekruitmenPabrikDetailEntity.setAction(bean2.getAction());
                        itRekruitmenPabrikDetailEntity.setCreatedWho(bean2.getCreatedWho());
                        itRekruitmenPabrikDetailEntity.setLastUpdateWho(bean2.getLastUpdateWho());
                        itRekruitmenPabrikDetailEntity.setCreatedDate(bean2.getCreatedDate());
                        itRekruitmenPabrikDetailEntity.setLastUpdate(bean2.getLastUpdate());
                        rekruitmenPabrikDetailDao.addAndSave(itRekruitmenPabrikDetailEntity);
                        saved=true;

                        if (saved){
                            String id;
                            try {
                                // Generating ID, get from postgre sequence
                                id = rekruitmenPabrikDetailDao.getNextRekruitmenPabrikDetailHistoryId();
                            } catch (HibernateException e) {
                                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                            }

                            ItRekruitmenPabrikDetailHistoryEntity entityData = new ItRekruitmenPabrikDetailHistoryEntity();
                            entityData.setRekruitmenPabrikId(rekruitmenPabrikId);
                            entityData.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailId);

                            entityData.setNip(rekruitmenPabrikDetail.getNip());
                            entityData.setNamaPegawai(rekruitmenPabrikDetail.getNamaPegawai());
                            entityData.setPosisiLama(rekruitmenPabrikDetail.getPosisiLama());
                            entityData.setPosisiBaru(rekruitmenPabrikDetail.getPosisiBaru());
                            entityData.setRekruitmenPabrikDetailId(rekruitmenPabrikDetailId);
                            entityData.setTanggalAktif(bean2.getCreatedDate());
                            entityData.setNoKontrak("");
                            entityData.setStatusGiling("DMG");
                            entityData.setApprovalGmFlag("N");
                            entityData.setApprovalSdmFlag("N");

                            entityData.setFlag(bean2.getFlag());
                            entityData.setAction(bean2.getAction());
                            entityData.setCreatedWho(bean2.getCreatedWho());
                            entityData.setLastUpdateWho(bean2.getLastUpdateWho());
                            entityData.setCreatedDate(bean2.getCreatedDate());
                            entityData.setLastUpdate(bean2.getLastUpdate());
                            entityData.setId(id);

                            try {
                                rekruitmenPabrikDetailDao.addAndSaveHistory(entityData);
                            } catch (HibernateException e) {
                                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }
                for (ItRekruitmenPabrikDetailEntity rekruitmenPabrikDetailSave : itRekruitmenPabrikDetailEntityOld) {
                    if (!rekruitmenDetailId.contains(rekruitmenPabrikDetailSave.getRekruitmenPabrikDetailId())){
                        rekruitmenPabrikDetailSave.setFlag("N");
                        rekruitmenPabrikDetailSave.setLastUpdate(bean2.getLastUpdate());
                        rekruitmenPabrikDetailSave.setLastUpdateWho(bean2.getLastUpdateWho());
                        rekruitmenPabrikDetailSave.setAction("U");
                        rekruitmenPabrikDetailDao.updateAndSave(rekruitmenPabrikDetailSave);
                    }
                }
            }
        }
        logger.info("[IjinBoImpl.saveAdd] end process <<<");
        return null;
    }
}