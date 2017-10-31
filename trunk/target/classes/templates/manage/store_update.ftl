<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>会员新增或修改</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/assets/bootstrap-tables/css/bootstrap-table.css">
    <link rel="stylesheet" href="/aw/assets/bootstrap-dateTimePicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
    <link rel="stylesheet" href="/aw/assets/city-data/css/city-picker.css">
    <link rel="stylesheet" href="/aw/assets/bootstrap-dateTimePicker/css/bootstrap-datetimepicker.min.css">
    <style>
        .city-picker-span{
            width:100%;height:34px;line-height:33px;
        }
        .margin_b20{margin-bottom: 25px;}
        .r{min-width:1000px;}
        .r1{min-width:1200px;}
    </style>
</head>
<body style="width: 1490;overflow: auto;">
    <div class="form-horizontal" role="form" id="userForm">
        <div style="padding: 20px 20px 20px 20px">
            <div class="row r">
                <div class="col-md-2 ">
                        <form id="action" method="post" enctype="multipart/form-data">
                            <div id="preview" class="ps-image" style="width:200px;height:200px;border:1px solid #CCC;background:url(${(store.showUrl1)!'/aw/img/add_img.png'}) no-repeat;background-size:200px 200px">
                                <input type="file" id="picTitle" class="checked" name="upfile" accept="image" value="${(store.showUrl)!''}" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
                                <input type="hidden" id="changeId"/>
                                <input type="hidden" value="${(store.showUrl)!''}" id="picTitleId"/>
                            </div>
                        </form>
                </div>
                <div class="col-md-10 ">
                        <input type="hidden" value="${(store.storeId)!''}" id="storeId">
                        <input type="hidden" value="${(store.bsid)!''}" id="bsid">
                        <input type="hidden" value="${(store.smid)!''}" id="smid">
                        <div class="row margin_b20">
                            <div class="col-md-4 ">
                                <label class="control-label">店铺名称：</label>
                                <input type="text" class="form-control" minlength="1" maxlength="30" placeholder="请输入店铺名称" value="${(store.name)!''}" id="name">
                            </div>
                            <div class="col-md-4 ">
                                <label class="control-label" >店铺品牌：</label>
                                <select id="brand" class="js-example-tags form-control"  name="brand">
                                <#list brand as b>
                                    <#if (store.brandNo)??>
                                        <#if store.brandNo == b.brandNo>
                                            <option value=${b.brandNo} selected="selected">${b.name}</option>
                                        <#else>
                                            <option value=${b.brandNo}>${b.name}</option>
                                        </#if>
                                    <#else>
                                        <option value=${b.brandNo}>${b.name}</option>
                                    </#if>
                                </#list>
                                </select>
                            </div>
                            <div class="col-md-4 ">
                                <label class="control-label  ">店铺负责人：</label>
                                <input type="text" class="form-control " minlength="1" maxlength="15" placeholder="请输入店铺负责人" value="${(store.principal)!''}" id="principal">
                            </div>
                        </div>
                        <div class="row margin_b20">
                            <div class="col-md-4 ">
                                <label class="control-label ">店铺联系人：</label>
                                <input class="form-control  " minlength="1" maxlength="15" placeholder="请输入店铺联系人" value="${(store.contacter)!''}" id="contacter">
                            </div>
                            <div class="col-md-4 ">
                                <label class="control-label ">店铺电话：</label>
                                <input type="text" class="form-control" minlength="1" maxlength="19" placeholder="请输入店铺电话" value="${(store.storePhone)!''}" id="storePhone">
                            </div>
                            <div class="col-md-4 ">
                                <label class="control-label ">联系电话：</label>
                                <input type="text" class="form-control  " minlength="1" maxlength="19" placeholder="请输入联系电话" value="${(store.contactPhone)!''}" id="contactPhone">
                            </div>
                        </div>
                        <div class="row margin_b20">
                            <div class="col-md-4 ">
                                <label class="control-label ">提供服务：</label>
                                <input type="text" class="form-control" maxlength="200" placeholder="请输入提供服务" value="${(store.provideService)!''}" id="provideService">
                            </div>
                            <div class="col-md-4 ">
                                <label class="control-label ">店铺门牌号：</label>
                                <input type="text" class="form-control " minlength="1" maxlength="19" placeholder="请输入店铺门牌号" value="${(store.doorplate)!''}" id="doorplate">
                            </div>
                            <div class="col-md-4 ">
                                <label class="control-label">营业时间：</label>
                                <input type="text" class="form-control " minlength="1" maxlength="49" placeholder="请输入营业时间" value="${(store.openingHours)!''}" id="openingHours">
                            </div>
                        </div>

                </div>
            </div>
            <div class="row r1 margin_b20">
                <div class=" col-md-3 ">
                    <label class="control-label ">场馆：</label>
                    <select id="mall" class="js-example-tags form-control  " name="mall">
                    <#list mall as m>
                        <#if (store.mallId)??>
                            <#if store.mallId == m.mallId>
                                <option value=${m.mallId} selected="selected">${m.name}</option>
                            <#else>
                                <option value=${m.mallId}>${m.name}</option>
                            </#if>
                        <#else>
                            <option value=${m.mallId}>${m.name}</option>
                        </#if>
                    </#list>
                    </select>
                </div>
                <div class=" col-md-3 ">
                    <label class="control-label ">楼层：</label>
                    <select id="floor" class="js-example-tags form-control" name="floor">
                    <#list 1..9 as t>
                        <#if (store.floor)??>
                            <#if store.floor == t>
                                <option value=${t} selected="selected">${t}F</option>
                            <#else>
                                <option value=${t} >${t}F</option>
                            </#if>
                        <#else>
                            <option value=${t}>${t}F</option>
                        </#if>
                    </#list>
                    </select>
                </div>
                <div class=" col-md-3 ">
                    <label class="control-label " style="width:30%; float:left;">店铺地址：</label>
                    <div style="position: relative; width:70%; float:right;">
                        <input id="address" class="form-control " name="address" type="text"/>
                    </div>
                </div>
                <div class=" col-md-3 ">
                    <label class="control-label ">详细地址：</label>
                    <input type="text" class="form-control " minlength="1" maxlength="199" placeholder="请选择详细地址" value="${(store.address)!''}" id="detailAddress">
                </div>
            </div>
            <div class="row r1 margin_b20">
                <div class=" col-md-3 ">
                    <label class="control-label ">店铺经度：</label>
                    <input type="text" id="longitude" name="companyLongitude" value="${(store.longitude)!''}" readonly="true" placeholder="请点击地图选择按钮" class="form-control  ">
                </div>
                <div class=" col-md-3 ">
                    <label class="control-label " style="float: left; ">店铺纬度：</label>
                    <input type="text" id="latitude" name="companyLatitude" value="${(store.latitude)!''}" readonly="true" placeholder="请点击地图选择按钮" class="form-control  ">
                </div>
                <div class=" col-md-3 ">
                    <button type="button"  class="btn btn-primary" style="margin-left: 30px;" onclick="showMap()">地图选择</button>
                </div>
                <div class=" col-md-3 ">
                <#if (store.storeId)??>
                    <button class="btn btn-success radius" id="update" style="margin-left: 20px;" onclick="update();" value="${(store.storeId)!''}">保存</button>
                <#else>
                    <button class="btn btn-success radius" id="add" style="margin-left: 20px;"  onclick="add();">添加</button>
                </#if>
                </div>
            </div>

        </div>

    </div>

    <div id="mapDiv" style="padding-left:40px;display: none">
        <label class="col-sm-1  control-label" style="padding: 5px 0 0 0;margin: 0;">快速定位：</label>
        <div class="col-sm-2 " style="padding-left: 0;">
            <input id="text_" type="text" class="form-control" placeholder="请输入你要查询的地址" onkeypress="if(event.keyCode==13) {searchByStationName();}"/>
        </div>
        <button type="button" class="btn btn-default"  style="height:35px;" onclick="searchByStationName();">
            <span class="glyphicon glyphicon-search" style="padding-bottom: 4px;">查询</span>
        </button>
        <div id="allmap" style="width:1440px;height:490px;margin-top:15px;padding: 50 100 0 100"></div>
    </div>
<script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="/aw/js/jquery.form.js"></script>
<script src="/aw/js/bootstrap.min.js"></script>
<script src="/aw/assets/layer/layer.js"></script>
<script src="/aw/assets/icheck/jquery.icheck.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/aw/assets/bootstrap-tables/js/jquery.dataTables.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/dataTables.bootstrap.min.js"></script>
<script src="/aw/assets/city-data/js/city-picker.data.js"></script>
<script src="/aw/assets/city-data/js/city-picker.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=8vZTXUNkRraY09RFkx0EupdHALhgSzyX"></script>
<script type="text/javascript" charset="utf-8">
    var map;
    var localSearch;
    var pattern = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    $(function () {
            if('${store.country}' != "kong"){
                var country = '${store.country}';
                var province ='${store.province}';
                var city = '${store.city}';
                var address = country+"/"+province+"/"+city;
                $("#address").val(address)
            }
        initCityPicker('address');
        $preview = document.getElementById("preview"),
        $logoImg = document.getElementById("picTitle"),
        initFileReader();
        // 初始化地图
        map = new BMap.Map("allmap", {minZoom: 1, maxZoom: 18});    // 创建Map实例
        map.centerAndZoom("常州", 10);  // 初始化地图,设置中心点坐标和地图级别
        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
        map.setCurrentCity("常州");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
        map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
        map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
        map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
        map.addControl(new BMap.OverviewMapControl({isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));   //右下角，打开
        localSearch = new BMap.LocalSearch(map);
        localSearch.enableAutoViewport(); //允许自动调节窗体大小
    });

    function initFileReader(){
        if ( typeof(FileReader) === 'undefined' ){
            layer.msg("抱歉，你的浏览器不支持 FileReader，请使用现代浏览器操作！");
            $logoImg.setAttribute( 'disabled','disabled' );
        } else {
            $logoImg.addEventListener('change',readFile,false );
        }
        function readFile(){
            var file = this.files[0];
            //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件
            if($logoImg.value==''){
                $preview.style.width='200px';
                $preview.style.height='200px';
                $preview.style.border='1px solid #CCC';
                $preview.style.background="url('/aw/img/add_img.png') no-repeat";
                $preview.style.backgroundSize='130px 130px';
            }else{

                if(!/image\/\w+/.test(file.type)){
                    layer.msg("请确保文件为图像类型！");
                    return false;
                }
                if(file.size >1048576){
                    layer.msg('文件不允许超过1M！')
                    return false;
                }
                $("#changeId").val("change");
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function(e){
                    $preview.style.width='200px';
                    $preview.style.height='200px';
                    $preview.style.border='1px solid #CCC';
                    $preview.style.background="url('"+this.result+"') no-repeat";
                    $preview.style.backgroundSize='200px 200px';
                }
            }
        }
    }

    function add(){
        var storeId = $("#storeId").val();
        var name = $("#name").val();
        var brand = $("#brand").val();
        var principal = $("#principal").val();
        var contacter = $("#contacter").val();
        var contactPhone = $("#contactPhone").val();
        var storePhone = $("#storePhone").val();
        var doorplate = $("#doorplate").val();
        var provideService=$("#provideService").val();
        var openingHours = $("#openingHours").val();
        var floor = $("#floor").val();
        var mall = $("#mall").val();
        var address = $("#address").val();
        var detailAddress = $("#detailAddress").val();
        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();
        if(name!=undefined&&name!=""&&brand!=undefined&&brand!=""&&principal!=undefined&&principal!=""&&contactPhone!=undefined&&contactPhone!=""
                &&contacter!=undefined&&contacter!=""&&storePhone!=undefined&&storePhone!="" &&doorplate!=undefined&&doorplate!=""
                &&openingHours!=undefined&&openingHours!=""&&floor!=undefined&&floor!="" &&mall!=undefined&&mall!=""
                &&address!=undefined&&address!=""&&detailAddress!=undefined&&detailAddress!="" &&longitude!=undefined&&longitude!=""
                &&latitude!=undefined&&latitude!=""&&$("#changeId").val()!=''&&$("#changeId").val()!=undefined&&$("#changeId").val()!=null) {
            if(pattern.test(contactPhone)) {
                $("#action").ajaxSubmit({
                    type: 'post',
                    dataType: "jsonp",
                    url: "/ueditor/controller?action=uploadimage",
                    success: function (data) {
                        if (data.state == 'SUCCESS') {
                            var showUrl = data.url;
                            $.post('/manage/store/saveOrUpdate', {
                                bsid: $("#bsid").attr("value"),
                                smid: $("#smid").attr("value"),
                                storeId: storeId,
                                name: name,
                                brand: brand,
                                principal: principal,
                                contacter: contacter,
                                contactPhone: contactPhone,
                                storePhone: storePhone,
                                doorplate: doorplate,
                                openingHours: openingHours,
                                floor: floor,
                                mall: mall,
                                address: address,
                                detailAddress: detailAddress,
                                longitude: longitude,
                                latitude: latitude,
                                provideService: provideService,
                                showUrl: showUrl,
                            }, function (res) {
                                if (res.count == 1) {
                                    layer.msg("添加成功")
                                    refresh()
                                } else {
                                    layer.msg("添加失败");
                                }

                            });
                        } else {
                            layer.msg("添加失败");
                        }
                    },
                    error : function(XmlHttpRequest, textStatus, errorThrown){
                    }
                });
            }else{
                layer.msg('联系电话格式不正确')
            }
        }else{
            layer.msg('数据不完整')
        }
    }

            function update(){
                var storeId = $("#storeId").val();
                var name = $("#name").val();
                var brand = $("#brand").val();
                var principal = $("#principal").val();
                var contacter = $("#contacter").val();
                var contactPhone = $("#contactPhone").val();
                var storePhone = $("#storePhone").val();
                var doorplate = $("#doorplate").val();
                var provideService=$("#provideService").val();
                var openingHours = $("#openingHours").val();
                var floor = $("#floor").val();
                var mall = $("#mall").val();
                var address = $("#address").val();
                var detailAddress = $("#detailAddress").val();
                var longitude = $("#longitude").val();
                var latitude = $("#latitude").val();
                if(name!=undefined&&name!=""&&brand!=undefined&&brand!=""&&principal!=undefined&&principal!=""&&contactPhone!=undefined&&contactPhone!=""
                        &&contacter!=undefined&&contacter!=""&&storePhone!=undefined&&storePhone!="" &&doorplate!=undefined&&doorplate!=""
                        &&openingHours!=undefined&&openingHours!=""&&floor!=undefined&&floor!="" &&mall!=undefined&&mall!=""
                        &&address!=undefined&&address!=""&&detailAddress!=undefined&&detailAddress!="" &&longitude!=undefined&&longitude!=""
                        &&latitude!=undefined&&latitude!="") {
                    if(pattern.test(contactPhone)) {
                        if ($("#changeId").val() != '' && $("#changeId").val() != undefined && $("#changeId").val() != null) {
                            $("#action").ajaxSubmit({
                                type: 'post',
                                dataType: "jsonp",
                                url: "/ueditor/controller?action=uploadimage",
                                success: function (data) {
                                    if (data.state == 'SUCCESS') {
                                        var showUrl = data.url;
                                        $.post('/manage/store/saveOrUpdate', {
                                            bsid: $("#bsid").attr("value"),
                                            smid: $("#smid").attr("value"),
                                            storeId: storeId,
                                            name: name,
                                            brand: brand,
                                            principal: principal,
                                            contacter: contacter,
                                            contactPhone: contactPhone,
                                            storePhone: storePhone,
                                            doorplate: doorplate,
                                            openingHours: openingHours,
                                            floor: floor,
                                            mall: mall,
                                            address: address,
                                            detailAddress: detailAddress,
                                            longitude: longitude,
                                            latitude: latitude,
                                            provideService: provideService,
                                            showUrl: showUrl,
                                        }, function (res) {
                                            if (res.count == 1) {
                                                layer.msg("保存成功")
                                                refresh()
                                                $('#table').bootstrapTable('refresh');
                                            } else {
                                                layer.msg("保存失败");
                                            }

                                        });
                                    } else {
                                        layer.msg("保存失败");
                                    }
                                },
                                error: function (XmlHttpRequest, textStatus, errorThrown) {
                                }
                            });
                        } else {
                            showUrl = $("#picTitleId").val();
                            $.post('/manage/store/saveOrUpdate', {
                                bsid: $("#bsid").attr("value"),
                                smid: $("#smid").attr("value"),
                                storeId: storeId,
                                name: name,
                                brand: brand,
                                principal: principal,
                                contacter: contacter,
                                contactPhone: contactPhone,
                                storePhone: storePhone,
                                doorplate: doorplate,
                                openingHours: openingHours,
                                floor: floor,
                                mall: mall,
                                address: address,
                                detailAddress: detailAddress,
                                longitude: longitude,
                                latitude: latitude,
                                provideService: provideService,
                                showUrl: showUrl,
                            }, function (res) {
                                if (res.count == 1) {
                                    layer.msg("保存成功")
                                    refresh()
                                } else {
                                    layer.msg("保存失败");
                                }

                            });
                        }
                    }else{
                        layer.msg('联系电话格式不正确')
                    }
                  }else{
                            layer.msg('数据不完整')
                }
                }

    /** 初始化三级地区联动 */
    function initCityPicker(id) {
        $('#' + id).citypicker('destroy');
        $('#' + id).citypicker();
    }

    // 初始化带有分秒的时间框
    function initDateTimePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
    }

    function refresh() {
        setTimeout(function(){
           location.reload();
        },800);
    }

    /**
     * 时间格式化，传递进来的时间
     */
    function formatterDate(value) {
        if (value == undefined || value == null || value == '') {
            return "";
        }
        else {
            var date = new Date(value);
            var year = date.getFullYear().toString();
            var month = (date.getMonth() + 1);
            var day = date.getDate().toString();
            var hour = date.getHours().toString();
            var minutes = date.getMinutes().toString();
            var seconds = date.getSeconds().toString();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (seconds < 10) {
                seconds = "0" + seconds;
            }
            return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
        }
    }

    // 初始化没有分秒的时间框
    function initDatePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
    }

    function showInfo(e) {
        $("#longitude").val(e.point.lng);
        $("#latitude").val(e.point.lat);
    }


    function searchByStationName() {
        map.clearOverlays();//清空原来的标注
        var keyword = document.getElementById("text_").value;
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            map.centerAndZoom(poi.point, 13);
            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
            map.addOverlay(marker);
            var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
            var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
            marker.addEventListener("click", function () {
                this.openInfoWindow(infoWindow);
            });
            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        });
        localSearch.search(keyword);
    }

    function blurredQuery(){
        initTable('table', '/manage/user/userList');
    }

    function showMap(){
        $("#mapDiv").css("display","block");
        map.addEventListener("click", showInfo);
    }

    function initDatePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
    }
</script>
</body>
</html>

