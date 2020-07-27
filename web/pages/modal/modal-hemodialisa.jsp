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
                <div class="box-body btn-hide">
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
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Skrening Nyeri</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pkj21" name="pkj2" onclick="cekNyeri(this.value, 'pkj3')"/><label
                                        for="pkj21">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="pkj22" name="pkj2" onclick="cekNyeri(this.value, 'pkj3')"/><label for="pkj22">Ya</label>
                                </div>
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<div class="col-md-offset-3 col-md-7">--%>
                                <%--<img src="<%= request.getContextPath() %>/pages/images/nyeri-1.jpg"--%>
                                     <%--style="width: 100%; border: solid 1px black">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <input id="temp_scala" type="hidden">
                    <canvas id="choice_emoji" style="display: none"></canvas>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-0.png" class="nyeri"
                                style="width: 100%; cursor: no-drop;" id="0">
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">0</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Tidak Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-2.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="2" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">2</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-4.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="4" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">4</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Lebih Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-6.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="6" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">6</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Lebih Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-8.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="8" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">8</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Sangat Nyeri</p>
                            </div>
                            <div class="col-md-2">
                                <img src="<%= request.getContextPath() %>/pages/images/scala-10.png" class="nyeri"
                                     style="width: 100%; cursor: no-drop" id="10" >
                                <p class="text-center" style="font-size: 12px; margin-top: 10px">10</p>
                                <p class="text-center" style="font-size: 12px; margin-top: -10px">Nyeri Sangat Hebat</p>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 10px">Skala</label>
                            <div class="col-md-3">
                                <input class="form-control" style="margin-top: 10px" id="pkj3" readonly>
                            </div>
                            <label class="col-md-offset-1 col-md-1" style="margin-top: 10px">Lokasi</label>
                            <div class="col-md-3">
                                <input class="form-control" style="margin-top: 10px" id="pkj4">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Durasi</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" style="margin-top: 7px" id="pkj5">
                            </div>
                            <label class="col-md-offset-1 col-md-1" style="margin-top: 7px">Jenis</label>
                            <div class="col-md-1">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Akut" id="pkj61" name="pkj6"/><label
                                        for="pkj61">Akut</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Kronis" id="pkj62" name="pkj6"/><label
                                        for="pkj62">Kronis</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
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
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control tensi-pasien" id="pkj8">
                                    <div class="input-group-addon" style="font-size: 10px; width: 50px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-offset-1 col-md-1" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control suhu-pasien" id="pkj10">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        &#8451
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Reguler" id="pkj111"
                                           name="pkj11"/><label for="pkj111">Reguler</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ireguler" id="pkj112"
                                           name="pkj11"/><label for="pkj112">Irreguler</label>
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Freq</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="pkj012">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
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
                                    <input type="radio" value="Dyspnea" id="pkj133"
                                           name="pkj13"/><label for="pkj133">Dyspnea</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-4">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Oedema Paru/Ronchi" id="pkj134"
                                               name="pkj13"/><label for="pkj134">Oedema Paru/Ronchi</label>
                                    </div>
                                </div>
                                <label class="col-md-1" style="margin-top: 7px">Freq</label>
                                <div class="col-md-3">
                                    <div class="input-group" style="margin-top: 7px">
                                        <input type="number" class="form-control" id="pkj014">
                                        <div class="input-group-addon" style="font-size: 10px">
                                            x/menit
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <label class="col-md-3">Konjungilva</label>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input type="radio" onclick="showKetPkj15(this.value)" value="Tidak Anemia" id="pkj151"
                                       name="pkj15"/><label for="pkj151">Tidak Anemia</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input type="radio" onclick="showKetPkj15(this.value)" value="Anemis" id="pkj152"
                                       name="pkj15"/><label for="pkj152">Anemis</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input type="radio" onclick="showKetPkj15(this.value)" value="Lain-Lain" id="pkj153"
                                       name="pkj15"/><label for="pkj153">Lain-Lain</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-7">
                                <input class="form-control" id="ket_pkj15" style="display: none">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Ekstemitas</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Normal" id="pkj161"
                                           name="pkj16"/><label for="pkj161">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Dehidrasi" id="pkj162"
                                           name="pkj16"/><label for="pkj162">Dehidrasi</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Oedema" id="pkj163"
                                           name="pkj16"/><label for="pkj163">Oedema</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Oedema Anarsaka" id="pkj164"
                                           name="pkj16"/><label for="pkj164">Oedema Anarsaka</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Pucat dan Dingin" id="pkj165"
                                           name="pkj16"/><label for="pkj165">Pucat dan Dingin</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Berat Badan</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="pkj17" placeholder="Pre HD">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        Kg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <input type="number" class="form-control" id="pkj18" placeholder="Post HD">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        Kg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px" >
                                    <input type="number" class="form-control" id="pkj19" placeholder="Kering">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        Kg
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Abdomen</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Normal" id="pkj201"
                                           name="pkj20"/><label for="pkj201">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Ascites" id="pkj202"
                                           name="pkj20"/><label for="pkj202">Ascites</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Akses Vaskuler</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="AV Vistula" id="pkj211"
                                           name="pkj21"/><label for="pkj211">AV Vistula</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Femoral" id="pkj212"
                                           name="pkj21"/><label for="pkj212">Femoral</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Punctie Arteri</label>
                            <label class="col-md-2">HD Kateter</label>
                            <div class="col-md-4">
                                <input class="form-control" id="pkj23">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Lokasi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Kanan" id="pkj221"
                                           name="pkj22"/><label for="pkj221">Kanan</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
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
                        <label class="col-md-4" style="margin-top: 7px">Diagnosa Medis Sekunder > 1</label>
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
                                <input type="radio" value="Penopang tingkat|15" id="res32"
                                       name="res3"/><label for="res32">Penopang tingkat</label>
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
                        <label class="col-md-5">Pemeriksaan penunjang (Lab, RO, lain-lain)</label>
                        <div class="col-md-6">
                            <textarea class="form-control penunjang-medis" rows="3" style="margin-top: 7px" id="pem1"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">Gizi (dikaji tiap 3-6 bulan atau diulangi jika dianggap terjadi
                            perburukan asupan gizi)
                        </div>
                        <label class="col-md-5 jarak">Tanggal</label>
                        <div class="col-md-6">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control tgl" id="pem2">
                            </div>
                        </div>
                        <label class="col-md-5 jarak">Score</label>
                        <div class="col-md-6">
                            <input class="form-control" style="margin-top: 7px" id="pem3" type="number">
                        </div>
                        <label class="col-md-5 jarak">Rekomendasi</label>
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
                            <label class="col-md-5" style="margin-top: 7px; text-align: justify">Adakah keyakinan /
                                tradisi budaya yang berkaitan dengan pelayanan kesehatan yang akan diberikan</label>
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
                    <hr class="garis">
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
                    </div>
                    <hr class="garis">
                    <div class="row">
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
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5">Kondisi saat ini</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tenang" id="pem81"
                                           name="pem8"/><label for="pem81">Tenang</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Gelisah" id="pem82"
                                           name="pem8"/><label for="pem82">Gelisah</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Marah" id="pem83"
                                           name="pem8"/><label for="pem83">  Marah</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Mudah tersinggung" id="pem84"
                                           name="pem8"/><label for="pem84"> Mudah tersinggung</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-7">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Takut terhadap tindakan" id="pem85"
                                           name="pem8"/><label for="pem85"> Takut terhadap tindakan</label>
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
                                    <input type="checkbox" name="diag1" id="diag12"
                                           value="Gangguan keseimbangan eletronik">
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
                                    <input type="checkbox" name="diag1" id="diag15"
                                           value="Nutrisi kurang dari kebutuhan tubuh">
                                    <label for="diag15"></label> Nutrisi kurang dari kebutuhan tubuh
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag16"
                                           value="Ketidak petuhan terhadap dllt">
                                    <label for="diag16"></label> Ketidak petuhan terhadap dllt
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="diag1" id="diag17"
                                           value="Gangguan keseimbangan asam basa">
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
                                <input class="form-control" style="margin-top: 4px; margin-left: -11px"
                                       id="lain-lain_diag1" oninput="$('#diag19').val(this.value)">
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
                                    <input type="checkbox" name="inter1" id="inter1"
                                           value="Monitor barat badan, Intake out put">
                                    <label for="inter1"></label> Monitor barat badan, Intake out put
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter12"
                                           value="Atur polisi pasien agar ventilasi adekuat">
                                    <label for="inter12"></label> Atur polisi pasien agar ventilasi adekuat
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter13"
                                           value="Berikan terapi oksigen sesuai kebutuhan">
                                    <label for="inter13"></label> Berikan terapi oksigen sesuai kebutuhan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter14"
                                           value="Observasi pasien (monitor vital sign) dan mesin">
                                    <label for="inter14"></label> Observasi pasien (monitor vital sign) dan mesin
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter15"
                                           value="Hentikan HD sesuai indikasi">
                                    <label for="inter15"></label> Hentikan HD sesuai indikasi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter16"
                                           value="Monitor tanda-tanda dan gejala hipoglikemi, hipetensi">
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
                                    <input type="checkbox" name="inter1" id="inter18"
                                           value="Posisikan supinasi dengan elevasi 30 dan elevasi kaki">
                                    <label for="inter18"></label> Posisikan supinasi dengan elevasi 30 dan elevasi kaki
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter19"
                                           value="Lakukan teknik distraksi relaksasi">
                                    <label for="inter19"></label> Lakukan teknik distraksi relaksasi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter110"
                                           value="Monitor tanda dan gejala infeksi (lokal dan sistemik)">
                                    <label for="inter110"></label> Monitor tanda dan gejala infeksi (lokal dan sistemik)
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter111"
                                           value="Ganti balutan luka sesuai dengan prosedur">
                                    <label for="inter111"></label> Ganti balutan luka sesuai dengan prosedur
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter112"
                                           value="Monitor tanda dan gejala hipoglikemi">
                                    <label for="inter112"></label> Monitor tanda dan gejala hipoglikemi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="inter1" id="inter113"
                                           value="Kaji kemampuan pasien mendapatkan nutrisi yang dibutuhkan">
                                    <label for="inter113"></label> Kaji kemampuan pasien mendapatkan nutrisi yang
                                    dibutuhkan
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
                                    <input type="checkbox" name="inter1" id="inter122"
                                           value="Pemberian antipiretik/Analgetik">
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
                                <input class="form-control" style="margin-top: 4px; margin-left: -11px"
                                       id="lain-lain_inter1" oninput="$('#inter123').val(this.value)">
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

<div class="modal fade" id="modal-hd-asesmen_hd">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Assesment Awal Hemodialisa
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_hd_asesmen_hd">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_hd_asesmen_hd"></p>
                    </div>
                    <button onclick="showModalHD('asesmen_awal')" class="btn btn-success"><i class="fa fa-plus"></i>
                        Asesmen Awal
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_hd_asesmen_hd">
                        <tbody>
                        <tr id="row_hd_asesmen_awal">
                            <td>Assesment Awal Hemodialisa</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_asesmen_awal" class="hvr-grow" onclick="detailMonHD('asesmen_awal')"
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


<div class="modal fade" id="modal-hd-asesmen_awal">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Assesment Awal Hemodialisa
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none"
                         id="warning_hd_asesmen_awal">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_hd_asesmen_awal"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Anamnese</label>
                            <div class="col-md-8">
                                <textarea rows="5" class="form-control anamnese" id="asse1"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Pemeriksaan Fisik</label>
                            <%--<div class="col-md-8">--%>
                                <%--<textarea rows="3" id="asse2" class="form-control" style="margin-top: 7px"></textarea>--%>
                            <%--</div>--%>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tensi</label>
                            <div class="col-md-3">
                                <input class="form-control tensi-pasien" style="margin-top: 7px" id="asse3" placeholder="mmHg">
                            </div>
                            <label class="col-md-2" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-3">
                                <input class="form-control nadi-pasien" style="margin-top: 7px" id="asse4" placeholder="x/menit">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pernafasan</label>
                            <div class="col-md-3">
                                <input class="form-control rr-pasien" style="margin-top: 7px" id="asse5" placeholder="x/menit">
                            </div>
                            <label class="col-md-2" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-3">
                                <input class="form-control suhu-pasien" style="margin-top: 7px" id="asse6" placeholder="C">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Keadaan Umum</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse7">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kesadaran</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse8">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Status Lokalis</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse9">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kepala</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse10">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Leher</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse11">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Dada</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perut</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse13">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kaki</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse14">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pinggang</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse15">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Alat Kelamin</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse16">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Anggota Gerak</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse17">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pemeriksan Penunjang</label>
                            <div class="col-md-8">
                                <input class="form-control penunjang-medis" style="margin-top: 7px" id="asse18">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diagnosa Kerja</label>
                            <div class="col-md-8">
                                <input class="form-control diagnosa-pasien" style="margin-top: 7px " id="asse19">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Diagnosa Banding</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse20">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Pengobatan / Tindakan</label>
                            <div class="col-md-8">
                                <input class="form-control" style="margin-top: 7px" id="asse21">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">TTD Dokter</label>
                            <div class="col-md-4">
                                <canvas class="paint-canvas-ttd" id="asse22"
                                        onmouseover="paintTtd('asse22')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('asse22')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_asesmen_awal" class="btn btn-success pull-right"
                        onclick="saveMonHD('asesmen_awal','asesmen_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_asesmen_awal" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-tranfusi_hd">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Tindakan Medis Transfusi Darah
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_hd_tranfusi_hd">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_hd_tranfusi_hd"></p>
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
                            <li><a onclick="showModalHD('tranfusi_informasi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Informasi</a></li>
                            <li><a onclick="showModalHD('tranfusi_penyataan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pernyataan</a></li>
                            <li><a onclick="showModalHD('tranfusi_persetujuan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Persetujuan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_hd_tranfusi_hd">
                        <tbody>
                        <tr id="row_hd_tranfusi_informasi">
                            <td>Informasi</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_tranfusi_informasi" class="hvr-grow"
                                     onclick="detailMonHD('tranfusi_informasi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_tranfusi_penyataan">
                            <td>Pernyataan</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_tranfusi_penyataan" class="hvr-grow"
                                     onclick="detailMonHD('tranfusi_penyataan')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_tranfusi_persetujuan">
                            <td>Persetujuan</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_tranfusi_persetujuan" class="hvr-grow"
                                     onclick="detailMonHD('tranfusi_persetujuan')"
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

<div class="modal fade" id="modal-hd-tranfusi_informasi">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Informasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_tranfusi_informasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_tranfusi_informasi"></p>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <td width="30%"><b>Jenis Informasi</b></td>
                        <td><b>Isi Informasi</b></td>
                        <td width="15%" align="center"><b>Check (<i class="fa fa-check"></i>)</b></td>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Diagnosis (WD dan DD)</td>
                            <td>
                                <div class="row">
                                    <input style="margin-left: 15px" class="form-control" id="ti1">
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti2" id="ti21" value="Ya">
                                    <label for="ti21"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Dasar diagnosis</td>
                            <td>Wawancara riwayat penyakit, pemeriksaan fisik, pemeriksaan laboratorium</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti3" id="ti31" value="Ya">
                                    <label for="ti31"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tindakan Kedokteran</td>
                            <td>Tranfusi Darah</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti4" id="ti41" value="Ya">
                                    <label for="ti41"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Indikasi Tindakan</td>
                            <td>Untuk perbaiakan keadaan umum pada kondisi anemia</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti5" id="ti51" value="Ya">
                                    <label for="ti51"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tata Cara</td>
                            <td>Memasukkan darah/produk darah melalui jalur intravena sesua prosedur yang dijelaskan
                                secara lisan
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti6" id="ti61" value="Ya">
                                    <label for="ti61"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tujuan</td>
                            <td>Meningkatkan kadar produk sarah yang dibutuhkan sesuai target</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti7" id="ti71" value="Ya">
                                    <label for="ti71"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Resiko</td>
                            <td>Reaksi tranfusi ringan sampai syok (termasuk berbagai kemungkinan yang tidak diprediksi
                                sebelumnya)
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti8" id="ti81" value="Ya">
                                    <label for="ti81"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Komplikasi</td>
                            <td>Reaksi tranfusi ringan sampai syok (termasuk berbagai kemungkinan yang tidak diprediksi
                                sebelumnya)
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti9" id="ti91" value="Ya">
                                    <label for="ti91"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Prognosis</td>
                            <td>
                                <div class="row">
                                    <input style="margin-left: 15px" class="form-control" id="ti10">
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti11" id="ti111" value="Ya">
                                    <label for="ti111"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Alternatif</td>
                            <td>
                                Pemberian zat besi per oral dengan risiko keadaan umum dapat semakin lemah dan proses
                                penyembuhan menjadi lebih lama
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti12" id="ti121" value="Ya">
                                    <label for="ti121"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Perkiraan Biaya</td>
                            <td>
                                <div class="row">
                                    <div class="form-group">
                                        <label style="margin-left: 15px">Sesuai ketentuan sebagai:</label> <input
                                            style="margin-left: 15px" class="form-control" id="ti13">
                                    </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ti14" id="ti141" value="Ya">
                                    <label for="ti141"></label>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_tranfusi_informasi" class="btn btn-success pull-right"
                        onclick="saveMonHD('tranfusi_informasi','tranfusi_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_tranfusi_informasi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-tranfusi_penyataan">
    <div class="modal-dialog modal-md" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pernyataan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_tranfusi_penyataan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_tranfusi_penyataan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group" style="display: none">
                            <div class="col-md-1">
                                <input type="color" style="margin-left: -6px; margin-top: -8px"
                                       class="js-color-picker  color-picker pull-left">
                            </div>
                            <div class="col-md-9">
                                <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72"
                                       value="1">
                            </div>
                            <div class="col-md-2">
                                <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                            </div>
                        </div>
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="text-align: justify">
                                    <div class="row">
                                        <label style="margin-left: 15px">Dengan ini menyatakan bahwa saya</label>
                                        <input style="margin-left: 15px; width: 80%" class="form-control" id="tpe1">
                                        <label style="margin-left: 15px">telah menerangkan hal-hal di atas secara benar
                                            dan jelas dan memberikan kesempatan untuk bertanya dan/atau
                                            berdiskusi</label>
                                    </div>

                                </td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="tranfusi_penyataan1"
                                            onmouseover="paintTtd('tranfusi_penyataan1')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('tranfusi_penyataan1')"><i class="fa fa-trash"></i>
                                        Clear
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: justify">
                                    <div class="row">
                                        <label style="margin-left: 15px">Dengan ini menyatakan bahwa saya</label>
                                        <input style="margin-left: 15px; width: 80%" class="form-control nama-pasien" id="tpe2">
                                        <label style="margin-left: 15px">telah menerima informasi sebagimana di atas
                                            yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan
                                            untuk bertanya/berdiskusi, dan telah memahaminya</label>
                                    </div>
                                </td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="tranfusi_penyataan2"
                                            onmouseover="paintTtd('tranfusi_penyataan2')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('tranfusi_penyataan2')"><i class="fa fa-trash"></i>
                                        Clear
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_tranfusi_penyataan" class="btn btn-success pull-right"
                        onclick="saveMonHD('tranfusi_penyataan','tranfusi_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_tranfusi_penyataan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-tranfusi_persetujuan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Persetujuan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_tranfusi_persetujuan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_tranfusi_persetujuan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama</label>
                            <div class="col-md-6">
                                <input class="form-control" id="tperse1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Umur</label>
                            <div class="col-md-6">
                                <input class="form-control" id="tperse2" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="tperse3" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="tperse4" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Dengan ini menyatakan</label>
                            <div class="col-md-6">
                                <select class="form-control" id="tperse5" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Setuju">Setuju</option>
                                    <option value="Menolak">Menolak</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-6">
                                <input class="form-control" id="tperse6" style="margin-top: 7px" value="Transfusi darah"
                                       readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                            <div class="col-md-6">
                                <input class="form-control nama-pasien" id="tperse7" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Umur</label>
                            <div class="col-md-6">
                                <input class="form-control umur-pasien" id="tperse8" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <input class="form-control jenis-kelamin" id="tperse9" style="margin-top: 7px">
                                <%--<select class="form-control" id="tperse9" style="margin-top: 7px">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="Laki-Laki">Laki-Laki</option>--%>
                                    <%--<option value="Perempuan">Perempuan</option>--%>
                                <%--</select>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control alamat-pasien" id="tperse10" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">TTD Yang Menyatakan</label>
                            <div class="col-md-6">
                                <canvas class="paint-canvas-ttd" id="tperse11"
                                        onmouseover="paintTtd('tperse11')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('tperse11')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">TTD Saksi</label>
                            <div class="col-md-6">
                                <canvas class="paint-canvas-ttd" id="tperse12"
                                        onmouseover="paintTtd('tperse12')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('tperse12')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">TTD Pendamping</label>
                            <div class="col-md-6">
                                <canvas class="paint-canvas-ttd" id="tperse13"
                                        onmouseover="paintTtd('tperse13')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('tperse13')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_tranfusi_persetujuan" class="btn btn-success pull-right"
                        onclick="saveMonHD('tranfusi_persetujuan','tranfusi_hd')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_hd_tranfusi_persetujuan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-persetujuan_hd">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Persetujuan HD
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_hd_persetujuan_hd">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_hd_persetujuan_hd"></p>
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
                            <li><a onclick="showModalHD('persetujuan_hd_informasi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Informasi</a></li>
                            <li><a onclick="showModalHD('persetujuan_hd_penyataan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pernyataan</a></li>
                            <li><a onclick="showModalHD('persetujuan_hd_persetujuan')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Persetujuan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_hd_persetujuan_hd">
                        <tbody>
                        <tr id="row_hd_persetujuan_hd_informasi">
                            <td>Informasi</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_persetujuan_hd_informasi" class="hvr-grow"
                                     onclick="detailMonHD('persetujuan_hd_informasi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_persetujuan_hd_penyataan">
                            <td>Pernyataan</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_persetujuan_hd_penyataan" class="hvr-grow"
                                     onclick="detailMonHD('persetujuan_hd_penyataan')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_hd_persetujuan_hd_persetujuan">
                            <td>Persetujuan</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_persetujuan_hd_persetujuan" class="hvr-grow"
                                     onclick="detailMonHD('persetujuan_hd_persetujuan')"
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

<div class="modal fade" id="modal-hd-persetujuan_hd_informasi">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Informasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_persetujuan_hd_informasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_persetujuan_hd_informasi"></p>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <td width="30%"><b>Jenis Informasi</b></td>
                        <td><b>Isi Informasi</b></td>
                        <td width="15%" align="center"><b>Check (<i class="fa fa-check"></i>)</b></td>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Diagnosis (WD dan DD)</td>
                            <td>
                                CKD V on HD
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti1" id="tti11" value="Ya">
                                    <label for="tti11"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Dasar diagnosis</td>
                            <td>Riwayat penyakit, pemeriksaan fisik, pemeriksaan penunjang</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti2" id="tti21" value="Ya">
                                    <label for="tti21"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tindakan Kedokteran</td>
                            <td>Hemodialisa</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti3" id="tti31" value="Ya">
                                    <label for="tti31"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Indikasi Tindakan</td>
                            <td>CKD V, gangguan eletronik, produk sampah ginjal dalam kadar toksik, sindroma kelebihan
                                cairan. Kegawatan dibidang nefrologi (hipertensi, oedema paru, encepalopati reumia,
                                anuria/oliguria)
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti4" id="tti41" value="Ya">
                                    <label for="tti41"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tata Cara</td>
                            <td>Pembuluh darah arteri dan vena dihubungkan dengan mesin hemodialisa yang menganalisa
                                darah, lalu sampah dan cairan dipindahkan dari tubuh dan darah kembali ke tubuh
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti5" id="tti51" value="Ya">
                                    <label for="tti51"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tujuan</td>
                            <td>Mengatur keseimbangan eletronik, keseimbangan cairan dan membersihkan tubuh dari sampah
                                ginjal
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti6" id="tti61" value="Ya">
                                    <label for="tti61"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Resiko</td>
                            <td>Pendarahan, pembengkakan dan infeksi di tempat penusukan, mual-muntah, kontaminasi air
                                yang digunakan hemodialisa, kram otot, penurunan tekanan darah, gejala
                                ketidakseimbangan, irama jantung tidak teratur, reaksi cairan dialisat, kematian
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti7" id="tti71" value="Ya">
                                    <label for="tti71"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Prognosis</td>
                            <td>
                                Dubia ad malam
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti8" id="tti81" value="Ya">
                                    <label for="tti81"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Alternattif</td>
                            <td>
                                CAPD, Cangkok ginjal
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti9" id="tti91" value="Ya">
                                    <label for="tti91"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Perkiraan Biaya</td>
                            <td>
                                <div class="row">
                                    <div class="form-group">
                                        <input style="margin-left: 15px" class="form-control" id="tti10">
                                    </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="tti11" id="tti111" value="Ya">
                                    <label for="tti111"></label>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_persetujuan_hd_informasi" class="btn btn-success pull-right"
                        onclick="saveMonHD('persetujuan_hd_informasi','persetujuan_hd')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_hd_persetujuan_hd_informasi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-persetujuan_hd_penyataan">
    <div class="modal-dialog modal-md" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pernyataan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_persetujuan_hd_penyataan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_persetujuan_hd_penyataan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group" style="display: none">
                            <div class="col-md-1">
                                <input type="color" style="margin-left: -6px; margin-top: -8px"
                                       class="js-color-picker  color-picker pull-left">
                            </div>
                            <div class="col-md-9">
                                <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72"
                                       value="1">
                            </div>
                            <div class="col-md-2">
                                <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                            </div>
                        </div>
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="text-align: justify">
                                    <div class="row">
                                        <label style="margin-left: 15px">Dengan ini menyatakan bahwa saya</label>
                                        <input style="margin-left: 15px; width: 80%" class="form-control" id="ttpe1">
                                        <label style="margin-left: 15px">telah menerangkan hal-hal di atas secara benar
                                            dan jelas dan memberikan kesempatan untuk bertanya dan/atau
                                            berdiskusi</label>
                                    </div>

                                </td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="persetujuan_hd_penyataan1"
                                            onmouseover="paintTtd('persetujuan_hd_penyataan1')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('persetujuan_hd_penyataan1')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: justify">
                                    <div class="row">
                                        <label style="margin-left: 15px">Dengan ini menyatakan bahwa saya</label>
                                        <input style="margin-left: 15px; width: 80%" class="form-control nama-pasien" id="ttpe2">
                                        <label style="margin-left: 15px">telah menerima informasi sebagimana di atas
                                            yang saya beri tanda/ paraf di kolom kanannya serta telah diberi kesempatan
                                            untuk bertanya/berdiskusi, dan telah memahaminya</label>
                                    </div>
                                </td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="persetujuan_hd_penyataan2"
                                            onmouseover="paintTtd('persetujuan_hd_penyataan2')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('persetujuan_hd_penyataan2')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_persetujuan_hd_penyataan" class="btn btn-success pull-right"
                        onclick="saveMonHD('persetujuan_hd_penyataan','persetujuan_hd')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_hd_persetujuan_hd_penyataan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-persetujuan_hd_persetujuan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Persetujuan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_persetujuan_hd_persetujuan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_persetujuan_hd_persetujuan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama</label>
                            <div class="col-md-6">
                                <input class="form-control" id="pperse1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Umur</label>
                            <div class="col-md-6">
                                <input class="form-control" id="pperse2" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="pperse3" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="pperse4" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Dengan ini menyatakan</label>
                            <div class="col-md-6">
                                <select class="form-control" id="pperse5" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Setuju">Setuju</option>
                                    <option value="Menolak">Menolak</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-6">
                                <input class="form-control" id="pperse6" style="margin-top: 7px"
                                       value="Tindakan medis hemodialisa" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                            <div class="col-md-6">
                                <input class="form-control nama-pasien" id="pperse7" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Umur</label>
                            <div class="col-md-6">
                                <input class="form-control umur-pasien" id="pperse8" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <input class="form-control jenis-kelamin" id="pperse9" style="margin-top: 7px">
                                <%--<select class="form-control" id="pperse9" style="margin-top: 7px">--%>
                                    <%--<option value="">[Select One]</option>--%>
                                    <%--<option value="Laki-Laki">Laki-Laki</option>--%>
                                    <%--<option value="Perempuan">Perempuan</option>--%>
                                <%--</select>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control alamat-pasien" id="pperse10" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">TTD Yang Menyatakan</label>
                            <div class="col-md-6">
                                <canvas class="paint-canvas-ttd" id="pperse11"
                                        onmouseover="paintTtd('pperse11')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('pperse11')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">TTD Saksi</label>
                            <div class="col-md-6">
                                <canvas class="paint-canvas-ttd" id="pperse12"
                                        onmouseover="paintTtd('pperse12')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('pperse12')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">TTD Pendamping</label>
                            <div class="col-md-6">
                                <canvas class="paint-canvas-ttd" id="pperse13"
                                        onmouseover="paintTtd('pperse13')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('pperse13')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_persetujuan_hd_persetujuan" class="btn btn-success pull-right"
                        onclick="saveMonHD('persetujuan_hd_persetujuan','persetujuan_hd')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_hd_persetujuan_hd_persetujuan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-catatan_tranfusi_darah">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Catatan Pemantauan Transfusi Darah
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_hd_catatan_tranfusi_darah">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_hd_catatan_tranfusi_darah"></p>
                    </div>
                    <button class="btn btn-success" onclick="showModalHD('catatan_tranfusi')"><i class="fa fa-plus"></i>
                        Catatan Tranfusi Darah
                    </button>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" style="font-size: 12px">
                        <thead>
                        <tr>
                            <td rowspan="2" style="vertical-align: middle" align="center">Waktu Pemantauan</td>
                            <td colspan="4" style="vertical-align: middle" align="center">Tanda-Tanda Vita</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">Tidak Ada Reaksi</td>
                            <td style="vertical-align: middle" align="center">Reaksi Ringan</td>
                            <td colspan="6" style="vertical-align: middle" align="center">Reaksi Sedang Berat</td>
                            <td colspan="8" style="vertical-align: middle" align="center">Reaksi Yang Mengancam Jiwa</td>
                        </tr>
                        <tr>
                            <td>TD</td>
                            <td>N</td>
                            <td>S</td>
                            <td>RR</td>
                            <td>Gatal</td>
                            <td>UB</td>
                            <td>KK</td>
                            <td>DM</td>
                            <td>ME</td>
                            <td>GE</td>
                            <td>PD</td>

                            <td>DM</td>
                            <td>ME</td>
                            <td>GE</td>
                            <td>PD</td>
                            <td>NC</td>
                            <td>UR</td>
                            <td>PH</td>
                            <td>GK</td>
                        </tr>
                        </thead>
                        <tbody id="body_hd_catatan_tranfusi_darah">

                        </tbody>
                    </table>
                    <div class="box-body">
                        <hr class="garis">
                    <div class="row">
                        <div class="col-md-3">
                            <ul style="margin-left: 5px">
                                <li>UB = Urtikaria Berat</li>
                                <li>KK = Kulit Kemerahan</li>
                                <li>DM = Demam > 38 C</li>
                                <li>ME = Menggigil</li>
                                <li>GE = Gelisah</li>
                                <li><i class="fa fa-check" style="color: #449d44"></i> = Ya</li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <ul>
                                <li>PD = Peningkatan detak jantung</li>
                                <li>NC = Nafas Cepat</li>
                                <li>UR = Urin yang berwarna hitam/gelap</li>
                                <li>PH = Pendarahan yang tidak jelas penyebabnya</li>
                                <li>GK = Gangguan kesadaran</li>
                                <li><i class="fa fa-times" style="color: #dd4b39"></i> = Tidak</li>
                            </ul>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-catatan_tranfusi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Catatan Tranfusi Darah
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_catatan_tranfusi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_catatan_tranfusi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Waktu Pemantauan</label>
                            <div class="col-md-6">
                                <select class="form-control" id="ct1">
                                    <option value="">[Select One]</option>
                                    <option value="5 menit">5 menit</option>
                                    <option value="10 menit">10 menit</option>
                                    <option value="15 menit">15 menit</option>
                                    <option value="30 menit">30 menit</option>
                                    <option value="1 jam">1 jam</option>
                                    <option value="1 jam 30 menit">1 jam 30 menit</option>
                                    <option value="2 jam">2 jam</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ct2" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nadi</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ct3" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Suhu</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ct4" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">RR</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ct5" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tidak ada reaksi</label>
                            <div class="col-md-6">
                                <input class="form-control" id="ct6" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">Reaksi Ringan</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Gatal</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct71"
                                           name="ct7"/><label for="ct71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct72"
                                           name="ct7"/><label for="ct72">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">Reaksi Sedang Berat</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Urtikaria Berat</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct81"
                                           name="ct8"/><label for="ct81">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct82"
                                           name="ct8"/><label for="ct82">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Kulit Kemerahan</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct91"
                                           name="ct9"/><label for="ct91">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct92"
                                           name="ct9"/><label for="ct92">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Demam > 38 C</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct101"
                                           name="ct10"/><label for="ct101">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct102"
                                           name="ct10"/><label for="ct102">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Menggigil</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct111"
                                           name="ct11"/><label for="ct111">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct112"
                                           name="ct11"/><label for="ct112">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Gelisah</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct121"
                                           name="ct12"/><label for="ct121">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct122"
                                           name="ct12"/><label for="ct122">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Peningkatan detak jantung</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct131"
                                           name="ct13"/><label for="ct131">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct132"
                                           name="ct13"/><label for="ct132">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">Reaksi yang mengancam jiwa</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Demam > 38 C</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct141"
                                           name="ct14"/><label for="ct141">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct142"
                                           name="ct14"/><label for="ct142">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Menggigil</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct151"
                                           name="ct15"/><label for="ct151">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct152"
                                           name="ct15"/><label for="ct152">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Gelisah</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct161"
                                           name="ct16"/><label for="ct161">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct162"
                                           name="ct16"/><label for="ct162">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Peningkatan detak jantung</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct171"
                                           name="ct17"/><label for="ct171">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct172"
                                           name="ct17"/><label for="ct172">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nafas Cepat</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct181"
                                           name="ct18"/><label for="ct181">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct182"
                                           name="ct18"/><label for="ct182">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Urin yang berwarna hitam/gelap</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct191"
                                           name="ct19"/><label for="ct191">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct192"
                                           name="ct19"/><label for="ct192">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Pendarahan yang tidak jelas penyebabnya</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct201"
                                           name="ct20"/><label for="ct201">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct202"
                                           name="ct20"/><label for="ct202">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Gangguan kesadaran</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="ct211"
                                           name="ct21"/><label for="ct211">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="ct212"
                                           name="ct21"/><label for="ct212">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_catatan_tranfusi" class="btn btn-success pull-right"
                        onclick="saveMonTransfusiDarah('catatan_tranfusi_darah')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_hd_catatan_tranfusi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-travelling">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Travelling Dialysis
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_hd_travelling">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_hd_travelling"></p>
                    </div>
                    <button class="btn btn-success" onclick="showModalHD('travelling_dialysis')"><i class="fa fa-plus"></i> Travelling Dialysis</button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_hd_travelling">
                        <tbody>
                        <tr id="row_hd_travelling_dialysis">
                            <td>Travelling Dialysis</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_travelling_dialysis" class="hvr-grow" onclick="detailMonHD('travelling_dialysis')"
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

<div class="modal fade" id="modal-hd-travelling_dialysis">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Travelling Dialysis
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_travelling_dialysis">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_travelling_dialysis"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama Pasien</label>
                            <div class="col-md-6">
                                <input class="form-control nama-pasien" id="td1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control alamat-pasien" id="td2" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Diagnosa</label>
                            <div class="col-md-6">
                                <input class="form-control diagnosa-pasien" id="td3" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Mulai Hemodialisa</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td4" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Terapi</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="td5" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tekanan Darah</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td6" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Komposisi Dialisat</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td7" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tipe Dializer</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td8" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Komplikasi Saat HD</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td9" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Pungsi</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td10" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Dosis Heparin</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td11" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Interval Hemodialisa</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td12" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">TMP</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td13" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">HbsAg</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td14" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat Transfusi</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td15" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Diet</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td16" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alergi</label>
                            <div class="col-md-6">
                                <input class="form-control alergi-pasien" id="td17" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Berat Badan Kering</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td18" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Golongan Darah</label>
                            <div class="col-md-6">
                                <input class="form-control" id="td19" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanda Tangan</label>
                            <div class="col-md-6">
                                <canvas style="margin-left: -1px;" onmouseover="paintTtd('hd_ttd_dokter')" class="paint-canvas-ttd" id="hd_ttd_dokter"></canvas>
                                <button style="margin-top: -5px; margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('hd_ttd_dokter')"><i class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_travelling_dialysis" class="btn btn-success pull-right" onclick="saveMonHD('travelling_dialysis','travelling')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_hd_travelling_dialysis" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-hd-perencanaan_hemodialisa">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Perencanaan Hemodialisa
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_hd_perencanaan_hemodialisa">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_hd_perencanaan_hemodialisa"></p>
                    </div>
                    <button class="btn btn-success" onclick="showModalHD('perencanaan_hemodialisa_pasien')"><i class="fa fa-plus"></i> Perencanaan Hemodialisa</button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_hd_perencanaan_hemodialisa">
                        <tbody>
                        <tr id="row_hd_perencanaan_hemodialisa_pasien">
                            <td>Perencanaan Hemodialisa</td>
                            <td width="20%" align="center">
                                <img id="btn_hd_perencanaan_hemodialisa_pasien" class="hvr-grow" onclick="detailMonHD('perencanaan_hemodialisa_pasien')"
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

<div class="modal fade" id="modal-hd-perencanaan_hemodialisa_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Perencanaan Hemodialisa
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_hd_perencanaan_hemodialisa_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_hd_perencanaan_hemodialisa_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama Pasien</label>
                            <div class="col-md-7">
                                <input class="form-control nama-pasien" id="ph1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-7">
                                <input class="form-control jenis-kelamin" id="ph13" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Umur</label>
                            <div class="col-md-7">
                                <input class="form-control umur-pasien" id="ph2" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-7">
                                <textarea rows="3" class="form-control alamat-pasien" id="ph3" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">No Asuransi/ BPJS/ Swasta</label>
                            <div class="col-md-7">
                                <input class="form-control no-bpjs" id="ph4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Perencanaan Hemodialisa</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Sebanyak</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="ph5" style="margin-top: 7px">
                            </div>
                            <label class="col-md-3" style="margin-top: 12px; margin-left: -20px">kali/minggu</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Lama Hemodialisa</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="ph6" style="margin-top: 7px">
                            </div>
                            <label class="col-md-1" style="margin-top: 12px; margin-left: -20px">Jam</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="ph7" style="margin-top: 7px">
                            </div>
                            <label class="col-md-1" style="margin-top: 12px; margin-left: -20px">Menit</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">UF</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="ph8" style="margin-top: 7px">
                            </div>
                            <label class="col-md-1" style="margin-top: 12px; margin-left: -20px">Liter</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Heparin</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="ph9" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">QB</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="ph10" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">BD</label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="ph11" style="margin-top: 7px">
                            </div>
                            <label class="col-md-1" style="margin-top: 12px; margin-left: -20px">Unit</label>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Catatan</label>
                            <div class="col-md-7">
                                <textarea rows="3" class="form-control" id="ph12" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">TTD Dokter</label>
                            <div class="col-md-6">
                                <canvas class="paint-canvas-ttd" id="ph14"
                                        onmouseover="paintTtd('ph14')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ph14')"><i class="fa fa-trash"></i>
                                    Clear
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_hd_perencanaan_hemodialisa_pasien" class="btn btn-success pull-right" onclick="saveMonHD('perencanaan_hemodialisa_pasien','perencanaan_hemodialisa')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_hd_perencanaan_hemodialisa_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>



