<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
        .box_shadow {
            box-shadow: 4px 8px 12px grey;
        }

        .form-check {
            display: inline-block;
            padding-left: 2px;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content: '';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 7px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 0px;
            left: 7px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/InitDashboardAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="content-wrapper">
    <section class="content-header">
        <h1>
            Dashboard
        </h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3">
                                Bulan
                                <select class="form-control select2" id="set_bulan" onchange="setAllCount()">
                                    <option value="">[Select One]</option>
                                    <option value="0">Januari</option>
                                    <option value="1">Februari</option>
                                    <option value="2">Maret</option>
                                    <option value="3">April</option>
                                    <option value="4">Mei</option>
                                    <option value="5">Juni</option>
                                    <option value="6">Juli</option>
                                    <option value="7">Agustus</option>
                                    <option value="8">September</option>
                                    <option value="9">Oktober</option>
                                    <option value="10">November</option>
                                    <option value="11">Desember</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                Tahun
                                <select class="form-control select2" id="set_tahun" onchange="setAllCount()">
                                    <option value="">[Select One]</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-dokter.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Jalan</span>
                                        <span class="info-box-number"><span id="sum_rj"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 30px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-perawat.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">IGD</span>
                                        <span class="info-box-number"><span id="sum_igd"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix visible-sm-block"></div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-poli.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Rawat Inap</span>
                                        <span class="info-box-number"><span id="sum_ri"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6 col-xs-12">
                                <div class="info-box box_shadow">
                                    <span class="info-box-icon bg-green"><img
                                            style="width: 50px; height: 50px; margin-top: 18px"
                                            src="<s:url value="/pages/images/logo-pasien.png"/>"></span>
                                    <div class="info-box-content">
                                        <span class="info-box-text">Telemedic</span>
                                        <span class="info-box-number"><span id="sum_telemedic"></span><small> Orang</small></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="line-chart" style="height: 300px; width: 100%"></div>
                                </div>
                                <div class="row" id="nama_rs">
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="col-md-2">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="sales-chart-0"
                                         style="height: 300px; position: relative;"></div>
                                    <p class="text-center" style="margin-top: -85px"><b>RS Gatoel</b></p>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="sales-chart-1"
                                         style="height: 300px; position: relative;"></div>
                                    <p class="text-center" style="margin-top: -85px"><b>RS Gatoel</b></p>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="sales-chart-2"
                                         style="height: 300px; position: relative;"></div>
                                    <p class="text-center" style="margin-top: -85px"><b>RS Gatoel</b></p>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="sales-chart-3"
                                         style="height: 300px; position: relative;"></div>
                                    <p class="text-center" style="margin-top: -85px"><b>RS Gatoel</b></p>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="sales-chart-4"
                                         style="height: 300px; position: relative;"></div>
                                    <p class="text-center" style="margin-top: -85px"><b>RS Gatoel</b></p>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="box-body chart-responsive">
                                    <div class="chart" id="sales-chart-5"
                                         style="height: 300px; position: relative;"></div>
                                    <p class="text-center" style="margin-top: -85px"><b>RS Medika Utama</b></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<script type='text/javascript'>

    $(document).ready(function () {
        $('#dashboard').addClass("active");
        setTahun();
        setBranch();
    });

    function setTahun() {
        var optiton = "";
        dwr.engine.setAsync(true);
        InitDashboardAction.getTahunPeriksa({
            callback: function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        optiton += '<option value="' + item.tahun + '">' + item.tahun + '</option>';
                    });
                }
                $('#set_tahun').html(optiton);
                var now = new Date();
                var year = now.getFullYear();
                var bulan = now.getMonth();
                $('#set_bulan').val(bulan).trigger('change');
                $('#set_tahun').val(year).trigger('change');
            }
        });
    }

    function setBranch() {
        var namaRS = "";
        dwr.engine.setAsync(true);
        InitDashboardAction.getComboBranch({
            callback: function (res) {
                if (res.length > 0) {
                    $.each(res, function (i, item) {
                        var color = getRandomColor();
                        namaRS += '<div class="col-md-2 text-center">\n' +
                            '<div class="form-check">\n' +
                            '<input checked="true" type="checkbox" name="cek_nama_rs" id="id_nama_rs_' + i + '" value="'+item.branchId+'">\n' +
                            '<label for="id_nama_rs_' + i + '"></label> <span style="color:' + color + '">'+item.branchName+'</span>\n' +
                            '</div>\n' +
                            '</div>';
                    });
                }
                $('#nama_rs').html(namaRS);
            }
        });
    }

    function setAllCount() {
        var bulan = $('#set_bulan').val();
        var tahun = $('#set_tahun').val();
        if(bulan && tahun != ''){
            dwr.engine.setAsync(true);
            InitDashboardAction.getCountAll({
                callback: function (response) {
                    $('#sum_rj').text(response.jmlRJ);
                    $('#sum_ri').text(response.jmlRI);
                    $('#sum_igd').text(response.jmlIGD);
                    $('#sum_telemedic').text(response.jmlTelemedic);
                }
            });

            var branchId = $('[name=cek_nama_rs]');
            var awal = "(";
            var akhir = ")";
            var isi = "";
            var branch = "";
            $.each(branchId, function (i, item) {
                if(item.checked){
                    if(isi != ""){
                        isi = isi+", '"+item.value+"'";
                    }else{
                        isi ="'"+item.value+"'";
                    }
                }
            });
            if(isi != ""){
                branch = awal+isi+akhir;
                var tempBranch = "";
                var tempTgl = "";
                var tempTotal = "";
                var dataBranch = [];
                var dataChart = [];

                dwr.engine.setAsync(true);
                InitDashboardAction.getKunjuganRJ(bulan, tahun, branch, {
                    callback: function (response) {
                        $.each(response, function (i, item) {
                            var tanggal = "";
                            if(item.tanggal != null && item.tanggal != ''){
                                tanggal = converterDate(item.tanggal);
                            }
                            if(tempBranch != item.branchId){
                                tempBranch = item.branchId;
                                dataBranch.push({
                                    'branch_id':item.branchId,
                                    'branch_name':item.branchName,
                                    'tanggal':item.tanggal,
                                    'total':item.total
                                });
                            }
                            dataChart.push({
                                'id_branch':item.branchId,
                                'nama_branch':item.branchName,
                                'tanggal':item.tanggal,
                                'total':item.total
                            })
                        });
                        var a = "{";
                        var b = "}";
                        if(dataBranch.length > 0 && dataChart.length > 0){
                            var temp = "";
                            $.each(dataBranch, function (i, item) {
                                var count = i+1;
                                if(temp != ""){
                                    temp = temp+', '+'item'+count;
                                }else{
                                    temp = 'item'+count;
                                }
                                // $.each(dataChart, function (ix, itemx) {
                                //     var tem = 'item'+i+1;
                                //     if(item.branch_id == itemx.id_branch){
                                //         console.log(itemx.tanggal);
                                //         console.log(itemx.total);
                                //     }
                                // });
                            });
                            console.log(a+temp+b);
                        }
                        var line = new Morris.Line({
                            element: 'line-chart',
                            resize: true,
                            data: [
                                {y: '2011 Q1', item1: 2666, item2: 12},
                                {y: '2011 Q2', item1: 2778, item2: 3423},
                                {y: '2011 Q3', item1: 4912, item2: 12},
                                {y: '2011 Q4', item1: 3767, item2: 31},
                            ],
                            xkey: 'y',
                            ykeys: ['item1','item2'],
                            labels: ['Item 1','item2'],
                            lineColors: ['#3c8dbc'],
                            hideHover: 'auto'
                        });
                    }
                });

            }
            // for (var i = 0; i < 6; i++) {
            //     var donut = new Morris.Donut({
            //         element: 'sales-chart-' + i,
            //         resize: true,
            //         colors: ["#4d4dff", "#0F9E5E", "#ffff00", "#66ff33", "#cc3399", "#f56954"],
            //         data: [
            //             {label: "Umum", value: 12},
            //             {label: "BPJS", value: 30},
            //             {label: "Asuransi", value: 20},
            //             {label: "Rekanan", value: 20},
            //             {label: "Medichal Checkup", value: 20},
            //             {label: "Promo", value: 20}
            //         ],
            //         hideHover: 'auto'
            //     });
            // }
        }
    }

    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>