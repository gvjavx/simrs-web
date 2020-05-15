package com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolDetailBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao.SettingReportKeuanganKonsolDao;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao.SettingReportKeuanganKonsolDetailDao;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.ImAkunSettingReportKeuanganKonsolDetail;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AkunSettingReportKeuanganKonsolDetailBoImpl implements AkunSettingReportKeuanganKonsolDetailBo {

    protected static transient Logger logger = Logger.getLogger(AkunSettingReportKeuanganKonsolBoImpl.class);
    private SettingReportKeuanganKonsolDao settingReportKeuanganKonsolDao;
    private SettingReportKeuanganKonsolDetailDao settingReportKeuanganKonsolDetailDao;

    public SettingReportKeuanganKonsolDao getSettingReportKeuanganKonsolDao() {
        return settingReportKeuanganKonsolDao;
    }

    public void setSettingReportKeuanganKonsolDao(SettingReportKeuanganKonsolDao settingReportKeuanganKonsolDao) {
        this.settingReportKeuanganKonsolDao = settingReportKeuanganKonsolDao;
    }

    public SettingReportKeuanganKonsolDetailDao getSettingReportKeuanganKonsolDetailDao() {
        return settingReportKeuanganKonsolDetailDao;
    }

    public void setSettingReportKeuanganKonsolDetailDao(SettingReportKeuanganKonsolDetailDao settingReportKeuanganKonsolDetailDao) {
        this.settingReportKeuanganKonsolDetailDao = settingReportKeuanganKonsolDetailDao;
    }

    @Override
    public void saveDelete(AkunSettingReportKeuanganKonsolDetail bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(AkunSettingReportKeuanganKonsolDetail bean) throws GeneralBOException {

    }

    @Override
    public AkunSettingReportKeuanganKonsolDetail saveAdd(AkunSettingReportKeuanganKonsolDetail bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsolDetail> getByCriteria(AkunSettingReportKeuanganKonsolDetail bean) throws GeneralBOException {
        logger.info("[AkunSettingReportKeuanganKonsolDetailBoImpl.getByCriteria] start process >>>");

        List<AkunSettingReportKeuanganKonsolDetail> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getSettingReportKonsolId() != null && !"".equalsIgnoreCase(bean.getSettingReportKonsolId())){
                hsCriteria.put("setting_report_konsol_id", bean.getSettingReportKonsolId());
            }
            if (bean.getSettingReportKonsolDetailId() != null && !"".equalsIgnoreCase(bean.getSettingReportKonsolDetailId())){
                hsCriteria.put("setting_report_konsol_detail_id", bean.getSettingReportKonsolDetailId());
            }
            if (bean.getRekeningId() != null && !"".equalsIgnoreCase(bean.getRekeningId())){
                hsCriteria.put("rekening_id", bean.getRekeningId());
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

            List<ImAkunSettingReportKeuanganKonsolDetail> entityList = new ArrayList<>();

            try {
                entityList = settingReportKeuanganKonsolDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[AkunSettingReportKeuanganKonsolDetailBoImpl.getByCriteria] Error get AkunSettingReportKeuanganKonsolDetail data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                AkunSettingReportKeuanganKonsolDetail konsolDetail;
                for (ImAkunSettingReportKeuanganKonsolDetail entity : entityList){
                    konsolDetail = new AkunSettingReportKeuanganKonsolDetail();
                    konsolDetail.setSettingReportKonsolId(entity.getSettingReportKonsolId());
                    konsolDetail.setSettingReportKonsolDetailId(entity.getSettingReportKonsolDetailId());
                    konsolDetail.setRekeningId(entity.getRekeningId());
                    konsolDetail.setOperator(entity.getOperator());
                    konsolDetail.setAction(entity.getAction());
                    konsolDetail.setFlag(entity.getFlag());
                    konsolDetail.setCreatedDate(entity.getCreatedDate());
//                    konsolDetail.setStCreatedDate(entity.getCreatedDate().toString());
                    konsolDetail.setCreatedWho(entity.getCreatedWho());
//                    konsol.setStLastUpdate(entity.getLastUpdate().toString());
                    konsolDetail.setLastUpdate(entity.getLastUpdate());
                    konsolDetail.setLastUpdateWho(entity.getLastUpdateWho());

                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (entity.getRekeningId() != null){
                        KodeRekening kodeRekening = new KodeRekening();
                        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) context.getBean("kodeRekeningBoProxy");
                        kodeRekening.setRekeningId(entity.getRekeningId());
                        kodeRekening.setFlag("Y");
                        List<KodeRekening> kodeRekenings = kodeRekeningBo.getByCriteria(kodeRekening);
                        String kodeRekeningName = kodeRekenings.get(0).getNamaKodeRekening();
                        konsolDetail.setNamaRekening(kodeRekeningName);
                    }
                    if ("T".equalsIgnoreCase(entity.getOperator()))
                        konsolDetail.setSbOperator("+");
                    else
                        konsolDetail.setSbOperator("-");

                    result.add(konsolDetail);
                }
            }
        }
        logger.info("[AkunSettingReportKeuanganKonsolDetailBoImpl.getByCriteria] end process <<<");

        return result;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsolDetail> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}