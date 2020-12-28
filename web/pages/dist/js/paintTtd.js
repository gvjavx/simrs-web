function paintTtd(id, change){
    const paintCanvas = document.querySelector("#"+id);
    const context = paintCanvas.getContext("2d");

    context.lineCap = "round";
    context.lineWidth = "3";
    var colorPicker = "";
    if(change){
        colorPicker = document.querySelector(".js-color-picker-op");
    }else{
        colorPicker = document.querySelector(".js-color-picker");
    }

    colorPicker.addEventListener("change", function (evt) {
        context.strokeStyle = evt.target.value;
    });

    const lineWidthRange = document.querySelector(".js-line-range");
    const lineWidthLabel = document.querySelector(".js-range-value");

    lineWidthRange.addEventListener("input", function (evt) {
        const width = evt.target.value;
        lineWidthLabel.innerHTML = width+" px";
        context.lineWidth = width;
    });

    let x = 0,
        y = 0;
    let isMouseDown = false;

    const stopDrawing = function () {
        isMouseDown = false;
    };

    const startDrawing = function (evt) {
        isMouseDown = true;
        [x, y] = [evt.offsetX, evt.offsetY];
    };

    const drawLine = function (evt) {
        if (isMouseDown) {
            const newX = evt.offsetX;
            const newY = evt.offsetY;
            context.beginPath();
            context.moveTo(x, y);
            context.lineTo(newX, newY);
            context.stroke();
            x = newX;
            y = newY;
        }
    };

    paintCanvas.addEventListener("mousedown", startDrawing);
    paintCanvas.addEventListener("mousemove", drawLine);
    paintCanvas.addEventListener("mouseup", stopDrawing);
    paintCanvas.addEventListener("mouseout", stopDrawing);

    paintCanvas.addEventListener("touchstart", startDrawing);
    paintCanvas.addEventListener("touchmove", drawLine);
    paintCanvas.addEventListener("touchend", stopDrawing);
}

function removePaint(id){
    var canvas = document.getElementById(id);
    if ("area_canvas" == id) {
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = "";
        if ("Laki-Laki" == jenisKelamin) {
            url = contextPath+'/pages/images/penanda-laki-laki.jpg';
        } else {
            url = contextPath+'/pages/images/penanda-perempuan.jpg';
        }

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if("area_paru" == id){
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = contextPath+'/pages/images/paru-1.png';

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if("area_ortopedi" == id){
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = contextPath+'/pages/images/ortopedi.png';

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if("area_ginjal" == id){
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = contextPath+'/pages/images/ginjal-1.png';

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    } else if ("area_ugd" == id) {
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = "";
        if ("Laki-Laki" == jenisKelamin) {
            url = contextPath+'/pages/images/male.jpg';
        } else {
            url = contextPath+'/pages/images/female.jpg';
        }

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if ("area_gigi1" == id || "area_gigi2" == id) {
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = contextPath+'/pages/images/scala-gigi.png';

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if("area_nyeri" == id){
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = contextPath+'/pages/images/scala-nyeri-number-2.jpg';

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if("area_mata" == id){
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = contextPath+'/pages/images/mata.png';

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if("area_kulit_kelamin" == id){
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        if(jenisKelamin == "Perempuan"){
            url = contextPath + '/pages/images/kk_girl.png';
        }else{
            url = contextPath + '/pages/images/kk_man.png';
        }

        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else if("area_jantung" == id){
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        var url = contextPath + '/pages/images/jantung.png';
        var ctx = canvas.getContext('2d');
        var img = new Image();
        img.src = url;
        img.onload = function (ev) {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(img, 0, 0);
        }
    }else {
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
    }
}

function cekItemIsNull(item) {
    var res = "";
    if(item != null){
        res = item;
    }
    return res;
}

function setLabelPx(val, id){
    $('#'+id).html(val+" px");
}

function convertToDataURL(id){
    var ttd = "";
    if(id != ''){
        ttd = id.toDataURL("image/png"),
        ttd = ttd.replace(/^data:image\/(png|jpg);base64,/, "");
    }
    return ttd;
}

function loadImgCanvas(id) {
    var url = "";
    if ("Laki-Laki" == jenisKelamin) {
        url = contextPath+'/pages/images/penanda-laki-laki.jpg';
    } else {
        url = contextPath+'/pages/images/penanda-perempuan.jpg';
    }
    var canvas = document.getElementById(''+id);
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