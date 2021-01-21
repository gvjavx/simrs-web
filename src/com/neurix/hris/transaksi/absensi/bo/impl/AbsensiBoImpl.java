package com.neurix.hris.transaksi.absensi.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.dao.CompanyDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImCompany;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.Department;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.jamkerja.dao.JamKerjaDao;
import com.neurix.hris.master.jamkerja.model.ImHrisJamKerja;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.master.jenisPegawai.dao.JenisPegawaiDao;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiEntity;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;
import com.neurix.hris.master.libur.dao.LiburDao;
import com.neurix.hris.master.libur.model.ImLiburEntity;
import com.neurix.hris.master.mappingpersengaji.dao.MappingPersenGajiDao;
import com.neurix.hris.master.mappingpersengaji.model.ImHrisMappingPersenGaji;
import com.neurix.hris.master.mappingpersengaji.model.MappingPersenGaji;
import com.neurix.hris.master.payrollSkalaGaji.dao.PayrollSkalaGajiDao;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGajiPkwt.dao.PayrollSkalaGajiPkwtDao;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.ImPayrollSkalaGajiPkwtEntity;
import com.neurix.hris.master.payrollTunjanganUmk.dao.PayrollTunjanganUmkDao;
import com.neurix.hris.master.payrollTunjanganUmk.model.ImPayrollTunjanganUmkEntity;
import com.neurix.hris.master.shift.dao.ShiftDao;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import com.neurix.hris.master.strukturJabatan.bo.impl.StrukturJabatanBoImpl;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.dao.*;
import com.neurix.hris.transaksi.absensi.model.*;
import com.neurix.hris.transaksi.cutiPegawai.dao.CutiPegawaiDao;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
import com.neurix.hris.transaksi.ijinKeluar.dao.IjinKeluarDao;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarEntity;
import com.neurix.hris.transaksi.indisipliner.dao.IndisiplinerDao;
import com.neurix.hris.transaksi.indisipliner.model.ItIndisiplinerEntity;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.lembur.dao.JamLemburDao;
import com.neurix.hris.transaksi.lembur.dao.LemburDao;
import com.neurix.hris.transaksi.lembur.dao.PengaliFaktorLemburDao;
import com.neurix.hris.transaksi.lembur.model.*;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.payroll.dao.PayrollDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollEntity;
import com.neurix.hris.transaksi.payroll.model.Payroll;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.sppd.dao.SppdDao;
import com.neurix.hris.transaksi.sppd.model.ItSppdPersonEntity;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import com.neurix.hris.transaksi.training.dao.TrainingDao;
import com.neurix.hris.transaksi.training.dao.TrainingPersonDao;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingPersonEntity;
import com.sun.org.apache.xpath.internal.CachedXPathAPI;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.jasper.tagplugins.jstl.core.Catch;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class AbsensiBoImpl implements AbsensiBo {

    private AbsensiPegawaiDao absensiPegawaiDao;
    private MesinAbsensiDao mesinAbsensiDao;
    private BiodataDao biodataDao;
    private JamKerjaDao jamKerjaDao;
    private MesinDao mesinDao;
    private PersonilPositionDao personilPositionDao;
    private LemburDao lemburDao;
    private IjinKeluarDao ijinKeluarDao;
    private PositionDao positionDao;
    private BranchDao branchDao;
    private MesinAbsensiDetailDao mesinAbsensiDetailDao;
    private AbsensiDashboardDao absensiDashboardDao;
    private PengaliFaktorLemburDao pengaliFaktorLemburDao;
    private JamLemburDao jamLemburDao;
    private PayrollDao payrollDao;
    private PayrollSkalaGajiDao payrollSkalaGajiDao;
    private PayrollSkalaGajiPkwtDao payrollSkalaGajiPkwtDao;
    private DepartmentDao departmentDao;
    private CutiPegawaiDao cutiPegawaiDao;
    private IndisiplinerDao indisiplinerDao;
    private SppdDao sppdDao;
    private TrainingDao trainingDao;
    private TrainingPersonDao trainingPersonDao;
    private PayrollTunjanganUmkDao payrollTunjanganUmkDao;
    private StrukturJabatanDao strukturJabatanDao;
    private NotifikasiDao notifikasiDao;
    private LiburDao liburDao;
    private PegawaiTambahanDao pegawaiTambahanDao;
    private PegawaiTambahanAbsensiDao pegawaiTambahanAbsensiDao;
    private ShiftDao shiftDao;
    private CompanyDao companyDao;
    private MesinAbsensiDetailOnCallDao mesinAbsensiDetailOnCallDao;
    private AbsensiOnCallDao absensiOnCallDao;
    private MappingPersenGajiDao mappingPersenGajiDao;

    private JenisPegawaiDao jenisPegawaiDao;

    public void setJenisPegawaiDao(JenisPegawaiDao jenisPegawaiDao) {
        this.jenisPegawaiDao = jenisPegawaiDao;
    }

    public JenisPegawaiDao getJenisPegawaiDao() {
        return jenisPegawaiDao;
    }

    public void setMappingPersenGajiDao(MappingPersenGajiDao mappingPersenGajiDao) {
        this.mappingPersenGajiDao = mappingPersenGajiDao;
    }

    public AbsensiOnCallDao getAbsensiOnCallDao() {
        return absensiOnCallDao;
    }

    public void setAbsensiOnCallDao(AbsensiOnCallDao absensiOnCallDao) {
        this.absensiOnCallDao = absensiOnCallDao;
    }

    public MesinAbsensiDetailOnCallDao getMesinAbsensiDetailOnCallDao() {
        return mesinAbsensiDetailOnCallDao;
    }

    public void setMesinAbsensiDetailOnCallDao(MesinAbsensiDetailOnCallDao mesinAbsensiDetailOnCallDao) {
        this.mesinAbsensiDetailOnCallDao = mesinAbsensiDetailOnCallDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public PayrollSkalaGajiPkwtDao getPayrollSkalaGajiPkwtDao() {
        return payrollSkalaGajiPkwtDao;
    }

    public void setPayrollSkalaGajiPkwtDao(PayrollSkalaGajiPkwtDao payrollSkalaGajiPkwtDao) {
        this.payrollSkalaGajiPkwtDao = payrollSkalaGajiPkwtDao;
    }

    public AbsensiDashboardDao getAbsensiDashboardDao() {
        return absensiDashboardDao;
    }

    public void setAbsensiDashboardDao(AbsensiDashboardDao absensiDashboardDao) {
        this.absensiDashboardDao = absensiDashboardDao;
    }

    public ShiftDao getShiftDao() {
        return shiftDao;
    }

    public void setShiftDao(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    public PegawaiTambahanAbsensiDao getPegawaiTambahanAbsensiDao() {
        return pegawaiTambahanAbsensiDao;
    }

    public void setPegawaiTambahanAbsensiDao(PegawaiTambahanAbsensiDao pegawaiTambahanAbsensiDao) {
        this.pegawaiTambahanAbsensiDao = pegawaiTambahanAbsensiDao;
    }

    public PegawaiTambahanDao getPegawaiTambahanDao() {
        return pegawaiTambahanDao;
    }

    public void setPegawaiTambahanDao(PegawaiTambahanDao pegawaiTambahanDao) {
        this.pegawaiTambahanDao = pegawaiTambahanDao;
    }

    private List<StrukturJabatan> strukturJabatanList = new ArrayList();

    public List<StrukturJabatan> getStrukturJabatanList() {
        return strukturJabatanList;
    }

    public void setStrukturJabatanList(List<StrukturJabatan> strukturJabatanList) {
        this.strukturJabatanList = strukturJabatanList;
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

    public PayrollTunjanganUmkDao getPayrollTunjanganUmkDao() {
        return payrollTunjanganUmkDao;
    }

    public void setPayrollTunjanganUmkDao(PayrollTunjanganUmkDao payrollTunjanganUmkDao) {
        this.payrollTunjanganUmkDao = payrollTunjanganUmkDao;
    }

    protected static transient Logger logger = Logger.getLogger(AbsensiBoImpl.class);

    public TrainingPersonDao getTrainingPersonDao() {
        return trainingPersonDao;
    }

    public void setTrainingPersonDao(TrainingPersonDao trainingPersonDao) {
        this.trainingPersonDao = trainingPersonDao;
    }

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public SppdDao getSppdDao() {
        return sppdDao;
    }

    public void setSppdDao(SppdDao sppdDao) {
        this.sppdDao = sppdDao;
    }

    public IndisiplinerDao getIndisiplinerDao() {
        return indisiplinerDao;
    }

    public void setIndisiplinerDao(IndisiplinerDao indisiplinerDao) {
        this.indisiplinerDao = indisiplinerDao;
    }

    public CutiPegawaiDao getCutiPegawaiDao() {
        return cutiPegawaiDao;
    }

    public void setCutiPegawaiDao(CutiPegawaiDao cutiPegawaiDao) {
        this.cutiPegawaiDao = cutiPegawaiDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PayrollSkalaGajiDao getPayrollSkalaGajiDao() {
        return payrollSkalaGajiDao;
    }

    public void setPayrollSkalaGajiDao(PayrollSkalaGajiDao payrollSkalaGajiDao) {
        this.payrollSkalaGajiDao = payrollSkalaGajiDao;
    }

    public LiburDao getLiburDao() {
        return liburDao;
    }

    public void setLiburDao(LiburDao liburDao) {
        this.liburDao = liburDao;
    }

    public PayrollDao getPayrollDao() {
        return payrollDao;
    }

    public void setPayrollDao(PayrollDao payrollDao) {
        this.payrollDao = payrollDao;
    }

    public JamLemburDao getJamLemburDao() {
        return jamLemburDao;
    }

    public void setJamLemburDao(JamLemburDao jamLemburDao) {
        this.jamLemburDao = jamLemburDao;
    }

    public PengaliFaktorLemburDao getPengaliFaktorLemburDao() {
        return pengaliFaktorLemburDao;
    }

    public void setPengaliFaktorLemburDao(PengaliFaktorLemburDao pengaliFaktorLemburDao) {
        this.pengaliFaktorLemburDao = pengaliFaktorLemburDao;
    }

    public MesinAbsensiDetailDao getMesinAbsensiDetailDao() {
        return mesinAbsensiDetailDao;
    }

    public void setMesinAbsensiDetailDao(MesinAbsensiDetailDao mesinAbsensiDetailDao) {
        this.mesinAbsensiDetailDao = mesinAbsensiDetailDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public IjinKeluarDao getIjinKeluarDao() {
        return ijinKeluarDao;
    }

    public void setIjinKeluarDao(IjinKeluarDao ijinKeluarDao) {
        this.ijinKeluarDao = ijinKeluarDao;
    }

    public LemburDao getLemburDao() {
        return lemburDao;
    }

    public void setLemburDao(LemburDao lemburDao) {
        this.lemburDao = lemburDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public MesinDao getMesinDao() {
        return mesinDao;
    }

    public void setMesinDao(MesinDao mesinDao) {
        this.mesinDao = mesinDao;
    }

    public JamKerjaDao getJamKerjaDao() {
        return jamKerjaDao;
    }

    public void setJamKerjaDao(JamKerjaDao jamKerjaDao) {
        this.jamKerjaDao = jamKerjaDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public MesinAbsensiDao getMesinAbsensiDao() {
        return mesinAbsensiDao;
    }

    public void setMesinAbsensiDao(MesinAbsensiDao mesinAbsensiDao) {
        this.mesinAbsensiDao = mesinAbsensiDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AbsensiBoImpl.logger = logger;
    }

    public AbsensiPegawaiDao getAbsensiPegawaiDao() {
        return absensiPegawaiDao;
    }

    public void setAbsensiPegawaiDao(AbsensiPegawaiDao absensiPegawaiDao) {
        this.absensiPegawaiDao = absensiPegawaiDao;
    }

    @Override
    public void saveDelete(AbsensiPegawai bean) throws GeneralBOException {
        logger.info("[absensiBoimpl.saveDelete] start process >>>");

        if (bean!=null) {
            String absensiPegawaiId = bean.getAbsensiPegawaiId();
            AbsensiPegawaiEntity absensiPegawaiEntity = null;
            try {
                // Get data from database by ID
                absensiPegawaiEntity = absensiPegawaiDao.getById("absensiPegawaiId", absensiPegawaiId);
            } catch (HibernateException e) {
                logger.error("[absensiBoimpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode Absensi, please inform to your admin...," + e.getMessage());
            }
            if (absensiPegawaiEntity != null) {
                // Modify from bean to entity serializable
                absensiPegawaiEntity.setFlag(bean.getFlag());
                absensiPegawaiEntity.setAction(bean.getAction());
                absensiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                absensiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Delete (Edit) into database
                    absensiPegawaiDao.updateAndSave(absensiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[absensiBoimpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data absensi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[absensiBoimpl.saveDelete] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data absensi with request id, please check again your data ...");
            }
        }
        logger.info("[absensiBoimpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(AbsensiPegawai bean) throws GeneralBOException {
        logger.info("[absensiBoimpl.saveDelete] start process >>>");

        if (bean!=null) {
            String absensiPegawaiId = bean.getAbsensiPegawaiId();
            AbsensiPegawaiEntity absensiPegawaiEntity = null;
            try {
                // Get data from database by ID
                absensiPegawaiEntity = absensiPegawaiDao.getById("absensiPegawaiId", absensiPegawaiId);
            } catch (HibernateException e) {
                logger.error("[absensiBoimpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode Absensi, please inform to your admin...," + e.getMessage());
            }
            if (absensiPegawaiEntity != null) {
                // Modify from bean to entity serializable
                absensiPegawaiEntity.setFlag(bean.getFlag());
                absensiPegawaiEntity.setAction(bean.getAction());
                absensiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                absensiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                absensiPegawaiEntity.setJamMasuk(bean.getJamMasuk());
                absensiPegawaiEntity.setJamKeluar(bean.getJamKeluar());
                absensiPegawaiEntity.setStatusAbsensi(bean.getStatusAbsensi());
                try {
                    // Delete (Edit) into database
                    absensiPegawaiDao.updateAndSave(absensiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[absensiBoimpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data absensi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[absensiBoimpl.saveDelete] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data absensi with request id, please check again your data ...");
            }
        }
        logger.info("[absensiBoimpl.saveDelete] end process <<<");
    }
    @Override
    public void saveApprove(AbsensiPegawai bean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.saveApprove] start process >>>");
        Map hsCriteria = new HashMap();
        String atasanNip = null;
        if (bean!=null) {
            String absensiPegawaiId = bean.getAbsensiPegawaiId();
            AbsensiPegawaiEntity absensiPegawaiEntity = null;
            try {
                // Get data from database by ID
                absensiPegawaiEntity = absensiPegawaiDao.getById("absensiPegawaiId", absensiPegawaiId);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.saveApprove] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Absensi by Kode Absensi Pegawai, please inform to your admin...," + e.getMessage());
            }
            if (absensiPegawaiEntity != null) {
                String userLogin = bean.getUserIdActive();
                absensiPegawaiEntity.setAbsensiPegawaiId(bean.getAbsensiPegawaiId());
                absensiPegawaiEntity.setFlag(bean.getFlag());
                //Approve
                if (bean.getApprovalFlag().equals("Y")) {
                    absensiPegawaiEntity.setApprovalFlag("Y");
                } else {
                    absensiPegawaiEntity.setNotApprovalNote(bean.getNotapprovalNote());
                    absensiPegawaiEntity.setApprovalFlag("N");
                }
                absensiPegawaiEntity.setApprovalId(bean.getUserIdActive());
                absensiPegawaiEntity.setApprovalName(bean.getUserNameActive());
                absensiPegawaiEntity.setApprovalDate(bean.getLastUpdate());

                absensiPegawaiEntity.setAction(bean.getAction());
                absensiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                absensiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    absensiPegawaiDao.updateAndSave(absensiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[AbsensiBoImpl.saveApprove] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Absensi Pegawai, please info to your admin..." + e.getMessage());
                }
                StrukturJabatan strukturJabatan = new StrukturJabatan();
                try {
                    strukturJabatan = strukturJabatanDao.searchStrukturRelation(bean.getNip());
                } catch (HibernateException e) {
                    logger.error("[AbsensiBoImpl.saveApprove] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
                // Send Notification
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();

                addNotif.setNotifId(idNotif);
                if (bean.getApprovalFlag()!=null){
                    if (bean.getApprovalFlag().equals("Y")){
                        addNotif.setNote("keterangan absensi anda pada tanggal "+absensiPegawaiEntity.getTanggal()+"disetujui oleh atasan anda");
                    }
                    else{
                        addNotif.setNote("keterangan absensi anda tidak disetujui atasan anda dikarenakan "+bean.getNotapprovalNote());
                    }
                }
                addNotif.setTipeNotifId("umum");
                addNotif.setTipeNotifName("Absensi Pegawai");
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setNip(absensiPegawaiEntity.getNip());
                addNotif.setFromPerson(bean.getUserIdActive());
                addNotif.setNoRequest(absensiPegawaiEntity.getAbsensiPegawaiId());
                addNotif.setCreatedDate(absensiPegawaiEntity.getCreatedDate());
                addNotif.setCreatedWho(absensiPegawaiEntity.getCreatedWho());
                addNotif.setLastUpdate(absensiPegawaiEntity.getLastUpdate());
                addNotif.setLastUpdateWho(absensiPegawaiEntity.getLastUpdateWho());
                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[AbsensiBoImpl.saveApprove] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[AbsensiBoImpl.saveApprove] Error, not found data CutiPegawai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data CutiPegawai with request id, please check again your data ...");
            }
        }
        logger.info("[AbsensiBoImpl.saveEdit] end process <<<");
    }
    @Override
    public void saveAddKeterangan(AbsensiPegawai bean) throws GeneralBOException{
        logger.info("[AbsensiBoimpl.saveAddKeterangan] start process >>>");
        if (bean!=null) {
            String absensiId = bean.getAbsensiPegawaiId();
            AbsensiPegawaiEntity absensiPegawaiEntity = null;
            try {
                absensiPegawaiEntity = absensiPegawaiDao.getById("absensiPegawaiId", absensiId);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoimpl.saveAddKeterangan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data absensi by Absensi Id, please inform to your admin...," + e.getMessage());
            }
            if (absensiPegawaiEntity != null) {
                absensiPegawaiEntity.setKeterangan(bean.getKeterangan());
                absensiPegawaiEntity.setFlagUangMakan(bean.getFlagUangMakan());

                absensiPegawaiEntity.setAction(bean.getAction());
                absensiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                absensiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                absensiPegawaiEntity.setFlag(bean.getFlag());
                try {
                    // Update into database
                    absensiPegawaiDao.updateAndSave(absensiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[AbsensiBoimpl.saveAddKeterangan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data absensi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[AbsensiBoimpl.saveAddKeterangan] Error, not found data absensi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data absensi with request id, please check again your data ...");
            }

            if (bean.getFlagUangMakan()!=null&&bean.getKeterangan()!=null){
                if (!"".equalsIgnoreCase(bean.getFlagUangMakan())&&!"".equalsIgnoreCase(bean.getKeterangan())){
                    String atasanNip="";
                    String atasanName="";
                    List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
                    try {
                        strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(absensiPegawaiEntity.getNip(),absensiPegawaiEntity.getBranchId());
                    } catch (HibernateException e) {
                        logger.error("[AbsensiPegawaiBoImpl.saveAddKeterangan] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }

                    for (StrukturJabatan strukturJabatan : strukturJabatanList ){
                        // Search Leader
                        if (strukturJabatan != null){
                            String[] parts = strukturJabatan.getParentId().split("-");
                            String parent = parts[0];

                            if (parent != null){

                                // search data postion_id from struktur jabatan by parameter parent
                                Map hsCriteria = new HashMap();
                                hsCriteria.put("struktur_jabatan_id", parent);
                                hsCriteria.put("flag","Y");
                                List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
                                try {
                                    strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
                                } catch (HibernateException e) {
                                    logger.error("[AbsensiPegawaiBoImpl.saveAddKeterangan] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                }

                                if (strukturJabatanEntities!=null){
                                    for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities){

                                        // search data nip from personil by parameter position_id from struktur jabatan
                                        String stPosition = "";
                                        if (listStruktur.getPositionId() != null){
                                            stPosition = String.valueOf(listStruktur.getPositionId());
                                        }
                                        hsCriteria = new HashMap();
                                        hsCriteria.put("position_id",stPosition);
                                        hsCriteria.put("flag","Y");
                                        List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                                        try {

                                            itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
                                        } catch (HibernateException e) {
                                            logger.error("[AbsensiPegawaiBoImpl.saveAddKeterangan] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        if (itPersonilPositionEntities != null){
                                            for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities){
                                                String nip2 = listPersonilPosition.getNip();

                                                hsCriteria = new HashMap();
                                                hsCriteria.put("nip",nip2);
                                                hsCriteria.put("flag","Y");

                                                List<ImBiodataEntity> imBiodataEntityList = null;
                                                try {
                                                    imBiodataEntityList = biodataDao.getByCriteria(hsCriteria);
                                                } catch (HibernateException e) {
                                                    logger.error("[AbsensiPegawaiBoImpl.saveAddKeterangan] Error, " + e.getMessage());
                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                }

                                                if (imBiodataEntityList != null){
                                                    for (ImBiodataEntity listBio : imBiodataEntityList){

                                                        //set date nip from personil into Trainig person's approvalId
                                                        atasanNip=listBio.getNip();
                                                        atasanName=listBio.getNamaPegawai();
                                                    }
                                                }
                                                // Send Notification
                                                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                                                String idNotif = notifikasiDao.getNextNotifikasiId();
                                                addNotif.setNotifId(idNotif);
                                                ImBiodataEntity imBiodataEntity = null ;
                                                imBiodataEntity = biodataDao.getById("nip", bean.getNip(), "Y");

                                                addNotif.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
                                                addNotif.setTipeNotifId("TN33");
                                                addNotif.setTipeNotifName("Absensi Pegawai");
                                                addNotif.setRead("Y");
                                                addNotif.setFlag("Y");
                                                addNotif.setAction("C");
                                                addNotif.setNip(atasanNip);
                                                addNotif.setFromPerson(absensiPegawaiEntity.getNip());
                                                addNotif.setNoRequest(absensiPegawaiEntity.getAbsensiPegawaiId());
                                                addNotif.setCreatedDate(absensiPegawaiEntity.getCreatedDate());
                                                addNotif.setCreatedWho(absensiPegawaiEntity.getCreatedWho());
                                                addNotif.setLastUpdate(absensiPegawaiEntity.getLastUpdate());
                                                addNotif.setLastUpdateWho(absensiPegawaiEntity.getLastUpdateWho());

                                                try {
                                                    notifikasiDao.addAndSave(addNotif);
                                                } catch (HibernateException e) {
                                                    logger.error("[AbsensiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("[AbsensiBoimpl.saveAddKeterangan] end process <<<");
    }
    @Override
    public AbsensiPegawai saveAdd(AbsensiPegawai bean) throws GeneralBOException {
        logger.info("[AbsensiPegawaiBoImpl.saveAdd] start process >>>");

        if (bean != null) {

            String absensiPegawaiId;
            try {
                // Generating ID, get from postgre sequence
                absensiPegawaiId = absensiPegawaiDao.getNextAbsensiPegawaiId();
            } catch (HibernateException e) {
                logger.error("[AbsensiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence Absensi Pegawai id, please info to your admin..." + e.getMessage());
            }

            AbsensiPegawaiEntity absensiPegawaiEntity = new AbsensiPegawaiEntity();

            absensiPegawaiEntity.setAbsensiPegawaiId(absensiPegawaiId);
            absensiPegawaiEntity.setIjin(bean.getIjin());
            absensiPegawaiEntity.setLembur(bean.getLembur());
            absensiPegawaiEntity.setNip(bean.getNip());
            absensiPegawaiEntity.setBranchId(bean.getBranchId());
            absensiPegawaiEntity.setJamMasuk(bean.getJamMasuk());
            absensiPegawaiEntity.setJamKeluar(bean.getJamKeluar());
            absensiPegawaiEntity.setTanggal(bean.getTanggal());
            absensiPegawaiEntity.setStatusAbsensi(bean.getStatusAbsensi());
            absensiPegawaiEntity.setJenisLembur(bean.getJenisLembur());
            absensiPegawaiEntity.setLamaLembur(bean.getLamaLembur());
            absensiPegawaiEntity.setJamLembur(bean.getJamLembur());
            absensiPegawaiEntity.setBiayaLembur(bean.getBiayaLembur());
            absensiPegawaiEntity.setTipeHari(bean.getTipeHari());
            absensiPegawaiEntity.setRealisasiJamLembur(bean.getRealisasiJamLembur());
            absensiPegawaiEntity.setFlagUangMakan(bean.getFlagUangMakan());
            absensiPegawaiEntity.setKeterangan(bean.getKeterangan());
            absensiPegawaiEntity.setFlagUangMakan(bean.getFlagUangMakan());
            absensiPegawaiEntity.setKeterangan(bean.getKeterangan());

            absensiPegawaiEntity.setStatusAbsensi2(bean.getStatusAbsensi2());
            absensiPegawaiEntity.setJamMasuk2(bean.getJamMasuk2());
            absensiPegawaiEntity.setJamPulang2(bean.getJamPulang2());
            absensiPegawaiEntity.setStatusAbsensiOnCall(bean.getStatusAbsensiOnCall());
            absensiPegawaiEntity.setJamMasukOnCall(bean.getJamMasukOnCall());
            absensiPegawaiEntity.setJamPulangOnCall(bean.getJamPulangOnCall());
            absensiPegawaiEntity.setTelat(bean.getTelat());

            absensiPegawaiEntity.setCreatedWho(bean.getCreatedWho());
            absensiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
            absensiPegawaiEntity.setCreatedDate(bean.getCreatedDate());
            absensiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            absensiPegawaiEntity.setAction(bean.getAction());
            absensiPegawaiEntity.setFlag(bean.getFlag());

            List<AbsensiPegawaiEntity> absensiPegawaiEntityList = new ArrayList<>();
            Map hsCriteria = new HashMap();
            hsCriteria.put("tanggal", bean.getTanggal());
            hsCriteria.put("nip", bean.getNip());
            hsCriteria.put("jam_masuk", bean.getJamMasuk());
            hsCriteria.put("jam_keluar", bean.getJamKeluar());
            hsCriteria.put("status_absensi", bean.getStatusAbsensi());
            hsCriteria.put("lama_lembur", bean.getLamaLembur());
            hsCriteria.put("jam_lembur", bean.getJamLembur());
            hsCriteria.put("flag", "Y");
            absensiPegawaiEntityList = absensiPegawaiDao.getByCriteria(hsCriteria);
            if (absensiPegawaiEntityList.size() == 0) {
                try {
                    absensiPegawaiDao.addAndSave(absensiPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[AbsensiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Absensi Pegawai, please info to your admin..." + e.getMessage());
                }
            }

        }
        logger.info("[AbsensiPegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public PegawaiTambahanAbsensi saveTambahan(PegawaiTambahanAbsensi bean) throws GeneralBOException {
        logger.info("[AbsensiPegawaiBoImpl.saveTambahan] start process >>>");

        if (bean != null) {
            String pegawaiTambahanAbsensiId;
            try {
                // Generating ID, get from postgre sequence
                pegawaiTambahanAbsensiId = pegawaiTambahanAbsensiDao.getNextPegawaiTambahanAbsensiId();
            } catch (HibernateException e) {
                logger.error("[AbsensiPegawaiBoImpl.saveTambahan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence Absensi Pegawai id, please info to your admin..." + e.getMessage());
            }
            PegawaiTambahanAbsensiEntity pegawaiTambahanAbsensiEntity = new PegawaiTambahanAbsensiEntity();

            pegawaiTambahanAbsensiEntity.setPegawaiTambahanAbsensiId(pegawaiTambahanAbsensiId);
            pegawaiTambahanAbsensiEntity.setPin(bean.getPin());
            pegawaiTambahanAbsensiEntity.setNama(bean.getNama());
            pegawaiTambahanAbsensiEntity.setJamMasuk(bean.getJamMasuk());
            pegawaiTambahanAbsensiEntity.setJamPulang(bean.getJamPulang());
            pegawaiTambahanAbsensiEntity.setStatusAbsensi(bean.getStatusAbsensi());
            pegawaiTambahanAbsensiEntity.setTanggal(bean.getTanggal());


            pegawaiTambahanAbsensiEntity.setCreatedWho(bean.getCreatedWho());
            pegawaiTambahanAbsensiEntity.setLastUpdate(bean.getLastUpdate());
            pegawaiTambahanAbsensiEntity.setCreatedDate(bean.getCreatedDate());
            pegawaiTambahanAbsensiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pegawaiTambahanAbsensiEntity.setAction(bean.getAction());
            pegawaiTambahanAbsensiEntity.setFlag(bean.getFlag());

            List<PegawaiTambahanAbsensiEntity> pegawaiTambahanAbsensiEntityList = new ArrayList<>();

            pegawaiTambahanAbsensiEntityList = pegawaiTambahanAbsensiDao.searchExistingAbsensi(bean.getPin(),bean.getTanggal());
            if (pegawaiTambahanAbsensiEntityList.size() == 0) {
                try {
                    pegawaiTambahanAbsensiDao.addAndSave(pegawaiTambahanAbsensiEntity);
                } catch (HibernateException e) {
                    logger.error("[AbsensiPegawaiBoImpl.saveTambahan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Absensi Pegawai, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[AbsensiPegawaiBoImpl.saveTambahan] end process <<<");
        return null;
    }
    //Perhitungan Tunjangan UMK
    private BigDecimal getTunjanganUmk(String branchId, String golonganId){
        BigDecimal hasil = new BigDecimal(0);
        List<ImPayrollTunjanganUmkEntity> ImtUmk = null;

        ImtUmk = payrollTunjanganUmkDao.getData2(branchId, golonganId, "2019");
        if(ImtUmk != null){
            for(ImPayrollTunjanganUmkEntity skalaUmk : ImtUmk){
                hasil = hasil.add(skalaUmk.getNilai());
            }
        }
        return hasil;
    }
    //Perhitungan Tunjangan Peralihan
    private BigDecimal getTunjPeralihan(String nip, Date tanggal){
        BigDecimal hasil = new BigDecimal(0);
        List<ItPayrollEntity> itPayrollEntityList= new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy");
        String tahun = df.format(tanggal);
        itPayrollEntityList = payrollDao.getTunjanganPeralihanForAbsensi(nip, tahun);
        for(ItPayrollEntity itPayrollEntity : itPayrollEntityList){
            hasil = hasil.add(itPayrollEntity.getTunjanganPeralihan());
            break;
        }
        return hasil;
    }

    // Sigit, 2020-11-26
    // Perhitungan Tunjangan Peralihan Gapok, bulan terakhir pada tahun sekarang
    private BigDecimal getPeralihanGapok(String nip, Date tanggal){
        BigDecimal hasil = new BigDecimal(0);
        List<ItPayrollEntity> itPayrollEntityList= new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy");
        String tahun = df.format(tanggal);
        try{
            itPayrollEntityList = payrollDao.getTunjanganPeralihanForAbsensi(nip, tahun);
        } catch (HibernateException e) {
            logger.error("[AbsensiBoImpl.getPeralihanGapok] Error, " + e.getMessage());
            throw new GeneralBOException ("Found problem when retrieving Tunjangan Peralihan For Absensi, " + e.getMessage());
        }
        for(ItPayrollEntity itPayrollEntity : itPayrollEntityList){
            hasil = hasil.add(itPayrollEntity.getPeralihanGapok());
            break;
        }
        return hasil;
    }

    @Override
    public List<AbsensiPegawaiEntity> saveTmp(AbsensiPegawai bean, String tipeHari) throws GeneralBOException {
        logger.info("[AbsensiPegawaiBoImpl.saveAdd] start process >>>");
        List<ImBiodataEntity> biodataList = new ArrayList<>();
        String branch ="";
        List<ItPersonilPositionEntity> personilPositionEntityList = new ArrayList<>();
        List<IjinKeluarEntity> ijinKeluarEntityList = new ArrayList<>();
        List<LemburEntity> lemburEntityList = new ArrayList<>();
        List<AbsensiPegawaiEntity> listOfAbsensiFinal = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        listOfAbsensiFinal = (List<AbsensiPegawaiEntity>) session.getAttribute("listOfResultAbsensiFinal");
        String tipePegawai;

        if (bean != null) {
            String tahunGaji="";
            try {
                ImCompany company = companyDao.getCompanyInfo("Y");
                if (!("").equalsIgnoreCase(company.getPeriodeGaji())) {
                    tahunGaji = company.getPeriodeGaji();
                } else {
                    String status = "Error : tidak ditemukan periode gaji pada Company";
                    logger.error("[PayrollBoImpl.dataAddPayroll] " + status);
                    throw new GeneralBOException(status);
                }
            }catch (HibernateException e){
                logger.error("[PayrollBoImpl.dataAddPayroll] " + e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }

            // creating object entity serializable
            AbsensiPegawaiEntity absensiPegawaiEntity = new AbsensiPegawaiEntity();

            absensiPegawaiEntity.setTanggal(bean.getTanggal());
            absensiPegawaiEntity.setJamMasuk(bean.getJamMasuk());
            absensiPegawaiEntity.setJamKeluar(bean.getJamKeluar());
            absensiPegawaiEntity.setStatusAbsensi(bean.getStatusAbsensi());
            absensiPegawaiEntity.setStTanggal(bean.getStTanggal());
            absensiPegawaiEntity.setNama(bean.getNama());
            absensiPegawaiEntity.setStatusName(bean.getStatusName());

            absensiPegawaiEntity.setRealisasiJamLembur(bean.getRealisasiJamLembur());
            absensiPegawaiEntity.setTipePegawai(bean.getTipePegawai());
            absensiPegawaiEntity.setStatusGiling(bean.getStatusGiling());
            absensiPegawaiEntity.setPin(bean.getPin());
            absensiPegawaiEntity.setTipeHari(tipeHari);
            absensiPegawaiEntity.setPengajuanLembur((double)0);
            if (tipeHari.equalsIgnoreCase("hari_libur")) {
                absensiPegawaiEntity.setTipeHariName("libur");
            } else {
                absensiPegawaiEntity.setTipeHariName("kerja");
            }

            if (bean.getPin() != null) {
                Map hsCriteria = new HashMap();
                hsCriteria.put("pin", bean.getPin());
                hsCriteria.put("flag", "Y");

                try {
                    biodataList = biodataDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retrieving Biodata by Criteria, " + e.getMessage());
                }
            }
            for (ImBiodataEntity biodataEntity : biodataList) {
                if (!("Y").equalsIgnoreCase(biodataEntity.getShift())){
                    absensiPegawaiEntity.setNip(biodataEntity.getNip());
                    tipePegawai = biodataEntity.getTipePegawai();
                    Map hsCriteria2 = new HashMap();
                    hsCriteria2.put("nip", biodataEntity.getNip());
                    hsCriteria2.put("flag", "Y");
                    try{
                        personilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria2);
                    } catch (HibernateException e) {
                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving Personil Position by Criteria, " + e.getMessage());
                    }
                    for (ItPersonilPositionEntity personilPosition : personilPositionEntityList) {
                        absensiPegawaiEntity.setBranchId(personilPosition.getBranchId());
                        branch=personilPosition.getBranchId();
                        if (personilPosition.getPositionId() != null) {
                            ImPosition imPosition;
                            try{
                                imPosition = positionDao.getById("positionId", personilPosition.getPositionId(), "Y");
                            } catch (HibernateException e) {
                                logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when retrieving Position by ID, " + e.getMessage());
                            }
                            if (imPosition.getDepartmentId() != null) {
                                if (!imPosition.getDepartmentId().equalsIgnoreCase("")) {
                                    ImDepartmentEntity department;
                                    try{
                                        department = departmentDao.getById("departmentId", imPosition.getDepartmentId(), "Y");
                                    } catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Department by ID" + e.getMessage());
                                    }
                                    absensiPegawaiEntity.setDivisi(department.getDepartmentName());
                                }
                            }
                        }
                    }
                    Map hsCriteria3 = new HashMap();
                    hsCriteria3.put("nip", biodataEntity.getNip());
                    hsCriteria3.put("flag", "Y");
                    hsCriteria3.put("tanggal", bean.getTanggal());
                    hsCriteria3.put("ijin_id", "IJ001");
                    hsCriteria3.put("approval_flag", "Y");
                    hsCriteria3.put("from", "absensi");
                    try{
                        ijinKeluarEntityList = ijinKeluarDao.getByCriteria(hsCriteria3);
                    } catch (HibernateException e) {
                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving Ijin Keluar by Criteria," + e.getMessage());
                    }
                    if (ijinKeluarEntityList.size() != 0) {
                        absensiPegawaiEntity.setIjin("Y");
                        for (IjinKeluarEntity ijinKeluarEntity : ijinKeluarEntityList) {
                            absensiPegawaiEntity.setMulaiIzin(ijinKeluarEntity.getJamKeluar());
                            absensiPegawaiEntity.setPulangIzin(ijinKeluarEntity.getJamKembali());
                        }
                    } else {
                        absensiPegawaiEntity.setIjin("N");
                        absensiPegawaiEntity.setMulaiIzin("-");
                        absensiPegawaiEntity.setPulangIzin("-");
                    }
                    try{
                        lemburEntityList = lemburDao.getByCriteria(hsCriteria3);
                    } catch (HibernateException e) {
                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving Lembur by Criteria, " + e.getMessage());
                    }
                    if (lemburEntityList.size() != 0&&!("00").equalsIgnoreCase(bean.getStatusAbsensi())) {
                        int iJamAwalKerja = Integer.parseInt(bean.getJamMasuk().replace(":",""));
                        int iJamAkhirKerja = Integer.parseInt(bean.getJamKeluar().replace(":",""));
                        int ijamAwalKerjaDb = Integer.parseInt(bean.getJamMasukDb().replace(":",""));
                        int iJamAkhirKerjaDb = Integer.parseInt(bean.getJamPulangDb().replace(":",""));
                        int iJamAwalLemburKerja = Integer.parseInt(bean.getJamMasukDb().replace(":",""));
                        int iJamAkhirLemburKerja = Integer.parseInt(bean.getJamPulangDb().replace(":",""));
                        Double lamaLembur=(double)0;
                        Double pengajuanLembur=(double)0;
                        for (LemburEntity lemburEntity : lemburEntityList) {
                            lamaLembur=lamaLembur+lemburEntity.getLamaJam();
                            pengajuanLembur=pengajuanLembur+lemburEntity.getLamaJam();
                            absensiPegawaiEntity.setJenisLembur(lemburEntity.getTipeLembur());
                            if (lemburEntity.getTipeLembur().equalsIgnoreCase("R")) {
                                absensiPegawaiEntity.setJenisLemburName("Rutin");
                            } else if (lemburEntity.getTipeLembur().equalsIgnoreCase("I")) {
                                absensiPegawaiEntity.setJenisLemburName("Non Rutin");
                            }
                            iJamAwalLemburKerja= Integer.parseInt(lemburEntity.getJamAwal().replace(":",""));
                            iJamAkhirLemburKerja= Integer.parseInt(lemburEntity.getJamAkhir().replace(":",""));

                            if (iJamAkhirKerja<=iJamAwalLemburKerja&&iJamAkhirKerja>=400){
                                absensiPegawaiEntity.setRealisasiJamLembur((double) 0);
                            }
                        }

                        if (bean.getRealisasiJamLembur() <= lamaLembur) {
                            lamaLembur = bean.getRealisasiJamLembur();
                        }

                        if (iJamAwalLemburKerja<=ijamAwalKerjaDb){
                            absensiPegawaiEntity.setAwalLembur(bean.getJamMasuk());
                            absensiPegawaiEntity.setSelesaiLembur(bean.getJamMasukDb());
                        }else if (iJamAkhirLemburKerja>=ijamAwalKerjaDb){
                            absensiPegawaiEntity.setAwalLembur(bean.getJamPulangDb());
                            absensiPegawaiEntity.setSelesaiLembur(bean.getJamKeluar());
                        }else {
                            absensiPegawaiEntity.setAwalLembur(bean.getJamPulangDb());
                            absensiPegawaiEntity.setSelesaiLembur(bean.getJamKeluar());
                        }
//                    absensiPegawaiEntity.setAwalLembur(bean.getJamPulangDb());
//                    absensiPegawaiEntity.setSelesaiLembur(bean.getJamKeluar());
                        absensiPegawaiEntity.setJamPulang(bean.getJamKeluar());
                        absensiPegawaiEntity.setLamaLembur(lamaLembur);
                        absensiPegawaiEntity.setPengajuanLembur(pengajuanLembur);

                        List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                        Map hsCriteria4 = new HashMap();
                        hsCriteria4.put("tipe_pegawai_id", tipePegawai);
                        hsCriteria4.put("flag", "Y");
                        double faktor = 0;
                        Double upahLembur = 0d;
                        Double gapok = 0d;
                        Double sankhus = 0d;
                        try{
                            pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                        } catch (HibernateException e) {
                            logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retrieving Pengali Faktor Lembur by Criteria, " + e.getMessage());
                        }
                        for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                            faktor = pengaliFaktorLemburEntity.getFaktor();
                        }

                        hsCriteria4 = new HashMap();
                        hsCriteria4.put("golongan_id", biodataEntity.getGolongan());
                        hsCriteria4.put("point", (int) Math.round(biodataEntity.getPoint()));
                        hsCriteria4.put("tahun", tahunGaji);
                        hsCriteria4.put("flag", "Y");
                        List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                        List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                        if (biodataEntity.getTipePegawai().equalsIgnoreCase("TP01")){
                            try{
                                payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(biodataEntity.getGolongan(),tahunGaji);
                            } catch (HibernateException e) {
                                logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when retrieving Payroll Skala Gaji, " + e.getMessage());
                            }
                            for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                                gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                                sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                            }
                        }else if (biodataEntity.getTipePegawai().equalsIgnoreCase("TP03")){
                            try{
                                payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(biodataEntity.getGolongan(),tahunGaji);
                            } catch (HibernateException e) {
                                logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when retrieving Payroll Skala Gaji PKWT, " + e.getMessage());
                            }
                            for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                                gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                                sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                            }
                        }

                        double jamLembur = 0;
                        int j = 1;
                        if (lamaLembur>0){
                            do{
                                if (lamaLembur>0&&lamaLembur<1){
                                    Map hsCriteria5 = new HashMap();
                                    hsCriteria5.put("tipe_hari", tipeHari);
                                    hsCriteria5.put("jam_lembur", j);
                                    hsCriteria5.put("flag", "Y");
                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                    try{
                                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                    } catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Jam Lembur by Criteria, " + e.getMessage());
                                    }
                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                        jamLembur = jamLembur + (lamaLembur*2);
                                    }
                                    lamaLembur= (double) 0;
                                }else{
                                    Map hsCriteria5 = new HashMap();
                                    hsCriteria5.put("tipe_hari", tipeHari);
                                    hsCriteria5.put("jam_lembur", j);
                                    hsCriteria5.put("flag", "Y");
                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                    try{
                                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                    } catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Jam Lembur by Criteria, " + e.getMessage());
                                    }
                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                        jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                    }
                                    lamaLembur= lamaLembur-1;
                                }
                                j=j+1;
                            }while (lamaLembur>0);
                        }
                        Double peralihan = 0d;
                        peralihan = getTunjPeralihan(absensiPegawaiEntity.getNip(),bean.getTanggal()).doubleValue();
//                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                        String strDate = dateFormat.format(bean.getTanggal());
//                        String[] a = strDate.split("-");
//                        int intBulan =Integer.valueOf(a[1])-1;
//                        if (intBulan<10){a[1]="0"+intBulan;}
//                        String bulan = a[1];
//                        String tahun = a[2];
//                        List<ItPayrollEntity> payrollEntityList = payrollDao.getTunjanganPeralihan(bean.getNip(),bulan,tahun);
//                        for (ItPayrollEntity itPayrollEntity : payrollEntityList){
//                            peralihan=itPayrollEntity.getTunjanganPeralihan().doubleValue();
//                        }
                        upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;

                        absensiPegawaiEntity.setLembur("Y");
                        absensiPegawaiEntity.setJamLembur(jamLembur);
                        absensiPegawaiEntity.setBiayaLembur(upahLembur);
                        String upahNew = "";
                        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setGroupingSeparator('.');

                        kursIndonesia.setDecimalFormatSymbols(formatRp);
                        upahNew = kursIndonesia.format(upahLembur);
                        absensiPegawaiEntity.setBiayaLemburName(upahNew);

                    } else {
                        absensiPegawaiEntity.setJamPulang(bean.getJamKeluar());
                        absensiPegawaiEntity.setLembur("N");
                        absensiPegawaiEntity.setJamLembur(0d);
                        absensiPegawaiEntity.setBiayaLembur(0d);
                        absensiPegawaiEntity.setJenisLembur("");
                        absensiPegawaiEntity.setJenisLemburName("-");
                        absensiPegawaiEntity.setLamaLembur(0d);
                        absensiPegawaiEntity.setRealisasiJamLembur(0d);
                        absensiPegawaiEntity.setAwalLembur("-");
                        absensiPegawaiEntity.setSelesaiLembur("-");
                    }
                }else {
                    boolean tglLibur = false;
                    List<ImLiburEntity> liburEntityList = new ArrayList<>();
                    try{
                        liburEntityList = liburDao.getListLibur(bean.getTanggal());
                    } catch (HibernateException e) {
                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving List Libur, " + e.getMessage());
                    }
                    //cari hari libur
                    if ( liburEntityList.size()!=0) {
                        tglLibur = true;
                        tipeHari="hari_libur";
                    }

                    absensiPegawaiEntity.setNip(biodataEntity.getNip());
                    tipePegawai = biodataEntity.getTipePegawai();
                    Map hsCriteria2 = new HashMap();
                    hsCriteria2.put("nip", biodataEntity.getNip());
                    hsCriteria2.put("flag", "Y");
                    try {
                        personilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria2);
                    } catch (HibernateException e) {
                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving Personil Position by Criteria, " + e.getMessage());
                    }
                    for (ItPersonilPositionEntity personilPosition : personilPositionEntityList) {
                        absensiPegawaiEntity.setBranchId(personilPosition.getBranchId());
                        branch=personilPosition.getBranchId();
                        if (personilPosition.getPositionId() != null) {
                            ImPosition imPosition;
                            try{
                                imPosition = positionDao.getById("positionId", personilPosition.getPositionId(), "Y");
                            } catch (HibernateException e) {
                                logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when retrieving Position by ID, " + e.getMessage());
                            }
                            if (imPosition.getDepartmentId() != null) {
                                if (!imPosition.getDepartmentId().equalsIgnoreCase("")) {
                                    ImDepartmentEntity department;
                                    try {
                                        department = departmentDao.getById("departmentId", imPosition.getDepartmentId(), "Y");
                                    } catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Department by ID, " + e.getMessage());
                                    }
                                    absensiPegawaiEntity.setDivisi(department.getDepartmentName());
                                }
                            }
                        }
                    }
                    Map hsCriteria3 = new HashMap();
                    hsCriteria3.put("nip", biodataEntity.getNip());
                    hsCriteria3.put("flag", "Y");
                    hsCriteria3.put("tanggal", bean.getTanggal());
                    hsCriteria3.put("ijin_id", "IJ001");
                    hsCriteria3.put("approval_flag", "Y");
                    hsCriteria3.put("from", "absensi");
                    try{
                        ijinKeluarEntityList = ijinKeluarDao.getByCriteria(hsCriteria3);
                    } catch (HibernateException e) {
                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving Ijin Keluar by Criteria, " + e.getMessage());
                    }
                    if (ijinKeluarEntityList.size() != 0) {
                        absensiPegawaiEntity.setIjin("Y");
                        for (IjinKeluarEntity ijinKeluarEntity : ijinKeluarEntityList) {
                            absensiPegawaiEntity.setMulaiIzin(ijinKeluarEntity.getJamKeluar());
                            absensiPegawaiEntity.setPulangIzin(ijinKeluarEntity.getJamKembali());
                        }
                    } else {
                        absensiPegawaiEntity.setIjin("N");
                        absensiPegawaiEntity.setMulaiIzin("-");
                        absensiPegawaiEntity.setPulangIzin("-");
                    }
                    try{
                        lemburEntityList = lemburDao.getByCriteria(hsCriteria3);
                    } catch (HibernateException e) {
                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when retrieving Lembur by Criteria, " + e.getMessage());
                    }
                    if (lemburEntityList.size() != 0&&!("00").equalsIgnoreCase(bean.getStatusAbsensi())) {
                        int iJamAwalKerja = Integer.parseInt(bean.getJamMasuk().replace(":",""));
                        int iJamAkhirKerja = Integer.parseInt(bean.getJamKeluar().replace(":",""));
                        int ijamAwalKerjaDb = Integer.parseInt(bean.getJamMasukDb().replace(":",""));
                        int iJamAkhirKerjaDb = Integer.parseInt(bean.getJamPulangDb().replace(":",""));
                        int iJamAwalLemburKerja = Integer.parseInt(bean.getJamMasukDb().replace(":",""));
                        int iJamAkhirLemburKerja = Integer.parseInt(bean.getJamPulangDb().replace(":",""));
                        Double lamaLembur=(double)0;
                        Double pengajuanLembur=(double)0;
                        for (LemburEntity lemburEntity : lemburEntityList) {
                            lamaLembur=lamaLembur+lemburEntity.getLamaJam();
                            pengajuanLembur=pengajuanLembur+lemburEntity.getLamaJam();
                            absensiPegawaiEntity.setJenisLembur(lemburEntity.getTipeLembur());
                            if (lemburEntity.getTipeLembur().equalsIgnoreCase("R")) {
                                absensiPegawaiEntity.setJenisLemburName("Rutin");
                            } else if (lemburEntity.getTipeLembur().equalsIgnoreCase("I")) {
                                absensiPegawaiEntity.setJenisLemburName("Non Rutin");
                            }
                            iJamAwalLemburKerja= Integer.parseInt(lemburEntity.getJamAwal().replace(":",""));
                            iJamAkhirLemburKerja= Integer.parseInt(lemburEntity.getJamAkhir().replace(":",""));
                        }

                        if (bean.getRealisasiJamLembur() <= lamaLembur) {
                            lamaLembur = bean.getRealisasiJamLembur();
                        }

                        if (iJamAwalLemburKerja<=ijamAwalKerjaDb){
                            absensiPegawaiEntity.setAwalLembur(bean.getJamMasuk());
                            absensiPegawaiEntity.setSelesaiLembur(bean.getJamMasukDb());
                        }else if (iJamAkhirLemburKerja>=ijamAwalKerjaDb){
                            absensiPegawaiEntity.setAwalLembur(bean.getJamPulangDb());
                            absensiPegawaiEntity.setSelesaiLembur(bean.getJamKeluar());
                        }else {
                            absensiPegawaiEntity.setAwalLembur(bean.getJamPulangDb());
                            absensiPegawaiEntity.setSelesaiLembur(bean.getJamKeluar());
                        }
//                    absensiPegawaiEntity.setAwalLembur(bean.getJamPulangDb());
//                    absensiPegawaiEntity.setSelesaiLembur(bean.getJamKeluar());
                        absensiPegawaiEntity.setJamPulang(bean.getJamKeluar());
                        absensiPegawaiEntity.setLamaLembur(lamaLembur);
                        absensiPegawaiEntity.setPengajuanLembur(pengajuanLembur);

                        List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                        Map hsCriteria4 = new HashMap();
                        hsCriteria4.put("tipe_pegawai_id", tipePegawai);
                        hsCriteria4.put("flag", "Y");
                        double faktor = 0;
                        Double upahLembur = 0d;
                        Double gapok = 0d;
                        Double sankhus= 0d;
                        try {
                            pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                        } catch (HibernateException e) {
                            logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retrieving Pengali Faktor Lembur, " + e.getMessage());
                        }
                        for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                            faktor = pengaliFaktorLemburEntity.getFaktor();
                        }

                        hsCriteria4 = new HashMap();
                        hsCriteria4.put("golongan_id", biodataEntity.getGolongan());
                        hsCriteria4.put("point", (int) Math.round(biodataEntity.getPoint()));
                        hsCriteria4.put("tahun", tahunGaji);
                        hsCriteria4.put("flag", "Y");
                        List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                        try {
                            payrollSkalaGajiList = payrollSkalaGajiDao.getByCriteria(hsCriteria4);
                        } catch (HibernateException e) {
                            logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retrieving Payroll Skala Gaji by Criteria, " + e.getMessage());
                        }
                        for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                            gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                            sankhus = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                        }

                        double jamLembur = 0;
                        int j = 1;
                        if (lamaLembur>0){
                            do{
                                if (lamaLembur>0&&lamaLembur<1){
                                    Map hsCriteria5 = new HashMap();
                                    hsCriteria5.put("tipe_hari", tipeHari);
                                    hsCriteria5.put("jam_lembur", j);
                                    hsCriteria5.put("flag", "Y");
                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                    try{
                                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                    }  catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Jam Lembur, " + e.getMessage());
                                    }
                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                        jamLembur = jamLembur + (lamaLembur*2);
                                    }
                                    lamaLembur= (double) 0;
                                }else{
                                    Map hsCriteria5 = new HashMap();
                                    hsCriteria5.put("tipe_hari", tipeHari);
                                    hsCriteria5.put("jam_lembur", j);
                                    hsCriteria5.put("flag", "Y");
                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                    try{
                                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                    } catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Jam Lembur, " + e.getMessage());
                                    }
                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                        jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                    }
                                    lamaLembur= lamaLembur-1;
                                }
                                j=j+1;
                            }while (lamaLembur>0);
                        }
                        Double peralihan = 0d;
                        peralihan = getTunjPeralihan(absensiPegawaiEntity.getNip(),bean.getTanggal()).doubleValue();
                        upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;

                        absensiPegawaiEntity.setLembur("Y");
                        absensiPegawaiEntity.setJamLembur(jamLembur);
                        absensiPegawaiEntity.setBiayaLembur(upahLembur);
                        String upahNew = "";
                        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setGroupingSeparator('.');

                        kursIndonesia.setDecimalFormatSymbols(formatRp);
                        upahNew = kursIndonesia.format(upahLembur);
                        absensiPegawaiEntity.setBiayaLemburName(upahNew);

                    } else if (tglLibur){
                        Double lamaLembur= 8d;
                        List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                        Map hsCriteria4 = new HashMap();
                        hsCriteria4.put("tipe_pegawai_id", tipePegawai);
                        hsCriteria4.put("flag", "Y");
                        double faktor = 0;
                        Double upahLembur = 0d;
                        Double gapok = 0d;
                        Double sankhus = 0d;
                        try{
                            pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                        } catch (HibernateException e) {
                            logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retrieving Pengali Faktor Lembur, " + e.getMessage());
                        }
                        for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                            faktor = pengaliFaktorLemburEntity.getFaktor();
                        }

                        hsCriteria4 = new HashMap();
                        hsCriteria4.put("golongan_id", biodataEntity.getGolongan());
                        hsCriteria4.put("point", (int) Math.round(biodataEntity.getPoint()));
                        hsCriteria4.put("tahun", tahunGaji);
                        hsCriteria4.put("flag", "Y");
                        List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                        try {
                            payrollSkalaGajiList = payrollSkalaGajiDao.getByCriteria(hsCriteria4);
                        }  catch (HibernateException e) {
                            logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retrieving Payroll Skala Gaji by Criteria, " + e.getMessage());
                        }
                        for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                            gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                            sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                        }

                        double jamLembur = 0;
                        int j = 1;
                        if (lamaLembur>0){
                            do{
                                if (lamaLembur>0&&lamaLembur<1){
                                    Map hsCriteria5 = new HashMap();
                                    hsCriteria5.put("tipe_hari", tipeHari);
                                    hsCriteria5.put("jam_lembur", j);
                                    hsCriteria5.put("flag", "Y");
                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                    try{
                                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                    }  catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Jam Lembur by Criteria, " + e.getMessage());
                                    }
                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                        jamLembur = jamLembur + (lamaLembur*2);
                                    }
                                    lamaLembur= (double) 0;
                                }else{
                                    Map hsCriteria5 = new HashMap();
                                    hsCriteria5.put("tipe_hari", tipeHari);
                                    hsCriteria5.put("jam_lembur", j);
                                    hsCriteria5.put("flag", "Y");
                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                    try{
                                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                    }  catch (HibernateException e) {
                                        logger.error("[AbsensiBoImpl.saveTmp] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when retrieving Jam Lembur by Criteria" + e.getMessage());
                                    }
                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                        jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                    }
                                    lamaLembur= lamaLembur-1;
                                }
                                j=j+1;
                            }while (lamaLembur>0);
                        }
                        Double peralihan = 0d;
                        peralihan = getTunjPeralihan(absensiPegawaiEntity.getNip(),bean.getTanggal()).doubleValue();

                        upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;

                        absensiPegawaiEntity.setLembur("Y");
                        absensiPegawaiEntity.setJamLembur(jamLembur);
                        absensiPegawaiEntity.setBiayaLembur(upahLembur);
                        String upahNew = "";
                        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                        formatRp.setCurrencySymbol("Rp. ");
                        formatRp.setGroupingSeparator('.');

                        kursIndonesia.setDecimalFormatSymbols(formatRp);
                        upahNew = kursIndonesia.format(upahLembur);
                        absensiPegawaiEntity.setBiayaLemburName(upahNew);

                        absensiPegawaiEntity.setJamPulang(bean.getJamKeluar());
                        absensiPegawaiEntity.setJenisLembur("I");
                        absensiPegawaiEntity.setJenisLemburName("Non Rutin");
                        absensiPegawaiEntity.setLamaLembur(8d);
                        absensiPegawaiEntity.setTipeHari(tipeHari);
                        absensiPegawaiEntity.setTipeHariName("libur");
                        absensiPegawaiEntity.setRealisasiJamLembur(8d);
                        absensiPegawaiEntity.setAwalLembur(bean.getJamMasuk());
                        absensiPegawaiEntity.setSelesaiLembur(bean.getJamKeluar());
                    }else {
                        absensiPegawaiEntity.setJamPulang(bean.getJamKeluar());
                        absensiPegawaiEntity.setLembur("N");
                        absensiPegawaiEntity.setJamLembur(0d);
                        absensiPegawaiEntity.setBiayaLembur(0d);
                        absensiPegawaiEntity.setJenisLembur("");
                        absensiPegawaiEntity.setJenisLemburName("-");
                        absensiPegawaiEntity.setLamaLembur(0d);
                        absensiPegawaiEntity.setRealisasiJamLembur(0d);
                        absensiPegawaiEntity.setAwalLembur("-");
                        absensiPegawaiEntity.setSelesaiLembur("-");
                    }
                }

            }
            try {
                if (listOfAbsensiFinal != null) {
                    listOfAbsensiFinal.add(absensiPegawaiEntity);
                } else {
                    listOfAbsensiFinal = new ArrayList();
                    listOfAbsensiFinal.add(absensiPegawaiEntity);
                }
                //insert into Session
                session.setAttribute("listOfResultAbsensiFinal", listOfAbsensiFinal);
            } catch (HibernateException e) {
                logger.error("[AbsensiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[AbsensiPegawaiBoImpl.saveAdd] end process <<<");
        return listOfAbsensiFinal;
    }

    @Override
    public List<AbsensiPegawaiEntity> getLemburBonusSatpam(List<AbsensiPegawaiEntity> absensiPegawaiEntityList) throws GeneralBOException {
        List<AbsensiPegawaiEntity> listOfResult = new ArrayList<>();
        List<AbsensiPegawaiEntity> listOfAbsensiFinalSatpam = new ArrayList<>();
        List<AbsensiPegawaiEntity> listOfAbsensiFinalBukanSatpam = new ArrayList<>();
        List<ImBiodataEntity> biodataEntityList = biodataDao.getListPersonalSatpam();

        for (ImBiodataEntity biodata : biodataEntityList){
            for (AbsensiPegawaiEntity mesinAbsensi : absensiPegawaiEntityList){
                if (biodata.getNip().equalsIgnoreCase(mesinAbsensi.getNip())) {
                    mesinAbsensi.setGolongan(biodata.getGolongan());
                    mesinAbsensi.setPoint(biodata.getPoint());
                    listOfAbsensiFinalSatpam.add(mesinAbsensi);
                }
            }
        }
        for (AbsensiPegawaiEntity mesinAbsensi : absensiPegawaiEntityList){
            boolean ada=false;
            for (ImBiodataEntity biodata : biodataEntityList){
                if (biodata.getNip().equalsIgnoreCase(mesinAbsensi.getNip())){
                    ada=true;
                    break;
                }
            }
            if (!ada){
                listOfAbsensiFinalBukanSatpam.add(mesinAbsensi);
            }
        }
        listOfResult.addAll(listOfAbsensiFinalBukanSatpam);

        boolean sudahJatahLembur=false;
        String nip="";
        for (AbsensiPegawaiEntity absensiPegawaiEntity : listOfAbsensiFinalSatpam){
            if (!nip.equalsIgnoreCase(absensiPegawaiEntity.getNip())){
                sudahJatahLembur=false;
            }
            if (!nip.equalsIgnoreCase(absensiPegawaiEntity.getNip())&&!sudahJatahLembur&&!("Y").equalsIgnoreCase(absensiPegawaiEntity.getLembur())&&!("00").equalsIgnoreCase(absensiPegawaiEntity.getStatusAbsensi())){
                nip=absensiPegawaiEntity.getNip();
                //hitung jam dan hari
                double faktor = 0;
                List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                Map hsCriteria4 = new HashMap();
                hsCriteria4.put("tipe_pegawai_id", "TP01");
                hsCriteria4.put("flag", "Y");
                pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                    faktor = pengaliFaktorLemburEntity.getFaktor();
                }
                Double upahLembur = 0d;
                hsCriteria4 = new HashMap();
                hsCriteria4.put("golongan_id", absensiPegawaiEntity.getGolongan());
                hsCriteria4.put("point", (int) Math.round(absensiPegawaiEntity.getPoint()));
                hsCriteria4.put("flag", "Y");
                List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                payrollSkalaGajiList = payrollSkalaGajiDao.getByCriteria(hsCriteria4);
                for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                    upahLembur = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                }
                double jamLembur = 15.50;
                Double umk =0d;
                Double peralihan = 0d;
                umk = getTunjanganUmk("KD01",absensiPegawaiEntity.getGolongan()).doubleValue();
                peralihan = getTunjPeralihan(absensiPegawaiEntity.getNip(),absensiPegawaiEntity.getTanggal()).doubleValue();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(absensiPegawaiEntity.getTanggal());
                String[] a = strDate.split("-");
                int intBulan =Integer.valueOf(a[1])-1;
                if (intBulan<10){a[1]="0"+intBulan;}
                String bulan = a[1];
                String tahun = a[2];
                List<ItPayrollEntity> payrollEntityList = payrollDao.getTunjanganPeralihan(absensiPegawaiEntity.getNip(),bulan,tahun);
                for (ItPayrollEntity itPayrollEntity : payrollEntityList){
                    peralihan=itPayrollEntity.getTunjanganPeralihan().doubleValue();
                }
                upahLembur = (upahLembur+umk+peralihan)*faktor*jamLembur;

                absensiPegawaiEntity.setLembur("Y");
                absensiPegawaiEntity.setLamaLembur(8.00);
                absensiPegawaiEntity.setJamLembur(jamLembur);
                absensiPegawaiEntity.setBiayaLembur(upahLembur);
                absensiPegawaiEntity.setRealisasiJamLembur(8.00);
                absensiPegawaiEntity.setPengajuanLembur(8.00);
                String upahNew = "";
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setGroupingSeparator('.');

                kursIndonesia.setDecimalFormatSymbols(formatRp);
                upahNew = kursIndonesia.format(upahLembur);
                absensiPegawaiEntity.setBiayaLemburName(upahNew);
                sudahJatahLembur=true;
            }
            absensiPegawaiEntity.setTipeHari("hari_kerja");
            absensiPegawaiEntity.setTipeHariName("kerja");
            listOfResult.add(absensiPegawaiEntity);
        }

        return listOfResult;
    }
    @Override
    public List<AbsensiPegawai> getByCriteria(AbsensiPegawai searchBean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<AbsensiPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getAbsensiPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getAbsensiPegawaiId())) {
                hsCriteria.put("absensi_pegawai_id", searchBean.getAbsensiPegawaiId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getStatusAbsensi() != null && !"".equalsIgnoreCase(searchBean.getStatusAbsensi())) {
                hsCriteria.put("status_absensi", searchBean.getStatusAbsensi());
            }
            if (searchBean.getLembur() != null && !"".equalsIgnoreCase(searchBean.getLembur())) {
                hsCriteria.put("lembur", searchBean.getLembur());
            }
            if (searchBean.getIjin() != null && !"".equalsIgnoreCase(searchBean.getIjin())) {
                hsCriteria.put("ijin", searchBean.getIjin());
            }
            if (searchBean.getStatusAbsensi() != null && !"".equalsIgnoreCase(searchBean.getStatusAbsensi())) {
                hsCriteria.put("status_absensi", searchBean.getStatusAbsensi());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getTanggal() != null) {
                hsCriteria.put("tanggal", searchBean.getTanggal());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }

            hsCriteria.put("flag", "Y");


            List<AbsensiPegawaiEntity> itAbsensiPegawaiEntity = null;
            try {

                itAbsensiPegawaiEntity = absensiPegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itAbsensiPegawaiEntity != null) {
                AbsensiPegawai returnAbsensi;
                // Looping from dao to object and save in collection
                for (AbsensiPegawaiEntity absensiPegawaiEntity : itAbsensiPegawaiEntity) {
                    returnAbsensi = new AbsensiPegawai();
                    returnAbsensi.setClear(true);
                    returnAbsensi.setAbsensiPegawaiId(absensiPegawaiEntity.getAbsensiPegawaiId());
                    returnAbsensi.setNip(absensiPegawaiEntity.getNip());
                    returnAbsensi.setTanggal(absensiPegawaiEntity.getTanggal());
                    returnAbsensi.setStTanggal(CommonUtil.convertDateToString(absensiPegawaiEntity.getTanggal()));
                    returnAbsensi.setLembur(absensiPegawaiEntity.getLembur());
                    returnAbsensi.setJamMasuk(absensiPegawaiEntity.getJamMasuk());
                    returnAbsensi.setJamPulang(absensiPegawaiEntity.getJamKeluar());
                    returnAbsensi.setJamMasuk2(absensiPegawaiEntity.getJamMasuk2());
                    returnAbsensi.setJamPulang2(absensiPegawaiEntity.getJamPulang2());
                    returnAbsensi.setStatusAbsensi(absensiPegawaiEntity.getStatusAbsensi());
                    returnAbsensi.setStatusAbsensi2(absensiPegawaiEntity.getStatusAbsensi2());
                    returnAbsensi.setFlagCutiGantiHari(absensiPegawaiEntity.getFlagCutiGantiHari());
                    returnAbsensi.setIjin(absensiPegawaiEntity.getIjin());
                    returnAbsensi.setTipeHari(absensiPegawaiEntity.getTipeHari());
                    returnAbsensi.setRealisasiJamLembur(absensiPegawaiEntity.getRealisasiJamLembur());
                    returnAbsensi.setJamLembur(absensiPegawaiEntity.getJamLembur());
                    returnAbsensi.setLamaLembur(absensiPegawaiEntity.getLamaLembur());
                    returnAbsensi.setBranchId(absensiPegawaiEntity.getBranchId());
                    returnAbsensi.setBiayaLembur(absensiPegawaiEntity.getBiayaLembur());
                    returnAbsensi.setKeterangan(absensiPegawaiEntity.getKeterangan());
                    returnAbsensi.setFlagUangMakan(absensiPegawaiEntity.getFlagUangMakan());
                    returnAbsensi.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(absensiPegawaiEntity.getBiayaLembur()), "###,###"));

                    List<Biodata> biodataList = biodataDao.getBiodataByNip(absensiPegawaiEntity.getNip());
                    for (Biodata biodata : biodataList){
                        returnAbsensi.setJabatan(biodata.getPositionName());
                        returnAbsensi.setNama(biodata.getNamaPegawai());
                    }

                    if (absensiPegawaiEntity.getStatusAbsensi()==null){
                        absensiPegawaiEntity.setStatusAbsensi("");
                    }
                    returnAbsensi.setStatusName(CommonUtil.statusName(absensiPegawaiEntity.getStatusAbsensi()));
                    if (absensiPegawaiEntity.getStatusAbsensi2()!=null){
                        returnAbsensi.setStatusName2(CommonUtil.statusName(absensiPegawaiEntity.getStatusAbsensi2()));
                    }
                    switch (absensiPegawaiEntity.getStatusAbsensi()) {
                        case "00":
                            returnAbsensi.setClear(false);
                            returnAbsensi.setCekMangkir(true);
                            break;
                        case "02":
                            returnAbsensi.setClear(false);
                            break;
                        case "03":
                            returnAbsensi.setClear(false);
                            returnAbsensi.setCekMangkir(true);
                            break;
                    }

                    returnAbsensi.setAction(absensiPegawaiEntity.getAction());
                    returnAbsensi.setFlag(absensiPegawaiEntity.getFlag());
                    returnAbsensi.setCreatedWho(absensiPegawaiEntity.getCreatedWho());
                    returnAbsensi.setCreatedDate(absensiPegawaiEntity.getCreatedDate());
                    returnAbsensi.setLastUpdate(absensiPegawaiEntity.getLastUpdate());
                    returnAbsensi.setTelat(absensiPegawaiEntity.getTelat());

                    if  (!searchBean.isMobile()) {
                        if (("ADMIN").equalsIgnoreCase(CommonUtil.roleAsLogin())){
                            returnAbsensi.setCekAdmin(true);
                        }
                    }

                    listOfResult.add(returnAbsensi);
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<AbsensiPegawai> getByCriteriaForReport(AbsensiPegawai searchBean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<AbsensiPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getAbsensiPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getAbsensiPegawaiId())) {
                hsCriteria.put("absensi_pegawai_id", searchBean.getAbsensiPegawaiId());
            }
            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getStatusAbsensi() != null && !"".equalsIgnoreCase(searchBean.getStatusAbsensi())) {
                hsCriteria.put("status_absensi", searchBean.getStatusAbsensi());
            }
            if (searchBean.getLembur() != null && !"".equalsIgnoreCase(searchBean.getLembur())) {
                hsCriteria.put("lembur", searchBean.getLembur());
            }
            if (searchBean.getIjin() != null && !"".equalsIgnoreCase(searchBean.getIjin())) {
                hsCriteria.put("ijin", searchBean.getIjin());
            }
            if (searchBean.getTanggal() != null) {
                hsCriteria.put("tanggal", searchBean.getTanggal());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }

            hsCriteria.put("flag", "Y");


            List<AbsensiPegawaiEntity> itAbsensiPegawaiEntity = null;
            List<ImBiodataEntity> imBiodataEntityList = new ArrayList<>();
            List<ItPersonilPositionEntity> itPersonilPositionEntityList = new ArrayList<>();
            List<ImPosition> imPositionList = new ArrayList<>();
            List<ImBranches> imBranchesList = new ArrayList<>();

            try {
                itAbsensiPegawaiEntity = absensiPegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itAbsensiPegawaiEntity != null) {
                AbsensiPegawai returnAbsensi;
                // Looping from dao to object and save in collection
                int i=0;
                for (AbsensiPegawaiEntity absensiPegawaiEntity : itAbsensiPegawaiEntity) {
                    i++;
                    returnAbsensi = new AbsensiPegawai();
                    returnAbsensi.setAbsensiPegawaiId(absensiPegawaiEntity.getAbsensiPegawaiId());
                    returnAbsensi.setNip(absensiPegawaiEntity.getNip());
                    returnAbsensi.setTanggal(absensiPegawaiEntity.getTanggal());
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    returnAbsensi.setStTanggal(df.format(absensiPegawaiEntity.getTanggal()));
                    returnAbsensi.setStatusAbsensi(absensiPegawaiEntity.getStatusAbsensi());
                    returnAbsensi.setLembur(absensiPegawaiEntity.getLembur());
                    returnAbsensi.setJamMasuk(absensiPegawaiEntity.getJamMasuk());
                    returnAbsensi.setJamKeluar(absensiPegawaiEntity.getJamKeluar());
                    if (absensiPegawaiEntity.getLembur().equalsIgnoreCase("Y")) {
                        //Get Jam Kerja
                        List<ImHrisJamKerja> jamKerjaList = new ArrayList<>();
                        AbsensiPegawai searchJamAbsensi = new AbsensiPegawai();
                        if (searchJamAbsensi.getJamMasuk() != null && searchJamAbsensi.getJamKeluar() != null) {
                            returnAbsensi.setJamMasukDb(searchJamAbsensi.getJamMasuk());
                            returnAbsensi.setJamPulangDb(searchJamAbsensi.getJamKeluar());
                        } else {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(returnAbsensi.getTanggal());
                            int day = cal.get(Calendar.DAY_OF_WEEK);
                            System.out.println(day);
                            ItPersonilPositionEntity positionEntity = personilPositionDao.getById("nip", returnAbsensi.getNip(), "Y");
                            Map hsCriteria2 = new HashMap();
                            hsCriteria2.put("branch_id", absensiPegawaiEntity.getBranchId());
                            hsCriteria2.put("hari", day);
                            hsCriteria2.put("flag", "Y");
                            jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                            if (jamKerjaList.size() > 1) {
                                hsCriteria2 = new HashMap();
                                hsCriteria2.put("tipe_pegawai", returnAbsensi.getTipePegawai());
                                hsCriteria2.put("branch_id", positionEntity.getBranchId());
                                hsCriteria2.put("hari", day);
                                hsCriteria2.put("flag", "Y");
                                jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                                if (jamKerjaList.size() > 1) {
                                    hsCriteria2 = new HashMap();
                                    hsCriteria2.put("status_giling", returnAbsensi.getStatusGiling());
                                    hsCriteria2.put("tipe_pegawai", returnAbsensi.getTipePegawai());
                                    hsCriteria2.put("branch_id", positionEntity.getBranchId());
                                    hsCriteria2.put("hari", day);
                                    hsCriteria2.put("flag", "Y");
                                    jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                                }
                            }
                            for (ImHrisJamKerja jamKerja : jamKerjaList) {
                                returnAbsensi.setJamMasukDb(jamKerja.getJamAwalKerja());
                                returnAbsensi.setJamPulangDb(jamKerja.getJamAkhirKerja());
                            }
                        }
                        returnAbsensi.setJamPulang(returnAbsensi.getJamPulangDb());
                        returnAbsensi.setAwalLembur(returnAbsensi.getJamPulangDb());
                        returnAbsensi.setSelesaiLembur(returnAbsensi.getJamKeluar());

                    } else {
                        returnAbsensi.setAwalLembur("");
                        returnAbsensi.setSelesaiLembur("");
                        returnAbsensi.setJamPulang(returnAbsensi.getJamKeluar());
                    }
                    returnAbsensi.setIjin(absensiPegawaiEntity.getIjin());
                    if (("03").equalsIgnoreCase(absensiPegawaiEntity.getStatusAbsensi())){
                        if (absensiPegawaiEntity.getJamMasuk()==null){
                            returnAbsensi.setTidakAbsenMasuk("1");
                        }
                        else if (absensiPegawaiEntity.getJamKeluar()==null){
                            returnAbsensi.setTidakAbsenPulang("1");
                        }
                    }
                    if (("02").equalsIgnoreCase(absensiPegawaiEntity.getStatusAbsensi())){
                        List<ImHrisJamKerja> jamKerjaList = new ArrayList<>();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(returnAbsensi.getTanggal());
                        int day = cal.get(Calendar.DAY_OF_WEEK);
                        System.out.println(day);
                        ItPersonilPositionEntity positionEntity = personilPositionDao.getById("nip", returnAbsensi.getNip(), "Y");
                        Map hsCriteria2 = new HashMap();
                        hsCriteria2.put("branch_id", positionEntity.getBranchId());
                        hsCriteria2.put("hari", day);
                        hsCriteria2.put("flag", "Y");
                        jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                        if (jamKerjaList.size() > 1) {
                            hsCriteria2 = new HashMap();
                            hsCriteria2.put("tipe_pegawai", returnAbsensi.getTipePegawai());
                            hsCriteria2.put("branch_id", positionEntity.getBranchId());
                            hsCriteria2.put("hari", day);
                            hsCriteria2.put("flag", "Y");
                            jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                            if (jamKerjaList.size() > 1) {
                                hsCriteria2 = new HashMap();
                                hsCriteria2.put("status_giling", returnAbsensi.getStatusGiling());
                                hsCriteria2.put("tipe_pegawai", returnAbsensi.getTipePegawai());
                                hsCriteria2.put("branch_id", positionEntity.getBranchId());
                                hsCriteria2.put("hari", day);
                                hsCriteria2.put("flag", "Y");
                                jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                            }
                        }
                        for (ImHrisJamKerja jamKerja : jamKerjaList) {
                            returnAbsensi.setJamMasukDb(jamKerja.getJamAwalKerja());
                            returnAbsensi.setJamPulangDb(jamKerja.getJamAkhirKerja());
                        }
                        if (returnAbsensi.getJamMasuk()!=null){
                            int jamMasuk=Integer.parseInt(returnAbsensi.getJamMasuk().replace(":",""));
                            int jamMasukDB=Integer.parseInt(returnAbsensi.getJamMasukDb().replace(":",""));

                            if (jamMasuk-jamMasukDB<100){
                                returnAbsensi.setTerlambatKurang60("1");
                            }else{
                                returnAbsensi.setTerlambatLebih60("1");
                            }
                        }
                    }
                    returnAbsensi.setRealisasiJamLembur(absensiPegawaiEntity.getRealisasiJamLembur());
                    returnAbsensi.setJamLembur(absensiPegawaiEntity.getJamLembur());
                    returnAbsensi.setLamaLembur(absensiPegawaiEntity.getLamaLembur());
                    returnAbsensi.setBranchId(absensiPegawaiEntity.getBranchId());
                    returnAbsensi.setBiayaLembur(absensiPegawaiEntity.getBiayaLembur());
                    returnAbsensi.setStBiayaLembur(CommonUtil.numbericFormat(BigDecimal.valueOf(absensiPegawaiEntity.getBiayaLembur()), "###,###"));

                    Map hsCriteria4 = new HashMap();
                    hsCriteria4.put("branch_id", absensiPegawaiEntity.getBranchId());
                    hsCriteria4.put("flag", "Y");
                    imBranchesList = branchDao.getByCriteria(hsCriteria4);
                    for (ImBranches imBranches : imBranchesList) {
                        returnAbsensi.setUnit(imBranches.getBranchName());
                    }
                    returnAbsensi.setAction(absensiPegawaiEntity.getAction());
                    returnAbsensi.setFlag(absensiPegawaiEntity.getFlag());
                    returnAbsensi.setCreatedWho(absensiPegawaiEntity.getCreatedWho());
                    returnAbsensi.setCreatedDate(absensiPegawaiEntity.getCreatedDate());
                    returnAbsensi.setLastUpdate(absensiPegawaiEntity.getLastUpdate());
                    listOfResult.add(returnAbsensi);
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<AbsensiPegawai> getByCriteriaForReportUangMakan(AbsensiPegawai searchBean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<AbsensiPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();
            if (searchBean.getNip() != null) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            hsCriteria.put("flag", "Y");

            List<AbsensiPegawaiEntity> itAbsensiPegawaiEntity = absensiPegawaiDao.getByCriteriaForReportUangMakan(hsCriteria);

            if (itAbsensiPegawaiEntity != null) {
                AbsensiPegawai returnAbsensi;
                // Looping from dao to object and save in collection
                for (AbsensiPegawaiEntity absensiPegawaiEntity : itAbsensiPegawaiEntity) {
                    returnAbsensi = new AbsensiPegawai();
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Map hsCriteria2 = new HashMap();

                    returnAbsensi.setNip(absensiPegawaiEntity.getNip());
                    returnAbsensi.setTanggal(absensiPegawaiEntity.getTanggal());
                    returnAbsensi.setStTanggal(df.format(absensiPegawaiEntity.getTanggal()));
                    if (absensiPegawaiEntity.getJamMasuk() == null) {
                        returnAbsensi.setJamMasuk("-");
                    } else {
                        returnAbsensi.setJamMasuk(absensiPegawaiEntity.getJamMasuk());
                    }
                    if (absensiPegawaiEntity.getJamKeluar() == null) {
                        returnAbsensi.setJamKeluar("-");
                        returnAbsensi.setJamPulang("-");
                    } else {
                        returnAbsensi.setJamKeluar(absensiPegawaiEntity.getJamKeluar());
                        returnAbsensi.setJamPulang(absensiPegawaiEntity.getJamKeluar());
                    }

                    returnAbsensi.setApprovalFlag(absensiPegawaiEntity.getApprovalFlag());
                    returnAbsensi.setStatusAbsensi(absensiPegawaiEntity.getStatusAbsensi());
                    returnAbsensi.setFlagUangMakan(absensiPegawaiEntity.getFlagUangMakan());
                    returnAbsensi.setAction(absensiPegawaiEntity.getAction());
                    returnAbsensi.setFlag(absensiPegawaiEntity.getFlag());
                    returnAbsensi.setCreatedWho(absensiPegawaiEntity.getCreatedWho());
                    returnAbsensi.setCreatedDate(absensiPegawaiEntity.getCreatedDate());
                    returnAbsensi.setLastUpdate(absensiPegawaiEntity.getLastUpdate());
                    listOfResult.add(returnAbsensi);
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PegawaiTambahanAbsensi> getByCriteriaForReportUangMakanTambahan(PegawaiTambahanAbsensi searchBean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PegawaiTambahanAbsensi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();
            if (searchBean.getPin() != null) {
                hsCriteria.put("pin", searchBean.getPin());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            hsCriteria.put("flag", "Y");

            List<PegawaiTambahanAbsensiEntity> pegawaiTambahanAbsensiEntityList = pegawaiTambahanAbsensiDao.getByCriteriaForReportUangMakan(hsCriteria);

            if (pegawaiTambahanAbsensiEntityList != null) {
                PegawaiTambahanAbsensi returnAbsensi;
                // Looping from dao to object and save in collection
                for (PegawaiTambahanAbsensiEntity pegawaiTambahanAbsensiEntity : pegawaiTambahanAbsensiEntityList) {
                    returnAbsensi = new PegawaiTambahanAbsensi();
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                    returnAbsensi.setPin(pegawaiTambahanAbsensiEntity.getPin());
                    returnAbsensi.setTanggal(pegawaiTambahanAbsensiEntity.getTanggal());
                    returnAbsensi.setStTanggal(df.format(pegawaiTambahanAbsensiEntity.getTanggal()));
                    if (pegawaiTambahanAbsensiEntity.getJamMasuk() == null) {
                        returnAbsensi.setJamMasuk("-");
                    } else {
                        returnAbsensi.setJamMasuk(pegawaiTambahanAbsensiEntity.getJamMasuk());
                    }
                    if (pegawaiTambahanAbsensiEntity.getJamPulang() == null) {
                        returnAbsensi.setJamPulang("-");
                    } else {
                        returnAbsensi.setJamPulang(pegawaiTambahanAbsensiEntity.getJamPulang());
                    }

                    returnAbsensi.setStatusAbsensi(pegawaiTambahanAbsensiEntity.getStatusAbsensi());
                    returnAbsensi.setAction(pegawaiTambahanAbsensiEntity.getAction());
                    returnAbsensi.setFlag(pegawaiTambahanAbsensiEntity.getFlag());
                    returnAbsensi.setCreatedWho(pegawaiTambahanAbsensiEntity.getCreatedWho());
                    returnAbsensi.setCreatedDate(pegawaiTambahanAbsensiEntity.getCreatedDate());
                    returnAbsensi.setLastUpdate(pegawaiTambahanAbsensiEntity.getLastUpdate());
                    listOfResult.add(returnAbsensi);
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<AbsensiPegawai> getByCriteriaForLembur(AbsensiPegawai searchBean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<AbsensiPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getNip() != null) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getTanggalAwal() != null) {
                hsCriteria.put("tanggal_awal", searchBean.getTanggalAwal());
            }
            if (searchBean.getTanggalAkhir() != null) {
                hsCriteria.put("tanggal_akhir", searchBean.getTanggalAkhir());
            }
            if (searchBean.getFlag() != null) {
                hsCriteria.put("flag", searchBean.getFlag());
            }

            List<AbsensiPegawaiEntity> itAbsensiPegawaiEntity = null;
            List <StrukturJabatan> strukturJabatanList ;
            List <StrukturJabatan> strukturJabatanList2 ;
            List <ImBiodataEntity> imBiodataEntities;

            try {
                strukturJabatanList = getPerBagianSys();
                strukturJabatanList2 = getPerBagianSisa();
                strukturJabatanList.addAll(strukturJabatanList2);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaForLembur] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            try {
                itAbsensiPegawaiEntity = absensiPegawaiDao.getByCriteriaForReportLembur(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaForLembur] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itAbsensiPegawaiEntity != null) {
                AbsensiPegawai returnAbsensi;
                // Looping from dao to object and save in collection
                for (AbsensiPegawaiEntity absensiPegawaiEntity : itAbsensiPegawaiEntity) {
                    returnAbsensi = new AbsensiPegawai();
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Map hsCriteria2 = new HashMap();

                    returnAbsensi.setNip(absensiPegawaiEntity.getNip());
                    returnAbsensi.setBranchId(absensiPegawaiEntity.getBranchId());
                    returnAbsensi.setTanggal(absensiPegawaiEntity.getTanggal());
                    returnAbsensi.setStTanggal(df.format(absensiPegawaiEntity.getTanggal()));

                    hsCriteria2.put("nip", absensiPegawaiEntity.getNip());
                    hsCriteria2.put("flag", "Y");
                    imBiodataEntities = biodataDao.getByCriteria(hsCriteria2);
                    for (ImBiodataEntity imBiodataEntity : imBiodataEntities) {
                        returnAbsensi.setNama(imBiodataEntity.getNamaPegawai());
                    }

                    for (StrukturJabatan strukturJabatan:strukturJabatanList){
                        if (returnAbsensi.getNip().equalsIgnoreCase(strukturJabatan.getNip())){
                            returnAbsensi.setBagian(strukturJabatan.getBagian());
                            returnAbsensi.setNoUrutBagian(strukturJabatan.getNoUrutBagian());
                        }
                    }

                    //keterangan lembur
                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (absensiPegawaiEntity.getTanggal() != null){
                        Lembur lembur = new Lembur();
                        LemburBo lemburBo = (LemburBo) context.getBean("lemburBoProxy");
//                        lembur.setTanggalAwal(absensiPegawaiEntity.getTanggal());
                        lembur.setStTanggalAwal(CommonUtil.convertDateToString(absensiPegawaiEntity.getTanggal()));
                        lembur.setStTanggalAkhir(CommonUtil.convertDateToString(absensiPegawaiEntity.getTanggal()));
                        lembur.setNip(absensiPegawaiEntity.getNip());
                        List<Lembur> lemburs = lemburBo.getByCriteria(lembur);
                        String keterangan = lemburs.get(0).getKeterangan();
                        returnAbsensi.setKeterangan(keterangan);
                    }


                    returnAbsensi.setBiayaLembur(absensiPegawaiEntity.getBiayaLembur());
                    returnAbsensi.setTipeHari(absensiPegawaiEntity.getTipeHari());
                    returnAbsensi.setLamaLembur(absensiPegawaiEntity.getLamaLembur());
                    returnAbsensi.setJamLembur(absensiPegawaiEntity.getJamLembur());
                    returnAbsensi.setRealisasiJamLembur(absensiPegawaiEntity.getRealisasiJamLembur());
                    returnAbsensi.setApprovalFlag(absensiPegawaiEntity.getApprovalFlag());
                    returnAbsensi.setStatusAbsensi(absensiPegawaiEntity.getStatusAbsensi());
                    returnAbsensi.setFlagUangMakan(absensiPegawaiEntity.getFlagUangMakan());
                    returnAbsensi.setAction(absensiPegawaiEntity.getAction());
                    returnAbsensi.setFlag(absensiPegawaiEntity.getFlag());
                    returnAbsensi.setCreatedWho(absensiPegawaiEntity.getCreatedWho());
                    returnAbsensi.setCreatedDate(absensiPegawaiEntity.getCreatedDate());
                    returnAbsensi.setLastUpdate(absensiPegawaiEntity.getLastUpdate());

                    listOfResult.add(returnAbsensi);
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteriaForLembur] end process <<<");

        return listOfResult;
    }

    @Override
    public String refreshDataAbsensi(String nip, Date tanggal){
        logger.info("[AbsensiBoImpl.refreshDataAbsensi] start process >>>");
        String status="";
        List<AbsensiPegawaiEntity> absensiPegawaiEntityList = new ArrayList<>();

        //cari status tidak masuk
        String statusTidakMasuk = cariStatusTidakMasuk(nip,tanggal);

        if (!("00").equalsIgnoreCase(statusTidakMasuk)){
            absensiPegawaiEntityList=absensiPegawaiDao.searchExistingAbsensi(nip,tanggal);

            for (AbsensiPegawaiEntity hasil : absensiPegawaiEntityList){
                if (("03").equalsIgnoreCase(hasil.getStatusAbsensi())){
                    if (!("00").equalsIgnoreCase(statusTidakMasuk)||!("08").equalsIgnoreCase(statusTidakMasuk)){
                        hasil.setStatusAbsensi(statusTidakMasuk);
                        absensiPegawaiDao.updateAndSave(hasil);
                        status="update berhasil";
                    }
                }else{
                    hasil.setStatusAbsensi(statusTidakMasuk);
                    absensiPegawaiDao.updateAndSave(hasil);
                    status="update berhasil";
                }
            }
        }else{
            status="tidak ada perubahan";
        }

        logger.info("[AbsensiBoImpl.refreshDataAbsensi] end process <<<");
        return status;
    }

    @Override
    public List<AbsensiPegawai> getByCriteriaForRekapLembur(AbsensiPegawai searchBean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteriaForRekapLembur] start process >>>");

        // Mapping with collection and put
        List<AbsensiPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getNip() != null) {
                if (!("").equalsIgnoreCase(searchBean.getNip())){
                    hsCriteria.put("nip", searchBean.getNip());
                }
            }
            if (searchBean.getTanggalAwal() != null) {
                hsCriteria.put("tanggal_awal", searchBean.getTanggalAwal());
            }
            if (searchBean.getTanggalAkhir() != null) {
                hsCriteria.put("tanggal_akhir", searchBean.getTanggalAkhir());
            }
            if (searchBean.getFlag() != null) {
                if (!("").equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", searchBean.getFlag());

                }
            }
            if (searchBean.getLembur() != null) {
                if (!("").equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("lembur", searchBean.getLembur());
                }
            }

            List<AbsensiPegawaiEntity> itAbsensiPegawaiEntity = null;
            List <ImBiodataEntity> imBiodataEntities;

            try {
                itAbsensiPegawaiEntity = absensiPegawaiDao.getByCriteriaForRekapLembur(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaForRekapLembur] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itAbsensiPegawaiEntity != null) {
                AbsensiPegawai returnAbsensi;
                // Looping from dao to object and save in collection
                for (AbsensiPegawaiEntity absensiPegawaiEntity : itAbsensiPegawaiEntity) {
                    returnAbsensi = new AbsensiPegawai();
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Map hsCriteria2 = new HashMap();

                    returnAbsensi.setNip(absensiPegawaiEntity.getNip());
                    returnAbsensi.setTanggal(absensiPegawaiEntity.getTanggal());
                    returnAbsensi.setStTanggal(df.format(absensiPegawaiEntity.getTanggal()));

                    hsCriteria2.put("nip", absensiPegawaiEntity.getNip());
                    hsCriteria2.put("flag", "Y");
                    imBiodataEntities = biodataDao.getByCriteria(hsCriteria2);
                    for (ImBiodataEntity imBiodataEntity : imBiodataEntities) {
                        returnAbsensi.setNama(imBiodataEntity.getNamaPegawai());
                        break;
                    }
                    returnAbsensi.setAbsensiPegawaiId(absensiPegawaiEntity.getAbsensiPegawaiId());
                    returnAbsensi.setBiayaLembur(absensiPegawaiEntity.getBiayaLembur());
                    returnAbsensi.setTipeHari(absensiPegawaiEntity.getTipeHari());
                    returnAbsensi.setLamaLembur(absensiPegawaiEntity.getLamaLembur());
                    returnAbsensi.setJamLembur(absensiPegawaiEntity.getJamLembur());
                    returnAbsensi.setRealisasiJamLembur(absensiPegawaiEntity.getRealisasiJamLembur());
                    returnAbsensi.setApprovalFlag(absensiPegawaiEntity.getApprovalFlag());
                    returnAbsensi.setStatusAbsensi(absensiPegawaiEntity.getStatusAbsensi());
                    returnAbsensi.setFlagUangMakan(absensiPegawaiEntity.getFlagUangMakan());
                    returnAbsensi.setAction(absensiPegawaiEntity.getAction());
                    returnAbsensi.setFlag(absensiPegawaiEntity.getFlag());
                    returnAbsensi.setCreatedWho(absensiPegawaiEntity.getCreatedWho());
                    returnAbsensi.setCreatedDate(absensiPegawaiEntity.getCreatedDate());
                    returnAbsensi.setLastUpdate(absensiPegawaiEntity.getLastUpdate());

                    listOfResult.add(returnAbsensi);
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteriaForLembur] end process <<<");

        return listOfResult;
    }
    @Override
    public List<MesinAbsensi> getByCriteriaMesin(MesinAbsensi searchBean) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteriaMesin] start process >>>");

        // Mapping with collection and put
        List<MesinAbsensi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getAbsensiId() != null && !"".equalsIgnoreCase(searchBean.getAbsensiId())) {
                hsCriteria.put("absensi_id", searchBean.getAbsensiId());
            }
            if (searchBean.getPin() != null && !"".equalsIgnoreCase(searchBean.getPin())) {
                hsCriteria.put("pin", searchBean.getPin());
            }
            if (searchBean.getJamMasuk() != null && !"".equalsIgnoreCase(searchBean.getJamMasuk())) {
                hsCriteria.put("jam_masuk", searchBean.getJamMasuk());
            }
            if (searchBean.getJamKeluar() != null && !"".equalsIgnoreCase(searchBean.getJamKeluar())) {
                hsCriteria.put("jam_keluar", searchBean.getJamKeluar());
            }
            if (searchBean.getStatus() != null && !"".equalsIgnoreCase(searchBean.getStatus())) {
                hsCriteria.put("status", searchBean.getStatus());
            }
            if (searchBean.getFlagAbsensi() != null && !"".equalsIgnoreCase(searchBean.getFlagAbsensi())) {
                hsCriteria.put("flag_absensi", searchBean.getFlagAbsensi());
            }
            if (searchBean.getTanggal() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getTanggal()))) {
                Date tanggal = CommonUtil.convertToDate(String.valueOf(searchBean.getTanggal()));
                hsCriteria.put("tanggal", tanggal);
            }
            hsCriteria.put("flag", "Y");


            List<MesinAbsensiEntity> itMesinAbsensiEntity = null;
            try {

                itMesinAbsensiEntity = mesinAbsensiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itMesinAbsensiEntity != null) {
                MesinAbsensi returnMesinAbsensi;
                // Looping from dao to object and save in collection
                for (MesinAbsensiEntity mesinAbsensiEntity : itMesinAbsensiEntity) {
                    returnMesinAbsensi = new MesinAbsensi();
                    returnMesinAbsensi.setPin(mesinAbsensiEntity.getPin());
                    returnMesinAbsensi.setAbsensiId(mesinAbsensiEntity.getAbsensiId());
                    returnMesinAbsensi.setTanggal(mesinAbsensiEntity.getTanggal());
                    returnMesinAbsensi.setJamMasuk(mesinAbsensiEntity.getJamMasuk());
                    returnMesinAbsensi.setJamKeluar(mesinAbsensiEntity.getJamKeluar());
                    returnMesinAbsensi.setFlagAbsensi(mesinAbsensiEntity.getFlagAbsensi());
                    returnMesinAbsensi.setStatus(mesinAbsensiEntity.getStatus());

                    returnMesinAbsensi.setAction(mesinAbsensiEntity.getAction());
                    returnMesinAbsensi.setFlag(mesinAbsensiEntity.getFlag());
                    returnMesinAbsensi.setCreatedWho(mesinAbsensiEntity.getCreatedWho());
                    returnMesinAbsensi.setCreatedDate(mesinAbsensiEntity.getCreatedDate());
                    returnMesinAbsensi.setLastUpdate(mesinAbsensiEntity.getLastUpdate());

                    listOfResult.add(returnMesinAbsensi);
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteriaMesin] end process <<<");

        return listOfResult;
    }

    @Override
    public List<AbsensiPegawai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public List getDataFromMesin(MesinAbsensi bean) throws Exception {
        Map hsCriteriaMesin = new HashMap();
        hsCriteriaMesin.put("flag", "Y");
        List<ImMesinAbsensiEntity> imMesinAbsensiEntityList = new ArrayList<>();
        imMesinAbsensiEntityList = mesinDao.getByCriteria(hsCriteriaMesin);
        for (ImMesinAbsensiEntity mesin : imMesinAbsensiEntityList) {
            String urlParameters = mesin.getMesinSn();
            String url = "http://"+mesin.getMesinName()+"/scanlog/all/paging";

            // CODING ASLI //
            String inputLine;
            StringBuffer response = new StringBuffer();
            List<MesinAbsensiDetail> mesinAbsensiDetailList = new ArrayList<>();
            byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int postDataLength = postData.length;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("POST");
            con.setDoOutput( true );
            con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            //add request header
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
                wr.write( postData );
            }
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer responseTemp = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                responseTemp.append(inputLine);
            }
            in.close();
            //GET DATA
            JSONObject myResponseCheck = new JSONObject(responseTemp.toString());
            boolean adaData = myResponseCheck.getBoolean("Result");
            if (adaData){
                JSONArray ja_data = myResponseCheck.getJSONArray("Data");
                int length = ja_data.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jObj = ja_data.getJSONObject(i);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
                    String stScanDate = jObj.getString("ScanDate");
                    java.util.Date parsedDate = dateFormat.parse(stScanDate);
                    Timestamp scanDate = new Timestamp(parsedDate.getTime());

                    MesinAbsensiDetail mesinAbsensiDetail = new MesinAbsensiDetail();
                    mesinAbsensiDetail.setPin(jObj.getString("PIN"));
                    mesinAbsensiDetail.setStatus(String.valueOf(jObj.getInt("IOMode")));
                    mesinAbsensiDetail.setScanDate(scanDate);
                    mesinAbsensiDetail.setVerifyMode(String.valueOf(jObj.getInt("VerifyMode")));
                    mesinAbsensiDetail.setWorkCode(String.valueOf(jObj.getInt("WorkCode")));
                    mesinAbsensiDetailList.add(mesinAbsensiDetail);
                }
                //Save To mesin Absensi Detail List
                for (MesinAbsensiDetail mesinAbsensiDetail : mesinAbsensiDetailList) {
                    MesinAbsensiDetailEntity mesinAbsensiDetailEntity = new MesinAbsensiDetailEntity();
                    String mesinAbsensiDetailId = mesinAbsensiDetailDao.getNextMesinAbsensiDetailId();
                    mesinAbsensiDetailEntity.setMesinAbsensiDetailId(mesinAbsensiDetailId);
                    mesinAbsensiDetailEntity.setVerifyMode(mesinAbsensiDetail.getVerifyMode());
                    mesinAbsensiDetailEntity.setScanDate(mesinAbsensiDetail.getScanDate());
                    mesinAbsensiDetailEntity.setStatus(mesinAbsensiDetail.getStatus());
                    mesinAbsensiDetailEntity.setPin(mesinAbsensiDetail.getPin());
                    mesinAbsensiDetailEntity.setWorkCode(mesinAbsensiDetail.getWorkCode());

                    String userLogin = bean.getCreatedWho();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                    mesinAbsensiDetailEntity.setAction("C");
                    mesinAbsensiDetailEntity.setFlag("Y");
                    mesinAbsensiDetailEntity.setLastUpdate(updateTime);
                    mesinAbsensiDetailEntity.setCreatedDate(updateTime);
                    mesinAbsensiDetailEntity.setLastUpdateWho(userLogin);
                    mesinAbsensiDetailEntity.setCreatedWho(userLogin);

                    mesinAbsensiDetailDao.addAndSave(mesinAbsensiDetailEntity);
                }
            }

            //jika berhasil maka akan ditaruh di last get
            ImMesinAbsensiEntity mesinAbsensiEntity = mesinDao.getById("mesinId",mesin.getMesinId());
            mesinAbsensiEntity.setLastGet(bean.getLastUpdate());
            mesinDao.updateAndSave(mesinAbsensiEntity);
        }
        // END OF CODING ASLI //

        /*//PERCOBAAN DATA DUMMY
        List<MesinAbsensiDetail> mesinAbsensiDetailList = new ArrayList<>();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        //add request header
        con.setRequestProperty("Content-Type", "application/json");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());

        boolean adaData = myResponse.getBoolean("Result");
        if (adaData){
            JSONArray ja_data = myResponse.getJSONArray("Data");
            int length = ja_data.length();
            for (int i = 0; i < length; i++) {
                JSONObject jObj = ja_data.getJSONObject(i);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
                String stScanDate = jObj.getString("ScanDate");
                java.util.Date parsedDate = dateFormat.parse(stScanDate);
                Timestamp scanDate = new Timestamp(parsedDate.getTime());

                MesinAbsensiDetail mesinAbsensiDetail = new MesinAbsensiDetail();
                mesinAbsensiDetail.setPin(jObj.getString("PIN"));
                mesinAbsensiDetail.setStatus(String.valueOf(jObj.getInt("IOMode")));
                mesinAbsensiDetail.setScanDate(scanDate);
                mesinAbsensiDetail.setVerifyMode(String.valueOf(jObj.getInt("VerifyMode")));
                mesinAbsensiDetail.setWorkCode(String.valueOf(jObj.getInt("WorkCode")));
                mesinAbsensiDetailList.add(mesinAbsensiDetail);
            }
            //Save To mesin Absensi Detail List
            for (MesinAbsensiDetail mesinAbsensiDetail : mesinAbsensiDetailList) {
                MesinAbsensiDetailEntity mesinAbsensiDetailEntity = new MesinAbsensiDetailEntity();
                String mesinAbsensiDetailId = mesinAbsensiDetailDao.getNextMesinAbsensiDetailId();
                mesinAbsensiDetailEntity.setMesinAbsensiDetailId(mesinAbsensiDetailId);
                mesinAbsensiDetailEntity.setVerifyMode(mesinAbsensiDetail.getVerifyMode());
                mesinAbsensiDetailEntity.setScanDate(mesinAbsensiDetail.getScanDate());
                mesinAbsensiDetailEntity.setStatus(mesinAbsensiDetail.getStatus());
                mesinAbsensiDetailEntity.setPin(mesinAbsensiDetail.getPin());
                mesinAbsensiDetailEntity.setWorkCode(mesinAbsensiDetail.getWorkCode());

                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                mesinAbsensiDetailEntity.setAction("C");
                mesinAbsensiDetailEntity.setFlag("Y");
                mesinAbsensiDetailEntity.setLastUpdate(updateTime);
                mesinAbsensiDetailEntity.setCreatedDate(updateTime);
                mesinAbsensiDetailEntity.setLastUpdateWho(userLogin);
                mesinAbsensiDetailEntity.setCreatedWho(userLogin);

                mesinAbsensiDetailDao.addAndSave(mesinAbsensiDetailEntity);
            }
        }

        // END OF PERCOBAAN DATA DUMMY*/
        return null;
    }

    @Override
    public List getAllDataFromMesin(MesinAbsensi bean) throws Exception {

        Map hsCriteriaMesin = new HashMap();
        hsCriteriaMesin.put("flag", "Y");

        List<ImMesinAbsensiEntity> imMesinAbsensiEntityList = new ArrayList<>();
        imMesinAbsensiEntityList = mesinDao.getByCriteria(hsCriteriaMesin);
        for (ImMesinAbsensiEntity mesin : imMesinAbsensiEntityList) {
            String urlParameters = "sn="+mesin.getMesinSn();
            String url = "http://"+mesin.getMesinName()+"/scanlog/all/paging";

            // CODING ASLI //
            boolean isSession;
            String inputLine;
            StringBuffer response = new StringBuffer();
            List<MesinAbsensiDetail> mesinAbsensiDetailList = new ArrayList<>();
            do {
                byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
                int postDataLength = postData.length;
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                // optional default is GET
                con.setRequestMethod("POST");
                con.setDoOutput( true );
                con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                //add request header
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
                    wr.write( postData );
                }
                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer responseTemp = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    responseTemp.append(inputLine);
                }
                in.close();
                JSONObject myResponseCheck = new JSONObject(responseTemp.toString());
                isSession = myResponseCheck.getBoolean("IsSession");
                JSONArray ja_data = myResponseCheck.getJSONArray("Data");
                int length = ja_data.length();
                for(int i=0; i<length; i++) {
                    JSONObject jObj = ja_data.getJSONObject(i);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
                    String stScanDate = jObj.getString("ScanDate");
                    java.util.Date parsedDate = dateFormat.parse(stScanDate);
                    Timestamp scanDate = new Timestamp(parsedDate.getTime());

                    MesinAbsensiDetail mesinAbsensiDetail = new MesinAbsensiDetail();
                    mesinAbsensiDetail.setPin(jObj.getString("PIN"));
                    mesinAbsensiDetail.setStatus(String.valueOf(jObj.getInt("IOMode")));
                    mesinAbsensiDetail.setScanDate(scanDate);
                    mesinAbsensiDetail.setVerifyMode(String.valueOf(jObj.getInt("VerifyMode")));
                    mesinAbsensiDetail.setWorkCode(String.valueOf(jObj.getInt("WorkCode")));
                    mesinAbsensiDetailList.add(mesinAbsensiDetail);
                }
                response.append(responseTemp);
            }
            while (isSession);

            //Save To mesin Absensi Detail List
            for (MesinAbsensiDetail mesinAbsensiDetail : mesinAbsensiDetailList) {
                List<MesinAbsensiDetailEntity> mesinAbsensiDetailEntityList = new ArrayList<>();
                Map hsCriteria = new HashMap();
                hsCriteria.put("pin", mesinAbsensiDetail.getPin());
                hsCriteria.put("status", mesinAbsensiDetail.getStatus());
                hsCriteria.put("scan_date", mesinAbsensiDetail.getScanDate());
                hsCriteria.put("verify_mode", mesinAbsensiDetail.getVerifyMode());
                hsCriteria.put("work_code", mesinAbsensiDetail.getWorkCode());
                hsCriteria.put("flag", "Y");
                mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getByCriteria(hsCriteria);
                if (mesinAbsensiDetailEntityList.size() == 0) {
                    MesinAbsensiDetailEntity mesinAbsensiDetailEntity = new MesinAbsensiDetailEntity();
                    String mesinAbsensiDetailId = mesinAbsensiDetailDao.getNextMesinAbsensiDetailId();
                    String userLogin = bean.getCreatedWho();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                    mesinAbsensiDetailEntity.setMesinAbsensiDetailId(mesinAbsensiDetailId);
                    mesinAbsensiDetailEntity.setPin(mesinAbsensiDetail.getPin());
                    mesinAbsensiDetailEntity.setStatus(mesinAbsensiDetail.getStatus());
                    mesinAbsensiDetailEntity.setScanDate(mesinAbsensiDetail.getScanDate());
                    mesinAbsensiDetailEntity.setVerifyMode(mesinAbsensiDetail.getVerifyMode());
                    mesinAbsensiDetailEntity.setWorkCode(mesinAbsensiDetail.getWorkCode());
                    mesinAbsensiDetailEntity.setBranchId(mesin.getBranchId());
                    mesinAbsensiDetailEntity.setAction("C");
                    mesinAbsensiDetailEntity.setFlag("Y");
                    mesinAbsensiDetailEntity.setLastUpdate(updateTime);
                    mesinAbsensiDetailEntity.setCreatedDate(updateTime);
                    mesinAbsensiDetailEntity.setLastUpdateWho(userLogin);
                    mesinAbsensiDetailEntity.setCreatedWho(userLogin);
                    mesinAbsensiDetailDao.addAndSave(mesinAbsensiDetailEntity);
                }
            }
            //jika berhasil maka akan ditaruh di last get
            ImMesinAbsensiEntity mesinAbsensiEntity = mesinDao.getById("mesinId",mesin.getMesinId());
            mesinAbsensiEntity.setLastGet(bean.getLastUpdate());
            mesinDao.updateAndSave(mesinAbsensiEntity);
        }

        // END OF CODING ASLI //
        return null;
    }

    public void getDataInquiryForCronJob() throws Exception{
        MesinAbsensi mesinAbsensi = new MesinAbsensi();
        mesinAbsensi.setCreatedWho("Cron");
        getDataFromMesin(mesinAbsensi);

        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal = df.format(date);
        List<MesinAbsensi> getDataInquiryForCronJob = new ArrayList<>();

        try {
            getDataInquiryForCronJob = inquiryForDashboard(formatter.format(date).toString());
            //getDataInquiryForCronJob = inquiryForDashboard("29-07-2019");
            if(getDataInquiryForCronJob.size() > 0){
                for(MesinAbsensi mesinLoop: getDataInquiryForCronJob){
                    AbsensiDashboardEntity absensiDashboardEntity = new AbsensiDashboardEntity();
                    absensiDashboardEntity.setAbsensiDashboardId(absensiDashboardDao.getNextAbsensiDashboard());
                    absensiDashboardEntity.setPin(mesinLoop.getPin());
                    absensiDashboardEntity.setNip(mesinLoop.getNip());
                    absensiDashboardEntity.setStatus(mesinLoop.getStatus());
                    absensiDashboardEntity.setStatusName(mesinLoop.getStatusName());
                    absensiDashboardEntity.setTanggal(mesinLoop.getTanggal());
                    absensiDashboardEntity.setJamMasuk(mesinLoop.getJamMasuk());

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                    absensiDashboardEntity.setAction("C");
                    absensiDashboardEntity.setFlag("Y");
                    absensiDashboardEntity.setLastUpdate(updateTime);
                    absensiDashboardEntity.setCreatedDate(updateTime);
                    absensiDashboardEntity.setLastUpdateWho(userLogin);
                    absensiDashboardEntity.setCreatedWho(userLogin);
                    absensiDashboardDao.addAndSave(absensiDashboardEntity);
                }
            }

        } catch (GeneralBOException e) {
            logger.error("[biodataDao.getAbsensiPerson] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please info to your admin..." + e.getMessage());
        }

    }

    // untuk absensi Dashboard
    private List<MesinAbsensi> inquiryForDashboard(String tanggal){
        return null;
    }

    @Override
    public List<MesinAbsensi> inquiry(String tanggal, Boolean awalTanggal, String statusPegawai, String branchId) throws Exception {
        List<ImBiodataEntity> pegawaiList = new ArrayList<>();
        List<MesinAbsensi> absensiFinal = new ArrayList<>();
        List<ImHrisJamKerja> jamKerjaList = new ArrayList<>();
        String jamMasukDB = null,jamPulangDB=null,jamIstirahatAwalDB,jamIstirahatAkhirDB,libur ="N",liburShift="N",daftarTidakInquiry="N",ijinKeluar = null,ijinKembali=null;
        int iJamMasukDB= 0,iJamPulangDB = 0,iJamIstirahatAwalDB= 0,iJamIstirahatAkhirDB= 0;

        try {
            pegawaiList = biodataDao.getDataBiodata("", "", branchId, "", null, "", "Y");
        } catch (HibernateException e) {
            logger.error("[biodataDao.getAbsensiPerson] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please info to your admin..." + e.getMessage());
        }

        java.sql.Date tanggalAwal = CommonUtil.convertToDate(tanggal);
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(tanggalAwal);
        cal2.setTime(tanggalAwal);

        cal.add(Calendar.DAY_OF_YEAR,1);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        cal2.set(Calendar.HOUR_OF_DAY, 3);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        java.sql.Date tanggalBesok = new java.sql.Date(cal.getTimeInMillis());
        tanggalAwal = new java.sql.Date(cal2.getTimeInMillis());
        java.sql.Timestamp tsTanggalAwal = new java.sql.Timestamp(tanggalAwal.getTime());
        java.sql.Timestamp tsTanggalBesok = new java.sql.Timestamp(tanggalBesok.getTime());
        cal = Calendar.getInstance();
        String branch = branchId;
        cal.setTime(tanggalAwal);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        Map hsCriteria2 = new HashMap();
        hsCriteria2.put("branch_id",branch);
        hsCriteria2.put("hari",day);
        hsCriteria2.put("flag","Y");
        jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
        for (ImHrisJamKerja jamKerja : jamKerjaList){
            jamMasukDB=jamKerja.getJamAwalKerja();
            jamPulangDB=jamKerja.getJamAkhirKerja();
            jamIstirahatAwalDB=jamKerja.getIstirahatAwal();
            jamIstirahatAkhirDB=jamKerja.getIstirahatAkhir();
            iJamMasukDB=Integer.parseInt(jamMasukDB.replace(":",""));
            iJamPulangDB=Integer.parseInt(jamPulangDB.replace(":",""));
            iJamIstirahatAwalDB=Integer.parseInt(jamIstirahatAwalDB.replace(":",""));
            iJamIstirahatAkhirDB=Integer.parseInt(jamIstirahatAkhirDB.replace(":",""));
        }



        Date tanggalLibur = CommonUtil.convertToDate(tanggal);
        List<ImLiburEntity> liburEntityList = liburDao.getListLibur(tanggalLibur);
        //cari hari libur
        if ( liburEntityList.size()!=0) {
            libur = "Y";
        }

        for (ImBiodataEntity pegawai : pegawaiList){
            //cek apakah sudah masuk ke tabel absensi
            List<AbsensiPegawaiEntity> absensiPegawaiEntityList = new ArrayList<>();
            try {
                absensiPegawaiEntityList = absensiPegawaiDao.searchExistingAbsensi(pegawai.getNip(),tanggalAwal);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (absensiPegawaiEntityList.size()==0){
                if (!("Y").equalsIgnoreCase(daftarTidakInquiry)){
                    MesinAbsensi hasilInquiry = new MesinAbsensi();
                    hasilInquiry.setNip(pegawai.getNip());
                    hasilInquiry.setTanggal(tanggalAwal);
                    hasilInquiry.setStTanggal(CommonUtil.convertDateToString(tanggalAwal));
                    hasilInquiry.setPin(pegawai.getPin());
                    hasilInquiry.setNama(pegawai.getNamaPegawai());
                    hasilInquiry.setJamMasukDb(jamMasukDB);
                    hasilInquiry.setJamPulangDb(jamPulangDB);
                    if (!("Y").equalsIgnoreCase(pegawai.getShift())){
                        if (day==1||day==7){
                            libur="Y";
                        }
                        // get list from database detail
                        List<MesinAbsensiDetailEntity> mesinAbsensiDetailEntityList = new ArrayList<>();
                        mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getAllDetailWithDateAndPin(pegawai.getPin(),tsTanggalAwal,tsTanggalBesok,branchId);
                        if (mesinAbsensiDetailEntityList.size()!=0){
                            int iJamMasukTmp=9999,iJamKeluarTmp=0,i=1,iJamLibur=0;
                            for (MesinAbsensiDetailEntity mesin : mesinAbsensiDetailEntityList){
                                if (!("Y").equalsIgnoreCase(libur)){
                                    //Jika hari kerja
                                    String jam = String.valueOf(mesin.getScanDate()).substring(11,16);
                                    int jamAsli =Integer.parseInt(jam.replace(":",""));
                                    if (jamAsli<iJamPulangDB-400&&jamAsli>300){
                                        if (jamAsli<iJamMasukTmp){
                                            hasilInquiry.setJamMasuk(jam);
                                            iJamMasukTmp=jamAsli;
                                        }
                                    }else if (jamAsli>iJamPulangDB-400||jamAsli<300&&jamAsli>0){
                                        if (jamAsli>iJamKeluarTmp){
                                            hasilInquiry.setJamKeluar(jam);
                                            iJamMasukTmp=jamAsli;
                                        }
                                    }
                                }else{
                                    //jika hari libur
                                    String jam = String.valueOf(mesin.getScanDate()).substring(11,16);
                                    int jamAsli =Integer.parseInt(jam.replace(":",""));
                                    if (i==1){
                                        hasilInquiry.setJamMasuk(jam);
                                        i++;
                                    }else{
                                        if (iJamLibur<jamAsli){
                                            hasilInquiry.setJamKeluar(jam);
                                            i++;
                                        }
                                    }
                                }
                            }
                            ijinKeluar = null;
                            ijinKembali=null;
                            List<IjinKeluarEntity> ijinKeluarEntityList = ijinKeluarDao.getListPersonalFromNip(hasilInquiry.getNip(),tanggalAwal);
                            for (IjinKeluarEntity ijinKeluarEntity:ijinKeluarEntityList){
                                ijinKeluar = ijinKeluarEntity.getJamKeluar();
                                ijinKembali = ijinKeluarEntity.getJamKembali();
                            }
                            List<ItIndisiplinerEntity> indisiplinerEntityList = indisiplinerDao.getListIndisiplinerFromBlokir(hasilInquiry.getNip(), tanggalAwal);
                            String statusAbsensi;
                            if(indisiplinerEntityList.size()!=0){
                                statusAbsensi="10";
                            }else{
                                //cari status absensi
                                statusAbsensi = cariStatusAbsensi(hasilInquiry.getJamMasuk(),hasilInquiry.getJamKeluar(),jamMasukDB,jamPulangDB,ijinKeluar,ijinKembali,libur);
                            }
                            hasilInquiry.setStatus(statusAbsensi);
                            hasilInquiry.setStatusName(CommonUtil.statusName(statusAbsensi));

                            if (("03").equalsIgnoreCase(hasilInquiry.getStatus())){
                                String statusTidakMasuk = cariStatusTidakMasuk(hasilInquiry.getNip(),tanggalAwal);
                                if (!("00").equalsIgnoreCase(statusTidakMasuk)&&!("08").equalsIgnoreCase(statusTidakMasuk)){
                                    hasilInquiry.setStatus(statusTidakMasuk);
                                    hasilInquiry.setStatusName(CommonUtil.statusName(statusTidakMasuk));
                                }
                            }
                        }else{
                            //cari status tidak masuk
                            String statusTidakMasuk = cariStatusTidakMasuk(hasilInquiry.getNip(),tanggalAwal);
                            hasilInquiry.setStatus(statusTidakMasuk);
                            hasilInquiry.setStatusName(CommonUtil.statusName(statusTidakMasuk));
                        }
                        if ("KNS".equalsIgnoreCase(pegawai.getStatusPegawai())){
                            //mencari lembur
                            String sJamMasukLembur=null,sJamPulangLembur = null;
                            int jamMasukLemburAsli=0,jamPulangLemburAsli=0;
                            int iJamMasukLembur = 0,iJamPulangLembur=0,i=1;
                            if (hasilInquiry.getJamMasuk()!=null){
                                if (!("").equalsIgnoreCase(hasilInquiry.getJamMasuk())){
                                    jamMasukLemburAsli=Integer.parseInt(hasilInquiry.getJamMasuk().replace(":",""));
                                }
                            }
                            if (hasilInquiry.getJamKeluar()!=null){
                                if (!("").equalsIgnoreCase(hasilInquiry.getJamKeluar())){
                                    jamPulangLemburAsli = Integer.parseInt(hasilInquiry.getJamKeluar().replace(":",""));
                                }
                            }
                            List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(hasilInquiry.getNip(),tanggalAwal);
                            if (lemburEntityList.size()!=0){
                                for (LemburEntity lemburEntity : lemburEntityList){
                                    int jamMasukLemburTmp = Integer.parseInt(lemburEntity.getJamAwal().replace(":",""));
                                    int jamPulangLemburTmp = Integer.parseInt(lemburEntity.getJamAkhir().replace(":",""));
                                    if (i==1){
                                        sJamMasukLembur=lemburEntity.getJamAwal();
                                        sJamPulangLembur=lemburEntity.getJamAkhir();
                                        iJamMasukLembur=jamMasukLemburTmp;
                                        iJamPulangLembur=jamPulangLemburTmp;
                                        i++;
                                    }else{
                                        if (jamMasukLemburTmp<=iJamMasukLembur){
                                            sJamMasukLembur=lemburEntity.getJamAwal();
                                            iJamMasukLembur=jamMasukLemburTmp;
                                        }
                                        if (jamPulangLemburTmp>=iJamPulangLembur){
                                            sJamPulangLembur=lemburEntity.getJamAkhir();
                                            iJamPulangLembur=jamPulangLemburTmp;
                                        }
                                    }
                                }
                                if (hasilInquiry.getJamMasuk()==null){
                                    hasilInquiry.setJamMasuk(sJamPulangLembur);
                                }
                                if (hasilInquiry.getJamKeluar()==null){
                                    hasilInquiry.setJamKeluar(sJamMasukLembur);
                                }

                                if (jamMasukLemburAsli>=iJamMasukLembur){
                                    sJamMasukLembur=hasilInquiry.getJamMasuk();
                                }else if (jamPulangLemburAsli<=iJamPulangLembur){
                                    sJamPulangLembur=hasilInquiry.getJamKeluar();
                                }

                                if (("Y").equalsIgnoreCase(libur)){
                                    hasilInquiry.setJamPulangDb(hasilInquiry.getJamMasuk());
                                }

                                hasilInquiry.setLembur("Y");
                                hasilInquiry.setRealisasiJamLembur(calcJamLembur(sJamMasukLembur,sJamPulangLembur,libur,day));
                            }
                        }
                    }else{
                        //untuk pegawai SHIFT
                        liburShift="N";
                        AbsensiPegawai jamKerjaShift = new AbsensiPegawai();
                        Date tanggalInquiry = CommonUtil.convertToDate(tanggal);
                        if (jamKerjaShift.getJamMasuk()!=null&&jamKerjaShift.getJamPulang()!=null){
                            // jika ada di jam masuk Shift
                            String jamMasukDBShift=jamKerjaShift.getJamMasuk();
                            String jamPulangDBShift=jamKerjaShift.getJamPulang();
                            hasilInquiry.setJamMasukDb(jamKerjaShift.getJamMasuk());
                            hasilInquiry.setJamPulangDb(jamKerjaShift.getJamPulang());

                            int iJamMasukShift = Integer.parseInt(jamMasukDBShift.replace(":",""));
                            int iJamPulangShift = Integer.parseInt(jamPulangDBShift.replace(":",""));
                            int iJamMasukDBShift = Integer.parseInt(jamMasukDBShift.substring(0,2));
                            int iJamPulangDBShift = Integer.parseInt(jamPulangDBShift.substring(0,2));
                            int iJamMasukDbShiftAsli = Integer.parseInt(jamMasukDBShift.replace(":",""));
                            int iJamPulangDbShiftAsli = Integer.parseInt(jamPulangDBShift.replace(":",""));

                            int iMenitMasukDBShift = Integer.parseInt(jamMasukDBShift.substring(3));
                            int iMenitPulangDBShift = Integer.parseInt(jamPulangDBShift.substring(3));

                            int jamMasukInquiry=iJamMasukDBShift-2;
                            int jamPulangInquiry=iJamPulangDBShift+2;

                            int hari=0;

                            if (jamPulangInquiry<jamMasukInquiry){
                                hari=hari+1;
                            }
                            // get list from database detail
                            Calendar cal3 = Calendar.getInstance();
                            Calendar cal4 = Calendar.getInstance();
                            cal3.setTime(tanggalInquiry);
                            cal4.setTime(tanggalInquiry);

                            cal3.add(Calendar.DAY_OF_YEAR,hari);
                            cal3.set(Calendar.HOUR_OF_DAY, jamPulangInquiry);
                            cal3.set(Calendar.MINUTE, 0);
                            cal3.set(Calendar.SECOND, 0);
                            cal3.set(Calendar.MILLISECOND, 0);

                            cal4.set(Calendar.HOUR_OF_DAY, jamMasukInquiry);
                            cal4.set(Calendar.MINUTE, 0);
                            cal4.set(Calendar.SECOND, 0);
                            cal4.set(Calendar.MILLISECOND, 0);


                            Date tanggalBesokShift = new java.sql.Date(cal3.getTimeInMillis());
                            Date tanggalAwalShift = new java.sql.Date(cal4.getTimeInMillis());
                            Timestamp tsTanggalAwalShift = new java.sql.Timestamp(tanggalAwalShift.getTime());
                            Timestamp tsTanggalBesokShift = new java.sql.Timestamp(tanggalBesokShift.getTime());

                            List<MesinAbsensiDetailEntity> mesinAbsensiDetailEntityList = new ArrayList<>();
                            mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getAllDetailWithDateAndPin(pegawai.getPin(),tsTanggalAwalShift,tsTanggalBesokShift,branchId);
                            if (mesinAbsensiDetailEntityList.size()!=0){
                                int iJamMasukTmp,iJamKeluarTmp,i,iJamLibur;
                                if (hari==1){
                                    iJamMasukTmp=0;
                                    iJamKeluarTmp=0;
                                    i=1;
                                    iJamLibur=0;
                                }
                                else{
                                    iJamMasukTmp=9999;
                                    iJamKeluarTmp=0;
                                    i=1;
                                    iJamLibur=0;
                                }
                                for (MesinAbsensiDetailEntity mesin : mesinAbsensiDetailEntityList){
                                    String jam = String.valueOf(mesin.getScanDate()).substring(11,16);
                                    int jamAsli =Integer.parseInt(jam.replace(":",""));
                                    if (hari==1){
                                        if (jamAsli>iJamPulangShift+400){
                                            hasilInquiry.setJamMasuk(jam);
                                            iJamMasukTmp=jamAsli;
                                        }
                                        else if (jamAsli>iJamPulangShift-400){
                                            hasilInquiry.setJamKeluar(jam);
                                            iJamMasukTmp=jamAsli;
                                        }
                                    }else{
                                        if (jamAsli<iJamPulangShift-400&&jamAsli>300){
                                            if (jamAsli<iJamMasukTmp){
                                                hasilInquiry.setJamMasuk(jam);
                                                iJamMasukTmp=jamAsli;
                                            }
                                        }else if (jamAsli>iJamPulangShift-400){
                                            if (jamAsli>iJamKeluarTmp){
                                                hasilInquiry.setJamKeluar(jam);
                                                iJamMasukTmp=jamAsli;
                                            }
                                        }
                                    }
                                }

                                List<IjinKeluarEntity> ijinKeluarEntityList = ijinKeluarDao.getListPersonalFromNip(hasilInquiry.getNip(),tanggalAwal);
                                for (IjinKeluarEntity ijinKeluarEntity:ijinKeluarEntityList){
                                    ijinKeluar = ijinKeluarEntity.getJamKeluar();
                                    ijinKembali = ijinKeluarEntity.getJamKembali();
                                }
                                List<ItIndisiplinerEntity> indisiplinerEntityList = indisiplinerDao.getListIndisiplinerFromBlokir(hasilInquiry.getNip(), tanggalAwal);
                                String statusAbsensi;
                                if(indisiplinerEntityList.size()!=0){
                                    statusAbsensi="10";
                                }else{
                                    //cari status absensi
                                    statusAbsensi = cariStatusAbsensi(hasilInquiry.getJamMasuk(),hasilInquiry.getJamKeluar(),jamMasukDBShift,jamPulangDBShift,ijinKeluar,ijinKembali,liburShift);
                                }
                                hasilInquiry.setStatus(statusAbsensi);
                                hasilInquiry.setStatusName(CommonUtil.statusName(statusAbsensi));
                            }else{
                                //cari status tidak masuk
                                String statusTidakMasuk = cariStatusTidakMasuk(hasilInquiry.getNip(),tanggalAwal);
                                hasilInquiry.setStatus(statusTidakMasuk);
                                hasilInquiry.setStatusName(CommonUtil.statusName(statusTidakMasuk));
                            }
                            if ("KNS".equalsIgnoreCase(pegawai.getStatusPegawai())){
                                String jamMasukLemburShift=null,jamPulangLemburShift=null;
                                List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(hasilInquiry.getNip(),tanggalAwal);
                                if (lemburEntityList.size()!=0) {
                                    for (LemburEntity lemburEntity : lemburEntityList) {
                                        jamMasukLemburShift = lemburEntity.getJamAwal();
                                        jamPulangLemburShift = lemburEntity.getJamAkhir();
                                    }
                                    if (jamMasukLemburShift != null && jamPulangLemburShift != null) {
                                        int iJamMasukLemburShift = Integer.parseInt(jamMasukLemburShift.replace(":", ""));
                                        int iJamPulangLemburShift = Integer.parseInt(jamPulangLemburShift.replace(":", ""));
                                        int iJamMasukDBLemburShift = Integer.parseInt(jamMasukLemburShift.substring(0, 2));
                                        int iJamPulangDBLemburShift = Integer.parseInt(jamPulangLemburShift.substring(0, 2));

                                        int jamMasukInquiryLembur = iJamMasukDBLemburShift - 2;
                                        int jamPulangInquiryLembur = iJamPulangDBLemburShift + 2;

                                        hari = 0;

                                        if (jamPulangInquiryLembur < jamMasukInquiryLembur) {
                                            hari = hari + 1;
                                        }
                                        // get list from database detail
                                        Calendar cal5 = Calendar.getInstance();
                                        Calendar cal6 = Calendar.getInstance();
                                        cal5.setTime(tanggalInquiry);
                                        cal6.setTime(tanggalInquiry);

                                        cal5.add(Calendar.DAY_OF_YEAR, hari);
                                        cal5.set(Calendar.HOUR_OF_DAY, jamPulangInquiryLembur);
                                        cal5.set(Calendar.MINUTE, 0);
                                        cal5.set(Calendar.SECOND, 0);
                                        cal5.set(Calendar.MILLISECOND, 0);

                                        cal6.set(Calendar.HOUR_OF_DAY, jamMasukInquiryLembur);
                                        cal6.set(Calendar.MINUTE, 0);
                                        cal6.set(Calendar.SECOND, 0);
                                        cal6.set(Calendar.MILLISECOND, 0);


                                        tanggalBesokShift = new java.sql.Date(cal5.getTimeInMillis());
                                        tanggalAwalShift = new java.sql.Date(cal6.getTimeInMillis());
                                        tsTanggalAwalShift = new java.sql.Timestamp(tanggalAwalShift.getTime());
                                        tsTanggalBesokShift = new java.sql.Timestamp(tanggalBesokShift.getTime());

                                        mesinAbsensiDetailEntityList = new ArrayList<>();
                                        mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getAllDetailWithDateAndPin(pegawai.getPin(), tsTanggalAwalShift, tsTanggalBesokShift,branchId);
                                        if (mesinAbsensiDetailEntityList.size() != 0) {
                                            int iJamMasukTmp = iJamMasukDbShiftAsli, iJamKeluarTmp = iJamPulangDbShiftAsli, i = 1, iJamLibur = 0;
                                            if (hari==0){
                                                for (MesinAbsensiDetailEntity mesin : mesinAbsensiDetailEntityList) {
                                                    String jam = String.valueOf(mesin.getScanDate()).substring(11, 16);
                                                    int jamAsli = Integer.parseInt(jam.replace(":", ""));
                                                    if (jamAsli < iJamMasukShift) {
                                                        if (jamAsli < iJamMasukTmp) {
                                                            hasilInquiry.setJamMasuk(jam);
                                                            iJamMasukTmp = jamAsli;
                                                        }
                                                    } else if (jamAsli > iJamPulangShift) {
                                                        if (jamAsli > iJamKeluarTmp) {
                                                            hasilInquiry.setJamKeluar(jam);
                                                            iJamMasukTmp = jamAsli;
                                                        }
                                                    }
                                                }
                                            }else if (hari==1){
                                                for (MesinAbsensiDetailEntity mesin : mesinAbsensiDetailEntityList) {
                                                    String jam = String.valueOf(mesin.getScanDate()).substring(11, 16);
                                                    int jamAsli = Integer.parseInt(jam.replace(":", ""));
                                                    if (jamAsli < iJamMasukShift) {
                                                        if (jamAsli < iJamMasukTmp) {
                                                            hasilInquiry.setJamKeluar(jam);
                                                            iJamMasukTmp = jamAsli;
                                                        }
                                                    } else if (jamAsli > iJamPulangShift) {
                                                        if (jamAsli > iJamKeluarTmp) {
                                                            hasilInquiry.setJamMasuk(jam);
                                                            iJamMasukTmp = jamAsli;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                String sJamMasukLembur=null,sJamPulangLembur = null;
                                int jamMasukLemburAsli=0,jamPulangLemburAsli=0;
                                int iJamMasukLembur = 0,iJamPulangLembur=0,i=1;
                                if (lemburEntityList.size()!=0){
                                    for (LemburEntity lemburEntity : lemburEntityList){
                                        int jamMasukLemburTmp = Integer.parseInt(lemburEntity.getJamAwal().replace(":",""));
                                        int jamPulangLemburTmp = Integer.parseInt(lemburEntity.getJamAkhir().replace(":",""));
                                        if (i==1){
                                            sJamMasukLembur=lemburEntity.getJamAwal();
                                            sJamPulangLembur=lemburEntity.getJamAkhir();
                                            iJamMasukLembur=jamMasukLemburTmp;
                                            iJamPulangLembur=jamPulangLemburTmp;
                                            i++;
                                        }else{
                                            if (jamMasukLemburTmp<=iJamMasukLembur){
                                                sJamMasukLembur=lemburEntity.getJamAwal();
                                                iJamMasukLembur=jamMasukLemburTmp;
                                            }
                                            if (jamPulangLemburTmp>=iJamPulangLembur){
                                                sJamPulangLembur=lemburEntity.getJamAkhir();
                                                iJamPulangLembur=jamPulangLemburTmp;
                                            }
                                        }
                                    }
                                    if (hasilInquiry.getJamMasuk()==null){
                                        hasilInquiry.setJamMasuk(sJamPulangLembur);
                                    }
                                    if (hasilInquiry.getJamKeluar()==null){
                                        hasilInquiry.setJamKeluar(sJamMasukLembur);
                                    }

                                    if (jamMasukLemburAsli>=iJamMasukLembur){
                                        sJamMasukLembur=hasilInquiry.getJamMasuk();
                                    }else if (jamPulangLemburAsli<=iJamPulangLembur){
                                        sJamPulangLembur=hasilInquiry.getJamKeluar();
                                    }
                                    if (("Y").equalsIgnoreCase(liburShift)){
                                        hasilInquiry.setJamPulangDb(hasilInquiry.getJamMasuk());
                                    }
//                                    if (!sJamMasukLembur.equalsIgnoreCase(hasilInquiry.getJamPulangDb())){
//                                        hasilInquiry.setJamPulangDb(sJamMasukLembur);
//                                    }
                                    hasilInquiry.setLembur("Y");
                                    hasilInquiry.setRealisasiJamLembur(calcJamLemburShift(sJamMasukLembur,sJamPulangLembur,libur,jamMasukDBShift,jamPulangDBShift));
                                    //cari status absensi
                                    String statusAbsensi = null;
                                    if (hari==0){
                                        statusAbsensi = cariStatusAbsensi(hasilInquiry.getJamMasuk(),hasilInquiry.getJamKeluar(),jamMasukDBShift,jamPulangDBShift,ijinKeluar,ijinKembali,liburShift);
                                    }else if (hari==1){
                                        statusAbsensi = cariStatusAbsensiHariBerikutnya(hasilInquiry.getJamMasuk(),hasilInquiry.getJamKeluar(),jamMasukDBShift,jamPulangDBShift,ijinKeluar,ijinKembali,liburShift);
                                    }
                                    hasilInquiry.setStatus(statusAbsensi);
                                    hasilInquiry.setStatusName(CommonUtil.statusName(statusAbsensi));
                                }else if (awalTanggal){

                                }
                            }
                        }else {
                            //mengambil data untuk masuk saat libur
                            List<ImHrisShiftEntity> shiftList = shiftDao.getJadwalShift();
                            for (ImHrisShiftEntity hrisShiftEntity : shiftList){
                                String jamMasukDBShift=hrisShiftEntity.getJamAwal();
                                String jamPulangDBShift=hrisShiftEntity.getJamAkhir();
                                hasilInquiry.setJamMasukDb(hrisShiftEntity.getJamAwal());
                                hasilInquiry.setJamPulangDb(hrisShiftEntity.getJamAkhir());

                                // jika ada di jam masuk Shift

                                int iJamMasukShift = Integer.parseInt(jamMasukDBShift.replace(":",""));
                                int iJamPulangShift = Integer.parseInt(jamPulangDBShift.replace(":",""));
                                int iJamMasukDBShift = Integer.parseInt(jamMasukDBShift.substring(0,2));
                                int iJamPulangDBShift = Integer.parseInt(jamPulangDBShift.substring(0,2));

                                int iMenitMasukDBShift = Integer.parseInt(jamMasukDBShift.substring(3));
                                int iMenitPulangDBShift = Integer.parseInt(jamPulangDBShift.substring(3));

                                int jamMasukInquiry=iJamMasukDBShift-2;
                                int jamPulangInquiry=iJamPulangDBShift+2;

                                int hari=0;

                                if (jamPulangInquiry<jamMasukInquiry){
                                    hari=hari+1;
                                }
                                // get list from database detail
                                Calendar cal3 = Calendar.getInstance();
                                Calendar cal4 = Calendar.getInstance();
                                cal3.setTime(tanggalInquiry);
                                cal4.setTime(tanggalInquiry);

                                cal3.add(Calendar.DAY_OF_YEAR,hari);
                                cal3.set(Calendar.HOUR_OF_DAY, jamPulangInquiry);
                                cal3.set(Calendar.MINUTE, 0);
                                cal3.set(Calendar.SECOND, 0);
                                cal3.set(Calendar.MILLISECOND, 0);

                                cal4.set(Calendar.HOUR_OF_DAY, jamMasukInquiry);
                                cal4.set(Calendar.MINUTE, 0);
                                cal4.set(Calendar.SECOND, 0);
                                cal4.set(Calendar.MILLISECOND, 0);


                                Date tanggalBesokShift = new java.sql.Date(cal3.getTimeInMillis());
                                Date tanggalAwalShift = new java.sql.Date(cal4.getTimeInMillis());
                                Timestamp tsTanggalAwalShift = new java.sql.Timestamp(tanggalAwalShift.getTime());
                                Timestamp tsTanggalBesokShift = new java.sql.Timestamp(tanggalBesokShift.getTime());

                                List<MesinAbsensiDetailEntity> mesinAbsensiDetailEntityList = new ArrayList<>();
                                mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getAllDetailWithDateAndPin(pegawai.getPin(),tsTanggalAwalShift,tsTanggalBesokShift,branchId);
                                if (mesinAbsensiDetailEntityList.size()!=0){
                                    int iJamMasukTmp,iJamKeluarTmp,i,iJamLibur;
                                    if (hari==1){
                                        iJamMasukTmp=0;
                                        iJamKeluarTmp=0;
                                        i=1;
                                        iJamLibur=0;
                                    }
                                    else{
                                        iJamMasukTmp=9999;
                                        iJamKeluarTmp=0;
                                        i=1;
                                        iJamLibur=0;
                                    }
                                    for (MesinAbsensiDetailEntity mesin : mesinAbsensiDetailEntityList){
                                        String jam = String.valueOf(mesin.getScanDate()).substring(11,16);
                                        int jamAsli =Integer.parseInt(jam.replace(":",""));
                                        if (hari==1){
                                            if (jamAsli>iJamPulangShift+400){
                                                hasilInquiry.setJamMasuk(jam);
                                                iJamMasukTmp=jamAsli;
                                            }
                                            else if (jamAsli>iJamPulangShift-400){
                                                hasilInquiry.setJamKeluar(jam);
                                                iJamMasukTmp=jamAsli;
                                            }
                                        }else{
                                            if (jamAsli<iJamPulangShift-400&&jamAsli>300){
                                                if (jamAsli<iJamMasukTmp){
                                                    hasilInquiry.setJamMasuk(jam);
                                                    iJamMasukTmp=jamAsli;
                                                }
                                            }else if (jamAsli>iJamPulangShift-400){
                                                if (jamAsli>iJamKeluarTmp){
                                                    hasilInquiry.setJamKeluar(jam);
                                                    iJamMasukTmp=jamAsli;
                                                }
                                            }
                                        }
                                    }

                                    List<IjinKeluarEntity> ijinKeluarEntityList = ijinKeluarDao.getListPersonalFromNip(hasilInquiry.getNip(),tanggalAwal);
                                    for (IjinKeluarEntity ijinKeluarEntity:ijinKeluarEntityList){
                                        ijinKeluar = ijinKeluarEntity.getJamKeluar();
                                        ijinKembali = ijinKeluarEntity.getJamKembali();
                                    }
                                    List<ItIndisiplinerEntity> indisiplinerEntityList = indisiplinerDao.getListIndisiplinerFromBlokir(hasilInquiry.getNip(), tanggalAwal);
                                    String statusAbsensi;
                                    if(indisiplinerEntityList.size()!=0){
                                        statusAbsensi="10";
                                    }else{
                                        //cari status absensi
                                        statusAbsensi = "15";
                                    }
                                    hasilInquiry.setStatus(statusAbsensi);
                                    hasilInquiry.setStatusName(CommonUtil.statusName(statusAbsensi));

                                    if (("03").equalsIgnoreCase(hasilInquiry.getStatus())){
                                        String statusTidakMasuk = cariStatusTidakMasuk(hasilInquiry.getNip(),tanggalAwal);
                                        if (!("00").equalsIgnoreCase(statusTidakMasuk)||!("08").equalsIgnoreCase(statusTidakMasuk)){
                                            hasilInquiry.setStatus(statusTidakMasuk);
                                            hasilInquiry.setStatusName(CommonUtil.statusName(statusTidakMasuk));
                                        }
                                    }
                                }
                                if (mesinAbsensiDetailEntityList.size()>=2){
                                    break;
                                }
                            }

                            List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(hasilInquiry.getNip(),tanggalAwal);
                            if (lemburEntityList.size()!=0) {
                                hasilInquiry.setLembur("Y");
                                Double hasil;
                                if (hasilInquiry.getJamMasuk()==null||hasilInquiry.getJamKeluar()==null ){
                                    hasil = Double.parseDouble("0");
                                }else {
                                    hasil = CommonUtil.SubtractJamAwalDanJamAkhir(hasilInquiry.getJamMasuk(),hasilInquiry.getJamKeluar(),"Positif");
                                }
                                hasilInquiry.setRealisasiJamLembur(hasil);
                            }else{
                                hasilInquiry.setRealisasiJamLembur((double) 0);
                            }
                        }
                    }
                    if(!("Y").equalsIgnoreCase(pegawai.getShift())){
                        if (!("Y").equalsIgnoreCase(libur)){
                            absensiFinal.add(hasilInquiry);
                        }else if (!("00").equalsIgnoreCase(hasilInquiry.getStatus())){
                            absensiFinal.add(hasilInquiry);
                        }
                    }else{
                        if (hasilInquiry.getStatus()!=null){
                            absensiFinal.add(hasilInquiry);
                        }
                    }
                }
            }
        }
        return absensiFinal;
    }

    @Override
    public List<PegawaiTambahanAbsensi> inquiryTambahan(String tanggal, Boolean awalTanggal) throws Exception {
        return null;
    }
    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        Long result = GenerateBoLog.generateBoLogCron(absensiPegawaiDao, message, moduleMethod);

        return result;
    }

    @Override
    public String refreshDataLembur(String nip, Date tanggal, String jamAwal, String jamAkhir){
        logger.info("[LemburBoImpl.refreshDataLembur] start process >>>");
        List<AbsensiPegawaiEntity> absensiPegawaiEntityList = new ArrayList<>();
        String status = "sukses";
        String tahunGaji ="";
        String branchId = "";

        try {
            absensiPegawaiEntityList = absensiPegawaiDao.searchExistingAbsensi(nip,tanggal);
        } catch (HibernateException e) {
            logger.error("[AbsensiBOImpl.refreshDataLembur] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
        }
        ItPersonilPositionEntity personilPositionEntity = personilPositionDao.getById("nip",nip);
        if (personilPositionEntity==null){
            String statusError = "Error : tidak ditemukan posisi personil";
            logger.error("[AbsensiBOImpl.refreshDataLembur] " + statusError);
            throw new GeneralBOException(statusError);
        }

        ImCompany company;
        try {
            company = companyDao.getCompanyInfo("Y");
            if (!("").equalsIgnoreCase(company.getPeriodeGaji())) {
                tahunGaji = company.getPeriodeGaji();
            } else {
                String statusError = "Error : tidak ditemukan periode gaji pada Company";
                logger.error("[AbsensiBOImpl.refreshDataLembur] " + statusError);
                throw new GeneralBOException(statusError);
            }
        }catch (HibernateException e){
            logger.error("[AbsensiBOImpl.refreshDataLembur] " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        if (absensiPegawaiEntityList.size()!=0){
            for (AbsensiPegawaiEntity absensiPegawaiEntity:absensiPegawaiEntityList){
                String libur="N";
                List<ImHrisJamKerja> jamKerjaList = new ArrayList<>();
                Calendar cal = Calendar.getInstance();
                cal.setTime(tanggal);
                int day = cal.get(Calendar.DAY_OF_WEEK);
                Map hsCriteria2 = new HashMap();
                hsCriteria2.put("branch_id",personilPositionEntity.getBranchId());
                hsCriteria2.put("hari",day);
                hsCriteria2.put("flag","Y");
                jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                List<ImLiburEntity> liburEntityList = liburDao.getListLibur(tanggal);
                //cari hari libur
                if ( liburEntityList.size()!=0||jamKerjaList.size()==0||day==1||day==7) {
                    libur = "Y";
                }
                String sJamMasukLembur=null,sJamPulangLembur = null;
                int jamMasukLemburAsli=0,jamPulangLemburAsli=0;
                int iJamMasukLembur = 0,iJamPulangLembur=0,i=1;
                if (absensiPegawaiEntity.getJamMasuk()!=null){
                    if (!("").equalsIgnoreCase(absensiPegawaiEntity.getJamMasuk())){
                        jamMasukLemburAsli=Integer.parseInt(absensiPegawaiEntity.getJamMasuk().replace(":",""));
                    }
                }
                if (absensiPegawaiEntity.getJamKeluar()!=null){
                    if (!("").equalsIgnoreCase(absensiPegawaiEntity.getJamKeluar())){
                        jamPulangLemburAsli = Integer.parseInt(absensiPegawaiEntity.getJamKeluar().replace(":",""));
                    }
                }
                List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(absensiPegawaiEntity.getNip(),absensiPegawaiEntity.getTanggal());
                if (lemburEntityList.size()!=0){
                    for (LemburEntity lemburEntity : lemburEntityList){
                        int jamMasukLemburTmp = Integer.parseInt(lemburEntity.getJamAwal().replace(":",""));
                        int jamPulangLemburTmp = Integer.parseInt(lemburEntity.getJamAkhir().replace(":",""));
                        if (i==1){
                            sJamMasukLembur=lemburEntity.getJamAwal();
                            sJamPulangLembur=lemburEntity.getJamAkhir();
                            iJamMasukLembur=jamMasukLemburTmp;
                            iJamPulangLembur=jamPulangLemburTmp;
                            i++;
                        }else{
                            if (jamMasukLemburTmp<=iJamMasukLembur){
                                sJamMasukLembur=lemburEntity.getJamAwal();
                                iJamMasukLembur=jamMasukLemburTmp;
                            }
                            if (jamPulangLemburTmp>=iJamPulangLembur){
                                sJamPulangLembur=lemburEntity.getJamAkhir();
                                iJamPulangLembur=jamPulangLemburTmp;
                            }
                        }
                    }
                    if (absensiPegawaiEntity.getJamMasuk()==null){
                        absensiPegawaiEntity.setJamMasuk(sJamPulangLembur);
                    }
                    if (absensiPegawaiEntity.getJamKeluar()==null){
                        absensiPegawaiEntity.setJamKeluar(sJamMasukLembur);
                    }

                    if (jamMasukLemburAsli>=iJamMasukLembur){
                        sJamMasukLembur=absensiPegawaiEntity.getJamMasuk();
                    }else if (jamPulangLemburAsli<=iJamPulangLembur){
                        sJamPulangLembur=absensiPegawaiEntity.getJamKeluar();
                    }

                    if (("Y").equalsIgnoreCase(libur)){
                        absensiPegawaiEntity.setJamPulangDb(absensiPegawaiEntity.getJamMasuk());
                    }

                    absensiPegawaiEntity.setLembur("Y");
                    try {
                        absensiPegawaiEntity.setRealisasiJamLembur(calcJamLembur(sJamMasukLembur,sJamPulangLembur,libur,day));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (lemburEntityList.size() != 0) {
                    Double lamaLembur=(double)0;
                    Double pengajuanLembur=(double)0;
                    for (LemburEntity lemburEntity : lemburEntityList) {
                        lamaLembur=lamaLembur+lemburEntity.getLamaJam();
                        pengajuanLembur=pengajuanLembur+lemburEntity.getLamaJam();
                        absensiPegawaiEntity.setJenisLembur(lemburEntity.getTipeLembur());
                        if (lemburEntity.getTipeLembur().equalsIgnoreCase("R")) {
                            absensiPegawaiEntity.setJenisLemburName("Rutin");
                        } else if (lemburEntity.getTipeLembur().equalsIgnoreCase("I")) {
                            absensiPegawaiEntity.setJenisLemburName("Non Rutin");
                        }
                    }
                    if (absensiPegawaiEntity.getRealisasiJamLembur() <= lamaLembur) {
                        lamaLembur = absensiPegawaiEntity.getRealisasiJamLembur();
                    }

                    absensiPegawaiEntity.setAwalLembur(absensiPegawaiEntity.getJamPulangDb());
                    absensiPegawaiEntity.setSelesaiLembur(absensiPegawaiEntity.getJamKeluar());
                    absensiPegawaiEntity.setJamPulang(absensiPegawaiEntity.getJamKeluar());
                    absensiPegawaiEntity.setLamaLembur(lamaLembur);
                    absensiPegawaiEntity.setPengajuanLembur(pengajuanLembur);

                    String tipePegawai="";
                    String golongan="";
                    int point=0;
                    List<ImBiodataEntity> biodataList = biodataDao.getDataBiodataHris(nip,"","","","","Y","");
                    for (ImBiodataEntity biodata:biodataList){
                        tipePegawai=biodata.getTipePegawai();
                        golongan=biodata.getGolongan();
                        point=biodata.getPoint();
                    }

                    List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                    Map hsCriteria4 = new HashMap();
                    hsCriteria4.put("tipe_pegawai_id", tipePegawai);
                    hsCriteria4.put("flag", "Y");
                    double faktor = 0;
                    Double upahLembur = 0d;
                    Double sankhus = 0d;
                    Double gapok = 0d;
                    pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                    for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                        faktor = pengaliFaktorLemburEntity.getFaktor();
                    }

                    List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                    List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                    if (tipePegawai.equalsIgnoreCase("TP01")){
                        payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(golongan,tahunGaji);
                        for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                            gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                            sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                        }
                    }else if (tipePegawai.equalsIgnoreCase("TP03")){
                        payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(golongan,tahunGaji);
                        for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                            gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                            sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                        }
                    }else{
                        String statusError = "ERROR : tidak bisa menemukan tipe pegawai";
                        throw new GeneralBOException(statusError);
                    }

                    String tipeHari="hari_kerja";
                    if (("Y").equalsIgnoreCase(libur)){
                        tipeHari="hari_libur";
                    }
                    double jamLembur = 0;
                    int j = 1;
                    if (lamaLembur>0){
                        do{
                            if (lamaLembur>0&&lamaLembur<1){
                                Map hsCriteria5 = new HashMap();
                                hsCriteria5.put("tipe_hari", tipeHari);
                                hsCriteria5.put("jam_lembur", j);
                                hsCriteria5.put("flag", "Y");
                                List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                    jamLembur = jamLembur + (lamaLembur*jamLemburEntity.getPengaliJamLembur());
                                }
                                lamaLembur= Double.valueOf(0);
                            }else{
                                Map hsCriteria5 = new HashMap();
                                hsCriteria5.put("tipe_hari", tipeHari);
                                hsCriteria5.put("jam_lembur", j);
                                hsCriteria5.put("flag", "Y");
                                List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                    jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                }
                                lamaLembur= lamaLembur-1;
                            }
                            j=j+1;
                        }while (lamaLembur>0);
                    }
                    double peralihan = 0d;
                    peralihan = getTunjPeralihan(absensiPegawaiEntity.getNip(),tanggal).doubleValue();

                    upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;

                    absensiPegawaiEntity.setLembur("Y");
                    absensiPegawaiEntity.setJamLembur(jamLembur);
                    absensiPegawaiEntity.setBiayaLembur(upahLembur);
                    String upahNew = "";
                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setGroupingSeparator('.');

                    kursIndonesia.setDecimalFormatSymbols(formatRp);
                    upahNew = kursIndonesia.format(upahLembur);
                    absensiPegawaiEntity.setBiayaLemburName(upahNew);

                    try {
                        absensiPegawaiDao.updateAndSave(absensiPegawaiEntity);
                    } catch (HibernateException e) {
                        logger.error("[LemburBoImpl.refreshDataLembur] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
                    }
                }
            }
        }else{
            status="data absensi belum ada";
        }

        logger.info("[LemburBoImpl.refreshDataLembur] end process <<<");
        return status;
    }
    @Override
    public String sesuaikanDataLembur(String nip, Date tanggal, String jamAwal, String jamAkhir, String keterangan){
        logger.info("[LemburBoImpl.sesuaikanDataLembur] start process >>>");
        List<AbsensiPegawaiEntity> absensiPegawaiEntityList = new ArrayList<>();
        String status = "sukses";
        try {
            absensiPegawaiEntityList = absensiPegawaiDao.searchExistingAbsensi(nip,tanggal);
        } catch (HibernateException e) {
            logger.error("[LemburBoImpl.sesuaikanDataLembur] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
        }
        if (absensiPegawaiEntityList.size()!=0){
            for (AbsensiPegawaiEntity absensiPegawaiEntity:absensiPegawaiEntityList){
                String libur="N";
                List<ImHrisJamKerja> jamKerjaList = new ArrayList<>();
                Calendar cal = Calendar.getInstance();
                cal.setTime(tanggal);
                int day = cal.get(Calendar.DAY_OF_WEEK);
                Map hsCriteria2 = new HashMap();
                hsCriteria2.put("branch_id","KD01");
                hsCriteria2.put("hari",day);
                hsCriteria2.put("flag","Y");
                jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                List<ImLiburEntity> liburEntityList = liburDao.getListLibur(tanggal);
                //cari hari libur
                if ( liburEntityList.size()!=0||jamKerjaList.size()==0||day==1||day==7) {
                    libur = "Y";
                }
                String sJamMasukLembur=null,sJamPulangLembur = null;
                int iJamMasukLembur = 0,iJamPulangLembur=0,i=1;
                List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(absensiPegawaiEntity.getNip(),absensiPegawaiEntity.getTanggal());
                if (lemburEntityList.size()!=0){
                    for (LemburEntity lemburEntity : lemburEntityList){
                        int jamMasukLemburTmp = Integer.parseInt(lemburEntity.getJamAwal().replace(":",""));
                        int jamPulangLemburTmp = Integer.parseInt(lemburEntity.getJamAkhir().replace(":",""));
                        if (i==1){
                            sJamMasukLembur=lemburEntity.getJamAwal();
                            sJamPulangLembur=lemburEntity.getJamAkhir();
                            iJamMasukLembur=jamMasukLemburTmp;
                            iJamPulangLembur=jamPulangLemburTmp;
                            i++;
                        }else{
                            if (jamMasukLemburTmp<=iJamMasukLembur){
                                sJamMasukLembur=lemburEntity.getJamAwal();
                                iJamMasukLembur=jamMasukLemburTmp;
                            }
                            if (jamPulangLemburTmp>=iJamPulangLembur){
                                sJamPulangLembur=lemburEntity.getJamAkhir();
                                iJamPulangLembur=jamPulangLemburTmp;
                            }
                        }
                    }
//                    absensiPegawaiEntity.setJamMasuk(sJamPulangLembur);
//                    absensiPegawaiEntity.setJamKeluar(sJamMasukLembur);

                    if (("Y").equalsIgnoreCase(libur)){
                        absensiPegawaiEntity.setJamPulangDb(absensiPegawaiEntity.getJamMasuk());
                    }

                    absensiPegawaiEntity.setLembur("Y");
                    try {
                        absensiPegawaiEntity.setRealisasiJamLembur(calcJamLembur(sJamMasukLembur,sJamPulangLembur,libur,day));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (lemburEntityList.size() != 0) {
                    Double lamaLembur=(double)0;
                    Double pengajuanLembur=(double)0;
                    for (LemburEntity lemburEntity : lemburEntityList) {
                        lamaLembur=lamaLembur+lemburEntity.getLamaJam();
                        pengajuanLembur=pengajuanLembur+lemburEntity.getLamaJam();
                        absensiPegawaiEntity.setJenisLembur(lemburEntity.getTipeLembur());
                        if (lemburEntity.getTipeLembur().equalsIgnoreCase("R")) {
                            absensiPegawaiEntity.setJenisLemburName("Rutin");
                        } else if (lemburEntity.getTipeLembur().equalsIgnoreCase("I")) {
                            absensiPegawaiEntity.setJenisLemburName("Non Rutin");
                        }
                    }
                    if (absensiPegawaiEntity.getRealisasiJamLembur() >= lamaLembur) {
                        lamaLembur = absensiPegawaiEntity.getRealisasiJamLembur();
                    }
//                    absensiPegawaiEntity.setAwalLembur(absensiPegawaiEntity.getJamPulangDb());
//                    absensiPegawaiEntity.setSelesaiLembur(absensiPegawaiEntity.getJamKeluar());
//                    absensiPegawaiEntity.setJamPulang(absensiPegawaiEntity.getJamKeluar());
                    absensiPegawaiEntity.setLamaLembur(lamaLembur);
                    absensiPegawaiEntity.setPengajuanLembur(pengajuanLembur);

                    String tipePegawai="";
                    String golongan="";
                    int point=0;
                    List<ImBiodataEntity> biodataList = biodataDao.getDataBiodataHris(nip,"","","","","Y","");
                    for (ImBiodataEntity biodata:biodataList){
                        tipePegawai=biodata.getTipePegawai();
                        golongan=biodata.getGolongan();
                        point=biodata.getPoint();
                    }

                    List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                    Map hsCriteria4 = new HashMap();
                    hsCriteria4.put("tipe_pegawai_id", tipePegawai);
                    hsCriteria4.put("flag", "Y");
                    double faktor = 0;
                    Double upahLembur = 0d;
                    pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                    for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                        faktor = pengaliFaktorLemburEntity.getFaktor();
                    }

                    hsCriteria4 = new HashMap();
                    hsCriteria4.put("golongan_id", golongan);
                    hsCriteria4.put("point", (int) Math.round(point));
                    hsCriteria4.put("tahun", "2019");
                    hsCriteria4.put("flag", "Y");
                    List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                    payrollSkalaGajiList = payrollSkalaGajiDao.getByCriteria(hsCriteria4);
                    for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                        upahLembur = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                    }
                    String tipeHari="hari_kerja";
                    if (("Y").equalsIgnoreCase(libur)){
                        tipeHari="hari_libur";
                    }
                    double jamLembur = 0;
                    int j = 1;
                    if (lamaLembur>0){
                        do{
                            if (lamaLembur>0&&lamaLembur<1){
                                Map hsCriteria5 = new HashMap();
                                hsCriteria5.put("tipe_hari", tipeHari);
                                hsCriteria5.put("jam_lembur", j);
                                hsCriteria5.put("flag", "Y");
                                List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                    jamLembur = jamLembur + (lamaLembur*jamLemburEntity.getPengaliJamLembur());
                                }
                                lamaLembur= Double.valueOf(0);
                            }else{
                                Map hsCriteria5 = new HashMap();
                                hsCriteria5.put("tipe_hari", tipeHari);
                                hsCriteria5.put("jam_lembur", j);
                                hsCriteria5.put("flag", "Y");
                                List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                    jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                }
                                lamaLembur= lamaLembur-1;
                            }
                            j=j+1;
                        }while (lamaLembur>0);
                    }
                    Double umk =0d;
                    Double peralihan = 0d;
                    umk = getTunjanganUmk("KD01",golongan).doubleValue();
                    peralihan = getTunjPeralihan(absensiPegawaiEntity.getNip(),tanggal).doubleValue();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = dateFormat.format(tanggal);
                    String[] a = strDate.split("-");
                    int intBulan =Integer.valueOf(a[1])-1;
                    if (intBulan<10){a[1]="0"+intBulan;}
                    String bulan = a[1];
                    String tahun = a[2];
                    List<ItPayrollEntity> payrollEntityList = payrollDao.getTunjanganPeralihan(nip,bulan,tahun);
                    for (ItPayrollEntity itPayrollEntity : payrollEntityList){
                        peralihan=itPayrollEntity.getTunjanganPeralihan().doubleValue();
                    }
                    upahLembur = (upahLembur+umk+peralihan)*faktor*jamLembur;

                    absensiPegawaiEntity.setLembur("Y");
                    absensiPegawaiEntity.setJamLembur(jamLembur);
                    absensiPegawaiEntity.setBiayaLembur(upahLembur);
                    String upahNew = "";
                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setGroupingSeparator('.');

                    kursIndonesia.setDecimalFormatSymbols(formatRp);
                    upahNew = kursIndonesia.format(upahLembur);
                    absensiPegawaiEntity.setBiayaLemburName(upahNew);
                    absensiPegawaiEntity.setKeteranganSesuaikan(keterangan);
                    absensiPegawaiEntity.setSesuaikanFlag("Y");
                    try {
                        absensiPegawaiDao.updateAndSave(absensiPegawaiEntity);
                    } catch (HibernateException e) {
                        logger.error("[LemburBoImpl.refreshDataLembur] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
                    }
                }
            }
        }else{
            status="data absensi belum ada";
        }

        logger.info("[LemburBoImpl.sesuaikanDataLembur] end process <<<");
        return status;
    }


    @Override
    public String addAbsensiLembur(String nip, Date tanggal, String jamAwal, String jamAkhir, String pengajuan){
        logger.info("[LemburBoImpl.addAbsensiLembur] start process >>>");
        String status = "sukses";
        AbsensiPegawaiEntity data = new AbsensiPegawaiEntity();
        String libur="N";
        String jamMasuk = null,jamPulang= null;

        List<ImHrisJamKerja> jamKerjaList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggal);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        Map hsCriteria2 = new HashMap();
        hsCriteria2.put("branch_id","KD01");
        hsCriteria2.put("hari",day);
        hsCriteria2.put("flag","Y");
        jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);

        if (jamKerjaList.size()!=0){
            for (ImHrisJamKerja jamKerja : jamKerjaList){
                jamMasuk=jamKerja.getJamAwalKerja();
                jamPulang=jamKerja.getJamAkhirKerja();
            }
        }

        String absensiPegawaiId;
        try {
            // Generating ID, get from postgre sequence
            absensiPegawaiId = absensiPegawaiDao.getNextAbsensiPegawaiId();
        } catch (HibernateException e) {
            logger.error("[AbsensiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence Absensi Pegawai id, please info to your admin..." + e.getMessage());
        }

        data.setAbsensiPegawaiId(absensiPegawaiId);
        data.setNip(nip);
        data.setTanggal(tanggal);
        data.setLembur("Y");
        data.setIjin("N");
        data.setBranchId("KD01");
        data.setAction("C");
        data.setFlag("Y");
        data.setCreatedWho(CommonUtil.userLogin());
        data.setLastUpdateWho(CommonUtil.userLogin());
        data.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        data.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        List<ImLiburEntity> liburEntityList = liburDao.getListLibur(tanggal);
        //cari hari libur
        if ( liburEntityList.size()!=0||jamKerjaList.size()==0||day==1||day==7) {
            libur = "Y";
        }
        String sJamMasukLembur=null,sJamPulangLembur = null;
        int jamMasukLemburAsli=0,jamPulangLemburAsli=0;
        int iJamMasukLembur = 0,iJamPulangLembur=0,i=1;

        if (libur.equalsIgnoreCase("N")){
            data.setStatusAbsensi("01");
            data.setJamMasuk(jamMasuk);
            data.setJamKeluar(jamPulang);
            data.setTipeHari("hari_kerja");
        }else{
            data.setTipeHari("hari_libur");
            data.setStatusAbsensi("15");
        }

        List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(nip,tanggal);
        if (lemburEntityList.size()!=0){
            for (LemburEntity lemburEntity : lemburEntityList){
                int jamMasukLemburTmp = Integer.parseInt(lemburEntity.getJamAwal().replace(":",""));
                int jamPulangLemburTmp = Integer.parseInt(lemburEntity.getJamAkhir().replace(":",""));
                if (i==1){
                    sJamMasukLembur=lemburEntity.getJamAwal();
                    sJamPulangLembur=lemburEntity.getJamAkhir();
                    iJamMasukLembur=jamMasukLemburTmp;
                    iJamPulangLembur=jamPulangLemburTmp;
                    i++;
                }else{
                    if (jamMasukLemburTmp<=iJamMasukLembur){
                        sJamMasukLembur=lemburEntity.getJamAwal();
                        iJamMasukLembur=jamMasukLemburTmp;
                    }
                    if (jamPulangLemburTmp>=iJamPulangLembur){
                        sJamPulangLembur=lemburEntity.getJamAkhir();
                        iJamPulangLembur=jamPulangLemburTmp;
                    }
                }
            }

            if (libur.equalsIgnoreCase("Y")){
                data.setJamMasuk(sJamMasukLembur);
                data.setJamKeluar(sJamPulangLembur);
            }

            try {
                data.setRealisasiJamLembur(calcJamLembur(sJamMasukLembur,sJamPulangLembur,libur,day));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (lemburEntityList.size() != 0) {
            Double lamaLembur=(double)0;
            Double pengajuanLembur=(double)0;
            for (LemburEntity lemburEntity : lemburEntityList) {
                lamaLembur=lamaLembur+lemburEntity.getLamaJam();
                pengajuanLembur=pengajuanLembur+lemburEntity.getLamaJam();
                data.setJenisLembur(lemburEntity.getTipeLembur());
                if (lemburEntity.getTipeLembur().equalsIgnoreCase("R")) {
                    data.setJenisLemburName("Rutin");
                } else if (lemburEntity.getTipeLembur().equalsIgnoreCase("I")) {
                    data.setJenisLemburName("Non Rutin");
                }
            }
            if (data.getRealisasiJamLembur() <= lamaLembur) {
                lamaLembur = data.getRealisasiJamLembur();
            }

            data.setLamaLembur(lamaLembur);
            data.setPengajuanLembur(pengajuanLembur);

            String tipePegawai="";
            String golongan="";
            int point=0;
            List<ImBiodataEntity> biodataList = biodataDao.getDataBiodataHris(nip,"","","","","Y","");
            for (ImBiodataEntity biodata:biodataList){
                tipePegawai=biodata.getTipePegawai();
                golongan=biodata.getGolongan();
                point=biodata.getPoint();
            }

            List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
            Map hsCriteria4 = new HashMap();
            hsCriteria4.put("tipe_pegawai_id", tipePegawai);
            hsCriteria4.put("flag", "Y");
            double faktor = 0;
            Double upahLembur = 0d;
            pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
            for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                faktor = pengaliFaktorLemburEntity.getFaktor();
            }

            hsCriteria4 = new HashMap();
            hsCriteria4.put("golongan_id", golongan);
            hsCriteria4.put("point", (int) Math.round(point));
            hsCriteria4.put("tahun", "2019");
            hsCriteria4.put("flag", "Y");
            List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
            payrollSkalaGajiList = payrollSkalaGajiDao.getByCriteria(hsCriteria4);
            for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                upahLembur = imPayrollSkalaGajiEntity.getNilai().doubleValue();
            }
            String tipeHari="hari_kerja";
            if (("Y").equalsIgnoreCase(libur)){
                tipeHari="hari_libur";
            }
            double jamLembur = 0;
            int j = 1;
            if (lamaLembur>0){
                do{
                    if (lamaLembur>0&&lamaLembur<1){
                        Map hsCriteria5 = new HashMap();
                        hsCriteria5.put("tipe_hari", tipeHari);
                        hsCriteria5.put("jam_lembur", j);
                        hsCriteria5.put("flag", "Y");
                        List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                        for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                            jamLembur = jamLembur + (lamaLembur*jamLemburEntity.getPengaliJamLembur());
                        }
                        lamaLembur= Double.valueOf(0);
                    }else{
                        Map hsCriteria5 = new HashMap();
                        hsCriteria5.put("tipe_hari", tipeHari);
                        hsCriteria5.put("jam_lembur", j);
                        hsCriteria5.put("flag", "Y");
                        List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                        jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                        for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                            jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                        }
                        lamaLembur= lamaLembur-1;
                    }
                    j=j+1;
                }while (lamaLembur>0);
            }
            Double umk =0d;
            Double peralihan = 0d;
            umk = getTunjanganUmk("KD01",golongan).doubleValue();
            peralihan = getTunjPeralihan(data.getNip(),tanggal).doubleValue();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = dateFormat.format(tanggal);
            String[] a = strDate.split("-");
            int intBulan =Integer.valueOf(a[1])-1;
            if (intBulan<10){a[1]="0"+intBulan;}
            String bulan = a[1];
            String tahun = a[2];
            List<ItPayrollEntity> payrollEntityList = payrollDao.getTunjanganPeralihan(nip,bulan,tahun);
            for (ItPayrollEntity itPayrollEntity : payrollEntityList){
                peralihan=itPayrollEntity.getTunjanganPeralihan().doubleValue();
            }
            upahLembur = (upahLembur+umk+peralihan)*faktor*jamLembur;

            data.setLembur("Y");
            data.setJamLembur(jamLembur);
            data.setBiayaLembur(upahLembur);
            String upahNew = "";
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setGroupingSeparator('.');

            kursIndonesia.setDecimalFormatSymbols(formatRp);
            upahNew = kursIndonesia.format(upahLembur);
            data.setBiayaLemburName(upahNew);

            try {
                absensiPegawaiDao.addAndSave(data);
            } catch (HibernateException e) {
                logger.error("[LemburBoImpl.addAbsensiLembur] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[LemburBoImpl.refreshDataLembur] end process <<<");
        return status;
    }

    @Override
    public List<MesinAbsensiDetail> getByCriteriaAbsensiDetail(MesinAbsensiDetail searchBean, String tanggal) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteriaAbsensiDetail] start process >>>");

        // Mapping with collection and put
        List<MesinAbsensiDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPin() != null && !"".equalsIgnoreCase(searchBean.getPin())) {
                hsCriteria.put("pin", searchBean.getPin());
            }

            hsCriteria.put("flag", "Y");


            List<MesinAbsensiDetailEntity> itMesinAbsensiDetailEntity = null;
            try {
                itMesinAbsensiDetailEntity = mesinAbsensiDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itMesinAbsensiDetailEntity != null){
                MesinAbsensiDetail returnMesinAbsensiDetail;
                // Looping from dao to object and save in collection
                for(MesinAbsensiDetailEntity mesinAbsensiDetailEntity : itMesinAbsensiDetailEntity){
                    returnMesinAbsensiDetail = new MesinAbsensiDetail();
                    returnMesinAbsensiDetail.setPin(mesinAbsensiDetailEntity.getPin());
                    returnMesinAbsensiDetail.setScanDate(mesinAbsensiDetailEntity.getScanDate());
                    returnMesinAbsensiDetail.setStatus(mesinAbsensiDetailEntity.getStatus());
                    returnMesinAbsensiDetail.setVerifyMode(mesinAbsensiDetailEntity.getVerifyMode());
                    returnMesinAbsensiDetail.setWorkCode(mesinAbsensiDetailEntity.getWorkCode());
                    returnMesinAbsensiDetail.setStatus(mesinAbsensiDetailEntity.getStatus());
                    if (mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("1")){
                        returnMesinAbsensiDetail.setStatusName("Masuk");
                    }else if(mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("2")){
                        returnMesinAbsensiDetail.setStatusName("Pulang");
                    }
                    else if(mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("3")){
                        returnMesinAbsensiDetail.setStatusName("Istirahat");
                    }
                    else if(mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("4")){
                        returnMesinAbsensiDetail.setStatusName("Kembali");
                    }
                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                    String newFormattedDate = newFormat.format(returnMesinAbsensiDetail.getScanDate());

                    returnMesinAbsensiDetail.setTanggal(newFormattedDate);

                    String ScanDate = String.valueOf(mesinAbsensiDetailEntity.getScanDate());
                    ScanDate = ScanDate.substring(0,10);
                    String tgl = ScanDate.substring(8);
                    String bln = ScanDate.substring(5,7);
                    String thn = ScanDate.substring(0,4);
                    ScanDate=tgl+"-"+bln+"-"+thn;
                    if (ScanDate.equalsIgnoreCase(tanggal)){
                        listOfResult.add(returnMesinAbsensiDetail);
                    }
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteriaMesin] end process <<<");

        return listOfResult;
    }

    @Override
    public List<MesinAbsensiDetail> getByCriteriaAbsensiDetailAll(MesinAbsensiDetail searchBean, String tanggal) throws GeneralBOException {
        logger.info("[AbsensiBoImpl.getByCriteriaAbsensiDetail] start process >>>");

        // Mapping with collection and put
        List<MesinAbsensiDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPin() != null && !"".equalsIgnoreCase(searchBean.getPin())) {
                hsCriteria.put("pin", searchBean.getPin());
            }
            hsCriteria.put("flag", "Y");
            List<MesinAbsensiDetailEntity> itMesinAbsensiDetailEntity = null;
            try {
                itMesinAbsensiDetailEntity = mesinAbsensiDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itMesinAbsensiDetailEntity != null) {
                MesinAbsensiDetail returnMesinAbsensiDetail;
                // Looping from dao to object and save in collection
                for (MesinAbsensiDetailEntity mesinAbsensiDetailEntity : itMesinAbsensiDetailEntity) {
                    returnMesinAbsensiDetail = new MesinAbsensiDetail();
                    returnMesinAbsensiDetail.setPin(mesinAbsensiDetailEntity.getPin());
                    returnMesinAbsensiDetail.setScanDate(mesinAbsensiDetailEntity.getScanDate());
                    returnMesinAbsensiDetail.setStatus(mesinAbsensiDetailEntity.getStatus());
                    returnMesinAbsensiDetail.setVerifyMode(mesinAbsensiDetailEntity.getVerifyMode());
                    returnMesinAbsensiDetail.setWorkCode(mesinAbsensiDetailEntity.getWorkCode());
                    returnMesinAbsensiDetail.setStatus(mesinAbsensiDetailEntity.getStatus());
                    if (mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("1")) {
                        returnMesinAbsensiDetail.setStatusName("Masuk");
                    } else if (mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("2")) {
                        returnMesinAbsensiDetail.setStatusName("Pulang");
                    } else if (mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("3")) {
                        returnMesinAbsensiDetail.setStatusName("Istirahat");
                    } else if (mesinAbsensiDetailEntity.getStatus().equalsIgnoreCase("4")) {
                        returnMesinAbsensiDetail.setStatusName("Kembali");
                    }
                    List<ImBiodataEntity> biodataEntityList = new ArrayList<>();
                    biodataEntityList = biodataDao.getListPersonalFromPin(searchBean.getPin());
                    Date tanggalFrom = CommonUtil.convertToDate(tanggal);
                    for (ImBiodataEntity imBiodataEntity : biodataEntityList) {

                        List<IjinKeluarEntity> ijinKeluarEntityList = new ArrayList<>();
                        ijinKeluarEntityList = ijinKeluarDao.getListPersonalFromNip(imBiodataEntity.getNip(), tanggalFrom);
                        for (IjinKeluarEntity ijinKeluarEntity : ijinKeluarEntityList) {
                            returnMesinAbsensiDetail.setJamKeluarIjin(ijinKeluarEntity.getJamKeluar());
                            returnMesinAbsensiDetail.setJamKembaliIjin(ijinKeluarEntity.getJamKembali());
                            returnMesinAbsensiDetail.setIjinName(ijinKeluarEntity.getKeterangan());
                        }
                    }
                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                    String newFormattedDate = newFormat.format(returnMesinAbsensiDetail.getScanDate());
                    returnMesinAbsensiDetail.setTanggal(newFormattedDate);
                    String ScanDate = String.valueOf(mesinAbsensiDetailEntity.getScanDate());
                    ScanDate = ScanDate.substring(0, 10);
                    String tgl = ScanDate.substring(8);
                    String bln = ScanDate.substring(5, 7);
                    String thn = ScanDate.substring(0, 4);
                    ScanDate = tgl + "-" + bln + "-" + thn;
                    if (ScanDate.equalsIgnoreCase(tanggal)) {
                        listOfResult.add(returnMesinAbsensiDetail);
                    }
                }
            }
        }
        logger.info("[AbsensiBoImpl.getByCriteriaMesin] end process <<<");

        return listOfResult;
    }

    @Override
    public List<AbsensiPegawai> cariAbseniSys(String nip, String tanggal, String statusabsensi) throws GeneralBOException {
        List<AbsensiPegawai> listAbsensi = new ArrayList<>();
        List<AbsensiPegawaiEntity> absensiPegawai = null;

        java.util.Date Awal = java.sql.Date.valueOf(tanggal + "-01");
        java.util.Date  Akhir = java.sql.Date.valueOf(tanggal + "-31");
        if(statusabsensi!=null){
            if(!statusabsensi.equalsIgnoreCase("")){
                absensiPegawai = absensiPegawaiDao.cariAbsensiSysStatusAbsensi(nip, Awal, Akhir, statusabsensi);
            }else {
                absensiPegawai = absensiPegawaiDao.cariAbsensiSys(nip, Awal, Akhir);
            }

        }
        else {
            absensiPegawai = absensiPegawaiDao.cariAbsensiSys(nip, Awal, Akhir);

        }

        if(absensiPegawai.size() > 0){
            for(AbsensiPegawaiEntity absenEntity: absensiPegawai){
                AbsensiPegawai absensi = new AbsensiPegawai();
                absensi.setTanggal(absenEntity.getTanggal());
                absensi.setStTanggal(CommonUtil.convertDateToString(absenEntity.getTanggal()));
                absensi.setJamMasuk(absenEntity.getJamMasuk());
                absensi.setJamKeluar(absenEntity.getJamKeluar());
                absensi.setStatusAbsensi(absenEntity.getStatusAbsensi());
                absensi.setStatusName(CommonUtil.statusName(absenEntity.getStatusAbsensi()));
                listAbsensi.add(absensi);
            }
        }
        return listAbsensi;
    }
    @Override
    public List<AbsensiPegawai> searchDetailLembur(String nip, String tanggal) throws GeneralBOException {
        List<AbsensiPegawai> listAbsensi = new ArrayList<>();
        List<AbsensiPegawaiEntity> absensiPegawai = null;

        Date dateTanggal = CommonUtil.convertToDate(tanggal);

        absensiPegawai = absensiPegawaiDao.searchDetailLembur(nip, dateTanggal);

        if(absensiPegawai.size() > 0){
            for(AbsensiPegawaiEntity absenEntity1: absensiPegawai){
                AbsensiPegawai absensi = new AbsensiPegawai();
                absensi.setRealisasiJamLembur(absenEntity1.getRealisasiJamLembur());
                absensi.setLamaLembur(absenEntity1.getLamaLembur());
                absensi.setJamLembur(absenEntity1.getJamLembur());
                absensi.setBiayaLembur(absenEntity1.getBiayaLembur());
                absensi.setLemburPerJam(absenEntity1.getBiayaLembur()/absenEntity1.getJamLembur());
                DecimalFormat df = new DecimalFormat("#.##");
                absensi.setLemburPerJam(Double.valueOf(df.format(absensi.getLemburPerJam())));
                listAbsensi.add(absensi);
            }
        }
        return listAbsensi;
    }

    public List <StrukturJabatan> getPerBagianSys() throws GeneralBOException {
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities  = strukturJabatanDao.getPerBagianDao();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        if(imStrukturJabatanEntities.size() > 0){
            strukturJabatanList.clear();
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){

                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();

                itemComboStrukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                itemComboStrukturJabatan.setParentId(imStrukturJabatanEntity.getParentId());
                itemComboStrukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                itemComboStrukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                itemComboStrukturJabatan.setPositionName(imStrukturJabatanEntity.getPositionName());
                itemComboStrukturJabatan.setPositionKelompokId(imStrukturJabatanEntity.getKelompokId());
                itemComboStrukturJabatan.setNip(imStrukturJabatanEntity.getNip());
                itemComboStrukturJabatan.setName(imStrukturJabatanEntity.getNamaPegawai());

                getListStruktur(imStrukturJabatanEntity.getStrukturJabatanId());

                String bagian[] = itemComboStrukturJabatan.getPositionName().split(" ");
                String bagian1 = "Bagian ";
                String bagian2 = "Bagian ";
                for (int a = 0; a < bagian.length; a++) {
                    if (bagian[a].equalsIgnoreCase("Bagian")) {
                        for (int b = a; b < bagian.length - 1; b++) {
                            bagian1 += " " + bagian[b + 1];
                        }
                        if (("Bagian  Pemeriksa Non Ops").equalsIgnoreCase(bagian1)||("Bagian  Pemeriksa Operasional").equalsIgnoreCase(bagian1)){
                            bagian1="Bagian  SPI";
                        }else if (("Bagian  Agronomi").equalsIgnoreCase(bagian1)||("Bagian  Sosial Ekonomi").equalsIgnoreCase(bagian1)){
                            bagian1="Bagian  Tanaman";
                        }else if (("Bagian  Risk Management dan GCG").equalsIgnoreCase(bagian1)){
                            bagian1="Sekretaris Perusahaan";
                        }
                        break;
                    }
                }
                if(("130").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("161").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("131").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("133").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("132").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("11").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("6").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("7").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("8").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())||("9").equalsIgnoreCase(itemComboStrukturJabatan.getPositionId())){
                }else{
                    itemComboStrukturJabatan.setBagian(bagian1);
                    strukturJabatans.add(itemComboStrukturJabatan);
                    boolean ada = false;
                    int noUrut = 1;
                    for(StrukturJabatan strukturJabatan: strukturJabatanList){
                        for(StrukturJabatan strukturJabatan1 : strukturJabatans){
                            if(strukturJabatan.getNip().equalsIgnoreCase(strukturJabatan1.getNip())){
                                ada = true;
                                break;
                            }
                        }
                        if(ada == false){
                            strukturJabatan.setBagian(bagian1);
                            strukturJabatan.setNoUrutBagian(noUrut);
                            strukturJabatans.add(strukturJabatan);
                            noUrut++;
                        }
                        ada = false;
                    }
                }
            }
        }
        Comparator<StrukturJabatan> comparator = new Comparator<StrukturJabatan>() {
            @Override
            public int compare(StrukturJabatan left, StrukturJabatan right) {
                if (left.getBagian()==right.getBagian()){
                    return 0;
                }
                if (left.getBagian()==null){
                    return -1;
                }
                if (right.getBagian()==null){
                    return 1;
                }
                return left.getBagian().compareTo(right.getBagian());
            }
        };
        Collections.sort(strukturJabatans, comparator);
        return strukturJabatans;
    }
    public List <StrukturJabatan> getPerBagianSisa() throws GeneralBOException {
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities  = strukturJabatanDao.getAllStrukturJabatan();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        if(imStrukturJabatanEntities.size() > 0){
            for (ImStrukturJabatanEntity imStrukturJabatanEntity:imStrukturJabatanEntities){
                StrukturJabatan strukturJabatan = new StrukturJabatan();
                strukturJabatan.setNip(imStrukturJabatanEntity.getNip());
                strukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                strukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                strukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                strukturJabatan.setPositionName(imStrukturJabatanEntity.getPositionName());
                strukturJabatan.setName(imStrukturJabatanEntity.getNamaPegawai());
                if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("130")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("161")){
                    strukturJabatan.setBagian("Satpam");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("131")){
                    strukturJabatan.setBagian("Pelayan");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("133")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("132")){
                    strukturJabatan.setBagian("Pengemudi");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("11")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("127")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("164")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("166")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("82")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("170")){
                    strukturJabatan.setBagian("Sekretaris Perusahaan");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("5")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("137")){
                    strukturJabatan.setBagian("Bagian  Tanaman");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("6")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("29")){
                    strukturJabatan.setBagian("Bagian  Teknik");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("7")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("36")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("193")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("172")){
                    strukturJabatan.setBagian("Bagian  Riset dan Pengembangan");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("8")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("189")){
                    strukturJabatan.setBagian("Bagian  Keuangan");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("9")){
                    strukturJabatan.setBagian("Bagian  SDM");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("10")){
                    strukturJabatan.setBagian("Bagian  SPI");
                    strukturJabatans.add(strukturJabatan);
                }
            }
        }
        return strukturJabatans;
    }
    private String getListStruktur(String id){
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        String hasil = "";
        imStrukturJabatanEntities = strukturJabatanDao.getIdStrukturJabatan(id);
        if(imStrukturJabatanEntities.size() > 0){
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){
                StrukturJabatan strukturJabatan1 = new StrukturJabatan();
                strukturJabatan1.setNip(imStrukturJabatanEntity.getNip());
                strukturJabatan1.setPositionName(imStrukturJabatanEntity.getPositionName());
                strukturJabatan1.setBagian(imStrukturJabatanEntity.getPositionName());

                hasil = imStrukturJabatanEntity.getStrukturJabatanId();
                strukturJabatanList.add(strukturJabatan1);
                if(imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL02") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL03") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL04") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL05") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL06") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL07")){
                    strukturJabatanList.add(strukturJabatan1);
                }
                getListStruktur(getListStruktur(hasil));
            }
        }
        return hasil;
    }

    @Override
    public AbsensiPegawai getJamLembur(double lamaLembur, String tipeHari){
        int j = 1;
        AbsensiPegawai result = new AbsensiPegawai();
        result.setHariKerja15((double) 0);
        result.setHariKerja20((double) 0);
        result.setHariLibur20((double) 0);
        result.setHariLibur30((double) 0);
        result.setHariLibur40((double) 0);
        if (lamaLembur>0){
            do{
                double pengaliJam = 0;
                double jamLembur = 0;
                Map hsCriteria5 = new HashMap();
                hsCriteria5.put("tipe_hari", tipeHari);
                hsCriteria5.put("jam_lembur", j);
                hsCriteria5.put("flag", "Y");
                List<JamLemburEntity> jamLemburEntityList;
                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                    if (lamaLembur>0&&lamaLembur<1) {
                        jamLembur = jamLembur + (lamaLembur*jamLemburEntity.getPengaliJamLembur());
                    }else{
                        jamLembur = jamLembur +jamLemburEntity.getPengaliJamLembur();
                    }

                    pengaliJam=jamLemburEntity.getPengaliJamLembur();
                }
                if (lamaLembur>0&&lamaLembur<1) {
                    lamaLembur = Double.valueOf(0);
                }else{
                    lamaLembur= lamaLembur-1;
                }
                if (tipeHari.equalsIgnoreCase("hari_kerja")) {
                    if (pengaliJam == 1.5) {
                        result.setHariKerja15(pengaliJam);
                    } else if (pengaliJam == 2) {
                        result.setHariKerja20(result.getHariKerja20() + jamLembur);
                    }
                }else if (tipeHari.equalsIgnoreCase("hari_libur")) {
                    if (pengaliJam==2){
                        result.setHariLibur20(result.getHariLibur20()+jamLembur);
                    }else if (pengaliJam==3){
                        result.setHariLibur30(result.getHariLibur30()+jamLembur);
                    }else if (pengaliJam==4){
                        result.setHariLibur40(result.getHariLibur40()+jamLembur);
                    }
                }
                j=j+1;
            }while (lamaLembur>0);
        }
        return result;
    }
    @Override
    public String cariStatusTidakMasuk(String nip, Date tanggal) {
        String status;
        List<ItCutiPegawaiEntity> itCutiPegawaiEntityList = cutiPegawaiDao.getListCutiByNipAndTanggal(nip, tanggal);
        List<IjinKeluarEntity> ijinKeluarEntityList = ijinKeluarDao.getListIjinByNipAndTanggal(nip, tanggal);
        List<SppdPerson> sppdPersonEntityList = sppdDao.getRealisasiSPPD(nip, tanggal);
        List<ItHrisTrainingEntity> trainingEntityList = trainingDao.getListTrainingByNipAndTanggal(tanggal);
        List<ItHrisTrainingPersonEntity> trainingPersonEntityList = new ArrayList<>();
        List<ItHrisTrainingPersonEntity> finalTraining = new ArrayList<>();
        if (trainingEntityList.size() != 0) {
            for (ItHrisTrainingEntity itHrisTrainingEntity : trainingEntityList) {
                trainingPersonEntityList = trainingPersonDao.getListTrainingByNipAndId(itHrisTrainingEntity.getTrainingId(), nip);
                finalTraining.addAll(trainingPersonEntityList);
            }
        }
        if (ijinKeluarEntityList.size() != 0) {
            status = "11";
        } else if (sppdPersonEntityList.size() != 0) {
            status = "12";
        } else if (finalTraining.size() != 0) {
            status = "13";
        }else if (itCutiPegawaiEntityList.size() != 0) {
            status = "08";
        } else {
            status = "00";
        }
        return status;
    }

    @Override
    public List<PegawaiTambahan> getDataPegawaiTambahan(String bagian) {
        List<PegawaiTambahanEntity> result = pegawaiTambahanDao.getPegawaiTambahanListBagian(bagian);
        List<PegawaiTambahan> finalResult = new ArrayList<>();

        for (PegawaiTambahanEntity pegawaiTambahanEntity : result){
            PegawaiTambahan data = new PegawaiTambahan();
            data.setPin(pegawaiTambahanEntity.getPin());
            data.setBagian(pegawaiTambahanEntity.getBagian());
            data.setNama(pegawaiTambahanEntity.getNama());
            data.setPegawaiTambahanId(pegawaiTambahanEntity.getPegawaiTambahanId());

            finalResult.add(data);
        }
        return finalResult;
    }

    public String cariStatusAbsensiHariBerikutnya(String jamMasuk,String jamPulang,String jamMasukDB,String jamPulangDB,String ijinJamKeluar,String ijinJamKembali,String libur){
        String status="-";
        Integer iJamMasuk=0;
        Integer iJamPulang=0;
        Integer iIjinKeluar=0;
        Integer iIjinKembali=0;
        Integer iJamMasukDB=0;
        Integer iJamPulangDB=0;
        if (jamMasuk!=null){
            if (!jamMasuk.equals("")){
                iJamMasuk = Integer.parseInt(jamMasuk.replace(":",""));
            }
        }
        if (jamPulang!=null){
            if (!jamPulang.equals("")){
                iJamPulang = Integer.parseInt(jamPulang.replace(":",""));
            }
        }
        if (!("Y").equalsIgnoreCase(libur)){
            iJamMasukDB = Integer.parseInt(jamMasukDB.replace(":",""));
            iJamPulangDB = Integer.parseInt(jamPulangDB.replace(":",""));
        }
        if (ijinJamKeluar!=null){
            if (!ijinJamKeluar.equals("")){
                iIjinKeluar = Integer.parseInt(ijinJamKeluar.replace(":",""));
            }
        }
        if (ijinJamKembali!=null){
            if (!ijinJamKembali.equals("")){
                iIjinKembali = Integer.parseInt(ijinJamKembali.replace(":",""));
            }
        }

        if (("Y").equalsIgnoreCase(libur)){
            status="15";
        }else{
            if (iJamMasuk<=iJamMasukDB&&iJamPulang<=iJamPulangDB&&jamMasuk!=null&&jamPulang!=null&&iJamPulang<iJamPulangDB-100){
                status="04";
            } else if (iJamMasuk<=iJamMasukDB&&iJamPulang<=iJamPulangDB&&jamMasuk!=null&&jamPulang!=null) {
                status = "01";
            }else if (jamPulang==null||jamMasuk==null){
                if(ijinJamKeluar!=null&&ijinJamKembali!=null){
                    if (iIjinKeluar<=iJamMasukDB||iIjinKembali>=iJamPulangDB){
                        status="05";
                    }else{
                        status="03";
                    }
                }else{
                    status="03";
                }
            }else if (iJamMasuk>iJamMasukDB) {
                if(ijinJamKeluar!=null&&ijinJamKembali!=null){
                    if (iIjinKeluar<=iJamMasukDB){
                        status="05";
                    }else{
                        status="02";
                    }
                }else{
                    status="02";
                }
            }else if (iJamPulang>iJamPulangDB){
                if(ijinJamKeluar!=null&&ijinJamKembali!=null){
                    if (iIjinKembali<=iJamPulangDB){
                        status="05";
                    }else{
                        status="14";
                    }
                }else{
                    status="14";
                }
            }
        }

        return status;
    }

    public String cariStatusAbsensi(String jamMasuk,String jamPulang,String jamMasukDB,String jamPulangDB,String ijinJamKeluar,String ijinJamKembali,String libur){
        String status="-";
        Integer iJamMasuk=0;
        Integer iJamPulang=0;
        Integer iIjinKeluar=0;
        Integer iIjinKembali=0;
        Integer iJamMasukDB=0;
        Integer iJamPulangDB=0;
        if (jamMasuk!=null){
            if (!jamMasuk.equals("")){
                iJamMasuk = Integer.parseInt(jamMasuk.replace(":",""));
            }
        }
        if (jamPulang!=null){
            if (!jamPulang.equals("")){
                iJamPulang = Integer.parseInt(jamPulang.replace(":",""));
            }
        }
        if (!("Y").equalsIgnoreCase(libur)){
            iJamMasukDB = Integer.parseInt(jamMasukDB.replace(":",""));
            iJamPulangDB = Integer.parseInt(jamPulangDB.replace(":",""));
        }
        if (ijinJamKeluar!=null){
            if (!ijinJamKeluar.equals("")){
                iIjinKeluar = Integer.parseInt(ijinJamKeluar.replace(":",""));
            }
        }
        if (ijinJamKembali!=null){
            if (!ijinJamKembali.equals("")){
                iIjinKembali = Integer.parseInt(ijinJamKembali.replace(":",""));
            }
        }

        if (("Y").equalsIgnoreCase(libur)){
            status="15";
        }else{
            if (iJamMasuk<=iJamMasukDB&&iJamPulang>=iJamPulangDB&&jamMasuk!=null&&jamPulang!=null&&iJamPulang>iJamPulangDB+100){
                status="04";
            } else if (iJamMasuk<=iJamMasukDB&&iJamPulang<=300&&jamMasuk!=null&&jamPulang!=null){
                status="04";
            } else if (iJamMasuk<=iJamMasukDB&&iJamPulang>=iJamPulangDB&&jamMasuk!=null&&jamPulang!=null) {
                status = "01";
            }else if (jamPulang==null||jamMasuk==null){
                if(ijinJamKeluar!=null&&ijinJamKembali!=null){
                    if (iIjinKeluar<=iJamMasukDB||iIjinKembali>=iJamPulangDB){
                        status="05";
                    }else{
                        status="03";
                    }
                }else{
                    status="03";
                }
            }else if (iJamMasuk>iJamMasukDB) {
                if(ijinJamKeluar!=null&&ijinJamKembali!=null){
                    if (iIjinKeluar<=iJamMasukDB){
                        status="05";
                    }else{
                        status="02";
                    }
                }else{
                    status="02";
                }
            }else if (iJamPulang<iJamPulangDB){
                if(ijinJamKeluar!=null&&ijinJamKembali!=null){
                    if (iIjinKembali>=iJamPulangDB){
                        status="05";
                    }else{
                        status="14";
                    }
                }else{
                    status="14";
                }
            }
        }

        return status;
    }
    public Double calcJamLembur(String jamAwal,String jamAkhir,String libur,int hariKerja) throws ParseException {
        logger.info("[AbsensiBoImpl.calcJamLembur] start process >>>");
        int iJamAwalKerja=Integer.parseInt(jamAwal.replace(":",""));
        int iJamAkhirKerja=Integer.parseInt(jamAkhir.replace(":",""));
        Double hasil= (double) 0;
        if (!("Y").equalsIgnoreCase(libur)){
            String sJamKerjaAwalDb = null,sJamKerjaAkhirDb=null;
            int iJamAwalDb = 0,iJamAkhirDb = 0;
            Map hsCriteria = new HashMap();
            hsCriteria.put("flag","Y");
            hsCriteria.put("hari",hariKerja);
            List<ImHrisJamKerja> jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria);
            for (ImHrisJamKerja jamKerja : jamKerjaList){
                sJamKerjaAwalDb=jamKerja.getJamAwalKerja();
                sJamKerjaAkhirDb=jamKerja.getJamAkhirKerja();
                iJamAwalDb=Integer.parseInt(sJamKerjaAwalDb.replace(":",""));
                iJamAkhirDb=Integer.parseInt(sJamKerjaAkhirDb.replace(":",""));
                break;
            }
            if (iJamAwalKerja<iJamAwalDb){
                hasil=hasil+CommonUtil.SubtractJamAwalDanJamAkhir (jamAwal,sJamKerjaAwalDb,"positif");
                if (iJamAkhirKerja>iJamAkhirDb){
                    hasil=hasil+CommonUtil.SubtractJamAwalDanJamAkhir (sJamKerjaAkhirDb,jamAkhir,"positif");
                }
            }
            if (iJamAkhirKerja<iJamAkhirDb&&iJamAkhirKerja>iJamAwalDb){
                hasil= (double) 0;
            }
            else if (iJamAwalKerja>=iJamAkhirDb){
                hasil=hasil+CommonUtil.SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
            }
        }else{
            hasil=CommonUtil.SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
        }
        return hasil;
    }
    public Double calcJamLemburShift(String jamAwal,String jamAkhir,String libur,String jamMasukDB,String jamPulangDB) throws ParseException {
        logger.info("[AbsensiBoImpl.calcJamLembur] start process >>>");
        int iJamAwalKerja=Integer.parseInt(jamAwal.replace(":",""));
        int iJamAkhirKerja=Integer.parseInt(jamAkhir.replace(":",""));
        Double hasil= (double) 0;
        if (!("Y").equalsIgnoreCase(libur)){
            String sJamKerjaAwalDb = null,sJamKerjaAkhirDb=null;
            int iJamAwalDb = 0,iJamAkhirDb = 0;
            sJamKerjaAwalDb=jamMasukDB;
            sJamKerjaAkhirDb=jamPulangDB;
            iJamAwalDb=Integer.parseInt(sJamKerjaAwalDb.replace(":",""));
            iJamAkhirDb=Integer.parseInt(sJamKerjaAkhirDb.replace(":",""));

            if (iJamAwalKerja<iJamAwalDb){
                hasil=hasil+CommonUtil.SubtractJamAwalDanJamAkhir (jamAwal,sJamKerjaAwalDb,"positif");
                if (iJamAkhirKerja>iJamAkhirDb){
                    hasil=hasil+CommonUtil.SubtractJamAwalDanJamAkhir (sJamKerjaAkhirDb,jamAkhir,"positif");
                }
            }
            if (iJamAwalKerja>=iJamAkhirDb){
                hasil=hasil+CommonUtil.SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
            }
        }else{
            hasil=CommonUtil.SubtractJamAwalDanJamAkhir (jamAwal,jamAkhir,"positif");
        }
        return hasil;
    }

    @Override
    public List<AbsensiTriwulanDTO> searchBiodataForTriwulan(String branchId, String nip, String stTanggalAwal, String stTanggalAkhir,String bagian){
        List<AbsensiTriwulanDTO> listFinalResult = new ArrayList<>();
        List<Biodata> daftarKaryawan = biodataDao.getBiodataListForUangMakan(branchId,"",bagian,nip);
        Date tanggalAwal = CommonUtil.convertToDate(stTanggalAwal);
        Date tanggalAkhir = CommonUtil.convertToDate(stTanggalAkhir);

        for (Biodata pegawai:daftarKaryawan){
            int days=0;
            if (tanggalAwal.after(pegawai.getTanggalAktif())){
                //menghitung hari kerja
                try {
                    List<ImLiburEntity> liburEntityList = liburDao.getLiburRange(tanggalAwal,tanggalAkhir);
                    days = CommonUtil.countDays(stTanggalAwal,stTanggalAkhir);
                    int jumlahLibur=liburEntityList.size();
                    days=days-jumlahLibur;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                String stTanggalAktif = CommonUtil.convertDateToString(pegawai.getTanggalAktif());
                //menghitung hari kerja
                try {
                    List<ImLiburEntity> liburEntityList = liburDao.getLiburRange(tanggalAwal,tanggalAkhir);
                    days = CommonUtil.countDays(stTanggalAktif,stTanggalAkhir);
                    int jumlahLibur=liburEntityList.size();
                    days=days-jumlahLibur;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            AbsensiTriwulanDTO result = new AbsensiTriwulanDTO();
            int masukSebelumJadwal=0,terlambatKurang60=0,terlambatLebih60=0,pulangTidakSesuai=0,masukKerja=0,tidakAbsenMasuk=0,tidakAbsenPulang=0,sakit=0,cuti=0,dinas=0,lain2=0,tanpaKeterangan=0,totalTidakMasuk=0;
            result.setNip(pegawai.getNip());
            result.setNama(pegawai.getNamaPegawai());
            result.setKetLain2("-");
            result.setBagian(pegawai.getBagianId());
            result.setBagianName(pegawai.getBagianName());

            List<ItPersonilPositionEntity> personilPositionEntityList = personilPositionDao.getPersonilPosition(pegawai.getNip());
            for (ItPersonilPositionEntity personil :personilPositionEntityList){
                result.setJabatan(personil.getPositionName());
            }

            List<AbsensiPegawaiEntity> absensiPegawaiEntityList;
            try {
                absensiPegawaiEntityList = absensiPegawaiDao.getDataForAbsensiTriwulan(pegawai.getNip(),tanggalAwal,tanggalAkhir);
            } catch (HibernateException e) {
                logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            for (AbsensiPegawaiEntity hasilAbsensi:absensiPegawaiEntityList){
                if (("01").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("02").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("03").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("04").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("05").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("06").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("07").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("14").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                    if (("01").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("04").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())||("05").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                        masukSebelumJadwal=masukSebelumJadwal+1;
                    }else if (("03").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                        if (hasilAbsensi.getJamMasuk()==null){
                            tidakAbsenMasuk=tidakAbsenMasuk+1;
                        }else{
                            tidakAbsenPulang=tidakAbsenPulang+1;
                        }
                    }else if(("02").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                        List<ImHrisJamKerja> jamKerjaList;
                        String jamMasukDB = null;
                        Double selisih = null;
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(hasilAbsensi.getTanggal());
                        String branch = CommonConstant.ID_KANPUS;
                        int day = cal.get(Calendar.DAY_OF_WEEK);
                        Map hsCriteria2 = new HashMap();
                        hsCriteria2.put("branch_id",branch);
                        hsCriteria2.put("hari",day);
                        hsCriteria2.put("flag","Y");
                        jamKerjaList = jamKerjaDao.getByCriteria(hsCriteria2);
                        for (ImHrisJamKerja jamKerja : jamKerjaList){
                            jamMasukDB= jamKerja.getJamAwalKerja();
                        }
                        try {
                            selisih = CommonUtil.SubtractJam(jamMasukDB,hasilAbsensi.getJamMasuk());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (selisih>=1){
                            terlambatLebih60=terlambatLebih60+1;
                        }else{
                            terlambatKurang60=terlambatKurang60+1;
                        }
                    }else if(("14").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())) {
                        pulangTidakSesuai=pulangTidakSesuai+1;
                    }
                    masukKerja=masukKerja+1;
                }else{
                    if (!("00").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                        if (("11").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                            List<IjinKeluarEntity> ijinKeluarEntityList = ijinKeluarDao.getListIjinByNipAndTanggal(pegawai.getNip(),hasilAbsensi.getTanggal());
                            for (IjinKeluarEntity dispensasi:ijinKeluarEntityList){
                                if (("IJ011").equalsIgnoreCase(dispensasi.getIjinId())){
                                    sakit=sakit+1;
                                }
                                else{
                                    lain2=lain2+1;
                                    result.setKetLain2(dispensasi.getIjinName());
                                }
                                break;
                            }
                            totalTidakMasuk=totalTidakMasuk+1;
                        }else if (("08").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                            cuti=cuti+1;
                            totalTidakMasuk=totalTidakMasuk+1;
                        }else if (("12").equalsIgnoreCase(hasilAbsensi.getStatusAbsensi())){
                            dinas=dinas+1;
                            totalTidakMasuk=totalTidakMasuk+1;
                        }
                    }else{
                        tanpaKeterangan=tanpaKeterangan+1;
                    }
                }
            }
            if (("TP01").equalsIgnoreCase(pegawai.getTipePegawai())){
                result.setJatahCuti("12");
            }else{
                result.setJatahCuti("0");
            }

            result.setHariKerja(String.valueOf(days));
            result.setKeteranganTerlambat("-");

            result.setMasukKurangJadwal(String.valueOf(masukSebelumJadwal));
            result.setTerlambatKurang60(String.valueOf(terlambatKurang60));
            result.setTerlambatLebih60(String.valueOf(terlambatLebih60));
            result.setPulangTidakSesuai(String.valueOf(pulangTidakSesuai));
            result.setMasukKerjaKantor(String.valueOf(masukKerja));
            result.setTidakAbsenMasuk(String.valueOf(tidakAbsenMasuk));
            result.setTidakAbsenPulang(String.valueOf(tidakAbsenPulang));
            result.setJmlSakit(String.valueOf(sakit));
            result.setJmlCuti(String.valueOf(cuti));
            result.setJmlDinas(String.valueOf(dinas));
            result.setJmlLain2(String.valueOf(lain2));
            result.setTotalTidakMasuk(String.valueOf(totalTidakMasuk));
            result.setTanpaKeterangan(String.valueOf(tanpaKeterangan));

            listFinalResult.add(result);
        }

        return listFinalResult;
    }

    @Override
    public AbsensiPegawai getJadwalShiftKerja ( String nip, Date tanggal){
        AbsensiPegawai result = new AbsensiPegawai();
        try {
//            result = absensiPegawaiDao.getSearchJadwalShift(nip, tanggal);
        } catch (HibernateException e) {
            logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        return result;
    }

    @Override
    public List<AbsensiPegawai> getHistoryAbsensiByMonth (String nip, String branchId, Date date){
        List<AbsensiPegawai> result = new ArrayList<>();
        SimpleDateFormat dateFormat  = new SimpleDateFormat("YYYY");
        String year = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("MM");
        String month = dateFormat.format(date);
        String firstDate = "01-" + month + "-" + year;
        String lastDate = CommonUtil.getLastDayOfMonth() + "-" + month + "-" + year;

        try {
          result = absensiPegawaiDao.getAbsensiByMonth(nip, branchId, firstDate, lastDate);
        } catch (HibernateException e){
            logger.error("[AbsensiBoImpl.getByCriteriaMesin] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        return result;
    }

    @Override
    public List<AbsensiPegawai> cronInquiry(AbsensiPegawai data){
        logger.info("[AbsensiBoImpl.cronInquiry] start process >>>");

        //validasi
        if (data.getTanggalUtil()==null||"".equalsIgnoreCase(data.getBranchId())||data.getBranchId()==null){
            String status="ERROR : [AbsensiBoImpl.cronInquiry] ";
            if (data.getTanggalUtil()==null){
                status += "Tanggal kosong \n";
            }
            if ("".equalsIgnoreCase(data.getBranchId())){
                status += "Unit Belum diisi \n";
            }
            if (data.getBranchId()==null){
                status += "Unit Belum diisi \n";
            }
            logger.error(status);
            throw new GeneralBOException(status);
        }

        java.util.Date tanggalSekarang = new java.util.Date();
        List<AbsensiPegawai> absensiPegawaiList = new ArrayList<>();
        List<ImBiodataEntity> biodataList;
        List<ImHrisJamKerja> jamKerjaList;
        List<ImLiburEntity> liburEntityList;
        String tahunGaji="";

        Calendar calTanggalInquiry = CommonUtil.dateToCalendar(data.getTanggalUtil());
        boolean hariLiburKerja = false;
        boolean hariLiburBesar = false;
        Integer hari = calTanggalInquiry.get(Calendar.DAY_OF_WEEK);

        try {
            biodataList=biodataDao.getDataBiodata(data.getNip(),"",data.getBranchId(),"",null,"","Y");
        } catch (HibernateException e){
            String status ="[AbsensiBoImpl.cronInquiry] ERROR : saat mengambil biodata";
            logger.error(status);
            throw new GeneralBOException(status);
        }

        try {
            liburEntityList=liburDao.getLiburByDate(data.getTanggalUtil());
        } catch (HibernateException e){
            String status ="[AbsensiBoImpl.cronInquiry] ERROR : saat mengambil jam kerja";
            logger.error(status);
            throw new GeneralBOException(status);
        }

        ImCompany company;

        try {
            company = companyDao.getCompanyInfo("Y");
            if (!("").equalsIgnoreCase(company.getPeriodeGaji())) {
                tahunGaji = company.getPeriodeGaji();
            } else {
                String status = "Error : tidak ditemukan periode gaji pada Company";
                logger.error("[PayrollBoImpl.dataAddPayroll] " + status);
                throw new GeneralBOException(status);
            }
        }catch (HibernateException e){
            logger.error("[PayrollBoImpl.dataAddPayroll] " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        if (liburEntityList.size()>0){
            hariLiburBesar=true;
        }

        //mengambil absensi per orang
        for (ImBiodataEntity biodata : biodataList){
            AbsensiPegawai absensiPegawai = new AbsensiPegawai();
            if ("".equalsIgnoreCase(biodata.getPin())||biodata.getPin()==null){}
            else{
                //cek apakah sudah masuk ke tabel absensi
                List<AbsensiPegawaiEntity> absensiPegawaiEntityList = new ArrayList<>();
                try {
                    absensiPegawaiEntityList = absensiPegawaiDao.searchExistingAbsensi(biodata.getNip(), CommonUtil.dateUtiltoDateSql(data.getTanggalUtil()));
                } catch (HibernateException e) {
                    String status ="[AbsensiBoImpl.cronInquiry] ERROR :"+e;
                    logger.error(status);
                    throw new GeneralBOException(status);
                }
                if (absensiPegawaiEntityList.size()==0){
                    absensiPegawai.setNip(biodata.getNip());
                    absensiPegawai.setNama(biodata.getNamaPegawai());
                    absensiPegawai.setTanggal(CommonUtil.dateUtiltoDateSql(data.getTanggalUtil()));
                    absensiPegawai.setBranchId(data.getBranchId());
                    absensiPegawai.setIjin("N");
                    absensiPegawai.setLembur("N");
                    absensiPegawai.setFlagUangMakan("N");
                    absensiPegawai.setTipeHari("hari_kerja");
                    absensiPegawai.setPin(biodata.getPin());
                    absensiPegawai.setDivisi(biodata.getDivisiName());
                    absensiPegawai.setStTanggal(CommonUtil.convertDateToString(data.getTanggalUtil()));
                    absensiPegawai.setPengajuanLembur((double)0);
                    absensiPegawai.setRealisasiJamLembur((double)0);
                    absensiPegawai.setJamLembur((double)0);
                    absensiPegawai.setBiayaLembur((double)0);
                    absensiPegawai.setFlagCutiGantiHari("N");
                    absensiPegawai.setTelat(BigInteger.valueOf(0));

                    absensiPegawai.setAction(data.getAction());
                    absensiPegawai.setFlag(data.getFlag());
                    absensiPegawai.setCreatedDate(data.getCreatedDate());
                    absensiPegawai.setLastUpdate(data.getLastUpdate());
                    absensiPegawai.setCreatedWho(data.getCreatedWho());
                    absensiPegawai.setLastUpdateWho(data.getLastUpdateWho());

                    if (hariLiburBesar){
                        absensiPegawai.setTipeHari("hari_libur");
                    }

                    //JIKA PEGAWAI KANTOR
                    if ("N".equalsIgnoreCase(biodata.getShift())){
                        Integer jamBatasAbsen=company.getJamBatasAbsen();
                        Timestamp tsJamMasukKantor = null;
                        Timestamp tsJamPulangKantor = null;
//                        Timestamp tsJamIstirahatAwalKantor = null;
                        Timestamp tsJamIstirahatAkhirKantor = null;

                        try {
                            jamKerjaList=jamKerjaDao.getJamKerjaByBranchAndHari(data.getBranchId(),hari);
                        } catch (HibernateException e){
                            String status ="[AbsensiBoImpl.cronInquiry] ERROR : saat mengambil jam kerja";
                            logger.error(status);
                            throw new GeneralBOException(status);
                        }

                        if (jamKerjaList.size()==0){
                            hariLiburKerja = true;
                        }else{
                            for (ImHrisJamKerja jamKerja : jamKerjaList){
                                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

                                String stTanggalMasukKantor = df.format(data.getTanggalUtil());
                                String stTanggalPulangKantor = df.format(data.getTanggalUtil());
                                String stTanggalIstirahatAwalKantor = df.format(data.getTanggalUtil());
                                String stTanggalIstirahatAkhirKantor = df.format(data.getTanggalUtil());

                                stTanggalMasukKantor = stTanggalMasukKantor+" "+jamKerja.getJamAwalKerja()+":00";
                                stTanggalPulangKantor = stTanggalPulangKantor+" "+jamKerja.getJamAkhirKerja()+":00";
//                                stTanggalIstirahatAwalKantor = stTanggalIstirahatAwalKantor+" "+jamKerja.getIstirahatAwal()+":00";
                                stTanggalIstirahatAkhirKantor = stTanggalIstirahatAkhirKantor+" "+jamKerja.getIstirahatAkhir()+":00";

                                try {
                                    java.util.Date dJamMasukKantor =  sdf.parse(stTanggalMasukKantor);
                                    java.util.Date dJamPulangKantor =  sdf.parse(stTanggalPulangKantor);
//                                    java.util.Date dJamIstirahatAwalKantor =  sdf.parse(stTanggalIstirahatAwalKantor);
                                    java.util.Date dJamIstirahatAkhirKantor =  sdf.parse(stTanggalIstirahatAkhirKantor);

                                    tsJamMasukKantor = new Timestamp(dJamMasukKantor.getTime());
                                    tsJamPulangKantor = new Timestamp(dJamPulangKantor.getTime());
//                                    tsJamIstirahatAwalKantor = new Timestamp(dJamIstirahatAwalKantor.getTime());
                                    tsJamIstirahatAkhirKantor = new Timestamp(dJamIstirahatAkhirKantor.getTime());

                                } catch (ParseException e) {
                                    String status ="[AbsensiBoImpl.cronInquiry] ERROR :"+e;
                                    logger.error(status);
                                    throw new GeneralBOException(status);
                                }
                            }
                        }

                        if (hariLiburKerja){
                            absensiPegawai.setTipeHari("hari_libur");
                        }

                        Calendar calJamAwalScan = Calendar.getInstance();
                        Calendar calJamAkhirScan = Calendar.getInstance();
                        calJamAwalScan.setTime(data.getTanggalUtil());
                        calJamAkhirScan.setTime(data.getTanggalUtil());

                        calJamAwalScan.set(Calendar.HOUR_OF_DAY, jamBatasAbsen);
                        calJamAwalScan.set(Calendar.MINUTE, 0);
                        calJamAwalScan.set(Calendar.SECOND, 0);
                        calJamAwalScan.set(Calendar.MILLISECOND, 0);

                        calJamAkhirScan.add(Calendar.DAY_OF_YEAR,1);
                        calJamAkhirScan.set(Calendar.HOUR_OF_DAY, jamBatasAbsen);
                        calJamAkhirScan.set(Calendar.MINUTE, 0);
                        calJamAkhirScan.set(Calendar.SECOND, 0);
                        calJamAkhirScan.set(Calendar.MILLISECOND, 0);

                        java.sql.Date tanggalBesok = new java.sql.Date(calJamAkhirScan.getTimeInMillis());
                        java.sql.Date tanggalAwal = new java.sql.Date(calJamAwalScan.getTimeInMillis());
                        java.sql.Timestamp tsTanggalAwal = new java.sql.Timestamp(tanggalAwal.getTime());
                        java.sql.Timestamp tsTanggalBesok = new java.sql.Timestamp(tanggalBesok.getTime());
                        java.sql.Timestamp tsJamAwalFinger = null;
                        java.sql.Timestamp tsJamAkhirFinger = null;

                        //mengambil  data absensi
                        List<MesinAbsensiDetailEntity> mesinAbsensiDetailEntityList = new ArrayList<>();

                        try {
                            mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getAllDetailWithDateAndPin(biodata.getPin(),tsTanggalAwal,tsTanggalBesok,data.getBranchId());
                        } catch (HibernateException e){
                            String status ="[AbsensiBoImpl.cronInquiry] ERROR : saat data mesin absensi";
                            logger.error(status);
                            throw new GeneralBOException(status);
                        }

                        //JIKA HARI KERJA
                        if ("hari_kerja".equalsIgnoreCase(absensiPegawai.getTipeHari())){
                            //jika tidak ada data absensi
                            if (mesinAbsensiDetailEntityList.size()==0){
                                absensiPegawai.setStatusAbsensi(cariStatusTidakMasuk(biodata.getNip(), CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())));
                            } else{
                                for (MesinAbsensiDetailEntity mesinAbsensiDetailEntity : mesinAbsensiDetailEntityList){
                                    if (mesinAbsensiDetailEntity.getScanDate().compareTo(tsJamIstirahatAkhirKantor)<0){
                                        if (tsJamAwalFinger==null || mesinAbsensiDetailEntity.getScanDate().compareTo(tsJamAwalFinger)<0){
                                            tsJamAwalFinger = mesinAbsensiDetailEntity.getScanDate();
                                        }
                                    } else {
                                        if (tsJamAkhirFinger==null || mesinAbsensiDetailEntity.getScanDate().compareTo(tsJamAkhirFinger)>0){
                                            tsJamAkhirFinger= mesinAbsensiDetailEntity.getScanDate();
                                        }
                                    }
                                }

                                //set status masuk
                                if (tsJamAwalFinger==null||tsJamAkhirFinger==null){
                                    absensiPegawai.setStatusAbsensi("03");
                                }else if (tsJamAwalFinger.compareTo(tsJamMasukKantor)>0){
                                    absensiPegawai.setTelat(BigInteger.valueOf(CommonUtil.compareTwoTimeStamps(tsJamAwalFinger,tsJamMasukKantor,"menit")));
                                    absensiPegawai.setStatusAbsensi("02");
                                }else if (tsJamAkhirFinger.compareTo(tsJamPulangKantor)<0){
                                    absensiPegawai.setStatusAbsensi("14");
                                }else if (tsJamAwalFinger.compareTo(tsJamMasukKantor)<=0&&tsJamAkhirFinger.compareTo(tsJamPulangKantor)>=0){
                                    if (CommonUtil.compareTwoTimeStamps(tsJamAkhirFinger,tsJamPulangKantor,"jam")>1||CommonUtil.compareTwoTimeStamps(tsJamMasukKantor,tsJamAwalFinger,"jam")>1){
                                        absensiPegawai.setStatusAbsensi("04");
                                    }else{
                                        absensiPegawai.setStatusAbsensi("01");
                                    }
                                }else{
                                    String status ="[AbsensiBoImpl.cronInquiry] ERROR : ada case yang belum tercover";
                                    logger.error(status);
                                    throw new GeneralBOException(status);
                                }

                                //set jam masuk dan jam pulang
                                DateFormat format = new SimpleDateFormat("HH:mm");
                                if (tsJamAwalFinger!=null){
                                    absensiPegawai.setJamMasuk(format.format(tsJamAwalFinger));
                                }
                                if (tsJamAkhirFinger!=null){
                                    absensiPegawai.setJamPulang(format.format(tsJamAkhirFinger));
                                }

                                //ambil lembur hari libur
                                List<LemburEntity> lemburEntityList = new ArrayList<>();
                                try{
                                    lemburEntityList = lemburDao.getListLemburByNipAndTanggal(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil()));
                                } catch (HibernateException e) {
                                    logger.error("[AbsensiBoImpl.cronInquiry] Error " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                }

                                if (lemburEntityList.size()>0) {
                                    if (tsJamAwalFinger != null && tsJamAkhirFinger != null) {
                                        absensiPegawai.setLembur("Y");
                                        Timestamp tsFinalAwalLembur = null;
                                        Timestamp tsFinalAkhirLembur = null;

                                        Timestamp tsJamAwalLembur = null;
                                        Timestamp tsJamAkhirLembur = null;

                                        Double lamaLembur = (double)0;
                                        Double realisasiLembur = (double)0;
                                        Double realisasiFinger = (double)0;
                                        String jenisLembur = "";

                                        for (LemburEntity lemburEntity : lemburEntityList) {
                                            jenisLembur = lemburEntity.getTipeLembur();
                                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                                            String stTanggalAwalLembur = df.format(data.getTanggalUtil());
                                            String stTanggalAkhirLembur = df.format(data.getTanggalUtil());

                                            stTanggalAwalLembur = stTanggalAwalLembur + " " + lemburEntity.getJamAwal() + ":00";
                                            stTanggalAkhirLembur = stTanggalAkhirLembur + " " + lemburEntity.getJamAkhir() + ":00";

                                            lamaLembur = lamaLembur+lemburEntity.getLamaJam();

                                            try {
                                                java.util.Date dJamAwalLembur = sdf.parse(stTanggalAwalLembur);
                                                java.util.Date dJamAkhirLembur = sdf.parse(stTanggalAkhirLembur);

                                                if (tsJamAwalLembur != null && tsJamAkhirLembur != null) {
                                                    if (new Timestamp(dJamAwalLembur.getTime()).compareTo(tsJamAwalLembur) < 0) {
                                                        tsJamAwalLembur = new Timestamp(dJamAwalLembur.getTime());
                                                    }
                                                    if (new Timestamp(dJamAkhirLembur.getTime()).compareTo(tsJamAkhirLembur) > 0) {
                                                        tsJamAkhirLembur = new Timestamp(dJamAkhirLembur.getTime());
                                                    }
                                                } else {
                                                    tsJamAwalLembur = new Timestamp(dJamAwalLembur.getTime());
                                                    tsJamAkhirLembur = new Timestamp(dJamAkhirLembur.getTime());
                                                }
                                            } catch (ParseException e) {
                                                String status = "[AbsensiBoImpl.cronInquiry] ERROR :" + e;
                                                logger.error(status);
                                                throw new GeneralBOException(status);
                                            }
                                        }

                                        if (tsJamAwalFinger.compareTo(tsJamAwalLembur)<0){
                                            tsFinalAwalLembur=tsJamAwalLembur;
                                        }else{
                                            tsFinalAwalLembur=tsJamAwalFinger;
                                        }

                                        if (tsJamAkhirFinger.compareTo(tsJamAkhirLembur)>0){
                                            tsFinalAkhirLembur=tsJamAkhirLembur;
                                        }else{
                                            tsFinalAkhirLembur=tsJamAkhirFinger;
                                        }

                                        //hitung lama lembur berdasarkan final lembur aslinya
                                        try {
                                            if (tsFinalAwalLembur.compareTo(tsJamMasukKantor)<0&&tsFinalAkhirLembur.compareTo(tsJamPulangKantor)>0){
                                                realisasiLembur = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsFinalAwalLembur),format.format(tsJamMasukKantor),"positif");
                                                realisasiLembur = realisasiLembur+CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsJamPulangKantor),format.format(tsFinalAkhirLembur),"positif");
                                            }else{
                                                realisasiLembur = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsFinalAwalLembur),format.format(tsFinalAkhirLembur),"positif");
                                            }

                                            if (tsJamAwalFinger.compareTo(tsJamMasukKantor)<0&&tsJamAkhirFinger.compareTo(tsJamPulangKantor)>0){
                                                realisasiFinger = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsJamAwalFinger),format.format(tsJamMasukKantor),"positif");
                                                realisasiFinger = realisasiFinger+CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsJamPulangKantor),format.format(tsJamAkhirFinger),"positif");
                                            }else {
                                                realisasiFinger = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsJamAwalFinger),format.format(tsJamAkhirFinger),"positif");
                                            }
                                        } catch (ParseException e) {
                                            String status = "[AbsensiBoImpl.cronInquiry] ERROR :" + e;
                                            logger.error(status);
                                            throw new GeneralBOException(status);
                                        }

                                        // menghitung upah lembur
                                        List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                                        Map hsCriteria4 = new HashMap();
                                        hsCriteria4.put("tipe_pegawai_id", biodata.getTipePegawai());
                                        hsCriteria4.put("flag", "Y");
                                        double faktor = 0;
                                        Double upahLembur = 0d;
                                        Double gapok = 0d;
                                        Double sankhus = 0d;

                                        try{
                                            pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                                        } catch (HibernateException e) {
                                            logger.error("[AbsensiBoImpl.cronInquiry] Error " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                                            faktor = pengaliFaktorLemburEntity.getFaktor();
                                        }

                                        hsCriteria4 = new HashMap();
                                        hsCriteria4.put("golongan_id", biodata.getGolongan());
                                        hsCriteria4.put("point", (int) Math.round(biodata.getPoint()));
                                        hsCriteria4.put("tahun", tahunGaji);
                                        hsCriteria4.put("flag", "Y");
                                        List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                                        List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                                        if (biodata.getTipePegawai().equalsIgnoreCase("TP01")){
                                            payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(biodata.getGolongan(),tahunGaji);
                                            for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                                                gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                                                sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                                            }
                                        }else if (biodata.getTipePegawai().equalsIgnoreCase("TP03")){
                                            payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(biodata.getGolongan(),tahunGaji);
                                            for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                                                gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                                                sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                                            }
                                        }

                                        double jamLembur = 0;
                                        double finalLamaLembur = 0;
                                        double finalLamaLembur2 = 0;

                                        if (lamaLembur<realisasiLembur){
                                            finalLamaLembur = lamaLembur;
                                            finalLamaLembur2 = lamaLembur;
                                        }else{
                                            finalLamaLembur = realisasiLembur;
                                            finalLamaLembur2 = realisasiLembur;
                                        }

                                        int j = 1;
                                        if (finalLamaLembur>0){
                                            do{
                                                if (finalLamaLembur>0&&finalLamaLembur<1){
                                                    Map hsCriteria5 = new HashMap();
                                                    hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                    hsCriteria5.put("jam_lembur", j);
                                                    hsCriteria5.put("flag", "Y");
                                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                    jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                        jamLembur = jamLembur + (finalLamaLembur*2);
                                                    }
                                                    finalLamaLembur= (double) 0;
                                                }else{
                                                    Map hsCriteria5 = new HashMap();
                                                    hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                    hsCriteria5.put("jam_lembur", j);
                                                    hsCriteria5.put("flag", "Y");
                                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                    jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                        jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                                    }
                                                    finalLamaLembur= finalLamaLembur-1;
                                                }
                                                j=j+1;
                                            }while (finalLamaLembur>0);
                                        }
                                        Double peralihan = 0d;
//                                        peralihan = getTunjPeralihan(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
//                                        upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;

                                        // Sigit 2020-11-26, Pencarian prosentase gaji dari unutk perhitungan upah lembur perjam, START
                                        BigDecimal prosentase = new BigDecimal(0);
//                                        List<ImHrisMappingPersenGaji> mappingPersenGajiList = mappingPersenGajiDao.getListMappingPersenGaji(biodata.getJenisPegawai());
                                        List<ImHrisMappingPersenGaji> mappingPersenGajiList =new ArrayList<>();
                                        String personPosition = "";

                                        try {
                                            personPosition = personilPositionDao.getJenisPegawaiByNip(biodata.getNip());
                                        } catch (HibernateException e) {
                                            logger.error("[AbsensiBoImpl.cronInquiry] Error " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        try {
                                            mappingPersenGajiList = mappingPersenGajiDao.getListMappingPersenGaji(personPosition);
                                        } catch (HibernateException e) {
                                            logger.error("[AbsensiBoImpl.cronInquiry] Error " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }

                                        if (mappingPersenGajiList.size() > 0){
                                            for (ImHrisMappingPersenGaji persenGaji : mappingPersenGajiList){

                                                if (persenGaji.getPresentase() != null){
                                                    BigDecimal bdPersenGaji = new BigDecimal(persenGaji.getPresentase());
                                                    prosentase = bdPersenGaji.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                                                }

                                            }
                                        }
                                        // END
                                        // Sigit, 2020-11-26 perubahan Perhitungan upah biaya lembur per jam, START
                                        peralihan = getPeralihanGapok(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
                                        Double totalGapokPeralihan = gapok+peralihan;
                                        BigDecimal bGapokPeralihan = new BigDecimal(totalGapokPeralihan);
                                        BigDecimal bFaktor = new BigDecimal(faktor);
                                        BigDecimal bJamLembur = new BigDecimal(jamLembur);

                                        BigDecimal bUpahLembur = bGapokPeralihan.multiply(prosentase).multiply(bFaktor).multiply(bJamLembur);
                                        upahLembur = bUpahLembur.doubleValue();
                                        // END


                                        String upahNew = "";
                                        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                                        formatRp.setCurrencySymbol("Rp. ");
                                        formatRp.setGroupingSeparator('.');

                                        kursIndonesia.setDecimalFormatSymbols(formatRp);
                                        upahNew = kursIndonesia.format(upahLembur);

                                        absensiPegawai.setLembur("Y");
                                        absensiPegawai.setPengajuanLembur(lamaLembur);
                                        absensiPegawai.setRealisasiJamLembur(realisasiFinger);
                                        absensiPegawai.setJamLembur(jamLembur);
                                        absensiPegawai.setLamaLembur(finalLamaLembur2);
                                        absensiPegawai.setBiayaLembur(upahLembur);
                                        absensiPegawai.setStBiayaLembur(upahNew);
                                        absensiPegawai.setJenisLembur(jenisLembur);
                                        absensiPegawai.setAwalLembur(absensiPegawai.getJamMasuk());
                                        absensiPegawai.setSelesaiLembur(absensiPegawai.getJamPulang());
                                    }
                                }
                            }
                        // JIKA HARI LIBUR
                        }else{
                            boolean liburGantiHari = false;
                            for (MesinAbsensiDetailEntity mesinAbsensiDetailEntity : mesinAbsensiDetailEntityList){
                                if (tsJamAwalFinger==null || mesinAbsensiDetailEntity.getScanDate().compareTo(tsJamAwalFinger)<0){
                                    tsJamAwalFinger = mesinAbsensiDetailEntity.getScanDate();
                                }else if (tsJamAkhirFinger==null || mesinAbsensiDetailEntity.getScanDate().compareTo(tsJamAkhirFinger)>0){
                                    tsJamAkhirFinger= mesinAbsensiDetailEntity.getScanDate();
                                }
                            }
                            //set jam masuk dan jam pulang
                            DateFormat format = new SimpleDateFormat("HH:mm");
                            if (tsJamAwalFinger!=null){
                                absensiPegawai.setJamMasuk(format.format(tsJamAwalFinger));
                            }
                            if (tsJamAkhirFinger!=null){
                                absensiPegawai.setJamPulang(format.format(tsJamAkhirFinger));
                            }

                            if (tsJamAwalFinger!=null||tsJamAkhirFinger!=null){
                                absensiPegawai.setStatusAbsensi("15");

                                //ambil ganti hari libur
                                //menambah cuti tahunan +1
                                List<IjinKeluarEntity> ijinKeluarEntityList = ijinKeluarDao.getListIjinByNipAndTanggal(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil()));
                                if (ijinKeluarEntityList.size()>0){
                                    liburGantiHari=true;
                                    absensiPegawai.setFlagCutiGantiHari("Y");
                                }
                            }

                            //ambil lembur hari libur
                            if (!liburGantiHari){
                                List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil()));
                                if (lemburEntityList.size()>0) {
                                    if (tsJamAwalFinger != null && tsJamAkhirFinger != null) {
                                        absensiPegawai.setLembur("Y");
                                        Timestamp tsFinalAwalLembur = null;
                                        Timestamp tsFinalAkhirLembur = null;

                                        Timestamp tsJamAwalLembur = null;
                                        Timestamp tsJamAkhirLembur = null;

                                        Double lamaLembur = (double)0;
                                        Double realisasiLembur = (double)0;
                                        Double realisasiFinger = (double)0;
                                        String jenisLembur = "";

                                        for (LemburEntity lemburEntity : lemburEntityList) {
                                            jenisLembur = lemburEntity.getTipeLembur();
                                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                                            String stTanggalAwalLembur = df.format(data.getTanggalUtil());
                                            String stTanggalAkhirLembur = df.format(data.getTanggalUtil());

                                            stTanggalAwalLembur = stTanggalAwalLembur + " " + lemburEntity.getJamAwal() + ":00";
                                            stTanggalAkhirLembur = stTanggalAkhirLembur + " " + lemburEntity.getJamAkhir() + ":00";

                                            lamaLembur = lamaLembur+lemburEntity.getLamaJam();

                                            try {
                                                java.util.Date dJamAwalLembur = sdf.parse(stTanggalAwalLembur);
                                                java.util.Date dJamAkhirLembur = sdf.parse(stTanggalAkhirLembur);

                                                if (tsJamAwalLembur != null && tsJamAkhirLembur != null) {
                                                    if (new Timestamp(dJamAwalLembur.getTime()).compareTo(tsJamAwalLembur) < 0) {
                                                        tsJamAwalLembur = new Timestamp(dJamAwalLembur.getTime());
                                                    }
                                                    if (new Timestamp(dJamAkhirLembur.getTime()).compareTo(tsJamAkhirLembur) > 0) {
                                                        tsJamAkhirLembur = new Timestamp(dJamAkhirLembur.getTime());
                                                    }
                                                } else {
                                                    tsJamAwalLembur = new Timestamp(dJamAwalLembur.getTime());
                                                    tsJamAkhirLembur = new Timestamp(dJamAkhirLembur.getTime());
                                                }
                                            } catch (ParseException e) {
                                                String status = "[AbsensiBoImpl.cronInquiry] ERROR :" + e;
                                                logger.error(status);
                                                throw new GeneralBOException(status);
                                            }
                                        }

                                        if (tsJamAwalFinger.compareTo(tsJamAwalLembur)<0){
                                            tsFinalAwalLembur=tsJamAwalLembur;
                                        }else{
                                            tsFinalAwalLembur=tsJamAwalFinger;
                                        }

                                        if (tsJamAkhirFinger.compareTo(tsJamAkhirLembur)>0){
                                            tsFinalAkhirLembur=tsJamAkhirLembur;
                                        }else{
                                            tsFinalAkhirLembur=tsJamAkhirFinger;
                                        }

                                        //hitung lama lembur berdasarkan final lembur aslinya
                                        try {
                                            realisasiLembur = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsFinalAwalLembur),format.format(tsFinalAkhirLembur),"positif");
                                            realisasiFinger = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsJamAwalFinger),format.format(tsJamAkhirFinger),"positif");
                                        } catch (ParseException e) {
                                            String status = "[AbsensiBoImpl.cronInquiry] ERROR :" + e;
                                            logger.error(status);
                                            throw new GeneralBOException(status);
                                        }

                                        // menghitung upah lembur
                                        List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                                        Map hsCriteria4 = new HashMap();
                                        hsCriteria4.put("tipe_pegawai_id", biodata.getTipePegawai());
                                        hsCriteria4.put("flag", "Y");
                                        double faktor = 0;
                                        Double upahLembur = 0d;
                                        Double gapok = 0d;
                                        Double sankhus = 0d;
                                        pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                                        for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                                            faktor = pengaliFaktorLemburEntity.getFaktor();
                                        }

                                        hsCriteria4 = new HashMap();
                                        hsCriteria4.put("golongan_id", biodata.getGolongan());
                                        hsCriteria4.put("point", (int) Math.round(biodata.getPoint()));
                                        hsCriteria4.put("tahun", tahunGaji);
                                        hsCriteria4.put("flag", "Y");
                                        List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                                        List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                                        if (biodata.getTipePegawai().equalsIgnoreCase("TP01")){
                                            payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(biodata.getGolongan(),tahunGaji);
                                            for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                                                gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                                                sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                                            }
                                        }else if (biodata.getTipePegawai().equalsIgnoreCase("TP03")){
                                            payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(biodata.getGolongan(),tahunGaji);
                                            for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                                                gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                                                sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                                            }
                                        }

                                        double jamLembur = 0;
                                        double finalLamaLembur = 0;
                                        double finalLamaLembur2 = 0;

                                        if (lamaLembur<realisasiLembur){
                                            finalLamaLembur = lamaLembur;
                                            finalLamaLembur2 = lamaLembur;
                                        }else{
                                            finalLamaLembur = realisasiLembur;
                                            finalLamaLembur2 = realisasiLembur;
                                        }


                                        int j = 1;
                                        if (finalLamaLembur>0){
                                            do{
                                                if (finalLamaLembur>0&&finalLamaLembur<1){
                                                    Map hsCriteria5 = new HashMap();
                                                    hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                    hsCriteria5.put("jam_lembur", j);
                                                    hsCriteria5.put("flag", "Y");
                                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                    jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                        jamLembur = jamLembur + (finalLamaLembur*2);
                                                    }
                                                    finalLamaLembur= (double) 0;
                                                }else{
                                                    Map hsCriteria5 = new HashMap();
                                                    hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                    hsCriteria5.put("jam_lembur", j);
                                                    hsCriteria5.put("flag", "Y");
                                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                    jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                        jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                                    }
                                                    finalLamaLembur= finalLamaLembur-1;
                                                }
                                                j=j+1;
                                            }while (finalLamaLembur>0);
                                        }
                                        Double peralihan = 0d;
//                                        peralihan = getTunjPeralihan(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
//                                        upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;

                                        // Sigit 2020-12-23, Pencarian prosentase gaji dari unutk perhitungan upah lembur perjam, START
                                        BigDecimal prosentase = new BigDecimal(0);

                                        String personJenisPegawai = "";
                                        try{
                                            personJenisPegawai = personilPositionDao.getJenisPegawaiByNip(biodata.getNip());
                                        } catch (HibernateException e) {
                                            logger.error("[AbsensiBoImpl.cronInquiry] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when retrieving Personil Jenis Pegawai by NIP, " + e.getMessage());
                                        }

                                        //RAKA-19JAN2021 ==> Mendapatkan nama jenis pegawai
                                        List<ImHrisJenisPegawaiEntity> personJenisPegawaiList = new ArrayList<>();
                                        try{
                                            Map hsCriteria6 = new HashMap();
                                            hsCriteria6.put("jenis_pegawai_id", personJenisPegawai);
                                            hsCriteria6.put("flag", "Y");
                                            personJenisPegawaiList = jenisPegawaiDao.getByCriteria(hsCriteria6);
                                        } catch (HibernateException e) {
                                            logger.error("[AbsensiBoImpl.cronInquiry] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when retrieving Jenis Pegawai by Criteria, " + e.getMessage());
                                        }

                                        String myJenisPegawai = "";
                                        for(ImHrisJenisPegawaiEntity jenisPegawai : personJenisPegawaiList){
                                            myJenisPegawai = jenisPegawai.getJenisPegawaiName();
                                        }
                                        //RAKA-end

                                        List<ImHrisMappingPersenGaji> mappingPersenGajiList = new ArrayList<>();
                                        try {
                                            mappingPersenGajiList = mappingPersenGajiDao.getListMappingPersenGaji(myJenisPegawai);
                                        } catch (HibernateException e) {
                                            logger.error("[AbsensiBoImpl.cronInquiry] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when retrieving List Mapping Persen Gaji, " + e.getMessage());
                                        }

                                        if (mappingPersenGajiList.size() > 0){
                                            for (ImHrisMappingPersenGaji persenGaji : mappingPersenGajiList){

                                                if (persenGaji.getPresentase() != null){
                                                    BigDecimal bdPersenGaji = new BigDecimal(persenGaji.getPresentase());
                                                    prosentase = bdPersenGaji.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                                                }

                                            }
                                        }
                                        // END
                                        // Sigit, 2020-12-23 perubahan Perhitungan upah biaya lembur per jam, START
                                        peralihan = getPeralihanGapok(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
                                        Double totalGapokPeralihan = gapok+peralihan;
                                        BigDecimal bGapokPeralihan = new BigDecimal(totalGapokPeralihan);
                                        BigDecimal bFaktor = new BigDecimal(faktor);
                                        BigDecimal bJamLembur = new BigDecimal(jamLembur);

                                        BigDecimal bUpahLembur = bGapokPeralihan.multiply(prosentase).multiply(bFaktor).multiply(bJamLembur);
                                        upahLembur = bUpahLembur.doubleValue();
                                        // END

                                        String upahNew = "";
                                        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                                        formatRp.setCurrencySymbol("Rp. ");
                                        formatRp.setGroupingSeparator('.');

                                        kursIndonesia.setDecimalFormatSymbols(formatRp);
                                        upahNew = kursIndonesia.format(upahLembur);

                                        absensiPegawai.setLembur("Y");
                                        absensiPegawai.setPengajuanLembur(lamaLembur);
                                        absensiPegawai.setRealisasiJamLembur(realisasiFinger);
                                        absensiPegawai.setJamLembur(jamLembur);
                                        absensiPegawai.setLamaLembur(finalLamaLembur2);
                                        absensiPegawai.setBiayaLembur(upahLembur);
                                        absensiPegawai.setStBiayaLembur(upahNew);
                                        absensiPegawai.setJenisLembur(jenisLembur);
                                        absensiPegawai.setAwalLembur(absensiPegawai.getJamMasuk());
                                        absensiPegawai.setSelesaiLembur(absensiPegawai.getJamPulang());
                                    }
                                }
                            }
                        }

                    //JIKA PEGAWAI SHIFT
                    }else{
                        Date tanggalInquiry = CommonUtil.dateUtiltoDateSql(data.getTanggalUtil());
                        List<AbsensiPegawai> jamKerjaShift = absensiPegawaiDao.getSearchJadwalShift(biodata.getNip(),tanggalInquiry);
                        List<AbsensiPegawai> jamKerjaShiftOnCall = absensiPegawaiDao.getSearchJadwalShiftOnCall(biodata.getNip(),tanggalInquiry);
                        //ada jadwal
                        if (jamKerjaShift.size()!=0){
                            int jadwalke = 1;
                            for (AbsensiPegawai jamKerja : jamKerjaShift){
                                if (jamKerja.getJamMasuk()!=null&&jamKerja.getJamPulang()!=null) {
                                    boolean lembur=false;
                                    Timestamp tsTanggalAwalShift;
                                    Timestamp tsTanggalBesokShift;
                                    Timestamp tsTanggalAwalFinalShift;
                                    Timestamp tsTanggalBesokFinalShift;
                                    Timestamp tsTanggalAwalLembur = null;
                                    Timestamp tsTanggalBesokLembur = null;
                                    Timestamp tsJamAwalFinger =null;
                                    Timestamp tsJamAkhirFinger=null;

                                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

                                    String stTanggalMasukShift = df.format(data.getTanggalUtil());
                                    String stTanggalPulangShift = df.format(data.getTanggalUtil());

                                    stTanggalMasukShift = stTanggalMasukShift+" "+jamKerja.getJamMasuk()+":00";
                                    stTanggalPulangShift = stTanggalPulangShift+" "+jamKerja.getJamPulang()+":00";

                                    try {
                                        java.util.Date dJamMasukShift = sdf.parse(stTanggalMasukShift);
                                        java.util.Date dJamPulangShift = sdf.parse(stTanggalPulangShift);

                                        tsTanggalAwalShift = new Timestamp(dJamMasukShift.getTime());
                                        tsTanggalBesokShift = new Timestamp(dJamPulangShift.getTime());
                                        tsTanggalAwalFinalShift = new Timestamp(dJamMasukShift.getTime());
                                        tsTanggalBesokFinalShift = new Timestamp(dJamPulangShift.getTime());

                                    }catch (Exception e){
                                        String status ="[AbsensiBoImpl.cronInquiry] ERROR : "+e;
                                        logger.error(status);
                                        throw new GeneralBOException(status);
                                    }

                                    // UNTUK LEMBUR
                                    double lamaLembur = 0;
                                    String jenisLembur = null;

                                    List<LemburEntity> lemburEntityList = lemburDao.getListLemburByNipAndTanggal(biodata.getNip(),tanggalInquiry);

                                    for (LemburEntity lemburEntity : lemburEntityList){
                                        lamaLembur = lamaLembur+lemburEntity.getLamaJam();
                                        jenisLembur = lemburEntity.getTipeLembur();
                                        String stTanggalAwalLembur = df.format(lemburEntity.getTanggalAwalSetuju());
                                        String stTanggalAkhirLembur = df.format(lemburEntity.getTanggalAkhirSetuju());

                                        stTanggalAwalLembur = stTanggalAkhirLembur+" "+lemburEntity.getJamAwal()+":00";
                                        stTanggalAkhirLembur = stTanggalAkhirLembur+" "+lemburEntity.getJamAkhir()+":00";

                                        try {
                                            java.util.Date dJamAwalLembur = sdf.parse(stTanggalAwalLembur);
                                            java.util.Date dJamAkhirLembur = sdf.parse(stTanggalAkhirLembur);

                                            tsTanggalAwalLembur = new Timestamp(dJamAwalLembur.getTime());
                                            tsTanggalBesokLembur = new Timestamp(dJamAkhirLembur.getTime());
                                        }catch (Exception e){
                                            String status ="[AbsensiBoImpl.cronInquiry] ERROR : "+e;
                                            logger.error(status);
                                            throw new GeneralBOException(status);
                                        }

                                        if (jadwalke==1){
                                            //jika lembur sebelum jadwal
                                            if (tsTanggalAwalLembur.before(tsTanggalAwalShift)){
                                              lembur=true;
                                              tsTanggalAwalFinalShift=tsTanggalAwalLembur;
                                              jamKerja.setJamMasuk(lemburEntity.getJamAwal());
                                            // jika lembur ditengah meneruskan lembur pertama
                                            }else if (jamKerja.getJamPulang().equalsIgnoreCase(lemburEntity.getJamAwal())){
                                              lembur=true;
                                              tsTanggalBesokFinalShift = tsTanggalAwalLembur;
                                              jamKerja.setJamPulang(lemburEntity.getJamAkhir());
                                            }
                                        }else{
                                            //jika lembur sesudah jadwal
                                            if (tsTanggalAwalLembur.after(tsTanggalAwalShift)){
                                                lembur=true;
                                                tsTanggalBesokFinalShift = tsTanggalBesokLembur;
                                                jamKerja.setJamPulang(lemburEntity.getJamAkhir());
                                            }else if (jamKerja.getJamMasuk().equalsIgnoreCase(lemburEntity.getJamAkhir())){
                                                lembur=true;
                                                tsTanggalAwalFinalShift = tsTanggalAwalLembur;
                                                jamKerja.setJamMasuk(lemburEntity.getJamAwal());
                                            }
                                        }
                                    }

                                    int iJamMasukDBShift = Integer.parseInt(jamKerja.getJamMasuk().substring(0,2));
                                    int iJamPulangDBShift = Integer.parseInt(jamKerja.getJamPulang().substring(0,2));

                                    int jamMasukInquiry=iJamMasukDBShift-2;
                                    int jamPulangInquiry=iJamPulangDBShift+2;

                                    int iHari=0;

                                    if (jamPulangInquiry<jamMasukInquiry){
                                        iHari=iHari+1;
                                    }
                                    // get list from database detail
                                    Calendar cal3 = Calendar.getInstance();
                                    Calendar cal4 = Calendar.getInstance();
                                    cal3.setTime(tanggalInquiry);
                                    cal4.setTime(tanggalInquiry);

                                    cal3.add(Calendar.DAY_OF_YEAR,iHari);
                                    cal3.set(Calendar.HOUR_OF_DAY, jamPulangInquiry);
                                    cal3.set(Calendar.MINUTE, 0);
                                    cal3.set(Calendar.SECOND, 0);
                                    cal3.set(Calendar.MILLISECOND, 0);

                                    cal4.set(Calendar.HOUR_OF_DAY, jamMasukInquiry);
                                    cal4.set(Calendar.MINUTE, 0);
                                    cal4.set(Calendar.SECOND, 0);
                                    cal4.set(Calendar.MILLISECOND, 0);

                                    Date tanggalBesokShift = new java.sql.Date(cal3.getTimeInMillis());
                                    Date tanggalAwalShift = new java.sql.Date(cal4.getTimeInMillis());
                                    Timestamp tsTanggalAwal = new java.sql.Timestamp(tanggalAwalShift.getTime());
                                    Timestamp tsTanggalBesok = new java.sql.Timestamp(tanggalBesokShift.getTime());

                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(tanggalAwalShift);
                                    calendar.add(Calendar.HOUR, 3);
                                    Timestamp tsBatasAwalShift = new Timestamp(calendar.getTimeInMillis());

                                    List<MesinAbsensiDetailEntity> mesinAbsensiDetailEntityList = new ArrayList<>();
                                    mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getAllDetailWithDateAndPin(biodata.getPin(),tsTanggalAwal,tsTanggalBesok,data.getBranchId());

                                    if (mesinAbsensiDetailEntityList.size()==0){
                                        String statusAbsensi =cariStatusTidakMasuk(biodata.getNip(), CommonUtil.dateUtiltoDateSql(data.getTanggalUtil()));
                                        if (jadwalke==1){
                                            absensiPegawai.setStatusAbsensi(statusAbsensi);
                                        }else{
                                            absensiPegawai.setStatusAbsensi2(statusAbsensi);
                                        }
                                    } else{
                                        for (MesinAbsensiDetailEntity mesinAbsensiDetailEntity : mesinAbsensiDetailEntityList){
                                            if (tsJamAwalFinger==null || mesinAbsensiDetailEntity.getScanDate().compareTo(tsJamAwalFinger)<0){
                                                if (mesinAbsensiDetailEntity.getScanDate().compareTo(tsBatasAwalShift)<0){
                                                    tsJamAwalFinger = mesinAbsensiDetailEntity.getScanDate();
                                                }else{
                                                    tsJamAkhirFinger= mesinAbsensiDetailEntity.getScanDate();
                                                }
                                            } else{
                                                if (tsJamAkhirFinger==null || mesinAbsensiDetailEntity.getScanDate().compareTo(tsJamAkhirFinger)>0){
                                                    tsJamAkhirFinger= mesinAbsensiDetailEntity.getScanDate();
                                                }
                                            }

                                        }

                                        //set status masuk
                                        String statusA = null;
                                        if (tsJamAwalFinger==null||tsJamAkhirFinger==null){
                                            statusA="03";
                                        }else if (tsJamAwalFinger.compareTo(tsTanggalAwalShift)>0){
                                            absensiPegawai.setTelat(BigInteger.valueOf(CommonUtil.compareTwoTimeStamps(tsJamAwalFinger,tsTanggalAwalShift,"menit")));
                                            statusA="02";
                                        }else if (tsJamAkhirFinger.compareTo(tsTanggalBesokShift)<0){
                                            statusA="14";
                                        }else if (tsJamAwalFinger.compareTo(tsTanggalAwalShift)<0&&tsJamAkhirFinger.compareTo(tsTanggalBesokShift)>0){
                                            if (CommonUtil.compareTwoTimeStamps(tsJamAkhirFinger,tsTanggalBesokShift,"jam")>1||CommonUtil.compareTwoTimeStamps(tsTanggalAwalShift,tsJamAwalFinger,"jam")>1){
                                                statusA="04";
                                            }else{
                                                statusA="01";
                                            }
                                        }else{
                                            String status ="[AbsensiBoImpl.cronInquiry] ERROR : ada case yang belum tercover";
                                            logger.error(status);
                                            throw new GeneralBOException(status);
                                        }

                                        if (jadwalke==1){
                                            absensiPegawai.setStatusAbsensi(statusA);
                                        }else{
                                            absensiPegawai.setStatusAbsensi2(statusA);
                                        }

                                        //set jam masuk dan jam pulang
                                        DateFormat format = new SimpleDateFormat("HH:mm");
                                        String jamMasuk=null;
                                        String jamPulang=null;
                                        if (tsJamAwalFinger!=null){
                                            jamMasuk=format.format(tsJamAwalFinger);
                                        }
                                        if (tsJamAkhirFinger!=null){
                                            jamPulang = format.format(tsJamAkhirFinger);
                                        }

                                        if (jadwalke==1){
                                            absensiPegawai.setJamMasuk(jamMasuk);
                                            absensiPegawai.setJamPulang(jamPulang);
                                        }else{
                                            absensiPegawai.setJamMasuk2(jamMasuk);
                                            absensiPegawai.setJamPulang2(jamPulang);
                                        }
                                    }
                                if (jadwalke==1){
                                    absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                                }else{
                                    absensiPegawai.setStatusName2(CommonUtil.statusName(absensiPegawai.getStatusAbsensi2()));
                                }

                                // Jika lembur
                                if (lembur){
                                    //hitung lama lembur berdasarkan final lembur aslinya
                                    double realisasiLembur = 0;
                                    double realisasiFinger = 0;
                                    DateFormat format = new SimpleDateFormat("HH:mm");
                                    Timestamp finalAwalLembur =null;
                                    Timestamp finalAkhirLembur =null;

                                    if (tsTanggalAwalLembur.before(tsTanggalAwalShift)){
                                        finalAwalLembur = tsJamAwalFinger;
                                        finalAkhirLembur = tsTanggalAwalShift;
                                    }else if (tsTanggalBesokLembur.after(tsTanggalBesokShift)){
                                        finalAwalLembur = tsTanggalBesokShift;
                                        finalAkhirLembur = tsTanggalBesokLembur;
                                    }

                                    try {
                                        realisasiLembur = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(tsTanggalAwalLembur),format.format(tsTanggalBesokLembur),"positif");
                                        realisasiFinger = CommonUtil.SubtractJamAwalDanJamAkhir(format.format(finalAwalLembur),format.format(finalAkhirLembur),"positif");
                                    } catch (ParseException e) {
                                        String status = "[AbsensiBoImpl.cronInquiry] ERROR :" + e;
                                        logger.error(status);
                                        throw new GeneralBOException(status);
                                    }

                                    // menghitung upah lembur
                                    List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                                    Map hsCriteria4 = new HashMap();
                                    hsCriteria4.put("tipe_pegawai_id", biodata.getTipePegawai());
                                    hsCriteria4.put("flag", "Y");
                                    double faktor = 0;
                                    Double upahLembur = 0d;
                                    Double gapok = 0d;
                                    Double sankhus = 0d;
                                    pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                                    for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                                        faktor = pengaliFaktorLemburEntity.getFaktor();
                                    }

                                    hsCriteria4 = new HashMap();
                                    hsCriteria4.put("golongan_id", biodata.getGolongan());
                                    hsCriteria4.put("point", (int) Math.round(biodata.getPoint()));
                                    hsCriteria4.put("tahun", tahunGaji);
                                    hsCriteria4.put("flag", "Y");
                                    List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                                    List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                                    if (biodata.getTipePegawai().equalsIgnoreCase("TP01")){
                                        payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(biodata.getGolongan(),tahunGaji);
                                        for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                                            gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                                            sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                                        }
                                    }else if (biodata.getTipePegawai().equalsIgnoreCase("TP03")){
                                        payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(biodata.getGolongan(),tahunGaji);
                                        for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                                            gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                                            sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                                        }
                                    }

                                    double jamLembur = 0;
                                    double finalLamaLembur = 0;
                                    double finalLamaLembur2 = 0;

                                    if (lamaLembur<realisasiLembur){
                                        finalLamaLembur = lamaLembur;
                                        finalLamaLembur2 = lamaLembur;
                                    }else{
                                        finalLamaLembur = realisasiLembur;
                                        finalLamaLembur2 = realisasiLembur;
                                    }


                                    int j = 1;
                                    if (finalLamaLembur>0){
                                        do{
                                            if (finalLamaLembur>0&&finalLamaLembur<1){
                                                Map hsCriteria5 = new HashMap();
                                                hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                hsCriteria5.put("jam_lembur", j);
                                                hsCriteria5.put("flag", "Y");
                                                List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                    jamLembur = jamLembur + (finalLamaLembur*2);
                                                }
                                                finalLamaLembur= (double) 0;
                                            }else{
                                                Map hsCriteria5 = new HashMap();
                                                hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                hsCriteria5.put("jam_lembur", j);
                                                hsCriteria5.put("flag", "Y");
                                                List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                    jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                                }
                                                finalLamaLembur= finalLamaLembur-1;
                                            }
                                            j=j+1;
                                        }while (finalLamaLembur>0);
                                    }
                                    Double peralihan = 0d;
                                    peralihan = getTunjPeralihan(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
                                    upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;


                                    String upahNew = "";
                                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                                    formatRp.setCurrencySymbol("Rp. ");
                                    formatRp.setGroupingSeparator('.');

                                    kursIndonesia.setDecimalFormatSymbols(formatRp);
                                    upahNew = kursIndonesia.format(upahLembur);

                                    absensiPegawai.setLembur("Y");
                                    absensiPegawai.setPengajuanLembur(lamaLembur);
                                    absensiPegawai.setRealisasiJamLembur(realisasiFinger);
                                    absensiPegawai.setJamLembur(jamLembur);
                                    absensiPegawai.setLamaLembur(finalLamaLembur2);
                                    absensiPegawai.setBiayaLembur(upahLembur);
                                    absensiPegawai.setStBiayaLembur(upahNew);
                                    absensiPegawai.setJenisLembur(jenisLembur);
                                    absensiPegawai.setAwalLembur(absensiPegawai.getJamMasuk());
                                    absensiPegawai.setSelesaiLembur(absensiPegawai.getJamPulang());
                                }
                                jadwalke++;
                            }
                        }
                        //LEMBUR ON CALL DISINI
                        }else if (jamKerjaShiftOnCall.size()>0){
                            absensiPegawai.setTipeHari("on_call");
                            for (AbsensiPegawai jamKerja : jamKerjaShiftOnCall) {
                                if (jamKerja.getJamMasuk() != null && jamKerja.getJamPulang() != null) {
                                    Timestamp tsTanggalAwalShift;
                                    Timestamp tsTanggalBesokShift;
                                    Timestamp tsJamAwalFinger = null;
                                    Timestamp tsJamAkhirFinger = null;

                                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

                                    String stTanggalMasukShift = df.format(data.getTanggalUtil());
                                    String stTanggalPulangShift = df.format(data.getTanggalUtil());

                                    stTanggalMasukShift = stTanggalMasukShift + " " + jamKerja.getJamMasuk() + ":00";
                                    stTanggalPulangShift = stTanggalPulangShift + " " + jamKerja.getJamPulang() + ":00";

                                    try {
                                        java.util.Date dJamMasukShift = sdf.parse(stTanggalMasukShift);
                                        java.util.Date dJamPulangShift = sdf.parse(stTanggalPulangShift);

                                        tsTanggalAwalShift = new Timestamp(dJamMasukShift.getTime());
                                        tsTanggalBesokShift = new Timestamp(dJamPulangShift.getTime());

                                    } catch (Exception e) {
                                        String status = "[AbsensiBoImpl.cronInquiry] ERROR : " + e;
                                        logger.error(status);
                                        throw new GeneralBOException(status);
                                    }

                                    int iJamMasukDBShift = Integer.parseInt(jamKerja.getJamMasuk().substring(0, 2));
                                    int iJamPulangDBShift = Integer.parseInt(jamKerja.getJamPulang().substring(0, 2));

                                    int jamMasukInquiry = iJamMasukDBShift - 2;
                                    int jamPulangInquiry = iJamPulangDBShift + 10;

                                    int iHari = 0;

                                    if (jamPulangInquiry < jamMasukInquiry) {
                                        iHari = iHari + 1;
                                    }
                                    // get list from database detail
                                    Calendar cal3 = Calendar.getInstance();
                                    Calendar cal4 = Calendar.getInstance();
                                    cal3.setTime(tanggalInquiry);
                                    cal4.setTime(tanggalInquiry);

                                    cal3.add(Calendar.DAY_OF_YEAR, iHari);
                                    cal3.set(Calendar.HOUR_OF_DAY, jamPulangInquiry);
                                    cal3.set(Calendar.MINUTE, 0);
                                    cal3.set(Calendar.SECOND, 0);
                                    cal3.set(Calendar.MILLISECOND, 0);

                                    cal4.set(Calendar.HOUR_OF_DAY, jamMasukInquiry);
                                    cal4.set(Calendar.MINUTE, 0);
                                    cal4.set(Calendar.SECOND, 0);
                                    cal4.set(Calendar.MILLISECOND, 0);

                                    Date tanggalBesokShift = new java.sql.Date(cal3.getTimeInMillis());
                                    Date tanggalAwalShift = new java.sql.Date(cal4.getTimeInMillis());
                                    Timestamp tsTanggalAwal = new java.sql.Timestamp(tanggalAwalShift.getTime());
                                    Timestamp tsTanggalBesok = new java.sql.Timestamp(tanggalBesokShift.getTime());

                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(tanggalAwalShift);
                                    calendar.add(Calendar.HOUR, 3);
                                    Timestamp tsBatasAwalShift = new Timestamp(calendar.getTimeInMillis());

                                    List<ItHrisMesinAbsensiDetailOnCallEntity> mesinAbsensiDetailOnCallEntityList = new ArrayList<>();
                                    mesinAbsensiDetailOnCallEntityList = mesinAbsensiDetailOnCallDao.getAllDetailWithDateAndPin(biodata.getPin(), tsTanggalAwal, tsTanggalBesok);
                                    if (mesinAbsensiDetailOnCallEntityList.size() == 0) {
                                        String statusAbsensi = cariStatusTidakMasuk(biodata.getNip(), CommonUtil.dateUtiltoDateSql(data.getTanggalUtil()));
                                        absensiPegawai.setStatusAbsensiOnCall(statusAbsensi);
                                    } else {
                                        for (ItHrisMesinAbsensiDetailOnCallEntity mesinAbsensiDetailOnCallEntity : mesinAbsensiDetailOnCallEntityList) {
                                            if (tsJamAwalFinger == null || mesinAbsensiDetailOnCallEntity.getScanDate().compareTo(tsJamAwalFinger) < 0) {
                                                tsJamAwalFinger = mesinAbsensiDetailOnCallEntity.getScanDate();
                                            } else if (tsJamAkhirFinger == null || mesinAbsensiDetailOnCallEntity.getScanDate().compareTo(tsJamAkhirFinger) > 0) {
                                                tsJamAkhirFinger = mesinAbsensiDetailOnCallEntity.getScanDate();
                                            }

                                        }

                                        //set status masuk
                                        String statusA = null;
                                        if (tsJamAwalFinger == null || tsJamAkhirFinger == null) {
                                            statusA = "03";
                                        } else if (tsJamAwalFinger.compareTo(tsTanggalAwalShift) > 0) {
                                            absensiPegawai.setTelat(BigInteger.valueOf(CommonUtil.compareTwoTimeStamps(tsJamAwalFinger,tsTanggalAwalShift,"menit")));
                                            statusA = "02";
                                        } else if (tsJamAkhirFinger.compareTo(tsTanggalBesokShift) < 0) {
                                            statusA = "14";
                                        } else if (tsJamAwalFinger.compareTo(tsTanggalAwalShift) < 0 && tsJamAkhirFinger.compareTo(tsTanggalBesokShift) > 0) {
                                            if (CommonUtil.compareTwoTimeStamps(tsJamAkhirFinger, tsTanggalBesokShift, "jam") > 1 || CommonUtil.compareTwoTimeStamps(tsTanggalAwalShift, tsJamAwalFinger, "jam") > 1) {
                                                statusA = "04";
                                            } else {
                                                statusA = "01";
                                            }
                                        } else {
                                            String status = "[AbsensiBoImpl.cronInquiry] ERROR : ada case yang belum tercover";
                                            logger.error(status);
                                            throw new GeneralBOException(status);
                                        }

                                        absensiPegawai.setStatusAbsensiOnCall(statusA);

                                        //set jam masuk dan jam pulang
                                        DateFormat format = new SimpleDateFormat("HH:mm");
                                        String jamMasuk = null;
                                        String jamPulang = null;
                                        if (tsJamAwalFinger != null) {
                                            jamMasuk = format.format(tsJamAwalFinger);
                                        }
                                        if (tsJamAkhirFinger != null) {
                                            jamPulang = format.format(tsJamAkhirFinger);
                                        }

                                        absensiPegawai.setJamMasukOnCall(jamMasuk);
                                        absensiPegawai.setJamPulangOnCall(jamPulang);
                                    }
                                    absensiPegawai.setStatusNameOnCall(CommonUtil.statusName(absensiPegawai.getStatusAbsensiOnCall()));

                                    if ("Y".equalsIgnoreCase(jamKerja.getFlagPanggil())){
                                        List<MesinAbsensiDetailEntity> mesinAbsensiDetailEntityList = new ArrayList<>();
                                        mesinAbsensiDetailEntityList = mesinAbsensiDetailDao.getAllDetailWithDateAndPin(biodata.getPin(),tsTanggalAwal,tsTanggalBesok,data.getBranchId());
                                        if (mesinAbsensiDetailEntityList.size() == 0) {
                                        } else {
                                            // UNTUK ONCALL LEBIH DARI 1a
                                            int i =1;
                                            int jmlFinger =0;
                                            List<AbsensiOnCall> absensiOnCallList = new ArrayList<>();
                                            AbsensiOnCall dataOnCall = new AbsensiOnCall();

                                            //mengisi on call
                                            for (MesinAbsensiDetailEntity mesinAbsensiDetailEntity : mesinAbsensiDetailEntityList) {
                                                if (i==1){
                                                    dataOnCall.setFingerAwal(mesinAbsensiDetailEntity.getScanDate());
                                                    i++;
                                                    jmlFinger++;
                                                    if (jmlFinger==mesinAbsensiDetailEntityList.size()){
                                                        absensiOnCallList.add(dataOnCall);
                                                        dataOnCall = new AbsensiOnCall();
                                                        i=1;
                                                    }
                                                }else{
                                                    dataOnCall.setFingerAkhir(mesinAbsensiDetailEntity.getScanDate());
                                                    absensiOnCallList.add(dataOnCall);
                                                    dataOnCall = new AbsensiOnCall();
                                                    i=1;
                                                    jmlFinger++;
                                                }
                                            }

                                            // looping on call untuk ambil absensi on call
                                            int jmlOnCall=1;
                                            double realisasiLembur=0;
                                            List<AbsensiOnCall> onCallFinal =  new ArrayList<>();
                                            for (AbsensiOnCall call : absensiOnCallList){
                                                AbsensiOnCall newOnCall = new AbsensiOnCall();
                                                //set jam masuk dan jam pulang
                                                DateFormat format = new SimpleDateFormat("HH:mm");
                                                String jamMasuk = null;
                                                String jamPulang = null;
                                                if (call.getFingerAwal() != null) {
                                                    jamMasuk = format.format(call.getFingerAwal());
                                                }
                                                if (call.getFingerAkhir() != null) {
                                                    jamPulang = format.format(call.getFingerAkhir());
                                                }

                                                if (jamMasuk!=null&&jamPulang!=null){
                                                    //hitung lama lembur berdasarkan final lembur aslinya
                                                    try {
                                                        realisasiLembur += CommonUtil.SubtractJamAwalDanJamAkhir(format.format(call.getFingerAwal()),format.format(call.getFingerAkhir()),"positif");
                                                        newOnCall.setLamaLembur(BigDecimal.valueOf(CommonUtil.SubtractJamAwalDanJamAkhir(format.format(call.getFingerAwal()),format.format(call.getFingerAkhir()),"positif")));
                                                    } catch (ParseException e) {
                                                        String status = "[AbsensiBoImpl.cronInquiry] ERROR :" + e;
                                                        logger.error(status);
                                                        throw new GeneralBOException(status);
                                                    }
                                                }else{
                                                    newOnCall.setLamaLembur(BigDecimal.ZERO);
                                                }

                                                newOnCall.setNip(biodata.getNip());
                                                newOnCall.setTanggal(absensiPegawai.getTanggal());
                                                newOnCall.setJamMasuk(jamMasuk);
                                                newOnCall.setJamPulang(jamPulang);

                                                if (jmlOnCall==1){
                                                    absensiPegawai.setJamMasuk(jamMasuk);
                                                    absensiPegawai.setJamPulang(jamPulang);
                                                    absensiPegawai.setStatusAbsensi("16");
                                                }else{
                                                    absensiPegawai.setJamMasuk2(jamMasuk);
                                                    absensiPegawai.setJamPulang2(jamPulang);
                                                    absensiPegawai.setStatusAbsensi2("16");
                                                }
                                                onCallFinal.add(newOnCall);
                                                jmlOnCall++;
                                            }

                                            //set on call to session
                                            HttpSession session = ServletActionContext.getRequest().getSession();
                                            session.setAttribute("listOfResultOnCall",onCallFinal);

                                            boolean boolOnCall = false;

                                            if (realisasiLembur!=0){
                                                boolOnCall=true;
                                            }

                                            if (boolOnCall){
                                                // menghitung upah lembur
                                                List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                                                Map hsCriteria4 = new HashMap();
                                                hsCriteria4.put("tipe_pegawai_id", biodata.getTipePegawai());
                                                hsCriteria4.put("flag", "Y");
                                                double faktor = 0;
                                                Double upahLembur = 0d;
                                                Double gapok = 0d;
                                                Double sankhus = 0d;
                                                pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                                                for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                                                    faktor = pengaliFaktorLemburEntity.getFaktor();
                                                }

                                                hsCriteria4 = new HashMap();
                                                hsCriteria4.put("golongan_id", biodata.getGolongan());
                                                hsCriteria4.put("point", (int) Math.round(biodata.getPoint()));
                                                hsCriteria4.put("tahun", tahunGaji);
                                                hsCriteria4.put("flag", "Y");
                                                List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                                                List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                                                if (biodata.getTipePegawai().equalsIgnoreCase("TP01")){
                                                    payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(biodata.getGolongan(),tahunGaji);
                                                    for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                                                        gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                                                        sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                                                    }
                                                }else if (biodata.getTipePegawai().equalsIgnoreCase("TP03")){
                                                    payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(biodata.getGolongan(),tahunGaji);
                                                    for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                                                        gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                                                        sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                                                    }
                                                }

                                                double jamLembur = 0;
                                                double finalLamaLembur = 0;
                                                double finalLamaLembur2 = 0;

                                                finalLamaLembur = realisasiLembur;
                                                finalLamaLembur2 = realisasiLembur;

                                                int j = 1;
                                                if (finalLamaLembur>0){
                                                    do{
                                                        if (finalLamaLembur>0&&finalLamaLembur<1){
                                                            Map hsCriteria5 = new HashMap();
                                                            hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                            hsCriteria5.put("jam_lembur", j);
                                                            hsCriteria5.put("flag", "Y");
                                                            List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                            jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                            for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                                jamLembur = jamLembur + (finalLamaLembur*2);
                                                            }
                                                            finalLamaLembur= (double) 0;
                                                        }else{
                                                            Map hsCriteria5 = new HashMap();
                                                            hsCriteria5.put("tipe_hari", "hari_kerja");
                                                            hsCriteria5.put("jam_lembur", j);
                                                            hsCriteria5.put("flag", "Y");
                                                            List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                            jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                            for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                                jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                                            }
                                                            finalLamaLembur= finalLamaLembur-1;
                                                        }
                                                        j=j+1;
                                                    }while (finalLamaLembur>0);
                                                }
                                                Double peralihan = 0d;
                                                peralihan = getTunjPeralihan(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
                                                upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;


                                                String upahNew = "";
                                                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                                                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                                                formatRp.setCurrencySymbol("Rp. ");
                                                formatRp.setGroupingSeparator('.');

                                                kursIndonesia.setDecimalFormatSymbols(formatRp);
                                                upahNew = kursIndonesia.format(upahLembur);

                                                absensiPegawai.setLembur("Y");
                                                absensiPegawai.setPengajuanLembur(realisasiLembur);
                                                absensiPegawai.setRealisasiJamLembur(realisasiLembur);
                                                absensiPegawai.setJamLembur(jamLembur);
                                                absensiPegawai.setLamaLembur(finalLamaLembur2);
                                                absensiPegawai.setBiayaLembur(upahLembur);
                                                absensiPegawai.setStBiayaLembur(upahNew);
                                                absensiPegawai.setJenisLembur("OC");
                                            }else{
                                                //jika ada yg null
                                                // menghitung upah lembur
                                                double onCall = 3;
                                                List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                                                Map hsCriteria4 = new HashMap();
                                                hsCriteria4.put("tipe_pegawai_id", biodata.getTipePegawai());
                                                hsCriteria4.put("flag", "Y");
                                                double faktor = 0;
                                                Double upahLembur = 0d;
                                                Double gapok = 0d;
                                                Double sankhus = 0d;
                                                pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                                                for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                                                    faktor = pengaliFaktorLemburEntity.getFaktor();
                                                }

                                                hsCriteria4 = new HashMap();
                                                hsCriteria4.put("golongan_id", biodata.getGolongan());
                                                hsCriteria4.put("point", (int) Math.round(biodata.getPoint()));
                                                hsCriteria4.put("tahun", tahunGaji);
                                                hsCriteria4.put("flag", "Y");
                                                List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                                                List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                                                if (biodata.getTipePegawai().equalsIgnoreCase("TP01")){
                                                    payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(biodata.getGolongan(),tahunGaji);
                                                    for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                                                        gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                                                        sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                                                    }
                                                }else if (biodata.getTipePegawai().equalsIgnoreCase("TP03")){
                                                    payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(biodata.getGolongan(),tahunGaji);
                                                    for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                                                        gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                                                        sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                                                    }
                                                }

                                                double jamLembur = 0;
                                                double finalLamaLembur = onCall;


                                                int j = 1;
                                                if (finalLamaLembur>0){
                                                    do{
                                                        if (finalLamaLembur>0&&finalLamaLembur<1){
                                                            Map hsCriteria5 = new HashMap();
                                                            hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                            hsCriteria5.put("jam_lembur", j);
                                                            hsCriteria5.put("flag", "Y");
                                                            List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                            jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                            for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                                jamLembur = jamLembur + (finalLamaLembur*2);
                                                            }
                                                            finalLamaLembur= (double) 0;
                                                        }else{
                                                            Map hsCriteria5 = new HashMap();
                                                            hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                            hsCriteria5.put("jam_lembur", j);
                                                            hsCriteria5.put("flag", "Y");
                                                            List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                            jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                            for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                                jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                                            }
                                                            finalLamaLembur= finalLamaLembur-1;
                                                        }
                                                        j=j+1;
                                                    }while (finalLamaLembur>0);
                                                }
                                                Double peralihan = 0d;
                                                peralihan = getTunjPeralihan(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
                                                upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;


                                                String upahNew = "";
                                                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                                                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                                                formatRp.setCurrencySymbol("Rp. ");
                                                formatRp.setGroupingSeparator('.');

                                                kursIndonesia.setDecimalFormatSymbols(formatRp);
                                                upahNew = kursIndonesia.format(upahLembur);

                                                absensiPegawai.setLembur("Y");
                                                absensiPegawai.setPengajuanLembur(onCall);
                                                absensiPegawai.setRealisasiJamLembur(onCall);
                                                absensiPegawai.setJamLembur(jamLembur);
                                                absensiPegawai.setLamaLembur(onCall);
                                                absensiPegawai.setBiayaLembur(upahLembur);
                                                absensiPegawai.setStBiayaLembur(upahNew);
                                                absensiPegawai.setJenisLembur("OC");
                                            }
                                        }
                                    }else{
                                        //jika lembur on call tapi tidak dipanggil
                                        // menghitung upah lembur
                                        double onCall = 3;
                                        List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = new ArrayList<>();
                                        Map hsCriteria4 = new HashMap();
                                        hsCriteria4.put("tipe_pegawai_id", biodata.getTipePegawai());
                                        hsCriteria4.put("flag", "Y");
                                        double faktor = 0;
                                        Double upahLembur = 0d;
                                        Double gapok = 0d;
                                        Double sankhus = 0d;
                                        pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria4);
                                        for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList) {
                                            faktor = pengaliFaktorLemburEntity.getFaktor();
                                        }

                                        hsCriteria4 = new HashMap();
                                        hsCriteria4.put("golongan_id", biodata.getGolongan());
                                        hsCriteria4.put("point", (int) Math.round(biodata.getPoint()));
                                        hsCriteria4.put("tahun", tahunGaji);
                                        hsCriteria4.put("flag", "Y");
                                        List<ImPayrollSkalaGajiEntity> payrollSkalaGajiList = new ArrayList<>();
                                        List<ImPayrollSkalaGajiPkwtEntity> payrollSkalaGajiPkwtEntityList = new ArrayList<>();
                                        if (biodata.getTipePegawai().equalsIgnoreCase("TP01")){
                                            payrollSkalaGajiList = payrollSkalaGajiDao.getDataSkalaGajiSimRs(biodata.getGolongan(),tahunGaji);
                                            for (ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity : payrollSkalaGajiList) {
                                                gapok = imPayrollSkalaGajiEntity.getNilai().doubleValue();
                                                sankhus = imPayrollSkalaGajiEntity.getSantunanKhusus().doubleValue();
                                            }
                                        }else if (biodata.getTipePegawai().equalsIgnoreCase("TP03")){
                                            payrollSkalaGajiPkwtEntityList = payrollSkalaGajiPkwtDao.getSkalaGajiPkwt(biodata.getGolongan(),tahunGaji);
                                            for (ImPayrollSkalaGajiPkwtEntity skalaGajiLoop:payrollSkalaGajiPkwtEntityList){
                                                gapok = skalaGajiLoop.getGajiPokok().doubleValue();
                                                sankhus = skalaGajiLoop.getSantunanKhusus().doubleValue();
                                            }
                                        }

                                        double jamLembur = 0;
                                        double finalLamaLembur = onCall;


                                        int j = 1;
                                        if (finalLamaLembur>0){
                                            do{
                                                if (finalLamaLembur>0&&finalLamaLembur<1){
                                                    Map hsCriteria5 = new HashMap();
                                                    hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                    hsCriteria5.put("jam_lembur", j);
                                                    hsCriteria5.put("flag", "Y");
                                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                    jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                        jamLembur = jamLembur + (finalLamaLembur*2);
                                                    }
                                                    finalLamaLembur= (double) 0;
                                                }else{
                                                    Map hsCriteria5 = new HashMap();
                                                    hsCriteria5.put("tipe_hari", absensiPegawai.getTipeHari());
                                                    hsCriteria5.put("jam_lembur", j);
                                                    hsCriteria5.put("flag", "Y");
                                                    List<JamLemburEntity> jamLemburEntityList = new ArrayList<>();
                                                    jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria5);
                                                    for (JamLemburEntity jamLemburEntity : jamLemburEntityList) {
                                                        jamLembur = jamLembur + (jamLemburEntity.getPengaliJamLembur());
                                                    }
                                                    finalLamaLembur= finalLamaLembur-1;
                                                }
                                                j=j+1;
                                            }while (finalLamaLembur>0);
                                        }
                                        Double peralihan = 0d;
                                        peralihan = getTunjPeralihan(biodata.getNip(),CommonUtil.dateUtiltoDateSql(data.getTanggalUtil())).doubleValue();
                                        upahLembur = (gapok+sankhus+peralihan)*faktor*jamLembur;


                                        String upahNew = "";
                                        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                                        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                                        formatRp.setCurrencySymbol("Rp. ");
                                        formatRp.setGroupingSeparator('.');

                                        kursIndonesia.setDecimalFormatSymbols(formatRp);
                                        upahNew = kursIndonesia.format(upahLembur);

                                        absensiPegawai.setLembur("Y");
                                        absensiPegawai.setPengajuanLembur(onCall);
                                        absensiPegawai.setRealisasiJamLembur(onCall);
                                        absensiPegawai.setJamLembur(jamLembur);
                                        absensiPegawai.setLamaLembur(onCall);
                                        absensiPegawai.setBiayaLembur(upahLembur);
                                        absensiPegawai.setStBiayaLembur(upahNew);
                                        absensiPegawai.setJenisLembur("OC");
                                    }
                                }
                            }
                        }
                    }
                    if (absensiPegawai.getStatusAbsensi()!=null){
                        absensiPegawai.setStatusName(CommonUtil.statusName(absensiPegawai.getStatusAbsensi()));
                        if ("hari_kerja".equalsIgnoreCase(absensiPegawai.getTipeHari())){
                            absensiPegawai.setTipeHariName("Kerja");
                        }else if ("hari_libur".equalsIgnoreCase(absensiPegawai.getTipeHari())){
                            absensiPegawai.setTipeHariName("Libur");
                        } else if ("on_call".equalsIgnoreCase(absensiPegawai.getTipeHari())){
                            absensiPegawai.setTipeHariName("On Call");
                        }
                        absensiPegawaiList.add(absensiPegawai);
                    }else if (absensiPegawai.getStatusAbsensiOnCall()!=null){
                        absensiPegawai.setTipeHari("on_call");
                        absensiPegawai.setTipeHariName("On Call");
                        absensiPegawaiList.add(absensiPegawai);
                    }
                }
            }
        }
        logger.info("[AbsensiBoImpl.cronInquiry] end process <<<");
        return absensiPegawaiList;
    }

    @Override
    public void saveAddAbsensi(List<AbsensiPegawai> absensiPegawaiList,List<AbsensiOnCall> absensiOnCallList, AbsensiPegawai bean) throws GeneralBOException {
        logger.info("[AbsensiPegawaiBoImpl.saveAddAbsensi] start process >>>");

        if (bean != null&&absensiPegawaiList!=null) {
            for (AbsensiPegawai dataAbsen : absensiPegawaiList){
                //validasi jika data sudah ada
                List<AbsensiPegawaiEntity> absensiPegawaiEntityList = new ArrayList<>();
                Map hsCriteria = new HashMap();
                hsCriteria.put("tanggal", dataAbsen.getTanggal());
                hsCriteria.put("nip", dataAbsen.getNip());
                hsCriteria.put("flag", "Y");
                absensiPegawaiEntityList = absensiPegawaiDao.getByCriteria(hsCriteria);

                if (absensiPegawaiEntityList.size() == 0) {
                    String absensiPegawaiId;
                    try {
                        // Generating ID, get from postgre sequence
                        absensiPegawaiId = absensiPegawaiDao.getNextAbsensiPegawaiId();
                    } catch (HibernateException e) {
                        logger.error("[AbsensiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when getting sequence Absensi Pegawai id, please info to your admin..." + e.getMessage());
                    }

                    AbsensiPegawaiEntity absensiPegawaiEntity = new AbsensiPegawaiEntity();

                    absensiPegawaiEntity.setAbsensiPegawaiId(absensiPegawaiId);
                    absensiPegawaiEntity.setIjin(dataAbsen.getIjin());
                    absensiPegawaiEntity.setLembur(dataAbsen.getLembur());
                    absensiPegawaiEntity.setNip(dataAbsen.getNip());
                    absensiPegawaiEntity.setBranchId(dataAbsen.getBranchId());
                    absensiPegawaiEntity.setJamMasuk(dataAbsen.getJamMasuk());
                    absensiPegawaiEntity.setJamKeluar(dataAbsen.getJamPulang());
                    absensiPegawaiEntity.setTanggal(dataAbsen.getTanggal());
                    absensiPegawaiEntity.setStatusAbsensi(dataAbsen.getStatusAbsensi());
                    absensiPegawaiEntity.setJenisLembur(dataAbsen.getJenisLembur());
                    absensiPegawaiEntity.setLamaLembur(dataAbsen.getLamaLembur());
                    absensiPegawaiEntity.setJamLembur(dataAbsen.getJamLembur());
                    absensiPegawaiEntity.setBiayaLembur(dataAbsen.getBiayaLembur());
                    absensiPegawaiEntity.setTipeHari(dataAbsen.getTipeHari());
                    absensiPegawaiEntity.setRealisasiJamLembur(dataAbsen.getRealisasiJamLembur());
                    absensiPegawaiEntity.setFlagUangMakan(dataAbsen.getFlagUangMakan());
                    absensiPegawaiEntity.setKeterangan(dataAbsen.getKeterangan());
                    absensiPegawaiEntity.setFlagUangMakan(dataAbsen.getFlagUangMakan());
                    absensiPegawaiEntity.setKeterangan(dataAbsen.getKeterangan());

                    absensiPegawaiEntity.setStatusAbsensi2(dataAbsen.getStatusAbsensi2());
                    absensiPegawaiEntity.setJamMasuk2(dataAbsen.getJamMasuk2());
                    absensiPegawaiEntity.setJamPulang2(dataAbsen.getJamPulang2());
                    absensiPegawaiEntity.setStatusAbsensiOnCall(dataAbsen.getStatusAbsensiOnCall());
                    absensiPegawaiEntity.setJamMasukOnCall(dataAbsen.getJamMasukOnCall());
                    absensiPegawaiEntity.setJamPulangOnCall(dataAbsen.getJamPulangOnCall());
                    absensiPegawaiEntity.setFlagCutiGantiHari(dataAbsen.getFlagCutiGantiHari());
                    absensiPegawaiEntity.setTelat(dataAbsen.getTelat());

                    absensiPegawaiEntity.setCreatedWho(bean.getCreatedWho());
                    absensiPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                    absensiPegawaiEntity.setCreatedDate(bean.getCreatedDate());
                    absensiPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    absensiPegawaiEntity.setAction(bean.getAction());
                    absensiPegawaiEntity.setFlag(bean.getFlag());

                    try {
                        absensiPegawaiDao.addAndSave(absensiPegawaiEntity);
                    } catch (HibernateException e) {
                        logger.error("[AbsensiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data Absensi Pegawai, please info to your admin..." + e.getMessage());
                    }

                    //save cuti jika flag perbaikan Y
                    if("Y".equalsIgnoreCase(dataAbsen.getFlagCutiGantiHari())){
                        BigInteger jumlahCutiPegawai = BigInteger.valueOf(0);
                        ItCutiPegawaiEntity itCutiPegawaiEntity = new ItCutiPegawaiEntity();

                        List<ItCutiPegawaiEntity> cutiPegawaiEntityList = cutiPegawaiDao.getJumlahHariCuti(dataAbsen.getNip(),CommonConstant.CUTI_TAHUNAN);
                        for (ItCutiPegawaiEntity cutiPegawai : cutiPegawaiEntityList){
                            jumlahCutiPegawai = cutiPegawai.getSisaCutiHari();
                        }

                        itCutiPegawaiEntity.setCutiPegawaiId(cutiPegawaiDao.getNextCutiPegawaiId());
                        itCutiPegawaiEntity.setNip(dataAbsen.getNip());
                        itCutiPegawaiEntity.setPegawaiPenggantiSementara("");
                        itCutiPegawaiEntity.setCutiId(CommonConstant.CUTI_TAHUNAN);
                        itCutiPegawaiEntity.setLamaHariCuti(BigInteger.ZERO);
                        itCutiPegawaiEntity.setSisaCutiHari(jumlahCutiPegawai.add(BigInteger.ONE));
                        itCutiPegawaiEntity.setApprovalId("System");
                        itCutiPegawaiEntity.setApprovalFlag("Y");
                        itCutiPegawaiEntity.setApprovalDate(new Timestamp(new java.util.Date().getTime()));
                        itCutiPegawaiEntity.setKeterangan("PENAMBAHAN CUTI KARENA GANTI HARI LIBUR");
                        itCutiPegawaiEntity.setAlamatCuti("");
                        itCutiPegawaiEntity.setApprovalDate(new Timestamp(new java.util.Date().getTime()));
                        itCutiPegawaiEntity.setNote("");
                        itCutiPegawaiEntity.setCancelFlag("N");
                        itCutiPegawaiEntity.setNoteApproval("");
                        itCutiPegawaiEntity.setTanggalDari(dataAbsen.getTanggal());
                        itCutiPegawaiEntity.setTanggalSelesai(dataAbsen.getTanggal());
                        itCutiPegawaiEntity.setFlag("Y");
                        itCutiPegawaiEntity.setAction("C");
                        itCutiPegawaiEntity.setCreatedWho("System");
                        itCutiPegawaiEntity.setLastUpdateWho("System");
                        itCutiPegawaiEntity.setCreatedDate(new Timestamp(new java.util.Date().getTime()));
                        itCutiPegawaiEntity.setLastUpdate(new Timestamp(new java.util.Date().getTime()));
                        itCutiPegawaiEntity.setFlagPerbaikan("N");

                        try {
                            // insert into database
                            cutiPegawaiDao.addAndSave(itCutiPegawaiEntity);
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }
            //save daftar on call
            for (AbsensiOnCall onCall : absensiOnCallList){
                ItHrisAbsensiOnCallEntity entity = new ItHrisAbsensiOnCallEntity();
                entity.setAbsensiOnCallId(absensiOnCallDao.getNextId());
                entity.setNip(onCall.getNip());
                entity.setJamMasuk(onCall.getJamMasuk());
                entity.setJamPulang(onCall.getJamPulang());
                entity.setTanggal(onCall.getTanggal());
                entity.setLamaLembur(onCall.getLamaLembur());

                entity.setAction(bean.getAction());
                entity.setFlag(bean.getFlag());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                absensiOnCallDao.addAndSave(entity);
            }

        }
        logger.info("[AbsensiPegawaiBoImpl.saveAddAbsensi] end process <<<");
    }

    @Override
    public void saveAddAbsensiOnCall(MesinAbsensiDetailOnCall bean) {

        ItHrisMesinAbsensiDetailOnCallEntity entity = new ItHrisMesinAbsensiDetailOnCallEntity();
        entity.setMesinAbsensiDetailOnCallId(mesinAbsensiDetailOnCallDao.getId());
        entity.setPin(bean.getPin());
        entity.setScanDate(bean.getScanDate());
        entity.setStatus(bean.getStatus());
        entity.setAction("C");
        entity.setFlag("Y");
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            mesinAbsensiDetailOnCallDao.addAndSave(entity);
        } catch (GeneralBOException e) {
            logger.error("[AbsensiPegawaiBoImpl.saveAddAbsensiOnCall] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving new data Absensi Pegawai, please info to your admin..." + e.getMessage());
        }
    }

    @Override
    public List<MesinAbsensiDetailOnCall> getAbsensiOnCallByCriteria(MesinAbsensiDetailOnCall bean) throws GeneralBOException {

        List<ItHrisMesinAbsensiDetailOnCallEntity> entities = new ArrayList<>();
        List<MesinAbsensiDetailOnCall> result = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if  (bean.getPin() != null && !bean.getPin().equalsIgnoreCase("")) {
            hsCriteria.put("pin", bean.getPin());
        }
        if (bean.getTanggalDari() != null) {
            hsCriteria.put("tanggal_dari", bean.getTanggalDari());
        }
        hsCriteria.put("flag", "Y");

        try {
            entities = mesinAbsensiDetailOnCallDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e){
            logger.error("[AbsensiPegawaiBoImpl.getAbsensiOnCallByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving new data Absensi Pegawai, please info to your admin..." + e.getMessage());
        }

        for (ItHrisMesinAbsensiDetailOnCallEntity item : entities) {
            MesinAbsensiDetailOnCall mesinAbsensiDetailOnCall = new MesinAbsensiDetailOnCall();
            mesinAbsensiDetailOnCall.setMesinAbsensiDetailOnCallId(item.getMesinAbsensiDetailOnCallId());
            mesinAbsensiDetailOnCall.setScanDate(item.getScanDate());
            mesinAbsensiDetailOnCall.setStatus(item.getStatus());

            result.add(mesinAbsensiDetailOnCall);
        }

        return result;

    }

    @Override
    public List<AbsensiOnCall> getAbsensiOnCall(AbsensiOnCall search){
        List<AbsensiOnCall> list = new ArrayList<>();

        List<ItHrisAbsensiOnCallEntity> historyOnCallEntities = absensiOnCallDao.getByTanggalAndNip(search.getNip(),search.getTanggal());
        for (ItHrisAbsensiOnCallEntity data : historyOnCallEntities){
            AbsensiOnCall newData = new AbsensiOnCall();
            newData.setAbsensiOnCallId(data.getAbsensiOnCallId());
            newData.setNip(data.getNip());
            newData.setJamMasuk(data.getJamMasuk());
            newData.setJamPulang(data.getJamPulang());
            newData.setLamaLembur(data.getLamaLembur());

            newData.setFlag(data.getFlag());
            newData.setAction(data.getAction());
            newData.setCreatedWho(data.getCreatedWho());
            newData.setCreatedDate(data.getCreatedDate());
            newData.setLastUpdate(data.getLastUpdate());
            newData.setLastUpdateWho(data.getLastUpdateWho());

            list.add(newData);
        }
        return list;
    }
}