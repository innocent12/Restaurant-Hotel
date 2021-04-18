@/*
    select标签中各个参数的说明:
    name : select的名称
    id : select的id
    underline : 是否带分割线
@*/
<div class="form-group">
    <label class="col-sm-3 control-label">${name}</label>
    <div class="col-sm-9">
        <select class="form-control" id="${id}" name="${id}">
            @if(isNotEmpty(value)){
            <option selected>${value}</option>
            @}
            @if(name=="房间状态"){
            <option>空闲</option>
            <option>占用中</option>
            @}else if(name=="订单类型"){
            <option>订房</option>
            <option>订餐</option>
            @}else if(name=="订单状态"){
            <option>已预约</option>
            <option>已入住</option>
            <option>已完成</option>
            <option>已删除</option>
            <option>已取消</option>
            @}
        </select>
        @if(isNotEmpty(hidden)){
            <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


