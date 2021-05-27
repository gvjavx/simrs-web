<div class="modal fade" id="modal-ring-ringkasan_pulang">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Ringkasan Pulang
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ring_ringkasan_pulang">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ring_ringkasan_pulang"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_ringkasan_pulang">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_ringkasan_pulang"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalRingkasanPasien('ringkasan_pulang_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Ringkasan Pulang
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ring_ringkasan_pulang">
                        <tbody>
                        <tr id="row_ring_ringkasan_pulang_pasien">
                            <td>Ringkasan Pulang</td>
                            <td width="20%" align="center">
                                <img id="btn_ring_ringkasan_pulang_pasien" class="hvr-grow"
                                     onclick="detailRingkasanPasien('ringkasan_pulang_pasien')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">

                                <img id="delete_ringkasan_pulang_pasien" class="hvr-grow btn-hide" onclick="conRing('ringkasan_pulang_pasien', 'ringkasan_pulang')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-ring-ringkasan_pulang_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Ringkasan Pulang
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ring_ringkasan_pulang_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ring_ringkasan_pulang_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal Masuk</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tanggal_masuk_rs" id="rps1" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal Keluar</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="rps2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Alasan Masuk RS</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rps3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Ringkasan Penyakit</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rps4"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Hasil Pemeriksaan Fisik</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control suhu-pasien" type="number" id="rps5" oninput="setSideValue('rps16', this.value)">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control nadi-pasien" type="number" id="rps6"  oninput="setSideValue('rps17', this.value)">
                                    <div class="input-group-addon" style="font-size: 10px" >
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">RR</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control rr-pasien" type="number" id="rps7"  oninput="setSideValue('rps18', this.value)">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Tensi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control tensi-pasien" id="rps8" data-inputmask="'mask': ['999/999']" data-mask=""  oninput="setSideValue('rps19', this.value)">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">GCS</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rps9" style="margin-top: 7px"  oninput="setSideValue('rps20', this.value)">
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">SPO2</label>
                            <div class="col-md-4">
                                <input class="form-control" style="margin-top: 7px" id="spo1" type="number" oninput="setSideValue('spo2', this.value)">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemeriksaan Lab</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rps10"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemeriksaan Radiologi</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="hasil_radiologi"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Terapi atau pengobatan selama di Rumah Sakit</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control tindakan-pasien" rows="3" id="rps11"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Hasil Konsultasi</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rps12"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diagnosa Utama</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control diagnosa-pasien" rows="3" id="rps13"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diagnosa Sekunder</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rps14"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tindakan/Prosedur</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rps15"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Keadaan Waktu Keluar RS</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control suhu-pasien" type="number" id="rps16">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control nadi-pasien" type="number" id="rps17">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">RR</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control rr-pasien" type="number" id="rps18">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Tensi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control tensi-pasien" id="rps19" data-inputmask="'mask': ['999/999']" data-mask="">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">GCS</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rps20" style="margin-top: 7px">
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">SPO2</label>
                            <div class="col-md-4">
                                <input class="form-control" style="margin-top: 7px" id="spo2" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 10px">
                                    <input onclick="showKetRing(this.value, 'penyembab_kematian')" type="radio" value="Diijinkan" id="rps211" name="rps21" /><label for="rps211">Diijinkan</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 10px">
                                    <input onclick="showKetRing(this.value, 'penyembab_kematian')" type="radio" value="Sembuh" id="rps212" name="rps21" /><label for="rps212">Sembuh</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 10px">
                                    <input onclick="showKetRing(this.value, 'penyembab_kematian')" type="radio" value="Dirujuk" id="rps213" name="rps21" /><label for="rps213">Dirujuk</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'penyembab_kematian')" type="radio" value="Permintaan Sendiri" id="rps214" name="rps21" /><label for="rps214">Permintaan Sendiri</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'penyembab_kematian')" type="radio" value="Meninggal" id="rps215" name="rps21" /><label for="rps215">Meninggal</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row" style="display: none" id="form-ring-penyembab_kematian">
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Penyebab Kematian</label>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="custom02">
                                        <input type="radio" value="Kondisi penyakit sebagai penyebab langsung" id="rps221" name="rps22" /><label for="rps221">Kondisi penyakit sebagai penyebab langsung</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="custom02" >
                                        <input type="radio" value="Kondisi lain yang berkontribusi kematian" id="rps222" name="rps22" /><label for="rps222">Kondisi lain yang berkontribusi kematian</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Prognosis</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="prognosis"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Instruksi tindak lanjut</label>
                            <div class="col-md-7">
                                <select class="form-control" id="rps23" style="margin-top: 7px" onchange="setKontrol('show', this.value)">
                                    <option value="">-</option>
                                    <option value="Kembali ke FKTP">Kembali ke FKTP</option>
                                    <option value="Kontrol Ulang">Kontrol Ulang</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div style="display: none" id="form_kontrol_ringkasan">
                        <div class="row" style="margin-top: 7px">
                            <div class="form-group">
                                <div class="col-md-3">
                                    <input style="cursor: pointer" class="form-control ptr-tgl tanggal_kontrol" id="tgl1" placeholder="Tanggal" readonly>
                                </div>
                                <div class="col-md-4">
                                    <input class="form-control poli_kontrol" id="poli1" placeholder="Poli">
                                </div>
                                <div class="col-md-4">
                                    <input class="form-control dokter_kontrol" id="dokter1" placeholder="Dokter">
                                </div>
                                <div class="col-md-1">
                                    <button onclick="setKontrol('add')" class="btn btn-success" style="margin-left: -20px; margin-top: 0px"><i class="fa fa-plus"></i></button>
                                </div>
                            </div>
                        </div>
                        <div id="set_kontrol"></div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Catatan Khusus</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="catatan_khusus"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Terapi Pulang</label>
                            <div class="col-md-8">
                                <button onclick="tambahTerapi()" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Terapi</button>
                            </div>
                        </div>
                        <div id="temp-terapi"></div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Keluarga</label>
                                <canvas class="paint-canvas-ttd" id="gen2" width="220"
                                        onmouseover="paintTtd('gen2')"></canvas>
                                <input class="form-control nama_petugas" id="nama_terang_gen2" placeholder="Nama Terang">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('gen2')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="gen3" width="220"
                                        onmouseover="paintTtd('gen3')"></canvas>
                                <input class="form-control nama_dokter_ri" id="nama_terang_gen3" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_gen3" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('gen3')"><i
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
                <button id="save_ring_ringkasan_pulang_pasien" class="btn btn-success pull-right"
                        onclick="saveRingkasanPasien('ringkasan_pulang_pasien','ringkasan_pulang')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ring_ringkasan_pulang_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ring-resume_medis">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Resume Medis
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ring_resume_medis">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ring_resume_medis"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalRingkasanPasien('resume_medis_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Resume Medis
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ring_resume_medis">
                        <tbody>
                        <tr id="row_ring_resume_medis_pasien">
                            <td>Resume Medis</td>
                            <td width="20%" align="center">
                                <img id="btn_ring_resume_medis_pasien" class="hvr-grow"
                                     onclick="detailRingkasanPasien('resume_medis_pasien')"
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

<div class="modal fade" id="modal-ring-resume_medis_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Resume Medis
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ring_resume_medis_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ring_resume_medis_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Anamnese (Riwayat Penyakit)</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3" id="rem1" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3" id="rem2" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemeriksaan Laboratorium</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemeriksaan Radiologi</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem4"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemeriksaan Lain-Lain</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jalannya penyakit (Konsultasi)</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem6"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diganosa Primer</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem7"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diagnosa Sekunder</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem8"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Terapi</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem9"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem10"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Terapi Pulang</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" rows="3" id="rem11"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Kondisi Saat Pulang</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" type="number" id="rem12">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" type="number" id="rem13">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">RR</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" type="number" id="rem14">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Tensi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" type="number" id="rem15">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">GCS</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rem16" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Keadaan saat keluar RS</label>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="custom02">
                                        <input onclick="showKetRing(this.value, 'rem17')" type="radio" value="Pulang sesuai advis dokter" id="rem171" name="rem17" /><label for="rem171">Pulang sesuai advis dokter</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="custom02" >
                                        <input onclick="showKetRing(this.value, 'rem17')" type="radio" value="Meninggal dunia, sebab kematian" id="rem172" name="rem17" /><label for="rem172">Meninggal dunia, sebab kematian</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="custom02" >
                                        <input onclick="showKetRing(this.value, 'rem17')" type="radio" value="Pulang atas permintaan sendiri" id="rem173" name="rem17" /><label for="rem173">Pulang atas permintaan sendiri</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="custom02" >
                                        <input onclick="showKetRing(this.value, 'rem17')" type="radio" value="Rujuk" id="rem174" name="rem17" /><label for="rem174">Rujuk</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" style="display: none" id="form-ring-rem17">
                                <div class="col-md-11">
                                    <input placeholder="Keterangan" class="form-control" id="ket_rem17">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">Prognosis</label>
                            <label class="col-md-12">Usulan Tindak Lanjut Kontrol Poli</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <button onclick="tambahPrognosis()" class="btn btn-success"><i class="fa fa-plus"></i> Tambah</button>
                            </div>
                        </div>
                        <div id="temp-prognosis">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pesan Khusus</label>
                            <div class="col-md-8">
                                <input class="form-control" id="rem18" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ring_resume_medis_pasien" class="btn btn-success pull-right"
                        onclick="saveRingkasanPasien('resume_medis_pasien','resume_medis')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ring_resume_medis_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ring-ringkasan_keluar">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Ringkasan Masuk Keluar
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ring_ringkasan_keluar">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ring_ringkasan_keluar"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_ringkasan_keluar">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_ringkasan_keluar"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalRingkasanPasien('ringkasan_keluar_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Ringkasan Masuk Keluar
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ring_ringkasan_keluar">
                        <tbody>
                        <tr id="row_ring_ringkasan_keluar_pasien">
                            <td>Ringkasan Masuk Keluar</td>
                            <td width="20%" align="center">
                                <img id="btn_ring_ringkasan_keluar_pasien" class="hvr-grow"
                                     onclick="detailRingkasanPasien('ringkasan_keluar_pasien')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_ringkasan_keluar_pasien" class="hvr-grow btn-hide" onclick="conRing('ringkasan_keluar_pasien', 'ringkasan_keluar')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-ring-ringkasan_keluar_pasien">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Ringkasan Masuk Keluar
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ring_ringkasan_keluar_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ring_ringkasan_keluar_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">MRS Melalui</label>
                            <div class="col-md-6">
                                <input class="form-control asal_masuk" id="rkp1">
                            </div>
                            <%--<div class="col-md-3">--%>
                                <%--<div class="custom02">--%>
                                    <%--<input type="radio" value="Poliklinik" id="rkp11" name="rkp1" /><label for="rkp11">Poliklinik</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-3">--%>
                                <%--<div class="custom02">--%>
                                    <%--<input type="radio" value="IGD" id="rkp12" name="rkp1" /><label for="rkp12">IGD</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-3">--%>
                                <%--<div class="custom02">--%>
                                    <%--<input type="radio" value="Persalinan" id="rkp13" name="rkp1" /><label for="rkp13">Persalinan</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Rujukan dari</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRing(this.value, 'rkp2')" value="Sendiri" id="rkp21" name="rkp2" /><label for="rkp21">Sendiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRing(this.value, 'rkp2')" value="Dokter" id="rkp22" name="rkp2" /><label for="rkp22">Dokter</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRing(this.value, 'rkp2')" value="Puskesmas" id="rkp23" name="rkp2" /><label for="rkp23">Puskesmas</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRing(this.value, 'rkp2')" value="RS Lain" id="rkp24" name="rkp2" /><label for="rkp24">RS Lain</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRing(this.value, 'rkp2')" value="Bidan" id="rkp25" name="rkp2" /><label for="rkp25">Bidan</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetRing(this.value, 'rkp2')" value="Polisi" id="rkp26" name="rkp2" /><label for="rkp26">Polisi</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ring-rkp2">
                            <div class="col-md-offset-3 col-md-9">
                                <input class="form-control" placeholder="Keterangan" id="ket_rkp2">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa Utama</label>
                            <div class="col-md-8">
                                <textarea class="form-control diagnosa-pasien" id="rkp3" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diagnosa Sekunder</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rkp4" rows="3" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-bottom: 20px">Operasi/Tindakan</label>
                            <div class="col-md-9">
                                <button onclick="tambahOp()" class="btn btn-success" style="margin-top: -7px"><i class="fa fa-plus"></i> Tambah</button>
                            </div>
                        </div>
                    </div>
                    <div id="temp-op">
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Whole Blood</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" placeholder="CC" id="rkp5">
                            </div>
                            <label class="col-md-2">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control ptr-tgl" id="rkp6">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Packet Red Cells</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" placeholder="CC" style="margin-top: 7px" id="rkp7">
                            </div>
                            <label class="col-md-2" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control ptr-tgl" id="rkp8">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Liquid Plasma</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" placeholder="CC" style="margin-top: 7px" id="rkp9">
                            </div>
                            <label class="col-md-2" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control ptr-tgl" id="rkp10">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Lahir Hidup</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Lahir Mati" id="rkp111" name="rkp11" /><label for="rkp111">Lahir Mati</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Lahir < 2500 gram" id="rkp112" name="rkp11" /><label for="rkp112">Lahir < 2500 gram</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Abortus" id="rkp113" name="rkp11" /><label for="rkp113">Abortus</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Keluar RS</label>
                            <div class="col-md-3">
                                <input class="form-control tgl" id="krs1">
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-2">
                                <input class="form-control jam" id="krs2">
                            </div>
                            <label class="col-md-1">Lama Dirawat</label>
                            <div class="col-md-2">
                                <input class="form-control" id="krs3">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Keadaan Keluar</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Lahir Mati" id="rkp121" name="rkp12" /><label for="rkp121">Lahir Mati</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Lahir < 2500 gram" id="rkp122" name="rkp12" /><label for="rkp122">Lahir < 2500 gram</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Abortus" id="rkp123" name="rkp12" /><label for="rkp123">Abortus</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mati < 48 jam" id="rkp124" name="rkp12" /><label for="rkp124">Mati < 48 jam</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mati > 48 jam" id="rkp125" name="rkp12" /><label for="rkp125">Mati > 48 jam</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Cara Keluar RS</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'rkp13')" type="radio" value="Dipulangkan" id="rkp131" name="rkp13" /><label for="rkp131">Dipulangkan</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'rkp13')" type="radio" value="Dirujuk" id="rkp132" name="rkp13" /><label for="rkp132">Dirujuk</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'rkp13')" type="radio" value="Ijin" id="rkp133" name="rkp13" /><label for="rkp133">Ijin</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'rkp13')" type="radio" value="Permintaan Sendiri" id="rkp134" name="rkp13" /><label for="rkp134">Permintaan Sendiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'rkp13')" type="radio" value="Melarikan diri" id="rkp135" name="rkp13" /><label for="rkp135">Melarikan diri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetRing(this.value, 'rkp13')" type="radio" value="Lain-Lain" id="rkp136" name="rkp13" /><label for="rkp136">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ring-rkp13">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input id="ket_rkp13" placeholder="Keterangan" class="form-control" style="margin-top: 7px">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ring_ringkasan_keluar_pasien" class="btn btn-success pull-right"
                        onclick="saveRingkasanPasien('ringkasan_keluar_pasien','ringkasan_keluar')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ring_ringkasan_keluar_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>