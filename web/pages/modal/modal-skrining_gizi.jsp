<div class="modal fade" id="modal-gizi-skrining_gizi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Skrining Gizi Awal
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_gizi_skrining_gizi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_gizi_skrining_gizi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_skrining_gizi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_skrining_gizi"></p>
                    </div>
                    <button type="button" onclick="showModalGizi('add_skrining_gizi')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen Awal Bayi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_gizi_skrining_gizi">
                        <tbody>
                        <tr id="row_gizi_skrining_gizi">
                            <td>Skrining Gizi Awal</td>
                            <td width="20%" align="center">
                                <img id="btn_gizi_add_skrining_gizi" class="hvr-grow"
                                     onclick="detailRB('add_skrining_gizi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_add_skrining_gizi" class="hvr-grow btn-hide" onclick="conRB('skrining_gizi_gizi', 'asesmen_awal_bayi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-gizi-asesmen_awal_bayi_gizi">
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
                     id="warning_gizi_asesmen_awal_bayi_gizi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_gizi_asesmen_awal_bayi_gizi"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tempat Lahir</label>
                            <div class="col-md-9">
                                <input class="form-control" id="gizi1">
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
                                    <input class="form-control tgl" id="gizi2">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="gizi3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Jenis Kelamin</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="gizi4" style="width: 100%">
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
                                    <input type="checkbox" name="gizi5" id="gizi51" value="Kelainan bawaan">
                                    <label for="gizi51"></label> Kelainan bawaan
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi5" id="gizi52" value="Hamil beberapa kali">
                                <label for="gizi52"></label> Hamil beberapa kali
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi5" id="gizi53" value="Catatan mengenai kehamilan dan partus">
                                <label for="gizi53"></label> Catatan mengenai kehamilan dan partus
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <textarea class="form-control" name="gizi5" id="gizi6"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Penderita IBU, Gangguan Hamil</label>
                            <div class="col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi7" id="gizi71" value="Tidak ada gangguan">
                                    <label for="gizi71"></label> Tidak ada gangguan
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi7" id="gizi72" value="Diabetes / praediabetes">
                                <label for="gizi72"></label> Diabetes / praediabetes
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="gizi7" id="gizi73" value="Toksikose">
                                <label for="gizi73"></label> Toksikose
                            </div>
                        </div>
                        <div class="col-md-6">
                            <input class="form-control" id="ket_gizi73" onchange="$('#gizi73').val('Toksikose, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi7" id="gizi74" value="Penyakit Lain">
                                <label for="gizi74"></label> Penyakit Lain-Lain
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" id="ket_gizi74" onchange="$('#gizi74').val('Penyakit Lain-Lain, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <div class="form-check">
                                <input type="checkbox" name="gizi7" id="gizi75" value="Perdarahan">
                                <label for="gizi75"></label> Perdarahan
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi7" id="gizi76" value="Obat-Obatan">
                                <label for="gizi76"></label> Obat-Obatan
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi7" id="gizi77" value="Riwayat Alergi">
                                <label for="gizi77"></label> Riwayat Alergi
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" id="ket_gizi77" onchange="$('#gizi77').val('Riwayat Alergi, '+this.value)">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Partus</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi8" id="gizi81" value="Masa nifas">
                                    <label for="gizi81"></label> Masa nifas
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi8" id="gizi82" value="Presentasi bayi">
                                    <label for="gizi82"></label> Presentasi bayi
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi83" value="Spontan">
                                <label for="gizi83"></label> Spontan
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi84" value="Forseps">
                                <label for="gizi84"></label> Forseps
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi85" value="Vakum extraksi">
                                <label for="gizi85"></label> Vakum extraksi
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi86" value="Seksio caesaria">
                                <label for="gizi86"></label> Seksio caesaria
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi87" value="Lain - Lain">
                                <label for="gizi87"></label> Lain - Lain
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" id="ket_gizi87" onchange="$('#gizi87').val('Lain - Lain, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi88" value="Dilaksanakan karena">
                                <label for="gizi88"></label> Dilaksanakan karena
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <input class="form-control" id="ket_gizi88" onchange="$('#gizi88').val('Dilaksanakan karena, '+this.value)">
                        </div>
                        <div class="col-md-offset-3 col-md-9 jarak">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi89" value="Narkose">
                                <label for="gizi89"></label> Narkose
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi810" value="Dibantu dengan pengobatan">
                                <label for="gizi810"></label> Dibantu dengan pengobatan
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Komplikasi</label>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi9" id="gizi91" value="Tidak ada">
                                    <label for="gizi91"></label> Tidak ada
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi9" id="gizi92" value="Plasenta praevia">
                                    <label for="gizi92"></label> Plasenta praevia
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi9" id="gizi93" value="Hamil beberapa kali">
                                <label for="gizi93"></label> Komplikasi/tali pusat
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi9" id="gizi94" value="Plasenta praevia">
                                <label for="gizi94"></label> Koplikasi/lain-lain
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi9" id="gizi95" value="Catatan mengenai kehamilan dan partus">
                                <label for="gizi95"></label> Keadaan plasenta/kebutuhan
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <textarea class="form-control" id="gizi96" name="gizi9"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Pemeriksaan Bayi</label>
                            <div class="col-md-4">
                                <input class="form-control" id="gizi10" placeholder="BB (gram)" type="number">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" id="gizi11" placeholder="PB (cm)" type="number">
                            </div>
                            <div class="col-md-offset-3 col-md-4 jarak">
                                <input class="form-control" id="gizi12" placeholder="UK (cm)" type="number">
                            </div>
                            <div class="col-md-4 jarak">
                                <input class="form-control" id="gizi13" placeholder="UD (cm)" type="number">
                            </div>
                            <div class="col-md-offset-3 col-md-9 jarak">
                                <input class="form-control" id="gizi14" placeholder="Nilai apgar menit ke" type="number">
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
                                <input class="form-control" name="gizi16" id="gizi15" placeholder="menit" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi161" id="gizi161" value="Pekerjaan resuitasi lain-lain">
                                <label for="gizi161"></label> Pekerjaan resuitasi lain-lain
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">HB</label>
                            <div class="col-md-4">
                                <input class="form-control" id="gizi17" placeholder="gram %" type="number">
                            </div>
                            <label class="col-md-1">Hot</label>
                            <div class="col-md-4">
                                <input class="form-control" id="gizi18" placeholder="%" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Golongan Darah</label>
                            <div class="col-md-3">
                                <input class="form-control" id="gizi19" placeholder="Bayi">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="gizi20" placeholder="Ibu">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="gizi21" placeholder="Ayah">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Menit ke</label>
                            <div class="col-md-3">
                                <input class="form-control" id="gizi22" placeholder="menit" type="number">
                            </div>
                            <label class="col-md-2"> Menit ke
                            </label>
                            <div class="col-md-3">
                                <input class="form-control" id="gizi23" placeholder="menit" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi241" id="gizi241" value="Perawatan mata Vitamin K1">
                                <label for="gizi241"></label> Perawatan mata Vitamin K1
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <textarea class="form-control diagnosa-pasien" id="gizi24"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Indikasi rawat inap</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="gizi25"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="gizi26"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_asesmen_awal_bayi_gizi" width="220"
                                        onmouseover="paintTtd('ttd1_asesmen_awal_bayi_gizi')"></canvas>
                                <input style="margin-left: 10px" class="form-control nama_dokter_ri" id="nama_terang_ttd1" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control sip_dokter_ri" id="sip_ttd1" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_asesmen_awal_bayi_gizi')"><i
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
                <button id="save_gizi_asesmen_awal_bayi_gizi" class="btn btn-success pull-right"
                        onclick="saveRB('asesmen_awal_bayi_gizi','asesmen_awal_bayi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_gizi_asesmen_awal_bayi_gizi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>