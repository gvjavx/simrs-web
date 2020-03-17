package com.neurix.simrs.transaksi.paketperiksa.action;

import com.neurix.akuntansi.master.masterVendor.bo.MasterVendorBo;
import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import com.neurix.simrs.transaksi.paketperiksa.model.ImSimrsKelasPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPeriksaAction extends BaseTransactionAction {

    public static transient Logger logger = Logger.getLogger(PaketPeriksaAction.class);

    private PaketPeriksaBo paketPeriksaBoProxy;
    private MasterVendorBo masterVendorBoProxy;
    //private MasterVendorBo masterVendorBoProxy;
    private PaketPeriksa paketPeriksa;


    public List<ImSimrsKelasPaketEntity> getListKelasPaket(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        PaketPeriksa paketPeriksa = new PaketPeriksa();

        return paketPeriksaBo.getListEntityKelasPaket(paketPeriksa);
    }

    public List<MasterVendor> getListMasterVendor(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MasterVendorBo masterVendorBo = (MasterVendorBo) ctx.getBean("masterVendorBoProxy");

        MasterVendor masterVendor = new MasterVendor();
        masterVendor.setFlag("Y");

        return masterVendorBo.getByCriteria(masterVendor);
    }

    public CrudResponse savePaket(String kelasPaket, String namaPaket, String jsonString) throws JSONException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        CrudResponse response = new CrudResponse();

        JSONArray json = new JSONArray(jsonString);
        List<MtSimrsItemPaketEntity> itemPaketEntities = new ArrayList<>();
        MtSimrsItemPaketEntity itemPaketEntity;
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            itemPaketEntity = new MtSimrsItemPaketEntity();
            itemPaketEntity.setIdItem(obj.getString("id_item"));
            itemPaketEntity.setIdKategoriItem(obj.getString("kategori_item"));
            itemPaketEntity.setJenisItem(obj.getString("jenis_item"));
            itemPaketEntities.add(itemPaketEntity);
        }

        MtSimrsPaketEntity paketPeriksa = new MtSimrsPaketEntity();
        paketPeriksa.setNamaPaket(namaPaket);
        paketPeriksa.setIdKelasPaket(kelasPaket);
        paketPeriksa.setFlag("Y");
        paketPeriksa.setAction("C");
        paketPeriksa.setBranchId(CommonUtil.userBranchLogin());
        paketPeriksa.setCreatedDate(time);
        paketPeriksa.setCreatedWho(userLogin);
        paketPeriksa.setLastUpdate(time);
        paketPeriksa.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");

        try {
            paketPeriksaBo.savePaketPeriksa(paketPeriksa, itemPaketEntities);
        } catch (GeneralBOException e){

        }

        return response;
    }

    public void setMasterVendorBoProxy(MasterVendorBo masterVendorBoProxy) {
        this.masterVendorBoProxy = masterVendorBoProxy;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        PaketPeriksa paketPeriksa = new PaketPeriksa();
        setPaketPeriksa(paketPeriksa);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public String add(){
        PaketPeriksa paketPeriksa = new PaketPeriksa();
        setPaketPeriksa(paketPeriksa);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "init_add";
    }

    public void setPaketPeriksaBoProxy(PaketPeriksaBo paketPeriksaBoProxy) {
        this.paketPeriksaBoProxy = paketPeriksaBoProxy;
    }

    public PaketPeriksa getPaketPeriksa() {
        return paketPeriksa;
    }

    public void setPaketPeriksa(PaketPeriksa paketPeriksa) {
        this.paketPeriksa = paketPeriksa;
    }
}
