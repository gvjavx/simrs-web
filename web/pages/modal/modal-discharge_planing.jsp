<div class="modal fade" id="modal-ina-discharge_planing">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Perencanaaan Pemulangan Pasien (Discharge Planing)
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_discharge_planing">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_discharge_planing"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_discharge_planing">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_discharge_planing"></p>
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
                            <li><a onclick="showModalAsesmenRawatInap('kriteria_discharge_planing')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Kriteria Discharge Planing</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('kebutuhan_discharge_planing')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Kebutuhan Discharge Planing</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_discharge_planing">
                        <tbody>
                        <tr id="row_ina_kriteria_discharge_planing">
                            <td>Kriteria Discharge Planning</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_kriteria_discharge_planing" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('kriteria_discharge_planing')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_kriteria_discharge_planing" class="hvr-grow" onclick="conRI('anamnesa_umum', 'discharge_planing')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_kebutuhan_discharge_planing">
                            <td>Kebutuhan Discharge Planning</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_kebutuhan_discharge_planing" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('kebutuhan_discharge_planing')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_kebutuhan_discharge_planing" class="hvr-grow" onclick="conRI('kebutuhan_discharge_planing', 'discharge_planing')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-ina-kriteria_discharge_planing">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Kriteria Discharge Planning
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_kriteria_discharge_planing">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_kriteria_discharge_planing"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-7">1. Pasien usia > 65 thn</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kdp11" name="kdp1" /><label for="kdp11">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kdp12" name="kdp1" /><label for="kdp12">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-7">2. Keterbatasan Mobilitas</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kdp21" name="kdp2" /><label for="kdp21">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kdp22" name="kdp2" /><label for="kdp22">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-7">3. Prawatan atau pengobatan lanjut</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kdp31" name="kdp3" /><label for="kdp31">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kdp32" name="kdp3" /><label for="kdp32">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-7">4. Bantuan untuk melakukan aktivitas sehari hari</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kdp41" name="kdp4" /><label for="kdp41">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kdp42" name="kdp4" /><label for="kdp42">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Diagnosis Utama</label>
                            <div class="col-md-7">
                                <textarea style="margin-top: 7px" class="form-control diagnosa-pasien" rows="3" id="kdp5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Diagnosis Sekunder</label>
                            <div class="col-md-7">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="kdp6"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Transportasi Pulang</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'kdp7')" value="Kendaraan Pribadi" id="kdp71" name="kdp7" /><label for="kdp71">Kendaraan Pribadi</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'kdp7')" value="Ambulan" id="kdp72" name="kdp7" /><label for="kdp72">Ambulan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'kdp7')" value="Lainnya" id="kdp73" name="kdp7" /><label for="kdp73">Lainnya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-kdp7">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" id="ket_kdp7" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Membutuhkan pendamping selama di rumah</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'kdp8')" value="Dokter" id="kdp81" name="kdp8" /><label for="kdp81">Dokter</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'kdp8')" value="Keluarga" id="kdp82" name="kdp8" /><label for="kdp82">Keluarga</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'kdp8')" value="Lainnya" id="kdp83" name="kdp8" /><label for="kdp83">Lainnya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02" style="margin-top: -30px">
                                    <input type="radio" onclick="showKetIna(this.value, 'kdp8')" value="Tenaga kesehatan lain" id="kdp84" name="kdp8" /><label for="kdp84">Tenaga kesehatan lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-kdp8">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" id="ket_kdp8" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_kriteria_discharge_planing" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('kriteria_discharge_planing','discharge_planing')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_kriteria_discharge_planing" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-kebutuhan_discharge_planing">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Kebutuhan Discharge Planning</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_kebutuhan_discharge_planing">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_kebutuhan_discharge_planing"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">
                                Diperkirakan akan membutuhkan bantuan dalam aktivitas sehari hari, misal: Pasca Stroke, Gangguan penglihatan, Pasca operasi, dll
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan1" id="plan11" value="Konsul Rehabilitasi">
                                    <label for="plan11"></label> Konsul Rehabilitasi
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan1" id="plan12" value="Membutuhkan anggota gerak palsu">
                                    <label for="plan12"></label> Membutuhkan anggota gerak palsu
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan1" id="plan13" value="Membutuhkan alat bantu gerak">
                                    <label for="plan13"></label> Membutuhkan alat bantu gerak
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan1" id="plan14" value="Terapi wicara">
                                    <label for="plan14"></label> Terapi wicara
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="plan1" id="plan15" value="Membutuhkan alat bantu medis (Oksigen, Tracrostiomi, Cateter, Dll)">
                                    <label for="plan15"></label> Membutuhkan alat bantu medis (Oksigen, Tracrostiomi, Cateter, Dll)
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="plan1" id="plan16" value="Lainnya" onclick="chekedChoice(this.id,'plan1')">
                                    <label for="plan16"></label> Lainnya
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-plan1">
                            <div class="col-md-12">
                                <input class="form-control" placeholder="Keterangan" id="ket_plan1" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">
                                Membutuhkan diet dan edukasi gizi yang kompleks terkait penyakitnya
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="plan2" id="plan21" value="Konsultasi gizi">
                                    <label for="plan21"></label> Konsultasi gizi
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="form-check">
                                    <input type="checkbox" name="plan2" id="plan22" value="Penggunaan alat bantu makan yang khusus">
                                    <label for="plan22"></label> Penggunaan alat bantu makan yang khusus
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="plan2" id="plan23" value="Lainnya" onclick="chekedChoice(this.id,'plan2')">
                                    <label for="plan23"></label> Lainnya
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-plan2">
                            <div class="col-md-12">
                                <input class="form-control" placeholder="Keterangan" id="ket_plan2" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">
                                Membutuhkan penanganan nyeri yang kronis
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan3" id="plan31" value="Konsultasi kepada tim nyeri">
                                    <label for="plan31"></label> Konsultasi kepada tim nyeri
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan3" id="plan32" value="Edukasi tentang obat nyeri">
                                    <label for="plan32"></label> Edukasi tentang obat nyeri
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan3" id="plan33" value="Penanganan nyeri mendiri">
                                    <label for="plan33"></label> Penanganan nyeri mendiri
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="plan3" id="plan34" value="Lainnya" onclick="chekedChoice(this.id,'plan3')">
                                    <label for="plan34"></label> Lainnya
                                </div>
                            </div>
                            <div class="form-group" style="display: none" id="form-ina-plan3">
                                <div class="col-md-12">
                                    <input class="form-control" placeholder="Keterangan" id="ket_plan3" style="margin-top: 7px">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Tujuan</label>
                            <div class="col-md-6">
                                <div class="form-check" style="margin-top: 7px">
                                    <input type="checkbox" name="plan4" id="plan41" value="Penatalaksanaan penyakit">
                                    <label for="plan41"></label> Penatalaksanaan penyakit
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="form-check" style="margin-top: 7px">
                                    <input type="checkbox" name="plan4" id="plan42" value="Tindakan" onclick="chekedChoice(this.id,'plan_tindakan')">
                                    <label for="plan42"></label> Tindakan
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-plan_tindakan">
                            <div class="col-md-offset-6 col-md-6">
                                <input class="form-control" placeholder="Sebutkan" id="ket_plan4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">
                                Diperkirakan akan membutuhkan pengelolaan penyakit secara berkelanjutan di luar RSG
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan5" id="plan51" value="Home Care">
                                    <label for="plan51"></label> Home Care
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan5" id="plan52" value="Panti/rumah singgah">
                                    <label for="plan52"></label> Panti/rumah singgah
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan5" id="plan53" value="Dokter keluarga / Dokter praktek">
                                    <label for="plan53"></label> Dokter keluarga / Dokter praktek
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan5" id="plan54" value="Rumah Sakit">
                                    <label for="plan54"></label> Rumah Sakit
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan5" id="plan55" value="Puskesmas">
                                    <label for="plan55"></label> Puskesmas
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan5" id="plan56" value="Lainnya" onclick="chekedChoice(this.id,'plan5')">
                                    <label for="plan56"></label> Lainnya
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-plan5">
                            <div class="col-md-12">
                                <input class="form-control" placeholder="Keterangan" id="ket_plan5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">
                                Membutuhkan penanganan penyakit berkelanjutan
                                <p>a. Perawatan luka: lokasi tidak terjangkau, luas</p>
                                <p style="margin-top: -10px">b. Perawatan pasca melahirkan: perawatan bayi BBLR</p>
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan6" id="plan61" value="Pendamping terlatih" onclick="chekedChoice(this.id,'ket1_plan6')">
                                    <label for="plan61"></label> Pendamping terlatih
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan6" id="plan62" value="Home Care">
                                    <label for="plan62"></label> Home Care
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan6" id="plan63" value="Edukasi kusus">
                                    <label for="plan63"></label> Edukasi kusus
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan6" id="plan64" value="Lainnya" onclick="chekedChoice(this.id,'ket2_plan6')">
                                    <label for="plan64"></label> Lainnya
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6" style="display: none" id="form-ina-ket1_plan6">
                                <input class="form-control" placeholder="Nama" id="ket1_plan6" style="margin-top: 7px">
                            </div>
                            <div class="col-md-6" style="display: none" id="form-ina-ket2_plan6">
                                <input class="form-control" placeholder="Keterangan" id="ket2_plan6" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">
                                Kebutuhan lainnya
                            </label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan7" id="plan71" value="Konsultasi kepada" onclick="chekedChoice(this.id,'ket1_plan7')">
                                    <label for="plan71"></label> Konsultasi kepada
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="plan7" id="plan72" value="Lainnya" onclick="chekedChoice(this.id,'ket2_plan7')">
                                    <label for="plan72"></label> Lainnya
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6" style="display: none" id="form-ina-ket1_plan7">
                                <input class="form-control" placeholder="Nama" id="ket1_plan7" style="margin-top: 7px">
                            </div>
                            <div class="col-md-6" style="display: none" id="form-ina-ket2_plan7">
                                <input class="form-control" placeholder="Keterangan" id="ket2_plan7" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_kebutuhan_discharge_planing" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('kebutuhan_discharge_planing','discharge_planing')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_kebutuhan_discharge_planing" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>