package com.neurix.akuntansi.transaksi.jurnal.bo;

import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.jurnal.model.JurnalDetail;
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
public interface JurnalBo extends BaseMasterBo<Jurnal> {
    public void saveDelete(Jurnal bean) throws GeneralBOException;

    JurnalDetail getBudgetTerpakai(String branchId, String divisiId, String tahun, String bulan, String coa,String budget,String budgetSdBulanIni);
}
