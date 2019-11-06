package com.neurix.hris.mobileapi.model;

/**
 * @author gondok
 * Wednesday, 27/03/19 13:23
 */
public class CountingNotifikasi {
    private int countDispensasi;
    private int countCuti;
    private int countTraining;
    private int countSppd;
    private int countLembur;

    public int getCountDispensasi() {
        return countDispensasi;
    }

    public void setCountDispensasi(int countDispensasi) {
        this.countDispensasi = countDispensasi;
    }

    public int getCountCuti() {
        return countCuti;
    }

    public void setCountCuti(int countCuti) {
        this.countCuti = countCuti;
    }

    public int getCountTraining() {
        return countTraining;
    }

    public void setCountTraining(int countTraining) {
        this.countTraining = countTraining;
    }

    public int getCountSppd() {
        return countSppd;
    }

    public void setCountSppd(int countSppd) {
        this.countSppd = countSppd;
    }

    public int getCountLembur() {
        return countLembur;
    }

    public void setCountLembur(int countLembur) {
        this.countLembur = countLembur;
    }
}
