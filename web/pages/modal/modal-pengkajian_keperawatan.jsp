<div class="modal fade" id="modal-puk-pengkajian">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Pengkajian Ulang Keperawatan Dan Tindakan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_puk_pengkajian">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_puk_pengkajian"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="modal_warning">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_warning"></p>
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
                            <li><a onclick="showModalPengkajianKep('resiko_jatuh')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Resiko Jatuh</a></li>
                            <li><a onclick="showModalPengkajianKep('pencegahan_umum')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pencegahan Umum</a></li>
                            <li><a onclick="showModalPengkajianKep('resiko_tinggi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pencengahan Resiko Tinggi</a></li>
                            <li><a onclick="showModalPengkajianKep('nyeri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pengkajian Ulang Nyeri</a></li>
                            <li><a onclick="showModalPengkajianKep('resiko_kulit')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Resiko Kulit</a></li>
                            <li><a onclick="showModalPengkajianKep('restrain')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Restrain</a></li>
                            <li><a onclick="showModalPengkajianKep('pemulangan_pasien')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Rencana Pemulangan Pasien  </a></li>
                            <li><a onclick="showModalPengkajianKep('pasien_akhir')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Pasien Pada Akhir Kehidupan  </a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_puk_pengkajian">
                        <tbody>
                        <tr id="row_puk_resiko_jatuh">
                            <td>Pengkajian Ulang Resiko Jatuh Scala Morse/Scala Humpty Dumpty/Scala Ontario</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_resiko_jatuh" class="hvr-grow"
                                     onclick="detailPengkajianKep('resiko_jatuh')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_puk_pencegahan_umum">
                            <td>Tindakan Pencengahan Umum</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_pencegahan_umum" class="hvr-grow"
                                     onclick="detailPengkajianKep('pencegahan_umum')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_puk_resiko_tinggi">
                            <td>Tindakan Pencengahan Resiko Tinggi</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_resiko_tinggi" class="hvr-grow"
                                     onclick="detailPengkajianKep('resiko_tinggi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_puk_nyeri">
                            <td>Pengkajian Ulang Nyeri</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_nyeri" class="hvr-grow"
                                     onclick="detailPengkajianKep('nyeri')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_puk_resiko_kulit">
                            <td>Pengkajian Ulang Resiko Kulit (Norton Scale)</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_resiko_kulit" class="hvr-grow"
                                     onclick="detailPengkajianKep('resiko_kulit')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_puk_restrain">
                            <td>Pengkajian Ulang Restrain</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_restrain" class="hvr-grow"
                                     onclick="detailPengkajianKep('restrain')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_puk_pemulangan_pasien">
                            <td>Pengkajian Ulang Rencana Pemulangan Pasien</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_pemulangan_pasien" class="hvr-grow"
                                     onclick="detailPengkajianKep('pemulangan_pasien')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_puk_pasien_akhir">
                            <td>Pengkajian Ulang Pasien Pada Akhir Kehidupan (terminal)</td>
                            <td width="20%" align="center">
                                <img id="btn_puk_pasien_akhir" class="hvr-grow"
                                     onclick="detailPengkajianKep('pasien_akhir')"
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

<div class="modal fade" id="modal-puk-resiko_jatuh">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Ulang Resiko Jatuh
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_resiko_jatuh">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_resiko_jatuh"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                               <select class="form-control" id="rj1">
                                   <option value="">[Select One]</option>
                                   <option value="pagi">Pagi</option>
                                   <option value="siang">Siang</option>
                                   <option value="malam">Malam</option>
                               </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                     <div class="input-group-addon">
                                         <i class="fa fa-calendar"></i>
                                     </div>
                                    <input class="form-control tgl" id="rj2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="rj3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <input id="jenis_resiko" type="hidden">
                    <div id="set_resiko_jatuh"></div>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-md-3" style="margin-top: 7px">Score</label>--%>
                            <%--<div class="col-md-8">--%>
                                <%--<input class="form-control" id="rj4" style="margin-top: 7px">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-md-3" style="margin-top: 7px">Tingkat Resiko</label>--%>
                            <%--<div class="col-md-8">--%>
                                <%--<input class="form-control" id="rj5" style="margin-top: 7px">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_puk_resiko_jatuh" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('resiko_jatuh','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_resiko_jatuh" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-puk-pencegahan_umum">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tindakan Pencengahan Umum
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_pencegahan_umum">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_pencegahan_umum"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                                <select class="form-control" id="pu1">
                                    <option value="">[Select One]</option>
                                    <option value="pagi">Pagi</option>
                                    <option value="siang">Siang</option>
                                    <option value="malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="pu2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="pu3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">1. Melakukan orientasi ruangan dan tindakan pencengahan umum</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: 15px">
                                    <input type="radio" value="Tidak" id="pu41" name="pu4" /><label for="pu41">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="pu42" name="pu4" /><label for="pu42">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">2. Berikan edukasi pencegahan jatuh pada pasien dan keluarga</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: 15px">
                                    <input type="radio" value="Tidak" id="pu51" name="pu5" /><label for="pu51">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="pu52" name="pu5" /><label for="pu52">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_puk_pencegahan_umum" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('pencegahan_umum','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_pencegahan_umum" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-puk-resiko_tinggi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tindakan Pencengahan Resiko Tinggi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_resiko_tinggi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_resiko_tinggi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                                <select class="form-control" id="rt1">
                                    <option value="">[Select One]</option>
                                    <option value="pagi">Pagi</option>
                                    <option value="siang">Siang</option>
                                    <option value="malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="rt2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="rt3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">1. Beri penanda berupa stiker warna kuning di gelang pasien</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: 15px">
                                    <input type="radio" value="Tidak" id="rt41" name="rt4" /><label for="rt41">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="rt42" name="rt4" /><label for="rt42">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">2. Beri pertanda pada bed berbentuk segitiga warna kuning</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: 15px">
                                    <input type="radio" value="Tidak" id="rt51" name="rt5" /><label for="rt51">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="rt52" name="rt5" /><label for="rt52">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12">3. Observasi secara teratur setiap pergantian shift</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-2">
                                <div class="custom02" style="margin-left: 15px">
                                    <input type="radio" value="Tidak" id="rt61" name="rt6" /><label for="rt61">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="rt62" name="rt6" /><label for="rt62">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_puk_resiko_tinggi" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('resiko_tinggi','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_resiko_tinggi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-puk-nyeri">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Ulang Nyeri
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_nyeri">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_nyeri"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                                <select class="form-control" id="yer1">
                                    <option value="">[Select One]</option>
                                    <option value="pagi">Pagi</option>
                                    <option value="siang">Siang</option>
                                    <option value="malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="yer2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="yer3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Scala Nyeri</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Perjalanan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kwantitas</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer6" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kwalitas</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer7" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Faktor Pemberat</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer9" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Faktor Peringan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer10" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Time Bound/Jangka waktu</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer11" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="yer12" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jam</label>
                            <div class="col-md-8">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control jam" id="yer13">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">1. Reposisi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="yer141" name="yer14" /><label for="yer141">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="yer142" name="yer14" /><label for="yer142">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">2. Kompres hangat</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="yer151" name="yer15" /><label for="yer151">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="yer152" name="yer15" /><label for="yer152">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">3. Kompres dingin</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="yer161" name="yer16" /><label for="yer161">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="yer162" name="yer16" /><label for="yer162">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">4. Kurangi rangsangan</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="yer171" name="yer17" /><label for="yer171">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="yer172" name="yer17" /><label for="yer172">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">5. Alihkan perhatian</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="yer181" name="yer18" /><label for="yer181">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="yer182" name="yer18" /><label for="yer182">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">6. Obat obatan</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="yer191" name="yer19" /><label for="yer191">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="yer192" name="yer19" /><label for="yer192">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_puk_nyeri" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('nyeri','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_nyeri" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-puk-resiko_kulit">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Ulang Resiko Kulit (Norton Scale)
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_resiko_kulit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_resiko_kulit"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                                <select class="form-control" id="klt1">
                                    <option value="">[Select One]</option>
                                    <option value="pagi">Pagi</option>
                                    <option value="siang">Siang</option>
                                    <option value="malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="klt2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="klt3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kriteria</label>
                            <div class="col-md-8">
                                <input class="form-control" id="klt4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">1. Ubah Posisi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="klt51" name="klt5" /><label for="klt51">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="klt52" name="klt5" /><label for="klt52">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">2. Jaga kebersihan kulit</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="klt61" name="klt6" /><label for="klt61">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="klt62" name="klt6" /><label for="klt62">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">3. Anjurkan intake adekuat</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="klt71" name="klt7" /><label for="klt71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="klt72" name="klt7" /><label for="klt72">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">4. Pasang kasur anti dekubitus</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="klt81" name="klt8" /><label for="klt81">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="klt82" name="klt8" /><label for="klt82">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_puk_resiko_kulit" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('resiko_kulit','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_resiko_kulit" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-puk-restrain">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Ulang Restrain
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_restrain">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_restrain"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                                <select class="form-control" id="res1">
                                    <option value="">[Select One]</option>
                                    <option value="pagi">Pagi</option>
                                    <option value="siang">Siang</option>
                                    <option value="malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="res2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="res3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Indikasi Restrain</label>
                            <div class="col-md-8">
                                <input class="form-control" id="res4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jenis Restrain</label>
                            <div class="col-md-8">
                                <input class="form-control" id="res5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="res6" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">1. Dekatkan kebutuhan pasien</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="res71" name="res7" /><label for="res71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="res72" name="res7" /><label for="res72">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">2. Edukasi pada penunggu</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="res81" name="res8" /><label for="res81">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="res82" name="res8" /><label for="res82">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">3. Bantu Keb dasar pasien</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="res91" name="res9" /><label for="res91">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="res92" name="res9" /><label for="res92">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_puk_restrain" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('restrain','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_restrain" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-puk-pemulangan_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Ulang Rencana Pemulangan Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_pemulangan_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_pemulangan_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                                <select class="form-control" id="pep1">
                                    <option value="">[Select One]</option>
                                    <option value="pagi">Pagi</option>
                                    <option value="siang">Siang</option>
                                    <option value="malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="pep2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="pep3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kriteria pemulangan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pep4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pep5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">1. Edukasi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pep61" name="pep6" /><label for="pep61">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="pep62" name="pep6" /><label for="pep62">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">2. Identifikasi kebutuhan pasien</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pep71" name="pep7" /><label for="pep71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="pep72" name="pep7" /><label for="pep72">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_puk_pemulangan_pasien" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('pemulangan_pasien','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_pemulangan_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-puk-pasien_akhir">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Ulang Pasien Pada Akhir Kehidupan (terminal)
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_puk_pasien_akhir">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_puk_pasien_akhir"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Waktu</label>
                            <div class="col-md-8">
                                <select class="form-control" id="pak1">
                                    <option value="">[Select One]</option>
                                    <option value="pagi">Pagi</option>
                                    <option value="siang">Siang</option>
                                    <option value="malam">Malam</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="pak2">
                                </div>
                            </div>
                            <label class="col-md-1" style="margin-top: 7px">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="pak3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kebutuhan Khusus</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pak4" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-8">
                                <input class="form-control" id="pak5" style="margin-top: 7px">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">1. Hub layanan khusus</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pak61" name="pak6" /><label for="pak61">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="pak62" name="pak6" /><label for="pak62">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">2. Pendamping keluarga</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pak71" name="pak7" /><label for="pak71">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="pak72" name="pak7" /><label for="pak72">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-5" style="margin-top: 7px">3. Mendoakan</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="pak81" name="pak8" /><label for="pak81">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="pak82" name="pak8" /><label for="pak82">Ya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Perawat</label>
                                <canvas class="paint-canvas-ttd" id="gen2" width="220"
                                        onmouseover="paintTtd('gen2')"></canvas>
                                <input class="form-control" id="nama_terang_gen2" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control" id="sip_gen2" placeholder="NIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('gen2')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6 text-center">
                                <label>TTD DPJP</label>
                                <canvas class="paint-canvas-ttd" id="gen3" width="220"
                                        onmouseover="paintTtd('gen3')"></canvas>
                                <input class="form-control nama_dokter" id="nama_terang_gen3" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter" id="sip_gen3" placeholder="SIP">
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
                <button id="save_puk_pasien_akhir" class="btn btn-success pull-right"
                        onclick="savePengkajianKep('pasien_akhir','pengkajian')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_puk_pasien_akhir" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>