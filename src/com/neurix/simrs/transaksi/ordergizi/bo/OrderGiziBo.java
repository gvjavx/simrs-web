package com.neurix.simrs.transaksi.ordergizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import com.neurix.simrs.transaksi.ordergizi.dao.OrderJenisDietDao;
import com.neurix.simrs.transaksi.ordergizi.model.DetailJenisDiet;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;

import java.util.List;

public interface OrderGiziBo {
    public List<OrderGizi> getByCriteria(OrderGizi bean) throws GeneralBOException;

    public CheckResponse saveAdd(List<OrderGizi> bean, String isTomorrow)throws GeneralBOException;
    public CheckResponse saveEdit(OrderGizi bean, List<String> list)throws GeneralBOException;
    public CheckResponse updateDiterimaFLag(OrderGizi bean) throws GeneralBOException;
    public CheckResponse cancelOrderGizi(OrderGizi bean) throws GeneralBOException;
    public List<DetailJenisDiet> getByCriteriaJenisDiet(DetailJenisDiet bean) throws GeneralBOException;
    public List<OrderGizi> cekOrderGizi(String id, String waktu) throws GeneralBOException;
}
