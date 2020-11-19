<div class="modal fade" id="modal-ina-privasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Privasi Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_privasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_privasi"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('privasi_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Privasi Pasien
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_privasi">
                        <tbody>
                        <tr id="row_ina_privasi_pasien">
                            <td>Privasi Pasien</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_privasi_pasien" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('privasi_pasien')"
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

<div class="modal fade" id="modal-ina-privasi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Privasi Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_privasi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_privasi_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><i class="fa fa-circle-o"></i> Data Yang Membuat Pernyataan</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nama</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pp1" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-8">
                                <textarea rows="4" style="margin-top: 7px" class="form-control" id="pp2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Hubungan dengan Pasien</label>
                            <div class="col-md-8">
                                <select class="form-control" id="pp3" style="margin-top: 7px" onchange="showKetIna(this.value, 'pp3')">
                                    <option value="">[Select One]</option>
                                    <option value="Pasien">Pasien</option>
                                    <option value="Suami">Suami</option>
                                    <option value="Istri">Istri</option>
                                    <option value="Orang Tua">Orang Tua</option>
                                    <option value="Lainnya">Lainnya</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pp3">
                            <div class="col-md-offset-3 col-md-8">
                                <input class="form-control" id="ket_pp3" style="margin-top: 7px" placeholder="hubungan lainnya">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><i class="fa fa-circle-o"></i> Data Pasien</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nama Pasien</label>
                            <div class="col-md-8">
                                <input style="margin-top: 7px" class="form-control nama-pasien" id="pp4">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal Lahir</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="pp5">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nomor RM</label>
                            <div class="col-md-8">
                                <input style="margin-top: 7px" class="form-control norm-pasien" id="pp6">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><i class="fa fa-circle-o"></i> Data Orang yang tidak diperbolehkan</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <button class="btn btn-success" onclick="tambahNama()"><i class="fa fa-plus"></i> Tambah Nama</button>
                            </div>
                        </div>
                        <div id="temp_nama"></div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Pembuat Pernyataan</label>
                                <canvas class="paint-canvas-ttd" id="pp7" width="220"
                                        onmouseover="paintTtd('pp7')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('pp7')"><i
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
                <button id="save_ina_privasi_pasien" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('privasi_pasien','privasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_privasi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>