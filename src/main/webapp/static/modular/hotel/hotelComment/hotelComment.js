/**
 * 管理员管理初始化
 */
var HotelComment = {
    id: "HotelCommentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelComment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '留言时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HotelComment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelComment.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加管理员
 */
HotelComment.openAddHotelComment = function () {
    var index = layer.open({
        type: 2,
        title: '添加管理员',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelComment/hotelComment_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看管理员详情
 */
HotelComment.openHotelCommentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '管理员详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelComment/hotelComment_update/' + HotelComment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除管理员
 */
HotelComment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelComment/delete", function (data) {
            Feng.success("删除成功!");
            HotelComment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelCommentId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = HotelComment.initColumn();
    var table = new BSTable(HotelComment.id, "/hotelComment/list", defaultColunms);
    table.setPaginationType("client");
    HotelComment.table = table.init();
});
