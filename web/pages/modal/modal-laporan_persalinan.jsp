<div class="modal fade" id="modal-rb-laporan_persalinan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Laporan Persalinan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_rb_laporan_persalinan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_laporan_persalinan"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_laporan_persalinan">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_laporan_persalinan"></p>
                    </div>
                    <button type="button" onclick="showModalRB('data_laporan_persalinan_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Data Laporan
                    </button>
                    <button type="button" onclick="showModalRB('laporan_persalinan_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Laporan Persalinan
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_data_laporan">
                        <tbody>
                        <tr id="row_rb_data_laporan_persalinan_rb">
                            <td>Data Laporan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_data_laporan_persalinan_rb" class="hvr-grow"
                                     onclick="detailRB('data_laporan_persalinan_rb')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_data_laporan_persalinan_rb" class="hvr-grow" onclick="conRB('data_laporan_persalinan_rb', 'laporan_persalinan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_rb_laporan_persalinan_rb">
                            <td>Laporan Persalinan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_laporan_persalinan_rb" class="hvr-grow"
                                     onclick="detailRB('laporan_persalinan_rb')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_laporan_persalinan_rb" class="hvr-grow" onclick="conRB('laporan_persalinan_rb', 'laporan_persalinan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-rb-data_laporan_persalinan_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Data Laporan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_data_laporan_persalinan_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_data_laporan_persalinan_rb"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Primer</label>
                            <div class="col-md-8">
                                <textarea class="form-control diagnosa-pasien" id="la1"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Sekunder</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="la2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Dokter / Bidan Penolong</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Bidang Pendamping</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">HPHT</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la5">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Status Ketuban</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Utuh" id="la61"
                                           name="la6"/><label for="la61">Utuh</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Pecah" id="la62"
                                           name="la6"/><label for="la62">Pecah</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Pecah Ketuban</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="la7">
                                </div>
                            </div>
                            <label class="col-md-1">jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam02" id="la8">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_rb_data_laporan_persalinan_rb" class="btn btn-success pull-right"
                        onclick="saveRB('data_laporan_persalinan_rb','laporan_persalinan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_data_laporan_persalinan_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rb-laporan_persalinan_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Laporan Persalinan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_laporan_persalinan_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_laporan_persalinan_rb"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kala I</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="rb1"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kala II</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="rb2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kala III</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="rb3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kala IV</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="rb4"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Dokter / Bidan Penolong</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_laporan_persalinan_rb" width="220"
                                        onmouseover="paintTtd('ttd1_laporan_persalinan_rb')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd1" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd1" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_laporan_persalinan_rb')"><i
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
                <button id="save_rb_laporan_persalinan_rb" class="btn btn-success pull-right"
                        onclick="saveRB('laporan_persalinan_rb','laporan_persalinan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_laporan_persalinan_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>