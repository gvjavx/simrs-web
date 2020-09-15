<div class="modal fade" id="modal-ina-edukasi_pasien_terintegrasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Edukasi Pasien dan Keluarga Terintegrasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_edukasi_pasien_terintegrasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_edukasi_pasien_terintegrasi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_edukasi_pasien_terintegrasi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_edukasi_pasien_terintegrasi"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button type="button" class="btn btn-success dropdown-toggle"
                                data-toggle="dropdown" style="height: 34px">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="showModalAsesmenRawatInap('ept_tppri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> TPPRI</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_perawat')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Perawat</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_dpjp')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> DPJP</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_nutrisionis')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Nutrisionis</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_farmasi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Farmasi</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_rehabilitasi_medis')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Rehabilitasi Medis</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_dokter')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Dokter/Perawat/Bidan</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_radiografer')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Radiografer</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_ppa')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Kolaborasi Antar PPA</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_edukasi_pasien_terintegrasi">
                        <tbody>
                        <tr id="row_ina_ept_tppri">
                            <td>TPPRI</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_tppri" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_tppri')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_tppri" class="hvr-grow btn-hide" onclick="conRI('ept_tppri', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_perawat">
                            <td>Perawat</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_perawat" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_perawat')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_perawat" class="hvr-grow btn-hide" onclick="conRI('ept_perawat', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_dpjp">
                            <td>DPJP</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_dpjp" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_dpjp')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_dpjp" class="hvr-grow btn-hide" onclick="conRI('ept_dpjp', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_nutrisionis">
                            <td>Nutrisionis</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_nutrisionis" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_nutrisionis')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_nutrisionis" class="hvr-grow btn-hide" onclick="conRI('ept_nutrisionis', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_farmasi">
                            <td>Farmasi</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_farmasi" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_farmasi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_farmasi" class="hvr-grow btn-hide" onclick="conRI('ept_farmasi', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_rehabilitasi_medis">
                            <td>Rehabilitasi Medis</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_rehabilitasi_medis" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_rehabilitasi_medis')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_rehabilitasi_medis" class="hvr-grow btn-hide" onclick="conRI('ept_rehabilitasi_medis', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_dokter">
                            <td>Dokter/Perawat/Bidan</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_dokter" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_dokter')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_dokter" class="hvr-grow btn-hide" onclick="conRI('ept_dokter', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_radiografer">
                            <td>Radiografer</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_radiografer" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_radiografer')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_radiografer" class="hvr-grow btn-hide" onclick="conRI('ept_radiografer', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_ppa">
                            <td>Kolaborasi Antar PPA</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_ppa" class="hvr-grow"
                                     onclick="listEdukasiPasien('ept_ppa')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ept_ppa" class="hvr-grow btn-hide" onclick="conRI('ept_ppa', 'edukasi_pasien_terintegrasi', '0', 'edukasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-ina-ept_tppri">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> TPPRI
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_tppri">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_tppri"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_tppri">
                                <thead id="head_ept_tppri">
                                </thead>
                                <tbody id="body_ept_tppri">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_tppri"></div>
                    <hr class="garis">
                    <div id="temp_ept_tppri"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_tppri" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_tppri','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_tppri" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_perawat">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Perawat
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_perawat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_perawat"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_perawat">
                                <thead id="head_ept_perawat">
                                </thead>
                                <tbody id="body_ept_perawat">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_perawat"></div>
                    <hr class="garis">
                    <div id="temp_ept_perawat"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_perawat" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_perawat','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_perawat" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_dpjp">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> DPJP
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_dpjp">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_dpjp"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_dpjp">
                                <thead id="head_ept_dpjp">
                                </thead>
                                <tbody id="body_ept_dpjp">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_dpjp"></div>
                    <hr class="garis">
                    <div id="temp_ept_dpjp"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_dpjp" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_dpjp','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_dpjp" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_nutrisionis">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Nutrisionis
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_nutrisionis">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_nutrisionis"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_nutrisionis">
                                <thead id="head_ept_nutrisionis">
                                </thead>
                                <tbody id="body_ept_nutrisionis">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_nutrisionis"></div>
                    <hr class="garis">
                    <div id="temp_ept_nutrisionis"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_nutrisionis" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_nutrisionis','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_nutrisionis" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_farmasi">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Farmasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_farmasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_farmasi"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_farmasi">
                                <thead id="head_ept_farmasi">
                                </thead>
                                <tbody id="body_ept_farmasi">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_farmasi"></div>
                    <hr class="garis">
                    <div id="temp_ept_farmasi"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_farmasi" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_farmasi','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_farmasi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_rehabilitasi_medis">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rehabilitas Medis
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_rehabilitasi_medis">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_rehabilitasi_medis"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_rehabilitasi_medis">
                                <thead id="head_ept_rehabilitasi_medis">
                                </thead>
                                <tbody id="body_ept_rehabilitasi_medis">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_rehabilitasi_medis"></div>
                    <hr class="garis">
                    <div id="temp_ept_rehabilitasi_medis"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_rehabilitasi_medis" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_rehabilitasi_medis','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_rehabilitasi_medis" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_dokter">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Dokter/Perawat/Bidan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_dokter">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_dokter"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_dokter">
                                <thead id="head_ept_dokter">
                                </thead>
                                <tbody id="body_ept_dokter">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_dokter"></div>
                    <hr class="garis">
                    <div id="temp_ept_dokter"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_dokter" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_dokter','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_dokter" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_radiografer">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Radiografer
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_radiografer">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_radiografer"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_radiografer">
                                <thead id="head_ept_radiografer">
                                </thead>
                                <tbody id="body_ept_radiografer">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_radiografer"></div>
                    <hr class="garis">
                    <div id="temp_ept_radiografer"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_radiografer" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_radiografer','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_radiografer" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ept_ppa">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Kolaborasi PPA (Kasus Kompleks)
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_ppa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_ppa"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px" id="table_ept_ppa">
                                <thead id="head_ept_ppa">
                                </thead>
                                <tbody id="body_ept_ppa">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="q_ept_ppa"></div>
                    <hr class="garis">
                    <div id="temp_ept_ppa"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_ppa" class="btn btn-success pull-right"
                        onclick="saveEdukasiPasien('ept_ppa','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_ppa" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>