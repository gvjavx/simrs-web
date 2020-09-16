function startSpinner(id, val){
    var url = contextPathHeader+'/pages/images/spinner.gif';
    $('#'+id+val).attr('src',url).css('width', '30px', 'height', '40px');
}
function stopSpinner(id, val){
    var url = contextPathHeader+'/pages/images/icons8-test-passed-25-2.png';
    $('#'+id+val).attr('src',url).css('width', '', 'height', '');
}
function startLoad() {
    $('#loading_page').show();
}
function stopLoad(id){
    $('#loading_page').hide();
}

function setLabelJenisPasien(id, jenis){
    if(jenis == 'asuransi'){
        $('#'+id).attr('style', 'background-color: #ffff00; color: black; border-radius: 5px; border: 1px solid black; padding: 5px');
    }else if(jenis == 'umum'){
        $('#'+id).attr('style', 'background-color: #4d4dff; color: white; border-radius: 5px; border: 1px solid black; padding: 5px');
    }else if(jenis == 'bpjs'){
        $('#'+id).attr('style', 'background-color: #00b300; color: white; border-radius: 5px; border: 1px solid black; padding: 5px');
    }else if(jenis == 'ptpn'){
        $('#'+id).attr('style', 'background-color: #66ff33; color: black; border-radius: 5px; border: 1px solid black; padding: 5px');
    }else{
        $('#'+id).attr('style', 'background-color: #cc3399; color: white; border-radius: 5px; border: 1px solid black; padding: 5px');
    }
}
function startSpin(id){
    var url = contextPathHeader+'/pages/images/spinner.gif';
    $('#'+id).attr('src',url).css('width', '30px', 'height', '40px');
}
function stopSpin(id){
    var url = contextPathHeader+'/pages/images/cancel-flat-new.png';
    $('#'+id).attr('src',url).css('width', '', 'height', '');
}

function startIconSpin(id){
    $('#'+id).removeClass("fa fa fa-trash");
    $('#'+id).addClass("fa fa-circle-o-notch fa-spin");
}
function stopIconSpin(id){
    $('#'+id).removeClass("fa fa-circle-o-notch fa-spin");
    $('#'+id).addClass("fa fa fa-trash");
}