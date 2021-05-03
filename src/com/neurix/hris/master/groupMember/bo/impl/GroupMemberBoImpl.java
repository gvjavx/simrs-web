package com.neurix.hris.master.groupMember.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.group.dao.GroupDao;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.groupMember.bo.GroupMemberBo;
import com.neurix.hris.master.groupMember.dao.GroupMemberDao;
import com.neurix.hris.master.groupMember.model.GroupMember;
import com.neurix.hris.master.groupMember.model.ImtHrisGroupMember;
import com.neurix.hris.master.shift.dao.ShiftDao;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class GroupMemberBoImpl implements GroupMemberBo {
    protected static transient Logger logger = Logger.getLogger(GroupMemberBoImpl.class);

    private GroupMemberDao groupMemberDao;
    private BranchDao branchDao;
    private BiodataDao biodataDao;
    private PersonilPositionDao personilPositionDao;
    private PositionDao positionDao;
    private DepartmentDao departmentDao;

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GroupMemberBoImpl.logger = logger;
    }

    public GroupMemberDao getGroupMemberDao() {
        return groupMemberDao;
    }

    public void setGroupMemberDao(GroupMemberDao groupMemberDao) {
        this.groupMemberDao = groupMemberDao;
    }

    @Override
    public void saveDelete(GroupMember bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            String groupMemberId = bean.getGroupMemberId();
            ImtHrisGroupMember imtHrisGroupMember = null;
            try {
                // Get data from database by ID
                imtHrisGroupMember = groupMemberDao.getById("groupMemberId", groupMemberId);
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imtHrisGroupMember != null) {
                // Modify from bean to entity serializable
                imtHrisGroupMember.setGroupMemberId(bean.getGroupMemberId());
                imtHrisGroupMember.setNip(bean.getNip());
                imtHrisGroupMember.setGroupId(bean.getGroupId());
                imtHrisGroupMember.setBranchId(bean.getBranchId());

                imtHrisGroupMember.setFlag(bean.getFlag());
                imtHrisGroupMember.setAction(bean.getAction());
                imtHrisGroupMember.setLastUpdateWho(bean.getLastUpdateWho());
                imtHrisGroupMember.setLastUpdate(bean.getLastUpdate());
                try {
                    // Delete (Edit) into database
                    groupMemberDao.updateAndSave(imtHrisGroupMember);
                } catch (HibernateException e) {
                    logger.error("[CutiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Cuti, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[CutiBoImpl.saveDelete] Error, not found data Cuti with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Cuti with request id, please check again your data ...");

            }
        }
        logger.info("[CutiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(GroupMember bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            ImtHrisGroupMember entityData = new ImtHrisGroupMember();
            entityData.setGroupMemberId(bean.getGroupMemberId());
            entityData.setNip(bean.getNip());
            entityData.setGroupId(bean.getGroupId());
            entityData.setBranchId(bean.getBranchId());

            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                groupMemberDao.updateAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public GroupMember saveAdd(GroupMember bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String groupmemberId;
            try {
                // Generating ID, get from postgre sequence
                groupmemberId = groupMemberDao.getNextGroupMemberId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImtHrisGroupMember entityData = new ImtHrisGroupMember();

            entityData.setGroupMemberId(groupmemberId);
            entityData.setGroupId(bean.getGroupId());
            entityData.setNip(bean.getNip());
            entityData.setBranchId(bean.getBranchId());

            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                groupMemberDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ShiftBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<GroupMember> getByCriteria(GroupMember searchBean) throws GeneralBOException {
        logger.info("[GroupMemberBo.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<GroupMember> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGroupId() != null && !"".equalsIgnoreCase(searchBean.getGroupId())) {
                hsCriteria.put("group_id", searchBean.getGroupId());
            }
            if (searchBean.getGroupMemberId() != null && !"".equalsIgnoreCase(searchBean.getGroupMemberId())) {
                hsCriteria.put("group_member_id", searchBean.getGroupMemberId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
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


            List<ImtHrisGroupMember> imtHrisGroupMemberList = null;
            try {

                imtHrisGroupMemberList = groupMemberDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imtHrisGroupMemberList != null){
                GroupMember returnData;
                // Looping from dao to object and save in collection
                for(ImtHrisGroupMember listEntity : imtHrisGroupMemberList){
                    returnData = new GroupMember();
                    returnData.setGroupId(listEntity.getGroupId());
                    returnData.setNip(listEntity.getNip());
                    returnData.setBranchId(listEntity.getBranchId());
                    returnData.setGroupMemberId(listEntity.getGroupMemberId());

                    if (listEntity.getBranchId()!=null&&!listEntity.getNip().equals("")){
                        Map hsCriteria2=new HashMap();
                        hsCriteria2.put("flag","Y");
                        hsCriteria2.put("branchId",listEntity.getBranchId());
                        List<ImBranches> imBranches = new ArrayList<>();
                        imBranches = branchDao.getByCriteria(hsCriteria2);
                        for (ImBranches branches: imBranches){
                            returnData.setBranchName(branches.getBranchName());
                        }
                    }
                    if (listEntity.getNip()!=null&&!listEntity.getNip().equals("")){
                        Map hsCriteria3 = new HashMap();
                        hsCriteria3.put("flag","Y");
                        hsCriteria3.put("nip",listEntity.getNip());

                        List<ImBiodataEntity> imBiodataEntities = new ArrayList<>();
                        imBiodataEntities= biodataDao.getByCriteria(hsCriteria3);

                        if (imBiodataEntities.size()!=0){
                            for (ImBiodataEntity imBiodataEntity:imBiodataEntities){
                                returnData.setNama(imBiodataEntity.getNamaPegawai());
                                returnData.setGolonganName("Golongan "+imBiodataEntity.getGolongan().substring(1));
                            }
                        }
                    }
                    if (listEntity.getNip()!=null&&!listEntity.getNip().equals("")){
                        Map hsCriteria4 = new HashMap();
                        hsCriteria4.put("flag","Y");
                        hsCriteria4.put("nip",listEntity.getNip());

                        List<ItPersonilPositionEntity> itPersonilPositionEntities= new ArrayList<>();
                        itPersonilPositionEntities= personilPositionDao.getByCriteria(hsCriteria4);

                        for (ItPersonilPositionEntity itPersonilPositionEntity:itPersonilPositionEntities){
                            if (itPersonilPositionEntity.getPositionId()!=null&&!itPersonilPositionEntity.getPositionId().equals("")){
                                Map hsCriteria5 = new HashMap();
                                hsCriteria5.put("flag","Y");
                                hsCriteria5.put("nip",listEntity.getNip());
                                List<ItPersonilPositionEntity> itPersonilPositionEntities1= new ArrayList<>();
                                itPersonilPositionEntities1= personilPositionDao.getByCriteria(hsCriteria5);
                                for (ItPersonilPositionEntity personilPositionEntity:itPersonilPositionEntities1){
                                    ImPosition imPosition = positionDao.getById("positionId",personilPositionEntity.getPositionId());
                                    returnData.setPositionName(imPosition.getPositionName());
                                    ImDepartmentEntity imDepartmentEntity = departmentDao.getById("departmentId",imPosition.getDepartmentId(),"Y");
                                    returnData.setDivisiName(imDepartmentEntity.getDepartmentName());
                                }
                            }
                        }
                    }

                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());

                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;

    }

    @Override
    public List<GroupMember> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
