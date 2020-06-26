package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kurir.bo.KurirBo;
import com.neurix.simrs.master.kurir.model.Kurir;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 11/06/20.
 */
public class TesTelemedicController implements ModelDriven<Object> {

    private static transient Logger logger = Logger.getLogger(TesTelemedicController.class);

    private String data;
    private String result;
    private String id;
    private String obat;
    private TelemedicBo telemedicBoProxy;
    private KurirBo kurirBoProxy;
    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public void setVerifikatorPembayaranBoProxy(VerifikatorPembayaranBo verifikatorPembayaranBoProxy) {
        this.verifikatorPembayaranBoProxy = verifikatorPembayaranBoProxy;
    }

    public void setKurirBoProxy(KurirBo kurirBoProxy) {
        this.kurirBoProxy = kurirBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object getModel() {
        return null;
    }

    public String index(){
        logger.info(data);
        switch (data){
            case "insert-umum-non-resep":
                insertDataTelemedic("umum", "");
                break;
            case "insert-umum-resep":
                insertDataTelemedic("umum","resep");
                break;
            case "insert-asuransi-non-resep":
                insertDataTelemedic("asuransi", "");
                break;
            case "insert-asuransi-resep":
                insertDataTelemedic("asuransi","resep");
                break;
            case "insert-resep":
                insertObat(this.id);
                break;
            case "insert-kurir":
                insertKurir(this.id);
                break;
            case "insert-e-resep":
                insertPemesananResep("umum");
                break;
            case "create-invoice-e-resep":
                createPembayaranResep(this.id, this.obat);
            case "bayar-resep-telemedic":
                bayar(this.id, "resep");
            case "bayar-konsultasi-telemedic":
                bayar(this.id, "konsultasi");
            default:
                logger.info("==========NO ONE CARE============");
        }
        return result;
    }

    private void insertDataTelemedic(String tipe, String jenis){

        logger.info("[TesTelemedicController.insertDataTelemedic] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
        antrianTelemedicEntity.setBranchId("RS01");
        antrianTelemedicEntity.setIdPasien("RS0104200035");
        antrianTelemedicEntity.setIdPelayanan("PYN00000002");
        antrianTelemedicEntity.setIdDokter("DKR00000012");
        antrianTelemedicEntity.setKodeBank("1.1.01.02.01");
        antrianTelemedicEntity.setIdJenisPeriksaPasien(tipe);
        if ("resep".equalsIgnoreCase(jenis)){
            antrianTelemedicEntity.setFlagResep("Y");
        }
        if ("asuransi".equalsIgnoreCase(tipe)){
            antrianTelemedicEntity.setNoKartu("080780808");
            antrianTelemedicEntity.setIdAsuransi("ASN00000002");
//            if ("resep".equalsIgnoreCase(jenis)){
//                antrianTelemedicEntity.setFlagBayarResep("Y");
//                antrianTelemedicEntity.setFlagBayarKonsultasi("Y");
//            } else {
//                antrianTelemedicEntity.setFlagBayarKonsultasi("Y");
//            }
        }
        antrianTelemedicEntity.setCreatedDate(time);
        antrianTelemedicEntity.setCreatedWho("admin");
        antrianTelemedicEntity.setLastUpdate(time);
        antrianTelemedicEntity.setLastUpdateWho("admin");
        antrianTelemedicEntity.setAlamat("Kartika Mas Regenci No 60");
        antrianTelemedicEntity.setLat("-7.345948");
        antrianTelemedicEntity.setLon("112.746283");
        antrianTelemedicEntity.setNoTelp("089090909909");
        antrianTelemedicEntity.setJenisPengambilan("kirim");

        try {
            telemedicBoProxy.saveAdd(antrianTelemedicEntity, "RS01", "1.1.01.02.01");
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertDataTelemedic] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertDataTelemedic] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertDataTelemedic] END <<<");
    }

    private void insertPemesananResep(String tipe){

        logger.info("[TesTelemedicController.insertDataTelemedic] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
        antrianTelemedicEntity.setBranchId("RS01");
        antrianTelemedicEntity.setIdPasien("RS0104200035");
        antrianTelemedicEntity.setIdPelayanan("PYN00000024");
//        antrianTelemedicEntity.setIdDokter("DKR00000012");
//        antrianTelemedicEntity.setKodeBank("1.1.01.02.01");
        antrianTelemedicEntity.setIdJenisPeriksaPasien(tipe);
        antrianTelemedicEntity.setFlagResep("Y");
        antrianTelemedicEntity.setFlagEresep("Y");
        if ("ansuransi".equalsIgnoreCase(tipe)){
            antrianTelemedicEntity.setNoKartu("080780808");
            antrianTelemedicEntity.setIdAsuransi("ASN00000002");
        }
        antrianTelemedicEntity.setCreatedDate(time);
        antrianTelemedicEntity.setCreatedWho("admin");
        antrianTelemedicEntity.setLastUpdate(time);
        antrianTelemedicEntity.setLastUpdateWho("admin");
        antrianTelemedicEntity.setAlamat("Kartika Mas Regenci No 60");
        antrianTelemedicEntity.setLat("-7.345948");
        antrianTelemedicEntity.setLon("112.746283");
        antrianTelemedicEntity.setNoTelp("089090909909");
        antrianTelemedicEntity.setJenisPengambilan("kirim");

        try {
            telemedicBoProxy.saveAdd(antrianTelemedicEntity, "RS01", "");
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertDataTelemedic] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertDataTelemedic] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertDataTelemedic] END <<<");
    }

    private void bayar(String idAntrianTelemedic, String jenis){
        logger.info("[TesTelemedicController.bayarResep] START >>>");

        Timestamp time = CommonUtil.getCurrentDateTimes();

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        antrianTelemedic.setId(idAntrianTelemedic);
        if ("resep".equalsIgnoreCase(jenis)){
            antrianTelemedic.setFlagBayarResep("Y");
        } else {
            antrianTelemedic.setFlagBayarKonsultasi("Y");
        }
        antrianTelemedic.setAction("U");
        antrianTelemedic.setLastUpdate(time);
        antrianTelemedic.setLastUpdateWho("admin");

        try {
            telemedicBoProxy.saveEdit(antrianTelemedic, "", "");
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.bayarResep] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.bayarResep] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.bayarResep] END <<<");
    }

    private void insertObat(String idTransksiObat){
        logger.info("[TesTelemedicController.insertObat] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdObat("OBT00000030");
        transaksiObatDetail.setIdPelayanan("PYN00000024");
        transaksiObatDetail.setQty(new BigInteger("5"));
        transaksiObatDetail.setFlag("Y");
        transaksiObatDetail.setAction("C");
        transaksiObatDetail.setCreatedDate(time);
        transaksiObatDetail.setCreatedWho("admin");

        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
        transaksiObatDetails.add(transaksiObatDetail);
        try {
            telemedicBoProxy.insertResepOnline(idTransksiObat, transaksiObatDetails);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertObat] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertObat] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertObat] END <<<");
    }

    private void insertKurir(String nama){
        logger.info("[TesTelemedicController.insertKurir] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        Kurir kurir = new Kurir();
        kurir.setNama(nama);
        kurir.setBranchId("RS01");
        kurir.setFlagReady("Y");
        kurir.setNoKtp("1212120012");
        kurir.setNoTelp("0089808008080");
        kurir.setFlag("Y");
        kurir.setAction("C");
        kurir.setCreatedDate(time);
        kurir.setCreatedWho("Admin");
        kurir.setLastUpdate(time);
        kurir.setLastUpdateWho("Admin");
        kurir.setPassword("123");
        kurir.setNoPolisi("S8908WD");

        try {
            kurirBoProxy.saveAdd(kurir);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertKurir] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertKurir] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertKurir] END <<<");
    }

    private void createPembayaranResep(String idAntrianOnline, String idObat){
        logger.info("[TesTelemedicController.insertObat] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        ItSimrsAntrianTelemedicEntity telemedicEntity = telemedicBoProxy.getAntrianTelemedicEntityById(idAntrianOnline);
        if (telemedicEntity != null){

            antrianTelemedic.setId(telemedicEntity.getId());
            antrianTelemedic.setCreatedDate(time);
            antrianTelemedic.setLastUpdate(time);
            antrianTelemedic.setCreatedWho("admin");
            antrianTelemedic.setLastUpdateWho("admin");

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
            transaksiObatDetail.setIdObat(idObat);
            transaksiObatDetail.setIdPelayanan(telemedicEntity.getId());
            transaksiObatDetail.setQty(new BigInteger("5"));
            transaksiObatDetail.setFlag("Y");
            transaksiObatDetail.setAction("C");
            transaksiObatDetail.setCreatedDate(time);
            transaksiObatDetail.setCreatedWho("admin");

            transaksiObatDetails.add(transaksiObatDetail);

            try {
                telemedicBoProxy.createPembayaranResep(antrianTelemedic, transaksiObatDetails);
            } catch (GeneralBOException e){
                logger.error("[TesTelemedicController.insertObat] ERROR. ",e);
                throw new GeneralBOException("[TesTelemedicController.insertObat] ERROR. ", e);
            }
        }
        logger.info("[TesTelemedicController.insertObat] END <<<");
    }
}
