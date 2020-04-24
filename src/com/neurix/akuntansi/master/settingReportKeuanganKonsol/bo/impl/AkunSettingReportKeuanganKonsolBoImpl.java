package com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.impl;

import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao.SettingReportKeuanganKonsolDao;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.ImAkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class AkunSettingReportKeuanganKonsolBoImpl implements AkunSettingReportKeuanganKonsolBo {

    protected static transient Logger logger = Logger.getLogger(AkunSettingReportKeuanganKonsolBoImpl.class);
    private SettingReportKeuanganKonsolDao settingReportKeuanganKonsolDao;
    private MappingJurnalDao mappingJurnalDao;

    public MappingJurnalDao getMappingJurnalDao() {
        return mappingJurnalDao;
    }

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AkunSettingReportKeuanganKonsolBoImpl.logger = logger;
    }

    public SettingReportKeuanganKonsolDao getSettingReportKeuanganKonsolDao() {
        return settingReportKeuanganKonsolDao;
    }

    public void setSettingReportKeuanganKonsolDao(SettingReportKeuanganKonsolDao settingReportKeuanganKonsolDao) {
        this.settingReportKeuanganKonsolDao = settingReportKeuanganKonsolDao;
    }

    @Override
    public void saveDelete(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {

        }
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] start process >>>");

        if (bean!=null) {

        }
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] end process <<<");
    }

    @Override
    public AkunSettingReportKeuanganKonsol saveAdd(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] start process >>>");
        if (bean!=null) {

        }

        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getByCriteria(AkunSettingReportKeuanganKonsol searchBean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<AkunSettingReportKeuanganKonsol> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();


        }
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
