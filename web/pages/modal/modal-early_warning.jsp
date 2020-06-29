<div class="modal fade" id="modal-ina-early_warning">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Observasi Pediatric Early Warning Score
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_early_warning">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_early_warning"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('early_warning_score')" class="btn btn-success"><i class="fa fa-plus"></i> Early Warning Score
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_early_warning">
                        <tbody>
                        <tr id="row_ina_early_warning_score">
                            <td>Observasi Pediatric Early Warning Score</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_early_warning_score" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('early_warning_score')"
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

<div class="modal fade" id="modal-ina-early_warning_score">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Early Warning Score
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_early_warning_score">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_early_warning_score"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ews1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ews2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Perilaku/Keadaan Umum</label>
                            <div class="col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Alert/Sadar penuh|0" id="ews31" name="ews3" /><label for="ews31">Alert/Sadar penuh</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: -10px">
                                    <input type="radio" value="Somnolen/Cenderung Tidur/Rewal|1" id="ews32" name="ews3" /><label for="ews32">Somnolen/Cenderung Tidur/Rewal</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Iritabel" id="ews33" name="ews3" /><label for="ews33">Iritabel</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Letargi/Respon nyeri menurun|3" id="ews34" name="ews3" /><label for="ews34">Letargi/Respon nyeri menurun</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kardiovaskuler</label>
                            <div class="col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Merah, CRT < 2 detik|0" id="ews41" name="ews4" /><label for="ews41">Merah, CRT < 2 detik</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Pucat, CRT > 3 detik|1" id="ews42" name="ews4" /><label for="ews42">Pucat, CRT > 3 detik</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Sianosis, CRT > 4 detik, Takikardi > 20 di atas normal|2" id="ews43" name="ews4" /><label for="ews43">Sianosis, CRT > 4 detik, Takikardi > 20 di atas normal</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Sianosis, CRT > 5 detik, Takikardi > 30 di atas normal|3" id="ews44" name="ews4" /><label for="ews44">Sianosis, CRT > 5 detik, Takikardi > 30 di atas normal</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Respirasi</label>
                            <div class="col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="Normal sesuai usia, tidak ada retraksi|0" id="ews51" name="ews5" /><label for="ews51">Normal sesuai usia, tidak ada retraksi</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="RR > 10 x/menit di atas parameter normal sesuai usia atau menggunakan otot bantu atau butuh oksigen FiO2 30% (Setara 3 lpm nasal kanul)|1" id="ews52" name="ews5" /><label for="ews52">RR > 10 x/menit di atas parameter normal sesuai usia atau menggunakan otot bantu atau butuh oksigen FiO2 30% (Setara 3 lpm nasal kanul)</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="RR > 10 x/menit di atas parameter normal sesuai usia atau menggunakan otot bantu atau butuh oksigen FiO2 40% (Setara 6 lpm nasal kanul)|2" id="ews53" name="ews5" /><label for="ews53">RR > 10 x/menit di atas parameter normal sesuai usia atau menggunakan otot bantu atau butuh oksigen FiO2 30% (Setara 3 lpm nasal kanul)</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" value="RR > 20 x/menit di atas parameter normal sesuai usia dengan retraksi atau merintih atau butuh oksigen FiO2 50% (Setara 8 lmp simple mask)|4" id="ews54" name="ews5" /><label for="ews54">RR > 20 x/menit di atas parameter normal sesuai usia dengan retraksi atau merintih atau butuh oksigen FiO2 50% (Setara 8 lmp simple mask)</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-8">Pasien 1/4 jam nebulisasi (terus menerus) atau muntah persisten setelah operasi</label>
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
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Pernafasan/RP.</label>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="ews7">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1">Suhu</label>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="ews8">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="ews9" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1" style="margin-top: 7px">SpO2</label>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="ews10" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="ews11" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-4">
                                <label style="margin-left: 8px">TTD Perawat</label>
                                <canvas class="paint-canvas-ttd" id="ews12" width="220"
                                        onmouseover="paintTtd('ews12')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('ews12')"><i
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
                <button id="save_ina_early_warning_score" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('early_warning_score','early_warning')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_early_warning_score" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>