<div class="modal fade" id="modal-mpp-evaluasi_awal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Form A Evaluasi Awal MPP
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_mpp_evaluasi_awal">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_mpp_evaluasi_awal"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_evaluasi_awal">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_evaluasi_awal"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button type="button" class="btn btn-success dropdown-toggle"
                                data-toggle="dropdown" style="height: 34px">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="showModalMpp('identifikasi')" style="cursor: pointer"><i class="fa fa-plus"></i> Identifikasi / Skrining</a></li>
                            <li><a onclick="showModalMpp('asesmen')" style="cursor: pointer"><i class="fa fa-plus"></i> Asesmen</a></li>
                            <li><a onclick="showModalMpp('identifikasi_resiko')" style="cursor: pointer"><i class="fa fa-plus"></i> Identifikasi Resiko</a></li>
                            <li><a onclick="showModalMpp('perencanaan_mpp')" style="cursor: pointer"><i class="fa fa-plus"></i> Perencanaan MPP</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_fisio">
                        <tbody>
                        <tr id="row_mpp_identifikasi">
                            <td>Identifikasi / Skrining</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_identifikasi" class="hvr-grow" onclick="detailFormMpp('identifikasi')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_identifikasi" class="hvr-grow btn-hide" onclick="conMPP('identifikasi', 'evaluasi_awal')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_asesmen">
                            <td>Asesmen</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_asesmen" class="hvr-grow" onclick="detailFormMpp('asesmen')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_asesmen" class="hvr-grow btn-hide" onclick="conMPP('asesmen', 'evaluasi_awal')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_identifikasi_resiko">
                            <td>Identifikasi Masalah Resiko</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_identifikasi_resiko" class="hvr-grow" onclick="detailFormMpp('identifikasi_resiko')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_identifikasi_resiko" class="hvr-grow btn-hide" onclick="conMPP('identifikasi_resiko', 'evaluasi_awal')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_perencanaan_mpp">
                            <td>Perencanaan Manajemen Pelaksanaan Pasien (MPP)</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_perencanaan_mpp" class="hvr-grow" onclick="detailFormMpp('perencanaan_mpp')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_perencanaan_mpp" class="hvr-grow btn-hide" onclick="conMPP('perencanaan_mpp', 'evaluasi_awal')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-mpp-identifikasi">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Identifikasi / Skrining
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_identifikasi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_identifikasi"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="idn0">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="idn00">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Usia</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Neunatus < 30 hr" id="idn11" name="idn1" /><label for="idn11">Neunatus < 30 hr</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Lansia > 65 th" id="idn12" name="idn1" /><label for="idn12">Lansia > 65 th</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Pasien dengan kognitif rendah (tidak sekolah, buta huruf, SD/sederajat)</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="idn21" name="idn2" /><label for="idn21">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="idn22" name="idn2" /><label for="idn22">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Berisiko tinggi</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Biaya tinggi" id="idn31" name="idn3" /><label for="idn31">Biaya tinggi</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Potensial" id="idn32" name="idn3" /><label for="idn32">Potensial</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sistem pembayaran yang rumit" id="idn33" name="idn3" /><label for="idn33">Sistem pembayaran yang rumit</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Kasus komplek (penyakit kronis, penyakit yang memakan biaya tinggi)</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Multiple diagnosa" id="idn41" name="idn4" /><label for="idn41">Multiple diagnosa</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Katastropik" id="idn42" name="idn4" /><label for="idn42">Katastropik</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ditangani lebih dari 2 dokter" id="idn43" name="idn4" /><label for="idn43">Ditangani lebih dari 2 dokter</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Terminal" id="idn44" name="idn4" /><label for="idn44">Terminal</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Penyakit kronis" id="idn45" name="idn4" /><label for="idn45">Penyakit kronis</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kemoterapi/kasus kanker" id="idn46" name="idn4" /><label for="idn46">Kemoterapi/kasus kanker</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Status fungsional rendah</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kebutuhan alat bantu hidup" id="idn51" name="idn5" /><label for="idn51">Kebutuhan alat bantu hidup</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Riwayat penggunaan alat medis di masa lalu" id="idn52" name="idn5" /><label for="idn52">Riwayat penggunaan alat medis di masa lalu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat gangguan mental</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Krisis keluarga" id="idn61" name="idn6" /><label for="idn61">Krisis keluarga</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Upaya bunuh diri" id="idn62" name="idn6" /><label for="idn62">Upaya bunuh diri</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Isu sosial (narkoba, terlantar)" id="idn63" name="idn6" /><label for="idn63">Isu sosial (narkoba, terlantar)</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat perawatan pasien</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sering masuk IGD" id="idn71" name="idn7" /><label for="idn71">Sering masuk IGD</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Lama dirawat melebihi rata-rata" id="idn72" name="idn7" /><label for="idn72">Lama dirawat melebihi rata-rata</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Rawat inap berulang dalam waktu 1 x 24 jam" id="idn73" name="idn7" /><label for="idn73">Rawat inap berulang dalam waktu 1 x 24 jam</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Membantu kontinuitas pelayanan sesuai rencana pemulangan pasien (Discharge planning)</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="idn81" name="idn8" /><label for="idn81">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="idn82" name="idn8" /><label for="idn82">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_identifikasi" class="btn btn-success pull-right" onclick="saveMpp('identifikasi','evaluasi_awal')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_identifikasi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-asesmen">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_asesmen">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_asesmen"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ases0">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ases00">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Fisik, fungsional, kognitif, kemandirian</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sadar" id="ases11" name="ases1" /><label for="ases11">Sadar</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak sadar" id="ases12" name="ases1" /><label for="ases12">Tidak sadar</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Fisik normal" id="ases13" name="ases1" /><label for="ases13">Fisik normal</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada gangguan fisik" id="ases14" name="ases1" /><label for="ases14">Ada gangguan fisik</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dibantu penuh" id="ases15" name="ases1" /><label for="ases15">Dibantu penuh</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dibantu sebagaian" id="ases16" name="ases1" /><label for="ases16">Dibantu sebagaian</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Mandiri" id="ases17" name="ases1" /><label for="ases17">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Lain-lain" id="ases18" name="ases1" /><label for="ases18">Lain-lain</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat Kesehatan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak perna dirawat" id="ases21" name="ases2" /><label for="ases21">Tidak perna dirawat</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Perna" id="ases22" name="ases2" /><label for="ases22">Pernah</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perilaku psiko, spiritual, sosial, kultural</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tenang" id="ases31" name="ases3" /><label for="ases31">Tenang</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Cemas" id="ases32" name="ases3" /><label for="ases32">Cemas</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Depresi" id="ases33" name="ases3" /><label for="ases33">Depresi</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Marah" id="ases34" name="ases3" /><label for="ases34">Marah</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kesehatan mental dan kognitif</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak ada gangguan" id="ases41" name="ases4" /><label for="ases41">Tidak ada gangguan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada riwayat gangguan mental" id="ases42" name="ases4" /><label for="ases42">Ada riwayat gangguan mental</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tersedianya dukungan keluarga, kemampuan merawat dari pemberiasuhan</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Handal" id="ases51" name="ases5" /><label for="ases51">Handal</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dipertanyakan" id="ases52" name="ases5" /><label for="ases52">Dipertanyakan</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kriris" id="ases53" name="ases5" /><label for="ases53">Kriris</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak ada" id="ases54" name="ases5" /><label for="ases54">Tidak ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Finalsial / sumber keuangan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Pensiunan" id="ases61" name="ases6" /><label for="ases61">Pensiunan</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Wiraswasta" id="ases62" name="ases6" /><label for="ases62">Wiraswasta</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak bekerja" id="ases63" name="ases6" /><label for="ases63">Tidak bekerja</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Buruh pekerja tidak tetap" id="ases64" name="ases6" /><label for="ases64">Buruh pekerja tidak tetap</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Asuransi / penjaminan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada" id="ases71" name="ases7" /><label for="ases71">Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada tidak aktif" id="ases72" name="ases7" /><label for="ases72">Ada tidak aktif</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak ada" id="ases73" name="ases7" /><label for="ases73">Tidak ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat penggunaan obat NAPSA</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ases81" name="ases8" /><label for="ases81">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada" id="ases82" name="ases8" /><label for="ases82">Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Riwayat trauma / kekerasan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ases91" name="ases9" /><label for="ases91">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada" id="ases92" name="ases9" /><label for="ases92">Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemahaman tentang kesehatan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Paham dan patuh" id="ases101" name="ases10" /><label for="ases101">Paham dan patuh</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Paham dan tidak patuh" id="ases102" name="ases10" /><label for="ases102">Paham dan tidak patuh</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak paham" id="ases103" name="ases10" /><label for="ases103">Tidak paham</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kemampuan menerima perubahan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Mampu beradaptasi" id="ases111" name="ases11" /><label for="ases111">Mampu beradaptasi</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak mampu beradaptasi" id="ases112" name="ases11" /><label for="ases112">Tidak mampu beradaptasi</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Aspek Legal</label>
                            <div class="col-md-9">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sedang dalam proses hukum" id="ases121" name="ases12" /><label for="ases121">Sedang dalam proses hukum</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Perna tersangkut masalah hukum" id="ases122" name="ases12" /><label for="ases122">Perna tersangkut masalah hukum</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak perna tersangkut masalah hukum" id="ases123" name="ases12" /><label for="ases123">Tidak perna tersangkut masalah hukum</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_asesmen" class="btn btn-success pull-right" onclick="saveMpp('asesmen','evaluasi_awal')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_asesmen" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-identifikasi_resiko">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Identifikasi Masalah Resiko
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_identifikasi_resiko">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_identifikasi_resiko"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ir1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ir2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Identifikasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control" style="margin-top: 7px" rows="6" id="ir3"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_identifikasi_resiko" class="btn btn-success pull-right" onclick="saveMpp('identifikasi_resiko','evaluasi_awal')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_identifikasi_resiko" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-perencanaan_mpp">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Perencanaan Manajemen Pelaksanaan Pasien (MPP)
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_perencanaan_mpp">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_perencanaan_mpp"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="pm1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="pm2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perencanaan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" style="margin-top: 7px" rows="6" id="pm3"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_perencanaan_mpp" class="btn btn-success pull-right" onclick="saveMpp('perencanaan_mpp','evaluasi_awal')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_perencanaan_mpp" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-impementasi_mpp">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Form B Catatan Implementasi MPP
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_mpp_impementasi_mpp">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_mpp_impementasi_mpp"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_impementasi_mpp">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_impementasi_mpp"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button type="button" class="btn btn-success dropdown-toggle"
                                data-toggle="dropdown" style="height: 34px">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="showModalMpp('rencana_mpp')" style="cursor: pointer"><i class="fa fa-plus"></i> Pelaksanaan Rencana MPP</a></li>
                            <li><a onclick="showModalMpp('monitoring_mpp')" style="cursor: pointer"><i class="fa fa-plus"></i> Monitoring</a></li>
                            <li><a onclick="showModalMpp('fasilitas_pelayanan')" style="cursor: pointer"><i class="fa fa-plus"></i> Memfasilitasi Pelayanan</a></li>
                            <li><a onclick="showModalMpp('advokasi')" style="cursor: pointer"><i class="fa fa-plus"></i> Advokasi Pelayanan Pasien</a></li>
                            <li><a onclick="showModalMpp('hasil_pelayanan')" style="cursor: pointer"><i class="fa fa-plus"></i> Hasil Pelayanan</a></li>
                            <li><a onclick="showModalMpp('terminasi')" style="cursor: pointer"><i class="fa fa-plus"></i> Terminasi MPP</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_mpp_impementasi_mpp">
                        <tbody>
                        <tr id="row_mpp_rencana_mpp">
                            <td>Pelaksanaan Rencana MPP</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_rencana_mpp" class="hvr-grow" onclick="detailFormMpp('rencana_mpp')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_rencana_mpp" class="hvr-grow btn-hide" onclick="conMPP('rencana_mpp', 'impementasi_mpp')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_monitoring_mpp">
                            <td>Monitoring</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_monitoring_mpp" class="hvr-grow" onclick="detailFormMpp('monitoring_mpp')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_monitoring_mpp" class="hvr-grow btn-hide" onclick="conMPP('monitoring_mpp', 'impementasi_mpp')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_fasilitas_pelayanan">
                            <td>Memfasilitasi Pelayanan</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_fasilitas_pelayanan" class="hvr-grow" onclick="detailFormMpp('fasilitas_pelayanan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_fasilitas_pelayanan" class="hvr-grow btn-hide" onclick="conMPP('fasilitas_pelayanan', 'impementasi_mpp')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_advokasi">
                            <td>Advokasi Pelayanan Pasien</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_advokasi" class="hvr-grow" onclick="detailFormMpp('advokasi')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_advokasi" class="hvr-grow btn-hide" onclick="conMPP('advokasi', 'impementasi_mpp')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_hasil_pelayanan">
                            <td>Hasil Pelayanan</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_hasil_pelayanan" class="hvr-grow" onclick="detailFormMpp('hasil_pelayanan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_hasil_pelayanan" class="hvr-grow btn-hide" onclick="conMPP('hasil_pelayanan', 'impementasi_mpp')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_mpp_terminasi">
                            <td>Terminasi MPP</td>
                            <td width="20%" align="center">
                                <img id="btn_mpp_terminasi" class="hvr-grow" onclick="detailFormMpp('terminasi')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_terminasi" class="hvr-grow btn-hide" onclick="conMPP('terminasi', 'impementasi_mpp')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-mpp-rencana_mpp">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pelaksanaan Rencana MPP
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_rencana_mpp">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_rencana_mpp"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="rc1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="rc2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perencanaan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" style="margin-top: 7px" rows="6" id="rc3"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_rencana_mpp" class="btn btn-success pull-right" onclick="saveMpp('rencana_mpp','impementasi_mpp')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_rencana_mpp" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-monitoring_mpp">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Monitoring
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_monitoring_mpp">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_monitoring_mpp"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="mm1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="mm2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Monitoring</label>
                            <div class="col-md-8">
                                <textarea class="form-control" style="margin-top: 7px" rows="6" id="mm3"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_monitoring_mpp" class="btn btn-success pull-right" onclick="saveMpp('monitoring_mpp','impementasi_mpp')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_monitoring_mpp" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-fasilitas_pelayanan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Memfasilitasi Pelayanan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_fasilitas_pelayanan">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_fasilitas_pelayanan"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="fp1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="fp2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Konsultasi / kolaborasi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="fp3" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Second opinion</label>
                            <div class="col-md-8">
                                <input class="form-control" id="fp4" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Rawat bersama / alih rawat </label>
                            <div class="col-md-8">
                                <input class="form-control" id="fp5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Komunikasi / Edukasi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="fp6" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Rujukan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="fp7" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Lain Lain</label>
                            <div class="col-md-8">
                                <input class="form-control" id="fp8" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_fasilitas_pelayanan" class="btn btn-success pull-right" onclick="saveMpp('fasilitas_pelayanan','impementasi_mpp')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_fasilitas_pelayanan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-advokasi">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Advokasi pelayanan pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_advokasi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_advokasi"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="ap1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ap2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap31" value="Diskusi dengan PPA staf lain tenatng kebutuhan pasien agar sesuai dengan cp">
                                <label for="ap31"></label> Diskusi dengan PPA staf lain tenatng kebutuhan pasien agar sesuai dengan cp
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap32" value="Diskusi dengan PPA untuk penanganan komplikasi">
                                <label for="ap32"></label> Diskusi dengan PPA untuk penanganan komplikasi
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap33" value="Memberikan edukasi pasien dan keluarga tentang penyakit, kondisi terkini dan rencana pelayanan">
                                <label for="ap33"></label> Memberikan edukasi pasien dan keluarga tentang penyakit, kondisi terkini dan rencana pelayanan
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap34" value="Meningkatkan kemandirian untuk menentukan pilihan/pengambilan keputusan">
                                <label for="ap34"></label> Meningkatkan kemandirian untuk menentukan pilihan/pengambilan keputusan
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap35" value="Memberik edukasi mengenai kondisi pasien">
                                <label for="ap35"></label> Memberik edukasi mengenai kondisi pasien
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap36" value="Mengenali, mencegah, menghinari disparitas untuk mengakses mutu dan hasil pelayanan terkait dengan ras, etnik, agama, gender, budaya, status, pernikahan, umur, politik, mental, kognitif">
                                <label for="ap36"></label> Mengenali, mencegah, menghinari disparitas untuk mengakses mutu dan hasil pelayanan terkait dengan ras, etnik, agama, gender, budaya, status, pernikahan, umur, politik, mental, kognitif
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap37" value="Untuk memenuhi kebutuhan pelayanan yang berkembang/bertambah karena perbuatan kondisi">
                                <label for="ap37"></label> Untuk memenuhi kebutuhan pelayanan yang berkembang/bertambah karena perbuatan kondisi
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="ap3" id="ap38" value="Membuat komunikasi dengan instansi terkait tentang upaya penyelsaian kendala keuangan">
                                <label for="ap38"></label> Membuat komunikasi dengan instansi terkait tentang upaya penyelsaian kendala keuangan
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_advokasi" class="btn btn-success pull-right" onclick="saveMpp('advokasi','impementasi_mpp')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_advokasi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-hasil_pelayanan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Hasil Pelayanan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_hasil_pelayanan">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_hasil_pelayanan"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="hp1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="hp2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Hasil Pelayanan</label>
                            <div class="col-md-8">
                                <textarea class="form-control" style="margin-top: 7px" rows="6" id="hp3"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_hasil_pelayanan" class="btn btn-success pull-right" onclick="saveMpp('hasil_pelayanan','impementasi_mpp')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_hasil_pelayanan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-mpp-terminasi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Terminasi MPP
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_mpp_terminasi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_mpp_terminasi"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-8">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="tp1">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="tp2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Terminasi MPP</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetTp3(this.value)" value="Perencanaan Pulang" id="tp31" name="tp3" /><label for="tp31">Perencanaan Pulang</label>
                                </div>
                            </div>
                            <div class="col-md-offset-3 col-md-8">
                                <input class="form-control" style="margin-top: 7px; display: none;" id="tp_ket31">
                            </div>
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetTp3(this.value)" value="Home Care" id="tp32" name="tp3" /><label for="tp32">Home Care</label>
                                </div>
                            </div>
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetTp3(this.value)" value="Lain-Lain" id="tp33" name="tp3" /><label for="tp33">Lain-Lain</label>
                                </div>
                            </div>
                            <div class="col-md-offset-3 col-md-8">
                                <input class="form-control"  style="margin-top: 7px; display: none" id="tp_ket32">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_mpp_terminasi" class="btn btn-success pull-right" onclick="saveMpp('terminasi','impementasi_mpp')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_mpp_terminasi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

