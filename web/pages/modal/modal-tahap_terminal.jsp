<div class="modal fade" id="modal-icu-tahap_terminal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Asesmen Pasien Tahap Terminal
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_icu_tahap_terminal">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_icu_tahap_terminal"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_tahap_terminal">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_tahap_terminal"></p>
                    </div>

                    <div class="btn-group">
                        <button type="button" onclick="showModalICU('terminal')" class="btn btn-success"><i class="fa fa-plus"></i> Asesmen
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_icu_terminal">
                        <tbody>
                        <tr id="row_icu_terminal">
                            <td>Asesmen Pasien Tahap Terminal</td>
                            <td width="20%" align="center">
                                <img id="btn_icu_terminal" class="hvr-grow" onclick="detailICU('terminal')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_terminal" class="hvr-grow" onclick="conICU('terminal', 'tahap_terminal')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-icu-terminal">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Pasien Tahap Terminal
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_icu_terminal">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_icu_terminal"></p>
                </div>
                <div class="box-body">
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
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Keluarga Terdekat</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ter1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Hubungan</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ter2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">No Telp</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ter3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Gelang Identifikasi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ada" id="ter41"
                                           name="ter4"/><label for="ter41">Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada" id="ter42"
                                           name="ter4"/><label for="ter42">Tidak ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Persetujuan Umum</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ada" id="ter51"
                                           name="ter5"/><label for="ter51">Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada" id="ter52"
                                           name="ter5"/><label for="ter52">Tidak ada</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Indikasi Pasien</label>
                            <div class="col-md-9">
                                <input class="form-control" id="ter06">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Alergi Pasien</label>
                            <div class="col-md-9">
                                <input class="form-control alergi-pasien" id="ter07">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="ter08" placeholder="Tensi">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="ter09" placeholder="Nadi">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="ter010" placeholder="Suhu">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="ter011" placeholder="RR">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="ter012" placeholder="BB">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="ter013" placeholder="TB">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <input class="form-control" id="ter014" placeholder="GCS">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Anamnesa</label>
                            <div class="col-md-9">
                                <textarea class="form-control anamnese" id="ter015"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-12">Alat Bantu yang digunakan</label>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Oksigen</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ada" id="ter61"
                                           name="ter6"/><label for="ter61">Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada" id="ter62"
                                           name="ter6"/><label for="ter62">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" placeholder="Keterangan" id="ket_ter6" oninput="$('#ter61').val('Ada, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Ventilator</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ada" id="ter71"
                                           name="ter7"/><label for="ter71">Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada" id="ter72"
                                           name="ter7"/><label for="ter72">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" placeholder="Keterangan" id="ket_ter7" oninput="$('#ter71').val('Ada, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Monitor Pasien</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ada" id="ter81"
                                           name="ter8"/><label for="ter81">Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada" id="ter82"
                                           name="ter8"/><label for="ter82">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" placeholder="Keterangan" id="ket_ter8" oninput="$('#ter81').val('Ada, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Alat bantu yang lain</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ada" id="ter91"
                                           name="ter9"/><label for="ter91">Ada</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada" id="ter92"
                                           name="ter9"/><label for="ter92">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" placeholder="Keterangan" id="ket_ter9" oninput="$('#ter91').val('Ada, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-12">Asesmen Neurologi</label>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Relaksasi Otot</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Menurun" id="ter101"
                                           name="ter10"/><label for="ter101">Menurun</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Normal" id="ter102"
                                           name="ter10"/><label for="ter102">Normal</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Mata / Refrek cahaya</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Menurun" id="ter111"
                                           name="ter11"/><label for="ter111">Menurun</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Normal" id="ter112"
                                           name="ter11"/><label for="ter112">Normal</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Berbicara</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Normal" id="ter121"
                                           name="ter12"/><label for="ter121">Normal</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak Normal" id="ter122"
                                           name="ter12"/><label for="ter122">Tidak Normal</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Kenjang</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Spasme" id="ter131"
                                           name="ter13"/><label for="ter131">Spasme</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Kaku Sendi" id="ter132"
                                           name="ter13"/><label for="ter132">Kaku Sendi</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Gastrointestinal</label>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="ter14" id="ter141" value="Mual">
                                    <label for="ter141"></label> Mual
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="ter14" id="ter142" value="Muntah">
                                    <label for="ter142"></label> Muntah
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-3 col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="ter14" id="ter143" value="Kembung">
                                <label for="ter143"></label> Kembung
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-check">
                                <input type="checkbox" name="ter14" id="ter144" value="Obstipasi">
                                <label for="ter144"></label> Obstipasi
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <label class="col-md-12">Kardio Vaskuler</label>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Nadi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Cepat" id="ter0141"
                                           name="ter014"/><label for="ter0141">Cepat</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Kecil" id="ter0142"
                                           name="ter014"/><label for="ter0142">Kecil</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Ritme</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Lemah" id="ter151"
                                           name="ter15"/><label for="ter151">Lemah</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Kuat" id="ter152"
                                           name="ter15"/><label for="ter152">Kuat</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Dingin" id="ter153"
                                           name="ter15"/><label for="ter153">Dingin</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Hangat" id="ter154"
                                           name="ter15"/><label for="ter154">Hangat</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Respirasi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sesak" id="ter161"
                                           name="ter16"/><label for="ter161">Sesak</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ngorok" id="ter162"
                                           name="ter16"/><label for="ter162">Ngorok</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Cepat" id="ter163"
                                           name="ter16"/><label for="ter163">Cepat</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sonisis" id="ter164"
                                           name="ter16"/><label for="ter164">Sonisis</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Akral</label>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Cepet Dangkal" id="ter171"
                                           name="ter17"/><label for="ter171">Cepet Dangkal</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Cheyne stoke" id="ter172"
                                           name="ter17"/><label for="ter172">Cheyne stoke</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Saluran Kencing</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Inkontennesnsia" id="ter181"
                                           name="ter18"/><label for="ter181">Inkontennesnsia</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Retensi" id="ter182"
                                           name="ter18"/><label for="ter182">Retensi</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Hematuria" id="ter183"
                                           name="ter18"/><label for="ter183">Hematuria</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tipe Kateter</label>
                            <div class="col-md-9">
                                <input id="ter19" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal Kateter</label>
                            <div class="col-md-9">
                                <div class="input-group">
                                    <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    <input id="ter20" class="form-control tgl"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6 text-center">
                                <label>TTD Petugas</label>
                                <canvas class="paint-canvas-ttd" id="ter21" width="220"
                                        onmouseover="paintTtd('ter21')"></canvas>
                                <input class="form-control" id="nama_terang_ter21" placeholder="Nama Terang">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ter21')"><i
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
                <button id="save_icu_terminal" class="btn btn-success pull-right"
                        onclick="saveICU('terminal','asesmen_icu')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_icu_terminal" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>