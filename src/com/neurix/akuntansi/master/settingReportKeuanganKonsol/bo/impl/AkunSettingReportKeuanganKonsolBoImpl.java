package com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.impl;

import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao.SettingReportKeuanganKonsolDao;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao.SettingReportKeuanganKonsolDetailDao;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.ImAkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.ImAkunSettingReportKeuanganKonsolDetail;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
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
    private SettingReportKeuanganKonsolDetailDao settingReportKeuanganKonsolDetailDao;
    private MappingJurnalDao mappingJurnalDao;

    public SettingReportKeuanganKonsolDetailDao getSettingReportKeuanganKonsolDetailDao() {
        return settingReportKeuanganKonsolDetailDao;
    }

    public void setSettingReportKeuanganKonsolDetailDao(SettingReportKeuanganKonsolDetailDao settingReportKeuanganKonsolDetailDao) {
        this.settingReportKeuanganKonsolDetailDao = settingReportKeuanganKonsolDetailDao;
    }

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
            String idReportKeuanganKonsol = bean.getSettingReportKonsolId();
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<AkunSettingReportKeuanganKonsolDetail> listOfResult = (List<AkunSettingReportKeuanganKonsolDetail>) session.getAttribute("listOfResultKonsolDetail");

            if (listOfResult != null){
                ImAkunSettingReportKeuanganKonsol konsol = null;
                try{
                    konsol = settingReportKeuanganKonsolDao.getById("settingReportKonsolId", idReportKeuanganKonsol);
                }catch (HibernateException e){
                    logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Report Keuangan Konsol by ID reportKonsolId, please inform to your admin...," + e.getMessage());
                }

                if (konsol != null){
                    konsol.setSettingReportKonsolId(idReportKeuanganKonsol);
                    konsol.setFlag(bean.getFlag());
                    konsol.setAction(bean.getAction());
                    konsol.setLastUpdate(bean.getLastUpdate());
                    konsol.setLastUpdateWho(bean.getLastUpdateWho());

                    try{
                        settingReportKeuanganKonsolDao.addAndSave(konsol);
                    }catch (HibernateException e){
                        logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                    }

                    for (int i=0; i<listOfResult.size(); i++){
                        if (listOfResult.get(i).getSettingReportKonsolDetailId() != null){
                            ImAkunSettingReportKeuanganKonsolDetail entity = null;
                            String reportKonsolIdDetail = listOfResult.get(i).getSettingReportKonsolDetailId();

                            try{
                                entity = settingReportKeuanganKonsolDetailDao.getById("settingReportKonsolDetailId", reportKonsolIdDetail);
                            }catch (HibernateException e){
                                logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data Report Keuangan Konsol by ID reportKonsolId, please inform to your admin...," + e.getMessage());
                            }

                            if (entity != null){
                                entity.setSettingReportKonsolDetailId(reportKonsolIdDetail);
                                entity.setFlag(bean.getFlag());
                                entity.setAction(bean.getAction());
                                entity.setLastUpdateWho(bean.getLastUpdateWho());
                                entity.setLastUpdate(bean.getLastUpdate());

                                try{
                                    settingReportKeuanganKonsolDetailDao.updateAndSave(entity);
                                }catch (HibernateException e){
                                    logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                                }
                            }
                        }else {
                            ImAkunSettingReportKeuanganKonsolDetail entity = new ImAkunSettingReportKeuanganKonsolDetail();
                            String reportKonsolIdDetail = settingReportKeuanganKonsolDao.getNextKeuanganKonsolDetailId();

                            entity.setSettingReportKonsolDetailId(reportKonsolIdDetail);
                            entity.setFlag(bean.getFlag());
                            entity.setAction(bean.getAction());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            entity.setLastUpdate(bean.getLastUpdate());

                            try{
                                settingReportKeuanganKonsolDetailDao.addAndSave(entity);
                            }catch (HibernateException e){
                                logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }else {
                    logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveDelete] Error, not found data Report Keuangan Konsol with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Report Keuangan Konsol with request id, please check again your data ...");
                }
            }else {
                logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveDelete] Error, not found data Report Keuangan Konsol with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Report Keuangan Konsol with request id, please check again your data ...");
            }
        }
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveDelete] end process <<<");
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getByDataCriteria(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getByCriteria] start process >>>");

        List<AkunSettingReportKeuanganKonsol> result = new ArrayList<>();

        if(bean != null){
            String kodeRekeningAlias = bean.getKodeRekeningAlias();
            String rekeningAlias = null;
            String[] arrOfStr = kodeRekeningAlias.split("\\.");
            for (int i=0; i < arrOfStr.length; i++){
                if (i < arrOfStr.length - 1){
                    if (arrOfStr.length - 1 == i){
                        rekeningAlias = rekeningAlias+"."+arrOfStr[i];
                    }else {
                        if (i == 0)
                            rekeningAlias = arrOfStr[i];
                        else
                            rekeningAlias = rekeningAlias+"."+arrOfStr[i];
                    }
                }
            }

            Map hsCriteria = new HashMap();
            if (bean.getSettingReportKonsolId() != null && !"".equalsIgnoreCase(bean.getSettingReportKonsolId())){
                hsCriteria.put("setting_report_konsol_id", bean.getSettingReportKonsolId());
            }
            if (rekeningAlias != null && !"".equalsIgnoreCase(rekeningAlias)){
                hsCriteria.put("kode_rekening_alias", rekeningAlias);
            }
            if (bean.getNamaKodeRekeningAlias() != null && !"".equalsIgnoreCase(bean.getNamaKodeRekeningAlias())){
                hsCriteria.put("nama_kode_rekening_alias", bean.getNamaKodeRekeningAlias());
            }

            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImAkunSettingReportKeuanganKonsol> entityList = new ArrayList<>();

            try {
                entityList = settingReportKeuanganKonsolDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                AkunSettingReportKeuanganKonsol konsol;
                for (ImAkunSettingReportKeuanganKonsol entity : entityList){
                    konsol = new AkunSettingReportKeuanganKonsol();
                    konsol.setSettingReportKonsolId(entity.getSettingReportKonsolId());
                    konsol.setKodeRekeningAlias(entity.getKodeRekeningAlias());
                    konsol.setNamaKodeRekeningAlias(entity.getNamaKodeRekeningAlias());
                    konsol.setFlagLabel(entity.getFlagLabel());
                    konsol.setAction(entity.getAction());
                    konsol.setFlag(entity.getFlag());
                    konsol.setCreatedDate(entity.getCreatedDate());
                    konsol.setStCreatedDate(entity.getCreatedDate().toString());
                    konsol.setCreatedWho(entity.getCreatedWho());
                    konsol.setStLastUpdate(entity.getLastUpdate().toString());
                    konsol.setLastUpdate(entity.getLastUpdate());
                    konsol.setLastUpdateWho(entity.getLastUpdateWho());

                    result.add(konsol);
                }
            }
        }
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getByCriteria] end process <<<");

        return result;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getDataById(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
//        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getDataById] start process >>>");
//
//        List<AkunSettingReportKeuanganKonsol> result = new ArrayList<>();
//
//        if(bean != null){
//
//            Map hsCriteria = new HashMap();
//
//            if (bean.getSettingReportKonsolId() != null && !"".equalsIgnoreCase(bean.getSettingReportKonsolId())){
//                hsCriteria.put("setting_report_konsol_id", bean.getSettingReportKonsolId());
//            }
//            if (bean.getKodeRekeningAlias() != null && !"".equalsIgnoreCase(bean.getKodeRekeningAlias())){
//                hsCriteria.put("kode_rekening_alias", bean.getKodeRekeningAlias());
//            }
//            if (bean.getNamaKodeRekeningAlias() != null && !"".equalsIgnoreCase(bean.getNamaKodeRekeningAlias())){
//                hsCriteria.put("nama_kode_rekening_alias", bean.getNamaKodeRekeningAlias());
//            }
//
//            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
//                if ("N".equalsIgnoreCase(bean.getFlag())) {
//                    hsCriteria.put("flag", "N");
//                } else {
//                    hsCriteria.put("flag", bean.getFlag());
//                }
//            } else {
//                hsCriteria.put("flag", "Y");
//            }
//
//            List<ImAkunSettingReportKeuanganKonsolDetail> entityList = new ArrayList<>();
//
//            try {
//                entityList = settingReportKeuanganKonsolDetailDao.getByCriteria(hsCriteria);
//            } catch (HibernateException e){
//                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
//            }
//
//            if (!entityList.isEmpty()){
//                AkunSettingReportKeuanganKonsol konsol;
//                for (ImAkunSettingReportKeuanganKonsol entity : entityList){
//                    konsol = new AkunSettingReportKeuanganKonsol();
//                    konsol.setSettingReportKonsolId(entity.getSettingReportKonsolId());
//                    konsol.setKodeRekeningAlias(entity.getKodeRekeningAlias());
//                    konsol.setNamaKodeRekeningAlias(entity.getNamaKodeRekeningAlias());
//                    konsol.setFlagLabel(entity.getFlagLabel());
//                    konsol.setAction(entity.getAction());
//                    konsol.setFlag(entity.getFlag());
//                    konsol.setCreatedDate(entity.getCreatedDate());
//                    konsol.setStCreatedDate(entity.getCreatedDate().toString());
//                    konsol.setCreatedWho(entity.getCreatedWho());
//                    konsol.setStLastUpdate(entity.getLastUpdate().toString());
//                    konsol.setLastUpdate(entity.getLastUpdate());
//                    konsol.setLastUpdateWho(entity.getLastUpdateWho());
//
//                    result.add(konsol);
//                }
//            }
//        }
//        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getByCriteria] end process <<<");

        return null;
    }

    @Override
    public void saveEdit(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String reportKonsolId = bean.getSettingReportKonsolId();
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<AkunSettingReportKeuanganKonsolDetail> listOfResult = (List<AkunSettingReportKeuanganKonsolDetail>) session.getAttribute("listOfResultKonsolDetailEdit");

            ImAkunSettingReportKeuanganKonsol konsol = null;
            try{
                konsol = settingReportKeuanganKonsolDao.getById("settingReportKonsolId", reportKonsolId);
            }catch (HibernateException e){
                logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Report Keuangan Konsol by ID reportKonsolId, please inform to your admin...," + e.getMessage());
            }

            if (konsol != null){
                konsol.setSettingReportKonsolId(reportKonsolId);
                konsol.setKodeRekeningAlias(bean.getKodeRekeningAlias());
                konsol.setNamaKodeRekeningAlias(bean.getNamaKodeRekeningAlias());
                konsol.setNamaKodeRekeningAlias(bean.getNamaKodeRekeningAlias());
                konsol.setFlagLabel(bean.getFlagLabel());
                konsol.setFlag(bean.getFlag());
                konsol.setAction(bean.getAction());
                konsol.setLastUpdate(bean.getLastUpdate());
                konsol.setLastUpdateWho(bean.getLastUpdateWho());

                try{
                    settingReportKeuanganKonsolDao.updateAndSave(konsol);
                }catch (HibernateException e){
                    logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                }

                if (listOfResult != null){
                    for (int i=0; i<listOfResult.size(); i++){
                        if (listOfResult.get(i).getSettingReportKonsolDetailId() != null){
                            ImAkunSettingReportKeuanganKonsolDetail entity = null;
                            String reportKonsolIdDetail = listOfResult.get(i).getSettingReportKonsolDetailId();

                            try{
                                entity = settingReportKeuanganKonsolDetailDao.getById("settingReportKonsolDetailId", reportKonsolIdDetail);
                            }catch (HibernateException e){
                                logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data Report Keuangan Konsol by ID reportKonsolId, please inform to your admin...," + e.getMessage());
                            }

                            if (entity != null){
                                entity.setSettingReportKonsolDetailId(reportKonsolIdDetail);
                                entity.setSettingReportKonsolId(reportKonsolId);
                                entity.setRekeningId(listOfResult.get(i).getRekeningId());
                                entity.setOperator(listOfResult.get(i).getOperator());
                                entity.setFlag(listOfResult.get(i).getFlag());
                                entity.setAction(bean.getAction());
                                entity.setLastUpdateWho(bean.getLastUpdateWho());
                                entity.setLastUpdate(bean.getLastUpdate());

                                try{
                                    settingReportKeuanganKonsolDetailDao.updateAndSave(entity);
                                }catch (HibernateException e){
                                    logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                                }
                            }
                        }else {
                            ImAkunSettingReportKeuanganKonsolDetail entity = new ImAkunSettingReportKeuanganKonsolDetail();
                            String reportKonsolIdDetail = settingReportKeuanganKonsolDao.getNextKeuanganKonsolDetailId();

                            entity.setSettingReportKonsolDetailId(reportKonsolIdDetail);
                            entity.setSettingReportKonsolId(reportKonsolId);
                            entity.setRekeningId(listOfResult.get(i).getRekeningId());
                            entity.setOperator(listOfResult.get(i).getOperator());
                            if (listOfResult.get(i).getFlag() != null)
                                entity.setFlag(listOfResult.get(i).getFlag());
                            else
                                entity.setFlag(bean.getFlag());
                            entity.setAction(bean.getAction());
                            entity.setCreatedWho(bean.getCreatedWho());
                            entity.setCreatedDate(bean.getCreatedDate());
                            entity.setLastUpdateWho(bean.getLastUpdateWho());
                            entity.setLastUpdate(bean.getLastUpdate());

                            try{
                                settingReportKeuanganKonsolDetailDao.addAndSave(entity);
                            }catch (HibernateException e){
                                logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }
            }else {
                logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] Error, not found data settingReportKonsolId with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data AkunSettingReportKeuanganKonsol with request id, please check again your data ...");
            }
        }
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveEdit] end process <<<");
    }

    @Override
    public AkunSettingReportKeuanganKonsol saveAdd(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getNamaKodeRekeningAlias());
            if (!status.equalsIgnoreCase("exist")){

                String reportKonsolId, reportKonsolIdDetail;
                HttpSession session = ServletActionContext.getRequest().getSession();
                List<AkunSettingReportKeuanganKonsolDetail> listOfResult = (List<AkunSettingReportKeuanganKonsolDetail>) session.getAttribute("listOfResultKonsolDetail");
                if (listOfResult != null){
                    try{
                        reportKonsolId = settingReportKeuanganKonsolDao.getNextKeuanganKonsolId();
                    }catch (HibernateException e){
                        logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when getting sequence setting report keuangan id, please info to your admin..." + e.getMessage());
                    }

                    ImAkunSettingReportKeuanganKonsol konsol = new ImAkunSettingReportKeuanganKonsol();
                    konsol.setSettingReportKonsolId(reportKonsolId);
                    konsol.setKodeRekeningAlias(bean.getKodeRekeningAlias());
                    konsol.setNamaKodeRekeningAlias(bean.getNamaKodeRekeningAlias());
                    konsol.setFlagLabel(bean.getFlagLabel());
                    konsol.setFlag(bean.getFlag());
                    konsol.setAction(bean.getAction());
                    konsol.setCreatedDate(bean.getCreatedDate());
                    konsol.setCreatedWho(bean.getCreatedWho());
                    konsol.setLastUpdate(bean.getLastUpdate());
                    konsol.setLastUpdateWho(bean.getLastUpdateWho());

                    try{
                        settingReportKeuanganKonsolDao.addAndSave(konsol);
                    }catch (HibernateException e){
                        logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                    }

                    for (AkunSettingReportKeuanganKonsolDetail keuanganKonsol : listOfResult){
                        ImAkunSettingReportKeuanganKonsolDetail entity = new ImAkunSettingReportKeuanganKonsolDetail();
                        reportKonsolIdDetail = settingReportKeuanganKonsolDao.getNextKeuanganKonsolDetailId();

                        entity.setSettingReportKonsolDetailId(reportKonsolIdDetail);
                        entity.setSettingReportKonsolId(reportKonsolId);
                        entity.setRekeningId(keuanganKonsol.getRekeningId());
                        entity.setOperator(keuanganKonsol.getOperator());
                        entity.setFlag(bean.getFlag());
                        entity.setAction(bean.getAction());
                        entity.setCreatedWho(bean.getCreatedWho());
                        entity.setCreatedDate(bean.getCreatedDate());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setLastUpdate(bean.getLastUpdate());

                        try{
                            settingReportKeuanganKonsolDetailDao.addAndSave(entity);
                        }catch (HibernateException e){
                            logger.error("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Setting Report Keuangan Konsol, please info to your admin..." + e.getMessage());
                        }
                    }

                }else {
                    throw new GeneralBOException("Maaf Data keuangan konsol detail tidak ada");
                }
            }else {
                throw new GeneralBOException("Maaf Data dengan Nama Kode Rekening Alias Tersebut Sudah Ada");
            }
        }

        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getByCriteria(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getByCriteria] start process >>>");

        List<AkunSettingReportKeuanganKonsol> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getSettingReportKonsolId() != null && !"".equalsIgnoreCase(bean.getSettingReportKonsolId())){
                hsCriteria.put("setting_report_konsol_id", bean.getSettingReportKonsolId());
            }
            if (bean.getKodeRekeningAlias() != null && !"".equalsIgnoreCase(bean.getKodeRekeningAlias())){
                hsCriteria.put("kode_rekening_alias", bean.getKodeRekeningAlias());
            }
            if (bean.getNamaKodeRekeningAlias() != null && !"".equalsIgnoreCase(bean.getNamaKodeRekeningAlias())){
                hsCriteria.put("nama_kode_rekening_alias", bean.getNamaKodeRekeningAlias());
            }

            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImAkunSettingReportKeuanganKonsol> entityList = new ArrayList<>();

            try {
                entityList = settingReportKeuanganKonsolDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                AkunSettingReportKeuanganKonsol konsol;
                for (ImAkunSettingReportKeuanganKonsol entity : entityList){
                    konsol = new AkunSettingReportKeuanganKonsol();
                    konsol.setSettingReportKonsolId(entity.getSettingReportKonsolId());
                    konsol.setKodeRekeningAlias(entity.getKodeRekeningAlias());
                    konsol.setNamaKodeRekeningAlias(entity.getNamaKodeRekeningAlias());
                    konsol.setFlagLabel(entity.getFlagLabel());
                    konsol.setAction(entity.getAction());
                    konsol.setFlag(entity.getFlag());
                    konsol.setCreatedDate(entity.getCreatedDate());
                    konsol.setStCreatedDate(entity.getCreatedDate().toString());
                    konsol.setCreatedWho(entity.getCreatedWho());
                    konsol.setStLastUpdate(entity.getLastUpdate().toString());
                    konsol.setLastUpdate(entity.getLastUpdate());
                    konsol.setLastUpdateWho(entity.getLastUpdateWho());

                    result.add(konsol);
                }
            }
        }
        logger.info("[AkunSettingReportKeuanganKonsolBoImpl.getByCriteria] end process <<<");

        return result;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaPelayanan)throws GeneralBOException{
        String status ="";
        List<ImAkunSettingReportKeuanganKonsol> entities = new ArrayList<>();
        try {
            entities = settingReportKeuanganKonsolDao.getDataPelayanan(namaPelayanan);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}
