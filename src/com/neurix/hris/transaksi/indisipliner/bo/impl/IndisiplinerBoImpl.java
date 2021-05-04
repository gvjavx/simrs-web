package com.neurix.hris.transaksi.indisipliner.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.dao.GolonganDao;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.indisipliner.bo.IndisiplinerBo;
import com.neurix.hris.transaksi.indisipliner.dao.IndisiplinerDao;

import com.neurix.hris.transaksi.indisipliner.model.Indisipliner;
import com.neurix.hris.transaksi.indisipliner.model.ItIndisiplinerEntity;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndisiplinerBoImpl implements IndisiplinerBo {

    protected static transient Logger logger = Logger.getLogger(IndisiplinerBoImpl.class);
    private IndisiplinerDao indisiplinerDao;
    private DepartmentDao departmentDao;
    private BranchDao branchDao;
    private BiodataDao biodataDao;
    private GolonganDao golonganDao;
    private PersonilPositionDao personilPositionDao;
    private NotifikasiDao notifikasiDao;
    private StrukturJabatanDao strukturJabatanDao;
    private PositionDao positionDao;
    private PositionBagianDao positionBagianDao;

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
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

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public GolonganDao getGolonganDao() {
        return golonganDao;
    }

    public void setGolonganDao(GolonganDao golonganDao) {
        this.golonganDao = golonganDao;
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

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        IndisiplinerBoImpl.logger = logger;
    }

    public IndisiplinerDao getIndisiplinerDao() {
        return indisiplinerDao;
    }

    public void setIndisiplinerDao(IndisiplinerDao indisiplinerDao) {
        this.indisiplinerDao = indisiplinerDao;
    }

    @Override
    public void saveDelete(Indisipliner bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Indisipliner bean) throws GeneralBOException {

    }

    @Override
    public Indisipliner saveAdd(Indisipliner bean) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.saveAdd] start process >>>");

        logger.info("[IndisiplinerBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Notifikasi> saveAddIndisipliner(Indisipliner bean) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.saveAdd] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        if (bean!=null) {
            String indisiplinerId;
            Map hsCriteria = new HashMap();
            if (bean.getNip() != null && !"".equalsIgnoreCase(bean.getNip())) {
                hsCriteria.put("nip", bean.getNip());
            }
            try {
                // Generating ID, get from postgre sequence
                indisiplinerId = indisiplinerDao.getNextIndisiplinerId();
            }
            catch (HibernateException e) {
                logger.error("[IndisiplinerBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItIndisiplinerEntity itIndisiplinerEntity = new ItIndisiplinerEntity();

            itIndisiplinerEntity.setIndisiplinerId(indisiplinerId);
            itIndisiplinerEntity.setNip(bean.getNip());
            itIndisiplinerEntity.setNama(bean.getNama());

            List<Biodata> biodataList = biodataDao.getBiodataListDefault("","","",bean.getNip());
            for (Biodata biodata : biodataList){
                bean.setBagianId(biodata.getBagianId());
                bean.setBagianName(biodata.getBagianName());
            }

            itIndisiplinerEntity.setBagianId(bean.getBagianId());
            if (!bean.getBagianId().equalsIgnoreCase("")){
                ImPositionBagianEntity positionBagianEntity = positionBagianDao.getById("bagianId",itIndisiplinerEntity.getBagianId(),"Y");
                itIndisiplinerEntity.setBagianName(positionBagianEntity.getBagianName());
            }
            itIndisiplinerEntity.setBranchId(bean.getBranchId());
            itIndisiplinerEntity.setTipeIndisipliner(bean.getTipeIndisipliner());
            itIndisiplinerEntity.setKeteranganPelanggaran(bean.getKeteranganPelanggaran());
            itIndisiplinerEntity.setTanggalAwalPantau(bean.getTanggalAwalPantau());
            itIndisiplinerEntity.setTanggalAkhirPantau(bean.getTanggalAkhirPantau());
            itIndisiplinerEntity.setTanggalAwalBlokirAbsensi(bean.getTanggalAwalBlokirAbsensi());
            itIndisiplinerEntity.setTanggalAkhirBlokirAbsensi(bean.getTanggalAkhirBlokirAbsensi());
            itIndisiplinerEntity.setTanggalAkhirBlokirSetuju(bean.getTanggalAkhirBlokirSetuju());
            itIndisiplinerEntity.setDampak(bean.getDampak());
            itIndisiplinerEntity.setTanggal(bean.getTanggal());
            itIndisiplinerEntity.setClosedFlag("N");

            itIndisiplinerEntity.setFlag(bean.getFlag());
            itIndisiplinerEntity.setAction(bean.getAction());
            itIndisiplinerEntity.setCreatedWho(bean.getCreatedWho());
            itIndisiplinerEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itIndisiplinerEntity.setCreatedDate(bean.getCreatedDate());
            itIndisiplinerEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                indisiplinerDao.addAndSave(itIndisiplinerEntity);
            } catch (HibernateException e) {
                logger.error("[IndisiplinerBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            ImBiodataEntity imBiodataEntity = new ImBiodataEntity();

            try {
                imBiodataEntity =  biodataDao.getById("nip", bean.getNip(), "Y");
            } catch (HibernateException e) {
                logger.error("[IndisiplinerBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            //Send notif ke atasan
            Notifikasi notifAtasan= new Notifikasi();
            notifAtasan.setNip(bean.getNip());
            notifAtasan.setNoRequest(indisiplinerId);
            notifAtasan.setTipeNotifId("TN44");
            notifAtasan.setTipeNotifName(("Indisipliner"));
            notifAtasan.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
            notifAtasan.setCreatedWho(bean.getNip());
            notifAtasan.setTo("atasan");

            notifikasiList.add(notifAtasan);
        }

        logger.info("[IndisiplinerBoImpl.saveAdd] end process >>>");
        return notifikasiList;
    }

    @Override
    public List<Indisipliner> getByCriteria(Indisipliner searchBean) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.getByCriteria] start process >>>");
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        // Mapping with collection and put
        List<Indisipliner> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getIndisiplinerId() != null && !"".equalsIgnoreCase(searchBean.getIndisiplinerId())) {
                hsCriteria.put("indisipliner_id", searchBean.getIndisiplinerId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getNama() != null && !"".equalsIgnoreCase(searchBean.getNama())) {
                hsCriteria.put("nama", searchBean.getNama());
            }
            if (searchBean.getBagianId() != null && !"".equalsIgnoreCase(searchBean.getBagianId())) {
                hsCriteria.put("bagian_id", searchBean.getBagianId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getApprovalFlag() != null && !"".equalsIgnoreCase(searchBean.getApprovalFlag())) {
                hsCriteria.put("approval_flag", searchBean.getApprovalFlag());
            }
            if (searchBean.getTanggal() != null ) {
                hsCriteria.put("tanggal", searchBean.getTanggal());
            }
            if (searchBean.getClosedFlag() != null && !"".equalsIgnoreCase(searchBean.getClosedFlag())) {
                hsCriteria.put("closed_flag", searchBean.getClosedFlag());
            }
            if (searchBean.getTipeIndisipliner() != null && !"".equalsIgnoreCase(searchBean.getTipeIndisipliner())) {
                hsCriteria.put("tipe_indisipliner", searchBean.getTipeIndisipliner());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Date tanggalDari = CommonUtil.convertToDate(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
                Date tanggalSelesai = CommonUtil.convertToDate(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
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


            List<ItIndisiplinerEntity> itIndisiplinerEntity = null;
            try {

                itIndisiplinerEntity = indisiplinerDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[IndisiplinerBoImpl.getSearchIndisiplinerByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itIndisiplinerEntity != null){
                Indisipliner returnIndisipliner;
                // Looping from dao to object and save in collection
                for(ItIndisiplinerEntity indisiplinerEntity : itIndisiplinerEntity){
                    returnIndisipliner = new Indisipliner();
                    returnIndisipliner.setIndisiplinerId(indisiplinerEntity.getIndisiplinerId());
                    returnIndisipliner.setNip(indisiplinerEntity.getNip());
                    returnIndisipliner.setNama(indisiplinerEntity.getNama());
                    returnIndisipliner.setBagianId(indisiplinerEntity.getBagianId());
                    returnIndisipliner.setBagianName(indisiplinerEntity.getBagianName());
                    returnIndisipliner.setBranchId(indisiplinerEntity.getBranchId());
                    returnIndisipliner.setTipeIndisipliner(indisiplinerEntity.getTipeIndisipliner());
                    returnIndisipliner.setKeteranganPelanggaran(indisiplinerEntity.getKeteranganPelanggaran());
                    returnIndisipliner.setTanggal(indisiplinerEntity.getTanggal());
                    returnIndisipliner.setTanggalAwalPantau(indisiplinerEntity.getTanggalAwalPantau());
                    returnIndisipliner.setTanggalAkhirPantau(indisiplinerEntity.getTanggalAkhirPantau());
                    returnIndisipliner.setTanggalAwalBlokirAbsensi(indisiplinerEntity.getTanggalAwalBlokirAbsensi());
                    returnIndisipliner.setTanggalAkhirBlokirAbsensi(indisiplinerEntity.getTanggalAkhirBlokirAbsensi());
                    returnIndisipliner.setTanggalAkhirBlokirSetuju(indisiplinerEntity.getTanggalAkhirBlokirSetuju());
                    returnIndisipliner.setDampak(indisiplinerEntity.getDampak());
                    returnIndisipliner.setFlag(indisiplinerEntity.getFlag());
                    returnIndisipliner.setStTanggal(dt1.format(indisiplinerEntity.getTanggal()));

                    if (indisiplinerEntity.getTanggalAwalPantau()!=null){
                        returnIndisipliner.setStTanggalAwalPantau(dt1.format(indisiplinerEntity.getTanggalAwalPantau()));
                    }
                    if (indisiplinerEntity.getTanggalAkhirPantau()!=null){
                        returnIndisipliner.setStTanggalAkhirPantau(dt1.format(indisiplinerEntity.getTanggalAkhirPantau()));
                    }
                    if (indisiplinerEntity.getTanggalAwalBlokirAbsensi()!=null){
                        returnIndisipliner.setStTanggalAwalBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAwalBlokirAbsensi()));
                    }
                    if (indisiplinerEntity.getTanggalAkhirBlokirAbsensi()!=null){
                        returnIndisipliner.setStTanggalAkhirBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAkhirBlokirAbsensi()));
                    }
                    List<ImBranches> imBranchesList = branchDao.getListBranchById(indisiplinerEntity.getBranchId());
                    for(ImBranches imBranches:imBranchesList){
                        returnIndisipliner.setBranchName(imBranches.getBranchName());
                    }
                    List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListPersonilPosition(indisiplinerEntity.getNip());
                    for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntityList){
                        returnIndisipliner.setPositionId(personilPositionEntity.getPositionId());
                        ImPosition position = positionDao.getById("positionId",personilPositionEntity.getPositionId(),"Y");
                        returnIndisipliner.setPositionName(position.getPositionName());

                    }
                    ImBiodataEntity biodataEntity = biodataDao.getById("nip",indisiplinerEntity.getNip(),"Y");
                    ImGolonganEntity imGolonganEntity = golonganDao.getById("golonganId",biodataEntity.getGolongan());

                    if (imGolonganEntity!=null){
                        returnIndisipliner.setGolongan(imGolonganEntity.getGolonganName());
                        returnIndisipliner.setGolonganId(imGolonganEntity.getGolonganId());
                    }
                    if (indisiplinerEntity.getApprovalFlag()!=null){
                        if(indisiplinerEntity.getApprovalFlag().equals("Y")){
                            returnIndisipliner.setIndisiplinerApprove(true);
                        }else if(indisiplinerEntity.getApprovalFlag().equals("N")){
                            returnIndisipliner.setNotApprove(true);
                        }
                    }
                    if (indisiplinerEntity.getClosedFlag()!=null){
                        if(indisiplinerEntity.getClosedFlag().equals("Y")) {
                            returnIndisipliner.setClosed(true);
                        }
                    }

                    listOfResult.add(returnIndisipliner);
                }
            }
        }
        logger.info("[IndisiplinerBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<Indisipliner> ListOfIndisipliner(Indisipliner searchBean) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.getByCriteria] start process >>>");
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        // Mapping with collection and put
        List<Indisipliner> listOfResult = new ArrayList();

        if (searchBean != null) {
            List<ItIndisiplinerEntity> itIndisiplinerEntity = null;
            try {

                itIndisiplinerEntity = indisiplinerDao.getListIndisiplinerFromPantau(searchBean.getNip(),searchBean.getTanggal());
            } catch (HibernateException e) {
                logger.error("[IndisiplinerBoImpl.getSearchIndisiplinerByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itIndisiplinerEntity != null){
                Indisipliner returnIndisipliner;
                // Looping from dao to object and save in collection
                for(ItIndisiplinerEntity indisiplinerEntity : itIndisiplinerEntity){
                    returnIndisipliner = new Indisipliner();
                    returnIndisipliner.setIndisiplinerId(indisiplinerEntity.getIndisiplinerId());
                    returnIndisipliner.setNip(indisiplinerEntity.getNip());
                    returnIndisipliner.setNama(indisiplinerEntity.getNama());
                    returnIndisipliner.setBagianId(indisiplinerEntity.getBagianId());
                    returnIndisipliner.setBagianName(indisiplinerEntity.getBagianName());
                    returnIndisipliner.setBranchId(indisiplinerEntity.getBranchId());
                    returnIndisipliner.setTipeIndisipliner(indisiplinerEntity.getTipeIndisipliner());
                    returnIndisipliner.setKeteranganPelanggaran(indisiplinerEntity.getKeteranganPelanggaran());
                    returnIndisipliner.setTanggalAwalPantau(indisiplinerEntity.getTanggalAwalPantau());
                    returnIndisipliner.setTanggalAkhirPantau(indisiplinerEntity.getTanggalAkhirPantau());
                    returnIndisipliner.setTanggalAwalBlokirAbsensi(indisiplinerEntity.getTanggalAwalBlokirAbsensi());
                    returnIndisipliner.setTanggalAkhirBlokirAbsensi(indisiplinerEntity.getTanggalAkhirBlokirAbsensi());
                    returnIndisipliner.setTanggalAkhirBlokirSetuju(indisiplinerEntity.getTanggalAkhirBlokirSetuju());
                    returnIndisipliner.setDampak(indisiplinerEntity.getDampak());
                    returnIndisipliner.setFlag(indisiplinerEntity.getFlag());
                    returnIndisipliner.setStTanggalAwalPantau(dt1.format(indisiplinerEntity.getTanggalAwalPantau()));
                    returnIndisipliner.setStTanggalAkhirPantau(dt1.format(indisiplinerEntity.getTanggalAkhirPantau()));
                    returnIndisipliner.setStTanggalAwalBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAwalBlokirAbsensi()));
                    returnIndisipliner.setStTanggalAkhirBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAkhirBlokirAbsensi()));

                    List<ImBranches> imBranchesList = branchDao.getListBranchById(indisiplinerEntity.getBranchId());
                    for(ImBranches imBranches:imBranchesList){
                        returnIndisipliner.setBranchName(imBranches.getBranchName());
                    }
                    List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getListPersonilPosition(indisiplinerEntity.getNip());
                    for (ItPersonilPositionEntity personilPositionEntity : personilPositionEntityList){
                        returnIndisipliner.setPositionId(personilPositionEntity.getPositionId());
                        ImPosition position = positionDao.getById("positionId",personilPositionEntity.getPositionId(),"Y");
                        returnIndisipliner.setPositionName(position.getPositionName());

                    }
                    ImBiodataEntity biodataEntity = biodataDao.getById("nip",indisiplinerEntity.getNip(),"Y");
                    ImGolonganEntity imGolonganEntity = golonganDao.getById("golonganId",biodataEntity.getGolongan());
                    returnIndisipliner.setGolongan(imGolonganEntity.getGolonganName());
                    returnIndisipliner.setGolonganId(imGolonganEntity.getGolonganId());
                    if (indisiplinerEntity.getApprovalFlag()!=null){
                        if(indisiplinerEntity.getApprovalFlag().equals("Y")){
                            returnIndisipliner.setIndisiplinerApprove(true);
                        }else if(indisiplinerEntity.getApprovalFlag().equals("N")){
                            returnIndisipliner.setNotApprove(true);
                        }
                    }
                    if (indisiplinerEntity.getClosedFlag()!=null){
                        if(indisiplinerEntity.getClosedFlag().equals("Y")) {
                            returnIndisipliner.setClosed(true);
                        }
                    }

                    listOfResult.add(returnIndisipliner);
                }
            }
        }
        logger.info("[IndisiplinerBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<Indisipliner> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public List<Indisipliner> getListIndisiplinerByPersonAndTanggal(String nip , Date tanggal) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.getListIndisiplinerByPersonAndTanggal] start process >>>");
        List<ItIndisiplinerEntity> itIndisiplinerEntity = null;
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            itIndisiplinerEntity = indisiplinerDao.getListIndisiplinerByPersonAndTanggal(nip,tanggal);
        } catch (HibernateException e) {
            logger.error("[IndisiplinerBoImpl.getSearchIndisiplinerByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<Indisipliner> listOfResult = new ArrayList<>();

        if(itIndisiplinerEntity != null){
            Indisipliner returnIndisipliner;
            // Looping from dao to object and save in collection
            for(ItIndisiplinerEntity indisiplinerEntity : itIndisiplinerEntity){
                returnIndisipliner = new Indisipliner();
                returnIndisipliner.setIndisiplinerId(indisiplinerEntity.getIndisiplinerId());
                returnIndisipliner.setNip(indisiplinerEntity.getNip());
                returnIndisipliner.setNama(indisiplinerEntity.getNama());
                returnIndisipliner.setBagianId(indisiplinerEntity.getBagianId());
                returnIndisipliner.setBagianName(indisiplinerEntity.getBagianName());
                returnIndisipliner.setBranchId(indisiplinerEntity.getBranchId());
                returnIndisipliner.setTipeIndisipliner(indisiplinerEntity.getTipeIndisipliner());
                returnIndisipliner.setKeteranganPelanggaran(indisiplinerEntity.getKeteranganPelanggaran());
                returnIndisipliner.setTanggalAwalPantau(indisiplinerEntity.getTanggalAwalPantau());
                returnIndisipliner.setTanggalAkhirPantau(indisiplinerEntity.getTanggalAkhirPantau());
                returnIndisipliner.setTanggalAwalBlokirAbsensi(indisiplinerEntity.getTanggalAwalBlokirAbsensi());
                returnIndisipliner.setTanggalAkhirBlokirAbsensi(indisiplinerEntity.getTanggalAkhirBlokirAbsensi());
                returnIndisipliner.setTanggalAkhirBlokirSetuju(indisiplinerEntity.getTanggalAkhirBlokirSetuju());
                returnIndisipliner.setDampak(indisiplinerEntity.getDampak());
                returnIndisipliner.setFlag(indisiplinerEntity.getFlag());
                returnIndisipliner.setStTanggalAwalPantau(dt1.format(indisiplinerEntity.getTanggalAwalPantau()));
                returnIndisipliner.setStTanggalAkhirPantau(dt1.format(indisiplinerEntity.getTanggalAkhirPantau()));
                returnIndisipliner.setStTanggalAwalBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAwalBlokirAbsensi()));
                returnIndisipliner.setStTanggalAkhirBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAkhirBlokirAbsensi()));

                listOfResult.add(returnIndisipliner);
            }
        }

        return listOfResult;
    }
    @Override
    public List<Indisipliner> getListIndisipliner(String nip) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.getListIndisiplinerByPersonAndTanggal] start process >>>");
        List<ItIndisiplinerEntity> itIndisiplinerEntity = null;
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            itIndisiplinerEntity = indisiplinerDao.getListIndisiplinerFromNip(nip);
        } catch (HibernateException e) {
            logger.error("[IndisiplinerBoImpl.getSearchIndisiplinerByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<Indisipliner> listOfResult = new ArrayList<>();

        if(itIndisiplinerEntity != null){
            Indisipliner returnIndisipliner;
            // Looping from dao to object and save in collection
            for(ItIndisiplinerEntity indisiplinerEntity : itIndisiplinerEntity){
                returnIndisipliner = new Indisipliner();
                returnIndisipliner.setIndisiplinerId(indisiplinerEntity.getIndisiplinerId());
                returnIndisipliner.setNip(indisiplinerEntity.getNip());
                returnIndisipliner.setNama(indisiplinerEntity.getNama());
                returnIndisipliner.setBagianId(indisiplinerEntity.getBagianId());
                returnIndisipliner.setBagianName(indisiplinerEntity.getBagianName());
                returnIndisipliner.setBranchId(indisiplinerEntity.getBranchId());
                returnIndisipliner.setTipeIndisipliner(indisiplinerEntity.getTipeIndisipliner());
                returnIndisipliner.setKeteranganPelanggaran(indisiplinerEntity.getKeteranganPelanggaran());
                returnIndisipliner.setTanggalAwalPantau(indisiplinerEntity.getTanggalAwalPantau());
                returnIndisipliner.setTanggalAkhirPantau(indisiplinerEntity.getTanggalAkhirPantau());
                returnIndisipliner.setTanggalAwalBlokirAbsensi(indisiplinerEntity.getTanggalAwalBlokirAbsensi());
                returnIndisipliner.setTanggalAkhirBlokirAbsensi(indisiplinerEntity.getTanggalAkhirBlokirAbsensi());
                returnIndisipliner.setTanggalAkhirBlokirSetuju(indisiplinerEntity.getTanggalAkhirBlokirSetuju());
                returnIndisipliner.setDampak(indisiplinerEntity.getDampak());
                returnIndisipliner.setFlag(indisiplinerEntity.getFlag());
                returnIndisipliner.setStTanggalAwalPantau(dt1.format(indisiplinerEntity.getTanggalAwalPantau()));
                returnIndisipliner.setStTanggalAkhirPantau(dt1.format(indisiplinerEntity.getTanggalAkhirPantau()));
                returnIndisipliner.setStTanggalAwalBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAwalBlokirAbsensi()));
                returnIndisipliner.setStTanggalAkhirBlokirAbsensi(dt1.format(indisiplinerEntity.getTanggalAkhirBlokirAbsensi()));
                returnIndisipliner.setStTanggal(dt1.format(indisiplinerEntity.getTanggal()));

                if (indisiplinerEntity.getTipeIndisipliner().equalsIgnoreCase("SP0")){
                    returnIndisipliner.setTipeIndisipliner("Teguran");
                }
                listOfResult.add(returnIndisipliner);
            }
        }

        return listOfResult;
    }
    @Override
    public List<Notifikasi> saveApprove(Indisipliner bean) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.saveEdit] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        if (bean!=null) {
            String IndisiplinerId = bean.getIndisiplinerId();
            ItIndisiplinerEntity itIndisiplinerEntity = null;
            try {
                // Get data from database by ID
                itIndisiplinerEntity = indisiplinerDao.getById("indisiplinerId", IndisiplinerId,"Y");
            } catch (HibernateException e) {
                logger.error("[IndisiplinerBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Indisipliner by Kode Indisipliner, please inform to your admin...," + e.getMessage());
            }

            if (itIndisiplinerEntity != null) {
                String userLogin = CommonUtil.userIdLogin();
                itIndisiplinerEntity.setIndisiplinerId(bean.getIndisiplinerId());
                itIndisiplinerEntity.setFlag(bean.getFlag());
                //Approve
                if (bean.getTmpApprove().equals("atasan")) {
                    if (bean.getApprovalFlag().equals("Y")) {
                        itIndisiplinerEntity.setApprovalFlag("Y");
                    } else {
                        itIndisiplinerEntity.setNotApprovalNote(bean.getNotApprovalNote());
                        itIndisiplinerEntity.setApprovalFlag("N");
                    }
                    itIndisiplinerEntity.setApprovalPersonId(userLogin);
                    itIndisiplinerEntity.setApprovalDate(bean.getLastUpdate());

                    itIndisiplinerEntity.setTanggalAkhirBlokirSetuju(bean.getTanggalAkhirBlokirSetuju());
                    ImBiodataEntity biodataEntity = biodataDao.getById("nip", userLogin, "Y");
                    if (biodataEntity != null) {
                        itIndisiplinerEntity.setApprovalPersonName(biodataEntity.getNamaPegawai());
                    }
                }
                itIndisiplinerEntity.setAction(bean.getAction());
                itIndisiplinerEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itIndisiplinerEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    indisiplinerDao.updateAndSave(itIndisiplinerEntity);
                } catch (HibernateException e) {
                    logger.error("[IndisiplinerBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Indisipliner, please info to your admin..." + e.getMessage());
                }

                // SEND NOTIF
                ImBiodataEntity imBiodataEntity =  biodataDao.getById("nip", itIndisiplinerEntity.getNip(), "Y");
                Integer kelompok = personilPositionDao.getKelompokPosition(itIndisiplinerEntity.getNip());

                if (bean.getApprovalFlag().equals("Y")) {
                    //Send notif ke orang yang mengajukan
                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itIndisiplinerEntity.getNip());
                    notifSelf.setNoRequest(bean.getIndisiplinerId());
                    notifSelf.setTipeNotifId("umum");
                    notifSelf.setTipeNotifName(("Indisipliner"));
                    notifSelf.setNote("Anda mendapat indisipliner dikarenakan "+itIndisiplinerEntity.getKeteranganPelanggaran()+" pada tanggal "+CommonUtil.convertDateToString(itIndisiplinerEntity.getTanggal()));
                    notifSelf.setCreatedWho(itIndisiplinerEntity.getNip());
                    notifSelf.setTo("self");

                    notifikasiList.add(notifSelf);
                }else{
                    String msg="";
                    if (!("").equalsIgnoreCase(itIndisiplinerEntity.getNotApprovalNote())){
                        msg="dikarenakan "+itIndisiplinerEntity.getNotApprovalNote();
                    }
                    //Send notif ke orang yang mengajukan
                    Notifikasi notifSelf= new Notifikasi();
                    notifSelf.setNip(itIndisiplinerEntity.getNip());
                    notifSelf.setNoRequest(bean.getIndisiplinerId());
                    notifSelf.setTipeNotifId("umum");
                    notifSelf.setTipeNotifName(("Indisipliner"));
                    notifSelf.setNote("Indisipliner anda : "+itIndisiplinerEntity.getKeteranganPelanggaran()+" pada tanggal "+CommonUtil.convertDateToString(itIndisiplinerEntity.getTanggal())+" tidak di approve oleh atasan "+msg);
                    notifSelf.setCreatedWho(itIndisiplinerEntity.getNip());
                    notifSelf.setTo("self");
                    notifikasiList.add(notifSelf);
                }
            }
        }


        logger.info("[IndisiplinerBoImpl.saveEdit] end process <<<");
        return notifikasiList;
    }

    @Override
    public void saveClosed(Indisipliner bean) throws GeneralBOException {
        logger.info("[IndisiplinerBoImpl.saveClosed] start process >>>");
        Map hsCriteria = new HashMap();
        List<ImStrukturJabatanEntity> atasan;
        if (bean!=null) {
            String IndisiplinerId = bean.getIndisiplinerId();
            ItIndisiplinerEntity itIndisiplinerEntity = null;
            try {
                // Get data from database by ID
                itIndisiplinerEntity = indisiplinerDao.getById("indisiplinerId", IndisiplinerId,"Y");
            } catch (HibernateException e) {
                logger.error("[IndisiplinerBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Indisipliner by Kode Indisipliner, please inform to your admin...," + e.getMessage());
            }

            if (itIndisiplinerEntity != null) {
                String userLogin = CommonUtil.userIdLogin();
                itIndisiplinerEntity.setIndisiplinerId(bean.getIndisiplinerId());
                itIndisiplinerEntity.setFlag(bean.getFlag());
                //Approve
                    if (bean.getClosedFlag().equals("Y")) {
                        itIndisiplinerEntity.setClosedFlag("Y");
                    }
                    itIndisiplinerEntity.setClosedDate(bean.getLastUpdate());
                itIndisiplinerEntity.setAction(bean.getAction());
                itIndisiplinerEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itIndisiplinerEntity.setLastUpdate(bean.getLastUpdate());
                itIndisiplinerEntity.setClosedNote(bean.getClosedNote());

                try {
                    // Update into database
                    indisiplinerDao.updateAndSave(itIndisiplinerEntity);
                } catch (HibernateException e) {
                    logger.error("[IndisiplinerBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Indisipliner, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[IndisiplinerBoImpl.saveEdit] Error, not found data Indisipliner with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Indisipliner with request id, please check again your data ...");
            }
        }
        logger.info("[IndisiplinerBoImpl.saveEdit] end process <<<");
    }

    @Override
    public List<Indisipliner> getIndisiplinerUser(Indisipliner searchBean){
        List<Indisipliner> listOfResultIndisipliner = new ArrayList<>();
        List<ItIndisiplinerEntity> listOfIndisipliner= new ArrayList();
        Map hsCriteria = new HashMap();
        if (searchBean!=null){
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Date tanggalDari = CommonUtil.convertToDate(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
                Date tanggalSelesai = CommonUtil.convertToDate(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            hsCriteria.put("flag", "Y");
            hsCriteria.put("approval_flag", "Y");
        }

        try {
            listOfIndisipliner = indisiplinerDao.getByCriteria2(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[IndisiplinerBoImpl.getIndisiplinerUser] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list , please info to your admin..." + e.getMessage());
        }
        for (ItIndisiplinerEntity pegawai : listOfIndisipliner){
            Indisipliner result = new Indisipliner();
            result.setNip(pegawai.getNip());
            result.setNama(pegawai.getNama());
            result.setTanggal(pegawai.getTanggal());
            result.setStTanggal(CommonUtil.convertDateToString(pegawai.getTanggal()));
            result.setDampak(pegawai.getDampak());
            result.setTipeIndisipliner(pegawai.getTipeIndisipliner());
            result.setIndisiplinerId(pegawai.getIndisiplinerId());
            result.setKeteranganPelanggaran(pegawai.getKeteranganPelanggaran());
            result.setFlag(pegawai.getFlag());

            if (result.getTipeIndisipliner().equalsIgnoreCase("SP0")){
                result.setTipeIndisipliner("Teguran");
            }
            listOfResultIndisipliner.add(result);
        }
        return listOfResultIndisipliner;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
