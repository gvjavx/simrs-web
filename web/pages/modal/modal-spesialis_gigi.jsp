<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="modal fade" id="modal-sps-spesialis_gigi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Dan Rencana Perawatan Gigi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_sps_spesialis_gigi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_gigi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
                    </div>
                        <button type="button" onclick="showModalSPS('asesmen_gigi')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen Gigi Awal
                        </button>
                    <button type="button" onclick="showModalSPS('asesmen_gigi_lanjut')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen Gigi Tindak Lanjut
                    </button>
                        <button type="button" onclick="showModalSPS('rencana_gigi_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Rencana Perawatan Gigi
                        </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_sps_spesialis_gigi">
                        <tbody>
                        <tr id="row_sps_asesmen_gigi">
                            <td>Asesmen Gigi Awal</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_asesmen_gigi" class="hvr-grow"
                                     onclick="detailSPS('asesmen_gigi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_asesmen_gigi" class="hvr-grow btn-hide" onclick="conSPS('asesmen_gigi', 'spesialis_gigi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_asesmen_gigi_lanjut">
                            <td>Asesmen Gigi Tindak Lanjut</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_asesmen_gigi_lanjut" class="hvr-grow"
                                     onclick="detailSPS('asesmen_gigi_lanjut')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_asesmen_gigi_lanjut" class="hvr-grow btn-hide" onclick="conSPS('asesmen_gigi_lanjut', 'spesialis_gigi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_rencana_gigi_pasien">
                            <td>Perawatan Gigi</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_rencana_gigi_pasien" class="hvr-grow"
                                     onclick="detailSPS('rencana_gigi_pasien')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_rencana_gigi_pasien" class="hvr-grow btn-hide" onclick="conSPS('rencana_gigi_pasien', 'spesialis_gigi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-asesmen_gigi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Gigi Awal
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_asesmen_gigi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_asesmen_gigi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-bordered" style="font-size: 12px">
                                <tbody>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_1">11 [51]</td>
                                    <td><input class="form-control" id="input_kiri_1"></td>
                                    <td><input class="form-control" id="input_kanan_1"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_1">[61] 21</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_2">12 [52]</td>
                                    <td><input class="form-control" id="input_kiri_2"></td>
                                    <td><input class="form-control" id="input_kanan_2"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_2">[62] 22</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_3">13 [53]</td>
                                    <td><input class="form-control" id="input_kiri_3"></td>
                                    <td><input class="form-control" id="input_kanan_3"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_3">[63] 23</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_4">14 [54]</td>
                                    <td><input class="form-control" id="input_kiri_4"></td>
                                    <td><input class="form-control" id="input_kanan_4"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_4">[64] 24</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_5">15 [55]</td>
                                    <td><input class="form-control" id="input_kiri_5"></td>
                                    <td><input class="form-control" id="input_kanan_5"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_5">[65] 25</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_6">16</td>
                                    <td><input class="form-control" id="input_kiri_6"></td>
                                    <td><input class="form-control" id="input_kanan_6"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_6">26</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_7">17</td>
                                    <td><input class="form-control" id="input_kiri_7"></td>
                                    <td><input class="form-control" id="input_kanan_7"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_7">27</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_8">18</td>
                                    <td><input class="form-control" id="input_kiri_8"></td>
                                    <td><input class="form-control" id="input_kanan_8"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_8">28</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row jarak text-center">
                        <div class="col-md-1">
                            <input type="color" class="js-color-picker-op  color-picker pull-left" value="#ff0000">
                        </div>
                        <div class="col-md-11">
                            <div class="text-center">
                                <canvas class="paint-canvas" style="cursor: pointer" id="area_gigi1" onmouseover="paintTtd('area_gigi1', true)"></canvas>
                            </div>
                            <canvas style="display: none" id="area_cek"></canvas>
                            <button style="margin-top: -5px; margin-left: 8px" type="button" class="btn btn-danger" onclick="removePaint('area_gigi1')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <textarea rows="3" id="keterangan_gambar" class="form-control" placeholder="Keterangan Gambar"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <table class="table table-bordered" style="font-size: 12px">
                                <tbody>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_9">48</td>
                                    <td><input class="form-control" id="input_kiri_9"></td>
                                    <td><input class="form-control" id="input_kanan_9"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_9">38</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_10">47</td>
                                    <td><input class="form-control" id="input_kiri_10"></td>
                                    <td><input class="form-control" id="input_kanan_10"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_10">37</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_11">46</td>
                                    <td><input class="form-control" id="input_kiri_11"></td>
                                    <td><input class="form-control" id="input_kanan_11"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_11">36</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_12">45 [85]</td>
                                    <td><input class="form-control" id="input_kiri_12"></td>
                                    <td><input class="form-control" id="input_kanan_12"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_12">[75] 35</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_13">44 [84]</td>
                                    <td><input class="form-control" id="input_kiri_13"></td>
                                    <td><input class="form-control" id="input_kanan_13"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_13">[74] 34</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_14">43 [83]</td>
                                    <td><input class="form-control" id="input_kiri_14"></td>
                                    <td><input class="form-control" id="input_kanan_14"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_14">[73] 33</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_15">42 [82]</td>
                                    <td><input class="form-control" id="input_kiri_15"></td>
                                    <td><input class="form-control" id="input_kanan_15"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_15">[72] 32</td>
                                </tr>
                                <tr class="label_gigi">
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kiri_16">41 [81]</td>
                                    <td><input class="form-control" id="input_kiri_16"></td>
                                    <td><input class="form-control" id="input_kanan_16"></td>
                                    <td align="center" width="15%" style="vertical-align: middle" id="label_kanan_16">[71] 31</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Occlusi</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Normal Bite" id="ag11" name="ag1" /><label for="ag11">Normal Bite</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Croos Bite" id="ag12" name="ag1" /><label for="ag12">Croos Bite</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Step Bite" id="ag13" name="ag1" /><label for="ag13">Step Bite</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Torus Palatinus</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Ada" id="ag21" name="ag2" /><label for="ag21">Tidak Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Kecil" id="ag22" name="ag2" /><label for="ag22">Kecil</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Sedang" id="ag23" name="ag2" /><label for="ag23">Sedang</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Besar" id="ag24" name="ag2" /><label for="ag24">Besar</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Multiple" id="ag25" name="ag2" /><label for="ag25">Multiple</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Torus Mandibularis</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Ada" id="ag31" name="ag3" /><label for="ag31">Tidak Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Sisi Kiri" id="ag32" name="ag3" /><label for="ag32">Sisi Kiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Sisi Kanan" id="ag33" name="ag3" /><label for="ag33">Sisi Kanan</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Kedua Sisi" id="ag34" name="ag3" /><label for="ag34">Kedua Sisi</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Palatum</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Dalam" id="ag41" name="ag4" /><label for="ag41">Dalam</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Sedang" id="ag42" name="ag4" /><label for="ag42">Sedang</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Rendah" id="ag43" name="ag4" /><label for="ag43">Rendah</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diastema</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetSPS(this.value, 'diastema')" type="radio" value="Tidak Ada" id="ag51" name="ag5" /><label for="ag51">Tidak Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetSPS(this.value, 'diastema')" type="radio" value="Ada" id="ag52" name="ag5" /><label for="ag52">Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="sps-diastema">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-6">
                                <input class="form-control" oninput="$('#ag52').val(''); $('#ag52').val('Ada, '+this.value);">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Gigi Anomali</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetSPS(this.value, 'anomali')" type="radio" value="Tidak Ada" id="ag61" name="ag6" /><label for="ag61">Tidak Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetSPS(this.value, 'anomali')" type="radio" value="Ada" id="ag62" name="ag6" /><label for="ag62">Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="sps-anomali">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-6">
                                <input class="form-control" oninput="$('#ag62').val(''); $('#ag62').val('Ada, '+this.value);">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Lain-Lain</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ag_lain_lain"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-3">
                                <span>D</span>
                                <input class="form-control" id="ag_d">
                            </div>
                            <div class="col-md-3">
                                <span>M</span>
                                <input class="form-control" id="ag_m">
                            </div>
                            <div class="col-md-3">
                                <span>F</span>
                                <input class="form-control" id="ag_f">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">Jumlah photo yang diambil</label>
                            <div class="col-md-3">
                                <input class="form-control" id="ag_p1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-6">Jumlah rontgen photo yang diambil</label>
                            <div class="col-md-3">
                                <input class="form-control" id="ag_p2">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Pemeriksa</label>
                                <canvas class="paint-canvas-ttd" id="ttd_ag9" width="220"
                                        onmouseover="paintTtd('ttd_ag9')"></canvas>
                                <input class="form-control nama_petugas" id="ttd_nama_terang_petugas" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control nip_petugas" id="ttd_sip_petugas" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_ag9')"><i
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
                <button id="save_sps_asesmen_gigi" class="btn btn-success pull-right"
                        onclick="saveSPS('asesmen_gigi','spesialis_gigi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_asesmen_gigi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-asesmen_gigi_lanjut">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Gigi Tindak Lanjut
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_asesmen_gigi_lanjut">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_asesmen_gigi_lanjut"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diastema</label>
                            <div class="col-md-9">
                                <input id="ag4" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Lain-Lain</label>
                            <div class="col-md-9">
                                <input id="ag5" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <input id="ag6" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <textarea rows="2" id="ag7" class="form-control resep-pasien"/></div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Intruksi Tindak Lanjut</label>
                        <div class="col-md-9">
                            <select class="form-control select2" id="intruksi_asesmen_gigi_lanjut" style="width: 100%" onchange="showKetIntruksi(this.value)">
                            </select>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="int-ket1">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <select class="form-control select2" id="ket_asesmen_gigi_lanjut" style="width: 100%">
                                    <option value="">-</option>
                                    <option value="Preventif">Preventif</option>
                                    <option value="Paliatif">Paliatif</option>
                                    <option value="Kuratif">Kuratif</option>
                                    <option value="Rehabilitatif">Rehabilitatif</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="int-ket2">
                        <div class="col-md-3">
                            <input class="form-control ptr-tgl int_tanggal_kontrol" placeholder="Tanggal" style="margin-top: 7px" id="int_tgl_kontrol_0">
                        </div>
                        <div class="col-md-4">
                            <select class="form-control select2 int_pelayanan_kontrol" id="int_pelayanan_0" onchange="setIntDokter(this.value, 'int_dokter_0')"></select>
                        </div>
                        <div class="col-md-4">
                            <select class="form-control select2 int_dokter_kontrol" id="int_dokter_0"></select>
                        </div>
                        <div class="col-md-1">
                            <button onclick="addKontrolUlang('int')" style="margin-left: -20px; margin-top: 7px" class="btn btn-success"><i class="fa fa-plus"></i></button>
                        </div>
                    </div>
                    <div id="set_kontrol_int"></div>
                    <div class="row jarak" style="display: none" id="int-ket3">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input class="form-control" id="int_ket_rs_lain" placeholder="Rumah Sakit">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="int-ket4">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <select class="form-control select2" id="int_ket_selesai"></select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Pemeriksa</label>
                                <canvas class="paint-canvas-ttd" id="ag9" width="220"
                                        onmouseover="paintTtd('ag9')"></canvas>
                                <input class="form-control nama_petugas" id="nama_terang_petugas" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control nip_petugas" id="sip_petugas" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ag9')"><i
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
                <button id="save_sps_asesmen_gigi_lanjut" class="btn btn-success pull-right"
                        onclick="saveSPS('asesmen_gigi_lanjut','spesialis_gigi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_asesmen_gigi_lanjut" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-rencana_gigi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rencana Perawatan Gigi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_rencana_gigi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_rencana_gigi_pasien"></p>
                </div>
                <div class="box-body">
                    <%--<div class="form-group" style="padding-top: 10px; padding-bottom: 10px">--%>
                        <%--<div class="col-md-1">--%>
                            <%--<input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker-op  color-picker pull-left">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row jarak">--%>
                        <%--<div class="col-md-12">--%>
                            <%--<div class="text-center">--%>
                                <%--<canvas class="paint-canvas" style="cursor: pointer" id="area_gigi2" onmouseover="paintTtd('area_gigi2', true)"></canvas>--%>
                            <%--</div>--%>
                            <%--<button style="margin-top: -5px; margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('area_gigi2')"><i class="fa fa-trash"></i> Clear--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr class="garis">--%>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Golongan Darah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg1">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg2" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Penyakit Jantung</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg31" name="gg3" /><label for="gg31">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg32" name="gg3" /><label for="gg32">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diabetes</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg41" name="gg4" /><label for="gg41">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg42" name="gg4" /><label for="gg42">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Haemopilia</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg51" name="gg5" /><label for="gg51">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg52" name="gg5" /><label for="gg52">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Hepatitis</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg61" name="gg6" /><label for="gg61">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg62" name="gg6" /><label for="gg62">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Gastritis</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg71" name="gg7" /><label for="gg71">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg72" name="gg7" /><label for="gg72">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Penyakit Lainnya</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetSPS(this.value, 'gg8')" type="radio" value="Tidak ada" id="gg81" name="gg8" /><label for="gg81">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetSPS(this.value, 'gg8')" type="radio" value="Ada" id="gg82" name="gg8" /><label for="gg82">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="sps-gg8">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="col-md-10">
                                    <input class="form-control" id="ket_gg8">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Alergi Obat</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetSPS(this.value, 'gg9')" type="radio" value="Tidak ada" id="gg91" name="gg9" /><label for="gg91">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetSPS(this.value, 'gg9')" type="radio" value="Ada" id="gg92" name="gg9" /><label for="gg92">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="sps-gg9">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="col-md-10">
                                    <input class="form-control" id="ket_gg9">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Alergi Makanan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg10">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Obat yang sedang dikonsumsi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg011" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal pencatatan data</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="gg012" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD</label>
                                <canvas class="paint-canvas-ttd" id="gg013" width="220"
                                        onmouseover="paintTtd('gg013')"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_gg013" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_gg013" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('gg013')"><i
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
                <button id="save_sps_rencana_gigi_pasien" class="btn btn-success pull-right"
                        onclick="saveSPS('rencana_gigi_pasien','spesialis_gigi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_rencana_gigi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>