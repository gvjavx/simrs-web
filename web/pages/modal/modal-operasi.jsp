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
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_ceklist_operasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_ceklist_operasi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_ceklist_operasi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_ceklist_operasi"></p>
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
                            <li><a id="btn_pre_operasi" onclick="showModalOperasi('pre_operasi')" style="cursor: pointer;"><i class="fa fa-plus"></i> Pre Operasi</a></li>
                            <li><a id="btn_kondisi_pasien" onclick="showModalOperasi('kondisi_pasien')" style="cursor: pointer;"><i class="fa fa-plus"></i> Kondisi Pasien</a></li>
                        </ul>
                    </div>
                </div>
                <input type="hidden" id="h_cek">
                <div class="box-body">
                    <table class="table" id="tabel_op_ceklist_operasi">
                        <tbody>
                        <tr id="row_op_pre_operasi">
                            <td>Persiapan Pasien Pre Operasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_pre_operasi" class="hvr-grow" onclick="detailOperasi('pre_operasi')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pre_operasi" class="hvr-grow btn-hide" onclick="conOP('pre_operasi', 'ceklist_operasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_kondisi_pasien">
                            <td>Kondisi Pasien Saat Serah Terima</td>
                            <td width="20%" align="center">
                                <img id="btn_op_kondisi_pasien" class="hvr-grow" onclick="detailOperasi('kondisi_pasien')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_kondisi_pasien" class="hvr-grow btn-hide" onclick="conOP('kondisi_pasien', 'ceklist_operasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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
    <div class="modal-dialog" style="width: 50%">
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
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="success_pre_operasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_pre_operasi"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="tgl1">
                                </div>
                            </div>
                            <label class="col-md-2">Jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="jam1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Ruangan</label>
                            <div class="col-md-9">
                                <input class="form-control nama_ruangan" id="ruangan1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-9">
                                <input class="form-control diagnosa-pasien" id="diagnosa1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Tindakan Operasi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="tindakan1">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Dokter Operator</label>
                            <div class="col-md-9">
                                <input class="form-control nama_dokter_ri" id="operator1">
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <table class="table" style="font-size: 12px">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
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
                                <div class="form-check btn-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list1212" id="cek_list122" value="Ya">
                                    <label for="cek_list122"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Persiapan Produk Darah</td>
                            <td>
                                <div class="form-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list13" id="cek_list131" value="Ya">
                                    <label for="cek_list131"></label>
                                </div>
                            </td>
                            <td>
                                <div class="form-check btn-check" style="margin-top: 7px;">
                                    <input type="checkbox" name="cek_list1313" id="cek_list132" value="Ya">
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
                                        <input onclick="showKete(this.value, 'checklist')" type="radio" value="Tidak" id="cek_list141" name="cek_list14" /><label for="cek_list141">Tidak</label>
                                    </div>
                                </div>
                                <div class="col-md-7">
                                    <div class="custom02" style="margin-top: 7px">
                                        <input onclick="showKete(this.value, 'checklist')" type="radio" value="Ya" id="cek_list142" name="cek_list14" /><label for="cek_list142">Ya</label>
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
                                        <input class="form-control berat-pasien" id="cek_list15" type="number">
                                        <div class="input-group-addon">
                                            Kg
                                        </div>
                                    </div>
                                </div>
                                <label class="col-md-3" style="margin-top: 7px">Tinggi Badan</label>
                                <div class="col-md-3">
                                    <div class="input-group" style="margin-top: 7px">
                                        <input class="form-control tinggi-pasien" id="cek_list16" type="number">
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
                               <label class="col-md-4"><b>Saat perjalanan</b></label>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4">Kesadaran Umum</label>
                               <div class="col-md-4">
                                   <select onchange="setSideValue('cek_list172', this.value)" class="form-control" id="cek_list171">
                                       <option value="">[Select One]</option>
                                       <option value="Baik">Baik</option>
                                       <option value="Cukup">Cukup</option>
                                       <option value="Lemah">Lemah</option>
                                       <option value="Jelek">Jelek</option>
                                   </select>
                               </div>
                               <div class="col-md-4">
                                   <select class="form-control" id="cek_list172">
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
                                   <input oninput="setSideValue('cek_list182', this.value)" class="form-control" style="margin-top: 7px" id="cek_list181">
                               </div>
                               <div class="col-md-4">
                                   <input class="form-control" style="margin-top: 7px" id="cek_list182">
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Tekanan Darah</label>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input oninput="setSideValue('cek_list192', this.value)" class="form-control tensi-pasien" data-inputmask="'mask': ['999/999']" data-mask="" id="cek_list191">
                                       <div class="input-group-addon">
                                           mmHg
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control tensi-pasien" data-inputmask="'mask': ['999/999']" data-mask="" id="cek_list192">
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
                                       <input oninput="setSideValue('cek_list202', this.value)" class="form-control suhu-pasien" type="number" id="cek_list201">
                                       <div class="input-group-addon">
                                           C
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control suhu-pasien" type="number" id="cek_list202">
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
                                       <input oninput="setSideValue('cek_list212', this.value)" class="form-control nadi-pasien" type="number" id="cek_list211">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control nadi-pasien" type="number" id="cek_list212">
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
                                       <input oninput="setSideValue('cek_list222', this.value)" class="form-control rr-pasien" type="number" id="cek_list221">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control rr-pasien" type="number" id="cek_list222">
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
                                       <input oninput="setSideValue('cek_list232', this.value)" class="form-control" type="number" id="cek_list231" step="any">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                               <div class="col-md-4">
                                   <div class="input-group" style="margin-top: 7px">
                                       <input class="form-control" type="number" id="cek_list232" step="any">
                                       <div class="input-group-addon">
                                           x/menit
                                       </div>
                                   </div>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="col-md-4" style="margin-top: 7px">Skala Nyeri</label>
                               <div class="col-md-4">
                                   <input oninput="setSideValue('cek_list242', this.value)" class="form-control" type="number" id="cek_list241">
                               </div>
                               <div class="col-md-4">
                                   <input class="form-control" type="number" id="cek_list242">
                               </div>
                           </div>
                       </div>
                   </div>
                <div class="box-body">
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Perawat Pengirim</label>
                                <canvas class="paint-canvas-ttd" id="ttd_pengirim" width="220"
                                        onmouseover="paintTtd('ttd_pengirim')"></canvas>
                                <input class="form-control" id="nama_terang_pengirim" placeholder="Nama Terang">
                                <input class="form-control" id="sip_terang_pengirim" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_pengirim')"><i
                                        class="fa fa-trash"></i> Clear
                                </button>
                            </div>
                            <div class="col-md-6">
                                <label style="margin-left: 8px">TTD Perawat Operasi</label>
                                <canvas class="paint-canvas-ttd" id="ttd_perawat" width="220"
                                        onmouseover="paintTtd('ttd_perawat')"></canvas>
                                <input class="form-control nama_petugas" id="nama_terang_perawat" placeholder="Nama Terang">
                                <input class="form-control" id="sip_terang_perawat" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_perawat')"><i
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
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_penandaan_area">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_penandaan_area"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_penandaan_area">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_penandaan_area"></p>
                    </div>
                    <button class="btn btn-success" onclick="penandaAreaOperasi()"><i class="fa fa-plus"></i> Penanda Area Operasi</button>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_penandaan_area">
                        <tbody>
                        <tr id="row_op_penandaan_area">
                            <td>Hasil Penandaan Area Operasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_penandaan_area" class="hvr-grow" onclick="detailOperasi('penandaan_area')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_penandaan_area" class="hvr-grow btn-hide" onclick="conOP('penandaan_area', 'penandaan_area')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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
                        <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker-op color-picker pull-left" value="#ff0000">
                    </div>
                    <div style="display: none">
                        <div class="col-md-9">
                            <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
                        </div>
                        <div class="col-md-2">
                            <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <canvas class="paint-canvas" id="area_canvas" onmouseover="paintTtd('area_canvas', true)"></canvas>
                </div>
                <canvas style="display: none" id="area_cek"></canvas>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" onclick="removePaint('area_canvas')"><i class="fa fa-trash"></i> Clear
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
                <div class="row" style="display: none">
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
                            <canvas onmouseover="paintTtd('op_ttd_pasien')" class="paint-canvas-ttd" id="op_ttd_pasien" width="380" height="300"></canvas>
                            <input class="form-control nama-pasien" id="nama_terang_pasien" placeholder="Nama Terang">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="removePaint('op_ttd_pasien')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <b style="margin-left: 8px">Tanda Tangan Dokter</b>
                            <canvas onmouseover="paintTtd('op_ttd_dokter')" class="paint-canvas-ttd" id="op_ttd_dokter" width="380" height="300"></canvas>
                            <input class="form-control nama_dokter_ri" id="nama_terang_dokter" placeholder="Nama Terang">
                            <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_dokter" placeholder="SIP">
                            <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="removePaint('op_ttd_dokter')"><i class="fa fa-trash"></i> Clear
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
                <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_pra_anestesi">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_op_pra_anestesi"></p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_pra_anestesi">
                    <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                    <p id="msg_pra_anestesi"></p>
                </div>
                <div class="box-body btn-hide">
                    <div class="btn-group">
                        <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> Tambah
                        </button>
                        <button type="button" class="btn btn-success dropdown-toggle"
                                data-toggle="dropdown" style="height: 34px">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="showModalOperasi('anamnesa')" style="cursor: pointer;"><i class="fa fa-plus"></i> Anamnesa</a></li>
                            <li><a onclick="showModalOperasi('pemeriksaan_fisik')" style="cursor: pointer;"><i class="fa fa-plus"></i> Pemeriksaan Fisik</a></li>
                            <li><a onclick="showModalOperasi('pemeriksaan_penunjang')" style="cursor: pointer;"><i class="fa fa-plus"></i> Hasil Pemeriksaan Penunjang</a></li>
                            <li><a onclick="showModalOperasi('status_fisik')" style="cursor: pointer;"><i class="fa fa-plus"></i> Status Fisik</a></li>
                            <li><a onclick="showModalOperasi('persiapan_anestesi')" style="cursor: pointer;"><i class="fa fa-plus"></i> Persiapan Anestesi</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_pra_anestesi">
                        <tbody>
                        <tr id="row_op_anamnesa">
                            <td>Anamnesa</td>
                            <td width="20%" align="center">
                                <img id="btn_op_anamnesa" class="hvr-grow" onclick="detailOperasi('anamnesa')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_anamnesa" class="hvr-grow btn-hide" onclick="conOP('anamnesa', 'pra_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_pemeriksaan_fisik">
                            <td>Pemeriksaan Fisik</td>
                            <td width="20%" align="center">
                                <img id="btn_op_pemeriksaan_fisik" class="hvr-grow" onclick="detailOperasi('pemeriksaan_fisik')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pemeriksaan_fisik" class="hvr-grow btn-hide" onclick="conOP('pemeriksaan_fisik', 'pra_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_pemeriksaan_penunjang">
                            <td>Hasil Pemeriksaan Penunjang</td>
                            <td width="20%" align="center">
                                <img id="btn_op_pemeriksaan_penunjang" class="hvr-grow" onclick="detailOperasi('pemeriksaan_penunjang')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_pemeriksaan_penunjang" class="hvr-grow btn-hide" onclick="conOP('pemeriksaan_penunjang', 'pra_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_status_fisik">
                            <td>Status Fisik</td>
                            <td width="20%" align="center">
                                <img id="btn_op_status_fisik" class="hvr-grow" onclick="detailOperasi('status_fisik')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_status_fisik" class="hvr-grow btn-hide" onclick="conOP('status_fisik', 'pra_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_persiapan_anestesi">
                            <td>Persiapan Anestesi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_persiapan_anestesi" class="hvr-grow" onclick="detailOperasi('persiapan_anestesi')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_persiapan_anestesi" class="hvr-grow btn-hide" onclick="conOP('persiapan_anestesi', 'pra_anestesi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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
                            <div class="col-md-8">
                                <input class="form-control alergi-pasien" id="cek_anamnesa1">
                            </div>
                            <%--<div class="col-md-2">--%>
                                <%--<div class="custom02" style="margin-top: 7px">--%>
                                    <%--<input onclick="showKete(this.value, 'alergi')" type="radio" value="Tidak" id="cek_alergi11" name="cek_anamnesa1" /><label for="cek_alergi11">Tidak</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-6">--%>
                                <%--<div class="custom02" style="margin-top: 7px">--%>
                                    <%--<input onclick="showKete(this.value, 'alergi')" type="radio" value="Ya" id="cek_alergi12" name="cek_anamnesa1" /><label for="cek_alergi12">Ya</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
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
                            <div class="col-md-8">
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
                                    <input class="form-control tensi-pasien" id="cek_pf9" data-inputmask="'mask': ['999/999']" data-mask="">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        mmHg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>Nadi</label>
                                <div class="input-group">
                                    <input type="number" class="form-control nadi-pasien" id="cek_pf10">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>Suhu</label>
                                <div class="input-group">
                                    <input class="form-control suhu-pasien" id="cek_pf111" type="number">
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
                                    <input type="number" class="form-control rr-pasien" id="cek_pf122">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        x/menit
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label style="margin-top: 7px">Berat Badan</label>
                                <div class="input-group">
                                    <input type="number" class="form-control berat-pasien" id="cek_pf133">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        kg
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label style="margin-top: 7px">Tinggi Badan</label>
                                <div class="input-group">
                                    <input type="number" class="form-control tinggi-pasien" id="cek_pf144">
                                    <div class="input-group-addon" style="font-size: 10px">
                                        cm
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 10px">Kemampuan Buka Mulut</label>
                            <div class="col-md-6">
                                <input class="form-control" id="cek_pf155" style="margin-top: 10px" placeholder="cm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Skala Malapaty</label>
                            <div class="col-md-6">
                                <%--<input class="form-control" id="cek_pf166" style="margin-top: 7px">--%>
                                <select class="form-control" id="cek_pf166" style="margin-top: 7px">
                                    <option value="">[Select One]</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
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
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Hasil Pemeriksaan Penunjang
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
                                <label>Hasil Pemeriksaan Penunjang</label>
                                <textarea class="form-control penunjang-medis" id="pp1" rows="5"></textarea>
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
                            <label class="col-md-12" style="margin-top: 7px">Status Fisik Anestesi</label>
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
                            <div class="col-md-offset-4 col-md-3">
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
                            <label class="col-md-5" style="margin-top: 7px">Makan minum terakhir jam</label>
                            <div class="col-md-6">
                                <div class="input-group" style="margin-top: 7px">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="pa1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Infus</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="pa2" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Obat pre medikasi</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="pa3" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" style="margin-top: 7px">Pesanan Pra Anestesi</label>
                            <div class="col-md-7">
                                <textarea rows="5" class="form-control" id="pa4" style="margin-top: 7px"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Dokter Spesialis Anestesi</label>
                                <canvas class="paint-canvas-ttd" id="ttd_spesialis" width="220"
                                        onmouseover="paintTtd('ttd_spesialis')"></canvas>
                                <input class="form-control nama_dokter_ri" id="nama_terang_spesialis" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_spesialis" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_spesialis')"><i
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
                <button id="save_op_persiapan_anestesi" class="btn btn-success pull-right" onclick="saveDataOperasi('persiapan_anestesi','pra_anestesi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_persiapan_anestesi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-informasi_dan_persetujuan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Pemberian Informasi Edukasi Dan Persetujuan
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_informasi_dan_persetujuan">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_informasi_dan_persetujuan"></p>
                    </div>
                    <div class="btn-group btn-hide">
                        <button type="button" onclick="showModalOperasi('add_informasi_dan_persetujuan')" class="btn btn-success"><i class="fa fa-plus"></i> Tambah Informasi dan Persetujuan
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_informasi_dan_persetujuan">
                        <tbody>
                        <tr id="row_op_informasi_dan_persetujuan">
                            <td>Pemberian Informasi dan Persetujuan</td>
                            <td width="20%" align="center">
                                <img id="btn_op_informasi_dan_persetujuan" class="hvr-grow" onclick="detailOperasi('informasi_dan_persetujuan')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
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

<div class="modal fade" id="modal-op-add_informasi_dan_persetujuan">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Pemberian Informasi dan Persetujuan Tindakan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_op_add_informasi_dan_persetujuan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_add_informasi_dan_persetujuan"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Jenis Persetujuan</label>
                            <div class="col-md-9">
                                <select class="form-control select2" id="persetujuan_op" style="width: 100%" onchange="pilihPersetujuanOK(this.value)">
                                    <option value="">[Select One]</option>
                                    <option value="general_anestesi">General Anestesi</option>
                                    <option value="regional_anestesi">Regional Anestesi</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-body" style="display: none" id="form-persetujuan-op">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="op1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Dokter Penanggung Jawab Anastesi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="op2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Pemberi Informasi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="op3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Penerima Informasi</label>
                            <div class="col-md-9">
                                <input class="form-control" id="op4">
                            </div>
                        </div>
                    </div>
                    <br>
                    <table class="table table-bordered" style="font-size: 12px">
                        <thead>
                        <tr style="font-weight: bold">
                            <td>Jenis Informasi</td>
                            <td>Isi Informasi</td>
                            <td align="center">Check(<i class="fa fa-check"></i>)</td>
                        </tr>
                        </thead>
                        <tbody id="body_persetujuan-op">
                        </tbody>
                    </table>
                    <br>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-7" style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerangkan hal-hal di atas secara benar dan jelas dengan memberikan kesempatakan bertanya dan atau diskusi kepada pasien dan/atau keluarganya sedemikian rupa sehingga telah memahaminya</label>
                            <div class="col-md-4">
                                <canvas class="paint-canvas-ttd" id="ttd1" width="220" height="100"
                                        onmouseover="paintTtd('ttd1')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -42px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd1')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-7" style="text-align: justify">Dengan ini menyatakan bahwa saya telah menerima informasi sebagaimana di atas dan telah memahaminya</label>
                            <div class="col-md-4">
                                <canvas class="paint-canvas-ttd" id="ttd2" width="220" height="100"
                                        onmouseover="paintTtd('ttd2')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -42px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd2')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-12" style="text-align: justify">Persetujuan Tindakan Medis</label>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Nama</label>
                            <div class="col-md-4">
                                <input class="form-control" id="op5">
                            </div>
                            <label class="col-md-2">Tanggal Lahir</label>
                            <div class="col-md-3">
                                <input class="form-control ptr-tgl" id="op6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Jenis Kelamin</label>
                            <div class="col-md-4">
                                <select class="form-control" id="op7">
                                    <option value="">[Select One]</option>
                                    <option value="Laki-Laki">Laki-Laki</option>
                                    <option value="Perempuan">Perempuan</option>
                                </select>
                            </div>
                            <label class="col-md-2">Tindakan</label>
                            <div class="col-md-3">
                                <input class="form-control" id="op8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Nama Pasien</label>
                            <div class="col-md-4">
                                <input class="form-control nama-pasien" id="op9" readonly>
                            </div>
                            <label class="col-md-2">Tanggal Lahir Pasien</label>
                            <div class="col-md-3">
                                <input class="form-control tgl-lahir-pasien" id="op10" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-3">Alamat Pasien</label>
                            <div class="col-md-9">
                                <textarea class="form-control alamat-pasien" id="op11"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-5">
                                <label style="margin-left: 8px">TTD Yang Menyatakan</label>
                                <canvas class="paint-canvas-ttd" id="ttd3" width="220" height="100"
                                        onmouseover="paintTtd('ttd3')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -100px; margin-top: 35px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd3')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-offset-5 col-md-3">Saksi</label>
                            <div class="col-md-5">
                                <canvas class="paint-canvas-ttd" id="ttd4" width="220" height="100"
                                        onmouseover="paintTtd('ttd4')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -100px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd4')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                            <div class="col-md-5">
                                <canvas class="paint-canvas-ttd" id="ttd5" width="220" height="100"
                                        onmouseover="paintTtd('ttd5')"></canvas>
                            </div>
                            <div class="col-md-1">
                                <button style="margin-left: -100px; margin-top: 10px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd5')"><i
                                        class="fa fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_add_informasi_dan_persetujuan" class="btn btn-success pull-right"
                        onclick="saveDataOperasi('add_informasi_dan_persetujuan','informasi_dan_persetujuan')"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_op_add_informasi_dan_persetujuan" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-pindah_rr">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Kriteria Pindah Dari RR
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_pindah_rr">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_pindah_rr"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_pindah_rr">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_pindah_rr"></p>
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
                            <li><a onclick="showModalOperasi('rr_dewasa')" style="cursor: pointer"><i class="fa fa-plus"></i> Anestesi Pasien Dewasa</a></li>
                            <li><a onclick="showModalOperasi('rr_anak_anak')" style="cursor: pointer"><i class="fa fa-plus"></i> Anestesi Pasien Anak-Anak</a></li>
                            <li><a onclick="showModalOperasi('rr_sab')" style="cursor: pointer"><i class="fa fa-plus"></i> Anestesi Pasien SAB/Epidural</a></li>
                        </ul>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_pindah_rr">
                        <tbody>
                        <tr id="row_op_rr_dewasa">
                            <td>Penilain Pasca General Anestesi Pasien Dewasa (Aldrette Score)</td>
                            <td width="20%" align="center">
                                <img id="btn_op_rr_dewasa" class="hvr-grow" onclick="detailOperasi('rr_dewasa')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_rr_dewasa" class="hvr-grow btn-hide" onclick="conOP('rr_dewasa', 'pindah_rr')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_rr_anak_anak">
                            <td>Penilain Pasca General Anestesi Pasien Anak-Anak (Stewart Score)</td>
                            <td width="20%" align="center">
                                <img id="btn_op_rr_anak_anak" class="hvr-grow" onclick="detailOperasi('rr_anak_anak')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_rr_anak_anak" class="hvr-grow btn-hide" onclick="conOP('rr_anak_anak', 'pindah_rr')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
                            </td>
                        </tr>
                        <tr id="row_op_rr_sab">
                            <td>Penilain Pasca Regional Anestesi Pasien SAB/Epidural (Bromage Score)</td>
                            <td width="20%" align="center">
                                <img id="btn_op_rr_sab" class="hvr-grow" onclick="detailOperasi('rr_sab')" src="<%= request.getContextPath() %>/pages/images/icons8-plus-25.png">
                                <img id="delete_rr_sab" class="hvr-grow btn-hide" onclick="conOP('rr_sab', 'pindah_rr')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-op-rr_dewasa">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Penilain Pasca General Anestesi Pasien Dewasa
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_rr_dewasa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_rr_dewasa"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kesadaran</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sadar baik, orientasi baik|2" id="rd11" name="rd1" /><label for="rd11">Sadar baik, orientasi baik</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dapat dibangunkan, tapi cepat tidur|1" id="rd12" name="rd1" /><label for="rd12">Dapat dibangunkan, tapi cepat tidur</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak ada respon|0" id="rd13" name="rd1" /><label for="rd13">Tidak ada respon</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Warna Kulit</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Merah, tanpa bantuan Oksigen sat O2 > 92%|2" id="rd21" name="rd2" /><label for="rd21">Merah, tanpa bantuan Oksigen sat O2 > 92%</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Pucat, perlu bantuan O2 agar sat O2 > 90%|1" id="rd22" name="rd2" /><label for="rd22">Pucat, perlu bantuan O2 agar sat O2 > 90%</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Sianosis|0" id="rd23" name="rd2" /><label for="rd23">Sianosis</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Aktifitas</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Gerak bertujuan, sesuai perintah verbal|2" id="rd31" name="rd3" /><label for="rd31">Gerak bertujuan, sesuai perintah verbal</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Bergerak, tak bertujuan|1" id="rd32" name="rd3" /><label for="rd32">Bergerak, tak bertujuan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak bergerak|0" id="rd33" name="rd3" /><label for="rd33">Tidak bergerak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Respirasi</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Nafas spontan, mampu nafas dalam, ada reflek batuk|2" id="rd41" name="rd4" /><label for="rd41">Nafas spontan, mampu nafas dalam, ada reflek batuk</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Nafas dangkal, sesak|1" id="rd42" name="rd4" /><label for="rd42">Nafas dangkal, sesak</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Apneu atau obstruksi, nafas dibantu|0" id="rd43" name="rd4" /><label for="rd43">Apneu atau obstruksi, nafas dibantu</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kardio Vaskuler</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tekanan darah tetap atau berubah < 20%|2" id="rd51" name="rd5" /><label for="rd51">Tekanan darah tetap atau berubah < 20%</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tekanan darah berubah 20-50%|1" id="rd52" name="rd5" /><label for="rd52">Tekanan darah berubah 20-50%</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tekanan darah berubah > 50%|0" id="rd53" name="rd5" /><label for="rd53">Tekanan darah berubah > 50%</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_rr_dewasa" class="btn btn-success pull-right" onclick="saveDataOperasi('rr_dewasa','pindah_rr')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_rr_dewasa" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-rr_anak_anak">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Penilain Pasca General Anestesi Pasien Anak-Anak
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_rr_anak_anak">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_rr_anak_anak"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Kesadaran</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Menangis|2" id="raa11" name="raa1" /><label for="raa11">Menangis</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Ada reaksi terhadap rangsangan|1" id="raa12" name="raa1" /><label for="raa12">Ada reaksi terhadap rangsangan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak ada respon|0" id="raa13" name="raa1" /><label for="raa13">Tidak ada respon</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Respirasi</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Batuk, menangis|2" id="raa21" name="raa2" /><label for="raa21">Batuk, menangis</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Pertahankan jalan nafas|1" id="raa22" name="raa2" /><label for="raa22">Pertahankan jalan nafas</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Perlu bantuan|0" id="raa23" name="raa2" /><label for="raa23">Perlu bantuan</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3" style="margin-top: 7px">Aktifitas</label>
                            <div class="col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Gerak bertujuan|2" id="raa31" name="raa3" /><label for="raa31">Gerak bertujuan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Gerak tak bertujuan|1" id="raa32" name="raa3" /><label for="raa32">Gerak tak bertujuan</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-8">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak bergerak|0" id="raa33" name="raa3" /><label for="raa33">Tidak bergerak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_rr_anak_anak" class="btn btn-success pull-right" onclick="saveDataOperasi('rr_anak_anak','pindah_rr')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_rr_anak_anak" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-rr_sab">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Penilain Pasca Regional Anestesi Pasien SAB/Epidural
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_rr_sab">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_rr_sab"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-1 col-md-11">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dapat menggerakan dan mengangkat tungkai bawah|0" id="sab11" name="sab1" /><label for="sab11">Dapat menggerakan dan mengangkat tungkai bawah</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-1 col-md-11">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Dapat menekuk lutut tetapi tidak bisa mengangkat tungkai bawah|1" id="sab12" name="sab1" /><label for="sab12">Dapat menekuk lutut tetapi tidak bisa mengangkat tungkai bawah</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-1 col-md-11">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Hanya dapat menggerakkan tungkai bawah, tidak mampu menekuk lutut|2" id="sab13" name="sab1" /><label for="sab13">Hanya dapat menggerakkan tungkai bawah, tidak mampu menekuk lutut</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-1 col-md-11">
                                <div class="custom02" style="margin-top: 7px">
                                    <input type="radio" value="Tidak mampu menggerakkan kaki|3" id="sab14" name="sab1" /><label for="sab14">Tidak mampu menggerakkan kaki</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_op_rr_sab" class="btn btn-success pull-right" onclick="saveDataOperasi('rr_sab','pindah_rr')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_rr_sab" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-laporan_operasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Laporan Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body btn-hide">
                    <div class="alert alert-success alert-dismissible" style="display: none" id="warning_op_laporan_operasi">
                        <h4><i class="icon fa fa-info"></i> Info!</h4>
                        <p id="msg_op_laporan_operasi"></p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warn_laporan_operasi">
                        <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                        <p id="msg_laporan_operasi"></p>
                    </div>
                    <div class="btn-group btn-hide">
                        <button type="button" class="btn btn-success" onclick="showModalOperasi('add_laporan_operasi')"><i class="fa fa-plus"></i> Laporan Operasi
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="tabel_op_add_laporan_operasi">
                        <tbody>
                        <tr id="row_op_laporan_operasi">
                            <td>Laporan Operasi</td>
                            <td width="20%" align="center">
                                <img id="btn_op_laporan_operasi" class="hvr-grow" onclick="detailOperasi('laporan_operasi')" src="<%= request.getContextPath() %>/pages/images/icons8-add-list-25.png">
                                <img id="delete_add_laporan_operasi" class="hvr-grow btn-hide" onclick="conOP('add_laporan_operasi', 'laporan_operasi')" src="<%= request.getContextPath() %>/pages/images/cancel-flat-new.png">
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

<div class="modal fade" id="modal-op-add_laporan_operasi">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> Laporan Operasi
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_op_add_laporan_operasi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_add_laporan_operasi"></p>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Tanggal</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control tgl" id="lap1">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Nama Ahli Bedah</label>
                            <div class="col-md-8">
                                <input class="form-control dokter_bedah" id="lap2">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Nama Asisten Bedah</label>
                            <div class="col-md-8">
                                <input class="form-control perawat_anestesi" id="lap3">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Nama Perawat Instrumen</label>
                            <div class="col-md-8">
                                <input class="form-control asisten_instrumen" id="lap4">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Kelas</label>
                            <div class="col-md-8">
                                <input class="form-control" id="lap5">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Medis Pre Operasi</label>
                            <div class="col-md-8">
                                <input class="form-control diagnosa-pasien" id="lap6">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Diagnosa Medis Post Operasi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="lap7">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Tindakan Operasi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="lap8">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Jaringan yang di incisi/excisi</label>
                            <div class="col-md-8">
                                <input class="form-control" id="lap9">
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Dikirim untuk pemeriksaan</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Ya" id="lap101"
                                           name="lap10"/><label for="lap101">Ya</label>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="custom02">
                                    <input type="radio" value="Tidak" id="lap102"
                                           name="lap10"/><label for="lap102">Tidak</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Pembedahan mulai jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam" id="lap11">
                                </div>
                            </div>
                            <label class="col-md-2">Selesai jam</label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-clock-o"></i>
                                    </div>
                                    <input class="form-control jam02" id="lap12">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4">Jenis Pembiusan</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="General" id="lap131"
                                           name="lap13"/><label for="lap131">General</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="Lokal Anestesi" id="lap132"
                                           name="lap13"/><label for="lap132">Lokal Anestesi</label>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="custom02">
                                    <input type="radio" value="RA-Epidural" id="lap133"
                                           name="lap13"/><label for="lap133">RA-Epidural</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="RA-SAB" id="lap134"
                                           name="lap13"/><label for="lap134">RA-SAB</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="RA-SAB" id="lap135"
                                           name="lap13"/><label for="lap135">Blok Syaraf</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Kategori Operasi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Khusus" id="lap141"
                                           name="lap14"/><label for="lap141">Khusus</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Besar" id="lap142"
                                           name="lap14"/><label for="lap142">Besar</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Sedang" id="lap143"
                                           name="lap14"/><label for="lap143">Sedang</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Kecil" id="lap144"
                                           name="lap14"/><label for="lap144">Kecil</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Jenis Operasi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Bersih" id="lap151"
                                           name="lap15"/><label for="lap151">Bersih</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Bersih Terkontaminasi" id="lap152"
                                           name="lap15"/><label for="lap152">Bersih Terkontaminasi</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-6">
                                <div class="custom02">
                                    <input type="radio" value="Terkontaminasi/Kotor" id="lap153"
                                           name="lap15"/><label for="lap153">Terkontaminasi/Kotor</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Posisi</label>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Terlentang" id="lap161"
                                           name="lap16"/><label for="lap161">Terlentang</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Lateral" id="lap162"
                                           name="lap16"/><label for="lap162">Lateral</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Litothomy" id="lap163"
                                           name="lap16"/><label for="lap163">Litothomy</label>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="custom02">
                                    <input type="radio" value="Prone" id="lap164"
                                           name="lap16"/><label for="lap164">Prone</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4">Jml Cairan masuk</label>
                            <div class="col-md-4">
                                <input class="form-control" id="lap18">
                            </div>
                            <%--<label class="col-md-4">Jml Perdarahan</label>--%>
                            <%--<div class="col-md-3">--%>
                                <%--<input class="form-control" id="lap17">--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-12"><b>Urutan Operasi</b></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_1">1. Persiapan Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_2">2. Posisi Pasien</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_3">3. Desinfektan</label>
                            <div class="col-md-8">
                                <select class="form-control urutan_op">
                                    <option value="Betadin">Betadin</option>
                                    <option value="Salvon">Salvon</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_4">4. Insisi kulit dan pembukaan lapangan operasi</label>
                            <div class="col-md-8">
                                <select class="form-control urutan_op">
                                    <option value="Midline">Midline</option>
                                    <option value="Pfanenstil">Pfanenstil</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_5">5. Pendapatan lapangan operasi dan kulit</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_6">6. Apa yang dilakukan</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_7">7. Penutupan lapangan Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_8">8. Komplikasi Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_9">9. Hasil Operasi</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_10">10. Diskripsi jaringan yang diambil</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_11">11. Lain-Lain yang perlu</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_12">12. Kesimpulan</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_13">13. Jumlah Perdarahan</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak">
                        <div class="form-group">
                            <label class="col-md-4" id="label_op_14">14. Kode implan (jika terpasang)</label>
                            <div class="col-md-8">
                                <textarea class="form-control urutan_op"></textarea>
                            </div>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-6">
                                <label style="margin-left: 8px">TTD Dokter Operator</label>
                                <canvas class="paint-canvas-ttd" id="ttd_lap_dokter" width="220"
                                        onmouseover="paintTtd('ttd_lap_dokter')"></canvas>
                                <input class="form-control nama_dokter_ri" id="nama_terang_sps" placeholder="Nama Terang">
                                <input style="margin-top: 3px" class="form-control sip_dokter_ri" id="sip_sps" placeholder="SIP">
                                <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                        onclick="removePaint('ttd_lap_dokter')"><i
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
                <button id="save_op_add_laporan_operasi" class="btn btn-success pull-right" onclick="saveDataOperasi('add_laporan_operasi','laporan_operasi')"><i class="fa fa-check"></i> Save</button>
                <button id="load_op_add_laporan_operasi" style="display: none; cursor: no-drop" type="button" class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-op-ttd_perawat">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-plus"></i> TTD Perawat
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none"
                     id="warning_op_ttd_perawat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_op_ttd_perawat"></p>
                </div>
                <div class="modal-body">
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
                                <div class="col-md-offset-2 col-md-8">
                                    <label>TTD Perawat OK</label>
                                    <canvas class="paint-canvas-ttd del-canvas" id="ttd_edit" width="300"
                                            onmouseover="paintTtd('ttd_edit')"></canvas>
                                    <input class="form-control" id="nama_ttd_perawat_edit" placeholder="Nama Terang">
                                    <input style="margin-top: 3px" class="form-control" id="sip_ttd_perawat_edit" placeholder="SIP">
                                    <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                            onclick="removePaint('ttd_edit')"><i
                                            class="fa fa-trash"></i> Clear
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button id="save_ttd_perawat" class="btn btn-success pull-right"><i class="fa fa-check"></i>
                    Save
                </button>
                <button id="load_ttd_perawat" style="display: none; cursor: no-drop" type="button"
                        class="btn btn-success"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>





