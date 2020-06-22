<div class="modal fade" id="modal-icu-obat-obatan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Obat Obatan/ Intakea/ Output
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_icu_obat-obatan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_obat-obatan"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="btn-group btn-hide">
                                <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                                </button>
                                <button type="button" class="btn btn-success dropdown-toggle"
                                        data-toggle="dropdown" style="height: 34px">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a onclick="showModalICU('injeksi_icu')" style="cursor: pointer"><i
                                            class="fa fa-plus"></i> Ijeksi</a></li>
                                    <li><a onclick="showModalICU('oral_icu')" style="cursor: pointer"><i
                                            class="fa fa-plus"></i> Oral</a></li>
                                    <li><a onclick="showModalICU('lain_icu')" style="cursor: pointer"><i
                                            class="fa fa-plus"></i> Lain-Lain</a></li>
                                    <li><a onclick="showModalICU('intakea_icu')" style="cursor: pointer"><i
                                            class="fa fa-plus"></i> Intakea</a></li>
                                    <li><a onclick="showModalICU('output_icu')" style="cursor: pointer"><i
                                            class="fa fa-plus"></i> Output</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="pull-right" style="margin-top: 20px">
                                <i class="fa fa-square" style="color: #b3ffb3"></i> Total Per Jam &nbsp;&nbsp;
                                <i class="fa fa-square" style="color: #ffff66"></i> Kumulatif Setiap Jam
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="is_new">
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr id="row_icu_injeksi">
                            <td>Injeksi</td>
                            <td width="20%" align="center"><img id="btn_icu_injeksi" class="hvr-grow"
                                                                onclick="listInputan('injeksi')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_oral">
                            <td>Oral</td>
                            <td width="20%" align="center"><img id="btn_icu_oral" class="hvr-grow"
                                                                onclick="listInputan('oral')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_lain">
                            <td>Lain-Lain</td>
                            <td width="20%" align="center"><img id="btn_icu_lain" class="hvr-grow"
                                                                onclick="listInputan('lain')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_intakea">
                            <td>Intakea</td>
                            <td width="20%" align="center"><img id="btn_icu_intakea" class="hvr-grow"
                                                                onclick="listInputan('intakea')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_icu_output">
                            <td>Output</td>
                            <td width="20%" align="center"><img id="btn_icu_output" class="hvr-grow"
                                                                onclick="listInputan('output')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
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

<div class="modal fade" id="modal-icu-injeksi_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Injeksi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_injeksi_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_injeksi_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2 waktu" id="waktu_injeksi_icu" style="width: 100%">
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
                    <div id="injeksi_icu"></div>
                    <div id="inpt_injeksi_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_injeksi_icu" class="btn btn-success pull-right" onclick="saveInputan('injeksi_icu', 'obat-obatan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_injeksi_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-oral_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Oral
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_oral_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_oral_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_oral_icu" style="width: 100%">
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
                    <div id="oral_icu"></div>
                    <div id="inpt_oral_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_oral_icu" class="btn btn-success pull-right" onclick="saveInputan('oral_icu', 'obat-obatan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_oral_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-lain_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Lain-Lain
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_lain_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_lain_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_lain_icu" style="width: 100%">
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
                    <div id="lain_icu"></div>
                    <div id="inpt_lain_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_lain_icu" class="btn btn-success pull-right" onclick="saveInputan('lain_icu', 'obat-obatan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_lain_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-intakea_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Intakea
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_intakea_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_intakea_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_intakea_icu" style="width: 100%">
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
                    <div id="intakea_icu"></div>
                    <div id="inpt_intakea_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_intakea_icu" class="btn btn-success pull-right" onclick="saveInputan('intakea_icu', 'obat-obatan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_intakea_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-output_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Output
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_output_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_output_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="waktu_output_icu" style="width: 100%">
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
                    <div id="output_icu"></div>
                    <div id="inpt_output_icu"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_output_icu" class="btn btn-success pull-right" onclick="saveInputan('output_icu', 'obat-obatan')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_output_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>