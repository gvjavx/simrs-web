package com.neurix.simrs.master.lab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class LabAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(LabAction.class);
    private LabBo labBoProxy;
    private Lab lab;
    private List<Lab> listOfLab = new ArrayList<>();
    private List<Lab> lisOfRadiologi = new ArrayList<>();

    public List<Lab> getLisOfRadiologi() {
        return lisOfRadiologi;
    }

    public void setLisOfRadiologi(List<Lab> lisOfRadiologi) {
        this.lisOfRadiologi = lisOfRadiologi;
    }

    public List<Lab> getListOfLab() {
        return listOfLab;
    }

    public void setListOfLab(List<Lab> listOfLab) {
        this.listOfLab = listOfLab;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LabAction.logger = logger;
    }

    public LabBo getLabBoProxy() {
        return labBoProxy;
    }

    public void setLabBoProxy(LabBo labBoProxy) {
        this.labBoProxy = labBoProxy;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
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

    public List<Lab> listLab(String idKategoriLab){

        logger.info("[LabAction.listLab] start process >>>");
        List<Lab> labList = new ArrayList<>();
        Lab lab = new Lab();
        lab.setIdKategoriLab(idKategoriLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabBo labBo = (LabBo) ctx.getBean("labBoProxy");

        try {
            labList = labBo.getByCriteria(lab);
        }catch (GeneralBOException e){
            logger.error("[LabAction.listLab] Error when get data lab ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[LabAction.listLab] end process >>>");
        return labList;

    }

    public String getListLab(){

        logger.info("[LabAction.getListLab] start process >>>");

        List<Lab> labList = new ArrayList<>();
        Lab lab = new Lab();
        lab.setIdKategoriLab("KAL00000002");
        try {
            labList = labBoProxy.getByCriteria(lab);
        }catch (GeneralBOException e){
            logger.error("[LabAction.getListLab] Error when get lab ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfLab.addAll(labList);
        logger.info("[LabAction.getListLab] end process <<<");
        return SUCCESS;

    }

    public String getListRadiologi(){

        logger.info("[LabAction.getListRadiologi] start process >>>");

        List<Lab> labList = new ArrayList<>();
        Lab lab = new Lab();
        lab.setIdKategoriLab("KAL00000001");

        try {
            labList = labBoProxy.getByCriteria(lab);
        }catch (GeneralBOException e){
            logger.error("[LabAction.getListRadiologi] Error when get lab radiologi," + "Found problem when saving add data, please inform to your admin.", e);
        }

        lisOfRadiologi.addAll(labList);
        logger.info("[LabAction.getListRadiologi] end process <<<");
        return SUCCESS;

    }

}