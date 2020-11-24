<div class="modal fade" id="modal-op-monitoring_intra_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Monitoring Intra Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_op_monitoring_intra_anestesi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_monitoring_intra_anestesi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_monitoring_intra_anestesi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_monitoring_intra_anestesi"></p>
                    </div>
                    <button type="button" onclick="showModalOperasi('data_intra_anestesi')" class="btn btn-success"><i class="fa fa-plus"></i> Data Intra Anestesi
                    </button>
                    <button type="button" onclick="showModalOperasi('mon_intra_anestesi')" class="btn btn-success"><i class="fa fa-plus"></i> Monitoring Intra Anestesi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_monitoing_intra_anestesi">
                        <tbody>
                        <tr id="row_op_data_intra_anestesi">
                            <td>Data Intra Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_data_intra_anestesi" class="hvr-grow"
                                     onclick="detailOperasi('data_intra_anestesi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_data_intra_anestesi" class="hvr-grow btn-hide" onclick="conOP('data_intra_anestesi', 'monitoring_intra_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_mon_intra_anestesi">
                            <td>Monitoring Intra Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_mon_intra_anestesi" class="hvr-grow"
                                     onclick="detailMonAnestesi('mon_intra_anestesi')"
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

<div class="modal fade" id="modal-op-data_intra_anestesi">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Data Intra Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_op_data_intra_anestesi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_data_intra_anestesi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Teknik Anestesi</label>
                            <div class="col-md-3">
                                <div class="form-check" style="font-size: 12px">
                                    <input type="checkbox" name="dt1" id="dt11" value="General Anestesi">
                                    <label for="dt11"></label> General Anestesi
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt1" id="dt12" value="Intubasi">
                                    <label for="dt12"></label> Intubasi
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt1" id="dt13" value="Facemask">
                                    <label for="dt13"></label> Facemask
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt1" id="dt14" value="LMA">
                                    <label for="dt14"></label> LMA
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt1" id="dt15" value="TIVA">
                                    <label for="dt15"></label> TIVA
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check" style="font-size: 12px">
                                    <input type="checkbox" name="dt1" id="dt16" value="Regional Anestesi">
                                    <label for="dt16"></label> Regional Anestesi
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-3">
                                    <div class="form-check">
                                        <input type="checkbox" name="dt1" id="dt17" value="SAB">
                                        <label for="dt17"></label> SAB
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-check">
                                        <input type="checkbox" name="dt1" id="dt18" value="Epidural">
                                        <label for="dt18"></label> Epidural
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-check" style="font-size: 11px">
                                        <input type="checkbox" name="dt1" id="dt19" value="Blok Syaraf perifer">
                                        <label for="dt19"></label> Blok Syaraf perifer
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Pernafasan</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt2" id="dt21" value="Spontan">
                                    <label for="dt21"></label> Spontan
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt2" id="dt22" value="Assisted">
                                    <label for="dt22"></label> Assisted
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt2" id="dt23" value="Kontrol">
                                    <label for="dt23"></label> Kontrol
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt2" id="dt24" value="Semiclosed">
                                    <label for="dt24"></label> Semiclosed
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt2" id="dt25" value="Closed">
                                    <label for="dt25"></label> Closed
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check" style="font-size: 12px">
                                    <input type="checkbox" name="dt2" id="dt26" value="Semiopen">
                                    <label for="dt26"></label> Semiopen
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-3">
                                    <div class="form-check">
                                        <input type="checkbox" name="dt2" id="dt27" value="ETT">
                                        <label for="dt27"></label> ETT
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-check">
                                        <input type="checkbox" name="dt2" id="dt28" value="Facemask">
                                        <label for="dt28"></label> Facemask
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-check">
                                        <input type="checkbox" name="dt2" id="dt29" value="Simple Mask">
                                        <label for="dt29"></label> Simple Mask
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-3">
                                    <div class="form-check">
                                        <input type="checkbox" name="dt2" id="dt210" value="Nasalprong">
                                        <label for="dt210"></label> Nasalprong
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Posisi</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt3" id="dt31" value="Spontan">
                                    <label for="dt31"></label> Supine
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt3" id="dt32" value="Assisted">
                                    <label for="dt32"></label> Lateral
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt3" id="dt33" value="Kontrol">
                                    <label for="dt33"></label> Litothomy
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt3" id="dt34" value="Semiclosed">
                                    <label for="dt34"></label> Prone
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Endotracheal</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt4" id="dt41" value="Supine">
                                    <label for="dt41"></label> Supine
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt4" id="dt42" value="Lateral">
                                    <label for="dt42"></label> Lateral
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt4" id="dt43" value="Litothomy">
                                    <label for="dt43"></label> Litothomy
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="dt4" id="dt44" value="Prone">
                                    <label for="dt44"></label> Prone
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Pre Medikasi</b></label>
                        <div class="col-md-3">
                            <span>Midazolam</span><small><b> (mg)</b></small>
                            <input class="form-control" id="dt5" type="number">
                        </div>
                        <div class="col-md-3">
                            <span>Pethidin</span><small><b> (mg)</b></small>
                            <input class="form-control" id="dt6" type="number">
                        </div>
                        <div class="col-md-3">
                            <span>Sulfas Atropin</span><small><b> (mg)</b></small>
                            <input class="form-control" id="dt7" type="number">
                        </div>
                        <div class="col-md-3">
                            <span>Fentanyl</span><small><b> (ug)</b></small>
                            <input class="form-control" id="dt8" type="number">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <span>Lain-Lain</span>
                            <input class="form-control" name="dt09" id="dt9">
                        </div>
                        <div class="col-md-3">
                            <span>Lain-Lain</span>
                            <input class="form-control" name="dt09" id="dt10">
                        </div>
                        <div class="col-md-3">
                            <span>Lain-Lain</span>
                            <input class="form-control" name="dt09" id="dt011">
                        </div>
                        <div class="col-md-3">
                            <span>Lain-Lain</span>
                            <input class="form-control" name="dt09" id="dt012">
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Pemberian</label>
                        <div class="col-md-1">
                            <div class="custom02">
                                <input type="radio" value="IM" id="dt131" name="dt13" /><label for="dt131" >IM</label>
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="custom02">
                                <input type="radio" value="IV" id="dt132" name="dt13" /><label for="dt132" >IV</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input type="radio" value="Lainnya" id="dt133" name="dt13" /><label for="dt133" >Lainnya</label>
                            </div>
                        </div>
                        <label class="col-md-offset-1 col-md-1">Jam</label>
                        <div class="col-md-3">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>
                                </div>
                                <input class="form-control jam" id="dt014">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Medikasi</b></label>
                        <div class="form-group">
                            <label class="col-md-3">Induksi</label>
                            <div class="col-md-4">
                                <input class="form-control" name="dt015" placeholder="1">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" name="dt015" placeholder="2">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <input class="form-control jarak" name="dt015" placeholder="3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Medikasi Lain</label>
                            <div class="col-md-4">
                                <input class="form-control" name="dt016" placeholder="1">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" name="dt016" placeholder="2">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <input class="form-control jarak" iname="dt016" placeholder="3">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control jarak" name="dt016" placeholder="4">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <input class="form-control jarak" name="dt016" placeholder="5">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control jarak" name="dt016" placeholder="6">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <input class="form-control jarak" name="dt016" placeholder="7">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control jarak" name="dt016" placeholder="8">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <input class="form-control jarak" name="dt016" placeholder="9">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control jarak" name="dt016" placeholder="10">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-4">
                            <span>Jumlah Intakea Cairan</span>
                            <input class="form-control" id="dt017">
                        </div>
                        <div class="col-md-4">
                            <span>Perdarahan</span> <small><b> ml</b></small>
                            <input class="form-control" id="dt018">
                        </div>
                        <div class="col-md-4">
                            <span>Urine</span> <small><b> ml</b></small>
                            <input class="form-control" id="dt019">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <span>Bayi Lahir Jam</span>
                            <input class="form-control" id="dt020">
                        </div>
                        <div class="col-md-3">
                            <span>Jenis Kel</span>
                            <input class="form-control" id="dt021">
                        </div>
                        <div class="col-md-3">
                            <span>Apgar Skore</span>
                            <input class="form-control" id="dt022">
                        </div>
                        <div class="col-md-3">
                            <span>BB</span>
                            <input class="form-control" id="dt023">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-3">
                            <span>PB</span>
                            <input class="form-control" id="dt024">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label>TTD Dokter</label>
                                <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('ttd1')" class="paint-canvas-ttd" id="ttd1"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_sps" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_sps" placeholder="SIP">
                                <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('ttd1')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_data_intra_anestesi" class="btn btn-success pull-right"
                        onclick="saveDataOperasi('data_intra_anestesi','monitoing_intra_anestesi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_op_data_intra_anestesi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-mon_intra_anestesi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Monitoring Intra Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_op_mon_intra_anestesi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_mon_intra_anestesi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
                                    <input class="form-control jam" id="jam">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">RR</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon1" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="28">28</option>
                                    <option value="24">24</option>
                                    <option value="20">20</option>
                                    <option value="16">16</option>
                                    <option value="12">12</option>
                                </select>
                            </div>
                            <label class="col-md-2 jarak">Nadi</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon2" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="180">180</option>
                                    <option value="160">160</option>
                                    <option value="140">140</option>
                                    <option value="120">120</option>
                                    <option value="100">100</option>
                                    <option value="80">80</option>
                                    <option value="60">60</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 jarak">Sistole</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon3" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="260">260</option>
                                    <option value="250">250</option>
                                    <option value="240">240</option>
                                    <option value="220">230</option>
                                    <option value="220">220</option>
                                    <option value="210">210</option>
                                    <option value="200">200</option>
                                    <option value="190">190</option>
                                    <option value="180">180</option>
                                    <option value="170">170</option>
                                    <option value="160">160</option>
                                    <option value="150">150</option>
                                    <option value="140">140</option>
                                    <option value="130">130</option>
                                    <option value="120">120</option>
                                    <option value="110">110</option>
                                    <option value="100">100</option>
                                    <option value="90">90</option>
                                    <option value="80">80</option>
                                    <option value="70">70</option>
                                    <option value="60">60</option>
                                    <option value="50">50</option>
                                    <option value="40">40</option>
                                    <option value="30">30</option>
                                    <option value="20">20</option>
                                    <option value="20">10</option>
                                    <option value="0">0</option>
                                </select>
                            </div>
                            <label class="col-md-2 jarak">Diastole</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon4" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="180">180</option>
                                    <option value="170">170</option>
                                    <option value="160">160</option>
                                    <option value="150">150</option>
                                    <option value="140">140</option>
                                    <option value="130">130</option>
                                    <option value="120">120</option>
                                    <option value="110">110</option>
                                    <option value="100">100</option>
                                    <option value="90">90</option>
                                    <option value="80">80</option>
                                    <option value="70">70</option>
                                    <option value="60">60</option>
                                    <option value="50">50</option>
                                    <option value="40">40</option>
                                    <option value="30">30</option>
                                    <option value="20">20</option>
                                    <option value="20">10</option>
                                    <option value="0">0</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-md-2" style="width: 100%; margin-top: 7px">Inhalasi</label>--%>
                            <%--<div class="col-md-4">--%>
                                <%--<select class="form-control select2" id="mon7">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="Ethran">Ethran</option>--%>
                                    <%--<option value="Iso">Iso</option>--%>
                                    <%--<option value="Sevo">Sevo</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-2">O2</label>
                            <div class="col-md-4">
                                <input class="form-control" id="mon5" type="number">
                            </div>
                            <label class="col-md-2">N2O</label>
                            <div class="col-md-4">
                                <input class="form-control" id="mon6" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">Inhalasi</label>
                            <div class="col-md-4">
                                <select class="form-control select2" id="mon7" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="Ethran">Ethran</option>
                                    <option value="Sevo">Sevo</option>
                                    <option value="Iso">Iso</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_mon_intra_anestesi" class="btn btn-success pull-right"
                        onclick="saveMonAnestesi('mon_intra_anestesi','monitoring_intra_anestesi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_op_mon_intra_anestesi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-chart_mon_intra_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Chart Monitoring Intra Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_chart_intra_anestesi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_op_chart_intra_anestesi"></p>
                    </div>
                    <div class="box-body chart-responsive">
                        <div class="chart" id="line-chart_mon_intra_anestesi" style="height: 300px; width: 100%"></div>
                        <hr class="garis">
                        <div class="row" style="font-size: 10px">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-1">
                                    <i class="fa fa-circle" style="color: #ff0000"></i> RR
                                </div>
                                <div class="col-md-1">
                                    <i class="fa fa-circle" style="color: #0000ff"></i> Nadi
                                </div>
                                <div class="col-md-2">
                                    <i class="fa fa-circle" style="color: #00cc00"></i> Sistole
                                </div>
                                <div class="col-md-2">
                                    <i class="fa fa-circle" style="color: #cc6699; margin-left: -70px"></i> Diastole
                                </div>
                            </div>
                        </div>
                        <div class="row jarak">
                            <div class="box-body">
                                <table class="table table-striped table-bordered" style="font-size: 12px">
                                    <tbody id="temp_body">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
            </div>
        </div>
    </div>
</div>