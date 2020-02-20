package com.neurix.simrs.transaksi.monitorruangan.action;

import com.neurix.common.action.BaseMasterAction;
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
import java.util.ArrayList;
import java.util.List;

public class MonitorRuanganAction extends BaseMasterAction {
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
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {

        List<Ruangan> ruanganList = new ArrayList<>();
        Ruangan ruangan = getRuangan();

        try {
            ruanganList = monitorRuanganBoProxy.getListRuangan(ruangan);
        } catch (GeneralBOException e) {
            logger.error("Found Error" + e.getMessage());
        }

        List<Ruangan> result = new ArrayList<>();

        if (ruanganList.size() > 0) {

            Ruangan ruangan1;
            for (Ruangan ruang : ruanganList) {

                ruangan1 = new Ruangan();

                if (ruang.getIdDetailCheckup() != null && !"".equalsIgnoreCase(ruang.getIdDetailCheckup())) {

                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                    detailCheckup.setIdDetailCheckup(ruang.getIdDetailCheckup());

                    List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

                    try {
                        detailCheckupList = checkupDetailBoProxy.getByCriteria(detailCheckup);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
                    }

                    BigInteger tarifTindakan = new BigInteger(String.valueOf(0));

                    try {
                        tarifTindakan = checkupDetailBoProxy.getSumOfTindakanByNoCheckup(ruangan.getIdDetailCheckup());
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.add] Error when get total tarif " + e.getMessage());
                    }

                    if (!detailCheckupList.isEmpty()) {
                        detailCheckup = detailCheckupList.get(0);

                        if (detailCheckup.getTarifBpjs() != null && detailCheckup.getTarifBpjs().compareTo(new BigDecimal(String.valueOf(0))) == 1) {

                            BigDecimal tarifResep = new BigDecimal(String.valueOf(0));
                            BigDecimal tarifLab = new BigDecimal(String.valueOf(0));
                            BigDecimal tarifGizi = new BigDecimal(String.valueOf(0));


                            HeaderCheckup headerCheckup = new HeaderCheckup();
                            headerCheckup.setNoCheckup(detailCheckup.getNoCheckup());
                            List<HeaderCheckup> headerCheckupList = new ArrayList<>();

                            try {
                                headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
                            } catch (GeneralBOException e) {
                                logger.error("Gound Error" + e.getMessage());
                            }

                            if (headerCheckupList.size() > 0) {

                                headerCheckup = headerCheckupList.get(0);

                                if (headerCheckup != null) {
                                    detailCheckup.setIdJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                                }
                            }

                            List<PeriksaLab> periksaLabList = new ArrayList<>();
                            PeriksaLab periksaLab = new PeriksaLab();
                            periksaLab.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                            periksaLab.setApproveFlag("Y");

                            try {
                                periksaLabList = periksaLabBoProxy.getByCriteria(periksaLab);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                            }

                            if (periksaLabList.size() > 0) {

                                for (PeriksaLab entity : periksaLabList) {

                                    List<Lab> labList = new ArrayList<>();
                                    Lab lab = new Lab();
                                    lab.setIdLab(entity.getIdLab());

                                    try {
                                        labList = labBoProxy.getByCriteria(lab);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tarif tindakan :" + e.getMessage());
                                    }

                                    if (labList.size() > 0) {
                                        lab = labList.get(0);
                                        if (lab != null) {

                                            BigDecimal tariff = new BigDecimal(String.valueOf(0));
                                            if (lab.getTarif() != null) {
                                                tariff = lab.getTarif();
                                            }

                                            tarifLab = tarifLab.add(tariff);
                                        }
                                    }
                                }
                            }

                            List<PermintaanResep> resepList = new ArrayList<>();
                            PermintaanResep resep = new PermintaanResep();
                            resep.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());

                            try {
                                resepList = permintaanResepBoProxy.getByCriteria(resep);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
                            }

                            if (resepList.size() > 0) {
                                for (PermintaanResep entity : resepList) {

                                    List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
                                    TransaksiObatDetail detail = new TransaksiObatDetail();
                                    detail.setIdPermintaanResep(entity.getIdPermintaanResep());

                                    try {
                                        obatDetailList = transaksiObatBoProxy.getSearchObatTransaksiByCriteria(detail);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                                    }

                                    BigInteger hitungTotalResep = hitungTotalBayar(obatDetailList);
                                    tarifResep = tarifResep.add(new BigDecimal(hitungTotalResep));
                                }
                            }

                            RawatInap rawatInap = new RawatInap();
                            rawatInap.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                            List<RawatInap> rawatInapList = new ArrayList<>();

                            try {
                                rawatInapList = rawatInapBoProxy.getByCriteria(rawatInap);
                            } catch (GeneralBOException e) {
                                logger.error("Found Error" + e.getMessage());
                            }

                            if (rawatInapList.size() > 0) {

                                rawatInap = rawatInapList.get(0);
                                if (rawatInap != null) {

                                    OrderGizi orderGizi = new OrderGizi();
                                    orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                                    List<OrderGizi> giziList = new ArrayList<>();

                                    try {
                                        giziList = orderGiziBoProxy.getByCriteria(orderGizi);
                                    } catch (GeneralBOException e) {
                                        logger.error("Found Error " + e.getMessage());
                                    }

                                    if (giziList.size() > 0) {

                                        for (OrderGizi gizi : giziList) {

                                            BigDecimal tariff = new BigDecimal(String.valueOf(0));
                                            if (gizi.getTarifTotal() != null) {
                                                tariff = gizi.getTarifTotal();
                                            }

                                            tarifGizi = tarifGizi.add(tariff);
                                        }
                                    }
                                }
                            }

                            BigDecimal totalTarifTindakan = tarifLab.add(tarifResep).add(new BigDecimal(tarifTindakan)).add(tarifGizi);

                            //=======START HITUNG TARIF TINDAKAN==========================

                            ruangan1.setTarifBpjs(detailCheckup.getTarifBpjs());
                            ruangan1.setTarifTindakan(totalTarifTindakan);

                            //=======END HITUNG TARIF TINDAKAN==========================
                        }
                    }
                }

                ruangan1.setIdKelasRuangan(ruang.getIdKelasRuangan());
                ruangan1.setIdRuangan(ruang.getIdRuangan());
                ruangan1.setNamaRuangan(ruang.getNamaRuangan());
                ruangan1.setNoRuangan(ruang.getNoRuangan());
                ruangan1.setIdDetailCheckup(ruang.getIdDetailCheckup());
                ruangan1.setStatusRuangan(ruang.getStatusRuangan());
                result.add(ruangan1);

            }
        }


        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", ruanganList);

        return "search";
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

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
