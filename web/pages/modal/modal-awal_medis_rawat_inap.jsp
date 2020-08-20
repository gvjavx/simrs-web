<div class="modal fade" id="modal-ina-awal_medis_rawat_inap">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Awal Medis Rawat Inap
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_awal_medis_rawat_inap">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_awal_medis_rawat_inap"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button type="button" class="btn btn-success dropdown-toggle"
                                data-toggle="dropdown" style="height: 34px">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="showModalAsesmenRawatInap('s_o')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Subjective & Objective</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('a_p')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Assesment & Planning</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_medis">
                        <tbody>
                        <tr id="row_ina_s_o">
                            <td>Subjective & Objective</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_s_o" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('s_o')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_a_p">
                            <td>Assesment & Planning</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_a_p" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('a_p')"
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

<div class="modal fade" id="modal-ina-s_o">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Subjective & Objective
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_s_o">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_s_o"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <label class="col-md-12"><b>Anamnesa</b></label>
                        <div class="form-group">
                            <label class="col-md-4">Keluhan Utama</label>
                            <div class="col-md-8">
                                <textarea id="so1" class="form-control anamnese" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-8">
                                <textarea id="so2" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Penyakit Keluarga</label>
                            <div class="col-md-8">
                                <textarea id="so3" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Alergi</label>
                            <div class="col-md-8">
                                <textarea id="so4" class="form-control alergi-pasien" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Status Gizi</label>
                            <div class="col-md-8">
                                <textarea id="so5" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Pemeriksaan Fisik</b></label>
                        <div class="col-md-12">
                            <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                                <div class="col-md-1">
                                    <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker-op  color-picker pull-left">
                                </div>
                            </div>
                            <div class="text-center">
                                <canvas class="paint-canvas" id="area_canvas" onmouseover="paintTtd('area_canvas', true)"></canvas>
                            </div>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('area_canvas')"><i
                                    class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Laborat</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="so6"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Radiologi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="so7"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">ECG</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="so8"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_s_o" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('s_o','awal_medis_rawat_inap')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_s_o" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-a_p">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Assesment & Planning</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_a_p">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_a_p"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Kerja</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ap1"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Banding</label>
                            <div class="col-md-8">
                                <textarea class="form-control diagnosa-pasien" id="ap2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-12"><b>Perencanaan Pelayanan</b></label>
                        <div class="form-group">
                            <label class="col-md-4">Pemeriksaan Penunjang</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ap3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Pengobatan / Tindakan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ap4"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Konsultasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ap5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Hasil yang diharapkan (Prognosa)</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="ap6"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Diisi oleh Dokter yang melakukan pemeriksaan</b></label>
                        <div class="col-md-6">
                            <div class="row" style="padding-top: 30px">
                                <label class="col-md-4">Tanggal</label>
                                <div class="col-md-8">
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input class="form-control tgl" id="ap7">
                                    </div>
                                </div>
                            </div>
                            <div class="row jarak">
                                <label class="col-md-4">Jam</label>
                                <div class="col-md-8">
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-clock-o"></i>
                                        </div>
                                        <input class="form-control jam" id="ap8">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <span class="text-center">TTD DPJP</span>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('ap9')" class="paint-canvas-ttd" id="ap9"></canvas>
                            <input class="form-control" placeholder="Nama Terang" id="nama_terang_ap9">
                            <input style="margin-top: 3px" class="form-control" placeholder="SIP" id="sip_ap9">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ap9')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        </div>
                    </div>
                </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_a_p" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('a_p','awal_medis_rawat_inap')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_a_p" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>