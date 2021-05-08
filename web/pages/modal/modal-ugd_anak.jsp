<div class="modal fade" id="modal-aud_asesmen-ugd">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Awal Gawat Darurat Anak</span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_aud_asesmen-ugd">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_aud_asesmen-ugd"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
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
                            <li><a onclick="addAsesmenUgd('keluhan_utama')" style="cursor: pointer" ><i class="fa fa-plus"></i> Triase</a></li>
                            <li><a onclick="addAsesmenUgd('pre_hospital')" style="cursor: pointer"><i class="fa fa-plus"></i> Data Pre Hospital & Data Primary Survei</a></li>
                            <li><a onclick="addAsesmenUgd('keperawatan')" style="cursor: pointer"><i class="fa fa-plus"></i> Keperawatan</a></li>
                            <li><a onclick="addAsesmenUgd('nyeri_anak')" style="cursor: pointer"><i class="fa fa-plus"></i> Asesmen Nyeri</a></li>
                            <li><a onclick="addAsesmenUgd('nutrisional_anak')" style="cursor: pointer"><i class="fa fa-plus"></i> Skrining Risiko Nutrisional</a></li>
                            <li><a onclick="addAsesmenUgd('jatuh_anak')" style="cursor: pointer"><i class="fa fa-plus"></i> Asesmen Resiko Jatuh</a></li>
                            <li><a onclick="addAsesmenUgd('status')" style="cursor: pointer"><i class="fa fa-plus"></i> Status Fungsional</a></li>
                            <li><a onclick="addAsesmenUgd('kebutuhan')" style="cursor: pointer"><i class="fa fa-plus"></i> Kebutuhan Edukasi</a></li>
                            <li><a onclick="addAsesmenUgd('diagnosis')" style="cursor: pointer"><i class="fa fa-plus"></i> Diagnosis Keperawatan</a></li>
                            <li><a onclick="addAsesmenUgd('asuhan')" style="cursor: pointer"><i class="fa fa-plus"></i> Rencanan Asuhan Keperawatan</a></li>
                            <li><a onclick="addAsesmenUgd('anamnesa')" style="cursor: pointer"><i class="fa fa-plus"></i> Anamnesis dan Pemeriksaan Fisik</a></li>
                            <li><a onclick="addAsesmenUgd('asuhan_medis')" style="cursor: pointer"><i class="fa fa-plus"></i> Rencana Asuhan Medis</a></li>
                            <li><a onclick="addAsesmenUgd('terintegrasi')" style="cursor: pointer"><i class="fa fa-plus"></i> Perkembangan Kondisi Pasien</a></li>
                            <li><a onclick="addAsesmenUgd('keluar_igd')" style="cursor: pointer"><i class="fa fa-plus"></i> Kondisi Saat Keluar IGD</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_aud">
                        <tbody>
                        <tr id="row_aud_keluhan_utama">
                            <td>Triase</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_keluhan_utama" class="hvr-grow" onclick="detailAud('keluhan_utama')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_keluhan_utama" class="hvr-grow btn-hide" onclick="conUGD('keluhan_utama', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_pre_hospital">
                            <td>Data Pre Hospital & Data Primary Survei</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_pre_hospital" class="hvr-grow" onclick="detailAud('pre_hospital')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pre_hospital" class="hvr-grow btn-hide" onclick="conUGD('pre_hospital', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_keperawatan">
                            <td>Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_keperawatan" class="hvr-grow" onclick="detailAud('keperawatan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_keperawatan" class="hvr-grow btn-hide" onclick="conUGD('keperawatan', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_nyeri_anak">
                            <td>Asesmen Nyeri</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_nyeri_anak" class="hvr-grow" onclick="detailAud('nyeri_anak')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_nyeri_anak" class="hvr-grow btn-hide" onclick="conUGD('nyeri_anak', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_nutrisional_anak">
                            <td>Skrining Risiko Nutrisional</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_nutrisional_anak" class="hvr-grow" onclick="detailAud('nutrisional_anak')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_nutrisional_anak" class="hvr-grow btn-hide" onclick="conUGD('nutrisional_anak', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_jatuh_anak">
                            <td>Asesmen Resiko Jatuh</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_jatuh_anak" class="hvr-grow" onclick="detailAud('jatuh_anak')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_jatuh_anak" class="hvr-grow btn-hide" onclick="conUGD('jatuh_anak', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_status">
                            <td>Status Fungsional</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_status" class="hvr-grow" onclick="detailAud('status')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_status" class="hvr-grow btn-hide" onclick="conUGD('status', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_kebutuhan">
                            <td>Kebutuhan Edukasi</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_kebutuhan" class="hvr-grow" onclick="detailAud('kebutuhan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_kebutuhan" class="hvr-grow btn-hide" onclick="conUGD('kebutuhan', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_diagnosis">
                            <td>Diagnosis Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_diagnosis" class="hvr-grow" onclick="detailAud('diagnosis')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_diagnosis" class="hvr-grow btn-hide" onclick="conUGD('diagnosis', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_asuhan">
                            <td>Rencanan Asuhan Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_asuhan" class="hvr-grow" onclick="detailAud('asuhan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_asuhan" class="hvr-grow btn-hide" onclick="conUGD('asuhan', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_anamnesa">
                            <td>Anamnesa dan Pemeriksaan Fisik</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_anamnesa" class="hvr-grow" onclick="detailAud('anamnesa')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_anamnesa" class="hvr-grow btn-hide" onclick="conUGD('anamnesa', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_asuhan_medis">
                            <td>Rencana Asuhan Medis</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_asuhan_medis" class="hvr-grow" onclick="detailAud('asuhan_medis')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_asuhan_medis" class="hvr-grow btn-hide" onclick="conUGD('asuhan_medis', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_aud_terintegrasi">
                            <td>Perkembangan Kondisi Pasien</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_terintegrasi" class="hvr-grow" onclick="detailCPPT('terintegrasi','asesmen-ugd', 'aud')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                            </td>
                        </tr>
                        <tr id="row_aud_keluar_igd">
                            <td>Kondisi Saat Keluar IGD</td>
                            <td width="20%" align="center">
                                <img id="btn_aud_keluar_igd" class="hvr-grow" onclick="detailAud('keluar_igd')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_keluar_igd" class="hvr-grow btn-hide" onclick="conUGD('keluar_igd', 'asesmen-ugd')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Triase
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
                            <label class="col-md-3">Triase</label>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #d73925"></i>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-left: -20px">
                                    <input onclick="setDataTriase(this.value)" type="radio" value="Merah|#d73925" id="triase1" name="radio_triase" /><label for="triase1" >Merah</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: yellow"></i>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: -20px">
                                    <input onclick="setDataTriase(this.value)" type="radio" value="Kuning|#e08e0b" id="triase2" name="radio_triase" /><label for="triase2">Kuning</label>
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
                                    <input onclick="setDataTriase(this.value)" type="radio" value="Hijau|#008d4c" id="triase3" name="radio_triase" /><label for="triase3">Hijau</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #2b2b2b"></i>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: -20px">
                                    <input onclick="setDataTriase(this.value)" type="radio" value="Hitam|#2b2b2b" id="triase4" name="radio_triase" /><label for="triase4">Hitam</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="set_triase"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_aud_keluhan_utama" class="btn btn-success pull-right" onclick="saveAsesmenUgd('keluhan_utama','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_keluhan_utama" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-pre_hospital">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Data Pre Hospital & Data Primary Survei
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
                            <label class="col-md-3">Cara tiba ke RS</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetUgd(this.value, 'ket_ct')" type="radio" value="Ambulan" id="ct1" name="radio_ct" /><label for="ct1">Ambulan</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input onclick="showKetUgd(this.value, 'ket_ct')" type="radio" value="Kendaraan Pribadi" id="ct2" name="radio_ct" /><label for="ct2">Kendaraan Pribadi</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetUgd(this.value, 'ket_ct')" type="radio" value="Lain-Lain" id="ct3" name="radio_ct" /><label for="ct3">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="ugd-ket_ct">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input class="form-control" id="ket_ct" placeholder="Keterangan Lain">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Rujukan</label>
                            <div class="col-md-9">
                               <input class="form-control" id="rujukan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tindakan yang sudah dilakukan</label>
                            <div class="col-md-9">
                                <textarea rows="4" class="form-control" id="tindakan_yang_sudah"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <label>Tekanan Darah</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_tkn" data-inputmask="'mask': ['999/999']" data-mask="">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>Nadi</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_nadi" type="number">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>RR</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_rr" type="number">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>Suhu</label>
                                <div class="input-group">
                                    <input class="form-control" id="pre_suhu" type="number">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        &#8451
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
                            <label class="col-md-3" style="margin-top: 7px">RR</label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control rr-pasien" id="breathing_rr" type="number">
                                    <div class="input-group-addon">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                    </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">Otot bantu nafas</label>
                                <div class="col-md-6">
                                    <input class="form-control" id="breathing_bantu">
                                </div>
                            </div>
                        </div>
                    <div class="row">
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
                            <label class="col-md-3" style="margin-top: 7px">SpO<small>2</small></label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="breathing_sp">
                                    <div class="input-group-addon">
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
                            <label class="col-md-3" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control nadi-pasien" id="circulation_nadi" type="number">
                                    <div class="input-group-addon">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                                    <input type="radio" value="> 2detik" id="cir_crt2" name="radio_cir_crt" /><label for="cir_crt2">>2detik</label>
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
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_aud_pre_hospital" class="btn btn-success pull-right" onclick="saveAsesmenUgd('pre_hospital','asesmen-ugd')"><i class="fa fa-check"></i> Save
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
                            <div class="col-md-4">
                                <label>Tekanan Darah</label>
                                <div class="input-group">
                                    <input class="form-control tensi-pasien" id="kep_tekanan_darah" data-inputmask="'mask': ['999/999']" data-mask="">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>Nadi</label>
                                <div class="input-group">
                                    <input class="form-control nadi-pasien" id="kep_nadi" type="number">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>RR</label>
                                <div class="input-group">
                                    <input class="form-control rr-pasien" id="kep_rr">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <label style="margin-top: 7px">Suhu</label>
                                <div class="input-group">
                                    <input class="form-control suhu-pasien" id="kep_suhu">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        &#8451
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label style="margin-top: 7px">Berat Badan</label>
                                <div class="input-group">
                                    <input class="form-control berat-pasien" id="kep_bb">
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
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border" style="font-size: 15px; margin-left: -10px"><i class="fa fa-circle-o"></i>Riwayat Alergi</div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat Alergi</label>
                            <div class="col-md-8">
                                <%--<select onchange="audKeterangan(this.value, 'alergi')" id="kep_alergi" class="form-control select2" style="width: 100%; margin-top: 7px">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="Tidak Ada">Tidak Ada</option>--%>
                                    <%--<option value="Obat">Obat</option>--%>
                                    <%--<option value="Makanan">Makanan</option>--%>
                                <%--</select>--%>
                                    <input class="form-control" id="kep_ket_alergi" style="margin-top: 7px">
                            </div>

                        </div>
                        <%--<div class="form-group" style="display: none" id="form_aud_alergi">--%>
                            <%--<div class="col-md-offset-3 col-md-8">--%>
                                <%--<input class="form-control" id="kep_ket_alergi" style="margin-top: 7px">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_aud_keperawatan" class="btn btn-success pull-right" onclick="saveAsesmenUgd('keperawatan','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_keperawatan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-nyeri_anak">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Nyeri
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_nyeri_anak">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_nyeri_anak"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Apakah terdapat keluhan nyeri</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ya11" name="ya1" /><label for="ya11">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ya12" name="ya1" /><label for="ya12">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Face</label>
                            <div class="col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada ekpresi tertentu atau senyuman|0" id="ya21" name="ya2" /><label for="ya21">Tidak ada ekpresi tertentu atau senyuman</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Menyeringai sesekali atau mengerutkan dahi, muram ogah ogahan|1" id="ya22" name="ya2" /><label for="ya22">Menyeringai sesekali atau mengerutkan dahi, muram ogah ogahan</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Dagu gementar dan rahang diketap berulang|2" id="ya23" name="ya2" /><label for="ya23">Dagu gementar dan rahang diketap berulang</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Leg</label>
                            <div class="col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Normal|0" id="ya31" name="ya3" /><label for="ya31">Normal</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Gelisah, resah, tegang|1" id="ya32" name="ya3" /><label for="ya32">Gelisah, resah, tegang</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Menendang atau menarik kaki|2" id="ya33" name="ya3" /><label for="ya33">Menendang atau menarik kaki</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Activity</label>
                            <div class="col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Rebahan dengan tenang, posisi normal, bergerak dengan mudah|0" id="ya41" name="ya4" /><label for="ya41">Rebahan dengan tenang, posisi normal, bergerak dengan mudah</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Menggeliat, maju mundur, tegang|1" id="ya42" name="ya4" /><label for="ya42">Menggeliat, maju mundur, tegang</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Menekuk atau posisi tubuh meringkuk, kaku atau menyentak|2" id="ya43" name="ya4" /><label for="ya43">Menekuk atau posisi tubuh meringkuk, kaku atau menyentak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Cry</label>
                            <div class="col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada tangisan (terjaga/tertidur)|0" id="ya51" name="ya5" /><label for="ya51">Tidak ada tangisan (terjaga/tertidur)</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Mengerang/merengek, gerutuan sesekali|1" id="ya52" name="ya5" /><label for="ya52">Mengerang/merengek, gerutuan sesekali</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Menangis tersedu-sedu, menjerit, terisak-isak, menggerutu berulang-ulang|2" id="ya53" name="ya5" /><label for="ya53">Menangis tersedu-sedu, menjerit, terisak-isak, menggerutu berulang-ulang</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Consolability</label>
                            <div class="col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Senang, santai|0" id="ya61" name="ya6" /><label for="ya61">Senang, santai</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Dapat ditenangkan dengan sentuhan, pelukan atau berbicara, dapat dialihkan|1" id="ya62" name="ya6" /><label for="ya62">Dapat ditenangkan dengan sentuhan, pelukan atau berbicara, dapat dialihkan</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <div class="custom02">
                                    <input type="radio" value="Sulit atau dapat ditenangkan dengan pelukan, sentuhan atau distraksi|2" id="ya63" name="ya6" /><label for="ya63">Sulit atau dapat ditenangkan dengan pelukan, sentuhan atau distraksi</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_nyeri_anak" class="btn btn-success pull-right" onclick="saveAsesmenUgd('nyeri_anak','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_nyeri_anak" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-nutrisional_anak">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_nutrisional_anak">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_nutrisional_anak"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><li style="list-style-type: none">1. Apakah pasien tampak kurus?</li></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|1" id="na11" name="na1" /><label for="na11">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="na12" name="na1" /><label for="na12">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><li style="list-style-type: none">2. Apakah terdapat penurunan berat badan dalam 1 bulan terakhir? (berdasarkan penilaian objektif data berat badan bila ada atau penilaian subjektif orang tua pasien atau untuk bayi < 1 bulan berat badan tidak naik selama 3 bulan berturut-turut)</li></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|1" id="na21" name="na2" /><label for="na21">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="na22" name="na2" /><label for="na22">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><li style="list-style-type: none">3. Apakah terdapat salag satu kondisi tersebut? (diare > 5x/hari dan muntah > 3x/hari dalam seminggu terakhir atau asupan makanan berkurang selama 1 minggu terakhir)</li></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|1" id="na31" name="na3" /><label for="na31">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="na32" name="na3" /><label for="na32">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><li style="list-style-type: none">4. Apakah terdapat penyakit atau keadaan yang mengakibatkan pasien berisiko mengalami malnutrisi? (lihat tabel)</li></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|2" id="na41" name="na4" /><label for="na41">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="na42" name="na4" /><label for="na42">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_nutrisional_anak" class="btn btn-success pull-right" onclick="saveAsesmenUgd('nutrisional_anak','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_nutrisional_anak" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-jatuh_anak">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_jatuh_anak">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_jatuh_anak"></p>
                    </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3">Usia</label>
                                <div class="col-md-9">
                                   <input class="form-control umur-pasien" id="rja1" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <label class="col-md-3">Jenis Kelamin</label>
                                <div class="col-md-9">
                                    <input class="form-control jenis-kelamin" id="rja2" readonly>
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3">Diagnosis</label>
                                <div class="col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Kelainan neurologi|4" id="rja31" name="rja3" /><label for="rja31">Kelainan neurologi</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Ggn Oks (Dypsnoe, Anemia, Sinkop, Dehidrasi, Anoreksia, Sakit kepala)|3" id="rja32" name="rja3" /><label for="rja32">Ggn Oks (Dypsnoe, Anemia, Sinkop, Dehidrasi, Anoreksia, Sakit kepala)</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Kelemahan Fisik|2" id="rja33" name="rja3" /><label for="rja33">Kelemahan Fisik</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Diagnosis Lain|1" id="rja34" name="rja3" /><label for="rja34">Diagnosis Lain</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3">Lingkungan</label>
                                <div class="col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Riwayat jatuh dari tempat tidur saat bayi atau anak|4" id="rja41" name="rja4" /><label for="rja41">Riwayat jatuh dari tempat tidur saat bayi atau anak</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Memakai alat bantu (Box/Mobel)|3" id="rja42" name="rja4" /><label for="rja42">Memakai alat bantu (Box/Mobel)</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Berada ditempat tidur|2" id="rja43" name="rja4" /><label for="rja43">Berada ditempat tidur</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Berada di luar ruang perawatan|1" id="rja44" name="rja4" /><label for="rja44">Berada di luar ruang perawatan</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3">Respon Terhadap Pembedahan/Sedasi/Anastesi</label>
                                <div class="col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="dalam 24 jam|3" id="rja51" name="rja5" /><label for="rja51">dalam 24 jam</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="dalam 48 jam|2" id="rja52" name="rja5" /><label for="rja52">dalam 48 jam</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="lebih dari 48 jam atau tidak menjalani pemebedahan/sedasa/anastesi|1" id="rja53" name="rja5" /><label for="rja53">lebih dari 48 jam atau tidak menjalani pemebedahan/sedasa/anastesi</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3">Penggunaan Obat</label>
                                <div class="col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Obat sedative (Kecuali pasien ICU yang tersedasi) dan paralyzed, Hipnotik, Phenotiazin, Barbiturat, anti depresan, diuretik, narkotik|3" id="rja61" name="rja6" /><label for="rja61">Obat sedative (Kecuali pasien ICU yang tersedasi) dan paralyzed, Hipnotik, Phenotiazin, Barbiturat, anti depresan, diuretik, narkotik</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Salah satu obat diatas|2" id="rja62" name="rja6" /><label for="rja62">Salah satu obat diatas</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <div class="custom02">
                                        <input type="radio" value="Pengobatan lain|1" id="rja63" name="rja6" /><label for="rja63">Pengobatan lain</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_jatuh_anak" class="btn btn-success pull-right" onclick="saveAsesmenUgd('jatuh_anak','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_jatuh_anak" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
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
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Status Fungsional
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
                <button id="save_aud_status" class="btn btn-success pull-right" onclick="saveAsesmenUgd('status','asesmen-ugd')"><i class="fa fa-check"></i> Save
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
                            <label class="col-md-4" style="margin-top: 7px">Bicara</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd(this.value, 'kebutuhan1')" type="radio" value="Normal" id="aud_kebutuhan11" name="radio_aud_kebutuhan1" /><label for="aud_kebutuhan11">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd(this.value, 'kebutuhan1')" type="radio" value="Gangguan" id="aud_kebutuhan12" name="radio_aud_kebutuhan1" /><label for="aud_kebutuhan12">Gangguan</label></div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="ugd-kebutuhan1">
                            <div class="col-md-offset-4 col-md-8">
                                <input class="form-control" id="aud_ket_kebutuhan1">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Perlu Penerjemah</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd(this.value, 'kebutuhan2')" type="radio" value="Tidak" id="aud_kebutuhan21" name="radio_aud_kebutuhan2" /><label for="aud_kebutuhan21">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd(this.value, 'kebutuhan2')" type="radio" value="Ya" id="aud_kebutuhan22" name="radio_aud_kebutuhan2" /><label for="aud_kebutuhan22">Ya</label></div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="ugd-kebutuhan2">
                            <div class="col-md-offset-4 col-md-8">
                                <input class="form-control" id="aud_ket_kebutuhan2">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Hambatan Belajar</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd(this.value, 'kebutuhan3')" type="radio" value="Tidak" id="aud_kebutuhan31" name="radio_aud_kebutuhan3" /><label for="aud_kebutuhan31">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd(this.value, 'kebutuhan3')" type="radio" value="Ya" id="aud_kebutuhan32" name="radio_aud_kebutuhan3" /><label for="aud_kebutuhan32">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div id="ugd-kebutuhan3" style="display: none">
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="pendengaran" id="ket_kebutuhan31" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan31">pendengaran</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="penglihatan" id="ket_kebutuhan32" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan32">penglihatan</label></div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="kognitif" id="ket_kebutuhan33" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan33">kognitif</label></div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="fisik" id="ket_kebutuhan34" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan34">fisik</label></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="budaya" id="ket_kebutuhan35" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan35">budaya</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="agama" id="ket_kebutuhan36" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan36">agama</label></div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="emosi" id="ket_kebutuhan37" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan37">emosi</label></div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="bahasa" id="ket_kebutuhan38" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan38">bahasa</label></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetUgd2(this.value, 'kebutuhan3')" type="radio" value="lainnya" id="ket_kebutuhan39" name="radio_ket_kebutuhan3" /><label for="ket_kebutuhan39">lainnya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                    <div class="row">
                        <div class="form-group" style="display: none" id="ugd-2-kebutuhan3">
                            <div class="col-md-offset-3 col-md-5">
                                <input class="form-control" id="aud_ket_kebutuhan310">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Kebutuhan Pembelajaran</label>
                            <div class="col-md-4" style="font-size: 12px">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input class="kp" type="checkbox" name="kp" id="kp1" value="Proses penyakit">
                                    <label for="kp1"></label> Proses penyakit
                                </div>
                            </div>
                            <div class="col-md-4" >
                                <div class="form-check" style="margin-top: 7px;">
                                    <input class="kp" type="checkbox" name="kp" id="kp2" value="Manajemen nyeri">
                                    <label for="kp2"></label> Manajemen nyeri
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4" >
                                <div class="form-check" style="margin-top: 7px;">
                                    <input class="kp" type="checkbox" name="kp" id="kp3" value="Terapi/obat">
                                    <label for="kp3"></label> Terapi/obat
                                </div>
                            </div>
                            <div class="col-md-4" >
                                <div class="form-check" style="margin-top: 7px;">
                                    <input class="kp" type="checkbox" name="kp" id="kp4" value="Diet/nutrisi">
                                    <label for="kp4"></label> Diet/nutrisi
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4" >
                                <div class="form-check" style="margin-top: 7px;">
                                    <input class="kp" type="checkbox" name="kp" id="kp5" value="Rehabilitas">
                                    <label for="kp5"></label> Rehabilitas
                                </div>
                            </div>
                            <div class="col-md-4" >
                                <div class="form-check" style="margin-top: 7px;">
                                    <input class="kp" type="checkbox" name="kp" id="kp6" value="lainnya" onclick="cekBox(this.id, 'ugd-kebutuhan4')">
                                    <label for="kp6"></label> Lainnya
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="ugd-kebutuhan4">
                            <div class="col-md-offset-4 col-md-5">
                                <input class="form-control" id="aud_ket_kebutuhan47" oninput="$('#kp6').val(this.value)">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_kebutuhan" class="btn btn-success pull-right" onclick="saveAsesmenUgd('kebutuhan','asesmen-ugd')"><i class="fa fa-check"></i> Save
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
                    <div class="col-md-12">
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
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_diagnosis" class="btn btn-success pull-right" onclick="saveAsesmenUgd('diagnosis','asesmen-ugd')"><i class="fa fa-check"></i> Save
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
                    <div class="col-md-12">
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
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_asuhan" class="btn btn-success pull-right" onclick="saveAsesmenUgd('asuhan','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_asuhan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-anamnesa">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Anamnesis dan Pemeriksaan Fisik
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_anamnesa">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_anamnesa"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-12"><b>Anamnesis</b></label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Autoanamnesis</label>
                            <div class="col-md-8">
                                <textarea class="form-control " id="an1" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Alloanamnesis oleh</label>
                            <div class="col-md-8">
                                <select class="form-control" id="an2" onchange="showKetUgd2(this.value, 'ket_an2')">
                                    <option value="Orang tua">Orang tua</option>
                                    <option value="Suami">Suami</option>
                                    <option value="Istri">Istri</option>
                                    <option value="Anak">Anak</option>
                                    <option value="Lainnya">Lainnya</option>
                                </select>
                                <div id="ugd-2-ket_an2" style="display: none">
                                    <input class="form-control" id="ket_an2" placeholder="Keterangan" style="margin-top: 7px;">
                                </div>
                                <textarea class="form-control" id="ket_allow" style="margin-top: 7px" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Pemeriksaan Fisik</b></label>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">GCS</label>
                            <div class="col-md-3">
                                <input class="form-control" id="an3" placeholder="E">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="an4" placeholder="V">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="an5" placeholder="M">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                                <div class="col-md-1">
                                    <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker-op  color-picker pull-left" value="#ff0000">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="text-center">
                                    <canvas class="paint-canvas" style="cursor: pointer" id="area_ugd" onmouseover="paintTtd('area_ugd', true)"></canvas>
                                </div>
                                <canvas style="display: none" id="area_cek"></canvas>
                                <button type="button" class="btn btn-danger" onclick="removePaint('area_ugd')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Kepala</label>
                                <textarea rows="2" class="form-control" id="an6"></textarea>
                            </div>
                            <div class="form-group jarak">
                                <label>Leher</label>
                                <textarea rows="2" class="form-control" id="an7"></textarea>
                            </div>
                            <div class="form-group jarak">
                                <label>Thorax</label>
                                <textarea rows="2" class="form-control" id="an8"></textarea>
                            </div>
                            <div class="form-group jarak">
                                <label>Abdomen dan Pelvis</label>
                                <textarea rows="2" class="form-control" id="an9"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Punggung dan Pinggang</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control" id="an10"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Ekstrimitas</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control" id="an11"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Penunjang Medis</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control penunjang-medis" id="an12"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Kerja</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control diagnosa-pasien" id="an13"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Banding</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control diagnosa-pasien" id="an14"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Sasaran</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control" id="an15"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_anamnesa" class="btn btn-success pull-right" onclick="saveAsesmenUgd('anamnesa','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_anamnesa" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-asuhan_medis">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rencana Asuhan Medis
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_asuhan_medis">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_asuhan_medis"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Hasil Skrining</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input onclick="showKetUgd2(this.value, 'ra1')" type="radio" value="Dapat dilayanani" id="ra11" name="ra1" /><label for="ra11" >Dapat dilayanani</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input onclick="showKetUgd2(this.value, 'ra1')" type="radio" value="Tidak dapat dilayanani" id="ra12" name="ra1" /><label for="ra12" >Tidak dapat dilayanani</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="ugd-2-ra1">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input class="form-control" id="ket_ra1" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ra2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kategori Tindakan</label>
                            <div class="col-md-9">
                                <select class="form-control" id="ra3" onchange="listTindakanPoli(this.value)"></select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tindakan</label>
                            <div class="col-md-9">
                                <select style="width: 100%;" class="form-control select2" id="ra4"></select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diet</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ra5"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_asuhan_medis" class="btn btn-success pull-right" onclick="saveAsesmenUgd('asuhan_medis','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_asuhan_medis" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-terintegrasi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Catatan Terintegrasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_aud_terintegrasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_aud_terintegrasi"></p>
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
                                    <input class="form-control tgl" id="cppt1">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="cppt2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">PPA</label>
                            <div class="col-md-8">
                                <select class="form-control" id="cppt3" style="margin-top: 7px">
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
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3"><b>S</b>ubjective</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="cppt4"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="alert alert-info alert-dismissible">
                        <p id="msg_ews">Early Warning System (EWS) Anak-Anak</p>
                    </div>
                    <input id="tipe_ews" value="anak_anak" type="hidden">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3" ><b>O</b>bjective</label>
                            <div class="col-md-4">
                                <span>Tensi </span> <small>(mmHg)</small>
                                <input class="form-control tensi-pasien" id="cppt5_tensi" data-inputmask="'mask': ['999/999']" data-mask="">
                            </div>
                            <div class="col-md-4">
                                <span>Suhu </span> <small>(&#8451)</small>
                                <input class="form-control suhu-pasien" id="cppt5_suhu" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <span>Apakah Merintih?</span>
                                <select class="form-control" id="merintih" onchange="setRR(this.value, 'cppt5_rr')">
                                    <option value="Tidak">Tidak</option>
                                    <option value="Ya">Ya</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <span>RR </span> <small>(x/menit)</small>
                                <input class="form-control rr-pasien" id="cppt5_rr" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <span>Nadi </span> <small>(x/menit)</small>
                                <input class="form-control nadi-pasien" id="cppt5_nadi" type="number">
                            </div>
                            <div class="col-md-4">
                                <span>SpO2 </span> <small>(x/menit)</small>
                                <input class="form-control" id="spo2" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <span>Alat Bantu O2</span>
                                <select class="form-control" id="o2" onchange="setHideShow(this.value, 'form_al')">
                                    <option value="Tidak">Tidak</option>
                                    <option value="Ya">Ya</option>
                                </select>
                            </div>
                            <div class="col-md-4" style="display: none" id="form_al">
                                <span>Keterangan Alat Bantu</span>
                                <input class="form-control" id="ket_o2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <textarea class="form-control" id="ket_cppt5" placeholder="Keterangan Objective"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-5">
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4">Perilaku</label>
                                    <div class="col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Bermain|0" id="ews31" name="ews3" /><label for="ews31">Bermain</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Tidur|1" id="ews32" name="ews3" /><label for="ews32">Tidur</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Iritabel|2" id="ews33" name="ews3" /><label for="ews33">Iritabel</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Letargi|3" id="ews34" name="ews3" /><label for="ews34">Letargi</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-4">Kardiovaskuler</label>
                                    <div class="col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Merah Jambu|0" id="ews41" name="ews4" /><label for="ews41">Merah Jambu</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Pucat|1" id="ews42" name="ews4" /><label for="ews42">Pucat</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Abu Abu, CRT 4|2" id="ews43" name="ews4" /><label for="ews43">Abu Abu, CRT 4</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <div class="custom02">
                                            <input type="radio" value="Abu Abu, CRT >= 5|3" id="ews44" name="ews4" /><label for="ews44">Abu Abu, CRT >= 5</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-6">Nebulisasi 15 mt / muntah persisten</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="ews61" name="ews6" /><label for="ews61">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|2" id="ews62" name="ews6" /><label for="ews62">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3" ><b>A</b>ssesment</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="cppt6"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3" ><b>P</b>lanning</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="cppt7"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3" >Instruksi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="cppt8"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Petugas</label>
                                <canvas class="paint-canvas-ttd" id="cppt9" width="220"
                                        onmouseover="paintTtd('cppt9')"></canvas>
                                <input class="form-control nama_petugas" id="nama_petugas" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control nip_petugas" id="sip_petugas" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('cppt9')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="cppt10" width="220"
                                        onmouseover="paintTtd('cppt10')"></canvas>
                                <input class="form-control nama_dokter" id="nama_dpjp" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_dpjp" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('cppt10')"><i
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
                <button id="save_aud_terintegrasi" class="btn btn-success pull-right"
                        onclick="saveCPPT('terintegrasi','asesmen-ugd', 'aud')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_aud_terintegrasi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-aud-keluar_igd">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Kondisi Saat keluar IGD
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_aud_keluar_igd">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_aud_keluar_igd"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Keadaan Umum</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ki1"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kesadaran</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ki2"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label>Tekanan Darah</label>
                                <div class="input-group">
                                    <input class="form-control tensi-pasien" id="ki3" data-inputmask="'mask': ['999/999']" data-mask="">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Nadi</label>
                                <div class="input-group">
                                    <input class="form-control nadi-pasien" id="ki4" type="number">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label>RR</label>
                                <div class="input-group">
                                    <input class="form-control rr-pasien" id="ki5" type="number">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Suhu</label>
                                <div class="input-group">
                                    <input class="form-control suhu-pasien" id="ki6" type="number">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        &#8451
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal Keluar</label>
                            <div class="col-md-3">
                                <input class="form-control tgl" id="ki7">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3">Jam Keluar</label>
                            <div class="col-md-3">
                                <input class="form-control jam" id="ki8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Instruksi Tindak Lanjut</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="ki9" style="width: 100%" onchange="showKetIntruksi(this.value)">
                                    <option value="">[Select One]</option>
                                    <option value="Rawat Inap">Rawat Inap</option>
                                    <option value="Rawat Intensif">Rawat Intensif</option>
                                    <option value="Rawat Isolasi">Rawat Isolasi</option>
                                    <option value="Dirujuk">Dirujuk</option>
                                    <option value="Pulang Atas Permintaan Sendiri">Pulang Atas Permintaan Sendiri</option>
                                    <option value="Pulang Atas Persetujuan Dokter">Pulang Atas Persetujuan Dokter</option>
                                    <option value="Pulang Karena Meninggal">Pulang Karena meninggal</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="int-ket1">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <select class="form-control select2" id="ket_ki91" style="width: 100%">
                                    <option value="Preventif">Preventif</option>
                                    <option value="Paliatif">Paliatif</option>
                                    <option value="Kuratif">Kuratif</option>
                                    <option value="Rehabilitatif">Rehabilitatif</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="int-ket2">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input class="form-control" id="ket_ki92" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="int-ket3">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <input class="form-control select2" id="ket_ki93" placeholder="Kontrol Tgl">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" id="ket_ki94" placeholder="Tempat">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-top: 7px">Perawat</label>
                                <canvas width="250" style="margin-left: -1px;" onmouseover="paintTtd('ki10')" class="paint-canvas-ttd" id="ki10"></canvas>
                                <input class="form-control nama_petugas" id="nama_terang_ki10" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control nip_petugas" id="sip_ki10" placeholder="SIP">
                                <button style="margin-top: -5px; margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ki10')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-top: 7px">Dokter</label>
                                <canvas width="250" style="margin-left: -1px;" onmouseover="paintTtd('ki11')" class="paint-canvas-ttd" id="ki11"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_ki11" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_ki11" placeholder="SIP">
                                <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ki11')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_aud_keluar_igd" class="btn btn-success pull-right" onclick="saveAsesmenUgd('keluar_igd','asesmen-ugd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_aud_keluar_igd" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ttd_dpjp">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> TTD DPJP
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ttd_dpjp">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ttd_dpjp"></p>
                </div>
                <div class="modal-body">
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
                                <div class="col-md-offset-2 col-md-8">
                                    <label>TTD DPJP</label>
                                    <canvas class="paint-canvas-ttd del-canvas" id="ttd_edit" width="300"
                                            onmouseover="paintTtd('ttd_edit')"></canvas>
                                    <input class="form-control nama_dokter" id="nama_dpjp_edit" placeholder="Nama Terang">
                                    <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_dpjp_edit" placeholder="SIP">
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('ttd_edit')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ttd_dpjp" class="btn btn-success pull-right"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ttd_dpjp" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>