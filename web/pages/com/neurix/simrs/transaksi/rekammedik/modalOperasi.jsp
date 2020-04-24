<div class="modal fade" id="modal-penanda-area">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-pencil"></i> Penanda Area Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                    <div class="col-md-1">
                        <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker  color-picker pull-left">
                    </div>
                    <div class="col-md-9">
                        <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
                    </div>
                    <div class="col-md-2">
                        <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                    </div>
                </div>
                <div class="text-center">
                    <canvas class="js-paint  paint-canvas" id="area_canvas"></canvas>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" onclick="clearConvas()"><i class="fa fa-pencil"></i> Clear
                </button>
                <button class="btn btn-success pull-right" onclick="saveResepObat()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>