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
                <div class="box-body">
                    <table style="font-size: 12px" class="table table-bordered" id="tabel_ina_catatan_pemberian">
                        <thead>
                        <tr>
                            <td width="15%">Waktu</td>
                            <td>Nama Obat</td>
                            <td>Aturan Pakai</td>
                            <td>Keterangan</td>
                            <td align="center">TTD</td>
                            <td width="10%" align="center">Action</td>
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

<div class="modal fade" id="modal-ina-action_pemberian_obat">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Action Pemberian Obat
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_action_pemberian_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_action_pemberian_obat"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none"
                     id="success_ina_action_pemberian_obat">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p id="msg_success_ina_action_pemberian_obat"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="col-md-3">Jam</label>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="jam">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <input type="checkbox" name="centangan" id="centangan1" value="Benar Pasien">
                                        <label for="centangan1"></label> Benar Pasien
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <input type="checkbox" name="centangan" id="centangan2" value="Benar Obat">
                                        <label for="centangan2"></label> Benar Obat
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <input type="checkbox" name="centangan" id="centangan3" value="Benar Dosis">
                                        <label for="centangan3"></label> Benar Dosis
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <input type="checkbox" name="centangan" id="centangan4" value="Benar Rute Pemeberian">
                                        <label for="centangan4"></label> Benar Rute Pemeberian
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <input type="checkbox" name="centangan" id="centangan5" value="Benar Waktu">
                                        <label for="centangan5"></label> Benar Waktu
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="tipe_cpo">
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px"><span id="title_ttd"></span></label>
                                <canvas class="paint-canvas-ttd" id="cpo9" width="220"
                                        onmouseover="paintTtd('cpo9')"></canvas>
                                <div id="form_apoteker" style="display: none">
                                    <input class="form-control" id="nama_terang_apoteker" placeholder="Nama Terang">
                                    <input class="form-control" id="sip_apoteker" placeholder="SIP">
                                </div>
                                <div id="form_perawat" style="display: none">
                                    <input class="form-control" id="nama_terang_cpo9" placeholder="Nama Terang">
                                </div>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('cpo9')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Perawat</label>
                                <canvas class="paint-canvas-ttd" id="cpo10" width="220"
                                        onmouseover="paintTtd('cpo10')"></canvas>
                                <input class="form-control" id="nama_terang_cpo10" placeholder="Nama Terang">
                                <input class="form-control" id="nip_cpo10" placeholder="NIP">
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
                <button id="save_ina_action_pemberian_obat" class="btn btn-success pull-right"
                        onclick="saveCatatanPemberianObat('action_pemberian_obat','catatan_pemberian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_action_pemberian_obat" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>