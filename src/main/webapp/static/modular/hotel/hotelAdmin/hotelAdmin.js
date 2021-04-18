/**
 * 管理员管理初始化
 */
var HotelAdmin = {
    id: "HotelAdminTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelAdmin.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '密码', field: 'password', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HotelAdmin.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelAdmin.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加管理员
 */
HotelAdmin.openAddHotelAdmin = function () {
    var index = layer.open({
        type: 2,
        title: '添加管理员',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelAdmin/hotelAdmin_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看管理员详情
 */
HotelAdmin.openHotelAdminDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '管理员详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelAdmin/hotelAdmin_update/' + HotelAdmin.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除管理员
 */
HotelAdmin.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelAdmin/delete", function (data) {
            Feng.success("删除成功!");
            HotelAdmin.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelAdminId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = HotelAdmin.initColumn();
    var table = new BSTable(HotelAdmin.id, "/hotelAdmin/list", defaultColunms);
    table.setPaginationType("client");
    HotelAdmin.table = table.init();
});
