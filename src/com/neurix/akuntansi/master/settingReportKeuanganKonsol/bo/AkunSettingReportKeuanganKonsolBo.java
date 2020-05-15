package com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo;

import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface AkunSettingReportKeuanganKonsolBo extends BaseMasterBo<AkunSettingReportKeuanganKonsol> {
    public void saveDelete(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException;

    public List<AkunSettingReportKeuanganKonsol> getByDataCriteria(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException;
    public List<AkunSettingReportKeuanganKonsol> getDataById(AkunSettingReportKeuanganKonsol bean) throws GeneralBOException;
}
