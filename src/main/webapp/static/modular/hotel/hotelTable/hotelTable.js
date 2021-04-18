/**
 * 酒店餐桌管理初始化
 */
var HotelTable = {
    id: "HotelTableTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelTable.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用餐人数', field: 'peopleNum', visible: true, align: 'center', valign: 'middle'},
            {title: '餐桌状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '餐桌名', field: 'tabName', visible: true, align: 'center', valign: 'middle'},
            {title: '餐桌价格', field: 'tabPrice', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HotelTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelTable.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加酒店餐桌
 */
HotelTable.openAddHotelTable = function () {
    var index = layer.open({
        type: 2,
        title: '添加酒店餐桌',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelTable/hotelTable_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看酒店餐桌详情
 */
HotelTable.openHotelTableDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '酒店餐桌详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelTable/hotelTable_update/' + HotelTable.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除酒店餐桌
 */
HotelTable.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelTable/delete", function (data) {
            Feng.success("删除成功!");
            HotelTable.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelTableId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = HotelTable.initColumn();
    var table = new BSTable(HotelTable.id, "/hotelTable/list", defaultColunms);
    table.setPaginationType("client");
    HotelTable.table = table.init();
});
