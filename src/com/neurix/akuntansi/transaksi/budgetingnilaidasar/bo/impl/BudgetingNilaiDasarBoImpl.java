package com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo.impl;

import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo.BudgetingNilaiDasarBo;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao.MasterBudgetingNilaiDasarDao;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao.TransBudgetingNilaiDasarDao;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.BudgetingNilaiDasar;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ImAkunBudgetingNilaiDasarEntity;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ItAkunBudgetingNilaiDasarEntity;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by reza on 10/08/20.
 */
public class BudgetingNilaiDasarBoImpl implements BudgetingNilaiDasarBo {

    private static transient Logger logger = Logger.getLogger(BudgetingNilaiDasarBoImpl.class);
    private MasterBudgetingNilaiDasarDao masterBudgetingNilaiDasarDao;
    private TransBudgetingNilaiDasarDao transBudgetingNilaiDasarDao;

    public void setMasterBudgetingNilaiDasarDao(MasterBudgetingNilaiDasarDao masterBudgetingNilaiDasarDao) {
        this.masterBudgetingNilaiDasarDao = masterBudgetingNilaiDasarDao;
    }

    public void setTransBudgetingNilaiDasarDao(TransBudgetingNilaiDasarDao transBudgetingNilaiDasarDao) {
        this.transBudgetingNilaiDasarDao = transBudgetingNilaiDasarDao;
    }

    @Override
    public List<BudgetingNilaiDasar> searchByTahun(String tahun) {
        return transBudgetingNilaiDasarDao.getBudgetingTahun(tahun);
    }

    @Override
    public List<BudgetingNilaiDasar> getSearchByCriteria(BudgetingNilaiDasar bean) {
        logger.info("[BudgetingNilaiDasarBoImpl.getSearchByCriteria] START >>>");

        List<ItAkunBudgetingNilaiDasarEntity> itAkunBudgetingNilaiDasarEntities = getTransBudgetingNilaiDasarEntity(bean);
        List<BudgetingNilaiDasar> budgetingNilaiDasars = new ArrayList<>();
        if (itAkunBudgetingNilaiDasarEntities.size() > 0){
            BudgetingNilaiDasar budgetingNilaiDasar;
            for (ItAkunBudgetingNilaiDasarEntity nilaiDasarEntity : itAkunBudgetingNilaiDasarEntities){
                budgetingNilaiDasar = new BudgetingNilaiDasar();
                budgetingNilaiDasar.setId(nilaiDasarEntity.getId());
                budgetingNilaiDasar.setTahun(nilaiDasarEntity.getTahun());
                budgetingNilaiDasar.setIdNilaiDasar(nilaiDasarEntity.getIdNilaiDasar());
                budgetingNilaiDasar.setKeterangan(getMasterBudgetingNilaiDasar(nilaiDasarEntity.getId()).getNamaNilaiDasar());
                budgetingNilaiDasar.setNilai(nilaiDasarEntity.getNilai());
                budgetingNilaiDasar.setFlag(nilaiDasarEntity.getFlag());
                budgetingNilaiDasar.setAction(nilaiDasarEntity.getAction());
                budgetingNilaiDasar.setCreatedDate(nilaiDasarEntity.getCreatedDate());
                budgetingNilaiDasar.setCreatedWho(nilaiDasarEntity.getCreatedWho());
                budgetingNilaiDasar.setLastUpdate(nilaiDasarEntity.getLastUpdate());
                budgetingNilaiDasar.setLastUpdateWho(nilaiDasarEntity.getLastUpdateWho());
                budgetingNilaiDasars.add(budgetingNilaiDasar);
            }
        }

        logger.info("[BudgetingNilaiDasarBoImpl.getSearchByCriteria] END <<<");
        return budgetingNilaiDasars;
    }

    private List<ItAkunBudgetingNilaiDasarEntity> getTransBudgetingNilaiDasarEntity(BudgetingNilaiDasar bean) throws GeneralBOException{
        logger.info("[BudgetingNilaiDasarBoImpl.getBudgetingNilaiDasarEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getBranchId() != null)
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getTahun() != null)
            hsCriteria.put("tahun", bean.getTahun());
        if (bean.getFlag() != null)
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getIdNilaiDasar() != null)
            hsCriteria.put("id_nilai_dasar", bean.getIdNilaiDasar());

        List<ItAkunBudgetingNilaiDasarEntity> budgetingNilaiDasarEntities = new ArrayList<>();
        try {
            budgetingNilaiDasarEntities = transBudgetingNilaiDasarDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.info("[BudgetingNilaiDasarBoImpl.getBudgetingNilaiDasarEntity] ERROR. ", e);
            throw new GeneralBOException("[BudgetingNilaiDasarBoImpl.getBudgetingNilaiDasarEntity] ERROR. ", e);
        }

        logger.info("[BudgetingNilaiDasarBoImpl.getBudgetingNilaiDasarEntity] END <<<");
        return budgetingNilaiDasarEntities;
    }

    private ImAkunBudgetingNilaiDasarEntity getMasterBudgetingNilaiDasar(String id){
        return masterBudgetingNilaiDasarDao.getById("idNilaiDasar", id);
    }

    private ItAkunBudgetingNilaiDasarEntity getTransBudgetingNilaiDasarByIdAndTahun(String id, String tahun){
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_nilai_dasar", id);
        hsCriteria.put("tahun", tahun);
        List<ItAkunBudgetingNilaiDasarEntity> budgetingNilaiDasarEntities = new ArrayList<>();
        try {
            budgetingNilaiDasarEntities = transBudgetingNilaiDasarDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.info("[BudgetingNilaiDasarBoImpl.getTransBudgetingNilaiDasarAndTahun] ERROR. ", e);
            throw new GeneralBOException("[BudgetingNilaiDasarBoImpl.getTransBudgetingNilaiDasarAndTahun] ERROR. ", e);
        }
        if (budgetingNilaiDasarEntities.size() > 0)
            return budgetingNilaiDasarEntities.get(0);
        return null;
    }

    @Override
    public void saveTransBudgeting(List<BudgetingNilaiDasar> budgetingNilaiDasarList) {
        logger.info("[BudgetingNilaiDasarBoImpl.saveTransBudgeting] START >>>");

        for (BudgetingNilaiDasar nilaiDasar : budgetingNilaiDasarList){

            BudgetingNilaiDasar budgetingNilaiDasar = new BudgetingNilaiDasar();
            budgetingNilaiDasar.setTahun(nilaiDasar.getTahun());
            budgetingNilaiDasar.setIdNilaiDasar(nilaiDasar.getIdNilaiDasar());
            List<ItAkunBudgetingNilaiDasarEntity> itAkunBudgetingNilaiDasarEntities = getTransBudgetingNilaiDasarEntity(budgetingNilaiDasar);
            boolean isAvail = itAkunBudgetingNilaiDasarEntities.size() > 0;
            if (isAvail){

                ItAkunBudgetingNilaiDasarEntity budgetingNilaiDasarEntity = itAkunBudgetingNilaiDasarEntities.get(0);
                budgetingNilaiDasarEntity.setIdNilaiDasar(nilaiDasar.getIdNilaiDasar());
                budgetingNilaiDasarEntity.setNilai(nilaiDasar.getNilai());
                budgetingNilaiDasarEntity.setLastUpdate(nilaiDasar.getLastUpdate());
                budgetingNilaiDasarEntity.setLastUpdateWho(nilaiDasar.getLastUpdateWho());
                budgetingNilaiDasarEntity.setAction("U");

                try {
                    transBudgetingNilaiDasarDao.updateAndSave(budgetingNilaiDasarEntity);
                } catch (HibernateException e){
                    logger.info("[BudgetingNilaiDasarBoImpl.saveTransBudgeting] ERROR when update. ", e);
                    throw new GeneralBOException("[BudgetingNilaiDasarBoImpl.saveTransBudgeting] START when update .. ", e);
                }
            } else {

                ItAkunBudgetingNilaiDasarEntity budgetingNilaiDasarEntity = new ItAkunBudgetingNilaiDasarEntity();
                budgetingNilaiDasarEntity.setId(generateIdNilaiDasar(nilaiDasar.getTahun()));
                budgetingNilaiDasarEntity.setNilai(nilaiDasar.getNilai());
                budgetingNilaiDasarEntity.setIdNilaiDasar(nilaiDasar.getIdNilaiDasar());
                budgetingNilaiDasarEntity.setTahun(nilaiDasar.getTahun());
                budgetingNilaiDasarEntity.setFlag("Y");
                budgetingNilaiDasarEntity.setAction("C");
                budgetingNilaiDasarEntity.setCreatedDate(nilaiDasar.getLastUpdate());
                budgetingNilaiDasarEntity.setCreatedWho(nilaiDasar.getLastUpdateWho());
                budgetingNilaiDasarEntity.setLastUpdate(nilaiDasar.getLastUpdate());
                budgetingNilaiDasarEntity.setLastUpdateWho(nilaiDasar.getLastUpdateWho());

                try {
                    transBudgetingNilaiDasarDao.addAndSave(budgetingNilaiDasarEntity);
                } catch (HibernateException e){
                    logger.info("[BudgetingNilaiDasarBoImpl.saveTransBudgeting] ERROR when add. ", e);
                    throw new GeneralBOException("[BudgetingNilaiDasarBoImpl.saveTransBudgeting] START when add .. ", e);
                }
            }
        }
        logger.info("[BudgetingNilaiDasarBoImpl.saveTransBudgeting] END <<<");
    }

    @Override
    public List<ImAkunBudgetingNilaiDasarEntity> getListMasterNilaiDasarEntityByCriteria(BudgetingNilaiDasar bean) throws GeneralBOException {
        logger.info("[BudgetingNilaiDasarBoImpl.getListNilaiDasarEntityByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getFlag() != null)
            hsCriteria.put("flag", bean.getFlag());

        List<ImAkunBudgetingNilaiDasarEntity> budgetingNilaiDasarEntities = new ArrayList<>();
        try {
            budgetingNilaiDasarEntities = masterBudgetingNilaiDasarDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.info("[BudgetingNilaiDasarBoImpl.getListNilaiDasarEntityByCriteria] START. ", e);
            throw new GeneralBOException("[BudgetingNilaiDasarBoImpl.getListNilaiDasarEntityByCriteria] START. ", e);
        }


        logger.info("[BudgetingNilaiDasarBoImpl.getListNilaiDasarEntityByCriteria] END <<<");
        return budgetingNilaiDasarEntities;
    }

    @Override
    public List<BudgetingNilaiDasar> getListMasterNilaiDasarAdd(BudgetingNilaiDasar bean) throws GeneralBOException {
        logger.info("[BudgetingNilaiDasarBoImpl.getListMasterNilaiDasarAdd] START >>>");

        List<BudgetingNilaiDasar> results = new ArrayList<>();

        // check tahun ini jika ada, Sigit
        List<ItAkunBudgetingNilaiDasarEntity> transNilaiDasars = getTransBudgetingNilaiDasarEntity(bean);
        boolean isAvail = transNilaiDasars.size() > 0;
        boolean notFound = false;
        if (isAvail){

            List<ImAkunBudgetingNilaiDasarEntity> masterNilaiDasars = getListMasterNilaiDasarEntityByCriteria(bean);

            BudgetingNilaiDasar nilaiDasar;
            for (ImAkunBudgetingNilaiDasarEntity masterNilaiDasar : masterNilaiDasars){
                nilaiDasar = new BudgetingNilaiDasar();
                nilaiDasar.setIdNilaiDasar(masterNilaiDasar.getId());
                nilaiDasar.setKeterangan(masterNilaiDasar.getNamaNilaiDasar());
                nilaiDasar.setTipe(masterNilaiDasar.getTipe());
                List<ItAkunBudgetingNilaiDasarEntity> filterTransList = transNilaiDasars.stream().filter( p -> p.getIdNilaiDasar().equalsIgnoreCase(masterNilaiDasar.getId())).collect(Collectors.toList());
                if (filterTransList.size() > 0)
                    nilaiDasar.setNilai(filterTransList.get(0).getNilai());
                results.add(nilaiDasar);
            }
        } else {
            // jika tidak ada
            // check tahun lalu jika ada, Sigit
            String tahunLalu = tahunLaluDari(bean.getTahun());
            bean.setTahun(tahunLalu);
            transNilaiDasars = getTransBudgetingNilaiDasarEntity(bean);
            isAvail = transNilaiDasars.size() > 0;
            if (isAvail){
                List<ImAkunBudgetingNilaiDasarEntity> masterNilaiDasars = getListMasterNilaiDasarEntityByCriteria(bean);

                BudgetingNilaiDasar nilaiDasar;
                for (ImAkunBudgetingNilaiDasarEntity masterNilaiDasar : masterNilaiDasars){
                    nilaiDasar = new BudgetingNilaiDasar();
                    nilaiDasar.setIdNilaiDasar(masterNilaiDasar.getId());
                    nilaiDasar.setKeterangan(masterNilaiDasar.getNamaNilaiDasar());
                    nilaiDasar.setTipe(masterNilaiDasar.getTipe());
                    List<ItAkunBudgetingNilaiDasarEntity> filterTransList = transNilaiDasars.stream().filter( p -> p.getIdNilaiDasar().equalsIgnoreCase(masterNilaiDasar.getId())).collect(Collectors.toList());
                    if (filterTransList.size() > 0)
                        nilaiDasar.setNilai(filterTransList.get(0).getNilai());
                    results.add(nilaiDasar);
                }
            } else {

                // jika tidak ada, Sigit
                List<ImAkunBudgetingNilaiDasarEntity> masterNilaiDasars = getListMasterNilaiDasarEntityByCriteria(bean);

                BudgetingNilaiDasar nilaiDasar;
                for (ImAkunBudgetingNilaiDasarEntity masterNilaiDasar : masterNilaiDasars){
                    nilaiDasar = new BudgetingNilaiDasar();
                    nilaiDasar.setIdNilaiDasar(masterNilaiDasar.getId());
                    nilaiDasar.setKeterangan(masterNilaiDasar.getNamaNilaiDasar());
                    nilaiDasar.setTipe(masterNilaiDasar.getTipe());
                    results.add(nilaiDasar);
                }
            }
        }

        logger.info("[BudgetingNilaiDasarBoImpl.getListMasterNilaiDasarAdd] END <<<");
        return results;
    }

    @Override
    public List<BudgetingNilaiDasar> getTransNilaiDasarByTahun(BudgetingNilaiDasar bean) throws GeneralBOException {
        logger.info("[BudgetingNilaiDasarBoImpl.getTransNilaiDasarByTahun] START >>>");

        List<ItAkunBudgetingNilaiDasarEntity> transNilaiDasars = getTransBudgetingNilaiDasarEntity(bean);
        List<ImAkunBudgetingNilaiDasarEntity> masterNilaiDasars = getListMasterNilaiDasarEntityByCriteria(bean);
        List<BudgetingNilaiDasar> results = new ArrayList<>();
        if (transNilaiDasars != null && transNilaiDasars.size() > 0){
            for (ImAkunBudgetingNilaiDasarEntity masterNilaiDasar : masterNilaiDasars){
                BudgetingNilaiDasar nilaiDasar = new BudgetingNilaiDasar();
                nilaiDasar.setIdNilaiDasar(masterNilaiDasar.getId());
                nilaiDasar.setKeterangan(masterNilaiDasar.getNamaNilaiDasar());
                nilaiDasar.setTipe(masterNilaiDasar.getTipe());

                List<ItAkunBudgetingNilaiDasarEntity> filterTransList = transNilaiDasars.stream().filter( p -> p.getIdNilaiDasar().equalsIgnoreCase(masterNilaiDasar.getId())).collect(Collectors.toList());
                if (filterTransList.size() > 0)
                    nilaiDasar.setNilai(filterTransList.get(0).getNilai());
                if ("persen".equalsIgnoreCase(masterNilaiDasar.getTipe())){
                    BigDecimal nilai = nilaiDasar.getNilai() == null ? new BigDecimal(0) : nilaiDasar.getNilai();
                    nilaiDasar.setNilaiTotal(nilai.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP, 2));
                } else {
                    nilaiDasar.setNilaiTotal(nilaiDasar.getNilai());
                }
                results.add(nilaiDasar);
            }
        }

        logger.info("[BudgetingNilaiDasarBoImpl.getTransNilaiDasarByTahun] END <<<");
        return results;
    }

    private String tahunLaluDari(String tahun){
        Integer intTahun = Integer.valueOf(tahun);
        intTahun --;
        return String.valueOf(intTahun);
    }

    private String generateIdNilaiDasar(String tahun){
        return tahun + transBudgetingNilaiDasarDao.getNextSeq();
    }
}
