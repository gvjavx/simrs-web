<div class="modal fade" id="modal-rb-awal_kebidanan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Awal Kebidanan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_rb_awal_kebidanan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_awal_kebidanan"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_awal_kebidanan">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_awal_kebidanan"></p>
                    </div>
                    <button type="button" onclick="showModalRB('awal_kebidanan_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_awal_kebidanan">
                        <tbody>
                        <tr id="row_rb_awal_kebidanan_rb">
                            <td>Asesmen Awal Kebidanan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_awal_kebidanan_rb" class="hvr-grow"
                                     onclick="detailRB('awal_kebidanan_rb')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">

                                <img id="delete_awal_kebidanan_rb" class="hvr-grow btn-hide" onclick="conRB('awal_kebidanan_rb', 'awal_kebidanan')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-rb-awal_kebidanan_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Awal Kebidanan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_awal_kebidanan_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_awal_kebidanan_rb"></p>
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
                                    <input class="form-control tgl" id="rb1">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="rb2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row ">
                        <div class="form-group">
                            <label class="col-md-5">Kawin</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb3" type="number" placeholder="kali">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Dengan Suami sekarang </label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb4" type="number" placeholder="tahun">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Kawin 1 pada umur </label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb5" type="number" placeholder="tahun">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Haid Manarche</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb6" type="number" placeholder="tahun">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Cyclus</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb7" type="number" placeholder="hari">
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Teratur" id="rb81" name="rb8"/><label for="rb81">Teratur</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="rb82" name="rb8"/><label for="rb82">Tidak Teratur</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Lama</label>
                            <div class="col-md-3">
                                <input onchange="setHPL('rb12', 'rb11', this.id)" class="form-control" id="rb9" type="number" placeholder="hari">
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sakit" id="rb101" name="rb10"/><label for="rb101">Sakit</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="rb102" name="rb10"/><label for="rb102">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">HPHT</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input onchange="setHPL('rb12', this.id, 'rb9')" class="form-control ptr-tgl" id="rb11" placeholder="HTPH" readonly style="cursor: pointer">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">HPL</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control" id="rb12" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Darah Putih</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb13">
                            </div>
                            <label class="col-md-2">Banyaknya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb14" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Lamanya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb15" type="number">
                            </div>
                            <label class="col-md-2">Warnanya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb16" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Baunya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb17" type="number">
                            </div>
                            <label class="col-md-2">Contact Bleeding</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb18" type="number">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kehamilan dan Persalinan dulu</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb19" type="number" placeholder="G">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb20" type="number" placeholder="P">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb21" type="number" placeholder="A">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control tahun" placeholder="Tahun">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control tempat" placeholder="Tempat">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control umur" placeholder="Umur Hamil">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jenis" placeholder="Jenis Persalinan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control penolong" placeholder="Penolong">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control penyulit" placeholder="Penyulit">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jk" placeholder="JK/BL">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control pendarahan" placeholder="Perdarahan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control hidup_mati" placeholder="Hidup/mati">
                            </div>
                            <div class="col-md-1">
                                <button class="btn btn-success" onclick="setRiwayatHamil('kebidanan')"><i class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div id="set_riwayat_hamil"></div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keluhan</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="rb22"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Nafsu Makan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb23">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kencing</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rb24">
                            </div>
                            <div class="col-md-5">
                                <input class="form-control" id="rb25" placeholder="buang air besar">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Penyakit Pasien</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb26">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Operasi</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb27">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb28" placeholder="sakit kerat">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb29" placeholder="perna dirawat">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat penyakit Keluarga</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb30">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diabetes</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rb31">
                            </div>
                            <div class="col-md-5">
                                <input class="form-control" id="rb32" placeholder="paru - paru">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Hipertensi</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rb33">
                            </div>
                            <div class="col-md-5">
                                <input class="form-control" id="rb34" placeholder="orang / anak cacat">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Gynekologi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb35">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat KB</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb36">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Alergi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb37">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Pemeriksaan Tanggal</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb38">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keadaan Umum</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb39">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kesadaran</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb40">
                            </div>
                            <label class="col-md-3">Tinggi Badan</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb41" type="number" placeholder="Cm">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">BB sebelum hamil</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb42" type="number" placeholder="Kg">
                            </div>
                            <label class="col-md-3">BB setelah hamil</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb43" type="number" placeholder="Kg">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <span>Tensi (mmHg)</span>
                                <input class="form-control" id="rb44" data-inputmask="'mask': ['999/999']" data-mask="">
                            </div>
                            <div class="col-md-3">
                                <span>Nadi (x/menit)</span>
                                <input class="form-control" id="rb45" type="number">
                            </div>
                            <div class="col-md-3">
                                <span>Suhu (&#8451)</span>
                                <input class="form-control" id="rb46" type="number">
                            </div>
                            <div class="col-md-3">
                                <span>RR (x/menit)</span>
                                <input class="form-control" id="rb47" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <span>TFU (cm)</span>
                                <input class="form-control" id="rb48" type="number">
                            </div>
                            <div class="col-md-3">
                                <span>DJJ (x/menit)</span>
                                <input class="form-control" id="rb49" type="number">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb50">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Indikasi Rawat Inap</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb51">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb52">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="rb53" width="220"
                                        onmouseover="paintTtd('rb53')"></canvas>
                                <input style="margin-left: 10px" class="form-control nama_dokter_ri" id="nama_terang_rb53" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control sip_dokter_ri" id="sip_rb53" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('rb53')"><i
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
                <button id="save_rb_awal_kebidanan_rb" class="btn btn-success pull-right"
                        onclick="saveRB('awal_kebidanan_rb','awal_kebidanan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_awal_kebidanan_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>