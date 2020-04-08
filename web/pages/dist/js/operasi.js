function penandaAreaOperasi(){
    var url = '/simrs/pages/images/penanda-perempuan.jpg';
    console.log(url);
    var canvas = document.getElementById('area_canvas');
    var ctx = canvas.getContext('2d');
    var img = new Image();
    img.src = url;
    img.onload = function (ev) {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.drawImage(img,0,0);
    }
    $('#modal-penanda-area').modal({show:true, backdrop:'static'});
}

function clearConvas(){
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
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.drawImage(img,0,0);
    }
}