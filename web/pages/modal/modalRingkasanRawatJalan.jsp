<div class="modal fade" id="modal-ina-ringkasan_rj">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Profil Rekam Medis Rawat Jalan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_ringkasan_rj">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_ringkasan_rj"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('ringkasan_rj_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Profil Rawat Jalan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" style="font-size: 12px">
                        <thead>
                        <tr>
                            <td>Tanggal dan Jam</td>
                            <td>Anamnese</td>
                            <td>Pemeriksaan Fisik/Penunjang</td>
                            <td>Diagnosa</td>
                            <td>Planing/Terapi</td>
                        </tr>
                        </thead>
                        <tbody id="body_ringkasan_rj">
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

<div class="modal fade" id="modal-ina-ringkasan_rj_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Profil Rekam Medis Rawat Jalan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ringkasan_rj_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ringkasan_rj_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="rj1">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="rj2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Anamnese</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="rj3" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemeriksaan Fisik/Penunjang</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="rj4" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diagnosa</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="rj5" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Planing/Terapi</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="rj6" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ringkasan_rj_pasien" class="btn btn-success pull-right"
                        onclick="saveRekamMedisRJ('ringkasan_rj_pasien','ringkasan_rj')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ringkasan_rj_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>