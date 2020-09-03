<div class="modal fade" id="modal-op-monitoring_pasca_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Monitoring Pasca Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_op_monitoring_pasca_anestesi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_monitoring_pasca_anestesi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_monitoring_pasca_anestesi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_monitoring_pasca_anestesi"></p>
                    </div>
                    <button type="button" onclick="showModalOperasi('data_pasca_anestesi')" class="btn btn-success"><i class="fa fa-plus"></i> Data Pasca Anestesi
                    </button>
                    <button type="button" onclick="showModalOperasi('mon_pasca_anestesi')" class="btn btn-success"><i class="fa fa-plus"></i> Monitoring Pasca Anestesi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_monitoring_pasca_anestesi">
                        <tbody>
                        <tr id="row_op_data_pasca_anestesi">
                            <td>Data Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_data_pasca_anestesi" class="hvr-grow"
                                     onclick="detailOperasi('data_pasca_anestesi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_data_pasca_anestesi" class="hvr-grow" onclick="conOP('data_pasca_anestesi', 'monitoring_pasca_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_mon_pasca_anestesi">
                            <td>Monitoring Pasca Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_mon_pasca_anestesi" class="hvr-grow"
                                     onclick="detailMonAnestesi('mon_pasca_anestesi')"
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

<div class="modal fade" id="modal-op-data_pasca_anestesi">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Data Pasca Anaestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_op_data_pasca_anestesi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_data_pasca_anestesi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5">Pasien masuk Ruang Recovery jam</label>
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
                        <div class="form-group">
                            <label class="col-md-5">Pindah ruangan jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam02" id="in6">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Keadaan saat pindah ruangan</label>
                            <div class="col-md-3">
                                <input class="form-control" id="in7" placeholder="KU">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="in8" placeholder="GCS">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <span>Tensi</span> <small><b>(mmHg)</b></small>
                            <input class="form-control tensi-pasien" id="in9">
                        </div>
                        <div class="col-md-3">
                            <span>Nadi</span> <small><b>(x/menit)</b></small>
                            <input class="form-control nadi-pasien" id="in10">
                        </div>
                        <div class="col-md-3">
                            <span>RR</span> <small><b>(x/menit)</b></small>
                            <input class="form-control rr-pasien" id="in11">
                        </div>
                        <div class="col-md-3">
                            <span>Suhu</span> <small><b>(C)</b></small>
                            <input class="form-control suhu-pasien" id="in12">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5">Jumlah Perdarahan paska op</label>
                            <div class="col-md-3">
                                <input class="form-control" id="in13" placeholder="CC">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-1">TFU</label>
                            <div class="col-md-3">
                                <input class="form-control" id="in14" >
                            </div>
                            <label class="col-md-1">Hb</label>
                            <div class="col-md-3">
                                <input class="form-control" id="in15" >
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5">Penilaian pasca anestesi dengan</label>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Aldrette score</label>
                            <div class="col-md-3">
                                <input class="form-control" id="in16" >
                            </div>
                            <label class="col-md-3">Bromage score</label>
                            <div class="col-md-3">
                                <input class="form-control" id="in17" >
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Steward score</label>
                            <div class="col-md-3">
                                <input class="form-control" id="in18" >
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label>TTD Perawat Ruang Recovery</label>
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
                <button id="save_op_data_pasca_anestesi" class="btn btn-success pull-right"
                        onclick="saveDataOperasi('data_pasca_anestesi','monitoring_pasca_anestesi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_op_data_pasca_anestesi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-mon_pasca_anestesi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Monitoring Pasca Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_op_mon_pasca_anestesi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_mon_pasca_anestesi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">Jam</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="jam" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="07:15">07:15</option>
                                    <option value="07:30">07:30</option>
                                    <option value="07:45">07:45</option>
                                    <option value="08:00">08:00</option>
                                    <option value="08:15">08:15</option>
                                    <option value="08:30">08:30</option>
                                    <option value="08:45">08:45</option>
                                    <option value="09:00">09:00</option>
                                    <option value="09:15">09:15</option>
                                    <option value="09:30">09:30</option>
                                    <option value="09:45">09:45</option>
                                    <option value="10:00">10:00</option>
                                    <option value="10:15">10:15</option>
                                    <option value="10:30">10:30</option>
                                    <option value="10:45">10:45</option>
                                    <option value="11:00">11:00</option>
                                    <option value="11:15">11:15</option>
                                    <option value="11:30">11:30</option>
                                    <option value="11:45">11:45</option>
                                    <option value="12:00">12:00</option>
                                    <option value="12:15">12:15</option>
                                    <option value="12:30">12:30</option>
                                    <option value="12:45">12:45</option>
                                    <option value="13:00">13:00</option>
                                    <option value="13:15">13:15</option>
                                    <option value="13:30">13:30</option>
                                    <option value="13:45">13:45</option>
                                    <option value="14:00">14:00</option>
                                    <option value="14:15">14:15</option>
                                    <option value="14:30">14:30</option>
                                    <option value="14:45">14:45</option>
                                    <option value="15:00">15:00</option>
                                    <option value="15:15">15:15</option>
                                    <option value="15:30">15:30</option>
                                    <option value="15:45">15:45</option>
                                    <option value="16:00">16:00</option>
                                    <option value="16:15">16:15</option>
                                    <option value="16:30">16:30</option>
                                    <option value="16:45">16:45</option>
                                    <option value="17:00">17:00</option>
                                    <option value="17:15">17:15</option>
                                    <option value="17:30">17:30</option>
                                    <option value="17:45">17:45</option>
                                    <option value="18:00">18:00</option>
                                    <option value="18:15">18:15</option>
                                    <option value="18:30">18:30</option>
                                    <option value="18:45">18:45</option>
                                    <option value="19:00">19:00</option>
                                    <option value="19:15">19:15</option>
                                    <option value="19:30">19:30</option>
                                    <option value="19:45">19:45</option>
                                    <option value="20:00">20:00</option>
                                    <option value="20:15">20:15</option>
                                    <option value="20:30">20:30</option>
                                    <option value="20:45">20:45</option>
                                    <option value="21:00">21:00</option>
                                    <option value="21:15">21:15</option>
                                    <option value="21:30">21:30</option>
                                    <option value="21:45">21:45</option>
                                    <option value="22:00">22:00</option>
                                    <option value="22:15">22:15</option>
                                    <option value="22:30">22:30</option>
                                    <option value="22:45">22:45</option>
                                    <option value="23:00">23:00</option>
                                    <option value="23:15">23:15</option>
                                    <option value="23:30">23:30</option>
                                    <option value="23:45">23:45</option>
                                    <option value="24:00">24:00</option>
                                    <option value="24:15">24:15</option>
                                    <option value="24:30">24:30</option>
                                    <option value="24:45">24:45</option>
                                    <option value="01:00">01:00</option>
                                    <option value="01:15">01:15</option>
                                    <option value="01:30">01:30</option>
                                    <option value="01:45">01:45</option>
                                    <option value="02:00">02:00</option>
                                    <option value="02:15">02:15</option>
                                    <option value="02:30">02:30</option>
                                    <option value="02:45">02:45</option>
                                    <option value="03:00">03:00</option>
                                    <option value="03:15">03:15</option>
                                    <option value="03:30">03:30</option>
                                    <option value="03:45">03:45</option>
                                    <option value="04:00">04:00</option>
                                    <option value="04:15">04:15</option>
                                    <option value="04:30">04:30</option>
                                    <option value="04:45">04:45</option>
                                    <option value="05:00">05:00</option>
                                    <option value="05:15">05:15</option>
                                    <option value="05:30">05:30</option>
                                    <option value="05:45">05:45</option>
                                    <option value="06:00">06:00</option>
                                    <option value="06:15">06:15</option>
                                    <option value="06:30">06:30</option>
                                    <option value="06:45">06:45</option>
                                </select>
                            </div>
                            <label class="col-md-2 jarak">RR</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon1" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="28">28</option>
                                    <option value="24">24</option>
                                    <option value="20">20</option>
                                    <option value="16">16</option>
                                    <option value="12">12</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">Nadi</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon2" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="180">180</option>
                                    <option value="160">160</option>
                                    <option value="140">140</option>
                                    <option value="120">120</option>
                                    <option value="100">100</option>
                                    <option value="80">80</option>
                                    <option value="60">60</option>
                                </select>
                            </div>
                            <label class="col-md-2 jarak">Tensi</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon3" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="220">220</option>
                                    <option value="200">200</option>
                                    <option value="180">180</option>
                                    <option value="160">160</option>
                                    <option value="140">140</option>
                                    <option value="120">120</option>
                                    <option value="100">100</option>
                                    <option value="80">80</option>
                                    <option value="60">60</option>
                                    <option value="40">40</option>
                                    <option value="20">20</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_mon_pasca_anestesi" class="btn btn-success pull-right"
                        onclick="saveMonAnestesi('mon_pasca_anestesi','monitoring_pasca_anestesi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_op_mon_pasca_anestesi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-chart_mon_pasca_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Chart Monitoring Pasca Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_chart_intra_anestesi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_op_chart_intra_anestesi"></p>
                    </div>
                    <div class="box-body chart-responsive">
                        <div class="chart" id="line-chart_mon_pasca_anestesi" style="height: 300px; width: 100%"></div>
                        <hr class="garis">
                        <div class="row" style="font-size: 12px">
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-1">
                                    <i class="fa fa-circle" style="color: #ff0000"></i> RR
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #0000ff"></i> Nadi
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #00cc00"></i> Tensi
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
            </div>
        </div>
    </div>
</div>