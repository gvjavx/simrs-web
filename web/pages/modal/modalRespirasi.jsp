<div class="modal fade" id="modal-icu-respirasi">
    <div class="modal-dialog" style="width: 75%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Respirasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_icu_respirasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_respirasi"></p>
                    </div>
                    <button onclick="showModalICU('respirasi_icu')" type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Respirasi
                    </button>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_icu" style="font-size: 11px">
                        <thead id="head_respirasi">
                        <tr>
                            <td rowspan="2" style="vertical-align: middle" align="center">Tanggal Jam</td>
                            <td colspan="3" style="vertical-align: middle" align="center">GCS</td>
                            <td colspan="2" style="vertical-align: middle" align="center">DP</td>
                            <td colspan="2" style="vertical-align: middle" align="center">RC</td>
                            <td colspan="4" style="vertical-align: middle" align="center">EXTR</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">NM</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">TP</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">TI</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">PE</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">FR</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">TV</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">MV</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">PS</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">PI</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">TR</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">IN</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">FL</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">FI</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">UK</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">SP</td>
                            <td rowspan="2" style="vertical-align: middle" align="center">SE</td>
                        </tr>
                        <tr>
                            <td align="center">E</td>
                            <td align="center">V</td>
                            <td align="center">M</td>
                            <td align="center">R</td>
                            <td align="center">L</td>
                            <td align="center">R</td>
                            <td align="center">L</td>
                            <td align="center">TR</td>
                            <td align="center">TL</td>
                            <td align="center">KR</td>
                            <td align="center">KL</td>
                        </tr>
                        </thead>
                        <tbody id="body_respirasi">

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-icu-respirasi_icu">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Tambah Data Respirasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_icu_respirasi_icu">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_icu_respirasi_icu"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Waktu</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="id1" style="width: 100%">
                                    <option value="">[Select One]</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                                    <option value="22:00">22:00</option>
                                    <option value="23:00">23:00</option>
                                    <option value="24:00">24:00</option>
                                    <option value="01:00">01:00</option>
                                    <option value="02:00">02:00</option>
                                    <option value="03:00">03:00</option>
                                    <option value="04:00">04:00</option>
                                    <option value="05:00">05:00</option>
                                    <option value="06:00">06:00</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">GCS (EVM)</label>
                            <div class="col-md-9">
                                <select class="form-control select2" style="width: 100%" id="id2">
                                    <option value="">[Select One E]</option>
                                    <option value="4">Spontan</option>
                                    <option value="3">Dengan Panggilan</option>
                                    <option value="2">Dengan rangsangan nyeri</option>
                                    <option value="1">Tidak ada respon</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <select class="form-control select2" style="width: 100%" id="id3">
                                    <option value="">[Select One V]</option>
                                    <option value="5">Orientasi</option>
                                    <option value="4">Apatis</option>
                                    <option value="3">Disorientasi</option>
                                    <option value="2">Mengarang</option>
                                    <option value="1">Tidak ada</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <select class="form-control select2" style="width: 100%" id="id4">
                                    <option value="">[Select One M]</option>
                                    <option value="6">Spontan</option>
                                    <option value="5">Terlokalisir</option>
                                    <option value="4">Fleksi</option>
                                    <option value="3">Deserebrasi</option>
                                    <option value="2">Dekortasi</option>
                                    <option value="1">Tak ada respon</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Diameter Pupil KA</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id5" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="8">8</option>
                                    <option value="7">7</option>
                                    <option value="6">6</option>
                                    <option value="5">5</option>
                                    <option value="4">4</option>
                                    <option value="3">3</option>
                                    <option value="2">2</option>
                                    <option value="1">1</option>
                                </select>
                            </div>
                            <label class="col-md-3 jarak">Diameter Pupil KI</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id6" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="8">8</option>
                                    <option value="7">7</option>
                                    <option value="6">6</option>
                                    <option value="5">5</option>
                                    <option value="4">4</option>
                                    <option value="3">3</option>
                                    <option value="2">2</option>
                                    <option value="1">1</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Reflek Cahaya KA</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id7" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="+">+</option>
                                    <option value="-">-</option>
                                </select>
                            </div>
                            <label class="col-md-3 jarak">Reflek Cahaya KI</label>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id8" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="+">+</option>
                                    <option value="-">-</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">TKA</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="id9">
                            </div>
                            <label class="col-md-3">TKI</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="id10">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">KKA</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="id11">
                            </div>
                            <label class="col-md-3">KKI</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="id12">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <select class="form-control select2" id="id13" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="NK">NK</option>
                                    <option value="RM">RM</option>
                                    <option value="NRM">NRM</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id014">
                            </div>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id14" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="T-Piece">T-Piece</option>
                                    <option value="J-Rise">J-Rise</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id15">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Tipe Ventilasi</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id16">
                            </div>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id17" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="PEEP">PEEP</option>
                                    <option value="CPAP">CPAP</option>
                                    <option value="ET C02">ET C02</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id18">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Frekwensi/ Freweksi Total</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="id19">
                            </div>
                            <label class="col-md-3 jarak">TV (I) TV(E)</label>
                            <div class="col-md-3">
                                <input class="form-control" type="number" id="id20">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak">MV (I) MV (I)</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id21">
                            </div>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id22" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="P-Support">P-Support</option>
                                    <option value="P-Asb">P-Asb</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id23">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <select class="form-control select2" id="id24" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="P-Inspirasi">P-Inspirasi</option>
                                    <option value="P-Control">P-Control</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id25">
                            </div>
                            <label class="col-md-3 jarak">Triger</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id26">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 jarak">Inspirasi Time</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id27">
                            </div>
                            <label class="col-md-3 jarak">Flow</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id28">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <select class="form-control select2" id="id29" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="Fio 2">Fio 2</option>
                                    <option value="Konsentrasi O2">Konsentrasi O2</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id30">
                            </div>
                            <div class="col-md-3">
                                <select class="form-control select2" id="id31" style="width: 100%">
                                    <option value="">[Select]</option>
                                    <option value="Ukuran ETT">Ukuran ETT</option>
                                    <option value="Kedalaman ETT">Kedalaman ETT</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id32">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3 jarak"> SPO2</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id33">
                            </div>
                            <label class="col-md-3 jarak"> Secret/ Sputum</label>
                            <div class="col-md-3">
                                <input class="form-control jarak" type="number" id="id34">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button id="save_icu_respirasi_icu" class="btn btn-success pull-right" onclick="saveRespirasi('respirasi_icu', 'respirasi')"><i class="fa fa-check"></i> Save
                </button>
                <button id="load_icu_respirasi_icu" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>