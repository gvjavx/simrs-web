function showDiagnosa(ply){
    $('#t_diagnosa').text('Tambah Diagnosa')
    $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
    $('#nosa_id_diagnosa, #nosa_ket_diagnosa').val('');
    $('#nosa_jenis_diagnosa').val('-').trigger('change');
    if(ply === "rj")
    { $('#nosa_jenis_diagnosa option[value="diagnosa_awal"]').remove(); }
    $('#load_diagnosa').hide();
    $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'\')').show();
    $('#modal-diagnosa').modal({show: true, backdrop: 'static'});
}

function editDiagnosa(id, idDiagnosa, jenis, ket) {
    $('#t_diagnosa').text("Edit Diagnosa");
    var jenisPasien = $('#jenis_pasien').val();
    $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
    $('#nosa_id_diagnosa').val(idDiagnosa);
    $('#nosa_ket_diagnosa').val(ket);
    $('#nosa_jenis_diagnosa').val(jenis).trigger('change');
    $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
    $('#modal-diagnosa').modal({show: true, backdrop: 'static'});
}

function showICD9(id){
    $('#id_icd9, #ket_icd9').val('');
    $('#load_icd9').hide();
    $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
    $('#modal-icd9').modal({show: true, backdrop: 'static'});
}

function editICD9(id, idIcd9, ketIcd9) {
    $('#load_icd9, #warning_icd9, #war_id_icd9').hide();
    $('#id_icd9').val(idIcd9);
    $('#id_edit_icd9').val(idIcd9);
    $('#ket_icd9').val(ketIcd9);
    $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
    $('#modal-icd9').modal({show: true, backdrop: 'static'});
}

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

function getListNamaDokter(tipe, id) {
    var option = '<option value="">[Select One]</option>';
    var def = '';
    CheckupAction.getListDokterByNoCheckup(noCheckupPasien, function (res) {
        if (res.length > 0) {
            $.each(res, function (i, item) {
                if (i == 0) {
                    def = item.idDokter + '|' + item.idPelayanan;
                }
                if(id == item.idDokter){
                    def = item.idDokter + '|' + item.idPelayanan;
                }
                option += '<option value="' + item.idDokter + '|' + item.idPelayanan + '">' + item.namaDokter + '</option>';
            });
            $('#tin_id_dokter_dpjp').html(option);
            $('#tin_id_dokter_dpjp').val(def).trigger('change');
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

                var data = [{
                    "id_detail_checkup" : idDetailCheckup,
                    "id_tindakan" : idTindakan,
                    "id_dokter" : idDok,
                    "qty" : qty,
                    "jenis_pasien" : idJenisPeriksa,
                    "id_pelayanan" : idPelayanan
                }];

                var sData = JSON.stringify(data);

                dwr.engine.setAsync(true);
                // TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDok, "RJ", qty, idJenisPeriksa, idPelayanan, null, {
                TindakanRawatAction.saveTindakanRawat(sData, {
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
    getListNamaDokter('edit', idDokter);
    $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
    setTimeout(function () {
        $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
        setTimeout(function () {
            $('#tin_id_tindakan').val(idTindakan).trigger('change');
        },500);
    },500);
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


function searchDiagnosa(id) {
    var menus, mapped;
    $('#' + id).typeahead({
        minLength: 3,
        source: function (query, process) {
            menus = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            CheckupAction.getICD10(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.idDiagnosa + '-' + item.descOfDiagnosa;
                mapped[labelItem] = {
                    id: item.idDiagnosa,
                    label: labelItem,
                    name: item.descOfDiagnosa
                };
                menus.push(labelItem);
            });

            process(menus);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            // insert to textarea diagnosa_ket
            $("#nosa_ket_diagnosa").val(selectedObj.name);
            return selectedObj.id;
        }
    });
}

function saveDiagnosa(id) {
    var idDiag = $('#nosa_id_diagnosa').val();
    var ketDiagnosa = $('#nosa_ket_diagnosa').val();
    var jenisPasien = $('#jenis_pasien').val();
    var jenisDiagnosa = $('#nosa_jenis_diagnosa').val();

    if (idDetailCheckup != '' && idDiag != '' && jenisDiagnosa != '' && ketDiagnosa != '') {
        if (!cekSession()) {
            $('#save_diagnosa').hide();
            $('#load_diagnosa').show();
            if (id != '') {
                dwr.engine.setAsync(true);
                DiagnosaRawatAction.editDiagnosa(id, idDiag, jenisDiagnosa, ketDiagnosa, jenisPasien, idDetailCheckup, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listDiagnosa(idDetailCheckup);
                            // hitungStatusBiaya();
                            $('#modal-diagnosa').modal('hide');
                        } else {
                            $('#warning_diagnosa').show().fadeOut(5000);
                            $('#msg_diagnosa').text(response.msg);
                            $('#save_diagnosa').show();
                            $('#load_diagnosa').hide();
                        }
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                DiagnosaRawatAction.saveDiagnosa(idDetailCheckup, idDiag, jenisDiagnosa, ketDiagnosa, jenisPasien, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listDiagnosa(idDetailCheckup);
                            /*hitungStatusBiaya();*/
                            $('#modal-diagnosa').modal('hide');
                        } else {
                            $('#warning_diagnosa').show().fadeOut(5000);
                            $('#msg_diagnosa').text(response.msg);
                            $('#save_diagnosa').show();
                            $('#load_diagnosa').hide();
                        }
                    }
                })
            }
        }
    } else {
        $('#warning_diagnosa').show().fadeOut(5000);
        $('#msg_diagnosa').text('Silahkan cek kembali data inputan...!');
        if (idDiag == '') {
            $('#war_diagnosa_bpjs').show();
        }
        if (jenisDiagnosa == '') {
            $('#war_jenis_diagnosa').show();
        }
    }
}

function searchICD9(id) {
    var menus, mapped;
    $('#' + id).typeahead({
        minLength: 3,
        source: function (query, process) {
            menus = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            CheckupAction.getICD9(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.idIcd9 + '-' + item.namaIcd9;
                mapped[labelItem] = {
                    id: item.idIcd9,
                    label: labelItem,
                    name: item.namaIcd9
                };
                menus.push(labelItem);
            });

            process(menus);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            $("#ket_icd9").val(selectedObj.name);
            return selectedObj.id;
        }
    });
}

function saveICD9(id) {

    var idIcd9 = $('#id_icd9').val();
    var ketIcd9 = $('#ket_icd9').val();
    var idIcd9Edit = $('#id_edit_icd9').val();
    var jenisPasien = $('#jenis_pasien').val();
    var data = "";

    if (idDetailCheckup != '' && idIcd9 != '' && ketIcd9 != '') {
        if (!cekSession()) {
            data = {
                'id_detail_checkup': idDetailCheckup,
                'jenis_pasien': jenisPasien,
                'id_icd9': idIcd9,
                'nama_icd9': ketIcd9,
                'id_tindakan_rawat_icd9': id,
                'id_edit_icd9': idIcd9Edit
            }

            $('#save_icd9').hide();
            $('#load_icd9').show();
            var result = JSON.stringify(data);

            if (id != '') {
                dwr.engine.setAsync(true);
                TindakanRawatICD9Action.edit(result, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listICD9(idDetailCheckup);
                            //hitungStatusBiaya();
                            $('#modal-icd9').modal('hide');
                            //$('#info_dialog').dialog('open');
                            //$('#close_pos').val(11);
                        } else {
                            $('#save_icd9').show();
                            $('#load_icd9').hide();
                            $('#warning_icd9').show().fadeOut(5000);
                            $('#msg_icd9').text(response.msg);
                        }
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                TindakanRawatICD9Action.save(result, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listICD9(idDetailCheckup);
                            //hitungStatusBiaya();
                            $('#modal-icd9').modal('hide');
                            //$('#info_dialog').dialog('open');
                            //$('#close_pos').val(11);
                        } else {
                            $('#save_icd9').show();
                            $('#load_icd9').hide();
                            $('#warning_icd9').show().fadeOut(5000);
                            $('#msg_icd9').text(response.msg);
                        }
                    }
                })
            }
        }
    } else {
        $('#warning_icd9').show().fadeOut(5000);
        $('#msg_icd9').text("Silahkan cek kembali inputan anda...!");
        if (id == '') {
            $('#war_id_icd9').show();
        }
    }
}

function listSelectTindakan(idKategori) {
    var option = "<option value=''>[Select One]</option>";
    if (idKategori != '') {
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListComboTindakan(idKategori, idKelasRuangan, flagVaksin, idPelayananHeader, jenisPeriksaPasien, {
            callback: function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                    });
                    $('#tin_id_tindakan').html(option);
                } else {
                    $('#tin_id_tindakan').html('');
                }
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
                var tarif = 0;
                if (res.diskon != '' && res.diskon != null) {
                    disk = res.diskon;
                }
                if (jenisPeriksaPasien == "bpjs") {
                    // $('#h_harga').val("Rp. " + formatRupiahAtas(res.tarifBpjs));
                    tarif = res.tarifBpjs;
                } else if (jenisPeriksaPasien == "bpjs_rekanan" || jenisPeriksaPasien == "rekanan"){

                    TindakanRawatAction.getTarifDetailRekanaOps(idDetailCheckup, id, function (res2) {

                        if (jenisPeriksaPasien == "bpjs_rekanan"){
                            disk = res2.diskonBpjs;
                            tarif = res2.tarifBpjs;
                            // $('#h_harga').val("Rp. " + formatRupiahAtas(res2.tarifBpjs));
                        }

                        if (jenisPeriksaPasien == "rekanan"){
                            disk = res2.diskonNonBpjs;
                            tarif = res2.tarif;
                            // $('#h_harga').val("Rp. " + formatRupiahAtas(res2.tarif));
                        }
                    });

                } else {
                    // $('#h_harga').val("Rp. " + formatRupiahAtas(res.tarif));
                    tarif = res.tarif;
                }

                var persen = (100 - disk);
                var after  = persen/100;
                var total  = after*tarif;
                $('#h_harga').val("Rp. " + formatRupiahAtas(Math.round(total)));


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

function showHasil(id, kategori){
    var data = $('#file_'+id).val();
    $('#item_hasil_lab').html('');
    $('#li_hasil_lab').html('');
    if(data != null && data != ''){
        var result = JSON.parse(data);
        $('#title_hasil_lab').html("Hasil Pemeriksaan "+kategori);
        if(result.length > 0){
            var set = '';
            var li = '';
            $.each(result, function (i, item) {
                var cla = 'class="item"';
                var claLi = '';
                if(i == 0){
                    cla = 'class="item active"';
                    claLi = 'class="active"';
                }
                var x = item.urlImg;
                var tipe = x.split('.').pop();
                if("pdf" == tipe){
                    set += '<div ' + cla + '>\n' +
                        '<embed src="'+item.urlImg+'" style="width: 100%; height: 70%"/>'+
                        '</div>';
                }else{
                    set += '<div ' + cla + '>\n' +
                        '<img src="' + item.urlImg + '" style="width: 100%; height: 70%">\n' +
                        '</div>';
                }
                li += '<li data-target="#carousel-hasil_lab" data-slide-to="'+i+'" '+claLi+'></li>';
            });
            $('#item_hasil_lab').html(set);
            $('#li_hasil_lab').html(li);
        }
        $('#modal-hasil_lab').modal({show: true, backdrop:'static'});
    }
}
