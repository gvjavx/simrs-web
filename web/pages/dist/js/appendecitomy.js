function showModalAppendecitomy(jenis) {
    if(isReadRM){
        $('.btn-hide').hide();
    }else{
        $('.btn-hide').show();
    }
    $('#modal-ic-' + jenis).modal({show: true, backdrop: 'static'});
}

function saveAppendecitomy(jenis, ket) {
    var data = [];
    var cek = false;
    if ("appendecitomy_informasi" == jenis) {
        var va1 = $('[name=ai1]:checked').val();
        var va2 = $('[name=ai2]:checked').val();
        var va3 = $('[name=ai3]:checked').val();
        var va4 = $('[name=ai4]:checked').val();
        var va5 = $('[name=ai5]:checked').val();
        var va6 = $('[name=ai6]:checked').val();
        var va7 = $('[name=ai7]:checked').val();
        var va8 = $('[name=ai8]:checked').val();
        var va9 = $('[name=ai9]:checked').val();
        var va10 = $('#ai10').val();
        var va11 = $('[name=ai11]:checked').val();
        var va12 = $('#ai012').val();
        var va13 = $('[name=ai13]:checked').val();

            data.push({
                'parameter': 'Diagnosis (WD dan DD)',
                'jawaban1': 'Appendicities acute',
                'jawaban2': va1,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dasar diagnosis',
                'jawaban1': 'Anamnesa, pemeriksaan klinis, laboratorium',
                'jawaban2': va2,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan Kedokteran',
                'jawaban1': 'Appendectomy',
                'jawaban2': va3,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Tindakan',
                'jawaban1': 'Appendicities acute',
                'jawaban2': va4,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tata Cara',
                'jawaban1': 'Pembiusan, irisan jaringan yang ditindak',
                'jawaban2': va5,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tujuan',
                'jawaban1': 'Kuratif',
                'jawaban2': va6,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Resiko',
                'jawaban1': 'Sedang, Hal-hal lain yang tidak dapat diprediksi sebelumnya',
                'jawaban2': va7,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Komplikasi',
                'jawaban1': 'Infeksi luka operasi, streng ilues dan hal-hal yang tidak dapat diprediksi sebelumnya',
                'jawaban2': va8,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Prognosis',
                'jawaban1': 'Baik',
                'jawaban2': va9,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alternatif',
                'jawaban1': va10,
                'jawaban2': va11,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perkiraan Biaya',
                'jawaban1': va12,
                'jawaban2': va13,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
    }

    if ("appendecitomy_penyataan" == jenis) {
        var va1 = document.getElementById("appendecitomy_penyataan1");
        var va2 = document.getElementById("appendecitomy_penyataan2");
        var va3 = document.getElementById("appendecitomy_penyataan3");
        var nm1 = $('#appen1').val();
        var nm2 = $('#appen2').val();
        var cekVa1 = isBlank(va1);
        var cekVa2 = isBlank(va2);
        var cekVa3 = isBlank(va3);
        if (!cekVa1 && !cekVa2 && !cekVa3 && nm1 && nm2) {

            var ttd1 = va1.toDataURL("image/png"),
                ttd1 = ttd1.replace(/^data:image\/(png|jpg);base64,/, "");
            var ttd2 = va2.toDataURL("image/png"),
                ttd2 = ttd2.replace(/^data:image\/(png|jpg);base64,/, "");
            var ttd3 = va3.toDataURL("image/png"),
                ttd3 = ttd3.replace(/^data:image\/(png|jpg);base64,/, "");

            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya Dokter ' + nm1 + ' telah menerangkan hal-hal di atas secara benar dan jelas dan memberikan kesempatan untuk bertanya dan/atau berdiskusi',
                'jawaban1': ttd1,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan bahwa saya Pasien' + nm2 + ' telah menerima informasi sebagimana di atas yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan untuk bertanya/berdiskusi, dan telah memahaminya',
                'jawaban1': ttd2,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanda tangan saksi',
                'jawaban1': ttd3,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });

            cek = true;
        }
    }

    if ("appendecitomy_persetujuan" == jenis) {
        var pe1 = $('#ape1').val();
        var pe2 = $('#ape2').val();
        var pe3 = $('#ape3').val();
        var pe4 = $('#ape4').val();
        var pe5 = $('#ape5').val();
        var pe6 = $('#ape6').val();
        var pe7 = $('#ape7').val();
        var pe8 = $('#ape8').val();
        var pe9 = $('#ape9').val();
        var pe10 = $('#ape10').val();
        var pe11 = $('#ape11').val();

        if (pe1 && pe2 && pe3 && pe4 && pe5 && pe6 && pe7 && pe8 && pe9 && pe10 != '') {
            data.push({
                'parameter': 'Nama',
                'jawaban1': pe1,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban1': pe2,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe3,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'No KTP',
                'jawaban1': pe4,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe5,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Dengan ini menyatakan',
                'jawaban1': pe6,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban1': pe7,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Nama Pasien',
                'jawaban1': pe8,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tanggal Lahir',
                'jawaban1': pe9,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Kelamin',
                'jawaban1': pe10,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Alamat',
                'jawaban1': pe11,
                'keterangan': jenis,
                'jenis': 'appendecitomy',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_ic_' + jenis).hide();
        $('#load_ic_' + jenis).show();
        dwr.engine.setAsync(true);
        AppendecitomyAction.saveAppendecitomy(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_ic_' + jenis).show();
                    $('#load_ic_' + jenis).hide();
                    $('#modal-ic-' + jenis).modal('hide');
                    $('#warning_ic_' + ket).show().fadeOut(5000);
                    $('#msg_ic_' + ket).text("Berhasil menambahkan data ic...");
                    $('#modal-ic-' + jenis).scrollTop(0);
                } else {
                    $('#save_ic_' + jenis).show();
                    $('#load_ic_' + jenis).hide();
                    $('#warning_ic_' + jenis).show().fadeOut(5000);
                    $('#msg_ic_' + jenis).text(res.msg);
                    $('#modal-ic-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_ic_' + jenis).show().fadeOut(5000);
        $('#msg_ic_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-ic-' + jenis).scrollTop(0);
    }
}

function detailAppendecitomy(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;

        AppendecitomyAction.getListAppendecitomy(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var jwb = "";
                    var jwb2 = "";
                    if (item.jawaban1 != null) {
                        jwb = item.jawaban1;
                    }
                    if (item.jawaban2 != null) {
                        jwb2 = item.jawaban2;
                    }
                    if ("appendecitomy_informasi" == item.keterangan) {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '<td align="center">' + checkIcon(jwb2) + '</td>' +
                            '</tr>';
                    } else if ("appendecitomy_penyataan" == item.keterangan) {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td align="center">' + '<img src="' + jwb + '" style="width: 150px">' + '</td>' +
                            '</tr>';
                    } else {
                        body += '<tr>' +
                            '<td>' + item.parameter + '</td>' +
                            '<td>' + jwb + '</td>' +
                            '</tr>';
                    }
                    cekData = true;
                    tgl = item.createdDate;
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if ("appendecitomy_informasi" == jenis) {
                if (cekData) {
                    head = '<tr style="font-weight: bold"><td width="25%">Jenis Informasi</td><td>Isi Informasi</td><td align="center" width="15%">Check (<i class="fa fa-check"></i>)</td></tr>'
                }
            }

            if ("appendecitomy_persetujuan" == jenis) {
                if (cekData) {
                    first = '<tr>' +
                        '<td colspan="2">Yang bertanda tangan dibawah ini, saya :</td>' +
                        '</tr>';
                    last = '<tr>' +
                        '<td colspan="2">Saya memahami perlunya dan manfaat tindakan tersebut sebagaimana telah dijelaskan seperti di atas kepada saya termasuk resiko dan komplikasi yang timbul. Saya juga menyadari bahwa oleh karena itu ilmu kedokteran bukan ilmu pasti. maka keberhasilan tindakan kedokteran bukan keniscayaan, tetapi bergantung kepada izin Tuhan Yang Maha Esa. Tanggal ' + formaterDate(tgl) + ', Jam ' + formaterTime(tgl) + '</td>' +
                        '</tr>';
                }
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_ic_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_ic_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_ic_' + jenis).attr('src', url);
            $('#btn_ic_' + jenis).attr('onclick', 'delRowAppendecitomy(\'' + jenis + '\')');
        });
    }
}

function delRowAppendecitomy(id) {
    $('#del_ic_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_ic_' + id).attr('src', url);
    $('#btn_ic_' + id).attr('onclick', 'detailAppendecitomy(\'' + id + '\')');
}