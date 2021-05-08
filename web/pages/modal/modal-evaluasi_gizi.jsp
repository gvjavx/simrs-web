<div class="modal fade" id="modal-gizi-evaluasi_gizi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Monitoring Evaluasi Gizi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_gizi_evaluasi_gizi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_gizi_evaluasi_gizi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_evaluasi_gizi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_evaluasi_gizi"></p>
                    </div>
                    <button type="button" onclick="showModalGizi('add_evaluasi_gizi')" class="btn btn-success"><i
                            class="fa fa-plus"></i> Tambah Monitoring Evaluasi Gizi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_gizi_evaluasi_gizi">
                        <tbody>
                        <tr id="row_gizi_add_evaluasi_gizi">
                            <td>Monitoring Evaluasi Gizi</td>
                            <td width="20%" align="center">
                                <img id="btn_gizi_add_evaluasi_gizi" class="hvr-grow"
                                     onclick="detailGizi('add_evaluasi_gizi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
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

<div class="modal fade" id="modal-gizi-add_evaluasi_gizi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Monitoring Evaluasi Gizii
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_gizi_add_evaluasi_gizi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_gizi_add_evaluasi_gizi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <label class="col-md-3">Tanggal</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control tgl" id="gizi1">
                            </div>
                        </div>
                        <label class="col-md-2">BB <small><b>(Kg)</b></small></label>
                        <div class="col-md-3">
                            <input class="form-control" id="gizi2" type="number">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Status Gizi</label>
                        <div class="col-md-4">
                            <input class="form-control" id="gizi3">
                        </div>
                        <label class="col-md-2">Intakea</label>
                        <div class="col-md-3">
                            <input class="form-control" id="gizi4">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Fisik/Klinis</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="gizi5"/>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Biokimia</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="gizi6"/>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Intervensi</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="gizi7"/>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Lain-Lain</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="gizi8"/>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-6">
                            <label>TTD Pasien/Keluarga</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('gizi_keluarga')" class="paint-canvas-ttd" id="gizi_keluarga"></canvas>
                            <input class="form-control" id="nama_gizi20" placeholder="Nama Terang">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('gizi_keluarga')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <label>TTD Ahli Gizi</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('gizi_dokter')" class="paint-canvas-ttd" id="gizi_dokter"></canvas>
                            <input class="form-control" id="nama_gizi21" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control" id="sip_dokter" placeholder="SIP/NIP">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('gizi_dokter')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_gizi_add_evaluasi_gizi" class="btn btn-success pull-right"
                        onclick="saveGizi('add_evaluasi_gizi', 'evaluasi_gizi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_gizi_add_evaluasi_gizi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>