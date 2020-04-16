<div class="modal fade" id="modal-op-ceklist_operasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-check"></i> Checklist Serah Terima Pasien Pre Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_ceklist_operasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_ceklist_operasi"></p>
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
                            <li><a onclick="showModalOperasi('pre_operasi')" href="#"><i class="fa fa-plus"></i> Keluhan Utama</a></li>
                            <li><a onclick="showModalOperasi('kondisi_pasien')" href="#"><i class="fa fa-plus"></i> Data Pre Hospital</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_ceklist_operasi">
                        <tbody>
                        <tr id="row_op_pre_operasi">
                            <td>Persiapan Pasien Pre Operasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_pre_operasi" class="hvr-grow" onclick="detailOperasi('pre_operasi')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_kondisi_pasien">
                            <td>Kondisi Pasien Saat Serah Terima</td>
                            <td width="20%" align="center">
                                <img id="btn_op_kondisi_pasien" class="hvr-grow" onclick="detailOperasi('kondisi_pasien')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
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

<div class="modal fade" id="modal-op-pre_operasi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-check"></i> Persiapan Pasien Pre Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_pre_operasi">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_op_pre_operasi"></p>
                    </div>
                    <table class="table">
                        <thead>
                        <th>Persiapan Pasien Pre Operasi</th>
                        <th width="20%">Perawat Pengirim</th>
                        <th width="15%">Perawat OK</th>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Pembatasan Nutrisi Per Oral(Puasa)</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list1" id="cek_list11" value="Ya">
                                    <label for="cek_list11"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list11" id="cek_list12" value="Ya">
                                    <label for="cek_list12"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Pemeriksaan Laboratorium</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list2" id="cek_list21" value="Ya">
                                    <label for="cek_list21"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list22" id="cek_list22" value="Ya">
                                    <label for="cek_list22"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Pemeriksaan Radiologi (Thorax Foto, USG, Scan)</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list3" id="cek_list31" value="Ya">
                                    <label for="cek_list31"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list33" id="cek_list32" value="Ya">
                                    <label for="cek_list32"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Pemeriksaan ECG</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list4" id="cek_list41" value="Ya">
                                    <label for="cek_list41"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list44" id="cek_list42" value="Ya">
                                    <label for="cek_list42"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Edukasi dan Informed Consent Bedah</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list5" id="cek_list51" value="Ya">
                                    <label for="cek_list51"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list55" id="cek_list52" value="Ya">
                                    <label for="cek_list52"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Penandaan Area Operasi</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list6" id="cek_list61" value="Ya">
                                    <label for="cek_list61"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list66" id="cek_list62" value="Ya">
                                    <label for="cek_list62"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Konsultasi Dokter Spesialis Anestesi</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list7" id="cek_list71" value="Ya">
                                    <label for="cek_list71"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list77" id="cek_list72" value="Ya">
                                    <label for="cek_list72"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Edukasi dan Informed Consent Anestesi</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list8" id="cek_list81" value="Ya">
                                    <label for="cek_list81"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list88" id="cek_list82" value="Ya">
                                    <label for="cek_list82"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Konsultasi Dokter Kardiologi ACC Operasi dengan Resiko: Ringan/Sedang/berat Cardiac Risk Index Grade I/II/III/IV</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list9" id="cek_list91" value="Ya">
                                    <label for="cek_list91"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list99" id="cek_list92" value="Ya">
                                    <label for="cek_list92"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Konsultasi Dokter Spesialis Penyakit Dalam</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list10" id="cek_list101" value="Ya">
                                    <label for="cek_list101"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list1010" id="cek_list102" value="Ya">
                                    <label for="cek_list102"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Schiren / Cukur</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list11" id="cek_list111" value="Ya">
                                    <label for="cek_list111"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list1111" id="cek_list112" value="Ya">
                                    <label for="cek_list112"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Melepas Perhiasan, Soft Lens, Gigi Palsu, DLL</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list12" id="cek_list121" value="Ya">
                                    <label for="cek_list121"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list1212" id="cek_list122" value="Ya">
                                    <label for="cek_list122"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Persiapan Prosuk Darah</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list1313" id="cek_list131" value="Ya">
                                    <label for="cek_list131"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list13" id="cek_list132" value="Ya">
                                    <label for="cek_list132"></label>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">Riwayat Penyakit</label>
                                <div class="col-md-2">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Tidak" id="cek_list141" name="cek_list14" /><label for="cek_list141">Tidak</label>
                                    </div>
                                </div>
                                <div class="col-md-7">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input type="radio" value="Ya" id="cek_list142" name="cek_list14" /><label for="cek_list142">Ya</label>
                                    </div>
                                </div>
                            </div>
                            <div id="form-ket-checklist" style="display: none">
                                <div class="form-group">
                                    <div class="col-md-offset-3 col-md-3">
                                        <div class="form-check" style="margin-top: 7px;">
                                            <input type="checkbox" name="cek_ket_list14" id="cek_ket_list141" value="CVA">
                                            <label for="cek_ket_list141"></label> CVA
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check" style="margin-top: 7px;">
                                            <input type="checkbox" name="cek_ket_list14" id="cek_ket_list142" value="Diabetes">
                                            <label for="cek_ket_list142"></label> Diabetes
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-check" style="margin-top: 7px;">
                                            <input type="checkbox" name="cek_ket_list14" id="cek_ket_list143" value="Hypertensi">
                                            <label for="cek_ket_list143"></label> Hypertensi
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-3 col-md-3">
                                        <div class="form-check" style="margin-top: 7px;">
                                            <input type="checkbox" name="cek_ket_list14" id="cek_ket_list144" value="Jantung">
                                            <label for="cek_ket_list144"></label> Jantung
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-check" style="margin-top: 7px;">
                                            <input type="checkbox" name="cek_ket_list14" id="cek_ket_list145" value="Lainnya">
                                            <label for="cek_ket_list145"></label> Lainnya
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 7px">Berat Badan</label>
                                <div class="col-md-3">
                                    <div class="input-group" style="margin-top: 7px">
                                        <input class="form-control" id="cek_list15" type="number">
                                        <div class="input-group-addon">
                                            Kg
                                        </div>
                                    </div>
                                </div>
                                <label class="col-md-3" style="margin-top: 7px">Tinggi Badan</label>
                                <div class="col-md-3">
                                    <div class="input-group" style="margin-top: 7px">
                                        <input class="form-control" id="cek_list16" type="number">
                                        <div class="input-group-addon">
                                            cm
                                        </div>
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
                <button id="save_op_pre_operasi" class="btn btn-success pull-right" onclick="saveDataOperasi('pre_operasi','ceklist_operasi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_pre_operasi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-kondisi_pasien">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-check"></i> Kondisi Pasien Saat Serah Terima
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_kondisi_pasien">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_kondisi_pasien"></p>
                </div>
                   <div class="row">
                       <div class="box-body">
                           <div class="form-group">
                               <label class="col-md-4"><b>Pemeriksaan</b></label>
                               <label class="col-md-4"><b>Sebelum di Transfer</b></label>
                               <label class="col-md-4"><b>Saat di terima</b></label>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Kesadaran Umum</label>
                               <div class="col-md-4">
                                   <select class="form-control select2" style="width: 100%" id="cek_list171">
                                       <option value="">[Select One]</option>
                                       <option value="Baik">Baik</option>
                                       <option value="Cukup">Cukup</option>
                                       <option value="Lemah">Lemah</option>
                                       <option value="Jelek">Jelek</option>
                                   </select>
                               </div>
                               <div class="col-md-4">
                                   <select class="form-control select2" style="width: 100%" id="cek_list172">
                                       <option value="">[Select One]</option>
                                       <option value="Baik">Baik</option>
                                       <option value="Cukup">Cukup</option>
                                       <option value="Lemah">Lemah</option>
                                       <option value="Jelek">Jelek</option>
                                   </select>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Kesadaran / GCS</label>
                               <div class="col-md-4">
                                   <input class="form-control" style="margin-top: 7px" id="cek_list181">
                               </div>
                               <div class="col-md-4">
                                   <input class="form-control" style="margin-top: 7px" id="cek_list182">
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Tekanan Darah</label>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list191">
                                       <div class="input-group-addon">
                                           mmHg
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list192">
                                       <div class="input-group-addon">
                                           mmHg
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Suhu</label>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list201">
                                       <div class="input-group-addon">
                                           C
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list202">
                                       <div class="input-group-addon">
                                           C
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Nadi</label>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list211">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list212">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Respirator</label>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list221">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list222">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">DJJ</label>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list231">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list232">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Skala Nyeri</label>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list241">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list242">
                                       <div class="input-group-addon">
                                           x/menit
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
                <button id="save_op_kondisi_pasien" class="btn btn-success pull-right" onclick="saveDataOperasi('kondisi_pasien','ceklist_operasi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_kondisi_pasien" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-penandaan_area">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Penandaan Area Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_penandaan_area">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_penandaan_area"></p>
                    </div>
                    <button class="btn btn-success" onclick="penandaAreaOperasi()"><i class="fa fa-plus"></i> Penanda Area Operasi</button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_penandaan_area">
                        <tbody>
                        <tr id="row_op_penandaan_area">
                            <td>Hasil Penandaan Area Operasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_penandaan_area" class="hvr-grow" onclick="detailOperasi('penandaan_area')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
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

<div class="modal fade" id="modal-op-ttd-penandaan-mlt">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Penanda Area Operasi <span id="jk_pasien"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_penanda_area">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_penanda_area"></p>
                </div>
                <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                    <div class="col-md-1">
                        <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker  color-picker pull-left">
                    </div>
                    <div class="col-md-9">
                        <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
                    </div>
                    <div class="col-md-2">
                        <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                    </div>
                </div>
                <div class="text-center">
                    <canvas class="js-paint  paint-canvas" id="area_canvas"></canvas>
                </div>
                <canvas style="display: none" id="area_cek"></canvas>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" onclick="clearConvas('area_canvas')"><i class="fa fa-trash"></i> Clear
                </button>
                <button class="btn btn-success pull-right" onclick="confirmSavePenanda()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-ttd-penandaan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-pencil"></i> Tanda Tangan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_ttd-penandaan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_ttd-penandaan"></p>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-7">
                            <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                                <div class="col-md-1">
                                    <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker  color-picker pull-left">
                                </div>
                                <div class="col-md-9">
                                    <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
                                </div>
                                <div class="col-md-2">
                                    <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-12">
                        <div class="col-md-6">
                            <b style="margin-left: 8px">Tanda Tangan Pasien</b>
                            <canvas class="js-paint-1 paint-canvas-ttd" id="op_ttd_pasien" width="380" height="300"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('op_ttd_pasien')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <b style="margin-left: 8px">Tanda Tangan Dokter</b>
                            <canvas class="js-paint-2 paint-canvas-ttd" id="op_ttd_dokter" width="380" height="300"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('op_ttd_dokter')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_ttd-penandaan" class="btn btn-success pull-right" onclick="saveDataOperasi('ttd-penandaan','penandaan_area')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_ttd-penandaan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-pra_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Asesmen Pra Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_pra_anestesi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_pra_anestesi"></p>
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
                            <li><a onclick="showModalOperasi('anamnesa')" href="#"><i class="fa fa-plus"></i> Anamnesa</a></li>
                            <li><a onclick="showModalOperasi('pemeriksaan_fisik')" href="#"><i class="fa fa-plus"></i> Pemeriksaan Fisik</a></li>
                            <li><a onclick="showModalOperasi('pemeriksaan_penunjang')" href="#"><i class="fa fa-plus"></i> Pemeriksaan Penunjang</a></li>
                            <li><a onclick="showModalOperasi('status_fisik')" href="#"><i class="fa fa-plus"></i> Status Fisik</a></li>
                            <li><a onclick="showModalOperasi('persiapan_anestesi')" href="#"><i class="fa fa-plus"></i> Persiapan Anestesi</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_pra_anestesi">
                        <tbody>
                        <tr id="row_op_anamnesa">
                            <td>Anamnesa</td>
                            <td width="20%" align="center">
                                <img id="btn_op_anamnesa" class="hvr-grow" onclick="detailOperasi('anamnesa')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_pemeriksaan_fisik">
                            <td>Pemeriksaan Fisik</td>
                            <td width="20%" align="center">
                                <img id="btn_op_pemeriksaan_fisik" class="hvr-grow" onclick="detailOperasi('pemeriksaan_fisik')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_pemeriksaan_penunjang">
                            <td>Pemeriksaan Penunjang</td>
                            <td width="20%" align="center">
                                <img id="btn_op_pemeriksaan_penunjang" class="hvr-grow" onclick="detailOperasi('pemeriksaan_penunjang')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_status_fisik">
                            <td>Status Fisik</td>
                            <td width="20%" align="center">
                                <img id="btn_op_status_fisik" class="hvr-grow" onclick="detailOperasi('status_fisik')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_persiapan_anestesi">
                            <td>Persiapan Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_persiapan_anestesi" class="hvr-grow" onclick="detailOperasi('persiapan_anestesi')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
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

<div class="modal fade" id="modal-op-anamnesa">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Anamnesa
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_anamnesa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_anamnesa"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alergi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="cek_alergi11" name="cek_anamnesa1" /><label for="cek_alergi11">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="cek_alergi12" name="cek_anamnesa1" /><label for="cek_alergi12">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="form-ket-alergi">
                            <div class="col-md-offset-4 col-md-6">
                                <textarea class="form-control" style="margin-top: 7px" id="cek_ket_anamnesa2"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat Asthma</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="cek_anamnesa21" name="cek_anamnesa2" /><label for="cek_anamnesa21">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="cek_anamnesa22" name="cek_anamnesa2" /><label for="cek_anamnesa22">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat DM</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="cek_anamnesa31" name="cek_anamnesa3" /><label for="cek_anamnesa31">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="cek_anamnesa32" name="cek_anamnesa3" /><label for="cek_anamnesa32">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat Hipertensi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="anamnesa41" name="cek_anamnesa4" /><label for="anamnesa41">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="anamnesa42" name="cek_anamnesa4" /><label for="anamnesa42">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Riwayat Penyakit Lain</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="cek_anamnesa5" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_anamnesa" class="btn btn-success pull-right" onclick="saveDataOperasi('anamnesa','pra_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_anamnesa" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-pemeriksaan_fisik">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan Fisik
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_pemeriksaan_fisik">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_pemeriksaan_fisik"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Keadaan Umum</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Baik" id="cek_pf11" name="cek_pf1" /><label for="cek_pf11">Baik</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Cukup" id="cek_pf12" name="cek_pf1" /><label for="cek_pf12">Cukup</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Jelek" id="cek_pf13" name="cek_pf1" /><label for="cek_pf13">Jelek</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Anemis" id="cek_pf14" name="cek_pf1" /><label for="cek_pf14">Anemis</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sianosis" id="cek_pf15" name="cek_pf1" /><label for="cek_pf15">Sianosis</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sesak" id="cek_pf16" name="cek_pf1" /><label for="cek_pf16">Sesak</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Obesitas" id="cek_pf17" name="cek_pf1" /><label for="cek_pf17">Obesitas</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nafas</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Spontan adekuat" id="cek_pf21" name="cek_pf2" /><label for="cek_pf21">Spontan adekuat</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sesak" id="cek_pf22" name="cek_pf2" /><label for="cek_pf22">Sesak</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Gigi Palsu</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak" id="cek_pf31" name="cek_pf3" /><label for="cek_pf31">Tidak</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ya" id="cek_pf32" name="cek_pf3" /><label for="cek_pf32">Ya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jalan Nafas</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Paten" id="cek_pf41" name="cek_pf4" /><label for="cek_pf41">Paten</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak Paten" id="cek_pf42" name="cek_pf4" /><label for="cek_pf42">Tidak Paten</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Suara Nafas</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Bersih" id="cek_pf51" name="cek_pf5" /><label for="cek_pf51">Bersih</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Wheezing" id="cek_pf52" name="cek_pf5" /><label for="cek_pf52">Wheezing</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ronchi" id="cek_pf53" name="cek_pf5" /><label for="cek_pf53">Ronchi</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Capillary Refill Time</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="< 3 detik" id="cek_pf61" name="cek_pf6" /><label for="cek_pf61">< 3 dtk</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="> 3 detik" id="cek_pf62" name="cek_pf6" /><label for="cek_pf62">> 3 dtk</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Perfusi</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="HKM" id="cek_pf71" name="cek_pf7" /><label for="cek_pf71">HKM</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Lainnya" id="cek_pf72" name="cek_pf7" /><label for="cek_pf72">Lainnya</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Kesadaran</label>
                            <div class="col-md-4">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Composmentis" id="cek_pf81" name="cek_pf8" /><label for="cek_pf81">Composmentis</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Apatis" id="cek_pf82" name="cek_pf8" /><label for="cek_pf82">Apatis</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Somnolen" id="cek_pf83" name="cek_pf8" /><label for="cek_pf83">Somnolen</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Delirium" id="cek_pf84" name="cek_pf8" /><label for="cek_pf84">Delirium</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Lainnya" id="cek_pf85" name="cek_pf8" /><label for="cek_pf85">Lainnya</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-4">
                                <label>Tekanan Darah</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="cek_pf9">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>Nadi</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="cek_pf10">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>Suhu</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="cek_pf111">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        C
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <label style="margin-top: 7px">Respirator Rate</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="cek_pf122">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label style="margin-top: 7px">Berat Badan</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="cek_pf133">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        kg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label style="margin-top: 7px">Tinggi Badan</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="cek_pf144">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        cm
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 10px">Kemampuan Buka Mulut</label>
                            <div class="col-md-6">
                                <input class="form-control" id="cek_pf155" style="margin-top: 10px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Skala Malampahty</label>
                            <div class="col-md-6">
                                <input class="form-control" id="cek_pf166" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Gerak Leher</label>
                            <div class="col-md-2">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Bebas" id="cek_pf1771" name="cek_pf177" /><label for="cek_pf1771">Bebas</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak Bebas" id="cek_pf1772" name="cek_pf177" /><label for="cek_pf1772">Tidak Bebas</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_pemeriksaan_fisik" class="btn btn-success pull-right" onclick="saveDataOperasi('pemeriksaan_fisik','pra_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_pemeriksaan_fisik" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-pemeriksaan_penunjang">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemeriksaan Penunjang
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_pemeriksaan_penunjang">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_pemeriksaan_penunjang"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>Pemeriksaan Penunjang</label>
                                <textarea class="form-control" id="pp1" rows="5"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_pemeriksaan_penunjang" class="btn btn-success pull-right" onclick="saveDataOperasi('pemeriksaan_penunjang','pra_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_pemeriksaan_penunjang" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-status_fisik">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Status Fisik
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_status_fisik">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_status_fisik"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Status Fisik Anestesi</label>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Elektif" id="cek_st11" name="cek_st1" /><label for="cek_st11">Elektif</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Emergency" id="cek_st12" name="cek_st1" /><label for="cek_st12">Emergency</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">ASA</label>
                            <div class="col-md-1">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="1" id="cek_st21" name="cek_st2" /><label for="cek_st21">1</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="2" id="cek_st22" name="cek_st2" /><label for="cek_st22">2</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="3" id="cek_st23" name="cek_st2" /><label for="cek_st23">3</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="4" id="cek_st24" name="cek_st2" /><label for="cek_st24">4</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="5" id="cek_st25" name="cek_st2" /><label for="cek_st25">5</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <label style="margin-top: 7px">Teknik Anestesi</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st31" value="General Anesti">
                                    <label for="cek_st31"></label> General Anesti
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st32" value="Intubasi">
                                    <label for="cek_st32"></label> Intubasi
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st33" value="Facemask">
                                    <label for="cek_st33"></label> Facemask
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st34" value="LMA">
                                    <label for="cek_st34"></label> LMA
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st35" value="TIVA">
                                    <label for="cek_st35"></label> TIVA
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st36" value="Regional Anestesi">
                                    <label for="cek_st36"></label> Regional Anestesi
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st37" value="SAB">
                                    <label for="cek_st37"></label> SAB
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st38" value="Epidural">
                                    <label for="cek_st38"></label> Epidural
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_st3" id="cek_st39" value="Blok syaraf perifer">
                                    <label for="cek_st39"></label> Blok syaraf perifer
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_status_fisik" class="btn btn-success pull-right" onclick="saveDataOperasi('status_fisik','pra_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_status_fisik" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-persiapan_anestesi">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Persiapan Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_persiapan_anestesi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_persiapan_anestesi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Makan minum terakhir</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="pa1" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Infus</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="pa2" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Obat pre medikasi</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="pa3" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_persiapan_anestesi" class="btn btn-success pull-right" onclick="saveDataOperasi('persiapan_anestesi','pra_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_persiapan_anestesi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-general_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Edukasi Dan Persetujuan General Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_general_anestesi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_general_anestesi"></p>
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
                            <li><a onclick="showModalOperasi('general_informasi')" style="cursor: pointer"><i class="fa fa-plus"></i> Informasi</a></li>
                            <li><a onclick="showModalOperasi('general_penyataan')" style="cursor: pointer"><i class="fa fa-plus"></i> Pernyataan</a></li>
                            <li><a onclick="showModalOperasi('general_persetujuan')" style="cursor: pointer"><i class="fa fa-plus"></i> Persetujuan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_general_anestesi">
                        <tbody>
                        <tr id="row_op_general_informasi">
                            <td>Informasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_general_informasi" class="hvr-grow" onclick="detailOperasi('general_informasi')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_general_penyataan">
                            <td>Pernyataan</td>
                            <td width="20%" align="center">
                                <img id="btn_op_general_penyataan" class="hvr-grow" onclick="detailOperasi('general_penyataan')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_general_persetujuan">
                            <td>Persetujuan</td>
                            <td width="20%" align="center">
                                <img id="btn_op_general_persetujuan" class="hvr-grow" onclick="detailOperasi('general_persetujuan')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
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

<div class="modal fade" id="modal-op-general_informasi">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Informasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_general_informasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_general_informasi"></p>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <td><b>Jenis Informasi</b></td>
                        <td><b>Isi Informasi</b></td>
                        <td width="15%" align="center"><b>Check (<i class="fa fa-check"></i>)</b></td>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Teknik Anestesi</td>
                            <td>
                                <div class="row">
                                    <div class="form-check" style="margin-top: 7px; margin-left: 15px">
                                        <input type="checkbox" name="ga1" id="ga11" value="General Anestesi">
                                        <label for="ga11"></label> General Anestesi
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-check" style="margin-top: 7px; margin-left: 15px">
                                        <input type="checkbox" name="ga1" id="ga12" value="Sedasi Moderat">
                                        <label for="ga12"></label> Sedasi Moderat
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-check" style="margin-top: 7px; margin-left: 15px">
                                        <input type="checkbox" name="ga1" id="ga13" value="Sedasi Dalam">
                                        <label for="ga13"></label> Sedasi Dalam
                                    </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga2" id="ga21" value="Ya">
                                    <label for="ga21"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Kondisi yang diharapkan</td>
                            <td>Rasa Cemas berkurang, mengantuk s/d kesadaran hilang, tidak merasa nyeri</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga3" id="ga31" value="Ya">
                                    <label for="ga31"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tata cara</td>
                            <td>Obat yang diinjeksikan ke pembuluh darah, obat dihirupkan keseluruh nafas</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga4" id="ga41" value="Ya">
                                    <label for="ga41"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Resiko</td>
                            <td>Aspirasi, udema pasru, cedera mulut (gigi, lidah, bibir), suara serak dan nteri tenggorokan</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga5" id="ga51" value="Ya">
                                    <label for="ga51"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tujuan</td>
                            <td>Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga6" id="ga61" value="Ya">
                                    <label for="ga61"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Komplikasi</td>
                            <td>Reaksi alergi obat, stroke, serangan jantung kematian</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga7" id="ga71" value="Ya">
                                    <label for="ga71"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tranfusi</td>
                            <td>
                                <div class="row">
                                        <div class="col-md-2">
                                            <div class="custom02" style="margin-top: 7px">
                                                <input type="radio" value="Tidak" id="ga81" name="ga8" /><label for="ga81">Tidak</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="custom02" style="margin-top: 7px">
                                                <input type="radio" value="Ya" id="ga82" name="ga8" /><label for="ga82">Ya</label>
                                            </div>
                                        </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga9" id="ga91" value="Ya">
                                    <label for="ga91"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Komplikasi</td>
                            <td>Reaksi tranfusi, penularan penyakit lewat darah</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga10" id="ga101" value="Ya">
                                    <label for="ga101"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Prognosa</td>
                            <td>
                                <div class="row">
                                    <input style="margin-left: 15px" class="form-control" id="ga10">
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga11" id="ga111" value="Ya">
                                    <label for="ga111"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Alternatif</td>
                            <td>
                                <div class="row">
                                    <input style="margin-left: 15px" class="form-control" id="ga122">
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="ga12" id="ga121" value="Ya">
                                    <label for="ga121"></label>
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
                <button id="save_op_general_informasi" class="btn btn-success pull-right" onclick="saveDataOperasi('general_informasi','general_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_general_informasi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-general_penyataan">
    <div class="modal-dialog modal-md" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pernyataan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_general_penyataan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_general_penyataan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatan bertanya dan atau berdiskusi</td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="general_penyataan1" onmouseover="paintTtd('general_penyataan1')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('general_penyataan1')"><i class="fa fa-trash"></i> Clear
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya</td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="general_penyataan2" onmouseover="paintTtd('general_penyataan2')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('general_penyataan2')"><i class="fa fa-trash"></i> Clear
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
                <button id="save_op_general_penyataan" class="btn btn-success pull-right" onclick="saveDataOperasi('general_penyataan','general_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_general_penyataan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-general_persetujuan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Persetujuan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_general_persetujuan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_general_persetujuan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama</label>
                            <div class="col-md-6">
                                <input class="form-control" id="perse1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control datepicker2 datemask2" id="perse2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="perse3" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-6">
                                <input class="form-control" id="perse4" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                            <div class="col-md-6">
                                <input class="form-control" id="perse5" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control datepicker2 datemask2" id="perse6">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="perse7" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="perse8" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_general_persetujuan" class="btn btn-success pull-right" onclick="saveDataOperasi('general_persetujuan','general_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_general_persetujuan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-regional_anestesi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Edukasi Dan Persetujuan Regional Anestesi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_regional_anestesi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_regional_anestesi"></p>
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
                            <li><a onclick="showModalOperasi('regional_informasi')" style="cursor: pointer"><i class="fa fa-plus"></i> Informasi</a></li>
                            <li><a onclick="showModalOperasi('regional_penyataan')" style="cursor: pointer"><i class="fa fa-plus"></i> Pernyataan</a></li>
                            <li><a onclick="showModalOperasi('regional_persetujuan')" style="cursor: pointer"><i class="fa fa-plus"></i> Persetujuan</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_regional_anestesi">
                        <tbody>
                        <tr id="row_op_regional_informasi">
                            <td>Informasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_regional_informasi" class="hvr-grow" onclick="detailOperasi('regional_informasi')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_regional_penyataan">
                            <td>Pernyataan</td>
                            <td width="20%" align="center">
                                <img id="btn_op_regional_penyataan" class="hvr-grow" onclick="detailOperasi('regional_penyataan')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
                            </td>
                        </tr>
                        <tr id="row_op_regional_persetujuan">
                            <td>Persetujuan</td>
                            <td width="20%" align="center">
                                <img id="btn_op_regional_persetujuan" class="hvr-grow" onclick="detailOperasi('regional_persetujuan')" src="<s:url value="/pages/images/icons8-plus-25.png"/>">
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

<div class="modal fade" id="modal-op-regional_informasi">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Informasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_regional_informasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_regional_informasi"></p>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <td><b>Jenis Informasi</b></td>
                        <td><b>Isi Informasi</b></td>
                        <td width="15%" align="center"><b>Check (<i class="fa fa-check"></i>)</b></td>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Teknik Anestesi</td>
                            <td>
                                <div class="row">
                                    <div class="form-check" style="margin-top: 7px; margin-left: 15px">
                                        <input type="checkbox" name="reg1" id="reg11" value="Sub Arachnoid Blok (SAB)">
                                        <label for="reg11"></label> Sub Arachnoid Blok (SAB)
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-check" style="margin-top: 7px; margin-left: 15px">
                                        <input type="checkbox" name="reg1" id="reg12" value="Epidural">
                                        <label for="reg12"></label> Epidural
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-check" style="margin-top: 7px; margin-left: 15px">
                                        <input type="checkbox" name="reg1" id="reg13" value="CSE/Block Syarat Mayor">
                                        <label for="reg13"></label> CSE/Block Syarat Mayor
                                    </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg2" id="reg21" value="Ya">
                                    <label for="reg21"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Kondisi yang diharapkan</td>
                            <td>Akan terjadi mati rasa pada bagian tubuh tertentu</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg3" id="reg31" value="Ya">
                                    <label for="reg31"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tata cara</td>
                            <td>Obat diinjeksikan pada kanal tulang belakang posisi pasien tidur miring atau duduk</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg4" id="reg41" value="Ya">
                                    <label for="reg41"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Resiko</td>
                            <td>Sakit kepala, sakit panggung, infeksi ditempat injeksi total spinal</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg5" id="reg51" value="Ya">
                                    <label for="reg51"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tujuan</td>
                            <td>Mengurangi rasa cemas, menghilangkan nyeri menjaga fungsi organ selama pembedahan</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg6" id="reg61" value="Ya">
                                    <label for="reg61"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Komplikasi</td>
                            <td>Reaksi alergi obat, stroke, serangan jantung kematian</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg7" id="reg71" value="Ya">
                                    <label for="reg71"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Tranfusi</td>
                            <td>
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="custom02" style="margin-top: 7px">
                                            <input type="radio" value="Tidak" id="reg81" name="reg8" /><label for="reg81">Tidak</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="custom02" style="margin-top: 7px">
                                            <input type="radio" value="Ya" id="reg82" name="reg8" /><label for="reg82">Ya</label>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg9" id="reg91" value="Ya">
                                    <label for="reg91"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Komplikasi</td>
                            <td>Reaksi tranfusi, penularan penyakit lewat darah</td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg10" id="reg101" value="Ya">
                                    <label for="reg101"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Prognosa</td>
                            <td>
                                <div class="row">
                                    <input style="margin-left: 15px" class="form-control" id="reg10">
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg11" id="reg111" value="Ya">
                                    <label for="reg111"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Alternatif</td>
                            <td>
                                <div class="row">
                                    <input style="margin-left: 15px" class="form-control" id="reg122">
                                </div>
                            </td>
                            <td align="center">
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="reg12" id="reg121" value="Ya">
                                    <label for="reg121"></label>
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
                <button id="save_op_regional_informasi" class="btn btn-success pull-right" onclick="saveDataOperasi('regional_informasi','regional_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_regional_informasi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-regional_penyataan">
    <div class="modal-dialog modal-md" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pernyataan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_regional_penyataan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_regional_penyataan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatan bertanya dan atau berdiskusi</td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="regional_penyataan1" onmouseover="paintTtd('regional_penyataan1')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('regional_penyataan1')"><i class="fa fa-trash"></i> Clear
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya</td>
                                <td>
                                    <canvas class="paint-canvas-ttd" id="regional_penyataan2" onmouseover="paintTtd('regional_penyataan2')"></canvas>
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('regional_penyataan2')"><i class="fa fa-trash"></i> Clear
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
                <button id="save_op_regional_penyataan" class="btn btn-success pull-right" onclick="saveDataOperasi('regional_penyataan','regional_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_regional_penyataan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-regional_persetujuan">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Persetujuan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_regional_persetujuan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_regional_persetujuan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Nama</label>
                            <div class="col-md-6">
                                <input class="form-control" id="reg_perse1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control datepicker2 datemask2" id="reg_perse2">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="reg_perse3" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tindakan</label>
                            <div class="col-md-6">
                                <input class="form-control" id="reg_perse4" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                            <div class="col-md-6">
                                <input class="form-control" id="reg_perse5" style="margin-top: 7px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control datepicker2 datemask2" id="reg_perse6">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                            <div class="col-md-6">
                                <select class="form-control" id="reg_perse7" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                            <div class="col-md-6">
                                <textarea class="form-control" id="reg_perse8" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_regional_persetujuan" class="btn btn-success pull-right" onclick="saveDataOperasi('regional_persetujuan','regional_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_regional_persetujuan" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
