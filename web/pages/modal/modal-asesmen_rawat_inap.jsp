<div class="modal fade" id="modal-ina-asesmen_keperawatan_ri">
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
                         id="warning_ina_asesmen_keperawatan_ri">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_asesmen_keperawatan_ri"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_asesmen_keperawatan_ri">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_asesmen_keperawatan_ri"></p>
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
                            <li><a onclick="showModalAsesmenRawatInap('resiko_jatuh')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Resiko Jatuh</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('psiko_social')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Psiko Sosial</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('skrining_nutrisi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Skrining Nutrisi</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('neurologi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Neurologi</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('genitourinaria')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Genitourinaria</a></li>
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
                                <img id="delete_riwayat_kesehatan" class="hvr-grow btn-hide" onclick="conRI('riwayat_kesehatan', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_keadaan_umum">
                            <td>Keadaan Umum</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_keadaan_umum" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('keadaan_umum')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_keadaan_umum" class="hvr-grow btn-hide" onclick="conRI('keadaan_umum', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_pemeriksaan_fisik">
                            <td>Pemeriksaan Fisik</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_pemeriksaan_fisik" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('pemeriksaan_fisik')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pemeriksaan_fisik" class="hvr-grow btn-hide" onclick="conRI('pemeriksaan_fisik', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_resiko_dekubitus">
                            <td>Asesmen Resiko Dekubitus (dengan Skala Norton)</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_resiko_dekubitus" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('resiko_dekubitus')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_resiko_dekubitus" class="hvr-grow btn-hide" onclick="conRI('resiko_dekubitus', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_nyeri">
                            <td>Asesmen Nyeri</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_nyeri" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('nyeri')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_nyeri" class="hvr-grow btn-hide" onclick="conRI('nyeri', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_funsional">
                            <td>Asesmen Fungsional (Barthel Indek Scale )</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_funsional" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('funsional')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_funsional" class="hvr-grow btn-hide" onclick="conRI('funsional', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_resiko_jatuh">
                            <td>Resiko Jatuh</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_resiko_jatuh" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('resiko_jatuh')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_resiko_jatuh" class="hvr-grow btn-hide" onclick="conRI('resiko_jatuh', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_psiko_social">
                            <td>Psiko-Social</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_psiko_social" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('psiko_social')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_psiko_social" class="hvr-grow btn-hide" onclick="conRI('psiko_social', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_skrining_nutrisi">
                            <td>Skrining Nutrisi</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_skrining_nutrisi" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('skrining_nutrisi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_skrining_nutrisi" class="hvr-grow btn-hide" onclick="conRI('skrining_nutrisi', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_neurologi">
                            <td>Neurologi</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_neurologi" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('neurologi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_neurologi" class="hvr-grow btn-hide" onclick="conRI('neurologi', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_ina_genitourinaria">
                            <td>Genitourinaria</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_genitourinaria" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('genitourinaria')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_genitourinaria" class="hvr-grow btn-hide" onclick="conRI('genitourinaria', 'asesmen_keperawatan_ri')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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
                                <textarea id="rk1" class="form-control anamnese" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Diagnosa Awal</label>
                            <div class="col-md-8">
                                <textarea id="rk2" style="margin-top: 7px"class="form-control diagnosa-pasien" rows="3"></textarea>
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
                            <div class="col-md-8">
                                <input class="form-control alergi-pasien" id="rk6">
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
                        onclick="saveAsesmenRawatInap('riwayat_kesehatan','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
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
                                    <input class="form-control tensi-pasien" id="kd3">
                                    <div class="input-group-addon"  style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1">Suhu</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control suhu-pasien" id="kd4">
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
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input class="form-control nadi-pasien" id="kd5">
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
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input class="form-control rr-pasien" id="kd6">
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
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="number" class="form-control berat-pasien" id="kd7">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        Kg
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-2">Tinggi Badan</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input type="number" class="form-control tinggi-pasien" id="kd8">
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
                        onclick="saveAsesmenRawatInap('keadaan_umum','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
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
                        onclick="saveAsesmenRawatInap('pemeriksaan_fisik','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
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
                        onclick="saveAsesmenRawatInap('resiko_dekubitus','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
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
                                    <input type="radio" onclick="cekNyeri(this.value, 'ny3')" value="Tidak" id="ny11" name="ny1" /><label for="ny11">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" onclick="cekNyeri(this.value, 'ny3')" value="Ya" id="ny12" name="ny1" /><label for="ny12">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Lokasi</label>
                            <div class="col-md-7">
                                <input class="form-control" style="margin-top: 7px;" id="y_lokasi">
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
                    <input id="temp_scala" type="hidden">
                    <input id="temp_jenis" type="hidden">
                    <canvas id="choice_emoji" style="display: none"></canvas>
                    <hr class="garis">
                    <div id="set_nyeri"></div>
                    <%--<input id="temp_scala" type="hidden">--%>
                    <%--<canvas id="choice_emoji" style="display: none"></canvas>--%>
                    <%--<hr class="garis">--%>
                    <%--<div class="row" style="margin-top: 10px">--%>
                        <%--<div class="form-group">--%>
                            <%--<div class="col-md-2">--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/scala-0.png" class="nyeri"--%>
                                     <%--style="width: 100%; cursor: no-drop;" id="0">--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: 10px">0</p>--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: -10px">Tidak Nyeri</p>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-2">--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/scala-2.png" class="nyeri"--%>
                                     <%--style="width: 100%; cursor: no-drop" id="2" >--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: 10px">2</p>--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Nyeri</p>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-2">--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/scala-4.png" class="nyeri"--%>
                                     <%--style="width: 100%; cursor: no-drop" id="4" >--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: 10px">4</p>--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Lebih Nyeri</p>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-2">--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/scala-6.png" class="nyeri"--%>
                                     <%--style="width: 100%; cursor: no-drop" id="6" >--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: 10px">6</p>--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: -10px">Lebih Nyeri</p>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-2">--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/scala-8.png" class="nyeri"--%>
                                     <%--style="width: 100%; cursor: no-drop" id="8" >--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: 10px">8</p>--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: -10px">Sangat Nyeri</p>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-2">--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/scala-10.png" class="nyeri"--%>
                                     <%--style="width: 100%; cursor: no-drop" id="10" >--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: 10px">10</p>--%>
                                <%--<p class="text-center" style="font-size: 12px; margin-top: -10px">Nyeri Sangat Hebat</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr class="garis">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-12">--%>
                            <%--<img src="<%= request.getContextPath() %>/pages/images/scala-nyeri-number.jpg" style="width: 100%;">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr class="garis">--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<div class="col-md-12">--%>
                                <%--<label>Nomeric Rating Scale</label>--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/rating-scale.png" style="width: 100%">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<hr class="garis">--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<div class="col-md-12">--%>
                                <%--<label>Wong Baker Paint Scale</label>--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/paint-scale.png" style="width: 100%">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_nyeri" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('nyeri','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
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
                                    <input type="radio" value="Mandiri|10" id="fun11" name="fun1" /><label for="fun11">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|5" id="fun12" name="fun1" /><label for="fun12">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|0" id="fun13" name="fun1" /><label for="fun13">100% dibantu</label>
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
                                    <input type="radio" value="Mandiri|10" id="fun21" name="fun2" /><label for="fun21">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|5" id="fun22" name="fun2" /><label for="fun22">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|0" id="fun23" name="fun2" /><label for="fun23">100% dibantu</label>
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
                                    <input type="radio" value="Mandiri|10" id="fun31" name="fun3" /><label for="fun31">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|5" id="fun32" name="fun3" /><label for="fun32">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|0" id="fun33" name="fun3" /><label for="fun33">100% dibantu</label>
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
                                    <input type="radio" value="Mandiri|10" id="fun41" name="fun4" /><label for="fun41">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|5" id="fun42" name="fun4" /><label for="fun42">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|0" id="fun43" name="fun4" /><label for="fun43">100% dibantu</label>
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
                                    <input type="radio" value="Mandiri|10" id="fun51" name="fun5" /><label for="fun51">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|2" id="fun52" name="fun5" /><label for="fun52">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|0" id="fun53" name="fun5" /><label for="fun53">100% dibantu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Mengontrol BAB/BAK</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Mandiri|10" id="fun61" name="fun6" /><label for="fun61">Mandiri</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="50% dibantu|5" id="fun62" name="fun6" /><label for="fun62">50% dibantu</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="100% dibantu|0" id="fun63" name="fun6" /><label for="fun63">100% dibantu</label>
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
                        onclick="saveAsesmenRawatInap('funsional','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
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

<div class="modal fade" id="modal-ina-resiko_jatuh">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Resiko Jatuh</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_resiko_jatuh">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_resiko_jatuh"></p>
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
                                    <input class="form-control tgl" id="ps01">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ps02">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <input id="jenis_resiko" type="hidden">
                    <div id="set_resiko_jatuh"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_resiko_jatuh" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('resiko_jatuh','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_resiko_jatuh" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-psiko_social">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Psiko Social</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_psiko_social">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_psiko_social"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tempat Tinggal</label>
                            <div class="col-md-8">
                                <select onchange="showKetIna(this.value, 'tempat_tinggal')" id="psko1" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Rumah Sendiri">Rumah Sendiri</option>
                                    <option value="Bermsama Orang Tua">Bermsama Orang Tua</option>
                                    <option value="Bersama Mertua">Bersama Mertua</option>
                                    <option value="Bersama Anak">Bersama Anak</option>
                                    <option value="Lainnya">Lainnya</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" id="form-ina-tempat_tinggal" style="display: none;">
                            <div class="col-md-offset-3 col-md-8">
                                <input class="form-control" id="ket_tempat_tinggal" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Status Emosional</label>
                            <div class="col-md-8">
                                <select id="psko2" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tenang">Tenang</option>
                                    <option value="Cemas">Cemas</option>
                                    <option value="Marah">Marah</option>
                                    <option value="Sedih">Sedih</option>
                                    <option value="Takut">Takut</option>
                                    <option value="Kecenderungan Bunuh Diri">Kecenderungan Bunuh Diri</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Masalah Perilaku</label>
                            <div class="col-md-8">
                                <select onchange="showKetIna(this.value, 'perilaku')" id="psko3" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Tidak">Tidak</option>
                                    <option value="Ada">Ada</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-perilaku">
                            <div class="col-md-offset-3 col-md-8">
                                <input class="form-control" id="ket_perilaku" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Status Pernikahan</label>
                            <div class="col-md-8">
                                <select id="psko4" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Belum Menikah">Belum Menikah</option>
                                    <option value="Menikah">Menikah</option>
                                    <option value="Cerai">Cerai</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Hubungan pasien dengan keluarga</label>
                            <div class="col-md-8">
                                <select id="psko5" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Baik">Baik</option>
                                    <option value="Tidak">Tidak</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Agama</label>
                            <div class="col-md-8">
                                <select id="psko6" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Islam">Islam</option>
                                    <option value="Kristen">Kristen</option>
                                    <option value="Katolik">Katolik</option>
                                    <option value="Hindu">Hindu</option>
                                    <option value="Buddha">Buddha</option>
                                    <option value="Konghucu">Konghucu</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perlu dibantu dalam ibadah</label>
                            <div class="col-md-8">
                                <select id="psko7" class="form-control select2" style="width: 100%; margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Ya">Ya</option>
                                    <option value="Tidak">Tidak</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_psiko_social" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('psiko_social','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_psiko_social" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-skrining_nutrisi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Skrining Nutrisi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_skrining_nutrisi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_skrining_nutrisi"></p>
                </div>
                <div class="box-body">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="form-group">
                                <label style="margin-top: 7px">1. Apakah pasien mengalami penurunan berat badan yang tidak direncanakan /tidak diinginkan dalam 6 bulan terakhir?</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetIna(this.value, 'penurunan')" type="radio" value="Tidak|0" id="aud_nutrisional1" name="radio_aud_nutrisional" /><label for="aud_nutrisional1">Tidak</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetIna(this.value, 'penurunan')" type="radio" value="Tidak Yakin|1" id="aud_nutrisional2" name="radio_aud_nutrisional" /><label for="aud_nutrisional2">Tidak Yakin (ada tanda: baju menjadi longgar)</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetIna(this.value, 'penurunan')" type="radio" value="Ya" id="aud_nutrisional3" name="radio_aud_nutrisional" /><label for="aud_nutrisional3">Ya, ada penurunan BB sebanyak:</label>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="display: none;" id="form-ina-penurunan">
                            <div class="form-group">
                                <div class="col-md-3">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="1 - 5 kg|1" id="aud_penurunan1" name="radio_aud_penurunan" /><label for="aud_penurunan1">1 - 5 kg</label>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="6 - 10 kg|2" id="aud_penurunan2" name="radio_aud_penurunan" /><label for="aud_penurunan2">6 - 10 kg</label>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="11 - 15 kg|3" id="aud_penurunan3" name="radio_aud_penurunan" /><label for="aud_penurunan3">11 - 15 kg</label>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="> 15 kg|4" id="aud_penurunan4" name="radio_aud_penurunan" /><label for="aud_penurunan4">> 15 kg</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input onclick="showKetIna(this.value, 'penurunan')" type="radio" value="Tidak tahu berapa kg penurunannya|2" id="aud_nutrisional4" name="radio_aud_nutrisional" /><label for="aud_nutrisional4">Tidak tahu berapa kg penurunannya</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label style="margin-top: 7px">2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan/kesulitan menerima makanan?</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak|0" id="aud_nafsu1" name="radio_aud_nafsu1" /><label for="aud_nafsu1">Tidak</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya|1" id="aud_nafsu2" name="radio_aud_nafsu1" /><label for="aud_nafsu2">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label style="margin-top: 7px">3. Pasien dengan diagnosis khusus?</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak|0" id="diagnosis1" name="radio_diagnosis1" /><label for="diagnosis1">Tidak</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya|3" id="diagnosis2" name="radio_diagnosis1" /><label for="diagnosis2">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_skrining_nutrisi" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('skrining_nutrisi','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_skrining_nutrisi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-neurologi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan Neurologi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_neurologi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_neurologi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu1" value="Normal">
                                <label for="neu1"></label> Normal
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu2" value="Pusing">
                                <label for="neu2"></label> Pusing
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu3" value="Vertigo">
                                <label for="neu3"></label> Vertigo
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu4" value="Pupil Anisokor">
                                <label for="neu4"></label> Pupil Anisokor
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu5" value="Sakit Kepala">
                                <label for="neu5"></label> Sakit Kepala
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu6" value="Tremoe">
                                <label for="neu6"></label> Tremoe
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu7" value="Kejang">
                                <label for="neu7"></label> Kejang
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu8" value="Gelisah">
                                <label for="neu8"></label> Gelisah
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu9">
                                <label for="neu9"></label>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <input class="form-control" id="ket_neu9" onchange="$('#neu9').val(this.value)">
                        </div>
                        <div class="col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="neu" id="neu10">
                                <label for="neu10"></label>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <input class="form-control" id="ket_neu10" onchange="$('#neu10').val(this.value)">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_neurologi" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('neurologi','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_neurologi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-genitourinaria">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan Genitourinaria</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_genitourinaria">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_genitourinaria"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen11" value="Normal">
                                <label for="gen11"></label> Normal
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen12" value="Frekuensi">
                                <label for="gen12"></label> Frekuensi
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen13" value="Disuria">
                                <label for="gen13"></label> Disuria
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen14" value="Inkontinensia">
                                <label for="gen14"></label> Inkontinensia
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen15" value="Nokturia">
                                <label for="gen15"></label> Nokturia
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen16" value="Foley Cath">
                                <label for="gen16"></label> Foley Cath
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen17" value="Monopause">
                                <label for="gen17"></label> Monopause
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen18">
                                <label for="gen18"></label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <input id="ket_gen18" class="form-control" onchange="$('#gen18').val('HPTP : '+this.value)">
                        </div>
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen19" value="Normal">
                                <label for="gen19"></label> Normal
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-4">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen110" value="Hamil">
                                <label for="gen110"></label> Hamil
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen111">
                                <label for="gen111"></label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <input class="form-control" id="ket_gen111" onchange="$('#gen111').val(this.value)">
                        </div>
                        <div class="col-md-1">
                            <div class="form-check">
                                <input type="checkbox" name="gen1" id="gen112">
                                <label for="gen112"></label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <input id="ket_gen112" class="form-control" onchange="$('#gen112').val(this.value)">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">
                            <span>Catatan</span>
                            <textarea class="form-control" id="catatan_gen"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Perawat</label>
                                <canvas class="paint-canvas-ttd" id="gen2" width="220"
                                        onmouseover="paintTtd('gen2')"></canvas>
                                <input class="form-control nama_petugas" id="nama_terang_gen2" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control nip_petugas" id="sip_gen2" placeholder="NIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('gen2')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="gen3" width="220"
                                        onmouseover="paintTtd('gen3')"></canvas>
                                <input class="form-control nama_dokter_ri" id="nama_terang_gen3" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_gen3" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('gen3')"><i
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
                <button id="save_ina_genitourinaria" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('genitourinaria','asesmen_keperawatan_ri')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_genitourinaria" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>