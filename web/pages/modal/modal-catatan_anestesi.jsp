<div class="modal fade" id="modal-op-catatan_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Catatan Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_catatan_anestesi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_catatan_anestesi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_catatan_anestesi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_catatan_anestesi"></p>
                    </div>
                    <button type="button" class="btn btn-success" onclick="showModalOperasi('pra_induksi')"><i class="fa fa-plus"></i> Pra Induksi
                    </button>
                    <button type="button" class="btn btn-success" onclick="showModalOperasi('perencanaan_anestesi')"><i class="fa fa-plus"></i> Perencanaan Anestesi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_add_catatan_anestesi">
                        <tbody>
                        <tr id="row_op_pra_induksi">
                            <td>Asesmen Pra Induksi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_pra_induksi" class="hvr-grow" onclick="detailOperasi('pra_induksi')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pra_induksi" class="hvr-grow btn-hide" onclick="conOP('pra_induksi', 'catatan_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_perencanaan_anestesi">
                            <td>Perencenaan Teknik Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_perencanaan_anestesi" class="hvr-grow" onclick="detailOperasi('perencanaan_anestesi')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_perencanaan_anestesi" class="hvr-grow btn-hide" onclick="conOP('perencanaan_anestesi', 'catatan_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-op-pra_induksi">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Pra Induksi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_pra_induksi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_pra_induksi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="pra1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Medis</label>
                            <div class="col-md-8">
                                <input class="form-control diagnosa-pasien" id="pra2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Rencana Tindakan</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control" id="pra3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Dokter Spesialis Anestesi</label>
                            <div class="col-md-8">
                                <input class="form-control nama_dokter_ri" id="pra4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Perawat Anestesi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pra5">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Dokter Bedah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pra6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Asisten dan Instrumen</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pra7">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Anamnesa</label>
                            <div class="col-md-8">
                                <textarea rows="2" class="form-control anamnese" id="pra8"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Makan minum terakhir jam</label>
                            <div class="col-md-8">
                                <input class="form-control jam" id="pra9">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Pemeriksaan Fisik</label>
                            <div class="col-md-8">
                                <textarea rows="3" class="form-control" id="pra10"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Keadaan Umum</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Baik" id="pra111"
                                           name="pra11"/><label for="pra111">Baik</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Cukup" id="pra112"
                                           name="pra11"/><label for="pra112">Cukup</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Jelek" id="pra113"
                                           name="pra11"/><label for="pra113">Jelek</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Pernafasan</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sesak" id="pra121"
                                           name="pra12"/><label for="pra121">Sesak</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Spontan adekuat" id="pra122"
                                           name="pra12"/><label for="pra122">Spontan adekuat</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Kemungkinan sulit manajemen airway</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="pra131"
                                           name="pra13"/><label for="pra131">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="pra132"
                                           name="pra13"/><label for="pra132">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input class="form-control" placeholder="persiapan khusus" id="ket_pra132" onchange="$('#pra132').val('Ya, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <span>Tensi</span>
                                <input class="form-control tensi-pasien" placeholder="mmHg" id="pra14" data-inputmask="'mask': ['999/999']" data-mask="">
                            </div>
                            <div class="col-md-3">
                                <span>Nadi</span>
                                <input class="form-control nadi-pasien" placeholder="x/menit" id="pra15" type="number">
                            </div>
                            <div class="col-md-3">
                                <span>RR</span>
                                <input class="form-control rr-pasien" placeholder="x/menit" id="pra16" type="number">
                            </div>
                            <div class="col-md-3">
                                <span>Suhu</span>
                                <input class="form-control suhu-pasien" placeholder="C" id="pra17" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <span>BB</span>
                                <input class="form-control berat-pasien" placeholder="Kg" id="pra18" type="number">
                            </div>
                            <div class="col-md-3">
                                <span>TB</span>
                                <input class="form-control tinggi-pasien" placeholder="Cm" id="pra19" type="number">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Perfusi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="HKM" id="pra201"
                                           name="pra20"/><label for="pra201">HKM</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Lain-Lain" id="pra202"
                                           name="pra20"/><label for="pra202">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Capillary refil time</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pra21" placeholder="detik" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Terpasang infus : IV cath no</label>
                            <div class="col-md-3">
                                <input class="form-control" id="pra22">
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Lancar" id="pra231" name="pra23"/><label for="pra231">Lancar</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Lancar" id="pra232" name="pra23"/><label for="pra232">Tidak Lancar</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Psikologis</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tenang" id="pra241" name="pra24"/><label for="pra241">Tenang</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Cemas" id="pra242" name="pra24"/><label for="pra242">Cemas</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Gelisah" id="pra243" name="pra24"/><label for="pra243">Gelisah</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">ASA</label>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="1" id="pra251" name="pra25"/><label for="pra251">1</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="2" id="pra252" name="pra25"/><label for="pra252">2</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="3" id="pra253" name="pra25"/><label for="pra253">3</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="4" id="pra254" name="pra25"/><label for="pra254">4</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02">
                                    <input type="radio" value="5" id="pra255" name="pra25"/><label for="pra255">5</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Elektif" id="pra261" name="pra26"/><label for="pra261">Elektif</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Emergency" id="pra262" name="pra26"/><label for="pra262">Emergency</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_pra_induksi" class="btn btn-success pull-right" onclick="saveDataOperasi('pra_induksi','catatan_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_pra_induksi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-perencanaan_anestesi">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Perencanaan Teknik Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_perencanaan_anestesi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_perencanaan_anestesi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Pre medikasi</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per11" value="Midazolam">
                                    <label for="per11"></label> Midazolam
                                </div>
                            </div>
                            <div class="col-md-6">
                                <input class="form-control" onchange="$('#per11').val('Midazolam, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per12" value="Pethidin">
                                    <label for="per12"></label> Pethidin
                                </div>
                            </div>
                            <div class="col-md-6">
                                <input class="form-control" onchange="$('#per12').val('Pethidin, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per13" value="Atropin">
                                    <label for="per13"></label> Atropin
                                </div>
                            </div>
                            <div class="col-md-6">
                                <input class="form-control" onchange="$('#per13').val('Atropin, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per14" value="Fentanyl">
                                    <label for="per14"></label> Fentanyl
                                </div>
                            </div>
                            <div class="col-md-6">
                                <input class="form-control" onchange="$('#per14').val('Fentanyl, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-1">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per15">
                                    <label for="per15"></label>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <input class="form-control" onchange="$('#per15').val(this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-1">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per16">
                                    <label for="per16"></label>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <input class="form-control" onchange="$('#per16').val(this.value)">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">General Anestesi</label>
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="per2" id="per21">
                                    <label for="per21"></label> Intubasi Oral / Nasal ETT No.
                                </div>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" onchange="$('#per21').val(''); $('#per21').val('Intubasi Oral / Nasal ETT No. '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="per2" id="per22" value="Facemaak">
                                    <label for="per22"></label> Facemaak
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="per2" id="per23" value="Lanngeal Mask Airway">
                                    <label for="per23"></label> Lanngeal Mask Airway
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="per2" id="per24" value="Total Intra Venous Anestesi">
                                    <label for="per24"></label> Total Intra Venous Anestesi
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Induksi dengan</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Propofol" id="per31" name="per3"/><label for="per31">Propofol</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Lancar" id="per32" name="per3"/><label for="per32">Ketamin</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Lancar" id="per33" name="per3"/><label for="per33">Ketamin</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Muscle relaksan</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Atracurium" id="per41" name="per4"/><label for="per41">Atracurium</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Rocuronium" id="per42" name="per4"/><label for="per42">Rocuronium</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Vecuronium" id="per43" name="per4"/><label for="per43">Vecuronium</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Maintenance</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Enflurane" id="per51" name="per5"/><label for="per51">Enflurane</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Isoflurane" id="per52" name="per5"/><label for="per52">Isoflurane</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sevoflurane" id="per53" name="per5"/><label for="per53">Sevoflurane</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Regional Anestesi</label>
                            <div class="col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="per6" id="per61" value="SAB">
                                    <label for="per61"></label> SAB
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="per6" id="per62" value="Epidual">
                                    <label for="per62"></label> Epidual
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="form-check">
                                    <input type="checkbox" name="per6" id="per63" value="Blok syaraf perifer">
                                    <label for="per63"></label> Blok syaraf perifer
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Obat-obatan</label>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Bupivacain 0.5% Heavy" id="per71" name="per7"/><label for="per71">Bupivacain 0.5% Heavy</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Lidorex 5%" id="per72" name="per7"/><label for="per72">Lidorex 5%</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Lidocain 2%" id="per73" name="per7"/><label for="per73">Lidocain 2%</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Adjuvan</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ephinephrine" id="per81" name="per8"/><label for="per81">Ephinephrine</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Clonidin" id="per82" name="per8"/><label for="per82">Clonidin</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Morphine" id="per83" name="per8"/><label for="per83">Morphine</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Jarum Spinal No</label>
                            <div class="col-md-6">
                                <input class="form-control" id="per9">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Oksigenasi</label>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Nasal Prong" id="per101" name="per10"/><label for="per101">Nasal Prong</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Simple Mask" id="per102" name="per10"/><label for="per102">Simple Mask</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Non Rebrething Mask" id="per103" name="per10"/><label for="per103">Non Rebrething Mask</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Rebrething Mask" id="per104" name="per10"/><label for="per104">Rebrething Mask</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Sedasi Dalam</label>
                            <div class="form-group">
                                <label class="col-md-3">Obat-obatan</label>
                                <div class="col-md-6">
                                    <div class="custom02">
                                        <input type="radio" value="Midazolamy" id="per111" name="per11"/><label for="per111">Midazolam</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-6 col-md-6">
                                    <div class="custom02">
                                        <input type="radio" value="Pethidin" id="per112" name="per11"/><label for="per112">Pethidin</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-6 col-md-6">
                                    <div class="custom02">
                                        <input type="radio" value="Fentanyl" id="per113" name="per11"/><label for="per113">Fentanyl</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-6 col-md-6">
                                    <div class="custom02">
                                        <input type="radio" value="Propofol" id="per114" name="per11"/><label for="per114">Propofol</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-6 col-md-6">
                                    <div class="custom02">
                                        <input type="radio" value="Ketamin" id="per115" name="per11"/><label for="per115">Ketamin</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-offset-3 col-md-3">Oksigenasi</label>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Nasal Prong" id="per121" name="per12"/><label for="per121">Nasal Prong</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Simple Mask" id="per122" name="per12"/><label for="per122">Simple Mask</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Non Rebrething Mask" id="per123" name="per12"/><label for="per123">Non Rebrething Mask</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Rebrething Mask" id="per124" name="per12"/><label for="per124">Rebrething Mask</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Dokter Spesialis</label>
                                <canvas class="paint-canvas-ttd" id="ttd_dokter_spesialis" width="220"
                                        onmouseover="paintTtd('ttd_dokter_spesialis')"></canvas>
                                <input class="form-control nama_dokter_ri" id="nama_terang_sps" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_sps" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_dokter_spesialis')"><i
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
                <button id="save_op_perencanaan_anestesi" class="btn btn-success pull-right" onclick="saveDataOperasi('perencanaan_anestesi','catatan_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_perencanaan_anestesi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>