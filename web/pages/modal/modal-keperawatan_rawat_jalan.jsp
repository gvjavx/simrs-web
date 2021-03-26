<div class="modal fade" id="modal-rj-keperawatan_rawat_jalan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Asesmen Keperawatan Rawat Jalan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_rj_keperawatan_rawat_jalan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rj_keperawatan_rawat_jalan"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
                    </div>
                    <div class="btn-group btn-hide">
                        <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button type="button" class="btn btn-success dropdown-toggle"
                                data-toggle="dropdown" style="height: 34px">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="showModalRJ('anamnesa_pemeriksaan_fisik')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Anamnesa & Pemeriksaan Fisik</a></li>
                            <li><a onclick="showModalRJ('psiko_sosial')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Psiko Sosial Spiritual Dan Ekonomi</a></li>
                            <li><a onclick="showModalRJ('skrining_gizi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Skrining Gizi Awal</a></li>
                            <li><a onclick="showModalRJ('resiko_jatuh')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Asesmen Resiko Jatuh</a></li>
                            <li><a onclick="showModalRJ('nyeri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Asesmen Nyeri</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr id="row_rj_anamnesa_pemeriksaan_fisik">
                            <td>Anamnesa & Pemeriksaan Fisik</td>
                            <td width="20%" align="center"><img id="btn_rj_anamnesa_pemeriksaan_fisik" class="hvr-grow"
                                                                onclick="detailRJ('anamnesa_pemeriksaan_fisik')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_anamnesa_pemeriksaan_fisik" class="hvr-grow btn-hide" onclick="conKepRJ('anamnesa_pemeriksaan_fisik', 'keperawatan_rawat_jalan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_rj_psiko_sosial">
                            <td>Psiko Sosial Spiritual & Ekonomi</td>
                            <td width="20%" align="center"><img id="btn_rj_psiko_sosial" class="hvr-grow"
                                                                onclick="detailRJ('psiko_sosial')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_psiko_sosial" class="hvr-grow btn-hide" onclick="conKepRJ('psiko_sosial', 'keperawatan_rawat_jalan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_rj_skrining_gizi">
                            <td>Skrining Gizi Awal</td>
                            <td width="20%" align="center"><img id="btn_rj_skrining_gizi" class="hvr-grow"
                                                                onclick="detailRJ('skrining_gizi')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_skrining_gizi" class="hvr-grow btn-hide" onclick="conKepRJ('skrining_gizi', 'keperawatan_rawat_jalan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_rj_resiko_jatuh">
                            <td>Asesmen Resiko Jatuh</td>
                            <td width="20%" align="center"><img id="btn_rj_resiko_jatuh" class="hvr-grow"
                                                                onclick="detailRJ('resiko_jatuh')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_resiko_jatuh" class="hvr-grow btn-hide" onclick="conKepRJ('resiko_jatuh', 'keperawatan_rawat_jalan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_rj_nyeri">
                            <td>Asesmen Nyeri</td>
                            <td width="20%" align="center"><img id="btn_rj_nyeri" class="hvr-grow"
                                                                onclick="detailRJ('nyeri')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_nyeri" class="hvr-grow btn-hide" onclick="conKepRJ('nyeri', 'keperawatan_rawat_jalan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-rj-anamnesa_pemeriksaan_fisik">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Anamnesa & Pemeriksaan Fisik
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rj_anamnesa_pemeriksaan_fisik">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_rj_anamnesa_pemeriksaan_fisik"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="af1">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="af2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Asal Pasien</label>
                            <div class="col-md-9">
                                <input class="form-control nama_ruangan" id="af3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-12"><b>Anamnese</b></label>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penggunaan Obat</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'af4')" value="Tidak Ada" id="af41" name="af4" /><label for="af41">Tidak Ada</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'af4')" value="Ada" id="af42" name="af4" /><label for="af42">Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form_af4">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input class="form-control" id="ket_af4" onchange="$('#af42').val('Ada, '+this.value)" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Alergi</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi-pasien" id="af5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-12"><b>Pemeriksaan Fisik</b></label>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keadaan Umum</label>
                            <div class="col-md-4">
                                <input type="number" class="form-control tinggi-pasien" id="af6" placeholder="Tingi Badan (Cm)">
                            </div>
                            <div class="col-md-4">
                                <input type="number" class="form-control berat-pasien" id="af7" placeholder="Berat Badan (Kg)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12">Tanda Vital</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <span>Tensi (mmHg)</span>
                                <input class="form-control tensi-pasien" id="af8" data-inputmask="'mask': ['999/999']" data-mask="">
                            </div>
                            <div class="col-md-3">
                                <span>Suhu (C)</span>
                                <input type="number" class="form-control suhu-pasien" id="af9">
                            </div>
                            <div class="col-md-3">
                                <span>RR (x/menit)</span>
                                <input type="number" class="form-control rr-pasien" id="af10">
                            </div>
                            <div class="col-md-3">
                                <span>Nadi (x/menit)</span>
                                <input type="number" class="form-control nadi-pasien" id="af11">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Hambatan</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'af12')" value="Bisu" id="af121" name="af12" /><label for="af121">Bisu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'af12')" value="Tuli" id="af122" name="af12" /><label for="af122">Tuli</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'af12')" value="Lain-Lain" id="af123" name="af12" /><label for="af123">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'af12')" value="Tidak Ada" id="af124" name="af12" /><label for="af124">Tidak Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="form_af12">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input class="form-control" id="ket_af12" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_rj_anamnesa_pemeriksaan_fisik" onclick="saveRJ('anamnesa_pemeriksaan_fisik', 'keperawatan_rawat_jalan')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_rj_anamnesa_pemeriksaan_fisik"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rj-psiko_sosial">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Psiko Sosial Spiritual & Ekonomi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rj_psiko_sosial">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_rj_psiko_sosial"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-6">Persepsi Klien terhadap penyakitnya</label>
                        <div class="col-md-6">
                            <select class="form-control" id="ps1">
                                <option value="">[Select One]</option>
                                <option value="Cobaan Tuhan">Cobaan Tuhan</option>
                                <option value="Hukuman">Hukuman</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-6">Ekspresi klien terhadap penyakitnya</label>
                        <div class="col-md-6">
                            <select class="form-control" id="ps2">
                                <option value="">[Select One]</option>
                                <option value="Murung/Diam">Murung/Diam</option>
                                <option value="Gelisah">Gelisah</option>
                                <option value="Marah/Menangis">Marah/Menangis</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-6">Gangguan konsep diri</label>
                        <div class="col-md-6">
                            <select class="form-control" id="ps3">
                                <option value="">[Select One]</option>
                                <option value="Ya">Ya</option>
                                <option value="Tidak">Tidak</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-6">Reaksi anak interaksi (khusus pasien anak)</label>
                        <div class="col-md-6">
                            <select class="form-control" id="ps4">
                                <option value="">[Select One]</option>
                                <option value="Kooperatif">Kooperatif</option>
                                <option value="Kooperatif">Tidak Kooperatif</option>
                                <option value="Curiga">Curiga</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-6">Pendidikan</label>
                        <div class="col-md-6">
                            <input class="form-control" id="ps5">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-6">Pekerjaan</label>
                        <div class="col-md-6">
                            <input class="form-control" id="ps6">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_rj_psiko_sosial" onclick="saveRJ('psiko_sosial', 'keperawatan_rawat_jalan')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_rj_psiko_sosial"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rj-skrining_gizi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Skrining Gizi Awal
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rj_skrining_gizi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_rj_skrining_gizi"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-8">1. Apakah pasien mengalami penuruanan / peningkatan BB yang tidak di inginkan dalam 6 bulan terakhir ?</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|2" id="gz11" name="gz1" /><label for="gz11">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="gz12" name="gz1" /><label for="gz12">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-8">2. Apakah asupan makan berkurang karena tidak nafsu makan ?</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|1" id="gz21" name="gz2" /><label for="gz21">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="gz22" name="gz2" /><label for="gz22">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-8">3. Pasien dengan diagnosa khusus / kondisi khusus ?</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya|2" id="gz31" name="gz3" /><label for="gz31">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak|0" id="gz32" name="gz3" /><label for="gz32">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Penyakit : </label>
                            <div class="col-md-9">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'gz4')" value="DM/Kemoterapi" id="gz41" name="gz4" /><label for="gz41">DM/Kemoterapi</label>
                                </div>
                            </div>
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'gz4')" value="Hemodialisa/Imunitas Menurun" id="gz42" name="gz4" /><label for="gz42">Hemodialisa/Imunitas Menurun</label>
                                </div>
                            </div>
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRJ(this.value, 'gz4')" value="Lain-Lain" id="gz43" name="gz4" /><label for="gz43">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form_gz4">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input class="form-control" id="ket_gz4" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_rj_skrining_gizi" onclick="saveRJ('skrining_gizi', 'keperawatan_rawat_jalan')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_rj_skrining_gizi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rj-resiko_jatuh">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Resiko Jatuh
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rj_resiko_jatuh">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_rj_resiko_jatuh"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ps01">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ps02">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <input id="jenis_resiko" type="hidden">
                    <div id="set_resiko_jatuh"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_rj_resiko_jatuh" onclick="saveRJ('resiko_jatuh', 'keperawatan_rawat_jalan')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_rj_resiko_jatuh"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-rj-nyeri">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Asemen Nyeri
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rj_nyeri">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_rj_nyeri"></p>
                    </div>
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
                            <div class="col-md-6">
                                <label>TTD Perawat</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('ttdPerawat')" class="paint-canvas-ttd" id="ttdPerawat"></canvas>
                                <input id="nama_terang" class="form-control nama_petugas" placeholder="Nama Terang">
                                <input id="nip_perawat" class="form-control nip_petugas" placeholder="NIP">
                                <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ttdPerawat')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_rj_nyeri" onclick="saveRJ('nyeri', 'keperawatan_rawat_jalan')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_rj_nyeri"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
