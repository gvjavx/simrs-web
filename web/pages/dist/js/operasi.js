$('[name=cek_list14]').click(function () {
    var cek = $('[name=cek_list14]:checked').val();
    if (cek == "Ya") {
        $('#form-ket-checklist').show();
    } else {
        $('#form-ket-checklist').hide();
    }
});

function penandaAreaOperasi() {
    var url = '/simrs/pages/images/penanda-perempuan.jpg';
    console.log(url);
    var canvas = document.getElementById('area_canvas');
    var ctx = canvas.getContext('2d');
    var img = new Image();
    img.src = url;
    img.onload = function (ev) {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(img, 0, 0);
    }
    $('#modal-penanda-area').modal({show: true, backdrop: 'static'});
}

function clearConvas() {
    var canvas = document.getElementById('area_canvas');
    const context = canvas.getContext('2d');
    context.clearRect(0, 0, canvas.width, canvas.height);
    var url = '/simrs/pages/images/penanda-perempuan.jpg';
    console.log(url);
    var canvas = document.getElementById('area_canvas');
    var ctx = canvas.getContext('2d');
    var img = new Image();
    img.src = url;
    img.onload = function (ev) {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(img, 0, 0);
    }
}

function showModalOperasi(jenis) {
    $('#modal-op-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveDataOperasi(jenis, ket) {
    var data = [];
    var cek = false;
    console.log(jenis);

    if ("pre_operasi" == jenis) {
        var cekList1 = $('[name=cek_list1]:checked').val();
        var cekList11 = $('[name=cek_list11]:checked').val();
        var cekList2 = $('[name=cek_list2]:checked').val();
        var cekList22 = $('[name=cek_list22]:checked').val();
        var cekList3 = $('[name=cek_list3]:checked').val();
        var cekList33 = $('[name=cek_list33]:checked').val();
        var cekList4 = $('[name=cek_list4]:checked').val();
        var cekList44 = $('[name=cek_list44]:checked').val();
        var cekList5 = $('[name=cek_list5]:checked').val();
        var cekList55 = $('[name=cek_list55]:checked').val();
        var cekList6 = $('[name=cek_list6]:checked').val();
        var cekList66 = $('[name=cek_list66]:checked').val();
        var cekList7 = $('[name=cek_list7]:checked').val();
        var cekList77 = $('[name=cek_list77]:checked').val();
        var cekList8 = $('[name=cek_list8]:checked').val();
        var cekList88 = $('[name=cek_list88]:checked').val();
        var cekList9 = $('[name=cek_list9]:checked').val();
        var cekList99 = $('[name=cek_list99]:checked').val();
        var cekList10 = $('[name=cek_list10]:checked').val();
        var cekList1010 = $('[name=cek_list1010]:checked').val();
        var cekList11 = $('[name=cek_list11]:checked').val();
        var cekList1111 = $('[name=cek_list1111]:checked').val();
        var cekList12 = $('[name=cek_list12]:checked').val();
        var cekList1212 = $('[name=cek_list1212]:checked').val();
        var cekList13 = $('[name=cek_list13]:checked').val();
        var cekList1313 = $('[name=cek_list1313]:checked').val();
        var cekList14 = $('[name=cek_list14]:checked').val();
        var tCekList14 = "";
        if (cekList14 == "Ya") {
            var checbox = $('[name=cek_ket_list14]');
            $.each(checbox, function (i, item) {
                if (item.checked) {
                    if (tCekList14 != '') {
                        tCekList14 = tCekList14 + ', ' + item.value;
                    } else {
                        tCekList14 = item.value;
                    }
                }
            });
        } else {
            tCekList14 = cekList14;
        }

        var cekList15 = $('#cek_list15').val();
        var cekList16 = $('#cek_list16').val();

        if (tCekList14 && cekList15 && cekList16 != '') {
            data.push({
                'parameter': 'Pembatasan Nutrisi Per Oral(Puasa)',
                'jawaban1': cekList1,
                'jawaban2': cekList11,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Laboratorium',
                'jawaban1': cekList2,
                'jawaban2': cekList22,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan Radiologi (Thorax Foto, USG, Scan)',
                'jawaban1': cekList3,
                'jawaban2': cekList33,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Pemeriksaan ECG',
                'jawaban1': cekList4,
                'jawaban2': cekList44,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Edukasi dan Informed Consent Bedah',
                'jawaban1': cekList5,
                'jawaban2': cekList55,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Penandaan Area Operasi',
                'jawaban1': cekList6,
                'jawaban2': cekList66,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Spesialis Anestesi',
                'jawaban1': cekList7,
                'jawaban2': cekList77,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Edukasi dan Informed Consent Anestesi',
                'jawaban1': cekList8,
                'jawaban2': cekList88,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Kardiologi ACC Operasi dengan Resiko: Ringan/Sedang/berat Cardiac Risk Index Grade I/II/III/IV',
                'jawaban1': cekList9,
                'jawaban2': cekList99,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Konsultasi Dokter Spesialis Penyakit Dalam',
                'jawaban1': cekList10,
                'jawaban2': cekList1010,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Schiren / Cukur',
                'jawaban1': cekList11,
                'jawaban2': cekList1111,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Melepas Perhiasan, Soft Lens, Gigi Palsu, DLL',
                'jawaban1': cekList12,
                'jawaban2': cekList1212,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Persiapan Prosuk Darah',
                'jawaban1': cekList13,
                'jawaban2': cekList1313,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Riwayat Penyakit',
                'jawaban1': tCekList14,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Berat Badan',
                'jawaban1': cekList15 + ' Kg',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tinggi Badan',
                'jawaban1': cekList16 + ' cm',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("kondisi_pasien" == jenis) {
        console.log('tes');
        var cekList171 = $('#cek_list171').val();
        var cekList172 = $('#cek_list172').val();
        var cekList181 = $('#cek_list181').val();
        var cekList182 = $('#cek_list182').val();
        var cekList191 = $('#cek_list191').val();
        var cekList192 = $('#cek_list192').val();
        var cekList201 = $('#cek_list201').val();
        var cekList202 = $('#cek_list202').val();
        var cekList211 = $('#cek_list211').val();
        var cekList212 = $('#cek_list212').val();
        var cekList221 = $('#cek_list221').val();
        var cekList222 = $('#cek_list222').val();
        var cekList231 = $('#cek_list231').val();
        var cekList232 = $('#cek_list232').val();
        var cekList241 = $('#cek_list241').val();
        var cekList242 = $('#cek_list242').val();

        if (cekList171 && cekList172 && cekList181 && cekList182 && cekList191 && cekList192 && cekList201 && cekList202 &&
            cekList211 && cekList212 && cekList221 && cekList222 && cekList231 && cekList232 && cekList241 && cekList242 != '') {
            data.push({
                'parameter': 'Kesadaran Umum',
                'jawaban1': cekList171,
                'jawaban2': cekList172,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kesadaran / GCS',
                'jawaban1': cekList181,
                'jawaban2': cekList182,
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tekanan Darah',
                'jawaban1': cekList191 + ' mmHg',
                'jawaban2': cekList191 + ' mmHg',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Suhu',
                'jawaban1': cekList201 + ' x/menit',
                'jawaban2': cekList202 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nadi',
                'jawaban1': cekList211 + ' x/menit',
                'jawaban2': cekList212 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Respirator',
                'jawaban1': cekList221 + ' x/menit',
                'jawaban2': cekList222 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'DJJ',
                'jawaban1': cekList231 + ' x/menit',
                'jawaban2': cekList232 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Skala Nyeri',
                'jawaban1': cekList241 + ' x/menit',
                'jawaban2': cekList242 + ' x/menit',
                'keterangan': jenis,
                'jenis': 'cek_list_pre_operasi',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
            console.log(data);
        }
    }


    if (cek) {
        var result = JSON.stringify(data);
        $('#save_op_' + jenis).hide();
        $('#load_op_' + jenis).show();
        dwr.engine.setAsync(true);
        AsesmenOperasiAction.saveAsesmenOperasi(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_op_' + jenis).show();
                    $('#load_op_' + jenis).hide();
                    $('#modal-op-' + jenis).modal('hide');
                    $('#warning_op_' + ket).show().fadeOut(5000);
                    $('#msg_op_' + ket).text("Berhasil menambahkan data operasi...");
                    $('#modal-op-' + jenis).scrollTop(0);
                } else {
                    $('#save_op_' + jenis).show();
                    $('#load_op_' + jenis).hide();
                    $('#warning_op_' + jenis).show().fadeOut(5000);
                    $('#msg_op_' + jenis).text(res.msg);
                    $('#modal-op-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_op_' + jenis).show().fadeOut(5000);
        $('#msg_op_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-op-' + jenis).scrollTop(0);
    }
}

function detailOperasi(jenis) {
    if (jenis != '') {
        var body = "";
        var head = "";
        AsesmenOperasiAction.getListAsesmenOperasi(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {

                    if ("pre_operasi" == item.keterangan || "kondisi_pasien" == item.keterangan ) {
                        var jwb1 = "";
                        var jwb2 = "";
                        if (item.jawaban1 != null) {
                            jwb1 = item.jawaban1;
                        }
                        if (item.jawaban2 != null) {
                            jwb2 = item.jawaban2;
                        }
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td align="center">' + checkIcon(jwb1) + '</td>' +
                            '<td align="center">' + checkIcon(jwb2) + '</td>' +
                            '</tr>';
                    } else {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td align="center">' + jwb1 + '</td>' +
                            '</tr>';
                    }

                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if ("pre_operasi" == jenis) {
                head = '<tr>' +
                    '<td><b>Persiapan Pasien Pre Operasi</b></td>' +
                    '<td><b>Perawat Pengirim</b></td>' +
                    '<td><b>Perawat OK</b></td>' +
                    '</tr>';
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tr bgcolor="#ffebcd">' +
                '<tbody>' + body + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_op_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_op_' + jenis));
            var url = '/simrs/pages/images/cancel-flat-new.png';
            $('#btn_op_' + jenis).attr('src', url);
            $('#btn_op_' + jenis).attr('onclick', 'delRowOperasi(\'' + jenis + '\')');
        });
    }
}

function delRowOperasi(id) {
    $('#del_op_' + id).remove();
    var url = '/simrs/pages/images/icons8-plus-25.png';
    $('#btn_op_' + id).attr('src', url);
    $('#btn_op_' + id).attr('onclick', 'detailOperasi(\'' + id + '\')');
}

function checkIcon(val) {
    var fa = val;
    if (val == "Ya") {
        fa = '<i class="fa fa-check"></i>'
    }
    return fa;
}

