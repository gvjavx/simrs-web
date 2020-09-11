<div class="modal fade" id="modal-rb-asesmen_ponek">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Ponek (Material)
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_rb_asesmen_ponek">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_asesmen_ponek"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_asesmen_ponek">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_asesmen_ponek"></p>
                    </div>
                    <button type="button" onclick="showModalRB('asesmen_ponek_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen Ponek
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_asesmen_ponek">
                        <tbody>
                        <tr id="row_rb_asesmen_ponek_rb">
                            <td>Asesmen Ponek</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_asesmen_ponek_rb" class="hvr-grow"
                                     onclick="detailRB('asesmen_ponek_rb')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_asesmen_ponek_rb" class="hvr-grow btn-hide" onclick="conRB('asesmen_ponek_rb', 'asesmen_ponek')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-rb-asesmen_ponek_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Ponek (Material)
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_asesmen_ponek_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_asesmen_ponek_rb"></p>
                </div>
                <div class="box-body">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="po1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="po2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Triase</label>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #d73925"></i>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Merah" id="triase1" name="po3" /><label for="triase1" >Merah</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #e08e0b"></i>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Kuning" id="triase2" name="po3" /><label for="triase2">Kuning</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #008d4c"></i>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Hijau" id="triase3" name="po3" /><label for="triase3">Hijau</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <i class="fa fa-square fa-2x" style="color: #2b2b2b"></i>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: -20px">
                                    <input type="radio" value="Hitam" id="triase4" name="po3" /><label for="triase4">Hitam</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Anamnesa</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="po4"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-12">Pemeriksaan Fisik</label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="po5" type="number" placeholder="GCS">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="po6" type="number" placeholder="TD">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="po7" type="number" placeholder="Nadi">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="po8" type="number" placeholder="RR">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="po9" type="number" placeholder="Suhu">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="po10" type="number" placeholder="TB">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="po11" type="number" placeholder="BB">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="po12" type="number" placeholder="GDA">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="po13" type="number" placeholder="SPO2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Riwayat Penyakit dahulu dan keluarga</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="po14"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Pemeriksaan Obsetetri dan Gynekologi</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="po15"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12">Status Perkawinan</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kawin</label>
                            <div class="col-md-3">
                                <input class="form-control" id="po16" placeholder="kali" type="number">
                            </div>
                            <label class="col-md-3">dengan suami sekarang</label>
                            <div class="col-md-3">
                                <input class="form-control" id="po17" placeholder="tahun" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5">Kawin pertama usia</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po18" placeholder="tahun" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Pendidikan Terakhir</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po19">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Manarce</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po20">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12">Dysmenorhea/tidak</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Siklus menstruasi</label>
                            <div class="col-md-3">
                                <input class="form-control" id="po21">
                            </div>
                            <label class="col-md-3">Siklus menstruasi</label>
                            <div class="col-md-3">
                                <input class="form-control" id="po22" placeholder="hari">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">HPHT</label>
                            <div class="col-md-3">
                                <input class="form-control" id="po23">
                            </div>
                            <label class="col-md-3">HPL</label>
                            <div class="col-md-3">
                                <input class="form-control" id="po24">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">KB Terakhir</label>
                            <div class="col-md-9">
                                <input class="form-control" id="po25">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Alergi</label>
                            <div class="col-md-7">
                                <input class="form-control alergi-pasien" id="po26">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Pemakaian obat saat ini</label>
                            <div class="col-md-7">
                                <input class="form-control alergi-pasien" id="po27">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Riwayat Anternal Care</label>
                            <div class="col-md-7">
                                <input class="form-control alergi-pasien" id="po28">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Riwayat Obstetri</label>
                            <div class="col-md-7">
                                <textarea class="form-control alergi-pasien" id="po29"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Pemeriksaan Penunjang</label>
                            <div class="col-md-7">
                                <textarea class="form-control alergi-pasien" id="po30"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Diagnosa Kerja/Diagnosa Banding</label>
                            <div class="col-md-7">
                                <textarea class="form-control alergi-pasien" id="po31"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Tindakan Terapi</label>
                            <div class="col-md-7">
                                <textarea class="form-control alergi-pasien" id="po32"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12">Tindak lanjut</label>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Rawat inap di</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po33">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Indikasi rawat inap</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po34">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Dipulangkan / kontrol ke poli</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po35">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Dirujuk ke</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po36">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Pulang paksa / menolak tindakan</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po37">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Meninggal</label>
                            <div class="col-md-7">
                                <input class="form-control" id="po38">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Bidang jaga</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_asesmen_ponek_rb" width="220"
                                        onmouseover="paintTtd('ttd1_asesmen_ponek_rb')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd1" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd1" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_asesmen_ponek_rb')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Dokter</label>
                                <canvas class="paint-canvas-ttd" id="ttd3_asesmen_ponek_rb" width="220"
                                        onmouseover="paintTtd('ttd3_asesmen_ponek_rb')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd2" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd2" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd3_asesmen_ponek_rb')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-6">
                                <label style="margin-left: 8px">TTD Pasien dan Keluarga</label>
                                <canvas class="paint-canvas-ttd" id="ttd2_asesmen_ponek_rb" width="220"
                                        onmouseover="paintTtd('ttd2_asesmen_ponek_rb')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd3" placeholder="Nama Terang">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd2_asesmen_ponek_rb')"><i
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
                <button id="save_rb_asesmen_ponek_rb" class="btn btn-success pull-right"
                        onclick="saveRB('asesmen_ponek_rb','asesmen_ponek')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_asesmen_ponek_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>