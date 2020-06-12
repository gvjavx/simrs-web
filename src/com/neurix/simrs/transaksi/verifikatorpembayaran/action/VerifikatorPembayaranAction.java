package com.neurix.simrs.transaksi.verifikatorpembayaran.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.antrian.model.Antrian;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranAction {
    private final static transient Logger logger = Logger.getLogger(VerifikatorPembayaranAction.class);

    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;
    private TelemedicBo telemedicBoProxy;
    private PembayaranOnline pembayaranOnline;
    public AntrianTelemedic antrianTelemedic;

    public AntrianTelemedic getAntrianTelemedic() {
        return antrianTelemedic;
    }

    public void setAntrianTelemedic(AntrianTelemedic antrianTelemedic) {
        this.antrianTelemedic = antrianTelemedic;
    }

    public PembayaranOnline getPembayaranOnline() {
        return pembayaranOnline;
    }

    public void setPembayaranOnline(PembayaranOnline pembayaranOnline) {
        this.pembayaranOnline = pembayaranOnline;
    }

    public void setVerifikatorPembayaranBoProxy(VerifikatorPembayaranBo verifikatorPembayaranBoProxy) {
        this.verifikatorPembayaranBoProxy = verifikatorPembayaranBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public String initForm(){
        logger.info("[VerifikatorPembayaranAction.initForm] START >>>");

        setPembayaranOnline(new PembayaranOnline());
        setAntrianTelemedic(new AntrianTelemedic());
        logger.info("[VerifikatorPembayaranAction.initForm] END <<<");
        return "search";
    }

    public String search(){
        logger.info("[VerifikatorPembayaranAction.search] START >>>");

        String branchId = CommonUtil.userBranchLogin();
        AntrianTelemedic antrianTelemedic = getAntrianTelemedic();
        AntrianTelemedic searchAntrian = new AntrianTelemedic();
        searchAntrian.setBranchId(branchId);

        if (antrianTelemedic != null){
            searchAntrian.setStatus(antrianTelemedic.getStatus());
            searchAntrian.setIdPelayanan(antrianTelemedic.getIdPelayanan());
        }


        List<AntrianTelemedic> listResults = new ArrayList<>();
        try {
            listResults = telemedicBoProxy.getSearchByCriteria(searchAntrian);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.search] ERROR. ",e);
            throw new GeneralBOException("[VerifikatorPembayaranAction.search] ERROR. ",e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResults");
        session.setAttribute("listOfResults", listResults);

        logger.info("[VerifikatorPembayaranAction.search] END <<<");
        return "search";
    }

    public List<PembayaranOnline> listDetailPembayaran(String idAntrianTelemedic){
        logger.info("[VerifikatorPembayaranAction.listDetailPembayaran] START >>>");

        List<PembayaranOnline> pembayaranOnlines = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        PembayaranOnline pembayaranOnline = new PembayaranOnline();
        pembayaranOnline.setIdAntrianTelemedic(idAntrianTelemedic);

        try {
            pembayaranOnlines = verifikatorPembayaranBo.getSearchByCriteria(pembayaranOnline);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.listDetailPembayaran] ERROR. ",e);
            throw new GeneralBOException("[VerifikatorPembayaranAction.listDetailPembayaran] ERROR. ",e);
        }

        logger.info("[VerifikatorPembayaranAction.listDetailPembayaran] END <<<");
        return pembayaranOnlines;
    }
}
