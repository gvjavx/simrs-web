package com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.impl;

import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ImAkunBudgetingNilaiDasarEntity;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ItAkunBudgetingNilaiDasarEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.BudgetingPerhitunganBo;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.dao.*;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.*;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by reza on 14/08/20.
 */
public class BudgetingPerhitunganBoImpl implements BudgetingPerhitunganBo {
    private static transient Logger logger = Logger.getLogger(BudgetingPerhitunganBoImpl.class);
    private PerhitunganBudgetingDao perhitunganBudgetingDao;
    private ParameterBudgetingDao parameterBudgetingDao;
    private JenisBudgetingDao jenisBudgetingDao;
    private KategoriParameterBudgetingDao kategoriParameterBudgetingDao;
    private NilaiParameterDao nilaiParameterDao;
    private PositionDao positionDao;
    private MasterDao masterDao;

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public void setKategoriParameterBudgetingDao(KategoriParameterBudgetingDao kategoriParameterBudgetingDao) {
        this.kategoriParameterBudgetingDao = kategoriParameterBudgetingDao;
    }

    public void setNilaiParameterDao(NilaiParameterDao nilaiParameterDao) {
        this.nilaiParameterDao = nilaiParameterDao;
    }

    public void setPerhitunganBudgetingDao(PerhitunganBudgetingDao perhitunganBudgetingDao) {
        this.perhitunganBudgetingDao = perhitunganBudgetingDao;
    }

    public void setParameterBudgetingDao(ParameterBudgetingDao parameterBudgetingDao) {
        this.parameterBudgetingDao = parameterBudgetingDao;
    }

    public void setJenisBudgetingDao(JenisBudgetingDao jenisBudgetingDao) {
        this.jenisBudgetingDao = jenisBudgetingDao;
    }

    private String getNextIdPerhitungan(String idParameter){
        return idParameter + perhitunganBudgetingDao.getNextId();
    }

    @Override
    public List<ImAkunParameterBudgetingEntity> getListParameterBudgetingEntity(ParameterBudgeting bean) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getIdJenisBudgeting() != null)
            hsCriteria.put("id_jenis_budgeting", bean.getIdJenisBudgeting());
        if (bean.getIdKategoriBudgeting() != null)
            hsCriteria.put("id_kategori_budgeting", bean.getIdKategoriBudgeting());

        List<ImAkunParameterBudgetingEntity> parameterBudgetingEntities = new ArrayList<>();
        try {
            parameterBudgetingEntities = parameterBudgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] ERROR. ", e);
            throw new GeneralBOException("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] ERROR. ", e);
        }
        logger.info("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] END <<<");
        return parameterBudgetingEntities;
    }

    @Override
    public List<ItAkunPerhitunganBudgetingEntity> getListPerhitunganBudgetingEntity(PerhitunganBudgeting bean) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] START >>>");
        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getIdParameterBudgeting() != null)
            hsCriteria.put("id_parameter_budgeting", bean.getIdParameterBudgeting());
        if (bean.getBranchId() != null)
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getTahun() != null)
            hsCriteria.put("tahun", bean.getTahun());

        List<ItAkunPerhitunganBudgetingEntity> perhitunganBudgetingEntities = new ArrayList<>();
        try {
            perhitunganBudgetingEntities = perhitunganBudgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] ERROR. ", e);
            throw new GeneralBOException("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] ERROR. ", e);
        }
        logger.info("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] END <<<");
        return perhitunganBudgetingEntities;
    }

    @Override
    public List<PerhitunganBudgeting> getListPendapatanTindakan(String branchId, String bulan, String tahun, String tipe) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListPendapatanTindakan] START >>>");
        if ("RJ".equalsIgnoreCase(tipe)){
            return perhitunganBudgetingDao.getListPerhitunganPendapatanTindakan(branchId, tahun, bulan);
        } else {
            return perhitunganBudgetingDao.getListPerhitunganPendapatanTindakanRi(branchId, tahun, bulan);
        }
    }

    @Override
    public List<PerhitunganBudgeting> getListPendapatanObat(String branchId, String bulan, String tahun, String tipe) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListPendapatanObat] START >>>");
        if ("RJ".equalsIgnoreCase(tipe)){
            return perhitunganBudgetingDao.getListPendapatanObatPeriksa(branchId, tahun, bulan);
        } else {
            return perhitunganBudgetingDao.getListPendapatanObatPeriksaRi(branchId, tahun, bulan);
        }
    }

    @Override
    public List<ParameterBudgeting> getSearchListParameterBudgeting(ParameterBudgeting bean) throws GeneralBOException {

        List<ParameterBudgeting> parameterBudgetings = new ArrayList<>();
        List<ImAkunParameterBudgetingEntity> parameterBudgetingEntityList = getListParameterBudgetingEntity(bean);
        if (parameterBudgetingEntityList.size() > 0){

            for (ImAkunParameterBudgetingEntity paramEntity : parameterBudgetingEntityList){
                ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
                parameterBudgeting.setId(paramEntity.getId());
                parameterBudgeting.setNama(paramEntity.getNama());
                parameterBudgeting.setRekeningId(paramEntity.getRekeningId());
                parameterBudgeting.setTipe(paramEntity.getTipe());
                parameterBudgeting.setIdJenisBudgeting(paramEntity.getIdJenisBudgeting());
                parameterBudgeting.setFlag(paramEntity.getFlag());
                parameterBudgeting.setAction(paramEntity.getAction());
                parameterBudgeting.setCreatedDate(paramEntity.getCreatedDate());
                parameterBudgeting.setCreatedWho(paramEntity.getCreatedWho());
                parameterBudgeting.setLastUpdate(paramEntity.getLastUpdate());
                parameterBudgeting.setLastUpdateWho(paramEntity.getLastUpdateWho());
                parameterBudgeting.setNilaiTotal(new BigDecimal(0));

                PerhitunganBudgeting perhitunganBudgeting = new PerhitunganBudgeting();
                perhitunganBudgeting.setIdParameterBudgeting(paramEntity.getId());
                perhitunganBudgeting.setBranchId(bean.getBranchId());
                perhitunganBudgeting.setTahun(bean.getTahun());
                List<ItAkunPerhitunganBudgetingEntity> perhitunganBudgetingEntities = getListPerhitunganBudgetingEntity(perhitunganBudgeting);
                if (perhitunganBudgetingEntities.size() > 0){
                    BigDecimal nilaiBudgeting = hitungNilaiBudgeting(perhitunganBudgetingEntities);
                    parameterBudgeting.setNilaiTotal(nilaiBudgeting);
                }
                parameterBudgetings.add(parameterBudgeting);
            }
        }

        return parameterBudgetings;
    }

    @Override
    public BigDecimal hitungNilaiBudgeting(List<ItAkunPerhitunganBudgetingEntity> beans) throws GeneralBOException {

        BigDecimal nilai = new BigDecimal(0);
        String opr = "";

        for (ItAkunPerhitunganBudgetingEntity perhitunganEntity : beans){
            if ("".equalsIgnoreCase(opr)){
                nilai = perhitunganEntity.getNilai();
                opr = perhitunganEntity.getOperator();
            } else {
                if ("=".equalsIgnoreCase(perhitunganEntity.getOperator())){
                    nilai = hitung(nilai, opr, perhitunganEntity.getNilai());
                    break;
                } else {
                    nilai = hitung(nilai, opr, perhitunganEntity.getNilai());
                    opr = perhitunganEntity.getOperator();
                }
            }
        }

        return nilai;
    }

    private BigDecimal hitung(BigDecimal n1, String opr, BigDecimal n2){

        MathContext m = new MathContext(3);
        if ("+".equalsIgnoreCase(opr))
            return n1.add(n2);
        if ("-".equalsIgnoreCase(opr))
            return n1.subtract(n2);
        if ("*".equalsIgnoreCase(opr))
            return n1.multiply(n2);
        if ("/".equalsIgnoreCase(opr))
            return n1.divide(n2, BigDecimal.ROUND_HALF_UP, 2).round(m);
        return new BigDecimal(0);
    }

    @Override
    public void saveAddPerhitunganBudgeting(List<ItAkunNilaiParameterBudgetingEntity> nilaiParameters,  List<ItAkunPerhitunganBudgetingEntity> listPerhitungan, PerhitunganBudgeting bean) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] START >>> ");

        for (ItAkunNilaiParameterBudgetingEntity nilaiParameterEntity : nilaiParameters){

            nilaiParameterEntity.setTahun(bean.getTahun());
            nilaiParameterEntity.setBranchId(bean.getBranchId());
            nilaiParameterEntity.setFlag(bean.getFlag());
            nilaiParameterEntity.setAction(bean.getAction());
            nilaiParameterEntity.setCreatedDate(bean.getCreatedDate());
            nilaiParameterEntity.setCreatedWho(bean.getCreatedWho());
            nilaiParameterEntity.setLastUpdate(bean.getLastUpdate());
            nilaiParameterEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                nilaiParameterDao.addAndSave(nilaiParameterEntity);
            } catch (HibernateException e){
                logger.error("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] ERROR when save nilai parameter. ", e);
                throw new GeneralBOException("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] ERROR when save nilai parameter. ", e);
            }

            List<ItAkunPerhitunganBudgetingEntity> filterListPerhitungan = listPerhitungan.stream().filter(p->p.getIdNilaiParameter().equalsIgnoreCase(nilaiParameterEntity.getId())).collect(Collectors.toList());
            if (filterListPerhitungan.size() > 0){
                int i = 1;
                for (ItAkunPerhitunganBudgetingEntity perhitunganEntity : filterListPerhitungan){
                    perhitunganEntity.setId(getNextIdPerhitungan(nilaiParameterEntity.getId()));
                    perhitunganEntity.setCreatedDate(bean.getCreatedDate());
                    perhitunganEntity.setCreatedWho(bean.getCreatedWho());
                    perhitunganEntity.setLastUpdate(bean.getLastUpdate());
                    perhitunganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    perhitunganEntity.setFlag(bean.getFlag());
                    perhitunganEntity.setAction(bean.getAction());
                    perhitunganEntity.setTahun(bean.getTahun());
                    perhitunganEntity.setBranchId(bean.getBranchId());
                    perhitunganEntity.setUrutan(i++);

                    try {
                        perhitunganBudgetingDao.addAndSave(perhitunganEntity);
                    } catch (HibernateException e){
                        logger.error("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] ERROR when save perhitungan. ", e);
                        throw new GeneralBOException("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] ERROR when save perhitungan. ", e);
                    }
                }
            }

        }
    }

    @Override
    public String getNextIdNilaiParameter(String branchId, String tahun, String idNilai) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getNextIdNilaiParameter] START >>> ");
        return idNilai + branchId + tahun + nilaiParameterDao.getNextSeq();
    }

    @Override
    public List<KategoriParameter> getListKategoriParameter(KategoriParameter bean) throws GeneralBOException {

        List<KategoriParameter> kategoriParameters = new ArrayList<>();
        List<ImAkunKategoriParameterBudgetingEntity> kategoriEntityList = getListEntityKategoriParameter(bean);
        if (kategoriEntityList.size() > 0){

            for (ImAkunKategoriParameterBudgetingEntity entity : kategoriEntityList){
                KategoriParameter kategoriParameter = new KategoriParameter();
                kategoriParameter.setId(entity.getId());
                kategoriParameter.setNama(entity.getNama());
                kategoriParameter.setIdJenisBudgeting(entity.getIdJenisBudgeting());
                kategoriParameter.setNilaiTotal(getNilaiTotalKategori(entity.getId(), bean.getTahun(), bean.getBranchId()));
                kategoriParameter.setFlag(entity.getFlag());
                kategoriParameter.setAction(entity.getAction());
                kategoriParameter.setCreatedDate(entity.getCreatedDate());
                kategoriParameter.setCreatedWho(entity.getCreatedWho());
                kategoriParameter.setLastUpdate(entity.getLastUpdate());
                kategoriParameter.setLastUpdateWho(entity.getLastUpdateWho());
                kategoriParameters.add(kategoriParameter);
            }
        }

        return kategoriParameters;
    }
    private List<ImAkunKategoriParameterBudgetingEntity> getListEntityKategoriParameter(KategoriParameter bean) throws GeneralBOException{
        logger.info("[BudgetingPerhitunganBoImpl.getListEntityKategoriParameter] START >>> ");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getIdJenisBudgeting() != null)
            hsCriteria.put("id_jenis_budgeting", bean.getIdJenisBudgeting());
        if (bean.getFlag() != null)
            hsCriteria.put("flag", bean.getFlag());

        List<ImAkunKategoriParameterBudgetingEntity> results = new ArrayList<>();
        try {
            results = kategoriParameterBudgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingPerhitunganBoImpl.getListEntityKategoriParameter] ERROR. ", e);
            throw new GeneralBOException("[BudgetingPerhitunganBoImpl.getListEntityKategoriParameter] ERROR. ", e);
        }
        logger.info("[BudgetingPerhitunganBoImpl.getListEntityKategoriParameter] END <<< ");
        return results;
    }

    private BigDecimal getNilaiTotalKategori(String idKategori, String tahun, String branchId){

        BigDecimal jumlah = new BigDecimal(0);
        if (idKategori != null && !"".equalsIgnoreCase(idKategori)){

            ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
            parameterBudgeting.setIdKategoriBudgeting(idKategori);
            List<ImAkunParameterBudgetingEntity> parameters = getListParameterBudgetingEntity(parameterBudgeting);
            if (parameters.size() > 0){
                for (ImAkunParameterBudgetingEntity parameterBudgetingEntity : parameters){

                    parameterBudgeting = new ParameterBudgeting();
                    parameterBudgeting.setIdParameter(parameterBudgetingEntity.getId());
                    parameterBudgeting.setTahun(tahun);
                    parameterBudgeting.setBranchId(branchId);

                    List<ItAkunNilaiParameterBudgetingEntity> nilaiParams = getListEntityNilaiParam(parameterBudgeting);
                    if (nilaiParams.size() > 0){
                        for (ItAkunNilaiParameterBudgetingEntity nilaiParam : nilaiParams){
                            jumlah = jumlah.add(nullEscape(nilaiParam.getNilaiTotal()));
                        }
                    }
                }
            }
        }
        return jumlah;
    }

    private List<ItAkunNilaiParameterBudgetingEntity> getListEntityNilaiParam(ParameterBudgeting bean) throws GeneralBOException{
        logger.info("[BudgetingPerhitunganBoImpl.getListEntityNilaiParam] START >>> ");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getIdParameter() != null)
            hsCriteria.put("id_parameter", bean.getIdParameter());
        if (bean.getMasterId() != null)
            hsCriteria.put("master_id", bean.getMasterId());
        if (bean.getDivisiId() != null)
            hsCriteria.put("divisi_id", bean.getDivisiId());
        if (bean.getBranchId() != null)
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getTahun() != null)
            hsCriteria.put("tahun", bean.getTahun());
        if (bean.getFlag() != null)
            hsCriteria.put("flag", bean.getFlag());

        List<ItAkunNilaiParameterBudgetingEntity> results = new ArrayList<>();
        try {
            results = nilaiParameterDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingPerhitunganBoImpl.getListEntityNilaiParam] ERROR. ", e);
            throw new GeneralBOException("[BudgetingPerhitunganBoImpl.getListEntityNilaiParam] ERROR. ", e);
        }
        logger.info("[BudgetingPerhitunganBoImpl.getListEntityNilaiParam] END <<< ");
        return results;
    }

    private BigDecimal nullEscape(BigDecimal nilai){
        if (nilai == null)
            return new BigDecimal(0);
        return nilai;
    }

    @Override
    public ImPosition getPositionByKodering(String id) throws GeneralBOException {
        return positionDao.getById("kodering", id);
    }

    @Override
    public ImMasterEntity getMasterByKodering(String id) throws GeneralBOException {
        return masterDao.getById("primaryKey.nomorMaster", id);
    }
}
