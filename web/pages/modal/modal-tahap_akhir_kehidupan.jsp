<div class="modal fade" id="modal-icu-tahap_akhir_kehidupan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Pengkajian Pasien Tahap Akhir Kehidupan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_icu_tahap_akhir_kehidupan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_tahap_akhir_kehidupan"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_tahap_akhir_kehidupan">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_tahap_akhir_kehidupan"></p>
                    </div>
                        <button type="button" onclick="showModalICU('pengkajian_medik')" class="btn btn-success"><i class="fa fa-plus"></i> Pengkajian Medik
                        </button>
                        <button type="button" onclick="showModalICU('pengkajian_keperawatan')" class="btn btn-success"><i class="fa fa-plus"></i> Pengkajian Keperawatan
                        </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_icu_tahap_akhir_kehidupan">
                        <tbody>
                        <tr id="row_icu_pengkajian_medik">
                            <td>Pengkajian Medis</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_pengkajian_medik" class="hvr-grow" onclick="detailICU('pengkajian_medik')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pengkajian_medik" class="hvr-grow btn-hide" onclick="conICU('pengkajian_medik', 'tahap_akhir_kehidupan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_icu_pengkajian_keperawatan">
                            <td>Pengkajian Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_pengkajian_keperawatan" class="hvr-grow" onclick="detailICU('pengkajian_keperawatan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pengkajian_keperawatan" class="hvr-grow btn-hide" onclick="conICU('pengkajian_keperawatan', 'tahap_akhir_kehidupan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-icu-pengkajian_medik">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengakajian Medis
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_pengkajian_medik">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_pengkajian_medik"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal Pengkajian</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="tgl_pengkajian_medik">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="jam_pengkajian_medik">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kelas</label>
                            <div class="col-md-9">
                                <input class="form-control" id="01pm">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa Medik</label>
                            <div class="col-md-9">
                                <textarea class="form-control diagnosa-pasien" id="02pm"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">DPJP</label>
                            <div class="col-md-9">
                                <input class="form-control nama_dokter_ri" id="03pm">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>1. Gejala - Gejala</b></label>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-6">
                                <div class="row">
                                    <label class="col-md-12">a. Kegawatan Pernafasan</label>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm11" value="Dypsnue">
                                            <label for="pm11"></label> Dypsnue
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm12" value="Nafas cepat dan dangkal">
                                            <label for="pm12"></label> Nafas cepat dan dangkal
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm1" id="pm13" value="Nafas lambat">
                                            <label for="pm13"></label> Nafas lambat
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm14" value="Nafas tidak beraturan">
                                            <label for="pm14"></label> Nafas tidak beraturan
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm15" value="Nafas melalui mulut">
                                            <label for="pm15"></label> Nafas melalui mulut
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm16" value="Mukosa oral kering">
                                            <label for="pm16"></label> Mukosa oral kering
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm1" id="pm17" value="Ada secret">
                                            <label for="pm17"></label> Ada secret
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm18" value="SpO2 < normal">
                                            <label for="pm18"></label> SpO2 < normal
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm1" id="pm19" value="Tidak ada kelainan">
                                            <label for="pm19"></label> Tidak ada kelainan
                                        </div>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <label>c. Nyeri</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input onclick="checkSelect(this.id, 'pm32', this.value)" type="checkbox" name="pm3" id="pm31" value="Tidak">
                                            <label for="pm31"></label> Tidak
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-check">
                                            <input onclick="checkSelect(this.id, 'pm31', this.value)" type="checkbox" name="pm3" id="pm32" value="Ya">
                                            <label for="pm32"></label> Ya
                                        </div>
                                    </div>
                                    <div class="col-md-9">
                                        <input class="form-control" id="ket_pm3" oninput="$('#pm32').val('Ya, '+this.value)">
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <label>e. Pencernaan</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm51" value="Mual">
                                            <label for="pm51"></label> Mual
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm52" value="Muntah">
                                            <label for="pm52"></label> Muntah
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm53" value="Intoleransi">
                                            <label for="pm53"></label> Intoleransi
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm54" value="NGT">
                                            <label for="pm54"></label> NGT
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <input class="form-control" id="ket_pm54" oninput="$('#pm54').val('NGT, '+this.value)">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm5" id="pm55" value="Lain-Lain">
                                            <label for="pm55"></label> Lain-lain
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <input class="form-control" id="ket_pm55" oninput="$('#pm55').val('Lain-Lain, '+this.value)">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <label class="col-md-12">b. Kehilangan otot tonus</label>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm21" value="Mual">
                                            <label for="pm21"></label> Mual
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm22" value="Penurunan pergerakan tubuh">
                                            <label for="pm22"></label> Penurunan pergerakan tubuh
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm2" id="pm23" value="Sulit bicara">
                                            <label for="pm23"></label> Sulit bicara
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm24" value="Sulit menelan">
                                            <label for="pm24"></label> Sulit menelan
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm25" value="Distensi abdomen">
                                            <label for="pm25"></label> Distensi abdomen
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm26" value="Inkontinensia urine">
                                            <label for="pm26"></label> Inkontinensia urine
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check" >
                                            <input type="checkbox" name="pm2" id="pm27" value="Inkontinensia feces">
                                            <label for="pm27"></label> Inkontinensia feces
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm2" id="pm28" value="Tidak ada kelainan">
                                            <label for="pm28"></label> Tidak ada kelainan
                                        </div>
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <div class="col-md-12">
                                        <label>d. Pelambatan sirkulasi</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm41" value="Bercak dan sianosis pada ekstremitas">
                                            <label for="pm41"></label> Bercak dan sianosis pada ekstremitas
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm42" value="Gelisah">
                                            <label for="pm42"></label> Gelisah
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm43" value="Gelisah">
                                            <label for="pm43"></label> Lemas
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm44" value="Kulit dengin dan berkeringat">
                                            <label for="pm44"></label> Kulit dengin dan berkeringat
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm45" value="Tekanan darah menurun">
                                            <label for="pm45"></label> Tekanan darah menurun
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-check">
                                            <input type="checkbox" name="pm4" id="pm46" value="Nadi lambat dan lemah">
                                            <label for="pm46"></label> Nadi lambat dan lemah
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>2. Faktor-faktor yang ,meningkatkan gejala fisik</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="pm6" id="pm61" value="Melakukan aktifitas fisik">
                                <label for="pm61"></label> Melakukan aktifitas fisik
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="pm6" id="pm62" value="Pindah posisi">
                                <label for="pm62"></label> Pindah posisi
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="pm6" id="pm63" value="Lainnya">
                                <label for="pm63"></label> Lainnya
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-8 col-md-4">
                                <input class="form-control" oninput="$('#pm63').val('Lainnya, '+this.value)" placeholder="Keterangan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>3. Asesment</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea class="form-control" id="pm7"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12"><b>4. Perencanaan</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea class="form-control" id="pm8"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row text-center">
                        <div class="col-md-6">
                            <label >TTD Dokter</label>
                            <canvas class="paint-canvas-ttd" id="ttd_dokter"
                                    onmouseover="paintTtd('ttd_dokter')"></canvas>
                            <input class="form-control nama_dokter_ri" id="nama_terang_dokter" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_dokter" placeholder="SIP">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_dokter')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_pengkajian_medik" class="btn btn-success pull-right" onclick="saveICU('pengkajian_medik', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_pengkajian_medik" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-pengkajian_keperawatan">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_pengkajian_keperawatan">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_pengkajian_keperawatan"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="tgl_pengkajian_keperawatan">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="jam_pengkajian_keperawatan">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="col-md-12">
                            <label><b>1. Orental spiritual pasien dan keluarga</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <div class="row">
                                <div class="col-md-12">
                                    <label>Apakah perlu pelayanan spiritual</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk12', this.value)" type="checkbox" name="pk1" id="pk11" value="Ya">
                                <label for="pk11"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk11', this.value)" type="checkbox" name="pk1" id="pk12" value="Tidak">
                                <label for="pk12"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk11">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk11">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <label><b>2. Urusan dan kebutuhan spiritual dan keluarga seperti putus asa, penderitaan, rasa bersalah atau pengampunan</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <div class="form-check">
                                <input type="checkbox" name="pk02" id="pk21" value="Perlu didoakan">
                                <label for="pk21"></label> Perlu didoakan
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk23', this.value)" type="checkbox" name="pk2" id="pk22" value="Ya">
                                <label for="pk22"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk22', this.value)" type="checkbox" name="pk2" id="pk23" value="Tidak">
                                <label for="pk23"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk22">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk22">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <div class="form-check">
                                <input type="checkbox" name="pk03" id="pk31" value="Perlu didoakan">
                                <label for="pk31"></label> Perlu bimbingan rohani
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk33', this.value)" type="checkbox" name="pk3" id="pk32" value="Ya">
                                <label for="pk32"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk32', this.value)" type="checkbox" name="pk3" id="pk33" value="Tidak">
                                <label for="pk33"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk32">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk32">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <div class="form-check">
                                <input type="checkbox" name="pk04" id="pk41" value="Perlu didoakan">
                                <label for="pk41"></label> Pelu pendamping rohani
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk43', this.value)" type="checkbox" name="pk4" id="pk42" value="Ya">
                                <label for="pk42"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk42', this.value)" type="checkbox" name="pk4" id="pk43" value="Tidak">
                                <label for="pk43"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="icu_pk42">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-5">
                                <input class="form-control" id="ket_pk42">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <label><b>3. Status psikososial pasien dan keluarga</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <label><b>a. Apakah ada orang yang ingin dihubungi saat ini?</b></label>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk52', this.value)" type="checkbox" name="pk5" id="pk51" value="Ya">
                                <label for="pk51"></label> Ya
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk51', this.value)" type="checkbox" name="pk5" id="pk52" value="Tidak">
                                <label for="pk52"></label> Tidak
                            </div>
                        </div>
                    </div>
                    <div id="icu_pk51" style="display: none">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk51" placeholder="Nama">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk52" placeholder="Dimana">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk53" placeholder="Hubungan">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-5 col-md-5">
                                    <input class="form-control" id="ket_pk54" placeholder="No Telepon/HP">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <label><b>b. Bagaimana rencana keperawatan selajutnya?</b></label>
                        </div>
                        <div class="col-md-7">
                            <div class="form-check">
                                <input onclick="checkSelect(this.id, 'pk62', this.value)" type="checkbox" name="pk6" id="pk61" value="Tetap dirawat dirumah sakit">
                                <label for="pk61"></label> Tetap dirawat dirumah sakit
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-7">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk61', this.value)" type="checkbox" name="pk6" id="pk62" value="Dirawat dirumah">
                                    <label for="pk62"></label> Dirawat dirumah
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="icu_pk62" style="display: none">
                        <div class="row jarak">
                            <div class="col-md-5">
                                <label>Apakah lingkungan rumah sudah disiapkan</label>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk72', this.value)" type="checkbox" name="pk7" id="pk71" value="Ya">
                                    <label for="pk71"></label> Ya
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk71', this.value)" type="checkbox" name="pk7" id="pk72" value="Tidak">
                                    <label for="pk72"></label> Tidak
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <label>Jika ya, apakah anda mampu merawat pasien dirumah</label>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk82', this.value)" type="checkbox" name="pk8" id="pk81" value="Ya">
                                    <label for="pk81"></label> Ya
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk81', this.value)" type="checkbox" name="pk8" id="pk82" value="Tidak">
                                    <label for="pk82"></label> Tidak
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <label>Jika tidak, apakah perlu difasilitasi oleh rumah sakit</label>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk92', this.value)" type="checkbox" name="pk9" id="pk91" value="Ya">
                                    <label for="pk91"></label> Ya
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input onclick="checkSelect(this.id, 'pk91', this.value)" type="checkbox" name="pk9" id="pk92" value="Tidak">
                                    <label for="pk92"></label> Tidak
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <label><b>c. Reaksi pasien atas penyakitnya?</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk101" value="Menyangkal">
                                <label for="pk101"></label> Menyangkal
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk102" value="Sedih/Menangis">
                                <label for="pk102"></label> Sedih/Menangis
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk103" value="Rasa bersalah">
                                <label for="pk103"></label> Rasa bersalah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk104" value="Tidak ada relasi">
                                <label for="pk104"></label> Tidak ada relasi
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk105" value="Marah">
                                <label for="pk105"></label> Marah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk106" value="Takut">
                                <label for="pk106"></label> Takut
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk107" value="Ketidakberdayaan">
                                <label for="pk107"></label> Ketidakberdaya
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk10" id="pk108" value="Tidak ada kontak">
                                <label for="pk108"></label> Tidak ada kontak
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-5">
                            <label><b>d. Reaksi keluarga atas penyakitnya?</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk111" value="Menyangkal">
                                <label for="pk111"></label> Marah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk112" value="Sedih/Menangis">
                                <label for="pk112"></label> Letih/lelah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk113" value="Rasa bersalah">
                                <label for="pk113"></label> Rasa bersalah
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk114" value="Tidak ada relasi">
                                <label for="pk114"></label> Sedih/menangis
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check">
                                <input type="checkbox" name="pk11" id="pk115" value="Keluarga kurang berpartisipasi membuat keputusan dalam perawatan pasien">
                                <label for="pk115"></label> Keluarga kurang berpartisipasi membuat keputusan dalam perawatan pasien
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row text-center">
                        <div class="col-md-6">
                            <label >TTD</label>
                            <canvas class="paint-canvas-ttd" id="ttd_perawat"
                                    onmouseover="paintTtd('ttd_perawat')"></canvas>
                            <input class="form-control nama_petugas" id="nama_terang_perawat" placeholder="Nama Terang">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_perawat')"><i class="fa fa-trash"></i>
                                Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_pengkajian_keperawatan" class="btn btn-success pull-right" onclick="saveICU('pengkajian_keperawatan', 'asesmen_icu')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_pengkajian_keperawatan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
