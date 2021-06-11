function choiceImg(id, url, idSet) {
    var temp = $('#temp_scala').val();
    if (temp == '') {
        $('#temp_scala').val(id);
        $('#' + id).attr('style', 'width: 100%; cursor: pointer; border-radius: 50%; border: solid 5px red');
    } else {
        if (temp == id) {
            $('#' + id).attr('style', 'width: 100%; cursor: pointer; border-radius: 50%; border: solid 5px red');
        } else {
            $('#' + temp).attr('style', 'width: 100%; cursor: pointer');
            $('#' + id).attr('style', 'width: 100%; cursor: pointer; border-radius: 50%; border: solid 5px red');
            $('#temp_scala').val(id);
        }
    }
    var canvas = document.getElementById('choice_emoji');
    var ctx = canvas.getContext('2d');
    var img = new Image();
    img.src = url;
    img.onload = function (ev) {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(img, 0, 0);
    }
    $('#' + idSet).val(id);
}

function cekNyeri(val, idSet) {
    if (val == "Ya") {
        $('.nyeri').attr('style', 'width: 100%; cursor: pointer');
        $('.nyeri').attr('onclick', 'choiceImg(this.id, $(\'#\'+this.id).attr(\'src\'), \'' + idSet + '\')');
        $('#apakah_nyeri, #emoji, #set_nyeri').show();
    } else {
        $('.nyeri').attr('style', 'width: 100%; cursor: no-drop');
        $('.nyeri').removeAttr('onclick');
        $('#' + idSet).val('');
        $('#apakah_nyeri, #emoji, #set_nyeri').hide();
    }
}

function setNyeri(id, tahun){
    var jenis   = "";
    var tipe    = "";
    if(parseInt(tahun) > 0 && parseInt(tahun) <= 18){
        jenis = '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <label class="col-md-2 label_nyeri">Face</label>\n' +
            '                            <div class="col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Tidak ada ekpresi tertentu atau senyuman|0" id="nyeri11" name="nyeri1" /><label for="nyeri11">Tidak ada ekpresi tertentu atau senyuman</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Menyeringai sesekali atau mengerutkan dahi, muram ogah ogahan|1" id="nyeri12" name="nyeri1" /><label for="nyeri12">Menyeringai sesekali atau mengerutkan dahi, muram ogah ogahan</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Dagu gementar dan rahang diketap berulang|2" id="nyeri13" name="nyeri1" /><label for="nyeri13">Dagu gementar dan rahang diketap berulang</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <hr class="garis">\n' +
            '                    <div class="row jarak">\n' +
            '                        <div class="form-group">\n' +
            '                            <label class="col-md-2 label_nyeri">Leg</label>\n' +
            '                            <div class="col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Normal|0" id="nyeri21" name="nyeri2" /><label for="nyeri21">Normal</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Gelisah, resah, tegang|1" id="nyeri22" name="nyeri2" /><label for="nyeri22">Gelisah, resah, tegang</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Menendang atau menarik kaki|2" id="nyeri23" name="nyeri2" /><label for="nyeri23">Menendang atau menarik kaki</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <hr class="garis">\n' +
            '                    <div class="row jarak">\n' +
            '                        <div class="form-group">\n' +
            '                            <label class="col-md-2 label_nyeri">Activity</label>\n' +
            '                            <div class="col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Rebahan dengan tenang, posisi normal, bergerak dengan mudah|0" id="nyeri31" name="nyeri3" /><label for="nyeri31">Rebahan dengan tenang, posisi normal, bergerak dengan mudah</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Menggeliat, maju mundur, tegang|1" id="nyeri32" name="nyeri3" /><label for="nyeri32">Menggeliat, maju mundur, tegang</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Menekuk atau posisi tubuh meringkuk, kaku atau menyentak|2" id="nyeri33" name="nyeri3" /><label for="nyeri33">Menekuk atau posisi tubuh meringkuk, kaku atau menyentak</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <hr class="garis">\n' +
            '                    <div class="row jarak">\n' +
            '                        <div class="form-group">\n' +
            '                            <label class="col-md-2 label_nyeri">Cry</label>\n' +
            '                            <div class="col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Tidak ada tangisan (terjaga/tertidur)|0" id="nyeri41" name="nyeri4" /><label for="nyeri41">Tidak ada tangisan (terjaga/tertidur)</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Mengerang/merengek, gerutuan sesekali|1" id="nyeri42" name="nyeri4" /><label for="nyeri42">Mengerang/merengek, gerutuan sesekali</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Menangis tersedu-sedu, menjerit, terisak-isak, menggerutu berulang-ulang|2" id="nyeri43" name="nyeri4" /><label for="nyeri43">Menangis tersedu-sedu, menjerit, terisak-isak, menggerutu berulang-ulang</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <hr class="garis">\n' +
            '                    <div class="row jarak">\n' +
            '                        <div class="form-group">\n' +
            '                            <label class="col-md-2 label_nyeri">Consolability</label>\n' +
            '                            <div class="col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Senang, santai|0" id="nyeri51" name="nyeri5" /><label for="nyeri51">Senang, santai</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Dapat ditenangkan dengan sentuhan, pelukan atau berbicara, dapat dialihkan|1" id="nyeri52" name="nyeri5" /><label for="nyeri52">Dapat ditenangkan dengan sentuhan, pelukan atau berbicara, dapat dialihkan</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                    <div class="row">\n' +
            '                        <div class="form-group">\n' +
            '                            <div class="col-md-offset-2 col-md-10">\n' +
            '                                <div class="custom02">\n' +
            '                                    <input type="radio" value="Sulit atau dapat ditenangkan dengan pelukan, sentuhan atau distraksi|2" id="nyeri53" name="nyeri5" /><label for="nyeri53">Sulit atau dapat ditenangkan dengan pelukan, sentuhan atau distraksi</label>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>';
        $('#temp_jenis').val('flacc');
    }else{
        jenis = '<div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label class="col-md-5" style="margin-top: 7px">Lokasi</label>\n' +
            '                                <div class="col-md-5">\n' +
            '                                    <input class="form-control" style="margin-top: 7px;" id="y_lokasi">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label class="col-md-5" style="margin-top: 7px">Jenis</label>\n' +
            '                                <div class="col-md-5">\n' +
            '                                    <div class="custom02" style="margin-top: 7px">\n' +
            '                                        <input type="radio" value="Akut" id="nyeri3" name="radio_nyeri_jenis" /><label for="nyeri3">Akut</label>\n' +
            '                                        <input type="radio" value="Kronis" id="nyeri4" name="radio_nyeri_jenis" /><label for="nyeri4">Kronis</label>\n' +
            '                                    </div>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label class="col-md-5" style="margin-top: 7px">Intensitas</label>\n' +
            '                                <div class="col-md-5">\n' +
            '                                    <input class="form-control" style="margin-top: 7px;" id="y_inten">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>' +
            '<div class="row" style="margin-top: 10px">\n' +
            '<label class="col-md-12"><b>Wong Baker Pain Scale</b><small style="color: red"> (*Lingkari skla nyeri dibawah)</small></label>'+
            '<div class="form-group">\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-0.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop;" id="0">\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">0</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Tidak Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-2.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="2" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">2</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-4.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="4" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">4</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Sedikit Lebih Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-6.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="6" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">6</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Lebih Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-8.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="8" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">8</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Sangat Nyeri</p>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <img src="'+contextPath+'/pages/images/scala-10.png" class="nyeri"\n' +
            '             style="width: 100%; cursor: no-drop" id="10" >\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: 10px">10</p>\n' +
            '        <p class="text-center" style="font-size: 12px; margin-top: -10px">Nyeri Sangat Hebat</p>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';
        $('#temp_jenis').val('emoji');
    }
    $('#'+id).html(jenis);

    // if(jenis != ''){
    //     $('#'+id).html(jenis);
    //     if(tipe == "dewasa"){
    //         var url = contextPath+'/pages/images/scala-nyeri-number-2.jpg';
    //         var canvas = document.getElementById('area_nyeri');
    //         var ctx = canvas.getContext('2d');
    //         var img = new Image();
    //         img.src = url;
    //         img.onload = function (ev) {
    //             canvas.width = img.width;
    //             canvas.height = img.height;
    //             ctx.clearRect(0, 0, canvas.width, canvas.height);
    //             ctx.drawImage(img, 0, 0);
    //         }
    //     }
    // }
}