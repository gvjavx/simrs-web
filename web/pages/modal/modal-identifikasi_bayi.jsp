<div class="modal fade" id="modal-rb-identifikasi_bayi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Identifikasi Bayi Baru Lahir
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_rb_identifikasi_bayi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_identifikasi_bayi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_identifikasi_bayi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_identifikasi_bayi"></p>
                    </div>
                    <button type="button" onclick="showModalRB('identifikasi_bayi_lahir')" class="btn btn-success"><i class="fa fa-plus"></i> Identifikasi Bayi
                    </button>
                    <button type="button" onclick="showModalRB('pernyataan_bayi_lahir')" class="btn btn-success"><i class="fa fa-plus"></i> Pernyataan
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_identifikasi_bayi">
                        <tbody>
                        <tr id="row_rb_identifikasi_bayi_lahir">
                            <td>Identifikasi Bayi Baru Lahir</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_identifikasi_bayi_lahir" class="hvr-grow"
                                     onclick="detailRB('identifikasi_bayi_lahir')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">

                                <img id="delete_identifikasi_bayi_lahir" class="hvr-grow btn-hide" onclick="conRB('identifikasi_bayi_lahir', 'identifikasi_bayi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_rb_pernyataan_bayi_lahir">
                            <td>Pernyataan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_pernyataan_bayi_lahir" class="hvr-grow"
                                     onclick="detailRB('pernyataan_bayi_lahir')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pernyataan_bayi_lahir" class="hvr-grow btn-hide" onclick="conRB('pernyataan_bayi_lahir', 'identifikasi_bayi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-rb-identifikasi_bayi_lahir">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Identifikasi Bayi Baru Lahir
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_identifikasi_bayi_lahir">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_identifikasi_bayi_lahir"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">No Rekam Medik</label>
                            <div class="col-md-8">
                                <input class="form-control norm-pasien" id="bl1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Nama Ibu</label>
                            <div class="col-md-8">
                                <input class="form-control nama-pasien" id="bl2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Nama Ayah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="bl3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Tanggal Lahir Bayi</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="bl4">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="bl5">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Nama Bayi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="bl6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Dokter/Bidan Penolong</label>
                            <div class="col-md-8">
                                <input class="form-control" id="bl7">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Jenis Kelamin</label>
                            <div class="col-md-8">
                                <select class="form-control" id="bl8">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Warna Kulit</label>
                            <div class="col-md-8">
                                <input class="form-control" id="bl9">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Apgar Skor</label>
                            <div class="col-md-8">
                                <input class="form-control" id="bl10">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Berat Badan</label>
                            <div class="col-md-3">
                                <input class="form-control" id="bl11" placeholder="gram" type="number">
                            </div>
                            <label class="col-md-2">Panjang Badan</label>
                            <div class="col-md-3">
                                <input class="form-control" id="bl12" placeholder="cm" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Lingkar Kepala</label>
                            <div class="col-md-3">
                                <input class="form-control" id="bl13" placeholder="cm" type="number">
                            </div>
                            <label class="col-md-2">Lingkar Lengan</label>
                            <div class="col-md-3">
                                <input class="form-control" id="bl14" placeholder="cm" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Keterangan</label>
                            <div class="col-md-8">
                                <textarea rows="5" class="form-control" id="bl15"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Penentu Jenis Kelamin</label>
                                <canvas class="paint-canvas-ttd" id="ttd1" width="220"
                                        onmouseover="paintTtd('ttd1')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd1" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd1" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Dokter DPJP</label>
                                <canvas class="paint-canvas-ttd" id="ttd2" width="220"
                                        onmouseover="paintTtd('ttd2')"></canvas>
                                <input style="margin-left: 10px" class="form-control nama_dokter_ri" id="nama_terang_ttd2" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control sip_dokter_ri" id="sip_ttd2" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd2')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Bidan Kamar Bersalin</label>
                                <canvas class="paint-canvas-ttd" id="ttd3" width="220"
                                        onmouseover="paintTtd('ttd3')"></canvas>
                                <input style="margin-left: 10px" class="form-control nama_petugas" id="nama_terang_ttd3" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control sip_petugas" id="sip_ttd3" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd3')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Perawat Ruang Bayi</label>
                                <canvas class="paint-canvas-ttd" id="ttd4" width="220"
                                        onmouseover="paintTtd('ttd4')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd4" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd4" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd4')"><i
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
                <button id="save_rb_identifikasi_bayi_lahir" class="btn btn-success pull-right"
                        onclick="saveRB('identifikasi_bayi_lahir','identifikasi_bayi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_identifikasi_bayi_lahir" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rb-pernyataan_bayi_lahir">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pernyataan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_pernyataan_bayi_lahir">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_pernyataan_bayi_lahir"></p>
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
                                    <input class="form-control tgl" id="p1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12">Saya menyatakan pada saat saya pulang, telah menerima bayi saya, memeriksa dan menyakinkan bahwa bayi
                            tersebut adalah betul-betul anak saya. Saya mengecek pengenalnya adalah gelang yang berisikan keterangan pengenal nama dan rekam medik yang sesuai</label>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Bidan/Perawat</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_pernyataan_bayi_lahir" width="220"
                                        onmouseover="paintTtd('ttd1_pernyataan_bayi_lahir')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd11" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd11" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_pernyataan_bayi_lahir')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Ibu</label>
                                <canvas class="paint-canvas-ttd" id="ttd2_pernyataan_bayi_lahir" width="220"
                                        onmouseover="paintTtd('ttd2_pernyataan_bayi_lahir')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd22" placeholder="Nama Terang">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd2_pernyataan_bayi_lahir')"><i
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
                <button id="save_rb_pernyataan_bayi_lahir" class="btn btn-success pull-right"
                        onclick="saveRB('pernyataan_bayi_lahir','identifikasi_bayi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_pernyataan_bayi_lahir" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>