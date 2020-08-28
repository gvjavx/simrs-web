<div class="modal fade" id="modal-rb-asesmen_awal_bayi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Awal Bayi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_rb_asesmen_awal_bayi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_asesmen_awal_bayi"></p>
                    </div>
                    <button type="button" onclick="showModalRB('asesmen_awal_bayi_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen Awal Bayi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_asesmen_awal_bayi">
                        <tbody>
                        <tr id="row_rb_asesmen_awal_bayi_rb">
                            <td>Asesmen Awal Bayi</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_asesmen_awal_bayi_rb" class="hvr-grow"
                                     onclick="detailRB('asesmen_awal_bayi_rb')"
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

<div class="modal fade" id="modal-rb-asesmen_awal_bayi_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Awal Bayi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_asesmen_awal_bayi_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_asesmen_awal_bayi_rb"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tempat Lahir</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal Lahir</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="rb2">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="rb3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Jenis Kelamin</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="rb4" style="width: 100%">
                                    <option class="">[Select One]</option>
                                    <option class="Laki-Laki">Laki-Laki</option>
                                    <option class="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Anamnesa</label>
                            <div class="col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="rb5" id="rb51" value="Kelainan bawaan">
                                    <label for="rb51"></label> Kelainan bawaan
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb5" id="rb52" value="Hamil beberapa kali">
                                <label for="rb52"></label> Hamil beberapa kali
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb5" id="rb53" value="Catatan mengenai kehamilan dan partus">
                                <label for="rb53"></label> Catatan mengenai kehamilan dan partus
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <textarea class="form-control" name="rb5" id="rb6"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Penderita IBU, Gangguan Hamil</label>
                            <div class="col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="rb7" id="rb71" value="Tidak ada gangguan">
                                    <label for="rb71"></label> Tidak ada gangguan
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb7" id="rb72" value="Diabetes / praediabetes">
                                <label for="rb72"></label> Diabetes / praediabetes
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="rb7" id="rb73" value="Toksikose">
                                <label for="rb73"></label> Toksikose
                            </div>
                        </div>
                        <div class="col-md-6">
                            <input class="form-control" id="ket_rb73" onchange="$('#rb73').val('Toksikose, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb7" id="rb74" value="Penyakit Lain">
                                <label for="rb74"></label> Penyakit Lain-Lain
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" id="ket_rb74" onchange="$('#rb74').val('Penyakit Lain-Lain, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <div class="form-check">
                                <input type="checkbox" name="rb7" id="rb75" value="Perdarahan">
                                <label for="rb75"></label> Perdarahan
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb7" id="rb76" value="Obat-Obatan">
                                <label for="rb76"></label> Obat-Obatan
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb7" id="rb77" value="Riwayat Alergi">
                                <label for="rb77"></label> Riwayat Alergi
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" id="ket_rb77" onchange="$('#rb77').val('Riwayat Alergi, '+this.value)">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Partus</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="rb8" id="rb81" value="Masa nifas">
                                    <label for="rb81"></label> Masa nifas
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="rb8" id="rb82" value="Presentasi bayi">
                                    <label for="rb82"></label> Presentasi bayi
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb83" value="Spontan">
                                <label for="rb83"></label> Spontan
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb84" value="Forseps">
                                <label for="rb84"></label> Forseps
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb85" value="Vakum extraksi">
                                <label for="rb85"></label> Vakum extraksi
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb86" value="Seksio caesaria">
                                <label for="rb86"></label> Seksio caesaria
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb87" value="Lain - Lain">
                                <label for="rb87"></label> Lain - Lain
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" id="ket_rb87" onchange="$('#rb87').val('Lain - Lain, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb88" value="Dilaksanakan karena">
                                <label for="rb88"></label> Dilaksanakan karena
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <input class="form-control" id="ket_rb88" onchange="$('#rb88').val('Dilaksanakan karena, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb89" value="Narkose">
                                <label for="rb89"></label> Narkose
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb8" id="rb810" value="Dibantu dengan pengobatan">
                                <label for="rb810"></label> Dibantu dengan pengobatan
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Komplikasi</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="rb9" id="rb91" value="Tidak ada">
                                    <label for="rb91"></label> Tidak ada
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="rb9" id="rb92" value="Plasenta praevia">
                                    <label for="rb92"></label> Plasenta praevia
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="rb9" id="rb93" value="Hamil beberapa kali">
                                <label for="rb93"></label> Komplikasi/tali pusat
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="rb9" id="rb94" value="Plasenta praevia">
                                <label for="rb94"></label> Koplikasi/lain-lain
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb9" id="rb95" value="Catatan mengenai kehamilan dan partus">
                                <label for="rb95"></label> Keadaan plasenta/kebutuhan
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <textarea class="form-control" id="rb96" name="rb9"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Pemeriksaan Bayi</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rb10" placeholder="BB (gram)" type="number">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" id="rb11" placeholder="PB (cm)" type="number">
                            </div>
                            <div class="col-md-offset-3 col-md-4 jarak">
                                <input class="form-control" id="rb12" placeholder="UK (cm)" type="number">
                            </div>
                            <div class="col-md-4 jarak">
                                <input class="form-control" id="rb13" placeholder="UD (cm)" type="number">
                            </div>
                            <div class="col-md-offset-3 col-md-9 jarak">
                                <input class="form-control" id="rb14" placeholder="Nilai apgar menit ke" type="number">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12">Tindakan</label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Nafas buatan</label>
                            <div class="col-md-4">
                                <input class="form-control" name="rb16" id="rb15" placeholder="menit" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb161" id="rb161" value="Pekerjaan resuitasi lain-lain">
                                <label for="rb161"></label> Pekerjaan resuitasi lain-lain
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">HB</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rb17" placeholder="gram %" type="number">
                            </div>
                            <label class="col-md-1">Hot</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rb18" placeholder="%" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Golongan Darah</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb19" placeholder="Bayi">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb20" placeholder="Ibu">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb21" placeholder="Ayah">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Menit ke</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb22" placeholder="menit" type="number">
                            </div>
                            <label class="col-md-2"> Menit ke
                            </label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb23" placeholder="menit" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="rb241" id="rb241" value="Perawatan mata Vitamin K1">
                                <label for="rb241"></label> Perawatan mata Vitamin K1
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <textarea class="form-control diagnosa-pasien" id="rb24"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Indikasi rawat inap</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="rb25"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="rb26"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_asesmen_awal_bayi_rb" width="220"
                                        onmouseover="paintTtd('ttd1_asesmen_awal_bayi_rb')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd1" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd1" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_asesmen_awal_bayi_rb')"><i
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
                <button id="save_rb_asesmen_awal_bayi_rb" class="btn btn-success pull-right"
                        onclick="saveRB('asesmen_awal_bayi_rb','asesmen_awal_bayi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_asesmen_awal_bayi_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>