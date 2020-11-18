<div class="modal fade" id="modal-ina-implementasi_perawat">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Implementasu Perawat
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_implementasi_perawat">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_implementasi_perawat"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_implementasi_perawat">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_implementasi_perawat"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('implementasi')"
                                class="btn btn-success"><i class="fa fa-plus"></i> Asuhan Keperawatan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_implementasi">
                        <tbody>
                        <tr id="row_ina_implementasi">
                            <td>Implementasi Perawat</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_implementasi" class="hvr-grow"
                                     onclick="detailImpl('implementasi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-implementasi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Implementasi Perawat
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_implementasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_implementasi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="impl1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="impl2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keterangan</label>
                            <div class="col-md-9">
                                <textarea class="form-control" rows="7" id="impl3"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-6 text-center">
                                <label style="margin-left: 8px">TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="impl4" width="250"
                                        onmouseover="paintTtd('impl4')"></canvas>
                                <input style="margin-left: 10px" class="form-control nama_dokter_ri" id="nama_terang" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control sip_dokter_ri" id="sip" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('impl4')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_implementasi" class="btn btn-success pull-right"
                        onclick="saveImpl('implementasi','implementasi_perawat')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_implementasi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>