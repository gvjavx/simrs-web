package com.neurix.hris.transaksi.notifikasi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MonitoringMesinAbsensiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(NotifikasiAction.class);
    private NotifikasiBo notifikasiBoProxy;
    private Notifikasi notifikasi;

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public Notifikasi getNotifikasi() {
        return notifikasi;
    }

    public void setNotifikasi(Notifikasi notifikasi) {
        this.notifikasi = notifikasi;
    }

    public Notifikasi init(String kode, String flag){
        logger.info("[NotifikasiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Notifikasi> listOfResult = (List<Notifikasi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Notifikasi notifikasi : listOfResult) {
                    if(kode.equalsIgnoreCase(notifikasi.getNotifId()) && flag.equalsIgnoreCase(notifikasi.getFlag())){
                        setNotifikasi(notifikasi);
                        break;
                    }
                }
            } else {
                setNotifikasi(new Notifikasi());
            }
            logger.info("[NotifikasiAction.init] end process >>>");
        }
        return getNotifikasi();
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
        logger.info("[NotifikasiAction.search] start process >>>");

        Notifikasi searchNotifikasi = getNotifikasi();
        List<Notifikasi> listOfsearchNotifikasi = new ArrayList();

        try {
            listOfsearchNotifikasi = notifikasiBoProxy.getByCriteria(searchNotifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifikasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[NotifikasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchNotifikasi);

        logger.info("[NotifikasiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[NotifikasiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[NotifikasiAction.initForm] end process >>>");
        return INPUT;
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
