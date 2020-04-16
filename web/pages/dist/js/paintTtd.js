function paintTtd(id){

    const paintCanvas = document.querySelector("#"+id);
    const context = paintCanvas.getContext("2d");

    context.lineCap = "round";

    const colorPicker = document.querySelector(".js-color-picker");

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