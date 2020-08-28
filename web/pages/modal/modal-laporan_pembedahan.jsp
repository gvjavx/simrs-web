<div class="modal fade" id="modal-rb-laporan_pembedahan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Laporan Pembedahan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_rb_laporan_pembedahan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_laporan_pembedahan"></p>
                    </div>
                    <button type="button" onclick="showModalRB('data_laporan_pembedahan_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Data Laporan
                    </button>
                    <button type="button" onclick="showModalRB('laporan_pembedahan_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Laporan Pembedahan
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_data_laporan">
                        <tbody>
                        <tr id="row_rb_data_laporan_pembedahan_rb">
                            <td>Data Laporan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_data_laporan_pembedahan_rb" class="hvr-grow"
                                     onclick="detailRB('data_laporan_pembedahan_rb')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_rb_laporan_pembedahan_rb">
                            <td>Laporan Pembedahan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_laporan_pembedahan_rb" class="hvr-grow"
                                     onclick="detailRB('laporan_pembedahan_rb')"
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

<div class="modal fade" id="modal-rb-data_laporan_pembedahan_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Data Laporan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_data_laporan_pembedahan_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_data_laporan_pembedahan_rb"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Pra Bedah</label>
                            <div class="col-md-8">
                                <textarea class="form-control diagnosa-pasien" id="la1"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Pasca Bedah</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="la2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Dokter Operator</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Asisten I</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Instrumen</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la5">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Dokter Anestesi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Asisten Anestesi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la7">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Tindakan Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="la8"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Jaringan / Cairan yang diambil</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la9">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Jenis Operasi</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Emergency" id="la101"
                                           name="la10"/><label for="la101">Emergency</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Elektif" id="la102"
                                           name="la10"/><label for="la102">Elektif</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Jenis Pembiusan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la11">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Mulai Operasi</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="la12">
                                </div>
                            </div>
                            <label class="col-md-2">s/d</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam02" id="la13">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Lama Operasi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="la14" readonly onclick="countJam()">
                                <span style="font-size: 12px">* klik pada kolom lama operasi untuk menghitung lama jam</span>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Macam Operasi</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Bersih" id="la151"
                                           name="la15"/><label for="la151">Bersih</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Kotor" id="la152"
                                           name="la15"/><label for="la151">Kotor</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Bersih terkontaminasi" id="la153"
                                           name="la15"/><label for="la153">Bersih terkontaminasi</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Terkontaminasi" id="la154"
                                           name="la15"/><label for="la154">Terkontaminasi</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Pemeriksaan PA</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="la161"
                                           name="la16"/><label for="la161">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="la162"
                                           name="la16"/><label for="la162">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_rb_data_laporan_pembedahan_rb" class="btn btn-success pull-right"
                        onclick="saveRB('data_laporan_pembedahan_rb','laporan_pembedahan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_data_laporan_pembedahan_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rb-laporan_pembedahan_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Laporan Pembedahan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_laporan_pembedahan_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_laporan_pembedahan_rb"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Persiapan Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb1"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Posisi Pasien</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Desifektan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Insisi kulit dan pembukaan lapangan operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb4"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Pendapatan lapangan operasi dan kulit</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Apa yang dikerjakan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb6"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Penutupan lapangan operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb7"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Komplikasi Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb8"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Hasil Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb9"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diskripsi jaringan yang diambil</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb10"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Lain-lain yang perlu</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb11"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Kesimpulan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="rb12"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Dokter Operator</label>
                                <canvas class="paint-canvas-ttd" id="ttd1_laporan_pembedahan_rb" width="220"
                                        onmouseover="paintTtd('ttd1_laporan_pembedahan_rb')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_ttd1" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_ttd1" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1_laporan_pembedahan_rb')"><i
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
                <button id="save_rb_laporan_pembedahan_rb" class="btn btn-success pull-right"
                        onclick="saveRB('laporan_pembedahan_rb','laporan_pembedahan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_laporan_pembedahan_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>