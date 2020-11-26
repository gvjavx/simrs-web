<div class="modal fade" id="modal-sps-spesialis_mata">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Mata
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_sps_spesialis_mata">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_mata"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
                    </div>
                    <button type="button" onclick="showModalSPS('pengkajian_mata')" class="btn btn-success"><i class="fa fa-plus"></i> Pengkajian Awal
                    </button>
                        <button type="button" onclick="showModalSPS('visus')" class="btn btn-success"><i class="fa fa-plus"></i> Pemeriksaan Visus dan Refraksi
                        </button>
                        <button type="button" onclick="showModalSPS('ophtal')" class="btn btn-success"><i class="fa fa-plus"></i> Pemeriksaan Ophtalmologist
                        </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_sps_spesialis_mata">
                        <tbody>
                        <tr id="row_sps_pengkajian_mata">
                            <td>Pengkajian Awal</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_pengkajian_mata" class="hvr-grow"
                                     onclick="detailSPS('pengkajian_mata')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pengkajian_mata" class="hvr-grow btn-hide" onclick="conSPS('pengkajian_mata', 'spesialis_mata')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_visus">
                            <td>Pemeriksaan Visus dan Refraksi</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_visus" class="hvr-grow"
                                     onclick="detailSPS('visus')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_visus" class="hvr-grow btn-hide" onclick="conSPS('visus', 'spesialis_mata')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_ophtal">
                            <td>Pemeriksaan Ophtalmologist</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_ophtal" class="hvr-grow"
                                     onclick="detailSPS('ophtal')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ophtal" class="hvr-grow btn-hide" onclick="conSPS('ophtal', 'spesialis_mata')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-pengkajian_mata">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Awal
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_pengkajian_mata">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_pengkajian_mata"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    <input class="form-control tgl" id="p1"/>
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
                                    <input class="form-control jam" id="p2"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Anamnesa</label>
                            <div class="col-md-9">
                                <textarea class="form-control anamnese" id="p3"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keluhan Utama</label>
                            <div class="col-md-9">
                                <textarea class="form-control anamnese" id="p4"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_sps_pengkajian_mata" class="btn btn-success pull-right"
                        onclick="saveSPS('pengkajian_mata','spesialis_mata')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_pengkajian_mata" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-visus">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan Visus Dan Refraksi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_visus">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_visus"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <label class="col-md-12"><b>OD</b></label>
                    </div>
                    <div class="row">
                        <label class="col-md-2" style="margin-top: 30px">Visual Aquality</label>
                        <div class="col-md-2" >
                            <span>&nbsp;</span>
                            <select class="form-control select2" id="v1">
                                <option value="">Select</option>
                                <option value="LP-">LP-</option>
                                <option value="LP+">LP+</option>
                                <option value="1/300">1/300</option>
                                <option value="0.25/60">0.25/60</option>
                                <option value="0.50/60">0.50/60</option>
                                <option value="1/60">1/60</option>
                                <option value="1.5/60">1.5/60</option>
                                <option value="2/60">2/60</option>
                                <option value="20/400">20/400</option>
                                <option value="20/200">20/200</option>
                                <option value="20/100">20/100</option>
                                <option value="20/80">20/80</option>
                                <option value="20/70">20/70</option>
                                <option value="20/60">20/60</option>
                                <option value="20/50">20/50</option>
                                <option value="20/40">20/40</option>
                                <option value="20/30">20/30</option>
                                <option value="20/25">20/25</option>
                                <option value="20/20">20/20</option>
                            </select>
                        </div>
                        <div class="col-md-2" >
                            <span>Sph</span>
                            <select class="form-control select2 sph-val" id="v2">
                            </select>
                        </div>
                        <div class="col-md-2" >
                            <span>Cyl</span>
                            <input style="margin-top: 7px" class="form-control" id="v3">
                        </div>
                        <div class="col-md-2" >
                            <span>X</span>
                            <input style="margin-top: 7px" class="form-control" id="v4">
                        </div>
                        <div class="col-md-2" >
                            <span>Visual Akhir</span>
                            <select class="form-control select2" id="v5">
                                <option value="">Select</option>
                                <option value="LP-">LP-</option>
                                <option value="LP+">LP+</option>
                                <option value="1/300">1/300</option>
                                <option value="0.25/60">0.25/60</option>
                                <option value="0.50/60">0.50/60</option>
                                <option value="1/60">1/60</option>
                                <option value="1.5/60">1.5/60</option>
                                <option value="2/60">2/60</option>
                                <option value="20/400">20/400</option>
                                <option value="20/200">20/200</option>
                                <option value="20/100">20/100</option>
                                <option value="20/80">20/80</option>
                                <option value="20/70">20/70</option>
                                <option value="20/60">20/60</option>
                                <option value="20/50">20/50</option>
                                <option value="20/40">20/40</option>
                                <option value="20/30">20/30</option>
                                <option value="20/25">20/25</option>
                                <option value="20/20">20/20</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px">KML</label>
                        <div class="col-md-2" >
                            <span>Sph</span>
                            <select class="form-control select2 sph-val" id="v6">
                            </select>
                        </div>
                        <div class="col-md-2" >
                            <span>Cyl</span>
                            <input style="margin-top: 7px" class="form-control" id="v7">
                        </div>
                        <div class="col-md-2" >
                            <span>X</span>
                            <input style="margin-top: 7px" class="form-control" id="v8">
                        </div>
                        <div class="col-md-2" >
                            <span>Visual Akhir</span>
                            <select class="form-control select2" id="v9">
                                <option value="">Select</option>
                                <option value="LP-">LP-</option>
                                <option value="LP+">LP+</option>
                                <option value="1/300">1/300</option>
                                <option value="0.25/60">0.25/60</option>
                                <option value="0.50/60">0.50/60</option>
                                <option value="1/60">1/60</option>
                                <option value="1.5/60">1.5/60</option>
                                <option value="2/60">2/60</option>
                                <option value="20/400">20/400</option>
                                <option value="20/200">20/200</option>
                                <option value="20/100">20/100</option>
                                <option value="20/80">20/80</option>
                                <option value="20/70">20/70</option>
                                <option value="20/60">20/60</option>
                                <option value="20/50">20/50</option>
                                <option value="20/40">20/40</option>
                                <option value="20/30">20/30</option>
                                <option value="20/25">20/25</option>
                                <option value="20/20">20/20</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px">Addisi</label>
                        <div class="col-md-4" >
                            <span>&nbsp;</span>
                            <input style="margin-top: 7px" class="form-control" id="v10">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px">Keratometri</label>
                        <div class="col-md-4" >
                            <span>K1</span>
                            <input style="margin-top: 7px" class="form-control" id="v11">
                        </div>
                        <div class="col-md-4" >
                            <span>K2</span>
                            <input style="margin-top: 7px" class="form-control" id="v12">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px">Tonometri</label>
                        <div class="col-md-4" >
                            <span>Non Contact <small>(mmHg)</small></span>
                            <input style="margin-top: 7px" class="form-control" id="v13">
                        </div>
                        <div class="col-md-4" >
                            <span>Schiotz <small>(mmHg)</small></span>
                            <input style="margin-top: 7px" class="form-control" id="v14">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>OS</b></label>
                    </div>
                    <div class="row">
                        <label class="col-md-2" style="margin-top: 30px">Visual Aquality</label>
                        <div class="col-md-2" >
                            <span>&nbsp;</span>
                            <select class="form-control select2" id="v15">
                                <option value="">Select</option>
                                <option value="LP-">LP-</option>
                                <option value="LP+">LP+</option>
                                <option value="1/300">1/300</option>
                                <option value="0.25/60">0.25/60</option>
                                <option value="0.50/60">0.50/60</option>
                                <option value="1/60">1/60</option>
                                <option value="1.5/60">1.5/60</option>
                                <option value="2/60">2/60</option>
                                <option value="20/400">20/400</option>
                                <option value="20/200">20/200</option>
                                <option value="20/100">20/100</option>
                                <option value="20/80">20/80</option>
                                <option value="20/70">20/70</option>
                                <option value="20/60">20/60</option>
                                <option value="20/50">20/50</option>
                                <option value="20/40">20/40</option>
                                <option value="20/30">20/30</option>
                                <option value="20/25">20/25</option>
                                <option value="20/20">20/20</option>
                            </select>
                        </div>
                        <div class="col-md-2" >
                            <span>Sph</span>
                            <select class="form-control select2 sph-val" id="v16">
                            </select>
                        </div>
                        <div class="col-md-2" >
                            <span>Cyl</span>
                            <input style="margin-top: 7px" class="form-control" id="v17">
                        </div>
                        <div class="col-md-2" >
                            <span>X</span>
                            <input style="margin-top: 7px" class="form-control" id="v18">
                        </div>
                        <div class="col-md-2" >
                            <span>Visual Akhir</span>
                            <select class="form-control select2" id="v19">
                                <option value="">Select</option>
                                <option value="LP-">LP-</option>
                                <option value="LP+">LP+</option>
                                <option value="1/300">1/300</option>
                                <option value="0.25/60">0.25/60</option>
                                <option value="0.50/60">0.50/60</option>
                                <option value="1/60">1/60</option>
                                <option value="1.5/60">1.5/60</option>
                                <option value="2/60">2/60</option>
                                <option value="20/400">20/400</option>
                                <option value="20/200">20/200</option>
                                <option value="20/100">20/100</option>
                                <option value="20/80">20/80</option>
                                <option value="20/70">20/70</option>
                                <option value="20/60">20/60</option>
                                <option value="20/50">20/50</option>
                                <option value="20/40">20/40</option>
                                <option value="20/30">20/30</option>
                                <option value="20/25">20/25</option>
                                <option value="20/20">20/20</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px">KML</label>
                        <div class="col-md-2" >
                            <span>Sph</span>
                            <select class="form-control select2 sph-val" id="v20">
                            </select>
                        </div>
                        <div class="col-md-2" >
                            <span>Cyl</span>
                            <input style="margin-top: 7px" class="form-control" id="v21">
                        </div>
                        <div class="col-md-2" >
                            <span>X</span>
                            <input style="margin-top: 7px" class="form-control" id="v22">
                        </div>
                        <div class="col-md-2" >
                            <span>Visual Akhir</span>
                            <select class="form-control select2" id="v23">
                                <option value="">Select</option>
                                <option value="LP-">LP-</option>
                                <option value="LP+">LP+</option>
                                <option value="1/300">1/300</option>
                                <option value="0.25/60">0.25/60</option>
                                <option value="0.50/60">0.50/60</option>
                                <option value="1/60">1/60</option>
                                <option value="1.5/60">1.5/60</option>
                                <option value="2/60">2/60</option>
                                <option value="20/400">20/400</option>
                                <option value="20/200">20/200</option>
                                <option value="20/100">20/100</option>
                                <option value="20/80">20/80</option>
                                <option value="20/70">20/70</option>
                                <option value="20/60">20/60</option>
                                <option value="20/50">20/50</option>
                                <option value="20/40">20/40</option>
                                <option value="20/30">20/30</option>
                                <option value="20/25">20/25</option>
                                <option value="20/20">20/20</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px;">Addisi</label>
                        <div class="col-md-4" >
                            <span>&nbsp;</span>
                            <input style="margin-top: 7px" class="form-control" id="v24">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px">Keratometri</label>
                        <div class="col-md-4" >
                            <span>K1</span>
                            <input style="margin-top: 7px" class="form-control" id="v25">
                        </div>
                        <div class="col-md-4" >
                            <span>K2</span>
                            <input style="margin-top: 7px" class="form-control" id="v26">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2" style="margin-top: 30px">Tonometri</label>
                        <div class="col-md-4" >
                            <span>Non Contact <small>(mmHg)</small></span>
                            <input style="margin-top: 7px" class="form-control" id="v27">
                        </div>
                        <div class="col-md-4" >
                            <span>Schiotz <small>(mmHg)</small></span>
                            <input style="margin-top: 7px" class="form-control" id="v28">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_sps_visus" class="btn btn-success pull-right"
                        onclick="saveSPS('visus','spesialis_mata')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_visus" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-ophtal">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan Opthtalmologist
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_ophtal">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_ophtal"></p>
                </div>
                <div class="box-body">
                    <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                        <div class="col-md-1">
                            <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker-op  color-picker pull-left">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <div class="text-center">
                                <canvas class="paint-canvas" style="cursor: pointer;" id="area_mata" onmouseover="paintTtd('area_mata', true)"></canvas>
                            </div>
                            <canvas style="display: none" id="area_cek"></canvas>
                            <button style="margin-top: -5px; margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('area_mata')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <textarea id="o1" class="form-control" placeholder="Keterangan Gambar"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Posisi</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label ">Pergerakan</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Palpebra Superior</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Palpebra Inferior</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Conj Tarsal Superior</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Conj Tarsal Inferior</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Conj Bulbi</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Cornea</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">C.O.A</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Pulpil</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Iris</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Vitreous</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Lensa</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Fenduscopy</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Diagnosa</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Therapy</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 o-label">Anjuran</label>
                            <div class="col-md-4">
                                <input class="form-control od-isi">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control os-isi">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD</label>
                                <canvas class="paint-canvas-ttd" id="ttd1" width="220"
                                        onmouseover="paintTtd('ttd1')"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_ttd1" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_ttd1" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1')"><i
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
                <button id="save_sps_ophtal" class="btn btn-success pull-right"
                        onclick="saveSPS('ophtal','spesialis_mata')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_ophtal" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-spesialis_mata_op">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Mata Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_sps_spesialis_mata_op">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_mata_op"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning_op">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning_op"></p>
                    </div>
                    <button type="button" onclick="showModalSPS('pengkajian_mata_op')" class="btn btn-success"><i class="fa fa-plus"></i> Pengkajian Awal
                    </button>
                    <button type="button" onclick="showModalSPS('biometri')" class="btn btn-success"><i class="fa fa-plus"></i> Pemeriksaan Biometri
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_sps_spesialis_mata_op">
                        <tbody>
                        <tr id="row_sps_pengkajian_mata_op">
                            <td>Pengkajian Awal</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_pengkajian_mata_op" class="hvr-grow"
                                     onclick="detailSPS('pengkajian_mata_op')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pengkajian_mata_op" class="hvr-grow btn-hide" onclick="conSPS('pengkajian_mata_op', 'spesialis_mata_op')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_biometri">
                            <td>Hasil Pemeriksaan Biometri</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_biometri" class="hvr-grow"
                                     onclick="detailSPS('biometri')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_biometri" class="hvr-grow btn-hide" onclick="conSPS('biometri', 'spesialis_mata_op')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-pengkajian_mata_op">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Awal
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_pengkajian_mata_op">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_pengkajian_mata_op"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    <input id="pk1" class="form-control tgl"/>
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
                                    <input id="pk2" class="form-control jam"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Anamnesa</label>
                            <div class="col-md-9">
                                <textarea id="pk3" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keluhan Utama</label>
                            <div class="col-md-9">
                                <textarea id="pk4" class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_sps_pengkajian_mata_op" class="btn btn-success pull-right"
                        onclick="saveSPS('pengkajian_mata_op','spesialis_mata_op')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_pengkajian_mata_op" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-biometri">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Hasil Pemeriksaan Biometri
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_biometri">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_biometri"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <label class="col-md-3"><b>Pemeriksaan</b></label>
                        <label class="col-md-4"><b>OD</b></label>
                        <label class="col-md-4"><b>OS</b></label>
                    </div>
                    <div class="row">
                        <label class="col-md-12">Keratometri</label>
                    </div>
                    <div class="row">
                        <label class="col-md-3 bi-label">K1</label>
                        <div class="col-md-4">
                            <input class="form-control bi-od">
                        </div>
                        <div class="col-md-4">
                            <input class="form-control bi-os">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label">K2</label>
                        <div class="col-md-4">
                            <input class="form-control bi-od">
                        </div>
                        <div class="col-md-4">
                            <input class="form-control bi-os">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label">ACD</label>
                        <div class="col-md-4">
                            <input class="form-control bi-od">
                        </div>
                        <div class="col-md-4">
                            <input class="form-control bi-os">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-12">Kalkukasi "IOT POWER"</label>
                    </div>
                    <div class="row">
                        <label class="col-md-offset-3 col-md-2"><b>Immersion</b></label>
                        <label class="col-md-2"><b>Contact</b></label>
                        <label class="col-md-2"><b>Immersion</b></label>
                        <label class="col-md-2"><b>Contact</b></label>
                    </div>
                    <div class="row">
                        <label class="col-md-3 bi-label-2">Axial Length</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Nano Fold/OP Fold</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Oculentis (L 313)</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Oculentis (LS 313 MP15)</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Oculentis (LS 313 MP30)</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">C Flex / Clare</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Cristalens Artis</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Lucidis 124 M</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">SBL - 3</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Nanao Iris</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">Ra 25</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">AT Lisa Tri 839 MP</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3 bi-label-2">CT Aspina 409 MP</label>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <input class="form-control bi-label-2" placeholder="Lainnya">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <input class="form-control bi-label-2" placeholder="Lainnya">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <input class="form-control bi-label-2" placeholder="Lainnya">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <input class="form-control bi-label-2" placeholder="Lainnya">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <input class="form-control bi-label-2" placeholder="Lainnya">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-od-co">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-im">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control bi-os-co">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_sps_biometri" class="btn btn-success pull-right"
                        onclick="saveSPS('biometri','spesialis_mata_op')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_biometri" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>