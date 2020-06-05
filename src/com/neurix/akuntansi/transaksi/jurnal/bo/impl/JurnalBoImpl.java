package com.neurix.akuntansi.transaksi.jurnal.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.jurnal.bo.JurnalBo;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.jurnal.model.JurnalDetail;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
public class JurnalBoImpl implements JurnalBo {

    protected static transient Logger logger = Logger.getLogger(JurnalBoImpl.class);
    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private KodeRekeningDao kodeRekeningDao;
    private PositionDao positionDao;

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public JurnalDetailDao getJurnalDetailDao() {
        return jurnalDetailDao;
    }

    public void setJurnalDetailDao(JurnalDetailDao jurnalDetailDao) {
        this.jurnalDetailDao = jurnalDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JurnalBoImpl.logger = logger;
    }

    public JurnalDao getJurnalDao() {
        return jurnalDao;
    }

    public void setJurnalDao(JurnalDao jurnalDao) {
        this.jurnalDao = jurnalDao;
    }



    @Override
    public List<Jurnal> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveDelete(Jurnal bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Jurnal bean) throws GeneralBOException {

    }

    @Override
    public Jurnal saveAdd(Jurnal bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Jurnal> getByCriteria(Jurnal searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public JurnalDetail getBudgetTerpakai(String branchId, String divisiId, String tahun, String bulan, String coa,String budget){
        JurnalDetail result = new JurnalDetail();
        ImPosition position = positionDao.getById("positionId",divisiId);
        BigDecimal nilaiBudget = BigDecimal.valueOf(Double.valueOf(budget.replace(".","").replace(",","")));

        List<ImKodeRekeningEntity> kodeRekeningEntity = kodeRekeningDao.getIdByCoa(coa);
        String rekeningId = "";
        for (ImKodeRekeningEntity rekeningEntity : kodeRekeningEntity){
            rekeningId = rekeningEntity.getRekeningId();
        }
        String koderingPosisi = position.getKodering();
        BigDecimal budgetTerpakai = jurnalDao.getBudgetTerpakai(branchId,koderingPosisi,bulan,tahun,rekeningId);
        BigDecimal sisaBudget = nilaiBudget.subtract(budgetTerpakai);

        result.setStBudgetTerpakai(CommonUtil.numbericFormat(budgetTerpakai,"###,###"));
        result.setStSisaBudget(CommonUtil.numbericFormat(sisaBudget,"###,###"));

        return result;
    }
}
