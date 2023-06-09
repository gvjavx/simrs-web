<div class="modal fade" id="modal-ina-catatan_pemberian">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Catatan Pemberian Obat
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_catatan_pemberian">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_catatan_pemberian"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('catatan_pemberian_obat')" class="btn btn-success"><i class="fa fa-plus"></i> Pemberian Obat
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table style="font-size: 12px" class="table table-striped table-bordered" id="tabel_ina_catatan_pemberian">
                        <thead>
                        <tr>
                            <td width="15%">Waktu</td>
                            <td>Nama Dokter</td>
                            <td>Nama Obat</td>
                            <td>Aturan Pakai</td>
                            <td>Tanggal Mulai</td>
                            <td>Tanggal Stop</td>
                            <td width="10%">TTD Dokter</td>
                            <td width="12%">TTD Apoteker</td>
                            <td width="10%" align="center">Status</td>
                        </tr>
                        </thead>
                        <tbody id="body_catatan_pemberian">

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

<div class="modal fade" id="modal-ina-catatan_pemberian_obat">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Catatan Pemberian Obat
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_catatan_pemberian_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_catatan_pemberian_obat"></p>
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
                                    <input class="form-control tgl" id="cpo1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-3">
                                <select class="form-control" id="cpo2">
                                    <option value="04">04</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="12">12</option>
                                    <option value="16">16</option>
                                    <option value="18">18</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="24">24</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nama Dokter</label>
                            <div class="col-md-8">
                                <input class="form-control" id="cpo3" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                            <div class="col-md-8">
                                <input class="form-control" id="cpo4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Aturan Pakai</label>
                            <div class="col-md-8">
                                <input class="form-control" id="cpo5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal Mulai</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="cpo6">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal Stop</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="cpo7">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemberian Obat</label>
                            <div class="col-md-8">
                                <select class="form-control" id="cpo8" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Ya">Telah diberikan </option>
                                    <option value="T">Tidak diberikan</option>
                                    <option value="K">Ditunda</option>
                                    <option value="A">Reaksi Alergi</option>
                                    <option value="ESO">Reaksi Efek Samping</option>
                                    <option value="TAP">Tidak Ada Persedian Obat</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Dokter</label>
                                <canvas class="paint-canvas-ttd" id="cpo9" width="220"
                                        onmouseover="paintTtd('cpo9')"></canvas>
                                <input class="form-control nama_dokter_ri" id="nama_terang_cpo9" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_cpo9" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('cpo9')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Apotek</label>
                                <canvas class="paint-canvas-ttd" id="cpo10" width="220"
                                        onmouseover="paintTtd('cpo10')"></canvas>
                                <input class="form-control" id="nama_terang_cpo10" placeholder="Nama Terang">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('cpo10')"><i
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
                <button id="save_ina_catatan_pemberian_obat" class="btn btn-success pull-right"
                        onclick="saveCatatanPemberianObat('catatan_pemberian_obat','catatan_pemberian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_catatan_pemberian_obat" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>