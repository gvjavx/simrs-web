package com.neurix.hris.master.medicalBiayaKacamata.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.hris.master.medicalBiayaKacamata.model.MedicalBiayaKacamata;
import com.neurix.hris.transaksi.medicalKacamata.model.MedicalKacamata;
import com.neurix.hris.transaksi.payroll.model.Payroll;
import com.neurix.common.exception.GeneralBOException;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface MedicalBiayaKacamataBo extends BaseMasterBo<MedicalBiayaKacamata>{
    public String getBiaya(String branchId, String golonganId, String tipe) throws GeneralBOException;

}
