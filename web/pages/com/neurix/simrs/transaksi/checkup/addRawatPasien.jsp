<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/addrawatpasien.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PaketPeriksaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>

    <script type='text/javascript'>

        function confirm() {

            var noBpjs = $('#no_bpjs').val();
            var idPasien = $('#id_pasien').val();
            var noKtp = $('#no_ktp').val();
            var namaPasien = $('#nama_pasien').val();
            var jenisKelamin = $('#jenis_kelamin').val();
            var tempatLahir = $('#tempat_lahir').val();
            var tglLahir = $('#tgl_lahir').val();
            var jalan = $('#jalan').val();
            var suku = $('#suku').val();
            var profesi = $('#profesi').val();
            var agama = $('#agama').val();
            var poli = $('#poli').val();
            var dokter = $('#dokter').val();
            var penjamin = $('#penjamin').val();
            var provinsi = $('#provinsi11').val();
            var kota = $('#kabupaten11').val();
            var kecamatan = $('#kecamatan11').val();
            var desa = $('#desa11').val();
            var imgInp = $('#imgInp').val();

            var tipe = $('#jenis_pasien').val();

            var pembayaran = $('#pembayaran').val();
            var uangMuka = $('#uang_muka').val();

            var diagnosaBpjs = $('#diagnosa_awal').val();
            var perujuk = $('#perujuk').val();
            var ketPerujuk = $('#intansi_perujuk').val();
            var noRujukan = $('#no_rujukan').val();
            var ppkRujukan = $('#ppk_rujukan').val();
            var tglRujukan = $('#tgl_rujukan').val();
            var fotoRujukan = $('#url_do').val();
            var statusBpjs = $('#status_bpjs').val();
            var statusRujukan = $('#status_rujukan').val();

            var isLaka = $('#is_laka').val();
            var asuransi = $('#id_asuransi').val();
            var noKartu = $('#no_kartu').val();
            var coverBiaya = $('#nominal_cover_biaya').val();

            var noKartuPtpn = $('#no_kartu_ptpn').val();
            var unitPtpn = $('#unit_ptpn').val();
            var unitPg = $('#unit_pg').val();
            var cekBpjs = $('#cek_is_bpjs').val();
            var poliUmum = $('#poli_umum').val();
            var cekEksekutif = $('#cek_poli_eksekutif').val();
            var isDaftarPJ = $('#is_daftar_pj').val();
            var cekUangMuka = $('#cek_is_uang_muka').is(':checked');
            var idPemeriksaan = $('#id_lab').val();
            var statusPerkawinan = $('#status_perkawinan').val();
            var pendidikan = $('#pendidikan').val();

            if(isDaftarPJ == "Y"){
                dokter = "N";
            }

            var cekNik = true;
            noKtp = replaceUnderLine(noKtp);
            if(noKtp.length == 6){
                for (var i = 0; i < noKtp.length; i++) {
                    if(noKtp.charAt(i) != "0"){
                        cekNik = false;
                    }
                }
                if(!cekNik){
                    $('#war_nik_pasien').show();
                }else{
                    $('#war_nik_pasien').hide();
                }
            }else{
                if(noKtp.length == 16){
                    $('#war_nik_pasien').hide();
                }else{
                    $('#war_nik_pasien').show();
                    cekNik = false;
                }
            }

            if (idPasien != '' && cekNik && namaPasien != '' && jenisKelamin != '' && tempatLahir != ''
                && tglLahir != '' && agama != '' && poli != '' && dokter != '' && penjamin != ''
                && provinsi != '' && kota != '' && kecamatan != '' && desa != '' && tipe != '' && jalan != '' && statusPerkawinan != '' && pendidikan != '') {

                PasienAction.getDataPasien(idPasien, function (res) {
                    if(res.idPasien != '' && res.idPasien != null){
                        if (tipe == "umum") {
                            if(cekEksekutif == 'Y'){
                                $('#confirm_dialog').dialog('open');
                            }else{
                                if("Y" == isDaftarPJ){
                                    if(idPemeriksaan != '' && idPemeriksaan != null){
                                        if(cekUangMuka){
                                            if (pembayaran != '' && uangMuka != '' && parseInt(uangMuka) > 0) {
                                                $('#confirm_dialog').dialog('open');
                                            }else{
                                                $("html, body").animate({scrollTop: 0}, 600);
                                                $('#warning_pasien').show().fadeOut(10000);
                                                $('#msg_pasien').text("Silahkan cek kembali data pembayaran...!");
                                                if (pembayaran == '') {
                                                    $('#war_pembayaran').show();
                                                }
                                                if (uangMuka == '' || parseInt(uangMuka) == 0) {
                                                    $('#war_uang_muka').show();
                                                }
                                            }
                                        }else{
                                            $('#confirm_dialog').dialog('open');
                                        }
                                    }else{
                                        $("html, body").animate({scrollTop: 0}, 600);
                                        $('#warning_pasien').show().fadeOut(10000);
                                        $('#msg_pasien').text("Silahkan cek kembali data jenis pemeriksaan...!");
                                        if (idPemeriksaan == '' || idPemeriksaan == null) {
                                            $('#war_lab').show();
                                        }
                                    }
                                }else{
                                    if (pembayaran != '' && uangMuka != '' && parseInt(uangMuka) > 0) {
                                        $('#confirm_dialog').dialog('open');
                                    } else {
                                        $("html, body").animate({scrollTop: 0}, 600);
                                        $('#warning_pasien').show().fadeOut(10000);
                                        $('#msg_pasien').text("Silahkan cek kembali data pembayaran...!");
                                        if (pembayaran == '') {
                                            $('#war_pembayaran').show();
                                        }
                                        if (uangMuka == '' || parseInt(uangMuka) == 0) {
                                            $('#war_uang_muka').show();
                                        }
                                    }
                                }
                            }
                        }

                        if (tipe == "bpjs" || tipe == "bpjs_rekanan") {
                            if (diagnosaBpjs != '' && perujuk != '' && ketPerujuk != ''
                                && noRujukan != '' && ppkRujukan != '' && tglRujukan != '' && fotoRujukan != '') {
                                if (statusBpjs != '' && statusRujukan != '') {
                                    if (statusBpjs == "aktif" && statusRujukan == "aktif") {
                                        if(tipe == "bpjs_rekanan"){
                                            if (noKartuPtpn != '' && unitPtpn != '') {
                                                $('#confirm_dialog').dialog('open');
                                            } else {
                                                $("html, body").animate({scrollTop: 0}, 600);
                                                $('#warning_pasien').show().fadeOut(10000);
                                                $('#msg_pasien').text("Silahkan cek kembali Data Rekanan...!");
                                                if (noKartuPtpn == '') {
                                                    $('#war_no_kartu_ptpn').show();
                                                }
                                                if (unitPtpn == '') {
                                                    $('#war_ptpn').show();
                                                }
                                            }
                                        }else{
                                            $('#confirm_dialog').dialog('open');
                                        }
                                    } else {
                                        var msg1 = "";
                                        var msg2 = "";
                                        $("html, body").animate({scrollTop: 0}, 600);
                                        $('#warning_pasien').show().fadeOut(10000);
                                        if (statusBpjs != "aktif") {
                                            msg1 = "No BPJS Tidak Aktif";
                                        }
                                        if (statusRujukan != "aktif") {
                                            msg2 = "No Rujukan Tidak Aktif";
                                        }
                                        $('#msg_pasien').text("Mohon maaf transaksi gagal, dikarenakan " + msg1 + ". " + msg2 + "...!");
                                    }
                                } else {
                                    $("html, body").animate({scrollTop: 0}, 600);
                                    $('#warning_pasien').show().fadeOut(10000);
                                    $('#msg_pasien').text("Silahkan klik tombol check untuk melakukan validasi No BPJS dan No Rujukan...!");
                                }
                            } else {
                                $("html, body").animate({scrollTop: 0}, 600);
                                $('#warning_pasien').show().fadeOut(10000);
                                $('#msg_pasien').text("Silahkan cek kembali data diagnosa awal dan data rujukan...!");
                                if (diagnosaBpjs == '') {
                                    $('#diagnosa_awal').css('border', 'solid 1px red');
                                }
                                if (perujuk == '') {
                                    $('#war_perujuk').show();
                                }
                                if (ketPerujuk == '') {
                                    $('#war_ket_perujuk').show();
                                }
                                if (noRujukan == '') {
                                    $('#war_no_rujukan').show();
                                }
                                if (ppkRujukan == '') {
                                    $('#war_ppk_rujukan').show();
                                }
                                if (tglRujukan == '') {
                                    $('#war_tgl_rujukan').show();
                                }
                                if (fotoRujukan == '') {
                                    $('#war_foto_rujukan').show();
                                }
                            }
                        }

                        if (tipe == "asuransi") {
                            // if(asuransi != '' && coverBiaya != ''){
                            if (asuransi != '') {

                                if (isLaka == "Y") {
                                    $('#confirm_dialog').dialog('open');
                                } else {
                                    if (noKartu != '') {
                                        $('#confirm_dialog').dialog('open');
                                    } else {
                                        $("html, body").animate({scrollTop: 0}, 600);
                                        $('#warning_pasien').show().fadeOut(10000);
                                        $('#msg_pasien').text("Silahkan cek kembali data asuransi...!");
                                        if (noKartu == '') {
                                            $('#war_no_asuransi').show();
                                        }
                                    }
                                }
                            } else {
                                $("html, body").animate({scrollTop: 0}, 600);
                                $('#warning_pasien').show().fadeOut(10000);
                                $('#msg_pasien').text("Silahkan cek kembali data asuransi...!");
                                if (asuransi == '') {
                                    $('#war_asuransi').show();
                                }
                                if (noKartu == '') {
                                    $('#war_no_asuransi').show();
                                }
                                if (coverBiaya == '') {
                                    $('#war_jml_cover').show();
                                }
                            }
                        }

                        if (tipe == "rekanan") {
                            if (noKartuPtpn != '' && unitPtpn != '') {
                                $('#confirm_dialog').dialog('open');
                            } else {
                                $("html, body").animate({scrollTop: 0}, 600);
                                $('#warning_pasien').show().fadeOut(10000);
                                $('#msg_pasien').text("Silahkan cek kembali Data Rekanan...!");
                                if (noKartuPtpn == '') {
                                    $('#war_no_kartu_ptpn').show();
                                }
                                if (unitPtpn == '') {
                                    $('#war_ptpn').show();
                                }
                            }
                        }

                        if (tipe == "paket_perusahaan" || tipe == "paket_individu") {
                            $('#confirm_dialog').dialog('open');
                        }
                    }else{
                        $("html, body").animate({scrollTop: 0}, 600);
                        $('#warning_pasien').show().fadeOut(15000);
                        $('#msg_pasien').text("Data pasien atas Nama "+namaPasien+" belum terdaftar sebagai pasien. Mohon didaftarkan terlabih dahulu dengan menekan tombol Pasien Baru");
                        $('#btn_new').removeClass("btn btn-success pull-right");
                        $('#btn_new').addClass("btn btn-success pull-right blink_me_atas");
                    }
                });

            } else {

                $("html, body").animate({scrollTop: 0}, 600);
                $('#warning_pasien').show().fadeOut(10000);
                $('#msg_pasien').text("Silahkan cek kembali inputan data pasien...!");

                if (idPasien == '') {
                    $('#id_pasien').css('border', 'red solid 1px');
                }
                if (!cekNik) {
                    $('#no_ktp').css('border', 'red solid 1px');
                    $('#war_nik_pasien').show();
                }
                if (namaPasien == '') {
                    $('#nama_pasien').css('border', 'red solid 1px');
                }
                if (jenisKelamin == '') {
                    $('#jenis_kelamin').css('border', 'red solid 1px');
                }
                if (tempatLahir == '') {
                    $('#tempat_lahir').css('border', 'red solid 1px');
                }
                if (tglLahir == '') {
                    $('#st_tgl_lahir').css('border', 'red solid 1px');
                }
                if (agama == '') {
                    $('#agama').css('border', 'red solid 1px');
                }
                if (poli == '') {
                    $('#war_poli').show();
                }
                if (dokter == '' || dokter == null) {
                    $('#war_dokter').show();
                }
                if (penjamin == '') {
                    $('#war_penjamin').show();
                }
                if (provinsi == '') {
                    $('#provinsi').css('border', 'red solid 1px');
                }
                if (kota == '') {
                    $('#kabupaten').css('border', 'red solid 1px');
                }
                if (kecamatan == '') {
                    $('#kecamatan').css('border', 'red solid 1px');
                }
                if (desa == '') {
                    $('#desa').css('border', 'red solid 1px');
                }
                if (imgInp == '') {
                    $('#img_file').css('border', 'red solid 1px');
                }
                if (statusPerkawinan == '') {
                    $('#status_perkawinan').css('border', 'red solid 1px');
                }
                if (pendidikan == '') {
                    $('#pendidikan').css('border', 'red solid 1px');
                }
                if (jalan == '') {
                    $('#jalan').css('border', 'red solid 1px');
                }
                if(tipe == '' || tipe == null){
                    $('#war_jp').show();
                }
            }


        }

        $.subscribe('beforeProcessSave', function (event, data) {
            event.originalEvent.options.submit = true;
            $('#confirm_dialog').dialog('close');
            $.publish('showDialog');
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function resetField(input) {
            var noCheckup = $('#no_checkup').val();
            if (input != 1) {
                var idPasien = $('#id_pasien').val();
                var idPelayanan = $('#h_id_pelayanan').val();
                var jenisPasien = $('#jenis_pasien').val();
                var isDaftarPJ = $('#is_daftar_pj').val();
                if(noCheckup != '' && noCheckup != null){
                    var form = { "headerCheckup.idPasien": $('#id_pasien').val()};
                    var host = contextPathHeader+'/checkup/search_checkup.action';
                    postAtas(host, form);
                }else{
                    if(jenisPasien != 'paket_perusahaan' && input != 1 && "N" == isDaftarPJ){
                        window.open('printNoAntrian_checkup.action?id='+idPasien+'&tipe='+idPelayanan, '_blank');
                    }
                }

                $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #img_file, #provinsi, #kabupaten, #kecamatan, #desa').css('border', '');
                $('#id_online').val(null);
                $('#pembayaran').val(null);
                $('#uang_muka').val(null);
                $('#diagnosa_awal').val(null);
                $('#intansi_perujuk').val(null);
                $('#no_rujukan').val(null);
                $('#ppk_rujukan').val(null);
                $('#tgl_rujukan').val(null);
                $('#url_do').val(null);
                $('#status_bpjs').val(null);
                $('#status_rujukan').val(null);
                $('#is_laka').val(null);
                $('#id_asuransi').val(null);
                $('#no_kartu').val(null);
                $('#nominal_cover_biaya').val(null);
                $('#no_kartu_ptpn').val(null);
                $('#unit_ptpn').val(null);
                $('#unit_pg').val(null);
                $('#cek_is_bpjs').val(null);
                $('#poli').val(null).trigger('change');
                $('#nama_dokter').val(null);
                $('#asuransi').val(null);
                $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #tgl_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #provinsi11, #kabupaten11, #kecamatan11, #desa11, #provinsi, #kabupaten, #kecamatan, #desa, #nama_penanggung, #no_telp, #hubungan').val(null);
                var img = '<s:url value="/pages/images/ktp-default.jpg"/>';
                $('#img-upload').attr('src', img);
                $('#imgInp').attr('value', null);
                $('#kelas_pasien').val(null);
                $('#no_mr').val(null);
                $('#idPelayananBpjs').val(null);
                $('#id_checkup_online').val(null);
                $('#tanggal_rujukan').val(null);
                $('#h_no_kartu').val(null);
                $('#h_id_asuransi').val(null);
                $('#h_no_rujukan').val(null);
                $('#id_paket').val(null);
                $('#cover_biaya_paket').val(null);
                $('#pembayaran').val(null);
                $('#uang_muka_val').val(null);
                $('#uang_muka').val(null);
                $('#url_do').val(null);

                $('#tgl_rujukan').val(null);
                $('#ppk_rujukan').val(null);
                $('#intansi_perujuk').val(null);
                $('#no_rujukan').val(null);
                $('#no_kartu_ptpn').val(null);
                $('#unit_pg').val(null);

                $('#unit_ptpn').val(null);
                $('#cek_is_bpjs').val(null);
                $('#foto_surat').val(null);
                $('#tanggal_kejadian').val(null);
                $('#no_polisi').val(null);
                $('#no_kartu').val(null);
                $('#asuransi').val(null);
                $('#kunjungan_val').val(null);
                $('#paket_perusahaan').val(null);
                $('#paket').val(null);
                $('#paket').attr("disabled", false);
                $('#dokter').val(null);
                $('#nama_dokter').val(null);
                $('#id_lab').val(null);
                $('#poli').val(null);
                $('#hubungan').val(null);
                $('#no_telp').val(null);
                $('#nama_penanggung').val(null);

                $('#img_ktp').val(null);
                $('#diagnosa_awal').val(null);
                $('#diagnosa_ket').val(null);
                $('#is_order_lab').val(null);
                $('#last_id_detail_checkup').val(null);

                $('#is_online').val(null);
                $('#tgl_antrian').val(null);
                $('#is_laka').val(null);
                $('#poli').attr('disabled', false);
                $('#jenis_pasien').attr('disabled', false);

                $('#ket_hubungan').hide();
                $('#form_jawa').hide();
                $('#form_profesi').hide();

                $('#suku, #profesi, #pendidikan, #status_perkawinan, #hubungan, #asuransi').val(null).trigger('change');
                $('#hub_keluarga, #ket_suku, #ket_profesi, #kunjungan_poli').val(null);
                $('#alert-pasien').hide();
                $('#id_online').attr('readonly', false);
                $('#jenis_pasien').attr('disabled', false);
                $('#id_pasien').attr('readonly', false);
                $('#no_bpjs').attr('readonly', false);
                $('#btn-finger').hide();
                $('#nama_dokter').val(null);
                $('#status_bpjs').val(null);
                $('#warn-bpjs').html('');
                $('#warn_rujukan').html('');
                $('#status_rujukan').val(null);
                $('#url_do').val(null);
                $('#surat_polisi, #surat_rujuk').val(null);
                $('#warning_pasien').hide();
                $('#form_vaksin').hide();
            }

            var url_string = window.location.href;
            var url = new URL(url_string);
            var idPasien = url.searchParams.get("idPasien");
            if(idPasien != null && idPasien != ''){
                window.location.reload(true);
            }
        }

        function resetAllField(input) {
            $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #img_file, #provinsi, #kabupaten, #kecamatan, #desa').css('border', '');
            $('#id_online').val(null);
            $('#pembayaran').val(null);
            $('#uang_muka').val(null);
            $('#diagnosa_awal').val(null);
            $('#intansi_perujuk').val(null);
            $('#no_rujukan').val(null);
            $('#ppk_rujukan').val(null);
            $('#tgl_rujukan').val(null);
            $('#url_do').val(null);
            $('#status_bpjs').val(null);
            $('#status_rujukan').val(null);
            $('#is_laka').val(null);
            $('#id_asuransi').val(null);
            $('#no_kartu').val(null);
            $('#nominal_cover_biaya').val(null);
            $('#no_kartu_ptpn').val(null);
            $('#unit_ptpn').val(null);
            $('#unit_pg').val(null);
            $('#cek_is_bpjs').val(null);
            $('#poli').val(null).trigger('change');
            $('#nama_dokter').val(null);
            $('#asuransi').val(null);
            $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #tgl_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #provinsi11, #kabupaten11, #kecamatan11, #desa11, #provinsi, #kabupaten, #kecamatan, #desa, #nama_penanggung, #no_telp, #hubungan').val(null);
            var img = '<s:url value="/pages/images/ktp-default.jpg"/>';
            $('#img-upload').attr('src', img);
            $('#imgInp').attr('value', null);
            $('#kelas_pasien').val(null);
            $('#no_mr').val(null);
            $('#idPelayananBpjs').val(null);
            $('#id_checkup_online').val(null);
            $('#tanggal_rujukan').val(null);
            $('#h_no_kartu').val(null);
            $('#h_id_asuransi').val(null);
            $('#h_no_rujukan').val(null);
            $('#id_paket').val(null);
            $('#cover_biaya_paket').val(null);
            $('#pembayaran').val(null);
            $('#uang_muka_val').val(null);
            $('#uang_muka').val(null);
            $('#url_do').val(null);

            $('#tgl_rujukan').val(null);
            $('#ppk_rujukan').val(null);
            $('#intansi_perujuk').val(null);
            $('#no_rujukan').val(null);
            $('#no_kartu_ptpn').val(null);
            $('#unit_pg').val(null);

            $('#unit_ptpn').val(null);
            $('#cek_is_bpjs').val(null);
            $('#foto_surat').val(null);
            $('#tanggal_kejadian').val(null);
            $('#no_polisi').val(null);
            $('#no_kartu').val(null);
            $('#asuransi').val(null);
            $('#kunjungan_val').val(null);
            $('#paket_perusahaan').val(null);
            $('#paket').val(null);
            $('#dokter').val(null);
            $('#nama_dokter').val(null);
            $('#id_lab').val(null);
            $('#poli').val(null);
            $('#hubungan').val(null);
            $('#no_telp').val(null);
            $('#nama_penanggung').val(null);

            $('#img_ktp').val(null);
            $('#diagnosa_awal').val(null);
            $('#diagnosa_ket').val(null);
            $('#is_order_lab').val(null);
            $('#last_id_detail_checkup').val(null);

            $('#is_online').val(null);
            $('#tgl_antrian').val(null);
            $('#is_laka').val(null);
            $('#poli').attr('disabled', false);
            $('#jenis_pasien').attr('disabled', false);

            $('#ket_hubungan').hide();
            $('#form_jawa').hide();
            $('#form_profesi').hide();

            $('#suku, #profesi, #pendidikan, #status_perkawinan, #hubungan, #asuransi').val(null).trigger('change');
            $('#hub_keluarga, #ket_suku, #ket_profesi, #kunjungan_poli').val(null);
            $('#alert-pasien').hide();
            $('#id_online').attr('readonly', false);
            $('#jenis_pasien').attr('disabled', false);
            $('#id_pasien').attr('readonly', false);
            $('#no_bpjs').attr('readonly', false);
            $('#btn-finger').hide();
            $('#nama_dokter').val(null);
            $('#status_bpjs').val(null);
            $('#warn-bpjs').html('');
            $('#warn_rujukan').html('');
            $('#status_rujukan').val(null);
            $('#url_do').val(null);
            $('#surat_polisi, #surat_rujuk').val(null);
            $('#warning_pasien').hide();
            $('#form_vaksin').hide();
            $('#paket').attr("disabled", false);
            $('#paket').val(null);
        }

        function formatRupiah2(angka) {
            var number_string = angka.replace(/[^,\d]/g, '').toString(),
                split = number_string.split(','),
                sisa = split[0].length % 3,
                rupiah = split[0].substr(0, sisa),
                ribuan = split[0].substr(sisa).match(/\d{3}/gi);

            if (ribuan) {
                separator = sisa ? '.' : '';
                rupiah += separator + ribuan.join('.');
            }

            rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
            return rupiah;
        }

        function formatRupiah(angka) {
            if (angka != '' && angka != null && angka > 0) {
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            } else {
                return 0;
            }
        }


    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="content-wrapper">
    <section class="content-header">
        <h1>
            <span id="text_title">Tambah Rawat Pasien</span>
        </h1>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user-plus"></i> <span id="text_sub">Inputan Data Pasien</span></h3>
                            </div>
                            <div class="col-md-6">
                                <div class="btn-group dropdown pull-right">
                                    <button type="button" class="btn btn-default"><i class="fa fa-medkit"></i> Riwayat
                                        Pasien
                                    </button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu pull-left" role="menu" id="riwayat">

                                    </ul>
                                </div>
                                <a type="button" onclick="showFinger()" style="margin-right: 5px" class="btn btn-warning pull-right" id="btnFingerPrint"><i
                                        class="fa fa-plus"></i> With Finger Print</a>
                            </div>
                        </div>
                    </div>
                    <s:form id="addCheckupForm" enctype="multipart/form-data" method="post" namespace="/checkup"
                            action="saveAdd_checkup.action" theme="simple">
                        <div class="box-body">
                            <div class="alert alert-danger alert-dismissible" id="warning_pasien" style="display: none">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_pasien"></p>
                            </div>
                            <div id="warn-bpjs"></div>
                            <input type="hidden" id="status_bpjs">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                                <a id="btn_new" onclick="showPasienBaru()" class="btn btn-success pull-right"><i
                                        class="fa fa-plus"></i> Pasien Baru
                                </a>
                                <a style="display: none" id="btn-finger" onclick="setFingerPrint()"
                                   class="btn btn-warning pull-right"><i class="fa fa-plus"></i> Finger Print
                                </a>
                            </div>
                            <div id="alert-pasien" style="display: none;" class="alert alert-warning alert-dismissible">
                                <button type="button" class="close" onclick="closeAlert()" aria-hidden="true">×</button>
                                <div id="nama-pasien"></div>
                                <div id="tgl-periksa"></div>
                                <div id="tgl_checkup"></div>
                                <input type="hidden" id="date-periksa">
                                <hr>
                                <table style="color: #fff;">
                                    <tr>
                                        <td><strong>Alergi</strong></td>
                                        <td><strong>&nbsp;:&nbsp;</strong></td>
                                        <td>
                                            <div id="alergi"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong>Diagnosa Terakhir</strong></td>
                                        <td><strong>&nbsp;:&nbsp;</strong></td>
                                        <td>
                                            <div id="diagnosa"></div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">NO Checkup
                                                    Online</label>
                                                <div class="col-md-8">
                                                    <div class="input-group">
                                                        <%--Sigit 2021-01-28, Merubah Ke Upercase--%>
                                                        <input class="form-control" id="id_online"
                                                               onchange="searchNoCheckup(this.value)"
                                                               placeholder="Scan No Checkup Online" style="text-transform:uppercase" >
                                                        <div class="input-group-btn">
                                                            <a class="btn btn-success"
                                                               onclick="searchNoCheckup($('#id_online').val())"> <span
                                                                    id="load_online"><i
                                                                    class="fa fa-search"></i> Check</span></a>

                                                            <a class="btn btn-warning" onclick="showPasienOnline()">
                                                                <i class="fa fa-list-ul"></i> List
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Jenis Pasien</label>
                                                <div class="col-md-8">
                                                    <select style="width: 100%" class="form-control select2"
                                                            id="jenis_pasien"
                                                            onchange="setJenisPasien(this.value); inputWarning('war_jp', 'con_jp')"></select>
                                                    <span style="color: red; display: none" id="war_jp"><i
                                                            class="fa fa-times"></i> silahkan pilih jenis pasien</span>
                                                    <span style="color: green; display: none" id="con_jp"><i
                                                            class="fa fa-check"></i> correct</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group" id="form-no-bpjs" style="display: none">
                                                <label class="col-md-4" style="margin-top: 7px">No BPJS</label>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="margin-top: 7px">
                                                        <s:textfield id="no_bpjs" name="headerCheckup.noBpjs"
                                                                     cssClass="form-control" placeholder="input 3 karakter no bpjs"
                                                                     oninput="searchNoBpjs(this.id)"/>
                                                        <div class="input-group-btn" onclick="checkBpjs()">
                                                            <a class="btn btn-success" id="btn_cek_bpjs">
                                                                <span id="btn-cek"><i
                                                                        class="fa fa-search"></i> Check</span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">NO RM</label>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="margin-top: 7px">
                                                        <s:textfield id="id_pasien" name="headerCheckup.idPasien"
                                                                     oninput="searchNoRM(this.id, this.value)" placeholder="input 3 karakter no rm/nama"
                                                                     onkeypress="$(this).css('border','');"
                                                                     cssClass="form-control"/>
                                                        <div class="input-group-btn">
                                                            <a id="btn_reset" class="btn btn-warning" onclick="resetAllField()"><i class="fa fa-refresh"></i> Reset</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">NIK</label>
                                                <div class="col-md-8">
                                                    <s:textfield id="no_ktp" name="headerCheckup.noKtp"
                                                                 oninput="cekNik('no_ktp','war_nik_pasien'); $(this).css('border','')" onkeyup="cekNik('no_ktp','war_nik_pasien');"
                                                                 data-inputmask="'mask': ['9999999999999999']" data-mask=""
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"
                                                    />
                                                    <span style="color: red; display: none;" id="war_nik_pasien"><i
                                                            class="fa fa-times"></i> jika tidak ada nik masukkan angka (0) 6 digit</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                                                <div class="col-md-8">
                                                    <s:textfield id="nama_pasien" name="headerCheckup.nama"
                                                                 onkeypress="$(this).css('border','')"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                                                <div class="col-md-8">
                                                    <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}"
                                                              cssStyle="margin-top: 7px"
                                                              onchange="$(this).css('border','')"
                                                              id="jenis_kelamin" name="headerCheckup.jenisKelamin"
                                                              headerKey="" headerValue=" - "
                                                              cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Tempat Lahir</label>
                                                <div class="col-md-8">
                                                    <s:textfield id="tempat_lahir" name="headerCheckup.tempatLahir"
                                                                 onkeypress="$(this).css('border','')"
                                                                 oninput="setKotaKab(this.id)"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                                                <div class="col-md-8">
                                                    <div class="input-group date" style="margin-top: 7px"
                                                         id="st_tgl_lahir">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl_lahir" name="headerCheckup.stTglLahir"
                                                                     cssClass="form-control tgl_lahir_validasi datemask" cssStyle="cursor: pointer" placeholder="yyyy-mm-dd"
                                                                     onchange="$('#st_tgl_lahir').css('border','')" readonly="true"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Agama</label>
                                                <div class="col-md-8">
                                                    <s:select id="agama" name="headerCheckup.agama"
                                                              list="#{'Islam':'Islam','Kristen':'Kristen','Katolik':'Katolik','Hindu':'Hindu','Buddha':'Buddha','Konghucu':'Konghucu'}"
                                                              onchange="$(this).css('border','')"
                                                              headerKey="" headerValue=" - "
                                                              cssStyle="margin-top: 7px" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Profesi</label>
                                                <div class="col-md-8">
                                                    <s:select id="profesi"
                                                              list="#{'Guru':'Guru','Dokter':'Dokter','Swasta':'Swasta','PNS':'PNS','Lainnya':'Lainnya'}"
                                                              onchange="$('#ket_profesi').val(this.value); $(this).css('border',''); if(this.value == 'Lainnya'){$('#form_profesi').show()}else{$('#form_profesi').hide()} "
                                                              headerKey="" headerValue=" - "
                                                              cssStyle="width: 100%" cssClass="form-control select2"/>
                                                    <s:hidden name="headerCheckup.profesi" id="ket_profesi"></s:hidden>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="display: none" id="form_profesi">
                                            <div class="form-group">
                                                <div class="col-md-offset-4 col-md-8">
                                                    <s:textfield placeholder="Keterangan Profesi"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"
                                                                 oninput="$('#ket_profesi').val(this.value);"></s:textfield>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Suku</label>
                                                <div class="col-md-8">
                                                    <s:select id="suku"
                                                              list="#{'Jawa':'Jawa','Batak':'Batak','Dayak':'Dayak','Asmat':'Asmat','Minahasa':'Minahasa','Melayu':'Melayu','Sunda':'Sunda','Madura':'Madura','Betawi':'Betawi','Bugis':'Bugis','Lainnya':'Lainnya'}"
                                                              onchange="$('#ket_suku').val(this.value); $(this).css('border',''); if(this.value == 'Lainnya'){$('#form_jawa').show()}else{$('#form_jawa').hide()} "
                                                              headerKey="" headerValue=" - "
                                                              cssStyle="width: 100%" cssClass="form-control select2"/>
                                                    <s:hidden name="headerCheckup.suku" id="ket_suku"></s:hidden>
                                                        <%--<s:textfield id="suku" name="headerCheckup.suku"--%>
                                                        <%--onkeypress="$(this).css('border','')"--%>
                                                        <%--cssClass="form-control" cssStyle="margin-top: 7px"/>--%>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="display: none" id="form_jawa">
                                            <div class="form-group">
                                                <div class="col-md-offset-4 col-md-8">
                                                    <s:textfield placeholder="Keterangan Suku" cssClass="form-control"
                                                                 cssStyle="margin-top: 7px"
                                                                 oninput="$('#ket_suku').val(this.value);"></s:textfield>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Status Perkawinan</label>
                                                <div class="col-md-8">
                                                    <s:select id="status_perkawinan"
                                                              name="headerCheckup.statusPerkawinan"
                                                              list="#{'Kawin':'Kawin','Belum Kawin':'Belum Kawin','Duda':'Duda','Janda':'Janda'}"
                                                              onchange="$(this).css('border','')"
                                                              headerKey="" headerValue=" - "
                                                              cssStyle="width: 100%; margin-top: 7px" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Pendidikan</label>
                                                <div class="col-md-8">
                                                    <s:select id="pendidikan" name="headerCheckup.pendidikan"
                                                              list="#{'Belum Sekolah':'Belum Sekolah','SD/Sederajat':'SD/Sederajat','SMP/Sederajat':'SMP/Sederajat','SMA/Sederajat':'SMA/Sederajat','S1':'S1','S2':'S2','S3':'S3'}"
                                                              onchange="$(this).css('border','')"
                                                              headerKey="" headerValue=" - "
                                                              cssStyle="width: 100%; margin-top: 7px" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4">Alamat</label>
                                                <div class="col-md-8">
                                                    <s:textarea id="jalan" rows="3"
                                                                onkeypress="$(this).css('border','')"
                                                                name="headerCheckup.jalan" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4">Provinsi</label>
                                                <div class="col-md-8">
                                                    <s:textfield cssStyle="margin-top: 7px" id="provinsi"
                                                                 name="headerCheckup.namaProvinsi"
                                                                 required="true" disabled="false"
                                                                 onkeypress="$(this).css('border',''); setProvAtas(this.id, 'provinsi11')"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px"
                                                                 id="provinsi11"
                                                                 name="headerCheckup.provinsiId" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Kota</label>
                                                <div class="col-md-8">
                                                    <s:textfield cssStyle="margin-top: 7px" id="kabupaten"
                                                                 name="headerCheckup.namaKota"
                                                                 required="true" disabled="false"
                                                                 onkeypress="$(this).css('border',''); setKabAtas(this.id, 'kabupaten11', 'provinsi11')"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px"
                                                                 id="kabupaten11"
                                                                 name="headerCheckup.kotaId" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Kecamatan</label>
                                                <div class="col-md-8">
                                                    <s:textfield cssStyle="margin-top: 7px" id="kecamatan"
                                                                 name="headerCheckup.namaKecamatan"
                                                                 required="true" disabled="false"
                                                                 onkeypress="$(this).css('border',''); setKecAtas(this.id, 'kecamatan11', 'kabupaten11')"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px"
                                                                 id="kecamatan11"
                                                                 name="headerCheckup.kecamatanId" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Kelurahan/Desa</label>
                                                <div class="col-md-8">
                                                    <s:textfield cssStyle="margin-top: 7px" id="desa"
                                                                 name="headerCheckup.namaDesa"
                                                                 required="true" disabled="false"
                                                                 onkeypress="$(this).css('border',''); setDesAtas(this.id, 'desa11', 'kecamatan11')"
                                                                 cssClass="form-control"/>
                                                    <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                                                 name="headerCheckup.desaId" required="true"
                                                                 disabled="false" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Foto Identitas</label>
                                                <div class="col-md-8">
                                                    <img id="img-upload" width="100%"
                                                         src="<s:url value="/pages/images/ktp-default.jpg"/>"
                                                         style="border: darkgray solid 1px; height: 170px; margin-top: 7px"/>
                                                    <s:hidden name="headerCheckup.urlKtp" id="img_ktp"></s:hidden>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="display: none" id="form_diagnosa_bpjs">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Diagnosa Awal</label>
                                                <div class="col-md-8">
                                                    <s:textfield id="diagnosa_awal" style="margin-top: 7px"
                                                                 name="headerCheckup.diagnosa" autocomplete="off"
                                                                 onkeypress="$(this).css('border',''); searchDiagnosa(this.id)"
                                                                 cssClass="form-control" required="false"/>
                                                    <s:textarea rows="4" id="diagnosa_ket"
                                                                cssStyle="margin-top: 7px" readonly="true"
                                                                name="headerCheckup.namaDiagnosa"
                                                                cssClass="form-control"></s:textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Penanggung Jawab</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4">Nama Penanggung</label>
                                                <div class="col-md-8">
                                                    <s:textfield id="nama_penanggung"
                                                                 name="headerCheckup.namaPenanggung"
                                                                 cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">No HP</label>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="margin-top: 7px">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-phone"></i>
                                                        </div>
                                                        <s:textfield id="no_telp" name="headerCheckup.noTelp"
                                                                     data-inputmask="'mask': ['9999-9999-9999']"
                                                                     data-mask=""
                                                                     cssClass="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Hubungan</label>
                                                <div class="col-md-8">
                                                    <s:select
                                                            list="#{'Ayah':'Ayah','Ibu':'Ibu','Kakak':'Kakak','Adik':'Adik','Sepupu':'Sepupu','Ipar':'Ipar','Anak':'Anak','Suami':'Suami','Istri':'Istri','Lainnya':'Lainnya'}"
                                                            onchange="$('#hub_keluarga').val(this.value); var cek = this.value; if(cek == 'Lainnya'){$('#ket_hubungan').show()}else{$('#ket_hubungan').hide()}"
                                                            id="hubungan"
                                                            headerKey="" headerValue=" - "
                                                            cssClass="form-control select2"/>
                                                </div>
                                                <s:hidden id="hub_keluarga"
                                                          name="headerCheckup.hubunganKeluarga"></s:hidden>
                                            </div>
                                        </div>
                                        <div class="row" style="display: none" id="ket_hubungan">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 7px">Lainnya</label>
                                                <div class="col-md-8">
                                                    <s:textfield oninput="$('#hub_keluarga').val(this.value)"
                                                                 cssClass="form-control" cssStyle="margin-top: 7px"
                                                                 placeholder="Keterangan Hubungan"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="form-rekanan" style="display: none">
                                <div class="box-header with-border"></div>
                                <div class="box-header with-border">
                                    <h3 class="box-title"><i class="fa fa-user"></i> Data Rekanan</h3>
                                </div>
                                <div class="box-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 10px">Nama
                                                        Rekanan</label>

                                                    <div class="col-md-8">
                                                        <select id="unit_ptpn" class="form-control select2"
                                                                style="width: 100%"
                                                                onchange="var warn =$('#war_ptpn').is(':visible'); if (warn){$('#con_ptpn').show().fadeOut(3000);$('#war_ptpn').hide()}; cekPtpn(this.value);">
                                                        </select>
                                                        <span style="color: red; display: none" id="war_ptpn"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_ptpn"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group" id="form_pg" style="display: none">
                                                    <label class="col-md-4" style="margin-top: 10px">PG Unit</label>
                                                    <div class="col-md-8">
                                                        <input class="form-control" id="unit_pg"
                                                               style="margin-top: 7px">
                                                        <span style="color: red; display: none" id="war_pg"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_pg"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 10px">No Kartu
                                                        Rekanan</label>
                                                    <div class="col-md-8">
                                                        <s:textfield id="no_kartu_ptpn"
                                                                     cssStyle="margin-top: 7px" cssClass="form-control"
                                                                     oninput="$('#h_no_kartu').val(this.value)"
                                                                     onkeypress="var warn =$('#war_no_kartu_ptpn').is(':visible'); if (warn){$('#con_no_kartu_ptpn').show().fadeOut(3000);$('#war_no_kartu_ptpn').hide()}"></s:textfield>
                                                        <span style="color: red; display: none"
                                                              id="war_no_kartu_ptpn"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none"
                                                              id="con_no_kartu_ptpn"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="form-rujukan" style="display: none">
                                <div class="box-header with-border"></div>
                                <div class="box-header with-border">
                                    <h3 class="box-title"><i class="fa fa-user"></i> Data Rujukan</h3>
                                </div>
                                <div class="box-body">
                                    <div id="warn_rujukan"></div>
                                    <input type="hidden" id="status_rujukan">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 7px">Perujuk/Asal</label>
                                                    <div class="col-md-8">
                                                        <s:select list="#{'2':'PPK 2 - RS Lain'}"
                                                                  cssStyle="margin-top: 7px"
                                                                  name="headerCheckup.rujuk"
                                                                  onchange="var warn =$('#war_perujuk').is(':visible'); if (warn){$('#con_perujuk').show().fadeOut(3000);$('#war_perujuk').hide()}"
                                                                  id="perujuk"
                                                                  headerKey="1" headerValue="PPK 1 - Puskesmas"
                                                                  cssClass="form-control"/>
                                                        <span style="color: red; display: none" id="war_perujuk"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_perujuk"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 7px">No Rujukan</label>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="margin-top: 7px">
                                                            <s:textfield id="no_rujukan" cssClass="form-control"
                                                                         oninput="$('#h_no_rujukan').val(this.value)"
                                                                         onkeypress="var warn =$('#war_no_rujukan').is(':visible'); if (warn){$('#con_no_rujukan').show().fadeOut(3000);$('#war_no_rujukan').hide()}"></s:textfield>
                                                            <div class="input-group-btn">
                                                                <a class="btn btn-success" onclick="cekNoRujukan()">
                                                                    <span id="btn-cek-rujukan"><i
                                                                            class="fa fa-search"></i> Check</span></a>
                                                            </div>
                                                        </div>
                                                        <span style="color: red; display: none" id="war_no_rujukan"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_no_rujukan"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 7px">Keterangan
                                                        Perujuk</label>
                                                    <div class="col-md-8">
                                                        <s:textfield id="intansi_perujuk" readonly="true"
                                                                     name="headerCheckup.ketPerujuk"
                                                                     cssClass="form-control" cssStyle="margin-top: 7px"
                                                                     onkeypress="var warn =$('#war_ket_perujuk').is(':visible'); if (warn){$('#con_ket_perujuk').show().fadeOut(3000);$('#war_ket_perujuk').hide()}"/>
                                                        <span style="color: red; display: none" id="war_ket_perujuk"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none"
                                                              id="con_ket_perujuk"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 7px">No PPK
                                                        Rujukan</label>
                                                    <div class="col-md-8">
                                                        <s:textfield name="headerCheckup.noPpkRujukan" id="ppk_rujukan" readonly="true"
                                                                     onkeypress="var warn =$('#war_ppk_rujukan').is(':visible'); if (warn){$('#con_ppk_rujukan').show().fadeOut(3000);$('#war_ppk_rujukan').hide()}"
                                                                     cssClass="form-control"
                                                                     cssStyle="margin-top: 7px"/>
                                                        <span style="color: red; display: none" id="war_ppk_rujukan"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none"
                                                              id="con_ppk_rujukan"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 7px">Tanggal
                                                        Rujukan</label>
                                                    <div class="col-md-8">
                                                        <div class="input-group date" style="margin-top: 7px">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="tgl_rujukan" readonly="true" placeholder="yyyy-mm-dd"
                                                                         cssClass="form-control" cssStyle="cursor: pointer"
                                                                         onchange="var warn =$('#war_tgl_rujukan').is(':visible'); if (warn){$('#con_tgl_rujukan').show().fadeOut(3000);$('#war_tgl_rujukan').hide()}; $('#tanggal_rujukan').val(this.value)"
                                                            />
                                                        </div>
                                                        <span style="color: red; display: none" id="war_tgl_rujukan"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none"
                                                              id="con_tgl_rujukan"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 7px">Foto Surat
                                                        Rujuk</label>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="margin-top: 7px" id="img_url">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file id="url_do" accept=".jpg"
                                                                            name="fileUploadDoc"></s:file>
                                                        </span>
                                                    </span>
                                                            <input type="text" class="form-control" id="surat_rujuk" readonly>
                                                        </div>
                                                        <span style="color: red; display: none" id="war_foto_rujukan"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_foto_rujukan"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Kunjungan</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Pelayanan</label>
                                                <div class="col-md-8">
                                                    <select style="width: 100%" class="form-control select2" id="poli"
                                                            onchange="listDokter(this.value); var warn =$('#war_poli').is(':visible'); if (warn){$('#cor_poli').show().fadeOut(3000);$('#war_poli').hide()}; cekKunjunganPoli(this.value)">
                                                    </select>
                                                    <span style="color: red; display: none" id="war_poli"><i
                                                            class="fa fa-times"></i> required</span>
                                                    <span style="color: green; display: none" id="con_poli"><i
                                                            class="fa fa-check"></i> correct</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group" id="form-lab" style="display: none">
                                                <label class="col-md-4" style="margin-top: 10px">Jenis Pemeriksaan</label>
                                                <div class="col-md-8">
                                                    <select id="id_lab" class="form-control select2"
                                                            style="margin-top: 7px; width: 100%" multiple name="headerCheckup.listPemeriksaan"
                                                            onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#con_lab').show().fadeOut(3000);$('#war_lab').hide()};">
                                                        <option value=''> - </option>
                                                    </select>
                                                    <span style="color: red; display: none" id="war_lab"><i
                                                            class="fa fa-times"></i> required</span>
                                                    <span style="color: green; display: none" id="con_lab"><i
                                                            class="fa fa-check"></i> correct</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="display: none" id="form_dokter_poli">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Dokter</label>
                                                <div class="col-md-8">
                                                    <div class="input-group" style="margin-top: 7px;">
                                                        <input readonly class="form-control" id="nama_dokter"
                                                               style="cursor: pointer"
                                                               placeholder="*klik untuk jadwal dokter">
                                                        <div class="input-group-btn" id="btn-dokter">
                                                            <a class="btn btn-success"><i class="fa fa-search"></i> Dokter</a>
                                                        </div>
                                                    </div>
                                                    <s:hidden name="headerCheckup.idDokter" id="dokter"></s:hidden>
                                                    <span style="color: red; display: none" id="war_dokter"><i
                                                            class="fa fa-times"></i> required</span>
                                                    <span style="color: green; display: none" id="con_dokter"><i
                                                            class="fa fa-check"></i> correct</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="display: none" id="form_eksekutif">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Eksekutif ?</label>
                                                <div class="col-md-8">
                                                    <div class="form-check" style="margin-top: 10px">
                                                        <input onclick="isUangMuka(this.id)" type="checkbox" id="is_uang_muka" value="yes">
                                                        <label for="is_uang_muka"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="form-group" style="display: none" id="form-paket">
                                                <label class="col-md-4" style="margin-top: 10px">Paket</label>
                                                <div class="col-md-8">
                                                    <select id="paket"
                                                            class="form-control select2"
                                                            style="width: 100%"
                                                            onchange="var warn =$('#war_paket').is(':visible'); if (warn){$('#con_paket').show().fadeOut(3000);$('#war_paket').hide()}; selectPelayanan(this.value)">
                                                        <option value=""> - </option>
                                                    </select>
                                                    <span style="color: red; display: none" id="warpaket"><i
                                                            class="fa fa-times"></i> required</span>
                                                    <span style="color: green; display: none" id="con_paket"><i
                                                            class="fa fa-check"></i> correct</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group" style="display: none" id="form-paket-perusahaan">
                                                <label class="col-md-4" style="margin-top: 10px">Paket</label>
                                                <div class="col-md-8">
                                                    <input style="margin-top: 7px" class="form-control"
                                                           id="paket_perusahaan" readonly>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Kunjungan RS</label>
                                                <div class="col-md-8">
                                                    <s:textfield name="headerCheckup.jenisKunjungan"
                                                                 id="kunjungan_val" cssClass="form-control"
                                                                 cssStyle="margin-top: 7px"
                                                                 readonly="true"></s:textfield>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Kunjungan Poli</label>
                                                <div class="col-md-8">
                                                    <s:textfield id="kunjungan_poli" name="headerCheckup.kunjunganPoli"
                                                                 readonly="true" cssStyle="margin-top: 7px"
                                                                 cssClass="form-control"></s:textfield>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="display: none" id="form_vaksin">
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Vaksin ?</label>
                                                <div class="col-md-8">
                                                    <div class="form-check" style="margin-top: 10px">
                                                        <input onclick="isVaksin(this.id)" type="checkbox" id="is_vaksin" value="yes">
                                                        <label for="is_vaksin"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="form-asuransi" style="display: none">
                                <div class="box-header with-border"></div>
                                <div class="box-header with-border">
                                    <h3 class="box-title"><i class="fa fa-user"></i> Silahkan Isi Data Asuransi</h3>
                                </div>
                                <div class="box-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 10px">Asuransi</label>
                                                    <div class="col-md-8">
                                                        <select id="asuransi"
                                                                class="form-control select2"
                                                                style="width: 100%"
                                                                onchange="var warn =$('#war_asuransi').is(':visible'); if (warn){$('#con_asuransi').show().fadeOut(3000);$('#war_asuransi').hide()}; showLaka(this.value);">
                                                            <option value=""> - </option>
                                                        </select>
                                                        <span style="color: red; display: none" id="war_asuransi"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_asuransi"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group" id="form_no_kartu">
                                                    <label class="col-md-4" style="margin-top: 10px">No Kartu
                                                        Asuransi</label>
                                                    <div class="col-md-8">
                                                        <s:textfield autocomplete="off" id="no_kartu"
                                                                     oninput="$('#h_no_kartu').val(this.value)"
                                                                     cssStyle="margin-top: 7px" cssClass="form-control"
                                                                     onkeypress="var warn =$('#war_no_asuransi').is(':visible'); if (warn){$('#con_no_asuransi').show().fadeOut(3000);$('#war_no_asuransi').hide()}"></s:textfield>
                                                        <span style="color: red; display: none" id="war_no_asuransi"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none"
                                                              id="con_no_asuransi"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row" id="form_jasaraharja_1" style="display: none">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 10px">No Polisi</label>
                                                    <div class="col-md-8">
                                                        <s:textfield oninput="$('#h_no_rujukan').val(this.value)"
                                                                     cssStyle="margin-top: 7px"
                                                                     cssClass="form-control"
                                                                     id="no_polisi"
                                                                     onkeypress="var warn =$('#war_no_asuransi').is(':visible'); if (warn){$('#con_no_asuransi').show().fadeOut(3000);$('#war_no_asuransi').hide()}"></s:textfield>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div id="form_jasaraharja_2" style="display: none">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-4" style="margin-top: 7px">Tanggal
                                                            Kejadian</label>
                                                        <div class="col-md-8">
                                                            <div class="input-group date" style="margin-top: 7px">
                                                                <div class="input-group-addon">
                                                                    <i class="fa fa-calendar"></i>
                                                                </div>
                                                                    <s:textfield cssStyle="cursor: pointer" placeholder="yyyy-mm-dd"
                                                                    id="tanggal_kejadian" readonly="true"
                                                                    cssClass="form-control datepicker"
                                                                    onchange="$('#tanggal_rujukan').val(this.value)"></s:textfield>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-4" style="margin-top: 10px">Foto Surat
                                                            Polisi</label>
                                                        <div class="col-md-8">
                                                            <div class="input-group" style="margin-top: 7px">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file accept=".jpg" id="foto_surat"
                                                                            name="fileUploadDocPolisi"></s:file>
                                                        </span>
                                                    </span>
                                                                <input type="text" class="form-control" readonly id="surat_polisi">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <s:hidden name="headerCheckup.kelasPasien" id="kelas_pasien"></s:hidden>
                            <s:hidden name="headerCheckup.noMr" id="no_mr"></s:hidden>
                            <s:hidden name="headerCheckup.idPelayananBpjs" id="idPelayananBpjs"></s:hidden>
                            <s:hidden name="headerCheckup.noCheckupOnline" id="id_checkup_online"></s:hidden>
                            <s:hidden name="headerCheckup.tglRujukan" id="tanggal_rujukan"></s:hidden>
                            <s:hidden name="headerCheckup.noKartuAsuransi" id="h_no_kartu"></s:hidden>
                            <s:hidden name="headerCheckup.idAsuransi" id="h_id_asuransi"></s:hidden>
                            <s:hidden name="headerCheckup.noRujukan" id="h_no_rujukan"></s:hidden>
                            <s:hidden name="headerCheckup.idPaket" id="id_paket"></s:hidden>
                            <s:hidden name="headerCheckup.coverBiaya" id="cover_biaya_paket"></s:hidden>
                            <s:hidden name="headerCheckup.isOnline" id="is_online"/>
                            <s:hidden name="headerCheckup.stTglAntrian" id="tgl_antrian"/>
                            <s:hidden name="headerCheckup.isRekananWithBpjs" id="cek_is_bpjs"></s:hidden>
                            <s:hidden name="headerCheckup.isOrderLab" id="is_order_lab"></s:hidden>
                            <s:hidden name="headerCheckup.lastIdDetailCheckup" id="last_id_detail_checkup"></s:hidden>
                            <s:hidden id="is_laka"></s:hidden>
                            <s:hidden name="headerCheckup.idPelayanan" id="h_id_pelayanan"></s:hidden>
                            <s:hidden name="headerCheckup.idJenisPeriksaPasien" id="h_id_jenis_pasien"></s:hidden>
                            <s:hidden id="h_id_order_lab"></s:hidden>
                            <s:hidden id="cek_poli_eksekutif" name="headerCheckup.isEksekutif"></s:hidden>
                            <s:hidden id="cek_is_vaksin" name="headerCheckup.isVaksin"></s:hidden>
                            <s:hidden id="is_daftar_pj"></s:hidden>
                            <s:hidden id="no_checkup" name="headerCheckup.noCheckup"></s:hidden>
                            <s:hidden name="headerCheckup.idDetailCheckup"></s:hidden>
                            <s:hidden name="headerCheckup.idTeamDokter"></s:hidden>
                            <s:hidden name="headerCheckup.idUangMuka"></s:hidden>

                            <div id="form-uang-muka" style="display: none">
                                <div class="box-header with-border"></div>
                                <div class="box-header with-border">
                                    <h3 class="box-title"><i class="fa fa-money"></i> <span id="text_centang">Uang Muka</span>
                                        <div id="cek_cek" class="form-check" style="display: none">
                                            <input onclick="cekIsUangMuka(this.id)" type="checkbox" id="cek_is_uang_muka" value="yes">
                                            <label for="cek_is_uang_muka"></label>
                                        </div>
                                    </h3>
                                </div>
                                <div class="box-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="row" id="form-nominal_uang_muka">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 10px">Uang Muka</label>
                                                    <div class="col-md-8">
                                                        <div class="input-group" style="margin-top: 7px">
                                                            <div class="input-group-addon">
                                                                Rp.
                                                            </div>
                                                            <s:hidden name="headerCheckup.uangMuka"
                                                                      id="uang_muka_val"></s:hidden>
                                                            <input id="uang_muka" class="form-control"
                                                                   oninput="var warn =$('#war_uang_muka').is(':visible'); if (warn){$('#con_uang_muka').show().fadeOut(3000);$('#war_uang_muka').hide()}; convertRpAtas(this.id, this.value, 'uang_muka_val')"/>
                                                        </div>
                                                        <span style="color: red; display: none" id="war_uang_muka"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_uang_muka"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="row" style="display: none">
                                                <div class="form-group">
                                                    <label class="col-md-4" style="margin-top: 10px"> Metode
                                                        Pembayaran</label>
                                                    <div class="col-md-8">
                                                        <s:select
                                                                list="#{'tunai':'Tunai','non_tunai':'Transfer'}"
                                                                cssStyle="margin-top: 7px"
                                                                id="pembayaran"
                                                                onchange="var warn =$('#war_pembayaran').is(':visible'); if (warn){$('#con_pembayaran').show().fadeOut(3000);$('#war_pembayaran').hide()}"
                                                                name="headerCheckup.metodePembayaran"
                                                                headerKey="" headerValue=" - "
                                                                cssClass="form-control"/>
                                                        <span style="color: red; display: none" id="war_pembayaran"><i
                                                                class="fa fa-times"></i> required</span>
                                                        <span style="color: green; display: none" id="con_pembayaran"><i
                                                                class="fa fa-check"></i> correct</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-header with-border" id="pos_kronis"></div>
                            <div class="alert alert-warning alert-dismissible" id="warning_kronis"
                                 style="display: none">
                                <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                                <p id="msg_kronis"></p>
                            </div>
                            <div class="alert alert-danger alert-dismissible" id="warning_cetak" style="display: none">
                                <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                                <p id="msg_cetak"></p>
                            </div>
                            <div class="alert alert-success alert-dismissible" id="success_kronis"
                                 style="display: none">
                                <h4><i class="icon fa fa-info"></i> Info!</h4>
                                <p id="msg_kronis2"></p>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-md-offset-2 col-md-8 text-center" style="margin-top: 7px">
                                                <button type="button" id="btn-save" class="btn btn-success"
                                                        onclick="confirm()"><i
                                                        class="fa fa-check"></i> <span id="text-save">Save</span>
                                                </button>
                                                <a type="button" id="btn-kronis" style="display:none;"
                                                   class="btn btn-info"
                                                   onclick="initKronis()">
                                                    <i class="fa fa-medkit"></i> Obat Kronis
                                                </a>
                                                <div class="btn-group dropup" style="display: none" id="btn-bpjs">
                                                    <button type="button" class="btn btn-info"><i
                                                            class="fa fa-print"></i> Print Gagal SEP
                                                    </button>
                                                    <button type="button" class="btn btn-info dropdown-toggle"
                                                            data-toggle="dropdown" style="height: 34px">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <li><a style="cursor: pointer"
                                                               onclick="printGagalSEP('SP10')"><i
                                                                class="fa fa-print"></i> Non Aktif</a></li>
                                                        <li><a style="cursor: pointer"
                                                               onclick="printGagalSEP('SP11')"><i
                                                                class="fa fa-print"></i> Kartu Tidak Dibawak</a>
                                                        </li>
                                                        <li><a style="cursor: pointer"
                                                               onclick="printGagalSEP('SP12')"><i
                                                                class="fa fa-print"></i> Kemauan Sendiri</a></li>
                                                        <li><a style="cursor: pointer"
                                                               onclick="printGagalSEP('SP13')"><i
                                                                class="fa fa-print"></i> Masa Pengurusan Denda</a>
                                                        </li>
                                                        <li><a style="cursor: pointer"
                                                               onclick="printGagalSEP('SP14')"><i
                                                                class="fa fa-print"></i> Penangguhan Pasien</a></li>
                                                    </ul>
                                                </div>
                                                <button type="button" class="btn btn-danger"
                                                        onclick="window.location.reload(true)">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </button>
                                                <a type="button" class="btn btn-warning" href="initForm_checkup.action">
                                                    <i class="fa fa-arrow-left"></i> Back
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none">
                                    <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                        <center><img border="0 " style="height: 40px; width: 40px"
                                                     src="<s:url value="/pages/images/icon_warning.ico"/>"
                                                     name="icon_success">
                                            Do you want to save this record?
                                        </center>
                                        <br>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-warning"
                                                    onclick="$('#confirm_dialog').dialog('close')"><i
                                                    class="fa fa-times"></i> No
                                            </button>
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                       formIds="addCheckupForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave"
                                                       onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                <i class="fa fa-check"></i>
                                                yes
                                            </sj:submit>
                                        </div>
                                    </sj:dialog>

                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                               modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Saving ...">
                                        Please don't close this window, server is processing your request ...
                                        <br>
                                        <center>
                                            <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                 src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                 name="image_indicator_write">
                                            <br>
                                            <img class="spin" border="0"
                                                 style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                 src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                 name="image_indicator_write">
                                        </center>
                                    </sj:dialog>

                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                               resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         $('body').scrollTop(0);
                                                                                         resetField();
                                                                                     }
                                                                            }">
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

                                    <sj:dialog id="info_dialog_new" modal="true"
                                               resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog_new').dialog('close');
                                                                                         $('body').scrollTop(0);
                                                                                     }
                                                                            }">
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

                                    <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Error Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                $('#error_dialog').dialog('close');
                                                                                }
                                                                            }"
                                    >
                                        <div class="alert alert-danger alert-dismissible">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                     name="icon_error"> System Found : <p id="errorMessage"></p>
                                            </label>
                                        </div>
                                    </sj:dialog>
                                </div>
                            </div>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="modal-obat-kronis">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Obat Kronis</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_list_kronis">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_list_kronis"></p>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <tr>
                                <td><b>NO RM</b></td>
                                <td><span id="kron_no_rm"></span></td>
                            </tr>
                            <tr>
                                <td><b>Nama</b></td>
                                <td><span id="kron_nama"></span></td>
                            </tr>
                            <tr>
                                <td><b>Terakhir Periksa</b></td>
                                <td><span id="kron_tgl_periksa"></span></td>
                            </tr>
                            <tr>
                                <td><b>Diagnosa Terakhir</b></td>
                                <td><span id="kron_diagnosa_terakhir"></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <table class="table table-bordered table-striped" id="tabel-kronis">
                    <thead>
                    <td>ID Obat</td>
                    <td>Nama Obat</td>
                    <td>Stok Obat</td>
                    <td>Jenis Satuan</td>
                    <td>Keterangan</td>
                    <td>Hari Kronis</td>
                    <td>Qty</td>
                    </thead>
                    <tbody id="body_kronis">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_kronis"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_kronis"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-jadwal-dokter">
    <div class="modal-dialog" style="width: 57%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Jadwal Dokter <span
                        id="dokter_pelayanan"></span> Hari Ini</h4>
            </div>
            <div class="modal-body" id="temp_jd">
                <div class="box-body">
                    <div class="col-md-12 text-center" style="display:inline; padding-left: 6%">
                        <div class="btn-wrapper">
                            <div id="jadwal_dokter"></div>
                        </div>
                    </div>
                    <div class="col-md-offset-9 col-md-3">
                        <ul style="list-style-type: none">
                            <li>
                                <span style="color: white; background-color: #ec971f; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">Kuota Non BPJS</span>
                            </li>
                            <li>
                                <span style="margin-left: 5px;color: white; background-color: #00a65a; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">Kuota BPJS</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <span onclick="cekScrol('fa_temp_jd', 'temp_jd')" class="pull-left hvr-grow" style="color: black; margin-top: 11px; cursor: pointer">
                    <i id="fa_temp_jd" class="fa fa-unlock"></i>
                </span>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-daftar-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Pasien Baru</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>No BPJS</label>
                                <input class="form-control" id="add_no_bpjs" oninput="$(this).css('border','')"
                                       type="number">
                                <%--<div class="input-group">--%>

                                <%--<div class="input-group-addon"--%>
                                <%--onclick="cekBpjs(this.value)" style="cursor:pointer;">--%>
                                <%--<i class="fa fa-search"></i> Check--%>
                                <%--</div>--%>
                                <%--</div>--%>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">NIK</label>
                                <input class="form-control" id="add_nik"
                                       oninput="cekNik('add_nik', 'war_add_nik_pasien'); $(this).css('border','')" onkeyup="cekNik('add_nik', 'war_add_nik_pasien');"
                                       data-inputmask="'mask': ['9999999999999999']" data-mask="">
                                <span style="color: red; display: none; font-size: 12px" id="war_add_nik_pasien"><i
                                        class="fa fa-times"></i> jika tidak ada nik masukkan angka (0) 6 digit</span>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Nama</label>
                                <input class="form-control" id="add_nama" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Jenis Kelamin</label>
                                <select class="form-control" id="add_jk" onchange="$(this).css('border','')">
                                    <option value=""> - </option>
                                    <option value="L">Laki-Laki</option>
                                    <option value="P">Perempuan</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tempat Lahir</label>
                                <input class="form-control" id="add_tempat_lahir"
                                       oninput="$(this).css('border',''); setKotaKab(this.id)">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tanggal Lahir</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl_lahir_validasi datemask" id="add_tanggal_lahir"
                                           onchange="$(this).css('border','')" oninput="$(this).css('border','')">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Status Perkawinan</label>
                                <s:select id="add_status_perkawinan" name="headerCheckup.statusPerkawinan"
                                          list="#{'Kawin':'Kawin','Belum Kawin':'Belum Kawin','Duda':'Duda','Janda':'Janda'}"
                                          onchange="$(this).css('border','')"
                                          headerKey="" headerValue=" - "
                                          cssStyle="width: 100%" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Pendidikan</label>
                                <s:select id="add_pendidikan" name="headerCheckup.pendidikan"
                                          list="#{'Belum Sekolah':'Belum Sekolah','SD/Sederajat':'SD/Sederajat','SMP/Sederajat':'SMP/Sederajat','SMA/Sederajat':'SMA/Sederajat','S1':'S1','S2':'S2','S3':'S3'}"
                                          onchange="$(this).css('border','')"
                                          headerKey="" headerValue=" - "
                                          cssStyle="width: 100%" cssClass="form-control"/>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Agama</label>
                                <select class="form-control" id="add_agama" onchange="$(this).css('border','')">
                                    <option value=""> - </option>
                                    <option value="Islam">Islam</option>
                                    <option value="Kristen">Kristen</option>
                                    <option value="Katolik">Katolik</option>
                                    <option value="Hindu">Hindu</option>
                                    <option value="Konguchu">Konguchu</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Profesi</label>
                                <s:select id="ket_add_profesi"
                                          list="#{'Guru':'Guru','Dokter':'Dokter','Swasta':'Swasta','PNS':'PNS','Lainnya':'Lainnya'}"
                                          onchange="$('#add_profesi').val(this.value); $(this).css('border',''); if(this.value == 'Lainnya'){$('#form_add_profesi').show()}else{$('#form_add_profesi').hide()} "
                                          headerKey="" headerValue=" - "
                                          cssStyle="width: 100%" cssClass="form-control"/>
                                <s:hidden id="add_profesi"></s:hidden>
                            </div>
                            <div class="form-group" style="display: none" id="form_add_profesi">
                                <s:textfield placeholder="Keterangan Profesi" cssClass="form-control"
                                             cssStyle="margin-top: 7px"
                                             oninput="$('#add_profesi').val(this.value);"></s:textfield>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Suku</label>
                                <s:select id="add_ket_suku"
                                          list="#{'Jawa':'Jawa','Batak':'Batak','Dayak':'Dayak','Asmat':'Asmat','Minahasa':'Minahasa','Melayu':'Melayu','Sunda':'Sunda','Madura':'Madura','Betawi':'Betawi','Bugis':'Bugis','Lainnya':'Lainnya'}"
                                          onchange="$('#add_suku').val(this.value); $(this).css('border',''); if(this.value == 'Lainnya'){$('#form_add_suku').show()}else{$('#form_add_suku').hide()} "
                                          headerKey="" headerValue=" - "
                                          cssStyle="width: 100%" cssClass="form-control"/>
                                <s:hidden id="add_suku"></s:hidden>
                            </div>
                            <div class="form-group" style="display: none" id="form_add_suku">
                                <s:textfield placeholder="Keterangan Suku" cssClass="form-control"
                                             cssStyle="margin-top: 7px"
                                             oninput="$('#add_suku').val(this.value);"></s:textfield>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Alamat</label>
                                <input class="form-control" id="add_alamat" oninput="$(this).css('border','')">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">No Telp</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-phone"></i>
                                    </div>
                                    <input class="form-control" id="add_no_telp" oninput="$(this).css('border','')"
                                           data-inputmask="'mask': ['9999-9999-9999']"
                                           data-mask="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Provinsi</label>
                                <input class="form-control" id="add_provinsi"
                                       oninput="$(this).css('border',''); setProvAtas(this.id, 'add_id_provinsi')">
                                <input type="hidden" id="add_id_provinsi">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Kota</label>
                                <input class="form-control" id="add_kota"
                                       oninput="$(this).css('border',''); setKabAtas(this.id, 'add_id_kota', 'add_id_provinsi')">
                                <input type="hidden" id="add_id_kota">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kecamatan</label>
                                <input class="form-control" id="add_kecamatan"
                                       oninput="$(this).css('border',''); setKecAtas(this.id, 'add_id_kecamatan', 'add_id_kota')">
                                <input type="hidden" id="add_id_kecamatan">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Kelurahan/Desa</label>
                                <input class="form-control" id="add_desa"
                                       oninput="$(this).css('border',''); setDesAtas(this.id, 'add_id_desa', 'add_id_kecamatan')">
                                <input type="hidden" id="add_id_desa">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Foto Identitas</label>
                                <div class="input-group">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse…<input type="file" accept=".jpg" name="fileUpload" id="ktp"
                                             onchange="$('#img_file').css('border',''); setCanvasAtas('img_ktp_canvas')">
                              </span>
                              </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <canvas id="img_ktp_canvas"
                                        style="border: solid 1px #ccc; width: 100%; height: 135px"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add" onclick="cekSaveNewPasien()"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-pasien-online">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Daftar Pasien Online</h4>
            </div>
            <div class="modal-body" style="height: 70%; overflow-y: scroll">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-bordered table-striped" style="font-size: 14px">
                                <thead>
                                <tr>
                                    <td>No Checkup Online</td>
                                    <td>No RM</td>
                                    <td>Nama</td>
                                    <td>Pelayanan</td>
                                    <td align="center">Status</td>
                                    <td align="center" width="10%">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_online"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-finger">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-plus"></i> Tambah Pasien Dengan Finger</h4>
            </div>
            <div class="modal-body">
                <s:form id="formFinger" theme="simple" cssClass="form-horizontal">
                    <div class="box-body">
                        <div class="row">
                            <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_finger">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_finger"></p>
                            </div>
                            <div class="form-group">
                                <label class="col-md-offset-1 col-sm-3" for="fin_id">NO BPJS</label>
                                <div class="col-sm-7">
                                    <s:hidden id="fin_id_pas" name="fin_id_pas" cssClass="form-control"/>
                                    <s:textfield id="fin_id" name="fin_id" cssClass="form-control"/>
                                </div>
                                <script type='text/javascript'>
                                    var functions, mapped;
                                    $('#fin_id').typeahead({
                                        minLength: 1,
                                        source: function (query, process) {
                                            functions = [];
                                            mapped = {};

                                            var data = [];
                                            dwr.engine.setAsync(false);
                                            PasienAction.listPasienWithId(query,function (listdata) {
                                                data = listdata;
                                            });

                                            $.each(data, function (i, item) {
                                                var labelItem = item.noBpjs+" "+item.nama;
                                                mapped[labelItem] = {id: item.noBpjs,nama:item.nama, label: labelItem,idPasien:item.idPasien};
                                                functions.push(labelItem);
                                            });
                                            process(functions);
                                        },
                                        updater: function (item) {
                                            var selectedObj = mapped[item];
                                            $('#fin_id_pas').val(selectedObj.idPasien);
                                            $('#fin_nm_pas').val(selectedObj.nama);
                                            return selectedObj.id;
                                        }
                                    });
                                </script>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <label class="col-md-offset-1 col-sm-3"for="fin_id">Nama Pasien</label>
                                <div class="col-sm-7">
                                    <s:textfield id="fin_nm_pas" name="fin_nm_pas" cssClass="form-control" readonly="true"/>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group" style="margin-top: 7px">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="button" onclick="scanFinger()" class="btn btn-success"><i class="fa fa-check"></i> Scan Finger</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript' src='<s:url value="/pages/dist/js/rekammedic.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>

<script type='text/javascript'>

    var contextPath = '<%= request.getContextPath() %>';

    $(document).ready(function () {
        $('#pendaftaran_active, #bayar_rawat_jalan').addClass('active');
        $('#pendaftaran_open').addClass('menu-open');
        listJenisPasien('<s:property value="headerCheckup.idJenisPeriksaPasien"/>');
        setPelayanan('<s:property value="headerCheckup.idPelayanan"/>');

        var idDokter = '<s:property value="headerCheckup.idDokter"/>';
        var namaDokter = '<s:property value="headerCheckup.namaDokter"/>';
        var uangMuka = '<s:property value="headerCheckup.uangMuka"/>';
        var idDetailCheckup = '<s:property value="headerCheckup.idDetailCheckup"/>';
        var isBayarUangMuka = '<s:property value="headerCheckup.statusBayar"/>';
        var suku = '<s:property value="headerCheckup.suku"/>';
        var profesi = '<s:property value="headerCheckup.profesi"/>';
        var hubunganKel = '<s:property value="headerCheckup.hubunganKeluarga"/>';
        var noKartu = '<s:property value="headerCheckup.noKartuAsuransi"/>';
        var jenisPasien = '<s:property value="headerCheckup.idJenisPeriksaPasien"/>';
        var noBpjs = '<s:property value="headerCheckup.noBpjs"/>';

        if(jenisPasien == 'bpjs' || jenisPasien == 'bpjs_rekanan'){
            $('#no_bpjs').attr('readonly', true);
            $('#btn_cek_bpjs').attr('disabled', true);
            checkBpjs();
        }else{
            $('#no_bpjs').attr('readonly', false);
            $('#btn_cek_bpjs').attr('disabled', false);
        }

        if(idDokter != ''){
            $('#dokter').val(idDokter);
        }
        if(namaDokter != ''){
            $('#nama_dokter').val(namaDokter);
        }
        if(uangMuka != ''){
            $('#uang_muka').val(formatRupiah(uangMuka));
            if("Y" == isBayarUangMuka){
                $('#uang_muka').attr('disabled', true);
            }else{
                $('#uang_muka').attr('disabled', false);
            }
        }

        if(idDetailCheckup != ''){
            $('#text-save').text("Edit");
            $('#text_title').text("Edit Data Pasien");
            $('#text_sub').text("Edit Data Pasien");
            $('#btnFingerPrint').hide();
            $('#btn_new').hide();
        }else{
            $('#text-save').text("Save");
            $('#text_title').text("Tambah Data Pasien");
            $('#text_sub').text("Inputan Data Pasien");
            $('#btnFingerPrint').show();
            $('#btn_new').show();
        }

        if(suku != ''){
            $('#suku').val(suku).trigger('change');
        }
        if(profesi != ''){
            $('#profesi').val(profesi).trigger('change');
        }
        if(hubunganKel != ''){
            $('#hubungan').val(hubunganKel).trigger('change');
        }
        if(noKartu != ''){
            $('#no_kartu').val(noKartu);
            $('#no_kartu_ptpn').val(noKartu);
        }


        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        var src = '<s:property value="headerCheckup.urlKtp"/>';

        if (src != '') {
            $('#img-upload').attr('src', src);
        }

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                input.val(log);
                var warn = $('#war_foto_rujukan').is(':visible');
                if (warn) {
                    $('#con_foto_rujukan').show().fadeOut(3000);
                    $('#war_foto_rujukan').hide()
                }
            } else {
                if (log) alert(log);
            }

        });

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#img-upload').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#imgInp").change(function () {
            readURL(this);
        });

        cekPasienUrl();
    });

    function cekNoRujukan() {
        var noRujukan = $('#no_rujukan').val();
        var perujuk = $('#perujuk').val();
        var jenisRujukan = "";

        if (noRujukan != '' && perujuk != '') {
            if (perujuk == '1') {
                jenisRujukan = "P";
            } else if (perujuk == '2') {
                jenisRujukan = "R";
            }

            $('#btn-cek-rujukan').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...')
            dwr.engine.setAsync(true);
            CheckupAction.checkSuratRujukan(noRujukan, jenisRujukan, {
                callback: function (response) {
                    var warnClass = "";
                    var title = "";
                    var msg = "";
                    var icon = "";
                    var val = "";
                    if (response.status == "200") {
                        // $('#idPelayananBpjs').val('IGD');
                        $('#idPelayananBpjs').val(response.kodePoliRujukan);
                        $('#ppk_rujukan').val(response.kdProviderProvUmum);
                        $('#intansi_perujuk').val(response.namaProvPerujuk);
                        $('#tgl_rujukan').val(response.tglKunjungan);
                        $('#tanggal_rujukan').val(response.tglKunjungan);
                        $('#diagnosa_awal').val(response.kodeDiagnosa);
                        $('#diagnosa_ket').val(response.namaDiagnosa);
                        setPelayananByKodeVclaim(response.kodePoliRujukan);
                        val = "aktif";
                        icon = "fa-info";
                        title = "Info!";
                        warnClass = "alert-success";
                        msg = '<p>Nomor Rujukan Berhasil Diverifikasi..! Silahkan dilanjutkan...!</p>' +
                            '<p>Jenis Rawat  : ' + response.namaPelayanan + '</p>' +
                            '<p>Poli Rujukan : ' + response.namaPoliRujukan + '</p>';

                        // const oneDay = 24 * 60 * 60 * 1000;
                        // const firstDate = new Date(response.tglKunjungan);
                        // const secondDate = new Date(2020, 9, 21);
                        // const diffDays = Math.round(Math.abs((firstDate - secondDate) / oneDay));
                        //
                        // if(diffDays == 0){
                        //     val = "aktif";
                        //     icon = "fa-info";
                        //     title = "Info!";
                        //     warnClass = "alert-success";
                        //     msg = '<p>Nomor Rujukan Berhasil Diverifikasi..!</p>' +
                        //         '<p>Jenis Rawat  : ' + response.namaPelayanan + '</p>' +
                        //         '<p>Poli Rujukan : ' + response.namaPoliRujukan + '</p>';
                        // }else{
                        //     if(diffDays <= 90){
                        //     }else{
                        //         val = "tidak ditemukan";
                        //         icon = "fa-warning";
                        //         title = "Warning!";
                        //         warnClass = "alert-danger";
                        //         msg = '<p>Nomor Rujukan Berhasil Diverifikasi..! Surat Rujukan sudah melebihi 90 hari. Silahkan urus kembali surat rujukan...!</p>' +
                        //             '<p>Jenis Rawat  : ' + response.namaPelayanan + '</p>' +
                        //             '<p>Poli Rujukan : ' + response.namaPoliRujukan + '</p>';
                        //     }
                        // }

                    } else {
                        val = "tidak ditemukan";
                        icon = "fa-warning";
                        title = "Warning!";
                        warnClass = "alert-warning";
                        msg = response.message;
                        $('#idPelayananBpjs').val('');
                        $('#ppk_rujukan').val('');
                        $('#intansi_perujuk').val('');
                        $('#tgl_rujukan').val('');
                        $('#diagnosa_awal').val('');
                        $('#diagnosa_ket').val('');
                        $('#tanggal_rujukan').val('');
                    }

                    var warning = '<div class="alert ' + warnClass + ' alert-dismissible">' +
                        '<h4><i class="icon fa ' + icon + '"></i>' + title + '</h4>' + msg +
                        '</div>';

                    $('#status_rujukan').val(val);
                    $('#warn_rujukan').html(warning);
                    $('#btn-cek-rujukan').html('<i class="fa fa-search"></i> Check');
                }
            });
        }
    }

    function listSelectAsuransi(idAsuransi, isLaka) {
        var option = "<option value=''> - </option>";
        CheckupAction.getComboAsuransi(function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    var laka = "";
                    if(item.isLaka != null && item.isLaka != ''){
                        laka = item.isLaka;
                    }
                    option += '<option value="' + item.idAsuransi + '|' + laka + '">' + item.namaAsuransi + '</option>';
                });
                $('#asuransi').html(option);
                if(idAsuransi != '' && idAsuransi != null){
                    $('#asuransi').val(idAsuransi+'|'+isLaka).trigger('change');
                }
            } else {
                $('#asuransi').html(option);
            }
        });
    }

    function listJenisPasien(idJenisPasien) {
        var option = '<option value=""> - </option>';
        CheckupAction.getComboJenisPeriksaPasienWithBpjs(function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += '<option value="' + item.idJenisPeriksaPasien + '">' + item.keterangan + '</option>';
                });
                $('#jenis_pasien').html(option);
            }
            if(idJenisPasien != null && idJenisPasien != '' && idJenisPasien != undefined){
                $('#jenis_pasien').val(idJenisPasien).trigger('change');
                $('#jenis_pasien').attr('disabled', true);
                $('#btn_reset').attr('disabled', true);
                $('#btn_reset').removeAttr('onclick');
            }else{
                $('#jenis_pasien').attr('disabled', false);
                $('#btn_reset').attr('disabled', false);
                $('#btn_reset').attr('onclick', 'resetAllField()');
            }
        });
    }

    function listSelectPaket(idPaket) {
        var option = "<option value=''>[Select One]</option>";
        PaketPeriksaAction.getListPaketPeriksaByTipe("rawat_jalan", function (response) {
            if (response.length > 0) {
                var paket = "";
                $.each(response, function (i, item) {
                    if(idPaket != null && idPaket != ''){
                        paket = item.idPaket + "|" + item.idPelayanan + "|" + item.tarif;
                    }
                    option += "<option value='" + item.idPaket + "|" + item.idPelayanan + "|" + item.tarif + "'>" + item.namaPaket + "</option>";
                });
                $('#paket').html(option);
                if(idPaket != null && idPaket != ''){
                    $('#paket').val(paket).trigger('change');
                }
            } else {
                $('#paket').html(option);
            }
        });
    }

    function listSelectRekanan(isBpjs, idRekanan, isBpjs, tipe) {
        var option = "<option value=''>[Select One]</option>";
        CheckupAction.getListRekananOps(isBpjs, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    var bpjs = "";
                    var tip = "";
                    if(item.isBpjs != null && item.isBpjs != ''){
                        bpjs = item.isBpjs;
                    }
                    if(item.tipe != null && item.tipe != ''){
                        tip = item.tipe;
                    }
                    option += "<option value='" + item.idRekananOps + '|' + bpjs + '|' + tip + "'>" + item.namaRekanan + "</option>";
                });
                $('#unit_ptpn').html(option);
                if(idRekanan != null && idRekanan != ''){
                    $('#unit_ptpn').val(idRekanan+ '|' + isBpjs + '|' + tipe).trigger('change');
                }
            } else {
                $('#unit_ptpn').html(option);
            }
        });
    }

    function cekPtpn(value) {
        if (value != '') {
            var rekanan = value.split("|");
            var idRekananOps = rekanan[0];
            var isBpjs = rekanan[1];
            var tipeRekanan = rekanan[2];

            if (isBpjs == 'Y') {
                $('#form-no-bpjs, #form-rujukan').show();
            } else {
                $('#form-no-bpjs, #form-rujukan').hide();
            }

            if (tipeRekanan == 'ptpn') {
                $('#form_pg').show();
            } else {
                $('#form_pg').hide();
            }

            $('#h_id_asuransi').val(idRekananOps);
            $('#cek_is_bpjs').val(isBpjs);
        }
    }

    function selectPelayanan(value) {
        if (value != '') {
            var isi = value.split("|");
            var pak = isi[0];
            var pel = isi[1];
            var cover = isi[2];
            $('#poli').val(pel).trigger('change').attr('disabled', true);
            $('#id_paket').val(pak);
            $('#cover_biaya_paket').val(cover);
        }
    }

    function setPelayanan(idPelayanan) {
        var jenisPasien = $('#jenis_pasien').val();
        var option = '<option value=""> - </option>';
        CheckupAction.getComboPelayananWithLabCtx(jenisPasien, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idPelayanan + '">' + item.namaPelayanan + '</option>';
                });
                $('#poli').html(option);
                if(idPelayanan != null && idPelayanan != '' && idPelayanan != undefined){
                    $('#poli').val(idPelayanan).trigger('change');
                }
            }
        });
    }

    function showLaka(idAsuransi) {
        if (idAsuransi != '') {
            var temp = idAsuransi.split("|");
            var id = temp[0];
            var isLaka = temp[1];
            if (id != 'null') {
                $('#h_id_asuransi').val(id);
                $('#is_laka').val(isLaka);
                if (isLaka == "Y") {
                    $('#form_jasaraharja_1, #form_jasaraharja_2').show();
                    $('#form_no_kartu').hide();
                    var cover = formatRupiah(20000000);
                    $('#nominal_cover_biaya').val(cover);
                    $('#cover_biaya').val('20000000');
                    $('#war_jml_cover').hide();
                } else {
                    $('#form_jasaraharja_1, #form_jasaraharja_2').hide();
                    $('#form_no_kartu').show();
                    $('#cover_biaya').val('');
                    $('#nominal_cover_biaya').val('');
                }
            }
        }
    }

    function checkBpjs() {
        var noBpjs = $('#no_bpjs').val();
        var perujuk = $('#perujuk').val();
        var jenisRujukan = "";
        if (perujuk == '1') {
            jenisRujukan = "P";
        } else if (perujuk == '2') {
            jenisRujukan = "R";
        }
        $('#btn-cek').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...');
        dwr.engine.setAsync(true);
        CheckupAction.checkStatusBpjs(noBpjs, jenisRujukan, {
            callback: function (response) {
                var warnClass = "";
                var title = "";
                var msg = "";
                var icon = "";
                var val = "";
                if(response.status == "error"){
                    val = "tidak aktif";
                    icon = "fa-warning";
                    title = "Warning!";
                    warnClass = "alert-warning";
                    msg = response.message;
                }else{
                    if (response.keteranganStatusPeserta == "AKTIF") {
                        $('#kelas_pasien').val(response.kodeKelas);
                        $('#no_mr').val(response.noMr);
                        val = "aktif";
                        icon = "fa-info";
                        title = "Info!";
                        warnClass = "alert-success";
                        msg = "No BPJS berhasil diverifikasi dengan status AKTIF!";
                        $('#no_rujukan').val(response.noKunjungan).trigger('input');
                        cekNoRujukan();
                    } else if (response.keteranganStatusPeserta == "TIDAK AKTIF") {
                        val = "tidak aktif";
                        icon = "fa-warning";
                        title = "Warning!";
                        warnClass = "alert-warning";
                        msg = "No BPJS berhasil diverifikasi dengan status TIDAK AKTIF!";
                    } else {
                        val = "tidak ditemukan";
                        icon = "fa-warning";
                        title = "Warning!";
                        warnClass = "alert-danger";
                        msg = "No BPJS tidak ditemukan atau periksa kembali koneksi internet anda...!";
                    }
                }

                var warning = '<div class="alert ' + warnClass + ' alert-dismissible">' +
                    '<h4><i class="icon fa ' + icon + '"></i>' + title + '</h4>' + msg +
                    '</div>';

                $('#status_bpjs').val(val);
                $('#warn-bpjs').html(warning);
                $('#btn-cek').html('<i class="fa fa-search"></i> Check');
            }
        });
    }

    function listDokter(idPelayanan) {
        $('#nama_dokter').val(null);
        $('#id_dokter').val(null);
        var option = "<option value=''>[Select One]</option>";
        var uangMuka = '<s:property value="headerCheckup.uangMuka"/>';
        if (idPelayanan != null && idPelayanan != '') {
            var online = $('#id_online').val();
            if(online != ""){
                $('#nama_dokter').removeAttr('onclick');
                $('#btn-dokter').removeAttr('onclick');
            }else{
                $('#nama_dokter').attr('onclick', 'showJadwalDokter(\'' + idPelayanan + '\')');
                $('#btn-dokter').attr('onclick', 'showJadwalDokter(\'' + idPelayanan + '\')');
            }
            $('#h_id_pelayanan').val(idPelayanan);
            PelayananAction.getDataPelayanan(idPelayanan, function (res) {
                var option2 = "";
                var jenisPasien = $('#jenis_pasien').val();
                if (res.idPelayanan != null) {
                    if (res.tipePelayanan == "lab" || res.tipePelayanan == "radiologi") {
                        LabAction.listLab(res.tipePelayanan, function (response) {
                            if (response.length > 0) {
                                $.each(response, function (i, item) {
                                    option2 += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                                });
                                $('#id_lab').html(option2);
                            } else {
                                $('#id_lab').html(option2);
                            }
                        });
                        $('#form-lab').show();
                        $('#form_dokter_poli').hide();
                        $('#is_daftar_pj').val("Y");
                        $('#text_centang').text(" Apakah ada uang muka?");
                        $('#form-nominal_uang_muka').hide();
                        $('#form_eksekutif').hide();
                        $('#cek_cek').show();
                        $('#cek_is_uang_muka').prop('checked', false);
                        $('#pembayaran').val('tunai');
                        $('#id_lab').val(null).trigger('change');
                        if(uangMuka == ''){
                            $('#uang_muka, #uang_muka_val').val('');
                        }
                    } else {
                        if(uangMuka == ''){
                            $('#uang_muka, #uang_muka_val').val('');
                        }
                        if("umum" == jenisPasien){
                            $('#form-nominal_uang_muka').show();
                            $('#pembayaran').val('tunai');
                            $('#form_eksekutif').hide();
                        }else{
                            $('#form-nominal_uang_muka').hide();
                            $('#pembayaran').val('');
                            $('#form_eksekutif').hide();
                        }
                        $('#h_id_order_lab').val(null);
                        $('#form-lab').hide();
                        $('#form_dokter_poli').show();
                        $('#is_daftar_pj').val("N");
                        $('#text_centang').text(" Uang Muka");
                        $('#cek_cek').hide();
                        $('#cek_is_uang_muka').prop('checked', false);
                        $('#id_lab').val(null).trigger('change');
                    }

                    if(res.isVaksin == "Y"){
                        $('#form_vaksin').show();
                    }else{
                        $('#form_vaksin').hide();
                    }
                }
            });
        } else {
            $('#form-lab').hide();
        }
    }

    function cekKunjunganPoli(idPelayanan) {
        var idPasien = $('#id_pasien').val();
        if (idPasien && idPelayanan != '') {
            CheckupAction.cekKunjunganPoliPasien(idPasien, idPelayanan, function (res) {
                if (res.length > 0) {
                    $('#kunjungan_poli').val("Lama");
                } else {
                    $('#kunjungan_poli').val("Baru");
                }
            });
        }
    }

    function alertPasien(noPasien) {
        var namapasien = "";
        var diagnosa = "";
        var tglperiksa = "";
        var alergi = "";
        CheckupAction.initAlertPasien(noPasien, function (response) {
            if (response != null && response.namaPasien != null) {

                namapasien = "<h4><i class=\"fa fa-user\"></i> " + response.namaPasien + "</h4>";
                tglperiksa = "Pemeriksaan terakhir pasien pada : <strong>" + converterDate(new Date(response.stTglKeluar)) + "</strong>";

                if (response.listOfAlergi != null) {
                    $.each(response.listOfAlergi, function (i, item) {
                        if (alergi != "") {
                            alergi = alergi + ", " + item
                        } else {
                            alergi = item
                        }
                    });
                }

                $("#tgl-periksa").html(tglperiksa);
                $('#date-periksa').val(converterDate(response.stTglKeluar));
                $("#nama-pasien").html(namapasien);
                $("#alergi").html(alergi);
                if("B20" == response.idDiagnosa){
                    $("#diagnosa").html('<span class="blink_me_atas" style="color: red; font-weight: bold">'+'['+response.idDiagnosa+']'+response.diagnosa+'<span>');
                }else{
                    $("#diagnosa").html('['+response.idDiagnosa+']-'+response.diagnosa);
                }
                $("#alert-pasien").removeAttr("style");
                $("#btn-rm").removeAttr("style");
            } else {
                closeAlert();
            }
        });
    }

    function alertObatKronis(idPasien) {
        var namapasien = "";
        var diagnosa = "";
        var tglperiksa = "";
        var alergi = "";
        CheckupAction.findRiwayatObatKronis(idPasien, function (response) {
            if (response.idDetailCheckup != null) {
                if (response.flagPengambilan == "Y") {
                    $('#btn-save').hide();
                    $('#btn-kronis').show();
                    $('#success_kronis').show();
                    $('#msg_kronis2').text("Pengambilan Obat Kronis, Silahkan tekan tombol Obat Kronis untuk melanjutkan");
                    $('#btn-kronis').attr('onclick', 'showObatKronis(\'' + response.idDetailCheckup + '\',\'' + response.idApprovalObat + '\')');
                    $('#btn-save').hide();
                } else {
                    if (response.flagKronisDiambil == "K") {
                        $('#btn-kronis').removeAttr('onclick');
                        $('#btn-kronis').hide();
                        $('#warning_kronis').show();
                        $('#msg_kronis').text(response.msg);
                    } else {
                        $('#btn-kronis').removeAttr('onclick');
                        $('#btn-kronis').hide();
                        $('#warning_kronis').show();
                        $('#msg_kronis').text(response.msg + ", Tanggal Pemgambilan " + converterDate(response.tglPengambilan));
                    }
                }
                $('html, body').animate({
                    scrollTop: $('#pos_kronis').offset().top
                }, 2000);

            } else {
                $('#btn-kronis').removeAttr('onclick');
                $('#btn-save').show();
                $('#btn-kronis').hide();
                $('#success_kronis').hide();
                $('#warning_kronis').hide();
            }
        });
    }

    function showObatKronis(idDetailCheckup, idApproval) {
        if (idDetailCheckup != '' && idApproval != '') {
            $('#kron_no_rm').html($('#id_pasien').val());
            $('#kron_nama').html($('#nama_pasien').val());
            $('#kron_tgl_periksa').html($('#date-periksa').val());
            $('#kron_diagnosa_terakhir').html($('#diagnosa').text());
            $('#modal-obat-kronis').modal({show: true, backdrop: 'static'});
            var table = "";
            CheckupAction.getListObatKronis(idDetailCheckup, idApproval, function (response) {
                if (response.length > 0) {

                    var idPelayanan = "";

                    $.each(response, function (i, item) {

                        var qtyTotal = 0;
                        var qtyBox = 0;
                        var qtyLembar = 0;
                        var qtyBiji = 0;
                        var hariKronis = 0;
                        var ket = "";

                        if (item.qtyBox != null) {
                            qtyBox = item.qtyBox;
                        }

                        if (item.qtyLembar != null) {
                            qtyLembar = item.qtyLembar;
                        }

                        if (item.qtyBiji != null) {
                            qtyBiji = item.qtyBiji;
                        }

                        if (item.lembarPerBox != null && item.bijiPerLembar != null) {
                            qtyTotal = parseInt(qtyBiji) + ((parseInt(item.lembarPerBox * parseInt(qtyBox))) * parseInt(item.bijiPerLembar));
                        }

                        if (item.keterangan != null) {
                            ket = item.keterangan;
                        }

                        hariKronis = parseInt(30) - parseInt(item.hariKronis);

                        table += '<tr>' +
                            '<td>' + item.idObat + '<input type="hidden" id="trans_id' + i + '" value="' + item.idTransaksiObatDetail + '">' + '</td>' +
                            '<td>' + item.namaObat + '</td>' +
                            '<td>' + qtyTotal + '</td>' +
                            '<td>biji</td>' +
                            '<td>' + ket + '</td>' +
                            '<td>' + hariKronis + '</td>' +
                            '<td width="20%">' + '<input type="number" onchange="validasiInput(\'' + qtyTotal + '\',\'' + i + '\')" class="form-control" id="qty' + i + '">' + '</td>' +
                            '</tr>';
                        idPelayanan = item.idPelayanan;
                    });

                    $('#body_kronis').html(table);
                    $('#save_kronis').attr('onclick', 'savePengambilanObatKronis(\'' + idDetailCheckup + '\',\'' + idPelayanan + '\')');

                } else {
                    $('#body_kronis').html("");
                }
            })
        }
    }

    function savePengambilanObatKronis(idDetailCheckup, idPelayanan) {
        if (idDetailCheckup != '' && idPelayanan != '') {
            var data = $('#tabel-kronis').tableToJSON();
            var result = [];
            var cek = false;
            var cekQty = false;

            $.each(data, function (i, item) {
                var idObat = data[i]["ID Obat"];
                var qty = $('#qty' + i).val();
                var qtyTotal = data[i]["Stok Obat"];
                var hariKronis = data[i]["Hari Kronis"];
                var keterangan = data[i]["Keterangan"];
                var transId = $('#trans_id' + i).val();

                if (qty != "") {
                    result.push({
                        'id_obat': idObat,
                        'jenis_satuan': 'biji',
                        'qty': qty,
                        'keterangan': keterangan,
                        'hari_selanjutnya': hariKronis,
                        'trans_id': transId
                    });
                    cekQty = true;
                }

                if (parseInt(qty) > parseInt(qtyTotal)) {
                    cek = true;
                }
            });

            var jsonString = JSON.stringify(result);

            if (cekQty) {

                if (cek) {
                    $('#warning_list_kronis').show().fadeOut(5000);
                    $('#msg_list_kronis').text("Qty request tidak boleh melebihi qty stok obat...!");
                } else {
                    $('#save_kronis').hide();
                    $('#load_kronis').show();
                    dwr.engine.setAsync(true);
                    CheckupAction.savePengambilanObatKronis(idDetailCheckup, jsonString, idPelayanan, {
                        callback: function (response) {
                            if (response.status == "success") {
                                $('#modal-obat-kronis').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#save_kronis').show();
                                $('#load_kronis').hide();
                            } else {
                                $('#save_kronis').show();
                                $('#load_kronis').hide();
                                $('#warning_list_kronis').show().fadeOut(5000);
                                $('#msg_list_kronis').text(response.msg);
                            }
                        }
                    });
                }

            } else {
                $('#warning_list_kronis').show().fadeOut(5000);
                $('#msg_list_kronis').text("Silahkan masukkan qty obat...!");
            }
        }
    }

    function validasiInput(stok, i) {
        if (stok != '') {
            var qty = $('#qty' + i).val();

            if (parseInt(qty) > parseInt(stok)) {
                $('#warning_list_kronis').show().fadeOut(10000);
                $('#msg_list_kronis').text("Qty request tidak boleh melebihi qty stok obat..!");
            }
        }
    }

    function closeAlert() {
        $("#alert-pasien").attr("style", "display:none");
        $("#btn-rm").attr("style", "display:none");
    }

    function showJadwalDokter(id) {
        var pel = $('#poli option:selected').text();
        var jenisPasien = $('#jenis_pasien').val();
        var table = "";
        if (id != null && id != '' && jenisPasien != null && jenisPasien != '') {
            CheckupAction.listOfDokter(id, function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var kuota = 0;
                        var sisa = 0;
                        var kuotaBpjs = 0;
                        var sisKuotaBpjs = 0;
                        var namaDokter = "";
                        var sip = "";
                        var label = "";
                        var label2 = "";
                        var jamkerja = "";
                        if (item.jamAwal != null && item.jamAkhir != null) {
                            jamkerja = item.jamAwal + " s/d " + item.jamAkhir;
                        }
                        if (item.kuotaOnSite != null && item.kuotaOnSite != '') {
                            kuota = item.kuotaOnSite;
                        }
                        if (item.kuotaBpjs != null && item.kuotaBpjs != '') {
                            kuotaBpjs = item.kuotaBpjs;
                        }
                        if (item.kuotaTerpakaiNonBpjs != null && item.kuotaTerpakaiNonBpjs != '') {
                            sisa = item.kuotaTerpakaiNonBpjs;
                        }
                        if (item.kuotaTerpakaiBpjs != null && item.kuotaTerpakaiBpjs != '') {
                            sisKuotaBpjs = item.kuotaTerpakaiBpjs;
                        }

                        label = sisa + "/" + kuota;
                        label2 = sisKuotaBpjs + "/" +kuotaBpjs;

                        if (item.namaDokter != '') {
                            namaDokter = item.namaDokter;
                        }
                        if (item.idDokter != '') {
                            sip = item.idDokter;
                        }

                        var foto = contextPathHeader+'/pages/images/unknown-person2.jpg';
                        if(item.urlImg != null && item.urlImg != ''){
                            foto = contextPathHeader+item.urlImg;
                        }

                        var clasBox = 'btn-trans';
                        var btnSet = 'onclick="setDokter(\'' + item.idDokter + '\', \'' + item.namaDokter + '\')"';
                        var noDrop = 'cursor: pointer';

                        if (item.flagLibur == "Y") {
                            clasBox = 'btn-trans-02';
                            btnSet = 'style="cursor: no-drop"';
                            noDrop = 'cursor: no-drop';
                        }else{
                            var cekSisa = 0;
                            var cekKuota = 0;
                            if(jenisPasien == 'bpjs'){
                                cekSisa = sisKuotaBpjs;
                                cekKuota = kuotaBpjs;
                            }else{
                                cekSisa = sisa;
                                cekKuota = kuota;
                            }

                            if(cekSisa == cekKuota){
                                clasBox = 'btn-trans-02';
                                btnSet = 'style="cursor: no-drop"';
                                noDrop = 'cursor: no-drop';
                            }
                        }
                        table += '<div id="id_box_' + i + '" class="' + clasBox + '" ' + btnSet + '>\n' +
                            '<div style="text-align:left; font-size:11px;">\n' +
                            '    <table align="center" style="width:100%; border-radius:5px; margin-top:2px; '+noDrop+'">\n' +
                            '        <tr>\n' +
                            '            <td align="left" colspan="2">\n' +
                            '                <span style="color: white; background-color: #337ab7; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">' + jamkerja + '</span>\n' +
                            '                <span class="pull-right" style="margin-top: -6px; color: white; background-color: #00a65a; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">' + label2 + '</span>\n' +
                            '                <span class="pull-right" style="margin-top: -6px; margin-left: 5px; color: white; background-color: #ec971f; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">' + label + '</span>\n' +
                            '                <%--<img style="margin-top: -6px" class="pull-right" src="<s:url value="/pages/images/icon_failure.ico"/>">--%>\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '            <td align="center" colspan="2">\n' +
                            '                <img class="img-circle" style="background-color:transparent; height:100px; padding-bottom: 2px; padding-top: 8px; width: 55%;" src="'+foto+'">\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '            <td align="left" colspan="2" style="color: black; font-size: 11px; padding-top: 3px; border-bottom: black solid 1px; padding-bottom: 3px">\n' +
                            '                <i class="fa fa-user"></i> ' + namaDokter + '\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '            <td align="left" colspan="2" style="color: black; font-size: 11px; padding-top: 5px">\n' +
                            '                <i class="fa fa-square" style="font-size: 10px"></i> ' + sip + '\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '    </table>\n' +
                            '</div>\n' +
                            '</div>';
                    });
                } else {
                    table = '<span class="text-center">Jadwal Dokter Tidak Ditemukan...!</span>'
                }
                $('#dokter_pelayanan').text(pel);
                $('#jadwal_dokter').html(table);
                $('#modal-jadwal-dokter').modal({show: true, backdrop: 'static'});
            });
        }
    }

    function setDokter(idDokter, namaDokter) {
        $('#nama_dokter').val(namaDokter);
        $('#dokter').val(idDokter);
        $('#modal-jadwal-dokter').modal('hide');
        $('#war_dokter').hide();
    }

    var listAlergi = [];

    function addAlergi(alergi) {
        listAlergi.push({"alergi": alergi});
    }

    function printGagalSEP(kode) {
        var idPasien = $('#id_pasien').val();
        if (idPasien != '') {
            window.open(contextPath + '/rekammedik/printSuratPernyataan_rekammedik?idPasien=' + idPasien + '&tipe=' + kode, '_blank');
        } else {
            $('#warning_cetak').show().fadeOut(5000);
            $('#msg_cetak').text("ID Pasien belum ada...!");
        }
    }

    function getRiwayatPemeriksaan(idPasien) {
        var li = "";
        CheckupAction.getRiwayatPemeriksaan(idPasien, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    li += '<li><a style="cursor: pointer"><i class="fa fa-stethoscope"></i> ' + converterDate(item.createdDate) + ' | ' + item.namaPelayanan + ' - ' + item.diagnosa + ' - ' + item.namaDiagnosa + '</a></li>';
                });
                $('#riwayat').html(li);
            }
        });
    }

    function searchNoBpjs(id) {
        var functions, mapped;
        if($('#jenis_pasien').val() != ''){
            $('#' + id).typeahead({
                minLength: 3,
                source: function (query, process) {
                    functions = [];
                    mapped = {};
                    var data = [];
                    dwr.engine.setAsync(false);
                    PasienAction.getListComboPasien(query, "", function (listdata) {
                        data = listdata;
                    });
                    if (data.length != 0) {
                        $.each(data, function (i, item) {
                            var labelItem = "";
                            var dddesa = "";
                            var tglLahir = "";
                            if(item.desa != null){
                                dddesa = '-'+item.desa;
                            }
                            if(item.tanggalLahir != null){
                                tglLahir = '-'+converterDate(item.tanggalLahir);
                            }

                            if (item.noBpjs != '' && item.noBpjs != null) {
                                labelItem = item.idPasien + "-" + item.noBpjs + "-" + item.nama+dddesa+tglLahir;
                            } else {
                                labelItem = item.noBpjs + "-" + item.nama+dddesa+tglLahir;
                            }
                            mapped[labelItem] = {
                                id: item.noBpjs,
                                idPasien: item.idPasien,
                                nama: item.nama,
                                namaPaket: item.namaPaket,
                                idPaket: item.idPaket,
                                tarif: item.tarif,
                                idPelayananPaket: item.idPelayanan
                            };
                            functions.push(labelItem);
                        });
                        process(functions);
                    }
                },
                updater: function (item) {
                    var selectedObj = mapped[item];
                    alertPasien(selectedObj.idPasien);
                    getRiwayatPemeriksaan(selectedObj.idPasien);
                    $('#id_pasien').val(selectedObj.idPasien);
                    $('#nama_pasien').val(selectedObj.nama);
                    PasienAction.detailPasien(selectedObj.idPasien, function(res){
                        if(res.idPasien != null){
                            $('#no_ktp').val(res.noKtp);
                            $('#jenis_kelamin').val(res.jenisKelamin);
                            $('#tempat_lahir').val(res.tempatLahir);
                            $('#tgl_lahir').val(res.tglLahir);
                            $('#agama').val(res.agama);
                            $('#profesi').val(res.profesi).trigger('change');
                            $('#jalan').val(res.jalan);
                            $('#suku').val(res.suku).trigger('change');
                            $('#img_ktp').val(res.imgKtp);
                            if(res.urlKtp != null && res.urlKtp != ''){
                                var cek = cekImages(res.urlKtp);
                                if(cek){
                                    $('#img-upload').attr('src', res.urlKtp);
                                }else{
                                    $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                                }
                            }else{
                                $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                            }
                            $('#provinsi').val(res.provinsi);
                            $('#kabupaten').val(res.kota);
                            $('#kecamatan').val(res.kecamatan);
                            $('#desa').val(res.desa);
                            $('#provinsi11').val(res.provinsiId);
                            $('#kabupaten11').val(res.kotaId);
                            $('#kecamatan11').val(res.kecamatanId);
                            $('#desa11').val(res.desaId);
                            $('#no_telp').val(res.notelp);
                            $('#pendidikan').val(res.pendidikan).trigger('change');
                            $('#status_perkawinan').val(res.statusPerkawinan).trigger('change');
                            if (res.isPasienLama) {
                                $('#kunjungan_val').val("Lama");
                            } else {
                                $('#kunjungan_val').val("Baru");
                            }

                            var tipe = $('#jenis_pasien').val();
                            if ("paket_perusahaan" == tipe) {
                                if (selectedObj.idPelayananPaket != null) {
                                    $('#poli').val(selectedObj.idPelayananPaket).trigger('change').attr('disabled', true);
                                }
                            } else {
                                if ("Y" == res.isCheckupUlang) {
                                    $('#poli').val(res.idPelayanan).trigger('change').attr('disabled', true);
                                } else {
                                    $('#poli').val('').trigger('change').attr('disabled', false);
                                }

                                $('#last_id_detail_checkup').val(res.idLastDetailCheckup);
                                $('#is_order_lab').val(res.isOrderLab);
                                if (res.tglCheckup != null) {
                                    $('#tgl_checkup').html("Tanggal Checkup Ulang : <b>" + res.tglCheckup + "</b>");
                                } else {
                                    $('#tgl_checkup').html("");
                                }
                            }

                            if (selectedObj.idPaket != null && selectedObj.idPaket != '') {
                                $('#id_paket').val(selectedObj.idPaket);
                                $('#paket_perusahaan').val(selectedObj.namaPaket);
                                $('#cover_biaya_paket').val(selectedObj.tarif);
                            }

                            if(res.flagMeninggal == "Y"){
                                $('#btn-save').hide();
                                $('#warning_pasien').show();
                                $('#msg_pasien').text("Pasien Sudah Meninggal Dunia Pada Tanggal ["+converterDate(res.tglMeninggal)+"]");
                            }else{
                                // if (res.isDaftar == "Y") {
                                //     $('#btn-save').hide();
                                //     $('#warning_pasien').show();
                                //     $('#msg_pasien').text("Pasien Sudah melakukan pendafataran...!");
                                // } else {
                                    $('#btn-save').show();
                                    $('#warning_pasien').hide();
                                    $('#msg_pasien').text("");
                                    alertObatKronis(selectedObj.id);
                                // }
                            }
                        }
                    });
                    $('#id_pasien').attr('readonly', true);
                    $('#no_bpjs').attr('readonly', true);
                    $('#no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #agama, #provinsi, #kabupaten, #kecamatan, #desa ').css('border', '');
                    return selectedObj.id;
                }
            });
        }else{
            $('#war_jp').show();
        }
    }

    function searchNoRM(id, value) {
        var functions, mapped;
        if($('#jenis_pasien').val() != ''){
            if (value != '') {
                $('#' + id).typeahead({
                    minLength: 3,
                    source: function (query, process) {
                        var jenisPasien = $('#jenis_pasien').val();
                        functions = [];
                        mapped = {};
                        var data = [];
                        dwr.engine.setAsync(false);
                        PasienAction.getListComboPasien(query, jenisPasien, function (listdata) {
                            data = listdata;
                        });
                        $.each(data, function (i, item) {
                            var labelItem = "";
                            var dddesa = "";
                            var tglLahir = "";
                            if(item.desa != null){
                                dddesa = '-'+item.desa;
                            }
                            if(item.tanggalLahir != null){
                                tglLahir = '-'+converterDate(item.tanggalLahir);
                            }

                            if (item.noBpjs != '' && item.noBpjs != null) {
                                labelItem = item.idPasien + "-" + item.noBpjs + "-" + item.nama + dddesa+tglLahir;
                            } else {
                                labelItem = item.idPasien + "-" + item.nama + dddesa+tglLahir;
                            }
                            mapped[labelItem] = {
                                id: item.idPasien,
                                nama: item.nama,
                                namaPaket: item.namaPaket,
                                idPaket: item.idPaket,
                                tarif: item.tarif,
                                idPelayananPaket: item.idPelayanan
                            };
                            functions.push(labelItem);
                        });
                        process(functions);

                    },
                    updater: function (item) {
                        var selectedObj = mapped[item];
                        alertPasien(selectedObj.id);
                        getRiwayatPemeriksaan(selectedObj.id);
                        $('#nama_pasien').val(selectedObj.nama);
                        PasienAction.detailPasien(selectedObj.id, function(res){
                            if(res.idPasien != null){
                                $('#no_bpjs').val(res.noBpjs);
                                $('#no_ktp').val(res.noKtp);
                                $('#jenis_kelamin').val(res.jenisKelamin);
                                $('#tempat_lahir').val(res.tempatLahir);
                                $('#tgl_lahir').val(res.tglLahir);
                                $('#agama').val(res.agama);
                                $('#profesi').val(res.profesi).trigger('change');
                                $('#jalan').val(res.jalan);
                                $('#suku').val(res.suku).trigger('change');
                                $('#img_ktp').val(res.imgKtp);
                                if(res.urlKtp != null && res.urlKtp != ''){
                                    var cek = cekImages(res.urlKtp);
                                    if(cek){
                                        $('#img-upload').attr('src', res.urlKtp);
                                    }else{
                                        $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                                    }
                                }else{
                                    $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                                }
                                $('#provinsi').val(res.provinsi);
                                $('#kabupaten').val(res.kota);
                                $('#kecamatan').val(res.kecamatan);
                                $('#desa').val(res.desa);
                                $('#provinsi11').val(res.provinsiId);
                                $('#kabupaten11').val(res.kotaId);
                                $('#kecamatan11').val(res.kecamatanId);
                                $('#desa11').val(res.desaId);
                                $('#no_telp').val(res.notelp);
                                $('#pendidikan').val(res.pendidikan).trigger('change');
                                $('#status_perkawinan').val(res.statusPerkawinan).trigger('change');
                                if (res.isPasienLama) {
                                    $('#kunjungan_val').val("Lama");
                                } else {
                                    $('#kunjungan_val').val("Baru");
                                }

                                var tipe = $('#jenis_pasien').val();
                                if ("paket_perusahaan" == tipe) {
                                    if (selectedObj.idPelayananPaket != null) {
                                        $('#poli').val(selectedObj.idPelayananPaket).trigger('change').attr('disabled', true);
                                    }
                                } else {
                                    if ("Y" == res.isCheckupUlang) {
                                        $('#poli').val(res.idPelayanan).trigger('change').attr('disabled', true);
                                    } else {
                                        $('#poli').val('').trigger('change').attr('disabled', false);
                                    }

                                    $('#last_id_detail_checkup').val(res.idLastDetailCheckup);
                                    $('#is_order_lab').val(res.isOrderLab);
                                    if (res.tglCheckup != null) {
                                        $('#tgl_checkup').html("Tanggal Checkup Ulang : <b>" + res.tglCheckup + "</b>");
                                    } else {
                                        $('#tgl_checkup').html("");
                                    }
                                }

                                if (selectedObj.idPaket != null && selectedObj.idPaket != '') {
                                    $('#id_paket').val(selectedObj.idPaket);
                                    $('#paket_perusahaan').val(selectedObj.namaPaket);
                                    $('#cover_biaya_paket').val(selectedObj.tarif);
                                }

                                if(res.flagMeninggal == "Y"){
                                    $('#btn-save').hide();
                                    $('#warning_pasien').show();
                                    $('#msg_pasien').text("Pasien Sudah Meninggal Dunia Pada Tanggal ["+converterDate(res.tglMeninggal)+"]");
                                }else{
                                    // if (res.isDaftar == "Y") {
                                    //     $('#btn-save').hide();
                                    //     $('#warning_pasien').show();
                                    //     $('#msg_pasien').text("Pasien Sudah melakukan pendafataran...!");
                                    // } else {
                                        $('#btn-save').show();
                                        $('#warning_pasien').hide();
                                        $('#msg_pasien').text("");
                                        alertObatKronis(selectedObj.id);
                                    // }
                                }
                            }
                        });
                        $('#id_pasien').attr('readonly', true);
                        $('#no_bpjs').attr('readonly', true);
                        $('#no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #agama, #provinsi, #kabupaten, #kecamatan, #desa ').css('border', '');
                        return selectedObj.id;
                    }
                });
            } else {
                $('#id_pasien').val('');
            }
        }else{
            $('#war_jp').show();
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
                $("#diagnosa_ket").val(selectedObj.name);
                return selectedObj.id;
            }
        });
    }

    function setJenisPasien(jenis) {
        var online = $('#is_online').val();
        if (jenis == "umum") {
            $('#form-uang-muka').show();
            $('#form-paket-perusahaan').hide();
            $('#form-paket').hide();
            $('#form-asuransi').hide();
            $('#form-rekanan').hide();
            $('#form-paket').hide();
            $('#form-no-bpjs').hide();
            $('#form-rujukan').hide();
            $('#poli').attr('disabled', false);
            $('#form_eksekutif').hide();
            $('#pembayaran').val('tunai');
            $('#form_diagnosa_bpjs').hide();
            $('#diagnosa_awal, #diagnosa_ket').val(null);
        } else if (jenis == "bpjs" || jenis == "bpjs_rekanan") {
            if (jenis == "bpjs_rekanan") {
                listSelectRekanan("Y");
                $('#form-rekanan').show();
            }
            if (jenis == "bpjs") {
                $('#form-rekanan').hide();
            }
            $('#form-no-bpjs').show();
            $('#form-rujukan').show();
            $('#form_pg').hide();
            $('#form-uang-muka').hide();
            $('#form-paket-perusahaan').hide();
            $('#form-paket').hide();
            $('#form-asuransi').hide();
            $('#poli').attr('disabled', false);
            $('#form_eksekutif').hide();
            $('#pembayaran').val('');
            $('#diagnosa_awal, #diagnosa_ket').val(null);
            $('#form_diagnosa_bpjs').show();
        }else if (jenis == "rekanan") {
            listSelectRekanan("N", '<s:property value="headerCheckup.idAsuransi"/>', '<s:property value="headerCheckup.isRekananWithBpjs"/>', '<s:property value="headerCheckup.tipeRekanan"/>');
            $('#form_pg').hide();
            $('#form-rekanan').show();
            $('#form-no-bpjs').hide();
            $('#form-rujukan').hide();
            $('#form-uang-muka').hide();
            $('#form-paket-perusahaan').hide();
            $('#form-paket').hide();
            $('#form-asuransi').hide();
            $('#poli').attr('disabled', false);
            $('#form_eksekutif').hide();
            $('#pembayaran').val('');
            $('#form_diagnosa_bpjs').hide();
            $('#diagnosa_awal, #diagnosa_ket').val(null);
        } else if (jenis == "paket_perusahaan") {
            listSelectPaket();
            $('#form-paket-perusahaan').show();
            $('#form-no-bpjs').hide();
            $('#form-rujukan').hide();
            $('#form-uang-muka').hide();
            $('#form-asuransi').hide();
            $('#form-rekanan').hide();
            $('#form-paket').hide();
            $('#poli').attr('disabled', true);
            $('#form_eksekutif').hide();
            $('#pembayaran').val('');
            resetAllField();
            $('#form_diagnosa_bpjs').hide();
            $('#diagnosa_awal, #diagnosa_ket').val(null);
        } else if (jenis == "paket_individu") {
            listSelectPaket('<s:property value="headerCheckup.idPaket"/>');
            $('#form-paket').show();
            $('#form-paket-perusahaan').hide();
            $('#form-no-bpjs').hide();
            $('#form-rujukan').hide();
            $('#form-asuransi').hide();
            $('#form-rekanan').hide();
            $('#form-uang-muka').hide();
            $('#poli').attr('disabled', true);
            $('#form_eksekutif').hide();
            $('#pembayaran').val('');
            $('#form_diagnosa_bpjs').hide();
            $('#diagnosa_awal, #diagnosa_ket').val(null);
        } else if (jenis == "asuransi") {
            listSelectAsuransi('<s:property value="headerCheckup.idAsuransi"/>', '<s:property value="headerCheckup.isLaka"/>');
            $('#form-asuransi').show();
            $('#form-paket').hide();
            $('#form-paket-perusahaan').hide();
            $('#form-no-bpjs').hide();
            $('#form-rujukan').hide();
            $('#form-uang-muka').hide();
            $('#form-rekanan').hide();
            $('#poli').attr('disabled', false);
            $('#form_eksekutif').hide();
            $('#pembayaran').val('');
            $('#form_diagnosa_bpjs').hide();
            $('#diagnosa_awal, #diagnosa_ket').val(null);
        }

        if (online == "Y") {
            $('#poli').attr('disabled', true);
            $('#id_online').attr('readonly', true);
            $('#jenis_pasien').attr('disabled', true);
            $('#id_pasien').attr('readonly', true);
            $('#no_bpjs').attr('readonly', true);
        } else {
            var url_string = window.location.href;
            var url = new URL(url_string);
            var idPasien = url.searchParams.get("idPasien");
            if(idPasien != null && idPasien != ''){
                $('#jenis_pasien').attr('disabled', true);
                $('#id_pasien').attr('readonly', true);
                $('#no_bpjs').attr('readonly', true);
                $('#id_online').attr('readonly', false);
            }else{
                resetField(1);
                $('#jenis_pasien').attr('disabled', false);
                if($('#id_pasien').val() != ''){
                    $('#id_pasien').attr('readonly', true);
                }else{
                    $('#id_pasien').attr('readonly', false);
                }
                if($('#no_bpjs').val() != ''){
                    $('#no_bpjs').attr('readonly', true);
                }else{
                    $('#no_bpjs').attr('readonly', false);
                }
            }
            setPelayanan();
        }
        $('#jenis_pasien').val(jenis);
        $('#h_id_jenis_pasien').val(jenis);
        $('#btn-finger').hide();
        $('#nama_dokter').val(null);
        $('#id_dokter').val(null);
    }

    function showPasienBaru() {
        $('#btn_new').removeClass("btn btn-success pull-right blink_me_atas");
        $('#btn_new').addClass("btn btn-success pull-right");
        $('#add_no_bpjs').val('');
        $('#add_nik').val('');
        $('#add_nama').val('');
        $('#add_jk').val('');
        $('#add_tempat_lahir').val('');
        $('#add_tanggal_lahir').val('');
        $('#add_no_telp').val('');
        $('#add_agama').val('');
        $('#add_profesi').val('');
        $('#add_suku').val('');
        $('#add_alamat').val('');
        $('#add_id_provinsi').val('');
        $('#add_id_kota').val('');
        $('#add_id_kecamatan').val('');
        $('#add_id_desa').val('');
        $('#add_provinsi').val('');
        $('#add_kota').val('');
        $('#add_kecamatan').val('');
        $('#add_desa').val('');
        $('#modal-daftar-pasien').modal({show: true, backdrop: 'static'});
    }

    function cekSaveNewPasien() {
        var noBpjs = $('#add_no_bpjs').val();
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var noTelp = $('#add_no_telp').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();
        var statusPerkawinan = $('#add_status_perkawinan').val();
        var pendidikan = $('#add_pendidikan').val();
        var cekCondTgl = false;

        var ktp = document.getElementById('img_ktp_canvas');
        var cek = false;

        var cekTglLahir = tanggalLahir.split("-");
        $.each(cekTglLahir, function (i, item) {
            var numbers = /^[0-9]+$/;
            if (!item.match(numbers)) {
                cekCondTgl = true;
            }
        });

        var cekNik = true;
        nik = replaceUnderLine(nik);
        if(nik.length == 6){
            for (var i = 0; i < nik.length; i++) {
                if(nik.charAt(i) != "0"){
                    cekNik = false;
                }
            }
            if(!cekNik){
                $('#war_add_nik_pasien').show();
            }else{
                $('#war_add_nik_pasien').hide();
            }
        }else{
            if(nik.length == 16){
                $('#war_add_nik_pasien').hide();
            }else{
                $('#war_add_nik_pasien').show();
                cekNik = false;
            }
        }

        if (cekNik && nama != '' && jk != '' && tempatLahir != '' && tanggalLahir != '' &&
            agama != '' && provinsi != '' && kota != '' && kecamatan != '' && desa != '' && noTelp != '' && !cekCondTgl && statusPerkawinan && pendidikan && alamat != '') {
            cek = true;
        } else {
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan anda...!");
            if (!cekNik) {
                $('#war_add_nik_pasien').show();
                $('#add_nik').css('border', 'solid 1px red');
            }
            if (nama == '') {
                $('#add_nama').css('border', 'solid 1px red');
            }
            if (jk == '') {
                $('#add_jk').css('border', 'solid 1px red');
            }
            if (tempatLahir == '') {
                $('#add_tempat_lahir').css('border', 'solid 1px red');
            }
            if (tanggalLahir == '' || cekCondTgl) {
                $('#add_tanggal_lahir').css('border', 'solid 1px red');
            }
            if (agama == '') {
                $('#add_agama').css('border', 'solid 1px red');
            }
            if (provinsi == '') {
                $('#add_provinsi').css('border', 'solid 1px red');
            }
            if (kota == '') {
                $('#add_kota').css('border', 'solid 1px red');
            }
            if (kecamatan == '') {
                $('#add_kecamatan').css('border', 'solid 1px red');
            }
            if (desa == '') {
                $('#add_desa').css('border', 'solid 1px red');
            }
            if (noTelp == '') {
                $('#add_no_telp').css('border', 'solid 1px red');
            }
            if (statusPerkawinan == '') {
                $('#add_status_perkawinan').css('border', 'solid 1px red');
            }
            if (pendidikan == '') {
                $('#add_pendidikan').css('border', 'solid 1px red');
            }
            if (alamat == '') {
                $('#add_alamat').css('border', 'solid 1px red');
            }
        }

        if (cek) {
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveNewPAsien()');
        }
    }

    function saveNewPAsien() {
        $('#modal-confirm-dialog').modal('hide');
        var data = "";
        var jenisPasien = $('#add_jenis').val();
        var noBpjs = $('#add_no_bpjs').val();
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var noTelp = $('#add_no_telp').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();
        var statusPerkawinan = $('#add_status_perkawinan').val();
        var pendidikan = $('#add_pendidikan').val();

        var ktp = document.getElementById('img_ktp_canvas');
        var ktpFinal = "";
        if ($('#ktp').val() != '') {
            ktpFinal = convertToDataURL(ktp);
        }
        data = {
            'no_bpjs': noBpjs,
            'nik': nik,
            'nama': nama,
            'jk': jk,
            'tempat_lahir': tempatLahir,
            'tanggal_lahir': tanggalLahir,
            'agama': agama,
            'no_telp': noTelp,
            'profesi': profesi,
            'suku': suku,
            'alamat': alamat,
            'desa_id': desa,
            'status': statusPerkawinan,
            'pendidikan': pendidikan,
            'img_ktp': ktpFinal
        };
        var objectString = JSON.stringify(data);
        if(!cekSession()){
            $('#save_add').hide();
            $('#load_add').show();
            dwr.engine.setAsync(true);
            PaketPeriksaAction.saveNewPasien(objectString, {
                callback: function (response) {
                    if (response.status == "success") {
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#info_dialog_new').dialog('open');
                        $('#id_pasien').val(response.idPasien).attr('readonly', true);
                        $('#no_bpjs').val(response.noBpjs).attr('readonly', true);
                        $('#no_ktp').val(response.noKtp);
                        $('#nama_pasien').val(response.nama);
                        $('#jenis_kelamin').val(response.jenisKelamin);
                        $('#tempat_lahir').val(response.tempatLahir);
                        $('#tgl_lahir').val(response.tglLahir);
                        $('#agama').val(response.agama);
                        $('#profesi').val(response.profesi).trigger('change');
                        $('#jalan').val(response.jalan);
                        $('#suku').val(response.suku).trigger('change');
                        $('#img_ktp').val(response.imgKtp);
                        if(response.urlKtp != null && response.urlKtp != ''){
                            var cek = cekImages(response.urlKtp);
                            if(cek){
                                $('#img-upload').attr('src', response.urlKtp);
                            }else{
                                $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                            }
                        }else{
                            $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                        }
                        $('#provinsi').val(response.provinsi);
                        $('#kabupaten').val(response.kota);
                        $('#kecamatan').val(response.kecamatan);
                        $('#desa').val(response.desa);
                        $('#provinsi11').val(response.provinsiId);
                        $('#kabupaten11').val(response.kotaId);
                        $('#kecamatan11').val(response.kecamatanId);
                        $('#desa11').val(response.desaId);
                        $('#no_telp').val(response.noTelp);
                        $('#status_perkawinan').val(response.statusPerkawinan).trigger('change');
                        $('#pendidikan').val(response.pendidikan).trigger('change');
                        // $('#kunjungan').val("Baru").attr('disabled', true);
                        $('#kunjungan_val').val("Baru");
                        $('#modal-daftar-pasien').modal('hide');
                        $('body').scrollTop(0);
                    } else {
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text(response.msg);
                    }
                }
            });
        }
    }

    function setFingerPrint() {
        var idPasien = $('#id_pasien').val();
    }

    function searchNoCheckup(id) {
        id = id.toUpperCase();
        if (id != '') {
            $('#load_online').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...');
            dwr.engine.setAsync(true);
            CheckupAction.getCheckupOnline(id, {
                callback: function (response) {
                    if (response.idPasien != null) {
                        $('#load_online').html('<i class="fa fa-search"></i> Check');
                        var today = new Date();
                        var dd = String(today.getDate()).padStart(2, '0');
                        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                        var yyyy = today.getFullYear();
                        var tglToday = mm + '-' + dd + '-' + yyyy;

                        var daftar = new Date(response.tglCheckupOnline);
                        var dDaf = String(daftar.getDate()).padStart(2, '0');
                        var mDaf = String(daftar.getMonth() + 1).padStart(2, '0'); //January is 0!
                        var yDaf = daftar.getFullYear();
                        var tglDaftar = mDaf + '-' + dDaf + '-' + yDaf;

                        var tgl1 = new Date(tglToday);
                        var tgl2 = new Date(tglDaftar);

                        var tanggalToday = Math.abs(tgl1);
                        var tanggalCheckup = Math.abs(tgl2);

                        var time = response.jamAwal;
                        var char = time.split(":");
                        var hh2 = char[0];
                        var min2 = char[1];

                        var timeDaftar = new Date();
                        timeDaftar.setHours(hh2, min2, 0);
                        timeDaftar.setMinutes(timeDaftar.getMinutes() - 30);

                        var timeToday = new Date();

                        if (tanggalToday == tanggalCheckup) {
                            if (Math.abs(timeToday) <= Math.abs(timeDaftar)) {
                                $('#id_pasien').val(response.idPasien);
                                $('#no_bpjs').val(response.noBpjs);
                                $('#no_ktp').val(response.noKtp);
                                $('#nama_pasien').val(response.nama);
                                $('#jenis_kelamin').val(response.jenisKelamin);
                                $('#tempat_lahir').val(response.tempatLahir);
                                $('#tgl_lahir').val(converterDateYmd(response.tglLahir));
                                $('#agama').val(response.agama);
                                $('#profesi').val(response.profesi);
                                $('#jalan').val(response.jalan);
                                $('#suku').val(response.suku);
                                $('#img_ktp').val(response.imgKtp);
                                if(response.urlKtp != null && response.urlKtp != ''){
                                    var cek = cekImages(response.urlKtp);
                                    if(cek){
                                        $('#img-upload').attr('src', response.urlKtp);
                                    }else{
                                        $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                                    }
                                }else{
                                    $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                                }

                                $('#provinsi').val(response.namaProvinsi);
                                $('#kabupaten').val(response.namaKota);
                                $('#kecamatan').val(response.namaKecamatan);
                                $('#desa').val(response.namaDesa);
                                $('#provinsi11').val(response.provinsiId);
                                $('#kabupaten11').val(response.kotaId);
                                $('#kecamatan11').val(response.kecamatanId);
                                $('#desa11').val(response.desaId);
                                $('#no_telp').val(response.noTelp);
                                $('#poli').val(response.idPelayanan).trigger('change').attr('disabled', true);
                                $('#dokter').val(response.idDokter);
                                CheckupAction.listOfDokter(response.idPelayanan, function (res) {
                                    if (res.length > 0) {
                                        $.each(res, function (i, item) {
                                            if (response.idDokter == item.idDokter) {
                                                $('#nama_dokter').val(item.namaDokter);
                                            }
                                        });
                                    }
                                });

                                $('#is_online').val(response.isOnline);
                                $('#tgl_antrian').val(converterDateYmdHms(response.tglAntian));
                                $('#id_checkup_online').val(response.noCheckupOnline);
                                $('#jenis_pasien').val(response.idJenisPeriksaPasien).trigger('change').attr('disabled', true);
                                $('#id_paket').val(response.idPaket);

                                if("paket_perusahaan" == response.idJenisPeriksaPasien){
                                    $('#paket_perusahaan').val(response.namaPaket).attr('disabled', true);
                                    $('#id_paket').val(response.idPaket);
                                    $('#cover_biaya_paket').val(response.tarif);
                                }

                                setTimeout(function () {
                                    if("paket_individu" == response.idJenisPeriksaPasien){
                                        $('#paket').val(response.idPaket+"|"+response.idPelayanan+"|"+response.tarif).trigger('change').attr('disabled', true);
                                        $('#dokter').val(response.idDokter);
                                        CheckupAction.listOfDokter(response.idPelayanan, function (res) {
                                            if (res.length > 0) {
                                                $.each(res, function (i, item) {
                                                    if (response.idDokter == item.idDokter) {
                                                        $('#nama_dokter').val(item.namaDokter);
                                                    }
                                                });
                                            }
                                        });
                                    }
                                },1000);

                                $('#kunjungan_val').val(response.jenisKunjungan);
                            } else {
                                $('#warning_pasien').show().fadeOut(5000);
                                $('#msg_pasien').text("Verifikasi sudah tidak bisa dilakukan, dikarenakan sudah lewat dari jam awal pelayanan...!, Silahkan lakukan pendaftaran manual.");
                            }
                        } else {
                            $('#warning_pasien').show().fadeOut(5000);
                            $('#msg_pasien').text("Verifikasi sudah tidak bisa dilakukan, dikarenakan sudah lewat dari jam awal pelayanan...!, Silahkan lakukan pendaftaran manual.");
                        }
                    } else {
                        $('#load_online').html('<i class="fa fa-search"></i> Check');
                        $('#id_pasien').val(null);
                        $('#no_bpjs').val(null);
                        $('#no_ktp').val(null);
                        $('#nama_pasien').val(null);
                        $('#jenis_kelamin').val(null);
                        $('#tempat_lahir').val(null);
                        $('#tgl_lahir').val(null);
                        $('#agama').val(null);
                        $('#profesi').val(null);
                        $('#jalan').val(null);
                        $('#suku').val(null);
                        $('#img_ktp').val(null);
                        var img = '<s:url value="/pages/images/ktp-default.jpg"/>';
                        $('#img-upload').attr('src', img);
                        $('#provinsi').val(null);
                        $('#kabupaten').val(null);
                        $('#kecamatan').val(null);
                        $('#desa').val(null);
                        $('#provinsi11').val(null);
                        $('#kabupaten11').val(null);
                        $('#kecamatan11').val(null);
                        $('#desa11').val(null);
                        $('#no_telp').val(null);
                        $('#poli').val(null).trigger('change').attr('disabled', false);
                        $('#dokter').val(null);
                        $('#nama_dokter').val(null);
                        $('#is_online').val(null);
                        $('#tgl_antrian').val(null);
                        $('#id_checkup_online').val(null);
                        $('#jenis_pasien').attr('disabled', false);
                        $('#warning_pasien').show().fadeOut(5000);
                        $('#msg_pasien').text("No Checkup Online tidak ditemukan...!");
                    }
                }
            });
        } else {
            $('#id_pasien').val(null);
            $('#no_bpjs').val(null);
            $('#no_ktp').val(null);
            $('#nama_pasien').val(null);
            $('#jenis_kelamin').val(null);
            $('#tempat_lahir').val(null);
            $('#tgl_lahir').val(null);
            $('#agama').val(null);
            $('#profesi').val(null);
            $('#jalan').val(null);
            $('#suku').val(null);
            $('#img_ktp').val(null);
            var img = '<s:url value="/pages/images/ktp-default.jpg"/>';
            $('#img-upload').attr('src', img);
            $('#provinsi').val(null);
            $('#kabupaten').val(null);
            $('#kecamatan').val(null);
            $('#desa').val(null);
            $('#provinsi11').val(null);
            $('#kabupaten11').val(null);
            $('#kecamatan11').val(null);
            $('#desa11').val(null);
            $('#no_telp').val(null);
            $('#poli').val(null).trigger('change').attr('disabled', false);
            $('#dokter').val(null);
            $('#nama_dokter').val(null);
            $('#is_online').val(null);
            $('#tgl_antrian').val(null);
            $('#id_checkup_online').val(null);
            $('#jenis_pasien').attr('disabled', false);
            $('#warning_pasien').show().fadeOut(5000);
            $('#msg_pasien').text("No Checkup Online tidak ditemukan...!");
        }
    }

    function setOrderLab(val) {
        $('#h_id_order_lab').val(val);
    }

    function showPasienOnline() {
        var table = "";
        $('#modal-pasien-online').modal({show: true, backdrop: 'static'});
        dwr.engine.setAsync(true);
        CheckupAction.daftarPasienOnline(null, {
            callback: function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var status = '';
                        var btn = "";
                        if(item.idDetailCheckup != null && item.idDetailCheckup != ''){
                            if(item.flag != null && item.flag != ''){
                                status = '<span class="span-success">sudah periksa</span>';
                            }else{
                                status = '<span class="span-biru">sudah daftar</span>';
                            }
                        }else{
                            status = '<span class="span-warning">belum periksa</span>';
                            btn = '<img style="cursor: pointer" class="hvr-grow" onclick="setNoCheckupOnline(\'' + item.noCheckupOnline + '\')" src="' + contextPathHeader + '/pages/images/icons8-create-25.png">';
                        }
                       table += '<tr>' +
                           '<td>'+item.noCheckupOnline+'</td>' +
                           '<td>'+item.idPasien+'</td>' +
                           '<td>'+item.nama+'</td>' +
                           '<td>'+item.namaPelayanan+'</td>' +
                           '<td align="center">'+status+'</td>' +
                           '<td align="center">'+btn+'</td>'+
                           '</tr>';
                        $('#body_online').html(table);
                    });
                }
            }
        });
    }

    function setNoCheckupOnline(no){
        if(no != ''){
            $('#id_online').val(no).trigger('change');
            $('#modal-pasien-online').modal('hide');
        }
    }

    function showFinger(){
        $('#modal-add-finger').modal({show:true, backdrop:'static'});
    }

    function scanFinger(){
        var hostname = window.location.origin+contextPathHeader;
        var idPasien = $('#fin_id_pas').val();
        if (idPasien != ''){
            var url=btoa(hostname+'/loginFinger.action?userId='+idPasien+'&hostname='+hostname);
            window.location.href = 'finspot:FingerspotVer;'+url;
        } else{
            $('#warning_finger').show().fadeOut(5000);
            $('#msg_finger').text("Silahkan cek kembali unputan anda...!");
        }
    }

    function cekPasienUrl(){
        var hostname = window.location.origin+contextPathHeader;
        var url_string = window.location.href;
        var url = new URL(url_string);
        var idPasien = url.searchParams.get("idPasien");
        if (idPasien != null) {
            $('#jenis_pasien').val('bpjs').trigger('change');
            $('#id_pasien').val(idPasien);
            window.history.replaceState(null, null, hostname+'/checkup/add_checkup.action');
            PasienAction.getListComboPasien(idPasien, 'bpjs', function (res) {
                if(res.length > 0){
                    $.each(res, function (i, item) {
                        $('#no_bpjs').val(item.noBpjs);
                        $('#no_ktp').val(item.noKtp);
                        $('#nama_pasien').val(item.nama);
                        $('#jenis_kelamin').val(item.jenisKelamin);
                        $('#tempat_lahir').val(item.tempatLahir);
                        $('#tgl_lahir').val(item.tglLahir);
                        $('#agama').val(item.agama);
                        $('#profesi').val(item.profesi).trigger('change');
                        $('#jalan').val(item.jalan);
                        $('#suku').val(item.suku).trigger('change');
                        $('#img_ktp').val(item.imgKtp);
                        if(item.urlKtp != null && item.urlKtp != ''){
                            var cek = cekImages(item.urlKtp);
                            if(cek){
                                $('#img-upload').attr('src', item.urlKtp);
                            }else{
                                $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                            }
                        }else{
                            $('#img-upload').attr('src', contextPathHeader+'/pages/images/no-images.png');
                        }
                        $('#provinsi').val(item.provinsi);
                        $('#kabupaten').val(item.kota);
                        $('#kecamatan').val(item.kecamatan);
                        $('#desa').val(item.desa);
                        $('#provinsi11').val(item.provinsiId);
                        $('#kabupaten11').val(item.kotaId);
                        $('#kecamatan11').val(item.kecamatanId);
                        $('#desa11').val(item.desaId);
                        $('#no_telp').val(item.noTelp);
                        $('#pendidikan').val(item.pendidikan).trigger('change');
                        $('#status_perkawinan').val(item.statusPerkawinan).trigger('change');
                        if (item.isPasienLama) {
                            $('#kunjungan_val').val("Lama");
                        } else {
                            $('#kunjungan_val').val("Baru");
                        }
                        var tipe = 'bpjs';
                        if ("paket_perusahaan" == tipe || "paket_individu" == tipe) {
                            if (item.idPelayanan != null) {
                                $('#poli').val(item.idPelayanan).trigger('change').attr('disabled', true);
                            }
                        } else {
                            if ("Y" == item.isCheckupUlang) {
                                $('#poli').val(item.idPelayanan).trigger('change').attr('disabled', true);
                            } else {
                                $('#poli').val('').trigger('change').attr('disabled', false);
                            }

                            $('#last_id_detail_checkup').val(item.idLastDetailCheckup);
                            $('#is_order_lab').val(item.isOrderLab);
                            if (item.tglCheckup != null) {
                                $('#tgl_checkup').html("Tanggal Checkup Ulang : <b>" + item.tglCheckup + "</b>");
                            } else {
                                $('#tgl_checkup').html("");
                            }
                        }
                        if (item.idPaket != null && item.idPaket != '') {
                            $('#id_paket').val(item.idPaket);
                            $('#paket_perusahaan').val(item.namaPaket);
                            $('#cover_biaya_paket').val(item.tarif);
                        }
                        if (item.isDaftar == "Y") {
                            $('#btn-save').hide();
                            $('#warning_pasien').show();
                            $('#msg_pasien').text("Pasien Sudah melakukan pendafataran...!");
                        } else {
                            $('#btn-save').show();
                            $('#warning_pasien').hide();
                            $('#msg_pasien').text("");
                            alertObatKronis(idPasien);
                        }
                        $('#no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #agama, #provinsi, #kabupaten, #kecamatan, #desa ').css('border', '');
                    });
                }
            });
        }
    }

    function setPelayananByKodeVclaim(id){
        CheckupAction.getPelayananWithIdVclaim(id, function (res) {
            if(res.idPelayanan != null){
                $('#poli').val(res.idPelayanan).trigger('change');
            }
        });
    }

    function isUangMuka(id){
        if($('#'+id).is(':checked')){
            $('#form-uang-muka').hide();
            $('#cek_poli_eksekutif').val('Y');
            $('#uang_muka, #uang_muka_val, #pembayaran').val('');
        }else{
            $('#form-uang-muka').show();
            $('#cek_poli_eksekutif').val('N');
            $('#pembayaran').val('tunai');
        }
    }

    function isVaksin(id){
        if($('#'+id).is(':checked')){
            $('#cek_is_vaksin').val('Y');
        }else{
            $('#cek_is_vaksin').val('N');
        }
    }

    function cekIsUangMuka(id){
        if($('#'+id).is(':checked')){
            $('#form-nominal_uang_muka').show();
        }else{
            $('#form-nominal_uang_muka').hide();
        }
        $('#uang_muka, #uang_muka_val').val('');
        $('#pembayaran').val('tunai');
    }

    function cekNik(id, warning){
        var noKtp = $('#'+id).val();
        noKtp = replaceUnderLine(noKtp);
        if(noKtp.length == 6){
            var cek = true;
            for (var i = 0; i < noKtp.length; i++) {
                if(noKtp.charAt(i) != "0"){
                    cek = false;
                }
            }
            if(!cek){
                $('#'+warning).show();
            }else{
                $('#'+warning).hide();
            }
        }else{
            if(noKtp.length == 16){
                $('#'+warning).hide();
            }else{
                $('#'+warning).show();
            }
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
