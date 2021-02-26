
function loadPositionJabatan() {
    $("#inputKodeDokter").css('display','block');
    var nip = $("#nip1").val();
    BiodataAction.listPersonilPosition(nip, function (res) {

        var str = "";
        $.each(res, function (i, item) {

            var no = i + parseInt(1);
            if (item.flag == 'N'){
                str += '<tr style="background-color: gainsboro">';
            } else {
                str += '<tr>';
            }

            str += '<td>' + no + '</td>' +
                '<td>'+ item.branchName + '</td>' +
                '<td>'+ item.positionName + '</td>' +
                '<td>'+ item.profesiName + '</td>' +
                '<td style="font-weight: bold;">'+ item.jenisPegawaiName + '</td>' +
                '<td>'+ labeledWhiteFlag(item.flagDigaji) + '</td>' +
                '<td align="center">';

            if (item.flag != 'N'){
                str += '<button class="btn btn-primary btn-sm" onclick="initEditJabatan(\''+item.nip+'\',\''+item.positionId+'\')"><i class="fa fa-edit"></i></button>' +
                    '<button class="btn btn-danger btn-sm" onclick="initDeleteJabatan(\''+item.nip+'\',\''+item.positionId+'\')"><i class="fa fa-trash"></i></button>';
            }

            str += '</td>' +
                '</tr>';

            // if(item.tipeProfesi == "dokter"){
            //     $("#inputKodeDokter").css('display','block');
            // }
        });

        $("#tbody-position").html(str);
    });
}

function labeledWhiteFlag(flag){
    if (flag == "Y")
        return "<div class='label label-success'><i class='fa fa-check'></i></div>";
    return "";
}

function listPositionJabatan(divisi, positionId) {

    if (divisi == null || divisi == ""){
        divisi = $("#department-jabatan option:selected").val();
    }

    PositionAction.searchPositionBiodataHistory(divisi, function (listdata) {

        var str = "";
        $.each(listdata, function (i, item) {
            if (positionId == item.positionId){
                str += '<option value="'+item.positionId+'" selected>'+item.positionName+'</option>';
            } else {
                str += '<option value="'+item.positionId+'">'+item.positionName+'</option>';
            }
        });

        $("#posisi-jabatan").html(str);
    });
}

function initAddJabatan() {
    cleanAllFormPositionJabatan();
    $("#personil-position-flag").val("Y");
    $("#modal-edit-jabatan-title").text("Add Jabatan");
    $("#modal-edit-jabatan").modal('show');
    $("#saveJabatan").attr("onclick", "saveAddJabatan()");
}

function initEditJabatan(id, positionId) {

    cleanAllFormPositionJabatan();

    $("#modal-edit-jabatan").modal('show');
    $("#modal-edit-jabatan-title").text("Edit Jabatan");

    BiodataAction.initEditSessionPosition(id, positionId, function (res) {

        $("#department-jabatan").val(res.divisiId);
        listPositionJabatan(res.divisiId, res.positionId);
        $("#posisi-jabatan").val(res.positionId);
        $("#position-id-lama").val(res.positionId);
        $("#position-nama-lama").val(res.positionName);
        $("#jenis-jabatan").val(res.jenisPegawai);
        $("#profesi-jabatan").val(res.profesiId);
        $("#personil-position-id").val(res.personilPositionId);
        $("#personil-position-flag").val("Y");
        $("#position-branch-id").val(res.branchId);
        $("#saveJabatan").attr("onclick", "saveEditJabatan()");
    });
}

function initDeleteJabatan(id, positionId) {

    cleanAllFormPositionJabatan();

    $("#modal-edit-jabatan").modal('show');
    $("#modal-edit-jabatan-title").text("Delete Jabatan");

    BiodataAction.initEditSessionPosition(id, positionId, function (res) {

        $("#department-jabatan").val(res.divisiId);
        listPositionJabatan(res.divisiId, res.positionId);
        $("#posisi-jabatan").val(res.positionId);
        $("#position-id-lama").val(res.positionId);
        $("#position-nama-lama").val(res.positionName);
        $("#jenis-jabatan").val(res.jenisPegawai);
        $("#profesi-jabatan").val(res.profesiId);
        $("#flag-digaji").val(res.flagDigaji);
        $("#personil-position-id").val(res.personilPositionId);
        $("#personil-position-flag").val("N");
        $("#position-branch-id").val(res.branchId);

        $("#position-branch-id").attr("disabled", "true");
        $("#department-jabatan").attr("disabled", "true");
        $("#posisi-jabatan").attr("disabled", "true");
        $("#jenis-jabatan").attr("disabled", "true");
        $("#profesi-jabatan").attr("disabled", "true");
        $("#flag-digaji").attr("disabled", "true");
        $("#saveJabatan").attr("onclick", "saveEditJabatan()");
    });
}

function getAllDepartment() {

    DepartmentAction.getListDepartmentAll(function (res) {

        var str = "";
        $.each(res, function (i, item) {
            str += '<option value="'+ item.departmentId + '">'+item.departmentName+'</option>';
        });

        $("#department-jabatan").append("");
        $("#department-jabatan").html(str);

    });
}

function saveEditJabatan() {
    var nip                 = $("#nip1").val() == "" ? "1" : $("#nip1").val();
    var departmentId        = $("#department-jabatan option:selected").val();
    var departmentName      = $("#department-jabatan option:selected").text();
    var posisiBaruId        = $("#posisi-jabatan option:selected").val();
    var posisiBaruName      = $("#posisi-jabatan option:selected").text();
    var profesiId           = $("#profesi-jabatan option:selected").val();
    var profesiName         = $("#profesi-jabatan option:selected").text();
    var posisiIdLama        = $("#position-id-lama").val();
    var posisiNameLama      = $("#position-name-lama").val();
    var personilPositionId  = $("#personil-position-id").val();
    var flag                = $("#personil-position-flag").val();
    var branchId            = $("#position-branch-id option:selected").val();
    var branchName          = $("#position-branch-id option:selected").text();
    var jenisPegawaiId      = $("#jenis-jabatan option:selected").val();
    var jenisPegawaiName    = $("#jenis-jabatan option:selected").text();
    var flagDigaji          = $("#flag-digaji").val();

    var objJson = {
        nip : nip,
        positionid : posisiBaruId,
        idpersonilposition : personilPositionId,
        positionidlama : posisiIdLama,
        positionname : posisiBaruName,
        profesiid : profesiId,
        profesiname : profesiName,
        branchid : branchId,
        branchname : branchName,
        jenispegawai : jenisPegawaiId,
        jenispegawainame : jenisPegawaiName,
        flagdigaji : flagDigaji,
        flag : flag,
        divisiid : departmentId,
        divisiname : departmentName
    };

    var stJson = JSON.stringify(objJson);

    BiodataAction.saveEditToPersonilPositionSession(stJson, function (res) {

        if (res.status == "error"){
            alert(res.msg);
        } else {
            alert("Data Berhasil Di Update")
            cleanAllFormPositionJabatan();
            loadPositionJabatan();
            $("#modal-edit-jabatan").modal('hide');
        }
    });
}

function saveAddJabatan() {
    var nip                 = $("#nip1").val() == "" ? "1" : $("#nip1").val();
    var departmentId        = $("#department-jabatan option:selected").val();
    var departmentName      = $("#department-jabatan option:selected").text();
    var posisiBaruId        = $("#posisi-jabatan option:selected").val();
    var posisiBaruName      = $("#posisi-jabatan option:selected").text();
    var profesiId           = $("#profesi-jabatan option:selected").val();
    var profesiName         = $("#profesi-jabatan option:selected").text();
    var posisiIdLama        = $("#position-id-lama").val();
    var posisiNameLama      = $("#position-name-lama").val();
    var personilPositionId  = $("#personil-position-id").val();
    var flag                = $("#personil-position-flag").val();
    var branchId            = $("#position-branch-id option:selected").val();
    var branchName          = $("#position-branch-id option:selected").text();
    var jenisPegawaiId      = $("#jenis-jabatan option:selected").val();
    var jenisPegawaiName    = $("#jenis-jabatan option:selected").text();
    var flagDigaji          = $("#flag-digaji").val();

    var objJson = {
        nip : nip,
        positionid : posisiBaruId,
        idpersonilposition : personilPositionId,
        positionidlama : posisiIdLama,
        positionname : posisiBaruName,
        profesiid : profesiId,
        profesiname : profesiName,
        branchid : branchId,
        branchname : branchName,
        jenispegawai : jenisPegawaiId,
        jenispegawainame : jenisPegawaiName,
        flagdigaji : flagDigaji,
        flag : flag,
        divisiid : departmentId,
        divisiname : departmentName
    };

    var stJson = JSON.stringify(objJson);

    BiodataAction.saveAddToPersonilPositionSession(stJson, function (res) {

        if (res.status == "error"){
            alert(res.msg);
        } else {
            alert("Data Berhasil Di Tambahkan")
            cleanAllFormPositionJabatan();
            loadPositionJabatan();
            $("#modal-edit-jabatan").modal('hide');
        }
    });
}

function cleanAllFormPositionJabatan() {

    $("#position-branch-id").removeAttr("disabled");
    $("#department-jabatan").removeAttr("disabled");
    $("#posisi-jabatan").removeAttr("disabled");
    $("#jenis-jabatan").removeAttr("disabled");
    $("#profesi-jabatan").removeAttr("disabled");
    $("#flag-digaji").removeAttr("disabled");

    $("#position-id-lama").empty();
    $("#position-name-lama").empty();
    $("#personil-position-id").empty();
    $("#personil-position-flag").empty();
}

function getAllJenisPegawai(){

    BiodataAction.getAllJenisPegawai(function(res){
        var str = "";
        $.each(res, function (i, item) {
            str += '<option value="'+item.jenisPegawaiId+'" selected>'+item.jenisPegawaiName+'</option>';
        });

        $("#jenis-jabatan").html(str);
    });
}

function clearPositionJabatanSession() {
    BiodataAction.clearSessionJabatan(function () {
        loadPositionJabatan();
    });
}