/**
 * 初始化酒店房型详情对话框
 */
var HotelRoomTypeInfoDlg = {
    hotelRoomTypeInfoData : {}
};

/**
 * 清除数据
 */
HotelRoomTypeInfoDlg.clearData = function() {
    this.hotelRoomTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelRoomTypeInfoDlg.set = function(key, val) {
    this.hotelRoomTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    // console.log(this.hotelRoomTypeInfoData)
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelRoomTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelRoomTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelRoomType.layerIndex);
}

/**
 * 收集数据
 */
HotelRoomTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('price')
    .set('type')
    .set('picture')
    .set('description');
}

/**
 * 提交添加
 */
HotelRoomTypeInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    var formData = new FormData($("#addRoomTypeForm")[0])
    $.ajax({
        url: Feng.ctxPath + "/hotelRoomType/add",
        data: formData,
        type: 'post',
        contentType: false,
        processData: false,
        success: function (data){
            Feng.success('添加成功');
            window.parent.HotelRoomType.table.refresh();
            HotelRoomTypeInfoDlg.close();
        },
        error: function (data) {
            Feng.error("添加失败！" + data.responseJJON.message + "!");
        }
    });

}

/**
 * 提交修改
 */
HotelRoomTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var formData = new FormData($("#editRoomTypeForm")[0])
    $.ajax({
        url: Feng.ctxPath + "/hotelRoomType/update",
        data: formData,
        type: 'post',
        contentType: false,
        processData: false,
        success: function (data){
            Feng.success('修改成功');
            window.parent.HotelRoomType.table.refresh();
            HotelRoomTypeInfoDlg.close();
        },
        error: function (data) {
            Feng.error("修改失败！" + data.responseJSON.message + "!");
        }
    });
}

$(function() {
    $("#file").on("change",function (){
        const file = $("#file")[0].files[0];
        if(file !== undefined){
            $("#picture").val(file.name)
            $("#pic_status").val("true");
        }else {
            $("#picture").val("")
        }
    })
});
