<div class="modal fade" id="modal-icu-tindakan_icu">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Pemberian Informasi dan Persetujuan Tindakan Kedokteran
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_icu_tindakan_icu">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_tindakan_icu"></p>
                    </div>
                    <button type="button" onclick="showModalICU('add_tindakan_icu')" class="btn btn-success"><i class="fa fa-plus"></i> Tindakan Dokter
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_icu_data_laporan">
                        <tbody>
                        <tr id="row_icu_tindakan_icu">
                            <td>Persetujuan Tindakan</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_tindakan_icu" class="hvr-grow"
                                     onclick="detailICU('tindakan_icu')"
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

<div class="modal fade" id="modal-icu-add_tindakan_icu">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemberian Informasi dan Persetujuan Tindakan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_icu_add_tindakan_icu">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_icu_add_tindakan_icu"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tindakan</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="tindakan_icu" style="width: 100%" onchange="pilihTindakanICU(this.value)">
                                    <option value="">[Select One]</option>
                                    <option value="Ventilator">Ventilator</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-body" style="display: none" id="form-tindakan-icu">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="icu1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Dokter Pelaksana Tindakan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="icu2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Pemberi Informasi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="icu3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Penerima Informasi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="icu4">
                            </div>
                        </div>
                    </div>
                    <br>
                    <table class="table table-bordered" style="font-size: 12px">
                        <thead>
                        <tr style="font-weight: bold">
                            <td>Jenis Informasi</td>
                            <td>Isi Informasi</td>
                            <td align="center">Check(<i class="fa fa-check"></i>)</td>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan_icu">
                        </tbody>
                    </table>
                    <br>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-7" style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatakan bertanya dan atau diskusi kepada pasien dan/atau keluarganya sedemikian rupa sehingga telah memahaminya</label>
                            <div class="col-md-4">
                                <canvas class="paint-canvas-ttd" id="ttd1" width="220" height="100"
                                        onmouseover="paintTtd('ttd1')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -42px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-7" style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya</label>
                            <div class="col-md-4">
                                <canvas class="paint-canvas-ttd" id="ttd2" width="220" height="100"
                                        onmouseover="paintTtd('ttd2')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -42px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd2')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12" style="text-align: justify">Biaya adalah perkiraan biaya yang harus dibayarkan oleh pihak pasien erdasarkan perkiraan dalam kasus-kasus sewajarnya dan tidak mengikat kedua belah pihak apabila ada perluasan</label>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Nama</label>
                            <div class="col-md-4">
                                <input class="form-control" id="icu5">
                            </div>
                            <label class="col-md-2">Tanggal Lahir</label>
                            <div class="col-md-3">
                                <input class="form-control ptr-tgl" id="icu6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Jenis Kelamin</label>
                            <div class="col-md-4">
                                <select class="form-control" id="icu7">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                            <label class="col-md-2">Tindakan</label>
                            <div class="col-md-3">
                                <input class="form-control" readonly id="icu8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Nama Pasien</label>
                            <div class="col-md-4">
                                <input class="form-control nama-pasien" id="icu9" readonly>
                            </div>
                            <label class="col-md-2">Tanggal Lahir Pasien</label>
                            <div class="col-md-3">
                                <input class="form-control tgl-lahir-pasien" id="icu10" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Alamat Pasien</label>
                            <div class="col-md-9">
                                <textarea class="form-control alamat-pasien" id="icu11"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-5">
                                <label style="margin-left: 8px">TTD Yang Menyatakan</label>
                                <canvas class="paint-canvas-ttd" id="ttd3" width="220" height="100"
                                        onmouseover="paintTtd('ttd3')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -100px; margin-top: 35px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd3')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-offset-5 col-md-3">Saksi</label>
                            <div class="col-md-5">
                                <canvas class="paint-canvas-ttd" id="ttd4" width="220" height="100"
                                        onmouseover="paintTtd('ttd4')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -100px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd4')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                            <div class="col-md-5">
                                <canvas class="paint-canvas-ttd" id="ttd5" width="220" height="100"
                                        onmouseover="paintTtd('ttd5')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -100px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd5')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_icu_add_tindakan_icu" class="btn btn-success pull-right"
                        onclick="saveICU('add_tindakan_icu','tindakan_icu')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_icu_add_tindakan_icu" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>