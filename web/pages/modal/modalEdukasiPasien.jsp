<div class="modal fade" id="modal-ina-edukasi_pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Edukasi Pasien dan Keluarga
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_edukasi_pasien">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_edukasi_pasien"></p>
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
                            <li><a onclick="showModalAsesmenRawatInap('asesmen_edukasi_pasien')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Asesmen</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('perencanaan_edukasi_pasien')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Perencanaan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_edukasi_pasien">
                        <tbody>
                        <tr id="row_ina_asesmen_edukasi_pasien">
                            <td>Asesmen Edukasi Pasien dan Keluarga</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_asesmen_edukasi_pasien" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('asesmen_edukasi_pasien')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_perencanaan_edukasi_pasien">
                            <td>Perencanaan Edukasi Pasien dan Keluarga</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_perencanaan_edukasi_pasien" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('perencanaan_edukasi_pasien')"
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

<div class="modal fade" id="modal-ina-asesmen_edukasi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Edukasi Pasien dan Keluarga
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_asesmen_edukasi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_asesmen_edukasi_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Keyakinan/Nilai-nilai</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep1')" type="radio" value="Tidak" id="ep11" name="ep1" /><label for="ep11">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep1')" type="radio" value="Ya" id="ep12" name="ep1" /><label for="ep12">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-ep1">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" placeholder="Keterangan" style="margin-top: 7px" id="ket_ep1">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Bahasa</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep2')" type="radio" value="Indonesia" id="ep21" name="ep2" /><label for="ep21">Indonesia</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep2')" type="radio" value="Inggris" id="ep22" name="ep2" /><label for="ep22">Inggris</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep2')" type="radio" value="Daerah" id="ep23" name="ep2" /><label for="ep23">Daerah</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep2')" type="radio" value="Lainnya" id="ep24" name="ep2" /><label for="ep24">Lainnya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-ep2">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" placeholder="Keterangan" style="margin-top: 7px" id="ket_ep2">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Agama</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep3')" type="radio" value="Islam" id="ep31" name="ep3" /><label for="ep31">Islam</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep3')" type="radio" value="Kristen" id="ep32" name="ep3" /><label for="ep32">Kristen</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep3')" type="radio" value="Katolik" id="ep33" name="ep3" /><label for="ep33">Katolik</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep3')" type="radio" value="Budha" id="ep34" name="ep3" /><label for="ep34">Budha</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep3')" type="radio" value="Hindu" id="ep35" name="ep3" /><label for="ep35">Hindu</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep3')" type="radio" value="Lainnya" id="ep36" name="ep3" /><label for="ep36">Lainnya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-ep3">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" placeholder="Keterangan" style="margin-top: 7px" id="ket_ep3">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Kebutuhan Peterjemah</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep4')" type="radio" value="Tidak" id="ep41" name="ep4" /><label for="ep41">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep4')" type="radio" value="Ya" id="ep42" name="ep4" /><label for="ep42">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-ep4">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" placeholder="Keterangan" style="margin-top: 7px" id="ket_ep4">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Pendidikan pasien</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep5')" type="radio" value="SD" id="ep51" name="ep5" /><label for="ep51">SD</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep5')" type="radio" value="SMP" id="ep52" name="ep5" /><label for="ep52">SMP</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep5')" type="radio" value="SMA" id="ep53" name="ep5" /><label for="ep53">SMA</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep5')" type="radio" value="D3/S1" id="ep54" name="ep5" /><label for="ep54">D3/S1</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep5')" type="radio" value="Lainnya" id="ep55" name="ep5" /><label for="ep55">Lainnya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-ep5">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" placeholder="Keterangan" style="margin-top: 7px" id="ket_ep5">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Baca dan Tulis</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Baik" id="ep61" name="ep6" /><label for="ep61">Baik</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Kurang" id="ep62" name="ep6" /><label for="ep62">Kurang</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Pilihan tipe pembelajaran</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Verbal" id="ep71" name="ep7" /><label for="ep71">Verbal</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Tulisan" id="ep72" name="ep7" /><label for="ep72">Tulisan</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Hambatan Edukasi</label>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Tidak ada" id="ep81" name="ep8" /><label for="ep81">Tidak ada</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Emosional" id="ep82" name="ep8" /><label for="ep82">Emosional</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-4">
                                <div class="custom02">
                                    <input type="radio" value="Motivasi kurang" id="ep83" name="ep8" /><label for="ep83">Motivasi kurang</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Keterbatasan fisik dan kognitif</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep9')" type="radio" value="Tidak" id="ep91" name="ep9" /><label for="ep91">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep9')" type="radio" value="Ya" id="ep92" name="ep9" /><label for="ep92">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-ep9">
                            <div class="col-md-offset-4 col-md-7">
                                <input class="form-control" placeholder="Keterangan" style="margin-top: 7px" id="ket_ep9">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Kesedian menerima informasi/edukasi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep10')" type="radio" value="Tidak" id="ep101" name="ep10" /><label for="ep101">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input onclick="showKetIna(this.value, 'ep10')" type="radio" value="Ya" id="ep102" name="ep10" /><label for="ep102">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ina-ep10">
                            <div class="col-md-offset-4 col-md-7">
                                <input placeholder="Keterangan" class="form-control" style="margin-top: 7px" id="ket_ep10">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_asesmen_edukasi_pasien" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('asesmen_edukasi_pasien','edukasi_pasien')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_asesmen_edukasi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ina-perencanaan_edukasi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Perencanaan Edukasi Pasien dan Keluarga
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_perencanaan_edukasi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_perencanaan_edukasi_pasien"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per11" value="Fasilitas kamar, jam kunjungan pasien, waktu visite dokter">
                                    <label for="per11"></label> Fasilitas kamar, jam kunjungan pasien, waktu visite dokter
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per12" value="Nama DPJP dan penjelasan gelang identitas">
                                    <label for="per12"></label> Nama DPJP dan penjelasan gelang identitas
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per13" value="Dignosa, tanda gejala dan tata laksana penyakit">
                                    <label for="per13"></label> Dignosa, tanda gejala dan tata laksana penyakit
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per14" value="Manfaat, efek samping, dan interaksi obat-obatan yang diberikan serta penggunaan obat waktu pulang">
                                    <label for="per14"></label> Manfaat, efek samping, dan interaksi obat-obatan yang diberikan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12" style="margin-left: 35px; margin-top: -7px">serta penggunaan obat waktu pulang</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per15" value="Program diet dan nutrisi">
                                    <label for="per15"></label> Program diet dan nutrisi
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per16" value="Teknik-teknik rehabilitasi medis">
                                    <label for="per16"></label> Teknik-teknik rehabilitasi medis
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per18" value="Cuci tangan yang benar">
                                    <label for="per18"></label> Cuci tangan yang benar
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12" style="margin-top: 7px">Penggunaan alat kedokteran (alat kesehatan)</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per19" value="Penggunaan APD (masker/sarung tangan)">
                                    <label for="per19"></label> Penggunaan APD (masker/sarung tangan)
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per10">
                                    <label for="per10"></label> Penggunaan Alat Lainnya
                                </div>
                            </div>
                            <div class="col-md-6">
                                <input class="form-control" style="margin-left: -15px" oninput="$('#per10').val('Penggunaan Alat Lainnya, '+this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per111" value="Manajemen nyeri">
                                    <label for="per111"></label> Manajemen nyeri
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per112" value="Prosedur perawatan sepsifik">
                                    <label for="per112"></label> Prosedur perawatan sepsifik
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per114" value="Perawatan insulin">
                                    <label for="per114"></label> Perawatan insulin
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per115" value="Pemberian obat inhalasi">
                                    <label for="per115"></label> Pemberian obat inhalasi
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per116" value="Pemberian Makan lewat NGT">
                                    <label for="per116"></label> Pemeberian Makan lewat NGT
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-3">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per117">
                                    <label for="per117"></label> Lain-Lain
                                </div>
                            </div>
                            <div class="col-md-7">
                                <input class="form-control" style="margin-left: -15px" oninput="$('#per117').val(this.value)">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="per1" id="per118" value="Persiapan pasien pulang (Waktu kontrol dll)">
                                    <label for="per118"></label> Persiapan pasien pulang (Waktu kontrol dll)
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_perencanaan_edukasi_pasien" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('perencanaan_edukasi_pasien','edukasi_pasien')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_perencanaan_edukasi_pasien" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>