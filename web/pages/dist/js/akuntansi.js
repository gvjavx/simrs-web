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
            if (len <= 3) {
                // Do Nothing
            }
            else if (len <= 5) {
                kode = kode.replace(/([0-9]{3})([0-9]{1,2})/g, "$1.$2");
            }
            else if (len <= 7) {
                kode = kode.replace(/([0-9]{3})([0-9]{2})([0-9]{1,2})/g, "$1.$2.$3");
            }
            else {
                kode = kode.replace(/([0-9]{3})([0-9]{2})([0-9]{2})([0-9]{1,2})/g, "$1.$2.$3.$4");
            }
            field.value = kode;
        } else {
            kode = kode.replace(/[^0-9]/g, "");
            field.value = kode;
            formatKodeRekening(field);
        }
    }

    if (kode.match(/^(\s*|(\d|\d{3})(\.\d{2}){0,3})$/)) {
        field.style.color = "black";
    } else {
        field.style.color = "red";
    }
}