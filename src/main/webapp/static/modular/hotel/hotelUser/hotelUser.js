/**
 * 酒店用户管理初始化
 */
var HotelUser = {
    id: "HotelUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '密码', field: 'password', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '电话号', field: 'phone', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HotelUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加酒店用户
 */
HotelUser.openAddHotelUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加酒店用户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelUser/hotelUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看酒店用户详情
 */
HotelUser.openHotelUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '酒店用户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelUser/hotelUser_update/' + HotelUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除酒店用户
 */
HotelUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelUser/delete", function (data) {
            Feng.success("删除成功!");
            HotelUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelUserId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = HotelUser.initColumn();
    var table = new BSTable(HotelUser.id, "/hotelUser/list", defaultColunms);
    table.setPaginationType("client");
    HotelUser.table = table.init();
});
