<div class="modal fade" id="modal-rb-awal_kandungan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Awal Kandungan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_rb_awal_kandungan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_rb_awal_kandungan"></p>
                    </div>
                    <button type="button" onclick="showModalRB('awal_kandungan_rb')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_rb_awal_kandungan">
                        <tbody>
                        <tr id="row_rb_awal_kandungan_rb">
                            <td>Asesmen Awal Kandungan</td>
                            <td width="20%" align="center">
                                <img id="btn_rb_awal_kandungan_rb" class="hvr-grow"
                                     onclick="detailRB('awal_kandungan_rb')"
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

<div class="modal fade" id="modal-rb-awal_kandungan_rb">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Awal Kandungan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_rb_awal_kandungan_rb">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_rb_awal_kandungan_rb"></p>
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
                                <input class="form-control" id="rb3" type="number" placeholder="Kali">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Lama Dengan Suami sekarang </label>
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
                            <label class="col-md-5">Haid Manarche</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb6" type="number" placeholder="tahun">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Cyclus</label>
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
                                    <input type="radio" value="Tidak" id="rb82" name="rb8"/><label for="rb82">Teratur</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Lama</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb9" type="number" placeholder="hari">
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sakit" id="rb511" name="rb51"/><label for="rb511">Sakit</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="rb512" name="rb51"/><label for="rb512">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-5">Darah beku/encer : HT yang biasa</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb10" type="number">
                            </div>
                            <label class="col-md-1">HPL</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb11" type="number">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Darah Putih</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb12">
                            </div>
                            <label class="col-md-2">Banyaknya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb13" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Lamanya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb14" type="number">
                            </div>
                            <label class="col-md-2">Warnanya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb15" type="number">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Baunya</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb16" type="number">
                            </div>
                            <label class="col-md-2">Contact Bleeding</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb17" type="number">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kehamilan dan Persalinan dulu</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb18" type="number" placeholder="P">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb19" type="number" placeholder="G">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb20" type="number" placeholder="Ab">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <input class="form-control" id="rb21" type="number" placeholder="Ah">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control tahun" placeholder="No. Tahun">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control hamil" placeholder="Kehamilan">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control persalinan" placeholder="Persalinan">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control oleh" placeholder="Oleh">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control jenis" placeholder="Jenis">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control berat" placeholder="Berat">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control hidup_mati" placeholder="Hidup/Mati">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control pendarahan" placeholder="Pendarahan">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control nifas" placeholder="Nifas">
                            </div>
                            <div class="col-md-1">
                                <button class="btn btn-success" onclick="setRiwayatHamil('kandungan')"><i class="fa fa-plus"></i></button>
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
                            <div class="col-md-3">
                                <input class="form-control" id="rb23">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb24" placeholder="batuk">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb25" placeholder="sesak">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kencing</label>
                            <div class="col-md-4">
                                <input class="form-control" id="rb26">
                            </div>
                            <div class="col-md-5">
                                <input class="form-control" id="rb27" placeholder="buang air besar">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Pemakaian obat</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb28">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Obat yang tidak tahan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb29">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat penyakit</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb30">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Malaria</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb31">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb32" placeholder="sakit perut">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb33" placeholder="pernah dirawat">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Operasi</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb34">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb35" placeholder="paru-paru">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb36" placeholder="Sesak">
                            </div>
                            <div class="col-md-offset-3 col-md-3">
                                <input style="margin-top: 7px" class="form-control" id="rb37" placeholder="kelamin">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat penyakit keluarga</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb38">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diabetes</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb39">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb40" placeholder="hipertensi">
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" id="rb41" placeholder="paru-paru">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Gameli</label>
                            <div class="col-md-3">
                                <input class="form-control" id="rb42">
                            </div>
                            <div class="col-md-6">
                                <input class="form-control" id="rb43" placeholder="orang/anak cacat">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Pemeriksaan Tanggal</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb44">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Keadaan Umum</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb45">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Riwayat Alergi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb46">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb47">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Indikasi Rawat Inap</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb48">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Terapi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="rb49">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="rb50" width="220"
                                        onmouseover="paintTtd('rb50')"></canvas>
                                <input style="margin-left: 10px" class="form-control" id="nama_terang_rb50" placeholder="Nama Terang">
                                <input style="margin-left: 10px; margin-top: 3px" class="form-control" id="sip_rb50" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('rb50')"><i
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
                <button id="save_rb_awal_kandungan_rb" class="btn btn-success pull-right"
                        onclick="saveRB('awal_kandungan_rb','awal_kandungan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_rb_awal_kandungan_rb" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>