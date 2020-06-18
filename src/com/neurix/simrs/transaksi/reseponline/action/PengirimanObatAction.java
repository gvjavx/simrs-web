package com.neurix.simrs.transaksi.reseponline.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 18/06/20.
 */
public class PengirimanObatAction {
    private static transient Logger logger = Logger.getLogger(PengirimanObatAction.class);
    private ResepOnlineBo resepOnlineBoProxy;
    private PengirimanObat pengirimanObat;

    public PengirimanObat getPengirimanObat() {
        return pengirimanObat;
    }

    public void setPengirimanObat(PengirimanObat pengirimanObat) {
        this.pengirimanObat = pengirimanObat;
    }

    public void setResepOnlineBoProxy(ResepOnlineBo resepOnlineBoProxy) {
        this.resepOnlineBoProxy = resepOnlineBoProxy;
    }

    private String initForm(){
        logger.info("PengirimanObatAction.initForm >>> ");
        setPengirimanObat(new PengirimanObat());
        logger.info("PengirimanObatAction.initForm <<< ");
        return "search";
    }

    private List<PengirimanObat> searchPengiriman(){
        logger.info("PengirimanObatAction.search >>> ");
        PengirimanObat getPengirimanObat = getPengirimanObat();
        List<PengirimanObat> listResults = new ArrayList<>();
        try {
            listResults = resepOnlineBoProxy.getListPengirimanObat(getPengirimanObat);
        } catch (GeneralBOException e){
            logger.error("[PengirimanObatAction.search] ERROR. ", e);
            throw new GeneralBOException("[PengirimanObatAction.search] ERROR. ", e);
        }

        logger.info("PengirimanObatAction.search <<< ");
        return listResults;
    }

    private List<PermintaanResep> searchApprovedResep(){
        logger.info("PengirimanObatAction.searchApprovedResep >>> ");
        String branchId = CommonUtil.userBranchLogin();
        List<PermintaanResep> listResults = new ArrayList<>();
        try {
            listResults = resepOnlineBoProxy.getListResepTelemedic(branchId);
        } catch (GeneralBOException e){
            logger.error("[PengirimanObatAction.search] ERROR. ", e);
            throw new GeneralBOException("[PengirimanObatAction.search] ERROR. ", e);
        }

        logger.info("PengirimanObatAction.searchApprovedResep <<< ");
        return listResults;
    }
}
