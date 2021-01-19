
function loadPositionJabatan() {
    var nip = $("#nip1").val();
    BiodataAction.listPersonilPosition(nip, function (res) {

        var str = "";
        $.each(res, function (i, item) {

            var no = i + parseInt(1);
            str += '<tr>' +
                '<td>' + no + '</td>' +
                '<td>'+ item.branchName + '</td>' +
                '<td>'+ item.positionName + '</td>' +
                '<td>'+ item.profesiName + '</td>' +
                '<td style="font-weight: bold;">'+ item.jenisPegawaiName + '</td>' +
                '<td>'+ labeledWhiteFlag(item.flagDigaji) + '</td>' +
                '<td align="center">' +
                '<button class="btn btn-primary btn-sm" onclick="initEditJabatan(\''+item.nip+'\',\''+item.positionId+'\')"><i class="fa fa-edit"></i></button>' +
                '<button class="btn btn-danger btn-sm" onclick="initDeleteJabatan(\''+item.nip+'\',\''+item.positionId+'\')"><i class="fa fa-trash"></i></button>' +
                '</td>' +
                '</tr>';
        });

        $("#tbody-position").html(str);
    });
}

function labeledWhiteFlag(flag){
    if (flag == "Y")
        return "<div class='label label-success'><i class='fa fa-check'></i></div>";
    return "";
}

function listPositionJabatan() {
    var divisi = $("#department-jabatan option:selected").val();

    PositionAction.searchPositionBiodataHistory(divisi, function (listdata) {

        var str = "";
        $.each(listdata, function (i, item) {
            str += '<option value="'+item.positionId+'">'+item.positionName+'</option>';
        });

        $("#posisi-jabatan").html(str);
    });
}

function initEditJabatan(id, positionId) {
    $("#modal-edit-jabatan").modal('show');
    $("#modal-edit-jabatan-title").html("Edit Jabatan");

    BiodataAction.initEditSessionPosition(id, positionId, function (res) {

        $("#department-jabatan").val(res.divisiId).change();
        $("#posisi-jabatan").val(res.positionId);
        $("#jenis-jabatan").val(res.jenisPegawai);
        $("#profesi-jabatan").val(res.profesiId);
        $("#personil-position-id").val(res.personilPositionId);
        $("#personil-position-flag").val("Y");
        $("#saveJabatan").attr("onclick", "saveEditJabatan()");
    });
}

function initDeleteJabatan(id, positionId) {
    $("#modal-edit-jabatan").modal('show');
    $("#modal-edit-jabatan-title").html("Delete Jabatan");

    BiodataAction.initEditSessionPosition(id, positionId, function (res) {

        $("#department-jabatan").val(res.divisiId);
        $("#posisi-jabatan").val(res.positionId);
        $("#jenis-jabatan").val(res.jenisPegawai);
        $("#profesi-jabatan").val(res.profesiId);
        $("#flag-digaji").val(res.flagDigaji);
        $("#personil-position-id").val(res.personilPositionId);
        $("#personil-position-flag").val("N");

        $("#department-jabatan").attr("disbled", "true");
        $("#department-jabatan").attr("disbled", "true");
        $("#posisi-jabatan").attr("disbled", "true");
        $("#jenis-jabatan").attr("disbled", "true");
        $("#profesi-jabatan").attr("disbled", "true");
        $("#flag-digaji").attr("disbled", "true");
        $("#saveJabatan").attr("onclick", "saveDeleteJabatan()");
    });
}

function getAllDepartment() {

    DepartmentAction.getListDepartmentAll(function (res) {

        var str = "";
        $.each(res, function (i, item) {
            str += '<option value="'+ item.departmentId + '">'+item.departmentName+'</option>';
        });

        $("#department-jabatan").html(str);
    });
}

function saveEditJabatan() {

    var nip             = $("#nip1").val();
    var departmentId    = $("#department-jabatan").val();
    var departmentName  = $("#department-jabatan").text();
    var posisiBaruId    = $("#posisi-jabatan").val();
    var posisiBaruName  = $("#posisi-jabatan").text();
    var profesiId       = $("#profesi-jabatan").val();
    var profesiName     = $("#profesi-jabatan").text();

}