package com.neurix.simrs.master.labdetail.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class LabDetailAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(LabDetailAction.class);
    private LabDetailBo labDetailBoProxy;
    private LabDetail labDetail;

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
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public List<LabDetail> listLabDetail(String idLab){

        logger.info("[LabAction.listLabDetail] start process >>>");
        List<LabDetail> labDetailList = new ArrayList<>();
        LabDetail labDetail = new LabDetail();
        labDetail.setIdLab(idLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");

        try {
            labDetailList = labDetailBo.getByCriteria(labDetail);
        }catch (GeneralBOException e){
            logger.error("[LabDetailAction.listLabDetail] Error when get data lab detail ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[LabDetailAction.listLabDetail] end process >>>");
        return labDetailList;

    }
}