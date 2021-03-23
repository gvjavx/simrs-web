<div class="modal fade" id="modal-ina-hand_over">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Formulir Serah Terima Antar Shift Jaga (Hand Over)
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_hand_over">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_hand_over"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('hand_over_jaga')" class="btn btn-success"><i class="fa fa-plus"></i> Hand Over
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_admisi">
                        <tbody>
                        <tr id="row_ina_hand_over_jaga">
                            <td>Hand Over</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_hand_over_jaga" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('hand_over_jaga')"
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

<div class="modal fade" id="modal-ina-hand_over_jaga">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Hand Over</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ina_hand_over_jaga">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_hand_over_jaga"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control" id="waktu">
                                    <option class="">[Select One]</option>
                                    <option class="Pagi">Pagi</option>
                                    <option class="Sore">Sore</option>
                                    <option class="Malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Ruangan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ho1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Hari/Tanggal</label>
                            <div class="col-md-5">
                                <select class="form-control" id="ho2">
                                    <option class="">[Select One]</option>
                                    <option class="Senin">Senin</option>
                                    <option class="Selasa">Selasa</option>
                                    <option class="Rabu">Rabu</option>
                                    <option class="Kamis">Kamis</option>
                                    <option class="Jumat">Jumat</option>
                                    <option class="Sabtu">Sabtu</option>
                                    <option class="Minggu">Minggu</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ho3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">DPJP</label>
                            <div class="col-md-9">
                                <input class="form-control nama_dokter_ri" id="ho4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnoses</label>
                            <div class="col-md-9">
                                <input class="form-control diagnosa-pasien" id="ho5">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Keluhan</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ho6"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kesadaran</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ho7"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">GCS (EVM)</label>
                            <div class="col-md-3">
                                <select class="form-control" id="ho8">
                                    <option value="">[Select E]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="X">X</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-control" id="ho9">
                                    <option value="">[Select V]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="X">X</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select class="form-control" id="ho10">
                                    <option value="">[Select M]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="X">X</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tensi <span style="font-size: 11px; font-weight: bold">(mmHg)</span></label>
                            <div class="col-md-3">
                                <input class="form-control tensi-pasien" id="ho11" data-inputmask="'mask': ['999/999']" data-mask="">
                            </div>
                            <label class="col-md-3">Nadi <span style="font-size: 11px; font-weight: bold">(x/menit)</span></label>
                            <div class="col-md-3">
                                <input class="form-control nadi-pasien" id="ho12" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Suhu <span style="font-size: 11px; font-weight: bold">(&#8451)</span></label>
                            <div class="col-md-3">
                                <input class="form-control suhu-pasien" id="ho13">
                            </div>
                            <label class="col-md-3">RR <span style="font-size: 11px; font-weight: bold">(x/menit)</span></label>
                            <div class="col-md-3">
                                <input class="form-control rr-pasien" id="ho14">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Nyeri</label>
                            <div class="col-md-3">
                                <input class="form-control" id="ho15">
                            </div>
                            <label class="col-md-3">Oksigen <span style="font-size: 11px; font-weight: bold">(l/menit)</span></label>
                            <div class="col-md-3">
                                <input class="form-control" id="ho16">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Infus <span style="font-size: 11px; font-weight: bold">(tts/menit)</span></label>
                            <div class="col-md-3">
                                <input class="form-control" id="ho17">
                            </div>
                            <label class="col-md-3">Tranfusi <span style="font-size: 11px; font-weight: bold">(tts/menit)</span></label>
                            <div class="col-md-3">
                                <input class="form-control" id="ho18">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kateter</label>
                            <div class="col-md-3">
                                <select class="form-control" id="ho19">
                                    <option value="">Select</option>
                                    <option value="Ya">Ya</option>
                                    <option value="Tidak">Tidak</option>
                                </select>
                            </div>
                            <label class="col-md-3">NGT</label>
                            <div class="col-md-3">
                                <select class="form-control" id="ho20">
                                    <option value="">Select</option>
                                    <option value="Ya">Ya</option>
                                    <option value="Tidak">Tidak</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Makan/Minum</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ho21">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Toileting</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ho22">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Aktivitas Gerak</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ho23">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa Keperawatan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ho24">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Intervensi yang sudah dilakukan</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ho25"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tindakan Kolaborasi</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ho26"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Rencana Umum</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="ho27"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-6">
                            <span class="text-center">Pemberi Operan</span>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('ho28')" class="paint-canvas-ttd" id="ho28"></canvas>
                            <input class="form-control" placeholder="Nama Terang" id="nama_terang_ho28">
                            <input style="margin-top: 3px" class="form-control" placeholder="NIP" id="nip_ho28">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ho28')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <span class="text-center">Penerima Operan</span>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('ho29')" class="paint-canvas-ttd" id="ho29"></canvas>
                            <input class="form-control" placeholder="Nama Terang" id="nama_terang_ho29">
                            <input style="margin-top: 3px" class="form-control" placeholder="NIP" id="nip_ho29">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ho29')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_hand_over_jaga" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('hand_over_jaga','hand_over')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_hand_over_jaga" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
