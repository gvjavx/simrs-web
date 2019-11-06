package com.neurix.hris.transaksi.jadwalShiftKerja.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.group.dao.GroupDao;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.groupShift.dao.GroupShiftDao;
import com.neurix.hris.master.groupShift.model.GroupShift;
import com.neurix.hris.master.groupShift.model.ImHrisGroupShift;
import com.neurix.hris.master.shift.dao.ShiftDao;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.dao.JadwalShiftKerjaDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.dao.JadwalShiftKerjaDetailDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.*;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class JadwalShiftKerjaBoImpl implements JadwalShiftKerjaBo {

    protected static transient Logger logger = Logger.getLogger(JadwalShiftKerjaBoImpl.class);
    private JadwalShiftKerjaDao jadwalShiftKerjaDao;
    private JadwalShiftKerjaDetailDao jadwalShiftKerjaDetailDao;
    private BranchDao branchDao;
    private GroupShiftDao groupShiftDao;
    private ShiftDao shiftDao;
    private GroupDao groupDao;

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public ShiftDao getShiftDao() {
        return shiftDao;
    }

    public void setShiftDao(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    public GroupShiftDao getGroupShiftDao() {
        return groupShiftDao;
    }

    public void setGroupShiftDao(GroupShiftDao groupShiftDao) {
        this.groupShiftDao = groupShiftDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public JadwalShiftKerjaDetailDao getJadwalShiftKerjaDetailDao() {
        return jadwalShiftKerjaDetailDao;
    }

    public void setJadwalShiftKerjaDetailDao(JadwalShiftKerjaDetailDao jadwalShiftKerjaDetailDao) {
        this.jadwalShiftKerjaDetailDao = jadwalShiftKerjaDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JadwalShiftKerjaBoImpl.logger = logger;
    }

    public JadwalShiftKerjaDao getJadwalShiftKerjaDao() {
        return jadwalShiftKerjaDao;
    }

    public void setJadwalShiftKerjaDao(JadwalShiftKerjaDao jadwalShiftKerjaDao) {
        this.jadwalShiftKerjaDao = jadwalShiftKerjaDao;
    }

    @Override
    public void saveDelete(JadwalShiftKerja bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(JadwalShiftKerja bean) throws GeneralBOException {

    }

    @Override
    public JadwalShiftKerja saveAdd(JadwalShiftKerja bean) throws GeneralBOException {
        boolean saved;
        logger.info("[RekruitmenPabrikBoImpl.saveAdd] start process >>>");
        String jadwalShiftKerjaId = null;

        if (bean!=null){
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<GroupShift> listOfsearchGroupShift = (List<GroupShift>) session.getAttribute("ListOfResultGroupShift");
            if (listOfsearchGroupShift != null) {
                jadwalShiftKerjaId = jadwalShiftKerjaDao.getNextJadwalShiftKerjaId();
                ItJadwalShiftKerjaEntity itJadwalShiftKerjaEntity = new ItJadwalShiftKerjaEntity();

                itJadwalShiftKerjaEntity.setJadwalShiftKerjaId(jadwalShiftKerjaId);
                itJadwalShiftKerjaEntity.setStatusGiling(bean.getStatusGiling());
                itJadwalShiftKerjaEntity.setBranchId(bean.getBranchId());
                itJadwalShiftKerjaEntity.setTanggal(bean.getTanggal());
                itJadwalShiftKerjaEntity.setJadwalShiftKerjaName(bean.getJadwalShiftKerjaName());
                itJadwalShiftKerjaEntity.setKeterangan(bean.getKeterangan());

                itJadwalShiftKerjaEntity.setFlag(bean.getFlag());
                itJadwalShiftKerjaEntity.setAction(bean.getAction());
                itJadwalShiftKerjaEntity.setCreatedWho(bean.getCreatedWho());
                itJadwalShiftKerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itJadwalShiftKerjaEntity.setCreatedDate(bean.getCreatedDate());
                itJadwalShiftKerjaEntity.setLastUpdate(bean.getLastUpdate());
                jadwalShiftKerjaDao.addAndSave(itJadwalShiftKerjaEntity);

                //save to jadwal shift kerja detail
                for (GroupShift groupShift : listOfsearchGroupShift) {
                    ItJadwalShiftKerjaDetailEntity itJadwalShiftKerjaDetailEntity = new ItJadwalShiftKerjaDetailEntity();
                    String jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailDao.getNextJadwalShiftKerjaDetailId();

                    itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaDetailId(jadwalShiftKerjaDetailId);
                    itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaId(jadwalShiftKerjaId);
                    itJadwalShiftKerjaDetailEntity.setShiftGroupId(groupShift.getGroupShiftId());

                    itJadwalShiftKerjaDetailEntity.setFlag(bean.getFlag());
                    itJadwalShiftKerjaDetailEntity.setAction(bean.getAction());
                    itJadwalShiftKerjaDetailEntity.setCreatedWho(bean.getCreatedWho());
                    itJadwalShiftKerjaDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    itJadwalShiftKerjaDetailEntity.setCreatedDate(bean.getCreatedDate());
                    itJadwalShiftKerjaDetailEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // insert into database
                        jadwalShiftKerjaDetailDao.addAndSave(itJadwalShiftKerjaDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[RekruitmenBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data Rekruitmen, please info to your admin..." + e.getMessage());
                    }

                }
            }
        }
        logger.info("[IjinBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<JadwalShiftKerja> getByCriteria(JadwalShiftKerja searchBean) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<JadwalShiftKerja> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getJadwalShiftKerjaName() != null && !"".equalsIgnoreCase(searchBean.getJadwalShiftKerjaName())) {
                hsCriteria.put("jadwal_shift_kerja_name", searchBean.getJadwalShiftKerjaName());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getStatusGiling() != null && !"".equalsIgnoreCase(searchBean.getStatusGiling())) {
                hsCriteria.put("status_giling", searchBean.getStatusGiling());
            }
            if (searchBean.getStTanggalAwal() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalAwal()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalAwal());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalAkhir() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalAkhir()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalAkhir());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            hsCriteria.put("flag", "Y");


            List<ItJadwalShiftKerjaEntity> itJadwalShiftKerjaEntity = null;
            try {

                itJadwalShiftKerjaEntity = jadwalShiftKerjaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[JadwalShiftKerjaBoImpl.getSearchJadwalShiftKerjaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            List<ItJadwalShiftKerjaDetailEntity> jadwalShiftKerjaDetails = new ArrayList<>();
            JadwalShiftKerja returnJadwalShiftKerja;
            String jadwalKerjaId=null;
            if(itJadwalShiftKerjaEntity != null){
                List<ImBranches> branch = new ArrayList<>();
                for(ItJadwalShiftKerjaEntity jadwalShiftKerjaEntity : itJadwalShiftKerjaEntity){
                    Map hsCriteria7=new HashMap();
                    hsCriteria7.put("flag","Y");
                    hsCriteria7.put("jadwal_shift_kerja_id",jadwalShiftKerjaEntity.getJadwalShiftKerjaId());
                    jadwalShiftKerjaDetails=jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria7);
                    for (ItJadwalShiftKerjaDetailEntity itJadwalShiftKerjaDetailEntity :jadwalShiftKerjaDetails){
                        returnJadwalShiftKerja = new JadwalShiftKerja();
                        if (jadwalShiftKerjaEntity.getJadwalShiftKerjaId().equals(jadwalKerjaId)){
                            returnJadwalShiftKerja.setJadwalShiftKerjaId("");
                            returnJadwalShiftKerja.setTanggal(null);
                            returnJadwalShiftKerja.setBranchId("");
                            returnJadwalShiftKerja.setStatusGiling("");
                            returnJadwalShiftKerja.setTamp(false);
                        }
                        else {
                            jadwalKerjaId=jadwalShiftKerjaEntity.getJadwalShiftKerjaId();
                            returnJadwalShiftKerja.setJadwalShiftKerjaId(jadwalShiftKerjaEntity.getJadwalShiftKerjaId());
                            returnJadwalShiftKerja.setJadwalShiftKerjaName(jadwalShiftKerjaEntity.getJadwalShiftKerjaName());
                            returnJadwalShiftKerja.setTanggal(jadwalShiftKerjaEntity.getTanggal());
                            returnJadwalShiftKerja.setBranchId(jadwalShiftKerjaEntity.getBranchId());
                            returnJadwalShiftKerja.setStatusGiling(jadwalShiftKerjaEntity.getStatusGiling());
                            returnJadwalShiftKerja.setKeterangan(jadwalShiftKerjaEntity.getKeterangan());

                            if (jadwalShiftKerjaEntity.getBranchId()!=null){
                                Map hsCriteria2 = new HashMap();
                                hsCriteria2.put("flag","Y");
                                hsCriteria2.put("branch_id",jadwalShiftKerjaEntity.getBranchId());
                                branch = branchDao.getByCriteria(hsCriteria2);
                                for (ImBranches imBranches : branch){
                                    returnJadwalShiftKerja.setBranchName(imBranches.getBranchName());
                                }
                            }
                            if(jadwalShiftKerjaEntity.getStatusGiling()!=null){
                                if (jadwalShiftKerjaEntity.getStatusGiling().equals("DMG")){
                                    returnJadwalShiftKerja.setStatusGilingName("Dalam Masa Giling");
                                }
                                else if(jadwalShiftKerjaEntity.getStatusGiling().equals("LMG")){
                                    returnJadwalShiftKerja.setStatusGilingName("Luar Masa Giling");
                                }
                            }
                        }
                        if (itJadwalShiftKerjaDetailEntity.getJadwalShiftKerjaId()!=null){
                            /*List<ItJadwalShiftKerjaDetailEntity> itJadwalShiftKerjaDetailEntities = new ArrayList<>();
                            Map hsCriteria2 = new HashMap();
                            hsCriteria2.put("flag","Y");
                            hsCriteria2.put("jadwal_shift_kerja_id",itJadwalShiftKerjaDetailEntity.getJadwalShiftKerjaId());
                            itJadwalShiftKerjaDetailEntities = jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria2);*/
//                            for (ItJadwalShiftKerjaDetailEntity jadwalShiftKerjaDetailEntity: itJadwalShiftKerjaDetailEntities){
                                List<ImHrisGroupShift> groupShifts = new ArrayList<>();
                                Map hsCriteria3 = new HashMap();
                                hsCriteria3.put("flag","Y");
                                hsCriteria3.put("group_shift_id",itJadwalShiftKerjaDetailEntity.getShiftGroupId());
                                groupShifts = groupShiftDao.getByCriteria(hsCriteria3);

                                for (ImHrisGroupShift groupShift:groupShifts){
                                    ImHrisShiftEntity imHrisShiftEntity = shiftDao.getById("shiftId",groupShift.getShiftId(),"Y");
                                    ImHrisGroupEntity imHrisGroupEntity = groupDao.getById("groupId",groupShift.getGroupId(),"Y");
                                    returnJadwalShiftKerja.setShiftName(imHrisShiftEntity.getShiftName());
                                    returnJadwalShiftKerja.setGroupName(imHrisGroupEntity.getGroupName());
                                }

//                            }
                        }
                        returnJadwalShiftKerja.setAction(jadwalShiftKerjaEntity.getAction());
                        returnJadwalShiftKerja.setFlag(jadwalShiftKerjaEntity.getFlag());
                        returnJadwalShiftKerja.setCreatedWho(jadwalShiftKerjaEntity.getCreatedWho());
                        returnJadwalShiftKerja.setCreatedDate(jadwalShiftKerjaEntity.getCreatedDate());
                        returnJadwalShiftKerja.setLastUpdate(jadwalShiftKerjaEntity.getLastUpdate());

                        listOfResult.add(returnJadwalShiftKerja);
                    }
                }
            }
        }
        logger.info("[JadwalShiftKerjaBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<JadwalShiftKerjaDetail> getByCriteriaDetail(JadwalShiftKerjaDetail searchBean) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getByCriteriaDetail] start process >>>");

        // Mapping with collection and put
        List<JadwalShiftKerjaDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getJadwalShiftKerjaId() != null && !"".equalsIgnoreCase(searchBean.getJadwalShiftKerjaId())) {
                hsCriteria.put("jadwal_shift_kerja_id", searchBean.getJadwalShiftKerjaId());
            }
            if (searchBean.getJadwalShiftKerjaDetailId() != null && !"".equalsIgnoreCase(searchBean.getJadwalShiftKerjaDetailId())) {
                hsCriteria.put("jadwal_shift_kerja_detail_id", searchBean.getJadwalShiftKerjaDetailId());
            }
            if (searchBean.getShiftGroupId() != null && !"".equalsIgnoreCase(searchBean.getShiftGroupId())) {
                hsCriteria.put("shift_group", searchBean.getShiftGroupId());
            }
            hsCriteria.put("flag", "Y");

            List<ItJadwalShiftKerjaDetailEntity> itJadwalShiftKerjaDetailEntity = null;
            try {

                itJadwalShiftKerjaDetailEntity = jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[JadwalShiftKerjaBoImpl.getSearchJadwalShiftKerjaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if(itJadwalShiftKerjaDetailEntity != null){
                JadwalShiftKerjaDetail returnJadwalShiftKerjaDetail;
                // Looping from dao to object and save in collection
                for(ItJadwalShiftKerjaDetailEntity jadwalShiftKerjaDetailEntity : itJadwalShiftKerjaDetailEntity){
                    returnJadwalShiftKerjaDetail = new JadwalShiftKerjaDetail();
                    returnJadwalShiftKerjaDetail.setJadwalShiftKerjaDetailId(jadwalShiftKerjaDetailEntity.getJadwalShiftKerjaDetailId());
                    returnJadwalShiftKerjaDetail.setJadwalShiftKerjaId(jadwalShiftKerjaDetailEntity.getJadwalShiftKerjaId());
                    returnJadwalShiftKerjaDetail.setShiftGroupId(jadwalShiftKerjaDetailEntity.getShiftGroupId());
//
                    returnJadwalShiftKerjaDetail.setAction(jadwalShiftKerjaDetailEntity.getAction());
                    returnJadwalShiftKerjaDetail.setFlag(jadwalShiftKerjaDetailEntity.getFlag());
                    returnJadwalShiftKerjaDetail.setCreatedWho(jadwalShiftKerjaDetailEntity.getCreatedWho());
                    returnJadwalShiftKerjaDetail.setCreatedDate(jadwalShiftKerjaDetailEntity.getCreatedDate());
                    returnJadwalShiftKerjaDetail.setLastUpdate(jadwalShiftKerjaDetailEntity.getLastUpdate());

                    listOfResult.add(returnJadwalShiftKerjaDetail);
                }
            }
        }
        logger.info("[JadwalShiftKerjaBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<JadwalShiftKerja> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    @Override
    public JadwalShiftKerja saveEdit(JadwalShiftKerja bean, JadwalShiftKerjaDetail bean2) throws GeneralBOException {
        logger.info("[RekruitmenPabrikBoImpl.saveEdit] start process >>>");
        boolean saved;
        String jadwalShiftKerjaId = bean.getJadwalShiftKerjaId();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getJadwalShiftKerjaId() != null && !"".equalsIgnoreCase(bean.getJadwalShiftKerjaId())) {
                hsCriteria.put("jadwal_shift_kerja_id", bean.getJadwalShiftKerjaId());
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

            List<ItJadwalShiftKerjaDetailEntity> itJadwalShiftKerjaDetailEntities = null;
            try {

                itJadwalShiftKerjaDetailEntities = jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekruitmenPabrikBoImpl.getSearchRekruitmenPabrikByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<GroupShift> listOfsearchJadwalShiftKerjaDetail = (List<GroupShift>) session.getAttribute("ListOfResultGroupShift");
            if (listOfsearchJadwalShiftKerjaDetail != null) {
                List<String> jadwalDetailId = new ArrayList<>();

                for (GroupShift groupShift : listOfsearchJadwalShiftKerjaDetail) {
                    jadwalDetailId.add(groupShift.getGroupShiftId());
                    Map hsCriteria2 = new HashMap();
                    hsCriteria2.put("jadwal_shift_kerja_id",bean.getJadwalShiftKerjaId());
                    hsCriteria2.put("shift_group_id",groupShift.getGroupShiftId());
                    hsCriteria2.put("flag","Y");
                    List<ItJadwalShiftKerjaDetailEntity> cekJadwalShiftKerjaDetail = new ArrayList<>();
                     cekJadwalShiftKerjaDetail = jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria2);
                    if (cekJadwalShiftKerjaDetail.size()==0){
                        String jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailDao.getNextJadwalShiftKerjaDetailId();
                        ItJadwalShiftKerjaDetailEntity itJadwalShiftKerjaDetailEntity = new ItJadwalShiftKerjaDetailEntity();

                        itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaDetailId(jadwalShiftKerjaDetailId);
                        itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaId(jadwalShiftKerjaId);
                        itJadwalShiftKerjaDetailEntity.setShiftGroupId(groupShift.getGroupShiftId());

                        itJadwalShiftKerjaDetailEntity.setFlag(bean2.getFlag());
                        itJadwalShiftKerjaDetailEntity.setAction(bean2.getAction());
                        itJadwalShiftKerjaDetailEntity.setCreatedWho(bean2.getCreatedWho());
                        itJadwalShiftKerjaDetailEntity.setLastUpdateWho(bean2.getLastUpdateWho());
                        itJadwalShiftKerjaDetailEntity.setCreatedDate(bean2.getCreatedDate());
                        itJadwalShiftKerjaDetailEntity.setLastUpdate(bean2.getLastUpdate());
                        jadwalShiftKerjaDetailDao.addAndSave(itJadwalShiftKerjaDetailEntity);
                    }
                }
                for (ItJadwalShiftKerjaDetailEntity jadwalShiftKerjaDetailSave : itJadwalShiftKerjaDetailEntities) {
                    if (!jadwalDetailId.contains(jadwalShiftKerjaDetailSave.getShiftGroupId())){
                        jadwalShiftKerjaDetailSave.setFlag("N");
                        jadwalShiftKerjaDetailSave.setLastUpdate(bean2.getLastUpdate());
                        jadwalShiftKerjaDetailSave.setLastUpdateWho(bean2.getLastUpdateWho());
                        jadwalShiftKerjaDetailSave.setAction("U");
                        jadwalShiftKerjaDetailDao.updateAndSave(jadwalShiftKerjaDetailSave);
                    }
                }
            }
        }
        logger.info("[IjinBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public List<GroupShift> deleteJadwalShiftKerja(String id) throws GeneralBOException {
        String contextPath = ServletActionContext.getRequest().getContextPath();
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<GroupShift> listComboGroupShift = new ArrayList();
        List<GroupShift> listComboGroupShiftFix = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {

            listComboGroupShift = (List<GroupShift>) session.getAttribute("ListOfResultGroupShift");
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listComboGroupShift != null) {
            for (GroupShift groupShift : listComboGroupShift) {
                if (!id.equals(groupShift.getGroupShiftId())) {
                    listComboGroupShiftFix.add(groupShift);
                }
            }
            session.setAttribute("ListOfResultGroupShift", listComboGroupShiftFix);
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboGroupShiftFix;
    }
    @Override
    public String saveTanggalOtomatis(JadwalShiftKerja bean) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.saveTanggalOtomatis] start process >>>");
        String status="";
        // Mapping with collection and put
        List<JadwalShiftKerja> listOfResult = new ArrayList();
        List<ItJadwalShiftKerjaEntity> itJadwalShiftKerjaEntity = null;
        try {
//            itJadwalShiftKerjaEntity = jadwalShiftKerjaDao.getJadwalShiftKerja(bean.getTanggalAwal(),bean.getTanggalAkhir());
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoImpl.getJadwalShiftKerja] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        List<ItJadwalShiftKerjaDetailEntity> jadwalShiftKerjaDetails = new ArrayList<>();
        JadwalShiftKerja returnJadwalShiftKerja;
        String jadwalKerjaId=null;
        if(itJadwalShiftKerjaEntity != null){
            List<ImBranches> branch = new ArrayList<>();
            for(ItJadwalShiftKerjaEntity jadwalShiftKerjaEntity : itJadwalShiftKerjaEntity){
                Map hsCriteria7=new HashMap();
                hsCriteria7.put("flag","Y");
                hsCriteria7.put("jadwal_shift_kerja_id",jadwalShiftKerjaEntity.getJadwalShiftKerjaId());
                jadwalShiftKerjaDetails=jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria7);
                for (ItJadwalShiftKerjaDetailEntity itJadwalShiftKerjaDetailEntity :jadwalShiftKerjaDetails){
                    returnJadwalShiftKerja = new JadwalShiftKerja();
                    if (jadwalShiftKerjaEntity.getJadwalShiftKerjaId().equals(jadwalKerjaId)){
                        returnJadwalShiftKerja.setJadwalShiftKerjaId("");
                        returnJadwalShiftKerja.setTanggal(null);
                        returnJadwalShiftKerja.setBranchId("");
                        returnJadwalShiftKerja.setStatusGiling("");
                        returnJadwalShiftKerja.setTamp(false);
                    }
                    else {
                        jadwalKerjaId=jadwalShiftKerjaEntity.getJadwalShiftKerjaId();
                        returnJadwalShiftKerja.setJadwalShiftKerjaId(jadwalShiftKerjaEntity.getJadwalShiftKerjaId());
                        returnJadwalShiftKerja.setJadwalShiftKerjaName(jadwalShiftKerjaEntity.getJadwalShiftKerjaName());
                        returnJadwalShiftKerja.setTanggal(jadwalShiftKerjaEntity.getTanggal());
                        returnJadwalShiftKerja.setBranchId(jadwalShiftKerjaEntity.getBranchId());
                        returnJadwalShiftKerja.setStatusGiling(jadwalShiftKerjaEntity.getStatusGiling());
                        returnJadwalShiftKerja.setKeterangan(jadwalShiftKerjaEntity.getKeterangan());

                        if (jadwalShiftKerjaEntity.getBranchId()!=null){
                            Map hsCriteria2 = new HashMap();
                            hsCriteria2.put("flag","Y");
                            hsCriteria2.put("branch_id",jadwalShiftKerjaEntity.getBranchId());
                            branch = branchDao.getByCriteria(hsCriteria2);
                            for (ImBranches imBranches : branch){
                                returnJadwalShiftKerja.setBranchName(imBranches.getBranchName());
                            }
                        }
                        if(jadwalShiftKerjaEntity.getStatusGiling()!=null){
                            if (jadwalShiftKerjaEntity.getStatusGiling().equals("DMG")){
                                returnJadwalShiftKerja.setStatusGilingName("Dalam Masa Giling");
                            }
                            else if(jadwalShiftKerjaEntity.getStatusGiling().equals("LMG")){
                                returnJadwalShiftKerja.setStatusGilingName("Luar Masa Giling");
                            }
                        }
                    }
                    if (itJadwalShiftKerjaDetailEntity.getJadwalShiftKerjaId()!=null){
                            /*List<ItJadwalShiftKerjaDetailEntity> itJadwalShiftKerjaDetailEntities = new ArrayList<>();
                            Map hsCriteria2 = new HashMap();
                            hsCriteria2.put("flag","Y");
                            hsCriteria2.put("jadwal_shift_kerja_id",itJadwalShiftKerjaDetailEntity.getJadwalShiftKerjaId());
                            itJadwalShiftKerjaDetailEntities = jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria2);*/
//                            for (ItJadwalShiftKerjaDetailEntity jadwalShiftKerjaDetailEntity: itJadwalShiftKerjaDetailEntities){
                        List<ImHrisGroupShift> groupShifts = new ArrayList<>();
                        Map hsCriteria3 = new HashMap();
                        hsCriteria3.put("flag","Y");
                        hsCriteria3.put("group_shift_id",itJadwalShiftKerjaDetailEntity.getShiftGroupId());
                        groupShifts = groupShiftDao.getByCriteria(hsCriteria3);

                        for (ImHrisGroupShift groupShift:groupShifts){
                            ImHrisShiftEntity imHrisShiftEntity = shiftDao.getById("shiftId",groupShift.getShiftId(),"Y");
                            ImHrisGroupEntity imHrisGroupEntity = groupDao.getById("groupId",groupShift.getGroupId(),"Y");
                            returnJadwalShiftKerja.setShiftName(imHrisShiftEntity.getShiftName());
                            returnJadwalShiftKerja.setGroupName(imHrisGroupEntity.getGroupName());
                        }

//                            }
                    }
                    returnJadwalShiftKerja.setAction(jadwalShiftKerjaEntity.getAction());
                    returnJadwalShiftKerja.setFlag(jadwalShiftKerjaEntity.getFlag());
                    returnJadwalShiftKerja.setCreatedWho(jadwalShiftKerjaEntity.getCreatedWho());
                    returnJadwalShiftKerja.setCreatedDate(jadwalShiftKerjaEntity.getCreatedDate());
                    returnJadwalShiftKerja.setLastUpdate(jadwalShiftKerjaEntity.getLastUpdate());

                    listOfResult.add(returnJadwalShiftKerja);
                }
            }
        }
        logger.info("[JadwalShiftKerjaBoImpl.saveTanggalOtomatis] end process <<<");

        return status;
    }

    @Override
    public List<JadwalShiftKerja> getJadwalforReport(JadwalShiftKerja bean) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalforReport] start process >>>");
        List<JadwalShiftKerja> dataFinal = new ArrayList<>();

        try {
            dataFinal = jadwalShiftKerjaDao.getJadwalForReport(bean.getTanggalAwal(),bean.getTanggalAkhir());
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoImpl.getJadwalShiftKerja] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        logger.info("[JadwalShiftKerjaBoImpl.getJadwalforReport] end process <<<");

        return dataFinal;
    }
}
