package com.neurix.simrs.transaksi.checkup.action;

import com.neurix.common.action.BaseMasterAction;

public class CheckupAction extends BaseMasterAction {
    @Override
    public String add() {
        return "add";
    }

    @Override
    public String edit() {
        return "edit";
    }

    @Override
    public String delete() {
        return "delete";
    }

    @Override
    public String view() {
        return "view";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}