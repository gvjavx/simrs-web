package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 12/12/2019.
 */
public class PermintaanObatPoliAction extends BaseTransactionAction{
    private static transient Logger logger = Logger.getLogger(PermintaanObatPoliAction.class);
    private ObatPoliBo obatPoliBoProxy;
    private PermintaanObatPoli permintaanObatPoli;
    private ObatPoli obatPoli;

    @Override
    public String search() {
        logger.info("[PermintaanObatPoliAction.search] START >>>>>>>");

        PermintaanObatPoli permintaanObatPoli = getPermintaanObatPoli();
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());

        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli);
        } catch (HibernateException e){
            logger.error("[PermintaanObatPoliAction.search] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.search] ERROR when get data list obat, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[PermintaanObatPoliAction.search] END <<<<<<<");
        return "search";
    }

    @Override
    public String initForm() {
        return "search";
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
    }

    public PermintaanObatPoli getPermintaanObatPoli() {
        return permintaanObatPoli;
    }

    public void setPermintaanObatPoli(PermintaanObatPoli permintaanObatPoli) {
        this.permintaanObatPoli = permintaanObatPoli;
    }

    public ObatPoli getObatPoli() {
        return obatPoli;
    }

    public void setObatPoli(ObatPoli obatPoli) {
        this.obatPoli = obatPoli;
    }
}
