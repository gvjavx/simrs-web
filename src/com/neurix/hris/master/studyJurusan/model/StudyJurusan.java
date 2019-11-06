package com.neurix.hris.master.studyJurusan.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class StudyJurusan extends BaseModel {
    private String jurusanId;
    private String jurusanName;

    public String getJurusanId() {
        return jurusanId;
    }

    public void setJurusanId(String jurusanId) {
        this.jurusanId = jurusanId;
    }

    public String getJurusanName() {
        return jurusanName;
    }

    public void setJurusanName(String jurusanName) {
        this.jurusanName = jurusanName;
    }
}