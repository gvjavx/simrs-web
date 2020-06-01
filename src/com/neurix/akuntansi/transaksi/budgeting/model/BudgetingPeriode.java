package com.neurix.akuntansi.transaksi.budgeting.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingPeriode {
    private Integer bulan;
    private Integer tahun;
    private String namaBulan;
    private String kuartal;
    private String semester;

    public Integer getBulan() {
        return bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public String getNamaBulan() {
        return namaBulan;
    }

    public void setNamaBulan(String namaBulan) {
        this.namaBulan = namaBulan;
    }

    public String getKuartal() {
        return kuartal;
    }

    public void setKuartal(String kuartal) {
        this.kuartal = kuartal;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<BudgetingPeriode> getListBudgetingPeriode(){
        List<BudgetingPeriode> listPeriode = new ArrayList<>();

        BudgetingPeriode budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(1);
        budgetingPeriode.setNamaBulan("januari");
        budgetingPeriode.setKuartal("quartal1");
        budgetingPeriode.setSemester("semester1");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(2);
        budgetingPeriode.setNamaBulan("februari");
        budgetingPeriode.setKuartal("quartal1");
        budgetingPeriode.setSemester("semester1");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(3);
        budgetingPeriode.setNamaBulan("maret");
        budgetingPeriode.setKuartal("quartal1");
        budgetingPeriode.setSemester("semester1");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(4);
        budgetingPeriode.setNamaBulan("april");
        budgetingPeriode.setKuartal("quartal2");
        budgetingPeriode.setSemester("semester1");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(5);
        budgetingPeriode.setNamaBulan("mei");
        budgetingPeriode.setKuartal("quartal2");
        budgetingPeriode.setSemester("semester1");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(6);
        budgetingPeriode.setNamaBulan("juni");
        budgetingPeriode.setKuartal("quartal2");
        budgetingPeriode.setSemester("semester1");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(7);
        budgetingPeriode.setNamaBulan("juli");
        budgetingPeriode.setKuartal("quartal3");
        budgetingPeriode.setSemester("semester2");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(8);
        budgetingPeriode.setNamaBulan("agustus");
        budgetingPeriode.setKuartal("quartal3");
        budgetingPeriode.setSemester("semester2");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(9);
        budgetingPeriode.setNamaBulan("september");
        budgetingPeriode.setKuartal("quartal3");
        budgetingPeriode.setSemester("semester2");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(10);
        budgetingPeriode.setNamaBulan("oktober");
        budgetingPeriode.setKuartal("quartal4");
        budgetingPeriode.setSemester("semester2");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(11);
        budgetingPeriode.setNamaBulan("november");
        budgetingPeriode.setKuartal("quartal4");
        budgetingPeriode.setSemester("semester2");
        listPeriode.add(budgetingPeriode);

        budgetingPeriode = new BudgetingPeriode();
        budgetingPeriode.setBulan(12);
        budgetingPeriode.setNamaBulan("desember");
        budgetingPeriode.setKuartal("quartal4");
        budgetingPeriode.setSemester("semester2");
        listPeriode.add(budgetingPeriode);
        return listPeriode;
    }
}
