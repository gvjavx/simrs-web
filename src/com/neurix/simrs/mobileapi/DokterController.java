package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.mobileapi.model.DokterMobile;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.batik.dom.GenericElement;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Thursday, 23/01/20 14:40
 */
public class DokterController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(DokterController.class);
    private DokterMobile model = new DokterMobile();
    private Collection<DokterMobile> listOfDokter;
    private DokterBo dokterBoProxy;
    private TeamDokterBo teamDokterBoProxy;
    private BiodataBo biodataBoProxy;
    private TelemedicBo telemedicBoProxy;
    private PelayananBo pelayananBoProxy;
    private RawatInapBo rawatInapBoProxy;
    private CheckupBo checkupBoProxy;

    private String idDokter;
    private String namaDokter;
    private String idSpesialis;
    private String namaSpesialis;
    private String action;
    private String kuota;
    private String kuotaTele;
    private String lat;
    private String lon;
    private String flagCall;
    private String flagTele;

    private String idPelayanan;
    private String branchId;

    private String foto;

    private String idTeamDokter;
    private String keterangan;
    private String flagApprove;

    private String tglAwal;
    private String tglAkhir;

    public String getTglAwal() {
        return tglAwal;
    }

    public void setTglAwal(String tglAwal) {
        this.tglAwal = tglAwal;
    }

    public String getTglAkhir() {
        return tglAkhir;
    }

    public void setTglAkhir(String tglAkhir) {
        this.tglAkhir = tglAkhir;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public RawatInapBo getRawatInapBoProxy() {
        return rawatInapBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlagApprove() {
        return flagApprove;
    }

    public void setFlagApprove(String flagApprove) {
        this.flagApprove = flagApprove;
    }

    public String getIdTeamDokter() {
        return idTeamDokter;
    }

    public void setIdTeamDokter(String idTeamDokter) {
        this.idTeamDokter = idTeamDokter;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public TeamDokterBo getTeamDokterBoProxy() {
        return teamDokterBoProxy;
    }

    public void setTeamDokterBoProxy(TeamDokterBo teamDokterBoProxy) {
        this.teamDokterBoProxy = teamDokterBoProxy;
    }

    public String getKuotaTele() {
        return kuotaTele;
    }

    public void setKuotaTele(String kuotaTele) {
        this.kuotaTele = kuotaTele;
    }

    public TelemedicBo getTelemedicBoProxy() {
        return telemedicBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public BiodataBo getBiodataBoProxy() {
        return biodataBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(DokterMobile model) {
        this.model = model;
    }

    public Collection<DokterMobile> getListOfDokter() {
        return listOfDokter;
    }

    public void setListOfDokter(Collection<DokterMobile> listOfDokter) {
        this.listOfDokter = listOfDokter;
    }

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }

    public String getNamaSpesialis() {
        return namaSpesialis;
    }

    public void setNamaSpesialis(String namaSpesialis) {
        this.namaSpesialis = namaSpesialis;
    }

    public String getFlagTele() {
        return flagTele;
    }

    public void setFlagTele(String flagTele) {
        this.flagTele = flagTele;
    }

    @Override
    public Object getModel() {
        return (listOfDokter != null ? listOfDokter : model);
    }

    public HttpHeaders create() {
        logger.info("[DokterController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<Dokter> result = new ArrayList<>();
        Biodata resultBio = new Biodata();
        List<AntrianTelemedic> resultAntrian = new ArrayList<>();

        Dokter bean = new Dokter();
        bean.setIdDokter(idDokter);
        bean.setIdSpesialis(idSpesialis);
        bean.setNamaDokter(namaDokter);
        bean.setNamaSpesialis(namaSpesialis);

        if (action.equalsIgnoreCase("get")) {

            try {
                result = dokterBoProxy.getDokterById(idDokter);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }

            model.setIdDokter(result.get(0).getIdDokter());
            model.setIdSpesialis(result.get(0).getIdSpesialis());
            model.setNamaDokter(result.get(0).getNamaDokter());
            model.setNamaSpesialis(result.get(0).getNamaSpesialis());
            model.setKuota(result.get(0).getKuota());
            model.setLat(result.get(0).getLat());
            model.setLon(result.get(0).getLon());
            model.setFlagCall(result.get(0).getFlagCall());
            model.setFlagTele(result.get(0).getFlagTele());
            model.setKuotaTele(result.get(0).getKuotaTele());
            model.setIdPelayanan(result.get(0).getIdPelayanan());
            model.setNamaPelayanan(result.get(0).getNamaPelayanan());

            try {
                resultBio = biodataBoProxy.getBiodataByNip(model.getIdDokter());
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }

            if (resultBio != null) {
                model.setFoto(resultBio.getFotoUpload());
            }

        }

        if (action.equalsIgnoreCase("kuota")) {

            try {
                dokterBoProxy.editKuota(idDokter, kuota, kuotaTele);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("saveEditLoc")){
            try {
                dokterBoProxy.editLatLon(idDokter, lat, lon);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if  (action.equalsIgnoreCase("editFlagCall")) {
            try {
                dokterBoProxy.editFlagCall(idDokter, flagCall);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if  (action.equalsIgnoreCase("editFlagTele")) {
            try {
                dokterBoProxy.editFlagTele(idDokter, flagTele);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("getDokterByPelayanan")) {
            listOfDokter = new ArrayList<>();
            try {
               result = dokterBoProxy.getDokterByPelayanan(idPelayanan, null);

               for (Dokter item : result ){
                   DokterMobile dokterMobile = new DokterMobile();
                   dokterMobile.setIdDokter(item.getIdDokter());
                   dokterMobile.setNamaDokter(item.getNamaDokter());
                   dokterMobile.setKuota(item.getKuota());
                   dokterMobile.setFlagTele(item.getFlagTele());
                   dokterMobile.setKuotaTele(item.getKuotaTele());

                   try {
                       resultBio = biodataBoProxy.getBiodataByNip(item.getIdDokter());
                   } catch (GeneralBOException e) {
                       logger.error("[DokterController.create] Error, " + e.getMessage());
                   }

                   if (resultBio != null) {
                       dokterMobile.setFoto(resultBio.getFotoUpload());
                   }

                   AntrianTelemedic beanAntrian = new AntrianTelemedic();
                   beanAntrian.setIdDokter(item.getIdDokter());
                   beanAntrian.setIsMobile("Y");
                   beanAntrian.setFlagDateNow(CommonUtil.convertTimestampToString2(now));
//                   beanAntrian.setCreatedDate(now);

                   try {
                      resultAntrian =  telemedicBoProxy.getSearchByCriteria(beanAntrian);
                   } catch (GeneralBOException e){
                       logger.error("[DokterController.create] Error, " + e.getMessage());
                   }

                   if (resultAntrian != null) {
                       List<AntrianTelemedic> temp = new ArrayList<>();
                       for(AntrianTelemedic antrianTelemedic : resultAntrian ){
                           String dateAntrian = CommonUtil.convertTimestampToString(antrianTelemedic.getCreatedDate());
                           String dateNow = CommonUtil.convertTimestampToString(now);
                           if (dateAntrian.equalsIgnoreCase(dateNow)){
                               temp.add(antrianTelemedic);
                           }
                       }
                       dokterMobile.setJumlahAntrian(String.valueOf(temp.size()));
                   } else  dokterMobile.setJumlahAntrian("0");

                   listOfDokter.add(dokterMobile);
               }
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }
        if (action.equalsIgnoreCase("getDokterByCriteria")){
            listOfDokter = new ArrayList<>();
            Dokter dokter = new Dokter();
            dokter.setIdDokter(idDokter);
            try {
                result = dokterBoProxy.getSearchByCriteria(dokter);
            } catch (GeneralBOException e){
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (Dokter item : result){
                    DokterMobile dokterMobile = new DokterMobile();
                    dokterMobile.setIdDokter(item.getIdDokter());
                    dokterMobile.setNamaDokter(item.getNamaDokter());
                    dokterMobile.setIdPelayanan(item.getIdPelayanan());
                    dokterMobile.setNamaPelayanan(item.getNamaPelayanan());
                    dokterMobile.setKuota(item.getKuota());
                    dokterMobile.setKuotaTele(item.getKuotaTele());
                    dokterMobile.setFlagTele(item.getFlagTele());
                    dokterMobile.setLat(item.getLat());
                    dokterMobile.setLon(item.getLon());

                    listOfDokter.add(dokterMobile);
                }
            }

        }
        if (action.equalsIgnoreCase("getRequestDpjp")) {

            listOfDokter = new ArrayList<>();
            List<DokterTeam> listRequestDokter = new ArrayList<>();
            DokterTeam dpjp1 = null;
            HeaderCheckup detailPasien = new HeaderCheckup();

            Timestamp tsTglAwal = null;
            Timestamp tsTglAkhir = null;
            if (tglAwal != null && !"".equalsIgnoreCase(tglAwal)) {
                Date dtTglAwal = CommonUtil.convertStringToDate(tglAwal);
                tsTglAwal = new Timestamp(dtTglAwal.getTime());
            }
            if (tglAkhir != null && !"".equalsIgnoreCase(tglAkhir)) {
                Date dtTglAkhir = CommonUtil.convertStringToDate(tglAkhir);
                tsTglAkhir = new Timestamp(dtTglAkhir.getTime());
            }

            //ambil request dpjp
            try {
                listRequestDokter = teamDokterBoProxy.cekRequestDokterByIdDokter(idDokter, flagApprove, tsTglAwal, tsTglAkhir);
            } catch (GeneralBOException e){
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }

            //ambil dokter dpjp1
            if (listRequestDokter.size() > 0) {
                for (DokterTeam item : listRequestDokter) {

                    DokterMobile dokterMobile = new DokterMobile();
                    if (!"dpjp_1".equalsIgnoreCase(item.getJenisDpjp())) {
                        try {
                            dpjp1 = teamDokterBoProxy.getNamaDokter(item.getIdDetailCheckup(), true);
                        } catch (GeneralBOException e){
                            logger.error("[DokterController.create] Error, " + e.getMessage());
                        }
                        dokterMobile.setIdDokterDpjp1(dpjp1.getIdDokter());
                        dokterMobile.setNamaDokterDpjp1(dpjp1.getNamaDokter());
                    }

                    try {
                        detailPasien = checkupBoProxy.getDataDetailPasien(item.getIdDetailCheckup());
                    } catch (GeneralBOException e) {
                        logger.error("[DokterController.create] Error, " + e.getMessage());
                    }

                    dokterMobile.setFlagApprove(item.getFlagApprove());
                    dokterMobile.setKeterangan(item.getKeterangan());
                    dokterMobile.setIdPelayanan(item.getIdPelayanan());
                    dokterMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    dokterMobile.setJenisDpjp(item.getJenisDpjp());
                    dokterMobile.setIdDokterTeam(item.getIdTeamDokter());
                    dokterMobile.setNamaPelayanan(item.getNamaPelayanan());
                    dokterMobile.setTipePelayanan(item.getTipePelayanan());
                    dokterMobile.setCreatedDate(CommonUtil.convertTimestampToString(item.getCreatedDate()));

                    dokterMobile.setIdPasien(detailPasien.getIdPasien());
                    dokterMobile.setNamaPasien(detailPasien.getNama());
                    dokterMobile.setTglLahir(detailPasien.getStTglLahir());
                    dokterMobile.setNoRuangan(detailPasien.getNoRuangan());
                    dokterMobile.setKelasRuangan(detailPasien.getNamaRuangan());
                    dokterMobile.setTglLahir(CommonUtil.convertDateToString(detailPasien.getTglLahir()));
                    dokterMobile.setUmur(CommonUtil.calculateAge(detailPasien.getTglLahir(),true));
                    dokterMobile.setJalan(detailPasien.getJalan());
                    dokterMobile.setJenisKelamin(detailPasien.getJenisKelamin());
                    listOfDokter.add(dokterMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("saveApproveDpjp")) {
            DokterTeam beanDokterTeam = new DokterTeam();
            beanDokterTeam.setIdTeamDokter(idTeamDokter);
            beanDokterTeam.setKeterangan(keterangan);
            beanDokterTeam.setFlagApprove(flagApprove);
            beanDokterTeam.setLastUpdate(now);
            beanDokterTeam.setLastUpdateWho("admin");

            try {
                teamDokterBoProxy.saveApproveDokter(beanDokterTeam);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        logger.info("[DokterController.create] end process POST / <<<");

        return new DefaultHttpHeaders("create").disableCaching();

    }
}
