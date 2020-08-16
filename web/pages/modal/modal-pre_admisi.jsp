<div class="modal fade" id="modal-admisi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Admisi Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_pre">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_pre"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-7">Adakah hal yang berkaitan dengan keyakinan anda yang perlu kami
                            ketahui ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ada" id="pre01-01" name="pre01" /><label for="pre01-01">Ada</label>
                                <input type="radio" value="Tidak Ada" id="pre01-02" name="pre01" /><label for="pre01-02">Tidak Ada</label>
                            </div>
                            <textarea rows="2" style="display: none" class="form-control" id="pre_keyakinan"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda membutuhkan penerjemah bahasa
                            ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre02-01" name="pre02" /><label for="pre02-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre02-02" name="pre02" /><label for="pre02-02">Tidak</label>
                            </div>
                            <input class="form-control" style="display: none" id="pre_penerjemah">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda memiliki masalah dalam berbicara,
                            pendengaran, penglihatan ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre03-01" name="pre03" /><label for="pre03-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre03-02" name="pre03" /><label for="pre03-02">Tidak</label>
                            </div>
                            <textarea style="display: none" class="form-control" id="pre_indra"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah kontak yang diisi sudah benar ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre04-01" name="pre04" /><label for="pre04-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre04-02" name="pre04" /><label for="pre04-02">Tidak</label>
                            </div>
                            <input style="display: none" class="form-control" id="pre_kontak">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7" >Apakah anda membutuhkan alat bantu khusus
                            ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre05-01" name="pre05" /><label for="pre05-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre05-02" name="pre05" /><label for="pre05-02">Tidak</label>
                            </div>
                            <select style="display: none" class="form-control" id="pre_alat_bantu">
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
                                <input type="radio" value="Ya" id="pre06-01" name="pre06" /><label for="pre06-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre06-02" name="pre06" /><label for="pre06-02">Tidak</label>
                            </div>
                            <select style="display: none; width: 50%" class="form-control" id="pre_alergi">
                                <option value="Obat">Obat</option>
                                <option value="Lainnya">Lainnya</option>
                            </select>
                            <textarea style="display: none; margin-top: 7px" class="form-control" id="pre_ket_alergi"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" onclick="saveFormAdmisi()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>
