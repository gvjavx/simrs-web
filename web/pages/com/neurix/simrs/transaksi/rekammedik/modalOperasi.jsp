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

<div class="modal fade" id="modal-penanda-area">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-pencil"></i> Penanda Area Operasi
                </h4>
            </div>
            <div class="modal-body">
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
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" onclick="clearConvas()"><i class="fa fa-pencil"></i> Clear
                </button>
                <button class="btn btn-success pull-right" onclick="saveResepObat()"><i class="fa fa-check"></i> Save</button>
            </div>
        </div>
    </div>
</div>