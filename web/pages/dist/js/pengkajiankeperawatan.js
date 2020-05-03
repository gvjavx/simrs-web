function showModalPengkajianKep(jenis) {
    if(isReadRM){
        $('.btn-hide').hide();
    }else{
        $('.btn-hide').show();
    }
    $('#modal-puk-' + jenis).modal({show: true, backdrop: 'static'});
    $('.tgl').datepicker({
        dateFormat: 'dd-mm-yy'
    });
    $('.tgl').val(formaterDate(new Date()));
    $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    $('.jam').timepicker();
    $('.jam').val(formaterTime(new Date()));
}

function savePengkajianKep(jenis, ket) {
    var data = [];
    var cek = false;
    if ("resiko_jatuh" == jenis) {
        var va1 = $('#rj1').val();
        var va2 = $('#rj2').val();
        var va3 = $('#rj3').val();
        var va4 = $('#rj4').val();
        var va5 = $('#rj5').val();

        if (va1 && va2 && va3 && va4 && va5 != '') {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Score',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tingkat Resiko',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pencegahan_umum" == jenis) {
        var va1 = $('#pu1').val();
        var va2 = $('#pu2').val();
        var va3 = $('#pu3').val();
        var va4 = $('[name=pu4]:checked').val();
        var va5 = $('[name=pu5]:checked').val();

        if (va1 && va2 && va3 != '' && va4 && va5 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Melakukan orientasi ruangan dan tindakan pencengahan umum',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Berikan edukasi pencegahan jatuh pada pasien dan keluarga',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("resiko_tinggi" == jenis) {
        var va1 = $('#rt1').val();
        var va2 = $('#rt2').val();
        var va3 = $('#rt3').val();
        var va4 = $('[name=rt4]:checked').val();
        var va5 = $('[name=rt5]:checked').val();
        var va6 = $('[name=rt6]:checked').val();

        if (va1 && va2 && va3 != '' && va4 && va5 && va6 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Beri penanda berupa stiker warna kuning di gelang pasien',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Beri pertanda pada bed berbentuk segitiga warna kuning',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Observasi secara teratur setiap pergantian shift',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("nyeri" == jenis) {
        var va1 = $('#yer1').val();
        var va2 = $('#yer2').val();
        var va3 = $('#yer3').val();
        var va4 = $('#yer4').val();
        var va5 = $('#yer5').val();
        var va6 = $('#yer6').val();
        var va7 = $('#yer7').val();
        var va9 = $('#yer9').val();
        var va10 = $('#yer10').val();
        var va11 = $('#yer11').val();
        var va12 = $('#yer12').val();
        var va13 = $('#yer13').val();
        var va14 = $('[name=yer14]:checked').val();
        var va15 = $('[name=yer15]:checked').val();
        var va16 = $('[name=yer16]:checked').val();
        var va17 = $('[name=yer17]:checked').val();
        var va18 = $('[name=yer18]:checked').val();
        var va19 = $('[name=yer19]:checked').val();

        if (va1 != '' && va14 && va15 && va16 && va17 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Scala Nyeri',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Perjalanan',
                'jawaban': va5,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kwantitas',
                'jawaban': va6,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kwalitas',
                'jawaban': va7,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Faktor Pemberat',
                'jawaban': va9,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Faktor Peringan',
                'jawaban': va10,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Time Bound/Jangka waktu',
                'jawaban': va11,
                'kode': '7',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va12,
                'kode': '8',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jam',
                'jawaban': va13,
                'kode': '9',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Reposisi',
                'jawaban': va14,
                'kode': '10',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Kompres hangat',
                'jawaban': va15,
                'kode': '11',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Kompres dingin',
                'jawaban': va16,
                'kode': '12',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Kurangi rangsangan',
                'jawaban': va17,
                'kode': '13',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '5. Alihkan perhatian',
                'jawaban': va18,
                'kode': '14',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '6. Obat obatan',
                'jawaban': va19,
                'kode': '15',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("resiko_kulit" == jenis) {
        var va1 = $('#klt1').val();
        var va2 = $('#klt2').val();
        var va3 = $('#klt3').val();
        var va4 = $('#klt4').val();
        var va5 = $('[name=klt5]:checked').val();
        var va6 = $('[name=klt6]:checked').val();
        var va7 = $('[name=klt7]:checked').val();
        var va8 = $('[name=klt8]:checked').val();

        if (va1 && va2 && va3 && va4 != '' && va5 && va6 && va7 && va8 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kriteria',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Ubah Posisi',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Jaga kebersihan kulit',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Anjurkan intake adekuat',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '4. Pasang kasur anti dekubitus',
                'jawaban': va8,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("restrain" == jenis) {
        var va1 = $('#res1').val();
        var va2 = $('#res2').val();
        var va3 = $('#res3').val();
        var va4 = $('#res4').val();
        var va5 = $('#res5').val();
        var va6 = $('#res6').val();
        var va7 = $('[name=res7]:checked').val();
        var va8 = $('[name=res8]:checked').val();
        var va9 = $('[name=res9]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 && va6 != '' && va7 && va8 && va9 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Indikasi Restrain',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Jenis Restrain',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Dekatkan kebutuhan pasien',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Edukasi pada penunggu',
                'jawaban': va8,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Bantu Keb dasar pasien',
                'jawaban': va9,
                'kode': '7',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pemulangan_pasien" == jenis) {
        var va1 = $('#pep1').val();
        var va2 = $('#pep2').val();
        var va3 = $('#pep3').val();
        var va4 = $('#pep4').val();
        var va5 = $('#pep5').val();
        var va6 = $('[name=pep6]:checked').val();
        var va7 = $('[name=pep7]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 != '' && va6 && va7 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kriteria pemulangan',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Edukasi',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Identifikasi kebutuhan pasien',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if ("pasien_akhir" == jenis) {
        var va1 = $('#pak1').val();
        var va2 = $('#pak2').val();
        var va3 = $('#pak3').val();
        var va4 = $('#pak4').val();
        var va5 = $('#pak5').val();
        var va6 = $('[name=pak6]:checked').val();
        var va7 = $('[name=pak7]:checked').val();
        var va8 = $('[name=pak8]:checked').val();

        if (va1 && va2 && va3 && va4 && va5 != '' && va6 && va7 && va8 != undefined) {
            var tanggal = va2.split("-").reverse().join("-");
            data.push({
                'parameter': 'Tanggal dan Jam',
                'jawaban': va2 + ' ' + va3,
                'kode': '1',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Kebutuhan Khusus',
                'jawaban': va4,
                'kode': '2',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': 'Tindakan',
                'jawaban': va5,
                'kode': '3',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '1. Hub layanan khusus',
                'jawaban': va6,
                'kode': '4',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '2. Pendamping keluarga',
                'jawaban': va7,
                'kode': '5',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            data.push({
                'parameter': '3. Mendoakan',
                'jawaban': va7,
                'kode': '6',
                'waktu': va1,
                'tanggal': tanggal,
                'keterangan': jenis,
                'jenis': 'pengkajian',
                'id_detail_checkup': idDetailCheckup
            });
            cek = true;
        }
    }

    if (cek) {
        var result = JSON.stringify(data);
        $('#save_puk_' + jenis).hide();
        $('#load_puk_' + jenis).show();
        dwr.engine.setAsync(true);
        PengkajianUlangKeperawatanAction.savePengkajianKeperawatan(result, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#save_puk_' + jenis).show();
                    $('#load_puk_' + jenis).hide();
                    $('#modal-puk-' + jenis).modal('hide');
                    $('#warning_puk_' + ket).show().fadeOut(5000);
                    $('#msg_puk_' + ket).text("Berhasil menambahkan data asesmen rawat pukp...");
                    $('#modal-puk-' + jenis).scrollTop(0);
                } else {
                    $('#save_puk_' + jenis).show();
                    $('#load_puk_' + jenis).hide();
                    $('#warning_puk_' + jenis).show().fadeOut(5000);
                    $('#msg_puk_' + jenis).text(res.msg);
                    $('#modal-puk-' + jenis).scrollTop(0);
                }
            }
        })
    } else {
        $('#warning_puk_' + jenis).show().fadeOut(5000);
        $('#msg_puk_' + jenis).text("Silahkan cek kembali data inputan anda...!");
        $('#modal-puk-' + jenis).scrollTop(0);
    }
}

function detailPengkajianKep(jenis) {
    if (jenis != '') {
        var head = "";
        var body = "";
        var totalSkor = 0;
        var first = "";
        var last = "";
        var tgl = "";
        var cekData = false;

        PengkajianUlangKeperawatanAction.getListPengkajianKeperawatan(idDetailCheckup, jenis, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var pagi = "";
                    var siang = "";
                    var malam = "";
                    if (item.pagi != null) {
                        pagi = item.pagi;
                    }
                    if (item.siang != null) {
                        siang = item.siang;
                    }
                    if (item.malam != null) {
                        malam = item.malam;
                    }

                    body += '<tr>' +
                        '<td>' + item.parameter + '</td>' +
                        '<td>' + set(pagi) + '</td>' +
                        '<td>' + set(siang) + '</td>' +
                        '<td>' + set(malam) + '</td>' +
                        '</tr>';

                    cekData = true;
                    tgl = item.createdDate;
                });
            } else {
                body = '<tr>' +
                    '<td>Data belum ada</td>' +
                    '</tr>';
            }

            if (cekData) {
                head = '<tr>' +
                    '<td><b>Pengkajian</b></td>' +
                    '<td width="17%"><b>Pagi</b></td>' +
                    '<td width="17%"><b>Siang</b></td>' +
                    '<td width="17%"><b>Malam</b></td>' +
                    '</tr>';
            }

            var table = '<table style="font-size: 12px" class="table table-bordered">' +
                '<thead>' + head + '</thead>' +
                '<tbody>' + first + body + last + '</tbody>' +
                '</table>';

            var newRow = $('<tr id="del_puk_' + jenis + '"><td colspan="2">' + table + '</td></tr>');
            newRow.insertAfter($('table').find('#row_puk_' + jenis));
            var url = contextPath + '/pages/images/minus-allnew.png';
            $('#btn_puk_' + jenis).attr('src', url);
            $('#btn_puk_' + jenis).attr('onclick', 'delRowPengkajianKep(\'' + jenis + '\')');
        });
    }
}

function delRowPengkajianKep(id) {
    $('#del_puk_' + id).remove();
    var url = contextPath + '/pages/images/icons8-plus-25.png';
    $('#btn_puk_' + id).attr('src', url);
    $('#btn_puk_' + id).attr('onclick', 'detailPengkajianKep(\'' + id + '\')');
}