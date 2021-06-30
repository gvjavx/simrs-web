package com.neurix.simrs.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalPelayananDTO;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.mobileapi.model.*;
import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPasien;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import com.opensymphony.xwork2.ModelDriven;
import io.agora.recording.common.Common;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.kinoroy.expo.push.*;

import java.io.IOException;
import java.util.*;

/**
 * @author gondok
 * Friday, 22/11/19 14:35
 */
public class PelayananController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(PelayananController.class);
    private PelayananMobile model = new PelayananMobile();
    private PelayananBo pelayananBoProxy;
    private AntrianOnlineBo antrianOnlineBoProxy;
    private DokterBo dokterBoProxy;
    private JadwalShiftKerjaBo jadwalShiftKerjaBoProxy;
    private ObatPoliBo  obatPoliBoProxy;
    private JenisObatBo jenisObatBoProxy;
    private BiodataBo biodataBoProxy;
    private UserBo userBoProxy;
    private PaketPeriksaBo paketPeriksaBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private Collection<PelayananMobile> listOfPelayanan = new ArrayList<>();
    private Collection<JenisPeriksaMobile> listOfJenisPeriksa = new ArrayList<>();
    private Collection<JenisObatMobile> listOfJenisObat = new ArrayList<>();
    private Collection<ObatPoliMobile> listOfObat = new ArrayList<>();

    private String tglCheckup;
    private String idPelayanan;
    private String namaPelayanan;
    private String branchId;
    private String flag;
    private String action;
    private String nip;
    private String channelId;
    private String tipePelayanan;
    private String idPasien;

    private String idPaket;

    private Map<String,Object> response = new HashMap<>();

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public PaketPeriksaBo getPaketPeriksaBoProxy() {
        return paketPeriksaBoProxy;
    }

    public void setPaketPeriksaBoProxy(PaketPeriksaBo paketPeriksaBoProxy) {
        this.paketPeriksaBoProxy = paketPeriksaBoProxy;
    }

    public UserBo getUserBoProxy() {
        return userBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public JenisObatBo getJenisObatBoProxy() {
        return jenisObatBoProxy;
    }

    public void setJenisObatBoProxy(JenisObatBo jenisObatBoProxy) {
        this.jenisObatBoProxy = jenisObatBoProxy;
    }

    public Collection<JenisObatMobile> getListOfJenisObat() {
        return listOfJenisObat;
    }

    public void setListOfJenisObat(Collection<JenisObatMobile> listOfJenisObat) {
        this.listOfJenisObat = listOfJenisObat;
    }

    public Collection<JenisPeriksaMobile> getListOfJenisPerika() {
        return listOfJenisPeriksa;
    }

    public void setListOfJenisPerika(Collection<JenisPeriksaMobile> listOfJenisPerika) {
        this.listOfJenisPeriksa = listOfJenisPerika;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) { this.biodataBoProxy = biodataBoProxy;}

    public JenisPriksaPasienBo getJenisPriksaPasienBoProxy() {
        return jenisPriksaPasienBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }

    public AntrianOnlineBo getAntrianOnlineBoProxy() {
        return antrianOnlineBoProxy;
    }

    public void setAntrianOnlineBoProxy(AntrianOnlineBo antrianOnlineBoProxy) {
        this.antrianOnlineBoProxy = antrianOnlineBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public JadwalShiftKerjaBo getJadwalShiftKerjaBoProxy() {
        return jadwalShiftKerjaBoProxy;
    }

    public void setJadwalShiftKerjaBoProxy(JadwalShiftKerjaBo jadwalShiftKerjaBoProxy) {
        this.jadwalShiftKerjaBoProxy = jadwalShiftKerjaBoProxy;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }


    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {this.obatPoliBoProxy = obatPoliBoProxy; }

    public void setModel(PelayananMobile model) {
        this.model = model;
    }

    public Collection<PelayananMobile> getListOfPelayanan() {
        return listOfPelayanan;
    }

    public void setListOfPelayanan(Collection<PelayananMobile> listOfPelayanan) {
        this.listOfPelayanan = listOfPelayanan;
    }

    public String getTglCheckup() {
        return tglCheckup;
    }

    public void setTglCheckup(String tglCheckup) {
        this.tglCheckup = tglCheckup;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public Object getModel() {
       switch (action) {
           case "show" :
               return listOfPelayanan;
           case "getListPelayanan" :
               return  listOfPelayanan;
           case "getListApotek" :
               return listOfPelayanan;
           case "getJenisPeriksaPasien" :
               return listOfJenisPeriksa;
           case "getJenisObat" :
               return listOfJenisObat;
           case "getListObat" :
               return listOfObat;
           case "getListPelayananRJ" :
               return listOfPelayanan;
           case "getDetailPaket" :
               return listOfPelayanan;
           default:
               return model;
       }
    }

    public HttpHeaders index() {
        logger.info("[PelayananController.index] start process GET / <<<");

        List<Pelayanan> result = new ArrayList<>();
         try {
            result = pelayananBoProxy.getListAllPelayanan();
         } catch (GeneralBOException e) {
             logger.error("[PelayananContoller.index] Error when get pelayanan",e);

         }

         if (result != null && result.size() > 0) {
             for (Pelayanan item : result) {
                 PelayananMobile pelayananMobile = new PelayananMobile();
                 pelayananMobile.setIdPelayanan(item.getIdPelayanan());
                 pelayananMobile.setNamaPelayanan(item.getNamaPelayanan());

                 listOfPelayanan.add(pelayananMobile);
             }
         }

        logger.info("[PelayananController.create] start process GET / <<<");
        return new DefaultHttpHeaders("index").disableCaching();

    }

    /** edit by fahmi 08 Jun 2021, menambahkan informasi foto dokter saat pengambilan data shift*/
    public HttpHeaders create() {
        logger.info("[PelayananController.create] start process POST / <<<");

        List<Pelayanan> listPelayanan = new ArrayList<>();
        List<Dokter> listDokter = new ArrayList<>();
        List<AntianOnline> listAntianOnline = new ArrayList<>();
        List<JadwalPelayananDTO> listJadwalPelayananDTO = new ArrayList<>();

        if (action.equalsIgnoreCase("show")) {
            try {
                listJadwalPelayananDTO = jadwalShiftKerjaBoProxy.getJadwalPelayanan(idPelayanan, "", branchId, nip, CommonUtil.convertStringToDate(tglCheckup));
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
//                    logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "registrasi online index");
                } catch (GeneralBOException el) {
                    logger.error("Pelayanan.create] Error when get jumlah antrian",e);

                }
            }

            int i = 0;

            for(JadwalPelayananDTO item : listJadwalPelayananDTO) {
                PelayananMobile result = new PelayananMobile();
                result.setIdPelayananApi(String.valueOf(i));
                result.setIdDokter(item.getIdDokter());
                result.setIdPelayanan(item.getIdPelayanan());
                result.setIdSpesialis(item.getIdSpesialis());
                result.setFotoDokter(item.getUrlImg());
                result.setNamaDokter(item.getNamaDokter());
                result.setNamaPelayanan(item.getNamaPelayanan());
                result.setNamaSpesialis(item.getNamaSpesialis());
                result.setJamAkhir(item.getJamAkhir());
                result.setJamAwal(item.getJamAwal());
                result.setStTanggal(item.getTanggal().toString());
                result.setBranchId(item.getBranchId());
                result.setBranchName(item.getBranchName());
                result.setKuota(item.getKuota());
                result.setFlagLibur(item.getFlagLibur());

                try {
                    listAntianOnline = antrianOnlineBoProxy.getAntrianByCriteria(item.getIdPelayanan(), item.getIdDokter(), "", CommonUtil.convertStringToDate(tglCheckup), item.getJamAwal(), item.getJamAkhir(), branchId, "");
                } catch (GeneralBOException e) {
                    logger.error("Pelayanan.create] Error when get jumlah antrian",e);
                    e.printStackTrace();
                }

                if (listAntianOnline.size() > 0) {
                    result.setJumlahAntrian(listAntianOnline.get(0).getJumlahAntrian());
                } else result.setJumlahAntrian("0");

                listOfPelayanan.add(result);

                i++;
            }
        }
        else if (action.equalsIgnoreCase("detail")) {
            try {
                listDokter = dokterBoProxy.getByIdPelayanan(idPelayanan, branchId);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
//                        logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "registrasi online index");
                } catch (GeneralBOException el) {

                }
            }
        }
        else if  (action.equalsIgnoreCase("getListPelayanan")){
            List<Pelayanan> result = new ArrayList<>();

//            Pelayanan bean = new Pelayanan();
//            bean.setTipePelayanan(tipePelayanan);
//            bean.setBranchId(branchId);

            try {
                result = pelayananBoProxy.getListPelayananTelemedic(branchId);

            } catch (GeneralBOException e) {
                logger.error("Pelayanan.create] Error when get list pelayanan",e);
            }

            for (Pelayanan item : result){
                PelayananMobile pelayananMobile = new PelayananMobile();
                pelayananMobile.setNamaPelayanan(item.getNamaPelayanan());
                pelayananMobile.setIdPelayanan(item.getIdPelayanan());

                listOfPelayanan.add(pelayananMobile);
            }
        }
        else if  (action.equalsIgnoreCase("getUsername")) {

            User user = new User();

            try {
               user = userBoProxy.getUserByIdPelayanan(idPelayanan);
            } catch (GeneralBOException e){
                logger.error("Pelayanan.create] Error when get list pelayanan",e);
            }

            model.setUsername(user.getUsername());
            model.setUserId(user.getUserId());
        }
        else if  (action.equalsIgnoreCase("getListApotek")) {

            List<Pelayanan> result = new ArrayList<>();

            try {
                result = pelayananBoProxy.getListApotek(branchId, "apotek");

            } catch (GeneralBOException e) {
                logger.error("Pelayanan.create] Error when get list apotek",e);
            }

            for (Pelayanan item: result){
                PelayananMobile pelayananMobile = new PelayananMobile();
                pelayananMobile.setNamaPelayanan(item.getNamaPelayanan());
                pelayananMobile.setIdPelayanan(item.getIdPelayanan());

                listOfPelayanan.add(pelayananMobile);
            }
        }
        // Add by Fahmi, untuk ambil list obat.
        else if (action.equalsIgnoreCase("getListObat"))
        {
            listOfObat = new ArrayList<>();
            List<ObatPoli> obatPoli = null;
            String msg = null;
            try {
                obatPoli = obatPoliBoProxy.getListObatGroupPoli(getIdPelayanan(), getBranchId(), null, null, idPasien);
            }
            catch (GeneralBOException e)
            {
                logger.error("[Pelayanan.getListObat] Error when get list obat");
                msg = e.getMessage();
            }

            // for handling message if error that just happen
            if(null!=msg)
            {
                ObatPoliMobile temp = new ObatPoliMobile();
                temp.setMessage(msg);
                listOfObat.add(temp);
            }
            else if (null != obatPoli && obatPoli.size() > 0)
            {
                ObatPoliMobile temp;
                for (ObatPoli a : obatPoli)
                {
                    temp = new ObatPoliMobile();

                    temp.setIdObat(a.getIdObat());
                    temp.setIdPelayanan(a.getIdPelayanan());
                    temp.setQty(String.valueOf(a.getQty()));
                    temp.setFlag(a.getFlag());
                    temp.setAction(a.getAction());
                    //temp.setCreatedWho(a.getCreatedWho());
                    //temp.setCreatedDate(a.getCreatedDate().toString());
                    //temp.setLastUpdateWho(a.getLastUpdateWho());
                    //temp.setLastUpdate(a.getLastUpdate().toString());

                    temp.setBranchId(a.getBranchId());

                    temp.setNamaPelayanan(a.getNamaPelayanan());

                    temp.setQtyBox(String.valueOf(a.getQtyBox()));
                    temp.setQtyLembar(String.valueOf(a.getQtyLembar()));
                    temp.setQtyBiji(String.valueOf(a.getQtyBiji()));
                    temp.setBijiPerLembar(String.valueOf(a.getBijiPerLembar()));
                    temp.setIdBarang(a.getIdBarang());
                    temp.setExp(a.getExp());
                    temp.setIdPabrik(a.getIdPabrik());
                    temp.setExpiredDate(null!=a.getExpiredDate()?CommonUtil.convertDateToString(a.getExpiredDate()):"");
                    temp.setFlagKronis(a.getFlagKronis());

                    temp.setNamaObat(a.getNamaObat());
                    temp.setIdJenisObat(a.getIdJenisObat());
                    listOfObat.add(temp);
                }
            }

        }
        else if (action.equalsIgnoreCase("getJenisObat")) {

            List<JenisObat> result = new ArrayList<>();
            JenisObat bean = new JenisObat();

            try {
                result = jenisObatBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("Pelayanan.create] Error when get list apotek",e);
            }

            for (JenisObat item : result){
                JenisObatMobile jenisObatMobile = new JenisObatMobile();
                jenisObatMobile.setIdJenisObat(item.getIdJenisObat());
                jenisObatMobile.setNamaJenisObat(item.getNamaJenisObat());

                listOfJenisObat.add(jenisObatMobile);
            }
        }
        else if  (action.equalsIgnoreCase("getJenisPeriksaPasien")) {
            List<JenisPriksaPasien> result = new ArrayList<>();
            PaketPasien paketPasien = new PaketPasien();

            JenisPriksaPasien bean = new JenisPriksaPasien();

            try {
               paketPasien = paketPeriksaBoProxy.checkPaketPasien(idPasien);
            } catch (GeneralBOException e) {
                logger.error("Pelayanan.create] Error when get list jenis periksa pasien",e);
                throw new GeneralBOException("Pelayanan.create] Error when get list jenis periksa pasien",e);
            }

            if (paketPasien != null) {
                try {
                    result = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(bean);
                } catch (GeneralBOException e){
                    logger.error("Pelayanan.create] Error when get list jenis periksa pasien",e);
                }
            } else {
                try {
                    result = jenisPriksaPasienBoProxy.getJenisPeriksaExecMCU();
                } catch (GeneralBOException e){
                    logger.error("Pelayanan.create] Error when get list jenis periksa pasien",e);
                }
            }

            for (JenisPriksaPasien item : result){
                JenisPeriksaMobile jenisPeriksaMobile = new JenisPeriksaMobile();
                jenisPeriksaMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                jenisPeriksaMobile.setKeterangan(item.getKeterangan());

                listOfJenisPeriksa.add(jenisPeriksaMobile);
            }
        }
        else if  (action.equalsIgnoreCase(("notif"))) {

            List<String> somePushTokens = Arrays.asList("ExponentPushToken[a5ZnTeN_kK0DmkQT4ibUlP]");
            List<String> ticketId = new ArrayList<>();

            List<Message> messages = new ArrayList<>();
            // You can check whether your push tokens are syntactically valid
            for (String token : somePushTokens) {
                // Each push token looks like ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]
                if (!ExpoPushClient.isExpoPushToken(token)) {
                    logger.error(token + " is not a valid Expo Push Token!");
                }
            }
            for (String token : somePushTokens) {
                // Construct a message
                Message message = new Message.Builder()
                        .to(token)
                        .title("Nyoba")
                        .sound("default")
                        .priority(Priority.HIGH)
                        .channelId(channelId)
                        .body("Oke")
                        .build();
                messages.add(message);
            }
            // The Expo push service accepts batches of messages, no more than 100 at a time.
            // If you know you're sending more than 100 messages,
            // Use ExpoPushClient.chunkItems to get lists of 100 or less items
            List<List<Message>> chunks = ExpoPushClient.chunkItems(messages);

            for (List<Message> chunk : chunks) {
                try {
                    PushTicketResponse response = ExpoPushClient.sendPushNotifications(messages);
                    List<ExpoError> errors = response.getErrors();
                    // If there is an error with the *entire request*:
                    // The errors object will be an list of errors,
                    // (usually just one)
                    if (errors != null) {
                        for (ExpoError error : errors) {
                            // Handle the errors
                        }
                    }
                    // If there are errors that affect individual messages but not the entire request,
                    // errors will be null and each push ticket will individually contain the status
                    // of each message (ok or error)
                    List<PushTicket> tickets = response.getTickets();
                    if (tickets != null) {
                        for (PushTicket ticket : tickets) {
                            // Handle each ticket (namely, check the status, and save the ID!)
                            // NOTE: If a ticket status is error, you can get the specific error
                            // from the details object. You must handle it appropriately.
                            // The error codes are listed in PushError
                            if (ticket.getStatus() == Status.OK) {
                                ticketId.add(ticket.getId());
                                // Save this id somewhere for later
                            } else {
                                // Handle the error
                                PushError e = ticket.getDetails().getError();
                                switch (e) {
                                    case MESSAGE_TOO_BIG:
                                    case INVALID_CREDENTIALS:
                                    case DEVICE_NOT_REGISTERED:
                                    case MESSAGE_RATE_EXCEEDED:
                                }

                            }
                        }
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }

            // Later, you can get the Push Receipts using the ids you saved from above.
            // Usually, the receipts are available within a few seconds, but when Expo is under load,
            // it can take up to 30 min. Push Reciepts are available for at least 1 day

            try {
                PushReceiptResponse response = ExpoPushClient.getPushReciepts(ticketId);
                Map<String, PushReceipt> receipts = response.getReceipts();
                for (String id : ticketId) {
                    PushReceipt rec = receipts.get(id);
                    if (rec != null) {
                        if (rec.getStatus() == Status.OK) {
                            // It's all good!
                        } else {
                            // Handle the error
                            PushError e = rec.getDetails().getError();
                            switch (e) {
                                case MESSAGE_TOO_BIG:
                                case INVALID_CREDENTIALS:
                                case DEVICE_NOT_REGISTERED:
                                case MESSAGE_RATE_EXCEEDED:
                            }
                        }
                    }
                }
            } catch (IOException e) {
                // Handle a network error here
                System.out.println(e.getMessage());
            }
        }
        else if (action.equalsIgnoreCase("getListPelayananRJ")) {
            List<Pelayanan> result = new ArrayList<>();


            try {
                result = pelayananBoProxy.getJustPelayananOnlyRJ(branchId);
            } catch (GeneralBOException e) {
                logger.error("Pelayanan.create] Error when get list pelayanan",e);
            }

            for (Pelayanan item : result){
                PelayananMobile pelayananMobile = new PelayananMobile();
                pelayananMobile.setNamaPelayanan(item.getNamaPelayanan());
                pelayananMobile.setIdPelayanan(item.getIdPelayanan());

                listOfPelayanan.add(pelayananMobile);
            }
        }
        else if (action.equalsIgnoreCase("checkPaketPasien")) {
            PaketPasien paketPasien;
            try {
                paketPasien = paketPeriksaBoProxy.checkPaketPasien(idPasien);
            } catch (GeneralBOException e) {
                logger.error("Pelayanan.create] Error when check paket pasien",e);
                throw new GeneralBOException("Pelayanan.create] Error when check paket pasien",e);
            }

            if (paketPasien != null) {
                model.setIdPaket(paketPasien.getIdPaket());
                model.setIdPelayanan(paketPasien.getIdPelayanan());
                model.setNamaPelayanan(paketPasien.getNamaPelayanan());
                model.setIdPerusahaan(paketPasien.getIdPerusahaan());
                model.setNamaPerusahaan(paketPasien.getNamaPerusahaan());
                model.setIdPasien(paketPasien.getIdPasien());
            }
        }

        if (action.equalsIgnoreCase("getDetailPaket")) {
            List<PaketPeriksa> paketPeriksaList = new ArrayList<>();
            try {
                paketPeriksaList = paketPeriksaBoProxy.getDetailPaketPeriksa(idPaket);
            } catch (GeneralBOException e) {
                logger.error("Pelayanan.create] Error when check paket pasien",e);
                throw new GeneralBOException("Pelayanan.create] Error when check paket pasien",e);
            }

            if (paketPeriksaList.size() > 0 ) {
              for (PaketPeriksa item : paketPeriksaList) {
                  PelayananMobile pelayananMobile = new PelayananMobile();
                  pelayananMobile.setIdPaket(item.getIdPaket());
                  pelayananMobile.setIdItem(item.getIdItem());
                  pelayananMobile.setNamaItem(item.getNamaItem());
                  pelayananMobile.setJenisItem(item.getJenisItem());
                  pelayananMobile.setTarifAwal(CommonUtil.numbericFormat(item.getTarifAwal(),"###,###"));
                  pelayananMobile.setTarif(CommonUtil.numbericFormat(item.getTarif(), "###,###"));

                  listOfPelayanan.add(pelayananMobile);
              }
            }
        }

        logger.info("[PelayananController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();

    }
}


