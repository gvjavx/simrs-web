<div class="modal fade" id="modal-asesmen-ugd-dewasa">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Awal Gawat Darurat
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_asesmen_ugd-dewasa">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_asesmen_ugd-dewasa"></p>
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
                            <li><a onclick="addAsesmenUgd('keluhan_utama')" style="cursor: pointer" ><i class="fa fa-plus"></i> Keluhan Utama</a></li>
                            <li><a onclick="addAsesmenUgd('pre_hospital')" style="cursor: pointer"><i class="fa fa-plus"></i> Data Pre Hospital</a></li>
                            <li><a onclick="addAsesmenUgd('keperawatan')" style="cursor: pointer"><i class="fa fa-plus"></i> Keperawatan</a></li>
                            <li><a onclick="addAsesmenUgd('nyeri')" style="cursor: pointer"><i class="fa fa-plus"></i> Asesmen Nyeri</a></li>
                            <li><a onclick="addAsesmenUgd('nutrisional')" style="cursor: pointer"><i class="fa fa-plus"></i> Skrining Risiko Nutrisional</a></li>
                            <li><a onclick="addAsesmenUgd('jatuh')" style="cursor: pointer"><i class="fa fa-plus"></i> Asesmen Resiko Jatuh</a></li>
                            <li><a onclick="addAsesmenUgd('status')" style="cursor: pointer"><i class="fa fa-plus"></i> Status Fungsional</a></li>
                            <li><a onclick="addAsesmenUgd('kebutuhan')" style="cursor: pointer"><i class="fa fa-plus"></i> Kebutuhan Edukasi</a></li>
                            <li><a onclick="addAsesmenUgd('diagnosis')" style="cursor: pointer"><i class="fa fa-plus"></i> Diagnosis Keperawatan</a></li>
                            <li><a onclick="addAsesmenUgd('asuhan')" style="cursor: pointer"><i class="fa fa-plus"></i> Rencanan Asuhan Keperawatan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_aud">
                        <tbody>
                        <tr id="row_aud_keluhan_utama">
                            <td>Keluhan Utama</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_keluhan_utama" class="hvr-grow" onclick="detailAud('keluhan_utama')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_pre_hospital">
                            <td>Data Pre Hospipal</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_pre_hospital" class="hvr-grow" onclick="detailAud('pre_hospital')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_keperawatan">
                            <td>Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_keperawatan" class="hvr-grow" onclick="detailAud('keperawatan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_nyeri">
                            <td>Asesmen Nyeri</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_nyeri" class="hvr-grow" onclick="detailAud('nyeri')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_nutrisional">
                            <td>Skrining Risiko Nutrisional</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_nutrisional" class="hvr-grow" onclick="detailAud('nutrisional')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_jatuh">
                            <td>Asesmen Resiko Jatuh</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_jatuh" class="hvr-grow" onclick="detailAud('jatuh')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_status">
                            <td>Status Fungsional</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_status" class="hvr-grow" onclick="detailAud('status')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_kebutuhan">
                            <td>Kebutuhan Edukasi</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_kebutuhan" class="hvr-grow" onclick="detailAud('kebutuhan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_diagnosis">
                            <td>Diagnosis Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_diagnosis" class="hvr-grow" onclick="detailAud('diagnosis')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_asuhan">
                            <td>Rencanan Asuhan Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_asuhan" class="hvr-grow" onclick="detailAud('asuhan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
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

<div class="modal fade" id="modal-aud-keluhan_utama">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Keluhan Utama
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_keluhan_utama">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_aud_keluhan_utama"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Triase</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Merah" id="triase1" name="radio_triase" /><label for="triase1">Merah</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kuning" id="triase2" name="radio_triase" /><label for="triase2">Kuning</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Hijau" id="triase3" name="radio_triase" /><label for="triase3">Hijau</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Hitam" id="triase4" name="radio_triase" /><label for="triase4">Hitam</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Keadaan Umum</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Gelisah/Koma" id="keadaan1" name="radio_keadaan" /><label for="keadaan1">Gelisah/Koma</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Lemah/Kesakitan" id="keadaan2" name="radio_keadaan" /><label for="keadaan2">Lemah/Kesakitan</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Cukup" id="keadaan3" name="radio_keadaan" /><label for="keadaan3">Cukup</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pernafasan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="< 12 / > 30x/m" id="napas1" name="radio_napas" /><label for="napas1">< 12 / > 30x/m</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="21-30x/m" id="napas2" name="radio_napas" /><label for="napas2">21-30x/m</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="12-20x/m" id="napas3" name="radio_napas" /><label for="napas3">12-20x/m</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="< 50 / > 150x/m" id="nadi1" name="radio_nadi" /><label for="nadi1"><50/>150x/m</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="101-150x/m" id="nadi2" name="radio_nadi" /><label for="nadi2">101-150x/m</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="50-100x/m" id="nadi3" name="radio_nadi" /><label for="nadi3">50-100x/m</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Akral</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="< 12 / > 30x/m" id="akral1" name="radio_akral" /><label for="akral1">Dingin</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="21-30x/m" id="akral2" name="radio_akral" /><label for="akral2">Dingin</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="12-20x/m" id="akral3" name="radio_akral" /><label for="akral3">Hangat</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">CRT</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="> 2 detik" id="crt1" name="radio_crt" /><label for="crt1">> 2 detik</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="< 2 detik" id="crt2" name="radio_crt" /><label for="crt2">< 2 detik</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="< 2 detik" id="crt3" name="radio_crt" /><label for="crt3">< 2 detik</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_aud_keluhan_utama" class="btn btn-success pull-right" onclick="saveAsesmenUgd('keluhan_utama')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_keluhan_utama" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-pre_hospital">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Keluhan Utama
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_pre_hospital">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_aud_pre_hospital"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <label>Tekanan Darah</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_tkn">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>Nadi</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_nadi">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>RR</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_rr">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>Suhu</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_suhu">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Airway</div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Airway</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Paten" id="airway1" name="radio_airway" /><label for="airway1">Paten</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Gurgling" id="airway2" name="radio_airway" /><label for="airway2">Gurgling</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Snooring" id="airway3" name="radio_airway" /><label for="airway3">Snooring</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Stridor" id="airway4" name="radio_airway" /><label for="airway4">Stridor</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Breathing</div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Warna Kulit</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="kulit1" name="radio_kulit" /><label for="kulit1">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Cyanosi" id="kulit2" name="radio_kulit" /><label for="kulit2">Cyanosi</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pola Nafas</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="pola1" name="radio_pola" /><label for="pola1">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kussmaul" id="pola2" name="radio_pola" /><label for="pola2">Kussmaul</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Cheyne Stoke" id="pola3" name="radio_pola" /><label for="pola3">Cheyne Stoke</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kerja Nafas</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="kerja_napas1" name="radio_kerja_napas" /><label for="kerja_napas1">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Takipnea" id="kerja_napas2" name="radio_kerja_napas" /><label for="kerja_napas2">Takipnea</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dyspnea" id="kerja_napas3" name="radio_kerja_napas" /><label for="kerja_napas3">Dyspnea</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Suara Nafas</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Vesikuler" id="suara_napas1" name="radio_suara_napas" /><label for="suara_napas1">Vesikuler</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Wheezing" id="suara_napas2" name="radio_suara_napas" /><label for="suara_napas2">Wheezing</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ronchi" id="suara_napas3" name="radio_suara_napas" /><label for="suara_napas3">Ronchi</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Stridor" id="suara_napas4" name="radio_suara_napas" /><label for="suara_napas4">Stridor</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jejas</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="jejas1" name="radio_jejas" /><label for="jejas1">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="jejas2" name="radio_jejas" /><label for="jejas2">Tidak</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Deviasi Trakhea</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="dt1" name="radio_dt" /><label for="dt1">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="dt2" name="radio_dt" /><label for="dt2">Tidak</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pengembangan Dada</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Simetris" id="pd1" name="radio_pd" /><label for="pd1">Simetris</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pd2" name="radio_pd" /><label for="pd2">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Distensi vena jugularis</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="dvj1" name="radio_dvj" /><label for="dvj1">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="dvj2" name="radio_dvj" /><label for="dvj2">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Otot bantu nafas</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="breathing_bantu">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">RR</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="breathing_rr">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">SpO<small>2</small></label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="breathing_sp">
                                    <div class="input-group-addon" style="font-size: 10px; width: 50%">
                                        %
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Cirulation</div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kwalitas nadi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kuat" id="kwn1" name="radio_kwn" /><label for="kwn1">Kuat</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Lemah" id="kwn2" name="radio_kwn" /><label for="kwn2">Lemah</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Irama</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Reguler" id="irama1" name="radio_irama" /><label for="irama1">Reguler</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ireguler" id="irama2" name="radio_irama" /><label for="irama2">Ireguler</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">CRT</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px;">
                                    <input type="radio" value="< 2 detik" id="cir_crt1" name="radio_cir_crt" /><label for="cir_crt1"><2detik</label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="custom02" style="margin-top: 7px;">
                                    <input type="radio" value="> 2detik" id="cir_crt2" name="radio_cir_crt" /><label for="cir_crt2"><2detik</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Warna Kulit</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="cir_warna_kulit1" name="radio_cir_warna_kulit" /><label for="cir_warna_kulit1">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Pucat" id="cir_warna_kulit2" name="radio_cir_warna_kulit" /><label for="cir_warna_kulit2">Pucat</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Akral</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Hangat" id="cri_akral1" name="radio_cri_akral" /><label for="cri_akral1">Hangat</label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dingin" id="cri_akral2" name="radio_cri_akral" /><label for="cri_akral2">Dingin</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="circulation_nadi">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_aud_pre_hospital" class="btn btn-success pull-right" onclick="saveAsesmenUgd('pre_hospital')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_pre_hospital" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-keperawatan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_keperawatan">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_aud_keperawatan"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <label>Tekanan Darah</label>
                                <div class="input-group">
                                    <input class="form-control" id="kep_tekanan_darah">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>Nadi</label>
                                <div class="input-group">
                                    <input class="form-control" id="kep_nadi">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>RR</label>
                                <div class="input-group">
                                    <input class="form-control" id="kep_rr">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>Suhu</label>
                                <div class="input-group">
                                    <input class="form-control" id="kep_suhu">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <label style="margin-top: 7px">Berat Badan</label>
                                <div class="input-group">
                                    <input class="form-control" id="kep_bb">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        Kg
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Psiko-sosio-Spritual</div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tempat Tinggal</label>
                            <div class="col-md-8">
                                <select onchange="audKeterangan(this.value, 'tempat_tinggal')" id="kep_tempat_tinggal" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Rumah Sendiri">Rumah Sendiri</option>
                                    <option value="Bermsama Orang Tua">Bermsama Orang Tua</option>
                                    <option value="Bersama Mertua">Bersama Mertua</option>
                                    <option value="Bersama Anak">Bersama Anak</option>
                                    <option value="Lainnya">Lainnya</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" id="form_aud_tempat_tinggal" style="display: none;">
                            <div class="col-md-offset-3 col-md-8">
                                <input class="form-control" id="kep_ada_tempat_tinggal" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Status Emosional</label>
                            <div class="col-md-8">
                                <select id="kep_emosional" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tenang">Tenang</option>
                                    <option value="Cemas">Cemas</option>
                                    <option value="Marah">Marah</option>
                                    <option value="Sedih">Sedih</option>
                                    <option value="Takut">Takut</option>
                                    <option value="Kecenderungan Bunuh Diri">Kecenderungan Bunuh Diri</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Masalah Perilaku</label>
                            <div class="col-md-8">
                                <select onchange="audKeterangan(this.value, 'perilaku')" id="kep_perilaku" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tidak">Tidak</option>
                                    <option value="Ada">Ada</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form_aud_perilaku">
                            <div class="col-md-offset-3 col-md-8">
                               <input class="form-control" id="kep_ada_perilaku" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Status Pernikahan</label>
                            <div class="col-md-8">
                                <select id="kep_pernikahan" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Belum Menikah">Belum Menikah</option>
                                    <option value="Menikah">Menikah</option>
                                    <option value="Cerai">Cerai</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Hubungan pasien dengan keluarga</label>
                            <div class="col-md-8">
                                <select id="kep_hubungan" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Baik">Baik</option>
                                    <option value="Tidak">Tidak</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Agama</label>
                            <div class="col-md-8">
                                <select id="kep_agama" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Islam">Islam</option>
                                    <option value="Kristen">Kristen</option>
                                    <option value="Katolik">Katolik</option>
                                    <option value="Hindu">Hindu</option>
                                    <option value="Buddha">Buddha</option>
                                    <option value="Konghucu">Konghucu</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perlu dibantu dalam ibadah</label>
                            <div class="col-md-8">
                                <select id="kep_dibantu" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Ya">Ya</option>
                                    <option value="Tidak">Tidak</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Ekonomi</div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pekerjaan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kep_pekerjaan">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Penanggung Jawab</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <select onchange="audKeterangan(this.value, 'penanggung')" id="kep_penanggung" class="form-control select2" style="width: 100%; margin-top: 7px">
                                        <option value="">[Select One]</option>
                                        <option value="Pribadi">Pribadi</option>
                                        <option value="Asuransi">Asuransi</option>
                                        <option value="Lainnya">Lainnya</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form_aud_penanggung">
                            <div class="col-md-offset-3 col-md-8">
                                <input class="form-control" id="kep_ada_penanggung" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Riwayat Kesehatan</div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat Penyakit Sekarang</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kep_sekarang">
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kep_dahulu">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat Penyakit Keluarga</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kep_keluarga" >
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Riwayat Alergi</div>
                <div class="row" style="margin-top: 10px">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Riwayat Alergi</label>
                        <div class="col-md-8">
                            <select onchange="audKeterangan(this.value, 'alergi')" id="kep_alergi" class="form-control select2" style="width: 100%; margin-top: 7px">
                                <option value="">[Select One]</option>
                                <option value="Tidak Ada">Tidak Ada</option>
                                <option value="Obat">Obat</option>
                                <option value="Makanan">Makanan</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="display: none" id="form_aud_alergi">
                        <div class="col-md-offset-3 col-md-8">
                            <input class="form-control" id="kep_ket_alergi" style="margin-top: 7px">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_aud_keperawatan" class="btn btn-success pull-right" onclick="saveAsesmenUgd('keperawatan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_keperawatan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-nyeri">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Nyeri
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_nyeri">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_nyeri"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Apakah terdapat keluhan nyeri</label>
                                <div class="col-md-2">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Ya" id="aud_nyeri1" name="radio_aud_nyeri" /><label for="aud_nyeri1">Ya</label>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Tidak" id="aud_nyeri2" name="radio_aud_nyeri" /><label for="aud_nyeri2">Tidak</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5" style="margin-top: 7px">Skala</label>
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
                                <label class="col-md-5" style="margin-top: 7px">Lokasi</label>
                                <div class="col-md-7">
                                    <input class="form-control" id="yer_lokasi">
                                </div>
                            </div>
                            <img src="<%= request.getContextPath() %>/pages/images/asesmen-nyeri.jpg" style="width: 100%; margin-top: 20px">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_nyeri" class="btn btn-success pull-right" onclick="saveAsesmenUgd('nyeri')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_nyeri" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-nutrisional">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Skrining Resiko Nutrisional
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_nutrisional">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_nutrisional"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label style="margin-top: 7px">1. Apakah pasien mengalami penurunan berat badan yang tidak direncanakan /tidak diinginkan dalam 6 bulan terakhir?</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak|0" id="aud_nutrisional1" name="radio_aud_nutrisional" /><label for="aud_nutrisional1">Tidak</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak Yakin|2" id="aud_nutrisional2" name="radio_aud_nutrisional" /><label for="aud_nutrisional2">Tidak Yakin (ada tanda: baju menjadi longgar)</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Ya" id="aud_nutrisional3" name="radio_aud_nutrisional" /><label for="aud_nutrisional3">Ya, ada penurunan BB sebanyak:</label>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none;" id="aud_penurunan">
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="1 - 5 kg|1" id="aud_penurunan1" name="radio_aud_penurunan" /><label for="aud_penurunan1">1 - 5 kg</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="6 - 10 kg|2" id="aud_penurunan2" name="radio_aud_penurunan" /><label for="aud_penurunan2">6 - 10 kg</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="11 - 15 kg|3" id="aud_penurunan3" name="radio_aud_penurunan" /><label for="aud_penurunan3">11 - 15 kg</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="> 15 kg|4" id="aud_penurunan4" name="radio_aud_penurunan" /><label for="aud_penurunan4">> 15 kg</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak tahu berapa kg penurunannya" id="aud_nutrisional4" name="radio_aud_nutrisional" /><label for="aud_nutrisional4">Tidak tahu berapa kg penurunannya</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label style="margin-top: 7px">2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan/kesulitan menerima makanan?</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak|0" id="aud_nafsu1" name="radio_aud_nafsu1" /><label for="aud_nafsu1">Tidak</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Ya|1" id="aud_nafsu2" name="radio_aud_nafsu1" /><label for="aud_nafsu2">Ya</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_nutrisional" class="btn btn-success pull-right" onclick="saveAsesmenUgd('nutrisional')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_nutrisional" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-jatuh">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Risiko Jatuh
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_jatuh">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_jatuh"></p>
                    </div>
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
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_jatuh" class="btn btn-success pull-right" onclick="saveAsesmenUgd('jatuh')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_jatuh" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-status">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Status Funsional
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_status">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_status"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Mengendalikan rangsang defekasi</label>
                            <div class="col-md-6">
                                <select id="status1" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tak terkendali/tak teratur (perlu pencahar)|0">Tak terkendali/tak teratur (perlu pencahar)</option>
                                    <option value="Kadang-kadang tak terkendali|5">Kadang-kadang tak terkendali</option>
                                    <option value="Terkendali teratur|10">Terkendali teratur</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Mengendalikan rangsang berkemih</label>
                            <div class="col-md-6">
                                <select id="status2" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tak terkendali/pakai kateter|0">Tak terkendali/pakai kateter</option>
                                    <option value="Kadang-kadang tak terkendali (1x24 jam)|5">Tak terkendali/pakai kateter (1x24 jam)</option>
                                    <option value="Mandiri|10">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Membersihkan diri (cuci muka, sisir rambut, sikat gigi)</label>
                            <div class="col-md-6">
                                <select id="status3" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Butuh pertolongan orang lain|0">Butuh pertolongan orang lain</option>
                                    <option value="Mandiri|5">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Penggunaan jamban masuk dan keluar (melepaskan, memakai celana, membersihkan, menyiram)</label>
                            <div class="col-md-6">
                                <select id="status4" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tergantung pertolongan orang lain|0">Tergantung pertolongan orang lain</option>
                                    <option value="Perlu pertolongan pada beberapa kegiatan tetapi dapat menegrjakan sendiri kegiataan yang lain|5">Perlu pertolongan pada beberapa kegiatan tetapi dapat menegrjakan sendiri kegiataan yang lain</option>
                                    <option value="Mandiri|10">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Makan</label>
                            <div class="col-md-6">
                                <select id="status5" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tidak mampu|0">Tidak mampu</option>
                                    <option value="Perlu pertolongan memotong makanan|5">Perlu pertolongan memotong makanan</option>
                                    <option value="Mandiri|10">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Berubah sikap dari berbaring ke duduk</label>
                            <div class="col-md-6">
                                <select id="status6" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tidak mampu|0">Tidak mampu</option>
                                    <option value="Perlu banyak bantuan untuk bisa duduk (2 orang)|5">Perlu banyak bantuan untuk bisa duduk (2 orang)</option>
                                    <option value="Mandiri|10">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Berpindah / Berjalan</label>
                            <div class="col-md-6">
                                <select id="status7" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tidak mampu|0">Tidak mampu</option>
                                    <option value="Bisa (pindah) dengan kursi roda|5">Bisa (pindah) dengan kursi roda</option>
                                    <option value="Berjalan dengan bantuan 1 orang|10">Berjalan dengan bantuan 1 orang</option>
                                    <option value="Mandiri|15">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Memakai baju</label>
                            <div class="col-md-6">
                                <select id="status8" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tergantung orang lain|0">Tergantung orang lain</option>
                                    <option value="Sebagian dibantu (misalnya mengancing baju)|5">Sebagian dibantu (misalnya mengancing baju)</option>
                                    <option value="Mandiri|10">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Naik turun tangga</label>
                            <div class="col-md-6">
                                <select id="status9" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tidak mampu|0">Tidak mampu</option>
                                    <option value="Kurang pertolongan|5">Kurang pertolongan</option>
                                    <option value="Mandiri|10">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Mandi</label>
                            <div class="col-md-6">
                                <select id="status10" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tergantung orang lain|0">Tergantung orang lain</option>
                                    <option value="Mandiri|10">Mandiri</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_status" class="btn btn-success pull-right" onclick="saveAsesmenUgd('status')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_status" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-kebutuhan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Kebutuhan Edukasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_kebutuhan">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_kebutuhan"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Bicara</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="aud_kebutuhan11" name="radio_aud_kebutuhan1" /><label for="aud_kebutuhan11">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Gangguan" id="aud_kebutuhan12" name="radio_aud_kebutuhan1" /><label for="aud_kebutuhan12">Gangguan</label></div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-kebutuhan1">
                            <div class="col-md-offset-3 col-md-5">
                                <input class="form-control" id="aud_ket_kebutuhan1">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perlu Penerjemah</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="aud_kebutuhan21" name="radio_aud_kebutuhan2" /><label for="aud_kebutuhan21">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="aud_kebutuhan22" name="radio_aud_kebutuhan2" /><label for="aud_kebutuhan22">Ya</label></div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-kebutuhan2">
                            <div class="col-md-offset-3 col-md-5">
                                <input class="form-control" id="aud_ket_kebutuhan2">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Hambatan belajar</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="aud_kebutuhan31" name="radio_aud_kebutuhan3" /><label for="aud_kebutuhan31">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="aud_kebutuhan32" name="radio_aud_kebutuhan3" /><label for="aud_kebutuhan32">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div id="form-ket-kebutuhan3" style="display: none">
                        <div class="form-group" style="font-size: 12px">
                            <div class="col-md-offset-3 col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="pendengaran" id="ket_kebutuhan31" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan31">pendengaran</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="penglihatan" id="ket_kebutuhan32" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan32">penglihatan</label></div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="kognitif" id="ket_kebutuhan33" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan33">kognitif</label></div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="fisik" id="ket_kebutuhan34" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan34">fisik</label></div>
                            </div>
                        </div>
                        <div class="form-group" style="font-size: 12px">
                            <div class="col-md-offset-3 col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="budaya" id="ket_kebutuhan35" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan35">budaya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="agama" id="ket_kebutuhan36" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan36">agama</label></div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="emosi" id="ket_kebutuhan37" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan37">emosi</label></div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="bahasa" id="ket_kebutuhan38" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan38">bahasa</label></div>
                            </div>
                        </div>
                        <div class="form-group" style="font-size: 12px">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="lainnya" id="ket_kebutuhan39" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan39">lainnya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                    <div class="row">
                        <div class="form-group" style="display: none" id="form-kebutuhan3">
                            <div class="col-md-offset-3 col-md-5">
                                <input class="form-control" id="aud_ket_kebutuhan310">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kebutuhan Pembelajaran</label>
                            <div class="col-md-2" style="font-size: 12px">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="proses penyakit" id="aud_kebutuhan41" name="radio_aud_kebutuhan4" /><label for="aud_kebutuhan41">proses penyakit</label>
                                </div>
                            </div>
                            <div class="col-md-2" style="font-size: 12px">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="manajemen nyeri" id="aud_kebutuhan42" name="radio_aud_kebutuhan4" /><label for="aud_kebutuhan42">manejemen nyeri</label></div>
                            </div>
                            <div class="col-md-2" style="font-size: 12px">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="terapi/obat" id="aud_kebutuhan43" name="radio_aud_kebutuhan4" /><label for="aud_kebutuhan43">terapi/obat</label></div>
                            </div>
                            <div class="col-md-2" style="font-size: 12px">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="diet/nutrisi" id="aud_kebutuhan44" name="radio_aud_kebutuhan4" /><label for="aud_kebutuhan44">diet/nutrisi</label></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2" style="font-size: 12px">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="rehabilitas" id="aud_kebutuhan45" name="radio_aud_kebutuhan4" /><label for="aud_kebutuhan45">rehabilitas</label>
                                </div>
                            </div>
                            <div class="col-md-5" style="font-size: 12px">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="lainnya" id="aud_kebutuhan46" name="radio_aud_kebutuhan4" /><label for="aud_kebutuhan46">lainnya</label></div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-kebutuhan4">
                            <div class="col-md-offset-3 col-md-5">
                                <input class="form-control" id="aud_ket_kebutuhan47">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_kebutuhan" class="btn btn-success pull-right" onclick="saveAsesmenUgd('kebutuhan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_kebutuhan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-diagnosis">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Diagnosis Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_diagnosis">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_diagnosis"></p>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis1" value="Resiko atau aktual perubahan pertukaran gas berhubungan dengan perubahan membran alveoli-paru">
                            <label for="diagnosis1"></label> Resiko atau aktual perubahan pertukaran gas berhubungan dengan perubahan membran alveoli-paru
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis2" value="Bersihan jalan nafas tidak efektif">
                            <label for="diagnosis2"></label> Bersihan jalan nafas tidak efektif
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis3" value="Intoleransi aktivitas berhubungan dengan ketidakseimbangan antara suplai dan kebutuhan oksigen">
                            <label for="diagnosis3"></label> Intoleransi aktivitas berhubungan dengan ketidakseimbangan antara suplai dan kebutuhan oksigen
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis4" value="Nyeri akut">
                            <label for="diagnosis4"></label> Nyeri akut
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis5" value="Kecemasan atau ketakutan berhubungan dengan ancaman kematian, perubahan kesehatan">
                            <label for="diagnosis5"></label> Kecemasan atau ketakutan berhubungan dengan ancaman kematian, perubahan kesehatan
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis6" value="Penurunan cardiac output berhubungan dengan vasokontriksi, penurunan kontraksi jantung">
                            <label for="diagnosis6"></label> Penurunan cardiac output berhubungan dengan vasokontriksi, penurunan kontraksi jantung
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis7" value="Perubahan perfusi jaringan otak">
                            <label for="diagnosis7"></label> Perubahan perfusi jaringan otak
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis8" value="Resiko bersihan jalan tidak efektif">
                            <label for="diagnosis8"></label> Resiko bersihan jalan tidak efektif
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis9" value="Resiko trauma berhubungan dengan kejang">
                            <label for="diagnosis9"></label> Resiko trauma berhubungan dengan kejang
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis10" value="Defisit volume cairan">
                            <label for="diagnosis10"></label> Defisit volume cairan
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis11" value="Resiko terjadi trauma tambahan berhubungan dengan pergerakan fragmen tulang">
                            <label for="diagnosis11"></label> Resiko terjadi trauma tambahan berhubungan dengan pergerakan fragmen tulang
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis12" value="Resiko terjadi disfungsi neurovaskuler perifer berhubungan dengan hambatan aliran darah, hipovolemia">
                            <label for="diagnosis12"></label> Resiko terjadi disfungsi neurovaskuler perifer berhubungan dengan hambatan aliran darah, hipovolemia
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis13" value="Resiko defisit cairan berhubungan dengan pengeluaran berlebihan melalui kulit, hipermetabolisme">
                            <label for="diagnosis13"></label> Resiko defisit cairan berhubungan dengan pengeluaran berlebihan melalui kulit, hipermetabolisme
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis14" value="Resiko infeksi berhubungan dengan hilangnya proteksi primer">
                            <label for="diagnosis14"></label> Resiko infeksi berhubungan dengan hilangnya proteksi primer
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis15" value="Perubahan pola nafas berhubungan dengan efek racun dalam tubuh">
                            <label for="diagnosis15"></label> Perubahan pola nafas berhubungan dengan efek racun dalam tubuh
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis16" value="Risiko terjadi disfungsi neurovaskuler perifer berhubungan dengan adanya racun di tubuh">
                            <label for="diagnosis16"></label> Risiko terjadi disfungsi neurovaskuler perifer berhubungan dengan adanya racun di tubuh
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis17" value="Pola nafas tidak efektif berhubungan dengan perdarahan nyeri akut">
                            <label for="diagnosis17"></label> Pola nafas tidak efektif berhubungan dengan perdarahan nyeri akut
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_diagnosis" id="diagnosis18" value="Perubahan perfusi jaringan berhubungan dengan perdarahan">
                            <label for="diagnosis18"></label> Perubahan perfusi jaringan berhubungan dengan perdarahan
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_diagnosis" class="btn btn-success pull-right" onclick="saveAsesmenUgd('diagnosis')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_diagnosis" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-asuhan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rencana Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_asuhan">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_asuhan"></p>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan1" value="Pemberian oksigen (nasal kanul, masker sederhana, masker non-rebreathing atau rebreathing dan ventilator)">
                            <label for="asuhan1"></label> Pemberian oksigen (nasal kanul, masker sederhana, masker non-rebreathing atau rebreathing dan ventilator)
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan3" value="Memasang oksimetri ">
                            <label for="asuhan3"></label> Memasang oksimetri
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan4" value="Melakukan suction melalui mulut atau hidung">
                            <label for="asuhan4"></label> Melakukan suction melalui mulut atau hidung
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan5" value="Memberikan bantuan nafas melalui BVM atau pocket mask">
                            <label for="asuhan5"></label> Memberikan bantuan nafas melalui BVM atau pocket mask
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan6" value="Memasang OPA (oropharygeal airway)">
                            <label for="asuhan6"></label> Memasang OPA (oropharygeal airway)
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan7" value="Memasang EKG">
                            <label for="asuhan7"></label> Memasang EKG
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan8" value="Melakukan resusitasi jantung pulmonal">
                            <label for="asuhan8"></label> Melakukan resusitasi jantung pulmonal
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan9" value="Pemasangan collar leher">
                            <label for="asuhan9"></label> Pemasangan collar leher
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan10" value="Memberikan insulin">
                            <label for="asuhan10"></label> Memberikan insulin
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan11" value="Melakukan pembidaian">
                            <label for="asuhan11"></label> Melakukan pembidaian
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan12" value="Melakukan perawatan luka">
                            <label for="asuhan12"></label> Melakukan perawatan luka
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan13" value="Perawatan luka bakar">
                            <label for="asuhan13"></label> Perawatan luka bakar
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan14" value="Melakukan kumbah lambung">
                            <label for="asuhan14"></label> Melakukan kumbah lambung
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_asuhan" id="asuhan15" value="Memasang infus dan resusitasi cairan">
                            <label for="asuhan15"></label> Memasang infus dan resusitasi cairan
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_asuhan" class="btn btn-success pull-right" onclick="saveAsesmenUgd('asuhan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_asuhan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>