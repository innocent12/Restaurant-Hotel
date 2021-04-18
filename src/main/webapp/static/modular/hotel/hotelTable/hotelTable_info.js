/**
 * 初始化酒店餐桌详情对话框
 */
var HotelTableInfoDlg = {
    hotelTableInfoData : {}
};

/**
 * 清除数据
 */
HotelTableInfoDlg.clearData = function() {
    this.hotelTableInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelTableInfoDlg.set = function(key, val) {
    this.hotelTableInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelTableInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelTableInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelTable.layerIndex);
}

/**
 * 收集数据
 */
HotelTableInfoDlg.collectData = function() {
    this
    .set('id')
    .set('peopleNum')
    .set('status')
    .set('tabName')
    .set('tabPrice');
}

/**
 * 提交添加
 */
HotelTableInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelTable/add", function(data){
        Feng.success("添加成功!");
        window.parent.HotelTable.table.refresh();
        HotelTableInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelTableInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HotelTableInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelTable/update", function(data){
        Feng.success("修改成功!");
        window.parent.HotelTable.table.refresh();
        HotelTableInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelTableInfoData);
    ajax.start();
}

$(function() {

});
