<div class="modal fade" id="modal-op-instruksi_pasca_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Intruksi Pasca Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_op_instruksi_pasca_anestesi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_instruksi_pasca_anestesi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_instruksi_pasca_anestesi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_instruksi_pasca_anestesi"></p>
                    </div>
                    <button type="button" onclick="showModalOperasi('add_instruksi_pasca_anestesi')" class="btn btn-success"><i class="fa fa-plus"></i> Instruksi Pasca Anestesi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_instruksi_pasca_anestesi">
                        <tbody>
                        <tr id="row_op_add_instruksi_pasca_anestesi">
                            <td>Instruksi Pasca Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_add_instruksi_pasca_anestesi" class="hvr-grow"
                                     onclick="detailOperasi('add_instruksi_pasca_anestesi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_add_instruksi_pasca_anestesi" class="hvr-grow btn-hide" onclick="conOP('add_instruksi_pasca_anestesi', 'instruksi_pasca_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-op-add_instruksi_pasca_anestesi">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Intruksi Pasca Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_op_add_instruksi_pasca_anestesi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_add_instruksi_pasca_anestesi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5">Keadaaan di akhir pembedahan jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="in1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Keadaan Umum</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Reflek(-)" id="in23" name="in2" /><label for="in23" >Reflek(-)</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Reflek(+)" id="in24" name="in2" /><label for="in24" >Reflek(+)</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sadar" id="in21" name="in2" /><label for="in21" >Sadar</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Belum Sadar" id="in22" name="in2" /><label for="in22" >Belum Sadar</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Syok" id="in25" name="in2" /><label for="in25" >Syok</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Lainnya" id="in26" name="in2" /><label for="in26" >Lainnya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Pernafasan</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Kontrol" id="in31" name="in3" /><label for="in31" >Kontrol</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Assited" id="in32" name="in3" /><label for="in32" >Assited</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Spontan" id="in33" name="in3" /><label for="in33" >Spontan</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Terpasang ETT" id="in34" name="in3" /><label for="in34" >Terpasang ETT</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Lainnya" id="in35" name="in3" /><label for="in35" >Lainnya</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Perfusi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="HKM" id="in41" name="in4" /><label for="in41" >HKM</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Lainnya" id="in42" name="in4" /><label for="in42" >Lainnya</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <input placeholder="Keterangan" class="form-control" onchange="$('#in42').val('Lainnya, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-offset-3 col-md-4">Capilarry refill time</label>
                        <div class="col-md-3">
                            <input class="form-control" type="number" id="in5">
                        </div>
                        <label class="col-md-1">detik</label>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-3">
                            <span>Tensi</span> <small><b>(mmHg)</b></small>
                            <input class="form-control tensi-pasien" id="in6">
                        </div>
                        <div class="col-md-3">
                            <span>Nadi</span> <small><b>(x/menit)</b></small>
                            <input class="form-control nadi-pasien" id="in7">
                        </div>
                        <div class="col-md-3">
                            <span>RR</span> <small><b>(x/menit)</b></small>
                            <input class="form-control rr-pasien" id="in8">
                        </div>
                        <div class="col-md-3">
                            <span>Suhu</span> <small><b>(C)</b></small>
                            <input class="form-control suhu-pasien" id="in9">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-3">1. Awasi</label>
                        <label class="col-md-6">Keadaan Umum Tensi Nadi Pernafasan Suhu Perdarahan setiap</label>
                        <div class="col-md-2">
                            <input class="form-control" id="in10" type="number">
                        </div>
                        <label class="col-md-1">menit</label>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">2. Posisi</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="in11" rows="2"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">3. Makan/Minum</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="in12" rows="2"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">4. Infus/Transfusi</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="in13" rows="2"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">5. Obat-Obatan</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="in14" rows="2"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">6. Lain-Lain</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="in15" rows="2"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label>TTD Dokter Spesialis Anestesi</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('ttd1')" class="paint-canvas-ttd" id="ttd1"></canvas>
                                <input class="form-control" id="nama_terang_sps" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control" id="sip_sps" placeholder="SIP">
                                <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ttd1')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_add_instruksi_pasca_anestesi" class="btn btn-success pull-right"
                        onclick="saveDataOperasi('add_instruksi_pasca_anestesi','instruksi_pasca_anestesi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_op_add_instruksi_pasca_anestesi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>