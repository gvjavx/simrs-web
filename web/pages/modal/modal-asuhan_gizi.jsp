<div class="modal fade" id="modal-gizi-asuhan_gizi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asuhan Gizi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_gizi_asuhan_gizi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_gizi_asuhan_gizi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_asuhan_gizi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_asuhan_gizi"></p>
                    </div>
                    <button type="button" onclick="showModalGizi('add_asuhan_gizi')" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Asuhan Gizi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_gizi_asuhan_gizi">
                        <tbody>
                        <tr id="row_gizi_add_asuhan_gizi">
                            <td>Asuhan Gizi</td>
                            <td width="20%" align="center">
                                <img id="btn_gizi_add_asuhan_gizi" class="hvr-grow"
                                     onclick="detailGizi('add_asuhan_gizi')"
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

<div class="modal fade" id="modal-gizi-add_asuhan_gizi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asuhan Gizi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_gizi_add_asuhan_gizi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_gizi_add_asuhan_gizi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <label class="col-md-3">Asesmen</label>
                        <div class="col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi1" id="gizi11" value="Tidak ada masalah gizi">
                                <label for="gizi11"></label>  Tidak ada masalah gizi
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi1')" type="checkbox" name="gizi1" id="gizi12" value="Status gizi">
                                <label for="gizi12"></label>  Status gizi
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" oninput="$('#gizi12').val(''); $('#gizi12').val('Status gizi, '+this.value);"
                                   style="display: none" id="form_gizi1" placeholder="Keterangan Status Gizi">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi13')" type="checkbox" name="gizi1" id="gizi13" value="Nilai laboratorium">
                                <label for="gizi13"></label>  Nilai laboratorium
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" oninput="$('#gizi13').val(''); $('#gizi13').val('Nilai laboratorium, '+this.value);"
                                   style="display: none" id="form_gizi13" placeholder="Keterangan Nilai laboratorium">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi14')" type="checkbox" name="gizi1" id="gizi14" value="Fisik/klinis">
                                <label for="gizi14"></label>  Fisik/klinis
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" oninput="$('#gizi14').val(''); $('#gizi14').val('Fisik/klinis, '+this.value);"
                                   style="display: none" id="form_gizi14" placeholder="Keterangan Fisik/klinis">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi15')" type="checkbox" name="gizi1" id="gizi15" value="Asupan makan">
                                <label for="gizi15"></label>  Asupan makan
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-9">
                            <input class="form-control" oninput="$('#gizi15').val(''); $('#gizi15').val('Asupan makan, '+this.value);"
                                   style="display: none" id="form_gizi15" placeholder="Keterangan Asupan makan">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-3">Diagnosa</label>
                        <div class="col-md-9">
                           <textarea class="form-control" id="gizi2"/>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-3">Intervensi Gizi</label>
                        <div class="col-md-9">
                            <span>Diberikan diet</span>
                            <textarea class="form-control" id="gizi3"/>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-4">
                            <span>Energi<small><b>(kkal)</b></small></span>
                            <input class="form-control" id="gizi4" type="number"/>
                        </div>
                        <div class="col-md-4">
                            <span>Protein<small><b>(gram)</b></small></span>
                            <input class="form-control" id="gizi5" type="number"/>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-4">
                            <span>Lemak<small><b>(gram)</b></small></span>
                            <input class="form-control" id="gizi6" type="number"/>
                        </div>
                        <div class="col-md-4">
                            <span>KH<small><b>(gram)</b></small></span>
                            <input class="form-control" id="gizi7" type="number"/>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-3">Rencana Monev</label>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi81" value="Antropometri">
                                <label for="gizi81"></label> Antropometri
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi82" value="Biokimia">
                                <label for="gizi82"></label>  Biokimia
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-3 col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi83" value="Klinik/fisik">
                                <label for="gizi83"></label> Klinik/fisik
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gizi8" id="gizi84" value="Asupan makan">
                                <label for="gizi84"></label>  Asupan makan
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-3">Edukasi</label>
                        <div class="col-md-9">
                            <div class="form-check">
                                <input type="checkbox" name="gizi9" id="gizi91" value="Edukasi gizi dan motivasi mengenai gizi dengan materi">
                                <label for="gizi91"></label>  Edukasi gizi dan motivasi mengenai gizi dengan materi
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="gizi9" id="gizi92">
                                <label for="gizi92"></label>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <input oninput="$('#gizi92').val(''); $('#gizi92').val(this.value);" class="form-control" style="margin-left: -15px; margin-top: -2px">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="gizi9" id="gizi93">
                                <label for="gizi93"></label>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <input oninput="$('#gizi93').val(''); $('#gizi93').val(this.value);" class="form-control" style="margin-left: -15px; margin-top: -2px">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-3 col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="gizi9" id="gizi94">
                                <label for="gizi94"></label>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <input oninput="$('#gizi94').val(''); $('#gizi94').val(this.value);" class="form-control" style="margin-left: -15px; margin-top: -2px">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-6">
                            <label>TTD Pasien/Keluarga</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('gizi_keluarga')" class="paint-canvas-ttd" id="gizi_keluarga"></canvas>
                            <input class="form-control" id="nama_gizi20" placeholder="Nama Terang">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('gizi_keluarga')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <label>TTD Ahli Gizi</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('gizi_dokter')" class="paint-canvas-ttd" id="gizi_dokter"></canvas>
                            <input class="form-control" id="nama_gizi21" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control" id="sip_dokter" placeholder="SIP/NIP">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('gizi_dokter')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_gizi_add_asuhan_gizi" class="btn btn-success pull-right"
                        onclick="saveGizi('add_asuhan_gizi', 'asuhan_gizi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_gizi_add_asuhan_gizi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>