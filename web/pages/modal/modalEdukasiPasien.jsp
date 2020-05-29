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

<div class="modal fade" id="modal-ina-edukasi_pasien_terintegrasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Edukasi Pasien dan Keluarga Terintegrasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_edukasi_pasien_terintegrasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_edukasi_pasien_terintegrasi"></p>
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
                            <li><a onclick="showModalAsesmenRawatInap('ept_tppri')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> TPPRI</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_perawat')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Perawat</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_dpjp')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> DPJP</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_nutrisionis')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Nutrisionis</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_farmasi')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Farmasi</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_rehabilitas_medis')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Rehabilitasi Medis</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_dokter')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Dokter/Perawat/Bidan</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_radiografer')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Radiografer</a></li>
                            <li><a onclick="showModalAsesmenRawatInap('ept_ppa')" style="cursor: pointer"><i
                                    class="fa fa-plus"></i> Kolaborasi Antar PPA</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_ina_edukasi_pasien_terintegrasi">
                        <tbody>
                        <tr id="row_ina_ept_tppri">
                            <td>TPPRI</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_tppri" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_tppri')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_perawat">
                            <td>Perawat</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_perawat" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_perawat')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_dpjp">
                            <td>DPJP</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_dpjp" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_dpjp')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_nutrisionis">
                            <td>Nutrisionis</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_nutrisionis" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_nutrisionis')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_farmasi">
                            <td>Farmasi</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_farmasi" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_farmasi')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_rehabilitasi_medis">
                            <td>Rehabilitasi Medis</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_rehabilitasi_medis" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_rehabilitasi_medis')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_dokter">
                            <td>Dokter/Perawat/Bidan</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_dokter" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_dokter')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_radiografer">
                            <td>Radiografer</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_radiogrfer" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_radiogrfer')"
                                     src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                            </td>
                        </tr>
                        <tr id="row_ina_ept_ppa">
                            <td>Kolaborasi Antar PPA</td>
                            <td width="20%" align="center">
                                <img id="btn_ina_ept_ppa" class="hvr-grow"
                                     onclick="detailAsesmenRawatInap('ept_ppa')"
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

<div class="modal fade" id="modal-ina-ept_tppri">
    <div class="modal-dialog" style="width: 63%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> TPPRI
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_ept_tppri">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_ept_tppri"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <table class="table table-bordered table-striped" style="font-size: 12px">
                                <thead>
                                <tr style="font-weight: bold">
                                    <td colspan="3" align="center" style="vertical-align: middle">Durasi Waktu</td>
                                    <td rowspan="2" align="center" style="vertical-align: middle" width="30%">Pelaksanaan Edukasi</td>
                                    <td colspan="3" align="center" style="vertical-align: middle">Pemahaman Awal</td>
                                    <td colspan="4" align="center" style="vertical-align: middle">Metode Edukasi</td>
                                    <td colspan="4" align="center" style="vertical-align: middle">Media Edukasi</td>
                                    <td colspan="3" align="center" style="vertical-align: middle">Evaluasi/Verifikasi</td>
                                </tr>
                                <tr style="font-weight: bold">
                                    <td align="center" title="3 menit">3</td>
                                    <td align="center">5</td>
                                    <td align="center">10</td>
                                    <td align="center">B</td>
                                    <td align="center">C</td>
                                    <td align="center">K</td>
                                    <td align="center">1</td>
                                    <td align="center">2</td>
                                    <td align="center">3</td>
                                    <td align="center">4</td>
                                    <td align="center">1</td>
                                    <td align="center">2</td>
                                    <td align="center">3</td>
                                    <td align="center">4</td>
                                    <td align="center">1</td>
                                    <td align="center">2</td>
                                    <td align="center">3</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="dw11" name="dw1" /><label for="dw11"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="5" id="dw12" name="dw1" /><label for="dw12"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="10" id="dw13" name="dw1" /><label for="dw13"></label>
                                        </div>
                                    </td>
                                    <td>Hak dan Kewajiban Pasien dan Keluarga</td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="B" id="pa11" name="pa1" /><label for="pa11"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="C" id="pa12" name="pa1" /><label for="pa12"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="K" id="pa13" name="pa1" /><label for="pa13"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="me11" name="me1" /><label for="me11"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="me12" name="me1" /><label for="me12"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="me13" name="me1" /><label for="me13"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="4" id="me14" name="me1" /><label for="me14"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="med11" name="med1" /><label for="med11"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="med12" name="med1" /><label for="med12"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="med13" name="med1" /><label for="med13"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="4" id="med14" name="med1" /><label for="med14"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="ev11" name="ev1" /><label for="ev11"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="ev12" name="ev1" /><label for="ev12"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="ev13" name="ev1" /><label for="ev13"></label>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="dw21" name="dw2" /><label for="dw21"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="5" id="dw22" name="dw2" /><label for="dw22"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="10" id="dw23" name="dw2" /><label for="dw23"></label>
                                        </div>
                                    </td>
                                    <td>Peraturan dan Tatterbit RS</td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="B" id="pa21" name="pa2" /><label for="pa21"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="C" id="pa22" name="pa2" /><label for="pa22"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="K" id="pa23" name="pa2" /><label for="pa23"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="me1" name="me" /><label for="me1"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="me2" name="me" /><label for="me2"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="me3" name="me" /><label for="me3"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="4" id="me4" name="me" /><label for="me4"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="med21" name="med2" /><label for="med21"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="med22" name="med2" /><label for="med22"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="med23" name="med2" /><label for="med23"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="4" id="med24" name="med2" /><label for="med24"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="ev21" name="ev2" /><label for="ev21"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="ev22" name="ev2" /><label for="ev22"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="ev23" name="ev2" /><label for="ev23"></label>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="dw31" name="dw3" /><label for="dw31"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="5" id="dw32" name="dw3" /><label for="dw32"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="10" id="dw33" name="dw3" /><label for="dw33"></label>
                                        </div>
                                    </td>
                                    <td>Fasilitas kamar, jam kunjungan dan visite Pasien</td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="B" id="pa31" name="pa3" /><label for="pa31"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="C" id="pa32" name="pa3" /><label for="pa32"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="K" id="pa33" name="pa3" /><label for="pa33"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="me31" name="me3" /><label for="me31"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="me32" name="me3" /><label for="me32"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="me33" name="me3" /><label for="me33"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="4" id="me34" name="me3" /><label for="me34"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="med31" name="med3" /><label for="med31"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="med32" name="med3" /><label for="med32"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="med33" name="med3" /><label for="med33"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="4" id="med34" name="med3" /><label for="med34"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="1" id="ev31" name="ev3" /><label for="ev31"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="2" id="ev32" name="ev3" /><label for="ev32"></label>
                                        </div>
                                    </td>
                                    <td align="center" style="vertical-align: middle">
                                        <div class="custom03">
                                            <input type="radio" value="3" id="ev33" name="ev3" /><label for="ev33"></label>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1">Tanggal</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label >TTD</label>
                                <canvas class="paint-canvas-ttd" id="cpo9" width="220"
                                        onmouseover="paintTtd('cpo9')"></canvas>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="col-md-12">
                            <label>Keterangan</label>
                        </div>
                        <div class="col-md-3">
                            <ul>
                                <li>Tingkat Pemahanan</li>
                                <li>Metode Edukasi</li>
                                <li>Medis Edukasi</li>
                                <li>Evaluasi/Verifikasi</li>
                            </ul>
                        </div>
                        <div class="col-md-8">
                            <ul style="list-style-type: none">
                                <li>Baik (B), Cukup (C), Kurang (K)</li>
                                <li>Wawancara (1), Diskusi (2), Ceramah (3), Demonstrasi (4)</li>
                                <li>Lisan (1), Leaflet (2), Brosur (3), Lembar Balik (4)</li>
                                <li>Mengerti (1), Re Edukasi (2), Re Demontrasi (3)</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ina_ept_tppri" class="btn btn-success pull-right"
                        onclick="saveAsesmenRawatInap('ept_tppri','edukasi_pasien_terintegrasi')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_ept_tppri" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>