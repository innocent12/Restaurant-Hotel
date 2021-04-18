/**
 * 初始化酒店订单详情对话框
 */
var HotelOrderInfoDlg = {
    hotelOrderInfoData : {}
};

/**
 * 清除数据
 */
HotelOrderInfoDlg.clearData = function() {
    this.hotelOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelOrderInfoDlg.set = function(key, val) {
    this.hotelOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelOrder.layerIndex);
}

/**
 * 收集数据
 */
HotelOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('status')
    .set('cardId')
    .set('tabId')
    .set('roomId')
    .set('orderType')
    .set('subscribeTime')
    .set('departTime')
    .set('createTime')
    .set('orderName');
}

/**
 * 提交添加
 */
HotelOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.HotelOrder.table.refresh();
        HotelOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HotelOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.HotelOrder.table.refresh();
        HotelOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelOrderInfoData);
    ajax.start();
}

$(function() {

});
