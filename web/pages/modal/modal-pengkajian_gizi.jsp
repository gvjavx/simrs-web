<div class="modal fade" id="modal-gizi-pengkajian_gizi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Pengkajian Gizi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_gizi_pengkajian_gizi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_gizi_pengkajian_gizi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_pengkajian_gizi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_pengkajian_gizi"></p>
                    </div>
                    <button type="button" onclick="showModalGizi('add_pengkajian_gizi')" class="btn btn-success"><i
                            class="fa fa-plus"></i> Tambah Pengkajian Gizi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_gizi_pengkajian_gizi">
                        <tbody>
                        <tr id="row_gizi_add_pengkajian_gizi">
                            <td>Pengkajian Gizi</td>
                            <td width="20%" align="center">
                                <img id="btn_gizi_add_pengkajian_gizi" class="hvr-grow"
                                     onclick="detailGizi('add_pengkajian_gizi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                                <%--<img id="delete_add_pengkajian_gizi" class="hvr-grow btn-hide"--%>
                                     <%--onclick="conGizi('add_pengkajian_gizi', 'pengkajian_gizi')"--%>
                                     <%--src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">--%>
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

<div class="modal fade" id="modal-gizi-add_pengkajian_gizi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pengkajian Gizi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_gizi_add_pengkajian_gizi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_gizi_add_pengkajian_gizi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12"><b>A. Antropmentri</b></div>
                    </div>
                    <div id="set_pengakajian"></div>
                    <hr>
                    <div class="row jarak">
                        <label class="col-md-3">Status Gizi</label>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input type="radio" value="Buruk" id="gizi91" name="gizi9"/>
                                <label for="gizi91">Buruk</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input type="radio" value="Kurang" id="gizi92" name="gizi9"/>
                                <label for="gizi92">Kurang</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input type="radio" value="Normal" id="gizi93" name="gizi9"/>
                                <label for="gizi93">Normal</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-3 col-md-3">
                            <div class="custom02">
                                <input type="radio" value="Lebih" id="gizi94" name="gizi9"/>
                                <label for="gizi94">Lebih</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input type="radio" value="Obesitas" id="gizi95" name="gizi9"/>
                                <label for="gizi95">Obesitas</label>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12"><b>B. Biokimia</b></div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <div class="custom02">
                                <input onclick="showKetGizi(this.value, 'gizi10')" type="radio" value="Normal" id="gizi101" name="gizi10"/>
                                <label for="gizi101">Normal</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input onclick="showKetGizi(this.value, 'gizi10')" type="radio" value="Bermasalah" id="gizi102"
                                       name="gizi10"/>
                                <label for="gizi102">Bermasalah</label>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" oninput="$('#gizi102').val(''); $('#gizi102').val('Bermasalah, berupa '+this.value);"
                                   style="display: none" id="form_gizi10" placeholder="Keterangan Bermasalah">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="col-md-12"><b>C. Fisik - Klinik</b></div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12">
                            <div class="custom02">
                                <input onclick="showKetGizi(this.value, 'gizi11')" type="radio" value="Normal" id="gizi111" name="gizi11"/>
                                <label for="gizi111">Normal</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="custom02">
                                <input onclick="showKetGizi(this.value, 'gizi11')" type="radio" value="Bermasalah" id="gizi112"
                                       name="gizi11"/>
                                <label for="gizi112">Bermasalah</label>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" oninput="$('#gizi112').val(''); $('#gizi112').val('Bermasalah, berupa '+this.value);"
                                   style="display: none" id="form_gizi11" placeholder="Keterangan Bermasalah">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="col-md-12"><b>D. Riwayat Gizi</b></div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12"><b>Dahulu</b></div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <div class="form-check">
                                <input type="checkbox" name="gizi13" id="gizi131" value="Tidak ada alergi makanan">
                                <label for="gizi131"></label> Tidak ada alergi makanan
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi132')" type="checkbox" name="gizi13" id="gizi132">
                                <label for="gizi132"></label> Ada alergi makanan
                            </div>
                            <input class="form-control" oninput="$('#gizi132').val(''); $('#gizi132').val('Ada alergi makanan, '+this.value);"
                                   style="display: none" id="form_gizi132">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <div class="form-check">
                                <input type="checkbox" name="gizi13" id="gizi133" value="Pola makan teratur">
                                <label for="gizi133"></label> Pola makan teratur
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi134')" type="checkbox" name="gizi13" id="gizi134">
                                <label for="gizi134"></label> Pola makan tidak teratur
                            </div>
                            <input class="form-control" oninput="$('#gizi134').val(''); $('#gizi134').val('Pola makan tidak teratur, '+this.value);"
                                   style="display: none" id="form_gizi134">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <div class="form-check">
                                <input type="checkbox" name="gizi13" id="gizi135" value="Susunan menu seimbang">
                                <label for="gizi135"></label> Susunan menu seimbang
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi137')" type="checkbox" name="gizi13" id="gizi137">
                                <label for="gizi137"></label> Susunan menu tidak seimbang
                            </div>
                            <input class="form-control" oninput="$('#gizi137').val(''); $('#gizi137').val('Susunan menu tidak seimbang, '+this.value);"
                                   style="display: none" id="form_gizi137">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <div class="form-check">
                                <input type="checkbox" name="gizi13" id="gizi138" value="Diberikan ASI">
                                <label for="gizi138"></label> Diberikan ASI
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi139')" type="checkbox" name="gizi13" id="gizi139">
                                <label for="gizi139"></label> Tidak diberikan ASI
                            </div>
                            <input class="form-control" oninput="$('#gizi139').val(''); $('#gizi139').val('Tidak diberikan ASI, '+this.value);"
                                   style="display: none" id="form_gizi139">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-6">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi1310')" type="checkbox" name="gizi13" id="gizi1310">
                                <label for="gizi1310"></label> Lain-Lain
                            </div>
                            <input class="form-control" oninput="$('#gizi1310').val(''); $('#gizi1310').val('Lain-Lain, '+this.value);"
                                   style="display: none" id="form_gizi1310">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-12"><b>Sekarang</b></div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-3">Nafsu Makan</label>
                        <div class="col-md-2">
                            <div class="custom02">
                                <input onclick="showKetGizi(this.value, 'gizi14')" type="radio" value="Baik" id="gizi141" name="gizi14"/>
                                <label for="gizi141">Baik</label>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="custom02">
                                <input onclick="showKetGizi(this.value, 'gizi14')" type="radio" value="Kurang" id="gizi142" name="gizi14"/>
                                <label for="gizi142">Kurang</label>
                            </div>
                        </div>
                        <div style="display: none; font-size: 12px" id="form_gizi14">
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi15" id="gizi151" value="Sulit menelan">
                                    <label for="gizi151"></label> Sulit menelan
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi15" id="gizi152" value="Sulit mengunyah">
                                    <label for="gizi152"></label> Sulit mengunyah
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi15" id="gizi153" value="Mual">
                                    <label for="gizi153"></label> Mual
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="gizi15" id="gizi154" value="Muntah">
                                    <label for="gizi154"></label> Muntah
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-2">Diet</label>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_berupa1')" type="checkbox" name="gizi16" id="gizi161" value="Parenteral">
                                <label for="gizi161"></label> Parenteral
                            </div>
                        </div>
                        <div class="col-md-7" style="display: none" id="form_berupa1">
                            <input class="form-control" placeholder="Berupa" oninput="$('#gizi161').val(''); $('#gizi161').val('Parenteral, berupa'+this.value)">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-2 col-md-3">
                            <div class="form-check">
                                <input onclick="isCekMulti(this.id)" type="checkbox" name="gizi16" id="gizi162" value="Enteral">
                                <label for="gizi162"></label> Enteral
                            </div>
                        </div>
                        <div id="form_gizi161" style="display: none">
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetGizi(this.value, 'gizi17')" type="radio" value="Oral" id="gizi171" name="gizi17"/>
                                    <label for="gizi171">Oral</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetGizi(this.value, 'gizi17')" type="radio" value="NGT" id="gizi172" name="gizi17"/>
                                    <label for="gizi172">NGT</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input onclick="showKetGizi(this.value, 'gizi17')" type="radio" id="gizi173" name="gizi17" value="Lain-Lain"/>
                                    <label for="gizi173">Lain-Lain</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="form_gizi17">
                        <div class="col-md-offset-5 col-md-7">
                            <input class="form-control" placeholder="Keterang Lain-Lain" oninput="$('#gizi173').val('');$('#gizi173').val('Lain-Lain, '+this.value);">
                        </div>
                    </div>
                    <div class="row jarak" style="display: none" id="form_gizi163">
                        <div class="col-md-offset-5 col-md-7">
                            <input class="form-control" placeholder="Berupa" oninput="$('#gizi162').val('');$('#gizi162').val('Enteral, berupa'+this.value);">
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row jarak">
                        <div class="col-md-12"><b>E. Riwayat Personal</b></div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-4">Riwayat penyakit dahulu</div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="gizi18" id="gizi181" value="Hipertensi">
                                <label for="gizi181"></label> Hipertensi
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="gizi18" id="gizi182" value="DM">
                                <label for="gizi182"></label> DM
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-4 col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="gizi18" id="gizi183" value="Jantung">
                                <label for="gizi183"></label> Jantung
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="gizi18" id="gizi184" value="Stroke">
                                <label for="gizi184"></label> Stroke
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-offset-4 col-md-3">
                            <div class="form-check">
                                <input onclick="isCek(this.id, 'form_gizi18')" type="checkbox" name="gizi18" id="gizi185" value="Stroke">
                                <label for="gizi185"></label> Lain-Lain
                            </div>
                        </div>
                        <div class="col-md-5">
                            <input style="display: none" id="form_gizi18" class="form-control" placeholder="Keterangan Lain-Lain">
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="col-md-4">Riwayat penyakit sekarang</div>
                        <div class="col-md-8">
                            <textarea class="form-control" id="gizi19" rows="3"></textarea>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-6">
                            <label>TTD Pasien/Keluarga</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('gizi20')" class="paint-canvas-ttd" id="gizi20"></canvas>
                            <input class="form-control" id="nama_gizi20" placeholder="Nama Terang">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('gizi20')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <label>TTD Ahli Gizi</label>
                            <canvas style="margin-left: -1px;" width="250" onmouseover="paintTtd('gizi21')" class="paint-canvas-ttd" id="gizi21"></canvas>
                            <input class="form-control" id="nama_gizi21" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control" id="sip_dokter" placeholder="SIP/NIP">
                            <button style="margin-left: -1px" type="button" class="btn btn-danger" onclick="removePaint('gizi21')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_gizi_add_pengkajian_gizi" class="btn btn-success pull-right"
                        onclick="saveGizi('add_pengkajian_gizi', 'pengkajian_gizi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_gizi_add_pengkajian_gizi" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>