<div class="modal fade" id="modal-aud-asesmen_triase">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Triase
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_aud_asesmen_triase">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_aud_asesmen_triase"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="addAsesmenUgd('triase')" class="btn btn-success"><i class="fa fa-plus"></i> Triase
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_aud_triase">
                        <tbody>
                        <tr id="row_aud_triase">
                            <td>Asesmen Triase</td>
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

<div class="modal fade" id="modal-aud-triase">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Triase
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_aud_triase">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_aud_triase"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Triase</label>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #d73925"></i>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Merah" id="triase1" name="tr1" /><label for="triase1" >Merah</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #e08e0b"></i>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Kuning" id="triase2" name="tr1" /><label for="triase2">Kuning</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #008d4c"></i>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Hijau" id="triase3" name="tr1" /><label for="triase3">Hijau</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #2b2b2b"></i>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Hitam" id="triase4" name="tr1" /><label for="triase4">Hitam</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keluhan</label>
                            <div class="col-md-9">
                                <textarea class="form-control anamnese" id="tr2"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <span>Tensi <small>(mmHg)</small></span>
                                <input class="form-control tensi-pasien" id="tr3">
                            </div>
                            <div class="col-md-3">
                                <span>Nadi <small>(x/menit)</small></span>
                                <input class="form-control nadi-pasien" id="tr4">
                            </div>
                            <div class="col-md-3">
                                <span>Suhu <small>(C)</small></span>
                                <input class="form-control suhu-pasien" id="tr5">
                            </div>
                            <div class="col-md-3">
                                <span>RR <small>(x/menit)</small></span>
                                <input class="form-control rr-pasien" id="tr6">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">GCS (EVM)</label>
                            <div class="col-md-3">
                                <select class="form-control" id="tr7">
                                    <option value="">[Select E]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="X">X</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-control" id="tr8">
                                    <option value="">[Select V]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="X">X</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-control" id="tr9">
                                    <option value="">[Select M]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="X">X</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Apakah terdapat keluhan nyeri</label>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="cekNyeri(this.value, 'y_inten')" type="radio" value="Tidak" id="nyeri2" name="radio_nyeri_keluhan" /><label for="nyeri2">Tidak</label>
                                    <input onclick="cekNyeri(this.value, 'y_inten')" type="radio" value="Ya" id="nyeri1" name="radio_nyeri_keluhan" /><label for="nyeri1">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Lokasi</label>
                            <div class="col-md-5">
                                <input class="form-control" style="margin-top: 7px;" id="y_lokasi">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Jenis</label>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Akut" id="nyeri3" name="radio_nyeri_jenis" /><label for="nyeri3">Akut</label>
                                    <input type="radio" value="Kronis" id="nyeri4" name="radio_nyeri_jenis" /><label for="nyeri4">Kronis</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Intensitas</label>
                            <div class="col-md-5">
                                <input class="form-control" style="margin-top: 7px;" id="y_inten">
                            </div>
                        </div>
                    </div>
                    <input id="temp_scala" type="hidden">
                    <input id="temp_jenis" type="hidden">
                    <canvas id="choice_emoji" style="display: none"></canvas>
                    <hr class="garis">
                    <div id="set_nyeri"></div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="tr10"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <textarea class="form-control resep-pasien" id="tr11"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Dokter IGD</label>
                                <canvas class="paint-canvas-ttd" id="tr12" width="220"
                                        onmouseover="paintTtd('tr12')"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_tr12" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_tr12" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('tr12')"><i
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
                <button id="save_aud_triase" class="btn btn-success pull-right"
                        onclick="saveAsesmenUgd('triase','asesmen_triase')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_aud_triase" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>