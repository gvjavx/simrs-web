<div class="modal fade" id="modal-sps-spesialis_urologi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Asesmen Urologi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_sps_spesialis_urologi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_urologi"></p>
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
                            <li><a onclick="showModalSPS('anamnesa_spesialis_urologi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Anamnesa</a></li>
                            <li><a onclick="showModalSPS('pemeriksaan_spesialis_urologi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pemeriksaan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr id="row_sps_anamnesa_spesialis_urologi">
                            <td>Anamnesa</td>
                            <td width="20%" align="center"><img id="btn_sps_anamnesa_spesialis_urologi" class="hvr-grow"
                                                                onclick="detailSPS('anamnesa_spesialis_urologi')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_anamnesa_spesialis_urologi" class="hvr-grow btn-hide" onclick="conSPS('anamnesa_spesialis_urologi', 'spesialis_urologi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_pemeriksaan_spesialis_urologi">
                            <td>Pemeriksaan</td>
                            <td width="20%" align="center"><img id="btn_sps_pemeriksaan_spesialis_urologi" class="hvr-grow"
                                                                onclick="detailSPS('pemeriksaan_spesialis_urologi')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pemeriksaan_spesialis_urologi" class="hvr-grow btn-hide" onclick="conSPS('pemeriksaan_spesialis_urologi', 'spesialis_urologi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-anamnesa_spesialis_urologi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Anamnesa
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_anamnesa_spesialis_urologi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_anamnesa_spesialis_urologi"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal Kunjungan</label>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="kut1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Alergi</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi-pasien" id="kut2"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <label>Tensi</label>
                                <input class="form-control tensi-pasien" placeholder="mmHg" id="kut3">
                            </div>
                            <div class="col-md-3">
                                <label>Nadi</label>
                                <input class="form-control nadi-pasien" placeholder="x/menit" type="number" id="kut4">
                            </div>
                            <div class="col-md-3">
                                <label>Suhu </label>
                                <input class="form-control suhu-pasien" placeholder="&#8451" type="number" id="kut5">
                            </div>
                            <div class="col-md-3">
                                <label>RR </label>
                                <input class="form-control rr-pasien" placeholder="x/menit" type="number" id="kut6">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-4">
                            <label>Berat Badan</label>
                                <input class="form-control berat-pasien" id="kut7" placeholder="Kg" type="number">
                            </div>
                            <div class="col-md-4">
                                <label>Tinggi Badan</label>
                                <input class="form-control tinggi-pasien" id="kut8" placeholder="cm" type="number">
                            </div>
                            <div class="col-md-4">
                                <label>Skala Nyeri</label>
                                <input class="form-control alergi" id="kut9" type="number">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kondisi nutrisi</label>
                            <div class="col-md-9">
                                <select class="form-control" id="kut10">
                                    <option value="Obesitas">Obesitas</option>
                                    <option value="Overweigh">Overweigh</option>
                                    <option value="Normoweight">Normoweight</option>
                                    <option value="Underweight">Underweight</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Anamnesis</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control anamnese" id="kut11"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keluhan Utama</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut12"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit Sekarang</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut13"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut14"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Obat obat yang sedang dikonsumsi</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut15"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_anamnesa_spesialis_urologi" onclick="saveSPS('anamnesa_spesialis_urologi', 'spesialis_urologi')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_anamnesa_spesialis_urologi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-pemeriksaan_spesialis_urologi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Pemeriksaan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_pemeriksaan_spesialis_urologi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_pemeriksaan_spesialis_urologi"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-4">Kondisi Umum</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Baik" id="pt11"
                                       name="pt1"/><label for="pt11">Baik</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Lemah" id="pt12"
                                       name="pt1"/><label for="pt12">Lemah</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Pucat" id="pt13"
                                       name="pt1"/><label for="pt13">Pucat</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Sesak" id="pt14"
                                       name="pt1"/><label for="pt14">Sesak</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-4 col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Sianosis" id="pt15"
                                       name="pt1"/><label for="pt15">Sianosis</label>
                            </div>
                        </div>
                        <label class="col-md-offset-1 col-md-1">GCS</label>
                        <div class="col-md-4">
                            <input id="pt2" class="form-control"/>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-12"><b>Kepala</b></label>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Kunjungtivita</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Anemis" id="pt31"
                                       name="pt3"/><label for="pt31">Anemis</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Tidak Anemis" id="pt32"
                                       name="pt3"/><label for="pt32">Tidak Anemis</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Sklera</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Normal" id="pt41"
                                       name="pt4"/><label for="pt41">Normal</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ikterik" id="pt42"
                                       name="pt4"/><label for="pt42">Ikterik</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Sianosis</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Tidak" id="pt51"
                                       name="pt5"/><label for="pt51">Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="custom02">
                                <input type="radio" value="Ya, Napas kuping hidung" id="pt52"
                                       name="pt5"/><label for="pt52">Ya, Napas kuping hidung</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">JVS</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Normal" id="pt61"
                                       name="pt6"/><label for="pt61">Normal</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Meninggi" id="pt62"
                                       name="pt6"/><label for="pt62">Meninggi</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Lainnya</label>
                        <div class="col-md-8">
                            <input class="form-control" id="pt7">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-4">KGB</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="pt8"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-12"><b>Thorak</b></label>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Jantung</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt9')" type="radio" value="Normal" id="pt91"
                                       name="pt9"/><label for="pt91">Normal</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt9')" type="radio" value="Kelainan" id="pt92"
                                       name="pt9"/><label for="pt92">Kelainan</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="sps-pt9">
                        <div class="col-md-offset-4 col-md-8">
                            <input class="form-control" id="ket_pt9">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Paru</label>
                        <div class="col-md-4">
                            <label>Inspetik</label>
                            <select class="form-control" id="pt10" onchange="showKetSPS(this.value, 'pt10')">
                                <option value="Simetris">Simetris</option>
                                <option value="Tidak Simetris">Tidak Simetris</option>
                                <option value="Retraksi">Retraksi</option>
                            </select>
                            <input style="display: none" class="form-control jarak" id="sps-pt10">
                        </div>
                        <div class="col-md-4">
                            <label>Palpasi</label>
                            <select class="form-control" id="pt011">
                                <option value="Normal">Normal</option>
                                <option value="Abnoormal">Abnoormal</option>
                                <option value="Krepitasi">Krepitasi</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-4 col-md-4">
                            <label>Perkusi</label>
                            <select class="form-control" id="pt012" onchange="showKetSPS(this.value, 'pt012')">
                                <option value="Sonor">Sonor</option>
                                <option value="Pekak">Pekak</option>
                                <option value="Lainnya">Lainnya</option>
                            </select>
                            <input style="display: none" class="form-control jarak" id="sps-pt012">
                        </div>
                        <div class="col-md-4">
                            <label>Auskultasi</label>
                            <select class="form-control" id="pt013" onchange="showKetSPS(this.value, 'pt013')">
                                <option value="Vasikuler">Vasikuler</option>
                                <option value="Bronkial">Bronkial</option>
                                <option value="Lainnya">Lainnya</option>
                            </select>
                            <input style="display: none" class="form-control jarak" id="sps-pt013">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-4 col-md-4">
                            <label>Ronchi</label>
                            <input class="form-control" placeholder="...../...." id="pt014">
                        </div>
                        <div class="col-md-4">
                            <label>Wheezing</label>
                            <input class="form-control" placeholder="...../...." id="pt015">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-4">Abdomen</label>
                        <div class="col-md-8">
                            <select class="form-control" id="pt016">
                                <option value="Lemas">Lemas</option>
                                <option value="Kembung">Kembung</option>
                                <option value="Asites">Asites</option>
                            </select>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-4 col-md-4">
                            <label>Nyeri telan</label>
                            <input class="form-control" id="pt017">
                        </div>
                        <div class="col-md-4">
                            <label>Bising usus</label>
                            <input class="form-control" id="pt018">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-4 col-md-4">
                            <label>Heper</label>
                            <select class="form-control" id="pt019" onchange="showKetSPS(this.value, 'pt019')">
                                <option value="Tidak teraba">Tidak teraba</option>
                                <option value="Teraba">Teraba</option>
                            </select>
                            <input style="display: none" class="form-control jarak" id="sps-pt019">
                        </div>
                        <div class="col-md-4">
                            <label>Lien</label>
                            <select class="form-control" id="pt020" onchange="showKetSPS(this.value, 'pt020')">
                                <option value="Tidak teraba">Tidak teraba</option>
                                <option value="Teraba">Teraba</option>
                            </select>
                            <input style="display: none" class="form-control jarak" id="sps-pt020">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-4 col-md-4">
                            <label>Massa</label>
                            <input class="form-control" id="pt021">
                        </div>
                        <div class="col-md-4">
                            <label>Lainnya</label>
                            <input class="form-control" id="pt022">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-12"><b>Ekstremitas Atas</b></label>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Edema</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Tidak" id="pt0231"
                                       name="pt023"/><label for="pt0231">Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pt0232"
                                       name="pt023"/><label for="pt0232">Ya</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Akral</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Hangat" id="pt0241"
                                       name="pt024"/><label for="pt0241">Hangat</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Dingin" id="pt0242"
                                       name="pt024"/><label for="pt0242">Dingin</label>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-12"><b>Hasil Uji Rehap Medik</b></label>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3"><b>VC</b></label>
                        <label class="col-md-2"><b>Fred</b></label>
                        <label class="col-md-2"><b>Actl</b></label>
                        <label class="col-md-2"><b>% Fred</b></label>
                        <label class="col-md-3"><b>Keterangan</b></label>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3"><b>FVC</b></label>
                        <div class="col-md-2">
                            <input class="form-control" id="pt025">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt026">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt027">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control" id="pt028">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3"><b>FEV 1/FVC</b></label>
                        <div class="col-md-2">
                            <input class="form-control" id="pt029">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt030">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt031">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control" id="pt032">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3"><b>PEF</b></label>
                        <div class="col-md-2">
                            <input class="form-control" id="pt033">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt034">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt035">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control" id="pt036">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_pemeriksaan_spesialis_urologi" onclick="saveSPS('pemeriksaan_spesialis_urologi', 'spesialis_urologi')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_pemeriksaan_spesialis_urologi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>