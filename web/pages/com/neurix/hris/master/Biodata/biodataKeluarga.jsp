<div id="modal-editKeluarga" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeluarga1">

                    <div style="visibility: hidden" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="keluargaId" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Name : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="keluargaName" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status Keluarga :</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="statusKeluarga" >
                                <option value="Istri">Istri</option>
                                <option value="Suami">Suami</option>
                                <option value="Anak">Anak</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Lahir : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="tanggalLahirkeluarga" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveKeluarga" type="button" class="btn btn-default btn-success">Simpan</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div id="deleteModalKeluarga" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormKeluargaDelete">

                    <div style="visibility: hidden" class="form-group">
                        <label class="control-label col-sm-3" >Id : </label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="keluargaIdDelete" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Name : </label>
                        <div class="col-sm-6">
                            <input readonly type="text" class="form-control" id="keluargaNameDelete" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status Keluarga :</label>
                        <div class="col-sm-6">
                            <select readonly="true" class="form-control" id="statusKeluargaDelete" >
                                <option value="Istri">Istri</option>
                                <option value="Suami">Suami</option>
                                <option value="Anak">Anak</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Lahir : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="tanggalLahirkeluargaDelete" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnDeleteKeluarga" type="button" class="btn btn-default btn-success">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<h3>Keluarga
    <button id="btnAddKeluarga" type="button" class="btn btn-default btn-success" data-toggle="modal" data-target="#modal-tambah"><i class="fa fa-plus"></i> </button>
</h3>
<table style="width: 80%;" class="keluargaTable table table-bordered">
</table>


<script>
    $(document).ready(function(){

        function loadKeluarga(nip){
            //alert(nip);
            $('.keluargaTable').find('tbody').remove();
            $('.keluargaTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            KeluargaAction.searchData(nip, "", function(listdata) {

                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>No</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>Name</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>Status Keluarga</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>Tanggal Lahir</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>Edit</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>Delete</th>"+
                        "</tr></thead>";

                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td >' + i + '</td>' +
                            '<td >' + item.name + '</td>' +
                            '<td align="center">' + item.statusKeluarga + '</td>' +
                            '<td align="center">' + (myDate.getDate()) +' - '+ ("0" + (myDate.getMonth() + 1)).slice(-2) +' - '+myDate.getFullYear() + '</td>' +
                                /*'<td align="center">' + myDate.toTimeString("dd-mm-yy") + '</td>' +*/
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-edit' data ='"+item.keluargaId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_edit.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-delete' data ='"+item.keluargaId+"' >" +
                            "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                            '</a>' +
                            '</td>' +
                            "</tr>";
                });
                $('.keluargaTable').append(tmp_table);
            });

        }

        $('#btnSaveKeluarga').click(function(){
            var url = $('#myFormKeluarga').attr('action');

            var id         = document.getElementById("keluargaId").value;
            var nip         = document.getElementById("nip1").value;

            var keluargaName    = document.getElementById("keluargaName").value;
            var statusKeluarga  = document.getElementById("statusKeluarga").value;
            var tanggalLahir    = document.getElementById("tanggalLahirkeluarga").value;

            var result = '';

            if(url == 'addKeluarga'){
                if(nip != ''){
                    if(keluargaName == ''){
                        alert('Name Must be Entry')
                    }else{
                        if (confirm('Are you sure you want to save this Record?')) {
                            dwr.engine.setAsync(false);
                            /*function errh(msg, exc) {
                             alert('NIP salah, atau tidak tersedia pada login user. Mohon periksa kembali');
                             }

                             dwr.engine.setErrorHandler(errh);*/
                            KeluargaAction.saveAdd(nip, keluargaName, statusKeluarga, tanggalLahir, function(listdata) {
                                alert('Data Successfully Added');
                                $('#modal-editKeluarga').modal('hide');
                                $('#myFormKeluarga')[0].reset();
                                loadKeluarga(nip);
                            });
                        }
                    }
                }else{
                    alert('nip must be Entry');
                }
            }else{
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    KeluargaAction.saveEdit(id, keluargaName, statusKeluarga, tanggalLahir, function(listdata) {
                        alert('Data Successfully Updated');
                        $('#modal-editKeluarga').modal('hide');
                        $('#myFormKeluarga')[0].reset();
                        loadKeluarga(nip);
                    });
                }
            }


        });

        $('#btnAddKeluarga').click(function(){
            $('#myFormKeluarga')[0].reset();
            //document.myFormKeluarga.reset();

            $('#modal-editKeluarga').modal('show');
            $('#myFormKeluarga').attr('action', 'addKeluarga');
            $('#modal-editKeluarga').find('.modal-title').text('Add Keluarga');
        });

        $('.keluargaTable').on('click', '.item-edit', function(){
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;

            KeluargaAction.searchData(nip, id,function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    $('#keluargaName').val(item.name);
                    $('#statusKeluarga').val(item.statusKeluarga);
                    $('#tanggalLahirkeluarga').val((myDate.getDate()) +'-'+ ("0" + (myDate.getMonth() + 1)).slice(-2) +'-'+myDate.getFullYear());
                    $('#keluargaId').val(item.keluargaId);
                });
            });
            $('#modal-editKeluarga').find('.modal-title').text('Edit Data');
            $('#modal-editKeluarga').modal('show');
            $('#myFormKeluarga').attr('action', 'editKeluarga');
        });

        $('.keluargaTable').on('click', '.item-delete', function(){
            var id = $(this).attr('data');
            var nip = document.getElementById("nip1").value;

            KeluargaAction.searchData(nip, id,function(listdata) {
                $.each(listdata, function (i, item) {
                    var myDate = new Date(item.tanggalLahir);
                    $('#keluargaNameDelete').val(item.name);
                    $('#statusKeluargaDelete').val(item.statusKeluarga);
                    $('#tanggalLahirkeluargaDelete').val((myDate.getDate()) +'-'+ ("0" + (myDate.getMonth() + 1)).slice(-2) +'-'+myDate.getFullYear());
                    $('#keluargaIdDelete').val(item.keluargaId);
                });
            });

            $('#deleteModalKeluarga').modal('show');
            //prevent previous handler - unbind()
            $('#btnDeleteKeluarga').unbind().click(function(){
                if (confirm('Are you sure you want to Delete this Record?')) {
                    KeluargaAction.saveDelete(id, function(listdata) {
                        $('#deleteModalKeluarga').modal('hide');
                        $('#myFormKeluarga')[0].reset();
                        alert('Record has been Deleted successfully.');
                        loadKeluarga(nip);
                    });
                }
            });
        });

        $('#tanggalLahirkeluarga').datepicker({
            dateFormat: 'dd-mm-yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0"
        });

    });
</script>

