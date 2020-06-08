<div class="modal fade" id="modal-sps-spesialis_neurologi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Asesmen Neurologi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_sps_spesialis_neurologi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_neurologi"></p>
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
                            <li><a onclick="showModalSPS('anamnesa_neurologi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Anamnesa</a></li>
                            <li><a onclick="showModalSPS('pemeriksaan_neurologi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pemeriksaan</a></li>
                            <li><a onclick="showModalSPS('edukasi_neurologi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Edukasi</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr id="row_sps_anamnesa_neurologi">
                            <td>Anamnesa</td>
                            <td width="20%" align="center"><img id="btn_sps_anamnesa_neurologi" class="hvr-grow"
                                                                onclick="detailSPS('anamnesa_neurologi')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_sps_pemeriksaan_neurologi">
                            <td>Pemeriksaan</td>
                            <td width="20%" align="center"><img id="btn_sps_pemeriksaan_neurologi" class="hvr-grow"
                                                                onclick="detailSPS('pemeriksaan_neurologi')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_sps_edukasi_neurologi">
                            <td>Edukasi</td>
                            <td width="20%" align="center"><img id="btn_sps_edukasi_neurologi" class="hvr-grow"
                                                                onclick="detailSPS('edukasi_neurologi')"
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

<div class="modal fade" id="modal-sps-anamnesa_neurologi">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_anamnesa_neurologi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_anamnesa_neurologi"></p>
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
                            <label class="col-md-3">Anamnese</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control anamnese" id="kut2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keluhan Utama</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut4"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit Keluarga</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi" id="kut5"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_anamnesa_neurologi" onclick="saveSPS('anamnesa_neurologi', 'spesialis_neurologi')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_anamnesa_neurologi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-pemeriksaan_neurologi">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_pemeriksaan_neurologi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_pemeriksaan_neurologi"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-4">Pemeriksaan Fisik</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="pt1"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Otonom</label>
                        <div class="col-md-3">
                            <input class="form-control" id="pt2" placeholder="Keringat">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control" id="pt3" placeholder="BAB">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt4" placeholder="BAK">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-4">Columna vertebralis</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt5')" type="radio" value="Normal" id="pt51" name="pt5" /><label for="pt51">Normal</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt5')" type="radio" value="Kelainan" id="pt52" name="pt5" /><label for="pt52">Kelainan</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt5')" type="radio" value="Lainnya" id="pt53" name="pt5" /><label for="pt53">Lainnya</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" id="sps-pt5" style="display: none">
                        <div class="col-md-offset-4 col-md-8">
                            <input class="form-control" id="ket_pt5" placeholder="Keterangan">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-4">Muskuloskeletal</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt6')" type="radio" value="Normal" id="pt61" name="pt6" /><label for="pt61">Nyeri Sendi</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt6')" type="radio" value="Trauma" id="pt62" name="pt6" /><label for="pt62">Trauma</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt6')" type="radio" value="Edema" id="pt63" name="pt6" /><label for="pt63">Edema</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-4 col-md-4">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt6')" type="radio" value="Gangguan Gerak" id="pt64" name="pt6" /><label for="pt64">Gangguan Gerak</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt6')" type="radio" value="Bentuk" id="pt65" name="pt6" /><label for="pt65">Bentuk</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetSPS(this.value, 'pt6')" type="radio" value="Lainnya" id="pt66" name="pt6" /><label for="pt66">Lainnya</label>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" id="sps-pt6" style="display: none">
                        <div class="col-md-offset-4 col-md-8">
                            <input class="form-control" id="ket_pt6" placeholder="Keterangan">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <label class="col-md-4">Pemeriksaan Neurologi</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="pt7"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Pemeriksaan Penunjang</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control penunjang-medis" id="pt8"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Diagnosa Kerja</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control diagnosa-pasien" id="pt9"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Diagnosa Banding</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control diagnosa-pasien" id="pt10"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Tindakan / Rencana Tindakan</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="pt11"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Kontrol Kembali</label>
                        <div class="col-md-8">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control tgl" id="pt12">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Indikasi Rawat Inap</label>
                        <div class="col-md-8">
                            <input class="form-control" id="pt13">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_pemeriksaan_neurologi" onclick="saveSPS('pemeriksaan_neurologi', 'spesialis_neurologi')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_pemeriksaan_neurologi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-sps-edukasi_neurologi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Edukasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_edukasi_neurologi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_edukasi_neurologi"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-12">Edukasi awal, tentang diagnosis, rencana, tujuan terapi kepada :</label>
                        <div class="col-md-12">
                            <div class="custom02">
                                <input type="radio" value="Pasien" id="et1" name="et" /><label for="et1">Pasien</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="custom02">
                                <input type="radio" value="Keluarga" id="et2" name="et" /><label for="et2">Keluarga pasien, nama</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <input placeholder="Nama" id="ket_nama"class="form-control"/>
                        </div>
                        <div class="col-md-6">
                            <label class="jarak" style="margin-left: 20px">Hubungan dengan pasien</label>
                        </div>
                        <div class="col-md-6">
                            <input placeholder="Hubungan" id="ket_hubungan" class="form-control jarak"/>
                        </div>
                        <div class="col-md-12 jarak">
                            <div class="custom02">
                                <input type="radio" value="Tidak" id="et3" name="et" /><label for="et3">Tidak dapat memberi edukasi kepada pasien atau karena</label>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <input id="ket_alasan" class="form-control" placeholder="Keterangan"/>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label>TTD Pasien/Keluarga</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('et4')" class="paint-canvas-ttd" id="et4"></canvas>
                                <button style="margin-top: -5px; margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('et4')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label>TTD Dokter</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('et5')" class="paint-canvas-ttd" id="et5"></canvas>
                                <button style="margin-top: -5px; margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('et5')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_edukasi_neurologi" onclick="saveSPS('edukasi_neurologi', 'spesialis_neurologi')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_edukasi_neurologi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>