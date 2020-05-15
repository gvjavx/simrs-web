package com.neurix.akuntansi.master.settingReportKeuanganKonsol.action;

import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolDetailBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.common.action.BaseMasterAction;
import org.apache.log4j.Logger;

public class SettingReportKeuanganKonsolDetailAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(SettingReportKeuanganKonsolDetailAction.class);
    private AkunSettingReportKeuanganKonsolDetailBo akunSettingReportKeuanganKonsolDetailBoProxy;
    private AkunSettingReportKeuanganKonsolDetail akunSettingReportKeuanganKonsolDetail;

    public AkunSettingReportKeuanganKonsolDetailBo getAkunSettingReportKeuanganKonsolDetailBoProxy() {
        return akunSettingReportKeuanganKonsolDetailBoProxy;
    }

    public void setAkunSettingReportKeuanganKonsolDetailBoProxy(AkunSettingReportKeuanganKonsolDetailBo akunSettingReportKeuanganKonsolDetailBoProxy) {
        this.akunSettingReportKeuanganKonsolDetailBoProxy = akunSettingReportKeuanganKonsolDetailBoProxy;
    }

    public AkunSettingReportKeuanganKonsolDetail getAkunSettingReportKeuanganKonsolDetail() {
        return akunSettingReportKeuanganKonsolDetail;
    }

    public void setAkunSettingReportKeuanganKonsolDetail(AkunSettingReportKeuanganKonsolDetail akunSettingReportKeuanganKonsolDetail) {
        this.akunSettingReportKeuanganKonsolDetail = akunSettingReportKeuanganKonsolDetail;
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
}