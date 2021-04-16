package com.neurix.simrs.transaksi.laporan.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.laporan.bo.LaporanOpsBo;
import com.neurix.simrs.transaksi.laporan.model.LaporanOps;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LaporanOpsAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(LaporanOpsAction.class);
    private LaporanOpsBo laporanOpsBoProxy;
    private BranchBo branchBoProxy;
    private LaporanOps laporanOps;

    @Override
    public String initForm(){
        setLaporanOps(new LaporanOps());
        return "search";
    }

    @Override
    public String search(){
        String laporan  = "print_not_found";
        String logo     = "";
        String title    = "";
        String branch   = CommonUtil.userBranchLogin();
        Branch branches = new Branch();
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        LaporanOps searchLaporan = getLaporanOps();

        if("rawat_jalan".equalsIgnoreCase(searchLaporan.getTipeLaporan()) || "rawat_inap".equalsIgnoreCase(searchLaporan.getTipeLaporan())){
            try {
                laporanOpsList = laporanOpsBoProxy.getLaporanRjRi(searchLaporan);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
            if("rawat_jalan".equalsIgnoreCase(searchLaporan.getTipeLaporan())){
                title = "Laporan Rawat Jalan Periode "+searchLaporan.getTahun();
            }
            if("rawat_inap".equalsIgnoreCase(searchLaporan.getTipeLaporan())){
                title = "Laporan Rawat Inap Kelas "+searchLaporan.getNamaKelasRuangan()+" Periode "+searchLaporan.getTahun();
            }
            laporan = "print_laporan_rawat_jalan";
        }

        if("unggulan".equalsIgnoreCase(searchLaporan.getTipeLaporan())){
            try {
                laporanOpsList = laporanOpsBoProxy.getLaporanPlyUnggulan(searchLaporan);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
            if(searchLaporan.getTahun() != null && !"".equalsIgnoreCase(searchLaporan.getTahun())){
                title = "Laporan Pelayanan Unggulan Periode "+searchLaporan.getTahun();
            }else{
                if(searchLaporan.getDateFrom() != null && !"".equalsIgnoreCase(searchLaporan.getDateFrom()) &&
                        searchLaporan.getDateTo() != null && !"".equalsIgnoreCase(searchLaporan.getDateTo())){
                    title = "Laporan Pelayanan Unggulan<br>Tanggal "+searchLaporan.getDateFrom() +" s/d "+searchLaporan.getDateTo();
                }else if(searchLaporan.getDateFrom() != null && !"".equalsIgnoreCase(searchLaporan.getDateFrom())){
                    title = "Laporan Pelayanan Unggulan<br>Dari Tanggal "+searchLaporan.getDateFrom() +" s/d Sekarang";
                }else if(searchLaporan.getDateTo() != null && !"".equalsIgnoreCase(searchLaporan.getDateTo())){
                    title = "Laporan Pelayanan Unggulan<br>s/d Tanggal "+searchLaporan.getDateTo();
                }
            }
            laporan = "print_ply_unggulan";
        }

        if("diagnosa".equalsIgnoreCase(searchLaporan.getTipeLaporan())){
            if(searchLaporan.getListBulan().size() == 0){
                List<String> stringList = new ArrayList<>();
                stringList.add("1");
                stringList.add("2");
                stringList.add("3");
                stringList.add("4");
                stringList.add("5");
                stringList.add("6");
                stringList.add("7");
                stringList.add("8");
                stringList.add("9");
                stringList.add("10");
                stringList.add("11");
                stringList.add("12");
                searchLaporan.setListBulan(stringList);
            }
            try {
                laporanOpsList = laporanOpsBoProxy.getListDiagnosaTerbanyak(searchLaporan);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
            String tipe = "Rawat Inap";
            if("rawat_jalan".equalsIgnoreCase(searchLaporan.getTipePelayanan())){
                tipe = "Rawat Jalan";
            }
            title = "Laporan Diagnosa Terbanyak "+tipe+" Periode "+searchLaporan.getTahun();
            laporan = "print_diagnosa_terbanyak";
        }

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(laporanOpsList);

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        reportParams.put("area", CommonUtil.userAreaName());
        reportParams.put("unit", CommonUtil.userBranchNameLogin());
        reportParams.put("logo", logo);
        reportParams.put("itemData", itemData);
        reportParams.put("title", title);

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            return "search";
        }
        setLaporanOps(searchLaporan);
        return laporan;
    }

    public List<LaporanOps> getListLaporanOps(){
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanOpsBo laporanOpsBo = (LaporanOpsBo) ctx.getBean("laporanOpsBoProxy");
        try {
            laporanOpsList = laporanOpsBo.getByCriteria(new LaporanOps());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return laporanOpsList;
    }

    public List<Ruangan> listRuangan(String idKelas, String branchId) {

        logger.info("[LaporanOpsAction.listRuangan] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        List<Ruangan> ruanganList = new ArrayList<>();
        try {
            ruanganList = ruanganBo.getJustListRuangan(idKelas, branchId);
        } catch (GeneralBOException e) {
            logger.error("[LaporanOpsAction.listRuangan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[LaporanOpsAction.listRuangan] start process >>>");
        return ruanganList;
    }

    public List<LaporanOps> getListTahunOps(){
        List<LaporanOps> laporanOpsList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanOpsBo laporanOpsBo = (LaporanOpsBo) ctx.getBean("laporanOpsBoProxy");
        try {
            laporanOpsList = laporanOpsBo.getListTahunByOps();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return laporanOpsList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LaporanOpsAction.logger = logger;
    }

    public void setLaporanOpsBoProxy(LaporanOpsBo laporanOpsBoProxy) {
        this.laporanOpsBoProxy = laporanOpsBoProxy;
    }

    public LaporanOps getLaporanOps() {
        return laporanOps;
    }

    public void setLaporanOps(LaporanOps laporanOps) {
        this.laporanOps = laporanOps;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }
}
