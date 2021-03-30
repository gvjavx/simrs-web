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
                         id="warning_ina_catatan_terintegrasi_ina">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_catatan_terintegrasi_ina"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_catatan_integrasi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_catatan_integrasi"></p>
                    </div>
                    <button class="btn btn-success btn-hide" onclick="showModalAsesmenRawatInap('catatan_integrasi_pasien_ina')"><i class="fa fa-plus"></i> Catatan Integrasi Pasien</button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_hd_monitoring_hd">
                        <tbody>
                        <tr id="row_ina_catatan_integrasi_pasien_ina">
                            <td>Catatan Perkembangan Pasien Terintegrasi</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_catatan_integrasi_pasien_ina" class="hvr-grow" onclick="detailCPPT('catatan_integrasi_pasien_ina', 'catatan_terintegrasi_ina', 'ina')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
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

<div class="modal fade" id="modal-ina-catatan_integrasi_pasien_ina">
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
                     id="warning_ina_catatan_integrasi_pasien_ina">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_catatan_integrasi_pasien_ina"></p>
                </div>
                <div class="modal-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none"
                         id="warning_hd_terintegrasi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_hd_terintegrasi"></p>
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
                                        <input class="form-control tgl" id="cppt1">
                                    </div>
                                </div>
                                <label class="col-md-1" style="margin-top: 7px">Jam</label>
                                <div class="col-md-3">
                                    <div class="input-group" style="margin-top: 7px">
                                        <div class="input-group-addon">
                                            <i class="fa fa-clock-o"></i>
                                        </div>
                                        <input class="form-control jam" id="cppt2">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">PPA</label>
                                <div class="col-md-8">
                                    <select class="form-control" id="cppt3" style="margin-top: 7px">
                                        <option value="">[Select One]</option>
                                        <option value="Dokter">Dokter</option>
                                        <option value="Perawat">Perawat</option>
                                        <option value="Apoteker">Apoteker</option>
                                        <%--<option value="Gizi">Gizi</option>--%>
                                        <option value="Bidan">Bidan</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <label class="col-md-3"><b>S</b>ubjective</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" id="cppt4"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <label class="col-md-3" ><b>O</b>bjective</label>
                                <div class="col-md-4">
                                    <span>Tensi </span> <small>(mmHg)</small>
                                    <input class="form-control tensi-pasien" id="cppt5_tensi" data-inputmask="'mask': ['999/999']" data-mask="">
                                </div>
                                <div class="col-md-4">
                                    <span>Suhu </span> <small>(&#8451)</small>
                                    <input class="form-control suhu-pasien" id="cppt5_suhu" type="number">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-4">
                                    <span>Nadi </span> <small>(x/menit)</small>
                                    <input class="form-control nadi-pasien" id="cppt5_nadi" type="number">
                                </div>
                                <div class="col-md-4">
                                    <span>RR </span> <small>(x/menit)</small>
                                    <input class="form-control rr-pasien" id="cppt5_rr" type="number">
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-8">
                                    <textarea class="form-control" id="ket_cppt5"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <label class="col-md-3" ><b>A</b>ssesment</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" id="cppt6"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <label class="col-md-3" ><b>P</b>lanning</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" id="cppt7"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="form-group">
                                <label class="col-md-3" >Instruksi</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" id="cppt8"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-6 text-center">
                                    <label>TTD Petugas</label>
                                    <canvas class="paint-canvas-ttd del-canvas" id="cppt9" width="220"
                                            onmouseover="paintTtd('cppt9')"></canvas>
                                    <input class="form-control nama_petugas" id="nama_petugas" placeholder="Nama Terang">
                                    <input style="margin-top: 3px" class="form-control nip_petugas" id="sip_petugas" placeholder="SIP">
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('cppt9')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </div>
                                <div class="col-md-6 text-center">
                                    <label>TTD DPJP</label>
                                    <canvas class="paint-canvas-ttd del-canvas" id="cppt10" width="220"
                                            onmouseover="paintTtd('cppt10')"></canvas>
                                    <input class="form-control nama_dokter_ri" id="nama_dpjp" placeholder="Nama Terang">
                                    <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_dpjp" placeholder="SIP">
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('cppt10')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_catatan_integrasi_pasien_ina" class="btn btn-success pull-right"
                        onclick="saveCPPT('catatan_integrasi_pasien_ina','catatan_terintegrasi_ina', 'ina')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_catatan_integrasi_pasien_ina" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-ttd_dpjp">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> TTD DPJP
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ttd_dpjp">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ttd_dpjp"></p>
                </div>
                <div class="modal-body">
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
                                <div class="col-md-offset-2 col-md-8">
                                    <label>TTD DPJP</label>
                                    <canvas class="paint-canvas-ttd del-canvas" id="ttd_edit" width="300"
                                            onmouseover="paintTtd('ttd_edit')"></canvas>
                                    <input class="form-control nama_dokter_ri" id="nama_dpjp_edit" placeholder="Nama Terang">
                                    <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_dpjp_edit" placeholder="SIP">
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('ttd_edit')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ttd_dpjp" class="btn btn-success pull-right"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ttd_dpjp" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>