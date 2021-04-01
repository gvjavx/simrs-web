function cekSession(){
    var timeout = false;
    UserLoginAction.getTimeOutSession(function (isTimeout) {
        if (isTimeout) {
            timeout = isTimeout;
            $('#modal-session').modal({show:true, backdrop:'static'});
            countDownNumber();
        }
    });
    return timeout;
}

function countDownNumber(){
    var jumlah = 10;
    var i = 0;
    var interval = setInterval(function () {
        if (jumlah >= i) {
            $('#hitung_mundur').text(jumlah);
            jumlah--;
        } else {
            clearInterval(interval);
            var contextPath = '<%= request.getContextPath() %>';
            document.location = contextPath + '/loginUser.action';
        }
    }, 500);
}

function toLogin(){
    var contextPath = '<%= request.getContextPath() %>';
    document.location = contextPath + '/loginUser.action';
}

window.checkDec = function(el){
    var ex = /^[0-9]+\.?[0-9]*$/;
    if(ex.test(el.value)==false){
        el.value = el.value.substring(0,el.value.length - 1);
    }
}

function formatRupiahAtas(angka) {
    if(angka != null && angka != '' && angka > 0){
        var reverse = angka.toString().split('').reverse().join(''),
            ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
    }else{
        return 0;
    }
}

function formatRupiahAtas2(angka) {
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

function converterDateTime(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = dd + '-' + mm + '-' + yyyy + ' ' + hh + ':' + min;
    }
    return today;
}

function converterDate(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = dd + '-' + mm + '-' + yyyy;
    }
    return today;
}

function converterTime(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = hh + ':' + min;
    }
    return today;
}

function isCanvasBlank(canvas) {
    const blank = document.createElement("canvas");
    blank.width = canvas.width;
    blank.height = canvas.height;
    return canvas.toDataURL() === blank.toDataURL();
}

function cekIcons(val) {
    var fa = val;
    if (val == "Ya") {
        fa = '<i class="fa fa-check"></i>'
    }
    return fa;
}

function cekIconsIsNotNull(val) {
    var fa = "";
    if (val == "Ya") {
        fa = '<i class="fa fa-check"></i>'
    }
    return fa;
}

function converterDateYmd(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = yyyy + '-' + mm + '-' + dd;
    }
    return today;
}

function converterDateYmdHms(dateTime) {

    var today = "";
    if (dateTime != '' && dateTime != null) {

        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        today = yyyy + '-' + mm + '-' + dd + ' ' +hh+':'+min+':'+sec;
    }
    return today;
}

function diff(start, end) {
    start = start.split(":");
    end = end.split(":");
    var startDate = new Date(0, 0, 0, start[0], start[1], 0);
    var endDate = new Date(0, 0, 0, end[0], end[1], 0);
    var diff = endDate.getTime() - startDate.getTime();
    var hours = Math.floor(diff / 1000 / 60 / 60);
    diff -= hours * 1000 * 60 * 60;
    var minutes = Math.floor(diff / 1000 / 60);
    if (hours < 0){
        hours = hours + 24;
    }
    return (hours < 9 ? "0" : "") + hours + ":" + (minutes < 9 ? "0" : "") + minutes;
}

function convertSentenceCaseUp(myString){
    if(myString != null && myString != ''){
        var rg = /(^\w{1}|\ \s*\w{1})/gi;
        myString = myString.replace(rg, function(toReplace) {
            return toReplace.toUpperCase();
        });
        return myString;
    }else{
        return "";
    }
}

function replaceTitik(val){
    var res = 0;
    if(val != ''){
        res = val.replace(/[.]/g, '');
    }
    return res;
}

function replaceStrip(val, id){
    var res = "";
    if(val != ''){
        res = val.replace(/[-]/g, '');
        res.replace(/[_]/g, '');
        $('#'+id).val(res);
    }else{
        $('#'+id).val(res);
    }
}

function replaceUnderLine(val){
    var res = '';
    if(val != ''){
        res = val.replace(/[_]/g, '');
    }
    return res;
}

function convertRpAtas(id, val, idHidden) {
    $('#'+id).val(formatRupiahAtas2(val));
    if(idHidden != '' && idHidden != null){
        val = val.replace(/[.]/g, '');
        var numbers = /^[0-9]+$/;
        if(val != ''){
            if(val.match(numbers)) {
                $('#' + idHidden).val(val);
            }
        }else{
            $('#' + idHidden).val('');
        }
    }
}

function cekNumber(id, val){
    var numbers = /^[0-9]+$/;
    if(val.match(numbers)) {
        $('#' + id).val(val);
    }else{
        $('#' + id).val('');
    }
}

function converterRupiah(value){
    var res = "";
    if(value != null && value != ''){
        res = formatRupiahAtas(value);
    }
    document.write(res);
}

function cekScrol(id, idTujuan) {
    var cek = $('#'+id).hasClass("fa fa-unlock");
    if(cek){
        $('#'+id).removeClass("fa fa-unlock");
        $('#'+id).addClass("fa fa-lock");
        $('#' + idTujuan).attr('style', 'height: 70%; overflow-y: scroll;');
    }else {
        $('#'+id).removeClass("fa fa-lock");
        $('#'+id).addClass("fa fa-unlock");
        $('#' + idTujuan).removeAttr('style');
    }
}

function setCanvasAtas(id) {
    var canvas = document.getElementById(id);
    var ctx = canvas.getContext('2d');
    var reader = new FileReader();
    reader.onload = function (event) {
        var img = new Image();
        img.onload = function () {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
        img.src = event.target.result;
    }
    reader.readAsDataURL(event.target.files[0]);
}

function setCanvasAtasWithText(id, tujuan) {
    var canvas = document.getElementById(id);
    var ctx = canvas.getContext('2d');
    var reader = new FileReader();
    reader.onload = function (event) {
        var img = new Image();
        img.onload = function () {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
        img.src = event.target.result;
    }
    reader.readAsDataURL(event.target.files[0]);
    $('#'+tujuan).val(event.target.files[0].name);
}

function setProvAtas(id, idHidden){
    var functions, mapped;
    $('#'+id).typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};
            var data = [];
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboProvinsi(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.provinsiName;
                mapped[labelItem] = {id: item.provinsiId, label: labelItem};
                functions.push(labelItem);
            });
            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById(idHidden).value = selectedObj.id;
            return namaAlat;
        }
    });
}

function setKabAtas(id, idHidden, idProv){
    var functions, mapped;
    $('#'+id).typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};
            var data = [];
            var prov = $('#'+idProv).val();
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboKota(query, prov, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                var labelItem = item.kotaName;
                mapped[labelItem] = {id: item.kotaId, label: labelItem};
                functions.push(labelItem);
            });
            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById(idHidden).value = selectedObj.id;
            return namaAlat;
        }
    });
}

function setKecAtas(id, idHidden, idKab){
    var functions, mapped;
    $('#'+id).typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};
            var data = [];
            var kab = $('#'+idKab).val();
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboKecamatan(query, kab, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                var labelItem = item.kecamatanName;
                mapped[labelItem] = {id: item.kecamatanId, label: labelItem};
                functions.push(labelItem);
            });
            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById(idHidden).value = selectedObj.id;
            return namaAlat;
        }
    });
}

function setDesAtas(id, idHidden, idKec){
    var functions, mapped;
    $('#'+id).typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};
            var data = [];
            var kec = $('#'+idKec).val();
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboDesa(query, kec, function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                var labelItem = item.desaName;
                mapped[labelItem] = {id: item.desaId, label: labelItem};
                functions.push(labelItem);
            });
            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById(idHidden).value = selectedObj.id;
            return namaAlat;
        }
    });
}

function setKotaKab(id){
    $('#'+id).typeahead({
        minLength: 3,
        source: function (query, process) {
            functions = [];
            mapped = {};
            var data = [];
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboKota(query, "", function (listdata) {
                data = listdata;
            });
            $.each(data, function (i, item) {
                var labelItem = item.kotaName;
                mapped[labelItem] = {
                    id: item.kotaId,
                    label: labelItem
                };
                functions.push(labelItem);
            });
            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var remove = selectedObj.label.substring(5);
            var namaKota = remove;
            return namaKota;
        }
    });
}

function cekDatePicker(val){
    var tgl = val.split("-");
    var cek = false;
    $.each(tgl, function (i, item) {
        var numbers = /^[0-9]+$/;
        if(!item.match(numbers)){
            cek = true;
        }
    });
    return cek;
}

function changeJenisPasien(jenis, value){
    var res = "";
    if(jenis == 'umum'){
        res = '<span class="span-biru">'+value+'</span>';
    }else if (jenis == 'bpjs'){
        res = '<span class="span-success">'+value+'</span>';
    }else if(jenis == 'rekanan'){
        res = '<span class="span-hijau-muda">'+value+'</span>';
    }else if(jenis == 'asuransi'){
        res = '<span class="span-kuning">'+value+'</span>';
    }else if(jenis == 'paket_perusahaan'){
        res = '<span class="span-ungu">'+value+'</span>';
    }else if(jenis == 'paket_individu'){
        res = '<span class="span-orange">'+value+'</span>';
    }else if(jenis == 'bpjs_rekanan'){
        res = '<span class="span-bpjs-rekanan">'+value+'</span>';
    }
    return res;
}

function convertToDataURLAtas(id){
    var ttd = "";
    if(id != ''){
        ttd = id.toDataURL("image/png"),
            ttd = ttd.replace(/^data:image\/(png|jpg);base64,/, "");
    }
    return ttd;
}

function cekImages(url){
    var http = new XMLHttpRequest();
    http.open('HEAD', url, false);
    http.send();
    return http.status!=404;
}

function imagesDefault(url){
    var res = contextPathHeader+'/pages/images/no-images.png';
    if(url != null && url != ''){
        if(cekImages(url)){
            res = url;
        }
    }
    var set = '<div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"\n' +
        'class="card card-4 pull-right">\n' +
        '<img border="2" id="img_ktp" src="'+res+'"\n' +
        'style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">\n' +
        '</div>';
    return set;
}

function inputWarning(war, suc){
    var warn =$('#'+war).is(':visible');
    if (warn){
        $('#'+suc).show().fadeOut(3000);
        $('#'+war).hide()
    }
}

function getPartDate(tanggal, tipe){
    var res = "";
    if(tanggal != '' && tanggal != null){
        tanggal = new Date(dateTime);
        if(tipe == 'yyyy'){
            res = tanggal.getFullYear();
        }else if(tipe == 'mm'){
            res = String(tanggal.getMonth() + 1).padStart(2, '0'); //January is 0!
        }else if(tipe == 'dd'){
            res = String(tanggal.getDate()).padStart(2, '0');
        }else if(tipe == 'hh'){
            res = ((tanggal.getHours() < 10 ? '0' : '') + tanggal.getHours());
        }else if(tipe == 'min'){
            res = ((tanggal.getMinutes() < 10 ? '0' : '') + tanggal.getMinutes());
        }else if(tipe == 'sec'){
            res = tanggal.getSeconds();
        }
    }
    return res;
}

function cekFileAtas(id){
    return $('#'+id).get(0).files.length === 0;
}

function postAtas(path, params) {
    var method='post';
    const form = document.createElement('form');
    form.method = method;
    form.action = path;
    for (const key in params) {
        if (params.hasOwnProperty(key)) {
            const hiddenField = document.createElement('input');
            hiddenField.type = 'hidden';
            hiddenField.name = key;
            hiddenField.value = params[key];
            form.appendChild(hiddenField);
        }
    }
    document.body.appendChild(form);
    form.submit();
}

function converterDateTimeComplex(dateTime) {
    var today = "";
    if (dateTime != '' && dateTime != null) {
        today = new Date(dateTime);
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
        var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
        var sec = today.getSeconds();
        var ml = today.getMilliseconds();
        today = yyyy+ '-' +mm+ '-' + dd + ' ' + hh + ':' + min+':'+sec+'.'+ml;
    }
    return today;
}