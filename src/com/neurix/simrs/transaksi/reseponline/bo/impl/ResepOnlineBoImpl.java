package com.neurix.simrs.transaksi.reseponline.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.dao.PengirimanObatDao;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.DateTimeDateFormat;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author gondok
 * Tuesday, 16/06/20 16:08
 */
public class ResepOnlineBoImpl implements ResepOnlineBo {

    private static transient Logger logger = Logger.getLogger(ResepOnlineBoImpl.class);


    private PengirimanObatDao pengirimanObatDao;

    public void setPengirimanObatDao(PengirimanObatDao pengirimanObatDao) {
        this.pengirimanObatDao = pengirimanObatDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    private String getNextIdPengiriman(String branchId){
        logger.info("[ResepOnlineBoImpl.getNextIdPengiriman] END <<<");
        return branchId + CommonUtil.stDateSeq() + pengirimanObatDao.getNextSeq();
    }
}
