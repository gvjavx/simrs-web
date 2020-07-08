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