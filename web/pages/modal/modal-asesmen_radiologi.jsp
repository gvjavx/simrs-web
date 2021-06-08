<div class="modal fade" id="modal-asesmen_radiologi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Radiologi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_asesmen_radiologi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_asesmen_radiologi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalPJ('add_asesmen_radiologi')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_aud_triase">
                        <tbody>
                        <tr id="row_aud_triase">
                            <td>Asesmen Radiologi</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_triase" class="hvr-grow" onclick="detailAud('triase')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_triase" class="hvr-grow btn-hide" onclick="conUGD('triase', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-add_asesmen_radiologi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Asesmen Radiologi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add_asesmen_radiologi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add_asesmen_radiologi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <label class="col-md-3" id="label_r1">Jenis Foto</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="r1" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="row top_jarak">
                        <label class="col-md-3" id="label_r2">Dosis</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="r2" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="row top_jarak">
                        <label class="col-md-3" id="label_r3">Resiko dan Manfaat</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="r3" rows="3"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_add_asesmen_radiologi" class="btn btn-success pull-right"
                        onclick="savePJ('add_asesmen_radiologi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_add_asesmen_radiologi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>