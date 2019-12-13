package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalPelayananDTO;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.mobileapi.model.PelayananMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Friday, 22/11/19 14:35
 */
public class PelayananController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(PelayananController.class);
    private PelayananMobile model = new PelayananMobile();
    private PelayananBo pelayananBoProxy;
    private DokterBo dokterBoProxy;
    private JadwalShiftKerjaBo jadwalShiftKerjaBoProxy;
    private Collection<PelayananMobile> listOfPelayanan = new ArrayList<>();

    private String tglCheckup;
    private String idPelayanan;
    private String namaPelayanan;
    private String branchId;
    private String flag;
    private String action;

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

    @Override
    public Object getModel() {
        return (listOfPelayanan != null ? listOfPelayanan : model);
    }

    public HttpHeaders create() {
        logger.info("[PelayananController.create] start process POST / <<<");


        List<Pelayanan> listPelayanan = new ArrayList<>();
        List<Dokter> listDokter = new ArrayList<>();
        List<JadwalPelayananDTO> listJadwalPelayananDTO = new ArrayList<>();


        if (action.equalsIgnoreCase("show")) {
            try {
                listJadwalPelayananDTO = jadwalShiftKerjaBoProxy.getJadwalPelayanan("", "", branchId, "", CommonUtil.convertStringToDate(tglCheckup));
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
//                    logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "registrasi online index");
                } catch (GeneralBOException el) {

                }
            }

            int i = 0;
            for(JadwalPelayananDTO item : listJadwalPelayananDTO) {
                PelayananMobile result = new PelayananMobile();
                result.setIdPelayananApi(String.valueOf(i));
                result.setIdDokter(item.getIdDokter());
                result.setIdPelayanan(item.getIdPelayanan());
                result.setIdSpesialis(item.getIdSpesialis());
                result.setIdDokter(item.getIdDokter());
                result.setNamaDokter(item.getNamaDokter());
                result.setNamaPelayanan(item.getNamaPelayanan());
                result.setNamaSpesialis(item.getNamaSpesialis());
                result.setJamAkhir(item.getJamAkhir());
                result.setJamAwal(item.getJamAwal());
                result.setStTanggal(item.getTanggal().toString());

                listOfPelayanan.add(result);

                i++;
            }


        }


        if (action.equalsIgnoreCase("detail")) {
            try {
                listDokter = dokterBoProxy.getByIdPelayanan(idPelayanan, branchId);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
//                        logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "registrasi online index");
                } catch (GeneralBOException el) {

                }
            }

//            try {
//                listJadwalPelayananDTO = jadwalShiftKerjaBoProxy.getJadwalPelayanan(idPelayanan,);
//            } catch (GeneralBOException e) {
//                Long logId = null;
//                try {
////                                    logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "registrasi online index");
//                } catch (GeneralBOException el) {
//
//                }
//            }

        }

        logger.info("[PelayananController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();

    }
}


