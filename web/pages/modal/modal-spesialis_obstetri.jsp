<div class="modal fade" id="modal-sps-spesialis_obstetri">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Asesmen Obstetri
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_sps_spesialis_obstetri">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_obstetri"></p>
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
                            <li><a onclick="showModalSPS('anamnesa_obstetri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Anamnesa</a></li>
                            <li><a onclick="showModalSPS('pemeriksaan_obstetri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pemeriksaan</a></li>
                            <li><a onclick="showModalSPS('edukasi_obstetri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Edukasi</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr id="row_sps_anamnesa_obstetri">
                            <td>Anamnesa</td>
                            <td width="20%" align="center"><img id="btn_sps_anamnesa_obstetri" class="hvr-grow"
                                                                onclick="detailSPS('anamnesa_obstetri')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_anamnesa_obstetri" class="hvr-grow" onclick="conSPS('anamnesa_obstetri', 'spesialis_obstetri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_pemeriksaan_obstetri">
                            <td>Pemeriksaan</td>
                            <td width="20%" align="center"><img id="btn_sps_pemeriksaan_obstetri" class="hvr-grow"
                                                                onclick="detailSPS('pemeriksaan_obstetri')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_pemeriksaan_obstetri" class="hvr-grow" onclick="conSPS('pemeriksaan_obstetri', 'spesialis_obstetri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_edukasi_obstetri">
                            <td>Edukasi</td>
                            <td width="20%" align="center"><img id="btn_sps_edukasi_obstetri" class="hvr-grow"
                                                                onclick="detailSPS('edukasi_obstetri')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="del_edukasi_obstetri" class="hvr-grow" onclick="conSPS('edukasi_obstetri', 'spesialis_obstetri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-anamnesa_obstetri">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_anamnesa_obstetri">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_anamnesa_obstetri"></p>
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
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-2">
                                <input class="form-control" id="kut4" placeholder="G">
                            </div>
                            <div class="col-md-2">
                                <input class="form-control" id="kut5" placeholder="P">
                            </div>
                            <label class="col-md-4">Haid Terakhir</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="kut6">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-4 col-md-4">Taksiran persalinan</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="kut7">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Kehamilan Sekarang</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control" id="kut8"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Persalinan</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi" id="kut9"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit Lain</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi" id="kut10"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit Keluarga</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi" id="kut11"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat KB</label>
                            <div class="col-md-9">
                                <textarea rows="3" class="form-control alergi" id="kut12"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_anamnesa_obstetri" onclick="saveSPS('anamnesa_obstetri', 'spesialis_obstetri')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_anamnesa_obstetri"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-pemeriksaan_obstetri">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_pemeriksaan_obstetri">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_pemeriksaan_obstetri"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-12"><b>Status Obstetri</b></label>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">TFU</label>
                        <div class="col-md-3">
                            <input class="form-control" id="pt1" placeholder="Djj">
                        </div>
                        <div class="col-md-3">
                            <input class="form-control" id="pt2" placeholder="Letak">
                        </div>
                        <div class="col-md-2">
                            <input class="form-control" id="pt3" placeholder="His">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">VT</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="pt4"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">USG Hamil</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control" id="pt5"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Diagnosa Kerja</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control diagnosa-pasien" id="pt6"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Diagnosa Banding</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control diagnosa-pasien" id="pt7"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Penyulit</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="pt8"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Pengobatan / Rencana Tindakan</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="pt9"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Anjuran Kontrol Kembali</label>
                        <div class="col-md-8">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control tgl" id="pt10">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Indikasi Rawat Inap</label>
                        <div class="col-md-8">
                            <input class="form-control" id="pt11">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_pemeriksaan_obstetri" onclick="saveSPS('pemeriksaan_obstetri', 'spesialis_obstetri')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_pemeriksaan_obstetri"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-sps-edukasi_obstetri">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_edukasi_obstetri">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_edukasi_obstetri"></p>
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
                <button class="btn btn-success pull-right" id="save_sps_edukasi_obstetri" onclick="saveSPS('edukasi_obstetri', 'spesialis_obstetri')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_edukasi_obstetri"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>