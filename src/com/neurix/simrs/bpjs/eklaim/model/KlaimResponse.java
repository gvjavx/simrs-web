package com.neurix.simrs.bpjs.eklaim.model;

public class KlaimResponse {
    private String patientId;
    private String admissionId;
    private String hospitalAdmissionId;

    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public String getHospitalAdmissionId() {
        return hospitalAdmissionId;
    }

    public void setHospitalAdmissionId(String hospitalAdmissionId) {
        this.hospitalAdmissionId = hospitalAdmissionId;
    }
}
