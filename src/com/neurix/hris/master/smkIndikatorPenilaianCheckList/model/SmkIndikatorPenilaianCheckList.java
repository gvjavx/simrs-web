package com.neurix.hris.master.smkIndikatorPenilaianCheckList.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkIndikatorPenilaianCheckList extends BaseModel {
    private String indikatorPenilaianCheckListId;
    private String checkListName;
    private String checkListId;
    private String indikatorName;
    private String nilai;

    public String getIndikatorPenilaianCheckListId() {
        return indikatorPenilaianCheckListId;
    }

    public void setIndikatorPenilaianCheckListId(String indikatorPenilaianCheckListId) {
        this.indikatorPenilaianCheckListId = indikatorPenilaianCheckListId;
    }

    public String getCheckListName() {
        return checkListName;
    }

    public void setCheckListName(String checkListName) {
        this.checkListName = checkListName;
    }

    public String getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public String getIndikatorName() {
        return indikatorName;
    }

    public void setIndikatorName(String indikatorName) {
        this.indikatorName = indikatorName;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}