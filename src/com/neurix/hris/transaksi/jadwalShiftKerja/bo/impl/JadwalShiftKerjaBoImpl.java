package com.neurix.hris.transaksi.jadwalShiftKerja.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.group.dao.GroupDao;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.groupShift.dao.GroupShiftDao;
import com.neurix.hris.master.groupShift.model.GroupShift;
import com.neurix.hris.master.groupShift.model.ImHrisGroupShift;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.libur.dao.LiburDao;
import com.neurix.hris.master.libur.model.ImLiburEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.profesi.dao.ProfesiDao;
import com.neurix.hris.master.profesi.model.ImProfesiEntity;
import com.neurix.hris.master.shift.dao.ShiftDao;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import com.neurix.hris.transaksi.absensi.dao.AbsensiPegawaiDao;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.dao.HistoryOnCallDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.dao.JadwalShiftKerjaDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.dao.JadwalShiftKerjaDetailDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.*;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    private PositionDao positionDao;
    private PersonilPositionDao personilPositionDao;
    private ProfesiDao profesiDao;
    private LiburDao liburDao;
    private PositionBagianDao positionBagianDao;
    private HistoryOnCallDao historyOnCallDao;
    private AbsensiPegawaiDao absensiPegawaiDao;

    public AbsensiPegawaiDao getAbsensiPegawaiDao() {
        return absensiPegawaiDao;
    }

    public void setAbsensiPegawaiDao(AbsensiPegawaiDao absensiPegawaiDao) {
        this.absensiPegawaiDao = absensiPegawaiDao;
    }

    public HistoryOnCallDao getHistoryOnCallDao() {
        return historyOnCallDao;
    }

    public void setHistoryOnCallDao(HistoryOnCallDao historyOnCallDao) {
        this.historyOnCallDao = historyOnCallDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public LiburDao getLiburDao() {
        return liburDao;
    }

    public void setLiburDao(LiburDao liburDao) {
        this.liburDao = liburDao;
    }

    public ProfesiDao getProfesiDao() {
        return profesiDao;
    }

    public void setProfesiDao(ProfesiDao profesiDao) {
        this.profesiDao = profesiDao;
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
        logger.info("[JadwalShiftKerjaBoimpl.saveEdit] start process >>>");
        String jadwalShiftKerjaId = bean.getJadwalShiftKerjaId();

        //Update jadwal shift kerja
        ItJadwalShiftKerjaEntity jadwalShiftKerjaEntity;
        try {
            jadwalShiftKerjaEntity = jadwalShiftKerjaDao.getById("jadwalShiftKerjaId",jadwalShiftKerjaId,"Y");
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoimpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        jadwalShiftKerjaEntity.setKeterangan(bean.getKeterangan());
        jadwalShiftKerjaEntity.setAction(bean.getAction());
        jadwalShiftKerjaEntity.setLastUpdate(bean.getLastUpdate());
        jadwalShiftKerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
        try {
            jadwalShiftKerjaDao.updateAndSave(jadwalShiftKerjaEntity);
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoimpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        //Menghapus semua detail
        List<ItJadwalShiftKerjaDetailEntity> jadwalShiftKerjaDetailEntityList = new ArrayList<>();
        try {
            Map hsCriteria = new HashMap();
            hsCriteria.put("jadwal_shift_kerja_id",jadwalShiftKerjaId);
            hsCriteria.put("flag","Y");
            jadwalShiftKerjaDetailEntityList = jadwalShiftKerjaDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoimpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        for (ItJadwalShiftKerjaDetailEntity deleteJadwalDetail : jadwalShiftKerjaDetailEntityList ){
            deleteJadwalDetail.setFlag("N");
            deleteJadwalDetail.setAction("D");
            deleteJadwalDetail.setLastUpdate(bean.getLastUpdate());
            deleteJadwalDetail.setLastUpdateWho(bean.getLastUpdateWho());
            jadwalShiftKerjaDetailDao.updateAndSave(deleteJadwalDetail);
        }

        //Menambahkan detail Baru
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JadwalShiftKerjaDetail> jadwalShiftKerjaDetailList = (List<JadwalShiftKerjaDetail>) session.getAttribute("listOfResultPegawaiShift");
        //save to jadwal shift kerja detail
        for (JadwalShiftKerjaDetail jadwalShiftKerjaDetail : jadwalShiftKerjaDetailList) {
            ItJadwalShiftKerjaDetailEntity itJadwalShiftKerjaDetailEntity = new ItJadwalShiftKerjaDetailEntity();
            String jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailDao.getNextJadwalShiftKerjaDetailId();

            itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaDetailId(jadwalShiftKerjaDetailId);
            itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaId(jadwalShiftKerjaId);
            itJadwalShiftKerjaDetailEntity.setNip(jadwalShiftKerjaDetail.getNip());
            itJadwalShiftKerjaDetailEntity.setNamaPegawai(jadwalShiftKerjaDetail.getNamaPegawai());
            itJadwalShiftKerjaDetailEntity.setPositionName(jadwalShiftKerjaDetail.getPositionName());
            itJadwalShiftKerjaDetailEntity.setShiftId(jadwalShiftKerjaDetail.getShiftId());
            itJadwalShiftKerjaDetailEntity.setProfesiId(jadwalShiftKerjaDetail.getProfesiid());
            itJadwalShiftKerjaDetailEntity.setProfesiName(jadwalShiftKerjaDetail.getProfesiName());
            itJadwalShiftKerjaDetailEntity.setOnCall(jadwalShiftKerjaDetail.getOnCall());
            itJadwalShiftKerjaDetailEntity.setFlagLibur("N");
            itJadwalShiftKerjaDetailEntity.setFlagPanggil("N");

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
        logger.info("[JadwalShiftKerjaBoimpl.saveEdit] end process <<<");
    }

    @Override
    public JadwalShiftKerja saveAdd(JadwalShiftKerja bean) throws GeneralBOException {
        logger.info("[RekruitmenPabrikBoImpl.saveAdd] start process >>>");
        String jadwalShiftKerjaId = null;

        if (bean!=null){
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<JadwalShiftKerjaDetail> jadwalShiftKerjaDetailList = (List<JadwalShiftKerjaDetail>) session.getAttribute("listOfResultPegawaiShift");
            if (jadwalShiftKerjaDetailList != null) {
                List<JadwalShiftKerja> jadwalShift = getJadwalShiftKerjaByUnitAndTanggal(bean.getBranchId(), bean.getTanggal());
                if(jadwalShift.size() != 0){
                    for(JadwalShiftKerja jadwal : jadwalShift){
                        jadwalShiftKerjaId = jadwal.getJadwalShiftKerjaId();
                    }
                }else {
                    jadwalShiftKerjaId = jadwalShiftKerjaDao.getNextJadwalShiftKerjaId();
                    ItJadwalShiftKerjaEntity itJadwalShiftKerjaEntity = new ItJadwalShiftKerjaEntity();

                    itJadwalShiftKerjaEntity.setJadwalShiftKerjaId(jadwalShiftKerjaId);
                    itJadwalShiftKerjaEntity.setBranchId(bean.getBranchId());
                    itJadwalShiftKerjaEntity.setTanggal(bean.getTanggal());
                    itJadwalShiftKerjaEntity.setJadwalShiftKerjaName(bean.getJadwalShiftKerjaName());
                    itJadwalShiftKerjaEntity.setKeterangan(bean.getKeterangan());
                    String namaJadwal = CommonUtil.convertDateToDay(bean.getTanggal()) + "," + CommonUtil.convertDateToString(bean.getTanggal());
                    itJadwalShiftKerjaEntity.setJadwalShiftKerjaName(namaJadwal);
                    itJadwalShiftKerjaEntity.setFlag(bean.getFlag());
                    itJadwalShiftKerjaEntity.setAction(bean.getAction());
                    itJadwalShiftKerjaEntity.setCreatedWho(bean.getCreatedWho());
                    itJadwalShiftKerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    itJadwalShiftKerjaEntity.setCreatedDate(bean.getCreatedDate());
                    itJadwalShiftKerjaEntity.setLastUpdate(bean.getLastUpdate());
                    jadwalShiftKerjaDao.addAndSave(itJadwalShiftKerjaEntity);
                }


                //save to jadwal shift kerja detail
                for (JadwalShiftKerjaDetail jadwalShiftKerjaDetail : jadwalShiftKerjaDetailList) {
                    String availShift = "";
                    try {
                        availShift = jadwalShiftKerjaDetailDao.checkByNipAndShift(jadwalShiftKerjaDetail.getNip(), jadwalShiftKerjaId, jadwalShiftKerjaDetail.getShiftId());
                    }catch (HibernateException e){
                        logger.error("[JadwalShiftKerjaBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when check available data Jadwal Shift Kerja Detail by NIP and Jadwal Shift ID, " + e.getMessage());
                    }

                    if("N".equalsIgnoreCase(availShift)) {
                        ItJadwalShiftKerjaDetailEntity itJadwalShiftKerjaDetailEntity = new ItJadwalShiftKerjaDetailEntity();
                        String jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailDao.getNextJadwalShiftKerjaDetailId();

                        itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaDetailId(jadwalShiftKerjaDetailId);
                        itJadwalShiftKerjaDetailEntity.setJadwalShiftKerjaId(jadwalShiftKerjaId);
                        itJadwalShiftKerjaDetailEntity.setNip(jadwalShiftKerjaDetail.getNip());
                        itJadwalShiftKerjaDetailEntity.setNamaPegawai(jadwalShiftKerjaDetail.getNamaPegawai());
                        itJadwalShiftKerjaDetailEntity.setPositionName(jadwalShiftKerjaDetail.getPositionName());
                        itJadwalShiftKerjaDetailEntity.setProfesiName(jadwalShiftKerjaDetail.getProfesiName());
                        itJadwalShiftKerjaDetailEntity.setProfesiId(jadwalShiftKerjaDetail.getProfesiid());
                        itJadwalShiftKerjaDetailEntity.setShiftId(jadwalShiftKerjaDetail.getShiftId());
                        itJadwalShiftKerjaDetailEntity.setOnCall(jadwalShiftKerjaDetail.getOnCall());
                        itJadwalShiftKerjaDetailEntity.setFlagPanggil("N");
                        itJadwalShiftKerjaDetailEntity.setFlagLibur("N");

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
            JadwalShiftKerja search = new JadwalShiftKerja();
            search.setNip(searchBean.getNip());
            search.setBranchId(searchBean.getBranchId());
            search.setGroupId(searchBean.getGroupId());

            if (!"".equalsIgnoreCase(searchBean.getStTanggalAwal())){
                Date tanggalAwal = CommonUtil.convertStringToDate(searchBean.getStTanggalAwal());
                search.setStTanggalAwal(CommonUtil.convertDateToString2(tanggalAwal));
            }

            if (!"".equalsIgnoreCase(searchBean.getStTanggalAkhir())){
                Date tanggalAkhir = CommonUtil.convertStringToDate(searchBean.getStTanggalAkhir());
                search.setStTanggalAkhir(CommonUtil.convertDateToString2(tanggalAkhir));
            }

            try {
                listOfResult = jadwalShiftKerjaDao.getByCriteriaJadwalShift(search);
            } catch (HibernateException e) {
                logger.error("[JadwalShiftKerjaBoImpl.getSearchJadwalShiftKerjaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            String jadwalKerjaId = null;
            for (JadwalShiftKerja jadwalShiftKerja : listOfResult){

                //cek di absensi
                List<AbsensiPegawaiEntity> absensiPegawaiEntityList =  absensiPegawaiDao.getListAbsensiByTanggal(jadwalShiftKerja.getTanggal());
                if (absensiPegawaiEntityList.size()>0){
                    jadwalShiftKerja.setAdaAbsen(true);
                }

                java.util.Date tanggalSekarang = null;
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    tanggalSekarang = sdf.parse(sdf.format(new java.util.Date()));

                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    tanggalSekarang = cal.getTime();

                }catch (Exception e){
                    logger.error(e);
                }

                java.util.Date tanggalJadwal = CommonUtil.dateSqltoDateUtil(jadwalShiftKerja.getTanggal());
                if (tanggalSekarang!=null && tanggalSekarang.equals(tanggalJadwal)){
                    jadwalShiftKerja.setHariIni(true);
                }

                jadwalShiftKerja.setStTanggal(CommonUtil.convertDateToString(jadwalShiftKerja.getTanggal()));

                if (jadwalShiftKerja.getJadwalShiftKerjaId().equals(jadwalKerjaId)){
                    jadwalShiftKerja.setJadwalShiftKerjaId("");
//                    jadwalShiftKerja.setTanggal(null);
                    jadwalShiftKerja.setStTanggal("");
                    jadwalShiftKerja.setBranchId("");
                    jadwalShiftKerja.setBranchName("");
                    jadwalShiftKerja.setTamp(false);
                }else{
                    jadwalKerjaId=jadwalShiftKerja.getJadwalShiftKerjaId();
                }
                jadwalShiftKerja.setFlag("Y");
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
                    returnJadwalShiftKerjaDetail.setShiftId(jadwalShiftKerjaDetailEntity.getShiftId());
                    returnJadwalShiftKerjaDetail.setPositionName(jadwalShiftKerjaDetailEntity.getPositionName());
                    returnJadwalShiftKerjaDetail.setNamaPegawai(jadwalShiftKerjaDetailEntity.getNamaPegawai());
                    returnJadwalShiftKerjaDetail.setNip(jadwalShiftKerjaDetailEntity.getNip());
                    returnJadwalShiftKerjaDetail.setOnCall(jadwalShiftKerjaDetailEntity.getOnCall());
                    returnJadwalShiftKerjaDetail.setFlagPanggil(jadwalShiftKerjaDetailEntity.getFlagPanggil());
                    returnJadwalShiftKerjaDetail.setPanggilWho(jadwalShiftKerjaDetailEntity.getPanggilWho());
                    returnJadwalShiftKerjaDetail.setPanggilDate(jadwalShiftKerjaDetailEntity.getPanggilDate());
                    returnJadwalShiftKerjaDetail.setFlagLibur(jadwalShiftKerjaDetailEntity.getFlagLibur());
                    List<ItPersonilPositionEntity> personilPositionEntities = personilPositionDao.getPersonilPosition(jadwalShiftKerjaDetailEntity.getNip());
                    for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntities){
                        ImPosition position = positionDao.getById("positionId",personilPositionEntity.getPositionId());

                        returnJadwalShiftKerjaDetail.setKelompokName(position.getImKelompokPositionEntity().getKelompokName());
                    }

                    ImHrisShiftEntity shiftEntity = shiftDao.getById("shiftId",jadwalShiftKerjaDetailEntity.getShiftId());

                    ImPositionBagianEntity bagianEntity = positionBagianDao.getById("bagianId",jadwalShiftKerjaDetailEntity.getProfesiId());

                    returnJadwalShiftKerjaDetail.setProfesiid(jadwalShiftKerjaDetailEntity.getProfesiId());
                    returnJadwalShiftKerjaDetail.setProfesiName(bagianEntity.getBagianName());
                    returnJadwalShiftKerjaDetail.setShiftName(shiftEntity.getShiftName());
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
    @Override
    public List<JadwalShiftKerjaDetail> getPegawaiByGrup(String kelompokPositionId,String unit) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalforReport] start process >>>");
        List<JadwalShiftKerjaDetail> dataFinal = new ArrayList<>();

        try {
            dataFinal = jadwalShiftKerjaDetailDao.getPegawaiByKelompokPositionId(kelompokPositionId,unit);
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoImpl.getJadwalShiftKerja] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        logger.info("[JadwalShiftKerjaBoImpl.getJadwalforReport] end process <<<");

        return dataFinal;
    }
    @Override
    public List<JadwalPelayananDTO> getJadwalPelayanan(String idPelayanan, String kelompokId, String branchId, String nip, Date tanggal) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalPelayanan] start process >>>");
        List<JadwalPelayananDTO> dataFinal = new ArrayList<>();
        try {
            dataFinal = jadwalShiftKerjaDao.getJadwalPelayanan(idPelayanan,kelompokId,branchId,nip,tanggal);
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoImpl.getJadwalPelayanan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        logger.info("[JadwalShiftKerjaBoImpl.getJadwalPelayanan] end process <<<");

        return dataFinal;
    }
    @Override
    public List<JadwalShiftKerja> getJadwalShiftKerjaByUnitAndTanggal(String branchId, Date tanggal) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalShiftKerjaByUnitAndTanggal] start process >>>");
        List<ItJadwalShiftKerjaEntity> jadwalShiftKerjaEntityList = new ArrayList<>();
        List<JadwalShiftKerja> dataFinal = new ArrayList<>();
        try {
            jadwalShiftKerjaEntityList = jadwalShiftKerjaDao.getJadwalShiftKerjaByUnitAndTanggal(branchId,tanggal);
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoImpl.getJadwalShiftKerjaByUnitAndTanggal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        for (ItJadwalShiftKerjaEntity jadwalShiftKerjaEntity : jadwalShiftKerjaEntityList){
            JadwalShiftKerja result = new JadwalShiftKerja();
            result.setJadwalShiftKerjaId(jadwalShiftKerjaEntity.getJadwalShiftKerjaId());
            result.setBranchId(jadwalShiftKerjaEntity.getBranchId());
            result.setTanggal(jadwalShiftKerjaEntity.getTanggal());
            result.setJadwalShiftKerjaName(jadwalShiftKerjaEntity.getJadwalShiftKerjaName());
            result.setKeterangan(jadwalShiftKerjaEntity.getKeterangan());

            dataFinal.add(result);
        }
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalShiftKerjaByUnitAndTanggal] end process <<<");
        return dataFinal;
    }
    @Override
    public List<JadwalShiftKerjaDetail> getJadwalShiftKerjaByUnitAndProfesiAndTanggal(String branchId, Date tgl,Date tglTo,String profesiId) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalShiftKerjaByUnitAndTanggal] start process >>>");
        List<JadwalShiftKerjaDetail> dataFinal = new ArrayList<>();
        try {
            dataFinal = jadwalShiftKerjaDao.getJadwalShiftKerjaByUnitAndProfesiAndTanggal(branchId,tgl,profesiId);
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoImpl.getJadwalShiftKerjaByUnitAndTanggal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalShiftKerjaByUnitAndTanggal] end process <<<");
        return dataFinal;
    }

    @Override
    public CrudResponse getListLibur(String tanggalAwal, String tanggalAkhir) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        List<ImLiburEntity> imLiburEntityList = new ArrayList<>();

        if (!"".equalsIgnoreCase(tanggalAwal) && !"".equalsIgnoreCase(tanggalAkhir)) {
            try {
                imLiburEntityList = liburDao.getLiburRange(CommonUtil.convertStringToDate(tanggalAwal), CommonUtil.convertStringToDate(tanggalAkhir));
            }
            catch (HibernateException e) {
                response.setStatus("Error");
                response.setMsg(e.getMessage());
            }
        }

        if (!"".equalsIgnoreCase(tanggalAwal)) {
            try {
                imLiburEntityList = liburDao.getListLibur(CommonUtil.convertStringToDate(tanggalAwal));
            }
            catch (HibernateException e) {
                response.setStatus("Error");
                response.setMsg(e.getMessage());
            }
        }

        if(imLiburEntityList.size()>0) {
            response.setStatus("error");
            response.setMsg("Tanggal libur. Lanjutkan?");
        }
        else {
            response.setStatus("success");
            response.setMsg("Berhasil mengajukan shift");
        }

        return response;
    }

    @Override
    public List<JadwalShiftKerjaDetail> getJadwalShiftByBulanTahun(String nip, String branchId, String profesiId, String tanggalAwal, String tanggalAkhir) {
        logger.info("[JadwalShiftKerjaBoImpl.getJadwalShift] start process >>>");

        List<JadwalShiftKerjaDetail> listOfResult = new ArrayList<>();

        try {
            listOfResult = jadwalShiftKerjaDao.getJadwalShiftByBulanTahun(nip, branchId, profesiId, tanggalAwal, tanggalAkhir);
        } catch (HibernateException e) {
            logger.error("[JadwalShiftKerjaBoImpl.getJadwalShift] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        logger.info("[JadwalShiftKerjaBoImpl.getJadwalShift] end process >>>");
        return listOfResult;
    }

    public List<Notifikasi> savePanggilBerdasarkanId(JadwalShiftKerjaDetail bean){
        List<Notifikasi> notifikasiList = new ArrayList<>();
        Notifikasi notif = new Notifikasi();

        try {
            //Update Panggilan Terbaru
            ItJadwalShiftKerjaDetailEntity jadwalShiftKerjaDetailEntity = jadwalShiftKerjaDetailDao.getById("jadwalShiftKerjaDetailId",bean.getJadwalShiftKerjaDetailId());
            jadwalShiftKerjaDetailEntity.setFlagPanggil(bean.getFlagPanggil());
            jadwalShiftKerjaDetailEntity.setPanggilWho(bean.getPanggilWho());
            jadwalShiftKerjaDetailEntity.setPanggilDate(bean.getPanggilDate());
            jadwalShiftKerjaDetailDao.updateAndSave(jadwalShiftKerjaDetailEntity);

            //Menyimpan Panggilan ke history On Call
            ItHrisHistoryOnCallEntity historyOnCallEntity = new ItHrisHistoryOnCallEntity();
            historyOnCallEntity.setHistoryOnCallId(historyOnCallDao.getNextId());
            historyOnCallEntity.setJadwalShiftKerjaDetailId(bean.getJadwalShiftKerjaDetailId());
            historyOnCallEntity.setPanggilDate(bean.getPanggilDate());
            historyOnCallEntity.setNip(jadwalShiftKerjaDetailEntity.getNip());
            historyOnCallEntity.setKeteranganPanggil(bean.getKeteranganPanggil());
            historyOnCallEntity.setAction("C");
            historyOnCallEntity.setFlag("Y");
            historyOnCallEntity.setCreatedDate(bean.getPanggilDate());
            historyOnCallEntity.setCreatedWho(bean.getPanggilWho());
            historyOnCallEntity.setLastUpdate(bean.getPanggilDate());
            historyOnCallEntity.setLastUpdateWho(bean.getPanggilWho());
            historyOnCallDao.addAndSave(historyOnCallEntity);

            // Ambil Shift
            ImHrisShiftEntity shiftEntity = shiftDao.getById("shiftId", jadwalShiftKerjaDetailEntity.getShiftId());

            //KIRIM NOTIFIKASI
            notif.setNip(jadwalShiftKerjaDetailEntity.getNip());
            notif.setNoRequest(jadwalShiftKerjaDetailEntity.getJadwalShiftKerjaDetailId());
            notif.setNote("Anda dipanggil bertugas pada "+shiftEntity.getJamAwal() + " s/d " + shiftEntity.getJamAkhir() + "\nKet : " + bean.getKeteranganPanggil());
            notif.setCreatedWho("admin");
            notif.setTo("self");
            notif.setTipeNotifId("umum");
            notif.setTipeNotifName("On Call");
            notif.setFromPerson(bean.getPanggilWho());
            notif.setRead("N");
            notif.setFlag("Y");
            notif.setAction("C");
            notif.setCreatedWho(bean.getPanggilWho());
            notif.setLastUpdateWho(bean.getLastUpdateWho());
            notif.setCreatedDate(bean.getPanggilDate());
            notif.setLastUpdate(bean.getLastUpdate());
            notifikasiList.add(notif);


        }catch (Exception e){
            logger.error("[JadwalShiftKerjaBoImpl.savePanggilBerdasarkanId] Error, " + e.getMessage());
            throw new GeneralBOException("Error : " + e.getMessage());
        }


        return notifikasiList;
    }

    @Override
    public void saveLiburBerdasarkanId(JadwalShiftKerjaDetail bean){
        try {
            ItJadwalShiftKerjaDetailEntity jadwalShiftKerjaDetailEntity = jadwalShiftKerjaDetailDao.getById("jadwalShiftKerjaDetailId",bean.getJadwalShiftKerjaDetailId());
            jadwalShiftKerjaDetailEntity.setFlagLibur(bean.getFlagLibur());
            jadwalShiftKerjaDetailEntity.setLastUpdate(bean.getLastUpdate());
            jadwalShiftKerjaDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
            jadwalShiftKerjaDetailDao.updateAndSave(jadwalShiftKerjaDetailEntity);

        }catch (Exception e){
            logger.error("[JadwalShiftKerjaBoImpl.savePanggilBerdasarkanId] Error, " + e.getMessage());
            throw new GeneralBOException("Error : " + e.getMessage());
        }
    }

    @Override
    public List<HistoryOnCall> getHistoryOnCall (HistoryOnCall search) {
        List<HistoryOnCall> list = new ArrayList<>();

        List<ItHrisHistoryOnCallEntity> historyOnCallEntities = historyOnCallDao.getByJadwalIdAndNip(search.getJadwalShiftKerjaDetailId(), search.getNip());
        for (ItHrisHistoryOnCallEntity data : historyOnCallEntities) {
            HistoryOnCall newData = new HistoryOnCall();
            newData.setHistoryOnCallId(data.getHistoryOnCallId());
            newData.setJadwalShiftKerjaDetailId(data.getJadwalShiftKerjaDetailId());
            newData.setNip(data.getNip());
            newData.setPanggilDate(data.getPanggilDate());
            newData.setStPanggilDate(CommonUtil.convertTimestampToStringLengkap(data.getPanggilDate()));
            newData.setFlag(data.getFlag());
            newData.setKeteranganPanggil(data.getKeteranganPanggil());
            newData.setAction(data.getAction());
            newData.setCreatedWho(data.getCreatedWho());
            newData.setCreatedDate(data.getCreatedDate());
            newData.setLastUpdate(data.getLastUpdate());
            newData.setLastUpdateWho(data.getLastUpdateWho());

            list.add(newData);
        }
        return list;
    }

    public List<JadwalPelayananDTO> getListJadwalDokter(String branchId, String idPelayanan, String notLike) throws GeneralBOException {
        logger.info("[JadwalShiftKerjaBoImpl.getListJadwalDokter] start process >>>");
        List<JadwalPelayananDTO> list = new ArrayList<>();
        if(branchId != null && !"".equalsIgnoreCase(branchId) && idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)){
            try {
                list = jadwalShiftKerjaDao.getListJadwalDokter(branchId, idPelayanan, notLike);
            }catch (HibernateException e){
                logger.error("[JadwalShiftKerjaBoImpl.getListJadwalDokter], error mencari jadwal shift kerja dokter");
            }
        }
        logger.info("[JadwalShiftKerjaBoImpl.getListJadwalDokter] end process >>>");
        return list;
    }
}
