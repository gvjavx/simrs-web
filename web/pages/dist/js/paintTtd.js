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
    // var clr = $('.js-color-picker').val();
    // context.strokeStyle = clr;

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
}

function removePaint(id){
    var canvas = document.getElementById(id);
    const context = canvas.getContext('2d');
    context.clearRect(0, 0, canvas.width, canvas.height);
}