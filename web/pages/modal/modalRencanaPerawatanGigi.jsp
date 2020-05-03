<div class="modal fade" id="modal-ina-rencana_gigi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Rencana Perawatan Gigi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_rencana_gigi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_rencana_gigi"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('rencana_gigi_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Rencana Perawatan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_rencana_gigi">
                        <tbody>
                        <tr id="row_ina_rencana_gigi_pasien">
                            <td>Perawatan Gigi</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_rencana_gigi_pasien" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('rencana_gigi_pasien')"
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

<div class="modal fade" id="modal-ina-rencana_gigi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rencana Perawatan Gigi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_rencana_gigi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_rencana_gigi_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <img style="width: 100%" src="<%= request.getContextPath() %>/pages/images/scala-gigi.png">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Golongan Darah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg1">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg2" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Penyakit Jantung</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg31" name="gg3" /><label for="gg31">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg32" name="gg3" /><label for="gg32">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diabetes</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg41" name="gg4" /><label for="gg41">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg42" name="gg4" /><label for="gg42">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Haemopilia</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg51" name="gg5" /><label for="gg51">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg52" name="gg5" /><label for="gg52">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Hepatitis</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg61" name="gg6" /><label for="gg61">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg62" name="gg6" /><label for="gg62">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Gastritis</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Tidak ada" id="gg71" name="gg7" /><label for="gg71">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input type="radio" value="Ada" id="gg72" name="gg7" /><label for="gg72">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Penyakit Lainnya</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetIna(this.value, 'gg8')" type="radio" value="Tidak ada" id="gg81" name="gg8" /><label for="gg81">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetIna(this.value, 'gg8')" type="radio" value="Ada" id="gg82" name="gg8" /><label for="gg82">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-gg8">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="col-md-10">
                                    <input class="form-control" id="ket_gg8">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Alergi Obat</label>
                            <div class="col-md-8">
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetIna(this.value, 'gg9')" type="radio" value="Tidak ada" id="gg91" name="gg9" /><label for="gg91">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetIna(this.value, 'gg9')" type="radio" value="Ada" id="gg92" name="gg9" /><label for="gg92">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-gg9">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="col-md-10">
                                    <input class="form-control" id="ket_gg9">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Alergi Makanan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg10">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Obat yang sedang dikonsumsi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="gg011" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal pencatatan data</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="gg012" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD</label>
                                <canvas class="paint-canvas-ttd" id="gg013" width="220"
                                        onmouseover="paintTtd('gg013')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('gg013')"><i
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
                <button id="save_ina_rencana_gigi_pasien" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('rencana_gigi_pasien','rencana_gigi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_rencana_gigi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>