<div class="modal fade" id="modal-icu-asesmen_icu">
    <div class="modal-dialog" style="width: 80%; position: center">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen ICU
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_icu_asesmen_icu">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_asesmen_icu"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_asesmen_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
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
                            <li><a onclick="showModalICU('hemodinamika_icu')" style="cursor: pointer"><i class="fa fa-plus"></i> Hemodinamika</a></li>
                            <li><a onclick="showModalICU('respirasi_icu')" style="cursor: pointer"><i class="fa fa-plus"></i> Respirasi</a></li>
                            <li><a onclick="showModalICU('keseimbangan_icu')" style="cursor: pointer"><i class="fa fa-plus"></i> Keseimbangan</a></li>
                            <%--<li><a onclick="showModalICU('injeksi_icu')" style="cursor: pointer"><i--%>
                                    <%--class="fa fa-plus"></i> Ijeksi</a></li>--%>
                            <%--<li><a onclick="showModalICU('oral_icu')" style="cursor: pointer"><i--%>
                                    <%--class="fa fa-plus"></i> Oral</a></li>--%>
                            <%--<li><a onclick="showModalICU('lain_icu')" style="cursor: pointer"><i--%>
                                    <%--class="fa fa-plus"></i> Lain-Lain</a></li>--%>
                            <%--<li><a onclick="showModalICU('intakea_icu')" style="cursor: pointer"><i--%>
                                    <%--class="fa fa-plus"></i> Intakea</a></li>--%>
                            <%--<li><a onclick="showModalICU('output_icu')" style="cursor: pointer"><i--%>
                                    <%--class="fa fa-plus"></i> Output</a></li>--%>
                            <li><a onclick="showModalICU('asuhan_keperawatan_icu')" style="cursor: pointer"><i class="fa fa-plus"></i> Rencana Asuhan Keperawatan</a></li>
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
                                <img id="delete_identitas" class="hvr-grow btn-hide" onclick="conICU('identitas', 'asesmen_icu')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_icu_alat_infasive">
                            <td>Alat Infasive</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_alat_infasive" class="hvr-grow" onclick="detailICU('alat_infasive')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_alat_infasive" class="hvr-grow btn-hide" onclick="conICU('alat_infasive', 'asesmen_icu')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_icu_resiko_jatuh">
                            <td>Skala Resiko Jatuh</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_resiko_jatuh" class="hvr-grow" onclick="detailICU('resiko_jatuh')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                                <%--<img id="delete_resiko_jatuh" class="hvr-grow btn-hide" onclick="conICU('resiko_jatuh', 'asesmen_icu')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">--%>
                            </td>
                        </tr>
                        <tr id="row_icu_decobitus">
                            <td>Derajat Decobitus</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_decobitus" class="hvr-grow" onclick="detailICU('decobitus')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                                <%--<img id="delete_decobitus" class="hvr-grow btn-hide" onclick="conICU('decobitus', 'asesmen_icu')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">--%>
                            </td>
                        </tr>
                        <tr id="row_icu_nyeri">
                            <td>Skala Nyeri</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_nyeri" class="hvr-grow" onclick="detailICU('nyeri')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                                <%--<img id="delete_nyeri" class="hvr-grow btn-hide" onclick="conICU('nyeri', 'asesmen_icu')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">--%>
                            </td>
                        </tr>
                        <tr id="row_icu_gcs">
                            <td>GCS <small>(Glasgow Coma Scale)</small></td>
                            <td width="20%" align="center">
                                <img id="btn_icu_gcs" class="hvr-grow" onclick="detailICU('gcs')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_gcs" class="hvr-grow btn-hide" onclick="conICU('gcs', 'asesmen_icu')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_icu_hemodinamika_icu">
                            <td>Hemodinamika</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_hemodinamika_icu" class="hvr-grow" onclick="listHemodinamika('hemodinamika_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_respirasi_icu">
                            <td>Respirasi</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_respirasi_icu" class="hvr-grow" onclick="listRespirasi('respirasi_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_keseimbangan_icu">
                            <td>Keseimbangan</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_keseimbangan_icu" class="hvr-grow" onclick="listInputan('keseimbangan_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                            </td>
                        </tr>
                        <%--<tr id="row_icu_injeksi_icu">--%>
                            <%--<td>Injeksi ICU</td>--%>
                            <%--<td width="20%" align="center">--%>
                                <%--<img id="btn_icu_injeksi_icu" class="hvr-grow" onclick="listInputan('injeksi_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr id="row_icu_oral_icu">--%>
                            <%--<td>Oral</td>--%>
                            <%--<td width="20%" align="center">--%>
                                <%--<img id="btn_icu_oral_icu" class="hvr-grow" onclick="listInputan('oral_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr id="row_icu_lain_icu">--%>
                            <%--<td>Lain-Lain</td>--%>
                            <%--<td width="20%" align="center">--%>
                                <%--<img id="btn_icu_lain_icu" class="hvr-grow" onclick="listInputan('lain_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr id="row_icu_intakea_icu">--%>
                            <%--<td>Intakea ICU</td>--%>
                            <%--<td width="20%" align="center">--%>
                                <%--<img id="btn_icu_intakea_icu" class="hvr-grow" onclick="listInputan('intakea_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr id="row_icu_output_icu">--%>
                            <%--<td>Output ICU</td>--%>
                            <%--<td width="20%" align="center">--%>
                                <%--<img id="btn_icu_output_icu" class="hvr-grow" onclick="listInputan('output_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <tr id="row_icu_asuhan_keperawatan_icu">
                            <td>Rencana Asuhan Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_asuhan_keperawatan_icu" class="hvr-grow" onclick="listAsuhanKeperawatanICU('asuhan_keperawatan_icu')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
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
                                <input class="form-control nama_jenis_pasien" id="id3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Asal Masuk</label>
                            <div class="col-md-9">
                                <input class="form-control asal_masuk" id="id4">
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
                                    <input class="form-control tanggal_masuk_rs" id="id5" disabled>
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
                                <input class="form-control berat-pasien" id="id7" type="number">
                            </div>
                            <label class="col-md-3">Tinggi Badan</label>
                            <div class="col-md-3">
                                <input class="form-control tinggi-pasien" id="id8" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">DPJP ICU</label>
                            <div class="col-md-9">
                                <input class="form-control nama_dokter_ri" id="id9">
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
                            <div id="apakah_nyeri" style="display: none">
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
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <input id="temp_scala" type="hidden">
                    <canvas id="choice_emoji" style="display: none"></canvas>
                    <div class="row" style="margin-top: 10px; display: none" id="emoji">
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
                                <option value="Orientasi|5">Orientasi Baik</option>
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
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="hemo1">
                                </div>
                            </div>
                            <label class="col-md-2" style="margin-top: 7px">Temperature</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo2" type="number" step="any"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2 jarak">Sistole</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo3" type="number"/>
                                <%--<select class="form-control select2" id="" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="260">260</option>--%>
                                    <%--<option value="250">250</option>--%>
                                    <%--<option value="240">240</option>--%>
                                    <%--<option value="220">230</option>--%>
                                    <%--<option value="220">220</option>--%>
                                    <%--<option value="210">210</option>--%>
                                    <%--<option value="200">200</option>--%>
                                    <%--<option value="190">190</option>--%>
                                    <%--<option value="180">180</option>--%>
                                    <%--<option value="170">170</option>--%>
                                    <%--<option value="160">160</option>--%>
                                    <%--<option value="150">150</option>--%>
                                    <%--<option value="140">140</option>--%>
                                    <%--<option value="130">130</option>--%>
                                    <%--<option value="120">120</option>--%>
                                    <%--<option value="110">110</option>--%>
                                    <%--<option value="100">100</option>--%>
                                    <%--<option value="90">90</option>--%>
                                    <%--<option value="80">80</option>--%>
                                    <%--<option value="70">70</option>--%>
                                    <%--<option value="60">60</option>--%>
                                    <%--<option value="50">50</option>--%>
                                    <%--<option value="40">40</option>--%>
                                    <%--<option value="30">30</option>--%>
                                    <%--<option value="20">20</option>--%>
                                    <%--<option value="20">10</option>--%>
                                    <%--<option value="0">0</option>--%>
                                <%--</select>--%>
                            </div>
                            <label class="col-md-2 jarak">Diastole</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo4" type="number"/>
                                <%--<select class="form-control select2" id="hemo4" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="180">180</option>--%>
                                    <%--<option value="170">170</option>--%>
                                    <%--<option value="160">160</option>--%>
                                    <%--<option value="150">150</option>--%>
                                    <%--<option value="140">140</option>--%>
                                    <%--<option value="130">130</option>--%>
                                    <%--<option value="120">120</option>--%>
                                    <%--<option value="110">110</option>--%>
                                    <%--<option value="100">100</option>--%>
                                    <%--<option value="90">90</option>--%>
                                    <%--<option value="80">80</option>--%>
                                    <%--<option value="70">70</option>--%>
                                    <%--<option value="60">60</option>--%>
                                    <%--<option value="50">50</option>--%>
                                    <%--<option value="40">40</option>--%>
                                    <%--<option value="30">30</option>--%>
                                    <%--<option value="20">20</option>--%>
                                    <%--<option value="20">10</option>--%>
                                    <%--<option value="0">0</option>--%>
                                <%--</select>--%>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2 jarak">HR</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo5" type="number"/>
                                <%--<select class="form-control select2" id="hemo5" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="200">200</option>--%>
                                    <%--<option value="180">180</option>--%>
                                    <%--<option value="160">160</option>--%>
                                    <%--<option value="140">140</option>--%>
                                    <%--<option value="120">120</option>--%>
                                    <%--<option value="100">100</option>--%>
                                    <%--<option value="80">80</option>--%>
                                    <%--<option value="60">60</option>--%>
                                    <%--<option value="40">40</option>--%>
                                    <%--<option value="20">20</option>--%>
                                    <%--<option value="0">0</option>--%>
                                <%--</select>--%>
                            </div>
                            <label class="col-md-2 jarak">RR</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo6" type="number"/>
                                <%--<select class="form-control select2" id="hemo6" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="100">100</option>--%>
                                    <%--<option value="95">95</option>--%>
                                    <%--<option value="90">90</option>--%>
                                    <%--<option value="85">85</option>--%>
                                    <%--<option value="80">80</option>--%>
                                    <%--<option value="75">75</option>--%>
                                    <%--<option value="70">70</option>--%>
                                    <%--<option value="65">65</option>--%>
                                    <%--<option value="60">60</option>--%>
                                    <%--<option value="55">55</option>--%>
                                    <%--<option value="50">50</option>--%>
                                    <%--<option value="45">45</option>--%>
                                    <%--<option value="40">40</option>--%>
                                    <%--<option value="35">35</option>--%>
                                    <%--<option value="30">30</option>--%>
                                    <%--<option value="25">25</option>--%>
                                    <%--<option value="20">20</option>--%>
                                    <%--<option value="15">15</option>--%>
                                    <%--<option value="10">10</option>--%>
                                    <%--<option value="5">5</option>--%>
                                    <%--<option value="0">0</option>--%>
                                <%--</select>--%>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">EKG</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="hemo7">
                                    <option value="">[Select One]</option>
                                    <option value="Rinue Rhytme">Sinue Rhytme</option>
                                    <option value="Takiakrdi">Takiakrdi</option>
                                    <option value="Bradikardi">Bradikardi</option>
                                    <option value="Aritmia">Aritmia</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2 jarak">ICP</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo8" type="number"/>
                                <%--<select class="form-control select2" id="hemo8" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="50">50</option>--%>
                                    <%--<option value="40">40</option>--%>
                                    <%--<option value="30">30</option>--%>
                                    <%--<option value="20">20</option>--%>
                                    <%--<option value="10">10</option>--%>
                                    <%--<option value="0">0</option>--%>
                                <%--</select>--%>
                            </div>
                            <label class="col-md-2 jarak">IBP</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo9" type="number"/>
                                <%--<select class="form-control select2" id="hemo9" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="250">250</option>--%>
                                    <%--<option value="200">200</option>--%>
                                    <%--<option value="150">150</option>--%>
                                    <%--<option value="100">100</option>--%>
                                    <%--<option value="50">50</option>--%>
                                    <%--<option value="0">0</option>--%>
                                <%--</select>--%>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2 jarak">CVP</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo10" type="number"/>
                                <%--<select class="form-control select2" id="hemo10" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="25">25</option>--%>
                                    <%--<option value="20">20</option>--%>
                                    <%--<option value="15">15</option>--%>
                                    <%--<option value="10">10</option>--%>
                                    <%--<option value="5">5</option>--%>
                                    <%--<option value="0">0</option>--%>
                                <%--</select>--%>
                            </div>
                            <label class="col-md-2 jarak">MAP</label>
                            <div class="col-md-4">
                                <input class="form-control" id="hemo11" type="number"/>
                                <%--<select class="form-control select2" id="hemo11" style="width: 100%">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="140">140</option>--%>
                                    <%--<option value="120">120</option>--%>
                                    <%--<option value="100">100</option>--%>
                                    <%--<option value="80">80</option>--%>
                                    <%--<option value="60">60</option>--%>
                                    <%--<option value="40">40</option>--%>
                                <%--</select>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_hemodinamika_icu" class="btn btn-success pull-right" onclick="saveHemodinamika('hemodinamika_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_hemodinamika_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-chart_hemodinamika_icu">
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
                                <div class="col-md-2">
                                    <i class="fa fa-circle" style="color: #ff0000; margin-left: 30px"></i> Temperatur
                                </div>
                                <div class="col-md-2">
                                    <i class="fa fa-circle" style="color: #0000ff; margin-left: -20px"></i> Sistole
                                </div>
                                <div class="col-md-2">
                                    <i class="fa fa-circle" style="color: #00cc00; margin-left: -70px"></i> Diastole
                                </div>
                                <div class="col-md-1" style="margin-left: -100px">
                                    <i class="fa fa-circle" style="color: #ff9933"></i> HI
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #cc6600"></i> RR
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #ffff66"></i> ICP
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #cc6699"></i> IBP
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #666633"></i> CVP
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #000066"></i> MAP
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

<div class="modal fade" id="modal-icu-respirasi_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Respirasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_respirasi_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_respirasi_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="res1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">GCS (EVM)</label>
                            <div class="col-md-9">
                                <select class="form-control select2" style="width: 100%" id="res2">
                                    <option value="">[Select One E]</option>
                                    <option value="4">Spontan</option>
                                    <option value="3">Dengan Panggilan</option>
                                    <option value="2">Dengan rangsangan nyeri</option>
                                    <option value="1">Tidak ada respon</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <select class="form-control select2" style="width: 100%" id="res3">
                                    <option value="">[Select One V]</option>
                                    <option value="5">Orientasi</option>
                                    <option value="4">Apatis</option>
                                    <option value="3">Disorientasi</option>
                                    <option value="2">Mengarang</option>
                                    <option value="1">Tidak ada</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <select class="form-control select2" style="width: 100%" id="res4">
                                    <option value="">[Select One M]</option>
                                    <option value="6">Spontan</option>
                                    <option value="5">Terlokalisir</option>
                                    <option value="4">Fleksi</option>
                                    <option value="3">Deserebrasi</option>
                                    <option value="2">Dekortasi</option>
                                    <option value="1">Tak ada respon</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Diameter Pupil KA</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="res5" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="8">8</option>
                                    <option value="7">7</option>
                                    <option value="6">6</option>
                                    <option value="5">5</option>
                                    <option value="4">4</option>
                                    <option value="3">3</option>
                                    <option value="2">2</option>
                                    <option value="1">1</option>
                                </select>
                            </div>
                            <label class="col-md-3 jarak">Diameter Pupil KI</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="res6" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="8">8</option>
                                    <option value="7">7</option>
                                    <option value="6">6</option>
                                    <option value="5">5</option>
                                    <option value="4">4</option>
                                    <option value="3">3</option>
                                    <option value="2">2</option>
                                    <option value="1">1</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Reflek Cahaya KA</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="res7" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="+">+</option>
                                    <option value="-">-</option>
                                </select>
                            </div>
                            <label class="col-md-3 jarak">Reflek Cahaya KI</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="res8" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="+">+</option>
                                    <option value="-">-</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">TKA</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="res9">
                            </div>
                            <label class="col-md-3">TKI</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="res10">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">KKA</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="res11">
                            </div>
                            <label class="col-md-3">KKI</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="res12">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">O2</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="res13" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="NK">NK</option>
                                    <option value="RM">RM</option>
                                    <option value="NRM">NRM</option>
                                    <option value="T-Piece">T-Piece</option>
                                    <option value="J-Rise">J-Rise</option>
                                </select>
                            </div>
                            <label class="col-md-3" style="margin-top: 7px">Tipe Ventilasi</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="res14" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="Pressure Control">Pressure Control</option>
                                    <option value="Volume Control">Volume Control</option>
                                    <option value="SIMV">SIMV</option>
                                    <option value="NIV">NIV</option>
                                    <option value="CPAP">CPAP</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">PEEP</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res15">
                            </div>
                            <label class="col-md-3">Frekwensi/ Freweksi Total</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res16">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">TV (I) TV(E)</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="res17">
                            </div>
                            <label class="col-md-3 jarak">MV (I) MV (I)</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="res18">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">P-Support/P-ASB</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res19">
                            </div>
                            <label class="col-md-3 jarak">P-Inspirasi/P-Control</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res20">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Triger</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res21">
                            </div>
                            <label class="col-md-3 jarak">Inspirasi Time</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res22">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Flow</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res23">
                            </div>
                            <label class="col-md-3 jarak">FIO 2/Konsentrasi O2</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res24">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Ukuran ETT</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res25">
                            </div>
                            <label class="col-md-3 jarak">Kedalaman ETT</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res26">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 jarak"> SPO2</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="res27">
                            </div>
                            <label class="col-md-3 jarak"> Secret/ Sputum</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="res28" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="Putih">Putih</option>
                                    <option value="Merah">Merah</option>
                                    <option value="Encer">Encer</option>
                                    <option value="Kuning">Kuning</option>
                                    <option value="Kental">Kental</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_respirasi_icu" class="btn btn-success pull-right" onclick="saveRespirasi('respirasi_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_respirasi_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-keseimbangan_icu">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Keseimbangan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_keseimbangan_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_keseimbangan_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-5">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="waktu_keseimbangan_icu">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label><b>I. Parenteral</b></label>
                        </div>
                    </div>
                    <input type="hidden" id="is_new_parenteral">
                    <input type="hidden" id="is_new_obat">
                    <input type="hidden" id="is_new_enteral">
                    <input type="hidden" id="is_new_output">
                    <div id="form-parenteral">
                        <div class="row">
                            <label class="col-md-3">Loading</label>
                            <div class="col-md-5">
                                <input class="form-control jenis_loading" placeholder="Jenis Loading">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control nilai_loading" placeholder="Nilai Loading" type="number">
                            </div>
                            <div class="col-md-1">
                                <button style="margin-top:2px; margin-left: -20px" onclick="setCustomeJenis('set_loading', 'loading')" class="btn btn-success"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div id="set_loading"></div>
                        <div class="row jarak">
                            <label class="col-md-3">Darah</label>
                            <div class="col-md-5">
                                <input class="form-control jenis_darah" placeholder="Jenis Darah">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control nilai_darah" placeholder="Nilai Darah" type="number">
                            </div>
                            <div class="col-md-1">
                                <button style="margin-top:2px; margin-left: -20px" onclick="setCustomeJenis('set_darah', 'darah')" class="btn btn-success"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div id="set_darah"></div>
                        <div class="row jarak" >
                            <label class="col-md-3">Cairan</label>
                            <div class="col-md-5">
                                <input class="form-control jenis_cairan" placeholder="Jenis Cairan">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control nilai_cairan" placeholder="Nilai Cairan" type="number">
                            </div>
                            <div class="col-md-1">
                                <button style="margin-top:2px; margin-left: -20px" onclick="setCustomeJenis('set_cairan', 'cairan')" class="btn btn-success"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div id="set_cairan"></div>
                    </div>
                    <div id="h_parenteral"></div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">
                            <label><b>II. Obat-Obatan</b></label>
                        </div>
                    </div>
                    <div id="form-obat">
                        <div class="row">
                            <label class="col-md-3">Injeksi</label>
                            <div class="col-md-4">
                                <input class="form-control jenis_injeksi" placeholder="Jenis Injeksi">
                            </div>
                            <div class="col-md-2">
                                <input class="form-control kali_injeksi" data-inputmask="'mask': ['9x9']"
                                       data-mask="">
                            </div>
                            <div class="col-md-2">
                                <input class="form-control nilai_injeksi" type="number" placeholder="Nilai">
                            </div>
                            <div class="col-md-1">
                                <button style="margin-top:2px; margin-left: -20px" onclick="setCustomeJenis('set_injeksi', 'injeksi')" class="btn btn-success"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div id="set_injeksi"></div>
                        <div class="row jarak">
                            <label class="col-md-3">Oral</label>
                            <div class="col-md-4">
                                <input class="form-control jenis_oral" placeholder="Jenis Oral">
                            </div>
                            <div class="col-md-2">
                                <input class="form-control kali_oral" data-inputmask="'mask': ['9x9']"
                                       data-mask="">
                            </div>
                            <div class="col-md-2">
                                <input class="form-control nilai_oral" type="number" placeholder="Nilai">
                            </div>
                            <div class="col-md-1">
                                <button style="margin-top:2px; margin-left: -20px" onclick="setCustomeJenis('set_oral', 'oral')" class="btn btn-success"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div id="set_oral"></div>
                        <div class="row jarak">
                            <label class="col-md-3">Lainnya</label>
                            <div class="col-md-4">
                                <input class="form-control jenis_lainnya" placeholder="Jenis Lainnya">
                            </div>
                            <div class="col-md-2">
                                <input class="form-control kali_lainnya" data-inputmask="'mask': ['9x9']"
                                       data-mask="">
                            </div>
                            <div class="col-md-2">
                                <input class="form-control nilai_lainnya" type="number" placeholder="Nilai">
                            </div>
                            <div class="col-md-1">
                                <button style="margin-top:2px; margin-left: -20px" onclick="setCustomeJenis('set_lainnya', 'lainnya')" class="btn btn-success"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                        <div id="set_lainnya"></div>
                    </div>
                    <div id="h_obat"></div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">
                            <label><b>III. Enteral</b></label>
                        </div>
                    </div>
                    <div id="form-enteral">
                        <div class="row">
                            <label class="col-md-3">NGT/Oral</label>
                            <div class="col-md-3">
                                <input class="form-control" id="ngt" type="number">
                            </div>
                            <label class="col-md-2">Minum</label>
                            <div class="col-md-3">
                                <input class="form-control" id="minum" type="number">
                            </div>
                        </div>
                    </div>
                    <div id="h_enteral"></div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">
                            <label><b>IV. Output</b></label>
                        </div>
                    </div>
                    <div id="form-output">
                        <div class="row">
                            <label class="col-md-3">Drain 1</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="drain1">
                            </div>
                            <label class="col-md-2">Drain 2</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="drain2">
                            </div>
                        </div>
                        <div class="row jarak">
                            <label class="col-md-3">Urin</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="urin">
                            </div>
                            <label class="col-md-2">Muntah</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="muntah">
                            </div>
                        </div>
                        <div class="row jarak">
                            <label class="col-md-3">BAB</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="bab">
                            </div>
                            <label class="col-md-2">IWL</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="iwl">
                            </div>
                        </div>
                        <div class="row jarak">
                            <label class="col-md-3">Stoma</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="stoma">
                            </div>
                        </div>
                    </div>
                    <div id="h_output"></div>
                    <%--<div id="select_isi"></div>--%>
                    <%--<div id="resus"></div>--%>
                    <%--<div id="darah"></div>--%>
                    <%--<div id="infus"></div>--%>
                    <%--<div id="inpt_keseimbangan_icu"></div>--%>
                    <%--<input type="hidden" id="is_new">--%>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_keseimbangan_icu" class="btn btn-success pull-right" onclick="saveInputan('keseimbangan_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_keseimbangan_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-injeksi_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Injeksi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_injeksi_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_injeksi_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2 waktu" id="waktu_injeksi_icu" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                                    <option value="22:00">22:00</option>
                                    <option value="23:00">23:00</option>
                                    <option value="24:00">24:00</option>
                                    <option value="01:00">01:00</option>
                                    <option value="02:00">02:00</option>
                                    <option value="03:00">03:00</option>
                                    <option value="04:00">04:00</option>
                                    <option value="05:00">05:00</option>
                                    <option value="06:00">06:00</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="injeksi_icu"></div>
                    <div id="inpt_injeksi_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_injeksi_icu" class="btn btn-success pull-right" onclick="saveInputan('injeksi_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_injeksi_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-oral_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Oral
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_oral_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_oral_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_oral_icu" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                                    <option value="22:00">22:00</option>
                                    <option value="23:00">23:00</option>
                                    <option value="24:00">24:00</option>
                                    <option value="01:00">01:00</option>
                                    <option value="02:00">02:00</option>
                                    <option value="03:00">03:00</option>
                                    <option value="04:00">04:00</option>
                                    <option value="05:00">05:00</option>
                                    <option value="06:00">06:00</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="oral_icu"></div>
                    <div id="inpt_oral_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_oral_icu" class="btn btn-success pull-right" onclick="saveInputan('oral_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_oral_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-lain_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Lain-Lain
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_lain_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_lain_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_lain_icu" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                                    <option value="22:00">22:00</option>
                                    <option value="23:00">23:00</option>
                                    <option value="24:00">24:00</option>
                                    <option value="01:00">01:00</option>
                                    <option value="02:00">02:00</option>
                                    <option value="03:00">03:00</option>
                                    <option value="04:00">04:00</option>
                                    <option value="05:00">05:00</option>
                                    <option value="06:00">06:00</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="lain_icu"></div>
                    <div id="inpt_lain_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_lain_icu" class="btn btn-success pull-right" onclick="saveInputan('lain_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_lain_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-intakea_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Intakea
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_intakea_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_intakea_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_intakea_icu" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                                    <option value="22:00">22:00</option>
                                    <option value="23:00">23:00</option>
                                    <option value="24:00">24:00</option>
                                    <option value="01:00">01:00</option>
                                    <option value="02:00">02:00</option>
                                    <option value="03:00">03:00</option>
                                    <option value="04:00">04:00</option>
                                    <option value="05:00">05:00</option>
                                    <option value="06:00">06:00</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="intakea_icu"></div>
                    <div id="inpt_intakea_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_intakea_icu" class="btn btn-success pull-right" onclick="saveInputan('intakea_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_intakea_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-output_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Output
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_output_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_output_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_output_icu" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                                    <option value="22:00">22:00</option>
                                    <option value="23:00">23:00</option>
                                    <option value="24:00">24:00</option>
                                    <option value="01:00">01:00</option>
                                    <option value="02:00">02:00</option>
                                    <option value="03:00">03:00</option>
                                    <option value="04:00">04:00</option>
                                    <option value="05:00">05:00</option>
                                    <option value="06:00">06:00</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="output_icu"></div>
                    <div id="inpt_output_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_output_icu" class="btn btn-success pull-right" onclick="saveInputan('output_icu', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_output_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-asuhan_keperawatan_icu">
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_icu_asuhan_keperawatan_icu">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_icu_asuhan_keperawatan_icu"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ake1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ake2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Diagnosa</label>
                            <div class="col-md-8">
                                <input class="form-control" id="diagnosa_askep_icu" oninput="searchDiagnosaAskep(this.id, 'icu')">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <table class="table table-striped table-bordered" style="font-size: 12px">
                        <thead>
                        <tr style="font-weight: bold">
                            <td width="20%">Diagnosis</td>
                            <td width="20%">Rencana</td>
                            <td width="20%">Tindakan</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div id="dia"></div>
                            </td>
                            <td><div id="rec"></div></td>
                            <td><select id="tin" class="form-control select2" multiple style="width: 100%"></select></td>
                        </tr>
                        </tbody>
                    </table>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-4">
                                <label>Data Subjektif</label>
                                <select class="form-control select2" id="askep_subjek" onchange="setSubjektif(this.value)" style="width: 100%"> </select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div id="temp_subjektif"></div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label>TTD Perawat</label>
                                <canvas class="paint-canvas-ttd" id="asuhan_perawat" width="220"
                                        onmouseover="paintTtd('asuhan_perawat')"></canvas>
                                <input class="form-control nama_petugas" id="nama_asuhan_perawat" placeholder="Nama Terang">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('asuhan_perawat')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="asuhan_dpjp" width="220"
                                        onmouseover="paintTtd('asuhan_dpjp')"></canvas>
                                <input class="form-control nama_dokter_ri" id="nama_asuhan_dpjp" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_asuhan_dpjp" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('asuhan_dpjp')"><i
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
                <button id="save_icu_asuhan_keperawatan_icu" class="btn btn-success pull-right"
                        onclick="saveAsuhanKeperawatanICU('asuhan_keperawatan_icu','asesmen_icu')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_icu_asuhan_keperawatan_icu" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-edit-obat">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Edit Obat-obatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_icu_edit_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_icu_edit_obat"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <label class="col-md-offset-2 col-md-2">Nilai</label>
                        <div class="col-md-6">
                            <input class="form-control" id="obat_nilai" type="number">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_icu_edit_obat" class="btn btn-success pull-right"
                        onclick="saveEditObat()"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_icu_edit_obat" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>