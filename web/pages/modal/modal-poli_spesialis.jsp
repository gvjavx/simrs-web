<div class="modal fade" id="modal-sps-poli_spesialis">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> <span id="judul_asesmen"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_sps_poli_spesialis">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_poli_spesialis"></p>
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
                            <li><a onclick="showModalSPS('anamnesis_pemeriksaan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Anamnesis dan Pemeriksaan</a></li>
                            <li><a onclick="showModalSPS('edukasi_spesialis')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Edukasi</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr id="row_sps_anamnesis_pemeriksaan">
                            <td>Anamnesis dan Pemeriksaan</td>
                            <td width="20%" align="center"><img id="btn_sps_anamnesis_pemeriksaan" class="hvr-grow"
                                                                onclick="detailSPS('anamnesis_pemeriksaan')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_anamnesis_pemeriksaan" class="hvr-grow btn-hide" onclick="conSPS('anamnesis_pemeriksaan', 'poli_spesialis')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_edukasi_spesialis">
                            <td>Edukasi</td>
                            <td width="20%" align="center"><img id="btn_sps_edukasi_spesialis" class="hvr-grow"
                                                                onclick="detailSPS('edukasi_spesialis')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_edukasi_spesialis" class="hvr-grow btn-hide" onclick="conSPS('edukasi_spesialis', 'poli_spesialis')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-anamnesis_pemeriksaan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Anamnesis dan Pemeriksaan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_anamnesis_pemeriksaan">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_anamnesis_pemeriksaan"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Tanggal Kunjungan</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ps1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <label class="col-md-12"><b>Anamnesis</b></label>
                    </div>
                    <hr>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Keluhan Utama</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control anamnese" id="ps2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="ps3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Penyakit Keluarga</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control alergi" id="ps4"></textarea>
                            </div>
                        </div>
                    </div>
                    <div id="form_psikiatri" style="display: none">
                        <hr>
                        <div class="row">
                            <label class="col-md-12"><b>Khusus Psikiatri</b></label>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-4">Persepsi (Halusinasi)</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk1" id="psk11" value="Tidak">
                                    <label for="psk11"></label> Tidak
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk1" id="psk12" value="Pendengaran">
                                    <label for="psk12"></label> Pendengaran
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk1" id="psk13" value="Penglihatan">
                                    <label for="psk13"></label> Penglihatan
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk1" id="psk14" value="Perabaaan">
                                    <label for="psk14"></label> Perabaaan
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk1" id="psk15" value="Pengecapan">
                                    <label for="psk15"></label> Pengecapan
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk1" id="psk16" value="Penciuman">
                                    <label for="psk16"></label> Penciuman
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-4">Proses Pikir</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk2" id="psk21" value="Sesuai">
                                    <label for="psk21"></label> Sesuai
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk2" id="psk22" value="Blocking">
                                    <label for="psk22"></label> Blocking
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="form-check">
                                    <input type="checkbox" name="psk2" id="psk23" value="Pengeluaran Pembicaraan">
                                    <label for="psk23"></label> Pengeluaran Pembicaraan
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="form-check">
                                    <input type="checkbox" name="psk2" id="psk24" value="Berputar putar">
                                    <label for="psk24"></label> Berputar putar
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-4">Isi Pikir</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk3" id="psk31" value="Sesuai">
                                    <label for="psk31"></label> Sesuai
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk3" id="psk32" value="Obsesi">
                                    <label for="psk32"></label> Obsesi
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk3" id="psk33" value="Fobia">
                                    <label for="psk33"></label> Fobia
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk3" id="psk34" value="Pikiran Magis">
                                    <label for="psk34"></label> Pikiran Magis
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-1">
                                <div class="form-check">
                                    <input type="checkbox" name="psk3" id="psk35">
                                    <label for="psk35"></label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input style="margin-left: -10px" class="form-control" onchange="$('#psk35').val(''); $('#psk35').val(this.value)">
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-4">Waham</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk4" id="psk41" value="Tidak Ada">
                                    <label for="psk41"></label> Tidak Ada
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk4" id="psk42" value="Agama">
                                    <label for="psk42"></label> Agama
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk4" id="psk43" value="Curiga">
                                    <label for="psk43"></label> Curiga
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk4" id="psk44" value="Kebesaran">
                                    <label for="psk44"></label> Kebesaran
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-1">
                                <div class="form-check">
                                    <input type="checkbox" name="psk4" id="psk45">
                                    <label for="psk45"></label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input style="margin-left: -10px" class="form-control" onchange="$('#psk45').val(''); $('#psk45').val(this.value)">
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-4">Tingkat Kgonitif</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk5" id="psk51" value="Koheren">
                                    <label for="psk51"></label> Koheren
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="psk5" id="psk52" value="Bingung">
                                    <label for="psk52"></label> Bingung
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="form-check">
                                    <input type="checkbox" name="psk5" id="psk53" value="Stupor/mematung">
                                    <label for="psk53"></label> Stupor/mematung
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="form-check">
                                    <input type="checkbox" name="psk5" id="psk54" value="Disorientasi(waktu, tempat, orang)">
                                    <label for="psk54"></label> Disorientasi(waktu, tempat, orang)
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-6">Memori/daya ingat</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Baik" id="psk61" name="psk6" />
                                    <label for="psk61">Baik</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Baik" id="psk62" name="psk6" />
                                    <label for="psk62">Tidak Baik</label>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-6">Tingkat konsentrasi dan berhitung</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Baik" id="psk71" name="psk7" />
                                    <label for="psk71">Baik</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Baik" id="psk72" name="psk7" />
                                    <label for="psk72">Tidak Baik</label>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <label class="col-md-6">Mengingkari penyakit yang diderita</label>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Baik" id="psk81" name="psk8" />
                                    <label for="psk81">Tidak</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-6 col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Baik" id="psk82" name="psk8" />
                                    <label for="psk82">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <input class="form-control" onchange="$('#psk82').val(''); $('#psk82').val(this.value);">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <label class="col-md-12"><b>Pemeriksaan</b></label>
                    </div>
                    <hr>
                    <div class="row">
                        <label class="col-md-4">Kondisi Umum</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="ps5"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Kepala Leher</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="ps6"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Thorax</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="ps7"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Abdomen</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="ps8"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Ekstremitas</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control" id="ps9"></textarea>
                        </div>
                    </div>
                    <div id="form-gambar" style="display: none">
                        <hr>
                        <div class="row">
                            <div class="col-md-1">
                                <input type="color" class="js-color-picker-op color-picker pull-left" value="#ff0000">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="text-center">
                                    <canvas class="paint-canvas canvas-cek" style="cursor: pointer"></canvas>
                                </div>
                                <canvas style="display: none" id="area_cek"></canvas>
                                <button type="button" class="btn btn-danger canvas-btn"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                        <hr>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Pemeriksaan Penunjang</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control penunjang-medis" id="ps10"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Diagnosa Kerja</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control diagnosa-pasien" id="ps11"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Diagnosa Banding</label>
                        <div class="col-md-8">
                            <textarea rows="2" class="form-control diagnosa-pasien" id="ps12"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Terapi</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control resep-pasien" id="ps13"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Tindakan / Rencana Tindakan</label>
                        <div class="col-md-8">
                            <textarea rows="3" class="form-control" id="ps14"></textarea>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-4">Intruksi Tindak Lanjut</label>
                        <div class="col-md-8">
                            <select class="form-control select2" id="intruksi_anamnesis_pemeriksaan" style="width: 100%" onchange="showKetIntruksi(this.value)">
                            </select>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="int-ket1">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <select class="form-control select2" id="ket_anamnesis_pemeriksaan" style="width: 100%">
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
                        <div class="form-group text-center">
                            <div class="col-md-offset-3 col-md-6">
                                <label>TTD Dokter</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('dpjp')" class="paint-canvas-ttd" id="dpjp"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_dpjp" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_dpjp" placeholder="SIP">
                                <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('dpjp')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_anamnesis_pemeriksaan" onclick="saveSPS('anamnesis_pemeriksaan', 'poli_spesialis')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_anamnesis_pemeriksaan"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-edukasi_spesialis">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_edukasi_spesialis">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_edukasi_spesialis"></p>
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
                <button class="btn btn-success pull-right" id="save_sps_edukasi_spesialis" onclick="saveSPS('edukasi_spesialis', 'poli_spesialis')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_edukasi_spesialis"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
