<div class="modal fade" id="modal-ring-admisi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Pre Admisi Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ring_admisi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ring_admisi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_admisi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_admisi"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalRingkasanPasien('pre_admisi')" class="btn btn-success"><i class="fa fa-plus"></i> Pre Admisi
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ring_admisi">
                        <tbody>
                        <tr id="row_ring_pre_admisi">
                            <td>Pre Admisi</td>
                            <td width="20%" align="center">
                                <img id="btn_ring_pre_admisi" class="hvr-grow"
                                     onclick="detailRingkasanPasien('pre_admisi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pre_admisi" class="hvr-grow" onclick="conRing('pre_admisi', 'admisi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-ring-pre_admisi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Pre Admisi Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ring_pre_admisi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ring_pre_admisi"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-7">Adakah hal yang berkaitan dengan keyakinan anda yang perlu kami
                            ketahui ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetRing(this.value, 'pre_keyakinan')" type="radio" value="Ada" id="pre01-01" name="pre01" /><label for="pre01-01">Ada</label>
                                <input onclick="showKetRing(this.value, 'pre_keyakinan')" type="radio" value="Tidak Ada" id="pre01-02" name="pre01" /><label for="pre01-02">Tidak Ada</label>
                            </div>
                            <textarea rows="2" style="display: none" class="form-control" id="form-ring-pre_keyakinan"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda membutuhkan penerjemah bahasa
                            ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetRing(this.value, 'pre_penerjemah')" type="radio" value="Ya" id="pre02-01" name="pre02" /><label for="pre02-01">Ya</label>
                                <input onclick="showKetRing(this.value, 'pre_penerjemah')" type="radio" value="Tidak" id="pre02-02" name="pre02" /><label for="pre02-02">Tidak</label>
                            </div>
                            <input class="form-control" style="display: none" id="form-ring-pre_penerjemah">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda memiliki masalah dalam berbicara,
                            pendengaran, penglihatan ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetRing(this.value, 'pre_indra')" type="radio" value="Ya" id="pre03-01" name="pre03" /><label for="pre03-01">Ya</label>
                                <input onclick="showKetRing(this.value, 'pre_indra')" type="radio" value="Tidak" id="pre03-02" name="pre03" /><label for="pre03-02">Tidak</label>
                            </div>
                            <textarea style="display: none" class="form-control" id="form-ring-pre_indra"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah kontak yang diisi sudah benar ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetRing(this.value, 'pre_kontak')" type="radio" value="Ya" id="pre04-01" name="pre04" /><label for="pre04-01">Ya</label>
                                <input onclick="showKetRing(this.value, 'pre_kontak')" type="radio" value="Tidak" id="pre04-02" name="pre04" /><label for="pre04-02">Tidak</label>
                            </div>
                            <input style="display: none" class="form-control" id="form-ring-pre_kontak" placeholder="No HP" type="number">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7" >Apakah anda membutuhkan alat bantu khusus
                            ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetRing(this.value, 'pre_alat_bantu')" type="radio" value="Ya" id="pre05-01" name="pre05" /><label for="pre05-01">Ya</label>
                                <input onclick="showKetRing(this.value, 'pre_alat_bantu')" type="radio" value="Tidak" id="pre05-02" name="pre05" /><label for="pre05-02">Tidak</label>
                            </div>
                            <select style="display: none" class="form-control" id="form-ring-pre_alat_bantu">
                                <option value="Kursi roda">Kursi roda</option>
                                <option value="Tongkat/kruk">Tongkak/kruk</option>
                                <option value="Brankar">Brankar</option>
                                <option value="Alat bantu dengar">Alat bantu dengar</option>
                                <option value="Asisten pribadi">Asisten pribadi</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda mempunyai riwayat alergi ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input onclick="showKetRing(this.value, 'pre_alergi')" type="radio" value="Ya" id="pre06-01" name="pre06" /><label for="pre06-01">Ya</label>
                                <input onclick="showKetRing(this.value, 'pre_alergi')" type="radio" value="Tidak" id="pre06-02" name="pre06" /><label for="pre06-02">Tidak</label>
                            </div>
                            <select style="display: none; width: 50%" class="form-control" id="form-ring-pre_alergi" onchange="showKetRing(this.value, 'pre_ket_alergi')">
                                <option value="Obat">Obat</option>
                                <option value="Lainnya">Lainnya</option>
                            </select>
                            <textarea style="display: none; margin-top: 7px" class="form-control" id="pre_ket_alergi"></textarea>
                        </div>
                    </div>
                </div>
                <hr class="garis">
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-6">
                            <label>TTD petugas</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('et4')" class="paint-canvas-ttd" id="et4"></canvas>
                            <input class="form-control" id="nama_petugas" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control" id="nip_petugas" placeholder="NIP">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('et4')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <label>TTD Dokter</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('et5')" class="paint-canvas-ttd" id="et5"></canvas>
                            <input class="form-control" id="nama_dokter" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control" id="sip_dokter" placeholder="SIP">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('et5')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ring_pre_admisi" class="btn btn-success pull-right"
                        onclick="saveRingkasanPasien('pre_admisi','admisi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ring_pre_admisi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
