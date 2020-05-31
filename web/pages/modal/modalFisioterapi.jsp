<div class="modal fade" id="modal-pengkajian-fisioterapi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Pengkajian Fisioterapi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_pengkajian_pengkajian">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_pengkajian_pengkajian"></p>
                    </div>
                    <button onclick="addFisioterapi('keadaan_umum')" class="btn btn-success"><i class="fa fa-plus"></i>
                        Keadaan Umum
                    </button>
                    <button onclick="addFisioterapi('psikologis')" class="btn btn-success"><i class="fa fa-plus"></i>
                        Riwayat Psikologis
                    </button>
                    <button onclick="addFisioterapi('nyeri')" class="btn btn-success"><i class="fa fa-plus"></i>
                        Tingakat Nyeri
                    </button>
                    <button onclick="addFisioterapi('jatuh')" class="btn btn-success"><i class="fa fa-plus"></i> Resiko
                        Jatuh
                    </button>
                    <button onclick="addFisioterapi('pengkajian')" class="btn btn-success"><i class="fa fa-plus"></i>
                        Pengkajian Fisioterapi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_fisio">
                        <tbody>
                        <tr id="row_keadaan_umum">
                            <td>Keadaan Umum</td>
                            <td width="20%" align="center"><img id="btn_keadaan_umum" class="hvr-grow"
                                                                onclick="detailFisio('keadaan_umum')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_psikologis">
                            <td>Riwayat Psikologis</td>
                            <td width="20%" align="center"><img id="btn_psikologis" class="hvr-grow"
                                                                onclick="detailFisio('psikologis')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_nyeri">
                            <td>Penilaian Tingkat Nyeri</td>
                            <td width="20%" align="center"><img id="btn_nyeri" class="hvr-grow"
                                                                onclick="detailFisio('nyeri')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_jatuh">
                            <td>Penilaian Resiko Jatuh</td>
                            <td width="20%" align="center"><img id="btn_jatuh" class="hvr-grow"
                                                                onclick="detailFisio('jatuh')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_pengkajian">
                            <td>Pengkajian Fisioterapi</td>
                            <td width="20%" align="center"><img id="btn_pengkajian" class="hvr-grow"
                                                                onclick="detailFisio('pengkajian')"
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

<div class="modal fade" id="modal-fisio-keadaan_umum">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Keadaan Umum
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_keadaan_umum">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_keadaan_umum"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Tekanan Darah</label>
                                <div class="input-group">
                                    <input class="form-control" type="number" id="f_darah">
                                    <div class="input-group-addon" style="width: 30%">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Frekuensi Nadi</label>
                                <div class="input-group">
                                    <input class="form-control" type="number" id="f_nadi">
                                    <div class="input-group-addon" style="width: 30%">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Frekuensi Nafas</label>
                                <div class="input-group">
                                    <input class="form-control" type="number" id="f_nafas">
                                    <div class="input-group-addon" style="width: 30%">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Suhu</label>
                                <div class="input-group">
                                    <input class="form-control" type="number" id="f_suhu">
                                    <div class="input-group-addon" style="width: 30%">
                                        &#8451
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Berat Badan</label>
                                <div class="input-group">
                                    <input class="form-control" type="number" id="f_berat">
                                    <div class="input-group-addon" style="width: 30%">
                                        kg
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Tinggi Badan</label>
                                <div class="input-group">
                                    <input class="form-control" type="number" id="f_tinggi">
                                    <div class="input-group-addon" style="width: 30%">
                                        cm
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Alat Bantu</label>
                                <input class="form-control" id="f_alat">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Prothesa</label>
                                <input class="form-control" id="f_prothesa">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">Cacat Tubuh</label>
                                <input class="form-control" id="f_cacat">
                            </div>
                            <div class="form-group">
                                <label style="margin-top: 7px">ADL</label>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_adl" id="adl1" value="Mandiri">
                                    <label for="adl1"></label> Mandiri
                                </div>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_adl" id="adl2" value="Dibantu">
                                    <label for="adl2"></label> Dibantu
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_keadaan_umum" onclick="saveFisio('keadaan_umum')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_keadaan_umum"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-fisio-psikologis">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Riwayat Psikologis
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_psikologis">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_psikologis"></p>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_psikologis" id="psiko1" value="Tidak Semangat">
                            <label for="psiko1"></label> Tidak Semangat
                        </div>
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_psikologis" id="psiko2" value="Depresi">
                            <label for="psiko2"></label> Depresi
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_psikologis" id="psiko4" value="Cepat Lelah">
                            <label for="psiko4"></label> Cepat Lelah
                        </div>
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_psikologis" id="psiko5" value="Sulit Konsentrasi">
                            <label for="psiko5"></label> Sulit Konsentrasi
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_psikologis" id="psiko3" value="Sulit Berbicara">
                            <label for="psiko3"></label> Sulit Berbicara
                        </div>
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_psikologis" id="psiko7" value="Tidak Kooperatif">
                            <label for="psiko7"></label> Tidak Kooperatif
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-check" style="margin-top: 7px;">
                            <input type="checkbox" name="cek_psikologis" id="psiko6" value="Menggunakan Obat Penenang">
                            <label for="psiko6"></label> Menggunakan Obat Penenang
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_psikologis" class="btn btn-success pull-right" onclick="saveFisio('psikologis')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_psikologis" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-fisio-nyeri">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Penilaian Tingkat Nyeri
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_nyeri">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_nyeri"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-7" style="margin-top: 7px">Apakah terdapat keluhan nyeri</label>
                                <div class="col-md-5">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input onclick="cekNyeri(this.value, 'y_inten')" type="radio" value="Tidak" id="nyeri2" name="radio_nyeri_keluhan" /><label for="nyeri2">Tidak</label>
                                        <input onclick="cekNyeri(this.value, 'y_inten')" type="radio" value="Ya" id="nyeri1" name="radio_nyeri_keluhan" /><label for="nyeri1">Ya</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-7" style="margin-top: 7px">Lokasi</label>
                                <div class="col-md-5">
                                    <input class="form-control" style="margin-top: 7px;" id="y_lokasi">
                                </div>
                            </div>
                            <%--<img src="<%= request.getContextPath() %>/pages/images/nyeri-1.jpg" style="width: 100%; margin-top: 20px">--%>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">Jenis</label>
                                <div class="col-md-9">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Akut" id="nyeri3" name="radio_nyeri_jenis" /><label for="nyeri3">Akut</label>
                                        <input type="radio" value="Kronis" id="nyeri4" name="radio_nyeri_jenis" /><label for="nyeri4">Kronis</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">Intensitas</label>
                                <div class="col-md-8">
                                    <input class="form-control" style="margin-top: 7px;" id="y_inten">
                                </div>
                            </div>
                            <%--<br>--%>
                            <%--<img src="<%= request.getContextPath() %>/pages/images/nyeri-2.jpg" style="width: 100%; margin-top: 30px">--%>
                        </div>
                    </div>
                    <input id="temp_scala" type="hidden">
                    <canvas id="choice_emoji" style="display: none"></canvas>
                    <hr class="garis">
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
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">
                            <img src="<%= request.getContextPath() %>/pages/images/scala-nyeri-number.jpg" style="width: 100%;">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_nyeri" class="btn btn-success pull-right" onclick="saveFisio('nyeri')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_nyeri" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-fisio-jatuh">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Penilaian Resiko Jatuh
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_jatuh">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_jatuh"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Metode</label>
                            <div class="col-md-8">
                                <select class="form-control select2" style="width: 100%;" id="j_metode">
                                    <option value="Morse">Morse</option>
                                    <option value="Hampty">Hampty</option>
                                    <option value="Dumpty">Dumpty</option>
                                    <option value="Ontariosydney">Ontariosydney</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Asesment Fisioterapi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3" style="margin-top: 7px" id="j_asesment"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Rencana Asuhan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3" style="margin-top: 7px" id="j_rencana"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Kategori</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="j_kategori">
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Skor</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="j_skor">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_jatuh" class="btn btn-success pull-right" onclick="saveFisio('jatuh')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_jatuh" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                    class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-fisio-pengkajian">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Pengkajian Fisioterapi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_pengkajian">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_pengkajian"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Keluhan Utama</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="p_keluhan"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Riwayat Penyakit Sekarang</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="p_penyakit_sekarang"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="p_penyakit_dahulu"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Pemeriksaan Fisik</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="p_fisik"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Pemeriksaan Penunjang</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="p_penunjang"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Assesment</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="p_assesment"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Terapi/tindakan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="p_terapi"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_pengkajian" class="btn btn-success pull-right" onclick="saveFisio('pengkajian')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_pengkajian" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-monitoring-kunjungan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Monitoring Kunjungan Fisioterapi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_monitoring_pengkajian">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_monitoring_pengkajian"></p>
                    </div>
                    <button onclick="addMonitoringFisio()" class="btn btn-success btn-hide"><i class="fa fa-plus"></i>
                        Monitoring
                    </button>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" style="font-size: 12px">
                        <thead>
                        <td>Tanggal</td>
                        <td>Tindakan</td>
                        <td>Keterangan</td>
                        <td>Fisiterapi</td>
                        </thead>
                        <tbody id="body_monitoring_fisioterapi"></tbody>
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

<div class="modal fade" id="modal-add-monitoring">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Tambah Monitoring Fisioterapi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mon_fisio">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mon_fisio"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px" >
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="mon_tanggal">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Tindakan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="mon_tindakan"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Keterangan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="mon_keterangan"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px" class="col-md-3">Fisioterapis</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="2" style="margin-top: 7px" id="mon_fisio"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mon_pengkajian" class="btn btn-success pull-right" onclick="saveMonitoringFisio()"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mon_pengkajian" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>