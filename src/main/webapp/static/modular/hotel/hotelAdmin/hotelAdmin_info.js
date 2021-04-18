/**
 * 初始化管理员详情对话框
 */
var HotelAdminInfoDlg = {
    hotelAdminInfoData : {}
};

/**
 * 清除数据
 */
HotelAdminInfoDlg.clearData = function() {
    this.hotelAdminInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelAdminInfoDlg.set = function(key, val) {
    this.hotelAdminInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelAdminInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelAdminInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelAdmin.layerIndex);
}

/**
 * 收集数据
 */
HotelAdminInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('password');
}

/**
 * 提交添加
 */
HotelAdminInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelAdmin/add", function(data){
        Feng.success("添加成功!");
        window.parent.HotelAdmin.table.refresh();
        HotelAdminInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON + "!");
    });
    ajax.set(this.hotelAdminInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HotelAdminInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelAdmin/update", function(data){
        Feng.success("修改成功!");
        window.parent.HotelAdmin.table.refresh();
        HotelAdminInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelAdminInfoData);
    ajax.start();
}

$(function() {

});
