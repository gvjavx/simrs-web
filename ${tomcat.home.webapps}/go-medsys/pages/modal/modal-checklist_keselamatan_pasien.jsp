<div class="modal fade" id="modal-op-checklist_keselamatan_pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Checklist Keselamatan Pasien Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_checklist_keselamatan_pasien">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_checklist_keselamatan_pasien"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_checklist_keselamatan_pasien">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_checklist_keselamatan_pasiena"></p>
                    </div>
                    <button type="button" class="btn btn-success" onclick="showModalOperasi('add_checklist_keselamatan')"><i class="fa fa-plus"></i> Checklist
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_add_checklist_keselamatan_pasien">
                        <tbody>
                        <tr id="row_op_add_checklist_keselamatan">
                            <td>Checklist Keselamatan Pasien Operasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_add_checklist_keselamatan" class="hvr-grow" onclick="detailOperasi('add_checklist_keselamatan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_add_checklist_keselamatan" class="hvr-grow btn-hide" onclick="conOP('add_checklist_keselamatan', 'checklist_keselamatan_pasien')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-op-add_checklist_keselamatan">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Checklist Keselamatan Pasien Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_add_checklist_keselamatan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_add_checklist_keselamatan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="kes1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-8">
                                <input class="form-control diagnosa-pasien" id="kes2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Operator</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kes3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Anestesi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kes4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Incisi Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="kes5">
                                </div>
                            </div>
                            <label class="col-md-2">Selesai Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam02" id="kes6">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Perawat Bedah</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kes7">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Perawat Anastesi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="kes8"></input>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Sebelum Induksi Anestesi (Fase Sign In)</b></label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">1. Konfirmasi identitas pasien</label>
                            <div class="col-md-6">
                                <div class="form-check" style="margin-left: 14px">
                                    <input type="checkbox" name="kes9" id="kes91" value="1. Konfirmasi identitas pasien">
                                    <label for="kes91"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">2. Informed consent</label>
                            <div class="col-md-6">
                                <div class="form-check" style="margin-left: 14px">
                                    <input type="checkbox" name="kes10" id="kes101" value="2. Informed consent">
                                    <label for="kes101"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">3. Penandaan area operasi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: 14px">
                                    <input type="radio" value="Ya" id="kes111" name="kes11"/><label for="kes111">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: 14px">
                                    <input type="radio" value="Tidak" id="kes112" name="kes11"/><label for="kes112">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">4. Apakah pasien memiliki</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">a. Riwayat alergi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kes121" name="kes12"/><label for="kes121">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kes122" name="kes12"/><label for="kes122">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">b. Kesulitan manajemen jalan nafas</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kes131" name="kes13"/><label for="kes131">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kes132" name="kes13"/><label for="kes132">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">c. Resiko kehilangan darah > 500ml atau > (70ml/kg BB) pada pasien anak</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kes141" name="kes14"/><label for="kes141">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kes142" name="kes14"/><label for="kes142">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">5. Ceklis kesiapan alat medis</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">a. Mesin anestesi</label>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input type="checkbox" name="kes15" id="kes151" value="a. Mesin anestesi">
                                    <label for="kes151"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">b. Lampu operasi</label>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input type="checkbox" name="kes16" id="kes161" value="b. Lampu operasi">
                                    <label for="kes161"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">c. Elektro surgical unit</label>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input type="checkbox" name="kes17" id="kes171" value="c. Elektro surgical unit">
                                    <label for="kes171"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">d. Monitor hemodinamik</label>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input type="checkbox" name="kes18" id="kes181" value="d. Monitor hemodinamik">
                                    <label for="kes181"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6" style="margin-left: 14px">e. Suction sentral</label>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input type="checkbox" name="kes19" id="kes191" value="e. Suction sentral">
                                    <label for="kes191"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <label style="margin-left: 8px">TTD Anestesi</label>
                            <canvas class="paint-canvas-ttd" id="ttd_anestesi" width="220"
                                    onmouseover="paintTtd('ttd_anestesi')"></canvas>
                            <input class="form-control" id="nama_terang_anestesi" placeholder="Nama Terang">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_anestesi')"><i
                                    class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Sebelum Incisi (Fase Time Out)</b></label>
                    </div>
                    <div class="row">
                        <label class="col-md-12"><b>Dokter Bedah</b></label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">1. Prosedur yang akan dilakukan ?</label>
                            <div class="col-md-6">
                                <input class="form-control" id="kes20">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-6">2. Perkiraan lama operasi ?</label>
                            <div class="col-md-6">
                                <input class="form-control" id="kes21">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-6">3. Antisipasi kehilangan darah ?</label>
                            <div class="col-md-6">
                                <input class="form-control" id="kes22">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-6">4. Antibiotik profilaksis ?</label>
                            <div class="col-md-6">
                                <input class="form-control" id="kes23">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-12"><b>Dokter Anestesi</b></label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">Adakah perhatian khusus dalam pembiusan ?</label>
                            <div class="col-md-6">
                                <input class="form-control" id="kes24">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-12"><b>Tim Bedah</b></label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">Adakah instrumen telah steril ?</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kes251" name="kes25"/><label for="kes251">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kes252" name="kes25"/><label for="kes252">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">Lokasi pasien plate ?</label>
                            <div class="col-md-4">
                                <input class="form-control" id="kes26">
                            </div>
                            <div class="col-md-2">
                                <label>Tourniquet</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="form-check jarak">
                                    <input type="checkbox" name="kes27" id="kes271" value="Kasa">
                                    <label for="kes271"></label> Kasa
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check jarak">
                                    <input type="checkbox" name="kes27" id="kes272" value="Darm gaas">
                                    <label for="kes272"></label> Darm gaas
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="form-check jarak">
                                    <input type="checkbox" name="kes27" id="kes273" value="Jarum">
                                    <label for="kes273"></label> Jarum
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-6">Adakah implan telah tersedia sesuai kebutuhan ?</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="kes281" name="kes28"/><label for="kes281">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="kes282" name="kes28"/><label for="kes282">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <label style="margin-left: 8px">TTD Operator</label>
                            <canvas class="paint-canvas-ttd" id="ttd_aperator" width="220"
                                    onmouseover="paintTtd('ttd_aperator')"></canvas>
                            <input class="form-control" id="nama_terang_aperator" placeholder="SIP">
                            <input style="margin-top: 3px" class="form-control" id="sip_aperator" placeholder="SIP">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_aperator')"><i
                                    class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-12"><b>Sebelum Check Out Kamar Operasi (Fase Sign Out)</b></label>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">1. Tindakan/ prosedur yang telah dilakukan ?</label>
                            <div class="col-md-6">
                                <input class="form-control" id="kes29">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-6">2. Apakah instrumen, kasa, jarum telah dihitung dan lengkap ?</label>
                            <div class="col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="kes30" id="kes301" value="Instrumen lengkap">
                                    <label for="kes301"></label> Instrumen lengkap
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="kes30" id="kes302" value="Kasa lengkap">
                                    <label for="kes302"></label> Kasa lengkap
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <div class="form-check">
                                    <input type="checkbox" name="kes30" id="kes303" value="Jarum lengkap">
                                    <label for="kes303"></label> Jarum lengkap
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">3. Apakah spesimen pa diberi label nama ?</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="kes31" id="kes311" value="Darm gaas">
                                    <label for="kes311"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">4. Apakah dipasang ?</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="kes32" id="kes321" value="Drain">
                                    <label for="kes321"></label> Drain
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="kes32" id="kes322" value="Tampon">
                                    <label for="kes322"></label> Tampon
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-6">5. Perhatian khusus di masa pemulihan ?</label>
                            <div class="col-md-6">
                                <input class="form-control" id="kes33">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <label style="margin-left: 8px">TTD Perawat Sirkuler</label>
                            <canvas class="paint-canvas-ttd" id="ttd_sirkuler" width="220"
                                    onmouseover="paintTtd('ttd_sirkuler')"></canvas>
                            <input class="form-control" id="nama_terang_sirkuler" placeholder="SIP">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_sirkuler')"><i
                                    class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_add_checklist_keselamatan" class="btn btn-success pull-right" onclick="saveDataOperasi('add_checklist_keselamatan','checklist_keselamatan_pasien')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_add_checklist_keselamatan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>