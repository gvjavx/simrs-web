function setDataPasien() {
    if(!cekSession()){
        var jam = $('.jam').length;
        var jam2 = $('.jam02').length;
        var tgl = $('.tgl').length;
        var gejala = $('.anamnese').length;
        var penMedis = $('.penunjang-medis').length;
        var nama = $('.nama-pasien').length;
        var alamat = $('.alamat-pasien').length;
        var nobpjs = $('.no-bpjs').length;
        var age = $('.umur-pasien').length;
        var jk = $('.jenis-kelamin').length;
        var diag = $('.diagnosa-pasien').length;
        var diagSen = $('.diagnosa-sekunder').length;
        var diagPri = $('.diagnosa-primer').length;
        var alr = $('.alergi-pasien').length;
        var bb = $('.berat-pasien').length;
        var tb = $('.tinggi-pasien').length;
        var sel = $('.select2').length;
        var idP = $('.norm-pasien').length;
        var tglPasien = $('.tgl-lahir-pasien').length;
        var patTgl = $('.ptr-tgl').length;

        var tensi = $('.tensi-pasien').length;
        var suhu = $('.suhu-pasien').length;
        var nadi = $('.nadi-pasien').length;
        var rr = $('.rr-pasien').length;

        var resep = $('.resep-pasien').length;
        var tindakan = $('.tindakan-pasien').length;
        var sip = $('.sip_dokter').length;
        var namaDok = $('.nama_dokter').length;
        var labPas = $('.lab-pasien').length;
        var radioPas = $('.radiologi-pasien').length;
        var sipRi = $('.sip_dokter_ri').length;
        var namaDokRi = $('.nama_dokter_ri').length;
        var namaPetugas = $('.nama_petugas').length;
        var namaRuangan = $('.nama_ruangan').length;

        if (tensi > 0 || gejala > 0 || bb > 0 || tb > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataPemeriksaanFisik(noCheckup, {
                callback: function (res) {
                    if (res != '') {
                        tempTensi = res.tensi;
                        tempSuhu = res.suhu;
                        tempNadi = res.nadi;
                        tempRr = res.pernafasan;
                        tempBerat = res.berat;
                        tempTinggi = res.tinggi;
                        tempAnmnesa = res.anamnese;
                    }
                }
            });
        }

        if (jam > 0) {
            $('.jam').timepicker();
            $('.jam').val(converterTime(new Date()));
        }
        if (jam2 > 0) {
            $('.jam02').timepicker();
            $('.jam02').val(converterTime(new Date()));
        }

        if (tgl > 0) {
            $('.tgl').datepicker({
                dateFormat: 'dd-mm-yy'
            });
            $('.tgl').val(converterDate(new Date()));
            $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        }
        if (gejala > 0) {
            $('.anamnese').val(tempAnmnesa);
        }
        if (penMedis > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "penunjang_medis", {
                callback: function (res) {
                    if (res != '') {
                        $('.penunjang-medis').val(res);
                    }
                }
            });
        }
        if (labPas > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "lab", {
                callback: function (res) {
                    if (res != '') {
                        $('.lab-pasien').val(res);
                    }
                }
            });
        }
        if (radioPas > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "radiologi", {
                callback: function (res) {
                    if (res != '') {
                        $('.radiologi-pasien').val(res);
                    }
                }
            });
        }
        if (resep > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "resep", {
                callback: function (res) {
                    if (res != '') {
                        $('.resep-pasien').val(res);
                    }
                }
            });
        }
        if (tindakan > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "tindakan", {
                callback: function (res) {
                    if (res != '') {
                        $('.tindakan-pasien').val(res);
                    }
                }
            });
        }
        if (diagSen > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "diagnosa_sekunder", {
                callback: function (res) {
                    if (res != '') {
                        $('.diagnosa_sekunder').val(res);
                    }
                }
            });
        }
        if (diagPri > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "diagnosa_primer", {
                callback: function (res) {
                    if (res != '') {
                        $('.diagnosa_primer').val(res);
                    }
                }
            });
        }
        if (nama > 0) {
            $('.nama-pasien').val(namaPasien);
        }
        if (alamat > 0) {
            $('.alamat-pasien').val(alamatLengkap);
        }
        if (nobpjs > 0) {
            $('.no-bpjs').val(noBpjs);
        }
        if (age > 0) {
            $('.umur-pasien').val(umur + ' Tahun');
        }
        if (jk > 0) {
            $('.jenis-kelamin').val(jenisKelamin);
        }
        if (diag > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(idDetailCheckup, "diagnosa", {
                callback: function (res) {
                    if (res != '') {
                        $('.diagnosa-pasien').val(res);
                    }
                }
            });
        }
        if (alr > 0) {
            dwr.engine.setAsync(true);
            CheckupAction.getDataByKey(noCheckup, "alergi", {
                callback: function (res) {
                    if (res != '') {
                        $('.alergi-pasien').val(res);
                    }
                }
            });
        }

        if(sip > 0 && namaDok > 0){
            dwr.engine.setAsync(true);
            CheckupAction.getDataDokterSip(idDetailCheckup, "RJ", {
                callback: function (res) {
                    if (res != '') {
                        $('.sip_dokter').val(res.sip);
                        $('.nama_dokter').val(res.namaDokter);
                    }
                }
            });
        }
        if(sipRi > 0 && namaDokRi > 0){
            dwr.engine.setAsync(true);
            CheckupAction.getDataDokterSip(idDetailCheckup, "RI", {
                callback: function (res) {
                    if (res != '') {
                        $('.sip_dokter_ri').val(res.sip);
                        $('.nama_dokter_ri').val(res.namaDokter);
                    }
                }
            });
        }
        if(namaPetugas > 0){
            dwr.engine.setAsync(true);
            CheckupAction.cekLogin({
                callback: function (res) {
                    if (res != '') {
                        $('.nama_petugas').val(res.msg);
                        $('.nip_petugas').val(res.status);
                    }
                }
            });
        }
        if (bb > 0) {
            $('.berat-pasien').val(tempBerat);
        }
        if (tb > 0) {
            $('.tinggi-pasien').val(tempTinggi);
        }
        if (sel > 0) {
            $('.select2').select2();
            $('.select2').attr('style', 'width: 100%');
        }
        if (idP > 0) {
            $('.norm-pasien').val(idPasien);
        }
        if (tglPasien > 0) {
            $('.tgl-lahir-pasien').val(tglLahir);
        }
        if (patTgl > 0) {
            $('.ptr-tgl').datepicker({
                dateFormat: 'dd-mm-yy'
            });
            $('.ptr-tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
        }
        if (tensi > 0) {
            $('.tensi-pasien').val(tempTensi);
        }
        if (suhu > 0) {
            $('.suhu-pasien').val(tempSuhu);
        }
        if (nadi > 0) {
            $('.nadi-pasien').val(tempNadi);
        }
        if (rr > 0) {
            $('.rr-pasien').val(tempRr);
        }
        if (namaRuangan > 0) {
            $('.nama_ruangan').val(namaRuanganPasien);
        }

        $(function () {
            $('[data-mask]').inputmask();
        });

        var delCanvas = $('.del-canvas');
        if(delCanvas.length > 0){
            $.each(delCanvas, function (i, item) {
                var canvas = document.getElementById(item.id);
                const context = canvas.getContext('2d');
                context.clearRect(0, 0, canvas.width, canvas.height);
            });
        }
    }
}