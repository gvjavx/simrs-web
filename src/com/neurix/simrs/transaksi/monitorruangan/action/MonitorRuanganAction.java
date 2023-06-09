package com.neurix.simrs.transaksi.monitorruangan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.monitorruangan.bo.MonitorRuanganBo;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MonitorRuanganAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(MonitorRuanganAction.class);
    private MonitorRuanganBo monitorRuanganBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private PeriksaLabBo periksaLabBoProxy;
    private LabBo labBoProxy;
    private PermintaanResepBo permintaanResepBoProxy;
    private TransaksiObatBo transaksiObatBoProxy;
    private RawatInapBo rawatInapBoProxy;
    private OrderGiziBo orderGiziBoProxy;
    private Ruangan ruangan;
    public static Logger getLogger() {
        return logger;
    }

    @Override
    public String search() {
        List<Ruangan> ruanganList = new ArrayList<>();
        List<Ruangan> result = new ArrayList<>();
        Ruangan ruangan = getRuangan();
        try {
            ruanganList = monitorRuanganBoProxy.getListRuangan(ruangan);
        } catch (GeneralBOException e) {
            logger.error("Found Error" + e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", ruanganList);
        setRuangan(ruangan);
        return "search";
    }

    public HeaderDetailCheckup getListBiayaForRawatInap(String idDetailCheckup) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            detailCheckup.setIdDetailCheckup(idDetailCheckup);

            BigDecimal totalBiayaTindakan = checkupDetailBoProxy.getSumJumlahTindakan(idDetailCheckup, "");

            List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

            try {
                detailCheckupList = checkupDetailBoProxy.getByCriteria(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.getListBiayaForRawatInap] Error When Get Header Checkup Data", e);
            }

            if (detailCheckupList.size() > 0) {
                HeaderDetailCheckup headerDetailCheckup = detailCheckupList.get(0);
                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setNoCheckup(headerDetailCheckup.getNoCheckup());

                List<HeaderCheckup> headerCheckups = new ArrayList<>();
                try {
                    headerCheckups = checkupBoProxy.getByCriteria(headerCheckup);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupDetailAction.getListBiayaForRawatInap] Error When Get Header Checkup Data", e);
                }

                if (headerCheckups.size() > 0) {
                    HeaderCheckup checkup = headerCheckups.get(0);
                    detailCheckup.setNamaPasien(checkup.getNama());
                }

                if (headerDetailCheckup.getIdDetailCheckup() != null && !"".equalsIgnoreCase(headerDetailCheckup.getIdDetailCheckup())) {
                    detailCheckup.setTarifBpjs(headerDetailCheckup.getTarifBpjs());
                    detailCheckup.setKodeCbg(headerDetailCheckup.getKodeCbg());
                    detailCheckup.setTarifTindakan(totalBiayaTindakan);
                    detailCheckup.setIdJenisPeriksaPasien(headerDetailCheckup.getIdJenisPeriksaPasien());
                }
            }
        }

        return detailCheckup;
    }

    private BigInteger hitungTotalBayar(List<TransaksiObatDetail> transaksiObatDetails) {

        BigInteger total = new BigInteger(String.valueOf("0"));
        if (transaksiObatDetails != null && transaksiObatDetails.size() > 0) {
            for (TransaksiObatDetail trans : transaksiObatDetails) {
                total = total.add(trans.getTotalHarga());
            }
        }
        return total;

    }

    @Override
    public String initForm() {
        Ruangan ruangan = new Ruangan();
        setRuangan(ruangan);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }


    public void setMonitorRuanganBoProxy(MonitorRuanganBo monitorRuanganBoProxy) {
        this.monitorRuanganBoProxy = monitorRuanganBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public void setPeriksaLabBoProxy(PeriksaLabBo periksaLabBoProxy) {
        this.periksaLabBoProxy = periksaLabBoProxy;
    }

    public void setLabBoProxy(LabBo labBoProxy) {
        this.labBoProxy = labBoProxy;
    }

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    public void setOrderGiziBoProxy(OrderGiziBo orderGiziBoProxy) {
        this.orderGiziBoProxy = orderGiziBoProxy;
    }

    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }
}
