<div class="modal fade" id="modal-ic-appendecitomy">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Informed Consent Appendecitomy
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ic_persetujuan_ic">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ic_appendecitomy"></p>
                    </div>
                    <div class="btn-group btn-hide">
                        <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button type="button" class="btn btn-success dropdown-toggle"
                                data-toggle="dropdown" style="height: 34px">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="showModalAppendecitomy('appendecitomy_informasi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Informasi</a></li>
                            <li><a onclick="showModalAppendecitomy('appendecitomy_penyataan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pernyataan</a></li>
                            <li><a onclick="showModalAppendecitomy('appendecitomy_persetujuan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Persetujuan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ic_appendecitomy">
                        <tbody>
                        <tr id="row_ic_appendecitomy_informasi">
                            <td>Informasi</td>
                            <td width="20%" align="center">
                                <img id="btn_ic_appendecitomy_informasi" class="hvr-grow"
                                     onclick="detailAppendecitomy('appendecitomy_informasi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ic_appendecitomy_penyataan">
                            <td>Pernyataan</td>
                            <td width="20%" align="center">
                                <img id="btn_ic_appendecitomy_penyataan" class="hvr-grow"
                                     onclick="detailAppendecitomy('appendecitomy_penyataan')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ic_appendecitomy_persetujuan">
                            <td>Persetujuan</td>
                            <td width="20%" align="center">
                                <img id="btn_ic_appendecitomy_persetujuan" class="hvr-grow"
                                     onclick="detailAppendecitomy('appendecitomy_persetujuan')"
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

<div class="modal fade" id="modal-ic-appendecitomy_informasi">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Informasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ic_appendecitomy_informasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ic_appendecitomy_informasi"></p>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <td width="30%"><b>Jenis Informasi</b></td>
                        <td><b>Isi Informasi</b></td>
                        <td width="15%" align="center"><b>Check (<i class="fa fa-check"></i>)</b></td>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Diagnosis (WD dan DD)</td>
                            <td>
                                Appendicities acute
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai1" id="ai12" value="Ya">
                                    <label for="ai12"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Dasar diagnosis</td>
                            <td>Anamnesa, pemeriksaan klinis, laboratorium</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai2" id="ai21" value="Ya">
                                    <label for="ai21"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tindakan Kedokteran</td>
                            <td>Appendectomy</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai3" id="ai31" value="Ya">
                                    <label for="ai31"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Indikasi Tindakan</td>
                            <td>Appendicities acute
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai4" id="ai41" value="Ya">
                                    <label for="ai41"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tata Cara</td>
                            <td>Pembiusan, irisan jaringan yang ditindak
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai5" id="ai51" value="Ya">
                                    <label for="ai51"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tujuan</td>
                            <td>Kuratif
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai6" id="ai61" value="Ya">
                                    <label for="ai61"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Resiko</td>
                            <td>Sedang, Hal-hal lain yang tidak dapat diprediksi sebelumnya
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai7" id="ai71" value="Ya">
                                    <label for="ai71"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Komplikasi</td>
                            <td>Infeksi luka operasi, streng ilues dan hal-hal yang tidak dapat diprediksi sebelumnya
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai8" id="ai81" value="Ya">
                                    <label for="ai81"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Prognosis</td>
                            <td> Baik
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai9" id="ai91" value="Ya">
                                    <label for="ai91"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Alternatif</td>
                            <td>
                                <div class="row">
                                    <div class="form-group">
                                        <input style="margin-left: 15px" class="form-control" id="ai10">
                                    </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai11" id="ai111" value="Ya">
                                    <label for="ai111"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Perkiraan Biaya</td>
                            <td>
                                <div class="row">
                                    <div class="form-group">
                                        <input style="margin-left: 15px" class="form-control" id="ai012">
                                    </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ai13" id="ai131" value="Ya">
                                    <label for="ai131"></label>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ic_appendecitomy_informasi" class="btn btn-success pull-right"
                        onclick="saveAppendecitomy('appendecitomy_informasi','appendecitomy')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ic_appendecitomy_informasi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ic-appendecitomy_penyataan">
    <div class="modal-dialog modal-md" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pernyataan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ic_appendecitomy_penyataan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ic_appendecitomy_penyataan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group" style="display: none">
                            <div class="col-md-1">
                                <input type="color" style="margin-left: -6px; margin-top: -8px"
                                       class="js-color-picker  color-picker pull-left">
                            </div>
                            <div class="col-md-9">
                                <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72"
                                       value="1">
                            </div>
                            <div class="col-md-2">
                                <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                            </div>
                        </div>
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="text-align: justify">
                                    <div class="row">
                                        <label style="margin-left: 15px">Dengan ini menyatakan bahwa saya Dokter</label>
                                        <input style="margin-left: 15px; width: 80%" class="form-control" id="appen1">
                                        <label style="margin-left: 15px">telah menerangkan hal-hal di atas secara benar
                                            dan jelas dan memberikan kesempatan untuk bertanya dan/atau
                                            berdiskusi</label>
                                    </div>

                                </td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="appendecitomy_penyataan1"
                                            onmouseover="paintTtd('appendecitomy_penyataan1')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="clearConvas('appendecitomy_penyataan1')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: justify">
                                    <div class="row">
                                        <label style="margin-left: 15px">Dengan ini menyatakan bahwa saya Pasien</label>
                                        <input style="margin-left: 15px; width: 80%" class="form-control" id="appen2">
                                        <label style="margin-left: 15px">telah menerima informasi sebagimana di atas
                                            yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan
                                            untuk bertanya/berdiskusi, dan telah memahaminya</label>
                                    </div>
                                </td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="appendecitomy_penyataan2"
                                            onmouseover="paintTtd('appendecitomy_penyataan2')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="clearConvas('appendecitomy_penyataan2')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: justify">
                                    Tanda tangan saksi
                                </td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="appendecitomy_penyataan3"
                                            onmouseover="paintTtd('appendecitomy_penyataan3')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="clearConvas('appendecitomy_penyataan3')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ic_appendecitomy_penyataan" class="btn btn-success pull-right"
                        onclick="saveAppendecitomy('appendecitomy_penyataan','appendecitomy')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ic_appendecitomy_penyataan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ic-appendecitomy_persetujuan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Persetujuan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ic_appendecitomy_persetujuan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ic_appendecitomy_persetujuan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ape1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ape2" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">No KTP</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ape3" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="ape4" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="ape5" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Dengan ini menyatakan</label>
                            <div class="col-md-6">
                                <select class="form-control" id="ape6" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Setuju">Setuju</option>
                                    <option value="Menolak">Menolak</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ape7" style="margin-top: 7px"
                                       value="Appendictomy" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ape8" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ape9" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="ape10" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="ape11" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ic_appendecitomy_persetujuan" class="btn btn-success pull-right"
                        onclick="saveAppendecitomy('appendecitomy_persetujuan','appendecitomy')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ic_appendecitomy_persetujuan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
