package com.neurix.akuntansi.master.reportDetail.bo.impl;

import com.neurix.akuntansi.master.reportDetail.bo.ReportDetailBo;
import com.neurix.akuntansi.master.reportDetail.dao.ReportDetailDao;
import com.neurix.akuntansi.master.reportDetail.model.ImReportDetailEntity;
import com.neurix.akuntansi.master.reportDetail.model.ReportDetail;
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
public class ReportDetailBoImpl implements ReportDetailBo {

    protected static transient Logger logger = Logger.getLogger(ReportDetailBoImpl.class);
    private ReportDetailDao reportDetailDao;

    @Override
    public void saveDelete(ReportDetail bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(ReportDetail bean) throws GeneralBOException {
        logger.info("[ReportDetailBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            ImReportDetailEntity reportDetailEntity= null;
            try {
                // Get data from database by ID
                reportDetailEntity = reportDetailDao.getById("rekeningId", bean.getRekeningId());
            } catch (HibernateException e) {
                logger.error("[ReportDetailBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }

            if (reportDetailEntity != null) {
                if (("Y").equalsIgnoreCase(bean.getFlag())) {
                    reportDetailEntity.setReportDetailId(bean.getReportDetailId());
                    reportDetailEntity.setReportId(bean.getReportId());
                    reportDetailEntity.setRekeningId(bean.getRekeningId());

                    reportDetailEntity.setFlag(bean.getFlag());
                    reportDetailEntity.setAction(bean.getAction());
                    reportDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    reportDetailEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        reportDetailDao.updateAndSave(reportDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[ReportDetailBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                    }
                }
            } else {
                logger.error("[ReportDetailBoImpl.saveEdit] Error, not found data with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data with request id, please check again your data ...");
            }
        }
        logger.info("[ReportDetailBoImpl.saveEdit] end process <<<");
    }

    @Override
    public List<ReportDetail> getByCriteria(ReportDetail searchBean) throws GeneralBOException {
        logger.info("[ReportDetailBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<ReportDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getReportDetailId() != null && !"".equalsIgnoreCase(searchBean.getReportDetailId())) {
                hsCriteria.put("report_detail_id", searchBean.getReportDetailId());
            }
            if (searchBean.getReportId() != null && !"".equalsIgnoreCase(searchBean.getReportId())) {
                hsCriteria.put("report_id", searchBean.getReportId());
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


            List<ImReportDetailEntity> imReportDetailEntityList = null;
            try {

                imReportDetailEntityList = reportDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ReportDetailBoImpl.getSearchIjinByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imReportDetailEntityList != null){
                ReportDetail returnReportDetail;
                // Looping from dao to object and save in collection
                for(ImReportDetailEntity reportDetailEntity : imReportDetailEntityList){
                    returnReportDetail = new ReportDetail();
                    returnReportDetail.setReportDetailId(reportDetailEntity.getReportDetailId());
                    returnReportDetail.setReportId(reportDetailEntity.getReportId());
                    returnReportDetail.setRekeningId(reportDetailEntity.getRekeningId());

                    returnReportDetail.setCreatedWho(reportDetailEntity.getCreatedWho());
                    returnReportDetail.setCreatedDate(reportDetailEntity.getCreatedDate());
                    returnReportDetail.setLastUpdate(reportDetailEntity.getLastUpdate());
                    returnReportDetail.setAction(reportDetailEntity.getAction());
                    returnReportDetail.setFlag(reportDetailEntity.getFlag());
                    listOfResult.add(returnReportDetail);
                }
            }
        }
        logger.info("[ReportDetailBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public ReportDetail saveAdd(ReportDetail bean) throws GeneralBOException {
        logger.info("[ReportDetailBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String reportDetailId;
            try {
                // Generating ID, get from postgre sequence
                reportDetailId = reportDetailDao.getNextReportDetailId();
            } catch (HibernateException e) {
                logger.error("[ReportDetailBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImReportDetailEntity imReportDetailEntity = new ImReportDetailEntity();

            imReportDetailEntity.setReportDetailId(reportDetailId);
            imReportDetailEntity.setReportId(bean.getReportId());
            imReportDetailEntity.setRekeningId(bean.getRekeningId());

            imReportDetailEntity.setFlag(bean.getFlag());
            imReportDetailEntity.setAction(bean.getAction());
            imReportDetailEntity.setCreatedWho(bean.getCreatedWho());
            imReportDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imReportDetailEntity.setCreatedDate(bean.getCreatedDate());
            imReportDetailEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                reportDetailDao.addAndSave(imReportDetailEntity);
            } catch (HibernateException e) {
                logger.error("[ReportDetailBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data , please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ReportDetailBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public List<ReportDetail> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ReportDetailBoImpl.logger = logger;
    }

    public ReportDetailDao getReportDetailDao() {
        return reportDetailDao;
    }

    public void setReportDetailDao(ReportDetailDao reportDetailDao) {
        this.reportDetailDao = reportDetailDao;
    }


}
