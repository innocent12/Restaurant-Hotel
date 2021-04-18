/**
 * 初始化酒店客房详情对话框
 */
var HotelRoomInfoDlg = {
    hotelRoomInfoData : {}
};

/**
 * 清除数据
 */
HotelRoomInfoDlg.clearData = function() {
    this.hotelRoomInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelRoomInfoDlg.set = function(key, val) {
    this.hotelRoomInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelRoomInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelRoomInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelRoom.layerIndex);
}

/**
 * 收集数据
 */
HotelRoomInfoDlg.collectData = function() {
    this
    .set('id')
    .set('floor')
    .set('roomId')
    .set('status')
    .set('description')
    .set('typeId');
}

/**
 * 提交添加
 */
HotelRoomInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelRoom/add", function(data){
        Feng.success("添加成功!");
        window.parent.HotelRoom.table.refresh();
        HotelRoomInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelRoomInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HotelRoomInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelRoom/update", function(data){
        Feng.success("修改成功!");
        window.parent.HotelRoom.table.refresh();
        HotelRoomInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelRoomInfoData);
    ajax.start();
}

$(function() {

});
