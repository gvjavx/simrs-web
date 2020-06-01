function setDataPasien(){
    var jam = $('.jam').length;
    var tgl = $('.tgl').length;
    var gejala = $('.anamnese').length;
    var penMedis = $('.penunjang-medis').length;
    var nama = $('.nama-pasien').length;
    var alamat = $('.alamat-pasien').length;
    var nobpjs = $('.no-bpjs').length;
    var age = $('.umur-pasien').length;
    var jk = $('.jenis-kelamin').length;
    var diag = $('.diagnosa-pasien').length;
    var alr = $('.alergi-pasien').length;
    var bb = $('.berat-pasien').length;
    var tb = $('.tinggi-pasien').length;

    if (jam > 0) {
        $('.jam').timepicker();
        $('.jam').val(converterDateTime(new Date()));
    }
    if (tgl > 0) {
        $('.tgl').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('.tgl').val(converterDate(new Date()));
        $('.tgl').inputmask('dd-mm-yyyy', {'placeholder': 'dd-mm-yyyy'});
    }
    if (gejala > 0) {
        $('.anamnese').val(anamnese);
    }
    if (penMedis > 0) {
        CheckupAction.getDataByKey(idDetailCheckup, "penunjang_medis", function (res) {
            if (res != '') {
                $('.penunjang-medis').val(res);
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
        $('.umur-pasien').val(umur);
    }
    if (jk > 0) {
        $('.jenis-kelamin').val(jenisKelamin);
    }
    if (diag > 0) {
        $('.diagnosa-pasien').val(diagnosa);
    }
    if (alr > 0) {
        $('.alergi-pasien').val(alergi);
    }
    if (bb > 0) {
        $('.berat-pasien').val(beratBadan);
    }
    if (tb > 0) {
        $('.tinggi-pasien').val(tinggiBadan);
        console.log('cek')
    }
}