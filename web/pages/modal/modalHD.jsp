<div class="modal fade" id="modal-hd-monitoring_hd">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Monitoring Harian Hemodalisa
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_hd_monitoring_hd">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_hd_monitoring_hd"></p>
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
                            <li><a onclick="showModalHD('pengkajian')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pengkajian Keperawatan</a></li>
                            <li><a onclick="showModalHD('resiko_jatuh')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Resiko Jatuh</a></li>
                            <li><a onclick="showModalHD('pemeriksaan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pemeriksaan</a></li>
                            <li><a onclick="showModalHD('diagnosa')" style="cursor: pointer"><i class="fa fa-plus"></i>
                                Diagnosa Keperawatan</a></li>
                            <li><a onclick="showModalHD('intervensi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Intervensi Keperawatan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_hd_monitoring_hd">
                        <tbody>
                        <tr id="row_hd_pengkajian">
                            <td>Pengkajian Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_pengkajian" class="hvr-grow" onclick="detailMonHD('pengkajian')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_resiko_jatuh">
                            <td>Resiko Jatuh</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_resiko_jatuh" class="hvr-grow" onclick="detailMonHD('resiko_jatuh')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_pemeriksaan">
                            <td>Pemeriksaan</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_pemeriksaan" class="hvr-grow" onclick="detailMonHD('pemeriksaan')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_diagnosa">
                            <td>Diagnosa Keperawatan</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_diagnosa" class="hvr-grow" onclick="detailMonHD('diagnosa')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_intervensi">
                            <td>Intervensi Keperawatan (Rekapitulasi pre-intra dalam post-HD)</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_intervensi" class="hvr-grow" onclick="detailMonHD('intervensi')"
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

<div class="modal fade" id="modal-hd-pengkajian">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_hd_pengkajian">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_hd_pengkajian"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Keluhan Utama</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj1(this.value)" value="Sesak Nafas" id="pkj11"
                                           name="pkj1"/><label for="pkj11">Sesak Nafas</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj1(this.value)" value="Mual-muntah" id="pkj12"
                                           name="pkj1"/><label for="pkj12">Mual-muntah</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj1(this.value)" value="Gatal" id="pkj13"
                                           name="pkj1"/><label for="pkj13">Gatal</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj1(this.value)" value="Lain-Lain" id="pkj14"
                                           name="pkj1"/><label for="pkj14">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-7">
                                <input class="form-control" id="ket_pkj1" style="margin-top: 7px; display: none">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Skrening Nyeri</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pkj21" name="pkj2"/><label
                                        for="pkj21">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="pkj22" name="pkj2"/><label for="pkj22">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-7">
                                <img src="<%= request.getContextPath() %>/pages/images/nyeri-1.jpg"
                                     style="width: 100%; border: solid 1px black">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Skala</label>
                            <div class="col-md-3">
                                <input class="form-control" style="margin-top: 10px" id="pkj3">
                            </div>
                            <label class="col-md-1" style="margin-top: 10px">Lokasi</label>
                            <div class="col-md-3">
                                <input class="form-control" style="margin-top: 10px" id="pkj4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Durasi</label>
                            <div class="col-md-3">
                                <input class="form-control" style="margin-top: 7px" id="pkj5">
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jenis</label>
                            <div class="col-md-1">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Akut" id="pkj61" name="pkj6"/><label
                                        for="pkj61">Akut</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kronis" id="pkj62" name="pkj6"/><label
                                        for="pkj62">Kronis</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Keadaan Umum</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj7(this.value)" value="Baik" id="pkj71"
                                           name="pkj7"/><label for="pkj71">Baik</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj7(this.value)" value="Sedang" id="pkj72"
                                           name="pkj7"/><label for="pkj72">Sedang</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj7(this.value)" value="Buruk" id="pkj73"
                                           name="pkj7"/><label for="pkj73">Buruk</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" onclick="showKetPkj7(this.value)" value="Lain-Lain" id="pkj74"
                                           name="pkj7"/><label for="pkj74">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-7">
                                <input class="form-control" id="ket_pkj7" style="margin-top: 7px; display: none">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px" >
                                    <input class="form-control" id="pkj8">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">MAP</label>
                            <div class="col-md-3">
                                <input class="form-control" id="pkj9" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px" >
                                    <input class="form-control" id="pkj10">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Reguler" id="pkj111"
                                           name="pkj11"/><label for="pkj111">Reguler</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ireguler" id="pkj112"
                                           name="pkj11"/><label for="pkj112">Ireguler</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Freq Nadi</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px" >
                                    <input class="form-control" id="pkj012">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Respirasi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="pkj131"
                                           name="pkj13"/><label for="pkj131">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kusmaul" id="pkj132"
                                           name="pkj13"/><label for="pkj132">Kusmaul</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dispnes" id="pkj133"
                                           name="pkj13"/><label for="pkj133">Dispnes</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-8">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Edema Parau/Ronohi" id="pkj134"
                                               name="pkj13"/><label for="pkj134">Edema Parau/Ronohi</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Freq Respirasi</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px" >
                                    <input class="form-control" id="pkj014">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-3">Konjungilva</label>
                        <div class="col-md-3">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" onclick="showKetPkj15(this.value)" value="Tidak Perna" id="pkj151"
                                       name="pkj15"/><label for="pkj151">Tidak Perna</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" onclick="showKetPkj15(this.value)" value="Anemis" id="pkj152"
                                       name="pkj15"/><label for="pkj152">Anemis</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" onclick="showKetPkj15(this.value)" value="Lain-Lain" id="pkj153"
                                       name="pkj15"/><label for="pkj153">Lain-Lain</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-7">
                                <input class="form-control" id="ket_pkj15" style="display: none">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3">Ekrimitas</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="pkj161"
                                           name="pkj16"/><label for="pkj161">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dehidrasi" id="pkj162"
                                           name="pkj16"/><label for="pkj162">Dehidrasi</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Oedema" id="pkj163"
                                           name="pkj16"/><label for="pkj163">Oedema</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Oedema Anarsaka" id="pkj164"
                                           name="pkj16"/><label for="pkj164">Oedema Anarsaka</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Pucat dan Dingin" id="pkj165"
                                           name="pkj16"/><label for="pkj165">Pucat dan Dingin</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">BB Kering</label>
                            <div class="col-md-7">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="pkj17">
                                    <div class="input-group-addon">
                                        Kg
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">BB Post HD</label>
                            <div class="col-md-7">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="pkj18">
                                    <div class="input-group-addon">
                                        Kg
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">BB Pre HD</label>
                            <div class="col-md-7">
                                <div class="input-group" style="margin-top: 7px">
                                    <input class="form-control" id="pkj19">
                                    <div class="input-group-addon">
                                        Kg
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3">Abdomen</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Normal" id="pkj201"
                                           name="pkj20"/><label for="pkj201">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Asites" id="pkj202"
                                           name="pkj20"/><label for="pkj202">Asites</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3">Askes Vaskuler</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="AV Vistula" id="pkj211"
                                           name="pkj21"/><label for="pkj211">AV Vistula</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Femoral" id="pkj212"
                                           name="pkj21"/><label for="pkj212">Femoral</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3">Lokasi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kanan" id="pkj221"
                                           name="pkj22"/><label for="pkj221">Kanan</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kiri" id="pkj222"
                                           name="pkj22"/><label for="pkj222">Kiri</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_pengkajian" class="btn btn-success pull-right"
                        onclick="saveMonHD('pengkajian','monitoring_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_pengkajian" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-resiko_jatuh">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Resiko Jatuh
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none"
                         id="warning_hd_resiko_jatuh">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_hd_resiko_jatuh"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-4" style="margin-top: 7px">Riwayat jatuh</label>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak|0" id="res11"
                                       name="res1"/><label for="res11">Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Ya|25" id="res12"
                                       name="res1"/><label for="res12">Ya</label>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <label class="col-md-4" style="margin-top: 7px">Dalgnosa Medis Sekunder > 1</label>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak|0" id="res21"
                                       name="res2"/><label for="res21">Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Ya|15" id="res22"
                                       name="res2"/><label for="res22">Ya</label>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <label class="col-md-4" style="margin-top: 7px">Alat bantu jalan</label>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Bed rest|0" id="res31"
                                       name="res3"/><label for="res31">Bedrest</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Furniture|30" id="res33"
                                       name="res3"/><label for="res33">Furniture</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Penompang tingkat|15" id="res32"
                                       name="res3"/><label for="res32">Penompang tingkat</label>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <label class="col-md-4" style="margin-top: 7px">Memakai terapi heparin look</label>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak|0" id="res41"
                                       name="res4"/><label for="res41">Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Ya|25" id="res42"
                                       name="res4"/><label for="res42">Ya</label>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <label class="col-md-4" style="margin-top: 7px">Cara berjalan/berpindah</label>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Lemah|15" id="res51"
                                       name="res5"/><label for="res51">Lemah</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Terganggu|30" id="res53"
                                       name="res5"/><label for="res53">Terganggu</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Normal/Bedrest/mobilisasi|0" id="res52"
                                       name="res5"/><label for="res52">Normal/Bedrest/mobilisasi</label>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <label class="col-md-4" style="margin-top: 7px">Status mental</label>
                        <div class="col-md-4">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Oriental sesuai kemampuan|0" id="res61"
                                       name="res6"/><label for="res61">Oriental sesuai kemampuan</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Lupa keterbatasan|15" id="res62"
                                       name="res6"/><label for="res62">Lupa keterbatasan</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_resiko_jatuh" class="btn btn-success pull-right"
                        onclick="saveMonHD('resiko_jatuh','monitoring_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_resiko_jatuh" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-pemeriksaan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_hd_pemeriksaan">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_hd_pemeriksaan"></p>
                    </div>
                    <div class="row">
                        <label class="col-md-5">Pemeriksaan penunjang (Lab, RX, lain-lain)</label>
                        <div class="col-md-6">
                            <input class="form-control" style="margin-top: 7px" id="pem1">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">Gizi (dikaji tiap 3-6 bulan atau diulangi jika dianggap terjadi perburukan asupan gizi) </div>
                        <label class="col-md-5">Tanggal</label>
                        <div class="col-md-6">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control" id="pem2">
                            </div>
                        </div>
                        <label class="col-md-5">Score</label>
                        <div class="col-md-6">
                            <input class="form-control" style="margin-top: 7px" id="pem3">
                        </div>
                        <label class="col-md-5">Rekomendasi</label>
                        <div class="col-md-6">
                            <textarea rows="4" class="form-control" style="margin-top: 7px" id="pem4"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                        <label class="col-md-12">Riwayat Psikososial</label>
                        </div>
                        <div class="form-group">
                        <label class="col-md-5" style="margin-top: 7px; text-align: justify">Adakah keyakinan / tradisi budaya yang berkaitan dengan pelayanan kesehatan yang akan diberikan</label>
                        <div class="col-md-3">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Tidak" id="pem51"
                                       name="pem5"/><label for="pem51">Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02" style="margin-top: 7px">
                                <input type="radio" value="Ya" id="pem52"
                                       name="pem5"/><label for="pem52">Ya</label>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Kendala Komunikasi</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak ada" id="pem61"
                                           name="pem6"/><label for="pem61">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada" id="pem62"
                                           name="pem6"/><label for="pem62">Ada</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Yang merawat dirumah</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak ada" id="pem71"
                                           name="pem7"/><label for="pem71">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada" id="pem72"
                                           name="pem7"/><label for="pem72">Ada</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">Kondisi saat ini</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tenang" id="pem81"
                                           name="pem8"/><label for="pem81">Tenang</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Gelisah" id="pem82"
                                           name="pem8"/><label for="pem82">Gelisah</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Marah" id="pem83"
                                           name="pem8"/><label for="pem83"></label> Marah
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Mudah tersinggung" id="pem84"
                                           name="pem8"/><label for="pem84"></label> Mudah tersinggung
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-7">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Takut terhadap tindakan" id="pem85"
                                           name="pem8"/><label for="pem85"></label> Takut terhadap tindakan
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_pemeriksaan" class="btn btn-success pull-right"
                        onclick="saveMonHD('pemeriksaan','monitoring_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_pemeriksaan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-diagnosa">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Diagnosa Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_hd_diagnosa">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_hd_diagnosa"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag11" value="Kelebihan volume cairan">
                                    <label for="diag11"></label> Kelebihan volume cairan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag12" value="Gangguan keseimbangan eletronik">
                                    <label for="diag12"></label> Gangguan keseimbangan eletronik
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag13" value="Penurunan curah jantung">
                                    <label for="diag13"></label> Penurunan curah jantung
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag14" value="Gangguan perfusi jaringan">
                                    <label for="diag14"></label> Gangguan perfusi jaringan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag15" value="Nutrisi kurang dari kebutuhan tubuh">
                                    <label for="diag15"></label> Nutrisi kurang dari kebutuhan tubuh
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag16" value="Ketidak petuhan terhadap dllt">
                                    <label for="diag16"></label> Ketidak petuhan terhadap dllt
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag17" value="Gangguan keseimbangan asam basa">
                                    <label for="diag17"></label> Gangguan keseimbangan asam basa
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag18" value="Gangguan rasa nyaman nyeri">
                                    <label for="diag18"></label> Gangguan rasa nyaman nyeri
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-1">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag19">
                                    <label for="diag19"></label>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <input class="form-control" style="margin-top: 4px; margin-left: -11px" id="lain-lain_diag1" oninput="$('#diag19').val(this.value)">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_diagnosa" class="btn btn-success pull-right"
                        onclick="saveMonHD('diagnosa','monitoring_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_diagnosa" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-intervensi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Intervensi Keperawatan (Rekapitulasi pre-intra
                    dalam post-HD)
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_hd_intervensi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_hd_intervensi"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter1" value="Monitor barat badan, Intake out put">
                                    <label for="inter1"></label> Monitor barat badan, Intake out put
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter12" value="Atur polisi pasien agar ventilasi adekuat">
                                    <label for="inter12"></label> Atur polisi pasien agar ventilasi adekuat
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter13" value="Berikan terapi oksigen sesuai kebutuhan">
                                    <label for="inter13"></label> Berikan terapi oksigen sesuai kebutuhan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter14" value="Observasi pasien (monitor vital sign) dan mesin">
                                    <label for="inter14"></label> Observasi pasien (monitor vital sign) dan mesin
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter15" value="Hentikan HD sesuai indikasi">
                                    <label for="inter15"></label> Hentikan HD sesuai indikasi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter16" value="Monitor tanda-tanda dan gejala hipoglikemi, hipetensi">
                                    <label for="inter16"></label> Monitor tanda-tanda dan gejala hipoglikemi, hipetensi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter17" value="PENKES">
                                    <label for="inter17"></label> PENKES
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter18" value="Posisikan supinasi dengan elevasi 30 dan elevasi kaki">
                                    <label for="inter18"></label> Posisikan supinasi dengan elevasi 30 dan elevasi kaki
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter19" value="Lakukan teknik distraksi relaksasi">
                                    <label for="inter19"></label> Lakukan teknik distraksi relaksasi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter110" value="Monitor tanda dan gejala infeksi (lokal dan sistemik)">
                                    <label for="inter110"></label> Monitor tanda dan gejala infeksi (lokal dan sistemik)
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter111" value="Ganti balutan luka sesuai dengan prosedur">
                                    <label for="inter111"></label> Ganti balutan luka sesuai dengan prosedur
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter112" value="Monitor tanda dan gejala hipoglikemi">
                                    <label for="inter112"></label> Monitor tanda dan gejala hipoglikemi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter113" value="Kaji kemampuan pasien mendapatkan nutrisi yang dibutuhkan">
                                    <label for="inter113"></label> Kaji kemampuan pasien mendapatkan nutrisi yang dibutuhkan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter114" value="Program HD">
                                    <label for="inter114"></label> Program HD
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter116" value="Pemeberian Oa Glukonas">
                                    <label for="inter116"></label> Pemeberian Oa Glukonas
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter117" value="Transfusi Darah">
                                    <label for="inter117"></label> Transfusi Darah
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter118" value="Kaloborasi Dllt">
                                    <label for="inter118"></label> Kaloborasi Dllt
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter119" value="Pemberian Preparat Besi">
                                    <label for="inter119"></label> Pemberian Preparat Besi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter120" value="Pemberian Erytropostin">
                                    <label for="inter120"></label> Pemberian Erytropostin
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter121" value="Obat-obat Emergensi">
                                    <label for="inter121"></label> Obat-obat Emergensi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter122" value="Pemberian antipiretik/Analgetik">
                                    <label for="inter122"></label> Pemberian antipiretik/Analgetik
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-1">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter123">
                                    <label for="inter123"></label>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <input class="form-control" style="margin-top: 4px; margin-left: -11px" id="lain-lain_inter1" oninput="$('#inter123').val(this.value)">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_intervensi" class="btn btn-success pull-right"
                        onclick="saveMonHD('intervensi','monitoring_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_intervensi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


