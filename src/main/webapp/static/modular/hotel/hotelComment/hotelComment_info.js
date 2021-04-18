/**
 * 初始化管理员详情对话框
 */
var HotelCommentInfoDlg = {
    hotelCommentInfoData : {}
};

/**
 * 清除数据
 */
HotelCommentInfoDlg.clearData = function() {
    this.hotelCommentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelCommentInfoDlg.set = function(key, val) {
    this.hotelCommentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelCommentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelCommentInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelComment.layerIndex);
}

/**
 * 收集数据
 */
HotelCommentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('content')
    .set('createTime');
}

/**
 * 提交添加
 */
HotelCommentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelComment/add", function(data){
        Feng.success("添加成功!");
        window.parent.HotelComment.table.refresh();
        HotelCommentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelCommentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HotelCommentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hotelComment/update", function(data){
        Feng.success("修改成功!");
        window.parent.HotelComment.table.refresh();
        HotelCommentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hotelCommentInfoData);
    ajax.start();
}

$(function() {

});
