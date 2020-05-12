<div class="modal fade" id="modal-ina-asuhan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Rencana Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none"
                         id="warning_ina_asuhan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_ina_asuhan"></p>
                    </div>
                    <div class="btn-group">
                        <button type="button" onclick="showModalAsesmenRawatInap('asuhan_keperawatan')" class="btn btn-success"><i class="fa fa-plus"></i> Asuhan Keperawatan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table style="font-size: 12px" class="table table-striped table-bordered" id="tabel_ina_asuhan">
                        <thead>
                        <tr>
                            <td rowspan="2" width="15%" style="vertical-align: middle" align="center">Tanggal Jam</td>
                            <td rowspan="2" width="25%" style="vertical-align: middle" align="center">Diagnosa Keperawatan/Masalah Kolaboratif</td>
                            <td colspan="2" width="50%" align="center">Rencana Keperawatan</td>
                            <td rowspan="3" width="10%" style="vertical-align: middle" align="center">Paraf</td>
                        </tr>
                        <tr>

                            <td width="25%" align="center">Tujuan dan Kriteria Hasil</td>
                            <td width="25%" align="center">Intervensi</td>
                        </tr>
                        </thead>
                        <tbody id="body_asuhan">

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

<div class="modal fade" id="modal-ina-asuhan_keperawatan">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Rencana Asuhan Keperawatan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_ina_asuhan_keperawatan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ina_asuhan_keperawatan"></p>
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
                                    <input class="form-control tgl" id="ake1">
                                </div>
                            </div>
                            <label class="col-md-1">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="ake2">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><b>Diagnosa Keperawatan / Masalah Kolaboratif</b></label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Resiko/Gangguan integritas kulit berhubungan</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">a. Eksternal</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="ake3" id="ake31" value="Substansi">
                                    <label for="ake31"></label> Substansi
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="ake3" id="ake32" value="Usia rawan">
                                    <label for="ake32"></label> Usia rawan
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="ake3" id="ake33" value="Kelembahan">
                                    <label for="ake33"></label> Kelembahan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="font-size: 12px">
                                    <input type="checkbox" name="ake3" id="ake34" value="Hipertermia/hipotermia">
                                    <label for="ake34"></label> Hipertermia/hipotermia
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="ake3" id="ake35" value="Faktor">
                                    <label for="ake35"></label> Faktor
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="ake3" id="ake36" value="Pengobatan">
                                    <label for="ake36"></label> Pengobatan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check">
                                    <input type="checkbox" name="ake3" id="ake37" value="Immobilisasi">
                                    <label for="ake37"></label> Immobilisasi fisik
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="form-check">
                                    <input type="checkbox" name="ake3" id="ake38" value="Radiasi">
                                    <label for="ake38"></label> Radiasi
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <label class="col-md-12">b. Internal</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake4" id="ake41" value="Perubahan status cairan/Pigmentasi/turgor">
                                    <label for="ake41"></label> Perubahan status cairan/Pigmentasi/turgor
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake4" id="ake42" value="Faktor perkembangan">
                                    <label for="ake42"></label> Faktor perkembangan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake4" id="ake43" value="Tingkat ketindak seimbangan nutrisi">
                                    <label for="ake43"></label> Tingkat ketindak seimbangan nutrisi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake4" id="ake44" value="Deficit imunologi">
                                    <label for="ake44"></label> Deficit imunologi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake4" id="ake45" value="Gangguan sirkulasi/metabolic/sensasi">
                                    <label for="ake45"></label> Gangguan sirkulasi/metabolic/sensasi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake4" id="ake46" value="Penonjolan">
                                    <label for="ake46"></label> Penonjolan
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <label class="col-md-12">DS</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake5" id="ake51" value="Mengunkapkan adanya gangguan kulit/kemerahan">
                                    <label for="ake51"></label> Mengunkapkan adanya gangguan kulit/kemerahan
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group">
                            <label class="col-md-12">DO</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="ake6" id="ake61" value="Kerusakan lapisan kulit">
                                    <label for="ake61"></label> Kerusakan lapisan kulit
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="ake6" id="ake62" value="Invasi">
                                    <label for="ake62"></label> Invasi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="ake6" id="ake63" value="Gangguan Permukaan Kulit">
                                    <label for="ake63"></label> Gangguan Permukaan Kulit
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="ake6" id="ake64" value="Bed rest">
                                    <label for="ake64"></label> Bed rest
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><b>Tujuan dan Kriterian Hasil</b></label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">NOC</label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-6" style="margin-top: 7px">Setelah dilakukan tindakan keperawatan</label>
                            <div class="col-md-4">
                                <div class="input-group" style="margin-left: -20px">
                                    <input type="number" class="form-control" id="ake7" >
                                    <div class="input-group-addon">
                                        x
                                    </div>
                                    <input type="number" class="form-control" id="ake8" >
                                </div>
                            </div>
                            <label class="col-md-2" style="margin-top: 7px; margin-left: -20px">menit/jam</label>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group" >
                            <label class="col-md-12">Kriteria</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake91" value="Respon alergi terlokalisir">
                                    <label for="ake91"></label> Respon alergi terlokalisir
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake92" value="Jaringan integrasi: membran mukosa dan kulit">
                                    <label for="ake92"></label> Jaringan integrasi: membran mukosa dan kulit
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake93" value="Penyembahan luka: primer dan sekunder normal">
                                    <label for="ake93"></label> Penyembahan luka: primer dan sekunder normal
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake94" value="Status sirkulasi normal">
                                    <label for="ake94"></label> Status sirkulasi normal
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake95" value="Keseimbangan cairan baik">
                                    <label for="ake95"></label> Keseimbangan cairan baik
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake96" value="Infeksi akut terjadi">
                                    <label for="ake96"></label> Infeksi akut terjadi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake97" value="Pengatahuan: infeksi manajemen baik">
                                    <label for="ake97"></label> Pengatahuan: infeksi manajemen baik
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake98" value="Status saraf parifer baik">
                                    <label for="ake98"></label> Status saraf parifer baik
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake99" value="Faktor resiko: hipertermia, hipotermia, proses infeksi, paparan matahari terkontrol">
                                    <label for="ake99"></label> Faktor resiko: hipertermia, hipotermia, proses infeksi, paparan matahari terkontrol
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake910" value="Perawatan diri: mandi dan hygienen">
                                    <label for="ake910"></label> Perawatan diri: mandi dan hygienen
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake911" value="Termoregulasi tubuh normal">
                                    <label for="ake911"></label> Termoregulasi tubuh normal
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake9" id="ake912" value="Perfusi jaringan seluler dan perifer baik">
                                    <label for="ake912"></label> Perfusi jaringan seluler dan perifer baik
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><b>Intervensi</b></label>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">NIC</label>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group" >
                            <label class="col-md-12">Mandiri</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake101" value="Kaji integrasi kulit klien">
                                    <label for="ake101"></label> Kaji integrasi kulit klien
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-5">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake102">
                                    <label for="ake102"></label> Monitor tanda tanda vital
                                </div>
                            </div>
                            <div class="col-md-2">
                                <input type="number" oninput="$('#ake102').val('Monitor tanda tanda vital '+this.value+' menit/jam')" class="form-control" id="ket_ake10" style="margin-left: -30px">
                            </div>
                            <label class="col-md-5" style="margin-left: -50px">menit/jam</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake103" value="Jaga personel hygienen klien">
                                    <label for="ake103"></label> Jaga personel hygienen klien
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake104" value="Kurangi perdarahan, luka">
                                    <label for="ake104"></label> Kurangi perdarahan, luka
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake105" value="Monitor kelembaban kulit, sirkulasi">
                                    <label for="ake105"></label> Monitor kelembaban kulit, sirkulasi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake106" value="Monitor kadar elektrolit">
                                    <label for="ake106"></label> Monitor kadar elektrolit
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check" style="font-size: 13px">
                                    <input type="checkbox" name="ake10" id="ake107" value="Berikan latihan ambulansi, keseimbangan, mobilisasi sendi, kontrol otot, peregangan dan stimulasi kutaneus">
                                    <label for="ake107"></label> Berikan latihan ambulansi, keseimbangan, mobilisasi sendi, kontrol otot, peregangan dan stimulasi kutaneus
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake108" value="Lakukan manajemen cairan dan elektrolit">
                                    <label for="ake108"></label> Lakukan manajemen cairan dan elektrolit
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake109" value="Perhatikan bahan latex">
                                    <label for="ake109"></label> Perhatikan bahan latex
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake1010" value="Monitor ekstremitas bawah, traksi">
                                    <label for="ake1010"></label> Monitor ekstremitas bawah, traksi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake1011" value="Berikan posisi yang nyaman">
                                    <label for="ake1011"></label> Berikan posisi yang nyaman
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake10" id="ake1012" value="Lakukan manajemen area pressure, perawatan luka">
                                    <label for="ake1012"></label> Lakukan manajemen area pressure, perawatan luka
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="form-group" >
                            <label class="col-md-12">Kolaborasi</label>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake11" id="ake111" value="Pemberian obat-obatan">
                                    <label for="ake111"></label> Pemberian obat-obatan
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake11" id="ake112" value="Monitor">
                                    <label for="ake112"></label> Monitor
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake11" id="ake113" value="Pemberian nutrisi adekut (enteral, perenteral)">
                                    <label for="ake113"></label> Pemberian nutrisi adekut (enteral, perenteral)
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="form-check">
                                    <input type="checkbox" name="ake11" id="ake114" value="Perawatan luka (debridement)">
                                    <label for="ake114"></label> Perawatan luka (debridement)
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Perawat</label>
                                <canvas class="paint-canvas-ttd" id="ake_ttd" width="220"
                                        onmouseover="paintTtd('ake_ttd')"></canvas>
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="clearConvas('ake_ttd')"><i
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
                <button id="save_ina_asuhan_keperawatan" class="btn btn-success pull-right"
                        onclick="saveAsuhanKeperawatan('asuhan_keperawatan','asuhan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ina_asuhan_keperawatan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>