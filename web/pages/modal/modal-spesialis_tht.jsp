<div class="modal fade" id="modal-sps-spesialis_tht">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Asesmen THT
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_sps_spesialis_tht">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_tht"></p>
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
                            <li><a onclick="showModalSPS('keadaan_umum_tht')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Keadaan Umum</a></li>
                            <li><a onclick="showModalSPS('tht_tht')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> THT</a></li>
                            <li><a onclick="showModalSPS('pemeriksaan_tht')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pemeriksaan</a></li>
                            <li><a onclick="showModalSPS('edukasi_tht')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Edukasi</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_fisio">
                        <tbody>
                        <tr id="row_sps_keadaan_umum_tht">
                            <td>Keadaan Umum</td>
                            <td width="20%" align="center"><img id="btn_sps_keadaan_umum_tht" class="hvr-grow"
                                                                onclick="detailSPS('keadaan_umum_tht')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_keadaan_umum_tht" class="hvr-grow btn-hide" onclick="conSPS('keadaan_umum_tht', 'spesialis_tht')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_tht_tht">
                            <td>THT</td>
                            <td width="20%" align="center"><img id="btn_sps_tht_tht" class="hvr-grow"
                                                                onclick="detailSPS('tht_tht')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_tht_tht" class="hvr-grow btn-hide" onclick="conSPS('tht_tht', 'spesialis_tht')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_pemeriksaan_tht">
                            <td>Pemeriksaan</td>
                            <td width="20%" align="center"><img id="btn_sps_pemeriksaan_tht" class="hvr-grow"
                                                                onclick="detailSPS('pemeriksaan_tht')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pemeriksaan_tht" class="hvr-grow btn-hide" onclick="conSPS('pemeriksaan_tht', 'spesialis_tht')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_edukasi_tht">
                            <td>Edukasi</td>
                            <td width="20%" align="center"><img id="btn_sps_edukasi_tht" class="hvr-grow"
                                                                onclick="detailSPS('edukasi_tht')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_edukasi_tht" class="hvr-grow btn-hide" onclick="conSPS('edukasi_tht', 'spesialis_tht')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-keadaan_umum_tht">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Keadaan Umum
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_keadaan_umum_tht">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_keadaan_umum_tht"></p>
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
                            <label class="col-md-3">Riwayat Alergi</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi-pasien" id="kut5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Status Lokalis</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut6"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_keadaan_umum_tht" onclick="saveSPS('keadaan_umum_tht', 'spesialis_tht')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_keadaan_umum_tht"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-tht_tht">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> THT (Telinga Hidung Tenggorokan)
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_tht_tht">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_tht_tht"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label><b>Telingan</b></label>
                            </div>
                            <div class="form-group jarak">
                                <label>Daun Telinga</label>
                                <input class="form-control" id="tht1">
                            </div>
                            <div class="form-group jarak">
                                <label>Liang Telinga</label>
                                <input class="form-control" id="tht2">
                            </div>
                            <div class="form-group jarak">
                                <label>Membran timpani</label>
                                <input class="form-control" id="tht3">
                            </div>
                            <div class="form-group jarak">
                                <label>Reflek Cahaya</label>
                                <input class="form-control" id="tht4">
                            </div>
                            <div class="form-group jarak">
                                <label>Rinne</label>
                                <input class="form-control" id="tht5">
                            </div>
                            <div class="form-group jarak">
                                <label>Waber</label>
                                <input class="form-control" id="tht6">
                            </div>
                            <div class="form-group jarak">
                                <label>Scabach</label>
                                <input class="form-control" id="tht7">
                            </div>
                            <div class="form-group jarak">
                                <label>Audimetri</label>
                                <input class="form-control" id="tht8">
                            </div>
                            <div class="form-group jarak">
                                <label>Timpanometri</label>
                                <input class="form-control" id="tht9">
                            </div>
                            <div class="form-group jarak">
                                <label>Fungsi tuba eustachius</label>
                                <input class="form-control" id="tht10">
                            </div>
                            <div class="form-group jarak">
                                <label>Reflek Stapedius</label>
                                <input class="form-control" id="tht11">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label><b>Hidung</b></label>
                            </div>
                            <div class="form-group jarak">
                                <label>Kavum nasi</label>
                                <input class="form-control" id="tht12">
                            </div>
                            <div class="form-group jarak">
                                <label>Konka Inferior</label>
                                <input class="form-control" id="tht13">
                            </div>
                            <div class="form-group jarak">
                                <label>Konka Medis</label>
                                <input class="form-control" id="tht14">
                            </div>
                            <div class="form-group jarak">
                                <label>Meatus Media</label>
                                <input class="form-control" id="tht15">
                            </div>
                            <div class="form-group jarak">
                                <label>Septum</label>
                                <input class="form-control" id="tht16">
                            </div>
                            <div class="form-group jarak">
                                <label>Lainnya</label>
                                <input class="form-control" id="tht17">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label><b>Tenggorokan</b></label>
                            </div>
                            <div class="form-group jarak">
                                <label>Orofaring</label>
                                <select class="form-control" id="tht18">
                                    <option value="Tonsil">Tonsil</option>
                                    <option value="Arkus Faring">Arkus Faring</option>
                                    <option value="Dinding Posterior">Dinding Posterior</option>
                                </select>
                            </div>
                            <div class="form-group jarak">
                                <label>Nasofaring</label>
                                <input class="form-control" id="tht19">
                            </div>
                            <div class="form-group jarak">
                                <label>Laring</label>
                                <input class="form-control" id="tht20">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_tht_tht" onclick="saveSPS('tht_tht', 'spesialis_tht')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_tht_tht"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-pemeriksaan_tht">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_pemeriksaan_tht">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_pemeriksaan_tht"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-4">Kepala</label>
                        <div class="col-md-8">
                            <input class="form-control" id="pt1">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Leher</label>
                        <div class="col-md-8">
                            <input class="form-control" id="pt2">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Pemeriksaan Penunjang</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control penunjang-medis" id="pt3"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Pemeriksaan Lainnya/ Tes Pendengaran</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control" id="pt4"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Diagnosa Kerja</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control diagnosa-pasien" id="pt5"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Terapi / Pengobatan</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control" id="pt6"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Tindakan / Rencana Tindakan</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control" id="pt7"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Kontrol Kembali</label>
                        <div class="col-md-8">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control ptr-tgl" id="pt8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Indikasi Rawat Inap</label>
                        <div class="col-md-8">
                            <input class="form-control" id="pt9">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_pemeriksaan_tht" onclick="saveSPS('pemeriksaan_tht', 'spesialis_tht')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_pemeriksaan_tht"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-sps-edukasi_tht">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_edukasi_tht">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_edukasi_tht"></p>
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
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <span>Isi Edukasi</span>
                                <textarea rows="4" class="form-control" id="isi_edukasi"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label>TTD Pasien/Keluarga</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('et4')" class="paint-canvas-ttd" id="et4"></canvas>
                                <input class="form-control" id="nama_terang_pasien" placeholder="Nama Terang">
                                <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('et4')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label>TTD Dokter</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('et5')" class="paint-canvas-ttd" id="et5"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_dokter" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_dokter" placeholder="SIP">
                                <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('et5')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_edukasi_tht" onclick="saveSPS('edukasi_tht', 'spesialis_tht')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_edukasi_tht"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>