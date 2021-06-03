<div class="modal fade" id="modal-ina-transfer_pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Transfer Pasien Antar Ruangan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success alert-dismissible" style="display: none"
                     id="warning_ina_transfer_pasien">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_ina_transfer_pasien"></p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_transfer_pasien">
                    <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                    <p id="msg_transfer_pasien"></p>
                </div>
                <div class="box-body btn-hide">
                        <button onclick="showModalAsesmenRawatInap('add_transfer_pasien')" type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Asesmen Transfer
                        </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_transfer_pasien">
                        <tbody>
                        <tr id="row_ina_add_transfer_pasien">
                            <td>Asesmen Transfer</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_add_transfer_pasien" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('add_transfer_pasien')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
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

<div class="modal fade" id="modal-ina-add_transfer_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Asesmen Transfer Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_add_transfer_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_add_transfer_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><b>Data Ruangan</b></label>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Ruang Asal</label>
                            <div class="col-md-8">
                                <input class="form-control nama_ruangan" id="dr1" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Ruang Tujuan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dr2" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Petugas yang menghubungi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dr3" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Petugas penerima telp</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dr4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Berangkat Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon"  >
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="dr5">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon"  >
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="dr6">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alasan Mentransfer</label>
                            <div class="col-md-8">
                                <div class="form-check" >
                                    <input type="checkbox" name="dr7" id="dr71" value="Perlu perawatan lebih lanjut">
                                    <label for="dr71"></label> Perlu perawatan lebih lanjut
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="form-check" >
                                    <input type="checkbox" name="dr7" id="dr72" value="Perlu ICU (Skala Prioritas 1/2/3)">
                                    <label for="dr72"></label> Perlu ICU (Skala Prioritas 1/2/3)
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-1">
                                <div class="form-check" >
                                    <input type="checkbox" name="dr7" id="dr73">
                                    <label for="dr73"></label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input placeholder="Lain-Lain" style="margin-top: -2px; margin-left: -13px" class="form-control" id="ket_dr7" oninput="$('#dr73').val(this.value)">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Derajat Transfer</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dr8" id="dr81" value="Derajat 0">
                                    <label for="dr81"></label> Derajat 0
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="dr8" id="dr82" value="Perawatan Pra PK">
                                    <label for="dr82"></label> Perawatan Pra PK
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dr8" id="dr83" value="Derajat 1">
                                    <label for="dr83"></label> Derajat 1
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="dr8" id="dr84" value="Perawatan (Pra 1)">
                                    <label for="dr84"></label> Perawatan (Pra 1)
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-3">
                                <div class="form-check" >
                                    <input type="checkbox" name="dr8" id="dr85" value="Derajat 2">
                                    <label for="dr85"></label> Derajat 2
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="dr8" id="dr86" value="Perawatan (Pra 2)">
                                    <label for="dr86"></label> Perawatan (Pra 2)
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dr8" id="dr87" value="Derajat 3">
                                    <label for="dr87"></label> Derajat 3
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check" >
                                    <input type="checkbox" name="dr8" id="dr88" value="Perawatan (Pra 2 + Dokter)">
                                    <label for="dr88"></label> Perawatan (Pra 2 + Dokter)
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Medis</label>
                            <div class="col-md-8">
                                <textarea class="form-control diagnosa-pasien" rows="3" id="dr9"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Dokter /petugas yang menunjuk</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dr10" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label><b>Catatan Klinis</b></label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Anamnesis</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control anamnese" id="ck1" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Alergi</label>
                            <div class="col-md-8">
                                <input class="form-control alergi-pasien" id="ck2">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ck3')" type="radio" value="Tidak ada" id="ck31" name="ck3" /><label for="ck31">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ck3')" type="radio" value="Ada" id="ck32" name="ck3" /><label for="ck32">Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-ina-ck3">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check" style="margin-top: 7px">
                                    <input type="checkbox" name="ket_ck3" id="ket_ck31" value="CVA">
                                    <label for="ket_ck31"></label> CVA
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check" style="margin-top: 7px">
                                    <input type="checkbox" name="ket_ck3" id="ket_ck32" value="Diabetes">
                                    <label for="ket_ck32"></label> Diabetes
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="ket_ck3" id="ket_ck33" value="Jantung">
                                    <label for="ket_ck33"></label> Jantung
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="ket_ck3" id="ket_ck34" value="Hipertensi">
                                    <label for="ket_ck34"></label> Hipertensi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-1">
                                <div class="form-check">
                                    <input type="checkbox" name="ket_ck3" id="ket_ck35">
                                    <label for="ket_ck35"></label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input placeholder="Lain-Lain" style="margin-top: -2px; margin-left: -13px" class="form-control" id="ket2_ck3" oninput="$('#ket_ck35').val(this.value)">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">Pengobatan yang sudah diberikan/ tindakan yang sudah dilakukan</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <button onclick="tambahTindakan()" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Tindakan</button>
                            </div>
                        </div>
                        <div id="temp_tindakan">

                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Luka</label>
                            <div class="col-md-8">
                                <input class="form-control" id="ck4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Makan / Minum terakhir jam</label>
                            <div class="col-md-8">
                                <input class="form-control jam" id="ck5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Dokumen yang disertakan</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="ck6" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Peralatan medis yang digunakan</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="ck7" id="ck71" value="Infus">
                                    <label for="ck71"></label> Infus
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="ck7" id="ck72" value="Oksigen">
                                    <label for="ck72"></label> Oksigen
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="ck7" id="ck73" value="CVP">
                                    <label for="ck73"></label> CVP
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check" style="margin-top: -10px">
                                    <input type="checkbox" name="ck7" id="ck74" value="Catheter">
                                    <label for="ck74"></label> Catheter
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check" style="margin-top: -10px">
                                    <input type="checkbox" name="ck7" id="ck75" value="Bidal">
                                    <label for="ck75"></label> Bidal
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check" style="margin-top: -10px">
                                    <input type="checkbox" name="ck7" id="ck76" value="Orotracheal">
                                    <label for="ck76"></label> Orotracheal
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-1">
                                <div class="form-check">
                                    <input type="checkbox" name="ck7" id="ck77">
                                    <label for="ck77"></label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input placeholder="Lain-Lain" style="margin-top: -2px; margin-left: -13px" class="form-control" id="ket_ck7" oninput="$('#ck77').val(this.value)">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Catatan Hal penting</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="ck8" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label><b>Kondisi Pasien Saat Serah Terima</b></label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4"><b>Pemeriksaan</b></label>
                            <label class="col-md-4"><b>Sebelum ditransfer</b></label>
                            <label class="col-md-4"><b>Saat perjalanan</b></label>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Kesadaran Umum</label>
                            <div class="col-md-4">
                                <select onchange="setSideValue('kps2', this.value)" class="form-control" id="kps1">
                                    <option value="">[Select One]</option>
                                    <option value="Baik">Baik</option>
                                    <option value="Cukup">Cukup</option>
                                    <option value="Lemah">Lemah</option>
                                    <option value="Jelek">Jelek</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-control" id="kps2">
                                    <option value="">[Select One]</option>
                                    <option value="Baik">Baik</option>
                                    <option value="Cukup">Cukup</option>
                                    <option value="Lemah">Lemah</option>
                                    <option value="Jelek">Jelek</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Kesadaran / GCS</label>
                            <div class="col-md-4">
                                <input oninput="setSideValue('kps4', this.value)" class="form-control" id="kps3" style="margin-top: 7px" type="number">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" id="kps4" style="margin-top: 7px" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input oninput="setSideValue('kps6', this.value)" class="form-control tensi-pasien" id="kps5" data-inputmask="'mask': ['999/999']" data-mask="">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control tensi-pasien" id="kps6" data-inputmask="'mask': ['999/999']" data-mask="">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input oninput="setSideValue('kps8', this.value)" type="number" class="form-control suhu-pasien" id="kps7">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        &#8451
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control suhu-pasien" id="kps8">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        &#8451
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input oninput="setSideValue('kps10', this.value)" type="number" class="form-control nadi-pasien" id="kps9">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control nadi-pasien" id="kps10">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">RR</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input oninput="setSideValue('kps12', this.value)" type="number" class="form-control rr-pasien" id="kps11">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control rr-pasien" id="kps12">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Saturasi</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input oninput="setSideValue('kps14', this.value)" type="number" class="form-control spo2_pasien" id="kps13">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        %
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control spo2_pasien" id="kps14">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        %
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Skala Nyeri</label>
                            <div class="col-md-4">
                                <input oninput="setSideValue('kps16', this.value)" type="number" class="form-control" id="kps15" style="margin-top: 7px">
                            </div>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="kps16" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Resiko Jatuh</label>
                            <div class="col-md-4">
                                <select onchange="setSideValue('kps18', this.value)" class="form-control" id="kps17" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Ringan">Ringan</option>
                                    <option value="Sedang">Sedang</option>
                                    <option value="Berat">Berat</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-control" id="kps18" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Ringan">Ringan</option>
                                    <option value="Sedang">Sedang</option>
                                    <option value="Berat">Berat</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Lain-Lain</label>
                            <div class="col-md-4">
                                <input oninput="setSideValue('kps20', this.value)" class="form-control" id="kps19" style="margin-top: 7px">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" id="kps20" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>Perawat Pengirim</label>
                                <canvas class="paint-canvas-ttd" id="perawat_pengirim" width="220"
                                        onmouseover="paintTtd('perawat_pengirim')"></canvas>
                                <input class="form-control nama_petugas" id="nama_terang_pengirim" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control nip_petugas" id="nip_perawat_pengirim" placeholder="NIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('perawat_pengirim')"><i
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
                <button id="save_ina_add_transfer_pasien" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('add_transfer_pasien','transfer_pasien')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_add_transfer_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ttd">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> TTD Penerima
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ttd">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ttd"></p>
                </div>
                <div class="modal-body">
                    <div class="box-body">
                        <div class="form-group" style="display: none">
                            <div class="col-md-1">
                                <input type="color" style="margin-left: -6px; margin-top: -8px"
                                       class="js-color-picker  color-picker pull-left">
                            </div>
                            <div class="col-md-9">
                                <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72"
                                       value="1">
                            </div>
                            <div class="col-md-2">
                                <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-8">
                                    <label>TTD Penerima</label>
                                    <canvas class="paint-canvas-ttd del-canvas" id="ttd_edit" width="300"
                                            onmouseover="paintTtd('ttd_edit')"></canvas>
                                    <input class="form-control nama_petugas" id="nama_ttd" placeholder="Nama Terang">
                                    <input style="margin-top: 3px" class="form-control nip_petugas" id="sip_ttd" placeholder="SIP">
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('ttd_edit')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ttd" class="btn btn-success pull-right"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ttd" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
