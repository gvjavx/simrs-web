package com.neurix.akuntansi.transaksi.budgeting.model;

import com.neurix.akuntansi.transaksi.budgeting.action.BudgetingAction;

import java.util.Comparator;

/**
 * Created by reza on 12/08/20.
 */
public class StatusBudgeting {

    String statusDetail;
    String status;
    Integer idx;

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    // sorting status budgeting acending
    public class SortByIdxAsc implements Comparator<StatusBudgeting>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(StatusBudgeting a, StatusBudgeting b)
        {
            return a.getIdx() - b.getIdx();
        }
    }

    // sorting status budgeting decending
    public class SortByIdxDesc implements Comparator<StatusBudgeting>
    {
        // Used for sorting in decending order of
        // roll number
        public int compare(StatusBudgeting a, StatusBudgeting b)
        {
            return b.getIdx() - a.getIdx();
        }
    }


}
