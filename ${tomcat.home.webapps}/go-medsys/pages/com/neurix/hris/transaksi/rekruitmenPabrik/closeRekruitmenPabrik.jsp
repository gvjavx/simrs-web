<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RekruitmenPabrikAction.js"/>'></script>

    <script type="text/javascript">
        function cancelBtn() {
            $('#view_dialog_menu_view').dialog('close');
        }
    </script>
</head>
<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="rekruitmenPabrikForm" method="post"  theme="simple" namespace="/rekruitmenPabrik" action="save_rekruitmenPabrik.action" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Close Rekruitmen Pabrik</legend>

                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >
                    <tr>
                        <td>
                            <label class="control-label"><small>Rekruitmen Pegawai Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="rekruitmenPabrikId123" name="rekruitmenPabrik.rekruitmenPabrikId" required="true" readonly="true" cssClass="form-control"/>                                </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="rekruitmenPabrik.branchId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control" disabled="true"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBagian" namespace="/strukturJabatan" name="searchBagianUnit_strukturJabatan"/>
                                <s:select cssClass="form-control" list="#comboBagian.listComboStrukturJabatan" id="bagianId" required="true" disabled="true"
                                          listKey="bagian" listValue="bagianName" headerKey="" headerValue="[Select one]" name="rekruitmenPabrik.bagianId" />
                            </table>
                        </td>
                    </tr>
                </table>



                <br>
                <br>
                <center>
                    <table id="showdata" width="96%">
                        <tr>
                            <td align="center">
                                <table style="width: 96%;" class="rekruitmenDetailTable table table-bordered">
                                </table>
                            </td>
                        </tr>
                    </table>
                </center>
                <br>
                <div id="actions" class="form-actions">
                    <table align="center">
                        <tr>
                            <td>
                                <button type="button" id="btnClosed" class="btn btn-success">
                                    <i class="fa fa-check"></i> Closed
                                </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" onclick="cancelBtn()">
                                    <i class="fa fa-close"></i> cancel
                                </button>
                            </td>
                        </tr>
                    </table>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
<script>
    window.loadPerson =  function(){
        //alert(nip);
        $('.rekruitmenDetailTable').find('tbody').remove();
        $('.rekruitmenDetailTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        RekruitmenPabrikAction.searchPegawaiCalon(function(listdata) {

            tmp_table = "<thead style='font-size: 12px' ><tr class='active'>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Nip</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Nama</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>bagian</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Jabatan Lama</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Jabatan Baru</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Status Pegawai</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Tipe Pegawai</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Approval GM</th>"+
                    "<th style='text-align: center;color: #fff; background-color:  #3c8dbc'>Approval SDM</th>"+
                    "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var sukses = "<img border='0' src='<s:url value='/pages/images/icon_success.ico'/>' name='icon_trash'>";
                var fail = "<img border='0' src='<s:url value='/pages/images/icon_failure.ico'/>' name='icon_trash'>";
                var icon,icon2,klas1,klas2;
                if (item.approvalSdmFlag=="Y"){
                    icon=sukses;
                    klas1="<a class ='sukses' data ='"+item.nip+"' >";
                }
                else {
                    icon=fail;
                    klas1="<a href='javascript:;' class ='item-approvalSDM' data ='"+item.nip+"' >";
                }
                if (item.approvalGmFlag=="Y"){
                    icon2=sukses;
                    klas2="<a class ='sukses' data ='"+item.nip+"' >";
                }
                else {
                    icon2=fail;
                    klas2="<a href='javascript:;' class ='item-approvalGM' data ='"+item.nip+"' >";
                }
                tmp_table += '<tr style="font-size: 11px;" ">' +
                        '<td >' + (i + 1) + '</td>' +
                        '<td >' + item.nip + '</td>' +
                        '<td >' + item.namaPegawai + '</td>' +
                        '<td align="center">' + item.bagianName + '</td>' +
                        '<td align="center">' + item.posisiLamaName + '</td>' +
                        '<td align="center">' + item.posisiBaruName + '</td>' +
                        '<td align="center">' + item.statusPegawaiName + '</td>' +
                        '<td align="center">' + item.tipePegawaiName + '</td>' +
                        '<td align="center">' +
                        klas2+
                        icon2+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' +
                        klas1 +
                        icon+
                        '</a>' +
                        '</td>' +
                        "</tr>";
            });
            $('.rekruitmenDetailTable').append(tmp_table);
        });
    };
    $(document).ready(function() {
        loadPerson();
        $('.rekruitmenDetailTable').on('click', '.item-edit', function(){
            var nip = $(this).attr('data');
            dwr.engine.setAsync(false);
            RekruitmenPabrikAction.searchRekruitmenPabrikPerson(nip ,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#nipPosisi').val(item.nip);
                    $('#nipNamaPosisi').val(item.namaPegawai);
                    $('#posisiLamaPosisi').val(item.posisiLama).change();
                    $('#posisiBaruPosisi').val(item.posisiBaru).change();
                });
            });

            $('#modal-edit-posisi').find('.modal-title').text('Edit Posisi Baru');
            $('#modal-edit-posisi').modal('show');
            $('#myFormEdit').attr('action', 'editPerson');
        });
        $('.rekruitmenDetailTable').on('click', '.item-delete', function(){
            var nip = $(this).attr('data');
            dwr.engine.setAsync(false);
            RekruitmenPabrikAction.searchRekruitmenPabrikPerson(nip ,function(listdata) {
                $.each(listdata, function (i, item) {
                    $('#nipHapus').val(item.nip);
                    $('#nipNamaHapus').val(item.namaPegawai);
                    $('#posisiLamaHapus').val(item.posisiLama).change();
                    $('#posisiBaruHapus').val(item.posisiBaru).change();
                });
            });

            $('#modal-edit-hapus').find('.modal-title').text('Hapus Data');
            $('#modal-edit-hapus').modal('show');
            $('#myFormHapus').attr('action', 'deletePerson');
        });
        $('#btnSavePosisi').click(function(){
            var nip = $('#nipPosisi').val();
            var posisiBaru = $('#posisiBaruPosisi').val();
            if (posisiBaru!=null){
                RekruitmenPabrikAction.editRekruitmenPabrikPerson(nip,posisiBaru ,function(listdata) {
                    $('#modal-edit-posisi').modal('hide');
                    $('#myFormEdit')[0].reset();
                    location.reload()
                });
            } else {
                alert ("masukkan posisi baru");
            }
            alert('Data Successfully Updated');
        });
        $('#btnClosed').click(function(){
            var id=$('#rekruitmenPabrikId123').val();
            if (confirm(" apakah anda ingin melakukan close pada data rekruitmen ini ? ")){
                RekruitmenPabrikAction.saveClosedRekruitmenPabrik(id,function(listdata) {
                    location.reload()
                });
                alert('Data Successfully closed');
            }

        });
    });
</script>


