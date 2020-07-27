<div class="modal fade" id="modal-ina-transfer_pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Transfer Pasien Antar Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_transfer_pasien">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_transfer_pasien"></p>
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
                            <li><a onclick="showModalAsesmenRawatInap('data_ruangan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Data Ruangan</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('catatan_klinis')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Catatan Klinis</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('kondisi_serah_terima')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Kondisi Pasien Saat Serah Terima</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_transfer_pasien">
                        <tbody>
                        <tr id="row_ina_data_ruangan">
                            <td>Data Ruangan</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_data_ruangan" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('data_ruangan')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_catatan_klinis">
                            <td>Catatan Klinis</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_catatan_klinis" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('catatan_klinis')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_kondisi_serah_terima">
                            <td>Kondisi Pasien Saat Serah Terima</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_kondisi_serah_terima" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('kondisi_serah_terima')"
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

<div class="modal fade" id="modal-ina-data_ruangan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Data Ruangan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_data_ruangan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_data_ruangan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Ruang Asal</label>
                            <div class="col-md-8">
                                <input class="form-control" id="dr1" style="margin-top: 7px">
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
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_data_ruangan" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('data_ruangan','transfer_pasien')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_data_ruangan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-catatan_klinis">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Catatan Klinis
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_catatan_klinis">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_catatan_klinis"></p>
                </div>
                <div class="box-body">
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
                                <%--<div class="custom02">--%>
                                    <%--<input type="radio" value="Tidak" id="ck21" name="ck2" /><label for="ck21">Tidak</label>--%>
                                <%--</div>--%>
                            </div>
                            <%--<div class="col-md-3">--%>
                                <%--<div class="custom02">--%>
                                    <%--<input type="radio" value="Tidak" id="ck22" name="ck2" /><label for="ck22">Ya</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
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
                                    <input type="checkbox" name="ck7" id="ck74" value="Cetater">
                                    <label for="ck74"></label> Cetater
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
                                    <input type="checkbox" name="ck7" id="ck76" value="Ortotrakeal">
                                    <label for="ck76"></label> Ortotrakeal
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
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_catatan_klinis" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('catatan_klinis','transfer_pasien')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_catatan_klinis" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-kondisi_serah_terima">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Kondisi Pasien Saat Serah Terima
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_kondisi_serah_terima">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_kondisi_serah_terima"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Pemeriksaan</label>
                            <label class="col-md-4">Sebelum ditransfer</label>
                            <label class="col-md-4">Saat diterima</label>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Kesadaran Umum</label>
                            <div class="col-md-4">
                                <select class="form-control" id="kps1">
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
                               <input class="form-control" id="kps3" style="margin-top: 7px">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" id="kps4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="kps5">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="kps6">
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
                                    <input type="number" class="form-control" id="kps7">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        C
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="kps8">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        C
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
                                    <input type="number" class="form-control" id="kps9">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="kps10">
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
                                    <input type="number" class="form-control" id="kps11">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        x/mnt
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="kps12">
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
                                    <input type="number" class="form-control" id="kps13">
                                    <div class="input-group-addon" style="font-size: 10px; width: 35%">
                                        %
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="kps14">
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
                                <input class="form-control" id="kps15" style="margin-top: 7px">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" id="kps16" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Resiko Jatuh</label>
                            <div class="col-md-4">
                                <select class="form-control" id="kps17" style="margin-top: 7px">
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
                            <input class="form-control" id="kps19" style="margin-top: 7px">
                        </div>
                        <div class="col-md-4">
                            <input class="form-control" id="kps20" style="margin-top: 7px">
                        </div>
                    </div>
                </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_kondisi_serah_terima" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('kondisi_serah_terima','transfer_pasien')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_kondisi_serah_terima" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>