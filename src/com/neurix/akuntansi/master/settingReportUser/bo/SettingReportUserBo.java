package com.neurix.akuntansi.master.settingReportUser.bo;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.settingReportUser.model.SettingReportUser;
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
public interface SettingReportUserBo extends BaseMasterBo<SettingReportUser> {
    public void saveDelete(SettingReportUser bean) throws GeneralBOException;
}
