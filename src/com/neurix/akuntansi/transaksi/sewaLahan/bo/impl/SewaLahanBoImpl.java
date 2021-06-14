package com.neurix.akuntansi.transaksi.sewaLahan.bo.impl;

import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.masterVendor.dao.MasterVendorDao;
import com.neurix.akuntansi.master.masterVendor.model.ImMasterVendorEntity;
import com.neurix.akuntansi.master.pembayaran.dao.PembayaranDao;
import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.akuntansi.transaksi.kas.dao.KasDao;
import com.neurix.akuntansi.transaksi.kas.dao.KasDetailDao;
import com.neurix.akuntansi.transaksi.kas.model.ItAkunKasDetailEntity;
import com.neurix.akuntansi.transaksi.kas.model.ItAkunKasEntity;
import com.neurix.akuntansi.transaksi.sewaLahan.bo.SewaLahanBo;
import com.neurix.akuntansi.transaksi.sewaLahan.dao.SewaLahanDao;
import com.neurix.akuntansi.transaksi.sewaLahan.model.ItAkunSewaLahanEntity;
import com.neurix.akuntansi.transaksi.sewaLahan.model.SewaLahan;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
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
public class SewaLahanBoImpl implements SewaLahanBo {

    protected static transient Logger logger = Logger.getLogger(SewaLahanBoImpl.class);
    private SewaLahanDao sewaLahanDao;
    private BranchDao branchDao;
    private MasterVendorDao masterVendorDao;
    private PembayaranDao pembayaranDao;
    private KasDao kasDao;
    private KasDetailDao kasDetailDao;
    private MappingJurnalDao mappingJurnalDao;


    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public void setKasDetailDao(KasDetailDao kasDetailDao) {
        this.kasDetailDao = kasDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SewaLahanBoImpl.logger = logger;
    }

    public void setSewaLahanDao(SewaLahanDao sewaLahanDao) {
        this.sewaLahanDao = sewaLahanDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setMasterVendorDao(MasterVendorDao masterVendorDao) {
        this.masterVendorDao = masterVendorDao;
    }

    public void setPembayaranDao(PembayaranDao pembayaranDao) {
        this.pembayaranDao = pembayaranDao;
    }

    public void setKasDao(KasDao kasDao) {
        this.kasDao = kasDao;
    }


    @Override
    public void saveDelete(SewaLahan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
        }
        logger.info("[SewaLahanBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SewaLahan bean) throws GeneralBOException {
        logger.info("[SewaLahanBoImpl.saveEdit] start process >>>");

        if (bean!=null) {

        }
        logger.info("[SewaLahanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SewaLahan saveAdd(SewaLahan bean) throws GeneralBOException {
        logger.info("[SewaLahanBoImpl.saveAdd] start process >>>");
        String sewaLahanId = sewaLahanDao.getNextId();
        ItAkunSewaLahanEntity itAkunSewaLahanEntity = new ItAkunSewaLahanEntity();
        if (bean!=null) {
            itAkunSewaLahanEntity.setSewaLahanId(sewaLahanId);
            itAkunSewaLahanEntity.setNamaPenyewa(bean.getNamaPenyewa());
            itAkunSewaLahanEntity.setKeterangan(bean.getKeterangan());
            itAkunSewaLahanEntity.setTanggalBayar(bean.getTanggalBayar());
            itAkunSewaLahanEntity.setNilai(bean.getNilai());
            itAkunSewaLahanEntity.setNilaiNetto(bean.getNilaiNetto());
            itAkunSewaLahanEntity.setNilaiPpn(bean.getNilaiPpn());
            itAkunSewaLahanEntity.setNilaiPph(bean.getNilaiPph());
            itAkunSewaLahanEntity.setCancelFlag(bean.getCancelFlag());
            itAkunSewaLahanEntity.setBranchId(bean.getBranchId());
            itAkunSewaLahanEntity.setFlag("Y");
            itAkunSewaLahanEntity.setNoFaktur(bean.getNoFaktur());
            itAkunSewaLahanEntity.setBank(bean.getBank());
            itAkunSewaLahanEntity.setBranchId(bean.getBranchId());
            sewaLahanDao.addAndSave(itAkunSewaLahanEntity);


            ItAkunKasEntity itAkunKasEntity = new ItAkunKasEntity();
//            itAkunKasEntity.setTipeKas("KM");
            itAkunKasEntity.setKasId(sewaLahanId);
            itAkunKasEntity.setTipeTransaksi(CommonConstant.TRANSAKSI_ID_PENYEWAAN_LAHAN);
            itAkunKasEntity.setTanggal(bean.getTanggalBayar());
//            itAkunKasEntity.setCoaKas(bean.getCoaKas());
            itAkunKasEntity.setBayar(bean.getNilai());
            itAkunKasEntity.setKeterangan(bean.getKeterangan());
//            itAkunKasEntity.setNoSlipBank(bean.getNoSlipBank());
            itAkunKasEntity.setBranchId(bean.getBranchId());
            itAkunKasEntity.setNoJurnal(bean.getNoJurnal());
            itAkunKasEntity.setTipeKas("KM");
            itAkunKasEntity.setFlag(bean.getFlag());
            itAkunKasEntity.setAction(bean.getAction());
            itAkunKasEntity.setCreatedWho(bean.getCreatedWho());
            itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itAkunKasEntity.setCreatedDate(bean.getCreatedDate());
            itAkunKasEntity.setLastUpdate(bean.getLastUpdate());
            kasDao.addAndSave(itAkunKasEntity);

            String transId = CommonConstant.TRANSAKSI_ID_PENYEWAAN_LAHAN;
            String tipeJurnalId = mappingJurnalDao.tipeJurnalByTransId(transId);
            List<ImMappingJurnalEntity> mappingJurnal = mappingJurnalDao.getMappingByTipeJurnalIdNTransId(tipeJurnalId,transId);
            for(ImMappingJurnalEntity imMappingJurnalEntity : mappingJurnal){
                if(imMappingJurnalEntity.getPosisi().equals("K") ){
                    ItAkunKasDetailEntity itAkunKasDetailEntity = new ItAkunKasDetailEntity();
                    String kasDetailId = kasDetailDao.getNextKasDetailId();
                    itAkunKasDetailEntity.setKasDetailId(kasDetailId);
                    itAkunKasDetailEntity.setKasId(sewaLahanId);
                    itAkunKasDetailEntity.setKodeCoa(imMappingJurnalEntity.getKodeRekening());
                    if(imMappingJurnalEntity.getKodeRekening().equals(CommonConstant.REKENING_KODE_PENDAPATAN_DILUAR_USAHA)){
                        itAkunKasDetailEntity.setJumlahPembayaran(bean.getNilaiNetto());
                    }
                    else if(imMappingJurnalEntity.getKodeRekening().equals(CommonConstant.REKENING_KODE_PPH_PASAL4_AYAT2)){
                        itAkunKasDetailEntity.setJumlahPembayaran(bean.getNilaiPph());
                    }
                    else if(imMappingJurnalEntity.getKodeRekening().equals(CommonConstant.REKENING_KODE_PPN_KELUARAN)){
                        itAkunKasDetailEntity.setJumlahPembayaran(bean.getNilaiPpn());
                    }
                    itAkunKasDetailEntity.setFlag(bean.getFlag());
                    itAkunKasDetailEntity.setAction(bean.getAction());
                    itAkunKasDetailEntity.setCreatedWho(bean.getCreatedWho());
                    itAkunKasDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    itAkunKasDetailEntity.setCreatedDate(bean.getCreatedDate());
                    itAkunKasDetailEntity.setLastUpdate(bean.getLastUpdate());
                    itAkunKasDetailEntity.setDivisiId(imMappingJurnalEntity.getDivisiId());
                    itAkunKasDetailEntity.setNoNota("");
                    itAkunKasDetailEntity.setMasterId(imMappingJurnalEntity.getMasterId().equalsIgnoreCase("Y")?bean.getNamaPenyewa():"");
//                    itAkunKasDetailEntity.setNoFakturPajak(bean.getNoFaktur());
                    kasDetailDao.addAndSave(itAkunKasDetailEntity);
                }
            }


            /*try {
                if(data.getStFileUpload() != null && !data.getStFileUpload().isEmpty()){

                    BASE64Decoder decoder = new BASE64Decoder();                            //butuh fungsi save upload- note by aji
                    byte[] decodedBytes = decoder.decodeBuffer(data.getStFileUpload());
                    logger.info("Decoded upload data : " + decodedBytes.length);

                    String fileName = data.getNoFakturPajak() + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
                    String pathFolder = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK;
                    String uploadFile = pathFolder + fileName;
                    Path path = Paths.get(pathFolder);
                    if (!Files.exists(path)) {
                        File file = new File(pathFolder);
                        file.mkdirs();
                    }

                    logger.info("File save path : " + uploadFile);
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                    if (image == null) {
                        logger.error("Buffered Image is null");
                    } else {
                        File f = new File(uploadFile);
                        // write the image
                        ImageIO.write(image, "png", f);
                        itAkunKasDetailEntity.setUrlFakturImage(fileName);
                    }
                }
            }catch (Exception e){
                String error = "ERROR : "+e.getMessage();
                logger.error(error);
                throw new GeneralBOException(error);
            }*/
            /////////////////////////////////////////////////////////
            /*
            itAkunSewaLahanEntity.setUrlFakturImage(bean.getUrlFakturImage());
            itAkunSewaLahanEntity.setAction(bean.getAction());
            itAkunSewaLahanEntity.setCreatedDate(bean.getCreatedDate());
            itAkunSewaLahanEntity.setCreatedWho(bean.getCreatedWho());
            itAkunSewaLahanEntity.setLastUpdate(bean.getLastUpdate());
            itAkunSewaLahanEntity.setLastUpdateWho(bean.getLastUpdateWho());*/

        }
        logger.info("[SewaLahanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SewaLahan> getByCriteria(SewaLahan searchBean) throws GeneralBOException {
        logger.info("[SewaLahanBoImpl.getByCriteria] start process >>>");
        List<SewaLahan> listOfResult = new ArrayList();
        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSewaLahanId() != null && !"".equalsIgnoreCase(searchBean.getSewaLahanId())) {
                hsCriteria.put("sewa_lahan_id", searchBean.getSewaLahanId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getKeterangan() != null && !"".equalsIgnoreCase(searchBean.getKeterangan())) {
                hsCriteria.put("keterangan", searchBean.getKeterangan());
            }
            if (searchBean.getNamaPenyewa() != null && !"".equalsIgnoreCase(searchBean.getNamaPenyewa())) {
                hsCriteria.put("nama_penyewa", searchBean.getNamaPenyewa());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(searchBean.getStTanggalDari())) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(searchBean.getStTanggalSelesai())) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
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
            List<ItAkunSewaLahanEntity> itAkunPenyewaanLahanEntityList = null;
            try {
                itAkunPenyewaanLahanEntityList = sewaLahanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.getSearchPengajuanBiayaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itAkunPenyewaanLahanEntityList != null){
                // Looping from dao to object and save in collection
                for(ItAkunSewaLahanEntity penyewaanLahanEntity : itAkunPenyewaanLahanEntityList){

                    listOfResult.add(convertEntity(penyewaanLahanEntity));
                }
            }
        }
        logger.info("[SewaLahanBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    @Override
    public List<SewaLahan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public ItAkunSewaLahanEntity getPenyewaanLahanById(String sewaLahanId){
        return sewaLahanDao.getById("sewaLahanId", sewaLahanId);
    }

    @Override
    public void batalkanSewaLahan(SewaLahan bean) throws GeneralBOException {
        logger.info("[SewaLahanBoImpl.batalkanSewaLahan] start process >>>");
        if (bean!=null) {
            ItAkunSewaLahanEntity itAkunSewaLahanEntity = null;
            try {
                // Get data from database by ID
                itAkunSewaLahanEntity = sewaLahanDao.getById("sewaLahanId", bean.getSewaLahanId());
            } catch (HibernateException e) {
                logger.error("[SewaLahanBoImpl.batalkanSewaLahan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }
            if (itAkunSewaLahanEntity != null) {
                itAkunSewaLahanEntity.setCancelFlag(bean.getCancelFlag());
                itAkunSewaLahanEntity.setCancelDate(bean.getCancelDate());
                itAkunSewaLahanEntity.setCancelWho(bean.getCancelWho());

                itAkunSewaLahanEntity.setFlag(bean.getFlag());
                /*itAkunSewaLahanEntity.setAction(bean.getAction());
                itAkunSewaLahanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunSewaLahanEntity.setLastUpdate(bean.getLastUpdate());*/
                try {
                    // Update into database
                    sewaLahanDao.updateAndSave(itAkunSewaLahanEntity);
                } catch (HibernateException e) {
                    logger.error("[SewaLahanBoImpl.batalkanSewaLahan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SewaLahanBoImpl.batalkanSewaLahan] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }
        }
        logger.info("[SewaLahanBoImpl.batalkanSewaLahan] end process <<<");
    }

    @Override
    public void postingJurnal(SewaLahan bean) throws GeneralBOException {
        logger.info("[SewaLahanBoImpl.postingJurnal] start process >>>");
        if (bean!=null) {
            ItAkunSewaLahanEntity itAkunSewaLahanEntity = null;
            try {
                // Get data from database by ID
                itAkunSewaLahanEntity = sewaLahanDao.getById("sewaLahanId", bean.getSewaLahanId());
            } catch (HibernateException e) {
                logger.error("[SewaLahanBoImpl.postingJurnal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Kas by Kode Kas, please inform to your admin...," + e.getMessage());
            }

            if (itAkunSewaLahanEntity != null) {


                ItAkunKasEntity itAkunKasEntity =  kasDao.getById("kasId",bean.getSewaLahanId());
               if(itAkunKasEntity != null){
                   itAkunSewaLahanEntity.setNoJurnal(bean.getNoJurnal());
                   itAkunSewaLahanEntity.setFlag(bean.getFlag());

                   /*itAkunKasEntity.setApprovalKeuanganFlag(bean.getApprovalFlag());
                   itAkunKasEntity.setApprovalKeuanganDate(bean.getApprovalDate());
                   itAkunKasEntity.setApprovalKeuanganName(bean.getApprovalWho());*/

                   itAkunKasEntity.setNoJurnal(bean.getNoJurnal());
                   itAkunKasEntity.setRegisteredDate(bean.getApprovalDate());
                   itAkunKasEntity.setRegisteredFlag(bean.getApprovalFlag());
                   itAkunKasEntity.setRegisteredWho(bean.getApprovalWho());

                   itAkunKasEntity.setAction(bean.getAction());
                   itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                   itAkunKasEntity.setLastUpdate(bean.getLastUpdate());
               }
                try {
                    // Update into database
                    sewaLahanDao.updateAndSave(itAkunSewaLahanEntity);
                    kasDao.updateAndSave(itAkunKasEntity);
                } catch (HibernateException e) {
                    logger.error("[SewaLahanBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SewaLahanBoImpl.postingJurnal] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }
        }
        logger.info("[SewaLahanBoImpl.postingJurnal] end process <<<");
    }

    private SewaLahan convertEntity (ItAkunSewaLahanEntity data){
        SewaLahan result =  new SewaLahan();
        result.setSewaLahanId(data.getSewaLahanId());
        result.setNamaPenyewa(data.getNamaPenyewa());
        result.setKeterangan(data.getKeterangan());
        result.setTanggalBayar(data.getTanggalBayar());
        result.setStTanggalBayar(CommonUtil.convertDateToString(data.getTanggalBayar()));
        result.setNilai(data.getNilai());
        result.setNilaiNetto(data.getNilaiNetto());
        result.setNilaiPpn(data.getNilaiPpn());
        result.setNilaiPph(data.getNilaiPph());
        result.setCancelFlag(data.getCancelFlag());
        result.setCancelWho(data.getCancelWho());
        result.setCancelDate(data.getCancelDate());
        result.setStNilaiNetto(CommonUtil.numbericFormat(data.getNilaiNetto(),"###,###"));
        result.setFlag(data.getFlag());

        List<ImBranches> branchesList = branchDao.getListBranchById(data.getBranchId());
        for (ImBranches branches : branchesList){
            result.setBranchName(branches.getBranchName());
        }

        result.setStNilai(CommonUtil.numbericFormat(data.getNilai(),"###,###"));
        result.setStNilaiPpn(CommonUtil.numbericFormat(data.getNilaiPpn(),"###,###"));
        result.setStNilaiPph(CommonUtil.numbericFormat(data.getNilaiPph(),"###,###"));
        result.setBranchId(data.getBranchId());
        result.setNoJurnal(data.getNoJurnal());
        result.setNoFaktur(data.getNoFaktur());
        List<ImAkunPembayaranEntity> pembayaranEntityList  = pembayaranDao.getDataAkunPembayaranByCoa(data.getBank());
        for (ImAkunPembayaranEntity pembayaranEntity : pembayaranEntityList){
            result.setBankName(pembayaranEntity.getPembayaranName());
        }
        result.setBank(data.getBank());
        result.setMetodeBayar(data.getBank().isEmpty() ? "tunai":"transfer");

        ItAkunKasEntity itAkunKasEntity =  kasDao.getById("kasId",data.getSewaLahanId());
        if(itAkunKasEntity!= null) {
            result.setApprovalFlag(itAkunKasEntity.getRegisteredFlag());
//        result.setUrlFakturImage(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK+itAkunKasEntity.getUrlFakturImage());
            result.setApprovalWho(itAkunKasEntity.getApprovalKeuanganName());
            result.setApprovalDate(itAkunKasEntity.getApprovalKeuanganDate());
            result.setAction(itAkunKasEntity.getAction());
            result.setCreatedDate(itAkunKasEntity.getCreatedDate());
            result.setCreatedWho(itAkunKasEntity.getCreatedWho());
            result.setLastUpdate(itAkunKasEntity.getLastUpdate());
            result.setLastUpdateWho(itAkunKasEntity.getLastUpdateWho());

            if (itAkunKasEntity.getApprovalKeuanganDate()!=null){
                result.setStApprovalDate(CommonUtil.convertTimestampToStringLengkap(itAkunKasEntity.getApprovalKeuanganDate()));
            }
        }

        String branchIdUser = CommonUtil.userBranchLogin();
        result.setBranchIdUser(branchIdUser);

        /*if (branchIdUser.equalsIgnoreCase(itAkunKasEntity.getBranchId())){
            if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(roleId)){
                result.setJabatan("keu");
            }else if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(roleId)){
                if ("Y".equalsIgnoreCase(kasEntity.getApprovalKeuanganFlag())){
                    result.setJabatan("kasub");
                }
            }else if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(roleId)){
                if ("Y".equalsIgnoreCase(kasEntity.getApprovalKasubKeuanganFlag())) {
                    result.setJabatan("ka");
                }
            }
        }*/



        ImMasterVendorEntity masterVendorEntity = masterVendorDao.getById("nomorMaster",data.getNamaPenyewa());
        result.setNamaPenyewaName(masterVendorEntity.getNama());
        if (data.getCancelDate()!=null){
            result.setStCancelDate(CommonUtil.convertTimestampToStringLengkap(data.getCancelDate()));
        }

        return result;
    }
}
