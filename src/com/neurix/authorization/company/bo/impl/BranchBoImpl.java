package com.neurix.authorization.company.bo.impl;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesHistory;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import com.neurix.common.util.CommonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class BranchBoImpl implements BranchBo {

    protected static transient Logger logger = Logger.getLogger(BranchBoImpl.class);
    private BranchDao branchDao;

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public List<Branch> getAll() throws GeneralBOException {

        logger.info("[BranchBoImpl.getAll] start process >>>");

        List<Branch> listOfResultBranch = new ArrayList();
        List<ImBranches> listOfBranch = null;
        try {
            listOfBranch = branchDao.getAll();
        } catch (HibernateException e) {
            logger.error("[BranchBoImpl.getAll] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting all data, please info to your admin..." + e.getMessage());
        }

        if ( listOfBranch != null) {
            Branch resultBranch;
            for (ImBranches imBranches : listOfBranch) {
                resultBranch = new Branch();

                resultBranch.setBranchId(imBranches.getPrimaryKey().getId());
                resultBranch.setBranchName(imBranches.getBranchName());
                resultBranch.setBranchAddress(imBranches.getBranchAddress());
                resultBranch.setCreatedWho(imBranches.getCreatedWho());
                resultBranch.setCreatedDate(imBranches.getCreatedDate());
                resultBranch.setLastUpdateWho(imBranches.getLastUpdateWho());
                resultBranch.setLastUpdate(imBranches.getLastUpdate());
                resultBranch.setAction(imBranches.getAction());
                resultBranch.setFlag(imBranches.getFlag());
                resultBranch.setAlamatSurat(imBranches.getAlamatSurat());
                resultBranch.setLogoName(imBranches.getLogoName());

                listOfResultBranch.add(resultBranch);
            }
        }

        logger.info("[BranchBoImpl.getAll] end process <<<");

        return listOfResultBranch;
    }

    public List<Branch> getByCriteria(Branch searchBranch) throws GeneralBOException {

        logger.info("[BranchBoImpl.getByCriteria] start process >>>");

        List<Branch> listOfResultBranch = new ArrayList();

        if (searchBranch != null) {
            Map hsCriteria = new HashMap();
            if (searchBranch.getBranchId() != null && !"".equalsIgnoreCase(searchBranch.getBranchId())) {
                hsCriteria.put("branch_id", searchBranch.getBranchId());
            }

            if (searchBranch.getBranchName() != null && !"".equalsIgnoreCase(searchBranch.getBranchName())) {
                hsCriteria.put("branch_name", searchBranch.getBranchName());
            }

            if (searchBranch.getBranchAddress() != null && !"".equalsIgnoreCase(searchBranch.getBranchAddress())) {
                hsCriteria.put("branch_address", searchBranch.getBranchAddress());
            }

            if (searchBranch.getAreaId() != null && !"".equalsIgnoreCase(searchBranch.getAreaId())) {
                hsCriteria.put("area_id", searchBranch.getAreaId());
            }

            if (searchBranch.getFlag() != null && !"".equalsIgnoreCase(searchBranch.getFlag())) {
                if ("N".equalsIgnoreCase(searchBranch.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBranch.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImBranches> listOfBranch = null;
            try {
                listOfBranch = branchDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BranchBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfBranch != null) {
                Branch resultBranch;
                for (ImBranches imBranches : listOfBranch) {
                    resultBranch = new Branch();

                    resultBranch.setBranchId(imBranches.getPrimaryKey().getId());
                    resultBranch.setBranchName(imBranches.getBranchName());
                    resultBranch.setBranchAddress(imBranches.getBranchAddress());

                    if (imBranches.getUmr()!=null){
                        resultBranch.setUmr(imBranches.getUmr());
                        resultBranch.setStUmr(CommonUtil.numbericFormat(imBranches.getUmr(),"###,###"));
                    }
                    else {
                        resultBranch.setUmr(BigDecimal.valueOf(0));
                        resultBranch.setStUmr(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }

                    if (imBranches.getMinBpjsKs()!=null){
                        resultBranch.setMinBpjsKs(imBranches.getMinBpjsKs());
                        resultBranch.setStMinBpjsKs(CommonUtil.numbericFormat(imBranches.getMinBpjsKs(),"###,###"));
                    }else{
                        resultBranch.setMinBpjsKs(BigDecimal.valueOf(0));
                        resultBranch.setStMinBpjsKs(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }

                    if (imBranches.getMaxBpjsKs()!=null){
                        resultBranch.setMaxBpjsKs(imBranches.getMaxBpjsKs());
                        resultBranch.setStMaxBpjsKs(CommonUtil.numbericFormat(imBranches.getMaxBpjsKs(),"###,###"));
                    }else{
                        resultBranch.setMaxBpjsKs(BigDecimal.valueOf(0));
                        resultBranch.setStMaxBpjsKs(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }

                    if (imBranches.getMinBpjsTk()!=null){
                        resultBranch.setMinBpjsTk(imBranches.getMinBpjsTk());
                        resultBranch.setStMinBpjsTk(CommonUtil.numbericFormat(imBranches.getMinBpjsTk(),"###,###"));
                    }else{
                        resultBranch.setMinBpjsTk(BigDecimal.valueOf(0));
                        resultBranch.setStMinBpjsTk(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }
                    if (imBranches.getMaxBpjsTk()!=null){
                        resultBranch.setMaxBpjsTk(imBranches.getMaxBpjsTk());
                        resultBranch.setStMaxBpjsTk(CommonUtil.numbericFormat(imBranches.getMaxBpjsTk(),"###,###"));
                    }else{
                        resultBranch.setMaxBpjsTk(BigDecimal.valueOf(0));
                        resultBranch.setStMaxBpjsTk(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }
                    if (imBranches.getPercentKsKary()!=null){
                        resultBranch.setPercentKsKary(imBranches.getPercentKsKary());
                        resultBranch.setStPercentKsKary(CommonUtil.numbericFormat(imBranches.getPercentKsKary(),"###,###"));
                    }else{
                        resultBranch.setPercentKsKary(BigDecimal.valueOf(0));
                        resultBranch.setStPercentKsKary(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }
                    if (imBranches.getPercentKsPers()!=null){
                        resultBranch.setPercentKsPers(imBranches.getPercentKsPers());
                        resultBranch.setStPercentKsPers(CommonUtil.numbericFormat(imBranches.getPercentKsPers(),"###,###"));
                    }else{
                        resultBranch.setPercentKsPers(BigDecimal.valueOf(0));
                        resultBranch.setStPercentKsPers(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }
                    if (imBranches.getPercentTkKary()!=null){
                        resultBranch.setPercentTkKary(imBranches.getPercentTkKary());
                        resultBranch.setStPercentTkKary(CommonUtil.numbericFormat(imBranches.getPercentTkKary(),"###,###"));
                    }else{
                        resultBranch.setPercentTkKary(BigDecimal.valueOf(0));
                        resultBranch.setStPercentTkKary(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }
                    if (imBranches.getPercentTkPers()!=null){
                        resultBranch.setPercentTkPers(imBranches.getPercentTkPers());
                        resultBranch.setStPercentTkPers(CommonUtil.numbericFormat(imBranches.getPercentTkPers(),"###,###"));
                    }else{
                        resultBranch.setPercentTkPers(BigDecimal.valueOf(0));
                        resultBranch.setStPercentTkPers(CommonUtil.numbericFormat(BigDecimal.valueOf(0),"###,###"));
                    }
                    resultBranch.setCreatedWho(imBranches.getCreatedWho());
                    resultBranch.setCreatedDate(imBranches.getCreatedDate());
                    resultBranch.setLastUpdateWho(imBranches.getLastUpdateWho());
                    resultBranch.setLastUpdate(imBranches.getLastUpdate());
                    resultBranch.setAction(imBranches.getAction());
                    resultBranch.setFlag(imBranches.getFlag());
                    resultBranch.setEnabled(imBranches.getEnabled());

                    resultBranch.setAreaId(imBranches.getAreaId());
                    resultBranch.setAlamatSurat(imBranches.getAlamatSurat());
                    resultBranch.setLogoName(imBranches.getLogoName());

                    resultBranch.setPpkPelayanan(imBranches.getPpkPelayanan());
                    resultBranch.setSuratSkdp(imBranches.getSuratSkdp());
                    resultBranch.setKodeTarif(imBranches.getKodeTarif());
                    resultBranch.setTarifPayorId(imBranches.getTarifPayorId());
                    resultBranch.setPayorCd(imBranches.getTarifPayorId());
                    resultBranch.setCoderNik(imBranches.getCoderNik());

                    listOfResultBranch.add(resultBranch);
                }
            }

        }

        logger.info("[BranchBoImpl.getByCriteria] end process <<<");

        return listOfResultBranch;
    }

    public Branch saveAdd(Branch branch) throws GeneralBOException {

        logger.info("[BranchBoImpl.saveAdd] start process >>>");

        if (branch != null) {
            ImBranches imBranches = new ImBranches();
            ImBranchesPK primaryKey = new ImBranchesPK();
            primaryKey.setId(branch.getBranchId());

            imBranches.setPrimaryKey(primaryKey);
            imBranches.setBranchName(branch.getBranchName());
            imBranches.setBranchAddress(branch.getBranchAddress());
            imBranches.setUmr(branch.getUmr());
            imBranches.setMinBpjsKs(branch.getMinBpjsKs());
            imBranches.setMaxBpjsKs(branch.getMaxBpjsKs());
            imBranches.setMinBpjsTk(branch.getMinBpjsTk());
            imBranches.setMaxBpjsTk(branch.getMaxBpjsTk());

            imBranches.setPercentKsKary(branch.getPercentKsKary());
            imBranches.setPercentKsPers(branch.getPercentKsPers());
            imBranches.setPercentTkKary(branch.getPercentTkKary());
            imBranches.setPercentTkPers(branch.getPercentTkPers());

            /*imBranches.setMultifikator(branch.getMultifikator());
            imBranches.setMaxJamIjinKeluar(branch.getMaxJamIjinKeluar());
            imBranches.setFaktorJasprod(branch.getFaktorJasprod());
            imBranches.setFaktorJubileum(branch.getFaktorJubileum());
            imBranches.setUangMakan(branch.getUangMakan());
            imBranches.setAlamatSurat(branch.getAlamatSurat());
            imBranches.setBiayaJasprod(branch.getBiayaJasprod());
            imBranches.setStatusPabrik(branch.getStatusPabrik());*/
            imBranches.setCreatedWho(branch.getCreatedWho());
            imBranches.setCreatedDate(branch.getCreatedDate());
            imBranches.setLastUpdateWho(branch.getLastUpdateWho());
            imBranches.setLastUpdate(branch.getLastUpdate());
            imBranches.setAction(branch.getAction());

            imBranches.setAreaId(branch.getAreaId());
            imBranches.setLogoName(branch.getLogoName());
            imBranches.setFlag("Y");

            String branchId = branch.getBranchId();
            boolean isAda ;

            try {
                isAda=branchDao.isExistBranch(branchId);
            } catch (HibernateException e) {
                logger.error("[FunctionBoImpl.saveAddFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when checking exist function, please info to your admin..." + e.getMessage());
            }

            try {
                branchDao.addAndSave(imBranches);
            } catch (HibernateException e) {
                logger.error("[BranchBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Branch, please info to your admin..." + e.getMessage());
            }

        }

        logger.info("[BranchBoImpl.saveAdd] end process <<<");

        return branch;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(branchDao, message, moduleMethod);

        return result;
    }

    public void saveEdit(Branch branchNew) throws GeneralBOException {

        logger.info("[BranchBoImpl.saveEdit] start process >>>");

        if (branchNew != null) {

            //retrieve last data by id
            String branchId = branchNew.getBranchId();

            ImBranches imBranchesOld = null;
            ImBranchesPK primaryKey = new ImBranchesPK();
            primaryKey.setId(branchId);

            try {
                imBranchesOld = branchDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[BranchBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Branch by id, please inform to your admin...," + e.getMessage());
            }

            if (imBranchesOld != null) {

                // move last data to table history
                ImBranchesHistory imBranchesDeactive = new ImBranchesHistory();
                try {
                    BeanUtils.copyProperties(imBranchesDeactive, imBranchesOld);
                } catch (IllegalAccessException e) {
                    logger.error("[BranchBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object BranchOld to ImBranchesBeforeDeactive, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[BranchBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object BranchOld to ImBranchesBeforeDeactive, please info to your admin..." + e.getMessage());
                }

                imBranchesDeactive.setBranchId(imBranchesOld.getPrimaryKey().getId());
                try {
                    branchDao.addAndSaveHistory(imBranchesDeactive);
                } catch (HibernateException e) {
                    logger.error("[BranchBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving deactive data Branch, please info to your admin..." + e.getMessage());
                }

                //update some of last data become new data
                imBranchesOld.setPrimaryKey(primaryKey);
                imBranchesOld.setBranchName(branchNew.getBranchName());
                imBranchesOld.setBranchAddress(branchNew.getBranchAddress());
                imBranchesOld.setUmr(branchNew.getUmr());

                imBranchesOld.setMinBpjsKs(branchNew.getMinBpjsKs());
                imBranchesOld.setMaxBpjsKs(branchNew.getMaxBpjsKs());
                imBranchesOld.setMinBpjsTk(branchNew.getMinBpjsTk());
                imBranchesOld.setMaxBpjsTk(branchNew.getMaxBpjsTk());

                imBranchesOld.setPercentKsKary(branchNew.getPercentKsKary());
                imBranchesOld.setPercentKsPers(branchNew.getPercentKsPers());
                imBranchesOld.setPercentTkKary(branchNew.getPercentTkKary());
                imBranchesOld.setPercentTkPers(branchNew.getPercentTkPers());
                /*imBranchesOld.setStatusPabrik(branchNew.getStatusPabrik());
                imBranchesOld.setMultifikator(branchNew.getMultifikator());
                imBranchesOld.setMaxJamIjinKeluar(branchNew.getMaxJamIjinKeluar());
                imBranchesOld.setFaktorJasprod(branchNew.getFaktorJasprod());
                imBranchesOld.setFaktorJubileum(branchNew.getFaktorJubileum());
                imBranchesOld.setUangMakan(branchNew.getUangMakan());
                imBranchesOld.setBiayaJasprod(branchNew.getBiayaJasprod());*/
                imBranchesOld.setLastUpdate(branchNew.getLastUpdate());
                imBranchesOld.setLastUpdateWho(branchNew.getLastUpdateWho());
                imBranchesOld.setFlag(imBranchesOld.getFlag());
                /*imBranchesOld.setPeriodeGajiAktif(branchNew.getPeriodeGajiAktif());
                imBranchesOld.setLemburGajiAwal(branchNew.getLemburGajiAwal());
                imBranchesOld.setLemburGajiAkhir(branchNew.getLemburGajiAkhir());*/

                try {
                    branchDao.updateAndSave(imBranchesOld);
                } catch (HibernateException e) {
                    logger.error("[BranchBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving updated data Branch, please inform to your admin...," + e.getMessage());
                }

            } else {
                logger.error("[BranchBoImpl.saveEdit] Unable to save edit cause no found Branch key.");
                throw new GeneralBOException("Found problem when saving edit data role cause no found Branch key., please info to your admin...");
            }
        }

        logger.info("[BranchBoImpl.saveEdit] end process <<<");
    }

    public void saveDelete(Branch branch) throws GeneralBOException {

        logger.info("[BranchBoImpl.saveDelete] start process >>>");

        if (branch != null) {

            String branchId = branch.getBranchId();

            ImBranches imBranchesOld = null;
            ImBranchesPK primaryKey = new ImBranchesPK();
            primaryKey.setId(branchId);

            try {
                imBranchesOld = branchDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[BranchBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving delete data Branch, please info to your admin..." + e.getMessage());
            }

            if (imBranchesOld != null) {

                Set listOfImBranchesBranchesUsers = imBranchesOld.getImAreasBranchesUsers();

                if (listOfImBranchesBranchesUsers.size() == 0) {

                    ImBranches imBranchToDeactive = new ImBranches();

                    try {
                        BeanUtils.copyProperties(imBranchToDeactive, imBranchesOld);
                    } catch (IllegalAccessException e) {
                        logger.error("[BranchBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object Branch Will be Delete to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                    } catch (InvocationTargetException e) {
                        logger.error("[BranchBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object Branch  Will be Delete to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                    }

                    //update data with flag=N
                    imBranchToDeactive.setFlag("N");
                    imBranchToDeactive.setAction(branch.getAction());
                    imBranchToDeactive.setLastUpdate(branch.getLastUpdate());
                    imBranchToDeactive.setLastUpdateWho(branch.getLastUpdateWho());

                    ImBranches imBranchesDeactive = (ImBranches) branchDao.getSessionFactory().getCurrentSession().merge(imBranchToDeactive);

                    try {
                        branchDao.updateAndSave(imBranchesDeactive);
                    } catch (HibernateException e) {
                        logger.error("[BranchBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving delete data Branch, please info to your admin..." + e.getMessage());
                    }

                } else {
                    logger.error("[BranchBoImpl.saveDelete] Unable to delete cause have reference data exist in Branch-branch-user table.");
                    throw new GeneralBOException("Found problem when saving delete data role cause have reference data exist in Branch-branch-user table, please info to your admin...");
                }
            } else {
                logger.error("[BranchBoImpl.saveDelete] Unable to delete cause no found Branch key.");
                throw new GeneralBOException("Found problem when saving delete data Branch cause no found Branch key., please info to your admin...");
            }
        }

        logger.info("[BranchBoImpl.saveDelete] end process <<<");
    }

    public Branch getBranchById(String branchId, String flag) throws GeneralBOException {

        logger.info("[BranchBoImpl.getBranchById] start process >>>");

        String getFlag = "";
        if (flag != null && !"".equalsIgnoreCase(flag)) {
            if (flag.equalsIgnoreCase("")) getFlag = "Y";
            else getFlag = flag;
        } else {
            getFlag = "Y";
        }

        ImBranches imBranches = null;
        ImBranchesPK primaryKey = new ImBranchesPK();
        primaryKey.setId(branchId);

        try {
            imBranches = branchDao.getById(primaryKey,getFlag);
        } catch (HibernateException e) {
            logger.error("[BranchBoImpl.getBranchById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving Branch based on id and flag, please info to your admin..." + e.getMessage());
        }

        Branch resultBranch = new Branch();
        if (imBranches != null) {

            resultBranch.setBranchId(imBranches.getPrimaryKey().getId());
            resultBranch.setBranchName(imBranches.getBranchName());
            resultBranch.setAlamatSurat(imBranches.getAlamatSurat());
            resultBranch.setBranchAddress(imBranches.getBranchAddress());
            resultBranch.setMinBpjsKs(imBranches.getMinBpjsKs());
            resultBranch.setMaxBpjsKs(imBranches.getMaxBpjsKs());
            resultBranch.setMinBpjsTk(imBranches.getMinBpjsTk());
            resultBranch.setMaxBpjsTk(imBranches.getMaxBpjsTk());

            resultBranch.setPercentKsKary(imBranches.getPercentKsKary());
            resultBranch.setPercentKsPers(imBranches.getPercentKsPers());
            resultBranch.setPercentTkKary(imBranches.getPercentTkKary());
            resultBranch.setPercentTkPers(imBranches.getPercentTkPers());
            resultBranch.setUmr(imBranches.getUmr());

            /*resultBranch.setStatusPabrik(imBranches.getStatusPabrik());
            resultBranch.setMultifikator(imBranches.getMultifikator());
            resultBranch.setMaxJamIjinKeluar(imBranches.getMaxJamIjinKeluar());
            resultBranch.setFaktorJasprod(imBranches.getFaktorJasprod());
            resultBranch.setFaktorJubileum(imBranches.getFaktorJubileum());
            resultBranch.setBiayaJasprod(imBranches.getBiayaJasprod());
            resultBranch.setPeriodeGajiAktif(imBranches.getPeriodeGajiAktif());
            resultBranch.setUangMakan(imBranches.getUangMakan());
            resultBranch.setLemburGajiAwal(imBranches.getLemburGajiAwal());
            resultBranch.setLemburGajiAkhir(imBranches.getLemburGajiAkhir());*/

            resultBranch.setCreatedWho(imBranches.getCreatedWho());
            resultBranch.setCreatedDate(imBranches.getCreatedDate());
            resultBranch.setLastUpdateWho(imBranches.getLastUpdateWho());
            resultBranch.setLastUpdate(imBranches.getLastUpdate());
            resultBranch.setAction(imBranches.getAction());
            resultBranch.setFlag(imBranches.getFlag());
            resultBranch.setAlamatSurat(imBranches.getAlamatSurat());
            resultBranch.setLogoName(imBranches.getLogoName());

            resultBranch.setPpkPelayanan(imBranches.getPpkPelayanan());
            resultBranch.setSuratSkdp(imBranches.getSuratSkdp());
            resultBranch.setKodeTarif(imBranches.getKodeTarif());
            resultBranch.setTarifPayorId(imBranches.getTarifPayorId());
            resultBranch.setPayorCd(imBranches.getTarifPayorId());
            resultBranch.setCoderNik(imBranches.getCoderNik());
        }

        logger.info("[BranchBoImpl.getBranchById] end process <<<");

        return resultBranch;
    }

    public List<Branch> getComboBranchWithCriteria(String query) throws GeneralBOException {
        logger.info("[BranchBoImpl.getComboBranchWithCriteria] start process >>>");

        List<Branch> listComboBranch = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBranches> listBranch = null;
        try {
            listBranch = branchDao.getListBranch(criteria);
        } catch (HibernateException e) {
            logger.error("[BranchBoImpl.getComboBranchWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list Branch with criteria, please info to your admin..." + e.getMessage());
        }

        if (listBranch != null) {
            for (ImBranches imBranches : listBranch) {
                Branch itemComboBranch = new Branch();
                itemComboBranch.setBranchId(imBranches.getPrimaryKey().getId());
                itemComboBranch.setBranchName(imBranches.getBranchName());
                listComboBranch.add(itemComboBranch);
            }
        }
        logger.info("[BranchBoImpl.getComboBranchWithCriteria] end process <<<");
        return listComboBranch;
    }

    public List<Branch> findAllBranch() throws GeneralBOException {
        logger.info("[BranchBoImpl.findAllBranch] start process >>>");

        List<ImBranches> listBranch = null;
        try {
            listBranch = branchDao.getAll();
        } catch (HibernateException e) {
            logger.error("[BranchBoImpl.findAllBranch] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list Branch with criteria, please info to your admin..." + e.getMessage());
        }

        List<Branch> branchers = new ArrayList<>();

        if(listBranch != null) {
            for (ImBranches branches : listBranch){
                Branch branch = new Branch();
                branch.setBranchName(branches.getBranchName());
                branch.setBranchId(branches.getPrimaryKey().getId());
                branch.setEnabled(branches.getEnabled());
                branch.setBranchAddress(branches.getBranchAddress());

                branchers.add(branch);
            }
        }


        logger.info("[BranchBoImpl.findAllBranch] end process <<<");
        return branchers;
    }

}
