<div class="modal fade" id="modal-ina-rekonsiliasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Rekonsiliasi Obat
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_rekonsiliasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_rekonsiliasi"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('rekonsiliasi_obat')" class="btn btn-success"><i class="fa fa-plus"></i> Rekonsiliasi Obat
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table style="font-size: 12px" class="table table-striped table-bordered" id="tabel_ina_rekonsiliasi">
                        <thead>
                        <tr>
                            <td rowspan="2" style="vertical-align: middle" align="center">Tanggal</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">Ruang</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">Nama Obat</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">Dosis</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">Aturan Pakai</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">Indikasi</td>
                            <td colspan="2" style="vertical-align: middle" align="center">Diteruskan</td>
                            <td colspan="2" style="vertical-align: middle" align="center">Tanda Tangan</td>
                        </tr>
                        <tr>
                            <td width="5%" align="center">Ya</td>
                            <td width="5%" align="center">Tidak</td>
                            <td width="10%" align="center">Pasien/Keluarga</td>
                            <td width="10%" align="center">Apoteker</td>
                        </tr>
                        </thead>
                        <tbody id="body_rekonsiliasi">

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

<div class="modal fade" id="modal-ina-rekonsiliasi_obat">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rekonsiliasi Obat
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_rekonsiliasi_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_rekonsiliasi_obat"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="reo1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Ruang</label>
                            <div class="col-md-8">
                                <input class="form-control" id="reo2">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                            <div class="col-md-8">
                                <input style="margin-top: 7px" class="form-control" id="reo3">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Dosis</label>
                            <div class="col-md-8">
                                <input style="margin-top: 7px" class="form-control" id="reo4">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Aturan Pakai</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" class="form-control" id="reo5" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Indikasi</label>
                            <div class="col-md-8">
                                <input style="margin-top: 7px" class="form-control" id="reo6">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diteruskan</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="reo71" name="reo7" /><label for="reo71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="reo72" name="reo7" /><label for="reo72">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Pasien/Keluarga</label>
                                <canvas class="paint-canvas-ttd" id="reo_ttd1" width="220"
                                        onmouseover="paintTtd('reo_ttd1')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('reo_ttd1')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Apoteker</label>
                                <canvas class="paint-canvas-ttd" id="reo_ttd2" width="220"
                                        onmouseover="paintTtd('reo_ttd2')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('reo_ttd2')"><i
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
                <button id="save_ina_rekonsiliasi_obat" class="btn btn-success pull-right"
                        onclick="saveRekonsiliasiObat('rekonsiliasi_obat','rekonsiliasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_rekonsiliasi_obat" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>