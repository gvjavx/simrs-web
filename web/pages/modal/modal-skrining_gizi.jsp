<div class="modal fade" id="modal-gizi-skrining_gizi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Skrining Gizi Awal
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_gizi_skrining_gizi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_gizi_skrining_gizi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_skrining_gizi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_skrining_gizi"></p>
                    </div>
                    <button type="button" onclick="showModalGizi('add_skrining_gizi')" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Skrining Gizi Awal
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_gizi_skrining_gizi">
                        <tbody>
                        <tr id="row_gizi_add_skrining_gizi">
                            <td>Skrining Gizi Awal</td>
                            <td width="20%" align="center">
                                <img id="btn_gizi_add_skrining_gizi" class="hvr-grow"
                                     onclick="detailGizi('add_skrining_gizi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_add_skrining_gizi" class="hvr-grow btn-hide" onclick="conGizi('add_skrining_gizi', 'skrining_gizi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-gizi-add_skrining_gizi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Skrining Gizi Awal
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_gizi_add_skrining_gizi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_gizi_add_skrining_gizi"></p>
                </div>
                <div class="box-body">
                    <div id="set_skrining_gizi"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_gizi_add_skrining_gizi" class="btn btn-success pull-right"
                        onclick="saveGizi('add_skrining_gizi','skrining_gizi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_gizi_add_skrining_gizi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>