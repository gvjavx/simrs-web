<div class="modal fade" id="modal-op-pra_bedah">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-check"></i> Asesmen Pra Bedah
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_pra_bedah">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_pra_bedah"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_pra_bedah">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_pra_bedah"></p>
                    </div>
                    <button onclick="showModalOperasi('add_pra_bedah')" type="button" class="btn btn-success btn-hide"><i class="fa fa-plus"></i> Pra Bedah</button>
                </div>
                <input type="hidden" id="h_cek">
                <div class="box-body">
                    <table class="table" id="tabel_op_pra_bedah">
                        <tbody>
                        <tr id="row_op_add_pra_bedah">
                            <td>Asesmen Pra Bedah</td>
                            <td width="20%" align="center">
                                <img id="btn_op_add_pra_bedah" class="hvr-grow" onclick="detailOperasi('add_pra_bedah')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_add_pra_bedah" class="hvr-grow btn-hide" onclick="conOP('add_pra_bedah', 'pra_bedah')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-op-add_pra_bedah">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-check"></i> Tambah Asesmen Pra Bedah
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_add_pra_bedah">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_op_add_pra_bedah"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="success_add_pra_bedah">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_add_pra_bedah"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="tgl1">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="jam1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <span>1. Keluhan</span>
                                <textarea rows="3" class="form-control" id="pra1"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3"></label>
                            <div class="col-md-12">
                                <span>2. Riwayat penyakit pasien dan keluarga</span>
                                <textarea rows="3" class="form-control" id="pra2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3"></label>
                            <div class="col-md-12">
                                <span>3. Riwayat Alergi</span>
                                <div class="row">
                                    <label style="margin-left: 15px" class="col-md-3">Obat-obatan</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="pra3">
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <label style="margin-left: 15px" class="col-md-3">Makanan</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="pra4">
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <label style="margin-left: 15px" class="col-md-3">Suhu/cuaca</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="pra5">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3"></label>
                            <div class="col-md-12">
                                <span>4. Pemeriksaan Fisik</span>
                                <textarea rows="3" class="form-control" id="pra6"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3"></label>
                            <div class="col-md-12">
                                <span>5. Keadaan Pra Bedah</span>
                                <div class="row">
                                    <label style="margin-left: 15px" class="col-md-3">Tinggi Badan (Cm)</label>
                                    <div class="col-md-3">
                                        <input class="form-control" id="pra7" type="number">
                                    </div>
                                    <label class="col-md-2">Nadi (x/menit)</label>
                                    <div class="col-md-3">
                                        <input class="form-control" id="pra8" type="number">
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <label style="margin-left: 15px" class="col-md-3">Berat Badan (Kg)</label>
                                    <div class="col-md-3">
                                        <input class="form-control" id="pra9" type="number">
                                    </div>
                                    <label class="col-md-2">Suhu (&#8451)</label>
                                    <div class="col-md-3">
                                        <input class="form-control" id="pra10" type="number">
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <label style="margin-left: 15px" class="col-md-3">Tekanan Darah (mmHg)</label>
                                    <div class="col-md-3">
                                        <input class="form-control" id="pra11" data-inputmask="'mask': ['999/999']" data-mask="">
                                    </div>
                                </div>
                                <div class="row jarak">
                                    <label style="margin-left: 15px" class="col-md-3">Status Gizi</label>
                                    <div class="col-md-1">
                                        <div class="custom02">
                                            <input type="radio" value="Gemuk" id="pra111" name="pra12" /><label for="pra111">Gemuk</label>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <div class="custom02">
                                            <input type="radio" value="Cukup" id="pra112" name="pra12" /><label for="pra112">Cukup</label>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <div class="custom02">
                                            <input type="radio" value="Kurus" id="pra113" name="pra12" /><label for="pra113">Kurus</label>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="custom02">
                                            <input type="radio" value="Kurang Gizi" id="pra114" name="pra12" /><label for="pra114">Kurang Gizi</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3"></label>
                            <div class="col-md-12">
                                <span>6. Pemeriksaan Penunjang</span>
                                <textarea rows="3" class="form-control" id="pra13"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3"></label>
                            <div class="col-md-12">
                                <span>7. Analisa Data dan Rencana Tindakan</span>
                                <textarea rows="3" class="form-control" id="pra14"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row text-center">
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <div>TTD DPJP</div>
                                <canvas class="paint-canvas-ttd del-canvas" id="ttd_edit" width="300"
                                        onmouseover="paintTtd('ttd_edit')"></canvas>
                                <input class="form-control nama_dokter" id="nama_dpjp_edit" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_dpjp_edit" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_edit')"><i
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
                <button id="save_op_add_pra_bedah" class="btn btn-success pull-right" onclick="saveDataOperasi('add_pra_bedah','pra_bedah')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_add_pra_bedah" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
