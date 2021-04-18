/**
 * 管理初始化
 */
var HotelFood = {
    id: "HotelFoodTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HotelFood.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '特色菜名', field: 'foodName', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'picture', visible: true, align: 'center', valign: 'middle',
                formatter: function (value,row,index) {
                    value = 'data:image/jpeg;base64,'+value
                    return '<img src='+value+' width="100", height="100" class="img-rounded" >';}},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HotelFood.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HotelFood.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
HotelFood.openAddHotelFood = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hotelFood/hotelFood_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
HotelFood.openHotelFoodDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hotelFood/hotelFood_update/' + HotelFood.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
HotelFood.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hotelFood/delete", function (data) {
            Feng.success("删除成功!");
            HotelFood.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hotelFoodId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = HotelFood.initColumn();
    var table = new BSTable(HotelFood.id, "/hotelFood/list", defaultColunms);
    table.setPaginationType("client");
    HotelFood.table = table.init();
});
