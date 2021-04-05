package com.neurix.hris.transaksi.logcron.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class logCron extends BaseModel {
    private String logCronId;
    private String cronName;
    private Timestamp cronDate;
    private String stCronDate;
    private String status;
    private String note;

    private Timestamp createdDate;
    private String stCreatedDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String stLastUpdate;
    private String createdWho;
    private String lastUpdateWho;
}
