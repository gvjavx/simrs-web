package com.neurix.akuntansi.transaksi.koreksi.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.kas.dao.LampiranDao;
import com.neurix.akuntansi.transaksi.kas.model.ItAkunLampiranEntity;
import com.neurix.akuntansi.transaksi.kas.model.Lampiran;
import com.neurix.akuntansi.transaksi.koreksi.bo.KoreksiBo;
import com.neurix.akuntansi.transaksi.koreksi.dao.KoreksiDao;
import com.neurix.akuntansi.transaksi.koreksi.dao.KoreksiDetailDao;
import com.neurix.akuntansi.transaksi.koreksi.model.ItAkunKoreksiDetailEntity;
import com.neurix.akuntansi.transaksi.koreksi.model.ItAkunKoreksiEntity;
import com.neurix.akuntansi.transaksi.koreksi.model.Koreksi;
import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Aji Noor on 18/03/2021
 */
public class KoreksiBoImpl implements KoreksiBo {
    protected static transient Logger logger = Logger.getLogger(KoreksiBoImpl.class);

    private KoreksiDao koreksiDao;
    private KoreksiDetailDao koreksiDetailDao;
    private LampiranDao lampiranDao;
    private BranchDao branchDao;
    private TransDao transDao;
    private PositionDao positionDao;
    private UserDao userDao;
    private JurnalDao jurnalDao;
    private KodeRekeningDao kodeRekeningDao;
    private MappingJurnalDao mappingJurnalDao;


    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public void setJurnalDao(JurnalDao jurnalDao) {
        this.jurnalDao = jurnalDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setKoreksiDetailDao(KoreksiDetailDao koreksiDetailDao) {
        this.koreksiDetailDao = koreksiDetailDao;
    }

    public void setTransDao(TransDao transDao) {
        this.transDao = transDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public static void setLogger(Logger logger) {
        KoreksiBoImpl.logger = logger;
    }

    public void setKoreksiDao(KoreksiDao koreksiDao) {
        this.koreksiDao = koreksiDao;
    }

    public void setLampiranDao(LampiranDao lampiranDao) {
        this.lampiranDao = lampiranDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public void saveDelete(Koreksi bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Koreksi bean) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            String koreksiId;
            // creating object header entity serializable
            ItAkunKoreksiEntity itAkunKoreksiEntity = koreksiDao.getById("koreksiId", bean.getKoreksiId());
            itAkunKoreksiEntity.setKoreksiId(bean.getKoreksiId());
            itAkunKoreksiEntity.setTanggalKoreksi(CommonUtil.convertStringToDate2(bean.getStTanggalKoreksi()));
            itAkunKoreksiEntity.setKeterangan(bean.getKeterangan());

            itAkunKoreksiEntity.setFlag(bean.getFlag());
            itAkunKoreksiEntity.setAction(bean.getAction());
            itAkunKoreksiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itAkunKoreksiEntity.setLastUpdate(bean.getLastUpdate());

            itAkunKoreksiEntity.setApprovalKeuanganFlag(bean.getApprovalKeuanganFlag());
            itAkunKoreksiEntity.setApprovalKeuanganName(bean.getApprovalKeuanganFlag());

            //langsung diapprove bawahan apabila yang membuat adalah atasan
            if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin()) || CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())) {
                itAkunKoreksiEntity.setApprovalKeuanganId(CommonUtil.userIdLogin());
                itAkunKoreksiEntity.setApprovalKeuanganDate(bean.getLastUpdate());
                itAkunKoreksiEntity.setApprovalKeuanganName(bean.getCreatedWho());
                itAkunKoreksiEntity.setApprovalKeuanganFlag("Y");
            }
            if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())) {
                itAkunKoreksiEntity.setApprovalKasubKeuanganId(CommonUtil.userIdLogin());
                itAkunKoreksiEntity.setApprovalKasubKeuanganDate(bean.getLastUpdate());
                itAkunKoreksiEntity.setApprovalKasubKeuanganName(bean.getCreatedWho());
                itAkunKoreksiEntity.setApprovalKasubKeuanganFlag("Y");
            }
            try {
                // insert into header database
                koreksiDao.updateAndSave(itAkunKoreksiEntity);
            } catch (HibernateException e) {
                logger.error("[KoreksiBoImpl.saveAddKas] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[KoreksiBoImpl.saveAdd] start process >>>");
    }

    @Override
    public Koreksi saveAdd(Koreksi bean) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.saveAdd] start process >>>");
        Koreksi result = null;
        if (bean != null) {
            String koreksiId;
            try {
                // Generating ID, get from postgre sequence
                koreksiId = koreksiDao.getNextKoreksiId();
            } catch (HibernateException e) {
                logger.error("[KoreksiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence kasId id, please info to your admin..." + e.getMessage());
            }
            // creating object header entity serializable
            ItAkunKoreksiEntity itAkunKoreksiEntity = new ItAkunKoreksiEntity();
            itAkunKoreksiEntity.setKoreksiId(koreksiId);
            itAkunKoreksiEntity.setTipeTransaksi(bean.getTipeTransaksi());
            itAkunKoreksiEntity.setTanggalKoreksi(bean.getTanggalKoreksi());
            itAkunKoreksiEntity.setKeterangan(bean.getKeterangan());
            itAkunKoreksiEntity.setBranchId(bean.getBranchId());
            itAkunKoreksiEntity.setNoJurnal(bean.getNoJurnal());

            itAkunKoreksiEntity.setFlag(bean.getFlag());
            itAkunKoreksiEntity.setAction(bean.getAction());
            itAkunKoreksiEntity.setCreatedWho(bean.getCreatedWho());
            itAkunKoreksiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itAkunKoreksiEntity.setCreatedDate(bean.getCreatedDate());
            itAkunKoreksiEntity.setLastUpdate(bean.getLastUpdate());

            //langsung diapprove bawahan apabila yang membuat adalah atasan
            if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin()) || CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())) {
                itAkunKoreksiEntity.setApprovalKeuanganId(CommonUtil.userIdLogin());
                itAkunKoreksiEntity.setApprovalKeuanganDate(bean.getLastUpdate());
                itAkunKoreksiEntity.setApprovalKeuanganName(bean.getCreatedWho());
                itAkunKoreksiEntity.setApprovalKeuanganFlag("Y");
            }

            if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())) {
                itAkunKoreksiEntity.setApprovalKasubKeuanganId(CommonUtil.userIdLogin());
                itAkunKoreksiEntity.setApprovalKasubKeuanganDate(bean.getLastUpdate());
                itAkunKoreksiEntity.setApprovalKasubKeuanganName(bean.getCreatedWho());
                itAkunKoreksiEntity.setApprovalKasubKeuanganFlag("Y");
            }

            try {
                // insert into header database
                koreksiDao.addAndSave(itAkunKoreksiEntity);
                result = convertEntityToKoreksi(itAkunKoreksiEntity);
            } catch (HibernateException e) {
                logger.error("[KoreksiBoImpl.saveAddKas] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[KoreksiBoImpl.saveAdd] start process >>>");
        return result;

    }

    @Override
    public List<Koreksi> getByCriteria(Koreksi searchBean) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getByCriteria] start process >>>");
        // Mapping with collection and put
        List<Koreksi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getKoreksiId() != null && !"".equalsIgnoreCase(searchBean.getKoreksiId())) {
                hsCriteria.put("koreksi_id", searchBean.getKoreksiId());
            }
            if (searchBean.getNoJurnal() != null && !"".equalsIgnoreCase(searchBean.getNoJurnal())) {
                hsCriteria.put("no_jurnal", searchBean.getNoJurnal());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getTanggalKoreksi() != null) {
                hsCriteria.put("tanggal", searchBean.getTanggalKoreksi());
            }
            if (searchBean.getTipeTransaksi() != null && !"".equalsIgnoreCase(searchBean.getTipeTransaksi())) {
                hsCriteria.put("tipe_transaksi", searchBean.getTipeTransaksi());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
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

            List<ItAkunKoreksiEntity> itAkunKoreksiEntity;
            try {
                itAkunKoreksiEntity = koreksiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[KoreksiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itAkunKoreksiEntity != null) {
                Koreksi returnKas;
                // Looping from dao to object and save in collection
                for (ItAkunKoreksiEntity koreksiEntity : itAkunKoreksiEntity) {
                    returnKas = convertEntityToKoreksi(koreksiEntity);
                    listOfResult.add(returnKas);
                }
            }
        }
        logger.info("[KoreksiBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    private Koreksi convertEntityToKoreksi(ItAkunKoreksiEntity itAkunKoreksiEntity) {
        Koreksi returnKoreksi = new Koreksi();
        returnKoreksi.setKoreksiId(itAkunKoreksiEntity.getKoreksiId());
        returnKoreksi.setTipeTransaksi(itAkunKoreksiEntity.getTipeTransaksi());
        returnKoreksi.setTanggalKoreksi(itAkunKoreksiEntity.getTanggalKoreksi());
        returnKoreksi.setStTanggalKoreksi(CommonUtil.convertDateToString(itAkunKoreksiEntity.getTanggalKoreksi()));
        returnKoreksi.setKeterangan(itAkunKoreksiEntity.getKeterangan());
        returnKoreksi.setBranchId(itAkunKoreksiEntity.getBranchId());

        //APPROVAL
        returnKoreksi.setApprovalKeuanganFlag(itAkunKoreksiEntity.getApprovalKeuanganFlag());
        returnKoreksi.setApprovalKeuanganId(itAkunKoreksiEntity.getApprovalKeuanganId());
        returnKoreksi.setApprovalKeuanganName(itAkunKoreksiEntity.getApprovalKeuanganName());
        returnKoreksi.setApprovalKeuanganDate(itAkunKoreksiEntity.getApprovalKeuanganDate());
        if (itAkunKoreksiEntity.getApprovalKeuanganDate() != null) {
            returnKoreksi.setStApprovalKeuanganDate(CommonUtil.convertTimestampToStringLengkap(itAkunKoreksiEntity.getApprovalKeuanganDate()));
        }
        returnKoreksi.setApprovalKasubKeuanganFlag(itAkunKoreksiEntity.getApprovalKasubKeuanganFlag());
        returnKoreksi.setApprovalKasubKeuanganId(itAkunKoreksiEntity.getApprovalKasubKeuanganId());
        returnKoreksi.setApprovalKasubKeuanganName(itAkunKoreksiEntity.getApprovalKasubKeuanganName());
        returnKoreksi.setApprovalKasubKeuanganDate(itAkunKoreksiEntity.getApprovalKasubKeuanganDate());
        if (itAkunKoreksiEntity.getApprovalKasubKeuanganDate() != null) {
            returnKoreksi.setStApprovalKasubKeuanganDate(CommonUtil.convertTimestampToStringLengkap(itAkunKoreksiEntity.getApprovalKasubKeuanganDate()));
        }

        List<ImBranches> branchesList = branchDao.getListBranchById(itAkunKoreksiEntity.getBranchId());
        for (ImBranches branches : branchesList) {
            returnKoreksi.setBranchName(branches.getBranchName());
        }

        String roleId = CommonUtil.roleIdAsLogin();
        String branchIdUser = CommonUtil.userBranchLogin();

        if (branchIdUser.equalsIgnoreCase(itAkunKoreksiEntity.getBranchId())) {
            if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(roleId)) {
                returnKoreksi.setJabatan("keu");
            } else if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(roleId)) {
                if ("Y".equalsIgnoreCase(itAkunKoreksiEntity.getApprovalKeuanganFlag())) {
                    returnKoreksi.setJabatan("kasub");
                }
            } else if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(roleId)) {
                if ("Y".equalsIgnoreCase(itAkunKoreksiEntity.getApprovalKasubKeuanganFlag())) {
                    returnKoreksi.setJabatan("ka");
                }
            }
        }

        returnKoreksi.setNoJurnal(itAkunKoreksiEntity.getNoJurnal());

        if (itAkunKoreksiEntity.getRegisteredFlag() != null) {
            if (("Y").equalsIgnoreCase(itAkunKoreksiEntity.getRegisteredFlag())) {
                returnKoreksi.setFlagPosting(true);
            }
        }
        if (itAkunKoreksiEntity.getTipeTransaksi() != null) {
            try {
                ImTransEntity transEntity = transDao.getById("transId", itAkunKoreksiEntity.getTipeTransaksi());
                returnKoreksi.setStTipeTransaksi(transEntity != null ? transEntity.getTransName() : "Uang Muka");
            } catch (HibernateException e) {
                logger.error("[KoreksiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        }
//        returnKoreksi.setNamaKas(kodeRekeningDao.getNamaRekeningByCoa(itAkunKoreksiEntity.getCoaKas()));

        returnKoreksi.setRegisteredFlag(itAkunKoreksiEntity.getRegisteredFlag());
        returnKoreksi.setRegisteredWho(itAkunKoreksiEntity.getRegisteredWho());
        returnKoreksi.setRegisteredDate(itAkunKoreksiEntity.getRegisteredDate());
        if (itAkunKoreksiEntity.getRegisteredDate() != null) {
            returnKoreksi.setStRegisteredDate(CommonUtil.convertTimestampToStringLengkap(itAkunKoreksiEntity.getRegisteredDate()));
        }
        returnKoreksi.setCreatedWho(itAkunKoreksiEntity.getCreatedWho());
        returnKoreksi.setCreatedDate(itAkunKoreksiEntity.getCreatedDate());
        returnKoreksi.setLastUpdate(itAkunKoreksiEntity.getLastUpdate());
        returnKoreksi.setAction(itAkunKoreksiEntity.getAction());
        returnKoreksi.setFlag(itAkunKoreksiEntity.getFlag());
        return returnKoreksi;
    }


    @Override
    public List<Koreksi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public String saveAddKoreksi(Koreksi bean, List<KoreksiDetail> koreksiDetailList, List<Lampiran> lampiranList) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.saveAdd] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Koreksi response = saveAdd(bean);

        if (koreksiDetailList != null) {
            for (KoreksiDetail koreksiDetail : koreksiDetailList) {
                String koreksiDetailId = koreksiDao.getNextKoreksiDetailId();
                ItAkunKoreksiDetailEntity detailEntity = new ItAkunKoreksiDetailEntity();
                detailEntity.setKoreksiDetailId(koreksiDetailId);
                detailEntity.setKoreksiId(response.getKoreksiId());
                detailEntity.setDivisiId(koreksiDetail.getDivisiId());
                detailEntity.setJumlahPembayaran(koreksiDetail.getJumlahPembayaran());
                detailEntity.setKodeCoa(koreksiDetail.getKodeCoa());
                detailEntity.setKodeVendor(koreksiDetail.getKodeVendor());
                detailEntity.setNoNota(koreksiDetail.getNoNota());
                detailEntity.setPosisi(koreksiDetail.getPosisiCoa());

                detailEntity.setFlag("Y");
                detailEntity.setCreatedDate(updateTime);
                detailEntity.setCreatedWho(userLogin);

                try {
                    koreksiDetailDao.addAndSave(detailEntity);
                } catch (Exception e) {
                    logger.error("[KoreksiBoImpl.saveAddKas] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                }
            }
        }

        if (lampiranList != null) {
            for (Lampiran lampiran : lampiranList) {
                ItAkunLampiranEntity lampiranEntity = new ItAkunLampiranEntity();
                try {
                    if (lampiran.getUploadFile() != null && !lampiran.getUploadFile().isEmpty()) {

                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes;
                        decodedBytes = decoder.decodeBuffer(lampiran.getUploadFile());
                        logger.info("Decoded upload data : " + decodedBytes.length);
                        String potNama = lampiran.getNamaLampiran().replace(" ", "");
                        if (potNama.length() > 20) {
                            potNama = potNama.substring(0, 20);
                        }
                        String randomNumber = "-" + String.valueOf(CommonUtil.getRandomNumberInts(1, 999)) + "-";
                        String fileName = potNama + randomNumber + dateFormater("dd") + dateFormater("MM") + dateFormater("yy") + ".png";
                        String folder = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_LAMPIRAN;
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_LAMPIRAN + fileName;
                        Path path = Paths.get(folder);
                        if (!Files.exists(path)) {
                            File file = new File(folder);
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
                            lampiranEntity.setUrl(fileName);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String lampiranId = lampiranDao.getNextLampiranId();
                lampiranEntity.setLampiranId(lampiranId);
                lampiranEntity.setNamaLaporan(lampiran.getNamaLampiran());
                lampiranEntity.setTransaksiId(response.getKoreksiId());

                lampiranEntity.setFlag(bean.getFlag());
                lampiranEntity.setAction(bean.getAction());
                lampiranEntity.setCreatedWho(bean.getCreatedWho());
                lampiranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                lampiranEntity.setCreatedDate(bean.getCreatedDate());
                lampiranEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    lampiranDao.addAndSave(lampiranEntity);
                } catch (HibernateException e) {
                    logger.error("[KoreksiBoImpl.saveAddKas] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[KoreksiBoImpl.saveAddKas] end process <<<");
        return null;
    }

    @Override
    public Koreksi saveEditKoreksi(Koreksi bean, List<KoreksiDetail> koreksiDetailList, List<Lampiran> lampiranList) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.saveEditKoreksi] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//        simpan perubahan header
        saveEdit(bean);

        if (koreksiDetailList.size() > 0) {
            for (KoreksiDetail koreksiDetail : koreksiDetailList) {
                // creating object entity serializable
                try {
                    if (koreksiDetail.getAction().equalsIgnoreCase("C")) {
                        String koreksiDetailId = koreksiDao.getNextKoreksiDetailId();
                        ItAkunKoreksiDetailEntity detailEntity = new ItAkunKoreksiDetailEntity();
                        detailEntity.setKoreksiDetailId(koreksiDetailId);
                        detailEntity.setKoreksiId(bean.getKoreksiId());
                        detailEntity.setDivisiId(koreksiDetail.getDivisiId());
                        detailEntity.setJumlahPembayaran(koreksiDetail.getJumlahPembayaran());
                        detailEntity.setKodeCoa(koreksiDetail.getKodeCoa());
                        detailEntity.setKodeVendor(koreksiDetail.getKodeVendor());
                        detailEntity.setNoNota(koreksiDetail.getNoNota());
                        detailEntity.setPosisi(koreksiDetail.getPosisiCoa());

                        detailEntity.setFlag(koreksiDetail.getFlag());
                        detailEntity.setCreatedDate(updateTime);
                        detailEntity.setCreatedWho(userLogin);
                        koreksiDetailDao.addAndSave(detailEntity);
                    } else {
                        ItAkunKoreksiDetailEntity detailEntity = null;
                        try {
                            detailEntity = koreksiDetailDao.getById("koreksiDetailId", koreksiDetail.getKoreksiDetailId());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        if (detailEntity != null) {
                            if (koreksiDetail.getAction().equalsIgnoreCase("U")) {
                                detailEntity.setJumlahPembayaran(CommonUtil.StringDenganFormatToBigDecimal(koreksiDetail.getStJumlahPembayaran()));
                            } else {
                                detailEntity.setJumlahPembayaran(koreksiDetail.getJumlahPembayaran());
                            }
                            detailEntity.setDivisiId(koreksiDetail.getDivisiId());
                            detailEntity.setKodeCoa(koreksiDetail.getKodeCoa());
                            detailEntity.setKodeVendor(koreksiDetail.getKodeVendor());
                            detailEntity.setNoNota(koreksiDetail.getNoNota());
                            detailEntity.setFlag(koreksiDetail.getFlag());

                            detailEntity.setLastUpdate(updateTime);
                            detailEntity.setLastUpdateWho(userLogin);
                            koreksiDetailDao.updateAndSave(detailEntity);
                        }
                    }
                } catch (HibernateException e) {
                    logger.error("[KoreksiBoImpl.saveEditKoreksi] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data MappingJurnal, please info to your admin..." + e.getMessage());
                }
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("param_id", bean.getKoreksiId());
        }
        /*if (lampiranList != null) {
            for (Lampiran lampiran : lampiranList) {
                ItAkunLampiranEntity lampiranEntity = new ItAkunLampiranEntity();
                try {
                    if (lampiran.getUploadFile() != null && !lampiran.getUploadFile().isEmpty()) {

                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes;
                        decodedBytes = decoder.decodeBuffer(lampiran.getUploadFile());
                        logger.info("Decoded upload data : " + decodedBytes.length);
                        String potNama = lampiran.getNamaLampiran().replace(" ", "");
                        if (potNama.length() > 20) {
                            potNama = potNama.substring(0, 20);
                        }
                        String randomNumber = "-" + String.valueOf(CommonUtil.getRandomNumberInts(1, 999)) + "-";
                        String fileName = potNama + randomNumber + dateFormater("dd") + dateFormater("MM") + dateFormater("yy") + ".png";
                        String folder = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_LAMPIRAN;
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_LAMPIRAN + fileName;
                        Path path = Paths.get(folder);
                        if (!Files.exists(path)) {
                            File file = new File(folder);
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
                            lampiranEntity.setUrl(fileName);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String lampiranId = bean.getKoreksiId();
                lampiranEntity.setLampiranId(lampiranId);
                lampiranEntity.setNamaLaporan(lampiran.getNamaLampiran());
                lampiranEntity.setTransaksiId(bean.getKoreksiId());

                lampiranEntity.setFlag(bean.getFlag());
                lampiranEntity.setAction(bean.getAction());
                lampiranEntity.setCreatedWho(bean.getCreatedWho());
                lampiranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                lampiranEntity.setCreatedDate(bean.getCreatedDate());
                lampiranEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    lampiranDao.updateAndSave(lampiranEntity);
                } catch (HibernateException e) {
                    logger.error("[KoreksiBoImpl.saveAddKas] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                }
            }
        }

        List<Lampiran> lampirans = new ArrayList<>();
        lampirans.addAll(lampiranList);
        if (lampiranList != null) {
            for (Lampiran lampiranBaru : lampiranList) {
                ItAkunLampiranEntity lampiranEntityDb = lampiranDao.getById("lampiranId", lampiranBaru.getLampiranId());
                ItAkunLampiranEntity lampiranEntity = null;
                if (lampiranEntityDb != null) {
                    lampirans.remove(lampiranBaru);
                }
                try {
                    if (lampiranBaru.getUploadFile() != null && !lampiranBaru.getUploadFile().isEmpty()) {
                        String url = CommonUtil.uploadImage(lampiranBaru);
                        if(url != null){
                            lampiranEntity.setUrl(url);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!lampiranBaru.getLampiranId().equalsIgnoreCase(lampiranEntityDb.getLampiranId()) ){
                    String lampiranId = lampiranDao.getNextLampiranId();
                    lampiranEntity = new ItAkunLampiranEntity();
                    lampiranEntity.setLampiranId(lampiranId);
                    lampiranEntity.setNamaLaporan(lampiranBaru.getNamaLampiran());
                    lampiranEntity.setTransaksiId(bean.getKoreksiId());

                    lampiranEntity.setFlag(bean.getFlag());
                    lampiranEntity.setAction(bean.getAction());
                    lampiranEntity.setCreatedWho(bean.getCreatedWho());
                    lampiranEntity.setLastUpdateWho(userLogin);
                    lampiranEntity.setCreatedDate(bean.getCreatedDate());
                    lampiranEntity.setLastUpdate(updateTime);
                }


                try {
                    // insert into database
                    if(lampiranEntity != null) {
                        lampiranDao.updateAndSave(lampiranEntity);
                    }
                } catch (HibernateException e) {
                    logger.error("[KoreksiBoImpl.saveEditKoreksi] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                }
            }

            if (lampirans.size() > 0) {
                Iterator it = lampirans.iterator();
                while (it.hasNext()) {
                    Lampiran lampiranLama = (Lampiran) it.next();
                    ItAkunLampiranEntity itAkunLampiranEntity = null;
                    try {
                        itAkunLampiranEntity = lampiranDao.getById("lampiranId", lampiranLama.getLampiranId());
                    } catch (Exception e) {
                        logger.error("[KoreksiBoImpl.saveEditKoreksi] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                    }
                    if (itAkunLampiranEntity != null) {
                        itAkunLampiranEntity.setFlag("N");
                        itAkunLampiranEntity.setLastUpdate(updateTime);
                        itAkunLampiranEntity.setLastUpdateWho(userLogin);
                        try {
                            lampiranDao.updateAndSave(itAkunLampiranEntity);
                        } catch (Exception e) {
                            logger.error("[KoreksiBoImpl.saveEditKoreksi] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }
        }*/


        // mungkin pakai yang ini
        for (Lampiran lampiranEntity : lampiranList) {
            ItAkunLampiranEntity itLampiranEntity = lampiranDao.getById("lampiranId", lampiranEntity.getLampiranId());
            // update or Delete
            if (itLampiranEntity != null) {
                itLampiranEntity.setNamaLaporan(lampiranEntity.getNamaLampiran());
                itLampiranEntity.setFlag(lampiranEntity.getFlag());
                itLampiranEntity.setAction(bean.getAction());
                itLampiranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    String url = CommonUtil.uploadImage(lampiranEntity);
                    if(url != null) {
                        itLampiranEntity.setUrl(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                }
                itLampiranEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // update into database
                    lampiranDao.updateAndSave(itLampiranEntity);
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                }
            }
            else {
//                    create baru
                String lampiranId = lampiranDao.getNextLampiranId();
                itLampiranEntity = new ItAkunLampiranEntity();
                itLampiranEntity.setLampiranId(lampiranId);
                itLampiranEntity.setTransaksiId(lampiranEntity.getTransaksiId());
                itLampiranEntity.setFlag(lampiranEntity.getFlag());
                itLampiranEntity.setNamaLaporan(lampiranEntity.getNamaLampiran());
                itLampiranEntity.setFlag(lampiranEntity.getFlag());
                itLampiranEntity.setAction(lampiranEntity.getAction());
                itLampiranEntity.setCreatedDate(lampiranEntity.getCreatedDate());
                itLampiranEntity.setCreatedWho(lampiranEntity.getCreatedWho());
                try {
                    String url = CommonUtil.uploadImage(lampiranEntity);
                    if(url!=null){
                        itLampiranEntity.setUrl(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                }
                try {
                    // insert into database
                    if(itLampiranEntity.getUrl() != null){
                        lampiranDao.addAndSave(itLampiranEntity);
                    }
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[KoreksiBoImpl.saveEditKoreksi] end process <<<");
        return bean;
    }

    @Override
    public List<KoreksiDetail> getKoreksiDetail(String koreksiId) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getKoreksiDetail] start process >>>");
        List<KoreksiDetail> listOfResult = new ArrayList<>();

        List<ItAkunKoreksiDetailEntity> koreksiDetailList;
        try {

            koreksiDetailList = koreksiDetailDao.getByAkunKoreksiId(koreksiId);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.getKoreksiDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by kas id, please info to your admin..." + e.getMessage());
        }

        if (koreksiDetailList != null) {
            KoreksiDetail returnKasDetail;
            // Looping from dao to object and save in collection
            for (ItAkunKoreksiDetailEntity akunKoreksiDetail : koreksiDetailList) {
                returnKasDetail = new KoreksiDetail();
                returnKasDetail.setKoreksiDetailId(akunKoreksiDetail.getKoreksiDetailId());
                returnKasDetail.setMasterId(akunKoreksiDetail.getKodeVendor());
                returnKasDetail.setDivisiId(akunKoreksiDetail.getDivisiId());


                List<ImPosition> positionList = positionDao.getListPositionKodering(akunKoreksiDetail.getDivisiId() != null ? akunKoreksiDetail.getDivisiId() : "");
                if (positionList.size() != 0) {
                    for (ImPosition position : positionList) {
                        returnKasDetail.setDivisiName(position.getPositionName());
                    }
                } else {
                    returnKasDetail.setDivisiName("");
                }

                String posisi = akunKoreksiDetail.getDivisiId().isEmpty() ? "K" : "D";
                String namaCoa = kodeRekeningDao.getNamaRekeningByCoa(akunKoreksiDetail.getKodeCoa());
                returnKasDetail.setNamaCoa(namaCoa);
                returnKasDetail.setKodeCoa(akunKoreksiDetail.getKodeCoa());
                returnKasDetail.setNoNota(akunKoreksiDetail.getNoNota());
                returnKasDetail.setPosisiCoa(posisi); //unutk Koreksi
                returnKasDetail.setStJumlahPembayaran(CommonUtil.numbericFormat(akunKoreksiDetail.getJumlahPembayaran(), "###,###"));

                /*
                if (akunKoreksiDetail.getPph()!=null){
                    returnKasDetail.setStPph(CommonUtil.numbericFormat(akunKoreksiDetail.getPph(),"###,###"));
                }
                if (akunKoreksiDetail.getPpn()!=null){
                    returnKasDetail.setStPpn(CommonUtil.numbericFormat(akunKoreksiDetail.getPpn(),"###,###"));
                }

                if (akunKoreksiDetail.getNoFakturPajak()!=null){
                    returnKasDetail.setNoFakturPajak(akunKoreksiDetail.getNoFakturPajak());
                    returnKasDetail.setUrlFakturImage(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK+akunKoreksiDetail.getUrlFakturImage());
                }else{
                    returnKasDetail.setNoFakturPajak("");
                    returnKasDetail.setUrlFakturImage("");
                }*/

                listOfResult.add(returnKasDetail);
            }
        }
        logger.info("[KoreksiBoImpl.getKoreksiDetail] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Lampiran> getLampiranList(String kasId) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getLampiranList] start process >>>");
        List<Lampiran> lampiranList = new ArrayList<>();

        List<ItAkunLampiranEntity> lampiranEntityList;
        try {

            lampiranEntityList = lampiranDao.getByTransaksiId(kasId);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.getLampiranList] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get lampiran, please info to your admin..." + e.getMessage());
        }

        if (lampiranEntityList != null) {
            // Looping from dao to object and save in collection
            for (ItAkunLampiranEntity data : lampiranEntityList) {
                Lampiran returnLampiran = new Lampiran();
                returnLampiran.setNamaLampiran(data.getNamaLaporan());
                returnLampiran.setUploadFile(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_LAMPIRAN + data.getUrl());
                lampiranList.add(returnLampiran);
            }
        }
        logger.info("[KoreksiBoImpl.getLampiranList] end process <<<");

        return lampiranList;
    }

    @Override
    public void approveKoreksi(Koreksi bean) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.approveKas] start process >>>");
        if (bean != null) {
            ItAkunKoreksiEntity itAkunKoreksiEntity = null;
            try {
                // Get data from database by ID
                itAkunKoreksiEntity = koreksiDao.getById("koreksiId", bean.getKoreksiId());
            } catch (HibernateException e) {
                logger.error("[KoreksiBoImpl.approveKas] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Kas by Kode Kas, please inform to your admin...," + e.getMessage());
            }
            if (itAkunKoreksiEntity != null) {
                if (bean.getApprovalKeuanganFlag() != null) {
                    itAkunKoreksiEntity.setApprovalKeuanganFlag(bean.getApprovalKeuanganFlag());
                    itAkunKoreksiEntity.setApprovalKeuanganId(bean.getApprovalKeuanganId());
                    itAkunKoreksiEntity.setApprovalKeuanganName(bean.getApprovalKeuanganName());
                    itAkunKoreksiEntity.setApprovalKeuanganDate(bean.getApprovalKeuanganDate());

                    if ("Y".equalsIgnoreCase(bean.getApprovalKeuanganFlag())) {
                        itAkunKoreksiEntity.setApprovalKasubKeuanganFlag(null);
                        itAkunKoreksiEntity.setApprovalKasubKeuanganId(null);
                        itAkunKoreksiEntity.setApprovalKasubKeuanganName(null);
                        itAkunKoreksiEntity.setApprovalKasubKeuanganDate(null);
                    }

                    //mengirim notif ke kasub keuangan
                    if (itAkunKoreksiEntity.getApprovalKasubKeuanganFlag() == null) {
                        userDao.getUserByBranchAndRole(itAkunKoreksiEntity.getBranchId(), CommonConstant.ROLE_ID_KASUB_KEU);
                    }

                } else if (bean.getApprovalKasubKeuanganFlag() != null) {
                    itAkunKoreksiEntity.setApprovalKasubKeuanganFlag(bean.getApprovalKasubKeuanganFlag());
                    itAkunKoreksiEntity.setApprovalKasubKeuanganId(bean.getApprovalKasubKeuanganId());
                    itAkunKoreksiEntity.setApprovalKasubKeuanganName(bean.getApprovalKasubKeuanganName());
                    itAkunKoreksiEntity.setApprovalKasubKeuanganDate(bean.getApprovalKasubKeuanganDate());

                    if ("N".equalsIgnoreCase(bean.getApprovalKasubKeuanganFlag())) {
                        itAkunKoreksiEntity.setApprovalKeuanganFlag(null);
                        itAkunKoreksiEntity.setApprovalKeuanganId(null);
                        itAkunKoreksiEntity.setApprovalKeuanganName(null);
                        itAkunKoreksiEntity.setApprovalKeuanganDate(null);
                    }

                    if ("Y".equalsIgnoreCase(bean.getApprovalKasubKeuanganFlag())) {
                        userDao.getUserByBranchAndRole(itAkunKoreksiEntity.getBranchId(), CommonConstant.ROLE_ID_KA_KEU);
                    }
                }

                itAkunKoreksiEntity.setAction(bean.getAction());
                itAkunKoreksiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKoreksiEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    koreksiDao.updateAndSave(itAkunKoreksiEntity);
                } catch (HibernateException e) {
                    logger.error("[KoreksiBoImpl.approveKas] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KoreksiBoImpl.approveKas] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }
        }
        logger.info("[KoreksiBoImpl.approveKas] end process <<<");
    }

    @Override
    public void postingJurnal(Koreksi bean) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.postingJurnal] start process >>>");
        if (bean != null) {
            ItAkunKoreksiEntity itAkunKoreksiEntity;
            try {
                // Get data from database by ID
                itAkunKoreksiEntity = koreksiDao.getById("koreksiId", bean.getKoreksiId());
            } catch (HibernateException e) {
                logger.error("[KoreksiBoImpl.postingJurnal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Kas by Kode Kas, please inform to your admin...," + e.getMessage());
            }
            if (itAkunKoreksiEntity != null) {
                itAkunKoreksiEntity.setRegisteredFlag("Y");
                itAkunKoreksiEntity.setRegisteredDate(bean.getRegisteredDate());
                itAkunKoreksiEntity.setRegisteredWho(bean.getLastUpdateWho());

                itAkunKoreksiEntity.setFlag(bean.getFlag());
                itAkunKoreksiEntity.setAction(bean.getAction());
                itAkunKoreksiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKoreksiEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    koreksiDao.updateAndSave(itAkunKoreksiEntity);
                } catch (HibernateException e) {
                    logger.error("[KoreksiBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }

                //update flag jurnal telah posting
                ItJurnalEntity jurnalEntity = jurnalDao.getById("noJurnal", itAkunKoreksiEntity.getNoJurnal());
                jurnalEntity.setRegisteredDate(CommonUtil.convertTimestampToDate(bean.getRegisteredDate()));
                jurnalEntity.setRegisteredUser(bean.getLastUpdateWho());
                jurnalEntity.setRegisteredFlag("Y");

                jurnalEntity.setFlag(bean.getFlag());
                jurnalEntity.setAction(bean.getAction());
                jurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                jurnalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    jurnalDao.updateAndSave(jurnalEntity);
                } catch (HibernateException e) {
                    logger.error("[KoreksiBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KoreksiBoImpl.postingJurnal] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }
        }
        logger.info("[KoreksiBoImpl.postingJurnal] end process <<<");
    }

    @Override
    public void addPrintCount(String noJurnal) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.addPrintCount] start process >>>");
        ItJurnalEntity jurnalEntity;
        try {
            // Update into database
            jurnalEntity = jurnalDao.getById("noJurnal", noJurnal);
            jurnalEntity.setPrintRegisterCount(jurnalEntity.getPrintRegisterCount().add(new BigDecimal(1)));

        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.addPrintCount] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when print data Kas, please info to your admin..." + e.getMessage());
        }

        try {
            // Update into database
            jurnalDao.updateAndSave(jurnalEntity);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.addPrintCount] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when print data Kas, please info to your admin..." + e.getMessage());
        }

        logger.info("[KoreksiBoImpl.postingJurnal] end process <<<");
    }

    @Override
    public String getNamaRekeningKasJurnal(String noJurnal) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getNamaRekeningKasJurnal] start process >>>");
        String rekeningKas = "";
        try {
            // Update into database
            rekeningKas = kodeRekeningDao.getNamaRekeningKasForJurnal(noJurnal);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.getNamaRekeningKasJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get data Kas, please info to your admin..." + e.getMessage());
        }
        logger.info("[KoreksiBoImpl.getNamaRekeningKasJurnal] end process <<<");
        return rekeningKas;
    }

    @Override
    public Koreksi getById(String koreksiId) {
        Koreksi result = null;
        ItAkunKoreksiEntity resultEntity = koreksiDao.getById("koreksiId", koreksiId);
        if (resultEntity != null) {
            result = convertEntityToKoreksi(resultEntity);
        }
        return result;
    }

    @Override
    public ImTransEntity getTipeMaster(String transId) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getTipeMaster] start process >>>");
        ImTransEntity transEntity = new ImTransEntity();
        try {
            transEntity = transDao.getById("transId", transId);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.getTipeMaster] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Kas, please info to your admin..." + e.getMessage());
        }
        logger.info("[KoreksiBoImpl.getTipeMaster] end process <<<");
        return transEntity;
    }

    @Override
    public Trans getDisableTrans(String transId, String coaLawan) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getDisableTrans] start process >>>");
        Trans data = new Trans();
        boolean belumketemu = true;
        List<ImMappingJurnalEntity> mappingJurnalEntityList;
        try {
            do {
                if (coaLawan == null) {
                    belumketemu = false;
                }
                mappingJurnalEntityList = mappingJurnalDao.getListMappingJurnalByTransIdAndKodeRekening(transId, coaLawan);

                if (mappingJurnalEntityList.size() > 0) {
                    for (ImMappingJurnalEntity mappingJurnalEntity : mappingJurnalEntityList) {
                        data.setDivisiId(mappingJurnalEntity.getDivisiId());
                        data.setMasterId(mappingJurnalEntity.getMasterId());
                        data.setNoNota(mappingJurnalEntity.getBukti());
                        data.setBiaya(mappingJurnalEntity.getEditBiaya());
                    }
                    belumketemu = false;
                }
                String rekeningIdParent = kodeRekeningDao.getKodeRekeningParent(coaLawan);
                coaLawan = kodeRekeningDao.getCoaByRekeningId(rekeningIdParent);
            } while (belumketemu);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.getDisableTrans] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
        }

        logger.info("[KoreksiBoImpl.getDisableTrans] end process <<<");
        return data; // ini dapatnya id bukan kode coa - note by aji
    }

    @Override
    public String getPosisiCoaDiMappingJurnal(String transId, String kodeRekening) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getPosisiCoaDiMappingJurnal] start process >>>");
        String posisiCoa = "";
        boolean belumketemu = true;
        List<ImMappingJurnalEntity> mappingJurnalEntityList;
        try {
            do {
                mappingJurnalEntityList = mappingJurnalDao.getListMappingJurnalByTransIdAndKodeRekening(transId, kodeRekening);

                if (mappingJurnalEntityList.size() > 0) {
                    belumketemu = false;
                }
                String rekeningIdParent = kodeRekeningDao.getKodeRekeningParent(kodeRekening);
                kodeRekening = kodeRekeningDao.getCoaByRekeningId(rekeningIdParent);
            } while (belumketemu);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.getPosisiCoaDiMappingJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
        }
        for (ImMappingJurnalEntity mappingJurnalEntity : mappingJurnalEntityList) {
            posisiCoa = mappingJurnalEntity.getPosisi();
        }
        logger.info("[KoreksiBoImpl.getPosisiCoaDiMappingJurnal] end process <<<");
        return posisiCoa;
    }

    @Override
    public List<KoreksiDetail> getSearchNotaKoreksi(String masterId, String transaksiId, String branchId, String divisiId, String coa) throws GeneralBOException {
        logger.info("[KoreksiBoImpl.getSearchNotaKas] start process >>>");
        List<KoreksiDetail> listOfResult = new ArrayList<>();
        String unit = "";
        if ((CommonConstant.BRANCH_KP).equalsIgnoreCase(branchId)) {
            List<ImBranches> branchList = new ArrayList<>();
            branchList = branchDao.getAllBranch();
            String filteredList = branchList.stream().map(ImBranches::getPrimaryKey).toString(); //coba
            System.out.println(filteredList);
            int i = 1;
            for (ImBranches dataUnit : branchList) {
                if (i == 1) {
                    unit = "'" + dataUnit.getPrimaryKey().getId() + "'";
                } else {
                    unit = unit + ",'" + dataUnit.getPrimaryKey().getId() + "'";
                }
                i++;
            }
        } else {
            unit = "'" + branchId + "'";
        }
//        String unit="'"+branchId+"'";

        List<KoreksiDetail> kasDetailList;

        try {
            String tipeJurnal = transDao.getTipeBayarByTransId(transaksiId);
            String rekeningId = kodeRekeningDao.getRekeningIdByCoa(coa);
            kasDetailList = koreksiDao.getSearchNotaKoreksi(masterId, unit, tipeJurnal, divisiId, rekeningId);
        } catch (HibernateException e) {
            logger.error("[KoreksiBoImpl.getSearchNotaKas] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data nota kas, please info to your admin..." + e.getMessage());
        }

        if (kasDetailList != null) {
            KoreksiDetail returnKasDetail;
            // Looping from dao to object and save in collection
            for (KoreksiDetail kasDetail : kasDetailList) {
                returnKasDetail = new KoreksiDetail();
                returnKasDetail.setKodeCoa(kasDetail.getKodeCoa());
                String jumlahPembayaran = kasDetail.getStJumlahPembayaran().replaceAll("[^\\d.]", "");
                returnKasDetail.setStJumlahPembayaran(kasDetail.getStJumlahPembayaran().replace(",", "."));  // perlu dibuat function - note by aji
                returnKasDetail.setStJumlahPembayaran(returnKasDetail.getStJumlahPembayaran().replace(" ", ""));
                returnKasDetail.setStJumlahPembayaran(returnKasDetail.getStJumlahPembayaran().replace("-", ""));

                if (kasDetail.getDivisiId() == null) {
                    returnKasDetail.setDivisiId("");
                } else {
                    returnKasDetail.setDivisiId(kasDetail.getDivisiId());
                }
                if (kasDetail.getNoNota() == null) {
                    returnKasDetail.setNoNota("");
                } else {
                    returnKasDetail.setNoNota(kasDetail.getNoNota());
                }
                if (kasDetail.getMasterId() == null) {
                    returnKasDetail.setMasterId("");
                } else {
                    returnKasDetail.setMasterId(kasDetail.getMasterId());
                }
                listOfResult.add(returnKasDetail);
            }
        }
        logger.info("[KoreksiBoImpl.getSearchNotaKas] end process <<<");

        return listOfResult;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

}
