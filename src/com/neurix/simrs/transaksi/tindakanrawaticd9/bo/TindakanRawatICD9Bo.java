package com.neurix.simrs.transaksi.tindakanrawaticd9.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.tindakanrawaticd9.model.TindakanRawatICD9;

import java.util.List;

public interface TindakanRawatICD9Bo {
    public List<TindakanRawatICD9> getByCriteria(TindakanRawatICD9 bean)throws GeneralBOException;
    public CrudResponse saveAdd(TindakanRawatICD9 bean)throws GeneralBOException;
    public CrudResponse saveEdit(TindakanRawatICD9 bean)throws GeneralBOException;
}
