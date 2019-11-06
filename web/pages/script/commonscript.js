/**
 *
 this script to handle common popup
 created by : ferdi
 created date : 25 Nov '07

 **/

/**
 * this method for show popup add, edit, and delete
 * ex : - refresh main page -> showDialogOpen(  '/aat-pelindo4/authorization/pagePopup.action',
 *                                              'id=12',
 *                                              'dialogHeight=500px,dialogWidth=400px',null,null,
 *                                              'user.type,user.address',
 *                                              '/aat-pelindo4/authorization/refresh.action',
 *                                              'user.userId,user.password,deliveryDate')
 *
 */
function showDialogOpen() {

    var action = arguments[0]; //action path
    var sendParamList = arguments[1] == null ? "" : arguments[1]; //parameter list for button edit from display table
    var windowSize = arguments[2]; //popup size
    var refreshMethod = arguments[3] == null ? "" : arguments[3]; //refresh method
    var searchParams = arguments[4] == null ? "" : arguments[4]; //list search criteria from main page, used to refresh main page

    var urlOpenPopup = action;

    //set param if come from display obj, then send params to popup
    if (sendParamList.length > 0) {

        urlOpenPopup += '?' + sendParamList;

    }

    if (window.showModalDialog) {
        window.showModalDialog(urlOpenPopup, null, windowSize);
    } else {
        window.open(urlOpenPopup, null, "width=300,height=300,left=300,modal=yes,alwaysRaised=yes", null);
    }

    if (refreshMethod.length > 0) {
        refreshMainPage(searchParams, refreshMethod);
    }

}


function refreshMainPage(searchParams, refreshAction) {

    var method = "post"; // Set method to post by default, if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", refreshAction);

    //get search param from main page
    var arrSearchParams = searchParams.split(',');
    var searchParamsList = '';
    var oSearch = null
    for (var i = 0; i < arrSearchParams.length; i++) {
        oSearch = eval("window.document.getElementsByName('" + arrSearchParams[i] + "')[0]");
        if (oSearch != null) {
            //update by ferdi, 15 des '07, for setting date picker at search page(main page) after refreshing
            if (oSearch.name.lastIndexOf("Date") != -1) {
                var datePicker = dojo.widget.byId(oSearch.name);
                if (datePicker != null) {

                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("type", "hidden");
                    hiddenField.setAttribute("name", arrSearchParams[i]);
                    hiddenField.setAttribute("value", datePicker.getDate());

                    form.appendChild(hiddenField);
                }
            } else {

                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", arrSearchParams[i]);
                hiddenField.setAttribute("value", oSearch.value);

                form.appendChild(hiddenField);
            }

        }
    }

    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);

}

/**
 * used to open new page on display object
 */
function openNewPage() {

    var action = arguments[0]; //action path
    var sendParamList = arguments[1] == null ? "" : arguments[1]; //parameter list for button edit from display table

    var urlOpenPage = action;

    if (sendParamList.length > 0) {
        urlOpenPage += '?' + sendParamList;
    }

    document.forms[0].action = urlOpenPage;
    document.forms[0].submit();

}



