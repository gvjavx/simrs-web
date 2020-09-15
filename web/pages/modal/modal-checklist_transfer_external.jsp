<div class="modal fade" id="modal-ina-checklist_transfer_external">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Checklist Persiapan Transfer Pasien Eksternal
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success alert-dismissible" style="display: none"
                     id="warning_ina_checklist_transfer_external">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_ina_checklist_transfer_external"></p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_checklist_transfer_external">
                    <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                    <p id="msg_checklist_transfer_external"></p>
                </div>
                <div class="box-body btn-hide">
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('transfer_external')" class="btn btn-success"><i class="fa fa-plus"></i> Checklist Persiapan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_checklist_transfer_external">
                        <tbody>
                        <tr id="row_ina_transfer_external">
                            <td>Checklist Persiapan Transfer Pasien Ekternal</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_transfer_external" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('transfer_external')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_transfer_external" class="hvr-grow btn-hide" onclick="conRI('transfer_external', 'checklist_transfer_external')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-ina-transfer_external">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Checklist Persiapan Transfer Pasien Eksternal
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_transfer_external">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_transfer_external"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Nama Pasien</label>
                            <div class="col-md-4">
                                <input class="form-control nama-pasien" id="ina1" readonly>
                            </div>
                            <label class="col-md-2">Tgl Lahir</label>
                            <div class="col-md-3">
                                <input class="form-control tgl-lahir-pasien" id="ina2" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal Masuk</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ina3">
                                </div>
                            </div>
                            <label class="col-md-2">DPJP</label>
                            <div class="col-md-3">
                                <input class="form-control" id="ina4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal transfers</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ina5">
                                </div>
                            </div>
                            <label class="col-md-2">Jam transfers</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ina6">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Dokter konsulen</label>
                            <div class="col-md-4">
                                <input class="form-control" id="ina7">
                            </div>
                            <label class="col-md-2">Unit yang dituju</label>
                            <div class="col-md-3">
                                <input class="form-control" id="ina8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa masuk</label>
                            <div class="col-md-4">
                                <textarea class="form-control diagnosa-pasien" id="ina9"></textarea>
                            </div>
                            <label class="col-md-2">Alasan transfer</label>
                            <div class="col-md-3">
                                <textarea class="form-control" id="ina10"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">

                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina11" id="ina111" value="Kelayakan transfer">
                                    <label for="ina111"></label> Kelayakan transfer
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina12" id="ina121" value="Keadaan Umum">
                                    <label for="ina121"></label> Keadaan Umum
                                </div>
                            </div>
                            <div class="col-md-8">
                                <input id="ket_ina121" class="form-control" oninput="$('#ina121').val('Keadaan Umum, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina12" id="ina122" value="Kesadaran">
                                    <label for="ina122"></label> Kesadaran
                                </div>
                            </div>
                            <div class="col-md-8">
                                <input id="ket_ina122" class="form-control" oninput="$('#ina122').val('Kesadaran, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina12" id="ina123" value="Keluhan">
                                    <label for="ina123"></label> Keluhan
                                </div>
                            </div>
                            <div class="col-md-8">
                                <input id="ket_ina123" class="form-control" oninput="$('#ina123').val('Keluhan, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina12" id="ina124" value="Pemeriksaan tanda vital">
                                    <label for="ina124"></label> Pemeriksaan vital
                                </div>
                            </div>
                            <div class="col-md-2">
                                <input id="ket_ina1241" class="form-control" placeholder="TD">
                            </div>
                            <div class="col-md-2">
                                <input id="ket_ina1242" class="form-control" placeholder="Suhu">
                            </div>
                            <div class="col-md-2">
                                <input id="ket_ina1243" class="form-control" placeholder="Nadi">
                            </div>
                            <div class="col-md-2">
                                <input id="ket_ina1244" class="form-control" placeholder="RR">
                            </div>
                        </div>
                    </div>

                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina13" id="ina13" value="Kesiapan dan kemampuan petugas pendamping transfers sesuai level kondisi pasien">
                                    <label for="ina13"></label> Kesiapan dan kemampuan petugas pendamping transfers sesuai level kondisi pasien
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina14" id="ina141" value="Dokter">
                                    <label for="ina141"></label> Dokter
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina14" id="ina142" value="Kesadaran">
                                    <label for="ina142"></label> Kesadaran
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina14" id="ina143" value="TPK / Petugas keamanan">
                                    <label for="ina143"></label> TPK / Petugas keamanan
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina15" id="ina15" value="Kesiapan dan kelengkapan peralatan">
                                    <label for="ina15"></label> Kesiapan dan kelengkapan peralatan
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina161" value="Tabung Oksigen (isi penuh), Canul 02">
                                    <label for="ina161"></label> Tabung Oksigen (isi penuh), Canul 02
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina162" value="Tiang Infus">
                                    <label for="ina162"></label> Tiang Infus
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina163" value="Monitor EKG, Oximetri (Bila perlu)">
                                    <label for="ina163"></label> Monitor EKG, Oximetri (Bila perlu)
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina164" value="Mesin suction dan Canul (Bila perlu)">
                                    <label for="ina164"></label> Mesin suction dan Canul (Bila perlu)
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina165" value="Alat Resultasi (Ambu bag, Oropharingeal airway, Laringoscop) bila perlu">
                                    <label for="ina165"></label> Alat Resultasi (Ambu bag, Oropharingeal airway, Laringoscop) bila perlu
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina166" value="Tensimeter, Stetoscop">
                                    <label for="ina166"></label> Tensimeter, Stetoscop
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina167" value="Kereta dorong, Kursi roda">
                                    <label for="ina167"></label> Kereta dorong, Kursi roda
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina16" id="ina168" value="Lain-Lain">
                                    <label for="ina168"></label> Lain-Lain
                                </div>
                            </div>
                            <div class="col-md-8">
                                <input class="form-control" id="ket_ina168" oninput="$('#ina168').val('Lain-Lain, '+this.value)">
                            </div>
                        </div>
                    </div>

                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina17" id="ina171" value="Hasil pemeriksaan diagnostik yang disertakan">
                                    <label for="ina171"></label> Hasil pemeriksaan diagnostik yang disertakan
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina18" id="ina181" value="Hasil Laboratorium">
                                    <label for="ina181"></label> Hasil Laboratorium
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina18" id="ina182" value="Hasil Radiologi">
                                    <label for="ina182"></label> Hasil Radiologi
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina18" id="ina183" value="Hasil Lainnya">
                                    <label for="ina183"></label> Hasil Lainnya
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina18" id="ina184" value="Lain-lain">
                                    <label for="ina184"></label> Lain-lain
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina19" id="ina191" value="Obat-Obat yang disertakan">
                                    <label for="ina191"></label> Obat-Obat yang disertakan
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-5">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina20" id="ina201" value="Obat-obat emergency">
                                    <label for="ina201"></label> Obat-obat Emergency
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input id="ket_ina201" class="form-control" oninput="$('#ina201').val('Obat-obat Emergency, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-5">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina20" id="ina202" value="Analgesik dan antipiretik">
                                    <label for="ina202"></label> Analgesik dan antipiretik
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input id="ket_ina202" class="form-control" oninput="$('#ina202').val('Analgesik dan antipiretik, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-5">
                                <div class="form-check" style="margin-left: 30px">
                                    <input type="checkbox" name="ina20" id="ina203" value="Lain-lain">
                                    <label for="ina203"></label> Lain-lain
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input id="ket_ina203" class="form-control" oninput="$('#ina203').val('Lain-lain, '+this.value)">
                            </div>
                        </div>
                    </div>

                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina21" id="ina211" value="Kesiapan alat transportasi (Ambulan dan fasilitas)">
                                    <label for="ina211"></label> Kesiapan alat transportasi (Ambulan dan fasilitas)
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina22" id="ina221" value="Komunikasi dengan unit/RS yang di tuju akan transfer (Kesiapan penerimaan unit/RS yang dituju)">
                                    <label for="ina221"></label> Komunikasi dengan unit/RS yang di tuju akan transfer (Kesiapan penerimaan unit/RS yang dituju)
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina23" id="ina231" value="Surat rujukan / surat pengantaran pemeriksaan untuk unit/RS yang dituju">
                                    <label for="ina231"></label> Surat rujukan / surat pengantaran pemeriksaan untuk unit/RS yang dituju
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina24" id="ina241" value="Penyelesaian administrasi">
                                    <label for="ina241"></label> Penyelesaian administrasi
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ina25" id="ina251" value="Lembar Observasi pasien selama perjalanan">
                                    <label for="ina251"></label> Lembar Observasi pasien selama perjalanan
                                </div>
                            </div>
                        </div>
                    </div>


                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Dokter</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_dokter" width="220"
                                        onmouseover="paintTtd('ttd1_dokter')"></canvas>
                                <input class="form-control" id="nama_terang_dokter" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control" id="sip_dokter" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_dokter')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Pasien</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_pasien" width="220"
                                        onmouseover="paintTtd('ttd1_pasien')"></canvas>
                                <input class="form-control" id="nama_terang_pasien" placeholder="Nama Terang">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_pasien')"><i
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
                <button id="save_ina_transfer_external" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('transfer_external','checklist_transfer_external')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_transfer_external" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>