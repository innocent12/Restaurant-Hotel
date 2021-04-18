/**
 * 酒店订单管理初始化
 */
var UserOrder = {
    id: "userOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '订单名', field: 'orderName', visible: true, align: "center", valign: "middle"},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '餐桌号', field: 'tabId', visible: true, align: 'center', valign: 'middle'},
        {title: '客房号', field: 'roomId', visible: true, align: 'center', valign: 'middle'},
        {title: '订单类型', field: 'orderType', visible: true, align: 'center', valign: 'middle'},
        {title: '到店时间', field: 'subscribeTime', visible: true, align: 'center', valign: 'middle'},
        {title: '离店时间', field: 'departTime', visible: true, align: 'center', valign: 'middle'},
        {title: '下单时间', field: 'createTime', visible: true, align: 'center', valign: 'middle', formatter: paramsFormatter},
        {title: '操作', field: 'operator',align: "center", valign: "middle",formatter: opFormatter,
            events:{
            'click button[title=取消预约]': cancelOrder,
            'click button[title=删除订单]': deleteOrder
            }}
        ];
};

function cancelOrder(e,v,r,i){
    var ajax = new $ax("/hotelOrder/update", function (data) {
        Feng.success("取消预约成功!");
        UserOrder.table.refresh();
    }, function (data) {
        Feng.error("取消预约失败!" + data.responseJSON.message + "!");
    });
    ajax.set({"id": r['id'], "status": "已取消"})
    ajax.start();
}

function deleteOrder(e,v,r,i){
    var ajax = new $ax("/hotelOrder/update", function (data) {
        Feng.success("删除订单成功!");
        UserOrder.table.refresh();
    }, function (data) {
        Feng.error("删除订单失败!" + data.responseJSON.message + "!");
    });
    ajax.set({"id": r['id'], "status": "已删除"})
    ajax.start();
}

// 操作按钮编辑
function opFormatter(value,row,index,field){
    if (row['status'] === '已完成'||row['status'] === '已取消'){
        return "<button title='删除订单'>删除订单</button>";
    }else if (row['status'] === '已预约'){
        return "<button title='取消预约'>取消预约</button>" + "<button title='删除订单'>删除订单</button>";
    }else {
        return "<button title='删除订单'>删除订单</button>";
    }
}

// 鼠标悬浮显示隐藏文字
function paramsFormatter (value,row,index,field){
    return "<span style='display: block;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;' title='" + value + "'>" + value + "</span>";
}

$(function () {
    var defaultColunms = UserOrder.initColumn();
    var table = new BSTable(UserOrder.id, "/hotelOrder/searchOrder", defaultColunms);
    table.setPaginationType("client");
    UserOrder.table = table.init();
});