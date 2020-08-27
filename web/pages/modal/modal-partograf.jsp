<div class="modal fade" id="modal-rb-partograf">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Partograf
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_rb_partograf">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_partograf"></p>
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
                            <li><a onclick="showModalRB('data_partograf')" style="cursor: pointer" ><i class="fa fa-plus"></i> Data Partograf</a></li>
                            <li><a onclick="showModalRB('janin_ibu_persalinan')" style="cursor: pointer"><i class="fa fa-plus"></i> Kondisi Ibu, Janin & Kemajuan Persalinan</a></li>
                            <li><a onclick="showModalRB('catatan_persalinan')" style="cursor: pointer"><i class="fa fa-plus"></i> Catatan Persalinan</a></li>
                            <li><a onclick="showModalRB('kala1')" style="cursor: pointer"><i class="fa fa-plus"></i> Kala I</a></li>
                            <li><a onclick="showModalRB('kala2')" style="cursor: pointer"><i class="fa fa-plus"></i> Kala II</a></li>
                            <li><a onclick="showModalRB('kala3')" style="cursor: pointer"><i class="fa fa-plus"></i> Kala III</a></li>
                            <li><a onclick="showModalRB('kala4')" style="cursor: pointer"><i class="fa fa-plus"></i> Kala IV</a></li>
                            <li><a onclick="showModalRB('bayi_baru_lahir')" style="cursor: pointer"><i class="fa fa-plus"></i> Bayi Bayu Lahir</a></li>
                            <li><a onclick="showModalRB('pemantauan_kalan4')" style="cursor: pointer"><i class="fa fa-plus"></i> Pemantauan Kala IV</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_partograf">
                        <tbody>
                        <tr id="row_rb_data_partograf">
                            <td>Data Partograf</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_data_partograf" class="hvr-grow" onclick="detailRB('data_partograf')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_janin_ibu_persalinan">
                            <td>Kondisi Ibu, Janin & Kemajuan Persalinan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_janin_ibu_persalinan" class="hvr-grow" onclick="detailRB('janin_ibu_persalinan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_catatan_persalinan">
                            <td>Catatan Persalinan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_catatan_persalinan" class="hvr-grow" onclick="detailRB('catatan_persalinan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_kala1">
                            <td>Kala I</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_kala1" class="hvr-grow" onclick="detailRB('kala1')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_kala2">
                            <td>Kala II</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_kala2" class="hvr-grow" onclick="detailRB('kala2')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_kala3">
                            <td>Kala III</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_kala3" class="hvr-grow" onclick="detailRB('kala3')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_kala4">
                            <td>Kala IV</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_kala4" class="hvr-grow" onclick="detailRB('kala4')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_bayi_baru_lahir">
                            <td>Bayi Bayu Lahir</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_bayi_baru_lahir" class="hvr-grow" onclick="detailRB('bayi_baru_lahir')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_pemantauan_kalan4">
                            <td>Pemantauan Kala IV</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_pemantauan_kalan4" class="hvr-grow" onclick="detailRB('pemantauan_kalan4')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
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

<div class="modal fade" id="modal-rb-data_partograf">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Data Partograf
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_data_partograf">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_data_partograf"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Gravida</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Para</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Abortus</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">No. Registrasi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp5">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Waktu Saat Masuk</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Waktu Mulai Mulas</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp7">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Waktu Kebutuban Pecah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dp8">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_rb_data_partograf" class="btn btn-success pull-right"
                        onclick="saveRB('data_partograf','partograf')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_data_partograf" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rb-janin_ibu_persalinan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Kondisi Ibu, Janin & Kemajuan Persilanan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_janin_ibu_persalinan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_janin_ibu_persalinan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2">Waktu</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ji1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Kondisi Janin</b></label>
                        <div class="form-group">
                            <label class="col-md-2">DJJ</label>
                            <div class="col-md-4">
                                <select class="form-control" id="ji2">
                                    <option value="">[Select One]</option>
                                    <option value="80">80</option>
                                    <option value="90">90</option>
                                    <option value="100">100</option>
                                    <option value="110">110</option>
                                    <option value="120">120</option>
                                    <option value="130">130</option>
                                    <option value="140">140</option>
                                    <option value="150">150</option>
                                    <option value="160">160</option>
                                    <option value="170">170</option>
                                    <option value="180">180</option>
                                    <option value="190">190</option>
                                    <option value="120">120</option>
                                </select>
                            </div>
                            <label class="col-md-2">Air Ketuban</label>
                            <div class="col-md-4">
                                <select class="form-control" id="ji3">
                                    <option value="">[Select One]</option>
                                    <option value="J">Jernih</option>
                                    <option value="H">Hijau</option>
                                    <option value="M">Mekaneal</option>
                                    <option value="U">Utuh</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Molase</label>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="ji41" name="ji4" /><label for="ji41" >Ya</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="ji42" name="ji4" /><label for="ji42" >Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Kemajuan Persalinan</b></label>
                        <div class="form-group">
                            <label class="col-md-2">Pembukaan</label>
                            <div class="col-md-4">
                                <select class="form-control" id="ji5">
                                    <option value="">[Select One]</option>
                                    <option value="0">0</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                </select>
                            </div>
                            <label class="col-md-2">Kontraksi</label>
                            <div class="col-md-4">
                                <select class="form-control" id="ji6">
                                    <option value="">[Select One]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Kondisi Ibu</b></label>
                        <div class="form-group">
                            <label class="col-md-3">Oksitosin UV</label>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="ji71" name="ji7" /><label for="ji71" >Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="ji72" name="ji7" /><label for="ji72" >Tidak</label>
                                </div>
                            </div>
                            <label class="col-md-2">Tetes/menit</label>
                            <div class="col-md-4">
                                <input class="form-control" type="number" id="ji8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Cairan dan cairan infus</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ji9"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Nadi</label>
                            <div class="col-md-4">
                                <select class="form-control" id="ji10">
                                    <option value="">[Select One]</option>
                                    <option value="80">80</option>
                                    <option value="90">90</option>
                                    <option value="100">100</option>
                                    <option value="110">110</option>
                                    <option value="120">120</option>
                                    <option value="130">130</option>
                                    <option value="140">140</option>
                                    <option value="150">150</option>
                                    <option value="160">160</option>
                                    <option value="170">170</option>
                                    <option value="180">180</option>
                                </select>
                            </div>
                            <label class="col-md-2">Tensi</label>
                            <div class="col-md-4">
                                <select class="form-control" id="ji11">
                                    <option value="">[Select One]</option>
                                    <option value="80">80</option>
                                    <option value="90">90</option>
                                    <option value="100">100</option>
                                    <option value="110">110</option>
                                    <option value="120">120</option>
                                    <option value="130">130</option>
                                    <option value="140">140</option>
                                    <option value="150">150</option>
                                    <option value="160">160</option>
                                    <option value="170">170</option>
                                    <option value="180">180</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Suhu</label>
                            <div class="col-md-4">
                                <input class="form-control" id="ji12" type="number">
                            </div>
                            <label class="col-md-2">RR</label>
                            <div class="col-md-4">
                               <input class="form-control" id="ji13" type="number">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_rb_janin_ibu_persalinan" class="btn btn-success pull-right"
                        onclick="saveRB('janin_ibu_persalinan','partograf')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_janin_ibu_persalinan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>