<div class="modal fade" id="modal-icu-asuhan_keperawatan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_icu_asuhan_keperawatan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_asuhan_keperawatan"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalICU('asuhan_keperawatan_icu')" class="btn btn-success"><i class="fa fa-plus"></i> Asuhan Keperawatan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table style="font-size: 12px" class="table table-striped table-bordered" id="tabel_icu_asuhan_keperawatan">
                        <thead>
                        <tr>
                            <td width="10%" style="vertical-align: middle" align="center">Tanggal Jam</td>
                            <td width="20%" style="vertical-align: middle" align="center">Diagnosa Keperawatan</td>
                            <td width="20%" style="vertical-align: middle" align="center">Planning/ Rencana Tindakan</td>
                            <td width="20%" style="vertical-align: middle" align="center">Tindakan</td>
                        </tr>
                        </thead>
                        <tbody id="body_asuhan_keperawatan">

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

<div class="modal fade" id="modal-icu-asuhan_keperawatan_icu">
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_icu_asuhan_keperawatan_icu">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_icu_asuhan_keperawatan_icu"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ake1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ake2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">Diagnosa</label>
                            <div class="col-md-8">
                                <input class="form-control" id="diagnosa_askep_icu" oninput="searchDiagnosaAskep(this.id, 'icu')">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <table class="table table-striped table-bordered" style="font-size: 12px">
                        <thead>
                        <tr style="font-weight: bold">
                            <td width="20%">Diagnosis</td>
                            <td width="20%">Rencana</td>
                            <td width="20%">Tindakan</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div id="dia"></div>
                            </td>
                            <td><div id="rec"></div></td>
                            <td><select id="tin" class="form-control select2" multiple style="width: 100%"></select></td>
                        </tr>
                        </tbody>
                    </table>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-4">
                                <label>Data Subjektif</label>
                                <select class="form-control select2" id="askep_subjek" onchange="setSubjektif(this.value)" style="width: 100%"> </select>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div id="temp_subjektif"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_icu_asuhan_keperawatan_icu" class="btn btn-success pull-right"
                        onclick="saveAsuhanKeperawatanICU('asuhan_keperawatan_icu','asuhan_keperawatan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_icu_asuhan_keperawatan_icu" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>