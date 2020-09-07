<div class="modal fade" id="modal-icu-checklist_masuk_icu">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Checklist Kriteria Pasien ICU
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_icu_checklist_masuk_icu">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_checklist_masuk_icu"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_checklist_masuk_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_checklist_masuk_icu"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalICU('checklist_kriteria')" class="btn btn-success"><i class="fa fa-plus"></i> Checklist
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_icu_checklist_masuk_icu">
                        <tbody>
                        <tr id="row_icu_pre_checklist_masuk_icu">
                            <td>Checklist Kriteria Pasien ICU</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_pre_checklist_masuk_icu" class="hvr-grow"
                                     onclick="detailICU('checklist_kriteria')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_checklist_kriteria" class="hvr-grow" onclick="conICU('checklist_kriteria', 'checklist_masuk_icu')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-icu-checklist_kriteria">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Check List Kriteria Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_checklist_kriteria">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_checklist_kriteria"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped table-bordered" style="font-size: 12px">
                                <thead id="th_checklist_kriteria"></thead>
                                <tbody id="td_checklist_kriteria"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row text-center">
                        <div class="col-md-6">
                            <label >TTD DPJP</label>
                            <canvas class="paint-canvas-ttd" id="ttd_dpjp"
                                    onmouseover="paintTtd('ttd_dpjp')"></canvas>
                            <input class="form-control" id="nama_terang_dokter" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control" id="sip_dokter" placeholder="SIP">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_dpjp')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <label >TTD Pasien/Keluarga</label>
                            <canvas class="paint-canvas-ttd" id="ttd_pasien"
                                    onmouseover="paintTtd('ttd_pasien')"></canvas>
                            <input class="form-control" id="nama_terang_pasien" placeholder="Nama Terang">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_pasien')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_checklist_kriteria" class="btn btn-success pull-right" onclick="saveICU('checklist_kriteria', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_checklist_kriteria" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>