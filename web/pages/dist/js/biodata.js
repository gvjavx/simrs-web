
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
                '<button class="btn btn-primary btn-sm"><i class="fa fa-edit"></i></button>' +
                '<button class="btn btn-danger btn-sm"><i class="fa fa-trash"></i></button>' +
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