<div class="modal fade" id="modal-icu-asesmen_icu">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen ICU
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_asesmen_icu">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_asesmen_icu"></p>
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
                            <li><a onclick="showModalICU('identitas')" style="cursor: pointer" ><i class="fa fa-plus"></i> Identitas</a></li>
                            <li><a onclick="showModalICU('alat_infasive')" style="cursor: pointer"><i class="fa fa-plus"></i> Alat Infasive</a></li>
                            <li><a onclick="showModalICU('resiko_jatuh')" style="cursor: pointer"><i class="fa fa-plus"></i> Skala Resiko Jatuh</a></li>
                            <li><a onclick="showModalICU('decobitus')" style="cursor: pointer"><i class="fa fa-plus"></i> Derajat Decobitus</a></li>
                            <li><a onclick="showModalICU('nyeri')" style="cursor: pointer"><i class="fa fa-plus"></i> Skala Nyeri</a></li>
                            <li><a onclick="showModalICU('gcs')" style="cursor: pointer"><i class="fa fa-plus"></i> Glasgow Coma Scale</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_icu">
                        <tbody>
                        <tr id="row_icu_identitas">
                            <td>Identitas</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_identitas" class="hvr-grow" onclick="detailICU('identitas')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_alat_infasive">
                            <td>Alat Infasive</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_alat_infasive" class="hvr-grow" onclick="detailICU('alat_infasive')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_resiko_jatuh">
                            <td>Skala Resiko Jatuh</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_resiko_jatuh" class="hvr-grow" onclick="detailICU('resiko_jatuh')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_decobitus">
                            <td>Derajat Decobitus</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_decobitus" class="hvr-grow" onclick="detailICU('decobitus')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_nyeri">
                            <td>Skala Nyeri</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_nyeri" class="hvr-grow" onclick="detailICU('nyeri')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_gcs">
                            <td>GCS <small>(Glasgow Coma Scale)</small></td>
                            <td width="20%" align="center">
                                <img id="btn_icu_gcs" class="hvr-grow" onclick="detailICU('gcs')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
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

<div class="modal fade" id="modal-icu-identitas">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Identitas
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_identitas">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_identitas"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="id1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="id2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Jaminan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="id3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Asal Masuk</label>
                            <div class="col-md-9">
                                <input class="form-control" id="id4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tgl Masuk</label>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="id5">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Hari Perawatan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="id6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Berat Badan</label>
                            <div class="col-md-3">
                                <input class="form-control berat-pasien" id="id7">
                            </div>
                            <label class="col-md-3">Tinggi Badan</label>
                            <div class="col-md-3">
                                <input class="form-control tinggi-pasien" id="id8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">DPJP ICU</label>
                            <div class="col-md-9">
                                <input class="form-control" id="id9">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Perawat Jaga</label>
                            <div class="col-md-9">
                                <select class="form-control" id="id10">
                                    <option class="Pagi">Pagi</option>
                                    <option class="Sore">Sore</option>
                                    <option class="Malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_identitas" class="btn btn-success pull-right" onclick="saveICU('identitas', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_identitas" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-alat_infasive">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Alat Infasive Yang Terpasang
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_alat_infasive">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_alat_infasive"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ai1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ai2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Lokasi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ai3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Alat</label>
                            <div class="col-md-9">
                                <select class="form-control select2" multiple id="ai4" style="width: 100%">
                                    <option class="Vena Perifer 1">Vena Perifer 1</option>
                                    <option class="Vena Perifer 2">Vena Perifer 2</option>
                                    <option class="Vena Perifer 3">Vena Perifer 3</option>
                                    <option class="Jalur Arteri">Jalur Arteri</option>
                                    <option class="Jalur Jagularis">Jalur Jagularis</option>
                                    <option class="CVC">CVC</option>
                                    <option class="Orotrakeal Tube">Orotrakeal Tube</option>
                                    <option class="Nasotrakeal Tube">Nasotrakeal Tube</option>
                                    <option class="NIBP">NIBP</option>
                                    <option class="Kateter Epidural">Kateter Epidural</option>
                                    <option class="Kateter Urine">Kateter Urine</option>
                                    <option class="NGT/OGT">NGT/OGT</option>
                                    <option class="WSD">WSD</option>
                                    <option class="Drain 1">Drain 1</option>
                                    <option class="Drain 2">Drain 2</option>
                                    <option class="Drain 3">Drain 3</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_alat_infasive" class="btn btn-success pull-right" onclick="saveICU('alat_infasive', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_alat_infasive" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-resiko_jatuh">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Skala Resiko Jatuh
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_resiko_jatuh">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_resiko_jatuh"></p>
                    </div>
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">1. Riwayat Jatuh</label>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Ya|25" id="aud_jatuh11" name="radio_aud_jatuh1" /><label for="aud_jatuh11">Ya</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Tidak|0" id="aud_jatuh12" name="radio_aud_jatuh1" /><label for="aud_jatuh12">Tidak</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">2. Diagnosis Sekunder</label>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Ya|15" id="aud_jatuh21" name="radio_aud_jatuh2" /><label for="aud_jatuh21">Ya</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Tidak|0" id="aud_jatuh22" name="radio_aud_jatuh2" /><label for="aud_jatuh22">Tidak</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">3. Alat Bantu</label>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Kursi/perabot|30" id="aud_jatuh31" name="radio_aud_jatuh3" /><label for="aud_jatuh31">Kursi/perabot</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Kruk/tongkat|15" id="aud_jatuh32" name="radio_aud_jatuh3" /><label for="aud_jatuh32">Kruk/tongkat</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Tidak ada/bed rest/dibantu perawat|0" id="aud_jatuh33" name="radio_aud_jatuh3" /><label for="aud_jatuh33">Tidak ada/bed rest/dibantu perawat</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">4. Terapi Intravena</label>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Ya|20" id="aud_jatuh41" name="radio_aud_jatuh4" /><label for="aud_jatuh41">Ya</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Tidak|0" id="aud_jatuh42" name="radio_aud_jatuh4" /><label for="aud_jatuh42">Tidak</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">5. Gaya Berjalan</label>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Terganggu|20" id="aud_jatuh51" name="radio_aud_jatuh5" /><label for="aud_jatuh51">Terganggu</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Lemah|10" id="aud_jatuh52" name="radio_aud_jatuh5" /><label for="aud_jatuh52">Lemah</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Normal|0" id="aud_jatuh53" name="radio_aud_jatuh5" /><label for="aud_jatuh53">Normal</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">6. Status Normal</label>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Lupa akan keterbatasan/pelupa|15" id="aud_jatuh61" name="radio_aud_jatuh6" /><label for="aud_jatuh61">Lupa akan keterbatasan/pelupa</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row radio-margin">
                                    <div class="form-group">
                                        <div class="custom02">
                                            <input type="radio" value="Menyadari kemampuan|0" id="aud_jatuh62" name="radio_aud_jatuh6" /><label for="aud_jatuh62">Menyadari kemampuan</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_resiko_jatuh" class="btn btn-success pull-right" onclick="saveICU('resiko_jatuh', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_resiko_jatuh" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-decobitus">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Derajat Decubitus
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_decobitus">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_decobitus"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check">
                                <input type="checkbox" name="db" id="db1" value="I : Kulit utuh kemerahan adema">
                                <label for="db1"></label> I : Kulit utuh kemerahan adema
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check">
                                <input type="checkbox" name="db" id="db2" value="II : Kerusakan kulit sampai epidermis dan dermis">
                                <label for="db2"></label> II : Kerusakan kulit sampai epidermis dan dermis
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check">
                                <input type="checkbox" name="db" id="db3" value="III : Kerusakan kulit luar nikrisis mulai sup cutan - facia">
                                <label for="db3"></label> III : Kerusakan kulit luar nikrisis mulai sup cutan - facia
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check">
                                <input type="checkbox" name="db" id="db4" value="IV : Kerusakan kulit luar Nikrosis jaringan otot, tulang sampai sendi">
                                <label for="db4"></label> IV : Kerusakan kulit luar Nikrosis jaringan otot, tulang sampai sendi
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_decobitus" class="btn btn-success pull-right" onclick="saveICU('decobitus', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_decobitus" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-nyeri">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Skala Nyeri
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_nyeri">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_nyeri"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Apakah terdapat keluhan nyeri</label>
                                <div class="col-md-2">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input onclick="cekNyeri(this.value, 'skala_nyeri')" type="radio" value="Ya" id="aud_nyeri1" name="radio_aud_nyeri" /><label for="aud_nyeri1">Ya</label>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input onclick="cekNyeri(this.value, 'skala_nyeri')" type="radio" value="Tidak" id="aud_nyeri2" name="radio_aud_nyeri" /><label for="aud_nyeri2">Tidak</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Lokasi</label>
                                <div class="col-md-7">
                                    <input class="form-control" id="yer_lokasi">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Jenis</label>
                                <div class="col-md-2">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Akut" id="aud_skala1" name="radio_aud_skala" /><label for="aud_skala1">Akut</label>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Kronis" id="aud_skala2" name="radio_aud_skala" /><label for="aud_skala2">Kronis</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Skala</label>
                                <div class="col-md-7">
                                    <input class="form-control" id="skala_nyeri" readonly>
                                </div>
                            </div>
                            <%--<img src="<%= request.getContextPath() %>/pages/images/asesmen-nyeri.jpg" style="width: 100%; margin-top: 20px">--%>
                        </div>
                    </div>
                    <hr class="garis">
                    <input id="temp_scala" type="hidden">
                    <canvas id="choice_emoji" style="display: none"></canvas>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-0.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop;" id="0">
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">0</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Tidak Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-2.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="2" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">2</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-4.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="4" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">4</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Lebih Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-6.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="6" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">6</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Lebih Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-8.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="8" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">8</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Sangat Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-10.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="10" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">10</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Nyeri Sangat Hebat</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_nyeri" class="btn btn-success pull-right" onclick="saveICU('nyeri', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_nyeri" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-gcs">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> GCS
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_gcs">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_gcs"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-6 jarak">E = Eye (membuka mata)</label>
                        <div class="col-md-6">
                            <select class="form-control select2" style="width: 100%" id="gc1">
                                <option value="">[Select One]</option>
                                <option value="Spontan|4">Spontan</option>
                                <option value="Dengan Panggilan|3">Dengan Panggilan</option>
                                <option value="Dengan rangsangan nyeri|2">Dengan rangsangan nyeri</option>
                                <option value="Tidak ada respon|1">Tidak ada respon</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-6 jarak">V = Verbal (komunikasi)</label>
                        <div class="col-md-6">
                            <select class="form-control select2" style="width: 100%" id="gc2">
                                <option value="">[Select One]</option>
                                <option value="Orientasi|5">Orientasi</option>
                                <option value="Apatis|4">Apatis</option>
                                <option value="Disorientasi|3">Disorientasi</option>
                                <option value="Mengarang|2">Mengarang</option>
                                <option value="Tidak ada|1">Tidak ada</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-6 jarak">M = Motorik (gerakan ekstimitas atas)</label>
                        <div class="col-md-6">
                            <select class="form-control select2" style="width: 100%" id="gc3">
                                <option value="">[Select One]</option>
                                <option value="Spontan|6">Spontan</option>
                                <option value="Terlokalisir|5">Terlokalisir</option>
                                <option value="Fleksi|4">Fleksi</option>
                                <option value="Deserebrasi|3">Deserebrasi</option>
                                <option value="Dekortasi|2">Dekortasi</option>
                                <option value="Tak ada respon|1">Tak ada respon</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_gcs" class="btn btn-success pull-right" onclick="saveICU('gcs', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_gcs" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>