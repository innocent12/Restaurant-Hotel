/**
 * 初始化酒店用户详情对话框
 */
var HotelUserInfoDlg = {
    hotelUserInfoData : {}
};

/**
 * 清除数据
 */
HotelUserInfoDlg.clearData = function() {
    this.hotelUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelUserInfoDlg.set = function(key, val) {
    this.hotelUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelUserInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelUser.layerIndex);
}

/**
 * 收集数据
 */
HotelUserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('password')
    .set('email')
    .set('phone');
}

/**
 * 提交添加
 */
HotelUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.HotelUser.table.refresh();
        HotelUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HotelUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.HotelUser.table.refresh();
        HotelUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelUserInfoData);
    ajax.start();
}

$(function() {

});
