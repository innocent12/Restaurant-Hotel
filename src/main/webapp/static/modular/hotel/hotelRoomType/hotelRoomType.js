/**
 * 酒店房型管理初始化
 */
var HotelRoomType = {
    id: "HotelRoomTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelRoomType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '房价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '类型(1.大床;2.单人;)', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'picture', visible: true, align: 'center', valign: 'middle',
                formatter: function (value,row,index) {
                    value = 'data:image/jpeg;base64,'+value
                    return '<img src='+value+' width="100", height="100" class="img-rounded" >';}},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle', formatter: paramsFormatter}
    ];
};
function paramsFormatter (value,row,index,field){
    return "<span style='display: block;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;' title='" + value + "'>" + value + "</span>";
}
/**
 * 检查是否选中
 */
HotelRoomType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelRoomType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加酒店房型
 */
HotelRoomType.openAddHotelRoomType = function () {
    var index = layer.open({
        type: 2,
        title: '添加酒店房型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelRoomType/hotelRoomType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看酒店房型详情
 */
HotelRoomType.openHotelRoomTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '酒店房型详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelRoomType/hotelRoomType_update/' + HotelRoomType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除酒店房型
 */
HotelRoomType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelRoomType/delete", function (data) {
            Feng.success("删除成功!");
            HotelRoomType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelRoomTypeId",this.seItem.id);
        ajax.start();
    }room_id
};

$(function () {
    var defaultColunms = HotelRoomType.initColumn();
    var table = new BSTable(HotelRoomType.id, "/hotelRoomType/list", defaultColunms);
    table.setPaginationType("client");
    HotelRoomType.table = table.init();
});
