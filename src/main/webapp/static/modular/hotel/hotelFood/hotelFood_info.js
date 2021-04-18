/**
 * 初始化详情对话框
 */
var HotelFoodInfoDlg = {
    hotelFoodInfoData : {},
};

/**
 * 清除数据
 */
HotelFoodInfoDlg.clearData = function() {
    this.hotelFoodInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelFoodInfoDlg.set = function(key, val) {
    this.hotelFoodInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HotelFoodInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HotelFoodInfoDlg.close = function() {
    parent.layer.close(window.parent.HotelFood.layerIndex);
}

/**
 * 收集数据
 */
HotelFoodInfoDlg.collectData = function() {
    this
    .set('id')
    .set('foodName')
    .set('picture')
    .set('price')
    .set('description')
    .set('file');
}

/**
 * 提交添加
 */
HotelFoodInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var formData = new FormData($("#addFoodForm")[0])
    $.ajax({
        url: Feng.ctxPath + "/hotelFood/add",
        data: formData,
        type: 'post',
        contentType: false,
        processData: false,
        success: function (data){
            Feng.success('添加成功');
            window.parent.HotelFood.table.refresh();
            HotelFoodInfoDlg.close();
        },
        error: function (data) {
            Feng.error("添加失败！" + data.responseJJON.message + "!");
        }
    });
}

/**
 * 提交修改
 */
HotelFoodInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var formData = new FormData($("#editFoodForm")[0])
    $.ajax({
        url: Feng.ctxPath + "/hotelFood/update",
        data: formData,
        type: 'post',
        contentType: false,
        processData: false,
        success: function (data){
            Feng.success('修改成功');
            window.parent.HotelFood.table.refresh();
            HotelFoodInfoDlg.close();
        },
        error: function (data) {
            Feng.error("修改失败！");
        }
    });
}

$(function() {
    $("#file").on("change",function (){
        const pic = $("#file").val();
        if(pic !== ""){
            const pos = pic.lastIndexOf("\\");
            const filename = pic.substring(pos+1);
            $("#picture").val(filename)
            $("#pic_status").val("true");
            console.log(filename)
        }else {
            $("#picture").val("")
        }
    })
});
