package com.neurix.authorization.company.bo.impl;

import com.neurix.authorization.company.bo.CompanyBo;
import com.neurix.authorization.company.dao.CompanyDao;
import com.neurix.authorization.company.model.Company;
import com.neurix.authorization.company.model.ImCompany;
import com.neurix.authorization.company.model.ImCompanyHistory;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 18/01/13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class CompanyBoImpl implements CompanyBo {

    protected static transient Logger logger = Logger.getLogger(CompanyBoImpl.class);
    private CompanyDao companyDao;

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(companyDao, message, moduleMethod);

        return result;
    }


    public void saveEdit(Company companyNew) throws GeneralBOException {

        logger.info("[CompanyBoImpl.saveEdit] start process >>>");

        if (companyNew != null) {

            //retrieve last data by id
            String companyId = companyNew.getCompanyId();

            ImCompany imCompanyOld = null;
            try {
                imCompanyOld = companyDao.getById("companyId",companyId, "Y");
            } catch (HibernateException e) {
                logger.error("[CompanyBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Company by id, please inform to your admin...," + e.getMessage());
            }

            if (imCompanyOld != null) {

                // move last data to table history
                ImCompanyHistory imCompanyDeactive = new ImCompanyHistory();
                try {
                    BeanUtils.copyProperties(imCompanyDeactive, imCompanyOld);
                } catch (IllegalAccessException e) {
                    logger.error("[CompanyBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object CompanyOld to ImCompanyBeforeDeactive, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[CompanyBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object CompanyOld to ImCompanyBeforeDeactive, please info to your admin..." + e.getMessage());
                }

                try {
                    companyDao.addAndSaveHistory(imCompanyDeactive);
                } catch (HibernateException e) {
                    logger.error("[CompanyBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving deactive data company, please info to your admin..." + e.getMessage());
                }

                //update some of last data become new data
                imCompanyOld.setCompanyId(imCompanyOld.getCompanyId());
                imCompanyOld.setCreatedDate(imCompanyOld.getCreatedDate());
                imCompanyOld.setCreatedWho(imCompanyOld.getCreatedWho());
                imCompanyOld.setFlag(imCompanyOld.getFlag());
                imCompanyOld.setCompanyName(companyNew.getCompanyName());
                imCompanyOld.setAddress(companyNew.getAddress());
                imCompanyOld.setNpwp(companyNew.getNpwp());
                imCompanyOld.setServiceOnOff(companyNew.getServiceOnOff());
                imCompanyOld.setMailServer(companyNew.getMailServer());
                imCompanyOld.setPortServer(companyNew.getPortServer());
                imCompanyOld.setUserNameServer(companyNew.getUserNameServer());
                imCompanyOld.setPasswordServer(companyNew.getPasswordServer());
                imCompanyOld.setDefaultEmailSender(companyNew.getDefaultEmailSender());
                imCompanyOld.setDefaultEmailSubject(companyNew.getDefaultEmailSubject());
                imCompanyOld.setDefaultEmailContent(companyNew.getDefaultEmailContent());
                imCompanyOld.setBiayaJabatanPersentase(new BigDecimal(companyNew.getBiayaJabatanPersentase()));
                imCompanyOld.setIuranPerusahaanJkmJkk(companyNew.getIuranPerusahaanJkmJkk());
                imCompanyOld.setBulanJubilium(companyNew.getRemainderJubileum());
                imCompanyOld.setBulanPensiun(companyNew.getRemainderPensiun());
                imCompanyOld.setKursDolar(companyNew.getKursDolar());

                imCompanyOld.setPayrollThrPersentase(companyNew.getPayrollThrPersentase());
                imCompanyOld.setPayrollPendidikanPersentase(companyNew.getPayrollPendidikanPersentase());
                imCompanyOld.setPayrollJasprodKali(companyNew.getPayrollJasprodKali());

                imCompanyOld.setMaxBpjsTk(companyNew.getMaxBpjsTk());
                imCompanyOld.setMaxBpjsPensiun(companyNew.getMaxBpjsPensiun());
                imCompanyOld.setMaxBpjsKesehatan(companyNew.getMaxBpjsKesehatan());

                imCompanyOld.setLastUpdateWho(companyNew.getLastUpdateWho());
                imCompanyOld.setLastUpdate(companyNew.getLastUpdate());

                try {
                    companyDao.updateAndSave(imCompanyOld);
                } catch (HibernateException e) {
                    logger.error("[CompanyBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving updated data Company, please inform to your admin...," + e.getMessage());
                }

            } else {
                logger.error("[CompanyBoImpl.saveEdit] Unable to save edit cause no found Company key.");
                throw new GeneralBOException("Found problem when saving edit data role cause no found Company key., please info to your admin...");
            }
        }

        logger.info("[CompanyBoImpl.saveEdit] end process <<<");
    }


    public Company getById(String companyId) throws GeneralBOException {

        logger.info("[CompanyBoImpl.getBranchById] start process >>>");

        ImCompany imCompany = null;
        try {
            imCompany = companyDao.getById("companyId",companyId,"Y");
        } catch (HibernateException e) {
            logger.error("[CompanyBoImpl.getBranchById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving Company based on id and flag, please info to your admin..." + e.getMessage());
        }

        Company resultCompany = new Company();
        if (imCompany != null) {

            try {
                BeanUtils.copyProperties(resultCompany, imCompany);
            } catch (IllegalAccessException e) {
                logger.error("[CompanyBoImpl.getBranchById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search imCompany to result Company to display , please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[CompanyBoImpl.getBranchById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search imCompany to result Company to display , please info to your admin..." + e.getMessage());
            }

            resultCompany.setFlag(imCompany.getFlag());
            resultCompany.setStMinimumLuasan(imCompany.getMinimumLuasan().toPlainString());
            resultCompany.setRemainderJubileum(imCompany.getBulanJubilium());
            resultCompany.setRemainderPensiun(imCompany.getBulanPensiun());
        }

        logger.info("[CompanyBoImpl.getBranchById] end process <<<");

        return resultCompany;
    }

    @Override
    public void saveDelete(Company bean) throws GeneralBOException {

    }

    @Override
    public Company saveAdd(Company bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Company> getByCriteria(Company searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Company> getAll() throws GeneralBOException {
        return null;
    }


}
