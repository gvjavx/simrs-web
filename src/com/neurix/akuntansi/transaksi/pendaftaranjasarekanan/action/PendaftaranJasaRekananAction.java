package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.action;

import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo.PendaftatanJasaRekananBo;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.PendaftaranJasa;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranJasaRekananAction extends BaseTransactionAction{
    public static transient Logger logger = Logger.getLogger(PendaftaranJasaRekananAction.class);

    private PendaftatanJasaRekananBo pendaftatanJasaRekananBoProxy;
    private PendaftaranJasa pendaftaranJasa;

    public PendaftaranJasa getPendaftaranJasa() {
        return pendaftaranJasa;
    }

    public void setPendaftaranJasa(PendaftaranJasa pendaftaranJasa) {
        this.pendaftaranJasa = pendaftaranJasa;
    }

    public void setPendaftatanJasaRekananBoProxy(PendaftatanJasaRekananBo pendaftatanJasaRekananBoProxy) {
        this.pendaftatanJasaRekananBoProxy = pendaftatanJasaRekananBoProxy;
    }

    @Override
    public String search() {
        logger.info("[PendaftaranJasaRekananAction.initForm] Start >>>");

        PendaftaranJasa dataPendaftaranJasa = getPendaftaranJasa();

        List<PendaftaranJasa> pendaftaranJasaList = new ArrayList<>();
        try {
            pendaftaranJasaList = pendaftatanJasaRekananBoProxy.getSearchByCriteria(dataPendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.initForm] Error ", e);
            addActionError("[PendaftaranJasaRekananAction.initForm] Error "+ e.getCause());
            return "success";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", pendaftaranJasaList);
        logger.info("[PendaftaranJasaRekananAction.initForm] End <<<");
        return "success";
    }

    @Override
    public String initForm() {
        logger.info("[PendaftaranJasaRekananAction.initForm] Start >>>");

        setPendaftaranJasa(new PendaftaranJasa());

        logger.info("[PendaftaranJasaRekananAction.initForm] End <<<");
        return "success";
    }
}
