package com.neurix.simrs.transaksi.verifikatorpembayaran.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antriantelemedic.dao.TelemedicDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.action.VerifikatorPembayaranAction;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.dao.VerifikatorPembayaranDao;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranBoImpl implements VerifikatorPembayaranBo {

    private static final transient Logger logger = Logger.getLogger(VerifikatorPembayaranBoImpl.class);
    private TelemedicDao telemedicDao;
    private VerifikatorPembayaranDao verifikatorPembayaranDao;

    public void setTelemedicDao(TelemedicDao telemedicDao) {
        this.telemedicDao = telemedicDao;
    }

    public void setVerifikatorPembayaranDao(VerifikatorPembayaranDao verifikatorPembayaranDao) {
        this.verifikatorPembayaranDao = verifikatorPembayaranDao;
    }

    @Override
    public List<PembayaranOnline> getSearchByCriteria(PembayaranOnline bean) throws GeneralBOException {

        List<PembayaranOnline> pembayaranOnlines = new ArrayList<>();
        List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = getSearchEntityByCriteria(bean);
        if (pembayaranOnlineEntities.size() > 0){

            for (ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity : pembayaranOnlineEntities){
                PembayaranOnline pembayaranOnline = new PembayaranOnline();
                pembayaranOnline.setId(pembayaranOnlineEntity.getId());
                pembayaranOnline.setIdAntrianTelemedic(pembayaranOnlineEntity.getIdAntrianTelemedic());
                pembayaranOnline.setIdItem(pembayaranOnlineEntity.getIdItem());
                pembayaranOnline.setIdRiwayatTindakan(pembayaranOnlineEntity.getIdRiwayatTindakan());
                pembayaranOnline.setNominal(pembayaranOnlineEntity.getNominal() == null ? new BigDecimal(0) : pembayaranOnlineEntity.getNominal());
                pembayaranOnline.setKeterangan(pembayaranOnlineEntity.getKeterangan());
                pembayaranOnline.setApprovedFlag(pembayaranOnlineEntity.getApprovedFlag());
                pembayaranOnline.setApprovedWho(pembayaranOnlineEntity.getApprovedWho());
                pembayaranOnline.setKodeBank(pembayaranOnlineEntity.getKodeBank());
                pembayaranOnline.setCreatedDate(pembayaranOnlineEntity.getCreatedDate());
                pembayaranOnline.setCreatedWho(pembayaranOnlineEntity.getCreatedWho());
                pembayaranOnline.setLastUpdate(pembayaranOnlineEntity.getLastUpdate());
                pembayaranOnline.setLastUpdateWho(pembayaranOnlineEntity.getLastUpdateWho());
                pembayaranOnline.setFlag(pembayaranOnlineEntity.getFlag());
                pembayaranOnline.setAction(pembayaranOnlineEntity.getAction());
                pembayaranOnline.setUrlFotoBukti(pembayaranOnlineEntity.getUrlFotoBukti());

                // mencari apakah sudah di bayar melalui bank
                ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicDao.getById("id", bean.getIdAntrianTelemedic());
                if (antrianTelemedicEntity != null){
                    if ("konsultasi".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                        if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagBayarKonsultasi())){
                            pembayaranOnline.setFlagBayar("Y");
                        }
                    } else if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                        if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagBayarResep())){
                            pembayaranOnline.setFlagBayar("Y");
                        }
                    }
                }


                pembayaranOnlines.add(pembayaranOnline);
            }
        }

        return pembayaranOnlines;
    }

    @Override
    public List<ItSimrsPembayaranOnlineEntity> getSearchEntityByCriteria(PembayaranOnline bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdAntrianTelemedic() != null){
            hsCriteria.put("id_antrian_telemedic", bean.getIdAntrianTelemedic());
        }
        if (bean.getApprovedFlag() != null){
            hsCriteria.put("approve_flag", bean.getApprovedFlag());
        }
        if (bean.getFlag() != null){
            hsCriteria.put("flag", bean.getFlag());
        }

        List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = new ArrayList<>();
        try {
            pembayaranOnlineEntities = verifikatorPembayaranDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] ERROR. ", e);
        }

        logger.info("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] END <<<");
        return pembayaranOnlineEntities;
    }

    @Override
    public void updateBuktiTransfer(String idTele, String pathBukti, String keterangan) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] START >>>");
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_antrian_telemedic", idTele);

        List<ItSimrsPembayaranOnlineEntity> resultPembayaran = new ArrayList<>();

        try {
           resultPembayaran = verifikatorPembayaranDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        resultPembayaran.get(0).setUrlFotoBukti(pathBukti);

        try {
            verifikatorPembayaranDao.updateAndSave(resultPembayaran.get(0));
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        Map hsCriteria2 = new HashMap();
        hsCriteria2.put("id", idTele);

        List<ItSimrsAntrianTelemedicEntity> resultTelemedic = new ArrayList<>();

        try {
            resultTelemedic = telemedicDao.getByCriteria(hsCriteria2);
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        if(keterangan.equalsIgnoreCase("konsultasi")) {
            resultTelemedic.get(0).setFlagBayarKonsultasi("Y");
        } else resultTelemedic.get(0).setFlagBayarResep("Y");

        logger.info("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] END <<<");
    }
}
