package com.neurix.hris.master.dashboardAbsensi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.dashboardAbsensi.bo.DashboardAbsensiBo;
import com.neurix.hris.master.dashboardAbsensi.dao.DashboardAbsensiDao;
import com.neurix.hris.master.dashboardAbsensi.model.DashboardAbsensi;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class DashboardAbsensiBoImpl implements DashboardAbsensiBo {

    protected static transient Logger logger = Logger.getLogger(DashboardAbsensiBoImpl.class);
    private DashboardAbsensiDao dashboardAbsensiDao;
    private BiodataDao biodataDao;
    private PositionBagianDao positionBagianDao;
    private DepartmentDao departmentDao;

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public DashboardAbsensiDao getDashboardAbsensiDao() {
        return dashboardAbsensiDao;
    }

    public void setDashboardAbsensiDao(DashboardAbsensiDao dashboardAbsensiDao) {
        this.dashboardAbsensiDao = dashboardAbsensiDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
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
        DashboardAbsensiBoImpl.logger = logger;
    }

    @Override
    public void saveDelete(DashboardAbsensi bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(DashboardAbsensi bean) throws GeneralBOException {

    }

    @Override
    public DashboardAbsensi saveAdd(DashboardAbsensi bean) throws GeneralBOException {
        logger.info("[UpdateGolonganBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<DashboardAbsensi> getByCriteria(DashboardAbsensi searchBean) throws GeneralBOException {

        return null;
    }

    @Override
    public List<DashboardAbsensi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<DashboardAbsensi> getComboGolonganWithCriteria(String query) throws GeneralBOException {
        return null;
    }

    @Override
    public List<DashboardAbsensi> getKaryawanTelat(String bulan, String tahun) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();

        listDashboard = dashboardAbsensiDao.getKaryawanTelat(bulan, tahun);

        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> getKaryawanAbsensiTelat(String tanggal) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();
        String []tanggalEdit = tanggal.split("-");
        listDashboard = dashboardAbsensiDao.getKaryawanAbsensiTelat(tanggalEdit[2] + tanggalEdit[1] + tanggalEdit[0]);
        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> getKaryawanAbsensiMangkir(String tanggal) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();
        String []tanggalEdit = tanggal.split("-");
        listDashboard = dashboardAbsensiDao.getKaryawanAbsensiMangkir(tanggalEdit[2] + tanggalEdit[1] + tanggalEdit[0]);
        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> getKaryawanAbsensiRajin(String tanggal) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();
        String []tanggalEdit = tanggal.split("-");
        listDashboard = dashboardAbsensiDao.getKaryawanAbsensiRajin(tanggalEdit[2] + tanggalEdit[1] + tanggalEdit[0]);
        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> getKaryawanTelat(String bulan, String tahun, String nip) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();

        listDashboard = dashboardAbsensiDao.getKaryawanTelat(bulan, tahun, nip);

        return listDashboard;
    }

    // medical records
    @Override
    public List<DashboardAbsensi> dashboardMedical(String bulan, String tahun) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();

        listDashboard = dashboardAbsensiDao.dashboardMedicalRecords(bulan, tahun);

        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> dashboardLembur(String bulan, String tahun) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();
        String tanggal1 = "";
        String tanggal2 = "";

        int strBulan = 0 ;
        int strTahun = Integer.parseInt(tahun);

        if(!bulan.equalsIgnoreCase("-")){
            strBulan = Integer.parseInt(bulan) - 1;
            if(strBulan < 1){
                strBulan = 12;
                strTahun--;
            }

            tanggal1 = strTahun + "-" + strBulan  + "-11";
            tanggal2 = tahun + "-" + bulan + "-10";
        }else{
            strTahun--;
            tanggal1 = strTahun + "-" + 12 + "-11";
            tanggal2 = tahun + "-" + 12 + "-10";
        }


        listDashboard = dashboardAbsensiDao.dashboardLembur(tanggal1, tanggal2);

        return listDashboard;
    }

    // medical record detail by divisi
    @Override
    public List<DashboardAbsensi> dashboardMedical(String bulan, String tahun, String divisiId) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();

        listDashboard = dashboardAbsensiDao.dashboardMedicalRecords(bulan, tahun, divisiId);

        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> dashboardLembur(String bulan, String tahun, String divisiId) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();

        String tanggal1 = "";
        String tanggal2 = "";

        int strBulan = 0 ;
        int strTahun = Integer.parseInt(tahun);

        if(!bulan.equalsIgnoreCase("-")){
            strBulan = Integer.parseInt(bulan) - 1;
            if(strBulan < 1){
                strBulan = 12;
                strTahun--;
            }

            tanggal1 = strTahun + "-" + strBulan  + "-11";
            tanggal2 = tahun + "-" + bulan + "-10";
        }else{
            strTahun--;
            tanggal1 = strTahun + "-" + 12 + "-11";
            tanggal2 = tahun + "-" + 12 + "-10";
        }

        listDashboard = dashboardAbsensiDao.dashboardLembur(tanggal1, tanggal2, divisiId);

        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> dashboardMedicalRecordsTop(String bulan, String tahun, String divisiName) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();
        String divisiId = "";

        if(divisiName != null && !divisiName.equalsIgnoreCase("")){
            if(!divisiName.equalsIgnoreCase("Kandir")){
                List<ImDepartmentEntity> imDepartmentEntity = departmentDao.getListDepartment(divisiName);
                if(imDepartmentEntity.size() > 0){
                    for(ImDepartmentEntity departmentLoop: imDepartmentEntity){
                        divisiId = departmentLoop.getDepartmentId();
                    }
                }
            }
        }

        listDashboard = dashboardAbsensiDao.dashboardMedicalRecordsTop(bulan, tahun, divisiId);

        return listDashboard;
    }

    @Override
    public List<DashboardAbsensi> dashboardLemburTop(String bulan, String tahun, String divisiName) throws GeneralBOException {
        List<DashboardAbsensi> listDashboard = new ArrayList<>();
        String divisiId = "";

        if(divisiName != null && !divisiName.equalsIgnoreCase("")){
            if(!divisiName.equalsIgnoreCase("Kandir")){
                List<ImDepartmentEntity> imDepartmentEntity = departmentDao.getListDepartment(divisiName);
                if(imDepartmentEntity.size() > 0){
                    for(ImDepartmentEntity departmentLoop: imDepartmentEntity){
                        divisiId = departmentLoop.getDepartmentId();
                    }
                }
            }
        }

        String tanggal1 = "";
        String tanggal2 = "";

        int strBulan = 0 ;
        int strTahun = Integer.parseInt(tahun);

        if(!bulan.equalsIgnoreCase("-")){
            strBulan = Integer.parseInt(bulan) - 1;
            if(strBulan < 1){
                strBulan = 12;
                strTahun--;
            }

            tanggal1 = strTahun + "-" + strBulan  + "-11";
            tanggal2 = tahun + "-" + bulan + "-10";
        }else{
            strTahun--;
            tanggal1 = strTahun + "-" + 12 + "-11";
            tanggal2 = tahun + "-" + 12 + "-10";
        }

        listDashboard = dashboardAbsensiDao.dashboardLemburTop(tanggal1, tanggal2, divisiId);

        return listDashboard;
    }
}
