<div class="modal fade" id="modal-ina-catatan_integrasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Catatan Perkembangan Pasien Terintegrasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_catatan_integrasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_catatan_integrasi"></p>
                    </div>
                    <button class="btn btn-success btn-hide" onclick="showModalAsesmenRawatInap('catatan_integrasi_pasien')"><i class="fa fa-plus"></i> Catatan Integrasi Pasien</button>
                </div>
                <div class="box-body">
                    <table style="font-size: 12px" class="table table-striped table-bordered" id="tabel_ina_catatan_integrasi">
                        <thead>
                        <tr>
                            <td rowspan="2" style="vertical-align: middle">Tanggal dan Jam</td>
                            <td rowspan="2" style="vertical-align: middle">PPA</td>
                            <td colspan="2" style="vertical-align: middle" align="center">Hasil Pemeriksaan, Analisa, Rencana Penatalaksaan Pasien</td>
                            <td colspan="2" style="vertical-align: middle" align="center">Paraf/Nama</td>
                        </tr>
                        <tr>
                            <td>Jenis</td>
                            <td>Instruksi</td>
                            <td width="10%">Petugas</td>
                            <td width="10%">DPJP</td>
                        </tr>
                        </thead>
                        <tbody id="body_catatan_integrasi">

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

<div class="modal fade" id="modal-ina-catatan_integrasi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Catatan Perkembangan Pasien Terintegrasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_catatan_integrasi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_catatan_integrasi_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="form-group" style="display: none">
                        <div class="col-md-1">
                            <input type="color" style="margin-left: -6px; margin-top: -8px"
                                   class="js-color-picker  color-picker pull-left">
                        </div>
                        <div class="col-md-9">
                            <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72"
                                   value="1">
                        </div>
                        <div class="col-md-2">
                            <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="cp1">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="cp2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">PPA</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="cp3">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jenis</label>
                            <div class="col-md-8">
                                <select class="form-control" id="cp4" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Subyektif">Subyektif</option>
                                    <option value="Obyektif">Obyektif</option>
                                    <option value="Asesmen">Asesmen</option>
                                    <option value="Planning">Planning</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Instruksi</label>
                            <div class="col-md-8">
                                <textarea style="margin-top: 7px" rows="4" class="form-control" id="cp5"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Petugas</label>
                                <canvas class="paint-canvas-ttd" id="cp6" width="220"
                                        onmouseover="paintTtd('cp6')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('cp6')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="cp7" width="220"
                                        onmouseover="paintTtd('cp7')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('cp7')"><i
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
                <button id="save_ina_catatan_integrasi_pasien" class="btn btn-success pull-right"
                        onclick="saveCatatanTerintegrasi('catatan_integrasi_pasien','catatan_integrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_catatan_integrasi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>