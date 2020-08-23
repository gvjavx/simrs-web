package com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.impl;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ImAkunBudgetingNilaiDasarEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.BudgetingPerhitunganBo;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.dao.*;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.*;
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

    private String getNextIdPerhitungan(String branchId, String idParameter, String tahun){
        return branchId + tahun + idParameter + perhitunganBudgetingDao.getNextId();
    }

    @Override
    public List<ImAkunParameterBudgetingEntity> getListParameterBudgetingEntity(ParameterBudgeting bean) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getIdJenisBudgeting() != null)
            hsCriteria.put("id_jenis_budgeting", bean.getIdJenisBudgeting());

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
                parameterBudgeting.setNilaiTotalBudgeting(new BigDecimal(0));

                PerhitunganBudgeting perhitunganBudgeting = new PerhitunganBudgeting();
                perhitunganBudgeting.setIdParameterBudgeting(paramEntity.getId());
                perhitunganBudgeting.setBranchId(bean.getBranchId());
                perhitunganBudgeting.setTahun(bean.getTahun());
                List<ItAkunPerhitunganBudgetingEntity> perhitunganBudgetingEntities = getListPerhitunganBudgetingEntity(perhitunganBudgeting);
                if (perhitunganBudgetingEntities.size() > 0){
                    BigDecimal nilaiBudgeting = hitungNilaiBudgeting(perhitunganBudgetingEntities);
                    parameterBudgeting.setNilaiTotalBudgeting(nilaiBudgeting);
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
                    perhitunganEntity.setId(getNextIdPerhitungan(bean.getBranchId(), bean.getIdParameterBudgeting(), bean.getTahun()));
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

        if (listPerhitungan.size() > 0){
            int i = 1;
            for (ItAkunPerhitunganBudgetingEntity perhitunganEntity : listPerhitungan){
                perhitunganEntity.setId(getNextIdPerhitungan(bean.getBranchId(), bean.getIdParameterBudgeting(), bean.getTahun()));
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
                    logger.error("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] ERROR. ", e);
                    throw new GeneralBOException("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] ERROR. ", e);
                }
            }
        }
        logger.info("[BudgetingPerhitunganBoImpl.saveAddPerhitunganBudgeting] END <<< ");
    }

    @Override
    public String getNextIdNilaiParameter(String branchId, String tahun, String idNilai) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getNextIdNilaiParameter] START >>> ");
        return idNilai + branchId + tahun + nilaiParameterDao.getNextSeq();
    }
}
