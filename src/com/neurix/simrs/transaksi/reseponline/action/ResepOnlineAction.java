package com.neurix.simrs.transaksi.reseponline.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.kurir.bo.KurirBo;
import com.neurix.simrs.master.kurir.model.Kurir;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 18/06/20.
 */
public class ResepOnlineAction {
    private static transient Logger logger = Logger.getLogger(ResepOnlineAction.class);
    private ResepOnlineBo resepOnlineBoProxy;
    private PengirimanObat pengirimanObat;
    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public PengirimanObat getPengirimanObat() {
        return pengirimanObat;
    }

    public void setPengirimanObat(PengirimanObat pengirimanObat) {
        this.pengirimanObat = pengirimanObat;
    }

    public void setResepOnlineBoProxy(ResepOnlineBo resepOnlineBoProxy) {
        this.resepOnlineBoProxy = resepOnlineBoProxy;
    }

    public String initForm(){
        logger.info("ResepOnlineAction.initForm >>> ");
        setPengirimanObat(new PengirimanObat());
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResults");
        logger.info("ResepOnlineAction.initForm <<< ");
        if ("monitoring".equalsIgnoreCase(this.tipe)){
            return "search_monitoring";
        } else {
            return "search";
        }
    }

    public String searchApprovedResep(){
        logger.info("ResepOnlineAction.searchApprovedResep >>> ");

        String branchId = CommonUtil.userBranchLogin();

        PengirimanObat pengirimanObat = getPengirimanObat();

        if (pengirimanObat != null && "monitoring".equalsIgnoreCase(pengirimanObat.getTipe())){

            List<PengirimanObat> listResults = new ArrayList<>();
            if (pengirimanObat != null){
                if (pengirimanObat.getStatus() != null){
                    if ("pickup".equalsIgnoreCase(pengirimanObat.getStatus())){
                        pengirimanObat.setFlagPickup("Y");
                    } else if ("diterima".equalsIgnoreCase(pengirimanObat.getStatus())){
                        pengirimanObat.setFlagDiterimaPasien("Y");
                    }
                }
            }

            pengirimanObat.setBranchId(branchId);
            try {
                listResults = resepOnlineBoProxy.getListPengirimanObat(pengirimanObat);
            } catch (GeneralBOException e){
                logger.error("[ResepOnlineAction.searchPengiriman] ERROR. ", e);
                throw new GeneralBOException("[ResepOnlineAction.searchPengiriman] ERROR. ", e);
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResults");
            session.setAttribute("listOfResults", listResults);

            logger.info("ResepOnlineAction.searchPengiriman <<< ");
            return "search_monitoring";

        } else {

            List<PermintaanResep> listResults = new ArrayList<>();

            try {
                listResults = resepOnlineBoProxy.getListResepTelemedic(branchId);
            } catch (GeneralBOException e){
                logger.error("[ResepOnlineAction.searchPengiriman] ERROR. ", e);
                throw new GeneralBOException("[ResepOnlineAction.searchPengiriman] ERROR. ", e);
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResults");
            session.setAttribute("listOfResults", listResults);

            logger.info("ResepOnlineAction.searchPengiriman <<< ");
            return "search";
        }
    }

    public List<PengirimanObat> listPengiriman(){
        logger.info("ResepOnlineAction.listPengiriman >>> ");
        PengirimanObat getPengirimanObat = getPengirimanObat();
        List<PengirimanObat> listResults = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ResepOnlineBo resepOnlineBo = (ResepOnlineBo) ctx.getBean("resepOnlineBoProxy");

        try {
            listResults = resepOnlineBo.getListPengirimanObat(getPengirimanObat);
        } catch (GeneralBOException e){
            logger.error("[ResepOnlineAction.listPengiriman] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineAction.listPengiriman] ERROR. ", e);
        }

        logger.info("ResepOnlineAction.listPengiriman <<< ");
        return listResults;
    }

    public List<PermintaanResep> listApprovedResep(){
        logger.info("ResepOnlineAction.listApprovedResep >>> ");
        String branchId = CommonUtil.userBranchLogin();
        List<PermintaanResep> listResults = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ResepOnlineBo resepOnlineBo = (ResepOnlineBo) ctx.getBean("resepOnlineBoProxy");

        try {
            listResults = resepOnlineBo.getListResepTelemedic(branchId);
        } catch (GeneralBOException e){
            logger.error("[ResepOnlineAction.listApprovedResep] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineAction.listApprovedResep] ERROR. ", e);
        }

        logger.info("ResepOnlineAction.listApprovedResep <<< ");
        return listResults;
    }

    public List<Kurir> listKurirByBranchLogin(){
        logger.info("ResepOnlineAction.listKurir >>> ");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KurirBo kurirBo = (KurirBo) ctx.getBean("kurirBoProxy");

        String branchId = CommonUtil.userBranchLogin();
        List<Kurir> kurirList = new ArrayList<>();

        Kurir kurir = new Kurir();
        kurir.setBranchId(branchId);
        kurir.setFlagReady("Y");

        try {
            kurirList = kurirBo.getByCriteria(kurir);
        } catch (GeneralBOException e){
            logger.error("[ResepOnlineAction.listKurir] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineAction.listKurir] ERROR. ", e);
        }

        logger.info("ResepOnlineAction.listKurir <<< ");
        return kurirList;
    }

    public CrudResponse saveAssignKurir(String idKurir, String idResep, String idPasien, String idPelayanan){
        logger.info("ResepOnlineAction.saveAssignKurir >>> ");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String branchId = CommonUtil.userBranchLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ResepOnlineBo resepOnlineBo = (ResepOnlineBo) ctx.getBean("resepOnlineBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");


        ItSimrsAntrianTelemedicEntity dataTelemedic = null;
        ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idResep);
        if (permintaanResepEntity != null){
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(permintaanResepEntity.getIdTransaksiOnline());
            if (pembayaranOnlineEntity != null){
                ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
                if (antrianTelemedicEntity != null){
                    dataTelemedic = new ItSimrsAntrianTelemedicEntity();
                    dataTelemedic = antrianTelemedicEntity;
                }
            }
        }

        CrudResponse response = new CrudResponse();

        PengirimanObat pengirimanObat = new PengirimanObat();
        pengirimanObat.setIdResep(idResep);
        pengirimanObat.setIdKurir(idKurir);
        pengirimanObat.setIdPasien(idPasien);
        pengirimanObat.setIdPelayanan(idPelayanan);
        pengirimanObat.setCreatedDate(time);
        pengirimanObat.setLastUpdate(time);
        pengirimanObat.setCreatedWho(userLogin);
        pengirimanObat.setLastUpdateWho(userLogin);
        pengirimanObat.setBranchId(branchId);
        if (dataTelemedic != null){
            pengirimanObat.setAlamat(dataTelemedic.getAlamat());
            pengirimanObat.setLat(dataTelemedic.getLat());
            pengirimanObat.setLon(dataTelemedic.getLon());
            pengirimanObat.setNoTelp(dataTelemedic.getNoTelp());
        }

        try {
            response = resepOnlineBo.saveAddPengirimanObat(pengirimanObat);
            response.setStatus("success");
        } catch (GeneralBOException e){
            response.setStatus("error");
            response.setMsg("[ResepOnlineAction.saveAssignKurir] ERROR. "+ e);
            logger.error("[ResepOnlineAction.saveAssignKurir] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineAction.saveAssignKurir] ERROR. ", e);
        }


        List<NotifikasiFcm> notifikasiFcm = new ArrayList<>();
        NotifikasiFcm bean = new NotifikasiFcm();
        Notifikasi notifBean = new Notifikasi();


        notifBean.setTipeNotifId("TN12");
        notifBean.setNip(pengirimanObat.getIdPasien());
        notifBean.setNamaPegawai("admin");
        notifBean.setNote("Kurir telah di Assign. Silahkan buka aplikasi untuk melihat status pengiriman");
        notifBean.setTo(pengirimanObat.getIdPasien());
        notifBean.setFromPerson("admin");
        notifBean.setNoRequest(pengirimanObat.getId());
        notifBean.setFlag("Y");
        notifBean.setRead("N");
        notifBean.setAction("C");
        notifBean.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        notifBean.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        notifBean.setCreatedWho("admin");
        notifBean.setLastUpdateWho("admin");

        notifikasiBo.saveAdd(notifBean);

        //Push Notif ke Pasien terkait perubahan status menjadi WL
        bean.setUserId(pengirimanObat.getIdPasien());
        notifikasiFcm = notifikasiFcmBo.getByCriteria(bean);
        FirebasePushNotif.sendNotificationFirebase(notifikasiFcm.get(0).getTokenFcm(),"Resep", "Kurir telah di Assign. Silahkan buka aplikasi untuk melihat status pengiriman", "WL", notifikasiFcm.get(0).getOs(), null);


        logger.info("ResepOnlineAction.saveAssignKurir <<< ");
        return response;
    }
}
