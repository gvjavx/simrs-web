function formatKodeRekening(field) {
    var kode = field.value;
    var strLen = kode.length;

    if (kode.charAt(strLen - 1) == ".") {
        if (!kode.match(/^\d{3}\.(?:\d{2}\.){0,2}$/)) {
            kode = kode.slice(0, strLen - 1);
        }
        field.value = kode;
    }
    else {
        kode = kode.replace(/\./g, "")
        var len = kode.length;
        if (kode.match(/^\d{0,9}$/)) {
            if (len <= 1) {
                // Do Nothing
            }else if (len<=2) {
                kode = kode.replace(/([0-9]{1})([0-9]{1,2})/g, "$1.$2");
            }
            else if (len <= 4) {
                kode = kode.replace(/([0-9]{1})([0-9]{1})([0-9]{1,2})/g, "$1.$2.$3");
            }
            else if(len<=6){
                kode = kode.replace(/([0-9]{1})([0-9]{1})([0-9]{2})([0-9]{1,2})/g, "$1.$2.$3.$4");
            }else{
                kode = kode.replace(/([0-9]{1})([0-9]{1})([0-9]{2})([0-9]{1,2})([0-9]{1,2})/g, "$1.$2.$3.$4.$5");
            }
            field.value = kode;
        } else {
            kode = kode.replace(/[^0-9]/g, "");
            field.value = kode;
            formatKodeRekening(field);
        }
    }
    //
    // if (kode.match(/^(\s*|(\d|\d{1})(\.\d{2}){0,2})$/)) {
    //     field.style.color = "black";
    // } else {
    //     field.style.color = "red";
    // }
}

function formatBintangKodeRekening(kodeRekening)
{
    var result=kodeRekening;
    var splitKode =kodeRekening.split(".");

    if(splitKode.length < 5 ) {
        result="";
        for (var i = 0; i <= 4; i++) {
            if (i <= splitKode.length - 1) {
                result += splitKode[i];
            } else {
                result += "*";
            }
            if (i < 4) {
                result += ".";
            }
        }
    }
    return result;
}

function formatRupiah2(field) {
    var kode = field.value;
    var number_string = kode.replace(/[^,\d]/g, '').toString(),
        split = number_string.split(','),
        sisa = split[0].length % 3,
        rupiah = split[0].substr(0, sisa),
        ribuan = split[0].substr(sisa).match(/\d{3}/gi);

    if (ribuan) {
        separator = sisa ? '.' : '';
        rupiah += separator + ribuan.join('.');
    }

    rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
    field.value = rupiah;
}
