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
                            <li><a onclick="showModalICU('checklist_kriteria')" style="cursor: pointer"><i class="fa fa-plus"></i> Check List Kriteria Pasien</a></li>
                            <li><a onclick="showModalICU('pengkajian_medik')" style="cursor: pointer"><i class="fa fa-plus"></i> Pengkajian Medik</a></li>
                            <li><a onclick="showModalICU('pengkajian_keperawatan')" style="cursor: pointer"><i class="fa fa-plus"></i> Pengkajian Keperawatan</a></li>
                            <li><a onclick="showModalICU('catatan_integrasi_pasien')" style="cursor: pointer"><i class="fa fa-plus"></i> Catatan Perkembangan Pasien</a></li>
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
                        <tr id="row_icu_checklist_kriteria">
                            <td>Check List Kriteria Pasien</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_checklist_kriteria" class="hvr-grow" onclick="detailICU('checklist_kriteria')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_pengkajian_medik">
                            <td>Pengkajian Pasien Tahap Akhir Kehidupan (Pengkajian Medik)</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_pengkajian_medik" class="hvr-grow" onclick="detailICU('pengkajian_medik')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_pengkajian_keperawatan">
                            <td>Pengkajian Pasien Tahap Akhir Kehidupan (Pengkajian keperawatan)</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_pengkajian_keperawatan" class="hvr-grow" onclick="detailICU('pengkajian_keperawatan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_catatan_integrasi_pasien">
                            <td>Catatan Perkembangan Pasien</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_catatan_integrasi_pasien" class="hvr-grow" onclick="listCatatanTerintegrasiICU('catatan_integrasi_pasien')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
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

<div class="modal fade" id="modal-icu-checklist_kriteria">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Check List Kriteria Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_checklist_kriteria">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_checklist_kriteria"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped table-bordered" style="font-size: 12px">
                                <thead id="th_checklist_kriteria"></thead>
                                <tbody id="td_checklist_kriteria"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row text-center">
                        <div class="col-md-6">
                            <label >TTD DPJP</label>
                            <canvas class="paint-canvas-ttd" id="ttd_dpjp"
                                    onmouseover="paintTtd('ttd_dpjp')"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_dpjp')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <label >TTD Pasien/Keluarga</label>
                            <canvas class="paint-canvas-ttd" id="ttd_pasien"
                                    onmouseover="paintTtd('ttd_pasien')"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_pasien')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_checklist_kriteria" class="btn btn-success pull-right" onclick="saveICU('checklist_kriteria', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_checklist_kriteria" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-pengkajian_medik">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengakajian Medik
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_pengkajian_medik">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_pengkajian_medik"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="tgl_pengkajian_medik">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="jam_pengkajian_medik">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kelas</label>
                            <div class="col-md-9">
                                <input class="form-control" id="01pm">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa Medik</label>
                            <div class="col-md-9">
                                <textarea class="form-control diagnosa-pasien" id="02pm"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">DPJP</label>
                            <div class="col-md-9">
                                <input class="form-control" id="03pm">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>1. Gejala - Gejala</b></label>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="row">
                                    <label class="col-md-12">a. Kegawatan Pernafasan</label>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm11" value="Dypsnue">
                                            <label for="pm11"></label> Dypsnue
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm12" value="Nafas cepat dan dangkal">
                                            <label for="pm12"></label> Nafas cepat dan dangkal
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm1" id="pm13" value="Nafas lambat">
                                            <label for="pm13"></label> Nafas lambat
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm14" value="Nafas tidak beraturan">
                                            <label for="pm14"></label> Nafas tidak beraturan
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm15" value="Nafas melalui mulut">
                                            <label for="pm15"></label> Nafas melalui mulut
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm16" value="Mukosa oral kering">
                                            <label for="pm16"></label> Mukosa oral kering
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm1" id="pm17" value="Ada secret">
                                            <label for="pm17"></label> Ada secret
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm18" value="SpO2 < normal">
                                            <label for="pm18"></label> SpO2 < normal
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm19" value="Tidak ada kelainan">
                                            <label for="pm19"></label> Tidak ada kelainan
                                        </div>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <label>c. Nyeri</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input onclick="checkSelect(this.id, 'pm32', this.value)" type="checkbox" name="pm3" id="pm31" value="Tidak">
                                            <label for="pm31"></label> Tidak
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input onclick="checkSelect(this.id, 'pm31', this.value)" type="checkbox" name="pm3" id="pm32" value="Ya">
                                            <label for="pm32"></label> Ya
                                        </div>
                                    </div>
                                    <div class="col-md-9">
                                        <input class="form-control" id="ket_pm3" oninput="$('#pm32').val('Ya, '+this.value)">
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <label>e. Pencernaan</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm51" value="Mual">
                                            <label for="pm51"></label> Mual
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm52" value="Muntah">
                                            <label for="pm52"></label> Muntah
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm53" value="Intoleransi">
                                            <label for="pm53"></label> Intoleransi
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm54" value="NGT">
                                            <label for="pm54"></label> NGT
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <input class="form-control" id="ket_pm54" oninput="$('#pm54').val('NGT, '+this.value)">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm55" value="Lain-Lain">
                                            <label for="pm55"></label> Lain-lain
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <input class="form-control" id="ket_pm55" oninput="$('#pm55').val('Lain-Lain, '+this.value)">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <label class="col-md-12">b. Kehilangan otot tonus</label>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm21" value="Mual">
                                            <label for="pm21"></label> Mual
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm22" value="Penurunan pergerakan tubuh">
                                            <label for="pm22"></label> Penurunan pergerakan tubuh
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm2" id="pm23" value="Sulit bicara">
                                            <label for="pm23"></label> Sulit bicara
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm24" value="Sulit menelan">
                                            <label for="pm24"></label> Sulit menelan
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm25" value="Distensi abdomen">
                                            <label for="pm25"></label> Distensi abdomen
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm26" value="Inkontinensia urine">
                                            <label for="pm26"></label> Inkontinensia urine
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm2" id="pm27" value="Inkontinensia feces">
                                            <label for="pm27"></label> Inkontinensia feces
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm28" value="Tidak ada kelainan">
                                            <label for="pm28"></label> Tidak ada kelainan
                                        </div>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <label>d. Pelambatan sirkulasi</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm41" value="Bercak dan sianosis pada ekstremitas">
                                            <label for="pm41"></label> Bercak dan sianosis pada ekstremitas
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm42" value="Gelisah">
                                            <label for="pm42"></label> Gelisah
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm43" value="Gelisah">
                                            <label for="pm43"></label> Lemas
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm44" value="Kulit dengin dan berkeringat">
                                            <label for="pm44"></label> Kulit dengin dan berkeringat
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm45" value="Tekanan darah menurun">
                                            <label for="pm45"></label> Tekanan darah menurun
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm46" value="Nadi lambat dan lemah">
                                            <label for="pm46"></label> Nadi lambat dan lemah
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>2. Faktor-faktor yang ,meningkatkan gejala fisik</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="pm6" id="pm61" value="Melakukan aktifitas fisik">
                                <label for="pm61"></label> Melakukan aktifitas fisik
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="pm6" id="pm62" value="Pindah posisi">
                                <label for="pm62"></label> Pindah posisi
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="pm6" id="pm63" value="Lainnya">
                                <label for="pm63"></label> Lainnya
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-8 col-md-4">
                                <input class="form-control" oninput="$('#pm63').val('Lainnya, '+this.value)" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>3. Asesment</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea class="form-control" id="pm7"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>4. Perencanaan</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea class="form-control" id="pm8"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row text-center">
                        <div class="col-md-6">
                            <label >TTD Dokter</label>
                            <canvas class="paint-canvas-ttd" id="ttd_dokter"
                                    onmouseover="paintTtd('ttd_dokter')"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_dokter')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_pengkajian_medik" class="btn btn-success pull-right" onclick="saveICU('pengkajian_medik', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_pengkajian_medik" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-pengkajian_keperawatan">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_pengkajian_keperawatan">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_pengkajian_keperawatan"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="tgl_pengkajian_keperawatan">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="jam_pengkajian_keperawatan">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="col-md-12">
                            <label><b>1. Orental spiritual pasien dan keluarga</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <div class="row">
                                <div class="col-md-12">
                                    <label>Apakah perlu pelayanan spiritual</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk12', this.value)" type="checkbox" name="pk1" id="pk11" value="Ya">
                                <label for="pk11"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk11', this.value)" type="checkbox" name="pk1" id="pk12" value="Tidak">
                                <label for="pk12"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk11">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk11">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <label><b>2. Urusan dan kebutuhan spiritual dan keluarga seperti putus asa, penderitaan, rasa bersalah atau pengampunan</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <div class="form-check">
                                <input type="checkbox" name="pk02" id="pk21" value="Perlu didoakan">
                                <label for="pk21"></label> Perlu didoakan
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk23', this.value)" type="checkbox" name="pk2" id="pk22" value="Ya">
                                <label for="pk22"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk22', this.value)" type="checkbox" name="pk2" id="pk23" value="Tidak">
                                <label for="pk23"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk22">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk22">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <div class="form-check">
                                <input type="checkbox" name="pk03" id="pk31" value="Perlu didoakan">
                                <label for="pk31"></label> Perlu bimbingan rohani
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk33', this.value)" type="checkbox" name="pk3" id="pk32" value="Ya">
                                <label for="pk32"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk32', this.value)" type="checkbox" name="pk3" id="pk33" value="Tidak">
                                <label for="pk33"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk32">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk32">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <div class="form-check">
                                <input type="checkbox" name="pk04" id="pk41" value="Perlu didoakan">
                                <label for="pk41"></label> Pelu pendamping rohani
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk43', this.value)" type="checkbox" name="pk4" id="pk42" value="Ya">
                                <label for="pk42"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk42', this.value)" type="checkbox" name="pk4" id="pk43" value="Tidak">
                                <label for="pk43"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk42">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk42">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <label><b>3. Status psikososial pasien dan keluarga</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <label><b>a. Apakah ada orang yang ingin dihubungi saat ini?</b></label>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk52', this.value)" type="checkbox" name="pk5" id="pk51" value="Ya">
                                <label for="pk51"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk51', this.value)" type="checkbox" name="pk5" id="pk52" value="Tidak">
                                <label for="pk52"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div id="icu_pk51" style="display: none">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk51" placeholder="Nama">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk52" placeholder="Dimana">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk53" placeholder="Hubungan">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk54" placeholder="No Telepon/HP">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <label><b>b. Bagaimana rencana keperawatan selajutnya?</b></label>
                        </div>
                        <div class="col-md-7">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk62', this.value)" type="checkbox" name="pk6" id="pk61" value="Tetap dirawat dirumah sakit Gatoel">
                                <label for="pk61"></label> Tetap dirawat dirumah sakit Gatoel
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-7">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk61', this.value)" type="checkbox" name="pk6" id="pk62" value="Dirawat dirumah">
                                    <label for="pk62"></label> Dirawat dirumah
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="icu_pk62" style="display: none">
                        <div class="row jarak">
                            <div class="col-md-5">
                                <label>Apakah lingkungan rumah sudah disiapkan</label>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk72', this.value)" type="checkbox" name="pk7" id="pk71" value="Ya">
                                    <label for="pk71"></label> Ya
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk71', this.value)" type="checkbox" name="pk7" id="pk72" value="Tidak">
                                    <label for="pk72"></label> Tidak
                                </div>
                            </div>
                        </div>
                    <div class="row">
                        <div class="col-md-5">
                            <label>Jika ya, apakah anda mampu merawat pasien dirumah</label>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk82', this.value)" type="checkbox" name="pk8" id="pk81" value="Ya">
                                <label for="pk81"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk81', this.value)" type="checkbox" name="pk8" id="pk82" value="Tidak">
                                <label for="pk82"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <label>Jika tidak, apakah perlu difasilitasi oleh rumah sakit</label>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk92', this.value)" type="checkbox" name="pk9" id="pk91" value="Ya">
                                <label for="pk91"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk91', this.value)" type="checkbox" name="pk9" id="pk92" value="Tidak">
                                <label for="pk92"></label> Tidak
                            </div>
                        </div>
                    </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <label><b>c. Reaksi pasien atas penyakitnya?</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk101" value="Menyangkal">
                                <label for="pk101"></label> Menyangkal
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk102" value="Sedih/Menangis">
                                <label for="pk102"></label> Sedih/Menangis
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk103" value="Rasa bersalah">
                                <label for="pk103"></label> Rasa bersalah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk104" value="Tidak ada relasi">
                                <label for="pk104"></label> Tidak ada relasi
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk105" value="Marah">
                                <label for="pk105"></label> Marah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk106" value="Takut">
                                <label for="pk106"></label> Takut
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk107" value="Ketidakberdayaan">
                                <label for="pk107"></label> Ketidakberdaya
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk108" value="Tidak ada kontak">
                                <label for="pk108"></label> Tidak ada kontak
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <label><b>d. Reaksi keluarga atas penyakitnya?</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk111" value="Menyangkal">
                                <label for="pk111"></label> Marah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk112" value="Sedih/Menangis">
                                <label for="pk112"></label> Letih/lelah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk113" value="Rasa bersalah">
                                <label for="pk113"></label> Rasa bersalah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk114" value="Tidak ada relasi">
                                <label for="pk114"></label> Sedih/menangis
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk115" value="Keluarga kurang berpartisipasi membuat keputusan dalam perawatan pasien">
                                <label for="pk115"></label> Keluarga kurang berpartisipasi membuat keputusan dalam perawatan pasien
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row text-center">
                        <div class="col-md-6">
                            <label >TTD</label>
                            <canvas class="paint-canvas-ttd" id="ttd_perawat"
                                    onmouseover="paintTtd('ttd_perawat')"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_perawat')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_pengkajian_keperawatan" class="btn btn-success pull-right" onclick="saveICU('pengkajian_keperawatan', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_pengkajian_keperawatan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-catatan_integrasi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Catatan Perkembangan Pasien Terintegrasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_icu_catatan_integrasi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_icu_catatan_integrasi_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="form-group" style="display: none">
                        <div class="col-md-1">
                            <input type="color" style="margin-left: -6px; margin-top: -8px"
                                   class="js-color-picker  color-picker pull-left">
                        </div>
                        <div class="col-md-9">
                            <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72"
                                   value="1">
                        </div>
                        <div class="col-md-2">
                            <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="cp1">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="cp2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">PPA</label>
                            <div class="col-md-8">
                                <select class="form-control" id="cp3" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Dokter">Dokter</option>
                                    <option value="Perawat">Perawat</option>
                                    <option value="Apoteker">Apoteker</option>
                                    <option value="Gizi">Gizi</option>
                                    <option value="Bidan">Bidan</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Jenis</label>--%>
                    <%--<div class="col-md-8">--%>
                    <%--<select class="form-control" id="cp4" style="margin-top: 7px">--%>
                    <%--<option value="">[Select One]</option>--%>
                    <%--<option value="Subyektif">Subyektif</option>--%>
                    <%--<option value="Obyektif">Obyektif</option>--%>
                    <%--<option value="Asesmen">Asesmen</option>--%>
                    <%--<option value="Planning">Planning</option>--%>
                    <%--</select>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Instruksi</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" rows="4" class="form-control" id="cp5"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Petugas</label>
                                <canvas class="paint-canvas-ttd" id="cp6" width="220"
                                        onmouseover="paintTtd('cp6')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('cp6')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="cp7" width="220"
                                        onmouseover="paintTtd('cp7')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('cp7')"><i
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
                <button id="save_icu_catatan_integrasi_pasien" class="btn btn-success pull-right"
                        onclick="saveCatatanTerintegrasiICU('catatan_integrasi_pasien','asesmen_icu')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_icu_catatan_integrasi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>