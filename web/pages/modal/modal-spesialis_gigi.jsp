<div class="modal fade" id="modal-sps-spesialis_gigi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Dan Rencana Perawatan Gigi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_sps_spesialis_gigi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_spesialis_gigi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
                    </div>
                        <button type="button" onclick="showModalSPS('asesmen_gigi')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen Gigi
                        </button>
                        <button type="button" onclick="showModalSPS('rencana_gigi_pasien')" class="btn btn-success"><i class="fa fa-plus"></i> Rencana Perawatan Gigi
                        </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_sps_spesialis_gigi">
                        <tbody>
                        <tr id="row_sps_asesmen_gigi">
                            <td>Asesmen Gigi</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_asesmen_gigi" class="hvr-grow"
                                     onclick="detailSPS('asesmen_gigi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_asesmen_gigi" class="hvr-grow btn-hide" onclick="conSPS('asesmen_gigi', 'spesialis_gigi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_rencana_gigi_pasien">
                            <td>Perawatan Gigi</td>
                            <td width="20%" align="center">
                                <img id="btn_sps_rencana_gigi_pasien" class="hvr-grow"
                                     onclick="detailSPS('rencana_gigi_pasien')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_rencana_gigi_pasien" class="hvr-grow btn-hide" onclick="conSPS('rencana_gigi_pasien', 'spesialis_gigi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-asesmen_gigi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Gigi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_sps_asesmen_gigi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_asesmen_gigi"></p>
                </div>
                <div class="box-body">
                    <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                        <div class="col-md-1">
                            <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker-op  color-picker pull-left">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <div class="text-center">
                                <canvas class="paint-canvas" style="cursor: pointer" id="area_gigi1" onmouseover="paintTtd('area_gigi1', true)"></canvas>
                            </div>
                            <canvas style="display: none" id="area_cek"></canvas>
                            <button style="margin-top: -5px; margin-left: 8px" type="button" class="btn btn-danger" onclick="removePaint('area_gigi1')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Occlusi</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Normal Bite" id="ag11" name="ag1" /><label for="ag11">Normal Bite</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Croos Bite" id="ag12" name="ag1" /><label for="ag12">Croos Bite</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Step Bite" id="ag13" name="ag1" /><label for="ag13">Step Bite</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Torus</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Palatinus" id="ag21" name="ag2" /><label for="ag21">Palatinus</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mandinulanis" id="ag22" name="ag2" /><label for="ag22">Mandinulanis</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Ada" id="ag23" name="ag2" /><label for="ag23">Tidak Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Palatum</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Dalam" id="ag31" name="ag3" /><label for="ag31">Dalam</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Sedang" id="ag32" name="ag3" /><label for="ag32">Sedang</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Rendah" id="ag33" name="ag3" /><label for="ag33">Rendah</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diastema</label>
                            <div class="col-md-9">
                                <input id="ag4" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Lain-Lain</label>
                            <div class="col-md-9">
                                <input id="ag5" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <input id="ag6" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <textarea rows="2" id="ag7" class="form-control resep-pasien"/></div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Rencana Tindak Lanjut</label>
                            <div class="col-md-9">
                                <textarea rows="3" id="ag8" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Pemeriksa</label>
                                <canvas class="paint-canvas-ttd" id="ag9" width="220"
                                        onmouseover="paintTtd('ag9')"></canvas>
                                <input class="form-control nama_petugas" id="nama_terang_petugas" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control nip_petugas" id="sip_petugas" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ag9')"><i
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
                <button id="save_sps_asesmen_gigi" class="btn btn-success pull-right"
                        onclick="saveSPS('asesmen_gigi','spesialis_gigi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_asesmen_gigi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-rencana_gigi_pasien">
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
                     id="warning_sps_rencana_gigi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_sps_rencana_gigi_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                        <div class="col-md-1">
                            <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker-op  color-picker pull-left">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <div class="text-center">
                                <canvas class="paint-canvas" style="cursor: pointer" id="area_gigi2" onmouseover="paintTtd('area_gigi2', true)"></canvas>
                            </div>
                            <button style="margin-top: -5px; margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('area_gigi2')"><i class="fa fa-trash"></i> Clear
                            </button>
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
                                        <input onclick="showKetSPS(this.value, 'gg8')" type="radio" value="Tidak ada" id="gg81" name="gg8" /><label for="gg81">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetSPS(this.value, 'gg8')" type="radio" value="Ada" id="gg82" name="gg8" /><label for="gg82">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="sps-gg8">
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
                                        <input onclick="showKetSPS(this.value, 'gg9')" type="radio" value="Tidak ada" id="gg91" name="gg9" /><label for="gg91">Tidak ada</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="custom02">
                                        <input onclick="showKetSPS(this.value, 'gg9')" type="radio" value="Ada" id="gg92" name="gg9" /><label for="gg92">Ada</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="sps-gg9">
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
                                <input class="form-control nama_dokter" id="nama_terang_gg013" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_gg013" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('gg013')"><i
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
                <button id="save_sps_rencana_gigi_pasien" class="btn btn-success pull-right"
                        onclick="saveSPS('rencana_gigi_pasien','spesialis_gigi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_sps_rencana_gigi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>