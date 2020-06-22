<div class="modal fade" id="modal-icu-keseimbangan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Keseimbangan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_icu_keseimbangan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_keseimbangan"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <button onclick="showModalICU('keseimbangan_icu')" type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Keseimbangan
                            </button>
                        </div>
                        <div class="col-md-6">
                            <div class="pull-right" style="margin-top: 20px">
                                <i class="fa fa-square" style="color: #b3ffb3"></i> Total Per Jam &nbsp;&nbsp;
                                <i class="fa fa-square" style="color: #ffff66"></i> Kumulatif Setiap Jam
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_icu" style="font-size: 12px">
                        <thead id="head_keseimbangan_icu">
                        </thead>
                        <tbody id="body_keseimbangan_icu">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-keseimbangan_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Keseimbangan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_keseimbangan_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_keseimbangan_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2 waktu" id="id1" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                                    <option value="22:00">22:00</option>
                                    <option value="23:00">23:00</option>
                                    <option value="24:00">24:00</option>
                                    <option value="01:00">01:00</option>
                                    <option value="02:00">02:00</option>
                                    <option value="03:00">03:00</option>
                                    <option value="04:00">04:00</option>
                                    <option value="05:00">05:00</option>
                                    <option value="06:00">06:00</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="resus"></div>
                    <div id="darah"></div>
                    <div id="infus"></div>
                    <div id="inpt_keseimbangan_icu"></div>
                    <input type="hidden" id="is_new">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_keseimbangan_icu" class="btn btn-success pull-right" onclick="saveInputan('keseimbangan_icu', 'keseimbangan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_keseimbangan_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>