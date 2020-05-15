<div class="modal fade" id="modal-ina-asesmen">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Awal Keperawatan Rawat Inap
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_asesmen">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_asesmen"></p>
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
                            <li><a onclick="showModalAsesmenRawatInap('riwayat_kesehatan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Riwayat Kesehatan</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('keadaan_umum')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Keadaan Umum</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('pemeriksaan_fisik')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pemeriksaan Fisik</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('resiko_dekubitus')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Asesmen Resiko Dekubitus (dengan Skala Norton)</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('nyeri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Asesmen Nyeri</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('funsional')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Asesmen Fungsional (Barthel Indek Scale )</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_appendecitomy">
                        <tbody>
                        <tr id="row_ina_riwayat_kesehatan">
                            <td>Riwayat Kesehatan</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_riwayat_kesehatan" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('riwayat_kesehatan')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_keadaan_umum">
                            <td>Keadaan Umum</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_keadaan_umum" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('keadaan_umum')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_pemeriksaan_fisik">
                            <td>Pemeriksaan Fisik</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_pemeriksaan_fisik" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('pemeriksaan_fisik')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_resiko_dekubitus">
                            <td>Asesmen Resiko Dekubitus (dengan Skala Norton)</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_resiko_dekubitus" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('resiko_dekubitus')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_nyeri">
                            <td>Asesmen Nyeri</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_nyeri" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('nyeri')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_funsional">
                            <td>Asesmen Fungsional (Barthel Indek Scale )</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_funsional" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('funsional')"
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

<div class="modal fade" id="modal-ina-riwayat_kesehatan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Riwayat Kesehatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_riwayat_kesehatan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_riwayat_kesehatan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Alasan Masuk RS</label>
                            <div class="col-md-8">
                                <textarea id="rk1" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Diagnosa Awal</label>
                            <div class="col-md-8">
                                <textarea id="rk2" style="margin-top: 7px"class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat Kesehatan yang lalu</label>
                            <div class="col-md-8">
                                <textarea id="rk3" style="margin-top: 7px" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat Penyakit Keluarga</label>
                            <div class="col-md-8">
                                <textarea id="rk4" style="margin-top: 7px" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Obat yang sedang dikonsumsi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="rk51" name="rk5" /><label for="rk51">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Ada" id="rk52" name="rk5" /><label for="rk52">Ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Alergi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk6')" value="Tidak" id="rk61" name="rk6" /><label for="rk61">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk6')" value="Ya" id="rk62" name="rk6" /><label for="rk62">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-rk6">
                            <div class="col-md-offset-4 col-md-4">
                                <select class="form-control" id="ket1_rk6" style="margin-top: 7px">
                                    <option value="Obat">Obat</option>
                                    <option value="Makanan">Makanan</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input placeholder="Keterangan" class="form-control" id="ket2_rk6" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Transfusi Darah</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk7')" value="Tidak" id="rk71" name="rk7" /><label for="rk71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk7')" value="Ya" id="rk72" name="rk7" /><label for="rk72">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-ina-rk7">
                        <div class="form-group">
                            <label class="col-md-4">Reaksi Transfusi Darah</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk71')" value="Tidak" id="ket1_rk71" name="ket1_rk7" /><label for="ket1_rk71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk71')" value="Ya" id="ket1_rk72" name="ket1_rk7" /><label for="ket1_rk72">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-rk71">
                            <div class="col-md-offset-4 col-md-8">
                                <input placeholder="Keterangan Reaksi Transfusi Darah" class="form-control" id="ket2_rk7" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Merokok</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk8')" value="Tidak" id="rk81" name="rk8" /><label for="rk81">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk8')" value="Ya" id="rk82" name="rk8" /><label for="rk82">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-rk8">
                            <div class="col-md-offset-4 col-md-4">
                                <input type="number" placeholder="Jumlah Batang/Hari" class="form-control" id="ket1_rk8" style="margin-top: 7px">
                            </div>
                            <div class="col-md-4">
                                <input type="number" placeholder="Lama/Tahun" class="form-control" id="ket2_rk8" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat Minum Minuman Keras</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk9')" value="Tidak" id="rk91" name="rk9" /><label for="rk91">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk9')" value="Ya" id="rk92" name="rk9" /><label for="rk92">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-ina-rk9">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <input placeholder="Jenis" class="form-control" id="ket1_rk9">
                            </div>
                            <div class="col-md-4">
                                <input type="number" placeholder="Jumlah" class="form-control" id="ket2_rk9">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Riwayat pergi ke luar negeri</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk10')" value="Tidak" id="rk101" name="rk10" /><label for="rk101">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'rk10')" value="Ya" id="rk102" name="rk10" /><label for="rk102">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="display: none" id="form-ina-rk10">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input placeholder="Nama Negera" class="form-control" id="ket1_rk10">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_riwayat_kesehatan" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('riwayat_kesehatan','asesmen')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_riwayat_kesehatan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-keadaan_umum">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Keadaan Umum</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_keadaan_umum">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_keadaan_umum"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kesadaran</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Apatis" id="kd11" name="kd1" /><label for="kd11">Apatis</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Coma" id="kd12" name="kd1" /><label for="kd12">Coma</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" value="Composmentis" id="kd13" name="kd1" /><label for="kd13">Composmentis</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Somnolent" id="kd14" name="kd1" /><label for="kd14">Somnolent</label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="custom02">
                                    <input type="radio" value="Sopor" id="kd15" name="kd1" /><label for="kd15">Sopor</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">GCS</label>
                            <div class="col-md-2">
                                <input placeholder="E" class="form-control" id="kd2_1">
                            </div>
                            <div class="col-md-2">
                                <input placeholder="V" class="form-control" id="kd2_2">
                            </div>
                            <div class="col-md-2">
                                <input placeholder="M" class="form-control" id="kd2_3">
                            </div>
                            <div class="col-md-2">
                                <input placeholder="Hasil" class="form-control" id="kd2_4">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tekanan Darah</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="number" class="form-control" id="kd3">
                                    <div class="input-group-addon"  style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1">Suhu</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input type="number" class="form-control" id="kd4">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Nadi</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input type="number" class="form-control" id="kd5">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Teratur" id="ket_kd51" name="ket_kd5" /><label for="ket_kd51">Teratur</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak Teratur" id="ket_kd52" name="ket_kd5" /><label for="ket_kd52">Tidak Teratur</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Nafas</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input type="number" class="form-control" id="kd6">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Teratur" id="ket_kd61" name="ket_kd6" /><label for="ket_kd61">Teratur</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak Teratur" id="ket_kd62" name="ket_kd6" /><label for="ket_kd62">Tidak Teratur</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Berat Badan</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input type="number" class="form-control" id="kd7">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        Kg
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-2">Tinggi Badan</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input type="number" class="form-control" id="kd8">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        cm
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_keadaan_umum" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('keadaan_umum','asesmen')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_keadaan_umum" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-pemeriksaan_fisik">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan Fisik</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_pemeriksaan_fisik">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_pemeriksaan_fisik"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kepala</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi1')" value="Tidak ada kelainan" id="pfi11" name="pfi1" /><label for="pfi11">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi1')" value="Ada kelainan" id="pfi12" name="pfi1" /><label for="pfi12">Ada kelainan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi1">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi1" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Wajah</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi2')" value="Tidak ada kelainan" id="pfi21" name="pfi2" /><label for="pfi21">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi2')" value="Kelainan bawaan" id="pfi22" name="pfi2" /><label for="pfi22">Kalainan Bawaan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi2')" value="Lain-Lain" id="pfi23" name="pfi2" /><label for="pfi23">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi2">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi2" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Mata</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi3')" value="Tidak ada kelainan" id="pfi31" name="pfi3" /><label for="pfi31">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi3')" value="Ada kelainan" id="pfi32" name="pfi3" /><label for="pfi32">Ada kelainan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi3">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi3" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Telinga</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi4')" value="Tidak ada kelainan" id="pfi41" name="pfi4" /><label for="pfi41">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi4')" value="Ada kelainan" id="pfi42" name="pfi4" /><label for="pfi42">Ada kelainan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi4">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi4" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Hidung</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi5')" value="Tidak ada kelainan" id="pfi51" name="pfi5" /><label for="pfi51">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi5')" value="NGT" id="pfi52" name="pfi5" /><label for="pfi52">NGT</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi5')" value="O2 Nasal" id="pfi53" name="pfi5" /><label for="pfi53">O2 Nasal</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi5')" value="Lain-Lain" id="pfi54" name="pfi5" /><label for="pfi54">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi51">
                            <div class="col-md-offset-3 col-md-4">
                                <input placeholder="Tanggal" id="ket1_pfi5" class="form-control tgl">
                            </div>
                            <div class="col-md-4">
                                <input placeholder="No" id="ket2_pfi5" class="form-control">
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi52">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="lt/menit" id="ket3_pfi5" class="form-control">
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi5">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket4_pfi5" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Mulut</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi6')" value="Tidak ada kelainan" id="pfi61" name="pfi6" /><label for="pfi61">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi6')" value="Bibir pucat" id="pfi62" name="pfi6" /><label for="pfi62">Bibir pucat</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi6')" value="Lain-Lain" id="pfi63" name="pfi6" /><label for="pfi63">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi6">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi6" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Leher</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi7')" value="Tidak ada kelainan" id="pfi71" name="pfi7" /><label for="pfi71">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi7')" value="Kaku kuduk" id="pfi72" name="pfi7" /><label for="pfi72">Kaku kuduk</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi7')" value="Lain-Lain" id="pfi73" name="pfi7" /><label for="pfi73">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi7">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi7" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Dada</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi8')" value="Tidak ada kelainan" id="pfi81" name="pfi8" /><label for="pfi81">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi8')" value="Kaku kuduk" id="pfi82" name="pfi8" /><label for="pfi82">Kaku kuduk</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi8')" value="Lain-Lain" id="pfi83" name="pfi8" /><label for="pfi83">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi8">
                            <div class="col-md-offset-3 col-md-8" style="">
                                <input placeholder="Keterangan" id="ket_pfi8" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Perut</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi9')" value="Tidak ada kelainan" id="pfi91" name="pfi9" /><label for="pfi91">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi9')" value="Ada kelainan" id="pfi92" name="pfi9" /><label for="pfi92">Ada kelainan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi9">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi9" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Anggota</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi10')" value="Tidak ada kelainan" id="pfi101" name="pfi10" /><label for="pfi101">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi10')" value="Cacat bawaan" id="pfi102" name="pfi10" /><label for="pfi102">Cacat bawaan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi10')" value="Lain-Lain" id="pfi103" name="pfi10" /><label for="pfi103">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi10">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi10" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Gerak</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi11')" value="Fraktur" id="pfi111" name="pfi11" /><label for="pfi111">Fraktur</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi11')" value="Kelemahan gerak" id="pfi112" name="pfi11" /><label for="pfi112">Kelemahan gerak</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi11')" value="Lain-Lain" id="pfi113" name="pfi11" /><label for="pfi113">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi11">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi11" class="form-control">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kulit</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi12')" value="Tidak ada kelainan" id="pfi121" name="pfi12" /><label for="pfi121">Tidak ada kelainan</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" onclick="showKetIna(this.value, 'pfi12')" value="Ada kelainan" id="pfi122" name="pfi12" /><label for="pfi122">Ada kelainan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-pfi12">
                            <div class="col-md-offset-3 col-md-8">
                                <input placeholder="Keterangan" id="ket_pfi12" class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_pemeriksaan_fisik" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('pemeriksaan_fisik','asesmen')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_pemeriksaan_fisik" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-resiko_dekubitus">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Resiko Dekubitus (dengan Skala Norton)</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_resiko_dekubitus">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_resiko_dekubitus"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kondisi Fisik</label>
                            <div class="col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Baik, Pasien tidak mengalami cacat atau kelemahan fisik|4" id="rd11" name="rd1" /><label for="rd11">Baik, Pasien tidak mengalami cacat atau kelemahan fisik</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Cukup|3" id="rd12" name="rd1" /><label for="rd12">Cukup</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Buruk|2" id="rd13" name="rd1" /><label for="rd13">Buruk</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Sangat Buruk|1" id="rd14" name="rd1" /><label for="rd14">Sangat Buruk</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Kondisi Mental</label>
                            <div class="col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Composmentis|4" id="rd21" name="rd2" /><label for="rd21">Composmentis</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Apatis|3" id="rd22" name="rd2" /><label for="rd22">Apatis</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Gelisah, Disorientasi, Sopor|2" id="rd23" name="rd2" /><label for="rd23">Gelisah, Disorientasi, Sopor</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Koma / Stupor|1" id="rd24" name="rd2" /><label for="rd24">Koma / Stupor</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Rentang Aktivitas</label>
                            <div class="col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Ambulasi / Pasien bisa bergerak bebas|4" id="rd31" name="rd3" /><label for="rd31">Ambulasi / Pasien bisa bergerak bebas</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Berjalan dengan alat bantu, misal Kruk, Tripod dll|3" id="rd32" name="rd3" /><label for="rd32">Berjalan dengan alat bantu, misal Kruk, Tripod dll</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Hanya bisa beraktifitas duduk|2" id="rd33" name="rd3" /><label for="rd33">Hanya bisa beraktifitas duduk</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Pasien bedrest|1" id="rd34" name="rd3" /><label for="rd34">Pasien bedrest</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Mobilitas</label>
                            <div class="col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Bisa bergerak bebas|4" id="rd41" name="rd4" /><label for="rd41">Bisa bergerak bebas</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Ada keterbatasan tetapi tidak memerlukan bantuan|3" id="rd42" name="rd4" /><label for="rd42">Ada keterbatasan tetapi tidak memerlukan bantuan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Bergerak sangat terbatas dan memerluakan bantuan minimal|2" id="rd43" name="rd4" /><label for="rd43">Bergerak sangat terbatas dan memerluakan bantuan minimal</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Imobilitas (Bantuan Penuh)|1" id="rd45" name="rd4" /><label for="rd45">Imobilitas (Bantuan Penuh)</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Inkontinentia</label>
                            <div class="col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="BAB dan BAK Normal|4" id="rd51" name="rd5" /><label for="rd51">BAB dan BAK Normal</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Kadang kesulitan BAB dan BAK|3" id="rd52" name="rd5" /><label for="rd52">Kadang kesulitan BAB dan BAK</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Pasien mengalami inkontinentia berkemih|2" id="rd53" name="rd5" /><label for="rd53">Pasien mengalami inkontinentia berkemih</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <div class="custom02">
                                    <input type="radio" value="Pasien mengalami inkontinentia BAB|1" id="rd54" name="rd5" /><label for="rd54">Pasien mengalami inkontinentia BAB</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_resiko_dekubitus" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('resiko_dekubitus','asesmen')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_resiko_dekubitus" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-nyeri">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Nyeri</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_nyeri">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_nyeri"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Nyeri</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="ny11" name="ny1" /><label for="ny11">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="ny12" name="ny1" /><label for="ny12">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3">Jenis</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Akut" id="ny21" name="ny2" /><label for="ny21">Akut</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Kronis" id="ny22" name="ny2" /><label for="ny22">Kronis</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3">Intensitas</label>
                            <div class="col-md-7">
                                <input class="form-control" id="ny3">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label>Nomeric Rating Scale</label>
                                <img src="<%= request.getContextPath() %>/pages/images/rating-scale.png" style="width: 100%">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label>Wong Baker Paint Scale</label>
                                <img src="<%= request.getContextPath() %>/pages/images/paint-scale.png" style="width: 100%">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_nyeri" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('nyeri','asesmen')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_nyeri" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-funsional">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Fungsional (Barthel Indek Scale )</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_funsional">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_funsional"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Makan/Minum</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mandiri|0" id="fun11" name="fun1" /><label for="fun11">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|1" id="fun12" name="fun1" /><label for="fun12">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|2" id="fun13" name="fun1" /><label for="fun13">100% dibantu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Mandi</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mandiri|0" id="fun21" name="fun2" /><label for="fun21">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|1" id="fun22" name="fun2" /><label for="fun22">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|2" id="fun23" name="fun2" /><label for="fun23">100% dibantu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Memakai Baju</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mandiri|0" id="fun31" name="fun3" /><label for="fun31">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|1" id="fun32" name="fun3" /><label for="fun32">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|2" id="fun33" name="fun3" /><label for="fun33">100% dibantu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">BAB/BAK</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mandiri|0" id="fun41" name="fun4" /><label for="fun41">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|1" id="fun42" name="fun4" /><label for="fun42">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|2" id="fun43" name="fun4" /><label for="fun43">100% dibantu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Naik Turun Tangga</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mandiri|0" id="fun51" name="fun5" /><label for="fun51">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|1" id="fun52" name="fun5" /><label for="fun52">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|2" id="fun53" name="fun5" /><label for="fun53">100% dibantu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_funsional" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('funsional','asesmen')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_funsional" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>