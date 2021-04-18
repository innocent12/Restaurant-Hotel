/**
 * 酒店订单管理初始化
 */
var HotelOrder = {
    id: "HotelOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '订单名', field: 'orderName', visible: true, align: "center", valign: "middle"},
        {title: '用户名字', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '状态(1.空闲;2.已预约;3(客房).已入住;4.已完成;0.已取消)', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号', field: 'cardid', visible: true, align: 'center', valign: 'middle', formatter: paramsFormatter},
        {title: '餐桌号', field: 'tabId', visible: true, align: 'center', valign: 'middle'},
        {title: '客房号', field: 'roomId', visible: true, align: 'center', valign: 'middle'},
        {title: '订单类型(1.访客订单;2.餐桌订单)', field: 'orderType', visible: true, align: 'center', valign: 'middle'},
        {title: '到店时间', field: 'subscribeTime', visible: true, align: 'center', valign: 'middle'},
        {title: '离店时间', field: 'departTime', visible: true, align: 'center', valign: 'middle'},
        {title: '订单创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle', formatter: paramsFormatter}
    ];
};
function paramsFormatter (value,row,index,field){
    return "<span style='display: block;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;' title='" + value + "'>" + value + "</span>";
}
/**
 * 检查是否选中
 */
HotelOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加酒店订单
 */
HotelOrder.openAddHotelOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加酒店订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelOrder/hotelOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看酒店订单详情
 */
HotelOrder.openHotelOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '酒店订单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelOrder/hotelOrder_update/' + HotelOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除酒店订单
 */
HotelOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelOrder/delete", function (data) {
            Feng.success("删除成功!");
            HotelOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelOrderId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = HotelOrder.initColumn();
    var table = new BSTable(HotelOrder.id, "/hotelOrder/list", defaultColunms);
    table.setPaginationType("client");
    HotelOrder.table = table.init();
});
