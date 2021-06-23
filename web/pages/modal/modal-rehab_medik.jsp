<div class="modal fade" id="modal-sps-rehab_medik">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Asesmen Rehab Medik
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_sps_rehab_medik">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_sps_rehab_medik"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
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
                            <li><a onclick="showModalSPS('anamnesa_rehab_medik')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Anamnesis</a></li>
                            <li><a onclick="showModalSPS('pemeriksaan_rehab_medik')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Daftar Masalah Kedokteran Fisik dan Rehabilitas</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr id="row_sps_anamnesa_rehab_medik">
                            <td>Anamnesis</td>
                            <td width="20%" align="center"><img id="btn_sps_anamnesa_rehab_medik" class="hvr-grow"
                                                                onclick="detailSPS('anamnesa_rehab_medik')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_anamnesa_rehab_medik" class="hvr-grow btn-hide" onclick="conSPS('anamnesa_rehab_medik', 'rehab_medik')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_sps_pemeriksaan_rehab_medik">
                            <td>Daftar Masalah Kedokteran Fisik dan Rehabilitas</td>
                            <td width="20%" align="center"><img id="btn_sps_pemeriksaan_rehab_medik" class="hvr-grow"
                                                                onclick="detailSPS('pemeriksaan_rehab_medik')"
                                                                src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pemeriksaan_rehab_medik" class="hvr-grow btn-hide" onclick="conSPS('pemeriksaan_rehab_medik', 'rehab_medik')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-sps-anamnesa_rehab_medik">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Anamnesis
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_anamnesa_rehab_medik">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_anamnesa_rehab_medik"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Tanggal Kunjungan</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="kut1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12">Anamnesis</label>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Keluhan Utama</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="kut2"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Penyakit Sekarang</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="kut3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Penyakit Dahulu</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="kut4"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Penyakit Keluarga</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="kut5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Klinis</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="kut6"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Fungsional</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="kut7"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_anamnesa_rehab_medik" onclick="saveSPS('anamnesa_rehab_medik', 'rehab_medik')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_anamnesa_rehab_medik"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-sps-pemeriksaan_rehab_medik">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-stethoscope"></i> Daftar Masalah Kedokteran Fisik dan Rehabilitas</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_sps_pemeriksaan_rehab_medik">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_sps_pemeriksaan_rehab_medik"></p>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-2 col-md-8">
                            <div class="row">
                                <label class="col-md-4">R1 (Mobilisasi)</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="rm1">
                                </div>
                            </div>
                            <div class="row jarak">
                                <label class="col-md-4">R2 (ADL)</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="rm2">
                                </div>
                            </div>
                            <div class="row jarak">
                                <label class="col-md-4">R3 (Komunikasi)</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="rm3">
                                </div>
                            </div>
                            <div class="row jarak">
                                <label class="col-md-4">R4 (Psikologis)</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="rm4">
                                </div>
                            </div>
                            <div class="row jarak">
                                <label class="col-md-4">R5 (Sosio Ekonomi)</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="rm5">
                                </div>
                            </div>
                            <div class="row jarak">
                                <label class="col-md-4">R6 (Vokasional)</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="rm6">
                                </div>
                            </div>
                            <div class="row jarak">
                                <label class="col-md-4">R7 (Lain-Lain)</label>
                                <div class="col-md-8">
                                    <input class="form-control" id="rm7">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <table class="table table-striped table-responsive table-bordered" style="font-size: 12px" id="tb_rehab_medik">
                                <tbody>
                                <tr>
                                    <td rowspan="2">Regio</td>
                                    <td colspan="6">Pergerakan Sendi</td>
                                    <td colspan="6">Kekuatan Otot</td>
                                </tr>
                                <tr>
                                    <td>Gerakan</td>
                                    <td colspan="5">ROM(aktif/pasif)</td>
                                    <td>Otot</td>
                                    <td colspan="5">MMT</td>
                                </tr>
                                <tr>
                                    <td rowspan="4">Siku</td>
                                    <td>Fleksi</td>
                                    <td colspan="5"><input class="form-control" id="td11"></td>
                                    <td>Fleksor</td>
                                    <td colspan="5"><input class="form-control" id="td12"></td>
                                </tr>
                                <tr>
                                    <td>Ektensi</td>
                                    <td colspan="5"><input class="form-control" id="td21"></td>
                                    <td>Ekstensor</td>
                                    <td colspan="5"><input class="form-control" id="td22"></td>
                                </tr>
                                <tr>
                                    <td>Pronasi</td>
                                    <td colspan="5"><input class="form-control" id="td31"></td>
                                    <td>Pronator</td>
                                    <td colspan="5"><input class="form-control" id="td32"></td>
                                </tr>
                                <tr>
                                    <td>Supinasi</td>
                                    <td colspan="5"><input class="form-control" id="td41"></td>
                                    <td>Supinator</td>
                                    <td colspan="5"><input class="form-control" id="td42"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td></td>
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                </tr>
                                <tr>
                                    <td rowspan="4">Jari Tangan: MCP</td>
                                    <td>Fleksi</td>
                                    <td><input class="form-control" id="td51"></td>
                                    <td><input class="form-control" id="td52"></td>
                                    <td><input class="form-control" id="td53"></td>
                                    <td><input class="form-control" id="td54"></td>
                                    <td><input class="form-control" id="td55"></td>
                                    <td>Fleksor</td>
                                    <td><input class="form-control" id="td56"></td>
                                    <td><input class="form-control" id="td57"></td>
                                    <td><input class="form-control" id="td58"></td>
                                    <td><input class="form-control" id="td59"></td>
                                    <td><input class="form-control" id="td510"></td>
                                </tr>
                                <tr>
                                    <td>Ekstensi</td>
                                    <td><input class="form-control" id="td61"></td>
                                    <td><input class="form-control" id="td62"></td>
                                    <td><input class="form-control" id="td63"></td>
                                    <td><input class="form-control" id="td64"></td>
                                    <td><input class="form-control" id="td65"></td>
                                    <td>Ekstensor</td>
                                    <td><input class="form-control" id="td66"></td>
                                    <td><input class="form-control" id="td67"></td>
                                    <td><input class="form-control" id="td68"></td>
                                    <td><input class="form-control" id="td69"></td>
                                    <td><input class="form-control" id="td610"></td>
                                </tr>
                                <tr>
                                    <td>Abduksi</td>
                                    <td><input class="form-control" id="td71"></td>
                                    <td><input class="form-control" id="td72"></td>
                                    <td><input class="form-control" id="td73"></td>
                                    <td><input class="form-control" id="td74"></td>
                                    <td><input class="form-control" id="td75"></td>
                                    <td>Abductor</td>
                                    <td><input class="form-control" id="td76"></td>
                                    <td><input class="form-control" id="td77"></td>
                                    <td><input class="form-control" id="td78"></td>
                                    <td><input class="form-control" id="td79"></td>
                                    <td><input class="form-control" id="td710"></td>
                                </tr>
                                <tr>
                                    <td>Aduksi</td>
                                    <td><input class="form-control" id="td81"></td>
                                    <td><input class="form-control" id="td82"></td>
                                    <td><input class="form-control" id="td83"></td>
                                    <td><input class="form-control" id="td84"></td>
                                    <td><input class="form-control" id="td85"></td>
                                    <td>Aduktor</td>
                                    <td><input class="form-control" id="td86"></td>
                                    <td><input class="form-control" id="td87"></td>
                                    <td><input class="form-control" id="td88"></td>
                                    <td><input class="form-control" id="td89"></td>
                                    <td><input class="form-control" id="td810"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">PIP</td>
                                    <td>Fleksi</td>
                                    <td><input class="form-control" id="td91"></td>
                                    <td><input class="form-control" id="td92"></td>
                                    <td><input class="form-control" id="td93"></td>
                                    <td><input class="form-control" id="td94"></td>
                                    <td><input class="form-control" id="td95"></td>
                                    <td>Fleksor</td>
                                    <td><input class="form-control" id="td96"></td>
                                    <td><input class="form-control" id="td97"></td>
                                    <td><input class="form-control" id="td98"></td>
                                    <td><input class="form-control" id="td99"></td>
                                    <td><input class="form-control" id="td910"></td>
                                </tr>
                                <tr>
                                    <td>Ekstensi</td>
                                    <td><input class="form-control" id="td101"></td>
                                    <td><input class="form-control" id="td102"></td>
                                    <td><input class="form-control" id="td103"></td>
                                    <td><input class="form-control" id="td104"></td>
                                    <td><input class="form-control" id="td105"></td>
                                    <td>Ekstensor</td>
                                    <td><input class="form-control" id="td106"></td>
                                    <td><input class="form-control" id="td107"></td>
                                    <td><input class="form-control" id="td108"></td>
                                    <td><input class="form-control" id="td109"></td>
                                    <td><input class="form-control" id="td1010"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">DIP</td>
                                    <td>Fleksi</td>
                                    <td><input class="form-control" id="td111"></td>
                                    <td><input class="form-control" id="td112"></td>
                                    <td><input class="form-control" id="td113"></td>
                                    <td><input class="form-control" id="td114"></td>
                                    <td><input class="form-control" id="td115"></td>
                                    <td>Fleksor</td>
                                    <td><input class="form-control" id="td116"></td>
                                    <td><input class="form-control" id="td117"></td>
                                    <td><input class="form-control" id="td118"></td>
                                    <td><input class="form-control" id="td119"></td>
                                    <td><input class="form-control" id="td1110"></td>
                                </tr>
                                <tr>
                                    <td>Ekstensi</td>
                                    <td><input class="form-control" id="td121"></td>
                                    <td><input class="form-control" id="td122"></td>
                                    <td><input class="form-control" id="td123"></td>
                                    <td><input class="form-control" id="td124"></td>
                                    <td><input class="form-control" id="td125"></td>
                                    <td>Ekstensor</td>
                                    <td><input class="form-control" id="td126"></td>
                                    <td><input class="form-control" id="td127"></td>
                                    <td><input class="form-control" id="td128"></td>
                                    <td><input class="form-control" id="td129"></td>
                                    <td><input class="form-control" id="td1210"></td>
                                </tr>
                                <tr>
                                    <td rowspan="6">Paha</td>
                                    <td>Fleksi</td>
                                    <td colspan="5"><input class="form-control" id="td131"></td>
                                    <td>Fleksor</td>
                                    <td colspan="5"><input class="form-control" id="td132"></td>
                                </tr>
                                <tr>
                                    <td>Ektensi</td>
                                    <td colspan="5"><input class="form-control" id="td141"></td>
                                    <td>Ekstensor</td>
                                    <td colspan="5"><input class="form-control" id="td142"></td>
                                </tr>
                                <tr>
                                    <td>Abduksi</td>
                                    <td colspan="5"><input class="form-control" id="td151"></td>
                                    <td>Abductor</td>
                                    <td colspan="5"><input class="form-control" id="td152"></td>
                                </tr>
                                <tr>
                                    <td>Aduksi</td>
                                    <td colspan="5"><input class="form-control" id="td161"></td>
                                    <td>Aduktor</td>
                                    <td colspan="5"><input class="form-control" id="td162"></td>
                                </tr>
                                <tr>
                                    <td>Rotasi-internal</td>
                                    <td colspan="5"><input class="form-control" id="td171"></td>
                                    <td>Rotator-internal</td>
                                    <td colspan="5"><input class="form-control" id="td172"></td>
                                </tr>
                                <tr>
                                    <td>Rotasi-eksternal</td>
                                    <td colspan="5"><input class="form-control" id="td181"></td>
                                    <td>Rotaror-eksternal</td>
                                    <td colspan="5"><input class="form-control" id="td182"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">Lutut</td>
                                    <td>Fleksi</td>
                                    <td colspan="5"><input class="form-control" id="td191"></td>
                                    <td>Fleksor</td>
                                    <td colspan="5"><input class="form-control" id="td192"></td>
                                </tr>
                                <tr>
                                    <td>Ektensi</td>
                                    <td colspan="5"><input class="form-control" id="td201"></td>
                                    <td>Ekstensor</td>
                                    <td colspan="5"><input class="form-control" id="td202"></td>
                                </tr>
                                <tr>
                                    <td rowspan="4">Pergelangan kaki</td>
                                    <td>Inversi</td>
                                    <td colspan="5"><input class="form-control" id="td211"></td>
                                    <td>Invertor</td>
                                    <td colspan="5"><input class="form-control" id="td212"></td>
                                </tr>
                                <tr>
                                    <td>Eversi</td>
                                    <td colspan="5"><input class="form-control" id="td221"></td>
                                    <td>Evertor</td>
                                    <td colspan="5"><input class="form-control" id="td222"></td>
                                </tr>
                                <tr>
                                    <td>Fleksi dorsalis</td>
                                    <td colspan="5"><input class="form-control" id="td231"></td>
                                    <td>Fleksor dorsalis</td>
                                    <td colspan="5"><input class="form-control" id="td232"></td>
                                </tr>
                                <tr>
                                    <td>Fleksi plantaris</td>
                                    <td colspan="5"><input class="form-control" id="td241"></td>
                                    <td>Fleksor plantaris</td>
                                    <td colspan="5"><input class="form-control" id="td242"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td></td>
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                </tr>
                                <tr>
                                    <td rowspan="2">Jari Kaki: MCP</td>
                                    <td>Fleksi</td>
                                    <td><input class="form-control" id="td251"></td>
                                    <td><input class="form-control" id="td252"></td>
                                    <td><input class="form-control" id="td253"></td>
                                    <td><input class="form-control" id="td254"></td>
                                    <td><input class="form-control" id="td255"></td>
                                    <td>Fleksor</td>
                                    <td><input class="form-control" id="td256"></td>
                                    <td><input class="form-control" id="td257"></td>
                                    <td><input class="form-control" id="td258"></td>
                                    <td><input class="form-control" id="td259"></td>
                                    <td><input class="form-control" id="td2510"></td>
                                </tr>
                                <tr>
                                    <td>Ekstensi</td>
                                    <td><input class="form-control" id="td261"></td>
                                    <td><input class="form-control" id="td262"></td>
                                    <td><input class="form-control" id="td263"></td>
                                    <td><input class="form-control" id="td264"></td>
                                    <td><input class="form-control" id="td265"></td>
                                    <td>Ekstensor</td>
                                    <td><input class="form-control" id="td266"></td>
                                    <td><input class="form-control" id="td267"></td>
                                    <td><input class="form-control" id="td268"></td>
                                    <td><input class="form-control" id="td269"></td>
                                    <td><input class="form-control" id="td2610"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">PIP</td>
                                    <td>Fleksi</td>
                                    <td><input class="form-control" id="td271"></td>
                                    <td><input class="form-control" id="td272"></td>
                                    <td><input class="form-control" id="td273"></td>
                                    <td><input class="form-control" id="td274"></td>
                                    <td><input class="form-control" id="td275"></td>
                                    <td>Fleksor</td>
                                    <td><input class="form-control" id="td276"></td>
                                    <td><input class="form-control" id="td277"></td>
                                    <td><input class="form-control" id="td278"></td>
                                    <td><input class="form-control" id="td279"></td>
                                    <td><input class="form-control" id="td2710"></td>
                                </tr>
                                <tr>
                                    <td>Ekstensi</td>
                                    <td><input class="form-control" id="td281"></td>
                                    <td><input class="form-control" id="td282"></td>
                                    <td><input class="form-control" id="td283"></td>
                                    <td><input class="form-control" id="td284"></td>
                                    <td><input class="form-control" id="td285"></td>
                                    <td>Ekstensor</td>
                                    <td><input class="form-control" id="td286"></td>
                                    <td><input class="form-control" id="td287"></td>
                                    <td><input class="form-control" id="td288"></td>
                                    <td><input class="form-control" id="td289"></td>
                                    <td><input class="form-control" id="td2810"></td>
                                </tr>
                                <tr>
                                    <td rowspan="2">DIP</td>
                                    <td>Fleksi</td>
                                    <td><input class="form-control" id="td291"></td>
                                    <td><input class="form-control" id="td292"></td>
                                    <td><input class="form-control" id="td293"></td>
                                    <td><input class="form-control" id="td294"></td>
                                    <td><input class="form-control" id="td295"></td>
                                    <td>Fleksor</td>
                                    <td><input class="form-control" id="td296"></td>
                                    <td><input class="form-control" id="td297"></td>
                                    <td><input class="form-control" id="td298"></td>
                                    <td><input class="form-control" id="td299"></td>
                                    <td><input class="form-control" id="td2910"></td>
                                </tr>
                                <tr>
                                    <td>Ekstensi</td>
                                    <td><input class="form-control" id="td301"></td>
                                    <td><input class="form-control" id="td302"></td>
                                    <td><input class="form-control" id="td303"></td>
                                    <td><input class="form-control" id="td304"></td>
                                    <td><input class="form-control" id="td305"></td>
                                    <td>Ekstensor</td>
                                    <td><input class="form-control" id="td306"></td>
                                    <td><input class="form-control" id="td307"></td>
                                    <td><input class="form-control" id="td308"></td>
                                    <td><input class="form-control" id="td309"></td>
                                    <td><input class="form-control" id="td3010"></td>
                                </tr>
                                <tr>
                                    <td>Status Lokalis</td>
                                    <td colspan="12"><input class="form-control" id="td311"></td>
                                </tr>
                                <tr>
                                    <td colspan="13">Pemeriksaan Penunjang</td>
                                </tr>
                                <tr>
                                    <td colspan="13">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-check">
                                                    <input class="kp" type="checkbox" name="pp" id="pp1" value="EMG-NCV">
                                                    <label for="pp1"></label> EMG-NCV
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-check">
                                                    <input class="kp" type="checkbox" name="pp" id="pp2" value="Biofeedback">
                                                    <label for="pp2"></label> Biofeedback
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-check">
                                                    <input class="kp" type="checkbox" name="pp" id="pp3" value="Pemeriksaan KFR Lain">
                                                    <label for="pp3"></label> Pemeriksaan KFR Lain
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-1">
                                                <div class="form-check">
                                                    <input class="kp" type="checkbox" name="pp" id="pp4" value="Radiologi">
                                                    <label for="pp4"></label>
                                                </div>
                                            </div>
                                            <div class="col-md-5">
                                                <input onchange="$('#pp4').val(''); $('#pp4').val('Radiologi '+this.value)" style="margin-left: -38px" class="form-control" placeholder="Radiologi">
                                            </div>
                                            <div class="col-md-1">
                                                <div class="form-check">
                                                    <input class="kp" type="checkbox" name="kp" id="pp5" value="Laboratorium">
                                                    <label for="pp5"></label>
                                                </div>
                                            </div>
                                            <div class="col-md-5">
                                                <input onchange="$('#pp5').val(''); $('#pp5').val('Laboratorium '+this.value)" style="margin-left: -38px" class="form-control" placeholder="Laboratorium">
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="13">Perencanaan</td>
                                </tr>
                                <tr>
                                    <td>Medik</td>
                                    <td colspan="12"><input class="form-control" id="td321"></td>
                                </tr>
                                <tr>
                                    <td>Bedah</td>
                                    <td colspan="12"><input class="form-control" id="td331"></td>
                                </tr>
                                <tr>
                                    <td colspan="13">Kedokteran Fisik dan Rehabilitasi PDx:</td>
                                </tr>
                                <tr>
                                    <td>PTx</td>
                                    <td colspan="3">Dokter KFR</td>
                                    <td colspan="2">FT</td>
                                    <td colspan="2">OT</td>
                                    <td colspan="2">ST</td>
                                    <td colspan="1">OP</td>
                                    <td colspan="1">PSM</td>
                                    <td colspan="1">Psi</td>
                                </tr>
                                <tr>
                                    <td>R1</td>
                                    <td colspan="3"><input class="form-control" id="td341"></td>
                                    <td colspan="2"><input class="form-control" id="td342"></td>
                                    <td colspan="2"><input class="form-control" id="td343"></td>
                                    <td colspan="2"><input class="form-control" id="td344"></td>
                                    <td colspan="1"><input class="form-control" id="td345"></td>
                                    <td colspan="1"><input class="form-control" id="td346"></td>
                                    <td colspan="1"><input class="form-control" id="td347"></td>
                                </tr>
                                <tr>
                                    <td>R2</td>
                                   <td colspan="3"><input class="form-control" id="td351"></td>
                                    <td colspan="2"><input class="form-control" id="td352"></td>
                                    <td colspan="2"><input class="form-control" id="td353"></td>
                                    <td colspan="2"><input class="form-control" id="td354"></td>
                                    <td colspan="1"><input class="form-control" id="td355"></td>
                                    <td colspan="1"><input class="form-control" id="td356"></td>
                                    <td colspan="1"><input class="form-control" id="td357"></td>
                                </tr>
                                <tr>
                                    <td>R3</td>
                                   <td colspan="3"><input class="form-control" id="td361"></td>
                                    <td colspan="2"><input class="form-control" id="td362"></td>
                                    <td colspan="2"><input class="form-control" id="td363"></td>
                                    <td colspan="2"><input class="form-control" id="td364"></td>
                                    <td colspan="1"><input class="form-control" id="td365"></td>
                                    <td colspan="1"><input class="form-control" id="td366"></td>
                                    <td colspan="1"><input class="form-control" id="td367"></td>
                                </tr>
                                <tr>
                                    <td>R4</td>
                                   <td colspan="3"><input class="form-control" id="td371"></td>
                                    <td colspan="2"><input class="form-control" id="td372"></td>
                                    <td colspan="2"><input class="form-control" id="td373"></td>
                                    <td colspan="2"><input class="form-control" id="td374"></td>
                                    <td colspan="1"><input class="form-control" id="td375"></td>
                                    <td colspan="1"><input class="form-control" id="td376"></td>
                                    <td colspan="1"><input class="form-control" id="td377"></td>
                                </tr>
                                <tr>
                                    <td>R5</td>
                                   <td colspan="3"><input class="form-control" id="td381"></td>
                                    <td colspan="2"><input class="form-control" id="td382"></td>
                                    <td colspan="2"><input class="form-control" id="td383"></td>
                                    <td colspan="2"><input class="form-control" id="td384"></td>
                                    <td colspan="1"><input class="form-control" id="td385"></td>
                                    <td colspan="1"><input class="form-control" id="td386"></td>
                                    <td colspan="1"><input class="form-control" id="td387"></td>
                                </tr>
                                <tr>
                                    <td>R6</td>
                                   <td colspan="3"><input class="form-control" id="td391"></td>
                                    <td colspan="2"><input class="form-control" id="td392"></td>
                                    <td colspan="2"><input class="form-control" id="td393"></td>
                                    <td colspan="2"><input class="form-control" id="td394"></td>
                                    <td colspan="1"><input class="form-control" id="td395"></td>
                                    <td colspan="1"><input class="form-control" id="td396"></td>
                                    <td colspan="1"><input class="form-control" id="td397"></td>
                                </tr>
                                <tr>
                                    <td>R7</td>
                                   <td colspan="3"><input class="form-control" id="td401"></td>
                                    <td colspan="2"><input class="form-control" id="td402"></td>
                                    <td colspan="2"><input class="form-control" id="td403"></td>
                                    <td colspan="2"><input class="form-control" id="td404"></td>
                                    <td colspan="1"><input class="form-control" id="td405"></td>
                                    <td colspan="1"><input class="form-control" id="td406"></td>
                                    <td colspan="1"><input class="form-control" id="td407"></td>
                                </tr>
                                <tr>
                                    <td>PMx</td>
                                   <td colspan="3"><input class="form-control" id="td411"></td>
                                    <td colspan="2"><input class="form-control" id="td412"></td>
                                    <td colspan="2"><input class="form-control" id="td413"></td>
                                    <td colspan="2"><input class="form-control" id="td414"></td>
                                    <td colspan="1"><input class="form-control" id="td415"></td>
                                    <td colspan="1"><input class="form-control" id="td416"></td>
                                    <td colspan="1"><input class="form-control" id="td417"></td>
                                </tr>
                                <tr>
                                    <td>PEx</td>
                                   <td colspan="3"><input class="form-control" id="td421"></td>
                                    <td colspan="2"><input class="form-control" id="td422"></td>
                                    <td colspan="2"><input class="form-control" id="td423"></td>
                                    <td colspan="2"><input class="form-control" id="td424"></td>
                                    <td colspan="1"><input class="form-control" id="td425"></td>
                                    <td colspan="1"><input class="form-control" id="td426"></td>
                                    <td colspan="1"><input class="form-control" id="td427"></td>
                                </tr>
                                <tr>
                                    <td colspan="13">TINDAK LANJUT PROGRAM KEDOKTERAN FISIk & REHABILITAS</td>
                                </tr>
                                <tr>
                                    <td colspan="13">
                                        <textarea class="form-control" rows="10" id="td431"></textarea>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-offset-3 col-md-6 text-center">
                            <div>TTD DPJP</div>
                            <canvas class="paint-canvas-ttd" id="asuhan_dpjp" width="220"
                                    onmouseover="paintTtd('asuhan_dpjp')"></canvas>
                            <input class="form-control nama_dokter" id="nama_asuhan_dpjp" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_asuhan_dpjp" placeholder="SIP">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('asuhan_dpjp')"><i
                                    class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" id="save_sps_pemeriksaan_rehab_medik" onclick="saveSPS('pemeriksaan_rehab_medik', 'rehab_medik')"><i class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_sps_pemeriksaan_rehab_medik"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>