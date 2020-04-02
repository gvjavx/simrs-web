package com.neurix.simrs.transaksi.paketperiksa.action;

import com.neurix.akuntansi.master.masterVendor.bo.MasterVendorBo;
import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.*;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPeriksaAction extends BaseTransactionAction {

    public static transient Logger logger = Logger.getLogger(PaketPeriksaAction.class);

    private PaketPeriksaBo paketPeriksaBoProxy;
    private MasterVendorBo masterVendorBoProxy;
    private PaketPeriksa paketPeriksa;


    public List<ImSimrsKelasPaketEntity> getListKelasPaket(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        PaketPeriksa paketPeriksa = new PaketPeriksa();

        return paketPeriksaBo.getListEntityKelasPaket(paketPeriksa);
    }

    public List<MasterVendor> getListMasterVendor(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MasterVendorBo masterVendorBo = (MasterVendorBo) ctx.getBean("masterVendorBoProxy");

        MasterVendor masterVendor = new MasterVendor();
        masterVendor.setFlag("Y");

        return masterVendorBo.getByCriteria(masterVendor);
    }

    public List<PaketPeriksa> getListPaketPeriksa(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        List<PaketPeriksa> list = new ArrayList<>();
        PaketPeriksa periksa = new PaketPeriksa();
        periksa.setFlag("Y");
        periksa.setBranchId(CommonUtil.userBranchLogin());

        try {
            list = paketPeriksaBo.getListPaketPeriksa(periksa);
        }catch (GeneralBOException e){
            logger.error("Found Error "+e.getMessage());
        }

        return list;
    }

    public CrudResponse savePaket(String idPelayanan, String namaPaket, BigDecimal tarifPaket, String jsonString) throws JSONException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        CrudResponse response = new CrudResponse();

        JSONArray json = new JSONArray(jsonString);
        List<MtSimrsItemPaketEntity> itemPaketEntities = new ArrayList<>();
        MtSimrsItemPaketEntity itemPaketEntity;
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            itemPaketEntity = new MtSimrsItemPaketEntity();
            itemPaketEntity.setIdItem(obj.getString("id_item"));
            itemPaketEntity.setIdKategoriItem(obj.getString("kategori_item"));
            itemPaketEntity.setJenisItem(obj.getString("jenis_item"));
            itemPaketEntities.add(itemPaketEntity);
        }

        MtSimrsPaketEntity paketPeriksa = new MtSimrsPaketEntity();
        paketPeriksa.setNamaPaket(namaPaket);
        paketPeriksa.setTarif(tarifPaket);
        paketPeriksa.setFlag("Y");
        paketPeriksa.setAction("C");
        paketPeriksa.setBranchId(CommonUtil.userBranchLogin());
        paketPeriksa.setCreatedDate(time);
        paketPeriksa.setCreatedWho(userLogin);
        paketPeriksa.setLastUpdate(time);
        paketPeriksa.setLastUpdateWho(userLogin);
        paketPeriksa.setIdPelayanan(idPelayanan);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        try {
            response = paketPeriksaBo.savePaketPeriksa(paketPeriksa, itemPaketEntities);
        } catch (GeneralBOException e){

        }

        return response;
    }

    public void setMasterVendorBoProxy(MasterVendorBo masterVendorBoProxy) {
        this.masterVendorBoProxy = masterVendorBoProxy;
    }

    @Override
    public String search() {

        PaketPeriksa paketPeriksa = getPaketPeriksa();
        paketPeriksa.setBranchId(CommonUtil.userBranchLogin());
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PaketPeriksa> list = new ArrayList<>();

        try {
            list = paketPeriksaBoProxy.getListPaketPeriksa(paketPeriksa);
        }catch (GeneralBOException e){
            logger.error("Found Error when search paket "+e.getMessage());
        }

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult",list);
        return "search";

    }

    public String searchDaftarPaket(){

        PaketPeriksa paketPeriksa = getPaketPeriksa();
        paketPeriksa.setBranchId(CommonUtil.userBranchLogin());
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PaketPeriksa> list = new ArrayList<>();

        try {
            list = paketPeriksaBoProxy.getListDaftarPaketPasien(paketPeriksa);
        }catch (GeneralBOException e){
            logger.error("Found Error when search paket "+e.getMessage());
        }

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult",list);
        return "search";
    }

    @Override
    public String initForm() {
        PaketPeriksa paketPeriksa = new PaketPeriksa();
        setPaketPeriksa(paketPeriksa);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public String add(){
        PaketPeriksa paketPeriksa = new PaketPeriksa();
        setPaketPeriksa(paketPeriksa);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "init_add";
    }

    public List<KategoriTindakan> getListKategoriTindakan(String idPelayanan){
        List<KategoriTindakan> tindakanList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KategoriTindakanBo kategoriTindakanBo = (KategoriTindakanBo) ctx.getBean("kategoriTindakanBoProxy");

        if(idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)){
            try {
                tindakanList = kategoriTindakanBo.getListKategoriTindakan(idPelayanan);
            }catch (GeneralBOException e){
               logger.error("Found error "+e.getMessage());
            }
        }
        return tindakanList;
    }

    public Pasien cekRMPasien(String nik, String nama, String jenisKelamin){

        Pasien pasien = new Pasien();

        if(nik != null && !"".equalsIgnoreCase(nik)){

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
            PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");
            Boolean pasienDaftarPaket = false;

            List<Pasien> list = new ArrayList<>();
            Pasien dataPasien = new Pasien();
            dataPasien.setNoKtp(nik);

            try {
               list = pasienBo.getByCriteria(dataPasien);
            }catch (GeneralBOException e){
                logger.error("Found Error "+e.getMessage());
            }

            Pasien pas = new Pasien();

            if(list.size() > 0){

                pas = list.get(0);

                try {
                    pasienDaftarPaket = paketPeriksaBo.cekPaketWithIdPasien(pas.getIdPasien());
                }catch (GeneralBOException e){
                    logger.error("Found Error "+e.getMessage());
                }

                if(pasienDaftarPaket){
                    pasien.setStatus("error");
                    pasien.setMsg("No RM "+pas.getIdPasien() +" Sudah terdaftar sebagai pasien paket..!");
                }else{
                    pasien = list.get(0);
                }

            }else{
                pasien = new Pasien();
            }
        }
        return pasien;
    }

    public CheckResponse savePaketPasien(String jsonString) throws JSONException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        CheckResponse response = new CheckResponse();

        JSONArray json = new JSONArray(jsonString);

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            PaketPasien paketPasien = new PaketPasien();
            paketPasien.setIdPasien(obj.getString("id_pasien"));
            paketPasien.setIdPaket(obj.getString("id_paket"));
            paketPasien.setIdPerusahaan(obj.getString("id_perusahaan"));
            paketPasien.setCreatedDate(time);
            paketPasien.setCreatedWho(userLogin);
            paketPasien.setLastUpdate(time);
            paketPasien.setLastUpdateWho(userLogin);

            try {
                response = paketPeriksaBo.savePaketPasien(paketPasien);
            } catch (GeneralBOException e){
                response.setStatus("Error");
                response.setMessage("Found Error "+e.getMessage()+" "+response.getMessage());
                return response;
            }
        }

        return response;
    }

    public Pasien saveNewPasien(String jsonString) throws JSONException, IOException {

        Pasien pasien = new Pasien();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        JSONObject obj = new JSONObject(jsonString);

        if (obj != null){

            Pasien dataPasien = new Pasien();
            dataPasien.setNoKtp(obj.getString("nik"));
            dataPasien.setNama(obj.getString("nama"));
            dataPasien.setJenisKelamin(obj.getString("jk"));
            dataPasien.setTempatLahir(obj.getString("tempat_lahir"));
            dataPasien.setTglLahir(obj.getString("tanggal_lahir"));
            dataPasien.setAgama(obj.getString("agama"));
            dataPasien.setProfesi(obj.getString("profesi"));
            dataPasien.setSuku(obj.getString("suku"));
            dataPasien.setAlamat(obj.getString("alamat"));
            dataPasien.setDesaId(obj.getString("desa_id"));
            dataPasien.setCreatedDate(time);
            dataPasien.setCreatedWho(userLogin);
            dataPasien.setLastUpdate(time);
            dataPasien.setLastUpdateWho(userLogin);

            if(obj.getString("img_ktp") != null && !"".equalsIgnoreCase(obj.getString("img_ktp"))){
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("img_ktp"));
                logger.info("Decoded upload data : " + decodedBytes.length);
                String fileName = dataPasien.getNoKtp()+"-"+dateFormater("MM")+dateFormater("yy")+".png";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_KTP_PASIEN+fileName;
                logger.info("File save path : " + uploadFile);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                if (image == null) {
                    logger.error("Buffered Image is null");
                }else{
                    File f = new File(uploadFile);
                    // write the image
                    ImageIO.write(image, "png", f);
                    dataPasien.setUrlKtp(fileName);
                }
            }

            try {
                pasien = pasienBo.saveAddWithResponse(dataPasien);
            }catch (GeneralBOException e){
                pasien.setStatus("error");
                pasien.setMsg("Error "+e.getMessage());
                logger.error("Found Error "+e.getMessage());
            }
        }

        return pasien;
    }

    public List<PaketPeriksa> detailDaftarPaketPasien(String idPaket, String idPerusahaan) throws JSONException{

        List<PaketPeriksa> list = new ArrayList<>();
        String branchId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        if( idPaket != null && !"".equalsIgnoreCase(idPaket)
            && idPerusahaan != null && !"".equalsIgnoreCase(idPerusahaan)){

            try {
                list = paketPeriksaBo.getListDetailDaftarPaketPasien(idPaket, idPerusahaan, branchId);
            }catch (GeneralBOException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return list;
    }

    public List<PaketPeriksa> detailPaket(String idPaket) throws JSONException{

        List<PaketPeriksa> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        if( idPaket != null && !"".equalsIgnoreCase(idPaket)){
            try {
                list = paketPeriksaBo.getDetailPaket(idPaket);
            }catch (GeneralBOException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return list;
    }

    public List<PaketPeriksa> detailItem(String idLab, String idPaket) throws JSONException{

        List<PaketPeriksa> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        if(idPaket != null && !"".equalsIgnoreCase(idPaket) && idLab != null && !"".equalsIgnoreCase(idLab)){
            try {
                list = paketPeriksaBo.getDetailItemPaket(idLab, idPaket);
            }catch (GeneralBOException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return list;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public void setPaketPeriksaBoProxy(PaketPeriksaBo paketPeriksaBoProxy) {
        this.paketPeriksaBoProxy = paketPeriksaBoProxy;
    }

    public PaketPeriksa getPaketPeriksa() {
        return paketPeriksa;
    }

    public void setPaketPeriksa(PaketPeriksa paketPeriksa) {
        this.paketPeriksa = paketPeriksa;
    }
}
