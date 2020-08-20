function startSpinner(id, val){
    var url = contextPathHeader+'/pages/images/spinner.gif';
    $('#'+id+val).attr('src',url).css('width', '30px', 'height', '40px');
}
function stopSpinner(id, val){
    var url = contextPathHeader+'/pages/images/icons8-test-passed-25-2.png';
    $('#'+id+val).attr('src',url).css('width', '', 'height', '');
}