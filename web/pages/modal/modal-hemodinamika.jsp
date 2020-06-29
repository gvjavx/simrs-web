<div class="modal fade" id="modal-icu-hemodinamika">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Hemodinamika
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_icu_hemodinamika">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_hemodinamika"></p>
                    </div>
                    <button onclick="showModalICU('hemodinamika_icu')" type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Hemodinamika
                    </button>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_icu" style="font-size: 12px">
                        <thead>
                        <tr style="font-weight: bold">
                            <td>Tanggal</td>
                            <td>Waktu</td>
                            <td>Tensi</td>
                            <td>BP</td>
                            <td>HI</td>
                            <td>RR</td>
                            <td>EKG</td>
                            <td>ICP</td>
                            <td>IBP</td>
                            <td>CVP</td>
                            <td>MAP</td>
                        </tr>
                        </thead>
                        <tbody id="body_hemodinamika">

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-hemodinamika_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Hemodinamika
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_hemodinamika_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_hemodinamika_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="id1" style="width: 100%">
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
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">Tensi</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id2" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="41">41</option>
                                    <option value="40">40</option>
                                    <option value="39">39</option>
                                    <option value="38">38</option>
                                    <option value="37">37</option>
                                    <option value="36">36</option>
                                    <option value="35">35</option>
                                    <option value="34">34</option>
                                    <option value="33">33</option>
                                    <option value="32">32</option>
                                    <option value="31">31</option>
                                </select>
                            </div>
                            <label class="col-md-1 jarak">BP</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id3" style="width: 100%">
                                    <option value="">[Select One]</option>
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
                                    <option value="0">0</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">HI</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id4" style="width: 100%">
                                    <option value="">[Select One]</option>
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
                                    <option value="0">0</option>
                                </select>
                            </div>
                            <label class="col-md-1 jarak">RR</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id5" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="100">100</option>
                                    <option value="90">90</option>
                                    <option value="80">80</option>
                                    <option value="70">70</option>
                                    <option value="60">60</option>
                                    <option value="50">50</option>
                                    <option value="40">40</option>
                                    <option value="30">30</option>
                                    <option value="20">20</option>
                                    <option value="10">10</option>
                                    <option value="0">0</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">EKG</label>
                            <div class="col-md-9">
                                <input class="form-control" id="id6" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">ICP</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id7" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="50">50</option>
                                    <option value="40">40</option>
                                    <option value="30">30</option>
                                    <option value="20">20</option>
                                    <option value="10">10</option>
                                    <option value="0">0</option>
                                </select>
                            </div>
                            <label class="col-md-1 jarak">IBP</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id8" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="250">250</option>
                                    <option value="200">200</option>
                                    <option value="150">150</option>
                                    <option value="100">100</option>
                                    <option value="50">50</option>
                                    <option value="0">0</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">CVP</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id9" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="25">25</option>
                                    <option value="20">20</option>
                                    <option value="15">15</option>
                                    <option value="10">10</option>
                                    <option value="5">5</option>
                                    <option value="0">0</option>
                                </select>
                            </div>
                            <label class="col-md-1 jarak">MAP</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="id10" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="140">140</option>
                                    <option value="120">120</option>
                                    <option value="100">100</option>
                                    <option value="80">80</option>
                                    <option value="60">60</option>
                                    <option value="40">40</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_hemodinamika_icu" class="btn btn-success pull-right" onclick="saveHemodinamika('hemodinamika_icu', 'hemodinamika')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_hemodinamika_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-chart_hemodinamika">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Chart Hemodinamika Tanggal <span id="tanggal_data"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_chart_hemodinamika">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_chart_hemodinamika"></p>
                    </div>
                    <div class="box-body chart-responsive">
                        <div class="chart" id="line-chart_hemodinamika" style="height: 300px; width: 100%"></div>
                        <hr class="garis">
                        <div class="row" style="font-size: 12px">
                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-1">
                                    <i class="fa fa-circle" style="color: #ff0000"></i> T
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #0000ff"></i> BP
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #00cc00"></i> HI
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #ff9933"></i> RR
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #cc6600"></i> ICP
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #ffff66"></i> IBP
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #cc6699"></i> CVP
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #666633"></i> MAP
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="box-body">
                            <table class="table table-bordered table-striped" style="font-size: 12px">
                                <tbody id="body_ekg"></tbody>
                            </table>
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