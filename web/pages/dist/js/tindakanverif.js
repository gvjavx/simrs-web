function showTindakan(){
    $('#h_harga, #h_diskon').val(null);
    $('#is_edit').val("N");
    $('#form_elektif').hide();
    getListNamaDokter('');
    $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
    $('#tin_qty').val('1');
    $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
    $('#save_tindakan').attr('onclick', 'saveTindakan(\'\')').show();
    $('#modal-tindakan').modal({show: true, backdrop: 'static'});
}

function getListNamaDokter(tipe) {
    var option = '<option value="">[Select One]</option>';
    var def = '';
    CheckupAction.getListDokterByNoCheckup(noCheckupPasien, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                if (i == 0) {
                    def = item.idDokter + '|' + item.idPelayanan;
                }
                option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '">' + item.namaDokter + '</option>';
            });
            $('#tin_id_dokter_dpjp').html(option);
            if (tipe != 'edit') {
                $('#tin_id_dokter_dpjp').val(def).trigger('change');
            }
        } else {
            $('#tin_id_dokter_dpjp').html(option);
        }
    });
}

function saveTindakan(id) {
    var idKategori = $('#tin_id_ketgori_tindakan').val();
    var idTindakan = $('#tin_id_tindakan').val();
    var idDokter = $('#tin_id_dokter_dpjp').val();
    var qty = $('#tin_qty').val();
    var idJenisPeriksa = $('#jenis_pasien').val();
    var idDok = "";
    var idPelayanan = "";

    if (idDetailCheckup != '' && idTindakan != '' && idTindakan != null && idDokter != '' && qty > 0 && idKategori != '' && idKategori != null) {
        if (!cekSession()) {
            $('#save_tindakan').hide();
            $('#load_tindakan').show();
            idDok = idDokter.split("|")[0];
            idPelayanan = idDokter.split("|")[1];
            if (id != '') {
                dwr.engine.setAsync(true);
                TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, idDok, "RJ", qty, idJenisPeriksa, idPelayanan, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan(noCheckupPasien, jenisPeriksaPasien);
                            $('#modal-tindakan').modal('hide');
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                            $('#success').show().fadeOut(5000);;
                            $('#msg_suc').text("Tindakan berhasil diupdate...!");
                            $('#top_up').scrollTop(0);
                        } else {
                            $('#warning').show().fadeOut(5000);
                            $('#msg_war').text(response.msg);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                            $('#top_up').scrollTop(0);
                        }
                    }
                });
            } else {
                dwr.engine.setAsync(true);
                TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDok, "RJ", qty, idJenisPeriksa, idPelayanan, null, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan(noCheckupPasien, jenisPeriksaPasien);
                            $('#modal-tindakan').modal('hide');
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                            $('#success').show().fadeOut(5000);;
                            $('#msg_suc').text("Tindakan berhasil disimpan...!");
                            $('#top_up').scrollTop(0);
                        } else {
                            $('#warning').show().fadeOut(5000);
                            $('#msg_war').text(response.msg);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                            $('#top_up').scrollTop(0);
                        }
                    }
                });
            }
        }
    } else {
        $('#warning_tindakan').show().fadeOut(5000);
        $('#msg_tindakan').text('Silahkan cek kembali data inputan dan jumlah harus lebih dari 0..!');

        if (idDokter == '') {
            $('#war_dpjp').show();
        }
        if (idKategori == '' || idKategori == null) {
            $('#war_kategori').show();
        }
        if (idTindakan == '' || idTindakan == null) {
            $('#war_tindakan').show();
        }
        if (qty <= 0 || qty == '') {
            $('#tin_qty').css('border', 'red solid 1px');
        }
    }
}

function editTindakan(id, idTindakan, idKategori, katRuangan, qty, idDokter, idPelayanan) {
    kategoriRuangan = katRuangan;
    $('#is_edit').val('Y');
    $('#form_elektif').hide();
    getListNamaDokter('edit');
    $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
    $('#tin_id_dokter_dpjp').val(idDokter + '|' + idPelayanan).trigger('change');
    $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
    $('#tin_id_tindakan').val(idTindakan).trigger('change');
    $('#tin_qty').val(qty);
    $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
    $('#modal-tindakan').modal({show: true, backdrop: 'static'});
}

function listSelectTindakanKategori(val) {
    var option = '<option value="">[Select One]</option>';
    var idDokter = "";
    var idPelayanan = "";
    var def = '';
    var isEdit = $('#is_edit').val();
    if (val != null && val != '') {
        var dataDokter = val.split("|");
        idDokter = dataDokter[0];
        idPelayanan = dataDokter[1];
        CheckupDetailAction.getListComboTindakanKategori(idPelayanan, kategoriRuangan, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    if (i == 0) {
                        def = item.idKategoriTindakan;
                    }
                    option += '<option value="' + item.idKategoriTindakan + '">' + item.kategoriTindakan + '</option>';
                });
                $('#tin_id_ketgori_tindakan').html(option);
                if (isEdit != "Y") {
                    setTimeout(function () {
                        $('#tin_id_ketgori_tindakan').val(def).trigger('change');
                    }, 100);
                }
            } else {
                $('#tin_id_ketgori_tindakan').html('');
            }
        });
    }
}

function listSelectTindakan(idKategori) {
    var option = "<option value=''>[Select One]</option>";
    if (idKategori != '') {
        CheckupDetailAction.getListComboTindakan(idKategori, idKelasRuangan, flagVaksin, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                });
                $('#tin_id_tindakan').html(option);
            } else {
                $('#tin_id_tindakan').html('');
            }
        });
    } else {
        $('#tin_id_tindakan').html('');
    }
}

function setDiskonHarga(id) {
    if (id != '') {
        TindakanAction.initTindakan(id, function (res) {
            if (res.idTindakan != '') {
                var disk = 0;
                if (res.diskon != '' && res.diskon != null) {
                    disk = res.diskon;
                }
                if (jenisPeriksaPasien == "bpjs") {
                    $('#h_harga').val("Rp. " + formatRupiahAtas(res.tarifBpjs));
                } else if (jenisPeriksaPasien == "bpjs_rekanan" || jenisPeriksaPasien == "rekanan"){

                    TindakanRawatAction.getTarifDetailRekanaOps(idDetailCheckup, id, function (res2) {

                        if (jenisPeriksaPasien == "bpjs_rekanan"){
                            disk = res2.diskonBpjs;
                            $('#h_harga').val("Rp. " + formatRupiahAtas(res2.tarifBpjs));
                        }

                        if (jenisPeriksaPasien == "rekanan"){
                            disk = res2.diskonNonBpjs;
                            $('#h_harga').val("Rp. " + formatRupiahAtas(res2.tarif));
                        }
                    });

                } else {
                    $('#h_harga').val("Rp. " + formatRupiahAtas(res.tarif));
                }
                $('#h_diskon').val(disk);
                if ("Y" == res.isElektif) {
                    $('#form_elektif').show();
                    $('#is_elektif').val("Y");
                } else {
                    $('#form_elektif').hide();
                    $('#is_elektif').val("N");
                }

                if ("Y" == res.flagKonsulGizi) {
                    $('#warning_konsul').fadeIn(1000);
                    $('#msg_konsul').text("Anda memilih tindakan Konsultasi Gizi. Setelah menambahkan tindakan ini, infokan kepada Pasien untuk melakukan Konsultasi Gizi ke Unit Gizi...!");
                } else {
                    $('#warning_konsul').hide();
                }
            }
        });
    }
}