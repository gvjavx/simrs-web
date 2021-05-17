<div class="modal fade" id="modal-ina-asuhan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Rencana Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_asuhan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_asuhan"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('asuhan_keperawatan_ina')" class="btn btn-success"><i class="fa fa-plus"></i> Asuhan Keperawatan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <%--<table style="font-size: 12px" class="table table-striped table-bordered" id="tabel_ina_asuhan">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<td width="10%" style="vertical-align: middle" align="center">Tanggal Jam</td>--%>
                            <%--<td width="20%" style="vertical-align: middle" align="center">Diagnosa Keperawatan</td>--%>
                            <%--<td width="20%" style="vertical-align: middle" align="center">Hasil Luaran Keperawatan</td>--%>
                            <%--<td width="20%" style="vertical-align: middle" align="center">Planning/ Rencana Tindakan</td>--%>
                            <%--<td width="20%" style="vertical-align: middle" align="center">Implementasi</td>--%>
                            <%--<td width="10%" style="vertical-align: middle" align="center">Evaluasi</td>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody id="body_asuhan">--%>

                        <%--</tbody>--%>
                    <%--</table>--%>
                        <table class="table" id="tabel_hd_monitoring_hd">
                            <tbody>
                            <tr id="row_ina_asuhan_keperawatan_ina">
                                <td>Rencana Asuhan Keperawatan Rawat Inap</td>
                                <td width="20%" align="center">
                                    <img id="btn_ina_asuhan_keperawatan_ina" class="hvr-grow" onclick="detailRA('asuhan_keperawatan_ina', 'asuhan', 'ina')"
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

<div class="modal fade" id="modal-ina-asuhan_keperawatan_ina">
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rencana Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_asuhan_keperawatan_ina">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_asuhan_keperawatan_ina"></p>
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
                                <input placeholder="masukkan diagnosa minimal 3 karakter" class="form-control" id="diagnosa_askep" oninput="searchDiagnosaAskep(this.id, 'all')">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <table class="table table-striped table-bordered" style="font-size: 12px">
                        <thead>
                        <tr style="font-weight: bold">
                            <td width="20%">Diagnosis</td>
                            <td width="20%">Tujuan dan Kriteria Hasil</td>
                            <td width="20%">Planning</td>
                            <td width="20%">Implementasi</td>
                            <td width="20%">Evaluasi</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><div id="dia"></div></td>
                            <td><div id="has"></div></td>
                            <td><div id="pla"></div></td>
                            <td><div id="imp"></div></td>
                            <td><div id="eva"></div></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_asuhan_keperawatan_ina" class="btn btn-success pull-right"
                        onclick="saveAsuhanKeperawatan('asuhan_keperawatan_ina','asuhan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_asuhan_keperawatan_ina" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>