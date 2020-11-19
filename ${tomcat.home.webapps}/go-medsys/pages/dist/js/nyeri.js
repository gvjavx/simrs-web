function choiceImg(id, url, idSet) {
    var temp = $('#temp_scala').val();
    if (temp == '') {
        $('#temp_scala').val(id);
        $('#' + id).attr('style', 'width: 100%; cursor: pointer; border-radius: 50%; border: solid 5px red');
    } else {
        if (temp == id) {
            $('#' + id).attr('style', 'width: 100%; cursor: pointer; border-radius: 50%; border: solid 5px red');
        } else {
            $('#' + temp).attr('style', 'width: 100%; cursor: pointer');
            $('#' + id).attr('style', 'width: 100%; cursor: pointer; border-radius: 50%; border: solid 5px red');
            $('#temp_scala').val(id);
        }
    }
    var canvas = document.getElementById('choice_emoji');
    var ctx = canvas.getContext('2d');
    var img = new Image();
    img.src = url;
    img.onload = function (ev) {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(img, 0, 0);
    }
    $('#' + idSet).val(id);
}

function cekNyeri(val, idSet) {
    if (val == "Ya") {
        $('.nyeri').attr('style', 'width: 100%; cursor: pointer');
        $('.nyeri').attr('onclick', 'choiceImg(this.id, $(\'#\'+this.id).attr(\'src\'), \'' + idSet + '\')');
    } else {
        $('.nyeri').attr('style', 'width: 100%; cursor: no-drop');
        $('.nyeri').removeAttr('onclick');
        $('#' + idSet).val('');
    }
}

function setNyeri(id, tahun){
    var jenis   = "";
    var tipe    = "";
    if(parseInt(tahun) > 0 && parseInt(tahun) <= 18){
        jenis = '<div class="row" style="margin-top: 10px">\n' +
            '<label class="col-md-12"><b>Wong Baker Pain Scale</b></label>'+
            '<div class="form-group">\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-0.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop;" id="0">\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">0</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Tidak Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-2.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="2" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">2</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-4.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="4" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">4</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Lebih Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-6.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="6" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">6</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Lebih Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-8.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="8" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">8</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Sangat Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-10.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="10" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">10</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Nyeri Sangat Hebat</p>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';
        $('#temp_jenis').val('emoji');
        tipe = "anak_anak";
    }else{
        jenis = '<div class="row">\n' +
            '<label class="col-md-12" style="margin-left: 10px; margin-top: -10px; padding-bottom: 10px"><b>Nomeric Rating Scale</b></label>'+
            '<div class="form-group" style="margin-top: 10px;">\n' +
            '<div class="col-md-1">\n' +
            '    <input type="color" style="margin-left: 9px; margin-top: -8px" class="js-color-picker-op  color-picker pull-left">\n' +
            '</div>\n' +
            '</div>'+
            '<div class="col-md-12 text-center">\n' +
            '    <canvas id="area_nyeri" class="paint-canvas" width="500" onmouseover="paintTtd(\'area_nyeri\', true)"></canvas>\n' +
            '</div>\n' +
            '</div>' +
            '<button style="margin-left: 10px" type="button" class="btn btn-danger" onclick="removePaint(\'area_nyeri\')"><i class="fa fa-trash"></i> Clear\n' +
            '</button>';
        $('#temp_jenis').val('scala');
        tipe = "dewasa";
    }

    if(jenis != ''){
        $('#'+id).html(jenis);
        if(tipe == "dewasa"){
            var url = contextPath+'/pages/images/scala-nyeri-number-2.jpg';
            var canvas = document.getElementById('area_nyeri');
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
    }
}