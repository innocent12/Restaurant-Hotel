/**
 * 酒店客房管理初始化
 */
var HotelRoom = {
    id: "HotelRoomTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelRoom.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '楼层', field: 'floor', visible: true, align: 'center', valign: 'middle'},
            {title: '房间号', field: 'roomId', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '类型Id', field: 'typeId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HotelRoom.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelRoom.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加酒店客房
 */
HotelRoom.openAddHotelRoom = function () {
    var index = layer.open({
        type: 2,
        title: '添加酒店客房',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelRoom/hotelRoom_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看酒店客房详情
 */
HotelRoom.openHotelRoomDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '酒店客房详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelRoom/hotelRoom_update/' + HotelRoom.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除酒店客房
 */
HotelRoom.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelRoom/delete", function (data) {
            Feng.success("删除成功!");
            HotelRoom.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelRoomId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = HotelRoom.initColumn();
    var table = new BSTable(HotelRoom.id, "/hotelRoom/list", defaultColunms);
    table.setPaginationType("client");
    HotelRoom.table = table.init();
});
